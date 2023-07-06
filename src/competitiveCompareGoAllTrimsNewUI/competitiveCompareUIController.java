package competitiveCompareGoAllTrimsNewUI;

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
import java.io.FileNotFoundException;
import java.io.FileReader;
//Test updated 02
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.mail.MessagingException;
import javax.swing.JOptionPane;

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

		String sPageTitle = "Select Vehicle";
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
	 * ------------------------------ Home Page Object Repositories:
	 * ------------------------------
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
//		if (bURL.contains("mitsubishi") && !(bURL.contains("https://compare.autodatadirect.com")  
//				| bURL.contains("https://uat-compare.autodatadirect.com")    //Staging public
//				| bURL.contains("http://uat-compare.sharedf.autodata.tech/") //Staging chaska
//				| bURL.contains("http://uat-compare.sharedp.autodata.tech/") //Staging hillsboro
//				| bURL.contains("http://prod-compare.sharedf.autodata.tech") //Prod chaska
//				| bURL.contains("http://prod-compare.sharedp.autodata.tech") //Prod hillsboro
//				| bURL.contains("prod-compare.product-london-test"))) {      // compare.autodatadirect.com
//			// is Prod URL
////			try {
//			driver.switchTo().alert().dismiss();
////			}catch (Exception e) {
////				System.out.println("\nCancel buttom (dismiss) does not exist. Failed to click it!\n");
////			}	
//		}

		return new SelectVehicle(driver);
	}

	public static void loadURLinCompare(WebDriver driver, String bURL) throws IOException {
		try {
			driver.get(bURL);
		} catch (Exception e) {
			// cannot load
			System.out.println("\nLoad URL failed!\n");
			Comlibs log = new Comlibs();
			log.rwExcel(bURL, false, bURL, "Load URL failed");
		}

//		if (bURL.contains("mitsubishi") && !(bURL.contains("https://compare.autodatadirect.com"))) {// compare.autodatadirect.com
//																									// is Prod URL
//			try {
//				driver.switchTo().alert().dismiss();
//			} catch (Exception e) {
//				System.out.println("\nCancel buttom (dismiss) does not exist. Failed to click it!\n");
//			}
//		}

//		return new Compare(driver);
	}

	public static SelectVehicle loadURLOld(WebDriver driver, String bURL) throws IOException {
		driver.get(bURL);
//		if (bURL.contains("mitsubishi") && !(bURL.contains("https://compare.autodatadirect.com")  
//				| bURL.contains("https://uat-compare.autodatadirect.com")    //Staging public
//				| bURL.contains("http://uat-compare.sharedf.autodata.tech/") //Staging chaska
//				| bURL.contains("http://uat-compare.sharedp.autodata.tech/") //Staging hillsboro
//				| bURL.contains("http://prod-compare.sharedf.autodata.tech") //Prod chaska
//				| bURL.contains("http://prod-compare.sharedp.autodata.tech") //Prod hillsboro
//				| bURL.contains("prod-compare.product-london-test"))) {      // compare.autodatadirect.com
//				// is Prod URL
//			try {
//				driver.switchTo().alert().dismiss();
//			} catch (Exception e) {
//				System.out.println("\nCancel buttom (dismiss) does not exist. Failed to click it!\n");
//			}
//		}

		return new SelectVehicle(driver);
	}

	private static String[] fetchOneDemArrayFromPropFile(String propertyName, Properties propFile) {
		// get array split up by the colin
		String a[] = propFile.getProperty(propertyName).split(",");
		return a;
	}

	private static String[] readTextData(String environment, String filename) throws IOException {
		Scanner sc = new Scanner(new File(filename));
		List<String> lines = new ArrayList<String>();
		while (sc.hasNextLine()) {
			lines.add(sc.nextLine());
		}
		String a[] = lines.toArray(new String[0]);
		return a;
	}

	public void loadFromDataPath(String filename) throws Exception {
		boolean loadFromClasspath = true;
		String fileName = filename; // provide an absolute path here to be sure that file is found
		BufferedReader reader = null;
		try {

			if (loadFromClasspath) {
				// loading from classpath
				// see the link above for more options
				InputStream in;
//				try {
				in = getClass().getClassLoader().getResourceAsStream("./data_new_UI/" + fileName);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} 
				reader = new BufferedReader(new InputStreamReader(in));
			} else {
				// load from file system
				reader = new BufferedReader(new FileReader(new File(fileName)));
			}

			String line = null;
			while ((line = reader.readLine()) != null) {
				// do something with the line here
				System.out.println("Line read: " + line);
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage() + " for lol.txt", "File Error",
					JOptionPane.ERROR_MESSAGE);
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
	}

	public static void CompetitiveCompareMonitor(WebDriver driver, String brw, String envment, String brand)
			throws Exception {
		// Load environment parameters
		Properties prop = new Properties();
		try {
			prop.load(competitiveCompareUIController.class.getClassLoader()
					.getResourceAsStream("./data_new_UI/competitiveCompareGoAllTrims.properties"));// "./main.properties";
		} catch (IOException e) {
			e.printStackTrace();
		}

		String env = envment;
		String Years[] = fetchOneDemArrayFromPropFile(env + ".Years", prop);
		String expectedPrimaryPrice = prop.getProperty(env + "." + brand + ".expectedPrimaryPrice");
		int wt = Integer.parseInt(prop.getProperty("CompetitiveCompare.waitTime"));
		String only1stTrim = prop.getProperty("CompetitiveCompare.only1stTrims");
		String onlyOneTrimOneBrand = prop.getProperty("CompetitiveCompare.onlyOneTrimOneBrand");
		// Initial
		String tc;
		String modelName = "";
		String modelNameS = "";
		int trimNumber = 0;
		int trimsNumbers = 0;
		String trimNameS = "";
		String urlString = "";
		String currentClientURL = "";
		String thisClientURL = "";
		String modelNameLowHighMSRPs = "";
		Comlibs log = new Comlibs();
		log.rwExcel("", "********* " + brand + " Competitive Compare UI**********", "");
		SelectVehicle SelectVehiclePage = new SelectVehicle(driver);
		tc = brand + " - Click On Got It";
		SelectVehiclePage.clickOnGotIt(driver, tc);//Issue in new UI

		for (String Year : Years) {
			tc = env + " - " + brand + " - Select year: " + Year;
			SelectVehiclePage.selectYear(driver, Year, tc);
			tc = Year + " - " + brand + " - CountVehicleArray";
			// Select first type and first vehicle: 1,1. Select second type and first
			// vehicle 2,1
			int vehicleArry[];
			log.Wait(wt);

			int groupArray = 0;

			int vehicleLength = 0;

			if (onlyOneTrimOneBrand.equalsIgnoreCase("Yes")) {
				vehicleArry = SelectVehiclePage.countVehicleArray(driver, tc);
				vehicleLength = vehicleArry.length;

				if (vehicleArry[0] == 0) {
					vehicleArry[1] = 1;
					groupArray = 2;
				} else {
					vehicleArry[0] = 1;
					groupArray = 1;
				}

			} else {
				vehicleArry = SelectVehiclePage.countVehicleArray(driver, tc);
				groupArray = vehicleArry.length;
			}
			System.out.println("Vehicle Array length = " + groupArray + " \n\n");
			for (int i = 1; i <= groupArray; i++) {
				log.Wait(wt);
////			// Debug
//				if (i==4) {
//				//stop
//					System.out.println("Stop here for any group!!!");
//				}
//				i=4;

				for (int v = 1; v <= vehicleArry[i - 1]; v++) {
////				//Debug 									// *************************
//					i = 1; // UI first =1 not 0
//					v = 8;

//					if (v ==6) {
//						//stop
//							System.out.println("Stop here for 6th vehicle!!!");
//						}

					currentClientURL = driver.getCurrentUrl();
					thisClientURL = currentClientURL;
					tc = env + " - " + brand + " - Select Year = " + Year;
					log.Wait(wt);
					SelectVehiclePage.selectYear(driver, Year, tc);
					log.Wait(wt);
					modelNameLowHighMSRPs = SelectVehiclePage.getModelLowHighMSRPs(driver, env, brand, i, v,
							currentClientURL);
					modelName = SelectVehiclePage.getModelName(driver, env, brand, i, v, currentClientURL);
					SelectVehiclePage.verifyLowHighMSRPs(driver, env, brand, modelName, thisClientURL,
							modelNameLowHighMSRPs, tc,i,v);
					System.out.println("\n getModelName = " + modelName + "\n");
					SelectVehiclePage.clickOnVehicle(driver, i, v, tc);
//					tc = brand + " - getModelName";
					tc = brand + " - Model Name - " + modelName;
					log.Wait(wt * 3);
					boolean check1stTrimExist = SelectVehiclePage.checkFirstTrimExist(driver, tc);
					boolean check2ndTrimExist = SelectVehiclePage.checkSecondTrimExist(driver, tc);
					trimNumber = SelectVehiclePage.getTrimNumber(driver, env, brand, currentClientURL, tc);
					if (trimNumber == 0) {
						log.rwExcel(tc, false, "Click on Year = " + Year + " vehicle v = " + v + " - not working!",
								"Trims pop-up not showing!");
						driver.get("http://www.google.com");
						log.Wait(2);
						loadURLOld(driver, currentClientURL);
						continue;
					}
					SelectVehiclePage.clickOnTrimOld_1st_OK(driver, env, brand, currentClientURL);
					Compare ComparePage = new Compare(driver);
					log.Wait(wt);
					loadURLOld(driver, currentClientURL);
////				trimNumber=1; //only go through just 1st trim
					if (only1stTrim.equalsIgnoreCase("Yes")) {
						trimNumber = 1;
					}
					for (int trim = 1; trim <= trimNumber; trim++) {// *************************
						try {
							currentClientURL = driver.getCurrentUrl();
							tc = env + " - " + brand + " - Select Year = " + Year;
							log.Wait(wt);
							SelectVehiclePage.selectYear(driver, Year, tc);
							log.Wait(wt);
							modelName = SelectVehiclePage.getModelName(driver, env, brand, i, v, currentClientURL);
							tc = tc + " -. Model Name " + modelName;
							SelectVehiclePage.clickOnVehicle(driver, i, v, tc);
							tc = brand + " - getModelName";
							trimsNumbers = SelectVehiclePage.getTrimNumber(driver, env, brand, currentClientURL, tc);
							log.Wait(wt * 3);

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
							currentClientURL = driver.getCurrentUrl();
							ComparePage.checkFeatturesPageshowOrNot(driver, currentClientURL, tc);
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
							log.Wait(2);
							ComparePage.verifyPrimaryStartingFromPrice(driver, env, brand, urlString + "\n\n" + tc,
									expectedPrimaryPrice, tc);
							log.Wait(wt);
							tc = env + " - " + brand + " - Click on New Compare";
							try {
								ComparePage.clickOnNewCompare(driver, tc);
							} catch (Exception e) {
								driver.get("http://www.google.com");
								loadURLOld(driver, thisClientURL);
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
							loadURLOld(driver, thisClientURL);
						}

					}

				}
			}
		}
	}

	public static void CompetitiveCompareGridValues(WebDriver driver, String brw, String envment, String brand)
			throws Exception {
		// Load environment parameters
		Properties prop = new Properties();
		try {
			prop.load(competitiveCompareUIController.class.getClassLoader()
					.getResourceAsStream("./data_new_UI/competitiveCompareGoAllTrims.properties"));// "./main.properties";
		} catch (IOException e) {
			e.printStackTrace();
		}

		String env = envment;
		String Years[] = fetchOneDemArrayFromPropFile(env + ".Years", prop);
		String expectedPrimaryPrice = prop.getProperty(env + "." + brand + ".expectedPrimaryPrice");
		int wt = Integer.parseInt(prop.getProperty("CompetitiveCompare.waitTime"));
		String only1stTrim = prop.getProperty("CompetitiveCompare.only1stTrims");
		String onlyOneTrimOneBrand = prop.getProperty("CompetitiveCompare.onlyOneTrimOneBrand");
		// Initial
		String tc;
		String modelName = "";
		String modelName_Secondary_01 = "";
		String modelName_Secondary_02 = "";
		String modelName_Secondary_03 = "";
		String modelNameS = "";
		int trimNumber = 0;
		int trim_count = 0;
		int trimsNumbers = 0;
		String trimNameS = "";
		String urlString = "";
		String currentClientURL = "";
		String thisClientURL = "";
		String categories[] = { "Pricing", "FUEL ECONOMY", "EXTERIOR FEATURES", "COMFORT", "CONVENIENCE", "LIGHTING",
				"INFOTAINMENT", "MECHANICAL", "SAFETY", "DIMENSIONS", "WARRANTY" };

		Comlibs log = new Comlibs();
		log.rwExcel("", "********* " + brand + " Competitive Compare UI**********", "");
		SelectVehicle SelectVehiclePage = new SelectVehicle(driver);
		tc = brand + " - Click On Got It";
		SelectVehiclePage.clickOnGotIt(driver, tc);

		for (String Year : Years) {
			tc = env + " - " + brand + " - Select year: " + Year;
			SelectVehiclePage.selectYear(driver, Year, tc);
			tc = Year + " - " + brand + " - CountVehicleArray";
			// Select first type and first vehicle: 1,1. Select second type and first
			// vehicle 2,1
			int vehicleArry[];
			log.Wait(wt);

			int groupArray = 0;

			int vehicleLength = 0;

			if (onlyOneTrimOneBrand.equalsIgnoreCase("Yes")) {
				vehicleArry = SelectVehiclePage.countVehicleArray(driver, tc);
				vehicleLength = vehicleArry.length;

				if (vehicleArry[0] == 0) {
					vehicleArry[1] = 1;
					groupArray = 2;
				} else {
					vehicleArry[0] = 1;
					groupArray = 1;
				}

			} else {
				vehicleArry = SelectVehiclePage.countVehicleArray(driver, tc);
				groupArray = vehicleArry.length;
			}
			System.out.println("Vehicle Array length = " + groupArray + " \n\n");
			for (int i = 1; i <= groupArray; i++) {
				log.Wait(wt);
				for (int v = 1; v <= vehicleArry[i - 1]; v++) {
////				//Debug 									// *************************
//					i = 4;
//					v = 1;

					currentClientURL = driver.getCurrentUrl();
					thisClientURL = currentClientURL;
					tc = env + " - " + brand + " - Select Year = " + Year;
					log.Wait(wt);
					SelectVehiclePage.selectYear(driver, Year, tc);
					log.Wait(wt);
					modelName = SelectVehiclePage.getModelName(driver, env, brand, i, v, currentClientURL);
					System.out.println("\n getModelName = " + modelName + "\n");
					SelectVehiclePage.clickOnVehicle(driver, i, v, tc);
//					tc = brand + " - getModelName";
					tc = brand + " - Model Name - " + modelName;
					log.Wait(wt * 3);
					boolean check1stTrimExist = SelectVehiclePage.checkFirstTrimExist(driver, tc);
					boolean check2ndTrimExist = SelectVehiclePage.checkSecondTrimExist(driver, tc);
					trimNumber = SelectVehiclePage.getTrimNumber(driver, env, brand, currentClientURL, tc);
					if (trimNumber == 0) {
						log.rwExcel(tc, false, "Click on Year = " + Year + " vehicle v = " + v + " - not working!",
								"Trims pop-up not showing!");
						driver.get("http://www.google.com");
						log.Wait(2);
						loadURLOld(driver, currentClientURL);
						continue;
					}
					SelectVehiclePage.clickOnTrimOld_1st_OK(driver, env, brand, currentClientURL);
					Compare ComparePage = new Compare(driver);
					log.Wait(wt);
					loadURLOld(driver, currentClientURL);
////				trimNumber=1; //only go through just 1st trim
					if (only1stTrim.equalsIgnoreCase("Yes")) {
						trimNumber = 1;
					}
					for (int trim = 1; trim <= trimNumber; trim++) {// *************************
						try {
							trim_count=trim_count+1;
							System.out.println("Trim_count = " + trim_count + "\n");
							currentClientURL = driver.getCurrentUrl();
							tc = env + " - " + brand + " - Select Year = " + Year;
							log.Wait(wt);
							SelectVehiclePage.selectYear(driver, Year, tc);
							log.Wait(wt);
							modelName = SelectVehiclePage.getModelName(driver, env, brand, i, v, currentClientURL);
							tc = tc + " -. Model Name " + modelName;
							SelectVehiclePage.clickOnVehicle(driver, i, v, tc);
							tc = brand + " - getModelName";
							trimsNumbers = SelectVehiclePage.getTrimNumber(driver, env, brand, currentClientURL, tc);
							log.Wait(wt * 3);

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
							currentClientURL = driver.getCurrentUrl();
							boolean featuresPageExist = ComparePage.checkFeatturesPageshowOrNotForGridValues(driver,
									currentClientURL, tc);
							if (featuresPageExist) {

								tc = brand + " - Click on Trim - " + modelNameS;
								log.Wait(wt);
								urlString = driver.getCurrentUrl() + "| " + "group = " + i + ". vehicle = " + v + "|"
										+ modelNameS + " - " + trimNameS;
								tc = env + " - " + brand + " - VerifyPrimaryImage - " + trimNameS;
								log.Wait(wt * 3);
//							ComparePage.verifyPrimaryImage(driver, env, brand, urlString + "\n\n" + tc, tc);
//							log.Wait(wt);
								tc = env + " - " + brand + " - VerifyPrimaryStartingFromPrice - " + modelNameS + " - "
										+ trimNameS;
								ComparePage.clickAvailableFeatures(driver, env, tc);
								int x = 0;
								// for (int x = 1; x <= 3; x++) {
								for (String category : categories) {
									x = x + 1;
									int categoryrows = ComparePage.getCategoryRowsFromName(category);
//								for (int x = 1; x <= 2; x++) {
									for (int y = 1; y <= categoryrows; y++) {
										ComparePage.get_grid_one_row_values(driver, env, brand, urlString, category, x,
												y, tc,trim_count);
//									}
									}
								}
								log.Wait(wt);
								tc = env + " - " + brand + " - Click on New Compare";
								try {
									ComparePage.clickOnNewCompare(driver, tc);
								} catch (Exception e) {
									driver.get("http://www.google.com");
									loadURLOld(driver, thisClientURL);
									SelectVehicle SelectVehiclePageAgain = new SelectVehicle(driver);
									tc = brand + " - Click On Got It Again after loading the URL!";
									try {
										SelectVehiclePageAgain.clickOnGotIt(driver, tc);
									} catch (Exception ee) {
										System.out.println(brand
												+ " - Click On Got It Button does not show after loading the URL!");
									}
									log.Wait(wt * 4);
								}
								// trim catch
							} else {
								driver.get("http://www.google.com");
								loadURLOld(driver, thisClientURL);
//							SelectVehicle SelectVehiclePageAgain = new SelectVehicle(driver);
//							tc = brand + " - Click On Got It Again after loading the URL!";
//							try {
//								SelectVehiclePageAgain.clickOnGotIt(driver, tc);
//							} catch (Exception ee) {
//								System.out.println(
//										brand + " - Click On Got It Button does not show after loading the URL!");
//							}
//							log.Wait(wt*2);
							}
						} catch (Exception e) {
							System.out.println(
									"\n***********Failed to click on the trim! need to send alert email?*******\n");
							loadURLOld(driver, thisClientURL);
						}

					}

				}
			}
		}
	}

	public static void ReLoadFailedURLs(WebDriver driver, String envment) throws Exception {
		// Load environment parameters
		Properties prop = new Properties();
		try {
			prop.load(competitiveCompareUIController.class.getClassLoader()
					.getResourceAsStream("./data_new_UI/competitiveCompareGoAllTrims.properties"));// "./main.properties";
		} catch (IOException e) {
			e.printStackTrace();
		}
		String expectedPrimaryPrice = prop.getProperty(envment + "." + "Kia" + ".expectedPrimaryPrice");
		String failedURLs[] = fetchOneDemArrayFromPropFile(envment + ".failedURLs", prop);

		int wt = Integer.parseInt(prop.getProperty("CompetitiveCompare.waitTime"));
		// Initial
		String env = envment;
		String tc;
		String modelName = "";
		String modelNameS = "";
		int trimNumber = 0;
		int trimsNumbers = 0;
		String trimNameS = "";
		String urlString = "";
		String currentClientURL = "";
		String thisClientURL = "";
		String brandURL = "";
		Comlibs log = new Comlibs();
		currentClientURL = "http://compare-qa1.product-london-test.autodata.tech/mazda/vehicle/";
		log.rwExcel("", "*********Competitive Compare URLs**********", "");
		loadURL(driver, currentClientURL);
		log.Wait(wt * 2);
		SelectVehicle SelectVehiclePage = new SelectVehicle(driver);
		log.Wait(wt * 2);
//		SelectVehiclePage.clickOnGotIt(driver, currentClientURL);
		currentClientURL = driver.getCurrentUrl();
		thisClientURL = currentClientURL;
		tc = " - Click On Got It";
//		SelectVehiclePage.clickOnGotIt(driver, tc);
		try {
			SelectVehiclePage.clickOnVehicle(driver, 1, 1, tc);
		} catch (Exception e) {
			System.out.println("\n **********Click on vehicle failed**********\n");
			SelectVehiclePage.clickOnVehicle(driver, 1, 1, tc);
		}

		log.Wait(wt);
		SelectVehiclePage.clickOnTrimOld_1st_OK(driver, thisClientURL, thisClientURL, currentClientURL);
		log.Wait(wt);
		tc = "Reload Failed url";
		log.Wait(wt);
		Compare ComparePage = new Compare(driver);
		ComparePage.clickOnNewCompare(driver, currentClientURL);
		log.Wait(wt);
		for (String failedURL : failedURLs) {// *************************
			try {
				currentClientURL = driver.getCurrentUrl();
//				tc = env + " - " + brand + " - Select Year = " + Year;
//				log.Wait(wt);
//				SelectVehiclePage.selectYear(driver, Year, tc);
//				log.Wait(wt);
//				modelName = SelectVehiclePage.getModelName(driver, env, brand, v, currentClientURL);
//				tc = tc + " -. Model Name " + modelName;
//				// SelectVehiclePage.clickOnVehicle(driver, i, v, tc);
				brandURL = failedURL;
				loadURLinCompare(driver, failedURL);
				currentClientURL = driver.getCurrentUrl();
				tc = failedURL;
				ComparePage.checkFeatturesPageshowOrNot(driver, currentClientURL, tc);
				tc = " - Click on Trim - " + modelNameS;
				// Get 2022 Kia
				// Get Model+Trim Name
				tc = ComparePage.getYearBrandModelTrim_Name(driver, currentClientURL, tc);

				log.Wait(wt);
				urlString = driver.getCurrentUrl() + " \n\n " + "group = . vehicle = \n " + modelNameS + " - "
						+ trimNameS;
//				tc = " - VerifyPrimaryImage - " + tc;
				log.Wait(wt * 3);
//							ComparePage.verifyPrimaryImage(driver, env, brand, urlString + "\n\n" + tc, tc);
//							log.Wait(wt);
				tc = " URL- VerifyPrimaryStartingFromPrice - " + tc;
				SelectVehiclePage.clickOnGotItIfItShows(driver, currentClientURL);
				ComparePage.verifyPrimaryStartingFromPrice(driver, env, " brand ", urlString + "\n\n" + tc,
						expectedPrimaryPrice, tc);
				log.Wait(wt);
				tc = " - Click on New Compare";
				try {
					ComparePage.clickOnNewCompareFrNotAutRunURL(driver, env, failedURL, tc);
				} catch (Exception e) {
					driver.get("http://www.google.com");
					loadURLOld(driver, thisClientURL);
					SelectVehicle SelectVehiclePageAgain = new SelectVehicle(driver);
					tc = " - Click On Got It Again after loading the URL!";
					try {
						SelectVehiclePageAgain.clickOnGotIt(driver, tc);
					} catch (Exception ee) {
						System.out.println(" - Click On Got It Button does not show after loading the URL!");
					}
					log.Wait(wt * 4);
				}
				// trim catch
			} catch (Exception e) {
				System.out.println("\n***********Failed to click on the trim! need to send alert email?*******\n");
				log.rwExcel(failedURL, false, "Try failed with this URL!", "Failed URL");
				loadURLOld(driver, thisClientURL);
			}

		}

	}

	public static void ReLoadAllURLs(WebDriver driver, String envment) throws Exception {
		competitiveCompareUIController loadTextData = new competitiveCompareUIController(driver, envment);
		String failedURLs[] = loadTextData.loadTextFromDataFolder(envment, "./data_new_UI/" + envment + "AllURLs.txt");
		// Load environment parameters
		Properties prop = new Properties();
		try {
			prop.load(competitiveCompareUIController.class.getClassLoader()
					.getResourceAsStream("./data_new_UI/competitiveCompareGoAllTrims.properties"));// "./main.properties";
		} catch (IOException e) {
			e.printStackTrace();
		}
		String expectedPrimaryPrice = prop.getProperty(envment + "." + "Kia" + ".expectedPrimaryPrice");
//		String failedURLs[] = fetchOneDemArrayFromPropFile(envment + ".failedURLs", prop);
//		String failedURLs[] = 
		int wt = Integer.parseInt(prop.getProperty("CompetitiveCompare.waitTime"));
		// Initial
		String env = envment;
		String tc;
		String modelName = "";
		String modelNameS = "";
		int trimNumber = 0;
		int trimsNumbers = 0;
		String trimNameS = "";
		String urlString = "";
		String currentClientURL = "";
		String thisClientURL = "";
		String brandURL = "";
		Comlibs log = new Comlibs();
		currentClientURL = "https://compare.autodatadirect.com/subaru/ca/vehicle/#/select/primary/compare";
		log.rwExcel("", "*********Competitive Compare URLs**********", "");
		loadURL(driver, currentClientURL);
		log.Wait(wt * 2);
		SelectVehicle SelectVehiclePage = new SelectVehicle(driver);
		log.Wait(wt * 2);
		SelectVehiclePage.clickOnGotIt(driver, currentClientURL);
		currentClientURL = driver.getCurrentUrl();
		thisClientURL = currentClientURL;
		tc = " - Click On Got It";
//		SelectVehiclePage.clickOnGotIt(driver, tc);
		try {
			SelectVehiclePage.clickOnVehicle(driver, 2, 1, tc);
		} catch (Exception e) {
			System.out.println("\n **********Click on vehicle failed**********\n");
			SelectVehiclePage.clickOnVehicle(driver, 1, 1, tc);
		}

		log.Wait(wt);
		SelectVehiclePage.clickOnTrimOld_1st_OK(driver, thisClientURL, thisClientURL, currentClientURL);
		log.Wait(wt);
		tc = "Reload Failed url";
		log.Wait(wt);
		Compare ComparePage = new Compare(driver);
		ComparePage.clickOnNewCompare(driver, currentClientURL);
		log.Wait(wt);
		for (String failedURL : failedURLs) {// *************************
			try {
				currentClientURL = driver.getCurrentUrl();
//				tc = env + " - " + brand + " - Select Year = " + Year;
//				log.Wait(wt);
//				SelectVehiclePage.selectYear(driver, Year, tc);
//				log.Wait(wt);
//				modelName = SelectVehiclePage.getModelName(driver, env, brand, v, currentClientURL);
//				tc = tc + " -. Model Name " + modelName;
//				// SelectVehiclePage.clickOnVehicle(driver, i, v, tc);
				brandURL = failedURL;
				loadURLinCompare(driver, failedURL);
				currentClientURL = driver.getCurrentUrl();
				tc = failedURL;
				ComparePage.checkFeatturesPageshowOrNot(driver, currentClientURL, tc);
				tc = " - Click on Trim - " + modelNameS;
				// Get 2022 Kia
				// Get Model+Trim Name
				tc = ComparePage.getYearBrandModelTrim_Name(driver, currentClientURL, tc);

				log.Wait(wt);
				urlString = driver.getCurrentUrl() + " \n\n " + "group = . vehicle = \n " + modelNameS + " - "
						+ trimNameS;
//				tc = " - VerifyPrimaryImage - " + tc;
				log.Wait(wt * 3);
//							ComparePage.verifyPrimaryImage(driver, env, brand, urlString + "\n\n" + tc, tc);
//							log.Wait(wt);
				tc = " URL- VerifyPrimaryStartingFromPrice - " + tc;
				SelectVehiclePage.clickOnGotItIfItShows(driver, currentClientURL);
				ComparePage.verifyPrimaryStartingFromPrice(driver, env, " brand ", urlString + "\n\n" + tc,
						expectedPrimaryPrice, tc);
				log.Wait(wt);
				tc = " - Click on New Compare";
				try {
					ComparePage.clickOnNewCompareFrNotAutRunURL(driver, env, failedURL, tc);
				} catch (Exception e) {
					driver.get("http://www.google.com");
					loadURLOld(driver, thisClientURL);
					SelectVehicle SelectVehiclePageAgain = new SelectVehicle(driver);
					tc = " - Click On Got It Again after loading the URL!";
					try {
						SelectVehiclePageAgain.clickOnGotIt(driver, tc);
					} catch (Exception ee) {
						System.out.println(" - Click On Got It Button does not show after loading the URL!");
					}
					log.Wait(wt * 4);
				}
				// trim catch
			} catch (Exception e) {
				System.out.println("\n***********Failed to click on the trim! need to send alert email?*******\n");
				loadURLOld(driver, thisClientURL);
			}

		}

	}

	public String[] loadTextFromDataFolder(String env, String filePathName) throws IOException {
		boolean loadFromClasspath = true;
//		String fileName = "StagingAllURLs.txt"; // provide an absolute path here to be sure that file is found
		BufferedReader reader = null;
		String[] empty = { "" };
		try {

			if (loadFromClasspath) {
				InputStream in = getClass().getClassLoader().getResourceAsStream(filePathName);
				reader = new BufferedReader(new InputStreamReader(in));
			} else {
				// load from file system
				reader = new BufferedReader(new FileReader(new File(filePathName)));
			}
			List<String> liness = new ArrayList<String>();
			String line = null;
//	        String liness="";
			while ((line = reader.readLine()) != null) {
				// do something with the line here
				System.out.println("Line read: " + line);
				liness.add(line);
			}

			String urls[] = liness.toArray(new String[0]);
			int arrsize = urls.length;
			System.out.println("\nArrary lenght=" + arrsize);
			for (String url : urls) {
				System.out.println("URL=" + url);
			}
			System.out.println("\nArrary lenght=" + arrsize);
			return urls;
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage() + " for AllURLs.txt", "File Error",
					JOptionPane.ERROR_MESSAGE);
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
		return empty;
	}

	public static void main(String[] args) throws Exception {
		// Load environment parameters
		Properties prop = new Properties();
		try {
			prop.load(competitiveCompareUIController.class.getClassLoader()
					.getResourceAsStream("./data_new_UI/competitiveCompareGoAllTrims.properties"));//
		} catch (IOException e) {
			e.printStackTrace();
		}
//		String env = prop.getProperty("CompetitiveCompare.environment");
		String envs[] = fetchOneDemArrayFromPropFile("CompetitiveCompare.environment", prop);// prop.getProperty("CompetitiveCompare.environment");
		String tBrowser = prop.getProperty("CompetitiveCompare.browser");
		String envDevice = prop.getProperty("CompetitiveCompare.envDevice");
		String onScreen = prop.getProperty("CompetitiveCompare.onScreen");
		int wt = Integer.parseInt(prop.getProperty("CompetitiveCompare.waitTime"));

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
				// i=3: run all 3 devices. So far only run PC, since table and smartphone do not
				// load all models in loading page
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
						log.Wait(wt * 2);

						//// 1.Competitive Compare page
						log.rwExcel("", "----" + brand + " Competitive Compare page Testing started-----" + (i + 1),
								"");
//						// 1. ***********Competitive Compare**************
//						CompetitiveCompareMonitor(driver, tBrowser, env, brand);
//						// ***********Competitive Compare***************

						// 2. ***********Reload failed URLs on Competitive Compare***20230419_ok***********
						// Set only one client when running this to avoid multiple runs
//						ReLoadFailedURLs(driver, env);
						// ***********Reload failed URLs on Competitive Compare**************

						// 3. ***********CompetitiveCompareGridValues***20230419_ok***********
						CompetitiveCompareGridValues(driver, tBrowser, env, brand);
						// ***********Competitive Compare***************

						// 4. ***********Read all en,fr,es URLs from data/envAllURLs.txt on Competitive**20230419_ok********
						// Compare**************
//						ReLoadAllURLs(driver, env);

						// ***********read all en,fr,es URLs on Competitive Compare**************

						log.rwExcel("", "****** Testing is complete ****** " + (i + 1), "");
						driver.quit();// driver.quit(), driver.close()
						System.out.println(env + " - " + brand + " - Test is complete!!!   i = " + (i + 1) + "\n");
					} catch (Exception e) {
						System.out.println("Test Client = " + brand + "\n");
						System.out.println("Test Browser = " + tBrowser + "\n");
						System.out.println("Test Device = " + Devices[i] + "\n");
						System.out.println("\n\nAlert!!!!\n\n");
						System.out
								.println("\n\n" + env + " - " + brand + " - Site is not loaded properly or down!\n\n");
						log.rwExcel(env + " - " + brand, false, competitiveCompareUIUR,
								brand + " site maybe is showing error or down.");
						SendEmail alertEmail = new SendEmail();
						alertEmail.SendAlertEmail_NewUI(env, brand, competitiveCompareUIUR,
								"Site is not loaded properly or down!");
						try {
							driver.quit();// driver.quit(), driver.close();
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