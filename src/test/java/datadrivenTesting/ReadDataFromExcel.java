package datadrivenTesting;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadDataFromExcel {
	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		// 1 convert physical file into java readable object
		FileInputStream fis = new FileInputStream("./src/test/resources/testdata.xlsx");

		// 2 open workbook

		Workbook wb = WorkbookFactory.create(fis);

		// 3 get control to the sheet

		Sheet sh = wb.getSheet("Sheet1");

		// 4 get control to the row

		Row ro = sh.getRow(4);

		// 5 get control to the cell
		
		Cell cel = ro.getCell(1);
		
		// 6 fetch the value
		
		System.out.println(cel.getStringCellValue());
		
		
		// method chaining concept
		
		 double data = wb.getSheet("Sheet1").getRow(2).getCell(1).getNumericCellValue();
		 System.out.println(data);
		 
		 //to over come numeric
		 // we will go with data formatter
		 
		 DataFormatter df = new DataFormatter();
		 
		  String data1 = df.formatCellValue(wb.getSheet("Sheet1").getRow(2).getCell(1));
		  System.out.println(data1);
		 
		// 7 close the workbook
			wb.close();
		 
		
		

	}

}
