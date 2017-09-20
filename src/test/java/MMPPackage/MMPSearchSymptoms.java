/**
 * 
 */
package MMPPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class MMPSearchSymptoms {

	WebDriver driver;
	public MMPSearchSymptoms(WebDriver driver){
		this.driver=driver;
	}
	public boolean Symptoms(String symptoms) throws InterruptedException{
		boolean solution = false;
		Thread.sleep(5000);
		driver.findElement(By.linkText("Search Symptoms")).click();
		driver.findElement(By.id("search")).sendKeys(symptoms);
		driver.findElement(By.name("submit")).sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		try
		{
		    solution = driver.findElement(By.xpath(".//*[@id='show']/table/tbody/tr/td[3]")).isDisplayed();
			String soln = driver.findElement(By.xpath(".//*[@id='show']/table/tbody/tr/td[3]")).getText();
			String diag = driver.findElement(By.xpath("//*[@id='show']/table/tbody/tr/td[2]")).getText();
			System.out.println("Diagnosis for the symptoms : " + diag);
			System.out.println("Solution for the symptoms are : " + soln);
		}
		catch(Exception e)
		{
			System.out.println("No Diagnosis and Solutions found for symptom");
			solution = true;
		}
		return solution;
		
		
	}
}