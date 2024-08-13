package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.WebDriverUtility;
/**
 * This class contains elements , locators & respective business libraries of creating new  contacts
 */
public class CreatingNewContactPage {
	// Declaration
	@FindBy(xpath = "//span[@class='lvtHeaderText']")
	private WebElement pageHeader;

	@FindBy(name = "lastname")
	private WebElement contactLastNameTF;

	@FindBy(xpath = "//input[contains(@title,'Save')]")
	private WebElement saveBTN;
	
	@FindBy(xpath = "//img[contains(@onclick,'Accounts')]")
	private WebElement organizationPlusBTN;
	
	private String organizationPath = "//a[text()='%s']";
	
	
	// Initialization
		public CreatingNewContactPage(WebDriver driver) {
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
		 * This method use to pass name for contact
		 * @param name
		 */
		public void setCVontactLastNameTF(String name) {
			contactLastNameTF.sendKeys(name);
		}
		
		/**
		 * This method use to save the contact
		 */
		public void clicksaveBTN() {
			saveBTN.click();
		}
		/**
		 * This method selects the existing organization in the organization list
		 * @param driverUtil
		 * @param orgname
		 */
		
		public void selectExistingOrganization(WebDriverUtility driverUtil, String orgname) {
			organizationPlusBTN.click();
			driverUtil.switchToWindowIdUrl("Accounts");
			driverUtil.convertDynamicXpathToWebElement(organizationPath, orgname).click();
			driverUtil.switchToWindowIdUrl("contacts");
		}

}
