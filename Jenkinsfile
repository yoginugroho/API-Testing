pipeline {
  agent any
  stages {
    stage('API testing') {
      steps {
        sh 'docker run --rm --name testing cp . testing:/app -w /app maven mvn clean install'
      }
    }

  }
}