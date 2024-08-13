package testNGimplimentation.copy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import genericUtilities.BaseClass;
import genericUtilities.TabNames;
import objectRepository.CreatingNewOrganizationPage;
import objectRepository.OrganizationInformationPage;
import objectRepository.OrganizationPage;
@Listeners(genericUtilities.ListenersImplementation.class)
public class CreateOrganization extends BaseClass {

	WebDriver d;

	public WebElement ele(WebDriver d, String e) {
		this.d = d;
		WebElement ele = d.findElement(By.xpath(e));
		return ele;
	}

	@Test(groups = "organization")
	public void createOrg() {
		
		OrganizationPage org = opm.getOrgpage();
		CreatingNewOrganizationPage corg = opm.getCreateOrgpage();
		OrganizationInformationPage iorg = opm.getOrginfoPage();

		ho.clickRequiredTab(driu, TabNames.ORGANIZATIONS);

		Assert.assertTrue(dri.getTitle().contains(exu.readFromExcel("org", 0, 1))); 
		
		org.clickcreateOrgBTN();

		Assert.assertTrue(corg.getPageHeader().contains("Creating New Organization"));

		CreateOrganization co = new CreateOrganization();

		ele(dri, "//input[@name='accountname']").sendKeys(exu.readFromExcel("org", 4, 1));

		ele(dri, "//input[@style='width:74%;']").sendKeys(exu.readFromExcel("org", 5, 1));

		ele(dri, "//input[@id='phone']").sendKeys(exu.readFromExcel("org", 6, 1));

		ele(dri, "//input[@name='tickersymbol']").sendKeys(exu.readFromExcel("org", 7, 1));

		ele(dri, "//input[@id='fax']").sendKeys(exu.readFromExcel("org", 8, 1));

		ele(dri, "//input[@id='otherphone']").sendKeys(exu.readFromExcel("org", 9, 1));

		ele(dri, "//input[@id='employees']").sendKeys(exu.readFromExcel("org", 10, 1));

		ele(dri, "//input[@id='email1']").sendKeys(exu.readFromExcel("org", 11, 1));

		ele(dri, "//input[@id='email2']").sendKeys(exu.readFromExcel("org", 12, 1));

		ele(dri, "//input[@id='ownership']").sendKeys(exu.readFromExcel("org", 13, 1));

		WebElement industry = ele(dri, "//select[@name='industry']");

		driu.handleDropDown(industry, 14);

		WebElement rating = ele(dri, "//select[@name='rating']");

		driu.handleDropDown(rating, 2);

		WebElement type = ele(dri, "//select[@name='accounttype']");

		driu.handleDropDown(type, 5);

		ele(dri, "//input[@id='siccode']").sendKeys(exu.readFromExcel("org", 14, 1));

		ele(dri, "//input[@name='emailoptout']").click();

		WebElement an = ele(dri, "//input[@name='annual_revenue']");

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

		Assert.assertTrue(header.getText().contains("sysco")) ;

		iorg.clickDeleteBTN();

		driu.handleAlert("ok");

		jau.waiting(3000);

		Assert.assertTrue(dri.getPageSource().contains("sysco"));
		if(dri.getPageSource().contains(exu.readFromExcel("org", 4, 1))){
			exu.writeFromExcel("org", 28, 1, "fail");

		} else {
			exu.writeFromExcel("org", 28, 1, "pass");

		}
		//soft.assertAll();
	}

}
