package SmokeTesting;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import Executor.testExecutor;
import Tools.ElementContainer;
import Tools.Highlight;
import Tools.Screenshot;

public class smokeTest extends testExecutor {

	@Test(priority = 0)
	public void getHomePageSignature() {
		String title = ElementContainer.homepageSignature().getText();// Web Page signature
		Assert.assertEquals(title, data[1][1], "Title is not correct");

		Highlight.flash(ElementContainer.homepageSignature(driver),"getHomePageSignature");
		 

	}

	@Test(priority = 1)
	public void verifyCountry() {

		Highlight.flash(ElementContainer.countryVerify(driver),"verifyCountry");
		String name = ElementContainer.countryVerify(driver).getText();
		Assert.assertEquals(name, "IN | ENG | INR", "India is not selected");
		Highlight.flash(ElementContainer.countryVerify(driver),"Selected Country");


	}

	@Test(priority = 2)
	public void verifyLoginButton() {

		Highlight.flash(ElementContainer.loginButton(driver), "verifyLoginButton");
		String buttonName = ElementContainer.loginButton(driver).getText();

		Assert.assertEquals(buttonName, "Login or Create Account", "Button is not found");

	}

	@Test(priority = 3)
	public void loginWithGmail() {
		try {

			Highlight.flash(ElementContainer.loginButton(driver), "LoginButton");
			ElementContainer.loginButton(driver).click();// login button
			Thread.sleep(500);
			Highlight.flash(ElementContainer.googleLogin(driver), "googleLogin");
			ElementContainer.googleLogin(driver).click();// login by google


			Set<String> handle = driver.getWindowHandles();
			Iterator<String> it = handle.iterator();
			String parentwindowid = it.next();
			String childwindowid = it.next();
			driver.switchTo().window(childwindowid);
			Thread.sleep(1000);


			ElementContainer.emailInput(driver).sendKeys(data[1][4]);// enter email
			Highlight.flash(ElementContainer.nextButton(driver),"emailInput");
			ElementContainer.nextButton(driver).click();// click next

			Thread.sleep(1000);
			ElementContainer.passwordInput().sendKeys(data[1][5]);// enter password
			Thread.sleep(2000);

			Highlight.flash(ElementContainer.nextButton(driver), "passwordInput");
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
			String popUp = ElementContainer.popupTitle().getText();// popup title
			if (popUp.equalsIgnoreCase(data[1][6])) {

				Highlight.flash(ElementContainer.googleClick(driver),"loginFromPopUp");
				ElementContainer.googleClick(driver).click();// login by google

				Set<String> handle = driver.getWindowHandles();
				Iterator<String> it = handle.iterator();
				String parentwindowid = it.next();
				String childwindowid = it.next();
				driver.switchTo().window(childwindowid);
				Thread.sleep(1000);


				ElementContainer.emailInput(driver).sendKeys(data[1][4]);// enter email
				Highlight.flash(ElementContainer.nextButton(driver),"emailInput");
				ElementContainer.nextButton(driver).click();// click next
				Thread.sleep(2000);
				ElementContainer.passwordInput(driver).sendKeys(data[1][5]);// enter password
				Highlight.flash(ElementContainer.nextButton(driver),"passwordInput");
				ElementContainer.nextButton(driver).click();// click next

				driver.switchTo().window(parentwindowid);
				Thread.sleep(5000);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
