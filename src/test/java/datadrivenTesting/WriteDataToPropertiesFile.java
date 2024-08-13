package datadrivenTesting;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class WriteDataToPropertiesFile {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileInputStream fis = new FileInputStream("./src/test/resources/data.properties");
		
		Properties property =new Properties();
		
		property.load(fis);
		
		property.put("url1", "amazon");
		
		FileOutputStream fos = new FileOutputStream("./src/test/resources/data.properties");
		
		property.store(fos, "data updated");

	}

}
