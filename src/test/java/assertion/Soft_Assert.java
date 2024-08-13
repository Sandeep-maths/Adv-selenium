package assertion;


import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Soft_Assert {
	@Test
	public void test() {
		SoftAssert soft = new SoftAssert();
		String s1 = "hello";
		String s2 = "universe";
		
		soft.assertEquals(s1, s2);
		
		System.out.println(s1);
		System.out.println(s2);
		soft.assertAll();
		
		
	}
}
