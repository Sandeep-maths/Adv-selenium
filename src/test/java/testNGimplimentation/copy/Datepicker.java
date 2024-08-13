package testNGimplimentation.copy;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import genericUtilities.BaseClass;
import genericUtilities.ExcelUtility;
import genericUtilities.Iconstantpath;
import genericUtilities.JavaUtility;
import genericUtilities.PropertiesUtility;
import genericUtilities.WebDriverUtility;
import objectRepository.CreateToDoPage;
import objectRepository.CreatingNewOrganizationPage;
import objectRepository.EvenInformationPage;
import objectRepository.HomePage;
import objectRepository.LoginPage;
import objectRepository.OrganizationInformationPage;
import objectRepository.OrganizationPage;

public class Datepicker extends BaseClass {

	@Test
	public void event() {

		LoginPage li = new LoginPage(dri);
		HomePage ho = new HomePage(dri);
		CreateToDoPage ctd = opm.getCreateTodo();
		EvenInformationPage eve = opm.getEip();

		soft.assertTrue(dri.getTitle().contains("vtiger CRM"));

		li.loginToVtiger(prou.readdata("un"), prou.readdata("pswd"));

		soft.assertTrue(dri.getTitle().contains("Home"));

		Map<String, String> map = exu.readFromExcel("EventsTestData", "Create New Event");
		ho.selectFormQuickCreateDD(driu, map.get("Quick Create"));

		jau.waiting(3000);

		String subject = map.get("Subject") + jau.generateRandomNum(100);
		ctd.setsubjectTF(subject);
		ctd.clickStartDateWidget();

		ctd.datePicker(jau, driu, map.get("Start Date"));
		jau.waiting(2000);

		ctd.clickdueDateWidget();

		ctd.datePicker(jau, driu, map.get("Due Date"));

		jau.waiting(3000);

		ctd.clicksaveBTN();
		soft.assertTrue(eve.getPageheader().contains(subject));
		if (eve.getPageheader().contains(subject)) {

			exu.writeToExcel("EventsTestData", "Create New Event", "Pass");
		} else {

			exu.writeToExcel("EventsTestData", "Create New Event", "Fail");
		}

		soft.assertTrue(eve.getPageheader().contains(exu.readFromExcel("EventsTestData", 2, 3)));
		eve.clickDeleteBTN();
		driu.handleAlert("ok");
		exu.saveExcel(Iconstantpath.EXCEL_FILE_PATH);

		
	}

}
