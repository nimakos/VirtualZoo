pipeline {
    environment {
        DOCKER_HUB_LOGIN = credentials('docker-hub')
        IMAGE_NAME = "spring-boot-mvc-image-without-sql"
        DOCKER_IMAGE_NAME = '$DOCKER_HUB_LOGIN_USR/$IMAGE_NAME'
        DOCKER_IMAGE = ''
    }
    agent any

    tools {
        maven 'Maven' //Name should be exactly like this
    }

    triggers {
        pollSCM '* * * * *'
    }

    stages {
        stage('1. Build JAR file') {
            steps {
            //instead of 'clean install' we use 'package'
                sh 'mvn -Dmaven.test.skip=true package'
            }
        }

        stage('2. Testing Application') {
            steps {
                sh 'mvn test'
            }
        }

        stage('3. Build Docker image') {
            steps {
                script {
                    DOCKER_IMAGE = docker.build DOCKER_IMAGE_NAME + ":" + "$BUILD_NUMBER"
                }
            }
        }

        stage('4. Push Docker image') {
            steps {
                script {
                    docker.withRegistry('', 'docker-hub') {
                        DOCKER_IMAGE.push()
                    }
                }
            }
        }

        stage('5. Remove Unused local Docker image') {
            steps{
                sh "docker rmi $DOCKER_IMAGE_NAME:$BUILD_NUMBER"
            }
        }
    }
}