package runner;


import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.Base;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import utils.ExcelUtility;


@CucumberOptions(
        features = "src/test/resources/features", // Path to feature file
        glue = "stepdefinitions", // Package where step definitions are implemented
        plugin = {"pretty", "html:target/cucumber-reports.html", "json:target/cucumber.json"},
        monochrome = true
)

public class TestNGCucumberRunner extends AbstractTestNGCucumberTests{
	
	@BeforeSuite
	public void setup() {
		
		Base.setup();
		
	}
	
	@AfterSuite
	public void tearDown() {
		Base.tearDown();
	}
		

}
