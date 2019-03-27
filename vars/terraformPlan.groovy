def call(Map config) {

    dir("${config.dir}") {
        ansiColor('xterm') {
            withProxyEnv() {
                sh '/usr/bin/terraform plan -var-file="../terraform.tfvars" -out=tfplan -input=false'
            }
        }
    }
}