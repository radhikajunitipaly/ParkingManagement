package selenium;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;
import org.openqa.selenium.*;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import selenium.functions.LoginTestFunctions;
import selenium.functions.PaymentFunctions;
import selenium.functions.RegisterUserFunctions;
import selenium.functions.SearchParkingSpotFunctions;

@RunWith(JUnitParamsRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoginValidationsTest extends SeleniumTestBase{
	static RegisterUserFunctions registerUserFunctions;
	static LoginTestFunctions loginTestFunctions;
	static SearchParkingSpotFunctions searchParkingSpotFunctions;
	static PaymentFunctions paymentFunctions;
	static SeleniumTestBase seleniumTestBase;
	 
	@BeforeClass
	public static void setUp() throws Exception {
		System.out.println("Before class");
		seleniumTestBase = new SeleniumTestBase();
	    registerUserFunctions = new RegisterUserFunctions();
	    loginTestFunctions = new LoginTestFunctions();
	    searchParkingSpotFunctions = new SearchParkingSpotFunctions();
	    paymentFunctions = new PaymentFunctions();
	    setDriver();
	}
	
	@AfterClass
	public static void tearDownAfterTest() throws Exception {
		driver.close();
	}

	@Test
	@FileParameters("./seleniumTestData/UserLoginTestData.csv")
	public void loginErrorValidationsTest(int testno, String username, String password, String errMsg) throws Exception {
		String error = "";
		  
		error = loginTestFunctions.loginErrorFunction(driver, username, password);
		assertEquals(errMsg, error);
		if(testDelay.equals("delay")) 
			Thread.sleep(2000);
	}
	
	@Test
	@FileParameters("./seleniumTestData/UserLoginTestDataSuccess.csv")
	public void loginSuccess(int testno, String username, String password, String errMsg) throws Exception{
		loginTestFunctions.loginSuccessFunction(driver, "brocoline", "Test@123");
		if(testDelay.equals("delay")) 
			Thread.sleep(1000);
		logout();
	}

	private void logout() throws Exception{
		driver.findElement(By.xpath(prop.getProperty("StudentFaculty_Logout_link"))).sendKeys(Keys.ENTER);
		if(testDelay.equals("delay")) Thread.sleep(1000);
	}
}
