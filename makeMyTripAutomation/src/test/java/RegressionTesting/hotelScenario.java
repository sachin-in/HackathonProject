package RegressionTesting;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import Executor.testExecutor;
import Tools.ElementContainer;
import Tools.Highlight;

public class hotelScenario extends testExecutor {

	ArrayList<String> adults;

	@Test(priority = 0)
	public void enterCity() {

		Highlight.flash(ElementContainer.hotelTab(driver), driver, "HotelButton");
		ElementContainer.hotelTab(driver).click();

		Highlight.flash(ElementContainer.enterCity(driver), driver, "EnterCityButton");
		ElementContainer.enterCity(driver).click();
		String buttonText = ElementContainer.enterCity(driver).getText();
		regReport.createTest("EnterHotelLocation");
		Assert.assertEquals(buttonText, "CITY / HOTEL / AREA / BUILDING", "Text is not correct");
		regReport.flush();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ElementContainer.inputCity(driver).sendKeys(data[1][8]);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Actions keyPress = new Actions(driver);
		keyPress.sendKeys(Keys.chord(Keys.ARROW_DOWN, Keys.ENTER)).perform();

	}

	@Test(dependsOnMethods = "enterCity", priority = 1)
	public void enterCheckInCheckOutDate() {

		Highlight.flash(ElementContainer.checkInTab(driver), driver, "Calender");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebElement checkInDate = ElementContainer.checkInDropdown(driver);// day picker list
		checkInDate.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement checkOutDate = ElementContainer.checkOutDropdown(driver);// day picker list
		checkOutDate.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String buttonText = ElementContainer.checkInTab(driver).getText();
		regReport.createTest("EnterCheckInChekOutDate");
		Assert.assertEquals(buttonText, "CHECK-IN", "Text is not correct");
		regReport.flush();
	}

	@Test(priority = 2)
	public void verifyRoomsAndGuestButton() {

		adults = new ArrayList<String>();
		ElementContainer.guestsButton(driver);// guest button
		Highlight.flash(ElementContainer.roomsAndGuestsTab(driver), driver, "RoomsAndGuestButton");
		String buttonText = ElementContainer.roomsAndGuestsTab(driver).getText();
		ElementContainer.guestsButton(driver).click();
		regReport.createTest("VerifyROoms&GuestButton");
		Assert.assertEquals(buttonText, "ROOMS & GUESTS", "Text doesn't match");
		regReport.flush();
	}

	@Test(priority = 3, dependsOnMethods = "verifyRoomsAndGuestButton")
	public void getAdultsList() throws InterruptedException {

		List<WebElement> adultsCount = ElementContainer.adultsList(driver);// no. of adults list

		for (WebElement c : adultsCount) {
			adults.add(c.getText());
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ElementContainer.applyButton(driver).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ElementContainer.hotelsearch(driver).click();

		regReport.createTest("SelectNumberOfAdults");
		Assert.assertEquals(adults.size() + ".0", data[1][2], "No. of adults is not correct");
		regReport.flush();

		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(priority = 4, dependsOnMethods = "getAdultsList")
	public void verifyFreeBreakfastCheckBox() throws InterruptedException {

		WebElement freeBreakfastButton =ElementContainer.freeBreakfastCheckbox(driver);
		Highlight.flash(freeBreakfastButton, driver, "FreeBreakfastCheckBox");
		String buttonText = freeBreakfastButton.getText();
		freeBreakfastButton.click();
		regReport.createTest("SelectFreeBreakfast");
		Assert.assertEquals(buttonText, "Free Breakfast", "Text is not correct");
		regReport.flush();

		Thread.sleep(2000);
		driver.navigate().to(data[1][0]);
		Thread.sleep(2000);
	}

}
