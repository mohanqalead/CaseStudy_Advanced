pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/mohanqalead/CaseStudy_Advanced.git'
            }
        }
        stage('Build') {
            steps {
                echo 'Building the project...'
                sh 'mvn clean compile'
            }
        }
        stage('Run Tests') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Generate Report') {
            steps {
                publishCucumberReports(cucumberJsonReportDirectory: 'target/cucumber-reports')
            }
        }
    }
}
