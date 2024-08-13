package objectRepository;

/**
 * This class contains elements , locators & respective business libraries of create to do
 */

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.JavaUtility;
import genericUtilities.WebDriverUtility;

public class CreateToDoPage {
	// declaration
	@FindBy(name = "subject")
	private WebElement subjectTF;

	@FindBy(id = "jscal_trigger_date_start")
	private WebElement startDateWidget;

	@FindBy(xpath = "//div[@class='calendar' and contains(@style,'block')]/descendant::td[@class='title']")
	private WebElement calendarTitle;

	private String calendarCommonPath = "//div[@class='calendar' and contains(@style,'block')]/descendant::td[text()='%s']";

	@FindBy(id = "jscal_trigger_due_date")
	private WebElement dueDateWidget;

	@FindBy(xpath = "//input[@value='  Save']")
	private WebElement saveBTN;

	// initialization

	public CreateToDoPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// utilization
	/**
	 * This method is used to set the subject of the event
	 * 
	 * @param subject
	 */
	public void setsubjectTF(String subject) {
		subjectTF.sendKeys(subject);
	}

	/**
	 * This method use to click start date calendar widget
	 */
	public void clickStartDateWidget() {
		startDateWidget.click();
	}

	/**
	 * This method use to click due date calendar widget
	 */
	public void clickdueDateWidget() {
		dueDateWidget.click();
	}

	/**
	 * This method used to pick the date
	 * 
	 * @param ju
	 * @param du
	 * @param reqDate
	 */
	public void datePicker(JavaUtility ju, WebDriverUtility du, String reqDate) {
		String[] startDate = ju.splitString(reqDate, "-");
		int reqStartYear = (Integer) ju.ConvertStringToAnyDataType(startDate[0], "int");
		String reqStartDate = startDate[2];
		int reqStartMonth = ju.covertMonthInInt(startDate[1]);

		String currentMonthYear = calendarTitle.getText();

		String[] str = ju.splitString(currentMonthYear, ", ");
		int currentYear = (Integer) ju.ConvertStringToAnyDataType(str[1], "int");

		while (currentYear < reqStartYear) {
			du.convertDynamicXpathToWebElement(calendarCommonPath, "»").click();

			currentMonthYear = calendarTitle.getText();

			str = ju.splitString(currentMonthYear, ", ");
			currentYear = (Integer) ju.ConvertStringToAnyDataType(str[1], "int");
		}

		int currentMonth = ju.covertMonthInInt(str[0]);

		while (currentMonth < reqStartMonth) {
			du.convertDynamicXpathToWebElement(calendarCommonPath, "›").click();
			currentMonthYear = calendarTitle.getText();

			str = ju.splitString(currentMonthYear, ", ");
			currentMonth = ju.covertMonthInInt(str[0]);
		}

		while (currentMonth > reqStartMonth) {
			du.convertDynamicXpathToWebElement(calendarCommonPath, "‹").click();
			currentMonthYear = calendarTitle.getText();

			str = ju.splitString(currentMonthYear, ", ");
			currentMonth = ju.covertMonthInInt(str[0]);
		}

		du.convertDynamicXpathToWebElement(calendarCommonPath, reqStartDate).click();
	}

	/**
	 * This method used to save the create to do
	 */
	public void clicksaveBTN() {
		saveBTN.submit();
	}

}
