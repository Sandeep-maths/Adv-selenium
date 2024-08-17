package testNG;

import org.testng.Reporter;
import org.testng.annotations.Test;
//test
public class SampleTest {
	@Test
	public void demo() {
		Reporter.log("Hello Universe!", true);
		
	}
}
