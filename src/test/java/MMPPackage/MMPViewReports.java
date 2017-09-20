package MMPPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MMPViewReports {
	WebDriver driver;
	WebDriverWait wait;
	
	public MMPViewReports(WebDriver driver){
		this.driver=driver;
	}
	
	public boolean reports() throws InterruptedException
	{
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Profile"))).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText("View Reports")).click(); // Clicking View Reports
		boolean reports = driver.findElement(By.xpath(".//div[1]/h3[@class='panel-title']")).isDisplayed();
		return reports;
	}
}