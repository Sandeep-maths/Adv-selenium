package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EvenInformationPage {
	
	//declaration
	@FindBy(xpath = "//span[@class='lvtHeaderText']")
	private WebElement pageheader;
	
	@FindBy(xpath = "//input[@name='Delete']")
	private WebElement deleteBTN;
	
	//Initialization
	public EvenInformationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//utilization
	
	/**
	 * This method returns page header
	 */
	public String getPageheader() {
		return pageheader.getText();
	}
	/**
	 * This method clicks on delete button
	 */
	public void clickDeleteBTN() {
		deleteBTN.click();
	}

}
