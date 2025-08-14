package cabBooking;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Helper.ExcelUtils;
import Helper.Logics;

//@Listeners(ExtentReportManager.class)
public class MakeMyTrip_TestNG {
	WebDriver driver;
	WebDriverWait wait;
	String parentWindow;
	int min = 0;
	ExcelUtils util = new ExcelUtils("C:\\Users\\2419352\\eclipse-workspace\\MakeMyTrip\\Excel\\Output.xlsx");
	Logics logic;
	cabPage cp;
	homePage hp;
	giftCard gc;
	cabFilterPage cfp;
	giftPage gp;
	HotelPage hotel;
	@BeforeClass
	public void setup() 
	{
		// launch browser
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		logic = new Logics(driver);
		cp = new cabPage(driver);
		hp = new homePage(driver);
		gc = new giftCard(driver);
		cfp = new cabFilterPage(driver);
		gp = new giftPage(driver);
		hotel = new HotelPage(driver);
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
//		POPUP
		driver.findElement(By.xpath("//span[@class=\"commonModal__close\"]")).click();
		hp.clickCab();
	}

	@Test(priority = 3)
	public void selectFromAndToCity() throws InterruptedException {
		// select from city
		cp.setFromCity();
		cp.setToCity("Manali");
		logic.dateHandler();
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
		catch (NoSuchElementException e) {}
		cabFilterPage cfp = new cabFilterPage(driver);
		cfp.clickSUV();
		Assert.assertTrue(driver.getTitle().contains("Online Cab Booking"), "failed");
	}

	@Test(priority = 5)
	public void lowestCharge() 
	{
		// return least charge SUV		
		min = logic.findMinValue(cfp.getCosts());
		System.out.println(min + " is the least");
		Assert.assertTrue(min > 0, "failed");
	}

	@Test(priority = 6)
	public void adultCount() {
		// return count of adults
		int cell = 0;
		cfp.clickHotel();
		hotel.clickRoomsAndGuests();
		hotel.selectAdults();
		for (WebElement i : hotel.fetchCountOfAdults()) {
			try 
			{
				util.setCellData("Output", cell, 1, i.getText());
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			System.out.println(i.getText());
			cell++;
		}
		Assert.assertTrue(hotel.fetchCountOfAdults().size() > 0, "Failed");
	}
	
//	cabNotFound_wrapper__azO8E

	@Test(priority = 7)
	public void validateResult() 
	{
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
		gp.chooseGift();
		// enter input details
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
