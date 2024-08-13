package testNG;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class PriorityFlagTest {
	
	@Test(priority = -1)
	public void sample1() {
		Reporter.log("sample1", true);
	}
	
	@Test(priority = 1)
	public void sample2() {
		Reporter.log("sample2", true);
	}
	
	@Test
	public void sample3() {
		Reporter.log("sample3", true);
	}
	
	@Test
	public void sample4() {
		Reporter.log("sample4", true);
	}
	
	@Test(priority = -2)
	public void sample5() {
		Reporter.log("sample5", true);
		
		
			
		}
	
	@Test
	public void mavenParameterization() {
		
		WebDriver d = null;
		String browser = System.getProperty("BROWSER");
		
		if (browser.equalsIgnoreCase("chrome")) {
			d= new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			d = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			d = new EdgeDriver();
		} else {
			System.out.println("invaild browser!");
		}
		d.manage().window().maximize();
		d.get(System.getProperty("URL"));
		
		System.out.println(d.getTitle());
		d.quit();
		
	}
	}


