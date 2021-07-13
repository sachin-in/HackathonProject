package RegressionTesting;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import Executor.testExecutor;
import Tools.ElementContainer;
import Tools.ExcelData;

public class cabScenario extends testExecutor {

	@Test(priority = 0)
	public void searchCab() {
		ElementContainer.cabButton(driver).click();// Cab button
		WebElement radio = ElementContainer.radioButton(driver);
		if (radio.isSelected() == false) {
			radio.click();

		}
	}

	@Test(priority = 1)
	public void departure() {
		ElementContainer.fromCity(driver).click();// from option
		ElementContainer.selectDeparture(driver).sendKeys(data[1][7]);// departure city
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}
		Actions keyPress = new Actions(driver);
		keyPress.sendKeys(Keys.chord(Keys.ARROW_DOWN, Keys.ENTER)).perform();
	}

	@Test(priority = 2)
	public void arrival() {
		ElementContainer.selectArrival(driver).sendKeys(data[1][8]);// arrival city
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}
		Actions keyPress = new Actions(driver);
		keyPress.sendKeys(Keys.chord(Keys.ARROW_DOWN, Keys.ENTER)).perform();
	}

	@Test(priority = 3)
	public void calender() {
		ElementContainer.calender(driver).click(); // calender
		List<WebElement> dates = ElementContainer.daySelect(driver);// day picker list
		
		for (int i = 0; i < dates.size(); i++) {
			String test = dates.get(i).getText();
			
			if ((test + ".0").equalsIgnoreCase(data[1][9])) {
				dates.get(i).click();
				break;
			}
		}
	}

	@Test(dependsOnMethods = { "searchCab", "departure", "arrival", "calender" })
	public void time() throws InterruptedException {
		ElementContainer.pickupTimeDropdown(driver).click();// pickup time
		List<WebElement> optionList = ElementContainer.pickupTimeLists(driver);
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].scrollIntoView(true);", optionList.get(14));
		ElementContainer.timeSet(driver).click();
		Thread.sleep(1500);
		ElementContainer.searchButton(driver).click();
		Thread.sleep(5000);
	}

	@Test(dependsOnMethods = "time")
	public void getCabDetails() throws Exception {
		ElementContainer.suvSelect(driver).click();
		
		String vehicleName=ElementContainer.carName(driver).getText();
		String fare=ElementContainer.charges(driver).getText().split(" ")[1];
		String popularity=ElementContainer.rating(driver).getText();
		ExcelData.writeData(0, 0, "CabScenario");
		ExcelData.writeData(1, 0, vehicleName);
		ExcelData.writeData(1, 1, fare);
		ExcelData.writeData(1, 2, popularity);
	}

}
