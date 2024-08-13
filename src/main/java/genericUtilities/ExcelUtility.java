package genericUtilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * This class contains reusable methods to read/write data from excel
 */
public class ExcelUtility {
	private Workbook wb;
	private DataFormatter df;
	
	/**
	 * This method is used initializes excel
	 * @param excelpath 
	 */
	public void excelInit(String excelpath)
	{
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(excelpath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			wb = WorkbookFactory.create(fis);
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		df = new DataFormatter();
	}
	/**
	 * This method fetches data from specified cell
	 * @param sheetname
	 * @param rownum
	 * @param cellnum
	 * @return
	 */
	public String readFromExcel(String sheetname, int rownum, int cellnum)
	{
		return df.formatCellValue(wb.getSheet(sheetname).getRow(rownum).getCell(cellnum));
	}
	/**
	 * This fetches the data required for specified test case
	 * @param sheetname
	 * @param expectedTestName
	 * @return
	 */
	public  Map<String, String> readFromExcel(String sheetname, String expectedTestName)
	{
		Map<String, String> map = new HashMap<>();
 		Sheet sh = wb.getSheet(sheetname);
		int requiredRowNum = 0;
		for(int i = 0; i <= sh.getLastRowNum(); i++) {
			requiredRowNum = i;
			if(df.formatCellValue(sh.getRow(i).getCell(1)).equalsIgnoreCase(expectedTestName)) {
				break;
			}
		}
		for(int i = requiredRowNum; i <= sh.getLastRowNum(); i++) {
			if(df.formatCellValue(sh.getRow(i).getCell(2)).equals("####")) {
				break;
			}
			map.put(df.formatCellValue(sh.getRow(i).getCell(2)), df.formatCellValue(sh.getRow(i).getCell(3)) );
		}
		
		return map;
	}
	/**
	 * This method writes to excel
	 * @param sheetName
	 * @param expectedTestName
	 * @param status
	 */
	public void writeToExcel(String sheetName, String expectedTestName, String status)
	{
		Sheet sh = wb.getSheet(sheetName);
		for(int i = 0; i <= sh.getLastRowNum(); i++) {
			if(df.formatCellValue(sh.getRow(i).getCell(1)).equalsIgnoreCase(expectedTestName)) {
				sh.getRow(i).createCell(4).setCellValue(status);
				break;
			}
		}
	}
	/**
	 * This method is used save excel
	 * @param excelpath
	 */
	public void saveExcel(String excelpath)
	{
		FileOutputStream fos=null;
		try {
			fos = new FileOutputStream(excelpath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			wb.write(fos);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * This method closes the workbook
	 */
	public void closeExcel() {
		try {
			wb.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * This method is used save excel
	 * @param sheetname
	 * @param rownum
	 * @param cellnum
	 * @param status
	 */
	public void writeFromExcel(String sheetname, int rownum, int cellnum, String status)
	{
	  wb.getSheet(sheetname).getRow(rownum).createCell(cellnum).setCellValue(status);
	}
}
