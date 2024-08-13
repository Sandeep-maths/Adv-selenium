package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.TabNames;
import genericUtilities.WebDriverUtility;

/**
 * This class contains elements, locators and respective business libraries of
 * home page
 */
public class HomePage {

	// Declaration
	private String commonPathForTabs =  "//a[contains(@href,'%s&action=index')]";

//	@FindBy(xpath = "(//a[text()='Organizations'])[1]")
//	private WebElement organizationTab;
//
//	@FindBy(xpath = "//a[normalize-space()='Contacts']")
//	private WebElement contactsTab;
//
//	@FindBy(xpath = "//a[contains(@href,'Leads&action=index')]")
//	private WebElement leadsTab;

	@FindBy(id = "qccombo")
	private WebElement quickcreateDD;

	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminWidget;

	@FindBy(xpath = "//a[text()='Sign Out']")
	private WebElement signoutLink;

	// Initialization

	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// utilization
//	/**
//	 * This method use to click organization tab
//	 */
//	public void clickorganizationTab() {
//		organizationTab.click();
//	}
//
//	/**
//	 * This method use to click contacts tab
//	 */
//	public void clickcontactsTab() {
//		contactsTab.click();
//	}
//
//	/**
//	 * This method use to click leads tab
//	 */
//	public void clickleadsTab() {
//		leadsTab.click();
//
//	}
//
	/**
	 * This method clicks on specified tab
	 * @param driverutil
	 * @param tabName
	 */
	public void clickRequiredTab(WebDriverUtility driverutil, TabNames tabName) {
		driverutil.convertDynamicXpathToWebElement(commonPathForTabs, tabName.getTabName()).click();
	}
	/**
	 * This method use to select an option from quick create drop down
	 * 
	 * @param driverutil
	 * @param value
	 */
	public void selectFormQuickCreateDD(WebDriverUtility driverutil, String value) {
		driverutil.handleDropDown(quickcreateDD, value);
	}

	/**
	 * This method used to sign out from vtiger
	 * 
	 * @param driverutil
	 */
	public void signOutFromVtiger(WebDriverUtility driverutil) {
		driverutil.mouseHover(adminWidget);
		signoutLink.click();
	}

}
