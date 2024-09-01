package AutomationFramework.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import AutomationFramework.TestComponents.BaseTest;
import AutomationFramework.TestComponents.Retry;
import AutomationFramework.pageobjects.CartPage;
import AutomationFramework.pageobjects.CheckoutPage;
import AutomationFramework.pageobjects.ConfirmationPage;
import AutomationFramework.pageobjects.LandingPage;
import AutomationFramework.pageobjects.ProductCatalogs;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidationsTest extends BaseTest{

    @Test(groups= {"ErrorHandling"}, retryAnalyzer=Retry.class)
    public void LoginErrorValidations() throws IOException, InterruptedException
    {
        
        landingPage.loginApplication("organic.test@gmail.com", "Abc234");
        Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
      
    }
    
    @Test
    public void ProductErrorValidations() throws IOException, InterruptedException
    {
        
    	String productName = "ZARA COAT 3";
        ProductCatalogs productCatalog =  landingPage.loginApplication("artificial.test@gmail.com", "Xyz$1234");      
        List <WebElement> products = productCatalog.getProductList();
        productCatalog.addProductToCart(productName);
        CartPage cartPage =productCatalog.goToCartPage();      
        Boolean match = cartPage.VerifyProductsDisplay("ZARA COAT 33");
        Assert.assertFalse(match);  
        
        //test comments
     
   
    }
}
