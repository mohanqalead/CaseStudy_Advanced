package base;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import utils.Utility;

public class Base {
	protected static WebDriver driver;
	static WebDriverWait wait;
	
	
	public static void setup() {
		try {
			
			driver = Utility.initializeDriver(System.getProperty("browser"));
			driver.get(Utility.getProperty("url"));		
			wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void tearDown() {
		driver.quit();
	}
	
	public void waitForElementVisibility(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForElementClickable(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	
	
	

}
