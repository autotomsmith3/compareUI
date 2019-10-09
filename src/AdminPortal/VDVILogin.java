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

	// By foot_01_DisclaimerLocator = By.xpath("//div[@id='footer']/nav/div/div/ul/li/span");
	// By foot_02_VersionLocator = By.xpath("//div[@id='footer']/nav/div/div/ul/li/span[2]");
	By pleaseLogLocator = By.xpath("//form[@id='loginForm']/div[2]/p");
	By rememeberMeLocator = By.xpath("//form[@id='loginForm']/div[7]/div/div/label/span");
	By rememeberMeCheckBoxLocator = By.id("rememberMe");
	By forgotPSLocator = By.xpath("//a[contains(text(),'Forgot Password?')]");
	By acceptAndContinueBtn = By.xpath("//button[@type='submit']");
	By engboxesLocator = By.xpath("//ul[@id='engine-options']/li/div");// Engine only

	By foot_01_DisclaimerLocator = By.xpath("//*[@id=\"vdviFooter\"]/div/div/ul[1]/li/span[1]");
	By foot_02_VersionLocator = By.xpath("//*[@id=\"vdviFooter\"]/div/div/ul[1]/li/span[2]");
	// line 1: //*[@id="vdviFooter"]/div/div/ul[1]/li/span[1]
	// line 2: //*[@id="vdviFooter"]/div/div/ul[1]/li/span[2]
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
		try {
			inputUername(driver, usr);
			inputPassword(driver, ps);
			clickLoginBtn(driver, tc);
			pagetitle = driver.getTitle();
			rwExcel(tc, true, "Verify Login Admin Portal", "Working fine.");
		} catch (Exception e) {
			pagetitle = driver.getTitle();
			rwExcel(tc, false, "Verify Login Admin Portal", "Not working");
		}
		return pagetitle;
	}

	public void verifyFooterVersionDisclaimer(String env, String versionNum, String tc) throws IOException {
		String disclaimer, versionText;
		String expectDisclaimer = "© 2019 Autodata Solutions, Inc. / Autodata Solutions Company | Legal Disclaimer";
		// String expectVersionNum = "App version: " + versionNum + " Environment: " + env + "";
		String expectVersionNum = "App version: " + versionNum + " Environment: " + env + "";
		boolean footerL1, footerL2, footerL1Text, footerL2Text;
		footerL1 = elementExist(driver, foot_01_DisclaimerLocator, true, tc);
		footerL2 = elementExist(driver, foot_02_VersionLocator, true, tc);
		disclaimer = driver.findElement(foot_01_DisclaimerLocator).getText();
		versionText = driver.findElement(foot_02_VersionLocator).getText();
		footerL1Text = disclaimer.equalsIgnoreCase(expectDisclaimer);
		footerL2Text = versionText.equalsIgnoreCase(expectVersionNum);
		rwExcel(tc, "Footer Version", "Footer and Version are displayed - "+footerL2Text);
		if (footerL1 && footerL2 && footerL1Text && footerL2Text) {
			rwExcel(tc, true, "Verify Footer and Version", "Footer and Version are displayed - "+footerL2Text);
		} else {
			rwExcel(tc, false, "Verify Footer and Version", "Footer and Version are NOT displayed properly");
			if (!footerL1) {
				rwExcel(tc, false, "Verify Footer line 1 - Disclaimer",
						"Expected is: " + expectDisclaimer + ". The website shows: " + disclaimer);
			}
			if (!footerL2) {
				rwExcel(tc, false, "Verify Footer line 2 - App version",
						"Expected is: " + expectVersionNum + ". The website shows: " + versionText);
			}
			if (!footerL1Text) {
				rwExcel(tc, false, "Verify Footer line 1 - Disclaimer Text",
						"Expected is: " + expectDisclaimer + ". The website shows: " + disclaimer);
			}
			if (!footerL2Text) {
				rwExcel(tc, false, "Verify Footer line 2 - App version #",
						"Expected is: " + expectVersionNum + ". The website shows: " + versionText);
			}
		}
	}
}
