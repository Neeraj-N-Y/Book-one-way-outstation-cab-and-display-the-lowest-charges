package Helper;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Logics 
{

	WebDriver driver;
	
	public Logics(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public int findMinValue(List<WebElement> costs) 
	{
		ExcelUtils util = new ExcelUtils("C:\\Users\\2419352\\eclipse-workspace\\MakeMyTrip\\Excel\\Output.xlsx");
		int cost;
		int cell = 0;
		int min=0;
		try {
			util.setCellData("Output", cell,0, "Cost");
			cell++;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for (WebElement e : costs) 
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
			try {
				util.setCellData("Output",cell,0,e.getText());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println(e.getText());
			cell++;
		}
		return min;
	}
	
	public void dateHandler()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
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

	}
	
	
	

}
