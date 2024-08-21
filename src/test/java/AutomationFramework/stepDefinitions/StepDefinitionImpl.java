package AutomationFramework.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import AutomationFramework.TestComponents.BaseTest;
import AutomationFramework.pageobjects.CartPage;
import AutomationFramework.pageobjects.CheckoutPage;
import AutomationFramework.pageobjects.ConfirmationPage;
import AutomationFramework.pageobjects.LandingPage;
import AutomationFramework.pageobjects.ProductCatalogs;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest {
	
	public LandingPage landingPage;
	public ProductCatalogs productCatalog;
	public ConfirmationPage confirmationPage;
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException
	{
		//code
		landingPage = launchApplication();
		
	}
    
	@Given("^Logged in with username (.+) and password (.+)$") 
	public void logged_in_username_and_password(String username, String password)
	{
		productCatalog =  landingPage.loginApplication(username,password);
	}
	
	@When("^Add the product (.+) to Cart$")
	public void i_add_product_to_cart(String productName) throws InterruptedException
	{
		List <WebElement> products = productCatalog.getProductList();
        productCatalog.addProductToCart(productName);
	}
	
	@When("^Checkout (.+) and submit the order$")
	public void checkout_submit_order(String productName)
	{
		
CartPage cartPage =productCatalog.goToCartPage();
        
        Boolean match = cartPage.VerifyProductsDisplay(productName);
        Assert.assertTrue(match);
        CheckoutPage checkoutpage = cartPage.goToCheckout();
        checkoutpage.selectCountry("United States");
        confirmationPage = checkoutpage.submitOrder();  
	}
	
	
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_displayed_confirmationPage(String string)
	{
		String confirmMessage = confirmationPage.getConfirmMessage(); 
	    Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
	    driver.close();
	}
	
	@Then("^\"([^\"]*)\" message is displayed$")
	public void something_message_is_displayed(String string) throws Throwable 
	{
		Assert.assertEquals(string, landingPage.getErrorMessage());
		driver.close();
		
	}
	
	
}
