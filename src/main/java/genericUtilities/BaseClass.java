package genericUtilities;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import objectRepository.HomePage;
import objectRepository.LoginPage;
import objectRepository.ObjectPageManager;

public class BaseClass {
	// @BeforeSuite;
	// @BeforeTest;

	protected WebDriver dri;
	protected PropertiesUtility prou;
	protected ExcelUtility exu;
	protected JavaUtility jau;
	protected WebDriverUtility driu;
	
	public static WebDriver sdriver;
	public static JavaUtility sjutil;

	protected ObjectPageManager opm;

	protected LoginPage li;
	protected HomePage ho;
	protected SoftAssert soft;
	
	
//	protected OrganizationPage org;
//	protected CreatingNewOrganizationPage corg;
//	protected OrganizationInformationPage iorg;
	//@Parameters("BROWSER")
	@BeforeClass(groups = "important")
	public void classSetUp() {
		prou = new PropertiesUtility();
		exu = new ExcelUtility();
		jau = new JavaUtility();
		driu = new WebDriverUtility();

		
		prou.propertiesInit(Iconstantpath.PROPERTIES_FILE_PATH);
		exu.excelInit(Iconstantpath.EXCEL_VTIGER);
		//exu.excelInit(Iconstantpath.EXCEL_FILE_PATH);
		
		//dri = driu.launchBrowser(browser);
		dri = driu.launchBrowser(prou.readdata("browser"));
		driu.maximizeBrowser();

		long time = (Long) jau.ConvertStringToAnyDataType(prou.readdata("timeouts"), "long");
		driu.waitTillElementFound(time);
		
		soft = new SoftAssert();
		
		sdriver=dri;
		sjutil=jau;
	}

	
	@BeforeMethod(groups = "important")
	public void methodSetUp() {
		driu.navigateToWebApp(prou.readdata("url"));

		opm = new ObjectPageManager(dri);
		li = new LoginPage(dri);
		ho = new HomePage(dri);
//		org = new OrganizationPage(dri);
//		corg = new CreatingNewOrganizationPage(dri);
//		iorg = new OrganizationInformationPage(dri);

		Assert.assertTrue(dri.getTitle().contains(exu.readFromExcel("org", 2, 1)));

		li.loginToVtiger(prou.readdata("un"), prou.readdata("pswd"));

		Assert.assertTrue(dri.getTitle().contains("Home"));
		
		
		


	}

	@AfterMethod(groups = "important")
	public void methodShutDown() {
		

		exu.saveExcel(Iconstantpath.EXCEL_VTIGER);
		
		

		//exu.saveExcel(Iconstantpath.EXCEL_VTIGER);

		ho.signOutFromVtiger(driu);
		exu.closeExcel();
	}

	@AfterClass(groups = "important")
	public void classShutDown() {
		//exu.closeExcel();
		driu.quitAllWindows();
	}
	// @AfterTest;
	// @AfterSuite

}
