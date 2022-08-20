package core;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


import com.relevantcodes.extentreports.LogStatus;


import org.apache.commons.io.FileUtils;


public class Keywords {
	public static WebDriver driver = null;
	public static String path = System.getProperty("user.dir");
	public static String geckoDriverPath = "C:\\Selenium\\GeckoDriver\\geckodriver.exe";
	public static final String DATE_FORMAT = "MM/dd/yyyy";
//	private static final TimeUnit  = null;
	
	public static void openBrowser(String browser) throws IOException{

		if(browser.equalsIgnoreCase("firefox" ) ){
//			ProfilesIni profileIni = new ProfilesIni();
//			FirefoxProfile fp = profileIni.getProfile("Santosh");
//			DesiredCapabilities capabilities = new DesiredCapabilities();
//			capabilities.setCapability(FirefoxDriver.PROFILE, fp);
			 
			System.setProperty("webdriver.gecko.driver", geckoDriverPath);
//			System.setProperty("webdriver.firefox.bin", "C:\\Program Files\\Mozilla Firefox\\firefox.exe");
//			driver = new FirefoxDriver(capabilities);
			driver = new FirefoxDriver();	
//			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

	        
	
	        

		}
		else if(browser.equalsIgnoreCase("iexplore")){
			File file = new File("src/IE Driver Server/IEDriverServer.exe"); 
			System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
			driver = new InternetExplorerDriver();
		}else if(browser.equalsIgnoreCase("chrome")){
			File file = new File("src/Chrome Driver/chromedriver.exe"); 
			System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
			driver = new ChromeDriver();
		}
	}
	
			



