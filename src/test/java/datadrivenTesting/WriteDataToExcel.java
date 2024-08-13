package datadrivenTesting;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WriteDataToExcel {
	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream("./src/test/resources/testdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("Sheet1");
		
		sh.createRow(5).createCell(0).setCellValue("subject");
		sh.getRow(5).createCell(1).setCellValue("java");
		
		
		FileOutputStream fos = new  FileOutputStream("./src/test/resources/testdata.xlsx");
		wb.write(fos);
		
		wb.close();
		
	}

}
