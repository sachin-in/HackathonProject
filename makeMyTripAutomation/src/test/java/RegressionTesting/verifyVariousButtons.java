package RegressionTesting;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import Executor.testExecutor;
import Tools.ElementContainer;
import Tools.Highlight;

public class verifyVariousButtons extends testExecutor {
	
	@Test(priority=0)
	public void verifyCabButton() {
		ElementContainer.cabButton(driver).click();
		String pageTitle = driver.getTitle();
		Assert.assertEquals(pageTitle,"Online Cab Booking - Book Outstation Cabs at Lowest Fare @ MakeMyTrip","Cab button is not working correctly" );
	}
	
	@Test(priority=1)
	public void verifyCabSearchButton() {
		ElementContainer.cabButton(driver).click();
		ElementContainer.searchButton(driver).click();
		String pageTitle = driver.getTitle();
		Assert.assertEquals(pageTitle, "Cabs","Search button is not working Correctly");
	}
	
	@Test(priority=2)
	public void verifyHotelButton() {

		Highlight.flash(ElementContainer.hotelTab(driver),driver,"HotelButton");
		String buttonName = ElementContainer.hotelTab(driver).getText();

		smokeReport.createTest("Hotel button");
		Assert.assertEquals(buttonName, "Hotels","Hotels button not found");
		smokeReport.flush();

	}
	
	@Test(priority=3)
	public void verifyHotelSearchButton() {
		ElementContainer.hotelTab(driver).click();
		ElementContainer.searchButton(driver).click();
		String text = ElementContainer.hotelsearch(driver).getText();
		Assert.assertEquals(text, "Hotels, Villas, Apartments and more in Goa","Hotel Search button is not working Correctly");
		
		
	}
	
	@Test(priority=4)
	public void verifyBuyNowButton() throws InterruptedException {//for gift card scenario
		giftCardScenario.giftCards();
		Actions click = new Actions(driver);

		Highlight.flash(ElementContainer.giftCard(driver),driver,"SelectedGiftCard");
		click.moveToElement(ElementContainer.giftCard(driver)).click().perform();

		Thread.sleep(1000);
		String buttonName=driver.findElement(By.xpath("//button[contains(text(),'BUY NOW')]")).getText();
			
		smokeReport.createTest("Buy Now Button");
		Assert.assertEquals(buttonName, "BUY NOW", "failed");
		smokeReport.flush();
		Thread.sleep(2000);
		driver.navigate().to(data[1][0]);
	}
	
}
