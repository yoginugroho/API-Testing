pipeline {
  agent any
  stages {
    stage('API testing') {
      steps {
        sh 'docker container start maven1 -w mvn clean install'
      }
    }

  }
}