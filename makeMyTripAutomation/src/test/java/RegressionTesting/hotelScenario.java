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

	@Test
	public void getAdultsList() {

		WebElement hotels = ElementContainer.hotelTab(driver);// hotels tab
		Highlight.flash(hotels, driver);
		hotels.click();
		ArrayList<String> adults = new ArrayList<String>();
		WebElement guest = ElementContainer.guestsButton(driver);// guest button
		Highlight.flash(guest, driver);
		guest.click();
		List<WebElement> adultsCount = ElementContainer.adultsList(driver);// no. of adults list
		for (WebElement c : adultsCount) {
			adults.add(c.getText());
		}
		Assert.assertEquals(adults.size() + ".0", data[1][2], "No. of adults is not correct");
	}
}
