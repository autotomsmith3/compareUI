package competitiveCompareUI;

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

public class competitiveCompareUIController extends Comlibs {
	private final WebDriver driver;
	final int wt_Secs = 0;
	static String[] vinpxConent = new String[200];
	static String[] stockpxConent = new String[200];
	static String[] lotpxConent = new String[200];
	final static int SINGLE_VIN_RENDER_MAX_WT = 10;
	final static int ALL_VINS_RENDER_MAX_WT = 20;

	public competitiveCompareUIController(WebDriver driver, String myId) throws IOException {
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

	public static SelectVehicle loadURL(WebDriver driver, String bURL) throws IOException {
		driver.get(bURL);
		return new SelectVehicle(driver);
	}

	private static String[] fetchOneDemArrayFromPropFile(String propertyName, Properties propFile) {

		// get array split up by the colin
		String a[] = propFile.getProperty(propertyName).split(",");

		return a;
	}

	public static void CompetitiveCompareTcs(WebDriver driver, String brw, String envment, String brand)
			throws Exception {
		// Load environment parameters
		Properties prop = new Properties();
		// testprop.load(new FileInputStream("data/autopxConf.properties"));
		try {
			prop.load(competitiveCompareUIController.class.getClassLoader()
					.getResourceAsStream("./data/competitiveCompare.properties"));// "./main.properties";
		} catch (IOException e) {
			e.printStackTrace();
		}

		String env = envment;
		String tBrowser = brw;
		String envDevice = prop.getProperty("competitiveCompare.envDevice");
		String onScreen = prop.getProperty("competitiveCompare.onScreen");

		String CompetitiveCompareUIURL = prop.getProperty(env + brand + ".competitiveCompareUIURL");

		int wt = Integer.parseInt(prop.getProperty("CompetitiveCompare.waitTime"));
		// Initial
		String tc;
		String ptitle;
		Comlibs log = new Comlibs();
		log.rwExcel("", "********* " + brand + " Competitive Compare UI**********", "");
		SelectVehicle SelectVehiclePage = new SelectVehicle(driver);

		tc = brand + " - TCxxxx_00";
		SelectVehiclePage.clickOnGotIt(driver, tc);

		tc = brand + " - TCxxxx_01";
		SelectVehiclePage.clickOnVehicle(driver, 1, 1, tc);
		tc = brand + " - TCxxxx_02";
		log.Wait(wt * 2);
		SelectVehiclePage.clickOnTrim(driver, "1", tc);

		Compare ComparePage = new Compare(driver);
		ComparePage.verifyPrimaryImage(driver, tc);
	}

	public static void main(String[] args) throws Exception {
		// Load environment parameters
		Properties prop = new Properties();
		try {
			prop.load(competitiveCompareUIController.class.getClassLoader()
					.getResourceAsStream("./data/competitiveCompare.properties"));//
		} catch (IOException e) {
			e.printStackTrace();
		}
		String brand = "Mazda";
		String env = prop.getProperty("CompetitiveCompare.environment");
		String tBrowser = prop.getProperty("CompetitiveCompare.browser");
		String envDevice = prop.getProperty("CompetitiveCompare.envDevice");
		String onScreen = prop.getProperty("CompetitiveCompare.onScreen");
		String competitiveCompareUIUR = prop.getProperty(env + "." + brand + ".competitiveCompareUIURL");
		String[] Devices = new String[3];

//		String vins[] = fetchOneDemArrayFromPropFile(env + ".CompareVINs", prop);

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

		// i=3: all 3 devices
		for (int i = 0; i < 1; i++) {
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

			loadURL(driver, competitiveCompareUIUR);

			//// 1.Competitive Compare page
			log.rwExcel("", "-----" + brand + " Competitive Compare page Testing started-----" + (i + 1), "");
//			1. ***********VIN Compare**************
			CompetitiveCompareTcs(driver, tBrowser, env, brand);
//			 ***********VIN Compare**************

			log.rwExcel("", "****** Testing is complete ****** " + (i + 1), "");
			driver.close();
			System.out.println("Test is complete!!!   i = " + (i + 1) + "\n");
		}
		System.out.println("*****************All Tests are done!!!*****************" + "\n");
		return;
	}

//	private static void CompetitiveCompareTcs(WebDriver driver2, String tBrowser, String env, String brand) {
//		// TODO Auto-generated method stub
//		
//	}
}
