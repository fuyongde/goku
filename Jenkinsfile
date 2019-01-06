pipeline {
  agent any
  stages {
    stage('Build') {
      tools {
        maven 'maven'
        jdk 'JDK8'
      }
      steps {
        echo 'Building...'
        sh 'mvn clean install -f goku-parent/pom.xml -Dmaven.test.skip=true'
      }
    }
  }
}