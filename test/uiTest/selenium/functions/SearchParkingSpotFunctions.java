package uiTest.selenium.functions;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class SearchParkingSpotFunctions extends SeleniumFunctionsBase{

	
	public void searchParkingSpot(WebDriver driver, String parkingAreaName, 
			String ParkingType, String fromTime, String toTime) {
		new Select(driver.findElement(By.id(prop.getProperty("SearchParkingSpot_parkingName_option"))))
			.selectByVisibleText(parkingAreaName);
		new Select(driver.findElement(By.id(prop.getProperty("SearchParkingSpot_type_option"))))
				.selectByVisibleText(ParkingType);
		driver.findElement(By.id(prop.getProperty("SearchParkingSpot_fromTime_txt"))).clear();
		driver.findElement(By.id(prop.getProperty("SearchParkingSpot_fromTime_txt"))).sendKeys(fromTime);
		driver.findElement(By.id(prop.getProperty("SearchParkingSpot_toTime_txt"))).clear();
		driver.findElement(By.id(prop.getProperty("SearchParkingSpot_toTime_txt"))).sendKeys(toTime);
		driver.findElement(By.id(prop.getProperty("SearchParkingSpot_selectedcart_checkbox"))).click();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.id(prop.getProperty("SearchParkingSpot_search_btn"))).sendKeys(Keys.ENTER);
	}
}