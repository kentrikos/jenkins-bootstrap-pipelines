#!groovy

def call(Map config) {

    dir("${config.dir}") {
        ansiColor('xterm') {
            withProxyEnv() {
                sh '/usr/bin/terraform apply -input=false tfplan'
            }
        }
        archiveArtifacts artifacts: 'outputs/**', allowEmptyArchive: true
    }
}