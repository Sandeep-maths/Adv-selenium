package datadrivenTesting;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadDataFromProperites {
public static void main(String[] args) throws IOException {
	FileInputStream fis = new FileInputStream("./src/test/resources/data.properties");
	Properties pro = new Properties();
	pro.load(fis);
	System.out.println(pro.getProperty("url"));
}
}
