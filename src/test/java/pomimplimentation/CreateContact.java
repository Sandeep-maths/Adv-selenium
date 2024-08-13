package pomimplimentation;

import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import javax.xml.crypto.KeySelector.Purpose;

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
import objectRepository.HomePage;
import objectRepository.LoginPage;
import utilities.Utilites;

public class CreateContact {

	public WebElement path(WebDriver d, String s) {
		WebElement data = d.findElement(By.xpath(s));

		return data;
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		
		PropertiesUtility pu = new PropertiesUtility();
		ExcelUtility eu = new ExcelUtility();
		JavaUtility ju = new JavaUtility();
		WebDriverUtility du = new WebDriverUtility();
		
		pu.propertiesInit(Iconstantpath.PROPERTIES_FILE_PATH);
		eu.excelInit("./src/test/resources/testdata.xlsx");
			
		WebDriver d = du.launchBrowser(pu.readdata("browser"));
		du.maximizeBrowser();
		long t = (Long)ju.ConvertStringToAnyDataType(pu.readdata("timeouts"),"long" );
		du.waitTillElementFound(t);
		
		
		LoginPage lp = new LoginPage(d);
		HomePage hp = new HomePage(d);
		 ContactsPage cp = new ContactsPage(d);
		 CreatingNewContactPage ccp = new CreatingNewContactPage(d);
		ContactInformationPage cip = new ContactInformationPage(d);

		
		du.navigateToWebApp(pu.readdata("url"));

		// perform login

		CreateContact x = new CreateContact();
		lp.loginToVtiger(pu.readdata("un"), pu.readdata("pswd"));
//		x.path(d, "//input[@name='user_name']").sendKeys(pu.readdata("un"));
//		x.path(d, "//input[@name='user_password']").sendKeys(pu.readdata("pswd"));
//		WebElement login = x.path(d, "//input[@id='submitButton']");
//		du.click(login);

		if (d.getTitle().contains("Home")) {
			System.out.println("vtiger home page success");
		} else {
			System.out.println("vtiger home page failed");
			du.quitAllWindows();
		}

		// create contact

		hp.clickRequiredTab(du, TabNames.CONTACTS);
//		WebElement contact = x.path(d, "//a[normalize-space()='Contacts']");
//		du.click(contact);

		if (d.getCurrentUrl().contains("Contacts")) {
			System.out.println("contact  page success");
		} else {
			System.out.println("contact page failed");
			d.quit();
		}
		cp.clickCreateContactBTN();
		//x.path(d, "//img[@title='Create Contact...']").click();

		String fn = eu.readFromExcel("contact", 0, 1);
		String ln = eu.readFromExcel("contact", 1, 1);
		String ofn = eu.readFromExcel("contact", 2, 1);
		String mbn = eu.readFromExcel("contact", 3, 1);
		String tit = eu.readFromExcel("contact", 4, 1);
		String dep = eu.readFromExcel("contact", 5, 1);
		String mai = eu.readFromExcel("contact", 6, 1);
		String des = eu.readFromExcel("contact", 7, 1);

		WebElement e = x.path(d, "//select[@name='salutationtype']");
		du.handleDropDown(e, 5);


		x.path(d, "//input[@name='firstname']").sendKeys(fn);
		x.path(d, "//input[@name='lastname']").sendKeys(ln);
		x.path(d, "//input[@id='phone']").sendKeys(ofn);
		
		ccp.selectExistingOrganization(du, "Evy");
		
//		WebElement addbut = x.path(d, "(//img[@title='Select'])[1]");
//		du.click(addbut);
//		du.switchToWindowIdUrl("Accounts");
//		x.path(d, "//a[@href='javascript:window.close();']").click();
//		du.switchToWindowIdUrl("Contacts");
		//String sw = d.getWindowHandle();
	//	Set<String> mw = d.getWindowHandles();
		/*for(String w : mw)
		{
			if(w.equals(sw)) {}
			else {
				d.switchTo().window(w);
				x.path(d, "//a[@href='javascript:window.close();']").click();
				
			}
		}
		d.switchTo().window(sw);*/
		Thread.sleep(3000);
		
		x.path(d, "//input[@id='mobile']").sendKeys(mbn);
		
		WebElement e2 = x.path(d, "//select[@name='leadsource']");
		du.handleDropDown(e2, 4);
		//Select s2 = new Select(e2);
		//s2.selectByIndex(4);
	
		x.path(d, "//input[@id='title']").sendKeys(tit);
		x.path(d, "//input[@id='department']").sendKeys(dep);
		x.path(d, "//input[@id='email']").sendKeys(mai);
		x.path(d, "//textarea[@name='description']").sendKeys(des);
		ccp.clicksaveBTN();
		//x.path(d, "(//input[@accesskey='S'])[2]").click();
		
		WebElement c = x.path(d, "//span[@class='dvHeaderText']");
		
		if (cip.getPageHeader().contains(eu.readFromExcel("contact", 0, 1)))
		{
			System.out.println("contact created successfully");
			eu.writeToExcel("contact", "Contact created successfully", "pass");
		}else {
			System.out.println("contact created failed");
			eu.writeToExcel("contact", "Contact created successfully", "fail");
		}
		eu.saveExcel("./src/test/resources/testdata.xlsx");
		
		hp.signOutFromVtiger(du);
		
//		WebElement so = x.path(d, "//img[@src='themes/softed/images/user.PNG']");
//		
//		du.mouseHover(so);
//	
//		
//		x.path(d, "//a[text()='Sign Out']").click();
		
		eu.closeExcel();
		
		du.quitAllWindows();
		

	}
}
