package AdminPortal;

/**
* @author Zhoul
* Initial date: 
* Modified by ...
* replaced by replaced byreplaced by replaced by replaced by replaced by replaced by replaced by replaced by 
* replaced by replaced by replaced by replaced by replaced by replaced by replaced by replaced by replaced by replaced by replaced by replaced by 
* replaced by replaced by replaced by replaced by replaced by replaced by replaced by 
* replaced by replaced by replaced by replaced by replaced by replaced by replaced by replaced by replaced by replaced by replaced by   
 */
/*
 * replaced by replaced by replaced by replaced by replaced by replaced by replaced by replaced by 
 * replaced by replaced by replaced by replaced by replaced by replaced by replaced by replaced by
 * replaced by replaced by replaced by replaced by replaced by replaced by replaced by replaced by 
 * replaced by replaced by replaced by replaced by replaced by replaced by replaced by replaced by 
 *  
 */
import java.awt.AWTException;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
//Test updated 02
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.Key;
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

public class AdminPortalController extends Comlibs {
	static String[] vinpxConent = new String[200];
	static String[] stockpxConent = new String[200];
	static String[] lotpxConent = new String[200];
	final static int SINGLE_VIN_RENDER_MAX_WT = 10;
	final static int ALL_VINS_RENDER_MAX_WT = 20;

