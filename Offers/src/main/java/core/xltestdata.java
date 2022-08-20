package core;


import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class xltestdata {
	private static Sheet dtSheet;
	private static WritableSheet wtSheet;
	public static Map<String, HashMap<Integer, String>> testDataCache = new HashMap<String, HashMap<Integer, String>>();
	public static String orName;
	public static String objectSetVal = null;
	public static WritableWorkbook wwbCopy;
	public static Workbook wbook;
	
	public static WritableSheet shSheet;


	public static Sheet readGlobalTestData(String xcelpath) throws Exception {
		FileInputStream fs1 = null;
		WorkbookSettings ws1 = null;
		fs1 = new FileInputStream(new File(xcelpath));
		ws1 = new WorkbookSettings();
		ws1.setLocale(new Locale("en", "EN"));
		Workbook DTworkbook = Workbook.getWorkbook(fs1, ws1);
		String sheet = "";
		sheet = DTworkbook.getSheet(0).getName();
		dtSheet = DTworkbook.getSheet(sheet);
		return dtSheet;
	}
	public static void addDataToCache(int row) {
		// list is containing the test data with column name
		List<String> list = new ArrayList<String>();
		// Add the data from all the rows from excel that is to be imported to cache
		String colName = "";
		// Retrieve data from each of the excel column and add it to cache
		for (int col = 0; col < dtSheet.getColumns(); col++) {
			HashMap<Integer, String> cellVal = new HashMap<Integer, String>();
			// Get column name
			colName = dtSheet.getCell(col, 0).getContents();
			// Get data from all the rows for the current column and add to
			// cache
				cellVal.put(row, dtSheet.getCell(col, row).getContents());
			testDataCache.put(colName, cellVal);
		}
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < dtSheet.getColumns(); j++) {
				orName = dtSheet.getCell(j, i).getContents();
				list.add(orName.toString());
			}
		}
	}
	public static String readTestDataFromCache(String columnName, int row) {
		HashMap<Integer, String> varValue;
		// Check if data is to be retrieved from cache
		if (columnName != null) {
				// Get the column name for the value to be retrieved
				varValue = testDataCache.get(columnName);
				// Get the value for the current imported row
				objectSetVal = varValue.get(row);
		}
		return objectSetVal;
	}
	public static WritableSheet writeOutPutTestData(String xcelpath, String OutPutxcelpath) throws Exception {

		wwbCopy = Workbook.createWorkbook(new File(OutPutxcelpath));
		WritableSheet shSheet = wwbCopy.createSheet("Reservations", 0);
		return shSheet;
	}
    public static void setHeaderIntoCell(String strSheetName, String columnName, int columnNumber) throws WriteException
    {
    	WritableSheet wshTemp = wwbCopy.getSheet(strSheetName);
    	WritableFont headerCellFont = new WritableFont(WritableFont.ARIAL, 10,
				WritableFont.BOLD, false);
    	WritableCellFormat headerCellFormat = new WritableCellFormat(headerCellFont);
    	Label headerCell1 = new Label(columnNumber, 0, columnName, headerCellFormat);
        try {
        	wshTemp.addCell(headerCell1);
             } 
            catch (Exception e) 
            {
                e.printStackTrace();
            }
	}
    public static void setValueIntoCell(String strSheetName,int iColumnNumber, int iRowNumber,String strData) throws WriteException
    {
    	WritableSheet wshTemp = wwbCopy.getSheet(strSheetName);
          Label labTemp = new Label(iColumnNumber, iRowNumber, strData);
        try {
            wshTemp.addCell(labTemp);
             } 
            catch (Exception e) 
            {
                e.printStackTrace();
            }
	}
    public static void closeFile()
    {
        try {
            // Closing the writable work book
            wwbCopy.write();
            wwbCopy.close();
            // Closing the original work book
//            wbook.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
