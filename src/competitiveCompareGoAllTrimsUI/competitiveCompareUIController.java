package competitiveCompareGoAllTrimsUI;

//
/**
* @author Zhoul
* Initial date:  -2020-10-16
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

import javax.mail.MessagingException;

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

import Mail.SendEmail;

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
		// Below to accept authentication only works for Firefox, Chrome scripts are not
		// ready yet. 2018-11-06.
		// Don't need below anymore since username and password embeded into the bURL
		// that works. 2019-03-19.
		// if (env.equalsIgnoreCase("Prod")) {
		// Thread.sleep(2 * 1000);
		// driver.switchTo().alert().sendKeys("admin" + Keys.TAB + "g4TT73Xy!");
		// driver.switchTo().alert().accept();//.dismiss()
//		driver.switchTo().alert().accept();//.dismiss() 
		// }
		if (bURL.contains("mitsubishi") && !(bURL.contains("https://compare.autodatadirect.com"))) {// compare.autodatadirect.com is Prod URL
			driver.switchTo().alert().dismiss();
		}

		return new SelectVehicle(driver);
	}

	public static SelectVehicle loadURLOld(WebDriver driver, String bURL) throws IOException {
		driver.get(bURL);
		return new SelectVehicle(driver);
	}

	private static String[] fetchOneDemArrayFromPropFile(String propertyName, Properties propFile) {
		// get array split up by the colin
		String a[] = propFile.getProperty(propertyName).split(",");
		return a;
	}

	public static void CompetitiveCompareMonitor(WebDriver driver, String brw, String envment, String brand)
			throws Exception {
		// Load environment parameters
		Properties prop = new Properties();
		try {
			prop.load(competitiveCompareUIController.class.getClassLoader()
					.getResourceAsStream("./data/competitiveCompareGoAllTrims.properties"));// "./main.properties";
		} catch (IOException e) {
			e.printStackTrace();
		}

		String env = envment;
		String Years[] = fetchOneDemArrayFromPropFile(env + ".Years", prop);
		String expectedPrimaryPrice = prop.getProperty(env + "." + brand + ".expectedPrimaryPrice");
		int wt = Integer.parseInt(prop.getProperty("CompetitiveCompare.waitTime"));
		String only1stTrim = prop.getProperty("CompetitiveCompare.only1stTrims");
		// Initial
		String tc;
		String modelNameS = "";
		int trimNumber = 0;
		String trimNameS = "";
		String urlString = "";
		String currentClientURL = "";
		Comlibs log = new Comlibs();
		log.rwExcel("", "********* " + brand + " Competitive Compare UI**********", "");
		SelectVehicle SelectVehiclePage = new SelectVehicle(driver);

		tc = brand + " - Click On Got It";
		SelectVehiclePage.clickOnGotIt(driver, tc);

		for (String Year : Years) {
			tc = env + " - " + brand + " - Select year: " + Year;
			SelectVehiclePage.selectYear(driver, Year, tc);
			tc = Year + " - " + brand + " - CountVehicleArray";
			// Select first type and first vehicle: 1,1. Select second type and first vehicle 2,1
			int vehicleArry[];
			log.Wait(wt);
			vehicleArry = SelectVehiclePage.countVehicleArray(driver, tc);
			int groupArray = vehicleArry.length;
			System.out.println("Vehicle Array length = " + groupArray + " \n\n");
			for (int i = 1; i <= groupArray; i++) {
				log.Wait(wt);
				for (int v = 1; v <= vehicleArry[i - 1]; v++) {
////				//Debug
//					i = 3;
//					v = 1;

					currentClientURL = driver.getCurrentUrl();
					tc = env + " - " + brand + " - Select Year = " + Year;
					SelectVehiclePage.selectYear(driver, Year, tc);
					log.Wait(wt);
					SelectVehiclePage.clickOnVehicle(driver, i, v, tc);
					tc = brand + " - getModelName";
					log.Wait(wt * 2);
					boolean check1stTrimExist = SelectVehiclePage.checkFirstTrimExist(driver, tc);
					boolean check2ndTrimExist = SelectVehiclePage.checkSecondTrimExist(driver, tc);
					trimNumber = SelectVehiclePage.getTrimNumber(driver, env, brand, tc);
					SelectVehiclePage.clickOnTrimOld_1st_OK(driver, env, brand, currentClientURL);
					Compare ComparePage = new Compare(driver);
					log.Wait(wt);
					loadURLOld(driver, currentClientURL);
////				trimNumber=1; //only go through just 1st trim
					if (only1stTrim.equalsIgnoreCase("Yes")) {
						trimNumber = 1;
					}
					for (int trim = 1; trim <= trimNumber; trim++) {
						try {
							currentClientURL = driver.getCurrentUrl();
							tc = env + " - " + brand + " - Select Year = " + Year;
							SelectVehiclePage.selectYear(driver, Year, tc);
							log.Wait(wt);
							SelectVehiclePage.clickOnVehicle(driver, i, v, tc);
							tc = brand + " - getModelName";
							log.Wait(wt * 2);

							modelNameS = SelectVehiclePage.getModelName(driver, tc);
							tc = brand + " - " + modelNameS;
//							tc = tc + " - getTrimName ";
							if (trim == 1) {
								// if 1st by does not exist, num=num+2
								trimNameS = SelectVehiclePage.getTrimName(driver, env, brand, trim, check1stTrimExist,
										check2ndTrimExist, tc);// take 2nd which is wrong
								tc = tc + " - " + trimNameS;
								SelectVehiclePage.clickOnTrimOld_1st_OK(driver, env, brand, currentClientURL);
							} else {
								trimNameS = SelectVehiclePage.getTrimName(driver, env, brand, trim, check1stTrimExist,
										check2ndTrimExist, tc);
								tc = tc + " - " + trimNameS;
								SelectVehiclePage.clickOnTrimNewAllTrims(driver, env, brand, trim, check1stTrimExist,
										check2ndTrimExist, currentClientURL);
							}

							tc = brand + " - Click on Trim - " + modelNameS;
							log.Wait(wt);
							urlString = driver.getCurrentUrl() + " \n\n " + "group = " + i + ". vehicle = " + v + "\n "
									+ modelNameS + " - " + trimNameS;
							tc = env + " - " + brand + " - VerifyPrimaryImage - " + trimNameS;
							log.Wait(wt * 3);
//							ComparePage.verifyPrimaryImage(driver, env, brand, urlString + "\n\n" + tc, tc);
//							log.Wait(wt);
							tc = env + " - " + brand + " - VerifyPrimaryStartingFromPrice - " + modelNameS + " - "
									+ trimNameS;

							ComparePage.verifyPrimaryStartingFromPrice(driver, env, brand, urlString + "\n\n" + tc,
									expectedPrimaryPrice, tc);
							log.Wait(wt);
							tc = env + " - " + brand + " - Click on Newe Compare";
							try {
								ComparePage.clickOnNewCompare(driver, tc);
							} catch (Exception e) {
								loadURLOld(driver, currentClientURL);
								SelectVehicle SelectVehiclePageAgain = new SelectVehicle(driver);
								tc = brand + " - Click On Got It Again after loading the URL!";
								try {
									SelectVehiclePageAgain.clickOnGotIt(driver, tc);
								} catch (Exception ee) {
									System.out.println(
											brand + " - Click On Got It Button does not show after loading the URL!");
								}
								log.Wait(wt * 4);
							}
							// trim catch
						} catch (Exception e) {
							System.out.println(
									"\n***********Failed to click on the trim! need to send alert email?*******\n");
							loadURLOld(driver, currentClientURL);
						}

					}

				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		// Load environment parameters
		Properties prop = new Properties();
		try {
			prop.load(competitiveCompareUIController.class.getClassLoader()
					.getResourceAsStream("./data/competitiveCompareGoAllTrims.properties"));//
		} catch (IOException e) {
			e.printStackTrace();
		}
//		String env = prop.getProperty("CompetitiveCompare.environment");
		String envs[] = fetchOneDemArrayFromPropFile("CompetitiveCompare.environment", prop);// prop.getProperty("CompetitiveCompare.environment");
		String tBrowser = prop.getProperty("CompetitiveCompare.browser");
		String envDevice = prop.getProperty("CompetitiveCompare.envDevice");
		String onScreen = prop.getProperty("CompetitiveCompare.onScreen");

		for (String env : envs) {

			String Clients[] = fetchOneDemArrayFromPropFile(env + ".Clients", prop);

			for (String brand : Clients) {

				String competitiveCompareUIUR = prop.getProperty(env + "." + brand + ".competitiveCompareUIURL");
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
				Comlibs log = new Comlibs();
				final WebDriver driver;
				driver = log.drivers(tBrowser);
				driver.manage().deleteAllCookies();
				// i=3: run all 3 devices
				for (int i = 0; i < 1; i++) {
					try {
						System.out.println("Testing is started in " + env + "\n");
						System.out.println("Test Client = " + brand + "\n");
						System.out.println("Test Browser = " + tBrowser + "\n");
						System.out.println("Test Device = " + Devices[i] + "\n");

//						driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
						if (!tBrowser.equalsIgnoreCase("Chromexxxxxxxxx")) {
							log.SelecBroswerResolution(driver, Devices[i], onScreen);
						}
						log.rwExcel("", "****** Testing started ******" + (i + 1), "");
						log.rwExcel("", "Test Browser", tBrowser);
						log.rwExcel("", "Test Environment", env);
						log.rwExcel("", "Test Devicer", Devices[i]);

						loadURL(driver, competitiveCompareUIUR);
						log.Wait(5);

						//// 1.Competitive Compare page
						log.rwExcel("", "-----" + brand + " Competitive Compare page Testing started-----" + (i + 1),
								"");
						// 1. ***********Competitive Compare**************
						CompetitiveCompareMonitor(driver, tBrowser, env, brand);
						// ***********Competitive Compare**************

						log.rwExcel("", "****** Testing is complete ****** " + (i + 1), "");
						driver.quit();//driver.quit(), driver.close()
						System.out.println(env + " - " + brand + " - Test is complete!!!   i = " + (i + 1) + "\n");
					} catch (Exception e) {
						System.out.println("Test Client = " + brand + "\n");
						System.out.println("Test Browser = " + tBrowser + "\n");
						System.out.println("Test Device = " + Devices[i] + "\n");
						System.out.println("\n\nAlert!!!!\n\n");
						System.out
								.println("\n\n" + env + " - " + brand + " - Site is not loaded properly or down!\n\n");
						log.rwExcel(env + " - " + brand, false, brand + " - Site is not loaded properly",
								brand + " site maybe is showing error or down.");
						SendEmail alertEmail = new SendEmail();
						alertEmail.SendAlertEmail(env, brand, competitiveCompareUIUR,
								"Site is not loaded properly or down!");
						try {
							driver.quit();//driver.quit(), driver.close();
						} catch (Exception ee) {
							System.out.println("\n\nAlert!!!!\n\n");
							System.out.println("\nBrowser cannot be closed!\n");
						}
					}
				}
			}
		}
		System.out.println("*****************All Tests are done!!!*****************" + "\n");
		return;
	}
}