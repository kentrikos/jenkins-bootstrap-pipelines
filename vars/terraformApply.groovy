def call(Map config) {

    input {
        message "Should we continue?"
        ok "Yes, we should."
    }
    steps {
        dir("${config.dir}") {
            ansiColor('xterm') {
                withProxyEnv() {
                    sh '/usr/bin/terraform apply -input=false tfplan'
                }
            }
        }
    }

}