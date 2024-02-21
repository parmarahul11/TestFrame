package Practice_Locators;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Multipe_Windows {

	@Test
	public void MultipleMethods() throws IOException {

		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/angularpractice/");
		driver.switchTo().newWindow(WindowType.TAB);
		Set<String> windowID = driver.getWindowHandles();
		Iterator<String> it = windowID.iterator();
		String parentID = it.next();
		String childID = it.next();
		driver.switchTo().window(childID);
		driver.get("https://rahulshettyacademy.com");
		String courseName = driver.findElements(By.cssSelector("a[href*='https://courses.rahulshettyacademy.com/p/']")).get(1).getText();
		driver.switchTo().window(parentID);
		WebElement nameField = driver.findElement(By.xpath("(//input[@name='name'])[1]"));
		nameField.sendKeys(courseName);
		File file =nameField.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file,new File("fieldSS.png"));
		
		// Get Height and Width of element
		System.out.println("Height of Element is: " + nameField.getRect().getHeight());
		System.out.println("Width of Element is: " + nameField.getRect().getWidth());
	}

}
