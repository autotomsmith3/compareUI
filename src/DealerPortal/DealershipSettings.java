package DealerPortal;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class DealershipSettings {
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
  public void testDealershipSettings() throws Exception {
    driver.get(baseUrl + "/DealerPortal/authenticate");
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("lucas.zhou@autodata.net");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("Autodata1");
    driver.findElement(By.id("btnSubmit")).click();
    driver.findElement(By.cssSelector("span.glyphicon.glyphicon-menu-down")).click();
    driver.findElement(By.linkText("My Dealership")).click();
    driver.findElement(By.id("dealerName")).clear();
    driver.findElement(By.id("dealerName")).sendKeys("ABC GM1");
    driver.findElement(By.id("dealerSite")).clear();
    driver.findElement(By.id("dealerSite")).sendKeys("ABasdfasdfasdfasfsafdasdfsadfasdfsadC.com1");
    driver.findElement(By.id("dealerTag")).clear();
    driver.findElement(By.id("dealerTag")).sendKeys("We sell great cars. More discounts are available for students, soldiers and workers. We maintain all cars we sold guaranteed!1");
    driver.findElement(By.id("dealerEmail")).clear();
    driver.findElement(By.id("dealerEmail")).sendKeys("lucas.zhou@autodata.net1");
    driver.findElement(By.id("dealerPhone")).clear();
    driver.findElement(By.id("dealerPhone")).sendKeys("1-888-888-99991");
    driver.findElement(By.id("globalMarketingMessage")).clear();
    driver.findElement(By.id("globalMarketingMessage")).sendKeys("AA1");
    driver.findElement(By.id("userFirstName")).clear();
    driver.findElement(By.id("userFirstName")).sendKeys("Lucas1");
    driver.findElement(By.id("userLastName")).clear();
    driver.findElement(By.id("userLastName")).sendKeys("Zhou1");
    driver.findElement(By.id("dealerAddr1")).clear();
    driver.findElement(By.id("dealerAddr1")).sendKeys("663 Rolling dr1");
    driver.findElement(By.id("dealerAddr2")).clear();
    driver.findElement(By.id("dealerAddr2")).sendKeys("1");
    driver.findElement(By.id("dealerCity")).clear();
    driver.findElement(By.id("dealerCity")).sendKeys("London1");
    new Select(driver.findElement(By.id("dealerCountry"))).selectByVisibleText("Canada");
    new Select(driver.findElement(By.id("dealerState"))).selectByVisibleText("ON");
    driver.findElement(By.id("dealerZip")).clear();
    driver.findElement(By.id("dealerZip")).sendKeys("100091");
    driver.findElement(By.id("userPassword")).clear();
    driver.findElement(By.id("userPassword")).sendKeys("1");
    driver.findElement(By.id("userConfirm")).clear();
    driver.findElement(By.id("userConfirm")).sendKeys("1");
    driver.findElement(By.id("emailOptIn")).click();
    driver.findElement(By.id("saveBtn")).click();
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
