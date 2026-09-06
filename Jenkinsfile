pipeline {
    agent any

    parameters {
        choice(
                name: 'TEST_SUITE',
                choices: ['all', 'api', 'ui'],
                description: 'Choose which TestNG suite to run'
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
    }
}
