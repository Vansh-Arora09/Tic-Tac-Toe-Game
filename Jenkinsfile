pipeline {
    agent any
    
    environment {
        DOCKER_IMAGE = 'tictactoe-pipeline-game'
        REGISTRY_USER = 'vansharora09'
    }
    
    stages {
        stage('Automated Checkout') {
            steps {
                checkout scm
            }
        }
        
        stage('Maven Packaging') {
            steps {
                // Changed from 'bat' to 'sh' for Linux container compatibility
                sh 'mvn clean package -DskipTests'
            }
        }
        
        stage('Docker Blueprint Build') {
            steps {
                // Changed from 'bat' to 'sh'
                sh "docker build -t ${DOCKER_IMAGE}:${BUILD_NUMBER} ."
                sh "docker tag ${DOCKER_IMAGE}:${BUILD_NUMBER} ${REGISTRY_USER}/${DOCKER_IMAGE}:latest"
            }
        }
        
        stage('Registry Distribution') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials', usernameVariable: 'USER', passwordVariable: 'PASS')]) {
                    // Changed from 'bat' to 'sh'
                    sh "docker login -u ${USER} -p ${PASS}"
                    sh "docker push ${REGISTRY_USER}/${DOCKER_IMAGE}:latest"
                }
            }
        }
    }
}
