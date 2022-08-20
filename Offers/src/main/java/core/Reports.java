package core;

import java.io.IOException;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Reports {
	public static ExtentReports extent;
	public static ExtentTest logger;
	
	
	public static void reportInfo(String value ){
		value = "  "  +value+ "  ";
		logger.log(LogStatus.INFO, value );
	}
	public static void reportInfo(String Description, String value ){
		value = "  "  +value+ "  ";
		String logvalue = "<span title='"+value+ "' alt='"+value+ "' class= '"+value+ ""+"label blue lighten-1 text-white'>"+"   "+value+ "    "+  "</span>";
		logger.log(LogStatus.INFO, Description+ " : " +logvalue );
	}

	public static void reportPass( String ActualResult, String ExpectedResult ){
		ActualResult = "  " +ActualResult+ "  ";
		ExpectedResult = "  " +ExpectedResult+ "  ";
		String logActualResult = "<span title='"+ActualResult+ "' alt='"+ActualResult+ "' class= '"+ActualResult+ "label green lighten-1 text-white'>"+"   "+ActualResult+ "    "+  "</span>";
		String logExpectedResult = "<span title='"+ExpectedResult+ "' alt='"+ExpectedResult+ "' class= '"+ExpectedResult+ "label green lighten-1 text-white'>"+"   "+ExpectedResult+ "    "+  "</span>";
		logger.log(LogStatus.PASS,  "Actual Result is:   "+logActualResult + "Expected Result is:  " +logExpectedResult);
	}
	public static void reportPass( String result ){
		result = "  " +result+ "  ";
		
		String logResult = "<span title='"+result+ "' alt='"+result+ "' class= '"+result+ "label green lighten-1 text-white'>"+"   "+result+ "    "+  "</span>";
		logger.log(LogStatus.PASS,  "  "+logResult );
	}
	public static void reportFail( String ActualResult, String ExpectedResult ){
		ActualResult = "  " +ActualResult+ "  ";
		ExpectedResult = "  " +ExpectedResult+ "  ";
		String logActualResult = "<span title='"+ActualResult+ "' alt='"+ActualResult+ "' class= '"+ActualResult+ "label red lighten-1 text-white'>"+"   "+ActualResult+ "    "+  "</span>";
		String logExpectedResult = "<span title='"+ExpectedResult+ "' alt='"+ExpectedResult+ "' class= '"+ExpectedResult+ "label red lighten-1 text-white'>"+"   "+ExpectedResult+ "    "+  "</span>";
		logger.log(LogStatus.FAIL,  "Actual Result is:   "+logActualResult + "Expected Result is:  " +logExpectedResult);
	}
	public static void reportFail( String result ) throws IOException{
		result = "  " +result+ "  ";
		
		String logResult = "<span title='"+result+ "' alt='"+result+ "' class= '"+result+ "label red lighten-1 text-white'>"+"   "+result+ "    "+  "</span>";
		logger.log(LogStatus.FAIL,  logResult+ Keywords.screenshot("Screenshot"));
	}

}
