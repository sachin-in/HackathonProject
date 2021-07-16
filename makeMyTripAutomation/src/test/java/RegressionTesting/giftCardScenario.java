package RegressionTesting;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import Executor.testExecutor;
import Tools.ElementContainer;

public class giftCardScenario extends testExecutor {

	@Test
	public void giftCards() throws InterruptedException {
		Actions moveCursor = new Actions(driver);
		moveCursor.moveToElement(ElementContainer.moreDropDown(driver)).perform();
		String giftCardLink=ElementContainer.giftCardTab(driver).getText();
		ElementContainer.giftCardTab(driver).click();
		Assert.assertEquals(giftCardLink, "Giftcards", "Name doesn't match");
	}

	@Test(dependsOnMethods = "giftCards")
	public void buyGiftCard() throws InterruptedException {
		Actions click = new Actions(driver);
		click.moveToElement(ElementContainer.giftCard(driver)).click().perform();
		Thread.sleep(500);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");

		ElementContainer.sendersName(driver).sendKeys(data[3][0]);
		ElementContainer.sendersMobile(driver).sendKeys(data[3][1]);
		ElementContainer.sendersEmail(driver).sendKeys(data[3][2]);
		ElementContainer.buyNowButton(driver).click();
		String errorMessage = ElementContainer.errorMessage(driver).getText();
		System.out.println(errorMessage);

	}

}
