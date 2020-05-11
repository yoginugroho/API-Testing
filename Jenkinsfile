pipeline {
  agent any
  stages {
    stage('API testing') {
      steps {
        sh 'docker run --rm --name my-maven-project -v "$(pwd)":/usr/src/mymaven -w /usr/src/mymaven maven:3.3-jdk-8 mvn clean verify'
      }
    }

  }
}