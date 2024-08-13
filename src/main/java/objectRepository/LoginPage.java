package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
/**
 * This class contains of elements & locators of vtiger login page 
 */
public class LoginPage {
	
	//Declaration 
	
	@FindBy(name="user_name")
	private WebElement unTF;


	@FindBy(name="user_password")
	private WebElement pswdTF;


	@FindBy(id="submitButton")
	private WebElement loginBTN;
	
	//Initialization
	
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	//Utilization
	
	/**
	 * This logs into vtiger application
	 * @param username
	 * @param password
	 */
	
	public void loginToVtiger(String username, String password) {
		unTF.sendKeys(username);
		pswdTF.sendKeys(password);
		loginBTN.click();
	}

}
