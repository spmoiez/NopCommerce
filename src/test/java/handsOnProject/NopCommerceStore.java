//Writing Test Automation Code to Register and place the order for a Book from NopCommerce website 
//and Generate the HTML report using TestNG framework
package handsOnProject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NopCommerceStore {
	
	WebDriver driver;
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	ExtentTest test;
	
	@BeforeTest
	public void setup() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://demo.nopcommerce.com/register"); //Opening NopCommerce Register page
		
		htmlReporter = new ExtentHtmlReporter("HTML_Report.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		Thread.sleep(3000);
	}
	
	@Test (priority = 1)
	public void testRegister() throws InterruptedException {
		
		test = extent.createTest("Register Test");
		
		//Step 1 - Register by entering necessary credentials
		driver.findElement(By.id("gender-male")).click();
		test.pass("Selected Gender Successfully");
		Thread.sleep(1000);
		
		driver.findElement(By.id("FirstName")).sendKeys("Abdul");
		test.pass("Entered FirstName Successfully");
		Thread.sleep(1000);
		
		driver.findElement(By.id("LastName")).sendKeys("Moiez");
		test.pass("Entered FirstName Successfully");
		Thread.sleep(1000);
		
		WebElement day = driver.findElement(By.name("DateOfBirthDay"));
		Select selectDay = new Select(day);
		selectDay.selectByVisibleText("15");
		test.pass("Selected Date of Birth Successfully");
		Thread.sleep(1000);
		
		WebElement month = driver.findElement(By.name("DateOfBirthMonth"));
		Select selectMonth = new Select(month);
		selectMonth.selectByVisibleText("July");
		test.pass("Selected Month of Birth Successfully");
		Thread.sleep(1000);	
		
		WebElement year = driver.findElement(By.name("DateOfBirthYear"));
		Select selectYear = new Select(year);
		selectYear.selectByVisibleText("1993");
		test.pass("Selected Year of Birth Successfully");
		Thread.sleep(1000);
		
		driver.findElement(By.id("Email")).sendKeys("abdulmoiez15@gmail.com");
		test.pass("Entered Email Successfully");
		Thread.sleep(1000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,700)", ""); //Scrolling down to see remaining elements
		Thread.sleep(2000);
		
		driver.findElement(By.id("Company")).sendKeys("Moolya");
		test.pass("Entered Company Name Successfully");
		Thread.sleep(1000);
		
		driver.findElement(By.id("Password")).sendKeys("Abdulmoiez15");
		test.pass("Entered Password Successfully");
		Thread.sleep(1000);
		
		driver.findElement(By.id("ConfirmPassword")).sendKeys("Abdulmoiez15");
		test.pass("Entered ConfirmPassword Successfully");
		Thread.sleep(1000);
		
		driver.findElement(By.id("register-button")).click();
		test.pass("Clicked Register Button Successfully");
		Thread.sleep(3000);
	}
	
	@Test (priority = 2)
	public void testClickBooks_Addtocart_ShippingCart() throws InterruptedException {
		
		test = extent.createTest("Select Item and Addtocart Test");
		
		//Step 2 - Click on book
		driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Books'] ")).click();
		test.pass("Clicked Books Tab Successfully");
		Thread.sleep(2000);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,300)", ""); //Scrolling down to select/see the items
		Thread.sleep(2000);
		
		//Step 3 - Add below item to the cart - “Add to cart”
		driver.findElement(By.xpath("//div[@class='item-grid']//div[1]//div[1]//div[2]//div[3]//div[2]//button[1]")).click();
		test.pass("Book Added to Cart Successfully");
		Thread.sleep(4000);
		
		//Step 4 - Click on Shopping cart
		driver.findElement(By.linkText("Shopping cart")).click();
		test.pass("Opened Shopping cart Successfully");
		Thread.sleep(3000);
	}
	
	@Test (priority = 3)
	public void testCheckoutTnC() throws InterruptedException {
		
		test = extent.createTest("CheckoutTnC Test");
		
		//Step 5 - Click on checkbox agreeing terms and conditions and click on checkout		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,300)", ""); //Scrolling down to select/see the items
		Thread.sleep(2000);
		
		driver.findElement(By.id("termsofservice")).click(); //Agreeing terms of service
		test.pass("Agreed T&C for Checkout Successfully");
		Thread.sleep(2000);
		
		driver.findElement(By.id("checkout")).click(); // Clicking Checkout
		test.pass("Clicked Checkout Successfully");
		Thread.sleep(2000);
	}
	
	@Test (priority = 4)
	public void testCheckoutasGuest() throws InterruptedException {
		
		test = extent.createTest("CheckoutasGuest Test");
		
		//Step 6 - Check out as Guest
		driver.findElement(By.xpath("//button[normalize-space()='Checkout as Guest']")).click();
		test.pass("Clicked Checkout as Guest Successfully");
		Thread.sleep(2000);
	}
	
	@Test (priority = 5)
	public void testPayment_PlaceOrder() throws InterruptedException {
		
		test = extent.createTest("Payment and Place Order Test");
		
		//Step 7 - Enter required Billing details
		driver.findElement(By.id("BillingNewAddress_FirstName")).sendKeys("Abdul"); //Entering FirstName
		test.pass("Entered FirstName for Billing Address Successfully");
		Thread.sleep(2000);
		
		driver.findElement(By.id("BillingNewAddress_LastName")).sendKeys("Moiez"); //Entering LastName
		test.pass("Entered LastName for Billing Address Successfully");
		Thread.sleep(2000);
		
		driver.findElement(By.id("BillingNewAddress_Email")).sendKeys("abdulmoiez@gmail.com"); //Entering Email
		test.pass("Entered Email for Billing Address Successfully");
		Thread.sleep(2000);
		
		//Selecting Country form dropdown
		WebElement country = driver.findElement(By.id("BillingNewAddress_CountryId"));
		Select selectCountry = new Select(country);
		selectCountry.selectByVisibleText("India");
		test.pass("Selected Country for Billing Address Successfully");
		Thread.sleep(2000);
		
		driver.findElement(By.id("BillingNewAddress_City")).sendKeys("Bangalore"); //Entering City
		test.pass("Entered City for Billing Address Successfully");
		Thread.sleep(2000);
		
		driver.findElement(By.id("BillingNewAddress_Address1")).sendKeys("Maruthi Nagar"); //Entering Address
		test.pass("Entered Address for Billing Successfully");
		Thread.sleep(2000);
		
		driver.findElement(By.id("BillingNewAddress_ZipPostalCode")).sendKeys("560068"); //Entering Pincode
		test.pass("Entered Pincode for Billing Address Successfully");
		Thread.sleep(2000);
		
		driver.findElement(By.id("BillingNewAddress_PhoneNumber")).sendKeys("9908001848"); //Entering Phone#
		test.pass("Entered Phone# for Billing Address Successfully");
		Thread.sleep(2000);
		
		driver.findElement(By.name("save")).click(); //Clicking on Continue - Billing Address completed
		test.pass("Clicked Continue Button to move into Shipping Method Successfully");
		Thread.sleep(2000);
		
		
		//Step 8 - Continue with Shipping method
		driver.findElement(By.id("shippingoption_0")).click(); //Selecting land transport method
		test.pass("Selected Shipping Method Successfully");
		Thread.sleep(2000);
		//Clicking Continue button after choosing shipping method
		driver.findElement(By.xpath("//button[@class='button-1 shipping-method-next-step-button']")).click();
		test.pass("Clicked Continue Button to move into Payment Mode Successfully");
		Thread.sleep(2000);
		
		
		//Step 9 - Choose Check/Money Order and continue
		driver.findElement(By.id("paymentmethod_0")).click(); //Selecting Check/Money Order method
		test.pass("Selected Payment Mode Successfully");
		Thread.sleep(2000);
		//Clicking Continue button after choosing payment method
		driver.findElement(By.xpath("//button[@class='button-1 payment-method-next-step-button']")).click();
		test.pass("Clicked Continue Button to move into Payment Information Page Successfully");
		Thread.sleep(2000);
		
		
		//Step 10 - Continue with below address - Payment information
		driver.findElement(By.xpath("//button[@class='button-1 payment-info-next-step-button']")).click();
		test.pass("Clicked Continue Button Successfully to Confirm the Order");
		Thread.sleep(4000);
		
		
		//Step 11 - Ensure to confirm the order
		driver.findElement(By.xpath("//button[@class='button-1 confirm-order-next-step-button']")).click();
		test.pass("Clicked Confirm Order Button Successfully");
		Thread.sleep(4000);
		
		
		//Step 12 - Display the order confirmation message in console
		System.out.println("Your order has been successfully processed!");
		
	}
	
	@AfterTest
	public void tearDown() {
		driver.close();
		extent.flush();
	}

}

















/*
@Test (priority = 2)
public void testLogin() throws InterruptedException {
	
	driver.findElement(By.linkText("Log in")).click(); //Redirecting to login page
	Thread.sleep(3000);
	
	driver.findElement(By.id("Email")).sendKeys("abdulmoiez@gmail.com");
	Thread.sleep(1000);
	
	driver.findElement(By.id("Password")).sendKeys("Abdulmoiez15");
	Thread.sleep(1000);
	
	driver.findElement(By.xpath("//button[@class='button-1 login-button']")).click();
	Thread.sleep(3000);
	
}
*/