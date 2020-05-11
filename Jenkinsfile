pipeline {
  agent any
  stages {
    stage('API testing') {
      steps {
        sh 'docker run -i --rm --name testing maven . mvn clean install'
      }
    }

  }
}