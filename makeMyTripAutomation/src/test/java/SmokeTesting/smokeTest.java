package SmokeTesting;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import Executor.testExecutor;
import Tools.ElementContainer;
import Tools.Highlight;

public class smokeTest extends testExecutor {

	@Test(priority = 0)
	public void getHomePageSignature() {
		String title = ElementContainer.homepageSignature(driver).getText();// Web Page signature
		smokeReport.createTest("HomePageSignature");
		System.out.println(title);
		Assert.assertEquals(title, data[1][1], "Title is not correct");
		smokeReport.flush();
		
		Highlight.flash(ElementContainer.homepageSignature(driver),driver,"Homepage");
		 

	}

	@Test(priority = 1)
	public void verifyCountry() {

		Highlight.flash(ElementContainer.countryVerify(driver),driver,"Country&Currency");
		String name = ElementContainer.countryVerify(driver).getText();
		smokeReport.createTest("WebsiteDomainContry&Currency");
		System.out.println(name);
		Assert.assertEquals(name, "IN | ENG | INR", "India is not selected");
		smokeReport.flush();

	}

	@Test(priority = 2)
	public void verifyLoginButton() {

		Highlight.flash(ElementContainer.loginButton(driver), driver,"LoginButton");
		String buttonName = ElementContainer.loginButton(driver).getText();
		smokeReport.createTest("Verifyloginbutton");
		Assert.assertEquals(buttonName, "Login or Create Account", "Button is not found");
		smokeReport.flush();

	}

	@Test(priority = 3)
	public void loginWithGmail() {
		try {

			smokeReport.createTest("LoginWithLoginButton");
			ElementContainer.loginButton(driver).click();// login button
			Thread.sleep(500);
			Highlight.flash(ElementContainer.googleLogin(driver), driver,"LoginWithGoogle");
			ElementContainer.googleLogin(driver).click();// login by google


			Set<String> handle = driver.getWindowHandles();
			Iterator<String> it = handle.iterator();
			String parentwindowid = it.next();
			String childwindowid = it.next();
			driver.switchTo().window(childwindowid);
			Thread.sleep(1000);


			ElementContainer.emailInput(driver).sendKeys(data[1][4]);// enter email
			Highlight.flash(ElementContainer.nextButton(driver),driver,"EmailNextButton");
			ElementContainer.nextButton(driver).click();// click next

			Thread.sleep(1000);
			ElementContainer.passwordInput(driver).sendKeys(data[1][5]);// enter password
			Thread.sleep(2000);

			Highlight.flash(ElementContainer.nextButton(driver), driver,"PasswordNextButton");
			ElementContainer.nextButton(driver).click();// click next

			driver.switchTo().window(parentwindowid);
			Thread.sleep(5000);
			ElementContainer.button(driver).click();
			Thread.sleep(5000);
			smokeReport.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 4)
	public void loginFromPopUp() {
		try {
			smokeReport.createTest("LoginFromPopUp");
			String popUp = ElementContainer.popupTitle(driver).getText();// popup title
			if (popUp.equalsIgnoreCase(data[1][6])) {

				Highlight.flash(ElementContainer.googleClick(driver),driver,"LoginWithGooglePopUp");
				ElementContainer.googleClick(driver).click();// login by google

				Set<String> handle = driver.getWindowHandles();
				Iterator<String> it = handle.iterator();
				String parentwindowid = it.next();
				String childwindowid = it.next();
				driver.switchTo().window(childwindowid);
				Thread.sleep(1000);


				ElementContainer.emailInput(driver).sendKeys(data[1][4]);// enter email

				ElementContainer.nextButton(driver).click();// click next
				Thread.sleep(2000);
				ElementContainer.passwordInput(driver).sendKeys(data[1][5]);// enter password

				ElementContainer.nextButton(driver).click();// click next

				driver.switchTo().window(parentwindowid);
				smokeReport.flush();
				Thread.sleep(5000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
