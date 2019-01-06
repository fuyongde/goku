pipeline {
  agent any
  options {
    buildDiscarder logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '', daysToKeepStr: '3', numToKeepStr: '5')
  }
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