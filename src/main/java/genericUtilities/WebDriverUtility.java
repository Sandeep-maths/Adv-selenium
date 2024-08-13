package genericUtilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This class contains reusable methods to perform driver related operations
 */
public class WebDriverUtility {
	WebDriver driver;
	WebDriverWait wait;
	Actions action;
	Select select;

	/**
	 * This method used to launch user defined browser
	 * 
	 * @param browser
	 * @return WebDriver
	 */
	public WebDriver launchBrowser(String browser) {
		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else {
			System.out.println("invaild browser!");
		}
		return driver;
	}

	/**
	 * This method maximizes the browser window
	 */
	public void maximizeBrowser() {
		driver.manage().window().maximize();
	}

	/**
	 * This method used to navigate to web application
	 * 
	 * @param url
	 */
	public void navigateToWebApp(String url) {
		driver.get(url);
	}

	/**
	 * This method used to perform click operation
	 * 
	 * @param e
	 */
	public void click(WebElement e) {
		e.click();
	}

	/**
	 * This method used waits till element or elements are found
	 * 
	 * @param time
	 */
	public void waitTillElementFound(long time) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
	}

	/**
	 * This method waits till element is displayed in the web page
	 * 
	 * @param time
	 * @param element
	 * @return WebElement
	 */
	public WebElement explicitWait(long time, WebElement element) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		return wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * This method wait till the element is enabled to receive click
	 * 
	 * @param element
	 * @param time
	 * @return WebElement
	 */
	public WebElement explicitWait(WebElement element, long time) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * This method waits till title of the web page appears
	 * 
	 * @param time
	 * @param title
	 * @return Boolean
	 */
	public Boolean explicitWait(long time, String title) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		return wait.until(ExpectedConditions.titleContains(title));
	}

	// chapter 2
	/**
	 * This method is used mouse hover on an element
	 * 
	 * @param element
	 */
	public void mouseHover(WebElement element) {
		action = new Actions(driver);
		action.moveToElement(element).perform();
	}

	/**
	 * This method is used to perform double click on an element
	 * 
	 * @param element
	 */
	public void doubleClick(WebElement element) {
		action = new Actions(driver);
		action.doubleClick(element).perform();
	}

	/**
	 * This method is used to perform right click on an element
	 * 
	 * @param element
	 */
	public void rightClick(WebElement element) {
		action = new Actions(driver);
		action.contextClick(element).perform();

	}

	/**
	 * This method is used to perform click operation on an element
	 * 
	 * @param element
	 */

	public void clickOperation(WebElement element) {
		action = new Actions(driver);
		action.click(element).perform();

	}

	/**
	 * This method used to perform drag and drop an element to destination
	 * 
	 * @param element
	 * @param destination
	 */
	public void dragAndDropAnElement(WebElement element, WebElement destination) {
		action = new Actions(driver);
		action.dragAndDrop(element, destination).perform();
	}

	/**
	 * This method used to select an options from drop down based on index
	 * 
	 * @param element
	 * @param index
	 */
	public void handleDropDown(WebElement element, int index) {
		select = new Select(element);
		select.selectByIndex(index);
	}

	/**
	 * This method used to select an options from drop down based on value attribute
	 * 
	 * @param element
	 * @param value
	 */
	public void handleDropDown(WebElement element, String value) {
		select = new Select(element);
		select.selectByValue(value);
	}

	/**
	 * This method used to select an options from drop down based on visible text
	 * 
	 * @param text
	 * @param element
	 */
	public void handleDropDown(String text, WebElement element) {
		select = new Select(element);
		select.selectByVisibleText(text);
	}

	/**
	 * This method used to switch to frame based on index
	 * 
	 * @param index
	 */
	public void switchToFrame(int index) {
		driver.switchTo().frame(index);
	}

	/**
	 * This method used to switch to frame based on id or name attribute value
	 * 
	 * @param idornameattribute
	 */
	public void switchToFrame(String idornameattribute) {
		driver.switchTo().frame(idornameattribute);
	}

	/**
	 * This method used to switch to frame based on frame element
	 * 
	 * @param frameelement
	 */
	public void switchToFrame(WebElement frameelement) {
		driver.switchTo().frame(frameelement);
	}

	/**
	 * This method used to switch to back from frame
	 */
	public void switchToFrame() {
		driver.switchTo().defaultContent();
	}

	/**
	 * This method used to captures the screenshot of a web page in base64 format
	 * 
	 * @param driver
	 * @return
	 */

	public String getScreenShot(WebDriver driver) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		return ts.getScreenshotAs(OutputType.BASE64);
	}

	/**
	 * This method used to captures the screenshot of a web page in file format
	 * 
	 * @param driver
	 * @param className
	 * @param jutil
	 * @return String
	 */

	public String getScreenShot(WebDriver driver, String className, JavaUtility jutil) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File temp = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File("./Screenshots/" + className + "_" + jutil.getCurrentTime() + ".png");
		try {
			FileUtils.copyFile(temp, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dest.getAbsolutePath();
	}

	/**
	 * This method scrolls till the element based on web element
	 * 
	 * @param element
	 */
	public void scrollToElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)", element);
	}

	/**
	 * This method scrolls till the element based on element location
	 * 
	 * @param location
	 */
	public void scrollToElement(Point location) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollby(" + location.getX() + "," + location.getY() + ")");
	}

	/**
	 * This method is used to alert pop up
	 * 
	 * @param status
	 */
	public void handleAlert(String status) {
		if (status.equalsIgnoreCase("ok")) {
			driver.switchTo().alert().accept();
		} else {
			driver.switchTo().alert().dismiss();
		}
	}

	/**
	 * This method used to returns current window id reference
	 * 
	 * @return
	 */
	public String getParentWindowId() {
		return driver.getWindowHandle();
	}

	/**
	 * This method used to returns child window by title reference
	 * 
	 * @param expectedTitle
	 */
	public void switchToWindowIdTitle(String expectedTitle) {
		Set<String> windowsIds = driver.getWindowHandles();
		Iterator<String> it = windowsIds.iterator();
		while (it.hasNext()) {
			driver.switchTo().window(it.next());
			if (driver.getTitle().contains(expectedTitle)) {
				break;
			}
		}

	}
	
	/**
	 * This method used to returns child window by URL reference
	 * 
	 * @param expectedTitle
	 */
	public void switchToWindowIdUrl(String expectedUrl) {
		Set<String> windowsIds = driver.getWindowHandles();
		Iterator<String> it = windowsIds.iterator();
		while (it.hasNext()) {
			driver.switchTo().window(it.next());
			if (driver.getCurrentUrl().contains(expectedUrl)) {
				break;
			}
		}

	}

	/**
	 * This method used to close current window
	 */
	public void closeWindow() {
		driver.close();
	}

	/**
	 * This method used to close all windows
	 */
	public void quitAllWindows() {
		driver.quit();
	}
	/**
	 * This method converts dynamic xpath to web element
	 * @param dynamicPath
	 * @param tabname
	 * @return
	 */
	public WebElement convertDynamicXpathToWebElement(String dynamicPath, String replaceData) {
		return driver.findElement(By.xpath(String.format(dynamicPath, replaceData)));
	}
}
