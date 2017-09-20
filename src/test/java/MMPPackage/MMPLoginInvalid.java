package MMPPackage;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MMPLoginInvalid {
	WebDriver driver;

	public MMPLoginInvalid(WebDriver driver){
		this.driver = driver;
	}
	public boolean Login() throws InterruptedException{
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))).sendKeys("T_n1");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys("123");

		driver.findElement(By.name("submit")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		boolean foundalert  = false;
		try
		{
			wait.until(ExpectedConditions.alertIsPresent());
			foundalert = true;
		}

		catch (Exception e){
			e.getMessage();
			foundalert = false;
		}

		Alert alrt = driver.switchTo().alert();
		String message = alrt.getText();
		alrt.accept();
		System.out.println(message);
		return foundalert;
	}

	public String blanklogin() throws InterruptedException{
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).clear();

		driver.findElement(By.name("submit")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		String Title = driver.getTitle();
		return Title;
	}
}