import org.jenkinsci.plugins.workflow.steps.FlowInterruptedException

def retryForTimeoutExceeded(count = 3, Closure closure) {
    for (int i = 1; i <= count; i++) {
        try {
            closure()
            break
        } catch (FlowInterruptedException error) {
            int retriesLeft = count - i
            def hasTimeoutExceeded = error.causes[0].getClass().toString() == 'class org.jenkinsci.plugins.workflow.steps.TimeoutStepExecution$ExceededTimeout'
            println "Timeout Exceeded for closure.\nRetries left: $retriesLeft"
            if (retriesLeft == 0 || !hasTimeoutExceeded) {
                throw error
            }
        }
    }
}

pipeline {
    agent any
    tools {
        maven 'maven'
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
        stage('Quality Gate') {
            steps {
                script {
                    retryForTimeoutExceeded {
                        timeout(time: 5, unit: 'MINUTES') {
                            // Just in case something goes wrong, pipeline will be killed after a timeout
                            def qg = waitForQualityGate() // Reuse taskId previously collected by withSonarQubeEnv
                            if (qg.status != 'OK') {
                                error "Pipeline aborted due to sonar quality gate failure: ${qg.status}"
                            }
                        }
                    }
                }
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
                        bat '''docker login -u mulyadikamsul -p "%dockerhub-password-binding%"'''
                    }
                    bat 'docker push mulyadikamsul/xmart-java'
                }
            }
        }
        stage('Docker Run') {
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
