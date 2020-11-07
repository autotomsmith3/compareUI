package competitiveCompareUI;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.mail.MessagingException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Mail.SendEmail;

public class Compare extends Comlibs {
	private final WebDriver driver;

	public Compare(WebDriver driver) throws IOException {
		this.driver = driver;
		// String wh1=driver.getWindowHandle();
		String sPageTitle = "Compare";
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

//  Select vehicles:
	By vehicle01 = By.xpath("/html/body/div[1]/div[3]/div[1]/div[1]/div[1]/div/div[3]/div/div[1]/div/div[1]/img");
	By vehicle02 = By.xpath("/html/body/div[1]/div[3]/div[1]/div[1]/div[1]/div/div[3]/div/div[2]/div/div[1]/img");
	By vehicle03 = By.xpath("/html/body/div[1]/div[3]/div[1]/div[1]/div[1]/div/div[3]/div/div[3]/div/div[1]/img");

	By vehicle04 = By.xpath("/html/body/div[1]/div[3]/div[1]/div[2]/div[1]/div/div[3]/div/div[1]/div/div[1]/img");

//	Select trims:
	By trim01 = By.xpath("//*[@id=\"vehicle-select-radio\"]");// //*[@id="vehicle-select-radio"]
	By trim02 = By.xpath("//*[@id=\"vehicle-select-radio\"]");// //*[@id="vehicle-select-radio"]
	By trim03 = By.xpath("");
	By trim04 = By.xpath("");
//	By xxx = By.xpath("");
//	By xxx = By.xpath("");

//	By xxx = By.xpath("");
//	By xxx = By.xpath("");

	public void verifyPrimaryImage(WebDriver driver, String env, String brand, String urlString, String tc)
			throws Exception {
		try {
			By PrimaryIageLocator = By.xpath("//*[@id=\"primary-vehicle\"]/div/div/div[1]/div[1]/div/img");
			VerifyImageLoaded(driver, PrimaryIageLocator, tc);
		} catch (Exception e) {
			rwExcel(tc, false, brand + " - Primary Image is not showing",
					brand + " site maybe is showing error or down.");
			SendEmail alertEmail = new SendEmail();
			alertEmail.SendAlertEmail(env, brand, urlString, tc);
			System.out.println("\n\n*****************Verify Primary Image Is Complete!*******************\n");

		}
	}

	public void verifyPrimaryStaringFromPrice(WebDriver driver, String env, String brand, String urlString,
			String expectedPrimaryPrices, String tc) throws Exception {
		String PrimaryStaringFromPriceString = "";
		String errorMsg = "";
		try {
			By PrimaryStaringFromPrice = By
					.xpath("/html/body/div[1]/div[4]/div[2]/ul/li[1]/div/div[2]/div/ul[1]/li[2]/div[2]/span");
			PrimaryStaringFromPriceString = driver.findElement(PrimaryStaringFromPrice).getText();
			if (PrimaryStaringFromPriceString.equalsIgnoreCase(expectedPrimaryPrices)) {
				System.out.println("\n\nPrimaryStaringFromPriceString matches!*****");
				rwExcel(tc, true, brand + " - Verify PrimaryStaringFromPriceString",
						brand + " PrimaryStaringFromPrice is showing and matching.");

			} else {
				System.out.println("\n\nPrimaryStaringFromPriceString does not match!*****");
				errorMsg = brand + " - Primary Vehicle Staringfrom Price does not match!" + "\n\n" + brand
						+ " Web Site Primary Staringfrom Price shows \"" + PrimaryStaringFromPriceString
						+ "\" and expected price is " + "\"" + expectedPrimaryPrices + "\"\n";
				rwExcel(tc, false, brand + " - Verify PrimaryStaringFromPrice", errorMsg);
				SendEmail alertEmail = new SendEmail();
				alertEmail.SendAlertEmail(env, brand, urlString+"\n"+errorMsg, tc);

			}

			System.out.println("\n\n*****");

		} catch (Exception e) {
			errorMsg= brand + " - Primary Vehicle Staringfrom Price does not match!" + "\n\n" + brand
					+ " Web Site Primary Staringfrom Price shows \"" + PrimaryStaringFromPriceString
					+ "\" and expected price is " + "\"" + expectedPrimaryPrices + "\"\n";;
			rwExcel(tc, false, brand + " - PrimaryStaringFromPrice is not showing properly",errorMsg);
			SendEmail alertEmail = new SendEmail();
			alertEmail.SendAlertEmail(env, brand, urlString+"\n"+errorMsg, tc);
			System.out.println("\n\n*****************Verify PrimaryStaringFromPrice Is Complete!*******************\n");

		}
	}

	public Compare clickOnVehicle(WebDriver driver, int vehicleTypeNumber, int vehicleNum, String tc) throws Exception {
		By vehicle01 = By.xpath("/html/body/div[1]/div[3]/div[1]/div[" + vehicleTypeNumber
				+ "]/div[1]/div/div[3]/div/div[" + vehicleNum + "]/div/div[1]/img");

		By vehicle02 = By.xpath("/html/body/div[1]/div[3]/div[1]/div[1]/div[1]/div/div[3]/div/div[2]/div/div[1]/img");
		By vehicle03 = By.xpath("/html/body/div[1]/div[3]/div[1]/div[1]/div[1]/div/div[3]/div/div[3]/div/div[1]/img");

		By vehicle04 = By.xpath("/html/body/div[1]/div[3]/div[1]/div[2]/div[1]/div/div[3]/div/div[1]/div/div[1]/img");

		elementExist(driver, vehicle01, true, tc);
		driver.findElement(vehicle01).click();
		return this;
	}

	public Compare clickOnTrim(WebDriver driver, String un, String tc) throws Exception {
		By trim01 = By.xpath("//*[@id=\"vehicle-select-radio\"]");
		elementExist(driver, trim01, true, tc);
		driver.findElement(trim01).click();
		return this;
	}

}
