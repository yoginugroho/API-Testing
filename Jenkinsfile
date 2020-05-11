pipeline {
  agent any
  stages {
    stage('API testing') {
      steps {
        sh '''cat pom.xml
docker container create --rm  --name maven maven
docker cp . maven:/app
docker container start -w /app maven mvn clean install
docker container stop maven
docker container rm maven'''
      }
    }

  }
}