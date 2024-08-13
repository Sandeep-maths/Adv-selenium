package interviewScenarios;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;



public class Filpkart_scenario {

	public static void main(String[] args) throws InterruptedException {

		WebDriver d = new ChromeDriver();
		d.manage().window().maximize();
		d.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		d.get("https://www.flipkart.com/");
		d.findElement(By.xpath("//input[@placeholder='Search for Products, Brands and More']")).sendKeys("macbook");
		d.findElement(By.xpath("//*[name()='svg' and @viewBox='0 0 24 24']")).click();

		String tr = d.findElement(By.xpath("//span[@class='BUOuZu']")).getText();

		String[] trs = tr.split(" ");
		System.out.println(trs[5]);
		int trss = Integer.parseInt(trs[5]);

		WebElement next = d.findElement(By.xpath("//span[text()='Next']"));
		Actions act = new Actions(d);
		act.scrollToElement(next).perform();
		int sum = 0;

	
		String page = d.findElement(By.xpath("//span[contains(text(),'Page')]")).getText();
		String[] p = page.split(" ");
		System.out.println(p[1] +"  "+ p[3]);
		int sp = Integer.parseInt(p[1]);
		int ep = Integer.parseInt(p[3]);

		List<WebElement> lts = d.findElements(By.xpath("//div[@class='KzDlHZ']"));
		System.out.println(lts.size());

		for (int i = sp; i<ep; i++) {
			if (next.isDisplayed()) {
				act.scrollToElement(next).perform();
				sum = sum + lts.size();
				act.click(next).perform();
				Thread.sleep(3000);
			} else {
				break;
			}
		}
		List<WebElement> ltss = d.findElements(By.xpath("//div[@class='KzDlHZ']"));
		sum = sum + ltss.size();
		System.out.println(sum);
		if (sum == trss) {
			System.out.println("products is equals to results");
		} else {
			System.out.println("products is not equals to results");
		}
      d.quit();
	}
}
