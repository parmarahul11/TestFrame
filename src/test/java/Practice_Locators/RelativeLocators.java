package Practice_Locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import static org.openqa.selenium.support.locators.RelativeLocator.*;

public class RelativeLocators {

	@Test
	public void relativeLocators() {

		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/angularpractice/");

		// above relative locator
		WebElement nameField = driver.findElement(By.xpath("(//input[@name='name'])[1]"));

		System.out.println(driver.findElement(with(By.tagName("label")).above(nameField)).getText());

		// Below relative locator
		WebElement doblabel = driver.findElement(By.xpath("//label[@for='dateofBirth']"));
		driver.findElement(with(By.tagName("input")).below(doblabel)).click();

		// Left Relative locator
		WebElement checkboxLabel = driver
				.findElement(By.xpath("//label[text()='Check me out if you Love IceCreams!']"));

		driver.findElement(with(By.tagName("input")).toLeftOf(checkboxLabel)).click();

	}
}
