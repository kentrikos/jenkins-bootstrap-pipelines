#!groovy

def call(Map config) {

    dir("${config.dir}") {
        ansiColor('xterm') {
            withProxyEnv() {
                sh '/usr/bin/terraform plan -var-file="../terraform.tfvars" -out=tfplandestroy -input=false -destroy'
            }
        }
    }
}