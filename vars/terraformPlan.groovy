def call(Map config) {
    stage('Plan') {
        steps {
            dir("${config.dir}") {
                ansiColor('xterm') {
                    withProxyEnv() {
                        sh '/usr/bin/terraform plan -var-file="../terraform.tfvars" -out=tfplan -input=false'
                    }
                }
            }
        }
    }
}