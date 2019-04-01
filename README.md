# About

This repo contain Jenkins pipeline definition for project Kentrikos.

## Requirements

Best option to use is from jenkins installed by terraform module from [kentrikos/terraform-aws-bootstrap-jenkins](https://github.com/kentrikos/terraform-aws-bootstrap-jenkins) 

Job need be run on node with installed:

* kubectl
* terraform
* jx
* helm
* tiller

## Layount

### iam

This pipeline job will generate account specific iam policies for project Kentrikos.

##### auto 

This job creates IAM policies on AWS account and updates Terraform state.

##### manual

This job only generates local json files for policies and does not update Terraform state.

### kubernetes

This pipeline job will install kubernetes cluster using terraform.

### jx

This pipeline job will install Jenkins using jx installer on kubernetes.