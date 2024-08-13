package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * This class contains elements , locators & respective business libraries of
 * organization information
 */
public class OrganizationInformationPage {

	// Declaration
	@FindBy(css = "span.dvHeaderText")
	private WebElement pageHeader;

	@FindBy(xpath = "//input[@name='Delete']")
	private WebElement deleteBTN;

	// Initialization
	public OrganizationInformationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Utilization
	/**
	 * This method use to fetches the page header
	 * 
	 * @return
	 */
	public String getPageHeader() {
		return pageHeader.getText();
	}

	/**
	 * This method use to delete the organization
	 */
	public void clickDeleteBTN() {
		deleteBTN.click();
	}

}
