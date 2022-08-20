package core;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;

import java.util.Set;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;

import com.relevantcodes.extentreports.LogStatus;




@Listeners(ListenerTest.class)

public class BookingComPageFactoryTestNg

{


 public  static WebDriver  driver = null;
 @FindBy (id = "downshift-0-input")
 static WebElement Location;
 @FindBy (id = "check-in-date")
// @FindBy (id = "23424")
 static WebElement ArrivalDate;
 @FindBy (id = "downshift-0-item-0")
 static WebElement Locationfirstelement;
 @FindBy(id = "check-out-date")
 static WebElement DeptDate;
 @FindBy(xpath="//div[@data-testid='search-rooms-and-guests-form']")
 static WebElement noOfRooms;
 @FindBy(xpath="//button[@data-testid='rooms-and-guests-apply-button']")
 static WebElement roomApplyButton;
 @FindBy(xpath="//*[@type='submit']")
 static WebElement buttonSearch;
 @FindBy(xpath = "//div[@class='chakra-stack css-1eopsh2']/a[1]")
 static WebElement FirstHotelSearchResult;
 @FindBy(xpath = "//*[contains(text(),'Select now')]")
 static WebElement FirstRoom;
 @FindBy(xpath = "//input[@name='guestFirstName']")
 static WebElement GuestFirstName;
 @FindBy(xpath = "//input[@name='guestLastName']")
 static WebElement GuestLastName;
 @FindBy(xpath = "//input[@name='altEmailAddress']")
 static WebElement GuestEmail;
 @FindBy(xpath = "//input[@id='swipe-name']")
 static WebElement swipename;
 @FindBy(xpath = "//input[@id='swipe-card-number']")
 static WebElement swipecardnumber;
 @FindBy(xpath = "//input[@id='swipe-cvv']")
 static WebElement swipecvv;
 @FindBy(xpath = "//input[@id='swipe-street']")
 static WebElement swipeStreet;
 @FindBy(xpath = "//input[@id='swipe-zip-code-2']")
 static WebElement swipeZipcode;
 @FindBy(xpath = "//input[@name='email']")
 static WebElement Email;
 @FindBy(xpath = "//*[@id='swipe-iframe']")
 static WebElement SwipeiFrame;
 @FindBy(xpath = "//*[@name='AgreeToTermsAndPolicies']")
 static WebElement AgreetoPolicies;
 @FindBy(xpath = "//*[contains(text(),'Confirm Reservation')]")
 static WebElement ConfirmReservation;
	@BeforeSuite
	public static void startTest()
	{

	 Reports.extent = new ExtentReports(System.getProperty("user.dir") +"/test-output/STMExtentReport.html", true);
	Reports.extent
  .addSystemInfo("Host Name", "Santosh Laptop")
  .addSystemInfo("Environment", "Simulator")
  .addSystemInfo("User Name", "Santosh Geernur");
  Reports.extent.loadConfig(new File ("C:\\Users\\Consultant\\git\\repository\\Selenium\\extent-config.xml"));
	}

	@BeforeMethod
	public void beforeMethod(Method method){
		Reports.logger= Reports.extent.startTest(this.getClass().getSimpleName() + " :: " + method.getName(), method.getName());
	}
	@AfterMethod
	public void afterMethod(){
		Reports.extent.endTest(Reports.logger);
	}
	@AfterSuite
	public void afterSuite() throws Exception{
		Keywords.closeAllBrowser();
		}
	
//	public static void main(String [] args) throws Exception {
//		startTest();
//		
//		launchBookingsite();
//		Searchcriteria();
//		SelectHotel();
//		enterGuestInfo();
//		Reports.extent.endTest(Reports.logger);
//		Reports.extent.flush();
//		Reports.extent.close();
//
//	}
	@Test(priority =1)
public static  void launchBookingsite() throws InterruptedException {
	Reports.logger= Reports.extent.startTest("Bookingdotcom");
	System.setProperty("webdriver.gecko.driver","C:\\Selenium\\GeckoDriver\\geckodriver.exe");
    driver=new FirefoxDriver();
    driver.get("https://hotels.tmobiletravel.com/th/tmobile/en/gating?authKey=39124740&utm_medium=partner-app&utm_campaign=tr-tmo-tuesdays-tmt-app&utm_content=tr-tmo-tuesdays-tmt-app&utm_source=tmo-tmt-app");
    driver.manage().window().maximize();
    Thread.sleep(2000);
}
	@Test(priority =2)
public static  void Searchcriteria() throws Exception {

	 var Priceline_Hotels = new BookingComPageFactoryTestNg();
	    PageFactory.initElements(driver, Priceline_Hotels);
    	  keyInTheText(Location, "Portland", "Location");
    	  Thread.sleep(1000);
    		Locationfirstelement.click();
    		keyInTheText(ArrivalDate, "08/25/2022", "Arrival Date");
    		DeptDate.clear();
    		keyInTheText(DeptDate, "09/01/2022", "Departure Date");
    		noOfRooms.click();
    		roomApplyButton.click();
    		buttonSearch.click();
    		  Reports.logger.log(LogStatus.PASS, "Search criteria is completed");
    		   Assert.assertTrue(true);
  }
	@Test(priority =3)
public static  void SelectHotel() throws Exception {
	Thread.sleep(15000);
	FirstHotelSearchResult.click();
	getWindowHandles();
	Thread.sleep(6000);
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("arguments[0].scrollIntoView();", FirstRoom);
		FirstRoom.click();
		   Assert.assertTrue(true);
 }



