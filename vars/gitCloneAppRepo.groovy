#!groovy

def call(Map config) {

    cleanWs()
    sh 'git config --global --add user.name "example"'
    sh 'git config --global --add user.email "example@example.com"'
    git credentialsId: 'bitbucket-key', url: "${config.repo}"

}