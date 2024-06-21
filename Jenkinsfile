pipeline {
    agent any
      tools {
        maven 'Maven' // Maven tool installation defined in Jenkins configuration
     }



    stages {


         stage('Checkout') {
             steps {
                //checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[credentialsId: 'jenkins', url: 'https://github.com/nin-oh/pfa-devops']])
                bat 'mvn clean install'
             }
         }

         stage('Build and Push Docker Image with Docker Compose') {
             steps {
                 script {

                        bat 'docker login -u "sabersimpx" -p "sabersimp@12"'
                         // Build and push the image using Docker Compose
                         bat 'docker build -t sabersimpx/revormclass:mid .'
                       bat 'docker push sabersimpx/revormclass:mid'

              }
          }
         }


    }

    // post {
    //      always {
    //         cleanWs()
    //     }
    //  }
}
