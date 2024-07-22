pipeline {
    agent any
    tools {
        maven 'maven'
    }
    stages {
        stage('Build Maven') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], userRemoteConfigs: [[url: 'https://github.com/HusanboyJorayev/simple_order_system.git']]])
                bat 'mvn clean install'
            }
        }
        stage("Build docker image"){
            steps{
                script{
                    bat "docker build -t order_system ."
                }

            }
        }
    }
}