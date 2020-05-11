pipeline {
  agent any
  stages {
    stage('API testing') {
      steps {
        sh '''cat pom.xml
docker run -i --rm --name testing maven cp . testing:/app -w /app mvn clean install'''
      }
    }

  }
}