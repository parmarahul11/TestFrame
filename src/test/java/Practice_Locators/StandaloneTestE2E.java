package Practice_Locators;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandaloneTestE2E {
	@Test
	public void eCommE2E() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		String productName = "ADIDAS ORIGINAL";
		driver.manage().window().maximize();
		driver.findElement(By.id("userEmail")).sendKeys("testerid0009@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Test@100");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

		WebElement searchprod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);

		searchprod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));

		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();

		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));

		Boolean match = cartProducts.stream().anyMatch(product -> product.getText().contentEquals(productName));
		Assert.assertTrue(match);

		driver.findElement(By.cssSelector(".totalRow button")).click();

		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("Ind");
//	List<WebElement> dropdownValues = driver.findElements(By.xpath("//section[@class='ta-results list-group ng-star-inserted']/button"));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".ta-results")));
		// dropdownValues.stream().filter(selectValue ->
		// selectValue.getText().equalsIgnoreCase("India"));
		driver.findElement(By.xpath("(//section[@class='ta-results list-group ng-star-inserted']/button)[2]")).click();
		Thread.sleep(3000);
//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@class,'.action__submit')]")));
		//Actions a = new Actions(driver);
		//a.moveToLocation(1061, 600).click().build().perform();
		driver.findElement(By.cssSelector(".action__submit")).click();
	//	driver.close();
		

	}

}
