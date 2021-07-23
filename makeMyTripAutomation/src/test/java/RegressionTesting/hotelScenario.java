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
	
	ArrayList<String> adults;	
	
	@Test(priority=0)
	public void enterCity() {
		
	}
	
	@Test(priority=1)
	public void enterCheckInCheckOutDate() {
		
	}
	
	@Test(priority=2)
	public void verifyRoomsAndGuestButton() {
		ElementContainer.hotelTab(driver);// hotels tab
		Highlight.flash(ElementContainer.hotelTab(driver), driver,"HotelButton");
		ElementContainer.hotelTab(driver).click();
		adults = new ArrayList<String>();
		ElementContainer.guestsButton(driver);// guest button
		ElementContainer.guestsButton(driver).click();
	}

	@Test(priority=3)
	public void getAdultsList() throws InterruptedException {
		
		List<WebElement> adultsCount = ElementContainer.adultsList(driver);// no. of adults list

		for (WebElement c : adultsCount) {
			adults.add(c.getText());
		}
		regReport.createTest("Number of adults (Hotel Scenario)");
		Assert.assertEquals(adults.size() + ".0", data[1][2], "No. of adults is not correct");
		regReport.flush();

	}
	
	@Test(priority=4)
	public void verifyFreeBreakfastCheckBox() {
		
		
	}
	
}
