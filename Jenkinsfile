pipeline {
  agent any
  stages {
    stage('API testing') {
      steps {
        sh '''cat pom.xml
docker run -i --rm --name testing -w / maven mvn clean install'''
      }
    }

  }
}