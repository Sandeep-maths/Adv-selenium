package hardcodedScripts;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Type_industry_org {
	public WebElement path(WebDriver d, String s) {
		WebElement ele = d.findElement(By.xpath(s));
		return ele;
	}

	public static void main(String[] args) throws InterruptedException {
		WebDriver d = new ChromeDriver();
		d.manage().window().maximize();
		d.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// navigate to vtiger login page

		d.get("http://localhost:8888/");

		// check weather navigate to vtiger login page
		if (d.getTitle().contains("vtiger")) {
			System.out.println("vtiger login  page success");
		} else {
			System.out.println("vtiger login  page failed");
			d.quit();
		}

		// add details to login

		Type_industry_org x = new Type_industry_org();

		x.path(d, "//input[@name='user_name']").sendKeys("admin");
		x.path(d, "//input[@name='user_password']").sendKeys("admin");
		x.path(d, "//input[@id='submitButton']").click();

		// check weather navigate to vtiger home page

		if (d.getTitle().contains("Home")) {
			System.out.println("vtiger home page success");
		} else {
			System.out.println("vtiger home page failed");
			d.quit();
		}

		// click on organization module

		x.path(d, "(//a[text()='Organizations'])[1]").click();

		// check weather navigated organization page

		if (d.getTitle().contains("Organization")) {
			System.out.println("Organization page success");
		} else {
			System.out.println("Organization page failed");
			d.quit();
		}

		// click on new organization

		x.path(d, "//img[@src='themes/softed/images/btnL3Add.gif']").click();

		// check weather navigated new organization page

		WebElement org = x.path(d, "//span[text()='Creating New Organization']");

		if (org.isDisplayed()) {
			System.out.println("New organization page success");
		} else {
			System.out.println("New organization page failed");
			d.quit();
		}

		// add data in required fields

		x.path(d, "//input[@name='accountname']").sendKeys("Ride");

		WebElement in = x.path(d, "//select[@name='industry']");
		Select s = new Select(in);
		s.selectByValue("Education");

		WebElement ty = x.path(d, "//select[@name='accounttype']");
		Select s2 = new Select(ty);
		s2.selectByVisibleText("Investor");

		x.path(d, "//textarea[@name='description']").sendKeys("Industry_Type organization is created successfully");

		WebElement sav = x.path(d, "(//input[@accesskey='S'])[2]");
		Actions act = new Actions(d);
		act.moveToElement(sav).perform();
		act.click(sav).perform();

		Thread.sleep(5000);

		// check weather ride org is creted or not

		WebElement r = x.path(d, "//span[@class='dvHeaderText']");
		if (r.getText().contains("Ride")) {
			System.out.println("Ride org created success. ");
		} else {
			System.out.println("Ride org created failed. ");
			d.quit();
		}

		// delete ride org

		x.path(d, "(//input[@accesskey='D'])[1]").click();
		d.switchTo().alert().accept();
		Thread.sleep(5000);

		// check weather ride org is deleted or not

		if (d.getPageSource().contains("Ride")) {
			System.out.println("ride is not deleted");
		} else {
			System.out.println("ride is deleted");
		}

		// perform logout

		WebElement lo = x.path(d, "//img[@src='themes/softed/images/user.PNG']");
		act.moveToElement(lo).perform();
		x.path(d, "//a[text()='Sign Out']").click();
		d.quit();
	}

}
