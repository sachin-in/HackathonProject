package SmokeTesting;
import java.util.Iterator;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.Test;

import Executor.testExecutor;
import RegressionTesting.giftCardScenario;
import Tools.ElementContainer;
import Tools.Highlight;
import Tools.Screenshot;

public class logingIn extends testExecutor{
	
	@Test(priority=0)
	public void getHomePageSignature() {
		String title = ElementContainer.homepageSignature(driver).getText();// Web Page signature
		Assert.assertEquals(title, data[1][1], "Title is not correct");
		Screenshot.captureScreenshot("Homepage", driver);
	}

	@Test(priority = 1)
	public void loginWithGmail() {
		try {
			Highlight.flash(ElementContainer.loginButton(driver), driver);
			ElementContainer.loginButton(driver).click();// login button
			Thread.sleep(500);
			Highlight.flash(ElementContainer.googleLogin(driver), driver);
			ElementContainer.googleLogin(driver).click();// login by google

			Set<String> handle = driver.getWindowHandles();
			Iterator<String> it = handle.iterator();
			String parentwindowid = it.next();
			String childwindowid = it.next();
			driver.switchTo().window(childwindowid);
			Thread.sleep(1000);

			ElementContainer.emailInput(driver).sendKeys(data[1][4]);// enter email
			Highlight.flash(ElementContainer.nextButton(driver), driver);
			ElementContainer.nextButton(driver).click();// click next
			Thread.sleep(1000);
			ElementContainer.passwordInput(driver).sendKeys(data[1][5]);// enter password
			Highlight.flash(ElementContainer.nextButton(driver), driver);
			ElementContainer.nextButton(driver).click();// click next
			driver.switchTo().window(parentwindowid);
			Thread.sleep(10000);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 2)
	public void loginFromPopUp() {
		try {
			String popUp = ElementContainer.popupTitle(driver).getText();// popup title
			if (popUp.equalsIgnoreCase(data[1][6])) {
				Highlight.flash(ElementContainer.googleClick(driver), driver);
				ElementContainer.googleClick(driver).click();// login by google
				Set<String> handle = driver.getWindowHandles();
				Iterator<String> it = handle.iterator();
				String parentwindowid = it.next();
				String childwindowid = it.next();
				driver.switchTo().window(childwindowid);
				Thread.sleep(1000);

				ElementContainer.emailInput(driver).sendKeys(data[1][4]);// enter email
				Highlight.flash(ElementContainer.nextButton(driver), driver);
				ElementContainer.nextButton(driver).click();// click next
				Thread.sleep(1000);
				ElementContainer.passwordInput(driver).sendKeys(data[1][5]);// enter password
				Highlight.flash(ElementContainer.nextButton(driver), driver);
				ElementContainer.nextButton(driver).click();// click next
				driver.switchTo().window(parentwindowid);
				Thread.sleep(10000);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	@Test
	public void verifyGiftCardTitle() throws InterruptedException {
		giftCardScenario.giftCards();
		Thread.sleep(500);
		String gcTitle = driver.getTitle();
		System.out.println("title is = "+gcTitle);
		
		Assert.assertEquals(gcTitle, "Gift Cards - Buy Gift Vouchers Online, Gift Vouchers | MakeMyTrip.com","Title is not correct");
		
		}
}
