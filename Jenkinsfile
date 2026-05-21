pipeline {
    agent any
    
    // Generic Best Practice: Add a tool block so Jenkins injects the Docker client binary
    tools {
        dockerTool 'default' // This matches the exact name we configured in global tools
    }
    
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
                // Using your new plugin capability to cleanly execute Docker build operations
                sh "docker build -t ${DOCKER_IMAGE}:${BUILD_NUMBER} ."
                sh "docker tag ${DOCKER_IMAGE}:${BUILD_NUMBER} ${REGISTRY_USER}/${DOCKER_IMAGE}:latest"
            }
        }
        
        stage('Registry Distribution') {
            steps {
                // Using credentials securely to push to Docker Hub
                withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials', usernameVariable: 'USER', passwordVariable: 'PASS')]) {
                    sh "echo ${PASS} | docker login -u ${USER} --password-stdin"
                    sh "docker push ${REGISTRY_USER}/${DOCKER_IMAGE}:latest"
                }
            }
        }
    }
    
    // Generic Best Practice: Post-actions to clean up or display visual trends
    post {
        always {
            // Cleans up the workspace files after the build so your host drive doesn't run out of storage
            cleanWs()
        }
        success {
            echo "Pipeline built and pushed successfully! Check out Stage View on your dashboard."
        }
    }
}
