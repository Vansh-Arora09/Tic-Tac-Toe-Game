pipeline {
    agent any
    
    environment {
        DOCKER_IMAGE = 'tictactoe-pipeline-game'
        REGISTRY_USER = 'vansharora09'
    }
    
    stages {
        stage('Automated Checkout') {
            steps {
                // Step 1: Pull fresh code from GitHub
                checkout scm
            }
        }
        
        stage('Docker Pipeline Build') {
            steps {
                // Step 2: Docker builds the JAR inside the container AND wraps it!
                sh "docker build -t ${DOCKER_IMAGE}:${BUILD_NUMBER} ."
                sh "docker tag ${DOCKER_IMAGE}:${BUILD_NUMBER} ${REGISTRY_USER}/${DOCKER_IMAGE}:latest"
            }
        }
        
        stage('Registry Distribution') {
            steps {
                // Step 3: Authenticate and push to your registry
                withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials', usernameVariable: 'USER', passwordVariable: 'PASS')]) {
                    sh "docker login -u ${USER} -p ${PASS}"
                    sh "docker push ${REGISTRY_USER}/${DOCKER_IMAGE}:latest"
                }
            }
        }
    }
}
