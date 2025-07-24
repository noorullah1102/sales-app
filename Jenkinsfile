pipeline {
    agent any

    stages {
        stage('Build JAR') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t springboot-xampp-app .'
            }
        }

        stage('Run App') {
            steps {
                sh '''
                    docker stop springboot-app || true
                    docker rm springboot-app || true
                    docker run -d --name springboot-app \
                        --network jenkins \
                        -p 8080:8080 \
                        springboot-xampp-app
                '''
            }
        }
    }
}
