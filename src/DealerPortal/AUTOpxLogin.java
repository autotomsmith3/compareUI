package DealerPortal;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AUTOpxLogin extends Comlibs {
	private final WebDriver driver;

	public AUTOpxLogin(WebDriver driver) throws IOException {
		this.driver = driver;
		// String wh1=driver.getWindowHandle();
		String sPageTitle = "AUTOpx Login";
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
	By logo_leftUpLocator = By.xpath("//img[@alt='Autodata']");
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

	public AUTOpxLogin inputUername(WebDriver driver, String un) {
		driver.findElement(usernameLocator).clear();
		driver.findElement(usernameLocator).sendKeys(un);
		return this;
	}

	public AUTOpxLogin inputPassword(WebDriver driver, String ps) {
		driver.findElement(passwordLocator).clear();
		driver.findElement(passwordLocator).sendKeys(ps);
		return this;
	}

	public AcceptLicenseAgreementtoContinue clickLoginBtn(WebDriver driver) throws IOException {
		driver.findElement(loginButtonLocator).click();
		return new AcceptLicenseAgreementtoContinue(driver);
	}
	public DealerProfile clickLoginBtnDealerProfile(WebDriver driver) throws IOException {
		driver.findElement(loginButtonLocator).click();
		return new DealerProfile(driver);
	}

	public void clickLoginBtn(WebDriver driver, String tc) throws IOException {
		driver.findElement(loginButtonLocator).click();
	}

	// public void clickLoginBtn(WebDriver driver) throws IOException {
	// driver.findElement(loginButtonLocator).click();
	// boolean acceptBtnExist = elementExist(driver, acceptAndContinueBtn, false, "");
	// if (acceptBtnExist) {
	// driver.findElement(acceptAndContinueBtn);
	//// return new AcceptLicenseAgreementtoContinue(driver);
	// }else{
	//// return new ImageGallery(driver);
	// }
	// }
	public String login(WebDriver driver, String usr, String ps, String tc) throws IOException {
		String pagetitle;

		inputUername(driver, usr);
		inputPassword(driver, ps);
		clickLoginBtn(driver, tc);
		pagetitle = driver.getTitle();

		return pagetitle;
	}

	public AcceptLicenseAgreementtoContinue login(WebDriver driver, String usr, String ps) throws IOException {
		inputUername(driver, usr);
		inputPassword(driver, ps);
		clickLoginBtn(driver);
		return new AcceptLicenseAgreementtoContinue(driver);
	}
	public DealerProfile loginDealerProfile(WebDriver driver, String usr, String ps) throws IOException {
		inputUername(driver, usr);
		inputPassword(driver, ps);
		clickLoginBtnDealerProfile(driver);
		return new DealerProfile(driver);
	}

	public void verifyHeaderFooter(String env, String versionNum, String tc) throws IOException {
		String disclaimer, versionText;
		String expectDisclaimer = "© 2018 Autodata Solutions, Inc. / Autodata Solutions Company";
		String expectVersionNum = "App version: " + versionNum + " Environment: " + env + "";
		boolean header, footerL1, footerL2, footerR, footerL1Text, footerL2Text;
		header = elementExist(driver, logo_leftUpLocator, true, tc);
		footerL1 = elementExist(driver, foot_01_DisclaimerLocator, true, tc);
		footerL2 = elementExist(driver, foot_02_VersionLocator, true, tc);
		footerR = elementExist(driver, logo_rightButtomLocator, true, tc);
		disclaimer = driver.findElement(foot_01_DisclaimerLocator).getText();
		versionText = driver.findElement(foot_02_VersionLocator).getText();
		footerL1Text = disclaimer.equalsIgnoreCase(expectDisclaimer);
		footerL2Text = versionText.equalsIgnoreCase(expectVersionNum);
		if (header && footerL1 && footerL2 && footerR && footerL1Text && footerL2Text) {
			rwExcel(tc, true, "Verify Header and Footer", "Header and Footer are displayed");
		} else {
			rwExcel(tc, false, "Verify Header and Footer", "Header and Footer are NOT displayed properly");
			if (!header) {
				rwExcel(tc, false, "Verify Header - Logo", "Header - Logo is NOT displayed properly");
			}
			if (!footerL1) {
				rwExcel(tc, false, "Verify Footer line 1 - Disclaimer",
						"Expected is: " + expectDisclaimer + ". The website shows: " + disclaimer);
			}
			if (!footerL2) {
				rwExcel(tc, false, "Verify Footer line 2 - App version",
						"Expected is: " + expectVersionNum + ". The website shows: " + versionText);
			}
			if (!footerR) {
				rwExcel(tc, false, "Verify Footer right line Product Logo",
						"Footer right Product Logo is NOT displayed properly");
			}
			if (!footerR) {
				rwExcel(tc, false, "Verify Footer right line Product Logos",
						"Footer right Product Logos are NOT displayed properly");
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

	public VDVIPasswordReset clickForgotPasswordLink(WebDriver driver, String tc) throws IOException {
		boolean forgotPSLink = elementExist(driver, forgotPSLocator, true, tc);

		if (forgotPSLink) {
			driver.findElement(forgotPSLocator).click();
		} else {
			rwExcel(tc, false, "\"Forgot Password?\" Link", "The link does not exist.");
		}
		return new VDVIPasswordReset(driver);
	}
}
