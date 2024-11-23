package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Base;


public class LoginPage extends Base{
	WebDriver driver;
	
	@FindBy(id = "user-name")
	public static WebElement userNameInput;
	
	@FindBy(id = "password")
	public static WebElement passwordInput;
	
	@FindBy(id = "login-button")
	public static WebElement loginButton;
	
	@FindBy(id = "react-burger-menu-btn")
	public static WebElement menuButton;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		}
	
	public void login(String username, String password) {
		
		waitForElementVisibility(userNameInput);		
		userNameInput.sendKeys(username);
		passwordInput.sendKeys(password);
		loginButton.click();
		
	}
	
	public String getTitle() {
		return driver.getTitle();
	}
	
	public boolean loginSuccess()throws Exception{
		waitForElementVisibility(menuButton);
		return menuButton.isDisplayed();
	}
	
	

}
