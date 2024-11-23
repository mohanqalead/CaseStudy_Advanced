package stepdefinitions;


import org.testng.Assert;
import base.Base;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import pages.CartPage;
import pages.CheckoutCompletePage;
import pages.CheckoutOverviewPage;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductPage;
import utils.ExcelUtility;
import utils.Utility;

public class SauceDemoStepTest extends Base{
	
	LoginPage loginPage = new LoginPage(driver);
	ProductPage productPage = new ProductPage(driver);
	CartPage cartPage = new CartPage(driver);
	CheckoutPage checkoutPage = new CheckoutPage(driver);
	CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(driver);
	CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage(driver);
	
	
	@Given("the application is launched")
    public void the_application_is_launched() throws Exception{
				
		Assert.assertEquals(loginPage.getTitle(), "Swag Labs");
    }

    @When("I login with username {string} and password {string}")
    public void i_login_with_username_and_password(String username, String password) throws Exception {
    	Object[][] testData = Utility.loginData();
    	for (Object[] data : testData) {
            String userName = (String) data[0];
            String passWord = (String) data[1];
    	loginPage.login(userName, passWord);
    	Assert.assertTrue(loginPage.loginSuccess());
    	}
        
    }

    @And("I add multiple items to the cart")
    public void i_add_multiple_items_to_the_cart() throws Exception {
    	Assert.assertEquals(productPage.getProductPageTitle(), "Products");
    	productPage.addItemToCart();
    	productPage.navigateToCartPage();
        
    }

    @And("I proceed to checkout")
    public void i_proceed_to_checkout() throws Exception {
    	Assert.assertEquals(cartPage.getCartPageTitle(), "Your Cart");
    	cartPage.getProductName();
    	cartPage.getItemPrice();
    	Assert.assertTrue(cartPage.isCorrectProductListedInCartPage(productPage.itemName));
    	Assert.assertTrue(cartPage.isItemPriceCorrectInCartPage(productPage.itemValue));
    	cartPage.checkout();
        
    }

    @And("I provide valid checkout information")
    public void i_provide_valid_checkout_information() throws Exception {
    	Assert.assertEquals(checkoutPage.getCheckoutPageTitle(), "Checkout: Your Information");
    	String filePath = "src\\test\\resources\\testdata\\LoginData.xlsx";
        String sheetName = "UserData";            	
    	checkoutPage.fillCheckoutInfo(ExcelUtility.readData(filePath, sheetName).get("1"));
        
    }

    @And("I complete the order")
    public void i_complete_the_order() throws Exception {
    	Assert.assertEquals(checkoutOverviewPage.getCheckoutOverviewPageTitle(), "Checkout: Overview");
    	Assert.assertTrue(checkoutOverviewPage.isCorrectProductListedInCheckoutPage(productPage.itemName));
    	Assert.assertTrue(checkoutOverviewPage.isItemPriceCorrectInCheckoutPage(productPage.itemValue));
        checkoutOverviewPage.completeCheckOut();
    }

    @Then("I should see the confirmation message {string}")
    public void i_should_see_the_confirmation_message(String expectedMessage) {
    	Assert.assertEquals(checkoutCompletePage.getCheckoutCompletePageTitle(), "Checkout: Complete!");
    	Assert.assertEquals(checkoutCompletePage.getCheckoutSuccessText(), expectedMessage);
        
    }
    
    @AfterStep
    public void takeScreenshotAfterStep(Scenario scenario) {
        
            //final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach( Utility.captureScreenshot(driver), "image/png", "Screenshot on"+scenario.getName());
        
    }

}
