pipeline {
    agent any
    tools {
        maven 'maven'
    }

    environment {
        BUILD_NUMBER_ENV = "${env.BUILD_NUMBER}"
        TEXT_SUCCESS_BUILD = "[#${env.BUILD_NUMBER}] Project Name : ${JOB_NAME} is Success"
        TEXT_FAILURE_BUILD = "[#${env.BUILD_NUMBER}] Project Name : ${JOB_NAME} is Failure"
    }

    stages {
        stage('Git Checkout') {
            steps {
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/MulyadiLikmi/xmart-java']])
                bat 'mvn clean install'
                echo 'Git Checkout Completed'
            }
        }
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonarqube-server') { // Use the correct SonarQube configuration name
                    script {
                        def sonarScannerHome = tool name: 'SonarScan', type: 'hudson.plugins.sonar.SonarRunnerInstallation'
                        bat """${sonarScannerHome}\\bin\\sonar-scanner.bat -Dsonar.projectKey=xmart-java -Dsonar.projectName=xmart-java -Dsonar.sources=src -Dsonar.sourceEncoding=UTF-8 -Dsonar.java.binaries=target/classes"""
                    }
                    echo 'SonarQube Analysis Completed'
                }
            }
        }
        stage('Sleep') {
            steps {
                script {
                    sleep 60
                    echo 'Sleep Completed'
                }
            }
        }

        stage("Quality Gate") {
            steps {
                waitForQualityGate abortPipeline: true
                echo 'Quality Gate Completed'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    bat 'docker build -t mulyadikamsul/xmart-java .'
                    echo 'Build Docker Image Completed'
                }
            }
        }

        stage('Docker Push') {
            steps {
                script {
                    withCredentials([string(credentialsId: 'dockerhub-pwd', variable: 'dockerhub-password')]) {
                        bat ''' docker login -u mulyadikamsul -p "%dockerhub-password%" '''
                    }
                    bat 'docker push mulyadikamsul/xmart-java'
                }
            }
        }

        stage ('Docker Run') {
            steps {
                script {
                    bat 'docker run -d --name xmart-java -p 8099:8080 mulyadikamsul/xmart-java'
                    echo 'Docker Run Completed'
                }
            }
        }

    }
    post {
        always {
            bat 'docker logout'
        }
        success {
            script{
                 withCredentials([string(credentialsId: 'telegram-token', variable: 'telegramToken'), string(credentialsId: 'telegram-chat-id', variable: 'chatId')]) {
                    bat ''' curl -s -X POST https://api.telegram.org/bot"%telegramToken%"/sendMessage -d chat_id="%chatId%" -d text="%TEXT_SUCCESS_BUILD%" '''
                 }
            }
        }
        failure {
            script{
                withCredentials([string(credentialsId: 'telegram-token', variable: 'telegramToken'), string(credentialsId: 'telegram-chat-id', variable: 'chatId')]) {
                    bat ''' curl -s -X POST https://api.telegram.org/bot"%telegramToken%"/sendMessage -d chat_id="%chatId%" -d text="%TEXT_FAILURE_BUILD%" '''
                }
            }
        }
    }
}