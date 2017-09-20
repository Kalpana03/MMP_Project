package MMPPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MMPInformation {
	WebDriver driver;

	public MMPInformation(WebDriver driver)
	{
		this.driver = driver;
	}

	public boolean verifyInformation() throws InterruptedException
	{
		boolean check;
		
		WebDriverWait wait = new WebDriverWait(driver,50);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/ul/li[5]/a/span"))).click();
					
		if(driver.findElement(By.xpath("//div[2]/div/div/div[2]")).isDisplayed() == true)
				
		{
			check = true;
		}
		else 
			check = false;
		return check;

	}
	

}