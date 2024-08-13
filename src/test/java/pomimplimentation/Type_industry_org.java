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
import objectRepository.ContactInformationPage;
import objectRepository.ContactsPage;
import objectRepository.CreatingNewContactPage;
import objectRepository.CreatingNewOrganizationPage;
import objectRepository.HomePage;
import objectRepository.LoginPage;
import objectRepository.OrganizationInformationPage;
import objectRepository.OrganizationPage;

public class Type_industry_org {
	public WebElement path(WebDriver d, String s) {
		WebElement ele = d.findElement(By.xpath(s));
		return ele;
	}

	public static void main(String[] args) throws InterruptedException {
	PropertiesUtility pu = new PropertiesUtility();
	ExcelUtility eu = new ExcelUtility();
	JavaUtility ju = new JavaUtility();
	WebDriverUtility du = new WebDriverUtility();
	
	pu.propertiesInit(Iconstantpath.PROPERTIES_FILE_PATH);
	eu.excelInit(Iconstantpath.EXCEL_FILE_PATH);
		
	WebDriver d = du.launchBrowser(pu.readdata("browser"));
	du.maximizeBrowser();
	long t = (Long)ju.ConvertStringToAnyDataType(pu.readdata("timeouts"),"long" );
	du.waitTillElementFound(t);
	
	
	LoginPage lp = new LoginPage(d);
	HomePage hp = new HomePage(d);
	OrganizationPage org = new OrganizationPage(d);
	CreatingNewOrganizationPage corg = new CreatingNewOrganizationPage(d);
	OrganizationInformationPage iorg = new OrganizationInformationPage(d);

		// navigate to vtiger login page

		du.navigateToWebApp(pu.readdata("url"));

		// check weather navigate to vtiger login page
		if (d.getTitle().contains("vtiger")) {
			System.out.println("vtiger login  page success");
		} else {
			System.out.println("vtiger login  page failed");
			du.quitAllWindows();
		}

		// add details to login

		Type_industry_org x = new Type_industry_org();
		lp.loginToVtiger(pu.readdata("un"), pu.readdata("pswd"));
//		x.path(d, "//input[@name='user_name']").sendKeys(pu.readdata("un"));
//		x.path(d, "//input[@name='user_password']").sendKeys(pu.readdata("pswd"));
//		x.path(d, "//input[@id='submitButton']").click();

		// check weather navigate to vtiger home page

		if (d.getTitle().contains("Home")) {
			System.out.println("vtiger home page success");
		} else {
			System.out.println("vtiger home page failed");
			du.quitAllWindows();
		}

		// click on organization module
		hp.clickRequiredTab(du, TabNames.ORGANIZATIONS);
		//x.path(d, "(//a[text()='Organizations'])[1]").click();

		// check weather navigated organization page

		if (d.getTitle().contains("Organization")) {
			System.out.println("Organization page success");
		} else {
			System.out.println("Organization page failed");
			du.quitAllWindows();
		}

		// click on new organization
		org.clickcreateOrgBTN();
		//x.path(d, "//img[@src='themes/softed/images/btnL3Add.gif']").click();

		// check weather navigated new organization page

		WebElement org1 = x.path(d, "//span[text()='Creating New Organization']");

		if (org1.isDisplayed()) {
			System.out.println("New organization page success");
		} else {
			System.out.println("New organization page failed");
			du.quitAllWindows();
		}

		// add data in required fields
		Map<String, String> map = eu.readFromExcel("OrganizationsTestData", "Create Organization");
		x.path(d, "//input[@name='accountname']").sendKeys(map.get("Organization Name"));

		WebElement in = x.path(d, "//select[@name='industry']");
		du.handleDropDown(in, eu.readFromExcel("OrganizationsTestData", 5, 3));

		WebElement ty = x.path(d, "//select[@name='accounttype']");
		du.handleDropDown(ty, eu.readFromExcel("OrganizationsTestData", 6, 3));
		Select s2 = new Select(ty);
		s2.selectByVisibleText("Investor");

		x.path(d, "//textarea[@name='description']").sendKeys("Industry_Type organization is created successfully");

		WebElement sav = x.path(d, "(//input[@accesskey='S'])[2]");
		
		du.scrollToElement(sav);
		du.clickOperation(sav);
		
		long sl = (Long) ju.ConvertStringToAnyDataType(pu.readdata("sleep"), "long");
		ju.waiting(sl);



		// check weather ride org is creted or not

		WebElement r = x.path(d, "//span[@class='dvHeaderText']");
		if (r.getText().contains(eu.readFromExcel("OrganizationsTestData", 2, 3))) {
			System.out.println(eu.readFromExcel("OrganizationsTestData", 2, 3)+" org created success. ");
			eu.writeToExcel("OrganizationsTestData", "Create Organization", "pass");
			
		} else {
			System.out.println(eu.readFromExcel("OrganizationsTestData", 2, 3)+"org created failed. ");
			eu.writeToExcel("OrganizationsTestData", "Create Organization", "failed");
			du.quitAllWindows();
		}
		eu.saveExcel(Iconstantpath.EXCEL_FILE_PATH);
		
		// delete  org

		WebElement del = x.path(d, "(//input[@accesskey='D'])[1]");
		du.clickOperation(del);
		du.handleAlert(pu.readdata("alert"));
		ju.waiting(sl);
		

		// check weather  org is deleted or not

		if (d.getPageSource().contains(eu.readFromExcel("OrganizationsTestData", 1, 3))) {
			
			System.out.println(eu.readFromExcel("OrganizationsTestData", 1, 3)+" is not deleted");
		} else {
			System.out.println(eu.readFromExcel("OrganizationsTestData", 1, 3)+" is deleted");
		}

		// perform logout

		WebElement lo = x.path(d, "//img[@src='themes/softed/images/user.PNG']");
		du.mouseHover(lo);
		WebElement out = x.path(d, "//a[text()='Sign Out']");
		du.click(out);
		eu.closeExcel();
		du.quitAllWindows();
	}

}
