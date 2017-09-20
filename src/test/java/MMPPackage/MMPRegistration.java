package MMPPackage;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MMPRegistration {
	WebDriver driver;
	WebDriverWait wait; 

	MMPRegistration(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public String registerViaRegister()
	{
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@id='navigation']//a[text()='Patient Login']"))).click();
		driver.findElement(By.xpath("//*[@id='testimonials']//a[text()='Register']")).click();
		return driver.getTitle();
	}
	
	public String registerViaLogin() throws InterruptedException
	{
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@id='navigation']//a[text()='Patient Login']"))).click();
		driver.findElement(By.xpath("//*[@id='testimonials']/div/div/div/div[2]/a")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='login']/form/p[7]/a/input"))).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		return driver.getTitle();
	}

	public String[] registerUser(String firstname, String lastname, String datepicker, String license, String ssn,
			String state, String city, String address, String zipcode, String age, 
			String height, String weight, String email, String password, String username,
			String confirmpassword, String answer) throws InterruptedException
	{
		String[] PageTitles = new String[2];
		
		PageTitles[0] = driver.getTitle();
		
		driver.findElement(By.id("firstname")).sendKeys(firstname);
		driver.findElement(By.id("lastname")).sendKeys(lastname);
		driver.findElement(By.id("datepicker")).sendKeys(datepicker);
		driver.findElement(By.id("license")).sendKeys(license);
		driver.findElement(By.id("ssn")).sendKeys(ssn);
		driver.findElement(By.id("state")).sendKeys(state);
		driver.findElement(By.id("city")).sendKeys(city);
		driver.findElement(By.id("address")).sendKeys(address);
		driver.findElement(By.id("zipcode")).sendKeys(zipcode);
		driver.findElement(By.id("age")).sendKeys(age);
		driver.findElement(By.id("height")).sendKeys(height);
		driver.findElement(By.id("weight")).sendKeys(weight);
		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("confirmpassword")).sendKeys(confirmpassword);
		Select question = new Select(driver.findElement(By.id("security")));
		question.selectByVisibleText("what is your pet name");
		driver.findElement(By.id("answer")).sendKeys(answer);
		driver.findElement(By.xpath("//*[@id='container_body']//input[@value='Save']")).sendKeys(Keys.ENTER);
		
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

        Thread.sleep(5000);
        
        PageTitles[1] = driver.getTitle();
		return PageTitles;
	}
	
	public String[] registerBlank() throws InterruptedException
	{
		String[] PageTitles = new String[2];
		
		PageTitles[0] = driver.getTitle();
		
		driver.findElement(By.xpath("//*[@id='container_body']//input[@value='Save']")).sendKeys(Keys.ENTER);
		
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

        Thread.sleep(5000);
        
        PageTitles[1] = driver.getTitle();
		return PageTitles;
	}
}
	
