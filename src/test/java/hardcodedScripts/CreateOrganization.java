package hardcodedScripts;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class CreateOrganization {

	WebDriver d;

	public WebElement ele(WebDriver d, String e) {
		this.d = d;
		WebElement ele = d.findElement(By.xpath(e));
		return ele;
	}

	public static void main(String[] args) throws InterruptedException {
		WebDriver dri = new ChromeDriver();
		dri.manage().window().maximize();
		dri.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		dri.get("http://localhost:8888/");

		if (dri.getTitle().contains("vtiger CRM")) {
			System.out.println("navigated to correct login resource");
		} else {
			System.out.println("not navigated to vtiger login page");
			dri.quit();
		}

		dri.findElement(By.xpath("//input[@name='user_name']")).sendKeys("admin");
		dri.findElement(By.xpath("//input[@name='user_password']")).sendKeys("admin");
		dri.findElement(By.xpath("//input[@id='submitButton']")).click();

		if (dri.getTitle().contains("Home")) {
			System.out.println("navigated to correct home resource");
		} else {
			System.out.println("not navigated to vtiger home page");
			dri.quit();
		}

		dri.findElement(By.xpath("//a[text()='Organizations' and contains(@href,'Accounts&action=index')]")).click();

		if (dri.getTitle().contains("Organizations")) {
			System.out.println("navigated to correct organizations resource");
		} else {
			System.out.println("not navigated to vtiger Organization page");
			dri.quit();
		}

		dri.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();

		/*
		 * WebElement page = dri.findElement(By.
		 * xpath("//span[contains(text(),'Creating New Organization')]")); if
		 * (page.isDisplayed()) {
		 * System.out.println("navigated to correct organizations create resource"); }
		 * else {
		 * System.out.println("not navigated to vtiger Organization create page");
		 * dri.quit(); }
		 */

		WebElement page = dri.findElement(By.xpath("//span[contains(@class,'lvtHeaderText')]"));
		if (page.getText().contains("Creating New Organization")) {
			System.out.println("navigated to correct organizations create resource");
		} else {
			System.out.println("not navigated to vtiger Organization create page");
			dri.quit();
		}

		CreateOrganization co = new CreateOrganization();

		co.ele(dri, "//input[@name='accountname']").sendKeys("sysco");

		co.ele(dri, "//input[@style='width:74%;']").sendKeys("syscocompany.com");

		co.ele(dri, "//input[@id='phone']").sendKeys("8947645545");

		co.ele(dri, "//input[@name='tickersymbol']").sendKeys("@78");

		co.ele(dri, "//input[@id='fax']").sendKeys("0x023");

		co.ele(dri, "//input[@id='otherphone']").sendKeys("0401234789");

		co.ele(dri, "//input[@id='employees']").sendKeys("30");

		co.ele(dri, "//input[@id='email1']").sendKeys("sysco@gmail.com");

		co.ele(dri, "//input[@id='email2']").sendKeys("sysco123@gmail.com");

		co.ele(dri, "//input[@id='ownership']").sendKeys("sdvarma");

		WebElement industry = co.ele(dri, "//select[@name='industry']");

		Select s = new Select(industry);

		s.selectByValue("Finance");

		WebElement rating = co.ele(dri, "//select[@name='rating']");

		Select s2 = new Select(rating);

		s2.selectByIndex(2);

		WebElement type = co.ele(dri, "//select[@name='accounttype']");

		Select s3 = new Select(type);

		s3.selectByIndex(5);

		co.ele(dri, "//input[@id='siccode']").sendKeys("5001");

		co.ele(dri, "//input[@name='emailoptout']").click();

		WebElement an = co.ele(dri, "//input[@name='annual_revenue']");

		an.clear();
		an.sendKeys("500000000");

		co.ele(dri, "//input[@name='notify_owner']").click();

		co.ele(dri, "//textarea[@name='bill_street']").sendKeys("hyderabad");

		co.ele(dri, "//input[@name='bill_pobox']").sendKeys("punjaguta");

		co.ele(dri, "//input[@name='bill_city']").sendKeys("hyderabad");

		co.ele(dri, "//input[@name='bill_state']").sendKeys("Telangana");

		co.ele(dri, "//input[@name='bill_code']").sendKeys("309");

		co.ele(dri, "//input[@name='bill_country']").sendKeys("India");

		co.ele(dri, "//input[@onclick='return copyAddressRight(EditView)']").click();

		co.ele(dri, "//textarea[@name='description']").sendKeys("sysco organization created successfully");

		co.ele(dri, "(//input[@class='crmbutton small save'])[2]").click();

		Thread.sleep(5000);

		WebElement header = co.ele(dri, "//span[@class='dvHeaderText']");

		if (header.getText().contains("sysco")) {
			System.out.println("sysco -  Organization is created successfully");

		} else {
			System.out.println("sysco -  Organization is not  created successfully");
			dri.quit();
		}

		co.ele(dri, "(//input[@class='crmbutton small delete'])[1]").click();

		dri.switchTo().alert().accept();

		Thread.sleep(5000);

		if (dri.getPageSource().contains("sysco")) {
			System.out.println("sysco organization is present.");
		} else {
			System.out.println("sysco organization is deleted.");

		}

		WebElement icon = co.ele(dri, "//img[@src='themes/softed/images/user.PNG']");

		Actions act = new Actions(dri);

		act.moveToElement(icon).perform();

		co.ele(dri, "//a[text()='Sign Out']").click();

		dri.quit();

	}

}
