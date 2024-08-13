package utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Utility {
	
	
	public String pro(String path, String key) throws IOException {

		FileInputStream fis = new FileInputStream(path);

		Properties p = new Properties();

		p.load(fis);

		String data = p.getProperty(key);

		return data;

	}
	
	public String exc(String path, String sh, int row, int cel) throws IOException
	{
		FileInputStream fis = new FileInputStream(path);
		
		Workbook wb = WorkbookFactory.create(fis);
		
		DataFormatter df = new DataFormatter();
		
		String data = df.formatCellValue(wb.getSheet(sh).getRow(row).getCell(cel));
		
		return data;
	}


}
