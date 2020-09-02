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
	static WebDriver driver;
	

	@BeforeClass		
	public void setup()
	
	{			
			//Envoke the step
			System.setProperty("webdriver.gecko.driver",
					"C:\\Users\\mlungisi.ntshingila\\Documents\\Mlungisi\\selenium\\geckodriver.exe");		
			WebDriver driver = new FirefoxDriver();/// set property for
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			WebDriverWait w = new WebDriverWait(driver, 6);
					
	}
	
	@AfterClass
	public void CloseBrowser() 
	{
		driver.quit();
	}
	
	@Test(priority=1)
	public void testcase1()
	{
		String ilab = utilData.url;//object from ilab website 
		//Open ilab website/url
		driver.get(ilab);
	}
	
	
	@Test(priority=2)
	public void Testcase2(String name,String email,String phone)throws InterruptedException
	
	{
			
			//click on career
			driver.findElement(By.linkText("CAREERS")).click();
			
			//Clikc on the link Souht Africa
			driver.findElement(By.linkText("South Africa")).click();
			
			//click on the first job from the list
			//driver.findElement(By.xpath("//div[@class='wpb_wrapper']/div/div[1]/div[1]")).click();
			driver.findElement(By.xpath("/html/body/section/div[2]/div/div/div/div[3]/div[2]/div/div/div/div/div/div[1]/div[1]")).click();
			
			//click the button "apply online"
			driver.findElement(By.cssSelector("span.wpjb-glyphs.wpjb-icon-down-open")).click();
			
			
			//pipilate fields
			driver.findElement(By.id("applicant_name")).sendKeys(name);
			
			driver.findElement(By.id("email")).sendKeys(email);
			
			driver.findElement(By.id("phone")).sendKeys(phone);
			
			driver.findElement(By.id("wpjb_submit")).click();
			
			//capture error message and validate
			boolean error=driver.findElement(By.xpath("//li[contains(text(),'You need to upload at least one file.')]")).isDisplayed();//object error element
			
			//w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(text(),'You need to upload at least one file.')]")));
			//System.out.println(error);
			System.out.println("Element displayed is :"+error);
		
	}

}
