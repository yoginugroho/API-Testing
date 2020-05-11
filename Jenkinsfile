pipeline {
  agent any
  stages {
    stage('API testing') {
      steps {
        sh 'docker run -it --rm --name testing maven mvn clean install'
      }
    }

  }
}