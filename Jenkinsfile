pipeline {
    agent any
      tools {
        maven 'Maven' // Maven tool installation defined in Jenkins configuration
     }



    stages {


        stage('Checkout') {
            steps {
               //checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[credentialsId: 'jenkins', url: 'https://github.com/nin-oh/pfa-devops']])
               sh 'mvn clean install'
            }
        }

         stage('Build and Push Docker Image with Docker Compose') {
             steps {
                 script {

                        sh 'docker login -u "sabersimpx" -p "sabersimp@12"'
                         // Build and push the image using Docker Compose
                         sh 'docker-compose build'
                       sh 'docker-compose push'

              }
          }
         }


    }

    post {
         always {
            cleanWs()
        }
     }
}
