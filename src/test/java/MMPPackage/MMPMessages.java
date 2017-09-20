package MMPPackage;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MMPMessages {
	WebDriver driver;
	WebDriverWait wait;
	String actual_message;

	MMPMessages(WebDriver driver)
	{
		this.driver = driver;
	}
	public String messages(String subject, String message) throws InterruptedException
	{
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Messages"))).click();
		driver.findElement(By.id("subject")).sendKeys(subject);
		driver.findElement(By.id("message")).sendKeys(message);
		driver.findElement(By.xpath("//div//tr[4]/td/input")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);

		Alert alert = driver.switchTo().alert();
		actual_message = alert.getText();
	    System.out.println(alert.getText());
		alert.accept();
		return actual_message;
	}
}
