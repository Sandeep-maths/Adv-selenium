package genericimplimentation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import genericUtilities.ExcelUtility;
import genericUtilities.Iconstantpath;
import genericUtilities.JavaUtility;
import genericUtilities.PropertiesUtility;
import genericUtilities.WebDriverUtility;

public class CreateLeads {
	public WebElement path(WebDriver d, String s) {
		WebElement data = d.findElement(By.xpath(s));

		return data;
	}

	public static void main(String[] args) throws InterruptedException {
		PropertiesUtility pu = new PropertiesUtility();
		ExcelUtility eu = new ExcelUtility();
		JavaUtility ju = new JavaUtility();
		WebDriverUtility du = new WebDriverUtility();

		pu.propertiesInit(Iconstantpath.PROPERTIES_FILE_PATH);
		eu.excelInit("./src/test/resources/testdata.xlsx");

		WebDriver d = du.launchBrowser(pu.readdata("browser"));
		du.maximizeBrowser();
		long t = (Long) ju.ConvertStringToAnyDataType(pu.readdata("timeouts"), "long");
		du.waitTillElementFound(t);

		du.navigateToWebApp(pu.readdata("url"));

		CreateLeads x = new CreateLeads();

		x.path(d, "//input[@name='user_name']").sendKeys(pu.readdata("un"));
		x.path(d, "//input[@name='user_password']").sendKeys(pu.readdata("pswd"));
		WebElement login = x.path(d, "//input[@id='submitButton']");
		du.click(login);

		if (d.getTitle().contains(eu.readFromExcel("leads", 1, 1))) {
			System.out.println("vtiger home page success");
		} else {
			System.out.println("vtiger home page failed");
			du.quitAllWindows();
		}

		WebElement lead = x.path(d, "(//a[normalize-space()='Leads'])[1]");
		du.click(lead);

		if (d.getTitle().contains(eu.readFromExcel("leads", 3, 1))) {
			System.out.println("leads  page success");
		} else {
			System.out.println("leads page failed");
			du.quitAllWindows();
		}

		x.path(d, "//img[@title='Create Lead...']").click();

		WebElement e = x.path(d, "//select[@name='salutationtype']");
		du.handleDropDown(e, 5);

		x.path(d, "//input[@name='lastname']").sendKeys(eu.readFromExcel("leads", 7, 1));
		x.path(d, "//input[@name='phone']").sendKeys(eu.readFromExcel("leads", 8, 1));
		x.path(d, "//input[@name='company']").sendKeys(eu.readFromExcel("leads", 9, 1));
		x.path(d, "//input[@name='email']").sendKeys(eu.readFromExcel("leads", 10, 1));
		x.path(d, "//input[@name='website']").sendKeys(eu.readFromExcel("leads", 11, 1));

		WebElement ls = x.path(d, "//select[@name='leadsource']");
		du.handleDropDown(ls, 3);

		WebElement lst = x.path(d, "//select[@name='leadstatus']");
		du.handleDropDown(lst, 9);

		WebElement ind = x.path(d, "//select[@name='industry']");
		du.handleDropDown(ind, 2);

		x.path(d, "//textarea[@name='lane']").sendKeys(eu.readFromExcel("leads", 13, 1));

		x.path(d, "//input[@name='city']").sendKeys(eu.readFromExcel("leads", 14, 1));

		x.path(d, "//input[@name='state']").sendKeys(eu.readFromExcel("leads", 15, 1));

		x.path(d, "//textarea[@name='description']").sendKeys(eu.readFromExcel("leads", 17, 1));

		x.path(d, "(//input[@value='  Save  '])[2]").click();

		Thread.sleep(3000);

		x.path(d, "(//input[@title='Duplicate [Alt+U]'])[1]").click();

		Thread.sleep(3000);

		WebElement dup = x.path(d, "//input[@name='lastname']");
		dup.clear();
		dup.sendKeys(eu.readFromExcel("leads", 19, 1));

		x.path(d, "(//input[@value='  Save  '])[2]").click();

		Thread.sleep(3000);
		
		WebElement header = x.path(d, "//span[@class='dvHeaderText']");

		if (header.getText().contains(eu.readFromExcel("leads", 19, 1))) {
			System.out.println("leo das -  lead is created successfully");

		} else {
			System.out.println("leo das -  lead is not  created successfully");
			du.quitAllWindows();
		}
		
		WebElement icon = x.path(d, "//img[@src='themes/softed/images/user.PNG']");

		du.mouseHover(icon);

		x.path(d, "//a[text()='Sign Out']").click();
		eu.closeExcel();
		du.quitAllWindows();

	}

}
