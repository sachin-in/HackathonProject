package SmokeTesting;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import Executor.testExecutor;
import RegressionTesting.cabScenario;
import RegressionTesting.giftCardScenario;
import Tools.ElementContainer;
import Tools.Highlight;
import Tools.Screenshot;

public class smokeTest extends testExecutor {

	@Test(priority = 0)
	public void getHomePageSignature() {
		String title = ElementContainer.homepageSignature(driver).getText();// Web Page signature
		Assert.assertEquals(title, data[1][1], "Title is not correct");
		Screenshot.captureScreenshot("Homepage", driver);
	}

	@Test(priority = 1)
	public void verifyCountry() {
		Highlight.flash(ElementContainer.countryVerify(driver), driver);
		String name = ElementContainer.countryVerify(driver).getText();
		Assert.assertEquals(name, "IN | ENG | INR", "India is not selected");
	}

	@Test(priority = 2)
	public void verifyLoginButton() {
		Highlight.flash(ElementContainer.loginButton(driver), driver);
		String buttonName = ElementContainer.loginButton(driver).getText();
		Assert.assertEquals(buttonName, "Login or Create Account", "Button is not found");

	}

	@Test(priority = 3)
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
			Thread.sleep(2000);
			Highlight.flash(ElementContainer.nextButton(driver), driver);
			ElementContainer.nextButton(driver).click();// click next
			driver.switchTo().window(parentwindowid);
			Thread.sleep(5000);
			driver.findElement(By.xpath(
					"//body/div[@id='root']/div[1]/div[1]/div[1]/div[2]/div[2]/section[1]/div[1]/div[1]/span[1]"))
					.click();
			Thread.sleep(5000);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 4)
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
				Thread.sleep(2000);
				ElementContainer.passwordInput(driver).sendKeys(data[1][5]);// enter password
				Highlight.flash(ElementContainer.nextButton(driver), driver);
				ElementContainer.nextButton(driver).click();// click next
				driver.switchTo().window(parentwindowid);
				Thread.sleep(5000);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test(priority = 14)
	public void verifyGiftCardTitle() throws InterruptedException {
		giftCardScenario.giftCards();
		Thread.sleep(500);
		String gcTitle = driver.getTitle();
		System.out.println("title is = " + gcTitle);

		Assert.assertEquals(gcTitle, "Gift Cards - Buy Gift Vouchers Online, Gift Vouchers | MakeMyTrip.com",
				"Title is not correct");
		Thread.sleep(2000);
		driver.navigate().to(data[1][0]);

		}


	@Test(priority=15)
	public void verifyFathersDayGiftCard() throws Exception {

		giftCardScenario.giftCards();
		Actions click = new Actions(driver);
		Highlight.flash(ElementContainer.giftCard(driver), driver);
		click.moveToElement(ElementContainer.giftCard(driver)).click().perform();

		Thread.sleep(1000);
		String giftCardName=driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/p[1]")).getText();

		Assert.assertEquals(giftCardName, "Father's Day Gift Card", "Gift card is not expected");
		Thread.sleep(2000);
		driver.navigate().to(data[1][0]);
	}

	@Test(priority=5)
	public void verifyBuyNowButton() throws InterruptedException {//for gift card scenario
		giftCardScenario.giftCards();
		Actions click = new Actions(driver);
		Highlight.flash(ElementContainer.giftCard(driver), driver);
		click.moveToElement(ElementContainer.giftCard(driver)).click().perform();
		Thread.sleep(1000);
		String buttonName=driver.findElement(By.xpath("//button[contains(text(),'BUY NOW')]")).getText();

		Assert.assertEquals(buttonName, "BUY NOW", "failed");
		Thread.sleep(2000);
		driver.navigate().to(data[1][0]);
	}

	@Test(priority=6)
	public void verifyCabButton() throws InterruptedException {
		cabScenario.searchCab();
		Highlight.flash(ElementContainer.cabButton(driver), driver);
		String buttonName = ElementContainer.cabButton(driver).getText();
		Assert.assertEquals(buttonName, "Cabs", "failed");
	}

	@Test(priority=7)
	public void verifySelectedRadioButton() throws InterruptedException {
		cabScenario.searchCab();
		boolean value = driver
				.findElement(By.xpath("//body/div[@id='root']/div[1]/div[2]/div[1]/div[1]/div[1]/ul[1]/li[1]"))
				.isEnabled();
		Assert.assertTrue(value);

	}

	@Test(priority=8)
	public void verifyErrorForSameCitySelected() throws InterruptedException {

		cabScenario.searchCab();
		Highlight.flash(ElementContainer.fromCity(driver), driver);
		ElementContainer.fromCity(driver).click();// from option
		ElementContainer.selectDeparture(driver).sendKeys(data[1][7]);// departure city
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}
		Actions keyPress = new Actions(driver);
		keyPress.sendKeys(Keys.chord(Keys.ARROW_DOWN, Keys.ENTER)).perform();
		
		ElementContainer.selectArrival(driver).sendKeys(data[1][7]);// arrival city
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}
		Actions keyPress1 = new Actions(driver);
		keyPress1.sendKeys(Keys.chord(Keys.ARROW_DOWN, Keys.ENTER)).perform();

		
	}
	@Test(priority=9)
	public void verifyCalenderDropDown() {

	}
	@Test(priority=10)
	public void verifyPickUpTimeDropDown() throws InterruptedException {
		 
	}

	@Test(priority=11)
	public void verifyCabSearchButton() throws InterruptedException {
		Highlight.flash(ElementContainer.searchButton(driver), driver);
		String buttonName = ElementContainer.searchButton(driver).getText();
		Assert.assertEquals(buttonName, "SEARCH", "failed");
	}

	@Test(priority=12)
	public void verifySUVButton() throws Exception {
		 
	}
	
	@Test(priority=13)
	public void verifyHotelButton() {
		Highlight.flash(ElementContainer.hotelTab(driver), driver);
		String buttonName = ElementContainer.hotelTab(driver).getText();
		Assert.assertEquals(buttonName, "Hotels","Hotels button not found");

	}
	@Test(priority=16)
	public void verifyRoomsAndGuestButton() {

	}

}
