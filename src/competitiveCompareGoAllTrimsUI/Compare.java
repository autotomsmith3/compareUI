package competitiveCompareGoAllTrimsUI;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.mail.MessagingException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

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
	By newCompare = By.xpath("/html/body/div[1]/div[3]/div/nav/div/div/div[3]/ul/li[1]/a/button");
	By closeError = By.xpath("/html/body/div[1]/div/div/div[3]/button");
//	By xxx = By.xpath("");

//	By xxx = By.xpath("");
//	By xxx = By.xpath("");

	public void verifyPrimaryImage(WebDriver driver, String env, String brand, String urlString, String tc)
			throws Exception {
		try {
			By PrimaryIageLocator = By.xpath("//*[@id=\"primary-vehicle\"]/div/div/div[1]/div[1]/div/img");
			VerifyImageLoaded(driver, PrimaryIageLocator, false, tc);
		} catch (Exception e) {
			rwExcel(tc, false, brand + " - Primary Image is not showing",
					brand + " site maybe is showing error or down.");
			SendEmail alertEmail = new SendEmail();
			alertEmail.SendAlertEmail(env, brand, urlString, tc);
			System.out
					.println("\n\n*****************Verify Primary Image Is Complete!*******************\n" + urlString);

		}
	}

	public void verifyPrimaryStartingFromPrice(WebDriver driver, String env, String brand, String urlString,
			String expectedPrimaryPrices, String tc) throws Exception {
		String PrimaryStartingFromPriceString = "";
		String errorMsg = "";
		int PMSRP = 0;
		try {
			By PrimaryStartingFromPrice = By
					.xpath("/html/body/div[1]/div[4]/div[2]/ul/li[1]/div/div[2]/div/ul[1]/li[2]/div[2]/span");
			PrimaryStartingFromPriceString = driver.findElement(PrimaryStartingFromPrice).getText();
			// $44,995
			tc=tc+" - MSRP = "+PrimaryStartingFromPriceString;
			if (!PrimaryStartingFromPriceString.equals("")) {
				PrimaryStartingFromPriceString = PrimaryStartingFromPriceString.replace("$", "");
				PrimaryStartingFromPriceString = PrimaryStartingFromPriceString.replace(",", "");
				PMSRP = Integer.parseInt(PrimaryStartingFromPriceString);
			}

//			if (PrimaryStartingFromPriceString.equalsIgnoreCase(expectedPrimaryPrices)) {
			if (PMSRP >= 10000 && PMSRP <= 200000) {
				System.out.println("\n\n******PrimaryStartingFromPriceString matches!*****");
				rwExcel(tc, true, brand + " - Verify PrimaryStartingFromPriceString",
						brand + " PrimaryStartingFromPrice is showing and matching.");

			} else {
				System.out.println("\n\nPrimaryStartingFromPriceString does not match!*****");
				errorMsg = brand + " - Primary Vehicle Startingfrom Price does not match!" + "\n\n" + brand
						+ " Web Site Primary Startingfrom Price shows \"" + PrimaryStartingFromPriceString
						+ "\" and expected price is " + "\"" + expectedPrimaryPrices + "\"\n";
				rwExcel(tc, false, brand + " - Verify PrimaryStartingFromPrice", errorMsg);
				SendEmail alertEmail = new SendEmail();
				alertEmail.SendAlertEmail(env, brand, urlString + "\n" + errorMsg, tc);

			}

			System.out.println("\n\n*****");

		} catch (Exception e) {
			errorMsg = brand + " - Primary Vehicle Startingfrom Price does not match!" + "\n\n" + brand
					+ " Web Site Primary Startingfrom Price shows \"" + PrimaryStartingFromPriceString
					+ "\" and expected price is " + "\"" + expectedPrimaryPrices + "\"\n";
			;
			rwExcel(tc, false, brand + " - PrimaryStartingFromPrice is not showing properly", errorMsg);
			SendEmail alertEmail = new SendEmail();
			alertEmail.SendAlertEmail(env, brand, urlString + "\n" + errorMsg, tc);
			System.out
					.println("\n\n*****************Verify PrimaryStartingFromPrice Is Complete!*******************\n");

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

	public SelectVehicle clickOnNewCompare(WebDriver driver, String tc) throws Exception {
		elementExist(driver, newCompare, true, tc);
		driver.findElement(newCompare).click();
		return new SelectVehicle(driver);
	}
	public SelectVehicle clickOnNewCompareFrNotAutRunURL(WebDriver driver,String env, String brand,String tc) throws Exception {
		elementExist(driver, newCompare, true, tc);
		driver.findElement(newCompare).click();
		Wait(2);
		if (brand.contains("mitsubishi") && ((env.equalsIgnoreCase("QA")) | (env.equalsIgnoreCase("Staging")))) {
			try {
				driver.switchTo().alert().dismiss();
			} catch (Exception e) {
				System.out.println(
						"Tomcat credential fields pop-up NOT showing! This is expected when running after first Mitisubishi model!\n");
			}
		}
		
		
		
		return new SelectVehicle(driver);
	}

	public Compare clickOnCloseError(WebDriver driver, String tc) throws Exception {
		elementExist(driver, closeError, true, tc);
		driver.findElement(closeError).click();
		return this;
	}

	public void clickRefleshF5Btn(WebDriver driver, String tc) throws IOException {
		// driver.findElement(addInventoryBtn).sendKeys(Keys.F5);
//		By closelocator=By.xpath("/html/body/div[1]/div/div/div[3]/button");
//		driver.findElement(closelocator).click();
		try {
			driver.navigate().to(driver.getCurrentUrl());
//			Actions actionObject = new Actions(driver);
//			actionObject.keyDown(Keys.CONTROL).sendKeys(Keys.F5).keyUp(Keys.CONTROL).perform();
			rwExcel(tc, true, "Click on F5 to fresh the page", "Working fine.");
			competitiveCompareUIController.loadURL(driver, "");
		} catch (Exception e) {
			rwExcel(tc, false, "Click on F5 to fresh the page", "Not Working.");
		}
		;
	}

	public void clickRefleshF5BtnLoadURL(WebDriver driver, String url, String tc) throws IOException {

		try {
			driver.get(url);
		} catch (Exception e) {
//			rwExcel(tc, false, "Re-load URL = " + url, "Not Working.");
			System.out.println("\nTomcat Page is not loading!\n");
		}
		;
	}

	public void checkFeatturesPageshowOrNot(WebDriver driver2, String currentClientURL, String tc) throws Exception {
		boolean imageExist = false;
		int wt = 5;
		int tries = 2;// =10,6,2 - reload MW time 
		try {
//			Wait(wt);
			By PrimaryIageLocator = By.xpath("//*[@id=\"primary-vehicle\"]/div/div/div[1]/div[1]/div/img");
			for (int i = 1; i <= tries; i++) {
				try {
				VerifyImageLoaded(driver, PrimaryIageLocator, false, tc);
				}catch (Exception ee) {
					//
					System.out.println("\n Tried time = "+i+". Image Page is not loading!!!\n");
				}

				imageExist = elementExist(driver, PrimaryIageLocator, false, tc);
				if (imageExist) {
					// image showing, exit.
					break;
				} else {
					// F5 reload page
					driver.get("http://www.google.com");Wait(wt);
					clickRefleshF5BtnLoadURL(driver, currentClientURL, tc);
					rwExcel(tc, currentClientURL, "Try times = " + i);
					Wait(wt);
					if (i == tries) {
						rwExcel(tc, false, currentClientURL, "Tried last times = " + tries);
					}
				}
			}
		} catch (Exception e) {
//			clickRefleshF5Btn(driver, tc);
			clickRefleshF5BtnLoadURL(driver, currentClientURL, tc);
		}
	}
}
