package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class MainNavs1 {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "https://qa-cfui.autodata.net/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testMainNavs1() throws Exception {
    driver.get(baseUrl + "/home.do;jsessionid=A1AD61940F91648FA82546E53684D4B1");
    driver.findElement(By.cssSelector("img[alt=\"TD Auto Finance\"]")).click();
    driver.findElement(By.linkText("Manage Your Account")).click();
    driver.findElement(By.cssSelector("img[alt=\"TD Auto Finance\"]")).click();
    driver.findElement(By.linkText("FAQs")).click();
    driver.findElement(By.id("logo")).click();
    driver.findElement(By.linkText("Contact Us")).click();
    driver.findElement(By.cssSelector("img[alt=\"TD Auto Finance\"]")).click();
    driver.findElement(By.linkText("About Us")).click();
    driver.findElement(By.id("logo")).click();
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

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
