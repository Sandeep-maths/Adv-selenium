package listeners;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class BaseClass {
	
	@BeforeSuite
	public void suiteSetup() {
		System.out.println("@BeforeSuite");
	}
	
	@BeforeTest
	public void testSetup() {
		System.out.println("@BeforeTest");
	}
	
	@BeforeClass
	public void classSetup() {
		System.out.println("@BeforeClass");
	}
	
	@BeforeMethod
	public void methodSetup() {
		System.out.println("@BeforeMethod");
	}
	
	@AfterMethod
	public void methodShutdown() {
		System.out.println("@AfterMethod");
	}
	
	@AfterClass
	public void classShutdown() {
		System.out.println("@AfterClass");
	}
	
	@AfterTest
	public void testShutdown() {
		System.out.println("@AfterTest");
	}
	
	@AfterSuite
	public void suiteShutdown() {
		System.out.println("@AfterSuite");
	}
	
}
