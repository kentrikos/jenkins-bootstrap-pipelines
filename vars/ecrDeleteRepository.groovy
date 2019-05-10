#!groovy

def call(Map config) {
    withProxyEnv() {
        sh "aws ecr delete-repository --force --region $AWS_REGION --repository-name ${config.repo_name}||true"
    }
}