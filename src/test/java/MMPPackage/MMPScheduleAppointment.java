package MMPPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MMPScheduleAppointment {
	WebDriver driver;
	WebDriverWait wait;

	MMPScheduleAppointment(WebDriver driver)
	{
		this.driver = driver;
	}
	public String scheduleAppointment(String doctorName, String symptoms) throws InterruptedException
	{
		wait = new WebDriverWait(driver,30);
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/ul/li[4]/a/span"))).click();
		driver.findElement(By.xpath("//div[3]/a/input")).click();
		driver.findElement(By.xpath("//ul[@class='list-inline list-users']//h4[text()='"+ doctorName +"']/../../../button[@id='opener']")).click();
		Thread.sleep(2000);
		driver.switchTo().frame("myframe");
		System.out.println("We are in the frame");

		System.out.println(driver.getTitle());
		driver.findElement(By.id("datepicker")).click();
		driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']//tr[5]/td[6]/a")).click();
		Thread.sleep(5000);
		Select time = new Select(driver.findElement(By.id("time")));
		Thread.sleep(2000);
		time.selectByVisibleText("11Am");
		driver.findElement(By.id("ChangeHeatName")).click();
		Thread.sleep(3000);
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//*[@id='sym']")).sendKeys(symptoms);
		driver.findElement(By.xpath("//div[3]/input")).sendKeys(Keys.ENTER);
		String doctor_Name = driver.findElement(By.xpath("//tr[1]/td[4]")).getText();
		return doctor_Name;
	
	}
}
