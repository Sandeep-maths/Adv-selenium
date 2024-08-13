package assertion;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class HardAssert {
		@Test
		public void test() {
			
			String s1 = "hello";
			String s2 = "universe";
			
			assertEquals(s1, s2);
			
			System.out.println(s1);
			System.out.println(s2);
			
			
		}
}
