package cabBooking;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class giftCard 
{

	WebDriver driver;
	
	public giftCard(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	
	
	@FindBy(xpath = "//input[@name = 'senderName']")
	WebElement name;
	
	@FindBy(xpath = "//input[@name = 'senderMobileNo']")
	WebElement mobile;

	@FindBy(xpath = "//input[@name = 'senderEmailId']")
	WebElement email;

	@FindBy(xpath = "//button[text()='BUY NOW']")
	WebElement submitbtn;	
	
	@FindBy(xpath = "//p[@class = 'red-text font11 append-top5']")
	WebElement message;
	
	
	
	public void setName(String username) 
	{
		name.sendKeys(username);
	}

	public void setMobile(String usermobile) 
	{
		mobile.sendKeys(usermobile);
	}

	public void setEmail(String useremail) 
	{
		email.sendKeys(useremail);
	}

	public void buyNow() 
	{
		submitbtn.click();
	}
	
	public String getMessage() 
	{
		return message.getText();
	}

	
}
