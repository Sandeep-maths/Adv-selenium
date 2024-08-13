package testNG;


import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class DependsOnMethodTest {
	@Test(dependsOnMethods = "sample2")
	public void sample1() {
		Reporter.log("sample1", true);
	}
	
	@Test()
	public void sample2() {
		Assert.fail();
		Reporter.log("sample2", true);
	}
	
	@Test(dependsOnMethods = "sample1", alwaysRun = true)
	public void sample3() {
		Reporter.log("sample3", true);
	}

}
