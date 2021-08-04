package RegressionTesting;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import Executor.testExecutor;
import Tools.ElementContainer;
import Tools.Highlight;

public class verifyVariousButtons extends testExecutor {

	@Test(priority = 0)
	public void verifyCabButton() {

		Highlight.flash(ElementContainer.cabButton(driver), driver, "CabButton");
		ElementContainer.cabButton(driver).click();

		String pageTitle = driver.getTitle();
		regReport.createTest("VerifyCabButon(CabScenario)");
		Assert.assertEquals(pageTitle, "Online Cab Booking - Book Outstation Cabs at Lowest Fare @ MakeMyTrip",
				"Cab button is not working correctly");
		regReport.flush();

	}

	@Test(priority = 1, dependsOnMethods = "verifyCabButton")
	public void verifyCabSearchButton() throws InterruptedException {

		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		ElementContainer.searchButton(driver).click();

		String pageTitle = driver.getTitle();
		regReport.createTest("VerifyCabSearchButton(CabScenario)");
		Assert.assertEquals(pageTitle, "Cabs", "Search button is not working Correctly");
		regReport.flush();
		
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		driver.navigate().to(data[1][0]);
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
	}

	@Test(priority = 2)
	public void verifyHotelButton() {

		Highlight.flash(ElementContainer.hotelTab(driver), driver, "HotelButton");
		ElementContainer.hotelTab(driver).click();

		String buttonName = ElementContainer.hotelTab(driver).getText();
		regReport.createTest("VerifyHotelButton(HotelScenario)");
		Assert.assertEquals(buttonName, "Hotels", "Hotels button not found");
		regReport.flush();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

	}

	@Test(priority = 3, dependsOnMethods = "verifyHotelButton")
	public void verifyHotelSearchButton() throws InterruptedException {

		ElementContainer.hotelsearch(driver).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		Thread.sleep(5000);
		WebElement freeBreakfastButton =  ElementContainer.freeBreakfastCheckbox(driver);
		Highlight.flash(freeBreakfastButton, driver, "FreeBreakfastCheckBox");

		String buttonText = freeBreakfastButton.getText();
		regReport.createTest("SelectFreeBreakfast");
		Assert.assertEquals(buttonText, "Free Breakfast", "Text is not correct");
		regReport.flush();
		
		
		driver.navigate().to(data[1][0]);
		
		
	}

	@Test(priority = 4)
	public void verifyBuyNowButton() throws InterruptedException {// for gift card scenario

		Actions moveCursor = new Actions(driver);

		moveCursor.moveToElement(ElementContainer.moreDropDown(driver)).perform();

		Highlight.flash(ElementContainer.giftCardTab(driver), driver, "GiftCardLink");
		ElementContainer.giftCardTab(driver).click();
		Actions click = new Actions(driver);

		Highlight.flash(ElementContainer.giftCard(driver), driver, "SelectedGiftCard");
		click.moveToElement(ElementContainer.giftCard(driver)).click().perform();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		//Thread.sleep(1000);
		String buttonName = ElementContainer.buyNowButton(driver).getText();

		regReport.createTest("VerifyBuyNowButton(GiftCardScenario)");
		Assert.assertEquals(buttonName, "BUY NOW", "failed");
		regReport.flush();
		
		
		driver.navigate().to(data[1][0]);
		
		
	}

}
