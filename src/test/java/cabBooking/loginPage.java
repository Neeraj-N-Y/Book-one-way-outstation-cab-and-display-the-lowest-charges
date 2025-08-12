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

public class loginPage {

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
				// *[@class='offerMainTitle']
//					System.setProperty("webdriver.chrome.driver",
//					"C:\\Users\\2419411\\Downloads\\chromedriver-win64 (1)\\chromedriver-win64\\chromedriver.exe");
				WebDriver driver = new ChromeDriver();
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
				int min = 0;

				driver.get("https://www.makemytrip.com/");
				driver.manage().window().maximize();
				String parentWindow = driver.getWindowHandle();
				driver.findElement(By.xpath("//span[@class=\"commonModal__close\"]")).click();

				driver.findElement(By.xpath("//span[@class = 'chNavIcon appendBottom2 chSprite chCabs inactive']")).click();
				driver.findElement(By.id("fromCity")).click();
//				
//				WebElement fromCity = driver.findElement(By.xpath("//input[@placeholder = \"From\"]"));
//				fromCity.click();
				driver.findElement(By.xpath("//span[text() = \"Delhi\"]")).click();
				driver.findElement(By.xpath("//input[@placeholder=\"To\"]")).sendKeys("Manali");
				driver.findElement(By.xpath("//span[text() = \"Manali, Himachal Pradesh, India\"]")).click();
		//driver.findElement(By.xpath("//span[@class = 'selectedTime']")).click();
		//driver.findElement(By.xpath("//span[text()='APPLY']")).click();
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='selectedDay']"))).click();
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@aria-label=\"Next Month\"]"))).click();
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@aria-label=\"Next Month\"]"))).click();
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@aria-label=\"Next Month\"]"))).click();
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@aria-label='Mon Dec 08 2025']"))).click();
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@data-cy='pickupTime']"))).click();
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='06  Hr']"))).click();
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='30  min']"))).click();
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='AM']"))).click();

				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='APPLY']"))).click();

				driver.findElement(By.xpath("//a[text() = 'Search']")).click();
				boolean popUp = false;
				try {
					popUp = driver.findElement(By.xpath("//img[@alt = 'Close']")).isDisplayed();
					driver.findElement(By.xpath("//img[@alt = 'Close']")).click();

				} catch (NoSuchElementException e) {
				}
				driver.findElement(By.xpath("//span[text()='SUV']")).click();
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
				System.out.println(min + "is the least");

				driver.findElement(By.xpath("//li[@class ='menu_Hotels']")).click();
				driver.findElement(By.id( "guest" )).click();
				driver.findElement(By.xpath("//span[@data-testid = 'adult_count' ]")).click();

				List<WebElement> adultCount = driver.findElements(By.xpath("//ul[@class =\"gstSlct__list\"]/li"));
				for (WebElement i : adultCount) {
					System.out.println(i.getText());
				}
				driver.navigate().back();
				Actions actions = new Actions(driver);
				WebElement more = driver.findElement(By.xpath("//span[text()='More' and @class = 'darkGreyText']"));
				actions.moveToElement(more).perform();
				driver.findElement(By.xpath("//a[@data-cy = 'submenu_Giftcards']")).click();
				try {
					popUp = driver.findElement(By.xpath("//img[@alt = 'Close']")).isDisplayed();
					driver.findElement(By.xpath("//img[@alt = 'Close']")).click();

				} catch (NoSuchElementException e) {
				}
				driver.findElement(By.xpath("//img[@src = 'https://promos.makemytrip.com/appfest/2x/giftcard-12012023.png']"))
						.click();
				driver.findElement(By.xpath("//input[@name = 'senderName']")).sendKeys("Sreekanth");
				driver.findElement(By.xpath("//input[@name = 'senderMobileNo']")).sendKeys("7680831614");
				driver.findElement(By.xpath("//input[@name = 'senderEmailId']")).sendKeys("asgfveuyf");
				driver.findElement(By.xpath("//button[text()='BUY NOW']")).click();
				TakesScreenshot ts = (TakesScreenshot) driver;
				File source = ts.getScreenshotAs(OutputType.FILE);
				File target = new File(System.getProperty("user.dir") + "\\ScreenShot\\fullpage.png");
				source.renameTo(target);
				System.out.println("Screenshot captured");
				String result = driver.findElement(By.xpath("//p[@class = 'red-text font11 append-top5']")).getText();
				System.out.println(result);

				driver.quit();
	}
}
