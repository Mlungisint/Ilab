package vpc_login;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class IlabChrome {

	public WebDriver driver;

	public static void main(String[] args) {

		IlabChrome run = new IlabChrome();

		run.setUp();// Action Setup method

		run.accessCareers();// Action access career method

		run.capturInfo(); // Action capture info method

	}

	// Setup Method for browser
	public void setUp() {

		// Envoke the step
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\mlungisi.ntshingila\\Documents\\Mlungisi\\selenium\\chromedriver.exe");// set property for
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		// Open ilab website/url
		driver.get("https://www.ilabquality.com/.");

	}

	// Access Carrer Tap
	public void accessCareers() {

		// click on career
		driver.findElement(By.linkText("CAREERS")).click();

		// Clikc on the link South Africa
		driver.findElement(By.linkText("South Africa")).click();

		// click on the first job from the list
		// driver.findElement(By.xpath("//div[@class='wpb_wrapper']/div/div[1]/div[1]")).click();
		driver.findElement(
				By.xpath("/html/body/section/div[2]/div/div/div/div[3]/div[2]/div/div/div/div/div/div[1]/div[1]"))
				.click();

	}

	// Populate data from the web form
	public void capturInfo() {
		String pathToLogin = "C:\\Users\\mlungisi.ntshingila\\eclipse-workspace\\VPC_BO\\Data\\datafile.csv";
		String name = ReadDataFromCSV("NAME", pathToLogin);
		String email = ReadDataFromCSV("EMAIL", pathToLogin);
		String phone = ReadDataFromCSV("EMAIL", pathToLogin);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		// click the button "apply online"
		driver.findElement(By.cssSelector("span.wpjb-glyphs.wpjb-icon-down-open")).click();

		// pipilate fields
		driver.findElement(By.id("applicant_name")).sendKeys(name);

		driver.findElement(By.id("email")).sendKeys(email);

		driver.findElement(By.id("phone")).sendKeys(phone);

		driver.findElement(By.id("wpjb_submit")).click();

		// capture error message and validate
		boolean error = driver.findElement(By.xpath("//li[contains(text(),'You need to upload at least one file.')]"))
				.isDisplayed();// object error element
		String actualMsg = driver
				.findElement(By.xpath("//li[contains(text(),'You need to upload at least one file.')]")).getText();

		System.out.println(error);

		// Assert the error message for upload
		Assert.assertEquals(actualMsg, "You need to upload at least one file.");

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
