package SmokeTesting;
import java.util.Iterator;
import java.util.Set;

import org.testng.annotations.Test;

import Executor.testExecutor;
import Tools.ElementContainer;

public class logingIn extends testExecutor{

	@Test(priority = 0)
	public void loginWithGmail() {
		try {
			ElementContainer.loginButton(driver).click();// login button
			Thread.sleep(500);
			ElementContainer.googleLogin(driver).click();// login by google

			Set<String> handle = driver.getWindowHandles();
			Iterator<String> it = handle.iterator();
			String parentwindowid = it.next();
			String childwindowid = it.next();
			driver.switchTo().window(childwindowid);
			Thread.sleep(1000);

			ElementContainer.emailInput(driver).sendKeys(data[1][4]);// enter email
			ElementContainer.nextButton(driver).click();// click next
			Thread.sleep(1000);
			ElementContainer.passwordInput(driver).sendKeys(data[1][5]);// enter password
			ElementContainer.nextButton(driver).click();// click next
			driver.switchTo().window(parentwindowid);
			Thread.sleep(10000);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 1)
	public void loginFromPopUp() {
		try {
			String popUp = ElementContainer.popupTitle(driver).getText();// popup title
			if (popUp.equalsIgnoreCase(data[1][6])) {
				ElementContainer.googleClick(driver).click();// login by google
				Set<String> handle = driver.getWindowHandles();
				Iterator<String> it = handle.iterator();
				String parentwindowid = it.next();
				String childwindowid = it.next();
				driver.switchTo().window(childwindowid);
				Thread.sleep(1000);

				ElementContainer.emailInput(driver).sendKeys(data[1][4]);// enter email
				ElementContainer.nextButton(driver).click();// click next
				Thread.sleep(1000);
				ElementContainer.passwordInput(driver).sendKeys(data[1][5]);// enter password
				ElementContainer.nextButton(driver).click();// click next
				driver.switchTo().window(parentwindowid);
				Thread.sleep(10000);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
