package MMPPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MMPViewHistory {

	WebDriver driver;
	WebDriverWait wait;
	String historyUrl = "http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/portal/viewhistory.php";

	public MMPViewHistory(WebDriver driver)
	{
		this.driver = driver;
	}
	public void viewHistory() throws InterruptedException
	{
		wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Profile"))).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText("View History")).click(); // Clicking View History

	}

	public boolean pastTransaction() 
	{
		driver.navigate().to(historyUrl);
		boolean Trans = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='wrapper']/p[1]/a/input"))).isDisplayed();// view past transactions
		return Trans;
	}

	public boolean pastAppointments() throws InterruptedException
	{
	   
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='wrapper']/p[2]/a/input"))).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		boolean patientPortal = driver.findElement(By.xpath("//div[1]/div[1]/div[2]/div[2]/div")).isDisplayed();
		return patientPortal;
		
	}
	
	public boolean pastDiagnosis() throws InterruptedException
	{
		Thread.sleep(3000);
		driver.navigate().to(historyUrl);
		driver.findElement(By.xpath("//*[@id='wrapper']/p[3]/a/input")).sendKeys(Keys.ENTER);
		boolean diagnosis = driver.findElement(By.xpath("//div[2]/div/div/div[1]/h3")).isDisplayed();
		return diagnosis;
	}
	public boolean pastPrescription() throws InterruptedException
	{
		Thread.sleep(3000);
		driver.navigate().to(historyUrl);
		driver.findElement(By.xpath("//*[@id='wrapper']/p[4]/a/input")).sendKeys(Keys.ENTER);;
		boolean prescription = driver.findElement(By.cssSelector(".panel-title")).isDisplayed();
		return prescription;
	}

}
