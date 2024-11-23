package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Base;

public class ProductPage extends Base{
	WebDriver driver;
	public List<String> itemName = new ArrayList<String>();
	public List<String> itemValue = new ArrayList<String>();
	
	
	@FindBy(className = "title")
	public static WebElement title;
	
	//@FindBy(xpath = "//*[text()='Add to cart']")
	//public static List<WebElement> productList;
	
	@FindBy(xpath = ".//div[@data-test='inventory-item-description']")
	public static List<WebElement> productList;
	
	@FindBy(className = "shopping_cart_link")
	public static WebElement cartLink;
	
	@FindBy(xpath = "//div[@data-test='inventory-item-name']")
	public static List<WebElement> productName;
	
	@FindBy(className = "inventory_item_price")
	public static List<WebElement> itemPrice;
	
	public ProductPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		}
	
	public String getProductPageTitle() {
		waitForElementVisibility(title);
		return title.getText();
	}
	
	public void addItemToCart() throws Exception{
	
		if(productList.size() > 0) {
			for(int i=0; i<productList.size(); i++) {
				itemName.add(productName.get(i).getText());
				
				itemValue.add(itemPrice.get(i).getText().replace("$", "").trim().toString());
				(productList).get(i).findElement(By.xpath("//div/button[text()='Add to cart']")).click();
				
				if( productList.size() == 1 || i == 1) {
					break;
				}
			}			
		}
				
	}
	
	public void navigateToCartPage() {
		cartLink.click();
	}
	

}
