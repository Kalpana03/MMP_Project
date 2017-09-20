package MMPPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MMPProfileViewHistory {

	WebDriver driver;

	public MMPProfileViewHistory(WebDriver driver)
	{
		this.driver = driver;
	}
	public void ViewHistory() throws InterruptedException
	{
		driver.findElement(By.linkText("Profile")).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText("View History")).click(); // Clicking View history

	}

	public boolean PastTransaction(){
		boolean Trans = driver.findElement(By.xpath(".//*[@id='wrapper']/p[1]/a/input")).isEnabled();// view past transactions
		return Trans;
	}

	public boolean appointment() throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='wrapper']/p[2]/a/input"))).click();
		//driver.findElement(By.xpath(".//*[@id='wrapper']/p[2]/a/input")).click();
		Thread.sleep(3000);
		boolean date = driver.findElement(By.xpath("//div[1]/div[1]/div[2]/div[2]/div")).isDisplayed();
		//	String doctor = driver.findElement(By.xpath(".//tr[1]/td[1]")).getText();
		//System.out.println("doctor" +doctor);
		return date;
	}

	public boolean Diagnosis()
	{
		driver.findElement(By.xpath(".//*[@id='wrapper']/p[3]/a/input")).click();
		boolean diagnosis = driver.findElement(By.xpath(".//div[2]/div/div/div[1]/h3")).isDisplayed();
		return diagnosis;
	}

	public boolean Prescription()
	{
		driver.findElement(By.xpath(".//*[@id='wrapper']/p[4]/a/input")).click();
		boolean prescription = driver.findElement(By.cssSelector(".panel-title")).isDisplayed();
		return prescription;
	}

}