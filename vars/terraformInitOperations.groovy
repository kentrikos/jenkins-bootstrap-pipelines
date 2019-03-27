def call(Map config) {

    dir("${config.dir}") {
        withProxyEnv() {
            sshagent(['bitbucket-key']) {
                sh '''#!/bin/bash -x
                            terraform init -input=false \
                            -backend-config="region=${AWS_REGION}" \
                            -backend-config="bucket=tf-${AWS_OPERATIONS_ACCOUNT_NUMBER}-ops-${AWS_REGION}-${PRODUCT_DOMAIN_NAME}-${ENVIRONMENT_TYPE}" \
                            -backend-config="dynamodb_table=tf-state-lock-bootstrap-${PRODUCT_DOMAIN_NAME}-${ENVIRONMENT_TYPE}" \
                            -backend-config="key=tf/tf-aws-product-domain-${PRODUCT_DOMAIN_NAME}-env-${ENVIRONMENT_TYPE}/${config.subcomponent}/terraform.tfstate"
                            '''
            }
        }
    }
}