def call() {

    steps {
        cleanWs()
        sh 'git config --global --add user.name "example"'
        sh 'git config --global --add user.email "example@example.com"'
        git credentialsId: 'bitbucket-key', url: '$CONFIG_REPO_URL'
    }
}