pipeline {
    agent any
    tools {
        maven 'jenkins-maven'
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
                withSonarQubeEnv('SonarQube') {
                    bat 'mvn clean package'
                    bat ''' mvn clean verify sonar:sonar -Dsonar.projectKey=xmart-java -Dsonar.projectName='xmart-java' -Dsonar.host.url=http://localhost:9000 '''
                    echo 'SonarQube Analysis Completed'
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
                    withCredentials([string(credentialsId: 'dockerhub-password', variable: 'dockerhub-password-binding')]) {
                        bat ''' docker login -u mulyadikamsul -p "%dockerhub-password-binding%" '''
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
    }
}