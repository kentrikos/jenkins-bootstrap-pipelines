#!groovy

library 'kentrikos-shared-library'
pipeline {

    agent any

    parameters {
        choice(name: 'K8S_FLAVOR', choices: ["eks", "kops"],
               description: 'Choose type of Kubernetes cluster (required for kops)')
    }
    environment {
        OPERATION_DIR = "operations/$AWS_REGION/env-$K8S_FLAVOR"
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

        stage('Destroy Prometheus/operations') {
            steps {
                dir("$OPERATION_DIR") {
                    withProxyEnv() {
                        script {
                            sh 'helm delete --purge dashboard'
                        }
                    }
                }
            }
        }
    }
}

