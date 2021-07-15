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

		ElementContainer.hotelTab(driver);// hotels tab
		Highlight.flash(ElementContainer.hotelTab(driver), driver);
		ElementContainer.hotelTab(driver).click();
		ArrayList<String> adults = new ArrayList<String>();
		ElementContainer.guestsButton(driver);// guest button
		Highlight.flash(ElementContainer.guestsButton(driver), driver);
		ElementContainer.guestsButton(driver).click();
		List<WebElement> adultsCount = ElementContainer.adultsList(driver);// no. of adults list
		for (WebElement c : adultsCount) {
			adults.add(c.getText());
		}
		Assert.assertEquals(adults.size() + ".0", data[1][2], "No. of adults is not correct");
	}
}
