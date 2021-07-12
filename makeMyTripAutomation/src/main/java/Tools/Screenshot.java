package Tools;
import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshot {
public static void captureScreenshot(String screenshotName, WebDriver driver) {
	try
	{
	

	File screenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	
	FileUtils.copyFile(screenshot, new File("C:\\Users\\sachi\\eclipse-workspace\\makeMyTripAutomation\\Screenshots\\"+screenshotName+".png"));
	}
	catch (Exception e)
	{
		System.out.println("Exception while taking screenshots " +e.getMessage());
	}
}
}
