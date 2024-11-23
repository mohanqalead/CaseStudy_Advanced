package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Base;

public class CheckoutCompletePage extends Base{
	
	WebDriver driver;
	
	@FindBy(className = "complete-header")
	public static WebElement thankYouText;
	
	@FindBy(className = "title")
	public static WebElement title;
	
	public CheckoutCompletePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		}
	
	public String getCheckoutSuccessText() {
		waitForElementVisibility(thankYouText);
		return thankYouText.getText();
	}
	
	public String getCheckoutCompletePageTitle() {
		waitForElementVisibility(title);
		return title.getText();
	}

}
