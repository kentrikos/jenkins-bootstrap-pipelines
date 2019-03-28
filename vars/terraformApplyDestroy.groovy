#!groovy

def call(Map config) {

    dir("${config.dir}") {
        ansiColor('xterm') {
            withProxyEnv() {
                sh '/usr/bin/terraform apply -input=false tfplandestroy'
            }
        }
        archiveArtifacts artifacts: 'outputs/**', allowEmptyArchive: true
    }
}