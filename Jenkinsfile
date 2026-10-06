pipeline {
    agent any

    environment {
        BASE_VERSION = '1.0.0'
    }

    options {
        buildDiscarder(logRotator(
                daysToKeepStr: '14',
                numToKeepStr: '5',
                artifactDaysToKeepStr: '14',
                artifactNumToKeepStr: '5'
        ))
    }

    triggers {
        // Starts this pipeline when GitHub sends a push webhook to Jenkins.
        githubPush()
    }

    parameters {
        gitParameter(
                    name: 'BRANCH',
                    type: 'PT_BRANCH',
                    branchFilter: 'origin/(.*)',   // убирает префикс origin/ из имён
                    defaultValue: 'main',
                    sortMode: 'DESCENDING_SMART',
                    selectedValue: 'DEFAULT',
                    quickFilterEnabled: true,
                    description: 'Git branch to run the tests on'
            )
        choice(
                name: 'TEST_SUITE',
                choices: ['all', 'api', 'ui'],
                description: 'Choose which TestNG suite to run'
        )
    }

    stages {
        stage('Checkout') {
            steps {
                script {
                    def branch = params.BRANCH?.trim() ?: 'main'

                    // Reuses repo URL and credentials from the job's SCM config,
                    // but checks out the branch chosen in the BRANCH parameter.
                    // Full history is required to count commits (no shallow clone).
                    checkout([
                            $class           : 'GitSCM',
                            branches         : [[name: "*/${branch}"]],
                            userRemoteConfigs: scm.userRemoteConfigs,
                            extensions       : [
                                    [$class: 'CloneOption', shallow: false]
                            ]
                    ])
                }
            }
        }
        stage('Version') {
            steps {
                script {
                    def branch = params.BRANCH?.trim() ?: 'main'

                    if (branch == 'main') {
                        // main: total number of commits in the branch history
                        def commits = sh(script: 'git rev-list --count HEAD', returnStdout: true).trim()
                        env.APP_VERSION = "${env.BASE_VERSION}-${commits}"
                    } else {
                        // other branches: number of commits made in this branch since it diverged from main
                        def commits = sh(script: 'git rev-list --count origin/main..HEAD', returnStdout: true).trim()
                        def slug = branch.replaceAll('[^A-Za-z0-9]+', '-')
                        env.APP_VERSION = "${env.BASE_VERSION}-${slug}-${commits}"
                    }

                    currentBuild.displayName = "#${env.BUILD_NUMBER} ${env.APP_VERSION}"
                    echo "Version: ${env.APP_VERSION}"
                }
            }
        }
        stage('Run tests') {
            steps {
                script {
                    def suiteFile = 'testng-master.xml'

                    if (params.TEST_SUITE == 'api') {
                        suiteFile = 'testng-api.xml'
                    } else if (params.TEST_SUITE == 'ui') {
                        suiteFile = 'testng-ui.xml'
                    }

                    sh "mvn clean test -Drevision=${env.APP_VERSION} -Dsurefire.suiteXmlFiles=${suiteFile}"
                }
            }
        }
    }

    post {
        always {
            junit '**/target/surefire-reports/*.xml'

            allure([
                includeProperties: false,
                jdk: '',
                properties: [],
                reportBuildPolicy: 'ALWAYS',
                results: [[path: 'target/allure-results']]
            ])
        }

        cleanup {
            deleteDir()
        }
    }
}
