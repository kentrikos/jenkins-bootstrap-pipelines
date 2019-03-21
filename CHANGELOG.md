# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]
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


