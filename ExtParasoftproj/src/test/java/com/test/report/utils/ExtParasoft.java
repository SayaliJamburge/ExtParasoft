package com.test.report.utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;
public class ExtParasoft {

	ExtentReports report;
	ExtentTest logger;
	WebDriver d;
	WebElement LoginButton;
	
	@Test(priority=0)
	public void verifyBlogTitle() throws InterruptedException, IOException
	{
	report=new ExtentReports("D:\\ExtentReportFolder\\ReportOfParasoft.html");
	logger=report.startTest("PARASOFT Report");

	System.setProperty("webdriver.chrome.driver","C:\\SeleniumJar\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
	
	// d= new ChromeDriver();
	//WebDriverManager.chromedriver().setup();
	ChromeOptions opt = new ChromeOptions();
    opt.addArguments("--remote-allow-origins=*");
	d= new ChromeDriver(opt);

	logger.log(LogStatus.INFO, "Browser Started");
	d.get("https://parabank.parasoft.com/parabank/index.htm");	

	logger.log(LogStatus.INFO, "Site Launch Successfully");
/*
	String title=d.getTitle();

	Assert.assertTrue(title.contains("OpenCart"));
	logger.log(LogStatus.INFO, "title checking Done");
	Thread.sleep(10000);
*/

//Registration Page WebElements
LoginButton=d.findElement(By.xpath("//*[@id=\"loginPanel\"]/p[2]/a"));
LoginButton.click();
logger.log(LogStatus.INFO, "Landed on Login page");	
WebElement fname=d.findElement(By.xpath("//*[@id=\"customer.firstName\"]"));
WebElement lname=d.findElement(By.xpath("//*[@id=\"customer.lastName\"]"));
WebElement address=d.findElement(By.xpath("//*[@id=\"customer.address.street\"]"));
WebElement city=d.findElement(By.xpath("//*[@id=\"customer.address.city\"]"));
WebElement state=d.findElement(By.xpath("//*[@id=\"customer.address.state\"]"));
WebElement zip=d.findElement(By.xpath("//*[@id=\"customer.address.zipCode\"]"));
WebElement phone=d.findElement(By.xpath("//*[@id=\"customer.phoneNumber\"]"));
WebElement SSN=d.findElement(By.xpath("//*[@id=\"customer.ssn\"]"));
WebElement Uname=d.findElement(By.xpath("//*[@id=\"customer.username\"]"));
WebElement Pwd=d.findElement(By.xpath("//*[@id=\"customer.password\"]"));
WebElement CPwd=d.findElement(By.xpath("//*[@id=\"repeatedPassword\"]"));

//Excel Input for User Registration

File file1=new File("C:\\Users\\SAYALI\\eclipse-workspace\\ExtParasoftproj\\src\\main\\resources\\Parabank.xlsx");
FileInputStream fis= new FileInputStream(file1);
XSSFWorkbook wb=new XSSFWorkbook(fis);
XSSFSheet sheet=wb.getSheet("Sheet1");
int rowCount=sheet.getLastRowNum()-sheet.getFirstRowNum();
for(int i=1;i<=rowCount;i++)
{
	int cellcount=sheet.getRow(i).getLastCellNum();
	//System.out.println("Row"+i+" data is: ");
	for(int j=0;j<cellcount;j++)
	{
	String dt=sheet.getRow(i).getCell(j).getStringCellValue();
	if(j==0)
	{
	fname.sendKeys(dt);
	}
	if(j==1)
	{
	lname.sendKeys(dt);
	
	}
	if(j==2)
	{
	address.sendKeys(dt);
	}
	if(j==3)
	{
	city.sendKeys(dt);
	}
	if(j==4)
	{
	state.sendKeys(dt);
	}
	if(j==5)
	{
	zip.sendKeys(dt);
	}
	if(j==6)
	{
	phone.sendKeys(dt);
	}
	if(j==7)
	{
	SSN.sendKeys(dt);
	}
	if(j==8)
	{
	Uname.sendKeys(dt);
	}
	if(j==9)
	{
	Pwd.sendKeys(dt);
	CPwd.sendKeys(dt);
	}
	}
	
}
d.findElement(By.xpath("//*[@id=\"customerForm\"]/table/tbody/tr[13]/td[2]/input")).click();
logger.log(LogStatus.INFO, "User Registered Successfully");

//Open New Account
d.findElement(By.xpath("//*[@id=\"leftPanel\"]/ul/li[1]/a")).click();
logger.log(LogStatus.INFO, "Landed on Open New Account page");
d.findElement(By.xpath("//*[@id=\"rightPanel\"]/div/div/form/div/input")).click();
logger.log(LogStatus.INFO, "New Account Created Successfully");

//Account Overview page
d.findElement(By.xpath("//*[@id=\"leftPanel\"]/ul/li[2]/a")).click();
logger.log(LogStatus.INFO, "Landed on Account Overview Page");

//Transfer Fund
d.findElement(By.xpath("//*[@id=\"leftPanel\"]/ul/li[3]/a")).click();
Thread.sleep(5000);
d.findElement(By.xpath("//*[@id=\"amount\"]")).sendKeys("20");
d.findElement(By.xpath("//*[@id=\"rightPanel\"]/div/div/form/div[2]/input")).click();
logger.log(LogStatus.INFO, "Fund Transfered Successfully");

//Update Profile
d.findElement(By.xpath("//*[@id=\"leftPanel\"]/ul/li[6]/a")).click();
Thread.sleep(5000);
d.findElement(By.xpath("//*[@id=\"customer.lastName\"]")).sendKeys("Patil123");
d.findElement(By.xpath("//*[@id=\"rightPanel\"]/div/div/form/table/tbody/tr[8]/td[2]/input")).click();
logger.log(LogStatus.INFO, "Profile Updated Successfully");

//Request Loan
d.findElement(By.xpath("//*[@id=\"leftPanel\"]/ul/li[7]/a")).click();
Thread.sleep(5000);
d.findElement(By.xpath("//*[@id=\"amount\"]")).sendKeys("20");
d.findElement(By.xpath("//*[@id=\"downPayment\"]")).sendKeys("10");
d.findElement(By.xpath("//*[@id=\"rightPanel\"]/div/div/form/table/tbody/tr[4]/td[2]/input")).click();
logger.log(LogStatus.INFO, "Loan Requested Successfully");

//Logout
d.findElement(By.xpath("//*[@id=\"leftPanel\"]/ul/li[8]/a")).click();
logger.log(LogStatus.INFO, "User Logout Successfully");

	}
	
/*
@Test (priority=1)
public void loginPage() throws InterruptedException
{
System.out.println("login procedure");
Thread.sleep(10000);

LoginButton=d.findElement(By.xpath("//*[@id=\"loginPanel\"]/p[2]/a"));
LoginButton.click();
logger.log(LogStatus.INFO, "Landed on Login page");
}


@Test (priority=2)
public void DoneReport()
{
	System.out.println("report procedure");

	report.endTest(logger);
	report.flush();

	d.get("D:\\ExtentReportFolder\\ReportOfOpenCart.html");
}
*/

@AfterMethod
public void tearDown(ITestResult result)
	{
	if (result.getStatus()==ITestResult.FAILURE)
	{
		logger.log(LogStatus.FAIL, "Code not working");
	}
	if(result.getStatus()==ITestResult.SUCCESS)
	{
		logger.log(LogStatus.PASS, "Code working fine");	
	}

	report.endTest(logger);
	report.flush();

	d.get("D:\\ExtentReportFolder\\ReportOfParasoft.html");
	
	
	}
}
