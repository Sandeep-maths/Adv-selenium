package interviewScenarios;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Spicejet {
	public static void main(String[] args) {
		ChromeOptions op = new ChromeOptions();
		op.addArguments("--disable-geolocation");
		op.addArguments("--disable-notifications");
		WebDriver d = new ChromeDriver();
		d.manage().window().maximize();
		d.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		d.get("https://www.spicejet.com/");
		//d.findElement(By.xpath("//*[name()='svg' and @viewBox='0 0 16 16'] ")).click();
		d.findElement(By.xpath("(//input[@class='css-1cwyjr8 r-homxoj r-ubezar r-10paoce r-13qz1uu'])[2]")).sendKeys("Hyderabad (HYD)");
		
		
		String cy = d.findElement(By.xpath("(//div[@class='css-76zvg2 r-homxoj r-adyw6z r-1kfrs79'])[2]")).getText();
	
		if(cy.contains("2025")) {
			}else {
				while(cy.contains("2025"))
				{
					d.findElement(By.xpath("(//*[name()='svg' and@viewBox='0 0 50 50'])[1]")).click();
				}
					
			}
	}

}
