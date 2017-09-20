package MMPPackage;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MMPLoginPage {
	WebDriver driver;
	WebDriverWait wait;

	MMPLoginPage(WebDriver driver)
	{
		this.driver = driver;
	}
	public String[] Login(String username, String password) throws InterruptedException
	{
		String[] title = new String[2];
		
		driver.findElement(By.xpath("//ul[@id='navigation']//a[text()='Patient Login']")).click();
		driver.findElement(By.xpath("//*[@id='testimonials']//a[text()='Login']")).click();

		title[0] = driver.getTitle();

		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@id='login']//input[@name='submit']")).sendKeys(Keys.ENTER);
		Thread.sleep(5000);

		try
		{

			Alert alert = driver.switchTo().alert();
			System.out.println(alert.getText());
			alert.accept();
		}
		catch(Exception e)
		{

		}
		title[1] = driver.getTitle();
		 
		return title;
	}
	
	public String verifyLoginPageTitle() throws InterruptedException
	{
		driver.findElement(By.xpath("//ul[@id='navigation']//a[text()='Patient Login']")).click();
		Thread.sleep(3000);		
		driver.findElement(By.xpath("//*[@id='testimonials']//a[text()='Login']")).click();
		Thread.sleep(3000);
		return driver.getTitle();
	}
	
}
