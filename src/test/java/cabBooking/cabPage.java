package cabBooking;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class cabPage {

	WebDriver driver;
	public cabPage(WebDriver driver) 
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(id="fromCity")
	WebElement fromCity;
	
	@FindBy(xpath="//input[@placeholder='To']")
	WebElement toCity;
	
	@FindBy(xpath="//span[text() = 'Delhi']")
	WebElement fromSuggest;
	
	@FindBy(xpath="//span[text() = 'Manali, Himachal Pradesh, India']")
	WebElement toSuggest;	
	
	@FindBy(xpath="//a[text() = 'Search']")
	WebElement search;
	
	
	public void setFromCity()
	{
		fromCity.click();
		fromSuggest.click();
	}
	
	public void setToCity(String to) throws InterruptedException
	{
		toCity.sendKeys(to);
		toSuggest.click();
	}
	
	public void clickSearch()
	{
		search.click();
	}
	
	
}
