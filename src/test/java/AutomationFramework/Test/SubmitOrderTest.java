package AutomationFramework.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import AutomationFramework.TestComponents.BaseTest;
import AutomationFramework.pageobjects.CartPage;
import AutomationFramework.pageobjects.CheckoutPage;
import AutomationFramework.pageobjects.ConfirmationPage;
import AutomationFramework.pageobjects.LandingPage;
import AutomationFramework.pageobjects.OrderPage;
import AutomationFramework.pageobjects.ProductCatalogs;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest{

	String productName = "ZARA COAT 3";
	
    @Test(dataProvider="getData", groups= {"Purchase"})
    public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException
    {
        
        ProductCatalogs productCatalog =  landingPage.loginApplication(input.get("email"), input.get("password"));
        
        List <WebElement> products = productCatalog.getProductList();
        productCatalog.addProductToCart(input.get("product"));
        CartPage cartPage =productCatalog.goToCartPage();
        
        Boolean match = cartPage.VerifyProductsDisplay(input.get("product"));
        Assert.assertTrue(match);
        CheckoutPage checkoutpage = cartPage.goToCheckout();
        checkoutpage.selectCountry("United States");
       ConfirmationPage confirmationPage = checkoutpage.submitOrder();     
     
     String confirmMessage = confirmationPage.getConfirmMessage();
     
     Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
   

   
    }
    
    @Test (dependsOnMethods = {"submitOrder"})
    public void OrderHistoryTest()
    {
    	ProductCatalogs productCatalog =  landingPage.loginApplication("organic.test@gmail.com", "Abc$1234");
    	OrderPage ordersPage = productCatalog.goToOrdersPage();
    	Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
    	
    }
    

    
    // Extent Reports
    
    @DataProvider
    public Object[][] getData() throws IOException
    {
 	
    	List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//AutomationFramework//data//PurchaseOrder.json");
    	return new Object[][] {{data.get(0)}, {data.get(1)} };
    	
    }
    
    //@DataProvider
    //public Object[][] getData()
   // {
    //	return new Object[][] {{"organic.test@gmail.com","Abc$1234","ZARA COAT 3"},{"artificial.test@gmail.comm","Xyz$1234","ADIDAS ORIGINAL"}}
    //}
    
    
//	HashMap<String, String> map = new HashMap<String, String>();
//	map.put("email", "organic.test@gmail.com");
//	map.put("password", "Abc$1234");
//	map.put("product", "ZARA COAT 3");
//	
//	HashMap<String, String> map1 = new HashMap<String, String>();
//	map1.put("email", "artificial.test@gmail.comm");
//	map1.put("password", "Xyz$1234");
//	map1.put("product", "ADIDAS ORIGINAL");
//
    
    
}
