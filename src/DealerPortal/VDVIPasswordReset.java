package DealerPortal;



import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class VDVIPasswordReset extends Comlibs {
	private final WebDriver driver;

	public VDVIPasswordReset(WebDriver driver) throws IOException {
		this.driver = driver;
		// String wh1=driver.getWindowHandle();
		String sPageTitle = "VDVI Password Reset";
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

	By logo_Locator = By.xpath("//*[@id=\"resetForm\"]/div[1]/img");
	By emailForgotPS = By.xpath("//*[@id=\"username\"]");
	By restPasswordBtn = By.xpath("//*[@id=\"btnSubmit\"]");
	By loginToYourAccountLink = By.xpath("//a[contains(text(),'Login to Your Account')]");
//	By sucessMsg = By.cssSelector("div.success-panel");
	By sucessMsg = By.xpath("//*[@id=\"resetForm\"]/div[4]/span");
//	//*[@id="resetForm"]/div[4]/span

	public VDVIPasswordReset inputEmail(WebDriver driver, String email) {
		driver.findElement(emailForgotPS).clear();
		driver.findElement(emailForgotPS).sendKeys(email);
		return this;
	}

	public VDVIPasswordReset clickRestPSBtn(WebDriver driver, String tc) throws IOException {
//		driver.findElement(restPasswordBtn).click();
		boolean restPSBtn = elementExist(driver, restPasswordBtn, true, tc);

		if (restPSBtn) {
			driver.findElement(restPasswordBtn).click();
		} else {
			rwExcel(tc, false, "RESET PASSWORD btton ", "The button does not exist.");
		}
		return this;
	}

	public void verifyMessage(WebDriver driver, String tc) throws IOException {
		boolean msg = elementExist(driver, sucessMsg, true, tc);
		String msgExpected = "An email containing your new password has been sent to your email address";
		if (msg) {
			String msgText = driver.findElement(sucessMsg).getText();
			if (msgText.equals(msgExpected)) {
				// Msg match
				rwExcel(tc, true, "After clicking the RESET PASSWORD button", "Success message shows");
			} else {
				// Success msg not match
				rwExcel(tc, false, "After clicking the RESET PASSWORD button",
						"Success message shows but does not match the expected one.");
				rwExcel(tc, false, "Expected =" + msgExpected, "Site shows=" + msgText);
			}
		} else {
			rwExcel(tc, false, "After clicking the RESET PASSWORD button", "Success message field cannot be found");
		}
		;

	}

	public AUTOpxLogin clickLoginToYourAccountLink(WebDriver driver, String tc) throws IOException {
		boolean loginYALink = elementExist(driver, loginToYourAccountLink, true, tc);

		if (loginYALink) {
			driver.findElement(loginToYourAccountLink).click();
		} else {
			rwExcel(tc, false, "Forgot Password? ", "The link does not exist.");
		}
		return new AUTOpxLogin(driver);
	}

}