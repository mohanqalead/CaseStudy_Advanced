pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                git branch: 'CaseStudy_Advanced', url: 'https://github.com/mohanqalead/CaseStudy_Advanced.git'
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