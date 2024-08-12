package Dealsdray;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FunctionalTestCase {
	WebDriver driver;
	WebDriverWait wait;

	@BeforeMethod
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://demo.dealsdray.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	@Test
	public void LoginToApp() throws IOException {
		WebElement username = driver.findElement(By.xpath("//input[@name='username']"));
		username.sendKeys("prexo.mis@dealsdray.com");

		WebElement password = driver.findElement(By.xpath("//input[@name='password']"));
		password.sendKeys("prexo.mis@dealsdray.com");

		WebElement loginBtn = driver.findElement(By.xpath("//button[@type='submit']"));
		wait.until(ExpectedConditions.elementToBeClickable(loginBtn));
		loginBtn.click();

		WebElement prexo = driver.findElement(By.xpath("//span[text()='Prexo']"));
		String actualMess = prexo.getText();
		String ExpectedMess = "Prexo";
		System.out.println(actualMess);
		if (actualMess.equals(ExpectedMess)) {
			System.out.println("value matched");
		} else {
			System.out.println("Value mismatch");
		}

		WebElement orderDrop = driver.findElement(By.xpath("//button[@type='button']/div/span[2]"));
		wait.until(ExpectedConditions.visibilityOfAllElements(orderDrop));
		orderDrop.click();

		WebElement orders = driver.findElement(By.xpath("//span[text()='Orders']"));
		wait.until(ExpectedConditions.visibilityOfAllElements(orders));
		orders.click();

		WebElement AddBullOrder = driver.findElement(By.xpath("//*[text()='Add Bulk Orders']"));
		wait.until(ExpectedConditions.visibilityOfAllElements(AddBullOrder));
		AddBullOrder.click();
		
		WebElement UploadFile=driver.findElement(By.xpath("//input[@type='file']"));
		UploadFile.sendKeys("C:\\Users\\srika\\Downloads\\demo-data.xlsx");
		
		WebElement importBtn=driver.findElement(By.xpath("//*[text()='Import']"));
		wait.until(ExpectedConditions.visibilityOfAllElements(importBtn));
		importBtn.click();
		
		TakesScreenshot obj=(TakesScreenshot)driver;
		File source=obj.getScreenshotAs(OutputType.FILE);
		File target=new File("FunctionalTesting/Screenshot.png");
		FileUtils.copyFile(source, target);
		


	}
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
