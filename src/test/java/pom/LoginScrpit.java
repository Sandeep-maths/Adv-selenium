package pom;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginScrpit {
	public static void main(String[] args) throws InterruptedException {
		WebDriver dri = new ChromeDriver();
		dri.manage().window().maximize();
		dri.get("https://demoapp.skillrary.com/login.php?type=login");
		dri.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		LoginPage li = new LoginPage(dri);
		
		li.setemailTF("admin");
		li.setpwdTF("admin");
		li.clickkeepMeLoggedInCB();
		li.clickloginBTN();
		 
		Thread.sleep(2000);
		dri.quit();
	}

}
