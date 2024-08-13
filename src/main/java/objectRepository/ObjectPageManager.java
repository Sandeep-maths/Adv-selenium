package objectRepository;

import org.openqa.selenium.WebDriver;

public class ObjectPageManager {
	
//	LoginPage lo;
//	HomePage hp;
//	OrganizationPage op;
//	CreatingNewOrganizationPage cop;
//	OrganizationInformationPage oip;
//	CreateToDoPage todo;
//	ContactsPage cp;
//	CreatingNewContactPage ccp;
//	ContactInformationPage cip;
//	EvenInformationPage eip;
	
	WebDriver driver;
	
	public ObjectPageManager(WebDriver driver) {
		this.driver=driver;
	}

	public LoginPage getLogin() {
		return new LoginPage(driver);
	}

	public HomePage getHomepage() {
		return new HomePage(driver);
	}

	public OrganizationPage getOrgpage() {
		return new OrganizationPage(driver);
	}

	public CreatingNewOrganizationPage getCreateOrgpage() {
		return  new CreatingNewOrganizationPage(driver);
	}

	public OrganizationInformationPage getOrginfoPage() {
		return new OrganizationInformationPage(driver);
	}

	public CreateToDoPage getCreateTodo() {
		return new CreateToDoPage(driver);
	}

	public ContactsPage getContactPage() {
		return  new ContactsPage(driver);
	}

	public CreatingNewContactPage getCreateContactPage() {
		return  new CreatingNewContactPage(driver);
	}

	public ContactInformationPage getCreateInfoPage() {
		return  new ContactInformationPage(driver) ;
	}

	public EvenInformationPage getEip() {
		return new EvenInformationPage(driver);
	}
	

}
