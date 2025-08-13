package cabBooking;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HotelPage 
{
	WebDriver driver;
	public HotelPage(WebDriver driver) 
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	
	@FindBy(id="guest")
	WebElement roomsAndGuests;
	@FindBy(xpath="//span[@data-testid = 'adult_count' ]")
	WebElement adults;
	@FindBy(xpath="//ul[@class ='gstSlct__list']/li")
	List<WebElement> countOfAdults;
	
	
	public void clickRoomsAndGuests()
	{
		roomsAndGuests.click();
	}
	
	public void selectAdults()
	{
		adults.click();
	}
	
	public List<WebElement> fetchCountOfAdults()
	{
		return countOfAdults;
	}

	
}
