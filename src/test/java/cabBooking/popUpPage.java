package cabBooking;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class popUpPage 
{
	WebDriver driver;
	
	
	public popUpPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//span[@class='commonModal__close']")
	WebElement popup1;
	
	@FindBy(xpath="")
	WebElement popup2;
	
	
}
