#!groovy

def call(Map config) {
    println('Get K8s cluster flavor')

    script {
        if (env.K8S_FLAVOR == "eks") {
            K8S_CLUSTER_NAME = "arn:aws:eks:$AWS_REGION:$AWS_OPERATIONS_ACCOUNT_NUMBER:cluster/$AWS_REGION-$PRODUCT_DOMAIN_NAME-$ENVIRONMENT_TYPE-ops"
        } else {
            K8S_CLUSTER_NAME = "$AWS_REGION-$PRODUCT_DOMAIN_NAME-$ENVIRONMENT_TYPE-ops.k8s.local"
        }
        println K8S_CLUSTER_NAME
    }

    println('Switch kubectl context to ops')

    script {
        sh "kubectl config use-context $K8S_CLUSTER_NAME"
    }
}
