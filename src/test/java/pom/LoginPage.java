package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
		// step 1 Declaration
	
		@FindBy(id="email")
		private WebElement emailTF;
		
		@FindBy(id="password")
		private WebElement pwdTF;
		
		@FindBy(id="keepLoggedInCheckBox")
		private WebElement keepMeLoggedInCB;
		
		@FindBy(id="toPasswordRecoveryPageLink")
		private WebElement forgotPWDLink;
		
		@FindBy(name="login")
		private WebElement loginBTN;
	
		
		// step 2  Initialization
		
		public LoginPage(WebDriver driver) {
			PageFactory.initElements(driver, this);
		}
		
		
		// step 3 Utilization
		
		
		public void setemailTF(String email) {
			emailTF.sendKeys(email);
		}
	
		
		public void setpwdTF(String pwd) {
			pwdTF.sendKeys(pwd);
		}
		
		public void clickkeepMeLoggedInCB() {
			keepMeLoggedInCB.click();
		}
		
		public void clickforgotPWDLink() {
			forgotPWDLink.click();
		}
		
		public void clickloginBTN() {
			loginBTN.click();
		}
	
}