	public static void selectCheckBox(String elementLocator,  String fieldName) throws Exception {
		try{
//			elementLocator = getTestObject(elementLocator);
		 getElement(elementLocator);

		 List<WebElement> oCheckBox = driver.findElements(By.xpath(elementLocator));
		Boolean is_selected = oCheckBox.get(0).isSelected();
		if (is_selected != true) {
			oCheckBox.get(0).click();
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
	public static void selectValueFromDropDownByXPath(String elementLocator, String value, String fieldName) throws Exception {
		elementLocator = getTestObject(elementLocator);
		WebElement elem = getElement(elementLocator);
//		fluentWaitforElement(elem, 15, 3);
		try{
			new Select(elem).selectByVisibleText(value);
			Reports.reportInfo("List box: Selected value from " + fieldName +" is ", value);
		
		}catch(TimeoutException ex){
			Reports.reportFail("Time out Exception");
			Reports.reportFail(ex.getMessage(), fieldName);
			throw ex;
		}catch(NoSuchElementException ex){
				Reports.reportFail("List box: Element Or value "+ elementLocator + " Or "+value+ "  does not exist");
				Reports.reportFail(ex.getMessage(), fieldName);
				throw ex;
			}
			
		}
	public static void selectByValue(String elementLocator, String value, String fieldName) throws Exception {
		elementLocator = getTestObject(elementLocator);
//		WebElement elem = (new WebDriverWait(driver, 45))
//				  .until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementLocator)));
		WebElement elem = Keywords.getElement(elementLocator);
		List<WebElement> optionList = elem.findElements(By.xpath(elementLocator));
		for (WebElement option : optionList) {
			if (option.getText().toLowerCase().equals(value))
				option.click();
				break;
		}
	}
	public static void acceptOrDismissAlert(String acceptOrDismiss) {
		Alert alert = driver.switchTo().alert();
		if (acceptOrDismiss.toLowerCase().startsWith("a")) {
			alert.accept();
		} else if (acceptOrDismiss.toLowerCase().startsWith("d")) {
			alert.dismiss();
		}
	}
	public static void clickOnLink(String elementLocator, String FieldName) throws Exception {
//		elementLocator = getTestObject(elementLocator);
		WebElement elem = getElement(elementLocator);
		try{

		if (elem != null){
			elem.click();
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


	public static void sendKeys(String elementLocator, String KeyComb) throws Exception {
		elementLocator = getTestObject(elementLocator);
		try{
		switch (KeyComb){
		case "PAGE_DOWN":
			driver.findElement(By.xpath(elementLocator)).sendKeys(Keys.PAGE_DOWN);	
			Reports.reportInfo("KeyStroke: Performed key stroke PAGE_DOWN");
		case "PAGE_UP":
			driver.findElement(By.xpath(elementLocator)).sendKeys(Keys.PAGE_UP);
			Reports.reportInfo("KeyStroke: Performed key stroke PAGE_UP");
		case "CNTRL_HOME":
			driver.findElement(By.xpath(elementLocator)).sendKeys(Keys.CONTROL, Keys.HOME);	
			Reports.reportInfo("KeyStroke: Performed key stroke CNTRL_HOME");
		case "CNTRL_END":
			driver.findElement(By.xpath(elementLocator)).sendKeys(Keys.CONTROL, Keys.END);	
			Reports.reportInfo("KeyStroke: Performed key stroke CNTRL_END");
		case "CNTRL_A":
			driver.findElement(By.xpath(elementLocator)).sendKeys(Keys.CONTROL, "A");	
			Reports.reportInfo("KeyStroke: Performed key stroke CNTRL_A");
		case "CNTRL_V":
			driver.findElement(By.xpath(elementLocator)).sendKeys(Keys.CONTROL, "V");	
			Reports.reportInfo("KeyStroke: Performed key stroke CNTRL_V");
		case "TAB":
			driver.findElement(By.xpath(elementLocator)).sendKeys(Keys.TAB);	
			Reports.reportInfo("KeyStroke: Performed key stroke TAB");
		}
		}catch (Exception ex){
			Reports.reportFail(ex.getMessage(), elementLocator);
			throw ex;
		}
	}

	public static void enterTextInTextField(String elementLocator, String text, String FieldName) throws Exception {
		WebElement elem = getElement(elementLocator);
		try{
			if (elem != null){
			elem.clear();
			elem.click();
			Thread.sleep(500);
			elem.sendKeys(text);
			Reports.reportInfo("Textbox: Entered text in the "+FieldName+"", text);
			}
			else{
				Reports.reportFail("Textbox: Unable to enter text on "+FieldName+"element locator value is " +elementLocator+"");
			}
			}catch (Exception ex){
				Reports.reportFail(ex.getMessage(), FieldName);
			throw ex;
		}
	}

	public static void clickonTextBox(String elementLocator, String FieldName) throws Exception {
		elementLocator = getTestObject(elementLocator);
		WebElement elem = getElement(elementLocator);
		try{

			if (elem != null){
				elem.click();
				Reports.reportInfo("Textbox: clicked on "+FieldName+"");
			}
			else{
				Reports.reportFail("Textbox: Unable to click on "+FieldName+"element locator value is " +elementLocator+"");
			}
			}catch (Exception ex){
//			Reports.testSteps.Reports.test.log(LogStatus.INFO, "" +FieldName+" element is not present");
				Reports.reportFail(ex.getMessage(), FieldName);
			throw ex;
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
	public static String getTextFromControl(String elementLocator, String fieldName) throws Exception {
//		elementLocator = getTestObject(elementLocator);
		String ctext = null;
		try{
			WebElement elem = getElement(elementLocator);
		if (isElementPresent(By.xpath(elementLocator))) {
			elem = driver.findElement(By.xpath(elementLocator));	
			if (null != elem) {
			ctext = elem.getText();
			if (ctext != null) {
				Reports.reportInfo("ReadText:Text from the control "+ fieldName +" is " , ctext);

			} else {
				Reports.reportFail("ReadText: Unable to read text from "+fieldName+"element locator value is " +elementLocator+"");
			}
			}
		}
		}
		catch(Exception ex){
			Reports.reportFail(ex.getMessage(), fieldName);
			throw ex;
		}
		return ctext;
		}
	public static void switchFrameByIndex(int Index) {
		driver.switchTo().frame(Index);
		Reports.reportInfo("Executed Method switchFrameByIndex");
	}
	public static void switchFrameByFrameNameorId(String FrameNameOrId) throws Exception {
		FrameNameOrId = getTestObject(FrameNameOrId);
		(new WebDriverWait(driver, 45))
		  .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt((By.xpath(FrameNameOrId))));
		Reports.reportInfo("Switched Frame to "+FrameNameOrId);
	}
	public static void switchFrametoparent() throws InterruptedException {
		try{
		Thread.sleep(1000);
		driver.switchTo().defaultContent();
	Reports.reportInfo("Switched frame to parent screen");
		}
		catch(Exception ex){
			Reports.reportFail(ex.getMessage(), "Parent Frame");
		}
	}
	public static File screenshot( String Sname) throws IOException {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HH-mm-ss");
		Date date = new Date();
		String strTime = dateFormat.format(date);
				String screenshotPath = "./Reports&Screenshots/" + Sname + "-" + strTime + ".jpg";
			File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(source, new File(screenshotPath));
			String image=Reports.logger.addScreenCapture(screenshotPath);
			Reports.logger.log(LogStatus.INFO, Sname, image);

			System.out.println("********Screenshot captured*********");
		return null;
		}


	public static boolean isElementPresent(By by) {
		try {

			driver.findElement(by);
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}
	public static boolean waitForElementPresent(By by) {
		try {
			WebDriverWait wait = (new WebDriverWait(driver, 10));
			wait.until(ExpectedConditions.presenceOfElementLocated(by));

			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}
	
	
	public static String getTestObject(String objectName) throws Exception{
		try{
			String [] objectValue = xlobjectdata.globalObjectDetails.get(objectName);
					return xlobjectdata.orValue = objectValue[0];
		}
		catch (Exception e){
			return objectName;
		}
	}
	public static WebElement hoverLink(String elementLocator) throws Exception {
		elementLocator = getTestObject(elementLocator);
		WebElement elem = getElement(elementLocator);
		try{
		Actions builder = new Actions(driver);
		builder.moveToElement(elem).perform();
		// By locator = By.id(text);
		builder.moveToElement(elem).build().perform();
		if (driver instanceof JavascriptExecutor) {
			VerificationPoints.highlight(elem);
		}
		}
		catch(Exception ex){
			Reports.reportFail(ex.getMessage(), elementLocator);
			throw ex;
		}
		return elem;
	}
	public static void doubleclick(String text, CLICK_STRATEGY clickStrategy) throws Exception {
		text = getTestObject(text);
		WebElement elem = getElement(text);
		switch (clickStrategy) {
		case USING_ACTION:
			Actions action = new Actions(driver);
			action.doubleClick(elem).perform();
			break;
		case USING_JS:
			((JavascriptExecutor) driver)
					.executeScript(
							"var evt = document.createEvent('MouseEvents');"
									+ "evt.initMouseEvent('dblclick',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);"
									+ "arguments[0].dispatchEvent(evt);",
							elem);
			break;
		}

	}
	public enum CLICK_STRATEGY {
		USING_JS, USING_ACTION
	}

	public static String getCurrentDate() {
		 Calendar cal = Calendar.getInstance();
	     SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
	     return sdf.format(cal.getTime());
	}
	public static String getFutureDate(int FutureDate) {
		 Calendar cal = Calendar.getInstance();
	     SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
	     cal.add(Calendar.DATE, FutureDate);
	     return sdf.format(cal.getTime());
	}
	public static String getOlderDate(int olderDate) {
		 Calendar cal = Calendar.getInstance();
	     SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
	     cal.add(Calendar.DATE, olderDate);
	     return sdf.format(cal.getTime());
	}
	public static void storeGriddatabyRowNumber( int row, String gridid) throws Exception{
		String colName = null;
		String value =  null;

			int colCount = driver.findElements(By.xpath(gridid+ "/div[2]/div[1]/table/thead/tr/th")).size();
			// Retrieve data from each of the grid column and add it to cache
	
			for (int col = 1; col <= colCount; col++) {
				// Get column name
				HashMap<Integer, String> cellVal = new HashMap<Integer, String>();
				try{
					colName =Keywords.getElements(gridid+"/div[2]/div[1]/table/thead/tr/th["+col+"]").getText();
					
					if ( colName.contains("Upgrd")||colName.contains("DCI Upsell")||colName.contains("Self Park")){
						value =Keywords.getElements(gridid+"/div[3]/div[1]/table/tbody/tr["+row+"]/td[" +col+ "]/div/img").getAttribute("id");
					cellVal.put(row, value);
					xltestdata.testDataCache.put(colName, cellVal);
					}
					
					else {						
						value = Keywords.getElements(gridid+"/div[3]/div[1]/table/tbody/tr["+row+"]/td[" +col+ "]").getText();
						cellVal.put(row, value);
						xltestdata.testDataCache.put(colName, cellVal);
					}
					
				}
				catch(Exception ex ){
					Reports.reportInfo("Cell value is either blank or null");
				}
				
				}
					
		}


	public static String readGridDataByRowNumber(String columnName, int row){
		String objectSetVal = null;
		try{
		
			HashMap<Integer, String> varValue;
			if (columnName != null) {
				// Get the column name for the value to be retrieved
				varValue = xltestdata.testDataCache.get(columnName);
				// Get the value for the current imported row
				objectSetVal = varValue.get(row);
			}
			}
		catch(Exception ex ){
			Reports.reportInfo("Cell value is either blank or null" );
		}
		return objectSetVal;
	}
	public static void performActionOnDailyActivityGrid( int row, String colName, String gridid) throws Exception{



		try{
			
			if ( colName.contains("Late CheckOut Time")){
				if(Keywords.isElementPresent(By.xpath(gridid+"/div[3]/div[1]/table/tbody/tr["+row+"]/td[18]/select[@name='strLateCheckoutTime']"))){
					Keywords.selectValueFromDropDownByXPath(gridid+"/div[3]/div[1]/table/tbody/tr["+row+"]/td[18]/select[@name='strLateCheckoutTime']", "4:00 PM", "Late Check Out");
				}
				else{
					Reports.reportPass("Late Check Out control is not present");
				}
					}
			if(colName.contains("Early Check In")){
				if(Keywords.isElementPresent(By.xpath(gridid+ "/div[3]/div[1]/table/tbody/tr["+row+"]/td[20]/select[@id='check-all'"))){
					Keywords.selectCheckBox(gridid+ "/div[3]/div[1]/table/tbody/tr["+row+"]/td[20]/select[@id='check-all'", colName);
				}
				else{
					Reports.reportPass("Early Check In control is disabled");
				}
					}
			if ( colName.contains("Disabled Early Check In")){

				WebElement elem = driver.findElement(By.xpath(gridid+ "/div[3]/div[1]/table/tbody/tr["+row+"]/td[20]/select[@id='check-all'"));
				if (elem.isEnabled() == false){
				Reports.reportPass("Additional Access is disabled");
				}

			}	
			
		}
		catch(Exception ex ){
			Reports.reportInfo("Cell value is either blank or null");
		}
		
//		}
				
	}
public static void HandleDialog() throws Exception{
	
	Runtime.getRuntime().exec("C:\\Users\\Santosh Geernur\\workspace\\Digital_Key_Scripts\\DigitalKeyDashboard\\src\\AutoIt\\ClickOkDialogClass.exe");

}
public static void HandleWindow() throws Exception{
	
	Runtime.getRuntime().exec("C:\\Users\\Santosh Geernur\\workspace\\Digital_Key_Scripts\\DigitalKeyDashboard\\src\\AutoIt\\ClickOkWindowClass.exe");

}
public static WebElement getElement(String elementLocator) {
	 WebElement elem =  null;
	 WebDriverWait wait = new WebDriverWait(driver, 15);

	try{
		if (isElementPresent(By.xpath(elementLocator))) {
			 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementLocator)));
		 elem = driver.findElement(By.xpath(elementLocator));
		 
	} else if (isElementPresent(By.linkText(elementLocator))) {
			elem = driver.findElement(By.linkText(elementLocator));
	}else if (isElementPresent(By.id(elementLocator))) {
			elem = driver.findElement(By.id(elementLocator));
	} else if (isElementPresent(By.name(elementLocator))) {
			elem = driver.findElement(By.name(elementLocator));
	} else if (isElementPresent(By.cssSelector(elementLocator))) {

		elem = driver.findElement(By.cssSelector(elementLocator));
	}
    
    }
    catch (Exception e) {
    	Reports.reportInfo("Element is either disabled or not present");
        e.printStackTrace();
        }
	
	return elem;
	    }

public static WebElement getElements(String elementLocator) {
	WebElement elem =  null;
	if (isElementPresent(By.xpath(elementLocator))) {
		 elem = driver.findElement(By.xpath(elementLocator));	
	} 
	return elem;
	    }
public static WebElement fluentWaitforElement(WebElement element, int timoutSec, int pollingSec) {

    FluentWait<WebDriver> fWait = new FluentWait<WebDriver>(driver).withTimeout(timoutSec, TimeUnit.SECONDS)
        .pollingEvery(pollingSec, TimeUnit.SECONDS)
        .ignoring(NoSuchElementException.class, TimeoutException.class).ignoring(StaleElementReferenceException.class);
    for (int i = 0; i < 2; i++) {
        try {
        fWait.until(ExpectedConditions.visibilityOf(element));
        fWait.until(ExpectedConditions.elementToBeClickable(element));

        } catch (Exception e) {

        System.out.println("Element Not found trying again - " + element.toString().substring(70));
        e.printStackTrace();
        }
    }
    return element;

    }

public static void TakeScreenshot() throws IOException {
    TakesScreenshot ts = (TakesScreenshot)driver;
    File source = ts.getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(source, new File("./Screenshots.png"));
    System.out.println("the Screenshot is taken");
}
public void waitFor(int iSec) {

	try {
		Thread.sleep(iSec * 1000);
	} catch (Exception e) {
	}
	}

public WebElement waitForAnElementTobeLocated(String elementidentifier, int sec) throws InterruptedException, IOException 

{   
 WebDriverWait wait = new WebDriverWait(driver, sec);
 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementidentifier)));
 driver.findElement(By.xpath(elementidentifier)).sendKeys("Text box is visible now");
return null;


   }

}
