pipeline {
    agent any

    stages {
        
        stage('Checkout') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], userRemoteConfigs: [[url: 'https://github.com/Namo-shubham/SpringWebApp.git']]])
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install -Dmaven.test.failure.ignore=true' // build the WAR artifact using Maven
                archiveArtifacts artifacts: 'target/*.war' // archive the WAR artifact
            }
        }

        stage('Deploy') {
            environment {
                TOMCAT_URL = 'http://3.216.125.233:8080/' // the URL of the Tomcat server
                WAR_FILE = 'target/*.war' // the path to the WAR artifact
                CONTEXT_PATH = '/myapp1' // the context path of the web application
                USERNAME = 'admin' // the username of a user with the manager-script role
                PASSWORD = 'admin' // the password of the user
          
            }

            steps {
                withCredentials([usernamePassword(credentialsId: 'tomcat_deployer', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                    sh 'curl -T $WAR_FILE $TOMCAT_URL/manager/text/deploy?path=$CONTEXT_PATH -u $USERNAME:$PASSWORD' // deploy the WAR artifact using cURL
                }
            }
        }
    }
}
