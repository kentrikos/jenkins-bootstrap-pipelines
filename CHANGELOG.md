# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

## [0.11.1] - 2019-10-31
### Fixed
- Job for installation of dashboard in Application account

## [0.11.0] - 2019-10-22
### Added
- Dashboard installation job in Application account
- Dashboard installation job in Operations account
- Job to create vpces in Application account
- Job to create endpoint to vpces in Operations account

## [0.10.0] - 2019-07-30
### Added
- Step for direct terraform destroy for avoiding tf 0.12 cycle problems

## [0.9.0] - 2019-06-14
### Changed 
- Change prometheus installation methods to use helm upgrade

## [0.8.1] - 2019-05-29
### Fixed
- Operations logging pipeline incorrect command syntax to create linked role.

## [0.8.0] - 2019-05-22
### Added
- Jobs to install/destroy VPC Endpoint Service in app account, associated with Kubernetes ingress

## [0.7.0] - 2019-05-10
### Added
- Added ability to pass `extraArgs` to terraformPlan library function (used to pass additional arguments to terraform exec)
- Added new pipelines to LMA section to install/destroy logging infrastructure in Operations account
- Added new pipelines to LMA section to install/destroy logging infrastructure in Application account

## [0.6.0] - 2019-04-15
### Added 
- Added new library steps to switch kubectl context
- Added ECR Create repo step to library
- Added step to clone app repo to library

### Changed 
- Update jobs to use new steps to switch kubectl context

## [0.5.1] - 2019-04-02
### Fixed
-Fix double `agent any` in destroy eks on application Jenkinsfile

## [0.5.0] - 2019-04-01
### Changed 
- Add import library 'kentrikos-shared-library' to each pipeline
- Moved withProxyEnv to library 'kentrikos-shared-library'
- New parameter for R53 HostedZoneID for LMA jobs (jx-related params were used before)

### Added 
- Added automation for Grafana's data-source for Prometheus on application account
- Added automation to create dashboards for both Prometheus datasources (operations and application)
- Added automation for Grafana's persistence (based on K8s PVs)
- Folder vars for pipeline library 'kentrikos-shared-library' 
- Pipeline steps for :

   ```groovy
    gitCloneConfigRepo
    terraformApplyDestroy
    terraformApply
    terraformInitApplication
    terraformInitOperations
    terraformPlanDestroy
    terraformPlan
    withProxyEnv
    ```

## [0.4.1] - 2019-03-25
### Fixed
- Fixed destroy job for application eks

## [0.4.0] - 2019-03-21
### Changed
- Adjustments to config repo change
   ```
   operations/$REGION/env -> operations/$REGION/env-kops
                          -> operations/$REGION/env-eks
   
   application/$REGION/env -> application/$REGION/env-kops
                           -> application/$REGION/env-eks
   ```
- Jobs for install kops and eks in separate directories

### Added
- Added jobs to destroy kops and eks on app account

## [0.3.0] - 2019-03-11
- Added application/kubernetes/install_eks/Jenkinsfile (Jenkins Core pipeline)

## [0.2.1] - 2019-03-01
### Added
- Added job to uninstall jx

### Changed
- Add sub dirs in jx job 

### Deprecated 
- operations/jx/Jenkinsfile will be removed use operations/jx/install/Jenkinsfile

### Fixed
- Fixed setting no proxy in jenkins installed for jx by adding quotas to configs GH-20

## [0.2.0] - 2019-02-25
### Changed
- Rewrite configs to support multi deployment

## [0.1.0] - 2019-02-05
### Added
- Pining versions
- This CHANGELOG file


