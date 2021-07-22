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
		
	}
	
	@Test(priority=1)
	public void verifyCabSearchButton() {
		
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
