package testNG;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class RetryTest {

		//@Test(retryAnalyzer = genericUtilities.IretryAnalyzerImplementation.class)
	    @Test()
		public void test() {
			Reporter.log("test", true);
			//Assert.fail();
		}
}
