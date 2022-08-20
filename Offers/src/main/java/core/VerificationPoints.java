package core;




import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.LogStatus;

public class VerificationPoints {
	public static boolean isVerification = false;
	public static String actualUiValue = null;
	
	public static boolean verifyTextBlock(String elementLocator,  String expectedresult) throws Exception {
		boolean isFound = false;
		elementLocator = Keywords.getTestObject(elementLocator);
		try{
			WebElement elem = Keywords.getElement(elementLocator);
			if (null != elem) {
				isFound = elem.getText().contains(expectedresult);
				if (isFound) {
					isVerification = true;
					 actualUiValue= elem.getText();
//					TestSteps.test.log(LogStatus.PASS, "Expected Result :"+expectedresult+ "    Actual Result: " +TestSteps.actualUiValue+"" );
					Reports.reportPass(actualUiValue, expectedresult);
				} else {
					isVerification = false;
					actualUiValue = elem.getText();
//					TestSteps.test.log(LogStatus.FAIL, "Expected Result :"+expectedresult+ "    Actual Result: " +TestSteps.actualUiValue+"");
					Reports.reportFail(actualUiValue, expectedresult);
				}

			}
				return isFound;
			}
		catch(Exception ex){
			Reports.reportFail("Could not verify the text block");
			throw ex;
		}
		}


	public static boolean verifyControlPresence(String elementLocator,  String FieldName) throws Exception {
		elementLocator = Keywords.getTestObject(elementLocator);
		boolean isFound = false;
		WebElement elem = null;
		try{
			if (Keywords.isElementPresent(By.xpath(elementLocator))) {
				elem = Keywords.driver.findElement(By.xpath(elementLocator));
			}
				if (elem !=null){
						Reports.reportPass(FieldName+"  Control is present");
						isFound = true;
					}
					else{
						Reports.reportFail(FieldName+"  Control is not present");
						isFound = false;
					}
		}
		catch(Exception ex){
			Reports.reportFail("Could not find element");
			throw ex;
		}
			return isFound;
		}
	public static boolean verifyControl(String elementLocator,  String FieldName) throws Exception {
		elementLocator = Keywords.getTestObject(elementLocator);
		boolean isFound = false;
		WebElement elem = null;
		try{
			if (Keywords.isElementPresent(By.xpath(elementLocator))) {
				elem = Keywords.driver.findElement(By.xpath(elementLocator));
			}
				if (elem !=null){
						isFound = true;
					}
					else{
						isFound = false;
					}
		}
		catch(Exception ex){
			isFound = false;
			throw ex;
		}
			return isFound;
		}
	public static void highlight(WebElement webElement) {

		JavascriptExecutor js = (JavascriptExecutor) Keywords.driver;
		for (int i = 0; i < 2; i++) {
			try {
				if (Keywords.driver instanceof JavascriptExecutor) {
					js.executeScript("arguments[0].style.border='3px solid red'", webElement);
					Thread.sleep(500);
					js.executeScript("arguments[0].style.border=''", webElement);
				}
			} catch (Exception e) {
				Reports.logger.log(LogStatus.FAIL, "Exception caught while highlighting element: " + webElement + ": " + e.getMessage());
			}
		}
	}
	
	public static void verify_URL(String url) {

		WebElement elem = (new WebDriverWait(Keywords.driver, 45))
				  .until(ExpectedConditions.presenceOfElementLocated(By.xpath(url)));
		actualUiValue = Keywords.driver.getCurrentUrl();
		 	if(url.equals(actualUiValue)){
		 		Reports.reportPass(actualUiValue, url);
		 } 
		 	else {
		 		Reports.reportFail(actualUiValue, url);
		 }
		 	}
	
	public static boolean verifyFocus(String elementLocator, String fieldName) throws Exception {
		elementLocator = Keywords.getTestObject(elementLocator);
//		WebElement elem = (new WebDriverWait(Keywords.driver, 45))
//				  .until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementLocator)));
		WebElement elem = Keywords.getElement(elementLocator);
		boolean Focusvalue = false;
		elem = Keywords.driver.findElement(By.xpath(elementLocator));


		if ((elem).equals(Keywords.driver.switchTo().activeElement())) {
			Focusvalue = true;
			Reports.reportPass(fieldName+"  Focus is present");

		} else {
			Reports.reportPass(fieldName+"  Focus is not present");
		}
		return Focusvalue;
	}
	
	public static Boolean verifyElementAttribute(String ElementLocator, String Attribute, String ExpValue) throws Exception{
		String AttributeValue = null;
		String elem = Keywords.getTestObject(ElementLocator);
		Boolean isFound = null;
		try{
			AttributeValue = Keywords.getElement(elem).getAttribute(Attribute);
			if (AttributeValue.equals(ExpValue)){
			Reports.reportPass(AttributeValue, ExpValue);
			isFound = true;
				}
		else {
			Reports.reportFail(AttributeValue, ExpValue);
			isFound =  false;
		}
		}
		catch(Exception ex){
			Reports.reportFail("Cannot find Object" +ex);
		}
		return isFound;
		}
}
	

