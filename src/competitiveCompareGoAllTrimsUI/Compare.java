package competitiveCompareGoAllTrimsUI;

import java.io.BufferedWriter;
import java.io.FileWriter;
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
	By availableFeaturesLocator = By.xpath("/html/body/div[1]/div[4]/div[1]/div/div[2]/div/div[2]/div[2]/span/input");

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
		String PrimaryImageStartingFromPriceString = "";
		String SecondaryStartingFromPriceString_01 = "";
		String SecondaryStartingFromPriceString_02 = "";
		String SecondaryStartingFromPriceString_03 = "";

		String errorMsg = "";
		int PMSRP = 0;
		int SMSRP_01 = 0;
		int SMSRP_02 = 0;
		int SMSRP_03 = 0;
		int PImageMSRP = 0;
		try {
			By PrimaryStartingFromPrice = By
					.xpath("/html/body/div[1]/div[4]/div[2]/ul/li[1]/div/div[2]/div/ul[1]/li[2]/div[2]/span");
			PrimaryStartingFromPriceString = driver.findElement(PrimaryStartingFromPrice).getText();

			// Secondary vehicles StartingFromPrice 01 - 03
			By SecondaryStartingFromPrice_01 = By
					.xpath("/html/body/div[1]/div[4]/div[2]/ul/li[1]/div/div[2]/div/ul[1]/li[6]/div[1]/span");
			SecondaryStartingFromPriceString_01 = driver.findElement(SecondaryStartingFromPrice_01).getText();
			By SecondaryStartingFromPrice_02 = By
					.xpath("/html/body/div[1]/div[4]/div[2]/ul/li[1]/div/div[2]/div/ul[1]/li[7]/div[1]/span");
			SecondaryStartingFromPriceString_02 = driver.findElement(SecondaryStartingFromPrice_02).getText();
			By SecondaryStartingFromPrice_03 = By
					.xpath("/html/body/div[1]/div[4]/div[2]/ul/li[1]/div/div[2]/div/ul[1]/li[8]/div[1]/span");
			SecondaryStartingFromPriceString_03 = driver.findElement(SecondaryStartingFromPrice_03).getText();

			// Verify SecondaryStartingFromPriceString_01
			if (!SecondaryStartingFromPriceString_01.equals("")) {
				SecondaryStartingFromPriceString_01 = SecondaryStartingFromPriceString_01.replace("$", "");
				SecondaryStartingFromPriceString_01 = SecondaryStartingFromPriceString_01.replace(",", "");
				SecondaryStartingFromPriceString_01 = SecondaryStartingFromPriceString_01.replace(" ", "");
				SMSRP_01 = Integer.parseInt(SecondaryStartingFromPriceString_01);
				if (SMSRP_01 == 0) {
					rwExcel(tc.replace("VerifyPrimary", "") + " - MSRP=$0", false, urlString,
							"StartFromPrice= $0 " + brand + " - Verify SecondaryStartingFromPriceString_01" + errorMsg);
				}
			} else {
				// SecondaryStartingFromPriceString_01 is empty ""
				rwExcel(tc.replace("VerifyPrimary", "") + " - MSRP=$0", false, urlString,
						brand + " - Verify SecondaryStartingFromPriceString_01 which is empty!" + errorMsg);
			}

			// Verify SecondaryStartingFromPriceString_02
			if (!SecondaryStartingFromPriceString_02.equals("")) {
				SecondaryStartingFromPriceString_02 = SecondaryStartingFromPriceString_02.replace("$", "");
				SecondaryStartingFromPriceString_02 = SecondaryStartingFromPriceString_02.replace(",", "");
				SecondaryStartingFromPriceString_02 = SecondaryStartingFromPriceString_02.replace(" ", "");
				SMSRP_02 = Integer.parseInt(SecondaryStartingFromPriceString_02);
				if (SMSRP_02 == 0) {
					rwExcel(tc.replace("VerifyPrimary", "") + " - MSRP=$0", false, urlString,
							"StartFromPrice= $0 " + brand + " - Verify SecondaryStartingFromPriceString_02" + errorMsg);
				}
			} else {
				// SecondaryStartingFromPriceString_02 is empty ""
				rwExcel(tc.replace("VerifyPrimary", "") + " - MSRP=$0", false, urlString,
						brand + " - Verify SecondaryStartingFromPriceString_02 which is empty!" + errorMsg);
			}

			// Verify SecondaryStartingFromPriceString_03
			if (!SecondaryStartingFromPriceString_03.equals("")) {
				SecondaryStartingFromPriceString_03 = SecondaryStartingFromPriceString_03.replace("$", "");
				SecondaryStartingFromPriceString_03 = SecondaryStartingFromPriceString_03.replace(",", "");
				SecondaryStartingFromPriceString_03 = SecondaryStartingFromPriceString_03.replace(" ", "");
				SMSRP_03 = Integer.parseInt(SecondaryStartingFromPriceString_03);
				if (SMSRP_03 == 0) {
					rwExcel(tc.replace("VerifyPrimary", "") + " - MSRP=$0", false, urlString,
							"StartFromPrice= $0 " + brand + " - Verify SecondaryStartingFromPriceString_03" + errorMsg);
				}
			} else {
				// SecondaryStartingFromPriceString_03 is empty ""
				rwExcel(tc.replace("VerifyPrimary", "") + " - MSRP=$0", false, urlString,
						brand + " - Verify SecondaryStartingFromPriceString_03 which is empty!" + errorMsg);
			}

			By PrimaryImageStartingFromPrice = By.xpath(
					"/html/body/div[1]/div[4]/div[1]/div/div[1]/div/div[1]/div/div/div[1]/div[2]/div[3]/span[2]");
			PrimaryImageStartingFromPriceString = driver.findElement(PrimaryImageStartingFromPrice).getText();

			// $44,995
			tc = tc + " - MSRP = " + PrimaryStartingFromPriceString;
			if (!PrimaryStartingFromPriceString.equals("")) {
				PrimaryStartingFromPriceString = PrimaryStartingFromPriceString.replace("$", "");
				PrimaryStartingFromPriceString = PrimaryStartingFromPriceString.replace(",", "");
				PrimaryStartingFromPriceString = PrimaryStartingFromPriceString.replace(" ", "");
				PMSRP = Integer.parseInt(PrimaryStartingFromPriceString);
			}

//          if (PrimaryStartingFromPriceString.equalsIgnoreCase(expectedPrimaryPrices)) {
			if (PMSRP >= 10000 && PMSRP <= 200000) {
				System.out.println("\n\n******PrimaryStartingFromPriceString matches!*****");
//				rwExcel(tc, true, brand + " - Verify PrimaryStartingFromPriceString",
//						brand + " PrimaryStartingFromPrice is showing and matching.");
				rwExcel(tc, true, brand + " - Verify PrimaryStartingFromPriceString", urlString);
			} else {
				System.out.println("\n\nPrimaryStartingFromPriceString does not match!*****");
				errorMsg = brand + " - Primary Vehicle Startingfrom Price does not match!" + "\n\n" + brand
						+ " Web Site Primary Startingfrom Price shows \"" + PrimaryStartingFromPriceString
						+ "\" and expected price is " + "\"" + expectedPrimaryPrices + "\"\n";
				rwExcel(tc, false, urlString, brand + " - Verify PrimaryStartingFromPrice" + errorMsg);
				SendEmail alertEmail = new SendEmail();
				alertEmail.SendAlertEmail(env, brand, urlString + "\n" + errorMsg, tc);

			}

			// PrimaryImageStartingFromPriceString
			// $44,995 *
			tc = tc + " - MSRP = " + PrimaryImageStartingFromPriceString;
			if (!PrimaryImageStartingFromPriceString.equals("")) {
				PrimaryImageStartingFromPriceString = PrimaryImageStartingFromPriceString.replace("$", "");
				PrimaryImageStartingFromPriceString = PrimaryImageStartingFromPriceString.replace(",", "");
				PrimaryImageStartingFromPriceString = PrimaryImageStartingFromPriceString.replace(" *", "");
				PrimaryImageStartingFromPriceString = PrimaryImageStartingFromPriceString.replace(" ", "");
				PImageMSRP = Integer.parseInt(PrimaryImageStartingFromPriceString);
			}

//          if (PMSRP == PImageMSRP)
			if (PMSRP == PImageMSRP) {
				System.out.println("\n\n******PrimaryImageStartingFromPriceString matches!*****");
				rwExcel(tc.replace("Verify", ""), true, brand + " - Verify PrimaryImageStartingFromPriceString",
						brand + " PrimaryImageStartingFromPriceString is showing and matching the grid one.");

			} else {
				System.out.println("\n\nPrimaryImageStartingFromPriceString does not match!*****");
				errorMsg = brand
						+ " - Primary Image Vehicle Startingfrom Price does not match the Grid Primary Vehicle Startingfrom Price!"
						+ "\n\n" + brand + " Web Site Primary Image Startingfrom Price shows \""
						+ PrimaryImageStartingFromPriceString + "\" and grid Starting from* is " + "\""
						+ PrimaryStartingFromPriceString + "\"\n";
				rwExcel(tc.replace("Verify", ""), false, urlString,
						brand + " - PrimaryImageStartingFromPriceString does NOT match");// errorMsg);

				try {
					SendEmail alertEmail = new SendEmail();
					alertEmail.SendAlertEmail(env, brand, urlString + "\n" + errorMsg, tc);
				} catch (Exception e) {
					rwExcel(tc, false, urlString, " - Failed to send alert email!");
				}

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

	public void get_grid_one_row_values(WebDriver driver, String env, String brand, String urlString,
			String sectionName, int section, int row, String tc) throws Exception {
		String PrimaryStartingFromPriceString = "";
		String PrimaryImageStartingFromPriceString = "";
		String SecondaryStartingFromPriceString_01 = "";
		String SecondaryStartingFromPriceString_02 = "";
		String SecondaryStartingFromPriceString_03 = "";
		String rowName = "";
		boolean rowNameExist = false;
		boolean rowNameLinkExist = false;
		String errorMsg = "";
		int PMSRP = 0;
		int SMSRP_01 = 0;
		int SMSRP_02 = 0;
		int SMSRP_03 = 0;
		int PImageMSRP = 0;
		try {

			By rowNameLocation = By.xpath("/html/body/div[1]/div[4]/div[2]/ul/li[" + section + "]/div/div[2]/div/ul["
					+ row + "]/li[1]/div[1]/span/span");
			rowNameExist = elementExist(driver, rowNameLocation, false, tc);
			By rowNamelinkLocation = By.xpath("/html/body/div[1]/div[4]/div[2]/ul/li[" + section
					+ "]/div/div[2]/div/ul[" + row + "]/li[1]/div[1]/span/button");
			rowNameLinkExist = elementExist(driver, rowNamelinkLocation, false, tc);

			if (rowNameExist) {
				rowName = driver.findElement(rowNameLocation).getText();
				if (rowName.equalsIgnoreCase("")) {
					if (rowNameLinkExist) {
						rowName = driver.findElement(rowNamelinkLocation).getText();
					}
				}
			} else if (rowNameLinkExist) {
				rowName = driver.findElement(rowNamelinkLocation).getText();
			}

			By PrimaryStartingFromPrice = By.xpath("/html/body/div[1]/div[4]/div[2]/ul/li[" + section
					+ "]/div/div[2]/div/ul[" + row + "]/li[2]/div[2]/span");
			PrimaryStartingFromPriceString = driver.findElement(PrimaryStartingFromPrice).getText();

			// Secondary vehicles StartingFromPrice 01 - 03
			By SecondaryStartingFromPrice_01 = By.xpath("/html/body/div[1]/div[4]/div[2]/ul/li[" + section
					+ "]/div/div[2]/div/ul[" + row + "]/li[6]/div[1]/span");
			SecondaryStartingFromPriceString_01 = driver.findElement(SecondaryStartingFromPrice_01).getText();
			By SecondaryStartingFromPrice_02 = By.xpath("/html/body/div[1]/div[4]/div[2]/ul/li[" + section
					+ "]/div/div[2]/div/ul[" + row + "]/li[7]/div[1]/span");
			SecondaryStartingFromPriceString_02 = driver.findElement(SecondaryStartingFromPrice_02).getText();
			By SecondaryStartingFromPrice_03 = By.xpath("/html/body/div[1]/div[4]/div[2]/ul/li[" + section
					+ "]/div/div[2]/div/ul[" + row + "]/li[8]/div[1]/span");
			SecondaryStartingFromPriceString_03 = driver.findElement(SecondaryStartingFromPrice_03).getText();

			String ScratchText = "| " + sectionName + " | " + rowName + ": | " + PrimaryStartingFromPriceString + " |  "
					+ SecondaryStartingFromPriceString_01 + " | " + SecondaryStartingFromPriceString_02 + " | "
					+ SecondaryStartingFromPriceString_03 + " | " + urlString + " | ";

//			System.out.println(ScratchText);
//			System.out.println(rowName+": "+PrimaryStartingFromPriceString+"   "+SecondaryStartingFromPriceString_01+"  "+SecondaryStartingFromPriceString_02+"  "+SecondaryStartingFromPriceString_03+"  \n");

			String filePath = "C:\\1\\Eclipse\\Test Results\\CompareUI\\CompetitiveCompareGridValues.txt";
			SaveScratch(filePath, ScratchText);
//			System.out.println("\n*****");

		} catch (Exception e) {

			rwExcel(tc, false, brand + " - sectionName = " + sectionName + ". section = " + section + ". row = " + row,
					"row value is not showing.");

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

	public SelectVehicle clickOnNewCompareFrNotAutRunURL(WebDriver driver, String env, String brand, String tc)
			throws Exception {
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
				} catch (Exception ee) {
					//
					System.out.println("\n Tried time = " + i + ". Image Page is not loading!!!\n");
				}

				imageExist = elementExist(driver, PrimaryIageLocator, false, tc);
				if (imageExist) {
					// image showing, exit.
					break;
				} else {
					// F5 reload page
					driver.get("http://www.google.com");
					Wait(wt);
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

	public boolean checkFeatturesPageshowOrNotForGridValues(WebDriver driver2, String currentClientURL, String tc)
			throws Exception {
		boolean imageExist = false;
		int wt = 5;
		int tries = 2;// =10,6,2 - reload MW time
		try {
//			Wait(wt);
			By PrimaryIageLocator = By.xpath("//*[@id=\"primary-vehicle\"]/div/div/div[1]/div[1]/div/img");
			for (int i = 1; i <= tries; i++) {
				try {
					VerifyImageLoaded(driver, PrimaryIageLocator, false, tc);
				} catch (Exception ee) {
					//
					System.out.println("\n Tried time = " + i + ". Image Page is not loading!!!\n");
				}

				imageExist = elementExist(driver, PrimaryIageLocator, false, tc);
				if (imageExist) {
					imageExist = true;
					break;
				} else {
					// F5 reload page
					driver.get("http://www.google.com");
					Wait(wt);
					clickRefleshF5BtnLoadURL(driver, currentClientURL, tc);
					rwExcel(tc, currentClientURL, "Try times = " + i);
					Wait(wt);
					if (i == tries) {
						rwExcel(tc, false, currentClientURL, "Tried last times = " + tries);
						imageExist = false;
					}
				}
			}
		} catch (Exception e) {
//			clickRefleshF5Btn(driver, tc);
			clickRefleshF5BtnLoadURL(driver, currentClientURL, tc);
		}
		return imageExist;
	}

	public void clickAvailableFeatures(WebDriver driver, String environment, String tc) throws IOException {
//		if (environment.equalsIgnoreCase("QA")) {
		elementExist(driver, availableFeaturesLocator, true, tc);
		try {
			driver.findElement(availableFeaturesLocator).click();
		} catch (Exception e) {
			System.out.println("\nClick on availableFeaturesLocator button failed!!!\n");
		}
//		}
	}

	public String getYearBrandModelTrim_Name(WebDriver driver, String currentClientURL, String tc) throws Exception {
		boolean imageExist = false;
		String year_brand = "";
		String modle_trim = "";
		String year_brand_model_trim_Name = "";

		try {
			By PrimaryIageLocator = By.xpath("//*[@id=\"primary-vehicle\"]/div/div/div[1]/div[1]/div/img");
			imageExist = elementExist(driver, PrimaryIageLocator, false, tc);
			if (imageExist) {
				By year_brand_locator = By.xpath("//*[@id=\"primary-vehicle\"]/div/div/div[1]/div[2]/div[1]");
				By modle_trim_locator = By.xpath("//*[@id=\"primary-vehicle\"]/div/div/div[1]/div[2]/div[2]");
				year_brand = driver.findElement(year_brand_locator).getText();
				modle_trim = driver.findElement(modle_trim_locator).getText();
				;

				year_brand_model_trim_Name = year_brand + " - " + modle_trim;

			} else {

			}

		} catch (Exception e) {
//			clickRefleshF5Btn(driver, tc);
			clickRefleshF5BtnLoadURL(driver, currentClientURL, tc);
		}
		return year_brand_model_trim_Name;

	}

	public static void SaveScratch(String pathfilename, String ScratchText) {
		try {
			// BufferedWriter out2 = new BufferedWriter(new FileWriter(dataDir+
			// "Acodes.txt", true)); //original OK
			BufferedWriter out2 = new BufferedWriter(new FileWriter(pathfilename, true));
			// out2.write("("+i+"): "+Acode+": ");
			// out2.write(i + ". " + Acode + ": "); //Original OK
			// out2.newLine();
			out2.write(ScratchText);
			out2.newLine();
			out2.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}

	public int getCategoryRowsFromName(String categoryName) {
		int num = 0;
		int dynomic_num = 0;
		switch (categoryName) {
		case "Pricing":
			num = 4;
			dynomic_num = getRowsFrUI(1);
			if (dynomic_num == 0) {
				System.err.println("Pricing_getRpwsFrIO. Rows=0!");
			} else {
				num = dynomic_num;
			}
			break;
		case "FUEL ECONOMY":
			num = 4;
			dynomic_num = getRowsFrUI(2);
			if (dynomic_num == 0) {
				System.err.println("FUEL ECONOMY_getRpwsFrIO. Rows=0!");
			} else {
				num = dynomic_num;
			}

			break;
		case "EXTERIOR FEATURES":
			num = 47;
			dynomic_num = getRowsFrUI(3);
			if (dynomic_num == 0) {
				System.err.println("EXTERIOR FEATURES_getRpwsFrIO. Rows=0!");
			} else {
				num = dynomic_num;
			}
			break;
		case "COMFORT":
			num = 22;
			dynomic_num = getRowsFrUI(4);
			if (dynomic_num == 0) {
				System.err.println("COMFORT_getRpwsFrIO. Rows=0!");
			} else {
				num = dynomic_num;
			}
			break;
		case "CONVENIENCE":
			num = 26;
			dynomic_num = getRowsFrUI(5);
			if (dynomic_num == 0) {
				System.err.println("CONVENIENCE_getRpwsFrIO. Rows=0!");
			} else {
				num = dynomic_num;
			}
			break;
		case "LIGHTING":
			num = 12;
			dynomic_num = getRowsFrUI(6);
			if (dynomic_num == 0) {
				System.err.println("LIGHTING_getRpwsFrIO. Rows=0!");
			} else {
				num = dynomic_num;
			}
			break;
		case "INFOTAINMENT":
			num = 15;
			dynomic_num = getRowsFrUI(7);
			if (dynomic_num == 0) {
				System.err.println("INFOTAINMENT_getRpwsFrIO. Rows=0!");
			} else {
				num = dynomic_num;
			}
			break;
		case "MECHANICAL":
			num = 23;// 23 or 24 or 25
			dynomic_num = getRowsFrUI(8);
			if (dynomic_num == 0) {
				System.err.println("MECHANICAL_getRpwsFrIO. Rows=0!");
			} else {
				num = dynomic_num;
			}
			break;
		case "SAFETY":
			num = 33;
			dynomic_num = getRowsFrUI(9);
			if (dynomic_num == 0) {
				System.err.println("SAFETY_getRpwsFrIO. Rows=0!");
			} else {
				num = dynomic_num;
			}
			break;
		case "DIMENSIONS":
			num = 21;// 21 or 22
			dynomic_num = getRowsFrUI(10);
			if (dynomic_num == 0) {
				System.err.println("DIMENSIONS_getRpwsFrIO. Rows=0!");
			} else {
				num = dynomic_num;
			}
			break;
		case "WARRANTY":
			num = 8; // 8 or 9 or 10
			dynomic_num = getRowsFrUI(11);
			if (dynomic_num == 0) {
				System.err.println("WARRANTY_getRpwsFrIO. Rows=0!");
			} else {
				num = dynomic_num;
			}
			break;
		default:
			num = 1;
		}

		return num;
	}

	private int getRowsFrUI(int num) {
		// TODO Auto-generated method stub
		By category = By.xpath("/html/body/div[1]/div[4]/div[2]/ul/li[" + num + "]/div/div[2]/div/ul");// 4

//		By category_01 = By.xpath("/html/body/div[1]/div[4]/div[2]/ul/li[1]/div/div[2]/div/ul");//4
//		By category_02 = By.xpath("/html/body/div[1]/div[4]/div[2]/ul/li[2]/div/div[2]/div/ul");//4
//		By category_03 = By.xpath("/html/body/div[1]/div[4]/div[2]/ul/li[3]/div/div[2]/div/ul");//47
//		By category_04 = By.xpath("/html/body/div[1]/div[4]/div[2]/ul/li[4]/div/div[2]/div/ul");//22
//		By category_05 = By.xpath("/html/body/div[1]/div[4]/div[2]/ul/li[5]/div/div[2]/div/ul");//0
//		By category_06 = By.xpath("/html/body/div[1]/div[4]/div[2]/ul/li[6]/div/div[2]/div/ul/");//0
		int dynomic_nums = 0;

		try {
			dynomic_nums = driver.findElements(category).size();
		} catch (Exception e) {// Catch exception if any
			dynomic_nums = 0;
			System.err.println("Error: " + e.getMessage());
		}

//		try {
//			dynomic_nums = driver.findElements(category_01).size();
//		} catch (Exception e) {// Catch exception if any
//			System.err.println("Error: " + e.getMessage());
//		}
//		try {dynomic_nums = 0;
//			dynomic_nums = driver.findElements(category_02).size();
//		} catch (Exception e) {// Catch exception if any
//			System.err.println("Error: " + e.getMessage());
//		}
//		try {dynomic_nums = 0;
//			dynomic_nums = driver.findElements(category_03).size();
//		} catch (Exception e) {// Catch exception if any
//			System.err.println("Error: " + e.getMessage());
//		}
//		try {dynomic_nums = 0;
//			dynomic_nums = driver.findElements(category_04).size();
//		} catch (Exception e) {// Catch exception if any
//			System.err.println("Error: " + e.getMessage());
//		}		
//		try {dynomic_nums = 0;
//			dynomic_nums = driver.findElements(category_05).size();
//		} catch (Exception e) {// Catch exception if any
//			System.err.println("Error: " + e.getMessage());
//		}		
//		try {dynomic_nums = 0;
//			dynomic_nums = driver.findElements(category_06).size();
//		} catch (Exception e) {// Catch exception if any
//			System.err.println("Error: " + e.getMessage());
//		}		

		return dynomic_nums;
	}

}
