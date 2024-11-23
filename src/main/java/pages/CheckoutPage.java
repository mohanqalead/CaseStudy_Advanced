package pages;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Base;

public class CheckoutPage extends Base{
	
	WebDriver driver;
	
	@FindBy(className = "title")
	public static WebElement title;
	
	@FindBy(id = "first-name")
	public static WebElement firstnameInput;
	
	@FindBy(id = "last-name")
	public static WebElement lastnameInput;
	
	@FindBy(id = "postal-code")
	public static WebElement postalCodeInput;
	
	@FindBy(id = "continue")
	public static WebElement continueButton;
	
	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		}
	
	public String getCheckoutPageTitle() {
		waitForElementVisibility(title);
		return title.getText();
	}
	
	public void fillCheckoutInfo(Map<String, String> data) {
		waitForElementVisibility(firstnameInput);
		firstnameInput.sendKeys(data.get("Firstname"));
		lastnameInput.sendKeys(data.get("Lastname"));
		postalCodeInput.sendKeys(data.get("Postalcode"));
		continueButton.click();
	}

}
