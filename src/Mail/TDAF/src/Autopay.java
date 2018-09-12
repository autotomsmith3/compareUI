
	//package com.example.tests;

	import java.util.regex.Pattern;
	import java.util.concurrent.TimeUnit;
	import org.junit.*;
	import static org.junit.Assert.*;
	import static org.hamcrest.CoreMatchers.*;
	import org.openqa.selenium.*;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.support.ui.Select;

	public class Autopay {
		private WebDriver driver;
		private String baseUrl;
		private StringBuffer verificationErrors = new StringBuffer();
		@Before
		public void setUp() throws Exception {
			driver = new FirefoxDriver();
			baseUrl = "http://qa-cfui.autodata.net/";
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}

		@Test
		public void testAutopay() throws Exception {
			driver.get(baseUrl + "/home.do");
			driver.findElement(By.id("logo")).click();
			driver.findElement(By.id("username")).clear();
			driver.findElement(By.id("username")).sendKeys("sectest1000");
			driver.findElement(By.id("password")).clear();
			driver.findElement(By.id("password")).sendKeys("Password88");
			driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
			new Select(driver.findElement(By.id("defaultAccountNumber"))).selectByVisibleText("1100072219");
			driver.findElement(By.xpath("//div[@id='contentContainer']/map/area[2]")).click();
			WebElement  
			
			
			
			driver.findElement(By.linkText("LOG OUT")).click();
		}

		@After
		public void tearDown() throws Exception {
			driver.quit();
			String verificationErrorString = verificationErrors.toString();
			if (!"".equals(verificationErrorString)) {
				fail(verificationErrorString);
			}
		}

		private boolean isElementPresent(By by) {
			try {
				driver.findElement(by);
				return true;
			} catch (NoSuchElementException e) {
				return false;
			}
		}
	}