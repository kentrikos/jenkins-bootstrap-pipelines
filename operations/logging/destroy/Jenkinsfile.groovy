#!groovy

import groovy.json.JsonOutput

library 'kentrikos-shared-library'
pipeline {

    agent any

    parameters {
        choice(name: 'K8S_FLAVOR', choices: ["eks", "kops"],
                description: 'Choose type of Kubernetes cluster (required for kops)')
    }
    environment {
        SUB_COMPONENT = "logging"
        OPERATION_DIR = "operations/$AWS_REGION/$SUB_COMPONENT"

        APP_NAME = 'fluentd-elasticsearch'
        ECR_REPO_NAME = "$PRODUCT_DOMAIN_NAME-$ENVIRONMENT_TYPE/$APP_NAME"
        ECR_REPO = "$AWS_OPERATIONS_ACCOUNT_NUMBER" + ".dkr.ecr." + "$AWS_REGION" + ".amazonaws.com/$ECR_REPO_NAME"
    }
    stages {
        stage('Git clone') {
            steps {
                gitCloneConfigRepo()
            }
        }
        stage('Switch K8S context') {
            steps {
                kubectlSwitchContextOps()
            }
        }
        stage('Init') {
            steps {
                terraformInitOperations dir: "$OPERATION_DIR", subcomponent: "$SUB_COMPONENT"
            }
        }
        stage('Plan') {
            steps {
                script {
                    K8S_CLUSTER_NAME = sh(script: "kubectl config current-context", returnStdout: true).trim()
                    terraformPlanDestroy dir: "$OPERATION_DIR", extraArgs: "-var=\"cluster_context=${K8S_CLUSTER_NAME}\" -var=\"fluentd_image_repository=${ECR_REPO}\" -var=\"fluentd_image_tag=latest\""
                }
            }
        }
        stage('Apply') {
            input {
                message "Should we continue?"
                ok "Yes, we should."
            }
            steps {
                terraformApplyDestroy dir: "$OPERATION_DIR"
            }
        }
    }
}