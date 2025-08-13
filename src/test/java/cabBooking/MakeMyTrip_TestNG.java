package cabBooking;
import java.io.File;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//@Listeners(ExtentReportManager.class)
public class MakeMyTrip_TestNG {
	WebDriver driver;
	WebDriverWait wait;
	String parentWindow;
	int min = 0;

	@BeforeClass
	public void setup() 
	{
<<<<<<< HEAD
		// launch browser
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
=======
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
>>>>>>> 4112947303617cf49966093233c387165f23fd87
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
		homePage hp = new homePage(driver);
//		POPUP
		driver.findElement(By.xpath("//span[@class=\"commonModal__close\"]")).click();
		hp.clickCab();
<<<<<<< HEAD
		

=======
>>>>>>> 4112947303617cf49966093233c387165f23fd87
	}

	@Test(priority = 3)
	public void selectFromAndToCity() throws InterruptedException {
		// select from city
		cabPage cp = new cabPage(driver);
		cp.setFromCity();
		cp.setToCity("Manali");
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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

		cp.clickSearch();
		Assert.assertTrue(driver.getTitle().contains("Online Cab Booking"), "failed");

	}
	
	@Test(priority = 4)
	public void selectSUV() 
	{
//		POPUP
		try {
			boolean popUp = false;
			popUp = driver.findElement(By.xpath("//img[@alt = 'Close']")).isDisplayed();
			driver.findElement(By.xpath("//img[@alt = 'Close']")).click();
			}
<<<<<<< HEAD
		catch (NoSuchElementException e) {
		}
=======
		catch (NoSuchElementException e) {}
>>>>>>> 4112947303617cf49966093233c387165f23fd87
		cabFilterPage cfp = new cabFilterPage(driver);
		cfp.clickSUV();
		Assert.assertTrue(driver.getTitle().contains("Online Cab Booking"), "failed");
	}

	@Test(priority = 5)
	public void lowestCharge() 
	{
		// return least charge SUV
		cabFilterPage cfp = new cabFilterPage(driver);
		int cost;
		int min = 0;
		for (WebElement e : cfp.getCosts()) 
		{	
			cost = Integer.parseInt(e.getText().replace("₹", "").replace(",", ""));
			if(min==0)
			{
				min=cost;
			}
			if (min > cost) 
			{
				min = cost;
			}
			System.out.println(e.getText());
		}
		System.out.println(min + " is the least");
		Assert.assertTrue(min > 0, "failed");
	}

	@Test(priority = 6)
	public void adultCount() {
		// return count of adults
		cabFilterPage cfp = new cabFilterPage(driver);
		cfp.clickHotel();
		
		HotelPage hotel = new HotelPage(driver);
		hotel.clickRoomsAndGuests();
		hotel.selectAdults();
		for (WebElement i : hotel.fetchCountOfAdults()) {
			System.out.println(i.getText());
		}
		Assert.assertTrue(hotel.fetchCountOfAdults().size() > 0, "Failed");
	}
	
//	cabNotFound_wrapper__azO8E

	@Test(priority = 7)
	public void validateResult() 
	{
		cabFilterPage cfp = new cabFilterPage(driver);
		driver.navigate().back();
		
		//navigate to more 
		cfp.movetomore();		
		//click on gift cards
		cfp.clickGiftcard();
		
//		POPUP
		try {
			boolean popUp = false;
			popUp = driver.findElement(By.xpath("//img[@alt = 'Close']")).isDisplayed();
			driver.findElement(By.xpath("//img[@alt = 'Close']")).click();

		} catch (NoSuchElementException e) {
		}
		// click on wedding gift card
		giftPage gp = new giftPage(driver);
		gp.chooseGift();
		// enter input details
		giftCard gc = new giftCard(driver);
		gc.setName("Sreekanth");
		gc.setMobile("9876543210");
		gc.setEmail("asgfveuyf");
		gc.buyNow();
		System.out.println(gc.getMessage());
		String s = "Please enter a valid Email id.";
		Assert.assertEquals(s, gc.getMessage(), "Text validation failed");
	}

	@Test(priority = 8)
	public void captureScreenshot() 
	{
		// capture screenshot
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "\\ScreenShot\\fullpage.png");
		source.renameTo(target);
		System.out.println("Screenshot captured");
		Assert.assertNotNull(source, "Screenshot not captured");
	}

	@AfterClass
	public void closeBrowser() 
	{
		// close browser
		driver.quit();
		System.out.println(" Browser closed");
	}

}
