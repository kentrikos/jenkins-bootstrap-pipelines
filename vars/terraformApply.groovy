#!groovy

def call(Map config) {
    with_aws = false
    if (config.containsKey('withAWS')) {
        with_aws = config.withAWS
    }

    if (with_aws) {
        dir("${config.dir}") {
            ansiColor('xterm') {
                withProxyEnv() {
                    withAWS(role: "$CROSS_ACCOUNT_ROLE_NAME", roleAccount: "$AWS_APPLICATION_ACCOUNT_NUMBER") {
                        sh '/usr/bin/terraform apply -input=false tfplan'
                    }
                }
            }
            archiveArtifacts artifacts: 'outputs/**', allowEmptyArchive: true
        }
    } else {
        dir("${config.dir}") {
            ansiColor('xterm') {
                withProxyEnv() {
                    sh '/usr/bin/terraform apply -input=false tfplan'
                }
            }
            archiveArtifacts artifacts: 'outputs/**', allowEmptyArchive: true
        }
    }

}