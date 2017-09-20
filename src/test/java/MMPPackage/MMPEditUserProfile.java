package MMPPackage;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MMPEditUserProfile {
	WebDriver driver;
	WebDriverWait wait;

	MMPEditUserProfile(WebDriver driver)
	{
		this.driver = driver;
	}

	public String[] profileEdit(String newusername, String newlname) throws InterruptedException
	{
		String[] Names = new String[4];
		String expectedFname = newusername;
		String expectedLname = newlname;
		
		Names[0] = expectedFname;
		Names[1] = expectedLname;
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div//li[3]/a/span")).click();
		driver.findElement(By.id("Ebtn")).click();
		driver.findElement(By.id("fname")).clear();
		driver.findElement(By.id("fname")).sendKeys(newusername);
		driver.findElement(By.id("lname")).clear();
		driver.findElement(By.id("lname")).sendKeys(newlname);
		
		driver.findElement(By.id("Sbtn")).click();
		Thread.sleep(5000);

		Alert alert = driver.switchTo().alert();
		String message = alert.getText();
		alert.accept();
		System.out.println(message);
		Thread.sleep(5000);
		
		String editedFname = driver.findElement(By.id("fname")).getAttribute("value");
		String editedLname = driver.findElement(By.id("lname")).getAttribute("value");
		Names[2] = editedFname;
		Names[3] = editedLname;
		
		return Names;
	}
	
	public boolean invalidEditprofile(String invalidFname) throws InterruptedException{
		driver.findElement(By.linkText("Profile")).click();
		Thread.sleep(1000);

		driver.findElement(By.id("Ebtn")).click();
		//Thread.sleep(1000);
		driver.findElement(By.id("fname")).clear();
		driver.findElement(By.id("fname")).sendKeys(invalidFname);
		driver.findElement(By.id("Sbtn")).click();

		Thread.sleep(3000);
		String errordisplayed = driver.findElement(By.id("firsterr1")).getText();
		System.out.println("Error: " +errordisplayed);
		boolean error = driver.findElement(By.id("firsterr1")).isDisplayed();
		System.out.println(error);
		return error;
	}
}
