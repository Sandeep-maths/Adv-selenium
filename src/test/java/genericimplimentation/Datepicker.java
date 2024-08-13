package genericimplimentation;


import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import genericUtilities.ExcelUtility;
import genericUtilities.Iconstantpath;
import genericUtilities.JavaUtility;
import genericUtilities.PropertiesUtility;
import genericUtilities.WebDriverUtility;



public class Datepicker {
	

	public static void main(String[] args) throws InterruptedException {
		PropertiesUtility propertyUtil = new PropertiesUtility();
		ExcelUtility excel = new ExcelUtility();
		JavaUtility jutil = new JavaUtility();
		WebDriverUtility driverUtil = new WebDriverUtility();

		propertyUtil.propertiesInit(Iconstantpath.PROPERTIES_FILE_PATH);
		excel.excelInit(Iconstantpath.EXCEL_FILE_PATH);

		WebDriver driver = driverUtil.launchBrowser(propertyUtil.readdata("browser"));
		driverUtil.maximizeBrowser();
		driverUtil.navigateToWebApp(propertyUtil.readdata("url"));

		long time = (Long) jutil.ConvertStringToAnyDataType(propertyUtil.readdata("timeouts"), "long");
		driverUtil.waitTillElementFound(time);

		if (driver.getTitle().contains("vtiger CRM"))
			System.out.println("Login Page Displayed");
		else
			driverUtil.quitAllWindows();

		driver.findElement(By.name("user_name")).sendKeys(propertyUtil.readdata("un"));
		driver.findElement(By.name("user_password")).sendKeys(propertyUtil.readdata("pswd"));
		driver.findElement(By.id("submitButton")).submit();

		if (driver.getTitle().contains("Home"))
			System.out.println("Home Page is Displayed");
		else
			driverUtil.quitAllWindows();

		Map<String, String> map = excel.readFromExcel("EventsTestData", "Create New Event");
		WebElement quickCreateDD = driver.findElement(By.id("qccombo"));
		driverUtil.handleDropDown(quickCreateDD, map.get("Quick Create"));

		jutil.waiting(3000);

		String subject = map.get("Subject") + jutil.generateRandomNum(100);
		driver.findElement(By.name("subject")).sendKeys(subject);
		driver.findElement(By.id("jscal_trigger_date_start")).click();

		String[] startDate = jutil.splitString(map.get("Start Date"), "-");
		int reqStartYear = (Integer) jutil.ConvertStringToAnyDataType(startDate[0], "int");
		String reqStartDate = startDate[2];
		int reqStartMonth = jutil.covertMonthInInt(startDate[1]);

		String currentMonthYear = driver
				.findElement(By
						.xpath("//div[@class='calendar' and contains(@style,'block')]/descendant::td[@class='title']"))
				.getText();
		String[] str = jutil.splitString(currentMonthYear, ", ");
		int currentYear = (Integer) jutil.ConvertStringToAnyDataType(str[1], "int");

		while (currentYear < reqStartYear) {
			driver.findElement(
					By.xpath("//div[@class='calendar' and contains(@style,'block')]/descendant::td[text()='»']"))
					.click();

			currentMonthYear = driver
					.findElement(By.xpath(
							"//div[@class='calendar' and contains(@style,'block')]/descendant::td[@class='title']"))
					.getText();
			str = jutil.splitString(currentMonthYear, ", ");
			currentYear = (Integer) jutil.ConvertStringToAnyDataType(str[1], "int");
		}

		int currentMonth = jutil.covertMonthInInt(str[0]);

		while (currentMonth < reqStartMonth) {
			driver.findElement(
					By.xpath("//div[@class='calendar' and contains(@style, 'block')]/descendant::td[text()='›']"))
					.click();
			currentMonthYear = driver
					.findElement(By.xpath(
							"//div[@class='calendar' and contains(@style,'block')]/descendant::td[@class='title']"))
					.getText();
			str = jutil.splitString(currentMonthYear, ", ");
			currentMonth = jutil.covertMonthInInt(str[0]);
		}

		while (currentMonth > reqStartMonth) {
			driver.findElement(
					By.xpath("//div[@class='calendar' and contains(@style, 'block')]/descendant::td[text()='‹']"))
					.click();
			currentMonthYear = driver
					.findElement(By.xpath(
							"//div[@class='calendar' and contains(@style,'block')]/descendant::td[@class='title']"))
					.getText();
			str = jutil.splitString(currentMonthYear, ", ");
			currentMonth = jutil.covertMonthInInt(str[0]);
		}

		driver.findElement(By.xpath(
				"//div[@class='calendar' and contains(@style, 'block')]/descendant::td[text()='" + reqStartDate + "']"))
				.click();

		driver.findElement(By.id("jscal_trigger_due_date")).click();

		String[] dueDate = jutil.splitString(map.get("Due Date"), "-");
		int reqEndYear = (Integer) jutil.ConvertStringToAnyDataType(dueDate[0], "int");
		String reqEndDate = dueDate[2];
		int reqEndMonth = jutil.covertMonthInInt(dueDate[1]);

		currentMonthYear = driver
				.findElement(By
						.xpath("//div[@class='calendar' and contains(@style,'block')]/descendant::td[@class='title']"))
				.getText();
		str = jutil.splitString(currentMonthYear, ", ");
		currentYear = (Integer) jutil.ConvertStringToAnyDataType(str[1], "int");

		while (currentYear < reqEndYear) {
			driver.findElement(
					By.xpath("//div[@class='calendar' and contains(@style,'block')]/descendant::td[text()='»']"))
					.click();

			currentMonthYear = driver
					.findElement(By.xpath(
							"//div[@class='calendar' and contains(@style,'block')]/descendant::td[@class='title']"))
					.getText();
			str = jutil.splitString(currentMonthYear, ", ");
			currentYear = (Integer) jutil.ConvertStringToAnyDataType(str[1], "int");
		}

		currentMonth = jutil.covertMonthInInt(str[0]);

		while (currentMonth < reqEndMonth) {
			driver.findElement(
					By.xpath("//div[@class='calendar' and contains(@style, 'block')]/descendant::td[text()='›']"))
					.click();
			currentMonthYear = driver
					.findElement(By.xpath(
							"//div[@class='calendar' and contains(@style,'block')]/descendant::td[@class='title']"))
					.getText();
			str = jutil.splitString(currentMonthYear, ", ");
			currentMonth = jutil.covertMonthInInt(str[0]);

		}

		while (currentMonth > reqEndMonth) {
			driver.findElement(
					By.xpath("//div[@class='calendar' and contains(@style, 'block')]/descendant::td[text()='‹']"))
					.click();
			currentMonthYear = driver
					.findElement(By.xpath(
							"//div[@class='calendar' and contains(@style,'block')]/descendant::td[@class='title']"))
					.getText();
			str = jutil.splitString(currentMonthYear, ", ");
			currentMonth = jutil.covertMonthInInt(str[0]);
		}

		driver.findElement(By.xpath(
				"//div[@class='calendar' and contains(@style, 'block')]/descendant::td[text()='" + reqEndDate + "']"))
				.click();

        jutil.waiting(3000);
		driver.findElement(By.xpath("//input[@value='  Save']")).submit();

		String newEventPageHeader = driver.findElement(By.xpath("//span[@class='lvtHeaderText']")).getText();
		if (newEventPageHeader.contains(subject)) {
			System.out.println("Event Created");
			excel.writeToExcel("EventsTestData", "Create New Event", "Pass");
		} else {
			System.out.println("Event Not Created");
			excel.writeToExcel("EventsTestData", "Create New Event", "Fail");
		}
		WebElement header = driver.findElement(By.xpath("//span[@class='lvtHeaderText']"));

		if (header.getText().contains(excel.readFromExcel("EventsTestData", 2, 3))) {
			System.out.println("event is created successfully");

		} else {
			System.out.println("event is not  created successfully");
			driverUtil.quitAllWindows();
		}

		excel.saveExcel(Iconstantpath.EXCEL_FILE_PATH);
		WebElement adminWidget = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		driverUtil.mouseHover(adminWidget);

		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();

		excel.closeExcel();

		driverUtil.quitAllWindows();
	}


	}


