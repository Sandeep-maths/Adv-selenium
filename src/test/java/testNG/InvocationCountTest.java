package testNG;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class InvocationCountTest {
	

	@Test(invocationCount  = -1)
	public void sample1() {
		Reporter.log("sample1", true);
	}
	
	@Test(invocationCount = 3)
	public void sample2() {
		Reporter.log("sample2", true);
	}
	
	@Test
	public void sample3() {
		Reporter.log("sample3", true);
	}
	

}
