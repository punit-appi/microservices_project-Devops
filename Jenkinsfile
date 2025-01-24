pipeline {
    agent any

    environment {
        GIT_CRED = 'git-cred' // GitHub token credentials ID
        DOCKER_NETWORK = 'my_custom_network' // Custom network name
    }

    stages {
        stage('Checkout Code from GitHub') {
            steps {
                script {
                    // Checkout code from GitHub
                    git credentialsId: "${GIT_CRED}", url: 'https://github.com/tohidhanfi20/microservices_project.git', branch: 'main'
                }
            }
        }
        stage('Create Docker Network') {
            steps {
                script {
                    // Check if the Docker network exists, create if it doesn't
                    def networkExists = sh(script: "docker network ls | grep -w ${DOCKER_NETWORK}", returnStatus: true) == 0
                    if (!networkExists) {
                        sh "docker network create ${DOCKER_NETWORK}"
                        echo "Docker network ${DOCKER_NETWORK} created."
                    } else {
                        echo "Docker network ${DOCKER_NETWORK} already exists."
                    }
                }
            }
        }
        stage('Build and Run Welcome Service') {
            steps {
                script {
                    // Build welcome-service Docker image
                    docker.build('welcome-service-image', './welcome-service')

                    // Run the welcome-service container on port 8083
                    docker.image('welcome-service-image').run("-d --network ${DOCKER_NETWORK} -p 8085:8085")
                }
            }
        }
        stage('Build and Run User Service') {
            steps {
                script {
                    // Build user-service Docker image
                    docker.build('user-service-image', './user-service')

                    // Run the user-service container on port 8082
                    docker.image('user-service-image').run("-d --network ${DOCKER_NETWORK} -p 8084:8084")
                }
            }
        }
        stage('Build and Run Auth Service') {
            steps {
                script {
                    // Build auth-service Docker image
                    docker.build('auth-service-image', './auth-service')

                    // Run the auth-service container on port 8081
                    docker.image('auth-service-image').run("-d --network ${DOCKER_NETWORK} -p 8083:8083")
                }
            }
        }
    }
}
