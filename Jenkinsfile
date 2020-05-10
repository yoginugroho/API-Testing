pipeline {
  agent any
  stages {
    stage('API testing') {
      steps {
        sh '''cd logs
cat RestAPI.log'''
      }
    }

  }
}