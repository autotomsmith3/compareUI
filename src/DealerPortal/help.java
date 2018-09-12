package DealerPortal;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class help {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://lnoc-q13v-xwa1.autodata.org/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testHelp() throws Exception {
    driver.get(baseUrl + "/DealerPortal/logout");
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("111115@gm.com");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("Autodata1");
    driver.findElement(By.id("btnSubmit")).click();
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.cssSelector("span.glyphicon.glyphicon-question-sign")).click();
    driver.findElement(By.linkText("Contact Support")).click();
    driver.findElement(By.id("supportMessage")).clear();
    driver.findElement(By.id("supportMessage")).sendKeys("This is testing.");
    driver.findElement(By.cssSelector("button.btn.asc-secondary-btn")).click();
    driver.findElement(By.cssSelector("span.glyphicon.glyphicon-question-sign")).click();
    driver.findElement(By.linkText("Contact Support")).click();
    driver.findElement(By.id("supportMessage")).clear();
    driver.findElement(By.id("supportMessage")).sendKeys("This is testing. Ignore.");
    driver.findElement(By.id("sendSupportBtn")).click();
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
