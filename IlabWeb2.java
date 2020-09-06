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
	
	
	
	public static void main(String[] args) {
		
		iLabFirefox run=new iLabFirefox();
		
		run.envokeFirefox();//setup method to the open browser
		run.careerTap();//run career method to click on careers
		run.captureInf();//run method to populate informtion
			
		
	}
	
	public void envokeFirefox()
	{
		//Envoke the step
		System.setProperty("webdriver.gecko.driver",
				"C:\\Users\\mlungisi.ntshingila\\Documents\\Mlungisi\\selenium\\geckodriver.exe");		
		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		//Open ilab website/url
		driver.get("https://www.ilabquality.com/.");
		String title=driver.getTitle();
		
		Assert.assertEquals(title, "Home Page | iLAB");//grab the webpage title
		
	}

		
	public void careerTap() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		//click on career
		driver.findElement(By.linkText("CAREERS")).click();
		String careertitle=driver.getTitle();
		System.out.println(careertitle);
		
		
		//Clikc on the link South Africa
		driver.findElement(By.linkText("South Africa")).click();
		
		
		
		//click on the first job from the list
		//driver.findElement(By.xpath("//div[@class='wpb_wrapper']/div/div[1]/div[1]")).click();
		driver.findElement(By.xpath("/html/body/section/div[2]/div/div/div/div[3]/div[2]/div/div/div/div/div/div[1]/div[1]")).click();
		
		
	}
	
	public void captureInf() {
		String pathToLogin = "C:\\Users\\mlungisi.ntshingila\\eclipse-workspace\\VPC_BO\\Data\\Infodata.csv";
		String name = ReadDataFromCSV("NAME", pathToLogin);
		String email = ReadDataFromCSV("EMAIL", pathToLogin);
		String phone = ReadDataFromCSV("EMAIL", pathToLogin);		
		
		
		//click the button "apply online"
		driver.findElement(By.cssSelector("span.wpjb-glyphs.wpjb-icon-down-open")).click();
		Reporter.log("Apply button clicked");
		
		
		//pipilate fields
		driver.findElement(By.id("applicant_name")).sendKeys(name);
		
		driver.findElement(By.id("email")).sendKeys(email);
		
		driver.findElement(By.id("phone")).sendKeys(phone);
		
		driver.findElement(By.id("wpjb_submit")).click();
		Reporter.log("All the fields populated");
		
		//capture error message and validate
		boolean error=driver.findElement(By.xpath("//li[contains(text(),'You need to upload at least one file.')]")).isDisplayed();//object error element
		String actualMsg=driver.findElement(By.xpath("//li[contains(text(),'You need to upload at least one file.')]")).getText();//error content
		//w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(text(),'You need to upload at least one file.')]")));
		System.out.println(error);
		//System.out.println("Element displayed is :"+error);
		
		if (actualMsg.contains("You need to upload at least one file."))
		{
			System.out.println("Test Passed");
			
		}
		else
		{
			System.out.println("Test Failed");
		}
		
		Reporter.log("error message is selected to upload a document");
	}
	
	
	// Method to read data from CVS
		public String ReadDataFromCSV(String datatoberead, String datafilepath) {

			String line = "";//
			String splitBy = ",";//comma seperated value
			String datatobereturned = "";//actual data/value
			try {
				BufferedReader br = new BufferedReader(new FileReader(datafilepath));
				while ((line = br.readLine()) != null) // returns a Boolean value
				{
					String[] employee = line.split(splitBy); // use comma as separator

					if (employee[0].equals(datatoberead)) {
						// Column that you want to
						datatobereturned = employee[1];
					}
				}

				return datatobereturned;
			} catch (IOException e) {
				System.out.println(e.getMessage());
				return "";
			}
		}

}
