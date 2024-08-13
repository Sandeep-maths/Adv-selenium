package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
/**
 * This class contains elements , locators & respective business libraries of contacts page
 */
public class ContactsPage {
	// Declaration
	

		@FindBy(xpath = "//img[@alt='Create Contact...']")
		private WebElement createContactBTN;
		
		// Initialization
		public ContactsPage(WebDriver driver) {
			PageFactory.initElements(driver, this);
		}
		//Utilization
		/**
		 * This method clicks on the create contacts button
		 */
		public void clickCreateContactBTN() {
			createContactBTN.click();
		}
}
