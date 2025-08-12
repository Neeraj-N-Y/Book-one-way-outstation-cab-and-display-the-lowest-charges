package cabBooking;
import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MakeMyTrip_TestNG {
	WebDriver driver;
	WebDriverWait wait;
	String parentWindow;
	int min = 0;

	@BeforeClass
	public void setup() {
		// launch browser

		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));

		driver.manage().window().maximize();

	}

	@Test(priority = 1)
	public void makeMyTrip() {
		// navigate to makeMyTrip website
		driver.get("https://www.makemytrip.com/");
		parentWindow = driver.getWindowHandle();
		System.out.println(driver.getTitle());
		Assert.assertTrue(driver.getTitle().contains("MakeMyTrip"));

	}

	@Test(priority = 2)
	public void navigateToCabs() {
		//navigate to cab tab in menu bar
		driver.findElement(By.xpath("//span[@class=\"commonModal__close\"]")).click();

		driver.findElement(By.xpath("//span[@class = 'chNavIcon appendBottom2 chSprite chCabs inactive']")).click();

	}

	@Test(priority = 3)
	public void selectFromAndToCity() {
		// select from city
		driver.findElement(By.xpath("//input[@id = \"fromCity\"]")).click();
//		
//		WebElement fromCity = driver.findElement(By.xpath("//input[@placeholder = \"From\"]"));
//		fromCity.click();
		driver.findElement(By.xpath("//span[text() = \"Delhi\"]")).click();
		//select to city
		driver.findElement(By.xpath("//input[@placeholder=\"To\"]")).sendKeys("Manali");
		driver.findElement(By.xpath("//span[text() = \"Manali, Himachal Pradesh, India\"]")).click();
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		//select date of traveling
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='selectedDay']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@aria-label=\"Next Month\"]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@aria-label=\"Next Month\"]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@aria-label=\"Next Month\"]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@aria-label='Mon Dec 08 2025']"))).click();
		// select pickup time
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@data-cy='pickupTime']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='06  Hr']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='30  min']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='AM']"))).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='APPLY']"))).click();
		driver.findElement(By.xpath("//a[text() = 'Search']")).click();
		Assert.assertTrue(driver.getTitle().contains("Online Cab Booking"), "failed");

	}

	@Test(priority = 4)
	public void selectSUV() {
		// select car type SUV
		try {
			boolean popUp = false;
			popUp = driver.findElement(By.xpath("//img[@alt = 'Close']")).isDisplayed();
			driver.findElement(By.xpath("//img[@alt = 'Close']")).click();

		} catch (NoSuchElementException e) {
		}
		driver.findElement(By.xpath("//span[text()='SUV']")).click();
		Assert.assertTrue(driver.getTitle().contains("Online Cab Booking"), "failed");
	}

	@Test(priority = 5)
	public void lowestCharge() {
		// return least charge SUV

		List<WebElement> price = new ArrayList<>();
		price = driver.findElements(By.xpath("//span[@class =\"cabDetailsCard_price__SHN6W\"]"));
		for (WebElement e : price) {
			WebElement first = price.get(0);
			min = Integer.parseInt(first.getText().replace("₹", "").replace(",", ""));
			System.out.println(e.getText().replace("₹", "").replace(",", ""));
			int i = Integer.parseInt(e.getText().replace("₹", "").replace(",", ""));
			if (min > i) {
				min = i;
			}
		}
		System.out.println(min + " is the least");
		Assert.assertTrue(min > 0, "failed");

	}

	@Test(priority = 6)
	public void adultCount() {
		// return count of adults

		driver.findElement(By.xpath("//li[@class ='menu_Hotels']")).click();
		driver.findElement(By.xpath("//input[@id ='guest' ]")).click();
		driver.findElement(By.xpath("//span[@data-testid = 'adult_count' ]")).click();

		List<WebElement> adultCount = driver.findElements(By.xpath("//ul[@class =\"gstSlct__list\"]/li"));
		for (WebElement i : adultCount) {
			System.out.println(i.getText());
		}
		Assert.assertTrue(adultCount.size() > 0, "Failed");
	}

	@Test(priority = 7)
	public void validateResult() {
		driver.navigate().back();
		Actions actions = new Actions(driver);
		//navigate to more 
		WebElement more = driver.findElement(By.xpath("//span[text()='More' and @class = 'darkGreyText']"));
		actions.moveToElement(more).perform();
		//click on gift cards
		driver.findElement(By.xpath("//a[@data-cy = 'submenu_Giftcards']")).click();
		try {
			boolean popUp = false;
			popUp = driver.findElement(By.xpath("//img[@alt = 'Close']")).isDisplayed();
			driver.findElement(By.xpath("//img[@alt = 'Close']")).click();

		} catch (NoSuchElementException e) {
		}
		// click on wedding gift card
		driver.findElement(By.xpath("//img[@src = 'https://promos.makemytrip.com/appfest/2x/giftcard-12012023.png']"))
				.click();
		// enter input details
		driver.findElement(By.xpath("//input[@name = 'senderName']")).sendKeys("Sreekanth");
		driver.findElement(By.xpath("//input[@name = 'senderMobileNo']")).sendKeys("7680831614");
		driver.findElement(By.xpath("//input[@name = 'senderEmailId']")).sendKeys("asgfveuyf");
		driver.findElement(By.xpath("//button[text()='BUY NOW']")).click();
		String result = driver.findElement(By.xpath("//p[@class = 'red-text font11 append-top5']")).getText();
		String s = "Please enter a valid Email id.";
		System.out.println(result);
		Assert.assertEquals(s, result, "Text validation failed");
	}

	@Test(priority = 8)
	public void captureScreenshot() {
		// capture screenshot
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "\\ScreenShot\\fullpage.png");
		source.renameTo(target);
		System.out.println("Screenshot captured");
		Assert.assertNotNull(source, "Screenshot not captured");
	}

	@AfterClass
	public void closeBrowser() {
		// close browser
		driver.quit();
		System.out.println(" Browser closed");
	}

}
