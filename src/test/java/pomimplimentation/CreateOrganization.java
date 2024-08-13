package pomimplimentation;

import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import genericUtilities.ExcelUtility;
import genericUtilities.Iconstantpath;
import genericUtilities.JavaUtility;
import genericUtilities.PropertiesUtility;
import genericUtilities.TabNames;
import genericUtilities.WebDriverUtility;
import objectRepository.CreatingNewOrganizationPage;
import objectRepository.HomePage;
import objectRepository.LoginPage;
import objectRepository.OrganizationInformationPage;
import objectRepository.OrganizationPage;

public class CreateOrganization {

	WebDriver d;

	public WebElement ele(WebDriver d, String e) {
		this.d = d;
		WebElement ele = d.findElement(By.xpath(e));
		return ele;
	}

	public static void main(String[] args) throws InterruptedException {
		
		PropertiesUtility prou = new PropertiesUtility();
		ExcelUtility exu = new ExcelUtility();
		JavaUtility jau = new JavaUtility();
		WebDriverUtility driu = new WebDriverUtility();
		
		prou.propertiesInit(Iconstantpath.PROPERTIES_FILE_PATH);
		exu.excelInit("./src/test/resources/testdata.xlsx");
		
		WebDriver dri = driu.launchBrowser(prou.readdata("browser"));
		driu.maximizeBrowser();
		driu.navigateToWebApp(prou.readdata("url"));
		long time = (Long)jau.ConvertStringToAnyDataType(prou.readdata("timeouts"), "long");
		driu.waitTillElementFound(time);
		
		LoginPage li = new LoginPage(dri);
		HomePage ho = new HomePage(dri);
		OrganizationPage org = new OrganizationPage(dri);
		CreatingNewOrganizationPage corg = new CreatingNewOrganizationPage(dri);
		OrganizationInformationPage iorg = new OrganizationInformationPage(dri);
	

		if (dri.getTitle().contains(exu.readFromExcel("org", 2, 1))) {
			System.out.println("navigated to correct login resource");
		} else {
			System.out.println("not navigated to vtiger login page");
			driu.quitAllWindows();
		}
		li.loginToVtiger(prou.readdata("un"), prou.readdata("pswd"));
//		dri.findElement(By.xpath("//input[@name='user_name']")).sendKeys(prou.readdata("un"));
//		dri.findElement(By.xpath("//input[@name='user_password']")).sendKeys(prou.readdata("pswd"));
//		dri.findElement(By.xpath("//input[@id='submitButton']")).click();

		if (dri.getTitle().contains(exu.readFromExcel("org", 1, 1))) {
			System.out.println("navigated to correct home resource");
		} else {
			System.out.println("not navigated to vtiger home page");
			driu.quitAllWindows();
		}
		
		ho.clickRequiredTab(driu, TabNames.ORGANIZATIONS);
		//dri.findElement(By.xpath("//a[text()='Organizations' and contains(@href,'Accounts&action=index')]")).click();

		if (dri.getTitle().contains(exu.readFromExcel("org", 0, 1))) {
			System.out.println("navigated to correct organizations resource");
		} else {
			System.out.println("not navigated to vtiger Organization page");
			driu.quitAllWindows();
		}
		org.clickcreateOrgBTN();
		//dri.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();

		/*
		 * WebElement page = dri.findElement(By.
		 * xpath("//span[contains(text(),'Creating New Organization')]")); if
		 * (page.isDisplayed()) {
		 * System.out.println("navigated to correct organizations create resource"); }
		 * else {
		 * System.out.println("not navigated to vtiger Organization create page");
		 * dri.quit(); }
		 */

		//WebElement page = dri.findElement(By.xpath("//span[contains(@class,'lvtHeaderText')]"));
		if (corg.getPageHeader().contains("Creating New Organization")) {
			System.out.println("navigated to correct organizations create resource");
		} else {
			System.out.println("not navigated to vtiger Organization create page");
			driu.quitAllWindows();
		}

		CreateOrganization co = new CreateOrganization();
	

		co.ele(dri, "//input[@name='accountname']").sendKeys(exu.readFromExcel("org", 4, 1));

		co.ele(dri, "//input[@style='width:74%;']").sendKeys(exu.readFromExcel("org", 5, 1));

		co.ele(dri, "//input[@id='phone']").sendKeys(exu.readFromExcel("org", 6, 1));

		co.ele(dri, "//input[@name='tickersymbol']").sendKeys(exu.readFromExcel("org", 7, 1));

		co.ele(dri, "//input[@id='fax']").sendKeys(exu.readFromExcel("org", 8, 1));

		co.ele(dri, "//input[@id='otherphone']").sendKeys(exu.readFromExcel("org", 9, 1));

		co.ele(dri, "//input[@id='employees']").sendKeys(exu.readFromExcel("org", 10, 1));

		co.ele(dri, "//input[@id='email1']").sendKeys(exu.readFromExcel("org", 11, 1));

		co.ele(dri, "//input[@id='email2']").sendKeys(exu.readFromExcel("org", 12, 1));

		co.ele(dri, "//input[@id='ownership']").sendKeys(exu.readFromExcel("org", 13, 1));

		WebElement industry = co.ele(dri, "//select[@name='industry']");
		
		driu.handleDropDown(industry, 14);

		WebElement rating = co.ele(dri, "//select[@name='rating']");

		driu.handleDropDown(rating, 2);

		WebElement type = co.ele(dri, "//select[@name='accounttype']");

		driu.handleDropDown(type, 5);

		co.ele(dri, "//input[@id='siccode']").sendKeys(exu.readFromExcel("org", 14, 1));

		co.ele(dri, "//input[@name='emailoptout']").click();

		WebElement an = co.ele(dri, "//input[@name='annual_revenue']");

		an.clear();
		an.sendKeys(exu.readFromExcel("org", 15, 1));

		co.ele(dri, "//input[@name='notify_owner']").click();

		co.ele(dri, "//textarea[@name='bill_street']").sendKeys(exu.readFromExcel("org", 19, 1));

		co.ele(dri, "//input[@name='bill_pobox']").sendKeys(exu.readFromExcel("org", 20, 1));

		co.ele(dri, "//input[@name='bill_city']").sendKeys(exu.readFromExcel("org", 21, 1));

		co.ele(dri, "//input[@name='bill_state']").sendKeys(exu.readFromExcel("org", 22, 1));

		co.ele(dri, "//input[@name='bill_code']").sendKeys(exu.readFromExcel("org", 23, 1));

		co.ele(dri, "//input[@name='bill_country']").sendKeys(exu.readFromExcel("org", 24, 1));

		co.ele(dri, "//input[@onclick='return copyAddressRight(EditView)']").click();

		co.ele(dri, "//textarea[@name='description']").sendKeys(exu.readFromExcel("org", 26, 1));

		co.ele(dri, "(//input[@class='crmbutton small save'])[2]").click();

		jau.waiting(3000);

		WebElement header = co.ele(dri, "//span[@class='dvHeaderText']");

		if (header.getText().contains("sysco")) {
			System.out.println("sysco -  Organization is created successfully");

		} else {
			System.out.println("sysco -  Organization is not  created successfully");
			driu.quitAllWindows();
		}
		
		iorg.clickDeleteBTN();

		//co.ele(dri, "(//input[@class='crmbutton small delete'])[1]").click();

		driu.handleAlert("ok");

		jau.waiting(3000);

		if (dri.getPageSource().contains(exu.readFromExcel("org", 4, 1))) {
			System.out.println("sysco organization is present.");
		} else {
			System.out.println("sysco organization is deleted.");

		}
	
		ho.signOutFromVtiger(driu);
//		WebElement icon = co.ele(dri, "//img[@src='themes/softed/images/user.PNG']");
//
//		driu.mouseHover(icon);
//
//		co.ele(dri, "//a[text()='Sign Out']").click();
		exu.closeExcel();
		driu.quitAllWindows();

	}

}
