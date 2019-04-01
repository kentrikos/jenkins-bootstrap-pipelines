#!groovy

def call(Map config) {
    if (config.withAWS) {
        dir("${config.dir}") {
            withProxyEnv() {
                withAWS(role: "$CROSS_ACCOUNT_ROLE_NAME", roleAccount: "$AWS_APPLICATION_ACCOUNT_NUMBER") {
                    sshagent(['bitbucket-key']) {
                        sh """#!/bin/bash -x
                                  terraform init -input=false \
                                    -backend-config="region=${AWS_REGION}" \
                                    -backend-config="bucket=tf-${AWS_APPLICATION_ACCOUNT_NUMBER}-app-${AWS_REGION}-${PRODUCT_DOMAIN_NAME}-${ENVIRONMENT_TYPE}" \
                                    -backend-config="dynamodb_table=tf-state-lock-bootstrap-${PRODUCT_DOMAIN_NAME}-${ENVIRONMENT_TYPE}" \
                                    -backend-config="key=tf/tf-aws-product-domain-${PRODUCT_DOMAIN_NAME}-env-${ENVIRONMENT_TYPE}/${config.subcomponent}/terraform.tfstate"
                                    """
                    }
                }
            }
        }
    } else {
        dir("${config.dir}") {
            withProxyEnv() {
                sshagent(['bitbucket-key']) {
                        sh """#!/bin/bash -x
                                terraform init -input=false \
                                  -backend-config="region=${AWS_REGION}" \
                                  -backend-config="bucket=tf-${AWS_APPLICATION_ACCOUNT_NUMBER}-app-${AWS_REGION}-${PRODUCT_DOMAIN_NAME}-${ENVIRONMENT_TYPE}" \
                                  -backend-config="dynamodb_table=tf-state-lock-bootstrap-${PRODUCT_DOMAIN_NAME}-${ENVIRONMENT_TYPE}" \
                                  -backend-config="key=tf/tf-aws-product-domain-${PRODUCT_DOMAIN_NAME}-env-${ENVIRONMENT_TYPE}/${config.subcomponent}/terraform.tfstate" \
                                  -backend-config="role_arn=arn:aws:iam::${AWS_APPLICATION_ACCOUNT_NUMBER}:role/KENTRIKOS_${AWS_REGION}_${PRODUCT_DOMAIN_NAME}_${ENVIRONMENT_TYPE}_CrossAccount"
                                """
                }
            }
        }
    }
}