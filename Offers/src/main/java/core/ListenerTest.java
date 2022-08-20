package core;

import java.io.IOException;

import org.testng.ITestListener ;		
import org.testng.ITestResult ;		

public class ListenerTest implements ITestListener{
	
    @Override		
    public void onTestStart(ITestResult arg0) {					
        System.out.println("Test has started");			
        		
    }		

    @Override		
    public void onTestSuccess(ITestResult arg0) {					
        try {
			Keywords.screenshot("Test Passed");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
        		
    }	
    
    //
    @Override		
    public void onTestFailure(ITestResult arg0) {					

        try {
			Keywords.screenshot("Test Failed");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        		
    }
	

}
