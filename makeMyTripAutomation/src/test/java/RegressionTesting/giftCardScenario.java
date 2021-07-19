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

		moveCursor.moveToElement(ElementContainer.moreDropDown()).perform();
		String giftCardLink=ElementContainer.giftCardTab().getText();
		Highlight.flash(ElementContainer.giftCardTab(),"giftCardsTab");
		ElementContainer.giftCardTab().click();

		regReport.createTest("Gift Card page");
		Assert.assertEquals(giftCardLink, "Giftcards", "Name doesn't match");
		regReport.flush();
	}

	@Test(dependsOnMethods = "giftCards")
	public void buyGiftCard() throws InterruptedException {
		Actions click = new Actions(driver);

		Highlight.flash(ElementContainer.giftCard(),"buyGiftCard");
		click.moveToElement(ElementContainer.giftCard()).click().perform();

		Thread.sleep(500);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");

		ElementContainer.sendersName().sendKeys(data[3][0]);
		ElementContainer.sendersMobile().sendKeys(data[3][1]);
		ElementContainer.sendersEmail().sendKeys(data[3][2]);
		Thread.sleep(2000);

		Highlight.flash(ElementContainer.buyNowButton(),"buyNowButton");
		ElementContainer.buyNowButton().click();
		String errorMessage = ElementContainer.errorMessage().getText();

		System.out.println(errorMessage);
		regReport.createTest("Invalid email in gift card");
		Assert.assertEquals(errorMessage, "Please enter a valid Email id.", "Error message displayed is not correct");
		regReport.flush();
		Thread.sleep(2000);
		driver.navigate().to(data[1][0]);

	}
	
	@Test(priority = 14)
	public void verifyGiftCardTitle() throws InterruptedException {
		giftCardScenario.giftCards();
		Thread.sleep(500);
		String gcTitle = driver.getTitle();
		System.out.println("title is = " + gcTitle);
		smokeReport.createTest("Gift Card Page");
		Assert.assertEquals(gcTitle, "Gift Cards - Buy Gift Vouchers Online, Gift Vouchers | MakeMyTrip.com", "Title is not correct");
		smokeReport.flush();
	
		}
	
	@Test(priority=15)
	public void verifyFathersDayGiftCard() throws Exception {

		Actions click = new Actions(driver);

		Highlight.flash(ElementContainer.giftCard(),"verifyFathersDayGiftCard");

		
		click.moveToElement(ElementContainer.giftCard()).click().perform();

		Thread.sleep(1000);
		String giftCardName=driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/p[1]")).getText();
		
		smokeReport.createTest("Father's Day gift card");
		Assert.assertEquals(giftCardName, "Father's Day Gift Card", "Gift card is not expected");
		smokeReport.flush();
		Thread.sleep(2000);
		driver.navigate().to(data[1][0]);
	}
	
	@Test(priority=5)
	public void verifyBuyNowButton() throws InterruptedException {//for gift card scenario
		giftCardScenario.giftCards();
		Actions click = new Actions(driver);

		Highlight.flash(ElementContainer.giftCard(),"verifyBuyNowButton");
		click.moveToElement(ElementContainer.giftCard()).click().perform();

		Thread.sleep(1000);
		String buttonName=driver.findElement(By.xpath("//button[contains(text(),'BUY NOW')]")).getText();
		
		smokeReport.createTest("Buy Now Button");
		Assert.assertEquals(buttonName, "BUY NOW", "failed");
		smokeReport.flush();
		Thread.sleep(2000);
		driver.navigate().to(data[1][0]);
	}

}
