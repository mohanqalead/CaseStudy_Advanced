package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;

public class Utility {
	static WebDriver driver;
    static Properties properties;

	
	public static WebDriver initializeDriver(String browser) {
		try {
			switch (browser) {
				case "chrome":
					System.setProperty("webdriver.chrome.driver","Resources\\chromedriver.exe");				
					driver = new ChromeDriver();								
					break;
					
				case "firefox":
					System.setProperty("webdriver.chrome.driver","Resources\\geckodriver.exe");
					driver = new FirefoxDriver();
					break;				

				default:
					System.setProperty("webdriver.chrome.driver","Resources\\chromedriver.exe");				
					driver = new ChromeDriver();
					break;
			}			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
        driver.manage().window().maximize();
        return driver;
    }
	
	public static String getProperty(String key) {
        if (properties == null) {
            try (FileInputStream fis = new FileInputStream("src\\test\\resources\\config.properties")) {
                properties = new Properties();
                properties.load(fis);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return properties.getProperty(key);
    }
	
	public static void captureScreenshot(WebDriver driver, String fileName) {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile, new File(getProperty("screenshot.path") + fileName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
	
	public static byte[] captureScreenshot(WebDriver driver) {
		final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		return screenshot;
	}
	@DataProvider(name = "LoginData")
    public static Object[][] loginData() {
        String filePath = "src\\test\\resources\\testdata\\LoginData.xlsx";
        String sheetName = "Credential";
        return ExcelUtility.getExcelData(filePath, sheetName);
    }

}
