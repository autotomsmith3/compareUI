package AdminPortal;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class VDVILogin extends Comlibs {
	private final WebDriver driver;

	public VDVILogin(WebDriver driver) throws IOException {
		this.driver = driver;
		// String wh1=driver.getWindowHandle();
		String sPageTitle = "VDVI Login";
		boolean existTitle = TitleDisplay(driver, sPageTitle);
		if (existTitle) {
			rwExcel("", true, "Page Title is displayed", sPageTitle);
		} else {
			rwExcel("", false, "Page title is NOT showing properly. Exceed time limit!",
					"The page title is NOT - " + sPageTitle);
		}
		if (!sPageTitle.equals(driver.getTitle())) {
			System.out.println("Page title does not matche. Expected page title =" + sPageTitle);
		}
	}

	By dealerShipName = By.cssSelector("span");
	By logo_middleLocator = By.xpath("(//img[@alt='Autodata'])[2]");
	By logo_rightButtomLocator = By.xpath("//li/a/img"); // By.xpath("(//img[@alt='Autodata'])[3]");

	By usernameLocator = By.id("username");
	By passwordLocator = By.id("password");
	By loginButtonLocator = By.id("btnSubmit");

	By foot_01_DisclaimerLocator = By.xpath("//div[@id='footer']/nav/div/div/ul/li/span");
	By foot_02_VersionLocator = By.xpath("//div[@id='footer']/nav/div/div/ul/li/span[2]");
	By pleaseLogLocator = By.xpath("//form[@id='loginForm']/div[2]/p");
	By rememeberMeLocator = By.xpath("//form[@id='loginForm']/div[7]/div/div/label/span");
	By rememeberMeCheckBoxLocator = By.id("rememberMe");
	By forgotPSLocator = By.xpath("//a[contains(text(),'Forgot Password?')]");
	By acceptAndContinueBtn = By.xpath("//button[@type='submit']");
	By engboxesLocator = By.xpath("//ul[@id='engine-options']/li/div");// Engine only

	// By Locator=
	// By Locator=
	// By Locator=
	// By Locator=
	// By Locator=
	// By Locator=
	// By Locator=
	// By Locator=
	// By Locator=

	// driver.findElement(By.id("password")).clear();
	// driver.findElement(By.id("password")).sendKeys("Autodata1");
	// driver.findElement(By.id("username")).clear();
	// driver.findElement(By.id("username")).sendKeys("lucas.zhou@autodata.net");
	// driver.findElement(By.id("btnSubmit")).click();
	// driver.findElement(By.cssSelector("span.glyphicon.glyphicon-menu-down")).click();
	// driver.findElement(By.linkText("My Dealership")).click();
	// driver.findElement(By.id("viewInventoryBtn")).click();

	public VDVILogin inputUername(WebDriver driver, String un) {
		driver.findElement(usernameLocator).clear();
		driver.findElement(usernameLocator).sendKeys(un);
		return this;
	}

	public VDVILogin inputPassword(WebDriver driver, String ps) {
		driver.findElement(passwordLocator).clear();
		driver.findElement(passwordLocator).sendKeys(ps);
		return this;
	}

	public void clickLoginBtn(WebDriver driver, String tc) throws IOException {
		driver.findElement(loginButtonLocator).click();
	}

	public String login(WebDriver driver, String usr, String ps, String tc) throws IOException {
		String pagetitle;

		inputUername(driver, usr);
		inputPassword(driver, ps);
		clickLoginBtn(driver, tc);
		pagetitle = driver.getTitle();

		return pagetitle;
	}

}
