#!groovy

def call(Map config) {
    with_aws = false
    extraArgs = ""
    if (config.containsKey('withAWS')) {
        with_aws = config.withAWS
    }

    if (config.containsKey('extraArgs')) {
        extraArgs = config.extraArgs
    }



    if (with_aws) {
        dir("${config.dir}") {
            ansiColor('xterm') {
                withProxyEnv() {
                    withAWS(role: "$CROSS_ACCOUNT_ROLE_NAME", roleAccount: "$AWS_APPLICATION_ACCOUNT_NUMBER") {
                        sh "/usr/bin/terraform plan -var-file=\"../terraform.tfvars\" -out=tfplan -input=false ${extraArgs}"
                    }
                }
            }
        }
    } else {
        dir("${config.dir}") {
            ansiColor('xterm') {
                withProxyEnv() {
                    sh "/usr/bin/terraform plan -var-file=\"../terraform.tfvars\" -out=tfplan -input=false ${extraArgs}"
                }
            }
        }
    }
}

