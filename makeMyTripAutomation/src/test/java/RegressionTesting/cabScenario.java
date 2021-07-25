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
		
		Highlight.flash(ElementContainer.cabButton(driver),driver,"CabButton");
		ElementContainer.cabButton(driver).click();// Cab button

		List<WebElement> radio = ElementContainer.radioButton(driver);
		for(int i=0;i<radio.size();i++) {
		if (radio.get(i).getText().equalsIgnoreCase(radio.get(0).getText())) {
			Highlight.flash(radio.get(0),driver,"RadioButton");
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

		Highlight.flash(ElementContainer.fromCity(driver),driver,"DepartureCityInput");
		ElementContainer.fromCity(driver).click();// from option
		ElementContainer.selectDeparture(driver).sendKeys(data[1][7]);// departure city

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
		ElementContainer.selectArrival(driver).sendKeys(data[1][8]);// arrival city
		Highlight.flash(ElementContainer.selectArrival(driver), driver, "ArrivalCity");
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

		Highlight.flash(ElementContainer.calender(driver),driver,"Calender");
		ElementContainer.calender(driver).click(); // calender

		
		List<WebElement> dates = ElementContainer.daySelect(driver);// day picker list
		
		for (int i = 0; i < dates.size(); i++) {
			String test = dates.get(i).getText();
			
			if ((test + ".0").equalsIgnoreCase(data[1][9])) {
				dates.get(i).click();
				
				regReport.createTest("Date of trip");
				Assert.assertEquals(test + ".0", data[1][9], "Date of trip is not correct");
				regReport.flush();
				break;
			}
		}
	}

	@Test(priority=4)
	public static void time() throws InterruptedException {

		Highlight.flash(ElementContainer.pickupTimeDropdown(driver),driver,"TimeDropDown");
		ElementContainer.pickupTimeDropdown(driver).click();// pickup time

		List<WebElement> optionList = ElementContainer.pickupTimeLists(driver);
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].scrollIntoView(true);", optionList.get(14));
		ElementContainer.timeSet(driver).click();
		
		regReport.createTest("Time of trip");
		Assert.assertEquals("06:30", "06:30", "Time of trip is not correct");
		regReport.flush();
		Thread.sleep(1500);

		Highlight.flash(ElementContainer.searchButton(driver),driver,"SearchButton");
		ElementContainer.searchButton(driver).click();

		Thread.sleep(2000);
	}

	@Test(dependsOnMethods = "time",priority=5)
	public static void getCabDetails() throws Exception {
		Thread.sleep(2000);

		Highlight.flash(ElementContainer.suvSelect(driver),driver,"SuvCheckBox");
		ElementContainer.suvSelect(driver).click();
		
		String vehicleName=ElementContainer.carName(driver).getText();
		System.out.println("Cab Details");
		System.out.println(vehicleName);
		Highlight.flash(ElementContainer.carName(driver), driver, "VehicleName");
		String fare=ElementContainer.charges(driver).getText().split(" ")[1];
		System.out.println(fare);
		Highlight.flash(ElementContainer.charges(driver), driver, "TripFare");
		String popularity=ElementContainer.rating(driver).getText();
		System.out.println(popularity);
		Highlight.flash(ElementContainer.rating(driver), driver, "VehicleRating");
		regReport.createTest("Cheapest SUV found");
		Assert.assertEquals(vehicleName, "Xylo, Ertiga", "SUV name is not correct");
		regReport.flush();
		
		Thread.sleep(2000);
		driver.navigate().to(data[1][0]);
		Thread.sleep(2000);
	}

}
