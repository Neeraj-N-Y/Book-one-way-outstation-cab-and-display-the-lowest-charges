package cabBooking;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class cabFilterPage 
{

	WebDriver driver;
	Actions actions;
	public cabFilterPage(WebDriver driver) 
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
		actions = new Actions(driver);
	}
	
	
	
	@FindBy(xpath="//span[text()='SUV']")
	WebElement SUV;
	
	@FindBy(xpath="//span[@class ='cabDetailsCard_price__SHN6W']")
	List<WebElement> costs;
	
	@FindBy(xpath="//li[@class ='menu_Hotels']")
	WebElement hotel;
	
	@FindBy(xpath="//span[text()='More' and @class = 'darkGreyText']")
	WebElement more;
	
	@FindBy(xpath="//a[@data-cy = 'submenu_Giftcards']")
	WebElement giftcards;
	
	
	
	
	
	
	
	public void clickSUV()
	{
		SUV.click();
	}
	
	public List<WebElement> getCosts()
	{
		return costs;
	}
	
	public void clickHotel()
	{
		hotel.click();
	}

	public void movetomore()
	{
		actions.moveToElement(more).perform();
	}
	
	public void clickGiftcard()
	{
		giftcards.click();
	}
}
