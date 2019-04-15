#!groovy

def call(Map config) {
    withProxyEnv() {
        sh "aws ecr create-repository --region $AWS_REGION --repository-name ${config.repo_name}||true"
    }
}