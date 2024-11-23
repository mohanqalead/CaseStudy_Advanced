package pages;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Base;

public class CheckoutOverviewPage extends Base{
	
	WebDriver driver;
	List<String> name = new ArrayList<String>();
	List<String> price = new ArrayList<String>();
	
	@FindBy(className = "title")
	public static WebElement title;
	
	@FindBy(className = "summary_subtotal_label")
	public static WebElement itemTotal;
	
	@FindBy(name = "finish")
	public static WebElement finishButton;
	
	@FindBy(xpath = "//div[@data-test='inventory-item-name']")
	public static List<WebElement> productName;
	
	@FindBy(className = "inventory_item_price")
	public static List<WebElement> itemPrice;
	
	public CheckoutOverviewPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		}
	
	public String getCheckoutOverviewPageTitle() {
		waitForElementVisibility(title);
		return title.getText();
	}
	
	public void completeCheckOut() {
		waitForElementClickable(finishButton);
		finishButton.click();
	}
	
public void getProductName()throws Exception{
		
		ListIterator<WebElement> iterator = productName.listIterator();
		while(iterator.hasNext()) {
			name.add(iterator.next().getText());
		}
		System.out.println(name);
				
	}
	
	public void getItemPrice()throws Exception{
		ListIterator<WebElement> iterator = itemPrice.listIterator();
		while(iterator.hasNext()) {
			price.add(iterator.next().getText().replace("$", "").trim().toString());
		}
		System.out.println(price);
	}
	
	public boolean isItemTotalCorrect()throws Exception{
		double itemTotalPrice = 0.0;
		ListIterator<WebElement> iterator = itemPrice.listIterator();
		while(iterator.hasNext()) {
			itemTotalPrice = itemTotalPrice + Double.valueOf(iterator.next().getText().replace("$", "").trim().toString());			
		}
		return  String.valueOf(itemTotalPrice) == itemTotal.getText().replace("$", "").trim().toString();		
	}
	
	public boolean isCorrectProductListedInCheckoutPage(List<String> product)throws Exception{
		
		return product.containsAll(name);
		
	}
	
	public boolean isItemPriceCorrectInCheckoutPage(List<String> itemPrice)throws Exception{
		return itemPrice.containsAll(price);
	}
	
	

}
