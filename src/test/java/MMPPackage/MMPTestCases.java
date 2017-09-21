package MMPPackage;

import org.testng.annotations.Test;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

//import org.apache.xalan.xsltc.compiler.sym;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

public class MMPTestCases { 

	public WebDriver driver;

	MMPRegistration registerPage;
	MMPLoginPage loginPage;
	MMPEditUserProfile profilePage;
	MMPScheduleAppointment appointmentPage;
	MMPInformation informationPage;
	MMPFees feesPage;
	MMPViewReports viewReportsPage;
	MMPViewHistory viewHistoryPage;
	MMPMessages messagesPage;
	MMPSearchSymptoms searchsymPage;
	MMPLogoutPage logoutPage;

	String data[][] = new String [1][17];
	String expected_Message = "Messages Successfully sent.";

	@Parameters({"url"})
	@BeforeTest(description = "TC70 : Lauching MMP Url")
	public void setUp(String url)
	{
		System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
		driver = new FirefoxDriver();
		//System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		//driver = new ChromeDriver();

		driver.get("http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		System.out.println("In BeforeTest");
	}

	@AfterTest(enabled=true, description = "TC91: User logged out of the Account")
	public void userLogout(){

		logoutPage = new MMPLogoutPage(driver);

		try
		{
			logoutPage.logout();
			System.out.println("User logged off Successfully!!!");			
		}
		catch(Exception e)
		{
			System.out.println("User logout failed");
		}
	}

	@Test(enabled=false, priority=1, description="TC74: Verify Registration Page Via Register")
	public void verifyRegisterPage()
	{
		registerPage = new MMPRegistration(driver);
		String pgTitle = registerPage.registerViaRegister();

		boolean registerPageTitle = pgTitle.equals("Registration");

		validateCondition(registerPageTitle,"Error opening Registration page","Registration page opened successfully");
	}

	@Test(enabled=false, priority=1, description="TC75: Verify Registration Page Via Login")
	public void verifyRegisterPageViaLogin() throws InterruptedException
	{
		registerPage = new MMPRegistration(driver);
		String pgTitle = registerPage.registerViaLogin();

		boolean registerPageTitle = pgTitle.equals("Registration");

		validateCondition(registerPageTitle,"Error opening Registration page","Registration page opened successfully");
	}

	@Test(enabled = false, priority=1, dataProvider = "DPRegister", description = "TC76: User Registartion with Valid values")
	public void userRegisterValid(String firstname, String lastname, String datepicker, String license, String ssn, 
			String state, String city, String address, String zipcode, 
			String age, String height, String weight, String email, 
			String password, String username, String confirmpassword, String answer) throws InterruptedException
	{
		registerPage = new MMPRegistration(driver);

		registerPage.registerViaRegister();

		String[] Title = new String[2];

		Title = registerPage.registerUser(firstname, lastname, datepicker, 
				license, ssn, state, city, address, zipcode, age, height, 
				weight, email, password, username, confirmpassword, answer);

		boolean registered = !Title[0].equals(Title[1]);

		validateCondition(registered,"Error Registring the user","User Registered successfully");
	}

	@Test(enabled = false, priority=1, dataProvider = "DPRegister", description = "TC77: User Registartion with InValid values")
	public void userRegisterInValid(String firstname, String lastname, String datepicker, String license, String ssn, 
			String state, String city, String address, String zipcode, 
			String age, String height, String weight, String email, 
			String password, String username, String confirmpassword, String answer) throws InterruptedException
	{
		registerPage = new MMPRegistration(driver);

		registerPage.registerViaRegister();

		String[] Title = new String[2];

		Title = registerPage.registerUser(firstname, lastname, datepicker, 
				license, ssn, state, city, address, zipcode, age, height, 
				weight, email, password, username, confirmpassword, answer);

		try
		{
			Assert.assertEquals(Title[0], Title[1], "User got registered successfully!!!");

			List<WebElement> Fields = driver.findElements(By.xpath("//p[contains(@style,'display: block;')]"));
			List<WebElement> Fields_info = driver.findElements(By.xpath("//p[contains(@style,'display: block;')]/../input"));

			System.out.println("The number of fields with invalid data are :" + Fields.size());
			System.out.println("The names of the fields are :\n");

			for(int i=0;i<Fields_info.size();i++)
			{
				System.out.println(Fields_info.get(i).getAttribute("id"));
			}

			System.out.println("Please veryfy the fields and reset with valid data");
		}
		catch(AssertionError e)
		{	
			System.out.println("User got registered successfully!!!");			
		}
	}	

	@Test(enabled = false, priority=1, description = "TC78: User Registartion with blank values")
	public void userRegisterBlank() throws InterruptedException
	{
		registerPage = new MMPRegistration(driver);

		registerPage.registerViaRegister();

		String[] Title = new String[2];

		Title = registerPage.registerBlank();

		try
		{
			Assert.assertEquals(Title[0], Title[1], "User got registered successfully!!!");

			List<WebElement> Fields = driver.findElements(By.xpath("//p[contains(@style,'display: block;')]"));
			List<WebElement> Fields_info = driver.findElements(By.xpath("//p[contains(@style,'display: block;')]/../input"));

			System.out.println("The number of fields with invalid data are :" + Fields.size());
			System.out.println("The names of the fields are :\n");

			for(int i=0;i<Fields_info.size();i++)
			{
				System.out.println(Fields_info.get(i).getAttribute("id"));
			}

			System.out.println("Please veryfy the fields and reset with valid data");
		}
		catch(AssertionError e)
		{	
			System.out.println("User got registered successfully!!!");			
		}
	}		

	@DataProvider(name="DPRegister")	
	public String[][] readXlsFile() throws BiffException, IOException 
	{
		File Xlsfile = new File("C://Kalpana//Selenium//HealthCare//Reg_Data.xls");
		Workbook wb = Workbook.getWorkbook(Xlsfile);
		Sheet sheet = wb.getSheet(0);
		int rows = sheet.getRows();
		int cols  = sheet.getColumns();
		for(int i=0;i<rows;i++)
		{
			for(int j=0;j<cols;j++)
			{
				Cell cell = sheet.getCell(j, i);
				String str= cell.getContents();
				data[i][j] = str;
				//				System.out.println(data[i][j]);
			}
		}
		return data;
	}
	@Test(enabled=false,priority = 2,description = "TC87: Validate User Login Page")	
	public void validateLoginPage() throws InterruptedException
	{
		loginPage = new MMPLoginPage(driver);

		String pgTitle = loginPage.verifyLoginPageTitle();

		boolean validLoginTitle = pgTitle.equals("Login");

		validateCondition(validLoginTitle,"Error opening Login Page","Login Page Succesfully opened");
	}

	@Parameters({"username","password"})
	@Test(enabled=true,priority = 3,description="TC88: Login_valid")
	public void Login(String username, String password) throws InterruptedException
	{
		loginPage = new MMPLoginPage(driver);
		loginPage.verifyLoginPageTitle();

		MMPLogin login = new MMPLogin(this.driver);
		String actual = login.Login(username,password);
		System.out.println(actual);
		String expected = username;

		boolean validUser = actual.equals(expected);

		validateCondition(validUser,"Error Logging in","User Successfully logged in");
	}

	@Test(enabled=false,priority = 3,description="TC89: Invalid Login")
	public void invalidLogin() throws InterruptedException
	{
		loginPage = new MMPLoginPage(driver);
		loginPage.verifyLoginPageTitle();

		MMPLoginInvalid login= new MMPLoginInvalid(driver);
		boolean actual = login.Login();

		System.out.println("Alert message "+actual);

		validateCondition(actual,"User Successfully logged in","Error Logging in");
	} 

	@Parameters({"LoginTitle"})
	@Test(enabled=false,priority = 3,description="TC90: Blank Login")
	public void blankLogin(String LoginTitle) throws InterruptedException
	{
		loginPage = new MMPLoginPage(driver);
		loginPage.verifyLoginPageTitle();

		MMPLoginInvalid login= new MMPLoginInvalid(this.driver);
		String actual  = login.blanklogin();
		String expected = LoginTitle;//Login

		boolean validateLoginTitle = actual.equals(expected);

		validateCondition(validateLoginTitle,"User Successfully logged in","Error Logging in");		
	} 			

	@Parameters({"Age"})
	@Test(enabled=true,priority = 4,description = "Editing information in User Profile",dependsOnMethods = {"Login"})
	public void EditProfile(String Age) throws InterruptedException
	{

		MMPEditProfile edit= new MMPEditProfile(driver);
		boolean actual = edit.Editprofile(Age);
		validateCondition(actual,"Error updating profile","User Profile updated Successfully");
	} 

	@Test(enabled=false, priority=4, description="TC97: EditProfilePage_blank",dependsOnMethods = {"Login"})
	public void blankEditProfile() throws InterruptedException
	{

		MMPEditProfile edit= new MMPEditProfile(this.driver);
		boolean actual = edit.Editprofileblank();
		validateCondition(actual,"User Profile updated Successfully", "Error updating profile");
	} 

	@Parameters({"invalidFname"})
	@Test(enabled=false, priority=4, description="TC96: EditProfilePage_invalid",dependsOnMethods = {"Login"})
	public void invalidEditProfile(String invalidFname) throws InterruptedException
	{
		profilePage = new MMPEditUserProfile(driver);
		boolean actual = profilePage.invalidEditprofile(invalidFname);
		validateCondition(actual,"User Profile updated Successfully", "Error updating profile");
	} 

	@Parameters({"doctorname", "symptoms"})
	@Test(enabled=true,priority = 5,description = "TC98: User Scheduling an Appointment",dependsOnMethods = {"Login"})
	public void patientAppointment(String doctorname, String symptoms) throws InterruptedException 
	{
		appointmentPage = new MMPScheduleAppointment(driver);
		String doctor_Name = appointmentPage.scheduleAppointment(doctorname, symptoms);
		boolean validateDoctor = doctorname.equals("Dr."+doctor_Name);
		validateCondition(validateDoctor,"Error Scheduling Appointment","Appointment booked with " + doctorname);
	}

	@Test(enabled=true,priority=6, description = "TC80: Doctor's Information",dependsOnMethods = {"Login"})
	public void doctorInformation() throws InterruptedException
	{		
		informationPage = new MMPInformation(driver);
		boolean check = informationPage.verifyInformation();

		validateCondition(check,"User not able to see MMP information", "User is able to see MMP information");	}

	@Parameters({"subject_text", "message_text"})
	@Test(enabled=true, priority=7, description="TC84: User is able to send Messages to Doctor",dependsOnMethods = {"Login"})
	public void userMessages(String subjecttext, String messagetext) throws InterruptedException
	{
		messagesPage = new MMPMessages(driver);
		String actual_Message = messagesPage.messages(subjecttext, messagetext);
		System.out.println(actual_Message);

		boolean validateMessage = actual_Message.equals(expected_Message);

		validateCondition(validateMessage,"Could not send the message to Doctor", "Message successfully sent to Doctor");
	}

	@Test(enabled=true, priority=8, description = "TC81: User's Fees information", dependsOnMethods = {"Login"})
	public void feesInformation()
	{		
		feesPage = new MMPFees(driver);
		boolean result = feesPage.fees();

		validateCondition(result,"Fees info not displayed", "Fees info displayed");
	}

	@Test(enabled=true, priority=9, description="TC82: User is able to view the Reports", dependsOnMethods = {"Login"})
	public void viewReports() throws InterruptedException
	{
		viewReportsPage = new MMPViewReports(driver);
		boolean view = viewReportsPage.reports();

		validateCondition(view,"Could not display user reports", "User reports displayed");
	}

	@Parameters({"symptoms"})
	@Test(enabled=true, priority=10, description="Search for Symptoms", dependsOnMethods = {"Login"})
	public void SearchSymptoms(String symptoms) throws InterruptedException
	{

		searchsymPage = new MMPSearchSymptoms(driver);
		boolean solution = searchsymPage.Symptoms(symptoms);

		validateCondition(solution,"User has given an Invalid Symptom", "Valid Sympton entered");
	}	

	@Test(enabled=true, priority=11, description="View Past Transaction",dependsOnMethods = {"Login"})
	public void PastTransaction() throws InterruptedException
	{
		MMPProfileViewHistory history= new MMPProfileViewHistory(driver);
		history.ViewHistory();
		boolean verifyTransaction = 	history.PastTransaction();
		validateCondition(verifyTransaction,"Could not view Transactions","Viewed Transactions Successfully");
	}

	@Test(enabled=true, priority=12, description = "View Past Appointments",dependsOnMethods = {"Login"})
	public void PastAppointment() throws InterruptedException
	{
		MMPProfileViewHistory appointment= new MMPProfileViewHistory(driver);
		appointment.ViewHistory();
		boolean verifyappointment = appointment.appointment();
		validateCondition(verifyappointment,"Could not view Appointments","Viewed Appointments History Successfully");
	}

	@Test(enabled=true, priority=13, description = "View Past Diagnosis",dependsOnMethods = {"Login"})
	public void Diagnosis() throws InterruptedException
	{
		MMPProfileViewHistory Diagnosis= new MMPProfileViewHistory(driver);
		Diagnosis.ViewHistory();
		boolean verifyDiag = Diagnosis.Diagnosis();
		validateCondition(verifyDiag,"Could not view Diagnosis History","Viewed Diagnosis History Successfully");
	}

	@Test(enabled=true, priority=14, description="View Past Prescription",dependsOnMethods = {"Login"})
	public void Prescription() throws InterruptedException
	{
		MMPProfileViewHistory Prescription = new MMPProfileViewHistory(driver);
		Prescription.ViewHistory();
		boolean verifyprescription = Prescription.Diagnosis();
		validateCondition(verifyprescription,"Could not view Prescriptions","Viewed Prescriptions History Successfully");
	}	

	public void validateCondition(boolean condition, String failMessage, String passMessage)
	{
		try
		{
			Assert.assertTrue(condition, failMessage);
			System.out.println(passMessage);
		}
		catch(AssertionError e)
		{
			System.out.println(failMessage);
			Assert.fail();
		}
	}

}

