#!groovy

def call(Map config) {
    withProxyEnv() {
        sh "aws ecr create-repository --repository-name ${config.repo_name}||true"
    }
}