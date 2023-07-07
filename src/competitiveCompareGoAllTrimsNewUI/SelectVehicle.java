package competitiveCompareGoAllTrimsNewUI;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SelectVehicle extends Comlibs {
	private final WebDriver driver;

	public SelectVehicle(WebDriver driver) throws IOException {
		this.driver = driver;
		// String wh1=driver.getWindowHandle();
		String sPageTitle = "Select Vehicle";// "Select Vehicle";
		boolean existTitle = TitleDisplay(driver, sPageTitle);
		if (existTitle) {
//			rwExcel("", true, "Page Title is displayed", sPageTitle);
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
//	By xxx = By.xpath("");  /html/body/div[1]/div/div/div[2]

	public String getModelName(WebDriver driver, String tc) throws Exception {
		By modelName = By.xpath("/html/body/div[2]/div[3]/div[2]");
		String modelNameString = "";
		elementExist(driver, modelName, true, tc);
		modelNameString = driver.findElement(modelName).getText();
		System.out.println("\n Modle Name = " + modelNameString);

		return modelNameString;
	}

	public int[] countVehicleArray(WebDriver driver, String tc) throws Exception {
		int vehiclesArray[];

		By groupCount = By.xpath("//*[@id=\"root\"]/div/div[3]/div");

		elementExist(driver, groupCount, true, tc);
		int groupCounts = driver.findElements(groupCount).size();
		groupCounts=groupCounts/2;
		vehiclesArray = new int[groupCounts];
		int oneArray = vehiclesArray.length;
		try {
			for (int i = 0; i <= (oneArray - 1); i++) {

//				By vehicleCount = By.xpath("/html/body/div[1]/div[3]/div[1]/div[" + (i + 1) + "]/div[1]/div/div[3]/div/div");

//				By vehicleCount = By.xpath("//*[@id=\"root\"]/div/div[3]/div[" + (i + 1) + "]/div[2]/div");
				
//				By vehicleCount =  By.xpath("//*[@id=\"root\"]/div/div[3]/div["+(i+1)*2+"]/div");//2023-06-20: MazdaCA 2024 failed.

//				By vehicleCount =  By.xpath("//*[@id=\"root\"]/div/div[3]/div");//2023-06-20: fixed MazdaCA 2024 failed.
				By vehicleCount =  By.xpath("/html/body/div/div/div[3]/div["+(i+1)*2+"]/div");//2023-06-23: fix 5 when should be 2

				
				elementExist(driver, vehicleCount, true, tc + " - [" + (i) + "]");
				int vehicleCounts = driver.findElements(vehicleCount).size();
				vehiclesArray[i] = vehicleCounts;
				System.out.println("\nArray [" + i + "] = " + vehiclesArray[i]);

			}
			if (oneArray == 1) {
				System.out.println("\nArray 1= " + vehiclesArray[0]);
				System.out.println("\n");
			} else if (oneArray == 2) {
				System.out.println("\nArray 1= " + vehiclesArray[0]);
				System.out.println("Array 2= " + vehiclesArray[1]);
				System.out.println("\n");
			} else if (oneArray == 3) {
				System.out.println("\nArray 1= " + vehiclesArray[0]);
				System.out.println("Array 2= " + vehiclesArray[1]);
				System.out.println("Array 3= " + vehiclesArray[2]);
				System.out.println("\n");
			} else if (oneArray == 4) {
				System.out.println("\nArray 1= " + vehiclesArray[0]);
				System.out.println("Array 2= " + vehiclesArray[1]);
				System.out.println("Array 3= " + vehiclesArray[2]);
				System.out.println("Array 4= " + vehiclesArray[3]);
				System.out.println("\n");
			} else if (oneArray == 5) {
				System.out.println("\nArray 1= " + vehiclesArray[0]);
				System.out.println("Array 2= " + vehiclesArray[1]);
				System.out.println("Array 3= " + vehiclesArray[2]);
				System.out.println("Array 4= " + vehiclesArray[3]);
				System.out.println("Array 5= " + vehiclesArray[4]);
				System.out.println("\n");
			} else if (oneArray == 6) {
				System.out.println("\nArray 1= " + vehiclesArray[0]);
				System.out.println("Array 2= " + vehiclesArray[1]);
				System.out.println("Array 3= " + vehiclesArray[2]);
				System.out.println("Array 4= " + vehiclesArray[3]);
				System.out.println("Array 5= " + vehiclesArray[4]);
				System.out.println("Array 6= " + vehiclesArray[5]);
				System.out.println("\n");
			}

		} catch (Exception ee) {
			rwExcel(tc, false, "Count Vehicle Array", "Failed; site maybe is showing error or down.");

		}

		return vehiclesArray;
	}

	public SelectVehicle clickOnVehicle_OldBackUp(WebDriver driver, int vehicleTypeNumber, int vehicleNum, String tc)
			throws Exception {
		By vehicle01 = By.xpath("/html/body/div[1]/div[3]/div[1]/div[" + vehicleTypeNumber
				+ "]/div[1]/div/div[3]/div/div[" + vehicleNum + "]/div/div[1]/img");
		By vehicle11 = By.xpath("/html/body/div[1]/div[3]/div[1]/div[1]/div[1]/div/div[3]/div/div[1]/div/div[1]/img");
		By vehicle12 = By.xpath("/html/body/div[1]/div[3]/div[1]/div[1]/div[1]/div/div[3]/div/div[2]/div/div[1]/img");
		By vehicle13 = By.xpath("/html/body/div[1]/div[3]/div[1]/div[1]/div[1]/div/div[3]/div/div[3]/div/div[1]/img");
		By vehicle14 = By.xpath("/html/body/div[1]/div[3]/div[1]/div[1]/div[1]/div/div[3]/div/div[4]/div/div[1]/img");

		By vehicle21 = By.xpath("/html/body/div[1]/div[3]/div[1]/div[2]/div[1]/div/div[3]/div/div[1]/div/div[1]/img");
		By vehicle22 = By.xpath("/html/body/div[1]/div[3]/div[1]/div[2]/div[1]/div/div[3]/div/div[2]/div/div[1]/img");
		By vehicle23 = By.xpath("/html/body/div[1]/div[3]/div[1]/div[2]/div[1]/div/div[3]/div/div[3]/div/div[1]/img");

		By vehicle31 = By.xpath("/html/body/div[1]/div[3]/div[1]/div[3]/div[1]/div/div[3]/div/div[1]/div/div[1]/img");
		By vehicle32 = By.xpath("/html/body/div[1]/div[3]/div[1]/div[3]/div[1]/div/div[3]/div/div[2]/div/div[1]/img");
		// ....
		By vehicle36 = By.xpath("/html/body/div[1]/div[3]/div[1]/div[3]/div[1]/div/div[3]/div/div[6]/div/div[1]/img");

		By vehicle41 = By.xpath("/html/body/div[1]/div[3]/div[1]/div[4]/div[1]/div/div[3]/div/div/div/div[1]/img");
		By vehicle42 = By.xpath("/html/body/div[1]/div[3]/div[1]/div[4]/div[1]/div/div[3]/div/div/div/div[1]/img");
		By vehicle43 = By.xpath("");

		elementExist(driver, vehicle01, true, tc);
		driver.findElement(vehicle01).click();
		return this;
	}

	public SelectVehicle clickOnVehicle(WebDriver driver, int vehicleTypeNumber, int vehicleNum, String tc)
			throws Exception {
//		By vehicle01 = By.xpath("/html/body/div/div/div[3]/div["+vehicleTypeNumber+"]/div[2]/div["+vehicleNum+"]");
		By vehicle01 = By.xpath("/html/body/div/div/div[3]/div["+vehicleTypeNumber*2+"]/div["+vehicleNum+"]/div/div[1]/img");

		By errorShow = By.xpath("/html/body/div[2]/div[3]/div[3]/div");

		elementExist(driver, vehicle01, true, tc);
		boolean errorShowing = false;
		try {
			// First clicking
			driver.findElement(vehicle01).click();

		} catch (Exception e) {
			// Second clicking
			// If the First clickng fail because the vehicle is beyond screen will need
			// second clicking.
			System.out.println(
					"\n First clicking the vehicle model failed so here is for second clicking the vehicle. \n");
			System.out.println("\nWaiting for 10 seconds.....\n");
			Wait(15);
			driver.findElement(vehicle01).click();
		}

		errorShowing = elementExist(driver, errorShow, false, tc);
		if (errorShowing) {
			try {
				driver.findElement(errorShow).click();

				driver.findElement(vehicle01).click();
				errorShowing = elementExist(driver, errorShow, false, tc);
				if (errorShowing) {
					driver.findElement(errorShow).click();
					driver.findElement(vehicle01).click();
					errorShowing = elementExist(driver, errorShow, false, tc);
					if (errorShowing) {
						rwExcel(tc, false, "ClickOnVehicle 2 times but still failed",
								"vehicleTypeNumber = " + vehicleTypeNumber + ". vehicleNum = " + vehicleNum);
					}

				}

			} catch (Exception e) {
				rwExcel(tc, false, "ClickOnVehicle failed",
						"vehicleTypeNumber = " + vehicleTypeNumber + ". vehicleNum = " + vehicleNum);
			}
		}
		return this;
	}

	public void clickOnGotIt(WebDriver driver, String tc) throws Exception {
		By GOTIT = By.xpath("//*[@id=\"root\"]/div/div[5]/button");
		elementExist(driver, GOTIT, true, tc);
		driver.findElement(GOTIT).click();

	}

	public void clickOnGotItIfItShows(WebDriver driver, String tc) throws Exception {
		By GOTIT = By.xpath("//*[@id=\"root\"]/div/div[7]/button");
		boolean shows = elementExist(driver, GOTIT, false, tc);
		if (shows) {
			driver.findElement(GOTIT).click();
		}
	}

	public Compare clickOnTrimOld_1st_OK(WebDriver driver, String env, String brand, String tc) throws Exception {
		By trim01 = By.xpath("/html/body/div[2]/div[3]/div[4]/div[1]");
		elementExist(driver, trim01, true, tc);
		driver.findElement(trim01).click();
		Wait(5);
//		if (brand.contains("Mitsubishi") && ((env.equalsIgnoreCase("QA")) | (env.equalsIgnoreCase("QAKu"))
//				)) {
////				| (env.equalsIgnoreCase("StagingEast")) | (env.equalsIgnoreCase("StagingWest"))
////				| (env.equalsIgnoreCase("Staging")))) {
//			try {
//				driver.switchTo().alert().dismiss();
//			} catch (Exception e) {
//				System.out.println(
//						"Tomcat credential fields pop-up NOT showing! This is expected when running after first Mitisubishi model!\n");
//			}
//		}
		return new Compare(driver);
	}

	public Compare clickOnTrimNewAllTrims(WebDriver driver, String env, String brand, int num, boolean lstTrimExist,
			boolean secondTrimExist, String tc) throws Exception {
//1		.trim-overlay > div:nth-child(2) > div:nth-child(1) > label:nth-child(1)
//2		.trim-overlay > div:nth-child(3) > div:nth-child(1) > label:nth-child(1)

		if (lstTrimExist) {
			num = num + 0;
		} else if (secondTrimExist) {
			num = num + 1;
		} else {
			num = num + 2;
		}

		By trim = By.xpath("/html/body/div[2]/div[3]/div[4]/div[" + num + "]/div[1]/div");
		elementExist(driver, trim, true, tc);
		driver.findElement(trim).click();
		Wait(2);
//		if (brand.contains("Mitsubishi") && ((env.equalsIgnoreCase("QA")) | (env.equalsIgnoreCase("QAKu"))
//				| (env.equalsIgnoreCase("Staging")))) {
//			try {
//				driver.switchTo().alert().dismiss();
//			} catch (Exception e) {
//				System.out.println(
//						"Tomcat credential fields pop-up NOT showing! This is expected when running after first Mitisubishi model!\n");
//			}
//		}
		return new Compare(driver);
	}

	public Compare clickOnTrimNewAllTrims(WebDriver driver, String env, String brand, int num, String tc)
			throws Exception {
//1		.trim-overlay > div:nth-child(2) > div:nth-child(1) > label:nth-child(1)
//2		.trim-overlay > div:nth-child(3) > div:nth-child(1) > label:nth-child(1)
//3		.trim-overlay > div:nth-child(4) > div:nth-child(1) > label:nth-child(1)
		boolean firstTrim = false;
		By trim = By.cssSelector("");
		if (num == 1) {
			num = num + 1;
			trim = By.cssSelector(".trim-overlay > div:nth-child(" + num + ") > div:nth-child(1) > label:nth-child(1)");
			firstTrim = elementExist(driver, trim, false, tc);
			if (!firstTrim) {
				num = num + 1;
				trim = By.cssSelector(
						".trim-overlay > div:nth-child(" + num + ") > div:nth-child(1) > label:nth-child(1)");
				elementExist(driver, trim, true, tc);
			}
		} else {
			num = num + 1;
			trim = By.cssSelector(".trim-overlay > div:nth-child(" + num + ") > div:nth-child(1) > label:nth-child(1)");
			firstTrim = elementExist(driver, trim, false, tc);
			if (!firstTrim) {
				num = num + 1;
				trim = By.cssSelector(
						".trim-overlay > div:nth-child(" + num + ") > div:nth-child(1) > label:nth-child(1)");
				elementExist(driver, trim, true, tc);
			}
		}

//		String trim_name = getTrimName(driver, env, brand, num, tc);

		driver.findElement(trim).click();
		Wait(2);
//		if (brand.contains("Mitsubishi") && ((env.equalsIgnoreCase("QA")) | (env.equalsIgnoreCase("QAKu"))
//				| (env.equalsIgnoreCase("Staging")))) {
//			try {
//				driver.switchTo().alert().dismiss();
//			} catch (Exception e) {
//				System.out.println(
//						"Tomcat credential fields pop-up NOT showing! This is expected when running after first Mitisubishi model!\n");
//			}
//		}
		return new Compare(driver);
	}

	public String getModelName(WebDriver driver, String env, String brand, int g, int num, String tc) throws Exception {

		String modelName = "";
		By modelNameLocator = By.xpath("/html/body/div/div/div[3]/div["+g*2+"]/div["+num+"]/div/div[2]");

		elementExist(driver, modelNameLocator, true, tc);
		try {
			modelName = driver.findElement(modelNameLocator).getText();
			Wait(1);
		} catch (Exception e) {
			modelName = "ModelName=Empty";
			System.out.println("\n********getModelName fails!!!*******\n");
		}
		return modelName;
	}

	public String getModelName_Secondary(WebDriver driver, String env, String brand, int g, int num, String tc)
			throws Exception {

		String modelName = "";
//		By modelNameLocator = By
//				.xpath("/html/body/div[1]/div[3]/div[1]/div[1]/div[1]/div/div[3]/div/div[" + num + "]/div/div[2]");
		By modelNameSecondaryLocator = By
				.xpath("/html/body/div/div/div[3]/div[" + g + "]/div[2]/div[" + num + "]/div/div[2]");

		elementExist(driver, modelNameSecondaryLocator, true, tc);
		try {
			modelName = driver.findElement(modelNameSecondaryLocator).getText();
			Wait(1);
		} catch (Exception e) {
			modelName = "modelNameSecondaryLocator=Empty";
			System.out.println("\n********modelNameSecondaryLocator fails!!!*******\n");
		}
		return modelName;
	}

	public String getModelLowHighMSRPs(WebDriver driver, String env, String brand, int g, int num, String tc)
			throws Exception {

		String modelMSRPs = "";

		By modelMSRPsLocator = By.xpath("/html/body/div/div/div[3]/div["+g*2+"]/div["+num+"]/div/div[3]");

		elementExist(driver, modelMSRPsLocator, true, tc);

		try {

			modelMSRPs = driver.findElement(modelMSRPsLocator).getText();
			Wait(1);
		} catch (Exception e) {
			modelMSRPs = "modelMSRPs=Empty";
			System.out.println("\n********getModelMSRP fails!!!*******\n");
		}
		return modelMSRPs;
	}

	public void verifyLowHighMSRPs(WebDriver driver, String env, String brand, String modelName, String ClientURL,
			String msrps, String tc,int group,int vehicle) throws Exception {
		String modelMSRPs;
		String modelMSRP_low;
		String modelMSRP_high;
//		String modelMSRPs = "";
//		By modelMSRPsLocator = By.xpath("/html/body/div[1]/div[3]/div[1]/div["+g+"]    /div[1]/div/div[3]/div/div["+num+"]/div/div[3]");
//		By modelNameLocator = By.xpath(	"/html/body/div[1]/div[3]/div[1]/div[" + g + "]/div[1]/div/div[3]/div/div[" + num + "]/div/div[2]");
//		By modelMSRPsLocator = By.xpath("/html/body/div[1]/div[3]/div[1]/div[1]/div[1]/div/div[3]/div/div[1]/div/div[3]");//	
		int msrpsLength = msrps.length();
		if (msrpsLength >= 20 && msrpsLength == 25) {
			// seems good

			modelMSRPs = msrps.replace("MSRP", "");
			modelMSRPs = modelMSRPs.replace(" ", "");
			modelMSRPs = modelMSRPs.replace("-", "");
			modelMSRPs = modelMSRPs.replace("*", "");
			modelMSRP_low = modelMSRPs.substring(0, 7);
			modelMSRP_high = modelMSRPs.substring(7, 14);
			if (modelMSRP_low.equalsIgnoreCase(modelMSRP_high)) {
				rwExcel(tc, false, ClientURL, env + " - " + brand + " - " + modelName + " - MSRPs=" + msrps
						+ " - MSRP low and high are same!!!");
				System.out.println("\n********MSRPs fails!*******\n");
			} else {
				rwExcel(tc, true, ClientURL, env + " - " + brand + " - " + modelName + " - MSRPs=" + msrps
						+ " - MSRP low and high are different!!! Looks good.");
			}

		} else {
			// <=20|>24 charaters
			// report issue here:
			rwExcel(tc, false, ClientURL, env + " - " + brand + " - " + modelName + " - MSRPs=" + msrps + " - "
					+ " Length is <= 20 charaters or > 24 charaters! Please manual check!"+"  Error info: group="+group+". v="+vehicle);
			System.out.println("\n********MSRPs <= 20 charaters!!!*******\n");
		}

//		elementExist(driver, modelMSRPsLocator, true, tc);
//		try {
//			modelMSRPs = driver.findElement(modelMSRPsLocator).getText();
//			Wait(1);
//		} catch (Exception e) {
//			modelMSRPs = "modelMSRPs=Empty";
//			System.out.println("\n********getModelMSRP fails!!!*******\n");
//		}
//		return modelMSRPs;
	}

	public String getTrimName(WebDriver driver, String env, String brand, int num, boolean lstTrimExist,
			boolean secondTrimExist, String tc) throws Exception {
		// 1st .trim-overlay > div:nth-child(2) > div:nth-child(1) > label:nth-child(1)
		// 2nd .trim-overlay > div:nth-child(3) > div:nth-child(1) > label:nth-child(1)
		// 3rd .trim-overlay > div:nth-child(4) > div:nth-child(1) > label:nth-child(1)
		if (lstTrimExist) {
			num = num + 0;
		} else if (secondTrimExist) {
			num = num + 1;
		} else {
			num = num + 2;
		}

		String trimName = "";
		By trim = By.xpath("/html/body/div[2]/div[3]/div[4]/div[" + num + "]/div[1]/div");
		elementExist(driver, trim, true, tc);
		try {
			trimName = driver.findElement(trim).getText();
			Wait(1);
		} catch (Exception e) {
			trimName = "TrimName=Empty";
			System.out.println("\n********getTrimName fails!!!*******\n");
		}
		return trimName;
	}

	public boolean checkFirstTrimExist(WebDriver driver, String tc) throws Exception {
		// 1st .trim-overlay > div:nth-child(2) > div:nth-child(1) > label:nth-child(1)
		// 2nd .trim-overlay > div:nth-child(3) > div:nth-child(1) > label:nth-child(1)
		// 3rd .trim-overlay > div:nth-child(4) > div:nth-child(1) > label:nth-child(1)
		By trim01 = By.xpath("/html/body/div[2]/div[3]/div[4]");
		By trim02 = By.xpath("/html/body/div[2]/div[3]/div[4]/div[2]");
		boolean firstTrimExist = elementExist(driver, trim01, false, tc);
//		elementExist(driver, trim02, true, tc + " - Second Trim does not exist!");
		return firstTrimExist;
	}

	public boolean checkSecondTrimExist(WebDriver driver, String tc) throws Exception {
		// 1st .trim-overlay > div:nth-child(2) > div:nth-child(1) > label:nth-child(1)
		// 2nd .trim-overlay > div:nth-child(3) > div:nth-child(1) > label:nth-child(1)
		// 3rd .trim-overlay > div:nth-child(4) > div:nth-child(1) > label:nth-child(1)
		By trim02 = By.xpath("/html/body/div[2]/div[3]/div[4]/div[2]");
		By trim03 = By.xpath("/html/body/div[2]/div[3]/div[4]/div[3]");
		boolean secondTrimExist = elementExist(driver, trim02, false, tc);
		elementExist(driver, trim03, false, tc + " - Third Trim does not exist!");
		return secondTrimExist;
	}

	public int getTrimNumber(WebDriver driver, String env, String brand, String urlS, String tc) throws Exception {
		int countTrim = 0;
		int wt = 2;
		By trims = By.xpath("/html/body/div[2]/div[3]/div[4]/div");
		boolean trimsExit = elementExist(driver, trims, false, tc);
		for (int i = 1; i <= wt; i++) {
			if (trimsExit) {
				System.out.println("\nTrims pop-up shows. \n");
				break;
			} else {
				if (i == wt) {
					rwExcel(tc, false, urlS, "Brand = " + brand + " Not showing after wait " + wt + " minutes.");
				}
				System.out.println("\nTrims pop-up not showing. Start to wait... i = " + i + " of total i = " + wt
						+ " X (60 secs)\n");
				Wait(2);
				trimsExit = elementExist(driver, trims, false, tc);
			}
		}

		elementExist(driver, trims, true, tc);
		countTrim = driver.findElements(trims).size();
		Wait(2);
		return countTrim;
	}

	public void selectYear(WebDriver driver, String year, String tc) throws Exception {
		boolean yearExist = false;
		int wtMins = 2;
		String y = "";
		String currentClientURL = "";
		By curretTestYear = By.xpath("//*[@id=\"" + year + "\"]");
		yearExist = elementExist(driver, curretTestYear, false, tc);
		for (int i = 1; i <= wtMins; i++) {
			if (yearExist) {
				System.out.println("Year = \"" + year + "\"" + " exist!");
				driver.findElement(curretTestYear).click();
			} else {
				System.out.println("Year = \"" + year + "\"" + " does not exist!" + "\n");
				Wait(2);
				yearExist = elementExist(driver, curretTestYear, false, tc);
				if (i == (wtMins - 1)) {
					// waiting failed to see all years, try to reload client site
					currentClientURL = driver.getCurrentUrl();
					driver.get(currentClientURL);
				} else {
					System.out.println(currentClientURL);
					rwExcel(tc, false, currentClientURL, "Year = \"" + year + "\"" + " does not exist!");
				}
			}
		}

	}

}