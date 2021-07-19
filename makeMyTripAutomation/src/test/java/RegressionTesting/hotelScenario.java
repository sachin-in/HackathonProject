package RegressionTesting;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Executor.testExecutor;
import Tools.ElementContainer;
import Tools.Highlight;


public class hotelScenario extends testExecutor{
	
	@Test(priority=13)
	public void verifyHotelButton() {
		Highlight.flash(ElementContainer.hotelTab(), driver);
		String buttonName = ElementContainer.hotelTab().getText();
		smokeReport.createTest("Hotel button");
		Assert.assertEquals(buttonName, "Hotels","Hotels button not found");
		smokeReport.flush();

	}
	@Test(priority=16)
	public void verifyRoomsAndGuestButton() {

	}

	@Test(priority=0)
	public void getAdultsList() throws InterruptedException {

		ElementContainer.hotelTab();// hotels tab
		Highlight.flash(ElementContainer.hotelTab(), driver);
		ElementContainer.hotelTab().click();
		ArrayList<String> adults = new ArrayList<String>();
		ElementContainer.guestsButton();// guest button
		Highlight.flash(ElementContainer.guestsButton(), driver);
		ElementContainer.guestsButton().click();
		List<WebElement> adultsCount = ElementContainer.adultsList();// no. of adults list
		for (WebElement c : adultsCount) {
			adults.add(c.getText());
		}
		regReport.createTest("Number of adults (Hotel Scenario)");
		Assert.assertEquals(adults.size() + ".0", data[1][2], "No. of adults is not correct");
		regReport.flush();
		Thread.sleep(2000);
		driver.navigate().to(data[1][0]);
	}
}
