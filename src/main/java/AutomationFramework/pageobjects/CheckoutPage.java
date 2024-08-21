package AutomationFramework.pageobjects;

import java.time.Duration;

import javax.security.auth.callback.ConfirmationCallback;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import AutomationFramework.AbstractComponents.AbstractComponents;

public class CheckoutPage extends AbstractComponents {

	WebDriver driver;
	// this is a test comment for CI/CD
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//a[text()='Place Order ']")
	private WebElement submit;
	
	
	
	@FindBy(css = "[placeholder='Select Country']")
	private WebElement country;
	
	
	@FindBy(css = ".ta-item:nth-of-type(1)")
	private WebElement selectCountry;
	
	private By results = By.cssSelector(".ta-results");
	
	public void selectCountry(String countryName) {
		 Actions a = new Actions(driver);
	     a.sendKeys(country, countryName).build().perform();
	     waitForElementToAppear(By.cssSelector(".ta-results"));     
	     selectCountry.click();
	}
	
	public ConfirmationPage submitOrder()
	{
		submit.click();
		return new ConfirmationPage(driver);
		
	}
	

}
