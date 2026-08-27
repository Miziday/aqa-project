pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Miziday/aqa-project'
            }
        }
        stage('Run tests') {
            steps {
                sh 'mvn clean test -Dselenide.remote=http://selenium-chrome:4444/wd/hub'
            }
        }
    }

    post {
        always {
            junit '**/target/surefire-reports/*.xml'
        }
    }
}
