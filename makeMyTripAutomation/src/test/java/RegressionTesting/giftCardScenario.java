package RegressionTesting;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import Executor.testExecutor;
import Tools.ElementContainer;
import Tools.Highlight;

public class giftCardScenario extends testExecutor {

	@Test(priority=0)
	public static void giftCards() throws InterruptedException {
		Actions moveCursor = new Actions(driver);

		moveCursor.moveToElement(ElementContainer.moreDropDown(driver)).perform();
		String giftCardLink=ElementContainer.giftCardTab(driver).getText();
		Highlight.flash(ElementContainer.giftCardTab(driver),driver,"GiftCardLink");
		ElementContainer.giftCardTab(driver).click();

		regReport.createTest("Gift Card page");
		Assert.assertEquals(giftCardLink, "Giftcards", "Name doesn't match");
		regReport.flush();
	}
	
	@Test(priority = 1)
	public void verifyGiftCardTitle() throws InterruptedException {
		Thread.sleep(500);
		String gcTitle = driver.getTitle();
		System.out.println("Title is = " + gcTitle);
		smokeReport.createTest("Gift Card Page");
		Assert.assertEquals(gcTitle, "Gift Cards - Buy Gift Vouchers Online, Gift Vouchers | MakeMyTrip.com", "Title is not correct");
		smokeReport.flush();
		}
	
	@Test(priority=2)
	public void verifyGiftCard() throws Exception {

		Thread.sleep(1000);
		String giftCardName=driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/p[1]")).getText();
		
		smokeReport.createTest("Eid Mubarak Gift Card");
		Assert.assertEquals(giftCardName, "Eid Mubarak Gift Card", "Gift card is not expected");
		smokeReport.flush();

	}

	@Test(dependsOnMethods = "giftCards", priority=3)
	public void buyGiftCard() throws InterruptedException {
		Actions click = new Actions(driver);

		Highlight.flash(ElementContainer.giftCard(driver),driver,"SelectedGiftCard");
		click.moveToElement(ElementContainer.giftCard(driver)).click().perform();

		Thread.sleep(500);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");

		ElementContainer.sendersName(driver).sendKeys(data[3][0]);
		ElementContainer.sendersMobile(driver).sendKeys(data[3][1]);
		ElementContainer.sendersEmail(driver).sendKeys(data[3][2]);
		Thread.sleep(2000);


		ElementContainer.buyNowButton(driver).click();
		String errorMessage = ElementContainer.errorMessage(driver).getText();

		System.out.println(errorMessage);
		regReport.createTest("Invalid email in gift card");
		Assert.assertEquals(errorMessage, "Please enter a valid Email id.", "Error message displayed is not correct");
		regReport.flush();

	}
	
	@Test(priority=4)
	public void verifyMajorErrorMessage() {
	
	}
	
}
