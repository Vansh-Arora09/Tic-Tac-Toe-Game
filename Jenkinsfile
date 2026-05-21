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
        
        stage('Docker Pipeline Build') {
            steps {
                sh "docker build -t ${DOCKER_IMAGE}:${BUILD_NUMBER} ."
                sh "docker tag ${DOCKER_IMAGE}:${BUILD_NUMBER} ${REGISTRY_USER}/${DOCKER_IMAGE}:latest"
            }
        }
        
        stage('Registry Distribution') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials', usernameVariable: 'USER', passwordVariable: 'PASS')]) {
                    sh "echo ${PASS} | docker login -u ${USER} --password-stdin"
                    sh "docker push ${REGISTRY_USER}/${DOCKER_IMAGE}:latest"
                }
            }
        }
    }
    
    post {
        success {
            echo "Pipeline built and pushed successfully!"
        }
        // Removed cleanWs() from always to prevent the FilePath context error if a step breaks
    }
}
