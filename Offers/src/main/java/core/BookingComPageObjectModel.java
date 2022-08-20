package core;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class BookingComPageObjectModel {

	public static ExtentReports extent;
	public static ExtentTest logger;
	
 public  static WebDriver  driver = null;
	 static By Location = By.id("downshift-0-input");
	 static By Locationfirstelement = By.id("downshift-0-item-0");
	 static By ArrivalDate = By.id("check-in-date");
	 static By DeptDate = By.id("check-out-date");
	 static By buttonSearch = By.xpath("//p[@class='chakra-text css-18kp540']");


	
	public static  void startTest()
	{
	extent = new ExtentReports (System.getProperty("user.dir") +"/test-output/STMExtentReport.html", true);
	extent
    .addSystemInfo("Host Name", "Redbox Tests")
    .addSystemInfo("Environment", "Redbox Testing Production Environment")
    .addSystemInfo("User Name", "Santosh Geernur");

    extent.loadConfig(new File("C:\\Users\\Consultant\\git\\repository\\Selenium\\extent-config.xml"));
	}
	
public static  void launchBookingsite() throws InterruptedException {
	logger = extent.startTest("Redboxlogin");
	System.setProperty("webdriver.gecko.driver","C:\\Selenium\\GeckoDriver\\geckodriver.exe");
    driver=new FirefoxDriver();
    driver.get("https://hotels.tmobiletravel.com/th/tmobile/en/?utm_medium=partner-app&utm_campaign=tr-tmo-tuesdays-tmt-app&utm_content=tr-tmo-tuesdays-tmt-app&utm_source=tmo-tmt-app");
    driver.manage().window().maximize();
    Thread.sleep(2000);

    
    
}
public static   void Searchcriteria() throws Exception {

	driver.findElement(Location).sendKeys("Portland");
	Thread.sleep(1000);
	driver.findElement(Locationfirstelement).click();
	driver.findElement(ArrivalDate).sendKeys("10/21/2021");
	driver.findElement(DeptDate).sendKeys("10/28/2021");
	driver.findElement(buttonSearch).click();
}
public static void endTest()
{
	extent.endTest(logger);
	extent.flush();
	extent.close();
}
public static void main(String[] args) throws Exception {
	 startTest();
launchBookingsite();
Thread.sleep(5000);
Searchcriteria();
 endTest();

 
  }

 
}
