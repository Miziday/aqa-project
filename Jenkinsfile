pipeline {
    agent any

    parameters {
        choice(
                name: 'TEST_SUITE',
                choices: ['all', 'api', 'ui'],
                description: 'Choose which tests to run'
        )
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Miziday/aqa-project'
            }
        }
        stage('Run tests') {
            steps {
                script {
                    def testCommand = 'mvn clean test'

                    if (params.TEST_SUITE == 'api') {
                        testCommand = 'mvn clean test -Papi'
                    } else if (params.TEST_SUITE == 'ui') {
                        testCommand = 'mvn clean test -Pui'
                    }

                    sh testCommand
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
    }
}
