pipeline {
    agent any

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
        string(
                name: 'BRANCH',
                defaultValue: 'main',
                description: 'Git branch to run the tests on (e.g. main, feature/my-task)'
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
                    currentBuild.displayName = "#${env.BUILD_NUMBER} ${branch}"

                    // Reuses repo URL and credentials from the job's SCM config,
                    // but checks out the branch chosen in the BRANCH parameter.
                    checkout([
                            $class           : 'GitSCM',
                            branches         : [[name: "*/${branch}"]],
                            userRemoteConfigs: scm.userRemoteConfigs
                    ])
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

                    sh "mvn clean test -Dsurefire.suiteXmlFiles=${suiteFile}"
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
