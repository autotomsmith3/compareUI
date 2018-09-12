//package com.example.tests;
//This code was imported from Selenium IDE on 1/17/2014

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class LoginJava {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "https://uat.tdautofinance.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testLoginJava() throws Exception {
    driver.get(baseUrl + "/home.do");
    driver.findElement(By.id("logo")).click();
    driver.findElement(By.cssSelector("area")).click();
    driver.findElement(By.xpath("//div[@id='header']/map/area[2]")).click();
    driver.findElement(By.xpath("//div[@id='header']/map/area[3]")).click();
    driver.findElement(By.xpath("//div[@id='header']/map/area[4]")).click();
    driver.findElement(By.id("logo")).click();
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("sectest1000");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("Password99");
    driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
    driver.findElement(By.id("secretAnswer")).clear();
    driver.findElement(By.id("secretAnswer")).sendKeys("2002");
    driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
    driver.findElement(By.xpath("//div[@id='contentContainer']/map/area[2]")).click();
    driver.findElement(By.xpath("//div[@id='contentContainer']/map/area[3]")).click();
    driver.findElement(By.xpath("//div[@id='contentContainer']/map/area[4]")).click();
    driver.findElement(By.xpath("//div[@id='contentContainer']/map/area[7]")).click();
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
