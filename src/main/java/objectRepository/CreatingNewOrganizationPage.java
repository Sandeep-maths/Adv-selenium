package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.WebDriverUtility;
/**
 * This class contains elements , locators & respective business libraries of creating new  organization
 */
public class CreatingNewOrganizationPage {
	// Declaration
	@FindBy(xpath = "//span[@class='lvtHeaderText']")
	private WebElement pageHeader;

	@FindBy(name = "accountname")
	private WebElement organizationNameTF;

	@FindBy(xpath = "//input[contains(@title,'save')]")
	private WebElement saveBTN;

	@FindBy(name = "industry")
	private WebElement industryDD;

	@FindBy(name = "accounttype")
	private WebElement typeDD;

	// Initialization
	public CreatingNewOrganizationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//Utilization
	
	
	/**
	 * This method use to fetches the page header
	 * @return
	 */
	public String getPageHeader() {
		return pageHeader.getText();
	}
	
	/**
	 * This method use to pass name for organization
	 * @param name
	 */
	public void setOrganizationName(String name) {
		organizationNameTF.sendKeys(name);
	}
	
	/**
	 * This method use to save the organization
	 */
	public void clicksaveBTN() {
		saveBTN.click();
	}
	
	/**
	 * This method use to select an industry from  industry drop down
	 * @param driverutil
	 * @param value
	 */
	public void selectindustryDD(WebDriverUtility driverutil, String value) {
		driverutil.handleDropDown(industryDD, value);
	}
	/**
	 *  This method use to select an type from  type drop down
	 */
	public void selecttypeDD(WebDriverUtility driverutil, String value) {
		driverutil.handleDropDown(typeDD, value);
	}

}
