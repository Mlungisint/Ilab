package iLabAssessment;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IlabWeb  extends utilData{
	static WebDriver driver;
	
	
	public static void main(String[] args) {
		
		String name=utilData.name;
		String email=utilData.email;
		String phone=utilData.phone;
		String errormessage="You need to upload at least one file";
		
		String ilab = "https://www.ilabquality.com/.";//object from ilab website 
				
		//Envoke the step
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\mlungisi.ntshingila\\Documents\\Mlungisi\\selenium\\chromedriver.exe");// set property for
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		WebDriverWait w = new WebDriverWait(driver, 6);
		
		
		//Open ilab website/url
		driver.get(ilab);
		
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
		String actualMsg=driver.findElement(By.xpath("//li[contains(text(),'You need to upload at least one file.')]")).getText();//error content
		//w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(text(),'You need to upload at least one file.')]")));
		//System.out.println(error);
		System.out.println("Element displayed is :"+error);
		
		if (actualMsg.contains(errormessage))
		{
			System.out.println("Test Passed");
		}
		else
		{
			System.out.println("Test Failed");
		}
		
	

	}

}