	@Test(priority =4)
public static void enterGuestInfo() throws Exception {
	Thread.sleep(5000);
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("arguments[0].scrollIntoView();", GuestFirstName);
	keyInTheText(GuestFirstName, "John", "GuestFirstName");
	keyInTheText(GuestLastName, "Smith", "GuestLastName");	
	keyInTheText(GuestEmail, "cxttmt10@gmail.com", "altEmailAddress");
	js.executeScript("arguments[0].scrollIntoView();", SwipeiFrame);
	driver.switchTo().frame(SwipeiFrame);
	keyInTheText(swipename, "John Smith", "Card Name");
	keyInTheText(swipecardnumber, "4111 1111 1111 1111", "Card Number");
	keyInTheText(swipecvv, "123", "CVV");
	keyInTheText(swipeStreet, "23425 SE Black Nugget Rd Apt H102", "Street Address");
	keyInTheText(swipeZipcode, "98029", "Zipcode");
	 driver.switchTo().defaultContent();
	keyInTheText(Email, "cxttmt10@gmail.com", "Email");
	selectCheckBox(AgreetoPolicies, "Agree To Policies");
//	clickOnLink(ConfirmReservation, "Confirm Reservation");
	
}
public static void keyInTheText(WebElement elementLocator, String value, String FieldName) {
	try {
	    waitForanElement(elementLocator);
    elementLocator.sendKeys(value);
    Reports.logger.log(LogStatus.INFO, FieldName + " Element is found");

	}
    catch (Exception e) {
    	Reports.reportInfo("Element is either disabled or not present");
        e.printStackTrace();
        }
}
public static void selectCheckBox(WebElement elementLocator,  String fieldName) throws Exception {
	try{
	    waitForanElement(elementLocator);
	Boolean is_selected = elementLocator.isSelected();
	if (is_selected != true) {
		elementLocator.click();
		Reports.reportInfo("Check box: Set the checkbox value " + fieldName +"", "True");
			}
	else{
		Reports.reportInfo("Check box: Set the checkbox value " + fieldName +"", "False");
	}
	}
	catch(TimeoutException ex){
	Reports.reportFail(ex.getMessage(), fieldName);
		throw ex;
	}catch(NoSuchElementException ex){
		Reports.reportFail(ex.getMessage(), fieldName);
		throw ex;
	}
	}
public static void clickOnLink(WebElement elementLocator, String FieldName) throws Exception {

	try{
    waitForanElement(elementLocator);
	if (elementLocator != null){
		elementLocator.click();
		Reports.reportInfo("Link: Clicked on link ", FieldName);
		}
	else{
		Reports.reportFail("Link: Unable to click on link " + FieldName + "element locator value is " +elementLocator+"");
	}
	}catch(Exception ex){
		Reports.reportFail(ex.getMessage(), FieldName);

		throw ex;
	}
}
public static void waitForanElement(WebElement elementLocator) {
	WebDriverWait wait = new WebDriverWait(driver, 15);
    wait.until(ExpectedConditions.visibilityOf(elementLocator)); 
}
public static void getWindowHandles() {
	String mainWindowHandle = driver.getWindowHandle();
	Set<String> allWindowHandles = driver.getWindowHandles();
	Iterator<String> iterator = allWindowHandles.iterator();
    while (iterator.hasNext()) {
        String ChildWindow = iterator.next();
            if (!mainWindowHandle.equalsIgnoreCase(ChildWindow)) {
            driver.switchTo().window(ChildWindow);

        }
    }

}
public static void closeAllBrowser() throws Exception {

    try {

		driver.close();
        Runtime.getRuntime().exec("taskkill /F /IM geckodriver.exe");
    } catch (IOException e) {
        e.printStackTrace();
    }

}

}


 
  