	public AdminPortalController(WebDriver driver, String myId) throws IOException {
		this.data.driver = driver;
		// String wh1=driver.getWindowHandle();

		String sPageTitle = "AUTOpx Login";
		boolean existTitle = TitleDisplay(driver, sPageTitle);
		if (existTitle)
			try {
				{
					rwExcel("", true, "Page Title is displayed", sPageTitle);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else {
			rwExcel("", false, "Page title is NOT showing properly. Exceed time limit!",
					"The page title is NOT - " + sPageTitle);
		}
		if (!sPageTitle.equals(driver.getTitle())) {
			// throw new IllegalStateException("The page title is NOT - "
			// + sPageTitle);
		}
	}

	AdminPortalControllerData data = new AdminPortalControllerData(0, By.id("dealerName"), By.id("dealerSite"),
			By.id("dealerTag"), By.id("dealerEmail"), By.id("dealerPhone"), By.id("accEmail"), By.id("userFirstName"),
			By.id("userLastName"), By.id("emailOptIn"), By.id("dealerAddr1"), By.id("dealerAddr2"), By.id("dealerCity"),
			By.id("dealerCountry"), By.id("dealerState"), By.id("dealerZip"), By.id("userPassword"),
			By.id("userConfirm"), By.id("saveBtn"), By.id("//img[@alt='Dealership Image']"), By.id("uploadLogo"),
			By.id("removeLogo"), By.id("vinpx"), By.id("lotpx"), By.id("stockpx"), By.id("//div[@id='bg-7']/div/img"));
	static String strHelpEmail = "contact@unityworksmedia.com"; // Prod:contact@unityworksmedia.com QA: tdautoaa@gmail.com
	static String strHelpTel = "1-800-293-2056";
	static int allVinNums = 0;
	static int allImageNums = 0;
	static String urlLink;

	public static VDVILogin loadURL(WebDriver driver, String bURL, String env) throws IOException {
		driver.get(bURL);
		// Below to accept authentication only works for Firefox, Chrome scripts are not ready yet. 2018-11-06
		if (env.equalsIgnoreCase("Prod")) {
			driver.switchTo().alert().sendKeys("admin" + Keys.TAB + "g4TT73Xy!");
			driver.switchTo().alert().accept();
		}
		;
		return new VDVILogin(driver);
	}

	public static void switchToWindow(WebDriver driver, String windowHandel) {
		Set<String> windowHandles = driver.getWindowHandles();
		System.out.println("Original WindowHandle=" + windowHandel);
		System.out.println("WindowHandles=" + windowHandles);

		for (String NewWindowHandle : windowHandles) {
			windowHandel = NewWindowHandle;
		}
		driver.switchTo().window(windowHandel);
	}

	public static void vehicleGallery(WebDriver driver, String brw, String envment)
			throws IOException, InterruptedException {
	}

	public static void inventoryGalleryTC(WebDriver driver, String brw, String envment, String ver)
			throws IOException, InterruptedException {
	}

	private static String[] fetchOneDemArrayFromPropFile(String propertyName, Properties propFile)
			throws IOException, InterruptedException {
		// get array split up by the colin
		String a[] = propFile.getProperty(propertyName).split(",");
		return a;
	}

	public static String getVehGUIDfromDealerCodeAndVIN(String dlrCode, String sVin)
			throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection conn = DriverManager.getConnection(
				"jdbc:sqlserver://LNOC-Q13V-MSQ1.autodata.org;user=VDVIWebServicesUserQA;password=HDuMy873JRFpkkU9;database=VDVI_Master");

		System.out.println("test");

		Statement sta = conn.createStatement();
		String Sql = "select dt01.DlrCode,vt01.VIN, vt01.VehGUID from DT01_Dealer as dt01 inner join VT01_DealerVehicles as vt01 on DT01.DlrGUID=VT01.DlrGUID where vt01.VIN=\'"
				+ sVin + "\' and dt01.DlrCode=\'" + dlrCode + "\'";
		String vGUID = "";
		ResultSet rs = sta.executeQuery(Sql);
		int icolumn = rs.getRow();
		while (rs.next()) {
			icolumn = rs.getRow();
			vGUID = rs.getString("VehGUID");
			System.out.println("Row =" + icolumn);
			System.out.println("Dealer Code = " + rs.getString("DlrCode") + "  VIN = " + rs.getString("VIN")
					+ "  VehGUID = " + rs.getString("VehGUID"));
		}
		if (icolumn == 1) {
			System.out.println("One VehGUID\n");
			// rs.getString("VehGUID");
			// String vGUID = rs.getString("VehGUID");
		} else {
			System.out.println("No any VehGUID or more than one\n");
			vGUID = "error!";
		}
		rs.close();
		sta.close();
		conn.close();
		return vGUID;
	}

	public static void RetriveValuesFrDealerSettingsPageFrNewDealerListPage(WebDriver driver, String brw,
			String versionNum, String envment, String checkEmail)
			throws IOException, InterruptedException, ClassNotFoundException, SQLException {

		// Load environment parameters
		Properties prop = new Properties();
		// testprop.load(new FileInputStream("data/autopxConf.properties"));
		try {
			prop.load(AdminPortalController.class.getClassLoader()
					.getResourceAsStream("AdminPortalData/adminPortalConf.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String env = prop.getProperty("AUTOpx.environment");
		String envBrowser = prop.getProperty("AUTOpx.browser");
		String render = prop.getProperty("AUTOpx.render");
		String addNewVIN = prop.getProperty("AUTOpx.addNewVIN");
		String accountEmail = prop.getProperty(env + ".VINpxEmail");
		String accountPS = prop.getProperty(env + ".VINpxPassword");
		// String baseURL = prop.getProperty(env + ".VINpxDealerPortalBaseURL");
		String dealershipName = prop.getProperty(env + ".VINpxDealershipname");
		String dealerCode = prop.getProperty(env + ".VINpxDealerCode");
		String vin01 = prop.getProperty(env + ".VINpxVin01");
		String vin02 = prop.getProperty(env + ".VINpxVin02");
		String vehGUID01 = prop.getProperty(env + ".VINpxVin01GUID");
		String vehGUID02 = prop.getProperty(env + ".VINpxVin02GUID");
		// String vinpxnewVin01 = prop.getProperty(env + ".VINpxNewVIN01");
		String[] VINpxNewVINs = fetchOneDemArrayFromPropFile(env + ".VINpxNewVINs", prop);
		String serverName = prop.getProperty(env + ".serverName");
		String dbName = prop.getProperty(env + ".dbName");
		String userName = prop.getProperty(env + ".userName");
		String password = prop.getProperty(env + ".password");
		String MaxVins = prop.getProperty(env + ".MaxVinsForPreview");
		int MaxVinsForPreview = Integer.parseInt(MaxVins);
		// Initial
		// final int wt_Secs = 6;
		String TCnum;
		// ====================
		String tempVIN = "";
		String tempVehGUID = "";
		String ProductVINpx = "";
		String ProductSTOCKpx = "";
		String ProductLOTpx = "";
		String Dealership_ID = "";
		String Dealership_Name = "";
		String Dealership_Email = "";
		String Account_Email = "";
		String Metadata = "";
		String dlrGuid = "";
		// ====================
		Comlibs ac = new Comlibs();
		ac.rwExcel("", "*********Retrive Values From Dealership Settings page**********", "");

		int count = 0;
		String getMetadataSavePathFile = "C:\\1\\Eclipse\\Test Results\\AUTOpx" + "\\Metadata_" + env + ".xls";
		String[] titleString = { "Env.", "S/N", "Dealership_ID", "Dealership_Name", "Account_Email", "Dealership_Email",
				"ProductVINpx", "ProductSTOCKpx", "ProductLOTpx", "Metadata", "dlrGuid" };
		// =================================================
		ac.writeTitle(getMetadataSavePathFile, titleString);
		int dataLength = 54;
		String[] metadataValues = new String[dataLength + 1];
		int datasize = metadataValues.length;

		// =================================================
		VDVILogin loginP = new VDVILogin(driver);
		int dealerN = 0;
		String dealerSN = "";
		loginP.login(driver, accountEmail, accountPS, "");
		String parentHandle = driver.getWindowHandle(); // get the current window handle
		UserList userListP = new UserList(driver);
		userListP.clickManageDealerShips(driver);
		ac.Wait(2);
		DealerList2 DealerListP = new DealerList2(driver);
		DealerListP.clickDisplayDropDownBtn(driver, "3");
		DealerListP.scrollUp(driver, -3000, "ddd"); // QA -2000 Prod -3000
		for (int next = 0; next < 50; next++) {
			for (int i = 0; i < 150; i++) {
				if (i >= 10) {
					ac.Wait(2);
					DealerListP.scrollUp(driver, 45, "ddd"); // 60 should be in Prod. 55 can run 150 records in QA. 120 - get almost 2 lines. 80 can run until 28 records, 60 can run until 110-120
					ac.Wait(2);
				}
				dealerN = dealerN + 1;
				dealerSN = String.valueOf(dealerN);
				try {
					DealerListP.clickEditBtn(driver, dealerSN);
				} catch (Exception e) {
					DealerListP.scrollUp(driver, 45, "ddd");
					DealerListP.clickEditBtn(driver, dealerSN);
				}
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
				}
				DealerProfile DealerProfieP = new DealerProfile(driver);
				// =========================================
				ProductVINpx = DealerProfieP.getVINpxProduct(driver, "");
				ProductSTOCKpx = DealerProfieP.getSTOCKpxProduct(driver, "");
				ProductLOTpx = DealerProfieP.getLOTpxProduct(driver, "");
				Dealership_ID = DealerProfieP.getDealershipID(driver);
				Dealership_Name = DealerProfieP.getDealershipName(driver);
				Dealership_Email = DealerProfieP.getDealershipEmail(driver);
				// Account_Email = DealerProfieP.getAccountEmail(driver);
				Metadata = DealerProfieP.getMetadata(driver);
				dlrGuid = DealerProfieP.getDlrGuid(driver);
				dlrGuid = DealerProfieP.trimURL(dlrGuid);
				// =========================================

				int wSize = titleString.length;
				String[] DealerSettingsArray = new String[wSize];

				DealerSettingsArray[0] = env;
				DealerSettingsArray[1] = Integer.toString(i);
				DealerSettingsArray[2] = Dealership_ID;
				DealerSettingsArray[3] = Dealership_Name;
				DealerSettingsArray[4] = Account_Email;
				DealerSettingsArray[5] = Dealership_Email;
				DealerSettingsArray[6] = ProductVINpx;
				DealerSettingsArray[7] = ProductSTOCKpx;
				DealerSettingsArray[8] = ProductLOTpx;
				DealerSettingsArray[9] = Metadata;
				DealerSettingsArray[10] = dlrGuid;
				ac.writeToSheet(getMetadataSavePathFile, DealerSettingsArray);

				// =========================================

				System.out.println("\nS/N=" + (i + 1) + " of page " + (next + 1) + ". Dealer number=" + dealerN + "\n");
				System.out.println("VINpx=" + ProductVINpx + "\n" + "STOCKpx= " + ProductSTOCKpx + "\n" + "LOTpx= "
						+ ProductLOTpx);
				System.out.println("Dealership_ID: " + Dealership_ID + "\n" + "Dealership_Name: " + Dealership_Name
						+ "\n" + "Dealership_Email: " + Dealership_Email + "\n" + "Account_Email: " + Account_Email
						+ "\n" + "Metadata: " + Metadata + "\n" + "dlrGuid:" + dlrGuid + "\n");

				DealerProfieP.clickBackToDealerListBtn(driver, parentHandle, "TC_num");
				if (i == 150) {
					ac.Wait(2);
				}

			}
			DealerListP.clickNext(driver);
			DealerListP.scrollUp(driver, -8000, "clickNext");
			dealerN = 0;// Reset
		}
		driver.close();
	}

	public static void RetriveValuesFrDealerSettingsPage(WebDriver driver, String brw, String versionNum,
			String envment, String checkEmail)
			throws IOException, InterruptedException, ClassNotFoundException, SQLException {

		// Load environment parameters
		Properties prop = new Properties();
		// testprop.load(new FileInputStream("data/autopxConf.properties"));
		try {
			prop.load(AdminPortalController.class.getClassLoader()
					.getResourceAsStream("AdminPortalData/adminPortalConf.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String env = prop.getProperty("AUTOpx.environment");
		String envBrowser = prop.getProperty("AUTOpx.browser");
		String render = prop.getProperty("AUTOpx.render");
		String addNewVIN = prop.getProperty("AUTOpx.addNewVIN");
		String accountEmail = prop.getProperty(env + ".VINpxEmail");
		String accountPS = prop.getProperty(env + ".VINpxPassword");
		// String baseURL = prop.getProperty(env + ".VINpxDealerPortalBaseURL");
		String dealershipName = prop.getProperty(env + ".VINpxDealershipname");
		String dealerCode = prop.getProperty(env + ".VINpxDealerCode");
		String vin01 = prop.getProperty(env + ".VINpxVin01");
		String vin02 = prop.getProperty(env + ".VINpxVin02");
		String vehGUID01 = prop.getProperty(env + ".VINpxVin01GUID");
		String vehGUID02 = prop.getProperty(env + ".VINpxVin02GUID");
		// String vinpxnewVin01 = prop.getProperty(env + ".VINpxNewVIN01");
		String[] VINpxNewVINs = fetchOneDemArrayFromPropFile(env + ".VINpxNewVINs", prop);
		String serverName = prop.getProperty(env + ".serverName");
		String dbName = prop.getProperty(env + ".dbName");
		String userName = prop.getProperty(env + ".userName");
		String password = prop.getProperty(env + ".password");
		String MaxVins = prop.getProperty(env + ".MaxVinsForPreview");
		int MaxVinsForPreview = Integer.parseInt(MaxVins);
		int wt = Integer.parseInt(prop.getProperty("AUTOpx.waitTime"));
		// Initial
		// final int wt_Secs = 6;
		String TCnum;
		// ====================
		String tempVIN = "";
		String tempVehGUID = "";
		String ProductVINpx = "";
		String ProductSTOCKpx = "";
		String ProductLOTpx = "";
		String Dealership_ID = "";
		String Dealership_Name = "";
		String Dealership_Email = "";
		String Account_Email = "";
		String Metadata = "";
		String dlrGuid = "";
		// ====================
		Comlibs ac = new Comlibs();
		ac.rwExcel("", "*********Retrive Values From Dealership Settings page**********", "");

		int count = 0;
		String getMetadataSavePathFile = "C:\\1\\Eclipse\\Test Results\\AUTOpx" + "\\Metadata_" + env + ".xls";
		String[] titleString = { "Env.", "S/N", "Dealership_ID", "Dealership_Name", "Account_Email", "Dealership_Email",
				"ProductVINpx", "ProductSTOCKpx", "ProductLOTpx", "Metadata", "dlrGuid" };
		// =================================================
		ac.writeTitle(getMetadataSavePathFile, titleString);
		int dataLength = 54;
		String[] metadataValues = new String[dataLength + 1];
		int datasize = metadataValues.length;

		// =================================================
		VDVILogin loginP = new VDVILogin(driver);
		int dealerN = 0;
		String dealerSN = "";
		loginP.login(driver, accountEmail, accountPS, "");
		String parentHandle = driver.getWindowHandle(); // get the current window handle
		UserList UserListP = new UserList(driver);
		UserListP.clickFirstNameSort(driver, "TC number");
		UserListP.clickFirstNameSort(driver, "TC number");
		UserListP.clickDisplayDropDownBtn(driver, "1"); // no longer use since 2018-11-02

		for (int next = 1; next <= 117; next++) {// prod=117, QA=86
			int numAccts = UserListP.getNumOfAccounts(driver, 1, "account nums");
			for (int i = 1; i <= numAccts; i++) {

				accountEmail = UserListP.getAccountEmail(driver, i, "tc");
				UserListP.clickViewDealerships(driver, i);
				if (i == numAccts) {
					UserListP.scrollUp(driver, 600, "ddd");
				}
				UserListP.scrollUp(driver, 40, "ddd");
				int dealers = 8;
				for (int dealer = 1; dealer <= dealers; dealer++) {
					// UserListP.getNumOfDealerships(driver,1,"tc"); //not ready yet 2018-11-06
					try {
						UserListP.clickEditOnDealership(driver, dealer);
					} catch (Exception e) {
						System.out.println("Account Email = " + accountEmail + ". Its Dealer number: " + dealer
								+ " does not exist. Exit!");
						UserListP.clickViewDealerships(driver, i);
						break;
						// ac.rwExcel("Dealer number: "+dealer+" does not exist", false, "", "");
					}
					// UserListP.checkStatus(driver,1);
					// UserListP
					// UserListP

					UserListP.scrollUp(driver, -3000, "ddd"); // QA -2000 Prod -3000 - negative means scrolldown

					// for (int i = 0; i < 150; i++) {
					// if (i >= 10) {//10
					//// UserListP.scrollUp(driver, 55, "ddd"); // 60 should be in Prod. 55 can run 150 records in QA. 120 - get almost 2 lines. 80 can run until 28 records, 60 can run until 110-120
					// UserListP.checkEditBtnLocationAndScroll(driver, dealerSN);
					// ac.Wait(wt);
					// }
					dealerN = dealerN + 1;
					dealerSN = String.valueOf(dealerN);
					// ac.Wait(wt);
					// UserListP.clickEditBtn(driver, dealerSN);
					// ac.Wait(wt);
					for (String winHandle : driver.getWindowHandles()) {
						driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
					}
					DealerProfile DealerProfieP = new DealerProfile(driver);
					// =========================================
					ProductVINpx = DealerProfieP.getVINpxProduct(driver, "");
					ProductSTOCKpx = DealerProfieP.getSTOCKpxProduct(driver, "");
					ProductLOTpx = DealerProfieP.getLOTpxProduct(driver, "");
					Dealership_ID = DealerProfieP.getDealershipID(driver);
					Dealership_Name = DealerProfieP.getDealershipName(driver);
					Dealership_Email = DealerProfieP.getDealershipEmail(driver);
					// Account_Email = DealerProfieP.getAccountEmail(driver);
					Metadata = DealerProfieP.getMetadata(driver);
					dlrGuid = DealerProfieP.getDlrGuid(driver);
					dlrGuid = DealerProfieP.trimURL(dlrGuid);
					// =========================================

					int wSize = titleString.length;
					String[] DealerSettingsArray = new String[wSize];

					DealerSettingsArray[0] = env;
					DealerSettingsArray[1] = Integer.toString(i);
					DealerSettingsArray[2] = Dealership_ID;
					DealerSettingsArray[3] = Dealership_Name;
					DealerSettingsArray[4] = accountEmail;
					DealerSettingsArray[5] = Dealership_Email;
					DealerSettingsArray[6] = ProductVINpx;
					DealerSettingsArray[7] = ProductSTOCKpx;
					DealerSettingsArray[8] = ProductLOTpx;
					DealerSettingsArray[9] = Metadata;
					DealerSettingsArray[10] = dlrGuid;
					ac.writeToSheet(getMetadataSavePathFile, DealerSettingsArray);

					// =========================================

					System.out.println("Account number =" + i + ".  Dealer number=" + dealerN);

					// System.out.println(i + 1 + ". - VINpx=" + ProductVINpx + "\n" + "STOCKpx= " + ProductSTOCKpx + "\n"
					// + "LOTpx= " + ProductLOTpx);
					// System.out.println("Dealership_ID: " + Dealership_ID + "\n" + "Dealership_Name: " + Dealership_Name
					// + "\n" + "Dealership_Email: " + Dealership_Email + "\n" + "Account_Email: " + Account_Email
					// + "\n" + "Metadata: " + Metadata + "\n" + "dlrGuid:" + dlrGuid + "\n "
					// + "\nDealer number = " + (i + 1) + ", is complete\n");

					DealerProfieP.clickBackToDealerListBtn(driver, parentHandle, "TC_num");
					// UserListP.scrollUp(driver, 40, "ddd");
					// if (i % 12 == 0) {
					// UserListP.scrollUp(driver, 550, "ddd"); // QA -2000 Prod -3000 - negative means scrolldown
					// }
				}
			}
			UserListP.clickNext(driver, next, "Next" + next);
			UserListP.scrollUp(driver, -3550, "ddd"); // QA -2000 Prod -3000 - negative means scrolldown
		}
		driver.close();
	}

	public static void ManageDealerShips(WebDriver driver, String brw, String versionNum, String envment,
			String checkEmail) throws Exception {

		// Load environment parameters
		Properties prop = new Properties();
		// testprop.load(new FileInputStream("data/autopxConf.properties"));
		try {
			prop.load(AdminPortalController.class.getClassLoader()
					.getResourceAsStream("AdminPortalData/adminPortalConf.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String tc = "";
		String env = prop.getProperty("AUTOpx.environment");
		String envBrowser = prop.getProperty("AUTOpx.browser");
		String render = prop.getProperty("AUTOpx.render");
		String addNewVIN = prop.getProperty("AUTOpx.addNewVIN");
		String accountEmail = prop.getProperty(env + ".VINpxEmail");
		String accountPS = prop.getProperty(env + ".VINpxPassword");
		// String baseURL = prop.getProperty(env + ".VINpxDealerPortalBaseURL");
		String dealershipName = prop.getProperty(env + ".VINpxDealershipname");
		String dealerCode = prop.getProperty(env + ".VINpxDealerCode");
		String vin01 = prop.getProperty(env + ".VINpxVin01");
		String vin02 = prop.getProperty(env + ".VINpxVin02");
		String vehGUID01 = prop.getProperty(env + ".VINpxVin01GUID");
		String vehGUID02 = prop.getProperty(env + ".VINpxVin02GUID");
		// String vinpxnewVin01 = prop.getProperty(env + ".VINpxNewVIN01");
		String[] VINpxNewVINs = fetchOneDemArrayFromPropFile(env + ".VINpxNewVINs", prop);
		String serverName = prop.getProperty(env + ".serverName");
		String dbName = prop.getProperty(env + ".dbName");
		String userName = prop.getProperty(env + ".userName");
		String password = prop.getProperty(env + ".password");
		String MaxVins = prop.getProperty(env + ".MaxVinsForPreview");
		int MaxVinsForPreview = Integer.parseInt(MaxVins);
		// dealership profile:
		String OEM = prop.getProperty(env + ".OEM");
		String[] Brands = fetchOneDemArrayFromPropFile(env + ".Brands", prop);
		String AllProdDealerCode = prop.getProperty(env + ".AllProdDealerCode");
		String AllProdEmail = prop.getProperty(env + ".AllProdEmail");
		String DealershipID = prop.getProperty(env + ".DealershipID");
		String DealershipName = prop.getProperty(env + ".DealershipName");
		String[] Products = fetchOneDemArrayFromPropFile(env + ".Products", prop);
		String MetadataValues = prop.getProperty(env + ".MetadataValues");
		String Address = prop.getProperty(env + ".Address");
		String AddressLine2 = prop.getProperty(env + ".AddressLine2");
		String City = prop.getProperty(env + ".City");
		int StateProvince = Integer.parseInt(prop.getProperty(env + ".StateProvince"));
		int Country = Integer.parseInt(prop.getProperty(env + ".Country"));
		String ZipPostalCode = prop.getProperty(env + ".ZipPostalCode");
		String DealershipEmail = prop.getProperty(env + ".DealershipEmail");
		String AccountEmail = prop.getProperty(env + ".AccountEmail");
		String FirstName = prop.getProperty(env + ".FirstName");
		String LastName = prop.getProperty(env + ".LastName");
		String TagLineMarkingMsg = prop.getProperty(env + ".TagLineMarkingMsg");
		String Website = prop.getProperty(env + ".Website");
		String DealershipPhoneNumber = prop.getProperty(env + ".DealershipPhoneNumber");
		int TemplateSettings = Integer.parseInt(prop.getProperty(env + ".TemplateSettings"));
		int SelectBackgroundSet = Integer.parseInt(prop.getProperty(env + ".SelectBackgroundSet"));
		int wt = Integer.parseInt(prop.getProperty("AUTOpx.waitTime"));
		String AddNewAccountEmail = prop.getProperty(env + ".AddNewAccountEmail");
		String addNewDealerExtension = addNewDealerExtension = prop.getProperty(env + ".addNewDealerExtension");
		String dealershipLogoPath = prop.getProperty("AUTOpx.dealershipLogoPath");
		String addNewDealerShip = prop.getProperty("AUTOpx.addNewDealerShip");
		// Initial
		// final int wt_Secs = 6;
		String TCnum;
		// ====================
		String tempVIN = "";
		String tempVehGUID = "";
		String ProductVINpx = "";
		String ProductSTOCKpx = "";
		String ProductLOTpx = "";
		String Dealership_ID = "";
		String Dealership_Name = "";
		String Dealership_Email = "";
		String Account_Email = "";
		String Metadata = "";
		String dlrGuid = "";
		String alertmessage = "";
		// ====================
		Comlibs ac = new Comlibs();
		ac.rwExcel("", "*********ManageDealerShips**********", "");

		int count = 0;
		String getMetadataSavePathFile = "C:\\1\\Eclipse\\Test Results\\AUTOpx" + "\\Metadata_" + env + ".xls";
		String[] titleString = { "Env.", "S/N", "Dealership_ID", "Dealership_Name", "Account_Email", "Dealership_Email",
				"ProductVINpx", "ProductSTOCKpx", "ProductLOTpx", "Metadata", "dlrGuid" };
		// =================================================
		ac.writeTitle(getMetadataSavePathFile, titleString);
		int dataLength = 54;
		String[] metadataValues = new String[dataLength + 1];
		int datasize = metadataValues.length;

		// =================================================
		VDVILogin loginP = new VDVILogin(driver);
		int dealerN = 0;
		String dealerSN = "";
		loginP.login(driver, accountEmail, accountPS, "");
		String parentHandle = driver.getWindowHandle(); // get the current window handle

		UserList UserListP = new UserList(driver);
		// *************************ManageAccounts - UserListP******************************************************
		// *************************ManageAccounts - UserListP******************************************************
		ac.Wait(wt);
		UserListP.scrollUp(driver, 3000, "ddd"); // QA -2000 Prod -3000 - negative means scrolldown
		UserListP.clickDisplayDropDownBtn(driver, "3");
		UserListP.scrollUp(driver, -3000, "ddd"); // QA -2000 Prod -3000 - negative means scrolldown
		// =========================== Add New Account Process============================================================
		tc = "TC_addNewAct_AttachDealer_DeleteIt";
		UserListP.clickAddAccount(driver);
		AccountProfile AccountProfileP = new AccountProfile(driver);
		AccountProfileP.inputAccountEmail(driver, AddNewAccountEmail);
		AccountProfileP.inputFirstName(driver, FirstName);
		AccountProfileP.inputLastName(driver, LastName);
		AccountProfileP.selectAccountStatus(driver, 1);
		AccountProfileP.clickBackToDealerListBtn(driver, parentHandle, tc);

		// =========================== Add Account============================================================
		tc = "TC228658_n";// "TC_addNewAct_with_Existing_ActEamil";
		UserListP.clickAddAccount(driver);
		// AccountProfile AccountProfileP = new AccountProfile(driver);
		AccountProfileP.inputAccountEmail(driver, accountEmail);
		AccountProfileP.inputFirstName(driver, FirstName);
		AccountProfileP.inputLastName(driver, LastName);
		AccountProfileP.selectAccountStatus(driver, 1);
		AccountProfileP.clickBackToDealerListBtn(driver, parentHandle, tc);
		tc = "TC228658_s";// "TC_addNewAct_with_Existing_ActEamil_SAVE";
		UserListP.clickAddAccount(driver);
		AccountProfileP.inputAccountEmail(driver, accountEmail);
		AccountProfileP.inputFirstName(driver, FirstName);
		AccountProfileP.inputLastName(driver, LastName);
		tc = "TC139104_L";// "TC_addNewAct_AccountStatusShouldNotBeChanged";
		try {
			AccountProfileP.selectAccountStatus(driver, 2);// 1- Active, 2- Lock out, 3-Change Password, 4-Disabled
			ac.rwExcel(tc, false, "Add Account - Account Status ", "Not Working - Can change status to 2 - Lock out");
		} catch (Exception e) {
			ac.rwExcel(tc, true, "Add Account - Account Status ", "Works good - Cannot change status to 2 - Lock out");
			AccountProfileP.selectAccountStatus(driver, 1);
		}
		AccountProfileP.clickSaveBtn(driver, tc);
		tc = "TC228658_c";// "TC_addNewAct_with_Existing_ActEamil_checkMSG";

		boolean MessageExistForAddExistAccountEmail = AccountProfileP.checkMessageDisplayedHead(driver,
				"Check required fields");
		if (MessageExistForAddExistAccountEmail) {
			ac.rwExcel(tc, true, "Add an Account ", "With Exist Account Email");
		} else {
			ac.rwExcel(tc, false, "Add an Account ", "With Exist Account Email");
		}
		AccountProfileP.clickBackToDealerListBtn(driver, parentHandle, tc);
		// Stop here!!! All above work fine.

		// =========================== Add Account============================================================

		// =========================== Manage Account - Add Dealership for existing DealerID============================================================
		tc = "TC228656_m1";
		UserListP.clickAddDealerShip(driver);

		DealerProfile DealerProfieP = new DealerProfile(driver);
		DealerProfieP.selectOEM(driver, 13);
		// check Buick and Cadillac and Chevrolet and GMC
		// DealerProfieP.selectOEMBrands(driver, 1); // check Buick
		// DealerProfieP.selectOEMBrands(driver, 2); // check Cadillac
		// DealerProfieP.selectOEMBrands(driver, 3); // check Chevrolet
		// DealerProfieP.selectOEMBrands(driver, 4); // check GMC
		// DealerProfieP.selectOEMBrands(driver, 5); // check Hummer
		for (String brand : Brands) {
			DealerProfieP.selectOEMBrands(driver, Integer.parseInt(brand));
		}
		DealerProfieP.inputDealersipID(driver, DealershipID);
		DealerProfieP.selectVINpxProd(driver);
		DealerProfieP.selectSTOCKpxProd(driver);
		// DealerProfieP.selectLOTpxProd(driver);
		// DealerProfieP.inputMetadata(driver, MetadataValues);

		// DealerProfieP.selectTemplateSetting(driver, TemplateSettings);// DEFAULT=1; replace=2;overlay=3;
		// DealerProfieP.selectTemplateSetting(driver, 1);
		DealerProfieP.inputDealershipName(driver, DealershipName);
		DealerProfieP.inputAddress(driver, Address);
		DealerProfieP.inputAddressLine2(driver, AddressLine2);
		DealerProfieP.inputCity(driver, City);
		DealerProfieP.inputDealersipEmail(driver, DealershipEmail);
		DealerProfieP.inputZipCode(driver, ZipPostalCode);
		DealerProfieP.inputCountry(driver, Country);// USA=1
		DealerProfieP.inputState(driver, StateProvince);// NY=33
		DealerProfieP.inputTagLineMarkingMsg(driver, TagLineMarkingMsg);
		DealerProfieP.inputWebsite(driver, Website);
		DealerProfieP.inputDealershipPhone(driver, DealershipPhoneNumber);

		DealerProfieP.selectBackGroundSet(driver, SelectBackgroundSet);// Generic Dealership=7; White Gradient=0
		DealerProfieP.scrollUp(driver, -3000, tc);
		DealerProfieP.clickSaveBtn(driver, tc);

		tc = "TC228656_m2";// "AddDealerInvalid_withExistDealershipID";
		boolean MessageExist = DealerProfieP.checkMessageDisplayedHead(driver,
				"There is already a record with this Manufacturer and Dealer Code.", tc);// "There is already a user record with this Login");
		if (MessageExist) {
			ac.rwExcel(tc, true, "Add a dealership ", "With Exist DealershipID");
		} else {
			ac.rwExcel(tc, false, "Add a dealership ", "With Exist DealershipID");
		}

		DealerProfieP.clickBackToDealerListBtn(driver, parentHandle, tc);

		// **************************Manage Account - Add a new dealership for account*****************************************************
		// click Add Dealership btn
		tc = "TC139021_m1";// Add a new dealership for account
		UserListP.clickAddDealerShip(driver);
		DealerProfieP.selectOEM(driver, 13);
		for (String brand : Brands) {
			DealerProfieP.selectOEMBrands(driver, Integer.parseInt(brand));
		}
		// String addNewDealerExtension = "_New_Added_18";// ************************ New one should be 18 ****************************
		DealerProfieP.inputDealersipID(driver, DealershipID + addNewDealerExtension);
		DealerProfieP.selectVINpxProd(driver);
		DealerProfieP.selectSTOCKpxProd(driver);
		// DealerProfieP.selectLOTpxProd(driver);
		DealerProfieP.inputMetadata(driver, MetadataValues);

		// DealerProfieP.selectTemplateSetting(driver, TemplateSettings);// DEFAULT=1; replace=2;overlay=3;
		// DealerProfieP.selectTemplateSetting(driver, 1);
		DealerProfieP.inputDealershipName(driver, DealershipName);
		DealerProfieP.inputAddress(driver, Address);
		DealerProfieP.inputAddressLine2(driver, AddressLine2);
		DealerProfieP.inputCity(driver, City);
		DealerProfieP.inputDealersipEmail(driver, "Autotomsmith4@gmail.com");// Auto_Added_"+DealershipEmail);
		DealerProfieP.inputZipCode(driver, ZipPostalCode);
		DealerProfieP.inputCountry(driver, Country);// USA=1
		DealerProfieP.inputState(driver, StateProvince);// NY=33
		DealerProfieP.inputTagLineMarkingMsg(driver, TagLineMarkingMsg);
		DealerProfieP.inputWebsite(driver, Website);
		DealerProfieP.inputDealershipPhone(driver, DealershipPhoneNumber);
		alertmessage = "You must save the dealer information before you can take this action";
		DealerProfieP.uploadDealershipLogo(driver, dealershipLogoPath, alertmessage, tc);
		ac.Wait(wt);
		DealerProfieP.selectBackGroundSet(driver, SelectBackgroundSet);// Generic Dealership=7; White Gradient=0
		DealerProfieP.scrollUp(driver, -3000, tc);
		DealerProfieP.clickSaveBtn(driver, tc);
		// Stop here for the time being since there is bug here AUTOPXOPS-1227
		ac.Wait(wt);
		tc = "TC139021_m2";
		// The successful message "Your settings have been saved" will only show one second then disappear.
		// So the successful message should be empty "" here;
		String successfulMsg = "";// "Your settings have been saved"
		MessageExist = DealerProfieP.checkMessageDisplayedHead(driver, successfulMsg, tc);
		// Bug here since entered Metadata. See AUTOPXOPS-1227. Now it shows an error "An error occurred. Please try again."
		// but the dealership has been created in our system. Issue fixed but "Your settings have been saved" message only shows a second and then disappears.
		if (MessageExist) {
			ac.rwExcel(tc, true, "Add a new dealership with all fields",
					"Sucessful msg shows: Your settings have been saved");
		} else {
			ac.rwExcel(tc, false, "Add a new dealership with all fields",
					"Failed to shows msg: Your settings have been saved. Currently it only shows a second and then disappears. Related to bug AUTOPXOPS-1227");
		}

		DealerProfieP.clickBackToDealerListBtn(driver, parentHandle, tc); // Stop here. Verify dealer added in system through Manage Dealership by input the dealerid and click the edit buttom then close Dealer Profile page

		// =========================== Manage Account - Add Dealership============================================================
		// **************************************************************************************
		UserListP.clickManageAccounts(driver);
		ac.Wait(wt);
		UserListP.inputSearch(driver, AllProdEmail);
		tc = "TC228723_m1";
		UserListP.clickEditBtn(driver, "1");// 1,2,3...
		String attachedDealerName = AccountProfileP.selectOneDealerFrAllDealers(driver, 7, tc);
		boolean dealerExistInAllDealers = false;
		boolean dealerExistInAccountDealers = false;
		tc = "TC228723_m2";// "Dealer should not exist in Account Dealer field_01";
		dealerExistInAccountDealers = AccountProfileP.verifyOneDealerInAccountDealersField(driver, attachedDealerName,
				false, tc);
		AccountProfileP.clickRightArrowAttachBtn(driver);
		tc = "TC228723_m3";// "TC_Verify atached dealer from All Dealers_01";
		dealerExistInAllDealers = AccountProfileP.verifyOneDealerInAllDealersField(driver, attachedDealerName, true,
				tc);
		tc = "TC228727_m1";// "Dealer should exist in Account Dealer field_02";
		dealerExistInAccountDealers = AccountProfileP.verifyOneDealerInAccountDealersField(driver, attachedDealerName,
				true, tc);
		AccountProfileP.selectOneDealerFrAccountDealers(driver, attachedDealerName, tc);
		ac.Wait(wt);
		AccountProfileP.clickLeftArrowDetachBtn(driver);
		tc = "TC228727";// "TC_Verify detach a dealer from Account Dealers_01";
		dealerExistInAccountDealers = AccountProfileP.verifyOneDealerInAccountDealersField(driver, attachedDealerName,
				true, tc);

		AccountProfileP.scrollUp(driver, -3000, tc);
		AccountProfileP.clickBackToDealerListBtn(driver, parentHandle, tc);
		tc = "TC139406_m1";
		UserListP.clickExpandDealersArrow(driver, 1);
		UserListP.clickEditOnDealer(driver, 1);
		DealerProfieP.clickBackToDealerListBtn(driver, parentHandle, tc);
		UserListP.clickViewDealerPortal(driver, 1, tc);
		DealerPortal.DealerProfile DealerPortalDealerProfieP = new DealerPortal.DealerProfile(driver);
		DealerPortalDealerProfieP.clickInventoryGalleryBtn(driver, tc);
		DealerPortal.ImageGallery DealerPortalImageGalleryP = new DealerPortal.ImageGallery(driver);
		DealerPortalImageGalleryP.clickDealerShipInfoBtn(driver);
		driver.close();// Close Dealer Profile page
		ac.switchToWindow(driver);
		System.out.println("Add a Dealership is done!");

		// *************************ManageAccounts - UserListP******************************************************
		//// *************************ManageAccounts - UserListP******************************************************

		//// *************************ManageDealerships - DealerListP******************************************************
		//// *************************ManageDealerships - DealerListP******************************************************

		// This part is the same of "Add Dealership for existing account" to end of "ManageAccounts - UserListP"
		UserListP.clickManageDealerShips(driver);
		ac.Wait(wt * 2);
		DealerList2 DealerListP2 = new DealerList2(driver);
		// Checking View On Dealer Portal link
		tc = "TC229379_d";// "Checking View On Dealer Portal link";
		DealerListP2.inputSearch(driver, "123456_New_Added_Cadillac");
		DealerListP2.clickViewOnDealerPortalBtn(driver, 1, tc);
		driver.close();
		ac.switchToWindow(driver);
		UserListP.clickManageDealerShips(driver);
		ac.Wait(wt * 3);
		tc = "TC229371_d1";
		DealerListP2.clickAddDealerShip(driver);
		ac.Wait(wt * 2);
		DealerProfile DealerProfileP = new DealerProfile(driver);
		DealerProfileP.selectOEM(driver, 13);
		// check Buick and Cadillac and Chevrolet and GMC
		// DealerProfileP.selectOEMBrands(driver, 1); // check Buick
		// DealerProfileP.selectOEMBrands(driver, 2); // check Cadillac
		// DealerProfileP.selectOEMBrands(driver, 3); // check Chevrolet
		// DealerProfileP.selectOEMBrands(driver, 4); // check GMC
		// DealerProfileP.selectOEMBrands(driver, 5); // check Hummer
		for (String brand : Brands) {
			DealerProfileP.selectOEMBrands(driver, Integer.parseInt(brand));
		}
		DealerProfileP.inputDealersipID(driver, DealershipID);
		DealerProfileP.selectVINpxProd(driver);
		DealerProfileP.selectSTOCKpxProd(driver);
		// DealerProfileP.selectLOTpxProd(driver);
		// DealerProfileP.inputMetadata(driver, MetadataValues);

		// DealerProfileP.selectTemplateSetting(driver, TemplateSettings);// DEFAULT=1; replace=2;overlay=3;
		// DealerProfileP.selectTemplateSetting(driver, 1);
		DealerProfileP.inputDealershipName(driver, DealershipName);
		DealerProfileP.inputAddress(driver, Address);
		DealerProfileP.inputAddressLine2(driver, AddressLine2);
		DealerProfileP.inputCity(driver, City);
		DealerProfileP.inputDealersipEmail(driver, DealershipEmail);
		DealerProfileP.inputZipCode(driver, ZipPostalCode);
		DealerProfileP.inputCountry(driver, Country);// USA=1
		DealerProfileP.inputState(driver, StateProvince);// NY=33
		DealerProfileP.inputTagLineMarkingMsg(driver, TagLineMarkingMsg);
		DealerProfileP.inputWebsite(driver, Website);
		DealerProfileP.inputDealershipPhone(driver, DealershipPhoneNumber);
		ac.Wait(wt);
		DealerProfileP.selectBackGroundSet(driver, SelectBackgroundSet);// Generic Dealership=7; White Gradient=0
		DealerProfileP.scrollUp(driver, -3000, tc);
		DealerProfileP.clickSaveBtn(driver, tc);
		ac.Wait(wt);
		tc = "TC229371_d2";// "AddDealerInvalid_withExistDealershipID";
		boolean MessageExistDealer = DealerProfileP.checkMessageDisplayedHead(driver,
				"There is already a record with this Manufacturer and Dealer Code.", tc);// "There is already a user record with this Login");
		if (MessageExistDealer) {
			ac.rwExcel(tc, true, "Add a dealership ", "With Exist DealershipID");
		} else {
			ac.rwExcel(tc, false, "Add a dealership ", "With Exist DealershipID");
		}

		DealerProfileP.clickBackToDealerListBtn(driver, parentHandle, tc);

		// **************************ManageDealerships - Add a new dealership for account*****************************************************
		// click Add Dealership btn
		UserListP.clickAddDealerShip(driver);
		DealerProfileP.selectOEM(driver, 13);
		for (String brand : Brands) {
			DealerProfileP.selectOEMBrands(driver, Integer.parseInt(brand));
		}
		// String addNewDealerExtension="_New_Added_16";// *******************************New one should be 17********************
		tc = "TC229370_d1";
		String addNewDealership = DealershipID + addNewDealerExtension + "_D";
		DealerProfileP.inputDealersipID(driver, addNewDealership);//
		DealerProfileP.selectVINpxProd(driver);
		DealerProfileP.selectSTOCKpxProd(driver);
		// DealerProfileP.selectLOTpxProd(driver);
		DealerProfileP.inputMetadata(driver, MetadataValues);

		// DealerProfileP.selectTemplateSetting(driver, TemplateSettings);// DEFAULT=1; replace=2;overlay=3;
		// DealerProfileP.selectTemplateSetting(driver, 1);
		DealerProfileP.inputDealershipName(driver, DealershipName);
		DealerProfileP.inputAddress(driver, Address);
		DealerProfileP.inputAddressLine2(driver, AddressLine2);
		DealerProfileP.inputCity(driver, City);
		DealerProfileP.inputDealersipEmail(driver, "Autotomsmith4@gmail.com");// Auto_Added_"+DealershipEmail);
		DealerProfileP.inputZipCode(driver, ZipPostalCode);
		DealerProfileP.inputCountry(driver, Country);// USA=1
		DealerProfileP.inputState(driver, StateProvince);// NY=33
		DealerProfileP.inputTagLineMarkingMsg(driver, TagLineMarkingMsg);
		DealerProfileP.inputWebsite(driver, Website);
		DealerProfileP.inputDealershipPhone(driver, DealershipPhoneNumber);
		alertmessage = "You must save the dealer information before you can take this action";
		DealerProfieP.uploadDealershipLogo(driver, dealershipLogoPath, alertmessage, tc);
		ac.Wait(wt);
		DealerProfileP.selectBackGroundSet(driver, SelectBackgroundSet);// Generic Dealership=7; White Gradient=0
		DealerProfileP.scrollUp(driver, -3000, tc);
		DealerProfileP.clickSaveBtn(driver, tc);
		// Verify msg: "Your settings have been saved"
		ac.Wait(wt);
		tc = "TC229370_d2";
		// The successful message "Your settings have been saved" will only show less then one second than disappears.
		// So the successful message should be empty "" here;
		String successfulMsgDealer = "";// "Your settings have been saved" - this msg shows only a second and then disappears.
		MessageExist = DealerProfileP.checkMessageDisplayedHead(driver, successfulMsgDealer, tc);
		// Bug here since entered Metadata. See AUTOPXOPS-1227. Now it shows an error "An error occurred. Please try again."
		// but the dealership has been created in our system.
		if (MessageExist) {
			ac.rwExcel("AddDealervalid", true, "Add a new dealership with all fields",
					"Sucessful msg shows: Your settings have been saved");
		} else {
			ac.rwExcel("AddDealervalid", false, "Add a new dealership with all fields",
					"Failed to shows msg: Your settings have been saved. Currently it only shows a second and then disappears. Related to bug AUTOPXOPS-1227");
		}
		DealerProfieP.clickBackToDealerListBtn(driver, parentHandle, tc);
		tc = "TC229395_d1";// "Upload dealership logo after creating the dealership";
		UserListP.clickManageDealerShips(driver);
		DealerList DealerListP = new DealerList(driver);
		DealerListP.inputSearch(driver, addNewDealership);
		DealerListP.clickEditBtn(driver, "1");
		DealerProfieP.scrollUp(driver, 500, tc);
		successfulMsg = "";
		DealerProfieP.uploadDealershipLogo(driver, dealershipLogoPath, successfulMsg, tc);
		// Verify good message upload dealership logog successfully here.
		DealerProfieP.scrollUp(driver, 500, tc);
		ac.Wait(wt);
		DealerProfieP.scrollUp(driver, -1000, tc);
		DealerProfieP.clickBackToDealerListBtn(driver, parentHandle, tc);
		ac.Wait(wt);

		// Stop here. Verify dealer added in system through Manage Dealership by input the dealerid and click the edit buttom then close Dealer Profile page

		//// *************************ManageDealerships - DealerListP******************************************************
		//// *************************ManageDealerships - DealerListP******************************************************

		//// *************************clickManageBGSetsBtn******************************************************
		//// *************************clickManageBGSetsBtn******************************************************
		ac.rwExcel("", "*********ManageBackGroundSets**********", "");
		tc = "TC148055_b1";
		UserListP.clickManageBGSets(driver);
		BackgroundSets BackgroundSetsP = new BackgroundSets(driver);
		// BackgroundSetsP.clickMapBackGrounds(driver, 3);
		BackgroundSetsP.inputSearch(driver, AllProdDealerCode);
		// ac.Wait(wt);
		try {
			BackgroundSetsP.clickEditSetBtn(driver, 1);
			ac.Wait(wt);
			BackgroundSetsP.clickAllDealershipsCheckBox(driver);// check
		} catch (Exception ex2) {
			ac.Wait(wt);
			BackgroundSetsP.clickEditSetBtn(driver, 1);
			ac.Wait(wt);
			BackgroundSetsP.clickAllDealershipsCheckBox(driver);// check
			System.out.println("\nFirst click it did not appear.Wait... ClickAllDealershipsCheckBox twice!!!!!");
		}
		ac.Wait(wt);
		BackgroundSetsP.clickAllDealershipsCheckBox(driver);// uncheck
		ac.Wait(wt);
		BackgroundSetsP.clickCancel(driver);
		ac.Wait(wt);
		BackgroundSetsP.clickManageBGImageBtn(driver, 1);
		ManageBackgrounds ManageBackgroundsP = new ManageBackgrounds(driver);
		ManageBackgroundsP.clickBackToManageSets(driver);
		BackgroundSetsP.clickDealersUseBackGroundBtn(driver, 1);
		ac.Wait(wt);
		BackgroundSetsP.clickClose(driver);
		ac.Wait(wt);
		tc = "TC139447";
		BackgroundSetsP.clickCreateNewSet(driver);
		ac.Wait(wt);
		BackgroundSetsP.inputSetName(driver, "a");
		BackgroundSetsP.clickCancel(driver);
		ac.Wait(wt);
		BackgroundSetsP.clickCreateNewSet(driver);
		ac.Wait(wt);
		BackgroundSetsP.inputSetName(driver, "a");
		BackgroundSetsP.selectSetType(driver, 3);// 1-Old (Do Not User), 2-Flat on Flat on Flat, 3-Normal, 4-GM Only, 5-FCA Only
		System.out.println("\nPlease wait at least 2 minutes untill Backgrounds page showing...");
		ac.Wait(wt);
		BackgroundSetsP.clickSubmit(driver);
		ac.Wait(wt);
		UserListP.clickManageBGSets(driver);
		ac.clickRefleshF5Btn(driver, tc);
		// tc = "TC139534"; //Edit "a" background set

		// tc = "TC139457"; //Verify edited "a" background set

		tc = "TC139459_d";
		BackgroundSetsP.clickDeleteBGSetBtn(driver, 1, tc);
		ac.acceptAlert(driver, tc, "OK");
		// ac.clickRefleshF5Btn(driver, tc);

		//// *************************clickManageBGSetsBtn******************************************************
		//// *************************clickManageBGSetsBtn******************************************************

		//// *************************clickManageImageTypeBtn******************************************************
		//// *************************clickManageImageTypeBtn******************************************************
		ac.rwExcel("", "*********ManageImageType**********", "");
		ac.Wait(wt);
		tc = "stop here";
		String searchDefaultSequence = "10100";
		String editedDefaultSequence = "10101";
		ac.clickRefleshF5Btn(driver, tc);
		// UserListP.clickManageAccounts(driver);
		UserListP.clickManageImageType(driver);
		ImageTypeList ImageTypeListP = new ImageTypeList(driver);
		// Add an Image Type and cancel
		ac.Wait(wt * 2);
		ImageTypeListP.clickAddImageTypeBtn(driver);
		ac.Wait(wt * 2);
		ImageTypeListP.inputShortIdentifier(driver, "996");
		ImageTypeListP.inputImageGroup(driver, "CUSTOM");
		ImageTypeListP.inputImageDefinition(driver, "DEALER IMAGE");
		ImageTypeListP.inputImageDescription(driver, "VEHICLE BENEFITS");
		ImageTypeListP.inputDefaultSequence(driver, searchDefaultSequence);
		ImageTypeListP.inputBackGroundType(driver, "n");
		ImageTypeListP.clickCancel(driver);
		ac.Wait(wt);
		// Add an Image Type and submit
		UserListP.clickManageImageType(driver);
		ac.Wait(wt);
		ImageTypeListP.clickAddImageTypeBtn(driver);
		ac.Wait(wt);
		ImageTypeListP.inputShortIdentifier(driver, "996");
		ImageTypeListP.inputImageGroup(driver, "CUSTOM");
		ImageTypeListP.inputImageDefinition(driver, "DEALER IMAGE");
		ImageTypeListP.inputImageDescription(driver, "VEHICLE BENEFITS");
		ImageTypeListP.inputDefaultSequence(driver, searchDefaultSequence);
		ImageTypeListP.inputBackGroundType(driver, "n");
		ImageTypeListP.clickSubmit(driver);
		ac.acceptAlert(driver, tc, "OK");
		ac.Wait(wt);
		// Edit an Image Type and submit
		ImageTypeListP.inputSearch(driver, searchDefaultSequence);
		ImageTypeListP.clickEditBtn(driver, 1);
		ImageTypeListP.inputShortIdentifier(driver, "996");
		ImageTypeListP.inputImageGroup(driver, "Edited" + "CUSTOM");
		ImageTypeListP.inputImageDefinition(driver, "Edited" + "DEALER IMAGE");
		ImageTypeListP.inputImageDescription(driver, "Edited" + "VEHICLE BENEFITS");
		ImageTypeListP.inputDefaultSequence(driver, editedDefaultSequence);
		ImageTypeListP.inputBackGroundType(driver, "n");
		ImageTypeListP.clickSubmit(driver);
		ac.acceptAlert(driver, tc, "OK");
		ac.Wait(wt);
		// Delete an Image Type and submit
		ImageTypeListP.inputSearch(driver, editedDefaultSequence);
		ImageTypeListP.clickDeleteBtn(driver, 1);
		ac.acceptAlert(driver, tc, "OK");
		ac.Wait(wt);
		ImageTypeListP.inputSearch(driver, editedDefaultSequence);
		String newDefaultSequenceName = ImageTypeListP.getDefaultSequenceRowString(driver, 1);
		if (newDefaultSequenceName.equalsIgnoreCase(editedDefaultSequence)) {
			// Bug here. Failed to delete an Image Type
			System.out.println(
					"Failed to submit for adding an image type if the Shot Identifier existing in system like 999 ...");
		}

		//// *************************clickManageImageTypeBtn******************************************************
		//// *************************clickManageImageTypeBtn******************************************************

		//// *************************clickManageAngleMappingsBtn******************************************************
		//// *************************clickManageAngleMappingsBtn******************************************************
		ac.rwExcel("", "*********ManageAngleMappings**********", "");
		ac.Wait(wt);
		String patternS = "2019-GM-6N[A-Z]26-...-6N[A-Z]26-...";
		// String patternS="2019-GM-6NF26-1SA-6NF26-1SA"; //WORKS IN QA TOOL
		String noteS = "19 Cadi Int XT5";
		String editedNotesS = "Edited_19 Cadi Int XT5";
		UserListP.clickManageAngleMappings(driver);
		ac.Wait(wt * 2);
		AngleMappingList AngleMappingListP = new AngleMappingList(driver);
		// Input all fields and click the Cancel
		try {
			AngleMappingListP.clickAddAngleMappingBtn(driver);
			ac.Wait(wt);
			AngleMappingListP.inputInstance(driver, "vdvi_interior");
			ac.Wait(wt);
		} catch (Exception e) {
			AngleMappingListP.clickAddAngleMappingBtn(driver);
			ac.Wait(wt);
			AngleMappingListP.inputInstance(driver, "vdvi_interior");
			System.out.println("Click on Add Angle Mapping button twice!!");
			ac.Wait(wt);
		}
		AngleMappingListP.inputOEM(driver, "gm");
		AngleMappingListP.inputSequence(driver, "1");
		AngleMappingListP.inputNote(driver, "19 Cadi Int XT5");
		// Note for Pattern:
		// 2019-GM-6N[A-Z]26-...-6N[A-Z]26-...
		// good but need to input all info: vdvi_interior, GM, 2, 19 Cadi Int XT5, or only vdvi_interior is must.
		AngleMappingListP.inputPattern(driver, patternS);
		ac.Wait(wt * 4);
		AngleMappingListP.selectImageType(driver, "1001", 2);
		AngleMappingListP.clickCancel(driver);
		ac.Wait(wt);
		// Input all fields and click the Submit
		AngleMappingListP.clickAddAngleMappingBtn(driver);
		ac.Wait(wt);
		AngleMappingListP.inputInstance(driver, "vdvi_interior");
		ac.Wait(wt);
		AngleMappingListP.inputOEM(driver, "gm");
		AngleMappingListP.inputSequence(driver, "1");
		AngleMappingListP.inputNote(driver, noteS);
		// Note for Pattern:
		// 2019-GM-6N[A-Z]26-...-6N[A-Z]26-...
		// good but need to input all info: vdvi_interior, GM, 2, 19 Cadi Int XT5, or only vdvi_interior is must.
		AngleMappingListP.inputPattern(driver, patternS);
		ac.Wait(wt);
		// matches from QA: 10019-042,10029-044,10039-059,10049-058
		// AngleMappingListP.selectImageType(driver, "1001", 2);
		AngleMappingListP.selectImageType(driver, "10019", 42);
		// AngleMappingListP.selectImageType(driver, "1002", 4);
		AngleMappingListP.selectImageType(driver, "10029", 44);
		// AngleMappingListP.selectImageType(driver, "1003", 6);
		AngleMappingListP.selectImageType(driver, "10039", 59);
		// AngleMappingListP.selectImageType(driver, "1004", 8);
		AngleMappingListP.selectImageType(driver, "10049", 58);
		AngleMappingListP.clickSubmit(driver);
		ac.Wait(wt);
		ac.acceptAlert(driver, tc, "OK");
		ac.Wait(wt);
		// click Search and Edit
		AngleMappingListP.inputSearch(driver, noteS);
		AngleMappingListP.clickEditBtn(driver, 1);
		ac.Wait(wt);
		AngleMappingListP.inputNote(driver, editedNotesS);
		// matches from QA: 10019-042,10029-044,10039-059,10049-058
		// AngleMappingListP.selectImageType(driver, "1001", 2);
		AngleMappingListP.selectImageType(driver, "10019", 43);
		// AngleMappingListP.selectImageType(driver, "1002", 4);
		AngleMappingListP.selectImageType(driver, "10029", 45);
		// AngleMappingListP.selectImageType(driver, "1003", 6);
		AngleMappingListP.selectImageType(driver, "10039", 57);
		// AngleMappingListP.selectImageType(driver, "1004", 8);
		AngleMappingListP.selectImageType(driver, "10049", 56);
		AngleMappingListP.clickSubmit(driver);
		ac.acceptAlert(driver, tc, "OK");
		ac.Wait(wt);
		// Delete the Image Type just added one
		AngleMappingListP.inputSearch(driver, editedNotesS);
		AngleMappingListP.clickDeleteBtn(driver, 1);
		ac.acceptAlert(driver, tc, "OK");
		// verify the delete angle still be there by checking note
		AngleMappingListP.inputSearch(driver, editedNotesS);
		String noteName = AngleMappingListP.getNoteNameString(driver, 1);
		if (noteName.equalsIgnoreCase(editedNotesS)) {
			// Failed to delete an Angle
			System.out.println("\nFailed to delete an Angle.......");
		}

		AngleMappingListP.clickAngleMappingErrorsBtn(driver);
		ac.Wait(wt);
		AngleMappingListP.clickAngleMappingErrorsTab(driver);
		ac.Wait(wt);
		AngleMappingListP.clickFlikVehiclesErrorsTab(driver);
		ac.Wait(wt);
		AngleMappingListP.clickCloseBtn(driver);
		ac.Wait(wt);
		//// *************************clickManageAngleMappingsBtn******************************************************
		//// *************************clickManageAngleMappingsBtn******************************************************

		//// *************************ManageExportTemplates******************************************************
		//// *************************ManageExportTemplates******************************************************

		UserListP.clickManageExportTemplates(driver);
		ac.rwExcel("", "*********ManageExportTemplates**********", "");
		ac.Wait(wt);
		String searchName = "cdk123456";
		String editedName = "cdkxxxxxx";
		String templateS = "dealer_id Vin photo_updated photo_url\r\n{{#vehicles}}\r\n{{dealer.dlrCode}} {{vehicle.vin}} Y {{#imageUrls}}{{.}} {{/imageUrls}}\r\n{{/vehicles}}";
		ExportTemplateList ExportTemplateListP = new ExportTemplateList(driver);
		// Add an Export Template and cancel
		ExportTemplateListP.clickAddExportTemplateBtn(driver);
		ExportTemplateListP.inputExportName(driver, searchName);
		ExportTemplateListP.inputExportPrettyName(driver, searchName.toUpperCase());
		ExportTemplateListP.inputFileName(driver, "phone.txt");
		ExportTemplateListP.inputUser(driver, "TEST@autodata.net");
		ExportTemplateListP.inputPassword(driver, "5k2cGG1");
		ExportTemplateListP.inputHost(driver, "LOCALHOST");
		ExportTemplateListP.inputTemplate(driver, templateS);
		ExportTemplateListP.clickCombinedFileCheckBox(driver);
		ExportTemplateListP.clickBrandedImagesCheckBox(driver);
		ExportTemplateListP.clickCancel(driver);
		ac.Wait(wt);
		// Add an Export Template and submit
		ExportTemplateListP.clickAddExportTemplateBtn(driver);
		ExportTemplateListP.inputExportName(driver, searchName);
		ExportTemplateListP.inputExportPrettyName(driver, searchName.toUpperCase());
		ExportTemplateListP.inputFileName(driver, "phone.txt");
		ExportTemplateListP.inputUser(driver, "TEST@autodata.net");
		ExportTemplateListP.inputPassword(driver, "5k2cGG1");
		ExportTemplateListP.inputHost(driver, "LOCALHOST");
		ExportTemplateListP.inputTemplate(driver, templateS);
		ac.Wait(wt);
		ExportTemplateListP.clickCombinedFileCheckBox(driver);
		ExportTemplateListP.clickBrandedImagesCheckBox(driver);
		ac.Wait(wt);
		ExportTemplateListP.clickBrandedImagesCheckBox(driver);
		ExportTemplateListP.clickBrandedImagesCheckBox(driver);
		ExportTemplateListP.clickSubmit(driver);
		ac.acceptAlert(driver, tc, "OK");
		// Edit Export Template
		ExportTemplateListP.inputSearch(driver, searchName);
		ac.Wait(wt);
		ExportTemplateListP.clickEditBtn(driver, 1);
		ExportTemplateListP.inputExportName(driver, editedName);
		ExportTemplateListP.inputExportPrettyName(driver, editedName.toUpperCase());
		ExportTemplateListP.inputFileName(driver, "Edited_phone.txt");
		ExportTemplateListP.inputUser(driver, "Edited_TEST@autodata.net");
		ExportTemplateListP.inputPassword(driver, "Edited_5k2cGG1");
		ExportTemplateListP.inputHost(driver, "Edited_LOCALHOST");
		ExportTemplateListP.inputTemplate(driver, "Edited_" + templateS);
		ac.Wait(wt);
		ExportTemplateListP.clickCombinedFileCheckBox(driver);
		ExportTemplateListP.clickBrandedImagesCheckBox(driver);
		ac.Wait(wt);
		ExportTemplateListP.clickCombinedFileCheckBox(driver);
		ExportTemplateListP.clickBrandedImagesCheckBox(driver);
		ExportTemplateListP.clickSubmit(driver);
		ac.acceptAlert(driver, tc, "OK");
		ac.Wait(wt);
		// Delete an Export Template
		ExportTemplateListP.inputSearch(driver, editedName);
		ExportTemplateListP.clickDeleteBtn(driver, 1);
		ac.acceptAlert(driver, tc, "OK");
		ac.Wait(wt);
		ExportTemplateListP.inputSearch(driver, editedName);
		String newName = ExportTemplateListP.getNameString(driver, 1);
		tc = "Delate an Export Tempate";
		if (newName.equalsIgnoreCase(editedName)) {
			// Bug here. Failed to delete an Export Template...logged AUTOPXOPS-1171
			System.out.println("\nFailed to delete an Export Template here......logged AUTOPXOPS-1171");
			ac.rwExcel(tc, false, "Delete an Export Template",
					"Failed to delete an Export Template here......logged AUTOPXOPS-1171");
		} else {
			ac.rwExcel(tc, true, "Delete an Export Template", "Edited name is not showing - Deleted.");
		}
		//// *************************ManageExportTemplates******************************************************
		//// *************************ManageExportTemplates******************************************************

		//// *************************ManageGlobalConfig******************************************************
		//// *************************ManageGlobalConfig******************************************************
		for (int j = 1; j <= 1; j++) { // 100 worked fine.
			UserListP.clickManageGlobalConfig(driver);
			ac.rwExcel("", "*********ManageGlobalConfig**********", "");
			String searchKey = "test_IMPORT_SITE";
			String editedKey = "edited_test_IMPORT_SITE";
			String valueS = "CC35943D,CC35953D,TC35903D,CK35943D,TC35943D,TK35903D,TK35953D,CC35903D,CK35953D,TC35953D,TK35943D,CK35903D";
			// From Production 20180917:
			// DRW_CODES CC35943D,CC35953D,TC35903D,CK35943D,TC35943D,TK35903D,TK35953D,CC35903D,CK35953D,TC35953D,TK35943D,CK35903D
			// IMPORT_SITE FNOC-PR3V-XET2
			GlobalConfig GlobalConfigP = new GlobalConfig(driver);
			GlobalConfigP.clickAddGlobalConfigBtn(driver);
			ac.Wait(wt);
			GlobalConfigP.inputKey(driver, searchKey);
			GlobalConfigP.inputValue(driver, valueS);
			GlobalConfigP.clickCancel(driver);
			ac.Wait(wt);
			//// Add an Global Config and cancel
			tc = "Add an Global Config";
			GlobalConfigP.clickAddGlobalConfigBtn(driver);
			GlobalConfigP.inputKey(driver, searchKey);
			GlobalConfigP.inputValue(driver, valueS);
			try {
				GlobalConfigP.clickSubmit(driver);
				ac.acceptAlert(driver, tc, "OK");
				GlobalConfigP.inputSearch(driver, searchKey);
			} catch (Exception e) {
				ac.Wait(wt * 2);
				try {
					GlobalConfigP.clickSubmit(driver);
					ac.acceptAlert(driver, tc, "OK");
					GlobalConfigP.inputSearch(driver, searchKey);
					System.out.println("\nFirst click it did not work? clickSubmit twice!!!!!!");
				} catch (Exception ex2) {
					GlobalConfigP.clickCancel(driver);
					ac.Wait(wt);
					ac.rwExcel(tc, false, "Add an Global Config", "The issue occurs again, see AUTOPXOPS-1172....");
					System.out.println(
							"\nClick Cancel to cancel the sumbit and wait...since the issue occurs again, see AUTOPXOPS-1172....");
				}
			}
			// Edit Global Config
			tc = "Edit Global Config";
			GlobalConfigP.clickEditBtn(driver, 1);
			if (GlobalConfigP.checkKeyFieldLocked(driver, tc)) {
				// Key field now is enabled to input when it should be locked on Edit
				ac.rwExcel(tc, false, "Edit Global Config", "The issue occurs again, see AUTOPXOPS-1173....");
				System.out.println(
						"\nFailed, the Key field now is enabled to input when it should be locked on Edit here......");
			}
			;
			GlobalConfigP.inputValue(driver, "edited" + valueS);
			ac.Wait(wt);
			GlobalConfigP.clickSubmit(driver);
			// System.out.println("\nThe issue occurs again, see AUTOPXOPS-1172....");
			ac.acceptAlert(driver, tc, "OK");
			ac.Wait(wt);
			// Delete an Global Config
			tc = "Delete an Global Config";
			GlobalConfigP.inputSearch(driver, "edited");
			GlobalConfigP.clickDeleteBtn(driver, 1);
			ac.acceptAlert(driver, tc, "OK");
			GlobalConfigP.inputSearch(driver, "edited");
			String newKey = GlobalConfigP.getKeyString(driver, 1);
			if (newKey.equalsIgnoreCase(editedKey)) {
				// Failed to delete an Global Config...
				ac.rwExcel(tc, false, "Delete an Global Config", "The issue occurs again, see AUTOPXOPS-1172,1173....");
				System.out.println("\nFailed to delete an Global Config here......");
			}
			System.out.println("Loop counts = " + j);
		}
		// Stop here!!!

		//// *************************ManageGlobalConfig******************************************************
		//// *************************ManageGlobalConfig******************************************************

		driver.close();
		// switchToWindow(driver, parentHandle);
		// driver.close();

	}

	public static void ManageDealerShipsAddNewAccount(WebDriver driver, String brw, String versionNum, String envment,
			String checkEmail) throws Exception {

		// Load environment parameters
		Properties prop = new Properties();
		// testprop.load(new FileInputStream("data/autopxConf.properties"));
		try {
			prop.load(AdminPortalController.class.getClassLoader()
					.getResourceAsStream("AdminPortalData/adminPortalConf.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String tc = "";
		String env = prop.getProperty("AUTOpx.environment");
		String envBrowser = prop.getProperty("AUTOpx.browser");
		String render = prop.getProperty("AUTOpx.render");
		String addNewVIN = prop.getProperty("AUTOpx.addNewVIN");
		String accountEmail = prop.getProperty(env + ".VINpxEmail");
		String accountPS = prop.getProperty(env + ".VINpxPassword");
		// String baseURL = prop.getProperty(env + ".VINpxDealerPortalBaseURL");
		String dealershipName = prop.getProperty(env + ".VINpxDealershipname");
		String dealerCode = prop.getProperty(env + ".VINpxDealerCode");
		String vin01 = prop.getProperty(env + ".VINpxVin01");
		String vin02 = prop.getProperty(env + ".VINpxVin02");
		String vehGUID01 = prop.getProperty(env + ".VINpxVin01GUID");
		String vehGUID02 = prop.getProperty(env + ".VINpxVin02GUID");
		// String vinpxnewVin01 = prop.getProperty(env + ".VINpxNewVIN01");
		String[] VINpxNewVINs = fetchOneDemArrayFromPropFile(env + ".VINpxNewVINs", prop);
		String serverName = prop.getProperty(env + ".serverName");
		String dbName = prop.getProperty(env + ".dbName");
		String userName = prop.getProperty(env + ".userName");
		String password = prop.getProperty(env + ".password");
		String MaxVins = prop.getProperty(env + ".MaxVinsForPreview");
		int MaxVinsForPreview = Integer.parseInt(MaxVins);
		// dealership profile:
		String OEM = prop.getProperty(env + ".OEM");
		String[] Brands = fetchOneDemArrayFromPropFile(env + ".Brands", prop);
		String AllProdDealerCode = prop.getProperty(env + ".AllProdDealerCode");
		String AllProdEmail = prop.getProperty(env + ".AllProdEmail");
		String DealershipID = prop.getProperty(env + ".DealershipID");
		String DealershipName = prop.getProperty(env + ".DealershipName");
		String[] Products = fetchOneDemArrayFromPropFile(env + ".Products", prop);
		String MetadataValues = prop.getProperty(env + ".MetadataValues");
		String Address = prop.getProperty(env + ".Address");
		String AddressLine2 = prop.getProperty(env + ".AddressLine2");
		String City = prop.getProperty(env + ".City");
		int StateProvince = Integer.parseInt(prop.getProperty(env + ".StateProvince"));
		int Country = Integer.parseInt(prop.getProperty(env + ".Country"));
		String ZipPostalCode = prop.getProperty(env + ".ZipPostalCode");
		String DealershipEmail = prop.getProperty(env + ".DealershipEmail");
		String AccountEmail = prop.getProperty(env + ".AccountEmail");
		String FirstName = prop.getProperty(env + ".FirstName");
		String LastName = prop.getProperty(env + ".LastName");
		String TagLineMarkingMsg = prop.getProperty(env + ".TagLineMarkingMsg");
		String Website = prop.getProperty(env + ".Website");
		String DealershipPhoneNumber = prop.getProperty(env + ".DealershipPhoneNumber");
		int TemplateSettings = Integer.parseInt(prop.getProperty(env + ".TemplateSettings"));
		int SelectBackgroundSet = Integer.parseInt(prop.getProperty(env + ".SelectBackgroundSet"));
		int wt = Integer.parseInt(prop.getProperty("AUTOpx.waitTime"));
		String AddNewAccountEmail = prop.getProperty(env + ".AddNewAccountEmail");

		// Initial
		// final int wt_Secs = 6;
		String TCnum;
		// ====================
		String tempVIN = "";
		String tempVehGUID = "";
		String ProductVINpx = "";
		String ProductSTOCKpx = "";
		String ProductLOTpx = "";
		String Dealership_ID = "";
		String Dealership_Name = "";
		String Dealership_Email = "";
		String Account_Email = "";
		String Metadata = "";
		String dlrGuid = "";
		// ====================
		Comlibs ac = new Comlibs();
		ac.rwExcel("", "*********ManageDealerShips**********", "");

		int count = 0;
		String getMetadataSavePathFile = "C:\\1\\Eclipse\\Test Results\\AUTOpx" + "\\Metadata_" + env + ".xls";
		String[] titleString = { "Env.", "S/N", "Dealership_ID", "Dealership_Name", "Account_Email", "Dealership_Email",
				"ProductVINpx", "ProductSTOCKpx", "ProductLOTpx", "Metadata", "dlrGuid" };
		// =================================================
		ac.writeTitle(getMetadataSavePathFile, titleString);
		int dataLength = 54;
		String[] metadataValues = new String[dataLength + 1];
		int datasize = metadataValues.length;

		// =================================================
		VDVILogin loginP = new VDVILogin(driver);
		int dealerN = 0;
		String dealerSN = "";
		loginP.login(driver, accountEmail, accountPS, "");
		String parentHandle = driver.getWindowHandle(); // get the current window handle

		UserList UserListP = new UserList(driver);
		// *************************UserListP******************************************************
		// *************************UserListP******************************************************
		ac.Wait(wt);
		UserListP.scrollUp(driver, 3000, "ddd"); // QA -2000 Prod -3000 - negative means scrolldown
		UserListP.clickDisplayDropDownBtn(driver, "3");
		UserListP.scrollUp(driver, -3000, "ddd"); // QA -2000 Prod -3000 - negative means scrolldown
		// =========================== Add New Account Process============================================================
		tc = "TC_addNewAct_AttachDealer_DeleteIt";
		UserListP.clickAddAccount(driver);
		AccountProfile AccountProfileP = new AccountProfile(driver);
		AccountProfileP.inputAccountEmail(driver, AddNewAccountEmail);
		AccountProfileP.inputFirstName(driver, FirstName);
		AccountProfileP.inputLastName(driver, LastName);
		AccountProfileP.selectAccountStatus(driver, 1);
		AccountProfileP.clickBackToDealerListBtn(driver, parentHandle, tc);

		// =========================== Add Account============================================================
		tc = "TC_addNewAct_with_Existing_ActEamil";
		UserListP.clickAddAccount(driver);
		// AccountProfile AccountProfileP = new AccountProfile(driver);
		AccountProfileP.inputAccountEmail(driver, accountEmail);
		AccountProfileP.inputFirstName(driver, FirstName);
		AccountProfileP.inputLastName(driver, LastName);
		AccountProfileP.selectAccountStatus(driver, 1);
		AccountProfileP.clickBackToDealerListBtn(driver, parentHandle, tc);
		tc = "TC_addNewAct_with_Existing_ActEamil_SAVE";
		UserListP.clickAddAccount(driver);
		AccountProfileP.inputAccountEmail(driver, accountEmail);
		AccountProfileP.inputFirstName(driver, FirstName);
		AccountProfileP.inputLastName(driver, LastName);
		tc = "TC_addNewAct_AccountStatusShouldNotBeChanged";
		try {
			AccountProfileP.selectAccountStatus(driver, 2);// 1- Active, 2- Lock out, 3-Change Password, 4-Disabled
			ac.rwExcel(tc, false, "Add Account - Account Status ", "Not Working - Can change status to 2 - Lock out");
		} catch (Exception e) {
			ac.rwExcel(tc, true, "Add Account - Account Status ", "Works good - Cannot change status to 2 - Lock out");
			AccountProfileP.selectAccountStatus(driver, 1);
		}
		AccountProfileP.clickSaveBtn(driver, tc);
		tc = "TC_addNewAct_with_Existing_ActEamil_checkMSG";

		boolean MessageExistForAddExistAccountEmail = AccountProfileP.checkMessageDisplayedHead(driver,
				"Check required fields");
		if (MessageExistForAddExistAccountEmail) {
			ac.rwExcel(tc, true, "Add an Account ", "With Exist Account Email");
		} else {
			ac.rwExcel(tc, false, "Add an Account ", "With Exist Account Email");
		}
		AccountProfileP.clickBackToDealerListBtn(driver, parentHandle, tc);
		// Stop here!!! All above work fine.

		// =========================== Add Account============================================================

		// =========================== Add Dealership for existing account============================================================
		tc = "TC139021_01";
		UserListP.clickAddDealerShip(driver);

		DealerProfile DealerProfieP = new DealerProfile(driver);
		DealerProfieP.selectOEM(driver, 13);
		// check Buick and Cadillac and Chevrolet and GMC
		// DealerProfieP.selectOEMBrands(driver, 1); // check Buick
		// DealerProfieP.selectOEMBrands(driver, 2); // check Cadillac
		// DealerProfieP.selectOEMBrands(driver, 3); // check Chevrolet
		// DealerProfieP.selectOEMBrands(driver, 4); // check GMC
		// DealerProfieP.selectOEMBrands(driver, 5); // check Hummer
		for (String brand : Brands) {
			DealerProfieP.selectOEMBrands(driver, Integer.parseInt(brand));
		}
		DealerProfieP.inputDealersipID(driver, DealershipID);
		DealerProfieP.selectVINpxProd(driver);
		DealerProfieP.selectSTOCKpxProd(driver);
		// DealerProfieP.selectLOTpxProd(driver);
		// DealerProfieP.inputMetadata(driver, MetadataValues);

		// DealerProfieP.selectTemplateSetting(driver, TemplateSettings);// DEFAULT=1; replace=2;overlay=3;
		// DealerProfieP.selectTemplateSetting(driver, 1);
		DealerProfieP.inputDealershipName(driver, DealershipName);
		DealerProfieP.inputAddress(driver, Address);
		DealerProfieP.inputAddressLine2(driver, AddressLine2);
		DealerProfieP.inputCity(driver, City);
		DealerProfieP.inputDealersipEmail(driver, DealershipEmail);
		DealerProfieP.inputZipCode(driver, ZipPostalCode);
		DealerProfieP.inputCountry(driver, Country);// USA=1
		DealerProfieP.inputState(driver, StateProvince);// NY=33
		DealerProfieP.inputTagLineMarkingMsg(driver, TagLineMarkingMsg);
		DealerProfieP.inputWebsite(driver, Website);
		DealerProfieP.inputDealershipPhone(driver, DealershipPhoneNumber);

		DealerProfieP.selectBackGroundSet(driver, SelectBackgroundSet);// Generic Dealership=7; White Gradient=0
		DealerProfieP.scrollUp(driver, -3000, tc);
		DealerProfieP.clickSaveBtn(driver, tc);

		tc = "AddDealerInvalid_withExistDealershipID";
		boolean MessageExist = DealerProfieP.checkMessageDisplayedHead(driver,
				"There is already a record with this Manufacturer and Dealer Code.", tc);// "There is already a user record with this Login");
		if (MessageExist) {
			ac.rwExcel(tc, true, "Add a dealership ", "With Exist DealershipID");
		} else {
			ac.rwExcel(tc, false, "Add a dealership ", "With Exist DealershipID");
		}

		DealerProfieP.clickBackToDealerListBtn(driver, parentHandle, tc);

		// **************************Add a new dealership for account*****************************************************
		// click Add Dealership btn
		UserListP.clickAddDealerShip(driver);
		DealerProfieP.selectOEM(driver, 13);
		for (String brand : Brands) {
			DealerProfieP.selectOEMBrands(driver, Integer.parseInt(brand));
		}

		DealerProfieP.inputDealersipID(driver, DealershipID + "_New_Added_14");// New one show be 15
		DealerProfieP.selectVINpxProd(driver);
		DealerProfieP.selectSTOCKpxProd(driver);
		// DealerProfieP.selectLOTpxProd(driver);
		DealerProfieP.inputMetadata(driver, MetadataValues);

		// DealerProfieP.selectTemplateSetting(driver, TemplateSettings);// DEFAULT=1; replace=2;overlay=3;
		// DealerProfieP.selectTemplateSetting(driver, 1);
		DealerProfieP.inputDealershipName(driver, DealershipName);
		DealerProfieP.inputAddress(driver, Address);
		DealerProfieP.inputAddressLine2(driver, AddressLine2);
		DealerProfieP.inputCity(driver, City);
		DealerProfieP.inputDealersipEmail(driver, "Autotomsmith4@gmail.com");// Auto_Added_"+DealershipEmail);
		DealerProfieP.inputZipCode(driver, ZipPostalCode);
		DealerProfieP.inputCountry(driver, Country);// USA=1
		DealerProfieP.inputState(driver, StateProvince);// NY=33
		DealerProfieP.inputTagLineMarkingMsg(driver, TagLineMarkingMsg);
		DealerProfieP.inputWebsite(driver, Website);
		DealerProfieP.inputDealershipPhone(driver, DealershipPhoneNumber);

		DealerProfieP.selectBackGroundSet(driver, SelectBackgroundSet);// Generic Dealership=7; White Gradient=0
		DealerProfieP.scrollUp(driver, -3000, tc);
		DealerProfieP.clickSaveBtn(driver, tc);
		// Stop here for the time being since there is bug here AUTOPXOPS-1227

		tc = "AddDealerInvalid_withMissingMUSTField";
		// The successful message "Your settings have been saved" will only show one second then disappear.
		// So the successful message should be empty "" here;
		String successfulMsg = "";
		MessageExist = DealerProfieP.checkMessageDisplayedHead(driver, "Your settings have been saved", tc);
		// Bug here since entered Metadata. See AUTOPXOPS-1227. Now it shows an error "An error occurred. Please try again."
		// but the dealership has been created in our system.
		if (MessageExist) {
			ac.rwExcel("AddDealervalid", true, "Add a new dealership with all fields",
					"Sucessful msg shows: Your settings have been saved");
		} else {
			ac.rwExcel("AddDealervalid", false, "Add a new dealership with all fields",
					"Failed to shows msg: Your settings have been saved. Currently it shows: An error occurred. Please try again. There is bug here, see AUTOPXOPS-1227");
		}

		DealerProfieP.clickBackToDealerListBtn(driver, parentHandle, tc);
		// =========================== Add Dealership============================================================

		// **************************************************************************************
		//
		UserListP.clickManageAccounts(driver);
		ac.Wait(wt);
		UserListP.inputSearch(driver, AllProdEmail);
		// **************************************************************************************
		// **************************************************************************************
		// **************************************************************************************
		// **************************************************************************************
		// **************************************************************************************

		UserListP.clickEditBtn(driver, "1");// 1,2,3...
		String attachedDealerName = AccountProfileP.selectOneDealerFrAllDealers(driver, 7, tc);
		boolean dealerExistInAllDealers = false;
		boolean dealerExistInAccountDealers = false;
		tc = "Dealer should not exist in Account Dealer field_01";
		dealerExistInAccountDealers = AccountProfileP.verifyOneDealerInAccountDealersField(driver, attachedDealerName,
				false, tc);
		AccountProfileP.clickRightArrowAttachBtn(driver);
		tc = "TC_Verify atached dealer from All Dealers_01";
		dealerExistInAllDealers = AccountProfileP.verifyOneDealerInAllDealersField(driver, attachedDealerName, true,
				tc);
		tc = "Dealer should exist in Account Dealer field_02";
		dealerExistInAccountDealers = AccountProfileP.verifyOneDealerInAccountDealersField(driver, attachedDealerName,
				true, tc);
		AccountProfileP.selectOneDealerFrAccountDealers(driver, attachedDealerName, tc);
		ac.Wait(wt);
		AccountProfileP.clickLeftArrowDetachBtn(driver);
		tc = "TC_Verify detach a dealer from Account Dealers_01";
		dealerExistInAccountDealers = AccountProfileP.verifyOneDealerInAccountDealersField(driver, attachedDealerName,
				true, tc);

		AccountProfileP.scrollUp(driver, -3000, tc);
		AccountProfileP.clickBackToDealerListBtn(driver, parentHandle, tc);
		tc = "TC139406_n1";
		UserListP.clickExpandDealersArrow(driver, 1);
		UserListP.clickEditOnDealer(driver, 1);
		DealerProfieP.clickBackToDealerListBtn(driver, parentHandle, tc);
		UserListP.clickViewDealerPortal(driver, 1, tc);

		DealerPortal.DealerProfile DealerPortalDealerProfieP = new DealerPortal.DealerProfile(driver);
		DealerPortalDealerProfieP.clickInventoryGalleryBtn(driver, tc);
		DealerPortal.ImageGallery DealerPortalImageGalleryP = new DealerPortal.ImageGallery(driver);
		DealerPortalImageGalleryP.clickDealerShipInfoBtn(driver);

		driver.close();// Close Dealer Profile page
		// goto parent page
		// for (String winHandle : driver.getWindowHandles()) {
		// driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
		// }
		ac.switchToWindow(driver);

		System.out.println("Stop here 2018-10-03");
		// Stop here!!! 2018-10-01

		// driver.close();
		// ac.switchToWindow(driver);
		// UserListP.clickDealerViewBtn(driver, 1);
		// driver.close();
		// ac.switchToWindow(driver);
		// *************************UserListP******************************************************
		//// *************************UserListP******************************************************

		//// *************************clickManageBGSetsBtn******************************************************
		//// *************************clickManageBGSetsBtn******************************************************
		ac.rwExcel("", "*********ManageBackGroundSets**********", "");
		UserListP.clickManageBGSets(driver);
		BackgroundSets BackgroundSetsP = new BackgroundSets(driver);
		// BackgroundSetsP.clickMapBackGrounds(driver, 3);
		BackgroundSetsP.inputSearch(driver, AllProdDealerCode);
		// ac.Wait(wt);
		try {
			BackgroundSetsP.clickEditSetBtn(driver, 1);
			ac.Wait(wt);
			BackgroundSetsP.clickAllDealershipsCheckBox(driver);// check
		} catch (Exception ex2) {
			ac.Wait(wt);
			BackgroundSetsP.clickEditSetBtn(driver, 1);
			ac.Wait(wt);
			BackgroundSetsP.clickAllDealershipsCheckBox(driver);// check
			System.out.println("\nFirst click it did not appear.Wait... ClickAllDealershipsCheckBox twice!!!!!");
		}
		ac.Wait(wt);
		BackgroundSetsP.clickAllDealershipsCheckBox(driver);// uncheck
		ac.Wait(wt);
		BackgroundSetsP.clickCancel(driver);
		ac.Wait(wt);
		BackgroundSetsP.clickManageBGImageBtn(driver, 1);
		ManageBackgrounds ManageBackgroundsP = new ManageBackgrounds(driver);
		ManageBackgroundsP.clickBackToManageSets(driver);
		BackgroundSetsP.clickDealersUseBackGroundBtn(driver, 1);
		ac.Wait(wt);
		BackgroundSetsP.clickClose(driver);
		ac.Wait(wt);
		BackgroundSetsP.clickCreateNewSet(driver);
		ac.Wait(wt);
		BackgroundSetsP.inputSetName(driver, "a");
		BackgroundSetsP.clickCancel(driver);
		ac.Wait(wt);
		BackgroundSetsP.clickCreateNewSet(driver);
		ac.Wait(wt);
		BackgroundSetsP.inputSetName(driver, "a");
		BackgroundSetsP.selectSetType(driver, 3);// 1-Old (Do Not User), 2-Flat on Flat on Flat, 3-Normal, 4-GM Only, 5-FCA Only
		System.out.println("\nPlease wait at least 2 minutes untill Backgrounds page showing...");
		ac.Wait(wt);
		BackgroundSetsP.clickSubmit(driver);
		ac.Wait(wt);
		UserListP.clickManageBGSets(driver);
		ac.clickRefleshF5Btn(driver, tc);
		tc = "TC148169_n";
		BackgroundSetsP.clickDeleteBGSetBtn(driver, 1, tc);
		ac.acceptAlert(driver, tc, "OK");
		// ac.clickRefleshF5Btn(driver, tc);

		//// *************************clickManageBGSetsBtn******************************************************
		//// *************************clickManageBGSetsBtn******************************************************

		//// *************************clickManageImageTypeBtn******************************************************
		//// *************************clickManageImageTypeBtn******************************************************
		ac.rwExcel("", "*********ManageImageType**********", "");
		ac.Wait(wt);
		String searchDefaultSequence = "10100";
		String editedDefaultSequence = "10101";
		ac.clickRefleshF5Btn(driver, tc);
		// UserListP.clickManageAccounts(driver);
		UserListP.clickManageImageType(driver);
		ImageTypeList ImageTypeListP = new ImageTypeList(driver);
		// Add an Image Type and cancel
		ImageTypeListP.clickAddImageTypeBtn(driver);
		ac.Wait(wt);
		ImageTypeListP.inputShortIdentifier(driver, "996");
		ImageTypeListP.inputImageGroup(driver, "CUSTOM");
		ImageTypeListP.inputImageDefinition(driver, "DEALER IMAGE");
		ImageTypeListP.inputImageDescription(driver, "VEHICLE BENEFITS");
		ImageTypeListP.inputDefaultSequence(driver, searchDefaultSequence);
		ImageTypeListP.inputBackGroundType(driver, "n");
		ImageTypeListP.clickCancel(driver);
		ac.Wait(wt);
		// Add an Image Type and submit
		UserListP.clickManageImageType(driver);
		ImageTypeListP.clickAddImageTypeBtn(driver);
		ImageTypeListP.inputShortIdentifier(driver, "996");
		ImageTypeListP.inputImageGroup(driver, "CUSTOM");
		ImageTypeListP.inputImageDefinition(driver, "DEALER IMAGE");
		ImageTypeListP.inputImageDescription(driver, "VEHICLE BENEFITS");
		ImageTypeListP.inputDefaultSequence(driver, searchDefaultSequence);
		ImageTypeListP.inputBackGroundType(driver, "n");
		ImageTypeListP.clickSubmit(driver);
		ac.acceptAlert(driver, tc, "OK");
		ac.Wait(wt);
		// Edit an Image Type and submit
		ImageTypeListP.inputSearch(driver, searchDefaultSequence);
		ImageTypeListP.clickEditBtn(driver, 1);
		ImageTypeListP.inputShortIdentifier(driver, "996");
		ImageTypeListP.inputImageGroup(driver, "Edited" + "CUSTOM");
		ImageTypeListP.inputImageDefinition(driver, "Edited" + "DEALER IMAGE");
		ImageTypeListP.inputImageDescription(driver, "Edited" + "VEHICLE BENEFITS");
		ImageTypeListP.inputDefaultSequence(driver, editedDefaultSequence);
		ImageTypeListP.inputBackGroundType(driver, "n");
		ImageTypeListP.clickSubmit(driver);
		ac.acceptAlert(driver, tc, "OK");
		ac.Wait(wt);
		// Delete an Image Type and submit
		ImageTypeListP.inputSearch(driver, editedDefaultSequence);
		ImageTypeListP.clickDeleteBtn(driver, 1);
		ac.acceptAlert(driver, tc, "OK");
		ac.Wait(wt);
		ImageTypeListP.inputSearch(driver, editedDefaultSequence);
		String newDefaultSequenceName = ImageTypeListP.getDefaultSequenceRowString(driver, 1);
		if (newDefaultSequenceName.equalsIgnoreCase(editedDefaultSequence)) {
			// Bug here. Failed to delete an Image Type
			System.out.println(
					"Failed to submit for adding an image type if the Shot Identifier existing in system like 999 ...");
		}

		//// *************************clickManageImageTypeBtn******************************************************
		//// *************************clickManageImageTypeBtn******************************************************

		//// *************************clickManageAngleMappingsBtn******************************************************
		//// *************************clickManageAngleMappingsBtn******************************************************
		ac.rwExcel("", "*********ManageAngleMappings**********", "");
		ac.Wait(wt);
		String patternS = "2019-GM-6N[A-Z]26-...-6N[A-Z]26-...";
		// String patternS="2019-GM-6NF26-1SA-6NF26-1SA"; //WORKS IN QA TOOL
		String noteS = "19 Cadi Int XT5";
		String editedNotesS = "Edited_19 Cadi Int XT5";
		UserListP.clickManageAngleMappings(driver);
		AngleMappingList AngleMappingListP = new AngleMappingList(driver);
		// Input all fields and click the Cancel
		AngleMappingListP.clickAddAngleMappingBtn(driver);
		AngleMappingListP.inputInstance(driver, "vdvi_interior");
		ac.Wait(wt);
		AngleMappingListP.inputOEM(driver, "gm");
		AngleMappingListP.inputSequence(driver, "1");
		AngleMappingListP.inputNote(driver, "19 Cadi Int XT5");
		// Note for Pattern:
		// 2019-GM-6N[A-Z]26-...-6N[A-Z]26-...
		// good but need to input all info: vdvi_interior, GM, 2, 19 Cadi Int XT5, or only vdvi_interior is must.
		AngleMappingListP.inputPattern(driver, patternS);
		ac.Wait(wt * 4);
		AngleMappingListP.selectImageType(driver, "1001", 2);
		AngleMappingListP.clickCancel(driver);
		ac.Wait(wt);
		// Input all fields and click the Submit
		AngleMappingListP.clickAddAngleMappingBtn(driver);
		ac.Wait(wt);
		AngleMappingListP.inputInstance(driver, "vdvi_interior");
		ac.Wait(wt);
		AngleMappingListP.inputOEM(driver, "gm");
		AngleMappingListP.inputSequence(driver, "1");
		AngleMappingListP.inputNote(driver, noteS);
		// Note for Pattern:
		// 2019-GM-6N[A-Z]26-...-6N[A-Z]26-...
		// good but need to input all info: vdvi_interior, GM, 2, 19 Cadi Int XT5, or only vdvi_interior is must.
		AngleMappingListP.inputPattern(driver, patternS);
		ac.Wait(wt);
		// matches from QA: 10019-042,10029-044,10039-059,10049-058
		// AngleMappingListP.selectImageType(driver, "1001", 2);
		AngleMappingListP.selectImageType(driver, "10019", 42);
		// AngleMappingListP.selectImageType(driver, "1002", 4);
		AngleMappingListP.selectImageType(driver, "10029", 44);
		// AngleMappingListP.selectImageType(driver, "1003", 6);
		AngleMappingListP.selectImageType(driver, "10039", 59);
		// AngleMappingListP.selectImageType(driver, "1004", 8);
		AngleMappingListP.selectImageType(driver, "10049", 58);
		AngleMappingListP.clickSubmit(driver);
		ac.Wait(wt);
		ac.acceptAlert(driver, tc, "OK");
		ac.Wait(wt);
		// click Search and Edit
		AngleMappingListP.inputSearch(driver, noteS);
		AngleMappingListP.clickEditBtn(driver, 1);
		ac.Wait(wt);
		AngleMappingListP.inputNote(driver, editedNotesS);
		// matches from QA: 10019-042,10029-044,10039-059,10049-058
		// AngleMappingListP.selectImageType(driver, "1001", 2);
		AngleMappingListP.selectImageType(driver, "10019", 43);
		// AngleMappingListP.selectImageType(driver, "1002", 4);
		AngleMappingListP.selectImageType(driver, "10029", 45);
		// AngleMappingListP.selectImageType(driver, "1003", 6);
		AngleMappingListP.selectImageType(driver, "10039", 57);
		// AngleMappingListP.selectImageType(driver, "1004", 8);
		AngleMappingListP.selectImageType(driver, "10049", 56);
		AngleMappingListP.clickSubmit(driver);
		ac.acceptAlert(driver, tc, "OK");
		ac.Wait(wt);
		// Delete the Image Type just added one
		AngleMappingListP.inputSearch(driver, editedNotesS);
		AngleMappingListP.clickDeleteBtn(driver, 1);
		ac.acceptAlert(driver, tc, "OK");
		// verify the delete angle still be there by checking note
		AngleMappingListP.inputSearch(driver, editedNotesS);
		String noteName = AngleMappingListP.getNoteNameString(driver, 1);
		if (noteName.equalsIgnoreCase(editedNotesS)) {
			// Failed to delete an Angle
			System.out.println("\nFailed to delete an Angle.......");
		}

		AngleMappingListP.clickAngleMappingErrorsBtn(driver);
		ac.Wait(wt);
		AngleMappingListP.clickAngleMappingErrorsTab(driver);
		ac.Wait(wt);
		AngleMappingListP.clickFlikVehiclesErrorsTab(driver);
		ac.Wait(wt);
		AngleMappingListP.clickCloseBtn(driver);
		ac.Wait(wt);
		//// *************************clickManageAngleMappingsBtn******************************************************
		//// *************************clickManageAngleMappingsBtn******************************************************

		//// *************************ManageExportTemplates******************************************************
		//// *************************ManageExportTemplates******************************************************

		UserListP.clickManageExportTemplates(driver);
		ac.rwExcel("", "*********ManageExportTemplates**********", "");
		ac.Wait(wt);
		String searchName = "cdk123456";
		String editedName = "cdkxxxxxx";
		String templateS = "dealer_id Vin photo_updated photo_url\r\n{{#vehicles}}\r\n{{dealer.dlrCode}} {{vehicle.vin}} Y {{#imageUrls}}{{.}} {{/imageUrls}}\r\n{{/vehicles}}";
		ExportTemplateList ExportTemplateListP = new ExportTemplateList(driver);
		// Add an Export Template and cancel
		ExportTemplateListP.clickAddExportTemplateBtn(driver);
		ExportTemplateListP.inputExportName(driver, searchName);
		ExportTemplateListP.inputExportPrettyName(driver, searchName.toUpperCase());
		ExportTemplateListP.inputFileName(driver, "phone.txt");
		ExportTemplateListP.inputUser(driver, "TEST@autodata.net");
		ExportTemplateListP.inputPassword(driver, "5k2cGG1");
		ExportTemplateListP.inputHost(driver, "LOCALHOST");
		ExportTemplateListP.inputTemplate(driver, templateS);
		ExportTemplateListP.clickCombinedFileCheckBox(driver);
		ExportTemplateListP.clickBrandedImagesCheckBox(driver);
		ExportTemplateListP.clickCancel(driver);
		ac.Wait(wt);
		// Add an Export Template and submit
		ExportTemplateListP.clickAddExportTemplateBtn(driver);
		ExportTemplateListP.inputExportName(driver, searchName);
		ExportTemplateListP.inputExportPrettyName(driver, searchName.toUpperCase());
		ExportTemplateListP.inputFileName(driver, "phone.txt");
		ExportTemplateListP.inputUser(driver, "TEST@autodata.net");
		ExportTemplateListP.inputPassword(driver, "5k2cGG1");
		ExportTemplateListP.inputHost(driver, "LOCALHOST");
		ExportTemplateListP.inputTemplate(driver, templateS);
		ac.Wait(wt);
		ExportTemplateListP.clickCombinedFileCheckBox(driver);
		ExportTemplateListP.clickBrandedImagesCheckBox(driver);
		ac.Wait(wt);
		ExportTemplateListP.clickBrandedImagesCheckBox(driver);
		ExportTemplateListP.clickBrandedImagesCheckBox(driver);
		ExportTemplateListP.clickSubmit(driver);
		ac.acceptAlert(driver, tc, "OK");
		// Edit Export Template
		ExportTemplateListP.inputSearch(driver, searchName);
		ac.Wait(wt);
		ExportTemplateListP.clickEditBtn(driver, 1);
		ExportTemplateListP.inputExportName(driver, editedName);
		ExportTemplateListP.inputExportPrettyName(driver, editedName.toUpperCase());
		ExportTemplateListP.inputFileName(driver, "Edited_phone.txt");
		ExportTemplateListP.inputUser(driver, "Edited_TEST@autodata.net");
		ExportTemplateListP.inputPassword(driver, "Edited_5k2cGG1");
		ExportTemplateListP.inputHost(driver, "Edited_LOCALHOST");
		ExportTemplateListP.inputTemplate(driver, "Edited_" + templateS);
		ac.Wait(wt);
		ExportTemplateListP.clickCombinedFileCheckBox(driver);
		ExportTemplateListP.clickBrandedImagesCheckBox(driver);
		ac.Wait(wt);
		ExportTemplateListP.clickCombinedFileCheckBox(driver);
		ExportTemplateListP.clickBrandedImagesCheckBox(driver);
		ExportTemplateListP.clickSubmit(driver);
		ac.acceptAlert(driver, tc, "OK");
		ac.Wait(wt);
		// Delete an Export Template
		ExportTemplateListP.inputSearch(driver, editedName);
		ExportTemplateListP.clickDeleteBtn(driver, 1);
		ac.acceptAlert(driver, tc, "OK");
		ac.Wait(wt);
		ExportTemplateListP.inputSearch(driver, editedName);
		String newName = ExportTemplateListP.getNameString(driver, 1);
		tc = "Delate an Export Tempate";
		if (newName.equalsIgnoreCase(editedName)) {
			// Bug here. Failed to delete an Export Template...logged AUTOPXOPS-1171
			System.out.println("\nFailed to delete an Export Template here......logged AUTOPXOPS-1171");
			ac.rwExcel(tc, false, "Delete an Export Template",
					"Failed to delete an Export Template here......logged AUTOPXOPS-1171");
		}
		//// *************************ManageExportTemplates******************************************************
		//// *************************ManageExportTemplates******************************************************

		//// *************************ManageGlobalConfig******************************************************
		//// *************************ManageGlobalConfig******************************************************
		for (int j = 1; j <= 1; j++) { // 100 worked fine.
			UserListP.clickManageGlobalConfig(driver);
			ac.rwExcel("", "*********ManageGlobalConfig**********", "");
			String searchKey = "test_IMPORT_SITE";
			String editedKey = "edited_test_IMPORT_SITE";
			String valueS = "CC35943D,CC35953D,TC35903D,CK35943D,TC35943D,TK35903D,TK35953D,CC35903D,CK35953D,TC35953D,TK35943D,CK35903D";
			// From Production 20180917:
			// DRW_CODES CC35943D,CC35953D,TC35903D,CK35943D,TC35943D,TK35903D,TK35953D,CC35903D,CK35953D,TC35953D,TK35943D,CK35903D
			// IMPORT_SITE FNOC-PR3V-XET2
			GlobalConfig GlobalConfigP = new GlobalConfig(driver);
			GlobalConfigP.clickAddGlobalConfigBtn(driver);
			ac.Wait(wt);
			GlobalConfigP.inputKey(driver, searchKey);
			GlobalConfigP.inputValue(driver, valueS);
			GlobalConfigP.clickCancel(driver);
			ac.Wait(wt);
			//// Add an Global Config and cancel
			tc = "Add an Global Config";
			GlobalConfigP.clickAddGlobalConfigBtn(driver);
			GlobalConfigP.inputKey(driver, searchKey);
			GlobalConfigP.inputValue(driver, valueS);
			try {
				GlobalConfigP.clickSubmit(driver);
				ac.acceptAlert(driver, tc, "OK");
				GlobalConfigP.inputSearch(driver, searchKey);
			} catch (Exception e) {
				ac.Wait(wt * 2);
				try {
					GlobalConfigP.clickSubmit(driver);
					ac.acceptAlert(driver, tc, "OK");
					GlobalConfigP.inputSearch(driver, searchKey);
					System.out.println("\nFirst click it did not work? clickSubmit twice!!!!!!");
				} catch (Exception ex2) {
					GlobalConfigP.clickCancel(driver);
					ac.Wait(wt);
					ac.rwExcel(tc, false, "Add an Global Config", "The issue occurs again, see AUTOPXOPS-1172....");
					System.out.println(
							"\nClick Cancel to cancel the sumbit and wait...since the issue occurs again, see AUTOPXOPS-1172....");
				}
			}
			// Edit Global Config
			tc = "Edit Global Config";
			GlobalConfigP.clickEditBtn(driver, 1);
			if (GlobalConfigP.checkKeyFieldLocked(driver, tc)) {
				// Key field now is enabled to input when it should be locked on Edit
				ac.rwExcel(tc, false, "Edit Global Config", "The issue occurs again, see AUTOPXOPS-1173....");
				System.out.println(
						"\nFailed, the Key field now is enabled to input when it should be locked on Edit here......");
			}
			;
			GlobalConfigP.inputValue(driver, "edited" + valueS);
			ac.Wait(wt);
			GlobalConfigP.clickSubmit(driver);
			// System.out.println("\nThe issue occurs again, see AUTOPXOPS-1172....");
			ac.acceptAlert(driver, tc, "OK");
			ac.Wait(wt);
			// Delete an Global Config
			tc = "Delete an Global Config";
			GlobalConfigP.inputSearch(driver, "edited");
			GlobalConfigP.clickDeleteBtn(driver, 1);
			ac.acceptAlert(driver, tc, "OK");
			GlobalConfigP.inputSearch(driver, "edited");
			String newKey = GlobalConfigP.getKeyString(driver, 1);
			if (newKey.equalsIgnoreCase(editedKey)) {
				// Failed to delete an Global Config...
				ac.rwExcel(tc, false, "Delete an Global Config", "The issue occurs again, see AUTOPXOPS-1172,1173....");
				System.out.println("\nFailed to delete an Global Config here......");
			}
			System.out.println("Loop counts = " + j);
		}
		// Stop here!!!

		//// *************************ManageGlobalConfig******************************************************
		//// *************************ManageGlobalConfig******************************************************

		driver.close();
		// switchToWindow(driver, parentHandle);
		// driver.close();

	}

	public static void main(String[] args) throws Exception {
		// Load environment parameters
		Properties prop = new Properties();
		// testprop.load(new FileInputStream("data/autopxConf.properties"));
		try {
			prop.load(AdminPortalController.class.getClassLoader()
					.getResourceAsStream("AdminPortalData/adminPortalConf.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String env = prop.getProperty("AUTOpx.environment");
		String versionNum = prop.getProperty("AUTOpx.versionNum");
		String tBrowser = prop.getProperty("AUTOpx.browser");
		String envDevice = prop.getProperty("AUTOpx.envDevice");
		String accountEmail = prop.getProperty(env + ".AllProdEmail");
		// String accountPS = prop.getProperty(env + ".AllProdPassword");
		String baseURL = prop.getProperty(env + ".AdminPortalBaseURL");
		String DealerPortalBaseURL = prop.getProperty(env + ".DealerPortalBaseURL");
		String chkEmail = prop.getProperty("AUTOpx.checkEmail");
		int wt = Integer.parseInt(prop.getProperty("AUTOpx.waitTime"));
		// String dealershipName = prop.getProperty(env + ".Dealershipname");

		// String[] VINpxNewVIN01
		// =fetchOneDemArrayFromPropFile(env+".VINpxNewVINs",prop);
		for (int i = 0; i < 1; i++) {
			System.out.println("Testing is started in " + env + "\n");
			// Initial
			Comlibs bc = new Comlibs();
			final WebDriver driver;
			driver = bc.drivers(tBrowser);// Firefox, Chrome
			driver.manage().deleteAllCookies();
			System.out.println("Test Browser = " + tBrowser + "\n");

			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			if (!tBrowser.equalsIgnoreCase("Chromexxxxxxxxx")) { // only Chrome doesn't work since Chrome updated on Jul, 2017, works on Dec 13,2017 webdriver ver3.8.5
				bc.SelecBroswerResolution(driver, envDevice);
			}
			bc.rwExcel("", "****** Testing started ******" + (i + 1), "");
			bc.rwExcel("", "Test Browser", tBrowser);
			bc.rwExcel("", "Test Environment", env);

			loadURL(driver, baseURL, env);
			// Alert alert =driver.switchTo().alert();
			// alert.sendKeys("username"+Key.TAB+"password");
			// alert.accept();
			// tempDebug(driver);// ***************************************Debug*****************************************
			// AddAllVINs(driver, tBrowser, env); //works, need to execlude #VINpx only in properties file, and include ##Add All VINs to VINpx - Add all New VIN

			// //// 0.RetriveValuesFrDealerSettingsPageFrNewDealerListPage: took back on 2018-11-29 - OK for Prod on 2018-12-17 from ManageDealerships.
			// bc.rwExcel("", "-----RetriveValuesFrDealerSettingsPage Testing started-----" + (i + 1), "");
			// RetriveValuesFrDealerSettingsPageFrNewDealerListPage(driver, tBrowser, versionNum, env, chkEmail);
			//
			// //// 1.RetriveValuesFrDealerSettingsPage: get Metadata values from ManageAccount page
			// bc.rwExcel("", "-----RetriveValuesFrDealerSettingsPage Testing started-----" + (i + 1), "");
			// RetriveValuesFrDealerSettingsPage(driver, tBrowser, versionNum, env, chkEmail);

			////// 1.ManageDealerShipsAddNewAccount:
			bc.rwExcel("", "-----ManageAccounts - Add An New Account Testing started-----" + (i + 1), "");
			ManageDealerShipsAddNewAccount ManageDealerShips = new ManageDealerShipsAddNewAccount();
			ManageDealerShips.AddNewAccount(driver, tBrowser, versionNum, env, chkEmail);

			//// 2.ManageDealerShips:
			loadURL(driver, baseURL, env);
			bc.rwExcel("", "-----ManageDealerShips - Add An Dealership Testing started-----" + (i + 1), "");
			ManageDealerShips(driver, tBrowser, versionNum, env, chkEmail);

			// bc.rwExcel("", "****** Testing is complete ****** " + (i + 1), "");
			// driver.close();
			System.out.println("Test is complete!!!");
		}
		return;
	}
}
