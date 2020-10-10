package compareUI;

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

public class vinCompareUIController extends Comlibs {
	private final WebDriver driver;
	final int wt_Secs = 0;
	static String[] vinpxConent = new String[200];
	static String[] stockpxConent = new String[200];
	static String[] lotpxConent = new String[200];
	final static int SINGLE_VIN_RENDER_MAX_WT = 10;
	final static int ALL_VINS_RENDER_MAX_WT = 20;

	public vinCompareUIController(WebDriver driver, String myId) throws IOException {
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

	public static Vin2VinCompare loadURL(WebDriver driver, String bURL) throws IOException {
		driver.get(bURL);
		return new Vin2VinCompare(driver);
	}



	private static String[] fetchOneDemArrayFromPropFile(String propertyName, Properties propFile) {

		// get array split up by the colin
		String a[] = propFile.getProperty(propertyName).split(",");

		return a;
	}
	public static void Vin2VinCompareTcs(WebDriver driver, String brw, String envment) throws Exception {
		// Load environment parameters
		Properties prop = new Properties();
		// testprop.load(new FileInputStream("data/autopxConf.properties"));
		try {
			prop.load(vinCompareUIController.class.getClassLoader()
					.getResourceAsStream("./data/compare.properties"));// "./main.properties";
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		String env = envment;
		String tBrowser = brw;
		String envDevice = prop.getProperty("Compare.envDevice");
		String onScreen = prop.getProperty("Compare.onScreen");
		String VinCompareUIURL = prop.getProperty(env+".VinCompareUIURL");
		
		String CompareScriptURL=prop.getProperty(env+".CompareScriptURL");
		
		String CompareAPIBaseURL=prop.getProperty(env+".CompareAPIBaseURL");
		
		String AccessToken=prop.getProperty(env+".AccessToken");
		
		String Profile=prop.getProperty(env+".Profile");		
		String ProductKey=prop.getProperty(env+".ProductKey");
		String Locale=prop.getProperty(env+".Locale");
		
		
		
		
		String vins[]=fetchOneDemArrayFromPropFile(env+".CompareVINs",prop);

		int wt = Integer.parseInt(prop.getProperty("Compare.waitTime"));
		// Initial
		String tc;
		String ptitle;
		Comlibs log = new Comlibs();
		log.rwExcel("", "*********VIN Compare Tester Tool UI**********", "");
		Vin2VinCompare Vin2VinComparePage = new Vin2VinCompare(driver);
		tc = "TCxxxx_01";
		Vin2VinComparePage.clickConfigurealeParameters(driver, tc);
		
		tc = "TCxxxx_021";
		Vin2VinComparePage.inputCompareScriptURL(driver, CompareScriptURL, tc);
		tc = "TCxxxx_022";
		Vin2VinComparePage.inputCompareAPIBaseURL(driver, CompareAPIBaseURL,tc);

		
		tc = "TCxxxx_023";
		Vin2VinComparePage.inputAccessToken(driver, AccessToken, tc);
		
		tc = "TCxxxx_024";
		Vin2VinComparePage.inputProfile(driver, Profile,tc);
		tc = "TCxxxx_025";
		Vin2VinComparePage.inputProductKey(driver, ProductKey,tc);	
		
		tc = "TCxxxx_026";
		Vin2VinComparePage.inputLocale(driver, Locale,tc);
		
//		tc = "TCxxxx_027";
//		Vin2VinComparePage(driver, xx,tc);
//		
//		
//		tc = "TCxxxx_028";
//		Vin2VinComparePage(driver, xx,tc);
//		
//		tc = "TCxxxx_028";
//		Vin2VinComparePage(driver, xx,tc);
		
					
		
		
		for (int i = 1; i <= 6; i++) {}
		tc = "TCxxxx_23";
//		studyPriceDemoP.clickYearArrow(driver, tc);
//			studyPriceDemoP
//			studyPriceDemoP
//			studyPriceDemoP
	}


	public static void main(String[] args) throws Exception {
		// Load environment parameters
		Properties prop = new Properties();
		try {
			prop.load(vinCompareUIController.class.getClassLoader()
					.getResourceAsStream("./data/compare.properties"));// "./main.properties";
		} catch (IOException e) {
			e.printStackTrace();
		}
		String env = prop.getProperty("Compare.environment");
		String tBrowser = prop.getProperty("Compare.browser");
		String envDevice = prop.getProperty("Compare.envDevice");
		String onScreen = prop.getProperty("Compare.onScreen");
		String VinCompareUIURL = prop.getProperty(env+".VinCompareUIURL");
		String vins[]=fetchOneDemArrayFromPropFile(env+".CompareVINs",prop);
		for (int i = 0; i < 1; i++) {
			System.out.println("Testing is started in " + env + "\n");
			// Initial
			Comlibs log = new Comlibs();
			final WebDriver driver;
			driver = log.drivers(tBrowser);// Firefox, Chrome
			driver.manage().deleteAllCookies();
			System.out.println("Test Browser = " + tBrowser + "\n");

			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			if (!tBrowser.equalsIgnoreCase("Chromexxxxxxxxx")) {
				log.SelecBroswerResolution(driver, envDevice, onScreen);
			}
			log.rwExcel("", "****** Testing started ******" + (i + 1), "");
			log.rwExcel("", "Test Browser", tBrowser);
			log.rwExcel("", "Test Environment", env);

			loadURL(driver, VinCompareUIURL);
			String vin1=vins[0];
			//// 1. Study Price Demo UI Home page
			log.rwExcel("", "-----VIN Compare Tester Tool UI page Testing started-----" + (i + 1), "");
//			1. ***********VIN Compare**************
			Vin2VinCompareTcs(driver, tBrowser, env);
//			 ***********VIN Compare**************
			
			
			
			
			log.rwExcel("", "****** Testing is complete ****** " + (i + 1), "");
			driver.close();
			System.out.println("Test is complete!!!   i = " + (i + 1));
		}
		return;
	}
}
