package capg.Tests;

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
import capg.TestComponents.BaseTest;
import capg.pageobjects.CartPage;
import capg.pageobjects.CheckoutPage;
import capg.pageobjects.ConfirmationPage;
import capg.pageobjects.LandingPage;
import capg.pageobjects.ProductCatalog;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest extends BaseTest{

	@Test
	public void submitOrder() throws IOException, InterruptedException 
	{
		String productname = "ZARA COAT 3";
		//LandingPage landingpage = launchApplication();   - As we've given BeforeMethod it executes bfr every metho and no need to intiate it
		//for landingpage to accessible in other classes we gonna create an oject on top of BaseTest.java
		ProductCatalog productCatalog = landingpage.loginApplication("aziza@gmail.com", "Aziza@1999");
		List<WebElement> products = productCatalog.getProductList();
		productCatalog.addProductToCart(productname);
		CartPage cartPage = productCatalog.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(productname);
		//Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("India");
		//checkoutPage.submitOrder();

		//Thread.sleep(5);
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();

		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		System.out.println("SUCCESS");
	}


}











//WebDriverManager.chromedriver().setup();
//WebDriver driver = new ChromeDriver();
//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//driver.manage().window().maximize();
//WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
//LandingPage landingpage= new LandingPage(driver);
//landingpage.goTo();