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
    stage('DelpoyDev') {
      steps {
        sh 'scp fastjson-demo/target/fastjson-demo.jar root@47.107.97.138:/opt/upload'
      }
    }
    stage('Hello') {
      parallel {
        stage('Hello1') {
          steps {
            echo 'Hello1'
          }
        }
        stage('Hello2') {
          steps {
            echo 'Hello1'
          }
        }
      }
    }
    stage('restart') {
      steps {
        echo 'restart'
      }
    }
  }
  options {
    buildDiscarder(logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '', daysToKeepStr: '3', numToKeepStr: '5'))
  }
}