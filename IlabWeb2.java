package iLabAssessment;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class IlabWeb2 extends utilData {
	public class IlabWeb2 extends utilData {  
	static WebDriver driver;
	static ExtentTest test;
	static ExtentReports report;
	// instance variables for data
	public static String name = "mungisi ntshingila";
	public static String email = "automationAssessment@iLABQuality.com";
	public static String phone = "0835687859";

	@BeforeClass
	public static void startTest()
	{
	report = new ExtentReports("C:\\Users\\mlungisi.ntshingila\\Test Reports\\Demo.html");
	test = report.startTest("ExtentDemo");
	}
	
	
	@Test
	public static void setup()
	{
		
		System.out.println("Start the method to envoke browswer");
		// Envoke the step 
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\mlungisi.ntshingila\\Documents\\Mlungisi\\selenium\\chromedriver.exe");// set property for
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		Reporter.log("Chrome Browser is envoked");

	}

	@Test(dependsOnMethods = "setup")
	public void testcase1() {
		String url = utilData.url; // object from ilab website
		// Open ilab website/url
		driver.get(url);
		System.out.println(driver.getTitle());
		Reporter.log("Ilab website is open");
		
		if(driver.getTitle().equals("Home Page | iLAB"))
		{
		test.log(LogStatus.PASS, "Navigated iLAB website");
		}
		else
		{
		test.log(LogStatus.FAIL, "Test Failed");
		}
		}
	

	@Test(dependsOnMethods = "testcase1")
	public void Testcase2() throws InterruptedException

	{
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		// click on career
		driver.findElement(By.linkText("CAREERS")).click();
		Reporter.log("career link is clicked");

		// Click on the link Souht Africa
		driver.findElement(By.linkText("South Africa")).click();
		Reporter.log("The link South Africa is select/clicked");

		// click on the first job from the list
		// driver.findElement(By.xpath("//div[@class='wpb_wrapper']/div/div[1]/div[1]")).click();
		driver.findElement(
				By.xpath("/html/body/section/div[2]/div/div/div/div[3]/div[2]/div/div/div/div/div/div[1]/div[1]"))
				.click();
		Reporter.log("link for the first Job is the list selected/clicked");

		// click the button "apply online"
		driver.findElement(By.cssSelector("span.wpjb-glyphs.wpjb-icon-down-open")).click();
		Reporter.log("Apply Button clicked");

		// pipilate fields
		driver.findElement(By.id("applicant_name")).sendKeys(ilab2.name);

		driver.findElement(By.id("email")).sendKeys(ilab2.email);

		driver.findElement(By.id("phone")).sendKeys(ilab2.phone);

		driver.findElement(By.id("wpjb_submit")).click();
		Reporter.log("All the fields populated with information");

		// capture error message and validate
		boolean error = driver.findElement(By.xpath("//li[contains(text(),'You need to upload at least one file.')]"))
				.isDisplayed();// object error element
		String errorMSG = driver.findElement(By.xpath("//li[contains(text(),'You need to upload at least one file.')]")).getText();
		

		// w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(text(),'You
		// need to upload at least one file.')]")));
		System.out.println(errorMSG);
		System.out.println("Element displayed is :" + error);
		Reporter.log("error messages displayed to upload docuement");
		
		if(errorMSG.equals(("You need to upload at least one file.")))
		{
		test.log(LogStatus.PASS, "Error message displayed");
		}
		else
		{
		test.log(LogStatus.FAIL, "Test Failed");
		}
		}
		
		
		
		

	// Close the broowser
	@AfterClass
	public void CloseBrowser() {
		report.endTest(test);
		report.flush();
		driver.quit();
		Reporter.log("The broswer is closed");
	}

}
