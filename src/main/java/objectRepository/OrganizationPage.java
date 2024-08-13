package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
/**
 * This class contains elements , locators & respective business libraries of organization page
 */
public class OrganizationPage {
	
	// Declaration
	@FindBy(xpath = "//img[@alt='Create Organization...']")
	private WebElement createOrgBTN;
	
	// Initialization
	public OrganizationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	//Utilization
	/**
	 * This method clicks on the create organization button
	 */
	public void clickcreateOrgBTN() {
		createOrgBTN.click();
	}
}
