def call() {
    stage('Git local config') {
        steps {
            sh 'git config --global --add user.name "example"'
            sh 'git config --global --add user.email "example@example.com"'
        }
    }
    stage('Git clone') {
        steps {
            cleanWs()
            git credentialsId: 'bitbucket-key', url: '$CONFIG_REPO_URL'
        }
    }

}