package Sprint1.ImbdTest;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ImbdTest {
	
	WebDriver driver;
	
	@BeforeTest
	public void setUP() {
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
	}
	
	@Test
	public void testCase1() {
		
		driver.get("https://www.imdb.com/");
		driver.findElement(By.id("suggestion-search")).sendKeys("Pushpa: The Rise",Keys.ENTER);
		driver.findElement(By.xpath("//div[text()='Exact matches']")).click();
		driver.findElement(By.xpath("//a[text()='Pushpa: The Rise - Part 1']")).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement e = driver.findElement(By.xpath("//a[text()='December 17, 2021 (United States)']"));
		js.executeScript("arguments[0].scrollIntoView(true);",e);
		System.out.println(e.getText());
		Assert.assertEquals(e.getText(), "December 17, 2021 (United States)");
		String s = driver.findElement(By.xpath("//a[text()='India']")).getText();
		System.out.println(s);	
		Assert.assertEquals(s, "India");
		
	}
	
	@Test
	public void testCase2() {
		driver.get("https://www.wikipedia.org/");
		driver.findElement(By.id("searchInput")).sendKeys("Pushpa: The Rise",Keys.ENTER);
		WebElement e = driver.findElement(By.xpath("//td[@class='infobox-data']//li//child::span//parent::li"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);",e);
		System.out.println(e.getText());
		Assert.assertEquals(e.getText(),"17 December 2021");
		String s = driver.findElement(By.xpath("//td[text()='India']")).getText();
		System.out.println(s);
		Assert.assertEquals(s, "India");
		
	}
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
