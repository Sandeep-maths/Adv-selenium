package hardcodedScripts;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;


import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import utility.Utility;

public class FlipkartDataWriteInExcel {

	public static void main(String[] args) throws IOException {

		String pp = "./src/test/resources/filp.properties";

		WebDriver d = new ChromeDriver();
		d.manage().window().maximize();

		// use utility

		Utility u = new Utility();
		String time = u.pro(pp, "time");
		Long t = Long.parseLong(time);
		d.manage().timeouts().implicitlyWait(Duration.ofSeconds(t));
		d.get(u.pro(pp, "url"));

		// check correct site or not

		if (d.getCurrentUrl().contains("flipkart")) {
			System.out.println("flipkart page success");
		} else {

			System.out.println("flipkart page falied");
			d.quit();
		}

		// type

		d.findElement(By.xpath("//input[@placeholder='Search for Products, Brands and More']"))
				.sendKeys(u.pro(pp, "type"));
		d.findElement(By.xpath("//*[name()='svg' and @width='24']")).click();

		List<WebElement> es = d.findElements(By.xpath("//div[@class='KzDlHZ']"));

		FileInputStream fis = new FileInputStream("./src/test/resources/flip.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("Sheet1");

		for (int i = 0; i< es.size(); i++) {
			
			String m = es.get(i).getText();
			String p = d.findElement(By.xpath("//div[text()='"+m+"']/ancestor::div[@class='yKfJKb row']/descendant::div[@class='Nx9bqj _4b5DiR']")).getText();
			sh.createRow(i).createCell(0).setCellValue(m);
			sh.getRow(i).createCell(1).setCellValue(p);
			System.out.println(m+"\t"+p);

		}

		FileOutputStream fos = new FileOutputStream("./src/test/resources/flip.xlsx");
		wb.write(fos);

		wb.close();
		
		d.quit();

	}

}
