package vinCompareDemoUI;

//Moved from perforce:1666..
/**
* @author Zhoul
* Initial date:  -2020-10-09
* Modified by ...
* In case of broken, update may needed:
*  1. Selenium Webdriver
*  2. Chromedriver
*  3. geckodriver (FF)
*  4. poi-3.9-20121203.jar
*  5. sqljdbc4.jar
*  6. javamail.jar
*  7.json-20160212.jar
*  8. bson-3.30.jar
*  
*   
*   */

import java.awt.AWTException;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
//Test updated 02
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class vinCompareDemoUIController extends Comlibs {
	private final WebDriver driver;
	final int wt_Secs = 0;
	static String[] vinpxConent = new String[200];
	static String[] stockpxConent = new String[200];
	static String[] lotpxConent = new String[200];
	final static int SINGLE_VIN_RENDER_MAX_WT = 10;
	final static int ALL_VINS_RENDER_MAX_WT = 20;

	public vinCompareDemoUIController(WebDriver driver, String myId) throws IOException {
		this.driver = driver;
		// String wh1=driver.getWindowHandle();

		String sPageTitle = "VINpx Login";
		boolean existTitle = TitleDisplay(driver, sPageTitle);
		if (existTitle)
			try {
				{
					rwExcel("", true, "Page Title is displayed!...", sPageTitle);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		else {
			rwExcel("", false, "Page title is NOT showing properly. Exceed time limit!",
					"The page title is NOT - " + sPageTitle);
		}
		if (!sPageTitle.equals(driver.getTitle())) {
			// throw new IllegalStateException("The page title is NOT - "
			// + sPageTitle);
			System.out.println("The page title is NOT - " + sPageTitle);
		}
	}

	/*
	 * ------------------------------ Home Page Object Repositories: ------------------------------
	 */
	By dealershipNameField = By.id("dealerName");
	By webSite = By.id("dealerSite");

	static String urlLink;

	public static Vin_Compare loadURL(WebDriver driver, String bURL) throws IOException {
		driver.get(bURL);
		return new Vin_Compare(driver);
	}

	private static String[] fetchOneDemArrayFromPropFile(String propertyName, Properties propFile) {

		// get array split up by the colin
		String a[] = propFile.getProperty(propertyName).split(",");

		return a;
	}

	public static void VinCompareDemoManulEntryVinTcs(WebDriver driver, String brw, String overLayOrPageScroll,
			String envment) throws Exception {
		// Load environment parameters
		Properties prop = new Properties();
		// testprop.load(new FileInputStream("data/autopxConf.properties"));
		try {
			prop.load(vinCompareDemoUIController.class.getClassLoader()
					.getResourceAsStream("./data/vinCompareDemo.properties"));// "./main.properties";
		} catch (IOException e) {
			e.printStackTrace();
		}

		String env = envment;
		String tBrowser = brw;
		String envDevice = prop.getProperty("Compare.envDevice");
		String onScreen = prop.getProperty("Compare.onScreen");
//		String OverLayOrPageScrollSwitches[] = fetchOneDemArrayFromPropFile("Compare.OverLayOrPageScrollSwitch", prop);
		String VinCompareUIURL = prop.getProperty(env + ".VinCompareUIURL");

		String CompareScriptURL = prop.getProperty(env + ".CompareScriptURL");

		String CompareAPIBaseURL = prop.getProperty(env + ".CompareAPIBaseURL");

		String AccessToken = prop.getProperty(env + ".AccessToken");

		String Profile = prop.getProperty(env + ".Profile");
		String ProductKey = prop.getProperty(env + ".ProductKey");
		String Locale = prop.getProperty(env + ".Locale");

		String vins[] = fetchOneDemArrayFromPropFile(env + ".CompareVINs", prop);
		int numOfVins = vins.length;

		String DealerPrices[] = fetchOneDemArrayFromPropFile(env + ".DealerPrices", prop);
		int numOfDealerPrices = vins.length;

		String Image1 = prop.getProperty(env + ".Image1");
		String Image2 = prop.getProperty(env + ".Image2");
		String Image3 = prop.getProperty(env + ".Image3");
		String Image4 = prop.getProperty(env + ".Image4");
		String Image5 = prop.getProperty(env + ".Image5");
		String Image6 = prop.getProperty(env + ".Image6");

		String[] Images = { Image1, Image2, Image3, Image4, Image5, Image6 };

		int wt = Integer.parseInt(prop.getProperty("Compare.waitTime"));
		// Initial
		String tc;
		String ptitle;
		Comlibs log = new Comlibs();
		log.rwExcel("", "*********VIN Compare Tester Tool UI**********", "");
		Vin_Compare VinComparePage = new Vin_Compare(driver);
		tc = "TC_ClickGM";
		VinComparePage.clickGM(driver, tc);
		log.Wait(wt);
		tc = "TC_ClickChevrolet";
		VinComparePage.clickChevrolet(driver, tc);
		log.Wait(wt);
		tc = "TC_click_ManuallyEnterVinsLik";
		VinComparePage.clickManuallyEnterVinsLink(driver, tc);

		for (int i = 0; i < numOfVins; i++) {
			log.Wait(wt);
			tc = "TC_input_vin_0 - " + (i + 1);
			VinComparePage.inputVin(driver, vins[i], tc);
			tc = "TC_input_url_image_0 - " + (i + 1);
			VinComparePage.inputCustomImage(driver, Images[i], tc);
			tc = "TC_input_dealerPrice_0 - " + (i + 1);
			VinComparePage.inputCustomDealerPrice(driver, DealerPrices[i], tc);
			tc = "TC_click_Add_0 - " + (i + 1);
			VinComparePage.clickAdd(driver, tc);
		}
		System.out.println("overLayOrPageScroll No. = " + overLayOrPageScroll + ".\n");
		log.Wait(wt * 2);
		if (overLayOrPageScroll.equalsIgnoreCase("2")) {
			// PageScroll
			tc = "TC_ClickPageScrollSwitch";
			VinComparePage.clickPageScrollSwitch(driver, overLayOrPageScroll, "1", tc);

			tc = "TC_ClickStartCompare";
			VinComparePage.clickStartCompare(driver, tc);
			log.Wait(wt * 2);

			tc = "TC_ClickNewCompare";
			VinComparePage.verifyPrimaryAsConfiguredPriceFrPageScroll(driver, envDevice, "PageScroll", "10000", tc);

			tc = "TC_ClickNewCompare";
			VinComparePage.clickNewComparePageScroll(driver, tc);
			log.Wait(wt * 2);
		} else {
			tc = "TC_ClickStartCompare";
			VinComparePage.clickStartCompare(driver, tc);
			log.Wait(wt * 2);

			tc = "TC_verifyPrimaryAsConfiguredPriceFrOverLay";
//			Not ready:
			VinComparePage.verifyPrimaryAsConfiguredPriceFrOverLay(driver, envDevice, "OverLay ", "10000", tc);

			tc = "TC_ClickNewCompare";
			VinComparePage.clickNewCompareOverLay(driver, tc);
			System.out.println("\nVinCompareDemoManulEntryVin is complete!!!\n");
			log.Wait(wt * 2);
		}
	}

	public static void VinCompareDemoVehicleTcs(WebDriver driver, String brw, String overLayOrPageScroll,
			String envment) throws Exception {
		// Load environment parameters
		Properties prop = new Properties();
		// testprop.load(new FileInputStream("data/autopxConf.properties"));
		try {
			prop.load(vinCompareDemoUIController.class.getClassLoader()
					.getResourceAsStream("./data/vinCompareDemo.properties"));// "./main.properties";
		} catch (IOException e) {
			e.printStackTrace();
		}

		String env = envment;
		String tBrowser = brw;
		String envDevice = prop.getProperty("Compare.envDevice");
//		String OverLayOrPageScrollSwitches[] = fetchOneDemArrayFromPropFile("Compare.OverLayOrPageScrollSwitch", prop);

		String onScreen = prop.getProperty("Compare.onScreen");
		String VinCompareUIURL = prop.getProperty(env + ".VinCompareUIURL");

		String CompareScriptURL = prop.getProperty(env + ".CompareScriptURL");

		String CompareAPIBaseURL = prop.getProperty(env + ".CompareAPIBaseURL");

		String AccessToken = prop.getProperty(env + ".AccessToken");

		String Profile = prop.getProperty(env + ".Profile");
		String ProductKey = prop.getProperty(env + ".ProductKey");
		String Locale = prop.getProperty(env + ".Locale");

		String vins[] = fetchOneDemArrayFromPropFile(env + ".CompareVINs", prop);
		int numOfVins = vins.length;

		String DealerPrices[] = fetchOneDemArrayFromPropFile(env + ".DealerPrices", prop);
		int numOfDealerPrices = vins.length;

		String Image1 = prop.getProperty(env + ".Image1");
		String Image2 = prop.getProperty(env + ".Image2");
		String Image3 = prop.getProperty(env + ".Image3");
		String Image4 = prop.getProperty(env + ".Image4");
		String Image5 = prop.getProperty(env + ".Image5");
		String Image6 = prop.getProperty(env + ".Image6");

		String[] Images = { Image1, Image2, Image3, Image4, Image5, Image6 };

		int wt = Integer.parseInt(prop.getProperty("Compare.waitTime"));
		// Initial
		String tc;
		String brandName = "";
		String vehicleName = "";
		Comlibs log = new Comlibs();
		log.rwExcel("", "*********VIN Compare Tester Tool UI**********", "");
		Vin_Compare VinComparePage = new Vin_Compare(driver);
		tc = "TC_ClickGM";
		VinComparePage.clickGM(driver, tc);
		log.Wait(wt);
		tc = "TC_getBrandNumbers";
		int brands = VinComparePage.countBrandNumber(driver, tc);
		for (int b = 1; b <= brands; b++) {
			tc = "TC_ClickGM - " + b;
			VinComparePage.clickGM(driver, tc);
			log.Wait(wt);
			tc = "TC_Click Brand = " + b;
			VinComparePage.clickBrand(driver, b, tc);
			brandName = VinComparePage.getkBrandName(driver, b, tc);
			log.Wait(wt);
			tc = "TC_getVehicleNumbers";
			int vehicles = VinComparePage.countVehicleNumber(driver, tc);

			for (int i = 1; i <= vehicles; i++) {
				tc = "TC_ClickGM - " + brandName;
				VinComparePage.clickGM(driver, tc);
				log.Wait(wt * 2);
				tc = "TC_Click Brand = " + brandName;
				VinComparePage.clickBrand(driver, b, tc);
				log.Wait(wt);
				vehicleName = VinComparePage.getVehicleName(driver, i, tc);
				tc = "TC_Select vehicle - " + vehicleName;
				VinComparePage.clickVehicle(driver, i, tc);
				tc = "TC_click Show More - " + vehicleName;
				log.Wait(wt);
				log.scrollUp(driver, -500, tc);
				VinComparePage.clickVehicleShowMore(driver, i, tc);
				log.Wait(wt);
				tc = "TC_click Show Less - " + vehicleName;
				VinComparePage.clickVehicleShowMore(driver, i, tc);
				log.Wait(wt);
				log.scrollUp(driver, 500, tc);

				tc = "TC_click PageScrollSwitch - " + vehicleName;
				VinComparePage.clickPageScrollSwitch(driver, overLayOrPageScroll, "2", tc);

				tc = "TC_ClickStartCompare - " + vehicleName;
				VinComparePage.clickStartCompareWithVehicle(driver, tc);
				log.Wait(wt * 2);
				System.out.println("overLayOrPageScroll No. = " + overLayOrPageScroll + ".\n");
				System.out.println("Brand No. = " + b + ". Vehicle No. = " + i + "\n");
				System.out.println("Brand Name = " + brandName + ". Vehicle = " + vehicleName + "\n");
				System.out.println("This VinCompareDemoVehicle is complete!!!\n");
				if (overLayOrPageScroll.equalsIgnoreCase("1")) {
					// OverLay: check first As Configured Price
//					tc = "TC_clickIdenticalSpecsSwitch" + i;
//					VinComparePage.clickIdenticalSpecsSwitch(driver, tc);
//					tc = "TC_verifyPrimaryImageFrOverLay" + i;
//					VinComparePage.verifyPrimaryImageOverLay(driver,envDevice,"brand="+b,"urlString",tc);

					tc = "TC_verifyPrimaryAsConfiguredPriceFrOverLay - " + vehicleName;
					VinComparePage.verifyPrimaryAsConfiguredPriceFrOverLay(driver, envDevice, "brand = " + brandName,
							"10000", tc);
					tc = "TC_clickNewCompareOverLay - " + vehicleName;
					VinComparePage.clickNewCompareOverLay(driver, tc);
				} else {
					// PageScroll: check first As Configured Price
					// OverLay: check first As Configured Price
					tc = "TC_verifyPrimaryAsConfiguredPriceFrPageScroll - " + vehicleName;
					VinComparePage.verifyPrimaryAsConfiguredPriceFrPageScroll(driver, envDevice, "brand=" + brandName,
							"10000", tc);// ok
					tc = "TC_clickNewComparePageScroll - " + vehicleName;
					try {
						VinComparePage.clickNewComparePageScroll(driver, tc);// ok
					} catch (Exception e) {
						log.scrollUp(driver, -10000, tc);
					}
				}

				log.Wait(wt * 2);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		// Load environment parameters
		Properties prop = new Properties();
		try {
			prop.load(vinCompareDemoUIController.class.getClassLoader()
					.getResourceAsStream("./data/vinCompareDemo.properties"));// "./main.properties";
		} catch (IOException e) {
			e.printStackTrace();
		}
		String env = prop.getProperty("Compare.environment");
		String tBrowser = prop.getProperty("Compare.browser");
		String envDevice = prop.getProperty("Compare.envDevice");
		String onScreen = prop.getProperty("Compare.onScreen");
		String VinCompareDemoUIURL = prop.getProperty(env + ".VinCompareDemoUIURL");
		String vins[] = fetchOneDemArrayFromPropFile(env + ".CompareVINs", prop);
		String OverLayOrPageScrollSwitches[] = fetchOneDemArrayFromPropFile("Compare.OverLayOrPageScrollSwitch", prop);
		String[] Devices = new String[3];

		if (envDevice.equalsIgnoreCase("PC")) {
			Devices[0] = "PC";
			Devices[1] = "Tablet";
			Devices[2] = "Smartphone";
		} else if (envDevice.equalsIgnoreCase("Tablet")) {
			Devices[0] = "Tablet";
			Devices[1] = "Smartphone";
			Devices[2] = "PC";
		} else if (envDevice.equalsIgnoreCase("Smartphone")) {
			Devices[0] = "Smartphone";
			Devices[1] = "PC";
			Devices[2] = "Tablet";
		} else {
			Devices[0] = "PC";
			Devices[1] = "Tablet";
			Devices[2] = "Smartphone";
		}
		for (String OverLayOrPageScrollSwitch : OverLayOrPageScrollSwitches) {
			// i=3: all 3 devices
			for (int i = 0; i < 3; i++) {
				System.out.println("Testing is started in " + env + "\n");
				// Initial
				Comlibs log = new Comlibs();
				final WebDriver driver;
				driver = log.drivers(tBrowser);// Firefox, Chrome
				driver.manage().deleteAllCookies();
				System.out.println("Test Browser = " + tBrowser + "\n");
				System.out.println("Test Device = " + Devices[i] + "\n");

				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				if (!tBrowser.equalsIgnoreCase("Chromexxxxxxxxx")) {
					log.SelecBroswerResolution(driver, Devices[i], onScreen);
				}
				log.rwExcel("", "****** Testing started ******" + (i + 1), "");
				log.rwExcel("", "Test Browser", tBrowser);
				log.rwExcel("", "Test Environment", env);
				log.rwExcel("", "Test Devicer", Devices[i]);

				loadURL(driver, VinCompareDemoUIURL);
				String vin1 = vins[0];
				//// 1. VIN Compare Tester Tool UI page
				log.rwExcel("", "-----VIN Compare Tester Tool UI page Testing started-----" + (i + 1), "");

//				1. ***********1. VinCompareDemoManulEntryVinTcs**************
				VinCompareDemoManulEntryVinTcs(driver, tBrowser, OverLayOrPageScrollSwitch, env);
//			 		*************1. VinCompareDemoManulEntryVinTcs**************
//				2. ***********2. VinCompareDemoVehiclesTcs**************
				VinCompareDemoVehicleTcs(driver, tBrowser, OverLayOrPageScrollSwitch, env);
////			 	*************2. VinCompareDemoVehiclesTcs**************				

				log.rwExcel("", "****** Testing is complete ****** " + (i + 1), "");
//				driver.quit();
				System.out.println("Test is complete!!!   i = " + (i + 1) + "\n");
			}
		}
		System.out.println("*****************All Tests are done!!!*****************" + "\n");
		return;
	}
}
