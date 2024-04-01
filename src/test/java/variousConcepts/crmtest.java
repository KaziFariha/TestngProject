package variousConcepts;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class crmtest {

	WebDriver driver; // object variable
	String browser = null;
	String url = null;

	String str = "demo@codefios.com";
	String PASSWORD = "abc123";
	String Full_Name = "Jainab";
	String Company = "Techfios";
	String Email = "KaziFarihahrm!7@gmail.com";
	String Phone = "01720612863";
	String Group = "java";

	String Country = "United States of America";

	By USER_Name_Field = By.id("user_name");
	By User_Password_Field = By.id("password");
	By Log_In_button_Field = By.id("login_submit");
	By Customer_field = By.xpath("/html/body/div[1]/aside[1]/div/nav/ul[2]/li[2]/a/span");
	By AddCustomer_field = By.xpath("//*[@id=\"customers\"]/li[2]/a/span");
	By AddCustomer_Header_field = By.xpath("//*[@id=\"customers\"]/li[2]/a/span");
	By Full_Name_Field = By.xpath("//*[@id=\"general_compnay\"]/div[1]/div/input");
	By DashBoard_Validation_Field = By.xpath("/html/body/div[1]/aside[1]/div/nav/ul[2]/li[1]/a/span");
	// WebElement DASHBOARD_VALIDATION_ELEMENT=
	// driver.findElement(By.xpath("/html/body/div[1]/aside[1]/div/nav/ul[2]/li[1]/a/span"));
	By company_Field = By.xpath("//select[@name= 'company_name']");
	By Email_Field = By.xpath("//*[@id=\"general_compnay\"]/div[3]/div/input");
	By Phone_Field = By.xpath("//*[@id=\"phone\"]");
	By Countary_DropDown_Field = By.xpath("//select[@name= 'country']");
	By Group_DropDown_Field = By.xpath("//select[@name= 'customer_group']");
	By Save_Button_Field = By.xpath("//*[@id=\"save_btn\"]");

	@BeforeClass
	public void readconfig() {
		// inputStream// BufferReader// FileReader/ Scanner
		try {
			InputStream input = new FileInputStream("src\\main\\java\\config\\config.properties");
			Properties prop = new Properties();
			prop.load(input);
			browser = prop.getProperty("browser");
			System.out.println("browser use" + browser);

			url = prop.getProperty("url");
			System.out.println("Url use  " + url);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@BeforeMethod
	public void init() {

		if (browser.equalsIgnoreCase("chrome")) {

			System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
			driver = new ChromeDriver();
		} else {
			System.setProperty("webdriver.edge.driver", "driver\\msedgedriver.exe");
			driver = new EdgeDriver();
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public void positiveLogingTest() throws InterruptedException {

		driver.findElement(USER_Name_Field).sendKeys(str);
		driver.findElement(User_Password_Field).sendKeys(PASSWORD);
		driver.findElement(Log_In_button_Field).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// Assert.assertEquals(DashBoard_Validation_Field , expected, message);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Assert.assertTrue(driver.findElement(DashBoard_Validation_Field).isDisplayed());

	}

	@Test
	public void AddCustomer() throws InterruptedException {
		positiveLogingTest();
		driver.findElement(Customer_field).click();
		driver.findElement(AddCustomer_field).click();
		// Assert.assertEquals(AddCustomer_Header_field, "New Customer", "New customer
		// not found");

		Assert.assertTrue(driver.findElement(AddCustomer_Header_field).isDisplayed());
		driver.findElement(Full_Name_Field).sendKeys(Full_Name);

		Select sel = new Select(driver.findElement(company_Field));
		sel.selectByVisibleText(Company);

		driver.findElement(Email_Field).sendKeys(generateRandomNum(777) + Email);
		driver.findElement(Phone_Field).sendKeys(Phone);
//bari bohe private method
		Select sel1 = new Select(driver.findElement(Countary_DropDown_Field));
		sel1.selectByVisibleText(Country);
		
		Select sel2 = new Select(driver.findElement(Group_DropDown_Field));
		sel2.selectByVisibleText(Group);
		
		driver.findElement(Save_Button_Field).click();

	}

	private int generateRandomNum(int boundryNum) {
		// TODO Auto-generated method stub
		Random rnd = new Random();
		int generateNum = rnd.nextInt(120);
		return generateNum;
	}

	@AfterMethod
	public void tearDown() throws InterruptedException {
		System.out.println("Teardown");
		Thread.sleep(4000);
		driver.quit(); // Use quit() instead of close() to close the entire browser window
	}

}
