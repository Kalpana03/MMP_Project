package MMPPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MMPFees {

	WebDriver driver;
	WebDriverWait wait;

	public MMPFees(WebDriver driver){
		this.driver= driver;
	}

	public boolean fees() 
	{
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Fees"))).click();
		String fee =  driver.findElement(By.xpath("//div/div[2]/p[3]")).getText();
		System.out.println("Outstanding fee is "+ fee);
		boolean fees = driver.findElement(By.xpath("//div/div[2]/p[3]")).isDisplayed();
		return fees;
	}
}