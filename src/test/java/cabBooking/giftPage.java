package cabBooking;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class giftPage 
{
	WebDriver driver;
	public giftPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//img[@src = 'https://promos.makemytrip.com/appfest/2x/giftcard-12012023.png']")
	WebElement giftcard;
	
	
	
	public void chooseGift()
	{
		giftcard.click();
	}
	
}
