package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Utilites {
	public String readp(String key) throws IOException {

		FileInputStream fis = new FileInputStream("./src/test/resources/data.properties");

		Properties p = new Properties();

		p.load(fis);

		String data = p.getProperty(key);

		return data;

	}
	
	public String reade(String sh, int row, int cel) throws IOException
	{
		FileInputStream fis = new FileInputStream("./src/test/resources/testdata.xlsx");
		
		Workbook wb = WorkbookFactory.create(fis);
		
		DataFormatter df = new DataFormatter();
		
		String data = df.formatCellValue(wb.getSheet(sh).getRow(row).getCell(cel));
		
		return data;
	}

}
