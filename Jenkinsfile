pipeline {
  agent any
  stages {
    stage('API testing') {
      steps {
        sh '''cat pom.xml
docker run -i --rm --name testing -w /app maven cp . testing:/app  mvn clean install'''
      }
    }

  }
}