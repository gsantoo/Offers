package core;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;

public class xlobjectdata {

	public static Map<String, HashMap<Integer, String>> testDataCache = new HashMap<String, HashMap<Integer, String>>();
	public static Map<String, String[]> globalObjectDetails = new HashMap<String, String[]>();
	public static String orName;
	public static int gorRowCount = 0;
	public static Sheet gorSheet = null;
	public static String orValName = null;
	public static String orValue = null;

	public static void storeGlobalTestObjectDetails(String globalORPath, String sheetName) {
		FileInputStream fs2 = null;
		WorkbookSettings ws2 = null;

		try {
			fs2 = new FileInputStream(new File(globalORPath));
			ws2 = new WorkbookSettings();
			ws2.setLocale(new Locale("en", "EN"));
		} catch (Exception e) {
		}

		try {
			Workbook ORworkbook = Workbook.getWorkbook(fs2, ws2);
//			gorSheet = ORworkbook.getSheet(0);
			gorSheet = ORworkbook.getSheet(sheetName);
			gorRowCount = gorSheet.getRows();
		} catch (Exception e) {

		}

		for (int g = 1; g < gorRowCount; g++) {
			String key = gorSheet.getCell(1, g).getContents();
			String[] value = new String[1];
			value[0] = gorSheet.getCell(2, g).getContents();
			globalObjectDetails.put(key, value);
		}

}
	
	

}