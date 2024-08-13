package genericUtilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
/**
 * This class contains reusable methods to read from properties file
 */
public class PropertiesUtility {
	Properties pro;
	/**
	 * This method initializes properties file
	 * @param fp
	 */
	public void propertiesInit(String fp)
	{
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(fp);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		pro = new Properties();
		try {
			pro.load(fis);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	/**
	 * This method will fetch the value for specified key from properties file
	 * @param key
	 * @return String
	 */

	public String readdata(String key) {
		
		return pro.getProperty(key);
	}

}
