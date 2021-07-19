package RegressionTesting;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import Executor.testExecutor;
import Tools.ElementContainer;
import Tools.ExcelData;
import Tools.Highlight;

public class cabScenario extends testExecutor {

	@Test(priority = 0)
	public static void searchCab() throws InterruptedException {
		
		Highlight.flash(ElementContainer.cabButton(), driver);
		ElementContainer.cabButton().click();// Cab button
		List<WebElement> radio = ElementContainer.radioButton(driver);
		for(int i=0;i<radio.size();i++) {
		if (radio.get(i).getText().equalsIgnoreCase(radio.get(0).getText())) {
			Highlight.flash(radio.get(0), driver);
			radio.get(i).click();
			String radioSelected=radio.get(0).getText();
			regReport.createTest("Outstation One way Selected");
			Assert.assertEquals(radioSelected, "OUTSTATION ONE-WAY", "Type of trip selected is not correct");
			regReport.flush();
		}
		}
	}

	@Test(priority = 1)
	public void departure() {
		Highlight.flash(ElementContainer.fromCity(driver), driver);
		ElementContainer.fromCity().click();// from option
		ElementContainer.selectDeparture().sendKeys(data[1][7]);// departure city
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}
		Actions keyPress = new Actions(driver);
		keyPress.sendKeys(Keys.chord(Keys.ARROW_DOWN, Keys.ENTER)).perform();
		
		regReport.createTest("Departure City is Delhi");
		Assert.assertEquals("Delhi", "Delhi", "Departure city is not correct");
		regReport.flush();
	}

	@Test(priority = 2)
	public void arrival() {
		ElementContainer.selectArrival().sendKeys(data[1][8]);// arrival city
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}
		Actions keyPress = new Actions(driver);
		keyPress.sendKeys(Keys.chord(Keys.ARROW_DOWN, Keys.ENTER)).perform();
		
		regReport.createTest("Arrival City is Manali");
		Assert.assertEquals("Manali, Himachal Pradesh, India", "Manali, Himachal Pradesh, India", "Arrival city is not correct");
		regReport.flush();
	}

	@Test(priority = 3)
	public void calender() {
		Highlight.flash(ElementContainer.calender(), driver);
		ElementContainer.calender().click(); // calender
		
		List<WebElement> dates = ElementContainer.daySelect();// day picker list
		
		for (int i = 0; i < dates.size(); i++) {
			String test = dates.get(i).getText();
			
			if ((test + ".0").equalsIgnoreCase(data[1][9])) {
				dates.get(i).click();
				
				regReport.createTest("Date of trip");
				Assert.assertEquals("20", "20", "Date of trip is not correct");
				regReport.flush();
				break;
			}
		}
	}

	@Test(priority=4)
	public static void time() throws InterruptedException {
		Highlight.flash(ElementContainer.pickupTimeDropdown(), driver);
		ElementContainer.pickupTimeDropdown().click();// pickup time
		List<WebElement> optionList = ElementContainer.pickupTimeLists(driver);
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].scrollIntoView(true);", optionList.get(14));
		ElementContainer.timeSet().click();
		
		regReport.createTest("Time of trip");
		Assert.assertEquals("06:30", "06:30", "Time of trip is not correct");
		regReport.flush();
		Thread.sleep(1500);
		Highlight.flash(ElementContainer.searchButton(), driver);
		ElementContainer.searchButton().click();
		Thread.sleep(2000);
	}

	@Test(dependsOnMethods = "time")
	public static void getCabDetails() throws Exception {
		Thread.sleep(2000);
		Highlight.flash(ElementContainer.suvSelect(), driver);
		ElementContainer.suvSelect().click();
		
		String vehicleName=ElementContainer.carName().getText();
		String fare=ElementContainer.charges().getText().split(" ")[1];
		String popularity=ElementContainer.rating().getText();
		regReport.createTest("Cheapest SUV found");
		Assert.assertEquals(vehicleName, "Maruti Suzuki Ertiga", "SUV name is not correct");
		regReport.flush();
		ExcelData.writeData(0, 0, "CabScenario");
		ExcelData.writeData(1, 0, "Car Name");
		ExcelData.writeData(1, 1, "Fare");
		ExcelData.writeData(1, 2, "Ratings");
		ExcelData.writeData(2, 0, vehicleName);
		ExcelData.writeData(2, 1, fare);
		ExcelData.writeData(2, 2, popularity);
		Thread.sleep(2000);
		driver.navigate().to(data[1][0]);
	}

}
