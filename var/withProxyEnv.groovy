def call(List envVars = [], Closure body) {
    List proxies = []
    List<String> envVariables  = ['GIT_SSH_COMMAND=ssh -o StrictHostKeyChecking=no']
    withAWSParameterStore(credentialsId: '', namePrefixes: '', naming: 'relative', path: "/$PRODUCT_DOMAIN_NAME/$ENVIRONMENT_TYPE", recursive: true, regionName: "$AWS_REGION") {
        script {
            if (env.PROXY_HTTP) {
                proxies << "http_proxy=$PROXY_HTTP"
            }
            if (env.PROXY_HTTPS) {
                proxies << "https_proxy=$PROXY_HTTPS"
            }
            if (env.PROXY_NO) {
                proxies << "no_proxy=$PROXY_NO"
            }
            envVariables.addAll(proxies)
        }
    }
    envVariables.addAll(envVars)
    withEnv(envVariables) {
        body.call()
    }

}