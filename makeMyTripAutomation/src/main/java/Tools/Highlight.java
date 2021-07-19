package Tools;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Highlight {
	public static void flash(WebElement element,String screenshotName)
	{
		WebDriver driver = DriverSetup.createWebDriver("Drivers");
		
		String bgColor = element.getCssValue("backgroundColor");
		
		changeColor("rgb(255,83,73)", element, driver);
		try {
			Thread.sleep(500);
			File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(src,new File(screenshotName+".png"));
		} catch(InterruptedException e) {
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		changeColor(bgColor, element, driver);
	
	}
	public static void changeColor(String color, WebElement element, WebDriver driver)
	{
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		js.executeScript("arguments[0].style.backgroundColor = '" +color+"'", element);
			
	}
}