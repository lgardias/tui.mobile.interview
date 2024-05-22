pipeline {
    agent any
    tools {
        gradle "8.8"
    }
    stages {
        stage('clone repository') {
            steps {
                sh "rm -r tui.mobile.interview"
                sh "git clone https://github.com/lgardias/tui.mobile.interview.git"
            }
        }
        stage('build application') {
            steps {
                dir('tui.mobile.interview') {
                  sh "ls -la"
                  sh 'gradle build'
                }
            }
        }
        stage('test the application') {
            steps {
                dir('tui.mobile.interview') {
                  sh 'gradle test'
                }
            }
        }
    }
}
