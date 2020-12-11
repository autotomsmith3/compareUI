package vinCompareDemoUI;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Mail.SendEmail;

public class Vin_Compare extends Comlibs {
	private final WebDriver driver;

	public Vin_Compare(WebDriver driver) throws IOException {
		this.driver = driver;
		// String wh1=driver.getWindowHandle();
		String sPageTitle = "Vin Compare";
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

//	By vinBtn = By.xpath("/html/body/div[1]/form/div/div[1]/div[1]/h4/a");
//	By vehicleInfoBtn = By.xpath("//*[@id=\"App\"]/div/div[2]/div/div/article/div/div[2]/div[1]/div/div/span/label[2]");
//	By vinDropListBtn = By
//			.xpath("//*[@id=\"App\"]/div/div[2]/div/div/article/div/div[2]/div[2]/div/div/form/div[1]/div[2]/div/div");
//
//	By ConfigurableParameters = By.xpath("/html/body/div[1]/form/div/div[1]/div[1]/h4/a");
//	By CompareScriptURL = By.xpath("//*[@id=\"compare-script-url\"]");
//	By CompareAPIBaseURL = By.xpath("//*[@id=\"data-compare-url\"]");
//	By AccessToken = By.xpath("//*[@id=\"data-access-token\"]");
//	By Profile = By.xpath("//*[@id=\"data-profile\"]");
//	By ProductKey = By.xpath("//*[@id=\"data-product-key\"]");
//	By Locale = By.xpath("//*[@id=\"data-locale\"]");
//	By CompareBtn = By.xpath("/html/body/div[1]/form/button[1]");
//	By EditConfiguration = By.xpath("/html/body/div[1]/form/button[2]");
//	By NewConfiguration = By.xpath("/html/body/div[1]/form/button[3]");
	By GMLocator = By.xpath("//*[@id=\"root\"]/div/div/div/div[2]/div[2]/div[1]/div/img");
	By ChevroletLocator = By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div[2]/div[1]/div/img");
	By BuickLocator = By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div[2]/div[2]/div/img");
	By GMCLocator = By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div[2]/div[3]/div/img");
	By CadillacLocator = By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div[2]/div[4]/div/img");
	By ManuallyEnterVins = By.xpath("//*[@id=\"root\"]/div/div/div[3]/div[1]/span");
	By vinField = By.xpath("//*[@id=\"root\"]/div/div/div[3]/div[2]/div[1]/form/div/div[1]/input");
	By customImageField = By.xpath("//*[@id=\"root\"]/div/div/div[3]/div[2]/div[1]/form/div/div[2]/input[1]");
	By customDealerPriceField = By.xpath("//*[@id=\"root\"]/div/div/div[3]/div[2]/div[1]/form/div/div[2]/input[2]");
	By xxxxxx = By.xpath("");
	By AddBtn = By.xpath("//*[@id=\"root\"]/div/div/div[3]/div[2]/div[1]/form/div/div[3]/input");
	By StartCompare = By.xpath("//*[@id=\"root\"]/div/div/div[3]/div[2]/div[3]/div/div[2]/button");
	By StartCompareVehicle = By.xpath("/html/body/div[1]/div/div/div[3]/div[2]/div[2]/div/div/div[2]/button");
	//
	By NewCompareOverLay = By.xpath("/html/body/div[3]/div/div/button");
	By NewComparePageScroll = By.xpath("//*[@id=\"root\"]/div/div/div[4]/button");

	By vehicle01 = By.xpath("//*[@id=\"root\"]/div/div/div[3]/div[2]/div[1]/div[1]/div[1]/img");
	By vehicle02 = By.xpath("//*[@id=\"root\"]/div/div/div[3]/div[2]/div[1]/div[2]/div[1]/img");
	By vehicle03 = By.xpath("");
	By vehicle04 = By.xpath("//*[@id=\"root\"]/div/div/div[3]/div[2]/div[1]/div[4]/div[1]/img");
	By vehicle05 = By.xpath("");
	By vehicle06 = By.xpath("");
//	By xxxxxx = By.xpath("");	
//	By xxxxxx = By.xpath("");	
//	By xxxxxx = By.xpath("");	
//	By xxxxxx = By.xpath("");	
//	By xxxxxx = By.xpath("");	
//	By xxxxxx = By.xpath("");	

	public Vin_Compare clickGM(WebDriver driver, String tc) throws Exception {
		elementExist(driver, GMLocator, true, tc);
		driver.findElement(GMLocator).click();
		return this;
	}

	public Vin_Compare clickChevrolet(WebDriver driver, String tc) throws Exception {
		elementExist(driver, ChevroletLocator, true, tc);
		driver.findElement(ChevroletLocator).click();
		return this;
	}

	public Vin_Compare clickBuick(WebDriver driver, String tc) throws Exception {
		elementExist(driver, BuickLocator, true, tc);
		driver.findElement(BuickLocator).click();
		return this;
	}

	public Vin_Compare clickGMC(WebDriver driver, String tc) throws Exception {
		elementExist(driver, GMCLocator, true, tc);
		driver.findElement(GMCLocator).click();
		return this;
	}

	public Vin_Compare clickCadillac(WebDriver driver, String tc) throws Exception {
		elementExist(driver, CadillacLocator, true, tc);
		driver.findElement(CadillacLocator).click();
		return this;
	}

	public Vin_Compare clickManuallyEnterVinsLink(WebDriver driver, String tc) throws Exception {
		elementExist(driver, ManuallyEnterVins, true, tc);
		driver.findElement(ManuallyEnterVins).click();
		return this;
	}

	public Vin_Compare inputVin(WebDriver driver, String vin, String tc) throws Exception {
		elementExist(driver, vinField, true, tc);
		driver.findElement(vinField).clear();
		driver.findElement(vinField).sendKeys(vin);
		return this;
	}

	public Vin_Compare inputCustomImage(WebDriver driver, String imageURL, String tc) throws Exception {
		elementExist(driver, customImageField, true, tc);
		driver.findElement(customImageField).clear();
		driver.findElement(customImageField).sendKeys(imageURL);
		return this;
	}

	public Vin_Compare inputCustomDealerPrice(WebDriver driver, String dealerPrice, String tc) throws Exception {
		elementExist(driver, customDealerPriceField, true, tc);
		driver.findElement(customDealerPriceField).clear();
		driver.findElement(customDealerPriceField).sendKeys(dealerPrice);
		return this;
	}

	public Vin_Compare clickAdd(WebDriver driver, String tc) throws Exception {
		elementExist(driver, AddBtn, true, tc);
		driver.findElement(AddBtn).click();
		return this;
	}

	public Vin_Compare clickStartCompare(WebDriver driver, String tc) throws Exception {
		elementExist(driver, StartCompare, true, tc);
		driver.findElement(StartCompare).click();
		return this;
	}

	public Vin_Compare clickStartCompareWithVehicle(WebDriver driver, String tc) throws Exception {
		elementExist(driver, StartCompareVehicle, true, tc);
		driver.findElement(StartCompareVehicle).click();
		return this;
	}

	public Vin_Compare clickNewCompareOverLay(WebDriver driver, String tc) throws Exception {
		elementExist(driver, NewCompareOverLay, true, tc);
		driver.findElement(NewCompareOverLay).click();
		return this;
	}

	public Vin_Compare clickNewComparePageScroll(WebDriver driver, String tc) throws Exception {
		elementExist(driver, NewComparePageScroll, true, tc);
		driver.findElement(NewComparePageScroll).click();
		return this;
	}

	public Vin_Compare clickBrand(WebDriver driver, int num, String tc) throws Exception {
		By brand = By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div[2]/div[" + num + "]/div/img");
		// //*[@id="root"]/div/div/div[2]/div/div[2]/div[2]/div[2]/div/img - 2nd
		elementExist(driver, brand, true, tc);
		getkBrandName(driver,num,tc);
		driver.findElement(brand).click();
		return this;
	}

	public String getkBrandName(WebDriver driver, int num, String tc) throws Exception {
		By brand = By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div[2]/div[" + num + "]/div/img");
		// //*[@id="root"]/div/div/div[2]/div/div[2]/div[2]/div[2]/div/img - 2nd
		elementExist(driver, brand, true, tc);
		String BrandName = "";
		try {
			BrandName = driver.findElement(brand).getAttribute("alt");
			BrandName=BrandName.replace("Logo", "");
		} catch (Exception e) {
			BrandName = driver.findElement(brand).getCssValue("alt");
		}

		return BrandName;
	}

	public Vin_Compare clickVehicle(WebDriver driver, int num, String tc) throws Exception {
//		By vehicle = By.xpath("//*[@id=\"root\"]/div/div/div[3]/div[2]/div[1]/div[" + num + "]/div[1]/img");
		By vehicle = By.xpath("/html/body/div[1]/div/div/div[3]/div[2]/div[1]/div["+num+"]/div[1]/h3");
		//                    /html/body/div[1]/div/div/div[3]/div[2]/div[1]/div[1]/div[1]/h3
		//					  /html/body/div[1]/div/div/div[3]/div[2]/div[1]/div[2]/div[1]/h3
		//						
		elementExist(driver, vehicle, true, tc);
		driver.findElement(vehicle).click();
		return this;
	}
	public String getVehicleName(WebDriver driver, int num, String tc) throws Exception {
		String VehicleName="";
//		By vehicle = By.xpath("//*[@id=\"root\"]/div/div/div[3]/div[2]/div[1]/div[" + num + "]/div[1]/img");
		By vehicle = By.xpath("/html/body/div[1]/div/div/div[3]/div[2]/div[1]/div["+num+"]/div[1]/h3");
		//                    /html/body/div[1]/div/div/div[3]/div[2]/div[1]/div[1]/div[1]/h3
		//					  /html/body/div[1]/div/div/div[3]/div[2]/div[1]/div[2]/div[1]/h3
		//						
		elementExist(driver, vehicle, true, tc);
		try {
		VehicleName=driver.findElement(vehicle).getText();
		}catch(Exception e) {
			VehicleName=driver.findElement(vehicle).getCssValue("h3");
		}
		return VehicleName;
	}

	public Vin_Compare clickVehicleShowMore(WebDriver driver, int num, String tc) throws Exception {
		// *[@id="root"]/div/div/div[3]/div[2]/div[1]/div[2]/div[2]/button - 2
		// *[@id="root"]/div/div/div[3]/div[2]/div[1]/div[5]/div[2]/button - 5
		// *[@id="root"]/div/div/div[3]/div[2]/div[1]/div[7]/div[2]/button - 7
		By showMore = By.xpath("//*[@id=\"root\"]/div/div/div[3]/div[2]/div[1]/div[" + num + "]/div[2]/button");
		elementExist(driver, showMore, true, tc);
		driver.findElement(showMore).click();
		return this;
	}

	public int countBrandNumber(WebDriver driver, String tc) throws Exception {
		By brands = By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div[2]/div");
		// //*[@id="root"]/div/div/div[2]/div/div[2]/div[2]/div[2]/div/img - 2nd
		elementExist(driver, brands, true, tc);
		int vehicleNum = driver.findElements(brands).size();
		return vehicleNum;
	}

	public int countVehicleNumber(WebDriver driver, String tc) throws Exception {
		By vehicles = By.xpath("//*[@id=\"root\"]/div/div/div[3]/div[2]/div[1]/div");
		elementExist(driver, vehicles, true, tc);
		int vehicleNum = driver.findElements(vehicles).size();
		return vehicleNum;
	}

	public Vin_Compare clickXbtn(WebDriver driver, String tc) throws Exception {
		By xBtn = By.xpath("/html/body/div[3]/div/div/span/svg");
		// /html/body/div[3]/div/div/span/svg/path
		// /html/body/div[3]/div/div/span/svg
		elementExist(driver, xBtn, true, tc);
		driver.findElement(xBtn).click();
		return this;
	}

	public Vin_Compare clickIdenticalSpecsSwitch(WebDriver driver, String tc) throws Exception {
		// not working since the page is a Flex page. Need to find work around solution
		By IdenticalSpecsSwitch = By
				.xpath("/html/body/div/div/div[1]/div[1]/div/div/div[2]/div/div/div[1]/div/div[2]/div");
		//
		//
		elementExist(driver, IdenticalSpecsSwitch, true, tc);
		driver.findElement(IdenticalSpecsSwitch).click();
		return this;
	}

	public void verifyPrimaryAsConfiguredPriceFrPageScroll(WebDriver driver, String env, String brand,
			String expectedAsConfiguredPrice, String tc) throws Exception {
		String PrimaryAsConfiguredPriceString = "";
		String errorMsg = "";
		int PMSRP = 0;
		try {
			By PrimaryAsConfiguredPrice = By
					.xpath("//*[@id=\"root\"]/div/div/div[4]/div/div/div[2]/div[2]/div[2]/div/div[3]/div[2]/span");
			// /html/body/div/div/div[2]/div[2]/div[2]/div/div[3]/div[2]/span
			// //*[@id="root"]/div/div/div[4]/div/div/div[2]/div[2]/div[2]/div/div[3]/div[2]/span
			// /html/body/div/div/div/div[4]/div/div/div[2]/div[2]/div[2]/div/div[3]/div[2]/span
//			PrimaryAsConfiguredPriceString = driver.findElement(PrimaryAsConfiguredPrice).getCssValue("vc-compare-cell-text");//<span class="vc-compare-cell-text">$48,525</span>
//			PrimaryAsConfiguredPriceString = driver.findElement(PrimaryAsConfiguredPrice).getTagName();
			PrimaryAsConfiguredPriceString = driver.findElement(PrimaryAsConfiguredPrice).getText();
			// $44,995
			if (!PrimaryAsConfiguredPriceString.equals("")) {
				PrimaryAsConfiguredPriceString = PrimaryAsConfiguredPriceString.replace("$", "");
				PrimaryAsConfiguredPriceString = PrimaryAsConfiguredPriceString.replace(",", "");
				PMSRP = Integer.parseInt(PrimaryAsConfiguredPriceString);
			}

//			if (PrimaryStaringFromPriceString.equalsIgnoreCase(expectedPrimaryPrices)) {
			if (PMSRP >= 10000 && PMSRP <= 200000) {
				System.out.println("\n\nPrimaryAsConfiguredPrice matches!*****");
				rwExcel(tc, true, brand + " - Verify PrimaryAsConfiguredPriceString",
						brand + " PrimaryAsConfiguredPrice is showing and matching.");

			} else {
				System.out.println("\n\nPrimaryAsConfiguredPriceString does not match!*****");
				errorMsg = brand + " - Primary Vehicle As Configured Price does not match!" + "\n\n" + brand
						+ " Web Site Primary As Configured Price shows \"" + PrimaryAsConfiguredPriceString
						+ "\" and expected price is " + "\"" + expectedAsConfiguredPrice + "\"\n";
				rwExcel(tc, false, brand + " - Verify PrimaryAsConfiguredPrice", errorMsg);
				SendEmail alertEmail = new SendEmail();
				alertEmail.SendAlertEmail(env, brand, errorMsg, tc);

			}

			System.out.println("\n\n*****");

		} catch (Exception e) {
			errorMsg = brand + " - Primary As Configured Price does not match!" + "\n\n" + brand
					+ " Web Site Primary As Configured Price shows \"" + PrimaryAsConfiguredPriceString
					+ "\" and expected price is " + "\"" + expectedAsConfiguredPrice + "\"\n";
			;
			rwExcel(tc, false, brand + " - PrimaryAsConfiguredPriceString is not showing properly", errorMsg);
//			SendEmail alertEmail = new SendEmail();
//			alertEmail.SendAlertEmail(env, brand, errorMsg, tc);
			System.out.println(
					"\n\n*****************Verify PrimaryAsConfiguredPriceString Is Complete!*******************\n");

		}
	}

	// /html/body/div/div/div[1]/div[2]/div[2]/div[2]/div[1]/span/img
	public void verifyPrimaryImageOverLay(WebDriver driver, String env, String brand, String urlString, String tc)
			throws Exception {
		// not working since the page is a Flex page. Need to find work around solution
		try {
			By PrimaryIageLocator = By.xpath("/html/body/div/div/div[1]/div[2]/div[2]/div[2]/div[1]/span/img");
			VerifyImageLoaded(driver, PrimaryIageLocator, tc);
		} catch (Exception e) {
			rwExcel(tc, false, brand + " - Primary Image is not showing",
					brand + " site maybe is showing error or down.");
			SendEmail alertEmail = new SendEmail();
			alertEmail.SendAlertEmail(env, brand, urlString, tc);
			System.out
					.println("\n\n*****************Verify Primary Image Is Complete!*******************\n" + urlString);

		}
	}

	public void verifyPrimaryAsConfiguredPriceFrOverLay(WebDriver driver, String env, String brand,
			String expectedAsConfiguredPrice, String tc) throws Exception {
		// not working since the page is a Flex page. Need to find work around solution
		String PrimaryAsConfiguredPriceString = "";
		String errorMsg = "";
		int PMSRP = 0;
		try {
			By PrimaryAsConfiguredPrice = By
					.cssSelector("div.vc-comparison-cell-as-configured-price:nth-child(2) > span:nth-child(1)");
			// /html/body/div/div/div[2]/div[2]/div[2]/div/div[3]/div[2]/span
			// //*[@id="root"]/div/div/div[4]/div/div/div[2]/div[2]/div[2]/div/div[3]/div[2]/span
			// /html/body/div/div/div/div[4]/div/div/div[2]/div[2]/div[2]/div/div[3]/div[2]/span
			// FF /html/body/div/div/div[2]/div[2]/div[2]/div/div[3]/div[2]/span
			// FF CSS Path html body div.vc-compare.cleanslate div.vc-root div.vc-categories div.vc-category div.collapse.in div.vc-features div.vc-feature div.vc-comparison-cell.vc-comparison-cell-as-configured-price span.vc-compare-cell-text
//			PrimaryAsConfiguredPriceString = driver.findElement(PrimaryAsConfiguredPrice).getCssValue("vc-compare-cell-text");//<span class="vc-compare-cell-text">$48,525</span>
//			PrimaryAsConfiguredPriceString = driver.findElement(PrimaryAsConfiguredPrice).getTagName();
			try {
				PrimaryAsConfiguredPriceString = driver.findElement(PrimaryAsConfiguredPrice)
						.getAttribute("vc-compare-cell-text");
				PrimaryAsConfiguredPriceString = driver.findElement(PrimaryAsConfiguredPrice).getText();
			} catch (Exception e) {
				PrimaryAsConfiguredPriceString = driver.findElement(PrimaryAsConfiguredPrice)
						.getCssValue("vc-compare-cell-text");
			}
			// $44,995
			if (!PrimaryAsConfiguredPriceString.equals("")) {
				PrimaryAsConfiguredPriceString = PrimaryAsConfiguredPriceString.replace("$", "");
				PrimaryAsConfiguredPriceString = PrimaryAsConfiguredPriceString.replace(",", "");
				PMSRP = Integer.parseInt(PrimaryAsConfiguredPriceString);
			}

//			if (PrimaryStaringFromPriceString.equalsIgnoreCase(expectedPrimaryPrices)) {
			if (PMSRP >= 10000 && PMSRP <= 200000) {
				System.out.println("\n\nPrimaryStaringFromPriceString matches!*****");
				rwExcel(tc, true, brand + " - Verify PrimaryStaringFromPriceString",
						brand + " PrimaryStaringFromPrice is showing and matching.");

			} else {
				System.out.println("\n\nPrimaryStaringFromPriceString does not match!*****");
				errorMsg = brand + " - Primary Vehicle Staringfrom Price does not match!" + "\n\n" + brand
						+ " Web Site Primary Staringfrom Price shows \"" + PrimaryAsConfiguredPriceString
						+ "\" and expected price is " + "\"" + expectedAsConfiguredPrice + "\"\n";
				rwExcel(tc, false, brand + " - Verify PrimaryStaringFromPrice", errorMsg);
				SendEmail alertEmail = new SendEmail();
				alertEmail.SendAlertEmail(env, brand, errorMsg, tc);

			}

			System.out.println("\n\n*****");

		} catch (Exception e) {
			errorMsg = brand + " - Primary As Configured Price does not match!" + "\n\n" + brand
					+ " Web Site Primary As Configured Price shows \"" + PrimaryAsConfiguredPriceString
					+ "\" and expected price is " + "\"" + expectedAsConfiguredPrice + "\"\n";
			;
			rwExcel(tc, true, brand + " - Known issue - PrimaryAsConfiguredPriceString is not showing properly", errorMsg);
//			SendEmail alertEmail = new SendEmail();
//			alertEmail.SendAlertEmail(env, brand, errorMsg, tc);
			System.out.println(
					"\n\n*****************Verify PrimaryAsConfiguredPriceString Is Complete!*******************\n");

		}
	}

	public Vin_Compare clickPageScrollSwitch(WebDriver driver, String OverLayOrPageScrollSwitch,
			String VinEnteyOrVehicle, String tc) throws IOException {
		By PageScrollSwitch = By.xpath("");

		if (OverLayOrPageScrollSwitch.equalsIgnoreCase("1")) {
			// OverLay
			PageScrollSwitch = By
					.xpath("/html/body/div[1]/div/div/div[3]/div[2]/div[2]/div/div/div[1]/div[2]/form/label[1]");
		} else {

			if (VinEnteyOrVehicle.equalsIgnoreCase("1")) {
				// PageScroll from Manual Entery;
				PageScrollSwitch = By
						.xpath("/html/body/div[1]/div/div/div[3]/div[2]/div[3]/div/div[1]/div[2]/form/label[2]");
				// /html/body/div[1]/div/div/div[3]/div[2]/div[2]/div/div/div[1]/div[2]/form/label[2]
				// /html/body/div[1]/div/div/div[3]/div[2]/div[3]/div/div[1]/div[2]/form/label[2]
			} else {
				// Page Scroll from Vehicles selected
				// /html/body/div[1]/div/div/div[3]/div[2]/div[2]/div/div/div[1]/div[2]/form/label[2]
				PageScrollSwitch = By
						.xpath("/html/body/div[1]/div/div/div[3]/div[2]/div[2]/div/div/div[1]/div[2]/form/label[2]");
			} // /html/body/div[1]/div/div/div[3]/div[2]/div[3]/div/div[1]/div[2]/form/label[2]

		}
		elementExist(driver, PageScrollSwitch, true, tc);
		driver.findElement(PageScrollSwitch).click();
		return this;
	}

	public Vin_Compare clickXxxxxbtn(WebDriver driver, String tc) throws Exception {
		By xBtn = By.xpath("/html/body/div[3]/div/div/span/svg");
		// /html/body/div[3]/div/div/span/svg/path
		// /html/body/div[3]/div/div/span/svg
		elementExist(driver, xBtn, true, tc);
		driver.findElement(xBtn).click();
		return this;
	}
}
