package MMPPackage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.support.ui.WebDriverWait;

public class MPPForgotPassword {

	public static void main(String[] args) throws InterruptedException {

		FirefoxDriver driver = new FirefoxDriver();
		System.setProperty("webdriver.firefox.driver", "geckodriver.exe");
//		WebDriverWait wait;
//		wait = new WebDriverWait(driver,30); 
		driver.get("http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//ul[@id='navigation']//a[text()='Patient Login']")).click();
		driver.findElement(By.xpath("//*[@id='testimonials']//a[text()='Login']")).click();

//		String aTitle = driver.getTitle();

	}
}
