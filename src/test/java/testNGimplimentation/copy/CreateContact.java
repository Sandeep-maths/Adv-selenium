package testNGimplimentation.copy;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import genericUtilities.BaseClass;
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

//create org

@Listeners(genericUtilities.ListenersImplementation.class)
public class CreateContact extends BaseClass {

	public WebElement path(WebDriver d, String s) {
		WebElement data = d.findElement(By.xpath(s));

		return data;
	}
	@Test(groups = "contact")
	public  void craeteContact(){
		
		
		//LoginPage lp = new LoginPage(dri);
		HomePage hp = new HomePage(dri);
		 ContactsPage cp = opm.getContactPage();
		 CreatingNewContactPage ccp = opm.getCreateContactPage();
		ContactInformationPage cip = opm.getCreateInfoPage();

		
		
		// perform login

		CreateContact x = new CreateContact();
		
//		x.path(d, "//input[@name='user_name']").sendKeys(pu.readdata("un"));
//		x.path(d, "//input[@name='user_password']").sendKeys(pu.readdata("pswd"));
//		WebElement login = x.path(d, "//input[@id='submitButton']");
//		du.click(login);

		
		// create contact

		hp.clickRequiredTab(driu, TabNames.CONTACTS);
//		WebElement contact = x.path(d, "//a[normalize-space()='Contacts']");
//		du.click(contact);

		if (dri.getCurrentUrl().contains("Contacts")) {
			System.out.println("contact  page success");
		} else {
			System.out.println("contact page failed");
			dri.quit();
		}
		cp.clickCreateContactBTN();
		//x.path(d, "//img[@title='Create Contact...']").click();

		String fn = exu.readFromExcel("contact", 0, 1);
		String ln = exu.readFromExcel("contact", 1, 1);
		String ofn = exu.readFromExcel("contact", 2, 1);
		String mbn = exu.readFromExcel("contact", 3, 1);
		String tit = exu.readFromExcel("contact", 4, 1);
		String dep = exu.readFromExcel("contact", 5, 1);
		String mai = exu.readFromExcel("contact", 6, 1);
		String des = exu.readFromExcel("contact", 7, 1);

		WebElement e = x.path(dri, "//select[@name='salutationtype']");
		driu.handleDropDown(e, 5);


		x.path(dri, "//input[@name='firstname']").sendKeys(fn);
		x.path(dri, "//input[@name='lastname']").sendKeys(ln);
		x.path(dri, "//input[@id='phone']").sendKeys(ofn);
		
		ccp.selectExistingOrganization(driu, "Evy");
	
		jau.waiting(3000);
		
		x.path(dri, "//input[@id='mobile']").sendKeys(mbn);
		
		WebElement e2 = x.path(dri, "//select[@name='leadsource']");
		driu.handleDropDown(e2, 4);
		//Select s2 = new Select(e2);
		//s2.selectByIndex(4);
	
		x.path(dri, "//input[@id='title']").sendKeys(tit);
		x.path(dri, "//input[@id='department']").sendKeys(dep);
		x.path(dri, "//input[@id='email']").sendKeys(mai);
		x.path(dri, "//textarea[@name='description']").sendKeys(des);
		ccp.clicksaveBTN();
		//x.path(d, "(//input[@accesskey='S'])[2]").click();
		
		
		
		if (cip.getPageHeader().contains(exu.readFromExcel("contact", 0, 1)))
		{
			System.out.println("contact created successfully");
			exu.writeToExcel("contact", "Contact created successfully", "pass");
		}else {
			System.out.println("contact created failed");
			exu.writeToExcel("contact", "Contact created successfully", "fail");
		}
		exu.saveExcel("./src/test/resources/testdata.xlsx");
		
		hp.signOutFromVtiger(driu);
		
//		WebElement so = x.path(d, "//img[@src='themes/softed/images/user.PNG']");
//		
//		du.mouseHover(so);
//	
//		
//		x.path(d, "//a[text()='Sign Out']").click();
		
		exu.closeExcel();
		
		driu.quitAllWindows();
		

	}
}
