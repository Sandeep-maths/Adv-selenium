package listeners;


import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
//@Listeners(listeners.ListenerImplimentation.class)
public class TestClass extends BaseClass {
	
	@Test
	public void sample() {
		System.out.println("@Test");
		Assert.fail();
	
	}

}
