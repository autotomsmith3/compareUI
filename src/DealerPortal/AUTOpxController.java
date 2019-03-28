package DealerPortal;

//Moved from perforce:1666..
/**
* @author Zhoul - re-imported on 2018-11-08 after migration to Win 10
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

public class AUTOpxController extends Comlibs {
	private final WebDriver driver;
	final int wt_Secs = 0;
	static String[] vinpxConent = new String[200];
	static String[] stockpxConent = new String[200];
	static String[] lotpxConent = new String[200];
	final static int SINGLE_VIN_RENDER_MAX_WT = 10;
	final static int ALL_VINS_RENDER_MAX_WT = 20;

	public AUTOpxController(WebDriver driver, String myId) throws IOException {
		this.driver = driver;
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

	/*
	 * ------------------------------ Home Page Object Repositories: ------------------------------
	 */
	By dealershipNameField = By.id("dealerName");
	By webSite = By.id("dealerSite");
	By tagLineMarkingMsg = By.id("dealerTag");
	By dealershipEmail = By.id("dealerEmail");
	By dealershipPhoneNumber = By.id("dealerPhone");
	By accountEmail = By.id("accEmail");
	By firstName = By.id("userFirstName");
	By lastName = By.id("userLastName");
	By receiveDailyInventoryEmailCheckBox = By.id("emailOptIn");
	By address1 = By.id("dealerAddr1");
	By address2 = By.id("dealerAddr2");
	By city = By.id("dealerCity");
	By country = By.id("dealerCountry");
	By stateProvince = By.id("dealerState");
	By zipPostalCode = By.id("dealerZip");
	By passwordLocator = By.id("userPassword");
	By confirmPasswordLocator = By.id("userConfirm");
	By saveLocator = By.id("saveBtn");
	By dealershipLogo = By.id("//img[@alt='Dealership Image']");
	By upLoadNewPicture = By.id("uploadLogo");
	By removeLogo = By.id("removeLogo");
	By VINpxCheckBox = By.id("vinpx");
	By LOTpxCheckBox = By.id("lotpx");
	By STOCKpxCheckBox = By.id("stockpx");
	By backGround7 = By.id("//div[@id='bg-7']/div/img");

	static String strHelpEmail = "contact@unityworksmedia.com"; // Prod:contact@unityworksmedia.com QA: tdautoaa@gmail.com
	static String strHelpTel = "1-800-293-2056";
	static int allVinNums = 0;
	static int allImageNums = 0;
	static String urlLink;

	public static AUTOpxLogin loadURL(WebDriver driver, String bURL) throws IOException {
		driver.get(bURL + "/DealerPortal/authenticate");
		return new AUTOpxLogin(driver);
	}

	public static void inventoryGallery(WebDriver driver, String brw, String envment, String ver)
			throws IOException, InterruptedException {
		// This is old one (mei you yong dao)
		// Load environment parameters
		Properties prop = new Properties();
		// testprop.load(new FileInputStream("data/autopxConf.properties"));
		try {
			prop.load(AUTOpxController.class.getClassLoader().getResourceAsStream("data/autopxConf.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String env = prop.getProperty("AUTOpx.environment");
		String envDevice = prop.getProperty("AUTOpx.envDevice");
		String render = prop.getProperty("AUTOpx.render");
		String checkEmail = prop.getProperty("AUTOpx.checkEmail");
		String accountEmail = prop.getProperty(env + ".AllProdEmail");
		String accountPS = prop.getProperty(env + ".AllProdPassword");
		String baseURL = prop.getProperty(env + ".DealerPortalBaseURL");
		String dealershipName = prop.getProperty(env + ".Dealershipname");
		String vin01 = prop.getProperty(env + ".Vin01");
		String vin02 = prop.getProperty(env + ".Vin02");
		String vehGUID01 = prop.getProperty(env + ".Vin01GUID");
		String vehGUID02 = prop.getProperty(env + ".Vin02GUID");
		String MaxVins = prop.getProperty(env + ".MaxVinsForPreview");
		String VINpxSupportEmail = prop.getProperty(env + ".VINpxSupportEmail");
		String VINpxSupportEmailPS = prop.getProperty(env + ".VINpxSupportEmailPS");
		int MaxVinsForPreview = Integer.parseInt(MaxVins);
		int numbers = 0;
		// Initial
		final int wt_Secs = 6;
		String TCnum;
		String ptitle;
		Comlibs ac = new Comlibs();
		ac.rwExcel("", "*********Mixed Inventory Gallery**********", "");
		AUTOpxLogin loginP = new AUTOpxLogin(driver);
		TCnum = "TC139659_7_ig";
		loginP.verifyHeaderFooter(env, ver, TCnum);

		loginP.login(driver, accountEmail, accountPS);
		AcceptLicenseAgreementtoContinue acceptLicenseP = new AcceptLicenseAgreementtoContinue(driver);
		acceptLicenseP.clickAcceptBtn(driver);

		TCnum = "TC5123123_ig1";
		ImageGallery igP = new ImageGallery(driver);
		// Help section
		TCnum = "TC139675_7_igP"; // Help - Contact Support. email and tel no.
		// igP.verifyHelpContactSupport(driver, strHelpEmail, strHelpTel, TCnum);
		igP.verifyHelpContactSupport(driver, strHelpEmail, strHelpTel, VINpxSupportEmail, VINpxSupportEmailPS,
				checkEmail, TCnum);
		TCnum = "TC139675_14_igP";
		igP.verifyHelpSystemHelp(driver, TCnum);// Help - System Help.
		TCnum = "TC139675_17_igP"; // Help - Report Issue. email and tel no.
		// igP.verifyHelpReportIssue(driver, strHelpEmail, strHelpTel, TCnum);
		igP.verifyHelpReportIssue(driver, strHelpEmail, strHelpTel, VINpxSupportEmail, VINpxSupportEmailPS, checkEmail,
				TCnum);

		// Type
		igP.clickTypeBtn(driver, TCnum);
		igP.clickUsedBtn(driver, TCnum);
		igP.clickNewBtn(driver, TCnum);
		igP.clickTypeApplyBtn(driver, TCnum);
		igP.verifyLoadPreviewTileImage(driver, SINGLE_VIN_RENDER_MAX_WT, vin01, vehGUID01, 1, TCnum);
		// Only New
		igP.clickClearAllFiltersBtn(driver, TCnum);
		igP.clickTypeBtn(driver, TCnum);
		igP.clickUsedBtn(driver, TCnum);
		igP.clickTypeClearBtn(driver, TCnum);
		igP.clickTypeXBtn(driver, TCnum);
		igP.clickTypeBtn(driver, TCnum);
		igP.clickUsedBtn(driver, TCnum);
		igP.clickTypeApplyBtn(driver, TCnum);
		igP.clickClearAllFiltersBtn(driver, TCnum);
		// Year

		TCnum = "TC139691_9";
		igP.clickYearBtn(driver, TCnum);
		igP.clickYearSelected(driver, "2015", TCnum);
		igP.clickYearApplyBtn(driver, TCnum);
		igP.verifyLoadPreviewTileImage(driver, SINGLE_VIN_RENDER_MAX_WT, vin01, vehGUID01, 1, TCnum);
		igP.clickClearAllFiltersBtn(driver, TCnum);

		igP.clickYearBtn(driver, TCnum);
		igP.clickYearSelected(driver, "2015", TCnum);
		igP.clickYearSelected(driver, "2016", TCnum);
		igP.clickYearClearBtn(driver, TCnum);
		igP.clickYearSelected(driver, "2015", TCnum);
		igP.clickYearXBtn(driver, TCnum);
		igP.clickYearBtn(driver, TCnum);
		igP.clickYearSelected(driver, "2015", TCnum);
		igP.clickYearSelected(driver, "2016", TCnum);
		igP.clickYearApplyBtn(driver, TCnum);
		igP.clickClearAllFiltersBtn(driver, TCnum);
		// Make
		TCnum = "TC139691_11";
		igP.clickMakeBtn(driver, TCnum);
		igP.clickMakeBrandBtn(driver, "Buick", TCnum);
		igP.clickMakeApplyBtn(driver, TCnum);
		igP.verifyLoadPreviewTileImage(driver, SINGLE_VIN_RENDER_MAX_WT, vin01, vehGUID01, 1, TCnum);
		igP.clickClearAllFiltersBtn(driver, TCnum);

		igP.clickMakeBtn(driver, TCnum);
		igP.clickMakeBrandBtn(driver, "Buick", TCnum);
		igP.clickMakeBrandBtn(driver, "Cadillac", TCnum);
		igP.clickMakeBrandBtn(driver, "GMC", TCnum);
		igP.clickMakeBrandBtn(driver, "Chevrolet", TCnum);
		igP.clickMakeClearBtn(driver, TCnum);
		igP.clickMakeBrandBtn(driver, "Buick", TCnum);
		igP.clickMakeXBtn(driver, TCnum);
		igP.clickMakeBtn(driver, TCnum);
		igP.clickMakeBrandBtn(driver, "Buick", TCnum);
		igP.clickMakeBrandBtn(driver, "Chevrolet", TCnum);
		igP.clickMakeApplyBtn(driver, TCnum);
		igP.clickClearAllFiltersBtn(driver, TCnum);

		// Model
		TCnum = "TC139691_14";
		igP.clickModelBtn(driver, TCnum);
		igP.clickModelBuickTab(driver, TCnum);
		igP.clickModelBuickAllCbx(driver, TCnum);

		igP.clickModelClearBtn(driver, TCnum);// Bug: cannot clear the All check box
		igP.clickModelXBtn(driver, TCnum);
		igP.clickModelBtn(driver, TCnum);
		igP.clickModelBuickTab(driver, TCnum);
		igP.clickModelBuickEnclaveCbx(driver, TCnum);
		igP.clickModelClearBtn(driver, TCnum);
		igP.clickModelBuickEncoreCbx(driver, TCnum);
		igP.clickModelClearBtn(driver, TCnum);
		igP.clickModelBuickAllCbx(driver, TCnum);
		igP.clickModelApplyBtn(driver, TCnum);
		igP.clickClearAllFiltersBtn(driver, TCnum);

		igP.clickModelBtn(driver, TCnum);
		igP.clickModelBuickTab(driver, TCnum);
		igP.clickModelBuickEnclaveCbx(driver, TCnum);
		igP.clickModelBuickEncoreCbx(driver, TCnum);
		igP.clickModelApplyBtn(driver, TCnum);
		igP.verifyLoadPreviewTileImage(driver, SINGLE_VIN_RENDER_MAX_WT, vin01, vehGUID01, 1, TCnum);
		igP.clickClearAllFiltersBtn(driver, TCnum);

		igP.clickSelectBtn(driver, vin01, vehGUID01, TCnum);
		igP.clickSelectBtn(driver, vin01, vehGUID01, TCnum);
		igP.clickSelectBtn(driver, vin02, vehGUID02, TCnum);

		TCnum = "TC139691";
		igP.enterTextInSearch("688");// 5GAKRBKD5FJ372688
		igP.verifyLoadPreviewTileImage(driver, SINGLE_VIN_RENDER_MAX_WT, vin01, vehGUID01, 1, TCnum);
		igP.enterTextInSearch(" ");

		TCnum = "TC5123131";
		igP.verifyLoadPreviewTileImage(driver, SINGLE_VIN_RENDER_MAX_WT, vin01, vehGUID01, 1, TCnum);
		igP.gotoMyDealerShip(driver);
		DealerProfile dpP = new DealerProfile(driver);
		dpP.clickViewInventoryBtn(driver, TCnum);
		dpP.clickLogout(driver);
		loginP.login(driver, accountEmail, accountPS);
		acceptLicenseP.clickAcceptBtn(driver);
		// login();
		igP.gotoMyDealerShip(driver);

		dpP.clickViewInventoryBtn(driver, TCnum);

		igP.clickViewDetailsBtn(driver, vin01, vehGUID01, TCnum);
		VehicleGallery vgP = new VehicleGallery(driver);
		vgP.verifyLoadPannelImage(driver, "TC5123132");
		vgP.clickBackToInventoryBtn(driver);
		TCnum = "TC5123133";
		igP.verifyRerenderBtnStatus(driver, false, TCnum);
		// igP.verifyGenerateURLsBtnStatus(driver, false, TCnum);
		igP.clickSelectBtn(driver, vin01, vehGUID01, TCnum);
		igP.verifyRerenderBtnStatus(driver, true, TCnum);
		TCnum = "TC139481_8";
		igP.clickRerenderBtn(driver, render, TCnum);
		igP.verifyGoodMsgShowing(driver, TCnum);
		// igP.verifyErrorMsgShowing(driver, TCnum);
		igP.verifyLoadPreviewTileImage(driver, SINGLE_VIN_RENDER_MAX_WT, vin01, vehGUID01, 1, TCnum);
		igP.verifyRerenderBtnStatus(driver, true, TCnum);
		TCnum = "TC5123136";
		igP.clickDeSelectBtn(driver, vin01, vehGUID01, TCnum);
		igP.verifyRerenderBtnStatus(driver, false, TCnum);

		TCnum = "TC5123137";
		igP.clickViewDetailsBtn(driver, vin01, vehGUID01, TCnum);
		vgP.verifyLoadPannelImage(driver, TCnum);
		vgP.clickBackToInventoryBtn(driver);
		TCnum = "TC139709_08";
		igP.clickSelectNoneBtn(driver, TCnum);
		igP.verifyRerenderBtnStatus(driver, false, TCnum);
		igP.clickSelectAllBtn(driver, TCnum);
		// get the number of all vins here and adding to the below
		numbers = igP.getReRenderNum(driver, TCnum);
		ac.rwExcel("", "------ All VINs re-rendering ------", "");
		igP.clickRerenderBtn(driver, render, TCnum);
		igP.verifyGoodMsgShowing(driver, TCnum);
		// igP.verifyErrorMsgShowing(driver, TCnum);
		igP.verifyRerenderBtnStatus(driver, true, TCnum);
		igP.verifyLoadPreviewTileImage(driver, ALL_VINS_RENDER_MAX_WT, vin01, vehGUID01, numbers, TCnum);

		TCnum = "TC5123139";
		igP.clickSelectAllBtn(driver, TCnum);
		allVinNums = igP.getReRenderNum(driver, TCnum);
		igP.clickSelectNoneBtn(driver, TCnum);
		if (allVinNums > 20) {
			igP.scrollUp(driver, 650, TCnum);
			if (igP.verifyLoadMoreVehicleBtnStatus(driver, true, TCnum)) {
				igP.clickLoadMoreVehicleBtn(driver, TCnum);
			}
			igP.scrollUp(driver, -650, TCnum);
		}
		if (allVinNums >= MaxVinsForPreview) {
			allVinNums = MaxVinsForPreview;
		}
		igP.enterTextInSearch(vin01);
		allImageNums = igP.getTileImageNum(driver, vehGUID01, "");
		igP.clickViewDetailsBtn(driver, vin01, vehGUID01, TCnum);
		vgP.verifyLoadPannelImage(driver, TCnum);
		;

		// Next image
		TCnum = "TC139484";
		for (int x = 0; x < allImageNums; x++) { // 14
			TCnum = "TC139930_ig";
			System.out.println("Left next Image =" + x + "\n");
			ac.rwExcel(TCnum, "Left next image", " next =" + x);
			vgP.clickLeftNextImageBtn(driver, TCnum);
			vgP.verifyLoadPannelImage(driver, TCnum);
		}

		for (int x = 0; x < allImageNums; x++) { // 14
			TCnum = "TC139929_ig";
			System.out.println("Right next Image =" + x + "\n");
			ac.rwExcel(TCnum, "Right next image", " next =" + x);
			vgP.clickRightNextImageBtn(driver, TCnum);
			vgP.verifyLoadPannelImage(driver, TCnum);
		}

		// Next vehicle
		for (int x = 0; x < allVinNums; x++) { // 31
			TCnum = "TC139934_ig";
			String currentVIN = vgP.retriveVIN(driver);
			System.out.println("left arrow previous vehicle =" + x + "   - Current VIN = " + currentVIN + "\n");
			ac.rwExcel(TCnum, "left arrow previous vehicle", " previous =" + x + "   - Current VIN = " + currentVIN);
			vgP.hoverOnLeftArrowPreviousVehicleBtn(driver, TCnum);
			vgP.verifyLoadPreviousControlImage(driver, TCnum);
			vgP.clickLeftNextVehicleBtn(driver, TCnum);
			vgP.verifyLoadPannelImage(driver, TCnum);
		}
		for (int x = 0; x < allVinNums; x++) { // 31
			TCnum = "TC139933_ig";
			String currentVIN = vgP.retriveVIN(driver);
			System.out.println("Right arrow next vehicle =" + x + "   - Current VIN = " + currentVIN + "\n");
			ac.rwExcel(TCnum, "Right arrow next vehicle", " next =" + x + "   - Current VIN = " + currentVIN);
			vgP.hoverOnRightArrowNextVehicleBtn(driver, TCnum);
			vgP.verifyLoadNextControlImage(driver, TCnum);
			vgP.clickRightNextVehicleBtn(driver, TCnum);
			vgP.verifyLoadPannelImage(driver, TCnum);
		}

		igP.clickBackToInventoryBtn(driver);
		TCnum = "TC5123150";
		igP.verifyRerenderBtnStatus(driver, false, TCnum);
		// igP.verifyGenerateURLsBtnStatus(driver, false, TCnum);
		igP.clickSelectBtn(driver, vin01, vehGUID01, TCnum);
		TCnum = "TC5123152";
		igP.verifyRerenderBtnStatus(driver, true, TCnum);
		// igP.verifyGenerateURLsBtnStatus(driver, true, TCnum);
		igP.clickViewDetailsBtn(driver, vin01, vehGUID01, TCnum);
		igP.clickBackToInventoryBtn(driver);

		igP.clickLogout(driver);
	}

	public static void verifyRerender(WebDriver driver, String brw) throws IOException, InterruptedException {
		// Load environment parameters
		Properties prop = new Properties();
		// testprop.load(new FileInputStream("data/autopxConf.properties"));
		try {
			prop.load(AUTOpxController.class.getClassLoader().getResourceAsStream("data/autopxConf.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String env = prop.getProperty("AUTOpx.environment");
		String versionNum = prop.getProperty("AUTOpx.versionNum");
		String envDevice = prop.getProperty("AUTOpx.envDevice");
		String render = prop.getProperty("AUTOpx.render");
		String accountEmail = prop.getProperty(env + ".AllProdEmail");
		String accountPS = prop.getProperty(env + ".AllProdPassword");
		String baseURL = prop.getProperty(env + ".AllPordDealerPortalBaseURL");
		String dealershipName = prop.getProperty(env + ".Dealershipname");
		String vin01 = prop.getProperty(env + ".Vin01");
		String vin02 = prop.getProperty(env + ".Vin02");
		String vehGUID01 = prop.getProperty(env + ".Vin01GUID");
		String vehGUID02 = prop.getProperty(env + ".Vin02GUID");
		int wt = Integer.parseInt(prop.getProperty("AUTOpx.waitTime"));
		// Initial
		String TCnum;
		String ptitle;
		int numbers = 0;

		Comlibs ac = new Comlibs();
		ac.rwExcel("", "*********Mixed Verify Rerender**********", "");
		AUTOpxLogin loginP = new AUTOpxLogin(driver);
		TCnum = "TC139659_7_render";
		loginP.verifyHeaderFooter(env, versionNum, TCnum);

		loginP.login(driver, accountEmail, accountPS);
		AcceptLicenseAgreementtoContinue acceptLicenseP = new AcceptLicenseAgreementtoContinue(driver);
		acceptLicenseP.clickAcceptBtn(driver);

		TCnum = "TC5123123_ig2";

		ImageGallery igP = new ImageGallery(driver);
		igP.verifyDealershipname(dealershipName, TCnum);
		igP.enterTextInSearch(vin01);
		igP.clickSelectBtn(driver, vin01, vehGUID01, TCnum);
		igP.verifyRerenderBtnStatus(driver, true, TCnum);
		// igP.verifyGenerateURLsBtnStatus(driver, true, TCnum);
		// Stop here!
		// need to verify next vehicle hover image
		TCnum = "TC139706_8";
		ac.rwExcel("", "------ Single VIN re-rendering ------", "");
		igP.clickRerenderBtn(driver, render, TCnum);
		igP.verifyGoodMsgShowing(driver, TCnum);
		// igP.verifyErrorMsgShowing(driver, TCnum);
		igP.enterTextInSearch(vin01);
		igP.verifyLoadPreviewTileImage(driver, SINGLE_VIN_RENDER_MAX_WT, vin01, vehGUID01, 1, TCnum);
		igP.verifyRerenderBtnStatus(driver, true, TCnum);
		// igP.verifyGenerateURLsBtnStatus(driver, true, TCnum);
		TCnum = "TC5123136";
		igP.clickDeSelectBtn(driver, vin01, vehGUID01, TCnum);
		igP.verifyRerenderBtnStatus(driver, false, TCnum);
		// igP.verifyGenerateURLsBtnStatus(driver, false, TCnum);
		TCnum = "TC5123137";
		igP.clickViewDetailsBtn(driver, vin01, vehGUID01, TCnum);
		VehicleGallery vgP = new VehicleGallery(driver);

		vgP.verifyLoadPannelImage(driver, TCnum);
		vgP.clickBackToInventoryBtn(driver);
		TCnum = "TC139709_08";
		igP.clickSelectNoneBtn(driver, TCnum);
		igP.verifyRerenderBtnStatus(driver, false, TCnum);
		// igP.verifyGenerateURLsBtnStatus(driver, false, TCnum);
		ac.Wait(wt);
		igP.clickSelectAllBtn(driver, TCnum);
		//// igP.clickSelectNoneBtn(driver, TCnum);
		// igP.clickSelectAllBtn(driver, TCnum);

		// get the number of all vins here and adding to the below
		numbers = igP.getReRenderNum(driver, TCnum);
		ac.rwExcel("", "------ All VINs re-rendering ------Total VINs selected=" + numbers, "");
		igP.clickRerenderBtn(driver, render, TCnum);
		igP.verifyGoodMsgShowing(driver, TCnum);
		// igP.verifyErrorMsgShowing(driver, TCnum);
		igP.verifyRerenderBtnStatus(driver, true, TCnum);
		// igP.verifyGenerateURLsBtnStatus(driver, true, TCnum);
		igP.enterTextInSearch(vin01);
		igP.verifyLoadPreviewTileImage(driver, ALL_VINS_RENDER_MAX_WT, vin01, vehGUID01, numbers, TCnum);

		igP.clickLogout(driver);
	}

	public static void vehicleGallery(WebDriver driver, String brw, String envment)
			throws IOException, InterruptedException {
		// Load environment parameters
		Properties prop = new Properties();
		// testprop.load(new FileInputStream("data/autopxConf.properties"));
		try {
			prop.load(AUTOpxController.class.getClassLoader().getResourceAsStream("data/autopxConf.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String env = prop.getProperty("AUTOpx.environment");
		String versionNum = prop.getProperty("AUTOpx.versionNum");
		String envDevice = prop.getProperty("AUTOpx.envDevice");
		String envBrowser = prop.getProperty("AUTOpx.browser");
		String render = prop.getProperty("AUTOpx.render");
		String accountEmail = prop.getProperty(env + ".AllProdEmail");
		String accountPS = prop.getProperty(env + ".AllProdPassword");
		String baseURL = prop.getProperty(env + ".DealerPortalBaseURL");
		String dealershipName = prop.getProperty(env + ".Dealershipname");
		String vin01 = prop.getProperty(env + ".Vin01");
		String vin02 = prop.getProperty(env + ".Vin02");
		String vehGUID01 = prop.getProperty(env + ".Vin01GUID");
		String vehGUID02 = prop.getProperty(env + ".Vin02GUID");
		String MaxVins = prop.getProperty(env + ".MaxVinsForPreview");
		int MaxVinsForPreview = Integer.parseInt(MaxVins);
		int wt = Integer.parseInt(prop.getProperty("AUTOpx.waitTime"));
		// Initial
		String TCnum;
		String ptitle;
		Comlibs ac = new Comlibs();
		ac.rwExcel("", "*********Mixed Vehicle Gallery**********", "");
		AUTOpxLogin loginP = new AUTOpxLogin(driver);
		TCnum = "TC139659_7_vg";
		loginP.verifyHeaderFooter(env, versionNum, TCnum);

		loginP.login(driver, accountEmail, accountPS);
		AcceptLicenseAgreementtoContinue acceptLicenseP = new AcceptLicenseAgreementtoContinue(driver);
		acceptLicenseP.clickAcceptBtn(driver);

		TCnum = "TC139502_12";

		ImageGallery igP = new ImageGallery(driver);
		ac.Wait(wt);
		igP.enterTextInSearch(vin01);
		ac.Wait(wt * 3);
		igP.clickViewDetailsBtn(driver, vin01, vehGUID01, TCnum);
		VehicleGallery vgP = new VehicleGallery(driver);

		boolean newLink = true;
		if (newLink) {
			String parentHandle = driver.getWindowHandle(); // get the current window handle
			vgP.clickURLLink(driver, brw, TCnum);
			for (String winHandle : driver.getWindowHandles()) {
				driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
			}
			vgP.verifyLoadURLImage(driver, TCnum);
			driver.close();
			driver.switchTo().window(parentHandle); // switch back to the original window
		} else {
			driver.get(urlLink);
			urlLink = vgP.getURLLink(driver, TCnum);
			vgP.verifyLoadURLImage(driver, TCnum);
			ac.GoBack(driver);
		}

		vgP.clickBackToInventoryBtn(driver);
		igP.clickDealerShipInfoBtn(driver);
		DealerProfile dpP = new DealerProfile(driver);

		dpP.clickLogout(driver);

		loginP.login(driver, accountEmail, accountPS);
		acceptLicenseP.clickAcceptBtn(driver);
		igP.clickDealerShipInfoBtn(driver);
		dpP.clickInventoryGalleryBtn(driver, TCnum);
		// igP.clickViewDetailsBtn(driver, vin01, vehGUID01, TCnum);
		// vgP.verifyLoadPannelImage(driver, "TC5123xxx");

		TCnum = "TC5123139";
		igP.clickSelectAllBtn(driver, TCnum);
		allVinNums = igP.getReRenderNum(driver, TCnum);
		igP.clickSelectNoneBtn(driver, TCnum);
		if (allVinNums > 20) {
			igP.scrollUp(driver, 650, TCnum);
			if (igP.verifyLoadMoreVehicleBtnStatus(driver, true, TCnum)) {
				igP.clickLoadMoreVehicleBtn(driver, TCnum);
			}
			igP.scrollUp(driver, -650, TCnum);
		}
		if (allVinNums >= MaxVinsForPreview) {
			allVinNums = MaxVinsForPreview;
		}
		ac.Wait(wt);
		igP.enterTextInSearch(vin01.substring(1, 5));// igP.enterTextInSearch(vin01.substring(1, 6));igP.enterTextInSearch(vin01);
		ac.Wait(wt * 3);
		allImageNums = igP.getTileImageNum(driver, vehGUID01, "");
		igP.clickViewDetailsBtn(driver, vin01, vehGUID01, TCnum);
		vgP.verifyLoadPannelImage(driver, TCnum);

		// Next image
		TCnum = "TC5123266";
		for (int x = 0; x < allImageNums; x++) { // 14
			TCnum = "TC51231111_vg";
			System.out.println("Left next Image =" + x + "\n");
			ac.rwExcel(TCnum, "Left next image", " next =" + x);
			vgP.clickLeftNextImageBtn(driver, TCnum);
			vgP.verifyLoadPannelImage(driver, TCnum);
		}

		for (int x = 0; x < allImageNums; x++) { // 14
			TCnum = "TC51231112_vg";
			System.out.println("Right next Image =" + x + "\n");
			ac.rwExcel(TCnum, "Right next image", " next =" + x);
			vgP.clickRightNextImageBtn(driver, TCnum);
			vgP.verifyLoadPannelImage(driver, TCnum);
		}

		// Next vehicle
		for (int x = 0; x < allVinNums; x++) { // 31
			TCnum = "TC5123128_vg";
			String currentVIN = vgP.retriveVIN(driver);
			System.out.println("left arrow previous vehicle =" + x + "   - Current VIN = " + currentVIN + "\n");
			ac.rwExcel(TCnum, "left arrow previous vehicle", " previous =" + x + "   - Current VIN = " + currentVIN);
			vgP.hoverOnLeftArrowPreviousVehicleBtn(driver, TCnum);
			vgP.verifyLoadPreviousControlImage(driver, TCnum);
			vgP.clickLeftNextVehicleBtn(driver, TCnum);
			vgP.verifyLoadPannelImage(driver, TCnum);
		}
		for (int x = 0; x < allVinNums; x++) { // 31
			TCnum = "TC5123129_vg";
			String currentVIN = vgP.retriveVIN(driver);
			System.out.println("Right arrow next vehicle =" + x + "   - Current VIN = " + currentVIN + "\n");
			ac.rwExcel(TCnum, "Right arrow next vehicle", " next =" + x + "   - Current VIN = " + currentVIN);
			vgP.hoverOnRightArrowNextVehicleBtn(driver, TCnum);
			vgP.verifyLoadNextControlImage(driver, TCnum);
			vgP.clickRightNextVehicleBtn(driver, TCnum);
			vgP.verifyLoadPannelImage(driver, TCnum);
		}

		igP.clickBackToInventoryBtn(driver);
		TCnum = "TC5123150";
		igP.verifyRerenderBtnStatus(driver, false, TCnum);
		ac.Wait(wt);
		igP.enterTextInSearch(vin01);
		ac.Wait(wt * 3);
		igP.clickSelectBtn(driver, vin01, vehGUID01, TCnum);
		TCnum = "TC5123152";
		igP.verifyRerenderBtnStatus(driver, true, TCnum);
		igP.enterTextInSearch(vin01);
		ac.Wait(wt * 3);
		igP.clickViewDetailsBtn(driver, vin01, vehGUID01, TCnum);
		igP.clickBackToInventoryBtn(driver);

		igP.clickLogout(driver);

	}

	public static void inventoryGalleryTC(WebDriver driver, String brw, String envment, String ver) throws Exception {
		// Load environment parameters
		Properties prop = new Properties();
		// testprop.load(new FileInputStream("data/autopxConf.properties"));
		try {
			prop.load(AUTOpxController.class.getClassLoader().getResourceAsStream("data/autopxConf.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String env = prop.getProperty("AUTOpx.environment");
		String versionNum = prop.getProperty("AUTOpx.versionNum");
		String envDevice = prop.getProperty("AUTOpx.envDevice");
		String render = prop.getProperty("AUTOpx.render");
		String accountEmail = prop.getProperty(env + ".AllProdEmail");
		String accountPS = prop.getProperty(env + ".AllProdPassword");
		// String baseURL = prop.getProperty(env + ".AllProdDealerPortalBaseURL");
		String dealershipName = prop.getProperty(env + ".Dealershipname");
		String vin01 = prop.getProperty(env + ".Vin01");
		String vin02 = prop.getProperty(env + ".Vin02");
		String vehGUID01 = prop.getProperty(env + ".Vin01GUID");
		String vehGUID02 = prop.getProperty(env + ".Vin02GUID");
		int wt = Integer.parseInt(prop.getProperty("AUTOpx.waitTime"));
		// Initial
		String TCnum;
		String ptitle;
		int allVinNums = 0;

		Comlibs ac = new Comlibs();
		ac.rwExcel("", "*********Inventory Gallery TCs**********", "");
		AUTOpxLogin loginP = new AUTOpxLogin(driver);
		TCnum = "TC139659_7_igTC";
		loginP.verifyHeaderFooter(env, ver, TCnum);

		TCnum = "TC144597";
		loginP.login(driver, accountEmail, accountPS);
		AcceptLicenseAgreementtoContinue acceptLicenseP = new AcceptLicenseAgreementtoContinue(driver);
		TCnum = "TC144866_ig";
		acceptLicenseP.verifyAgreementTitle(driver, 3, "VINpx Agreement", "STOCKpx Agreement", "LOTpx Agreement", true,
				TCnum); // VINpx Agreement, STOCKpx Agreement, LOTpx Agreement
		vinpxConent = acceptLicenseP.getFile("data/vinpxAgreement.txt");
		int vinpxAgreementTotalLines = acceptLicenseP.getLineNum();
		TCnum = "TC144867_5_vinpx";
		acceptLicenseP.verifyPDF(driver, 1, "vinpx", vinpxConent, vinpxAgreementTotalLines, TCnum, envment);

		stockpxConent = acceptLicenseP.getFile("data/stockpxAgreement.txt");
		int stockpxAgreementTotalLines = acceptLicenseP.getLineNum();
		TCnum = "TC144868_5_stockpx";
		acceptLicenseP.verifyPDF(driver, 2, "stockpx", stockpxConent, stockpxAgreementTotalLines, TCnum, envment);

		lotpxConent = acceptLicenseP.getFile("data/lotpxAgreement.txt");
		int lotpxAgreementTotalLines = acceptLicenseP.getLineNum();
		TCnum = "TC144869_5_lotpx";

		acceptLicenseP.verifyPDF(driver, 3, "lotpx", lotpxConent, lotpxAgreementTotalLines, TCnum, envment);

		acceptLicenseP.clickAcceptBtn(driver);
		ImageGallery igP = new ImageGallery(driver);

		/**
		 * // Help section TCnum = "TC139675_7_InventoryG"; // Help - Contact Support. email and tel no. igP.verifyHelpContactSupport(driver, strHelpEmail, strHelpTel, TCnum); TCnum = "TC139675_14_InventoryG"; igP.verifyHelpSystemHelp(driver, TCnum);// Help - System Help. TCnum = "TC139675_17_InventoryG"; // Help - Report Issue. email and tel no. igP.verifyHelpReportIssue(driver, strHelpEmail, strHelpTel, TCnum);
		 **/
		TCnum = "TC139691_5";
		// Type
		igP.clickTypeBtn(driver, TCnum);
		igP.clickUsedBtn(driver, TCnum);
		igP.clickNewBtn(driver, TCnum);
		igP.clickTypeApplyBtn(driver, TCnum);
		igP.clickTypeBtn(driver, TCnum);
		igP.clickUsedBtn(driver, TCnum);
		igP.clickNewBtn(driver, TCnum);
		igP.clickUsedBtn(driver, TCnum);
		igP.clickNewBtn(driver, TCnum);
		igP.clickTypeClearBtn(driver, TCnum);
		igP.clickTypeXBtn(driver, TCnum);

		// igP.clickTypeXBtnOnTypeButton(driver, TCnum);// Firefox shows
		// different with Chrome. There is a ticket for this issue.
		igP.clickClearAllFiltersBtn(driver, TCnum);
		// Year
		TCnum = "TC139691_9";
		igP.clickYearBtn(driver, TCnum);
		igP.clickYearSelected(driver, "2015", TCnum);
		igP.clickYearApplyBtn(driver, TCnum);
		igP.clickYearBtn(driver, TCnum);
		igP.clickYearSelected(driver, "2015", TCnum);
		igP.clickYearSelected(driver, "2015", TCnum);
		igP.clickYearClearBtn(driver, TCnum);
		igP.clickYearXBtn(driver, TCnum);
		igP.clickClearAllFiltersBtn(driver, TCnum);
		// Make
		TCnum = "TC139691_13";
		igP.clickMakeBtn(driver, TCnum);
		igP.clickMakeBrandBtn(driver, "Buick", TCnum);
		igP.clickMakeApplyBtn(driver, TCnum);
		igP.clickMakeBtn(driver, TCnum);
		igP.clickMakeBrandBtn(driver, "Buick", TCnum);
		igP.clickMakeBrandBtn(driver, "Buick", TCnum);
		igP.clickMakeClearBtn(driver, TCnum);
		igP.clickMakeXBtn(driver, TCnum);
		igP.clickClearAllFiltersBtn(driver, TCnum);
		// Model
		TCnum = "TC139691_17";
		igP.clickModelBtn(driver, TCnum);
		igP.clickModelTab(driver, "Cadillac", TCnum);
		igP.clickModelTrimCbx(driver, "Cadillac", "SRX", TCnum);
		igP.clickModelApplyBtn(driver, TCnum);
		igP.clickClearAllFiltersBtn(driver, TCnum);

		igP.clickModelBtn(driver, TCnum);
		igP.clickModelTab(driver, "Buick", TCnum);
		igP.clickModelTrimCbx(driver, "Buick", "Cascada", TCnum);
		igP.clickModelApplyBtn(driver, TCnum);
		igP.clickClearAllFiltersBtn(driver, TCnum);

		igP.clickModelBtn(driver, TCnum);
		igP.clickModelTab(driver, "GMC", TCnum);
		igP.clickModelTrimCbx(driver, "GMC", "Sierra 2500HD", TCnum);
		igP.clickModelApplyBtn(driver, TCnum);
		igP.clickClearAllFiltersBtn(driver, TCnum);

		igP.clickModelBtn(driver, TCnum);
		igP.clickModelTab(driver, "GMC", TCnum);
		igP.clickModelTrimCbx(driver, "GMC", "All", TCnum);
		igP.clickModelApplyBtn(driver, TCnum);
		igP.clickClearAllFiltersBtn(driver, TCnum);

		// Color
		TCnum = "TC139691_19";
		igP.clickColorsBtn(driver, TCnum);
		igP.clickColorExteriorTab(driver, TCnum);
		igP.clickColorExteriorColorCheckBox(driver, "Summit White(GAZ)", TCnum);
		igP.clickColorInteriorTab(driver, TCnum);
		String selectedInterior = "Dark Atmosphere/Medium Atmosphere w/Cloth Seat Trim(HHR)";
		igP.clickColorInteriorColorCheckBox(driver, selectedInterior, TCnum); // on 2018-12-29
		// igP.clickColorInteriorColorCheckBox(driver, "Ebony Black", TCnum); //on 2018-09-30
		// igP.clickColorInteriorColorCheckBox(driver, "Jet Black w/Cloth Seat Trim(AFJ)", TCnum); // afte 2018-09-30, works for Chrome but Firefox 2018-12-17
		igP.clickColorApplyBtn(driver, TCnum);
		igP.clickClearAllFiltersBtn(driver, TCnum);
		TCnum = "TC139691_19_exterior";
		igP.clickColorsBtn(driver, TCnum);
		igP.clickColorExteriorTab(driver, TCnum);
		igP.clickColorExteriorColorCheckBox(driver, "Summit White(GAZ)", TCnum);
		igP.clickColorInteriorTab(driver, TCnum);
		igP.clickColorInteriorColorCheckBox(driver, selectedInterior, TCnum); // on 2018-09-30
		// igP.clickColorInteriorColorCheckBox(driver, "Jet Black w/Cloth Seat Trim(AFJ)", TCnum); // afte 2018-09-30, works for Chrome but Firefox 2018-12-17
		igP.clickColorApplyBtn(driver, TCnum);
		igP.clickColorsBtn(driver, TCnum);
		igP.clickColorExteriorTab(driver, TCnum);
		String selectedExterior = "Black Raven(GBA)";
		igP.clickColorExteriorColorCheckBox(driver, selectedExterior, TCnum);
		igP.clickColorExteriorColorCheckBox(driver, selectedExterior, TCnum);
		igP.clickColorApplyBtn(driver, TCnum);
		TCnum = "TC139691_19_interior";
		igP.clickClearAllFiltersBtn(driver, TCnum);
		igP.clickColorsBtn(driver, TCnum);
		igP.clickColorExteriorTab(driver, TCnum);
		igP.clickColorExteriorColorCheckBox(driver, selectedExterior, TCnum);
		igP.clickColorExteriorColorCheckBox(driver, selectedExterior, TCnum);
		igP.clickColorInteriorTab(driver, TCnum);
		// igP.clickColorInteriorColorCheckBox(driver, "Ebony w/Ebony accents (AFC)", TCnum);
		igP.clickColorClearBtn(driver, TCnum);
		igP.clickColorXBtn(driver, TCnum);
		igP.clickClearAllFiltersBtn(driver, TCnum);

		TCnum = "TC139688_5";
		// Type
		igP.clickTypeBtn(driver, TCnum);
		igP.clickNewBtn(driver, TCnum);
		igP.clickTypeApplyBtn(driver, TCnum);
		// Year
		TCnum = "TC139688_8";
		igP.clickYearBtn(driver, TCnum);
		igP.clickYearSelected(driver, "2016", TCnum);
		igP.clickYearApplyBtn(driver, TCnum);
		// Make
		TCnum = "TC139688_11";
		igP.clickMakeBtn(driver, TCnum);
		igP.clickMakeBrandBtn(driver, "Buick", TCnum);
		igP.clickMakeApplyBtn(driver, TCnum);
		// Model
		TCnum = "TC139688_14";
		igP.clickModelBtn(driver, TCnum);
		igP.clickModelTab(driver, "Buick", TCnum);
		igP.clickModelTrimCbx(driver, "Buick", "Cascada", TCnum);
		igP.clickModelApplyBtn(driver, TCnum);

		// Color
		TCnum = "TC139688_16";
		igP.clickColorsBtn(driver, TCnum);
		igP.clickColorExteriorTab(driver, TCnum);
		igP.clickColorExteriorColorCheckBox(driver, "Graphite Gray Metallic(G7Q)", TCnum);
		igP.clickColorInteriorTab(driver, TCnum);
		// igP.clickColorInteriorColorCheckBox(driver, "Jet Black (4AA)", TCnum);
		igP.clickColorApplyBtn(driver, TCnum);

		// Stop here
		igP.clickClearAllFiltersBtn(driver, TCnum);

		// click on gridViewBtn and listViewBtn - 20181218
		TCnum = "TC139693_01";
		igP.clickGridViewBtn(driver, TCnum);
		igP.clickTilesViewBtn(driver, TCnum);
		igP.clickGridViewBtn(driver, TCnum);
		igP.enterTextInSearch(vin01);
		ac.Wait(wt * 3);
		igP.clickGridRowOnlyOneRecordToCheck(driver, TCnum);
		igP.clickGridRowOnlyOneRecord(driver, TCnum);
		VehicleGallery vgP = new VehicleGallery(driver);
		vgP.clickBackToInventoryBtn(driver);
		TCnum = "TC139693_02";
		igP.clickGridViewBtn(driver, TCnum);
		igP.clickTilesViewBtn(driver, TCnum);
		igP.clickGridViewBtn(driver, TCnum);
		igP.enterTextInSearch("123");
		ac.Wait(wt * 3);
		igP.clickGridRowOneRecordWithNumToCheck(driver, "3", TCnum);
		igP.clickGridRowOneRecordWithNum(driver, "3", TCnum);
		vgP.clickBackToInventoryBtn(driver);

		// Verify Show All button TC141797

		igP.clickSelectNoneBtn(driver, TCnum);
		igP.verifyRerenderBtnStatus(driver, false, TCnum);
		igP.clickSelectAllBtn(driver, TCnum);
		// get the number of all vins here and adding to the below
		allVinNums = igP.getReRenderNum(driver, TCnum);// allVinNums = igP.getReRenderNum(driver, TCnum);
		igP.clickSelectNoneBtn(driver, TCnum);

		TCnum = "TC141797_6";
		if (allVinNums > 20) {
			if (igP.verifyShowAllBtnStatus(driver, true, TCnum)) {
				igP.clickShowAllBtn(driver, TCnum);
				igP.verifyLoadMoreVehicleBtnStatus(driver, true, TCnum);
				igP.verifyShowAllBtnStatus(driver, false, TCnum);
			}
		} else {
			igP.verifyShowAllBtnStatus(driver, false, TCnum);
			igP.verifyLoadMoreVehicleBtnStatus(driver, false, TCnum);
			igP.verifyShowAllBtnStatus(driver, false, TCnum);
		}
		// igP.gotoMyDealerShip(driver);
		igP.clickDealerShipInfoBtn(driver);
		// igP.clickBackToInventoryBtn(driver);
		igP.clickInventoryGalleryBtn(driver);

		// Verify Load More Vehicles button TC141795
		TCnum = "TC141795_6";
		if (allVinNums > 20) {
			if (igP.verifyLoadMoreVehicleBtnStatus(driver, true, TCnum)) {
				igP.verifyShowAllBtnStatus(driver, true, TCnum);
				igP.scrollUp(driver, 650, TCnum);
				igP.clickLoadMoreVehicleBtn(driver, TCnum);
				igP.verifyLoadMoreVehicleBtnStatus(driver, false, TCnum);
				igP.verifyShowAllBtnStatus(driver, false, TCnum);
			}
		} else {
			igP.verifyLoadMoreVehicleBtnStatus(driver, false, TCnum);
			igP.verifyShowAllBtnStatus(driver, false, TCnum);
		}

		// Need to do. Verify Toggle buttons TC139693
		// Need to do. Verify Select and Sort Inventory [TC:139692

		/**
		 * TCnum = "TC139661_5"; igP.enterTextInSearch(" "); ac.Wait(wt); igP.enterTextInSearch(vin01); igP.verifyLoadPreviewTileImage(driver, SINGLE_VIN_RENDER_MAX_WT, vin01, vehGUID01, 1, TCnum); igP.enterTextInSearch(" "); igP.enterTextInSearch(vin01);// fake Stock Number igP.verifyLoadPreviewTileImage(driver, SINGLE_VIN_RENDER_MAX_WT, vin01, vehGUID01, 1, TCnum); igP.enterTextInSearch(" "); igP.enterTextInSearch(vin01); igP.verifyLoadPreviewTileImage(driver, SINGLE_VIN_RENDER_MAX_WT, vin01, vehGUID01, 1, TCnum);
		 * 
		 * TCnum = "TC139663_5"; igP.clickSelectBtn(driver, vin01, vehGUID01, TCnum); igP.verifyRerenderBtnStatus(driver, true, TCnum); // removed on 2018-09-20 igP.verifyGenerateURLsBtnStatus(driver, true, TCnum); ac.rwExcel("", "------ Single VIN re-rendering ------", ""); igP.clickRerenderBtn(driver, render, TCnum); igP.verifyGoodMsgShowing(driver, TCnum); igP.verifyLoadPreviewTileImage(driver, SINGLE_VIN_RENDER_MAX_WT, vin01, vehGUID01, 1, TCnum); TCnum = "TC139663_8"; igP.clickViewDetailsBtn(driver, vin01, vehGUID01, TCnum); VehicleGallery vgP = new VehicleGallery(driver); vgP.verifyLoadPannelImage(driver, TCnum); vgP.clickBackToInventoryBtn(driver);
		 **/ // Need to do: click PREVIEW button, DELETE button, SELECT or DESELECT
				//
				// My Dealership Settings
				// TC139666
		TCnum = "TC139666_7";
		igP.clickDealerShipInfoBtn(driver);
		DealerProfile dpP = new DealerProfile(driver);
		// removed on 2018-09-20 dpP.verifyDealershipIDBrands("GM123456_4 | ", "GMC | Cadillac | Buick | Chevrolet", TCnum);
		// TC139666_8 Verify Dealership Name
		TCnum = "TC139666_8";
		// Retrieve dealership info
		String ContactTitle = dpP.retrieve_ContactTitle(driver, TCnum);
		String ContactName = dpP.retrieve_ContactName(driver, TCnum);
		String ContactDepartment = dpP.retrieve_ContactDepartment(driver, TCnum);
		String ContactPhone = dpP.retrieve_ContactPhone(driver, TCnum);
		String DealershipName = dpP.retrieve_DealershipName(driver, TCnum);
		String DealershipEmail = dpP.retrieve_DealershipEmail(driver, TCnum);
		String DealershipAddress1 = dpP.retrieve_Address1(driver, TCnum);
		String DealershipAddress2 = dpP.retrieve_Address2(driver, TCnum);

		// Update dealership info
		TCnum = "TC139666_update";
		String updatewith = "_edited";
		dpP.enter_ContactTitle(driver, ContactTitle + updatewith, TCnum);
		dpP.enter_ContactName(driver, ContactName + updatewith, TCnum);
		dpP.enter_ContactDepartment(driver, ContactDepartment + updatewith, TCnum);
		dpP.enter_ContactPhone(driver, ContactPhone + updatewith, TCnum);
		dpP.enter_DealershipName(driver, DealershipName + updatewith, TCnum);
		dpP.enter_DealershipEmail(driver, DealershipEmail + updatewith + ".com", TCnum);
		dpP.enter_Address1(driver, DealershipAddress1 + updatewith, TCnum);
		dpP.enter_Address2(driver, DealershipAddress2 + updatewith, TCnum);

		// // issue here with Change Password field
		// String updatewithempty = "";
		// dpP.enter_ChangePassword(driver, updatewithempty, TCnum); // remove this after issue fixed

		// Click on SAVE
		TCnum = "TC139666_SAVE";
		dpP.clickSAVE(driver, TCnum);
		dpP.clickNOBtn(driver, TCnum);
		ac.Wait(wt);
		igP.clickInventoryGalleryBtn(driver);// dpP.clickViewInventoryBtn(driver, TCnum);
		igP.clickDealerShipInfoBtn(driver);
		// Retrieve all field again
		TCnum = "TC139666_getFields";
		String Edited_ContactTitle = dpP.retrieve_ContactTitle(driver, TCnum);
		String Edited_ContactName = dpP.retrieve_ContactName(driver, TCnum);
		String Edited_ContactDepartment = dpP.retrieve_ContactDepartment(driver, TCnum);
		String Edited_ContactPhone = dpP.retrieve_ContactPhone(driver, TCnum);
		String Edited_DealershipName = dpP.retrieve_DealershipName(driver, TCnum);
		String Edited_DealershipEmail = dpP.retrieve_DealershipEmail(driver, TCnum);
		String Edited_DealershipAddress1 = dpP.retrieve_Address1(driver, TCnum);
		String Edited_DealershipAddress2 = dpP.retrieve_Address2(driver, TCnum);

		// Verify all fields
		if (!Edited_ContactTitle.equals((ContactTitle + updatewith))) {
			ac.rwExcel(TCnum, false, "Verify Edited ContactTitle", "Failed! Edited_ContactTitle =" + ContactTitle
					+ updatewith + ".  But site shows =" + Edited_ContactTitle);
		}
		if (!Edited_ContactName.equals((ContactName + updatewith))) {
			ac.rwExcel(TCnum, false, "Verify Edited ContactName", "Failed! Edited_ContactName =" + ContactName
					+ updatewith + ".  But site shows =" + Edited_ContactName);
		}
		if (!Edited_ContactDepartment.equals((ContactDepartment + updatewith))) {
			ac.rwExcel(TCnum, false, "Verify Edited ContactDepartment", "Failed! Edited_ContactDepartment ="
					+ ContactDepartment + updatewith + ".  But site shows =" + Edited_ContactDepartment);
		}
		if (!Edited_ContactPhone.equals((ContactPhone + updatewith))) {
			ac.rwExcel(TCnum, false, "Verify Edited ContactPhone", "Failed! Edited_ContactPhone =" + ContactPhone
					+ updatewith + ".  But site shows =" + Edited_ContactPhone);
		}
		if (!Edited_DealershipName.equals((DealershipName + updatewith))) {
			ac.rwExcel(TCnum, false, "Verify Edited DealershipName", "Failed! Edited_DealershipName =" + DealershipName
					+ updatewith + ".  But site shows =" + Edited_DealershipName);
		}
		if (!Edited_DealershipEmail.equals((DealershipEmail + updatewith + ".com"))) {
			ac.rwExcel(TCnum, false, "Verify Edited DealershipEmail", "Failed! Edited_DealershipEmail ="
					+ DealershipEmail + updatewith + ".  But site shows =" + Edited_DealershipEmail);
		}
		if (!Edited_DealershipAddress1.equals((DealershipAddress1 + updatewith))) {
			ac.rwExcel(TCnum, false, "Verify Edited DealershipAddress1", "Failed! Edited_DealershipAddress1 ="
					+ DealershipAddress1 + updatewith + ".  But site shows =" + Edited_DealershipAddress1);
		}
		if (!Edited_DealershipAddress2.equals((DealershipAddress2 + updatewith))) {
			ac.rwExcel(TCnum, false, "Verify Edited DealershipAddress2", "Failed! Edited_DealershipAddress2 ="
					+ DealershipAddress2 + updatewith + ".  But site shows =" + Edited_DealershipAddress2);
		}

		// Re-enter all dealership info
		TCnum = "TC139666_refill";
		dpP.enter_ContactTitle(driver, ContactTitle, TCnum);
		dpP.enter_ContactName(driver, ContactName, TCnum);
		dpP.enter_ContactDepartment(driver, ContactDepartment, TCnum);
		dpP.enter_ContactPhone(driver, ContactPhone, TCnum);
		dpP.enter_DealershipName(driver, DealershipName, TCnum);
		dpP.enter_DealershipEmail(driver, DealershipEmail, TCnum);
		dpP.enter_Address1(driver, DealershipAddress1, TCnum);
		dpP.enter_Address2(driver, DealershipAddress2, TCnum);
		dpP.clickSAVE(driver, TCnum);
		dpP.clickNOBtn(driver, TCnum);
		ac.Wait(wt);

		igP.clickInventoryGalleryBtn(driver);// dpP.clickViewInventoryBtn(driver, TCnum);

		// Need to do:
		// TC139668
		// TC139670
		// Dealership Settings reflect on Inventory Gallery I...
		// C139669
		// C139671
		// C139673
		// Forgot Password, Dealership name, Helps & Logout
		// TC139686 Done
		// TC141677
		// TC139675

		TCnum = "TC141679_6_ig";
		igP.verifyDealershipname(dealershipName, TCnum);
		TCnum = "TC139684_06";
		igP.clickLogout(driver);
	}

	public static void VINpxInventoryTC(WebDriver driver, String brw, String versionNum, String envment,
			String checkEmail) throws IOException, InterruptedException, ClassNotFoundException, SQLException {

		// Load environment parameters
		Properties prop = new Properties();
		// testprop.load(new FileInputStream("data/autopxConf.properties"));
		try {
			prop.load(AUTOpxController.class.getClassLoader().getResourceAsStream("data/autopxConf.properties"));
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
		String VINpxDealershipBrandName = prop.getProperty(env + ".VINpxDealershipBrandName");
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
		String VINpxSupportEmail = prop.getProperty(env + ".VINpxSupportEmail");
		String VINpxSupportEmailPS = prop.getProperty(env + ".VINpxSupportEmailPS");
		String VINpxdlrGuid = prop.getProperty(env + ".VINpxdlrGuid");

		int MaxVinsForPreview = Integer.parseInt(MaxVins);
		int wt = Integer.parseInt(prop.getProperty("AUTOpx.waitTime"));
		// Initial
		// final int wt_Secs = 6;
		String TCnum;
		// String ptitle;
		String tempVIN = "";
		String tempVehGUID = "";
		Comlibs ac = new Comlibs();
		ac.rwExcel("", "*********VINpx Inventory TCs**********", "");
		AUTOpxLogin loginP = new AUTOpxLogin(driver);
		TCnum = "TC139659_7_vinpx";
		loginP.verifyHeaderFooter(env, versionNum, TCnum);
		TCnum = "TC141679_6_vinpx";
		// Verify invalid Username and PS
		TCnum = "TC171252";
		loginP.login(driver, "invalid" + accountEmail, accountPS, TCnum, "Invalid Username or Password");
		TCnum = "TC171253";
		loginP.login(driver, accountEmail, accountPS + "invalid", TCnum, "Invalid Username or Password");
		ac.Wait(wt * 10);// wait 20 secs here because we have a perfomance issue when login. Remove it once issue fixed. 2019-01-14
		loginP.login(driver, accountEmail, accountPS);
		AcceptLicenseAgreementtoContinue acceptLicenseP = new AcceptLicenseAgreementtoContinue(driver);
		acceptLicenseP.clickCancelBtn(driver);
		// Verify Forgot Password process

		if ((accountEmail.contains("gmail.com")) && (checkEmail.equalsIgnoreCase("Yes"))) {
			// So far only accept gmail email
			TCnum = "TC139686_02";
			loginP.clickForgotPasswordLink(driver, TCnum);
			VDVIPasswordReset vdviPasswordP = new VDVIPasswordReset(driver);
			vdviPasswordP.inputEmail(driver, accountEmail);
			TCnum = "TC139686_02_PSB";
			vdviPasswordP.clickRestPSBtn(driver, TCnum);
			vdviPasswordP.verifyMessage(driver, TCnum);
			TCnum = "TC139686_02_LTYAL";
			vdviPasswordP.clickLoginToYourAccountLink(driver, TCnum);
			MailReader gMail = new MailReader();
			String tempPS = "";
			String subject = "Reset Password for VINpx";

			String content1 = "You have requested to have your password reset for your VINpx account.";
			String psB4 = "Your temporary password is ";
			String psAfter = "Go to";
			// mailID = "Imap.gmail.com";
			// email ="tdautof1@gmail.com";
			// mailPassword = "Autodata1";
			tempPS = gMail.getTemporaryPS(subject, psB4, psAfter, "Imap.gmail.com", accountEmail, accountPS);
			TCnum = "TC139686_02_tempPS";
			loginP.login(driver, accountEmail, tempPS); // this should be correct one. Now issue here, see autopxops-1196
			// loginP.loginDealerProfile(driver, accountEmail, tempPS);//this is temp, it skips agreement page
			// AcceptLicenseAgreementtoContinue acceptLicenseP = new AcceptLicenseAgreementtoContinue(driver);
			acceptLicenseP.clickAcceptPSBtn(driver);
			DealerProfile dpP = new DealerProfile(driver);
			TCnum = "TC139674";
			dpP.changePS(driver, accountPS, TCnum);
			TCnum = "TC139686_02_NoBtn";
			ac.Wait(wt);
			dpP.clickNOBtn(driver, TCnum);
			TCnum = "TC139686_02_Inv";
			ac.Wait(wt);
			dpP.clickInventoryGalleryBtn(driver, TCnum);
			ac.Wait(wt);
			dpP.clickLogout(driver);
		} else {
			TCnum = "TC139686_02";
			ac.rwExcel(TCnum, false, "Cannot test Forgot Password test procedure",
					"Since the account email is not gmail email. Account Email=" + accountEmail);
		}
		// End of Verify Forgot Password process

		loginP.login(driver, accountEmail, accountPS);
		// AcceptLicenseAgreementtoContinue acceptLicenseP = new AcceptLicenseAgreementtoContinue(driver);

		TCnum = "TC144867_vinpx";
		acceptLicenseP.verifyAgreementTitle(driver, 1, "VINpx Agreement", true, TCnum); // VINpx Agreement, STOCKpx Agreement, LOTpx Agreement

		vinpxConent = acceptLicenseP.getFile("data/vinpxAgreement.txt");
		int vinpxAgreementTotalLines = acceptLicenseP.getLineNum();
		TCnum = "TC144867_5_vinpx";
		acceptLicenseP.verifyPDF(driver, 1, "vinpx", vinpxConent, vinpxAgreementTotalLines, TCnum, envment);

		acceptLicenseP.clickAcceptBtn(driver);
		ImageGallery igP = new ImageGallery(driver);
		igP.clickDealerShipInfoBtn(driver);
		DealerProfile dpP = new DealerProfile(driver);
		dpP.selectBand(driver, VINpxDealershipBrandName);
		dpP.clickInventoryGalleryBtn(driver, TCnum);

		// Help section - QA only, not for Prod since cannot access the prod emails
		if (env.equalsIgnoreCase("QA")) {

			TCnum = "TC139675_7_VINpx"; // Help - Contact Support. email and tel no.
			// (WebDriver driver, String email, String tel,String VINpxSupportEmail, String VINpxSupportEmailPS, String tc
			igP.verifyHelpContactSupport(driver, strHelpEmail, strHelpTel, VINpxSupportEmail, VINpxSupportEmailPS,
					checkEmail, TCnum);
			TCnum = "TC139675_14_VINpx";
			igP.verifyHelpSystemHelp(driver, TCnum);// Help - System Help.
			TCnum = "TC139675_17_VINpx"; // Help - Report Issue. email and tel no.
			igP.verifyHelpReportIssue(driver, strHelpEmail, strHelpTel, VINpxSupportEmail, VINpxSupportEmailPS,
					checkEmail, TCnum);
		}
		tempVIN = "6";
		igP.enterTextInSearch(tempVIN);
		ac.Wait(wt);
		igP.verifyDealershipname(dealershipName, TCnum);
		tempVIN = " ";
		igP.enterTextInSearch(tempVIN);
		ac.Wait(wt);
		if (addNewVIN.equalsIgnoreCase("Yes") && render.equalsIgnoreCase("Yes") && (!env.equalsIgnoreCase("Prod"))) {
			// *********************Add VIN for VINpx*******************************************************
			// Add VIN
			// Verify Add A New Vehicle to Inventory on VINpx
			// dealer...TC139695
			TCnum = "TC139695_5";
			ac.rwExcel(TCnum, "------ Add VIN for VINpx ------", "");
			igP.clickAddInventoryBtn(driver, TCnum);
			ac.Wait(wt);
			igP.clickAddInventoryCancelBtn(driver, TCnum);
			ac.Wait(wt);
			igP.clickAddInventoryBtn(driver, TCnum);
			ac.Wait(wt);
			igP.clickAddInventoryXBtn(driver, TCnum);
			ac.Wait(wt);
			igP.clickAddInventoryBtn(driver, TCnum);
			ac.Wait(wt);
			for (String vin : VINpxNewVINs) {
				igP.inputVinInAddInventoryField(driver, vin, TCnum);
				igP.clickAddBtn(driver, TCnum);
				if (igP.verifyGoodOrBadMsgShowing(driver, TCnum)) {
					// good msg shows
					tempVIN = vin;
					break;
				}
			}
			igP.clickAddInventoryXBtn(driver, TCnum);
			igP.clickRefleshF5Btn(driver, TCnum); // Remove this after VDVDIIMG-687 fixed
			ac.Wait(wt);
			igP.enterTextInSearch(tempVIN);
			ac.Wait(wt * 2);
			// get VehGUID with vin, dlrdoce
			tempVehGUID = igP.getVehGUID(dealerCode, tempVIN, serverName, dbName, userName, password);
			igP.verifyLoadPreviewTileImage(driver, SINGLE_VIN_RENDER_MAX_WT, tempVIN, tempVehGUID, 1, TCnum);
			igP.clickRefleshF5Btn(driver, TCnum);
			ac.Wait(wt * 15);
			igP.removeVINfrDealer(VINpxdlrGuid, tempVIN, serverName, dbName, userName, password, TCnum);
			igP.clickRefleshF5Btn(driver, TCnum);
			System.out.println("\nAdding a new VIN and removing it from the dealer processes are completed!\n");
			// ********************End of Add VIN********************************************************
		}
		igP.clickSelectNoneBtn(driver, TCnum);
		igP.verifyRerenderBtnStatus(driver, false, TCnum);
		igP.clickSelectAllBtn(driver, TCnum);
		// get the number of all vins here and adding to the below
		// allVinNums = igP.getReRenderNum(driver, TCnum);
		allVinNums = igP.getReRenderNum(driver, TCnum);
		igP.clickSelectNoneBtn(driver, TCnum);

		// Verify Rerender Single Vehicle 01 for VINpx dealer
		TCnum = "TC139706_8";
		igP.enterTextInSearch(vin01);
		ac.Wait(wt * 2);
		igP.verifyRerenderBtnStatus(driver, false, TCnum);
		// igP.verifyGenerateURLsBtnStatus(driver, false, TCnum);
		igP.clickSelectBtn(driver, vin01, vehGUID01, TCnum);
		igP.verifyRerenderBtnStatus(driver, true, TCnum);
		// igP.verifyGenerateURLsBtnStatus(driver, true, TCnum);
		ac.rwExcel("", "------ Single VIN re-rendering ------", "");
		igP.clickRerenderBtn(driver, render, TCnum);
		igP.verifyGoodMsgShowing(driver, TCnum);
		igP.verifyLoadPreviewTileImage(driver, SINGLE_VIN_RENDER_MAX_WT, vin01, vehGUID01, 1, TCnum);
		igP.verifyRerenderBtnStatus(driver, true, TCnum);
		// igP.verifyGenerateURLsBtnStatus(driver, true, TCnum);
		TCnum = "TC139706_9";
		igP.clickViewDetailsBtn(driver, vin01, vehGUID01, TCnum);
		VehicleGallery vgP = new VehicleGallery(driver);
		TCnum = "TC139706_10";
		vgP.verifyLoadPannelImage(driver, TCnum);
		vgP.clickBackToInventoryBtn(driver);

		// Verify Rerender Single Vehicle 02 for VINpx dealer
		TCnum = "TC139706_8_vin02";
		ac.Wait(wt);
		igP.enterTextInSearch(vin02);
		ac.Wait(wt * 2);
		igP.verifyRerenderBtnStatus(driver, false, TCnum);
		// igP.verifyGenerateURLsBtnStatus(driver, false, TCnum);
		igP.clickSelectBtn(driver, vin02, vehGUID02, TCnum);
		igP.verifyRerenderBtnStatus(driver, true, TCnum);
		// igP.verifyGenerateURLsBtnStatus(driver, true, TCnum);
		igP.clickRerenderBtn(driver, render, TCnum);
		igP.verifyGoodMsgShowing(driver, TCnum);
		igP.verifyLoadPreviewTileImage(driver, SINGLE_VIN_RENDER_MAX_WT, vin02, vehGUID02, 1, TCnum);
		igP.verifyRerenderBtnStatus(driver, true, TCnum);
		// igP.verifyGenerateURLsBtnStatus(driver, true, TCnum);
		TCnum = "TC139706_9_vin02";
		igP.clickViewDetailsBtn(driver, vin02, vehGUID02, TCnum);
		// VehicleGallery vgP = new VehicleGallery(driver);
		TCnum = "TC139706_10_vin02";
		vgP.verifyLoadPannelImage(driver, TCnum);
		vgP.clickBackToInventoryBtn(driver);

		// Verify Rerender All Vehicles for VINpx dealer
		// TC139709
		TCnum = "TC139709_06";
		igP.clickSelectAllBtn(driver, TCnum);
		allVinNums = igP.getReRenderNum(driver, TCnum);
		igP.clickRerenderBtn(driver, render, TCnum);
		igP.verifyRerenderBtnStatus(driver, true, TCnum);
		// igP.verifyGenerateURLsBtnStatus(driver, true, TCnum);
		igP.verifyGoodMsgShowing(driver, TCnum);
		ac.Wait(wt * 5);
		igP.enterTextInSearch(vin01);
		ac.Wait(wt * 2);
		igP.verifyLoadPreviewTileImage(driver, ALL_VINS_RENDER_MAX_WT, vin01, vehGUID01, allVinNums, TCnum);
		ac.Wait(wt);
		igP.enterTextInSearch(vin02);
		ac.Wait(wt * 2);
		igP.verifyLoadPreviewTileImage(driver, ALL_VINS_RENDER_MAX_WT, vin02, vehGUID02, allVinNums / 2, TCnum);
		igP.verifyRerenderBtnStatus(driver, true, TCnum);
		// igP.verifyGenerateURLsBtnStatus(driver, true, TCnum);
		TCnum = "TC139709_06";
		igP.clickViewDetailsBtn(driver, vin02, vehGUID02, TCnum);
		// VehicleGallery vgP = new VehicleGallery(driver);
		TCnum = "TC139709_06";
		vgP.verifyLoadPannelImage(driver, TCnum);

		// VINpx msg field
		TCnum = "TC139904_vinpx_msg_08";
		String vinMSG = vgP.twoRandomNum() + "_This is VINpx MSG!";
		int filedMaxLength = 30;
		vgP.inputMessage(driver, vinMSG, TCnum);
		TCnum = "TC139904_vinpx_msg_09";
		vgP.clickSaveChangesBtn(driver, TCnum);
		TCnum = "TC139904_vinpx_msg_10";
		vgP.acceptAlert(TCnum, "OK");
		vgP.clickBackToInventoryBtn(driver);
		TCnum = "TC139904_vinpx_msg_12";
		igP.enterTextInSearch(vin02);
		ac.Wait(wt * 2);
		igP.clickViewDetailsBtn(driver, vin02, vehGUID02, TCnum);
		TCnum = "TC139904_vinpx_msg_13";
		vgP.verifyVinMsg(driver, vin02, vinMSG, filedMaxLength, TCnum);

		// VINpx New Vehicle Checkbox
		TCnum = "TC139713_08_vinpx";
		vgP.unSelectNewVehicleCheckBox(driver);
		TCnum = "TC139713_09";
		vgP.clickSaveChangesBtn(driver, TCnum);
		vgP.acceptAlert(TCnum, "OK");
		vgP.clickBackToInventoryBtn(driver);
		igP.enterTextInSearch(vin02);
		ac.Wait(wt * 2);
		TCnum = "TC139713_11";
		igP.clickViewDetailsBtn(driver, vin02, vehGUID02, TCnum);
		TCnum = "TC139713_12";
		vgP.verifyNewVehicleCheckBox(driver, vin02, false, TCnum);
		vgP.selectNewVehicleCheckBox(driver);
		TCnum = "TC139713_09_select";
		vgP.clickSaveChangesBtn(driver, TCnum);
		vgP.acceptAlert(TCnum, "OK");
		vgP.clickBackToInventoryBtn(driver);
		igP.enterTextInSearch(vin02);
		ac.Wait(wt * 3);
		igP.clickViewDetailsBtn(driver, vin02, vehGUID02, TCnum);
		vgP.verifyNewVehicleCheckBox(driver, vin02, true, TCnum);

		vgP.clickBackToInventoryBtn(driver);
		// Verify No Upload New Picture function for VINpx de... TC139919
		TCnum = "TC139919_05";
		ac.Wait(wt);
		igP.clickSelectAllBtn(driver, TCnum);
		allVinNums = igP.getReRenderNum(driver, TCnum);
		igP.clickSelectNoneBtn(driver, TCnum);
		if (allVinNums > 20) {
			igP.scrollUp(driver, 650, TCnum);
			if (igP.verifyLoadMoreVehicleBtnStatus(driver, true, TCnum)) {
				igP.clickLoadMoreVehicleBtn(driver, TCnum);
			}
			igP.scrollUp(driver, -650, TCnum);
		}
		if (allVinNums >= MaxVinsForPreview) {
			allVinNums = MaxVinsForPreview;
		}
		ac.Wait(wt);
		// igP.enterTextInSearch(vin01); // This will make only one vehicle for the Next VIN.
		// ac.Wait(wt);
		try {
			allImageNums = igP.getTileImageNum(driver, vehGUID01, "");
		} catch (Exception ex) {
			System.out.println("error occurs!");
			ac.rwExcel(TCnum, false, "getTileImageNum", "failed");
		}
		ac.Wait(wt);
		igP.clickViewDetailsBtn(driver, vin01, vehGUID01, TCnum);
		// VehicleGallery vgP = new VehicleGallery(driver);
		vgP.verifyUplaodCustomPicsBtnExist(driver, false, TCnum);

		// Arrow right for Image TC139929
		// Previous Image
		TCnum = "TC139930_4";
		for (int x = 0; x < allImageNums; x++) { // 15
			System.out.println("Left next Image =" + x + "\n");
			ac.rwExcel(TCnum, "Left next image", " next =" + x);
			vgP.clickLeftNextImageBtn(driver, TCnum);
			vgP.verifyLoadPannelImage(driver, TCnum);
		}
		// Next Image
		TCnum = "TC139929_4";
		for (int x = 0; x < allImageNums; x++) { // 15
			System.out.println("Right next Image =" + x + "\n");
			ac.rwExcel(TCnum, "Right next image", " next =" + x);
			vgP.clickRightNextImageBtn(driver, TCnum);
			vgP.verifyLoadPannelImage(driver, TCnum);
		}
		// Previous and Next Image, Arrow ritht & left for Image TC139931
		TCnum = "TC139931_4";
		vgP.clickLeftNextImageBtn(driver, TCnum);
		vgP.verifyLoadPannelImage(driver, TCnum);
		vgP.clickRightNextImageBtn(driver, TCnum);
		vgP.verifyLoadPannelImage(driver, TCnum);

		// Previous VIN, Arrow left for VIN TC139934
		TCnum = "TC139934_19";
		for (int x = 0; x < allVinNums; x++) { // 4
			String currentVIN = vgP.retriveVIN(driver);
			System.out.println("left arrow previous vehicle =" + x + "   - Current VIN = " + currentVIN + "\n");
			ac.rwExcel(TCnum, "left arrow previous vehicle", " previous =" + x + "   - Current VIN = " + currentVIN);
			vgP.hoverOnLeftArrowPreviousVehicleBtn(driver, TCnum);
			vgP.verifyLoadPreviousControlImage(driver, TCnum);
			vgP.clickLeftNextVehicleBtn(driver, TCnum);
			vgP.verifyLoadPannelImage(driver, TCnum);
		}
		// Next VIN, Arrow right for VIN TC139933
		TCnum = "TC139933_6";

		for (int x = 0; x < allVinNums; x++) { // 4
			String currentVIN = vgP.retriveVIN(driver);
			System.out.println("Right arrow next vehicle =" + x + "   - Current VIN = " + currentVIN + "\n");
			ac.rwExcel(TCnum, "Right arrow next vehicle", " next =" + x + "   - Current VIN = " + currentVIN);
			vgP.hoverOnRightArrowNextVehicleBtn(driver, TCnum);
			vgP.verifyLoadNextControlImage(driver, TCnum);
			vgP.clickRightNextVehicleBtn(driver, TCnum);
			vgP.verifyLoadPannelImage(driver, TCnum);
		}
		// Arrow rigtht & left for VIN TC139935
		TCnum = "TC139935_19";
		vgP.hoverOnLeftArrowPreviousVehicleBtn(driver, TCnum);
		vgP.verifyLoadPreviousControlImage(driver, TCnum);
		vgP.clickLeftNextVehicleBtn(driver, TCnum);
		vgP.verifyLoadPannelImage(driver, TCnum);
		vgP.hoverOnRightArrowNextVehicleBtn(driver, TCnum);
		vgP.verifyLoadNextControlImage(driver, TCnum);
		vgP.clickRightNextVehicleBtn(driver, TCnum);
		vgP.verifyLoadPannelImage(driver, TCnum);
		vgP.clickRightNextImageBtn(driver, TCnum);
		// Need to do. Verify Generate Single Vehicle URL for VINpx dealer...
		// TC139966.
		// Need to do. Verify Generate All Vehicles URL for VINpx dealer
		// TC139963.

		// Verify Copy single URL for VINpx dealer TC139967
		TCnum = "TC139967_9";
		boolean newLink = true;
		if (newLink) {
			String parentHandle = driver.getWindowHandle(); // get the current window handle
			vgP.clickURLLink(driver, brw, TCnum);
			for (String winHandle : driver.getWindowHandles()) {
				driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
			}
			vgP.verifyLoadURLImage(driver, TCnum);
			driver.close();
			driver.switchTo().window(parentHandle); // switch back to the original window
		} else {
			driver.get(urlLink);
			urlLink = vgP.getURLLink(driver, TCnum);
			vgP.verifyLoadURLImage(driver, TCnum);
			ac.GoBack(driver);
		}
		vgP.clickBackToInventoryBtn(driver);

		// Stop here!

		// Need to do. Verify Account Status for VINpx dealer TC139969. There
		// is a ticket for the issue.

		TCnum = "TC139684_06";
		igP.clickLogout(driver);
	}

	public static void STOCKpxInventoryTC(WebDriver driver, String brw, String envment)
			throws IOException, InterruptedException, ClassNotFoundException, SQLException {

		// Load environment parameters
		Properties prop = new Properties();
		// testprop.load(new FileInputStream("data/autopxConf.properties"));
		try {
			prop.load(AUTOpxController.class.getClassLoader().getResourceAsStream("data/autopxConf.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String env = prop.getProperty("AUTOpx.environment");
		String versionNum = prop.getProperty("AUTOpx.versionNum");
		String envBrowser = prop.getProperty("AUTOpx.browser");
		String render = prop.getProperty("AUTOpx.render");
		String addNewVIN = prop.getProperty("AUTOpx.addNewVIN");
		String checkEmail = prop.getProperty("AUTOpx.checkEmail");
		String accountEmail = prop.getProperty(env + ".STOCKpxEmail");
		String accountPS = prop.getProperty(env + ".STOCKpxPassword");
		// String baseURL = prop.getProperty(env + ".STOCKpxDealerPortalBaseURL");
		String dealershipName = prop.getProperty(env + ".STOCKpxDealershipname");
		String dealerCode = prop.getProperty(env + ".STOCKpxDealerCode");
		String vin01 = prop.getProperty(env + ".STOCKpxVin01");
		String vin02 = prop.getProperty(env + ".STOCKpxVin02");
		String vehGUID01 = prop.getProperty(env + ".STOCKpxVin01GUID");
		String vehGUID02 = prop.getProperty(env + ".STOCKpxVin02GUID");
		String[] STOCKpxNewVINs = fetchOneDemArrayFromPropFile(env + ".STOCKpxNewVINs", prop);
		String serverName = prop.getProperty(env + ".serverName");
		String dbName = prop.getProperty(env + ".dbName");
		String userName = prop.getProperty(env + ".userName");
		String password = prop.getProperty(env + ".password");
		String MaxVins = prop.getProperty(env + ".MaxVinsForPreview");
		String VINpxSupportEmail = prop.getProperty(env + ".VINpxSupportEmail");
		String VINpxSupportEmailPS = prop.getProperty(env + ".VINpxSupportEmailPS");
		String STOCKpxdlrGuid = prop.getProperty(env + ".STOCKpxdlrGuid");
		int MaxVinsForPreview = Integer.parseInt(MaxVins);
		int wt = Integer.parseInt(prop.getProperty("AUTOpx.waitTime"));
		// Initial
		// final int wt_Secs = 6;
		String TCnum;
		// String ptitle;
		String tempVIN = "";
		String tempVehGUID = "";
		int allVinNums = 0;
		Comlibs ac = new Comlibs();
		ac.rwExcel("", "*********STOCKpx Inventory TCs**********", "");
		AUTOpxLogin loginP = new AUTOpxLogin(driver);
		TCnum = "TC139659_7_stockpx";
		loginP.verifyHeaderFooter(env, versionNum, TCnum);
		TCnum = "TC141679_6_stockpx";
		loginP.login(driver, accountEmail, accountPS);
		AcceptLicenseAgreementtoContinue acceptLicenseP = new AcceptLicenseAgreementtoContinue(driver);
		TCnum = "TC144868_stockpx";
		acceptLicenseP.verifyAgreementTitle(driver, 1, "STOCKpx Agreement", true, TCnum); // VINpx Agreement, STOCKpx Agreement, LOTpx Agreement

		stockpxConent = acceptLicenseP.getFile("data/stockpxAgreement.txt");
		int stockpxAgreementTotalLines = acceptLicenseP.getLineNum();
		TCnum = "TC144868_5_stockpx";
		acceptLicenseP.verifyPDF(driver, 1, "stockpx", stockpxConent, stockpxAgreementTotalLines, TCnum, envment);

		acceptLicenseP.clickAcceptBtn(driver);
		ImageGallery igP = new ImageGallery(driver);
		igP.verifyDealershipname(dealershipName, TCnum);
		// Help section
		TCnum = "TC139675_7_STOCKpx"; // Help - Contact Support. email and tel no.
		// igP.verifyHelpContactSupport(driver, strHelpEmail, strHelpTel, TCnum);
		igP.verifyHelpContactSupport(driver, strHelpEmail, strHelpTel, VINpxSupportEmail, VINpxSupportEmailPS,
				checkEmail, TCnum);
		TCnum = "TC139675_14_STOCKpx";
		igP.verifyHelpSystemHelp(driver, TCnum);// Help - System Help.
		TCnum = "TC139675_17_STOCKpx"; // Help - Report Issue. email and tel no.
		// igP.verifyHelpReportIssue(driver, strHelpEmail, strHelpTel, TCnum);
		igP.verifyHelpReportIssue(driver, strHelpEmail, strHelpTel, VINpxSupportEmail, VINpxSupportEmailPS, checkEmail,
				TCnum);
		if (addNewVIN.equalsIgnoreCase("Yes") && render.equalsIgnoreCase("Yes")) {
			//// *********************Add VIN for STOCKpx*******************************************************
			// Add VIN
			// Verify Add A New Vehicle to Inventory on STOCKpx d...
			// TC139704
			TCnum = "TC139704_5";
			ac.rwExcel(TCnum, "------ Add VIN for STOCKpx ------", "");
			igP.clickAddInventoryBtn(driver, TCnum);
			ac.Wait(wt);
			igP.clickAddInventoryCancelBtn(driver, TCnum);
			ac.Wait(wt);
			igP.clickAddInventoryBtn(driver, TCnum);
			ac.Wait(wt);
			igP.clickAddInventoryXBtn(driver, TCnum);
			ac.Wait(wt);
			igP.clickAddInventoryBtn(driver, TCnum);
			ac.Wait(wt);
			for (String vin : STOCKpxNewVINs) {
				igP.inputVinInAddInventoryField(driver, vin, TCnum);
				igP.clickAddBtn(driver, TCnum);
				if (igP.verifyGoodOrBadMsgShowing(driver, TCnum)) {
					// good msg shows
					tempVIN = vin;
					break;
				}
			}
			igP.clickAddInventoryXBtn(driver, TCnum);
			igP.clickRefleshF5Btn(driver, TCnum);
			ac.Wait(wt);
			igP.enterTextInSearch(tempVIN);
			ac.Wait(wt);
			// get VehGUID with vin, dlrdoce
			tempVehGUID = igP.getVehGUID(dealerCode, tempVIN, serverName, dbName, userName, password);
			igP.verifyLoadPreviewTileImage(driver, SINGLE_VIN_RENDER_MAX_WT, tempVIN, tempVehGUID, 1, TCnum);
			igP.clickRefleshF5Btn(driver, TCnum);
			ac.Wait(wt * 15);
			igP.removeVINfrDealer(STOCKpxdlrGuid, tempVIN, serverName, dbName, userName, password, TCnum);
			igP.clickRefleshF5Btn(driver, TCnum);
			System.out.println("\nAdding a new Stock VIN and removing it from the dealer processes are complete!\n");
			//// *********************End of Add VIN*******************************************************
		}
		// Verify Rerender Single Vehicle TC139707
		TCnum = "TC139707_8";
		igP.verifyRerenderBtnStatus(driver, false, TCnum);
		// igP.verifyGenerateURLsBtnStatus(driver, false, TCnum);
		igP.enterTextInSearch(vin01);
		ac.Wait(wt);
		igP.clickSelectBtn(driver, vin01, vehGUID01, TCnum);
		igP.verifyRerenderBtnStatus(driver, true, TCnum);
		// igP.verifyGenerateURLsBtnStatus(driver, true, TCnum);
		igP.clickRerenderBtn(driver, render, TCnum);
		igP.verifyGoodMsgShowing(driver, TCnum);
		igP.verifyLoadPreviewTileImage(driver, SINGLE_VIN_RENDER_MAX_WT, vin01, vehGUID01, 1, TCnum);
		igP.verifyRerenderBtnStatus(driver, true, TCnum);
		// igP.verifyGenerateURLsBtnStatus(driver, true, TCnum);
		TCnum = "TC139707_9";
		igP.clickViewDetailsBtn(driver, vin01, vehGUID01, TCnum);
		VehicleGallery vgP = new VehicleGallery(driver);
		TCnum = "TC139707_10";
		vgP.verifyLoadPannelImage(driver, TCnum);
		vgP.clickBackToInventoryBtn(driver);
		// Verify Rerender Single Vehicle 02 for STOCpx dealer
		TCnum = "TC139707_8_vin02";
		igP.verifyRerenderBtnStatus(driver, false, TCnum);
		// igP.verifyGenerateURLsBtnStatus(driver, false, TCnum);
		ac.Wait(wt);
		igP.enterTextInSearch(vin02);
		ac.Wait(wt);
		igP.clickSelectBtn(driver, vin02, vehGUID02, TCnum);
		igP.verifyRerenderBtnStatus(driver, true, TCnum);
		// igP.verifyGenerateURLsBtnStatus(driver, true, TCnum);
		igP.clickRerenderBtn(driver, render, TCnum);
		igP.verifyGoodMsgShowing(driver, TCnum);
		igP.verifyLoadPreviewTileImage(driver, SINGLE_VIN_RENDER_MAX_WT, vin02, vehGUID02, 1, TCnum);
		igP.verifyRerenderBtnStatus(driver, true, TCnum);
		// igP.verifyGenerateURLsBtnStatus(driver, true, TCnum);
		TCnum = "TC139707_9_vin02";
		igP.clickViewDetailsBtn(driver, vin02, vehGUID02, TCnum);
		// VehicleGallery vgP = new VehicleGallery(driver);
		TCnum = "TC139707_10_vin02";
		vgP.verifyLoadPannelImage(driver, TCnum);
		vgP.clickBackToInventoryBtn(driver);
		igP.clickSelectAllBtn(driver, TCnum);
		allVinNums = igP.getReRenderNum(driver, TCnum);
		igP.clickSelectNoneBtn(driver, TCnum);
		// Verify Rerender All Vehicles for STOCKpx dealer TC139712
		TCnum = "TC139712_06";
		if (allVinNums > 20) {
			igP.clickShowAllBtn(driver, TCnum);
		}
		igP.clickSelectAllBtn(driver, TCnum);
		igP.clickRerenderBtn(driver, render, TCnum);
		igP.verifyRerenderBtnStatus(driver, true, TCnum);
		// igP.verifyGenerateURLsBtnStatus(driver, true, TCnum);
		igP.verifyGoodMsgShowing(driver, TCnum);
		ac.Wait(wt * 5);
		igP.enterTextInSearch(vin01);
		ac.Wait(wt);
		igP.verifyLoadPreviewTileImage(driver, ALL_VINS_RENDER_MAX_WT, vin01, vehGUID01, allVinNums, TCnum);
		ac.Wait(wt);
		igP.enterTextInSearch(vin02);
		ac.Wait(wt);
		igP.verifyLoadPreviewTileImage(driver, ALL_VINS_RENDER_MAX_WT, vin02, vehGUID02, allVinNums / 2, TCnum);
		igP.verifyRerenderBtnStatus(driver, true, TCnum);
		// igP.verifyGenerateURLsBtnStatus(driver, true, TCnum);
		TCnum = "TC139712_06_1";
		igP.clickViewDetailsBtn(driver, vin02, vehGUID02, TCnum);
		// VehicleGallery vgP = new VehicleGallery(driver);
		TCnum = "TC139712_06_2";
		vgP.verifyLoadPannelImage(driver, TCnum);

		// STOCKpx msg field
		TCnum = "TC139908_stockpx_msg_08";
		String vinMSG = vgP.twoRandomNum() + "_This is VINpx MSG!";
		int filedMaxLength = 30;
		vgP.inputMessage(driver, vinMSG, TCnum);
		TCnum = "TC139908_stockpx_msg_09";
		vgP.clickSaveChangesBtn(driver, TCnum);
		TCnum = "TC139908_stockpx_msg_10";
		vgP.acceptAlert(TCnum, "OK");
		vgP.clickBackToInventoryBtn(driver);
		TCnum = "TC139908_stockpx_msg_12";
		igP.enterTextInSearch(vin02);
		ac.Wait(wt);
		igP.clickViewDetailsBtn(driver, vin02, vehGUID02, TCnum);
		TCnum = "TC139908_stockpx_msg_13";
		vgP.verifyVinMsg(driver, vin02, vinMSG, filedMaxLength, TCnum);
		// STOCKpx New Vehicle Checkbox
		TCnum = "TC139909_08_vinpx";
		vgP.unSelectNewVehicleCheckBox(driver);
		TCnum = "TC139909_09";
		vgP.clickSaveChangesBtn(driver, TCnum);
		vgP.acceptAlert(TCnum, "OK");
		vgP.clickBackToInventoryBtn(driver);
		igP.enterTextInSearch(vin02);
		ac.Wait(wt);
		TCnum = "TC139909_11";
		igP.clickViewDetailsBtn(driver, vin02, vehGUID02, TCnum);
		TCnum = "TC139909_12";
		vgP.verifyNewVehicleCheckBox(driver, vin02, false, TCnum);
		vgP.selectNewVehicleCheckBox(driver);
		TCnum = "TC139909_09_select";
		vgP.clickSaveChangesBtn(driver, TCnum);
		vgP.acceptAlert(TCnum, "OK");
		vgP.clickBackToInventoryBtn(driver);
		igP.enterTextInSearch(vin02);
		ac.Wait(wt);
		igP.clickViewDetailsBtn(driver, vin02, vehGUID02, TCnum);
		vgP.verifyNewVehicleCheckBox(driver, vin02, true, TCnum);

		vgP.clickBackToInventoryBtn(driver);

		// Verify No Upload New Picture Function for STOCKpx ... TC139920
		TCnum = "TC139920_05";
		ac.Wait(wt);
		igP.clickSelectAllBtn(driver, TCnum);
		allVinNums = igP.getReRenderNum(driver, TCnum);
		igP.clickSelectNoneBtn(driver, TCnum);
		if (allVinNums > 20) {
			igP.scrollUp(driver, 650, TCnum);
			if (igP.verifyLoadMoreVehicleBtnStatus(driver, false, TCnum)) {
				igP.clickLoadMoreVehicleBtn(driver, TCnum);
			}
			igP.scrollUp(driver, -650, TCnum);
		}
		if (allVinNums >= MaxVinsForPreview) {
			allVinNums = MaxVinsForPreview;
		}
		ac.Wait(wt);
		allImageNums = igP.getTileImageNum(driver, vehGUID01, "");

		igP.clickViewDetailsBtn(driver, vin01, vehGUID01, TCnum);
		// VehicleGallery vgP = new VehicleGallery(driver);
		vgP.verifyUplaodCustomPicsBtnExist(driver, false, TCnum);

		// Arrow left for Image TC139948
		// Previous Image
		TCnum = "TC139948_4";
		for (int x = 0; x < allImageNums; x++) { // 15
			System.out.println("Left next Image =" + x + "\n");
			ac.rwExcel(TCnum, "Left next image", " next =" + x);
			vgP.clickLeftNextImageBtn(driver, TCnum);
			vgP.verifyLoadPannelImage(driver, TCnum);
		}
		// Next Image - Arrow right for Image TC139947
		TCnum = "TC139947_4";
		for (int x = 0; x < allImageNums; x++) { // 15
			System.out.println("Right next Image =" + x + "\n");
			ac.rwExcel(TCnum, "Right next image", " next =" + x);
			vgP.clickRightNextImageBtn(driver, TCnum);
			vgP.verifyLoadPannelImage(driver, TCnum);
		}
		// Previous and Next Image, Arrow ritht & left for Image TC139949
		TCnum = "TC139949_4";
		vgP.clickLeftNextImageBtn(driver, TCnum);
		vgP.verifyLoadPannelImage(driver, TCnum);
		vgP.clickRightNextImageBtn(driver, TCnum);
		// Previous VIN, Arrow left for VIN TC139952
		TCnum = "TC139952_19";
		for (int x = 0; x < allVinNums; x++) { // 4
			String currentVIN = vgP.retriveVIN(driver);
			System.out.println("left arrow previous vehicle =" + x + "   - Current VIN = " + currentVIN + "\n");
			ac.rwExcel(TCnum, "left arrow previous vehicle", " previous =" + x + "   - Current VIN = " + currentVIN);
			vgP.hoverOnLeftArrowPreviousVehicleBtn(driver, TCnum);
			vgP.verifyLoadPreviousControlImage(driver, TCnum);
			vgP.clickLeftNextVehicleBtn(driver, TCnum);
			vgP.verifyLoadPannelImage(driver, TCnum);
		}
		// Next VIN, Arrow right for VIN TC139951
		TCnum = "TC139951_6";
		for (int x = 0; x < allVinNums; x++) { // 4
			String currentVIN = vgP.retriveVIN(driver);
			System.out.println("Right arrow next vehicle =" + x + "   - Current VIN = " + currentVIN + "\n");
			ac.rwExcel(TCnum, "Right arrow next vehicle", " next =" + x + "   - Current VIN = " + currentVIN);
			vgP.hoverOnRightArrowNextVehicleBtn(driver, TCnum);
			vgP.verifyLoadNextControlImage(driver, TCnum);
			vgP.clickRightNextVehicleBtn(driver, TCnum);
			vgP.verifyLoadPannelImage(driver, TCnum);
		}
		// Arrow rigtht & left for VIN TC139953
		TCnum = "TC139953_19";
		vgP.hoverOnLeftArrowPreviousVehicleBtn(driver, TCnum);
		vgP.verifyLoadPreviousControlImage(driver, TCnum);
		vgP.clickLeftNextVehicleBtn(driver, TCnum);
		vgP.verifyLoadPannelImage(driver, TCnum);
		vgP.hoverOnRightArrowNextVehicleBtn(driver, TCnum);
		vgP.verifyLoadNextControlImage(driver, TCnum);
		vgP.clickRightNextVehicleBtn(driver, TCnum);
		vgP.verifyLoadPannelImage(driver, TCnum);
		vgP.clickRightNextImageBtn(driver, TCnum);
		// Need to do. Verify Generate Single Vehicle URL for STOCKpx dea...
		// TC139972
		// Need to do. Verify Generate All Vehicles URL for STOCKpx deale...
		// TC139973

		// Verify Copy single URL for STOCKpx dealer TC139974
		TCnum = "TC139974_9";
		boolean newLink = true;
		if (newLink) {
			String parentHandle = driver.getWindowHandle(); // get the current window handle
			vgP.clickURLLink(driver, brw, TCnum);
			for (String winHandle : driver.getWindowHandles()) {
				driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
			}
			vgP.verifyLoadURLImage(driver, TCnum);
			driver.close();
			// vgP.wait(2);
			driver.switchTo().window(parentHandle); // switch back to the original window
		} else {
			driver.get(urlLink);
			urlLink = vgP.getURLLink(driver, TCnum);
			vgP.verifyLoadURLImage(driver, TCnum);
			ac.GoBack(driver);
		}
		vgP.clickBackToInventoryBtn(driver);

		// Stop here!

		// Need to do. Verify Account Status for STOCKpx dealer. TBD. TC139478.
		// There
		// is a ticket for the issue.

		TCnum = "TC139684_06";
		igP.clickLogout(driver);

	}

	public static void LOTpxInventoryTC(WebDriver driver, String brw, String envment)
			throws IOException, InterruptedException, ClassNotFoundException, SQLException {

		// Load environment parameters
		Properties prop = new Properties();
		// testprop.load(new FileInputStream("data/autopxConf.properties"));
		try {
			prop.load(AUTOpxController.class.getClassLoader().getResourceAsStream("data/autopxConf.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String env = prop.getProperty("AUTOpx.environment");
		String versionNum = prop.getProperty("AUTOpx.versionNum");
		String envDevice = prop.getProperty("AUTOpx.envDevice");
		String envBrowser = prop.getProperty("AUTOpx.browser");
		String render = prop.getProperty("AUTOpx.render");
		String img = prop.getProperty("AUTOpx.customPicPathFile");
		String addNewVIN = prop.getProperty("AUTOpx.addNewVIN");
		String checkEmail = prop.getProperty("AUTOpx.checkEmail");
		String accountEmail = prop.getProperty(env + ".LOTpxEmail");
		String accountPS = prop.getProperty(env + ".LOTpxPassword");
		// String baseURL = prop.getProperty(env + ".LOTpxDealerPortalBaseURL");
		String dealershipName = prop.getProperty(env + ".LOTpxDealershipname");
		String LOTpxDealershipBrandName = prop.getProperty(env + ".LOTpxDealershipBrandName");
		String dealerCode = prop.getProperty(env + ".LOTpxDealerCode");
		String vin01 = prop.getProperty(env + ".LOTpxVin01");
		String vin02 = prop.getProperty(env + ".LOTpxVin02");
		String vehGUID01 = prop.getProperty(env + ".LOTpxVin01GUID");
		String vehGUID02 = prop.getProperty(env + ".LOTpxVin02GUID");
		String[] LOTpxNewVINs = fetchOneDemArrayFromPropFile(env + ".LOTpxNewVINs", prop);
		String serverName = prop.getProperty(env + ".serverName");
		String dbName = prop.getProperty(env + ".dbName");
		String userName = prop.getProperty(env + ".userName");
		String password = prop.getProperty(env + ".password");
		String MaxVins = prop.getProperty(env + ".MaxVinsForPreview");
		String VINpxSupportEmail = prop.getProperty(env + ".VINpxSupportEmail");
		String VINpxSupportEmailPS = prop.getProperty(env + ".VINpxSupportEmailPS");
		int MaxVinsForPreview = Integer.parseInt(MaxVins);
		int wt = Integer.parseInt(prop.getProperty("AUTOpx.waitTime"));
		// Initial
		// final int wt_Secs = 6;
		String TCnum;
		// String ptitle;
		String tempVIN = "";
		String tempVehGUID = "";
		int allVinNums = 0;
		Comlibs ac = new Comlibs();
		ac.rwExcel("", "*********LOTpx Inventory TCs**********", "");
		AUTOpxLogin loginP = new AUTOpxLogin(driver);
		TCnum = "TC139659_7_lotpx";
		loginP.verifyHeaderFooter(env, versionNum, TCnum);
		TCnum = "TC141679_6_lotpx";
		loginP.login(driver, accountEmail, accountPS);
		AcceptLicenseAgreementtoContinue acceptLicenseP = new AcceptLicenseAgreementtoContinue(driver);
		TCnum = "TC144866_ig";
		acceptLicenseP.verifyAgreementTitle(driver, 1, "LOTpx Agreement", true, TCnum); // VINpx Agreement, STOCKpx Agreement, LOTpx Agreement

		lotpxConent = acceptLicenseP.getFile("data/lotpxAgreement.txt");
		int lotpxAgreementTotalLines = acceptLicenseP.getLineNum();
		TCnum = "TC144869_5_lotpx";
		acceptLicenseP.verifyPDF(driver, 1, "lotpx", lotpxConent, lotpxAgreementTotalLines, TCnum, envment);

		acceptLicenseP.clickAcceptBtn(driver);
		ImageGallery igP = new ImageGallery(driver);
		igP.clickDealerShipInfoBtn(driver);
		DealerProfile dpP = new DealerProfile(driver);
		dpP.selectBand(driver, LOTpxDealershipBrandName);
		dpP.clickInventoryGalleryBtn(driver, TCnum);

		// acceptLicenseP.clickAcceptBtn(driver);
		// ImageGallery igP = new ImageGallery(driver);
		// Help section
		TCnum = "TC139675_7_LOTpx"; // Help - Contact Support. email and tel no.
		// igP.verifyHelpContactSupport(driver, strHelpEmail, strHelpTel, TCnum);
		igP.verifyHelpContactSupport(driver, strHelpEmail, strHelpTel, VINpxSupportEmail, VINpxSupportEmailPS,
				checkEmail, TCnum);
		TCnum = "TC139675_14_LOTpx";
		igP.verifyHelpSystemHelp(driver, TCnum);// Help - System Help.
		TCnum = "TC139675_17_LOTpx"; // Help - Report Issue. email and tel no.
		// igP.verifyHelpReportIssue(driver, strHelpEmail, strHelpTel, TCnum);
		igP.verifyHelpReportIssue(driver, strHelpEmail, strHelpTel, VINpxSupportEmail, VINpxSupportEmailPS, checkEmail,
				TCnum);
		tempVIN = "6";
		igP.enterTextInSearch(tempVIN);
		igP.verifyDealershipname(dealershipName, TCnum);
		tempVIN = " ";
		igP.enterTextInSearch(tempVIN);
		if (addNewVIN.equalsIgnoreCase("Yes") && render.equalsIgnoreCase("Yes")) {
			//// *********************Add VIN for LOTpx*******************************************************
			// Add VIN
			// Verify Add A New Vehicle to Inventory on LOTpx d...
			// TC139705
			TCnum = "TC139705_5";
			ac.rwExcel(TCnum, "------ Add VIN for LOTpx ------", "");
			igP.clickAddInventoryBtn(driver, TCnum);
			ac.Wait(wt);
			igP.clickAddInventoryCancelBtn(driver, TCnum);
			ac.Wait(wt);
			igP.clickAddInventoryBtn(driver, TCnum);
			ac.Wait(wt);
			igP.clickAddInventoryXBtn(driver, TCnum);
			ac.Wait(wt);
			igP.clickAddInventoryBtn(driver, TCnum);
			ac.Wait(wt);
			for (String vin : LOTpxNewVINs) {

				igP.inputVinInAddInventoryField(driver, vin, TCnum);
				igP.clickAddBtn(driver, TCnum);
				if (igP.verifyGoodOrBadMsgShowing(driver, TCnum)) {
					// good msg shows
					tempVIN = vin;
					break;
				}
			}
			igP.clickAddInventoryXBtn(driver, TCnum);
			igP.clickRefleshF5Btn(driver, TCnum);
			igP.enterTextInSearch(tempVIN);
			// get VehGUID with vin, dlrdoce
			tempVehGUID = igP.getVehGUID(dealerCode, tempVIN, serverName, dbName, userName, password);
			igP.verifyLoadPreviewTileImage(driver, SINGLE_VIN_RENDER_MAX_WT, tempVIN, tempVehGUID, 1, TCnum);
			igP.clickRefleshF5Btn(driver, TCnum);
			// ********************End of Add VIN********************************************************
		}
		igP.clickSelectNoneBtn(driver, TCnum);
		igP.verifyRerenderBtnStatus(driver, false, TCnum);
		igP.clickSelectAllBtn(driver, TCnum);
		// get the number of all vins here and adding to the below
		allVinNums = igP.getReRenderNum(driver, TCnum);
		igP.clickSelectNoneBtn(driver, TCnum);

		// Verify Rerender Single Vehicle 01 TC147966
		TCnum = "TC147966_8";
		igP.verifyRerenderBtnStatus(driver, false, TCnum);
		// igP.verifyGenerateURLsBtnStatus(driver, false, TCnum);
		ac.Wait(wt);
		igP.enterTextInSearch(vin01);
		igP.clickSelectBtn(driver, vin01, vehGUID01, TCnum);
		igP.verifyRerenderBtnStatus(driver, true, TCnum);
		// igP.verifyGenerateURLsBtnStatus(driver, true, TCnum);
		ac.rwExcel("", "------ Single VIN re-rendering ------", "");
		igP.clickRerenderBtn(driver, render, TCnum);
		igP.verifyGoodMsgShowing(driver, TCnum);
		igP.verifyLoadPreviewTileImage(driver, SINGLE_VIN_RENDER_MAX_WT, vin01, vehGUID01, 1, TCnum);
		igP.verifyRerenderBtnStatus(driver, true, TCnum);
		// igP.verifyGenerateURLsBtnStatus(driver, true, TCnum);
		TCnum = "TC147966_9";
		igP.clickViewDetailsBtn(driver, vin01, vehGUID01, TCnum);
		VehicleGallery vgP = new VehicleGallery(driver);
		TCnum = "TC147966_10";
		vgP.verifyLoadPannelImage(driver, TCnum);
		vgP.clickBackToInventoryBtn(driver);
		// Verify Rerender Single Vehicle 02 for LOTpx dealer
		TCnum = "TC147966_8_vin02";
		igP.verifyRerenderBtnStatus(driver, false, TCnum);
		// igP.verifyGenerateURLsBtnStatus(driver, false, TCnum);
		ac.Wait(wt);
		igP.enterTextInSearch(vin02);
		igP.clickSelectBtn(driver, vin02, vehGUID02, TCnum);
		igP.verifyRerenderBtnStatus(driver, true, TCnum);
		// igP.verifyGenerateURLsBtnStatus(driver, true, TCnum);
		igP.clickRerenderBtn(driver, render, TCnum);
		igP.verifyGoodMsgShowing(driver, TCnum);
		igP.verifyLoadPreviewTileImage(driver, SINGLE_VIN_RENDER_MAX_WT, vin02, vehGUID02, 1, TCnum);
		igP.verifyRerenderBtnStatus(driver, true, TCnum);
		// igP.verifyGenerateURLsBtnStatus(driver, true, TCnum);
		TCnum = "TC147966_9_vin02";
		igP.clickViewDetailsBtn(driver, vin02, vehGUID02, TCnum);
		// VehicleGallery vgP = new VehicleGallery(driver);
		TCnum = "TC147966_10_vin02";
		vgP.verifyLoadPannelImage(driver, TCnum);
		vgP.clickBackToInventoryBtn(driver);
		// Verify Rerender All Vehicles for LOTpx dealer TC_LOTpx_All
		TCnum = "TC147967_06";
		igP.clickSelectAllBtn(driver, TCnum);
		allVinNums = igP.getReRenderNum(driver, TCnum);
		TCnum = "TC147967_07";
		// igP.clickRerenderBtn(driver, render, TCnum);
		igP.verifyRerenderBtnStatus(driver, true, TCnum);
		// igP.verifyGenerateURLsBtnStatus(driver, true, TCnum);
		igP.verifyGoodMsgShowing(driver, TCnum);
		TCnum = "TC147967_10";
		ac.Wait(wt * 5);
		igP.enterTextInSearch(vin01);
		igP.verifyLoadPreviewTileImage(driver, ALL_VINS_RENDER_MAX_WT, vin01, vehGUID01, allVinNums, TCnum);
		ac.Wait(wt);
		igP.enterTextInSearch(vin02);
		igP.verifyLoadPreviewTileImage(driver, ALL_VINS_RENDER_MAX_WT, vin02, vehGUID02, allVinNums / 2, TCnum);
		igP.verifyRerenderBtnStatus(driver, true, TCnum);
		// igP.verifyGenerateURLsBtnStatus(driver, true, TCnum);
		TCnum = "TC147967_06_1";
		igP.clickViewDetailsBtn(driver, vin02, vehGUID02, TCnum);
		// VehicleGallery vgP = new VehicleGallery(driver);
		TCnum = "TC147967_06_2";
		vgP.verifyLoadPannelImage(driver, TCnum);

		// LOTpx msg field
		TCnum = "TC139914_lotpx_msg_08";
		String vinMSG = vgP.twoRandomNum() + "_This is VINpx MSG!";
		int filedMaxLength = 30;
		vgP.inputMessage(driver, vinMSG, TCnum);
		TCnum = "TC139914_lotpx_msg_09";
		vgP.clickSaveChangesBtn(driver, TCnum);
		TCnum = "TC139914_lotpx_msg_10";
		vgP.acceptAlert(TCnum, "OK");
		vgP.clickBackToInventoryBtn(driver);
		TCnum = "TC139914_lotpx_msg_12";
		igP.enterTextInSearch(vin02);
		igP.clickViewDetailsBtn(driver, vin02, vehGUID02, TCnum);
		TCnum = "TC139914_lotpx_msg_13";
		vgP.verifyVinMsg(driver, vin02, vinMSG, filedMaxLength, TCnum);
		// LOTpx New Vehicle Checkbox
		TCnum = "TC139915_08_vinpx";
		vgP.unSelectNewVehicleCheckBox(driver);
		TCnum = "TC139915_09";
		vgP.clickSaveChangesBtn(driver, TCnum);
		vgP.acceptAlert(TCnum, "OK");
		vgP.clickBackToInventoryBtn(driver);
		igP.enterTextInSearch(vin02);
		TCnum = "TC139915_11";
		igP.clickViewDetailsBtn(driver, vin02, vehGUID02, TCnum);
		TCnum = "TC139915_12";
		vgP.verifyNewVehicleCheckBox(driver, vin02, false, TCnum);
		vgP.selectNewVehicleCheckBox(driver);
		TCnum = "TC139915_09_select";
		vgP.clickSaveChangesBtn(driver, TCnum);
		vgP.acceptAlert(TCnum, "OK");
		vgP.clickBackToInventoryBtn(driver);
		igP.enterTextInSearch(vin02);
		igP.clickViewDetailsBtn(driver, vin02, vehGUID02, TCnum);
		vgP.verifyNewVehicleCheckBox(driver, vin02, true, TCnum);

		vgP.clickBackToInventoryBtn(driver);

		// Verify Upload New Picture function for LOTpx de... TC139922

		TCnum = "TC139922_XX";
		igP.clickSelectAllBtn(driver, TCnum);
		allVinNums = igP.getReRenderNum(driver, TCnum);
		igP.clickSelectNoneBtn(driver, TCnum);
		if (allVinNums > 20) {
			igP.scrollUp(driver, 650, TCnum);
			if (igP.verifyLoadMoreVehicleBtnStatus(driver, true, TCnum)) {
				igP.clickLoadMoreVehicleBtn(driver, TCnum);
			}
			igP.scrollUp(driver, -650, TCnum);
		}
		if (allVinNums >= MaxVinsForPreview) {
			allVinNums = MaxVinsForPreview;
		}
		allImageNums = igP.getTileImageNum(driver, vehGUID01, "");
		igP.clickViewDetailsBtn(driver, vin01, vehGUID01, TCnum);
		// VehicleGallery vgP = new VehicleGallery(driver);
		vgP.verifyUplaodCustomPicsBtnExist(driver, true, TCnum);

		// Arrow left for Image TC139956
		// Previous Image
		TCnum = "TC139956_22";
		for (int x = 0; x < allImageNums; x++) { // 15
			System.out.println("Left next Image =" + x + "\n");
			ac.rwExcel(TCnum, "Left next image", " next =" + x);
			vgP.clickLeftNextImageBtn(driver, TCnum);
			vgP.verifyLoadPannelImage(driver, TCnum);
		}
		// Next Image - Arrow right for Image TC139955
		TCnum = "TC139955_10";
		for (int x = 0; x < allImageNums; x++) { // 15
			System.out.println("Right next Image =" + x + "\n");
			ac.rwExcel(TCnum, "Right next image", " next =" + x);
			vgP.clickRightNextImageBtn(driver, TCnum);
			vgP.verifyLoadPannelImage(driver, TCnum);
		}
		// Previous and Next Image, Arrow ritht & left for Image TC139957
		TCnum = "TC139957";
		vgP.clickLeftNextImageBtn(driver, TCnum);
		vgP.verifyLoadPannelImage(driver, TCnum);
		vgP.clickRightNextImageBtn(driver, TCnum);
		// Previous VIN, Arrow left for VIN TC139960
		TCnum = "TC139960_22";
		for (int x = 0; x < allVinNums; x++) { // 4
			String currentVIN = vgP.retriveVIN(driver);
			System.out.println("left arrow previous vehicle =" + x + "   - Current VIN = " + currentVIN + "\n");
			ac.rwExcel(TCnum, "left arrow previous vehicle", " previous =" + x + "   - Current VIN = " + currentVIN);
			vgP.hoverOnLeftArrowPreviousVehicleBtn(driver, TCnum);
			vgP.verifyLoadPreviousControlImage(driver, TCnum);
			vgP.clickLeftNextVehicleBtn(driver, TCnum);
			vgP.verifyLoadPannelImage(driver, TCnum);
		}
		// Next VIN, Arrow right for VIN TC139959
		TCnum = "TC139959_11";
		for (int x = 0; x < allVinNums; x++) { // 4
			String currentVIN = vgP.retriveVIN(driver);
			System.out.println("Right arrow next vehicle =" + x + "   - Current VIN = " + currentVIN + "\n");
			ac.rwExcel(TCnum, "Right arrow next vehicle", " next =" + x + "   - Current VIN = " + currentVIN);
			vgP.hoverOnRightArrowNextVehicleBtn(driver, TCnum);
			vgP.verifyLoadNextControlImage(driver, TCnum);
			vgP.clickRightNextVehicleBtn(driver, TCnum);
			vgP.verifyLoadPannelImage(driver, TCnum);
		}
		// Arrow rigtht & left for VIN TC139961
		TCnum = "TC139961_23";
		vgP.hoverOnLeftArrowPreviousVehicleBtn(driver, TCnum);
		vgP.verifyLoadPreviousControlImage(driver, TCnum);
		vgP.clickLeftNextVehicleBtn(driver, TCnum);
		vgP.verifyLoadPannelImage(driver, TCnum);
		vgP.hoverOnRightArrowNextVehicleBtn(driver, TCnum);
		vgP.verifyLoadNextControlImage(driver, TCnum);
		vgP.clickRightNextVehicleBtn(driver, TCnum);
		vgP.verifyLoadPannelImage(driver, TCnum);
		vgP.clickRightNextImageBtn(driver, TCnum);
		// Need to do. Verify Generate Single Vehicle URL for LOTpx dea...
		// TC139975
		// Need to do. Verify Generate All Vehicles URL for LOTpx deale...
		// TC139976

		// Verify Copy single URL for LOTpx dealer TC139977
		TCnum = "TC139977_9";
		boolean newLink = true;
		if (newLink) {
			String parentHandle = driver.getWindowHandle(); // get the current window handle
			vgP.clickURLLink(driver, brw, TCnum);
			for (String winHandle : driver.getWindowHandles()) {
				driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
			}
			vgP.verifyLoadURLImage(driver, TCnum);
			driver.close();
			driver.switchTo().window(parentHandle); // switch back to the original window
		} else {
			driver.get(urlLink);
			urlLink = vgP.getURLLink(driver, TCnum);
			vgP.verifyLoadURLImage(driver, TCnum);
			ac.GoBack(driver);
		}
		vgP.clickBackToInventoryBtn(driver);

		// Stop here!

		// Need to do. Verify Account Status for LOTpx dealer. TBD. TC139477.
		// There is a ticket for the issue.

		TCnum = "TC139684_06";
		igP.clickLogout(driver);
	}

	public static void LOTpxUploadCustomPic(WebDriver driver, String brw, String product)
			throws IOException, InterruptedException, ClassNotFoundException, SQLException, AWTException {

		// Load environment parameters
		Properties prop = new Properties();
		// testprop.load(new FileInputStream("data/autopxConf.properties"));
		try {
			prop.load(AUTOpxController.class.getClassLoader().getResourceAsStream("data/autopxConf.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		String env = prop.getProperty("AUTOpx.environment");
		String envDevice = prop.getProperty("AUTOpx.envDevice");
		String envBrowser = prop.getProperty("AUTOpx.browser");
		String render = prop.getProperty("AUTOpx.render");
		String img = prop.getProperty("AUTOpx.customPicPathFile");
		String tBrowser = prop.getProperty("AUTOpx.browser");
		String LOTpxDealershipBrandName = prop.getProperty(env + ".LOTpxDealershipBrandName");

		String accountEmail = "";
		String accountPS = "";
		String dealerCode = "";
		String vin01 = "";
		String vehGUID01 = "";

		if (product.equalsIgnoreCase("All")) {
			accountEmail = prop.getProperty(env + ".AllProdEmail");
			accountPS = prop.getProperty(env + ".AllProdPassword");
			dealerCode = prop.getProperty(env + ".AllProdDealerCode");
			vin01 = prop.getProperty(env + ".Vin01");
			vehGUID01 = prop.getProperty(env + ".Vin01GUID");
		} else if (product.equalsIgnoreCase("LOTpx")) {
			accountEmail = prop.getProperty(env + ".LOTpxEmail");
			accountPS = prop.getProperty(env + ".LOTpxPassword");
			dealerCode = prop.getProperty(env + ".LOTpxDealerCode");
			vin01 = prop.getProperty(env + ".LOTpxVin01");
			vehGUID01 = prop.getProperty(env + ".LOTpxVin01GUID");
		} else {
			System.out.println("Product is not defined properly. Prod=" + product);
			driver.close();
		}

		String serverName = prop.getProperty(env + ".serverName");
		String dbName = prop.getProperty(env + ".dbName");
		String userName = prop.getProperty(env + ".userName");
		String password = prop.getProperty(env + ".password");
		String baseURL = prop.getProperty(env + ".DealerPortalBaseURL");
		int wt = Integer.parseInt(prop.getProperty("AUTOpx.waitTime"));
		String TCnum = "Debug01";
		Comlibs ac = new Comlibs();
		ac.rwExcel("", "*********LOPpx Upload Custom Picture TCs**********", "");
		AUTOpxLogin loginP = new AUTOpxLogin(driver);
		loginP.login(driver, accountEmail, accountPS);
		AcceptLicenseAgreementtoContinue acceptLicenseP = new AcceptLicenseAgreementtoContinue(driver);
		acceptLicenseP.clickAcceptBtn(driver);

		ImageGallery igP = new ImageGallery(driver);
		igP.clickDealerShipInfoBtn(driver);
		DealerProfile dpP = new DealerProfile(driver);
		dpP.selectBand(driver, LOTpxDealershipBrandName);
		dpP.clickInventoryGalleryBtn(driver, TCnum);

		// ImageGallery igP = new ImageGallery(driver);
		TCnum = "TC139922_8_&_9";//TC139497
		igP.clickViewDetailsBtn(driver, vin01, vehGUID01, TCnum);
		VehicleGallery vgP = new VehicleGallery(driver);
		String tempDateTime = vgP.getCurrentDateTime(4, serverName, dbName, userName, password);
		vgP.uploadCustomPicture(driver, img);
		ac.Wait(wt*2);
		boolean alertPass = false;
		try {
			driver.switchTo().alert().accept();// on the “Ok” button as soon as the pop up window appears.
			driver.switchTo().alert().dismiss();// clicks on the “Cancel” button as soon as the pop up window appears.
			driver.switchTo().defaultContent();//
			alertPass = true;
			System.out.println("1. Accept the alert.");
			ac.rwExcel(TCnum, true, "Upload Custom Picture", "Alerts showing, accetped.");
		} catch (Throwable e) {
			alertPass = false;
			System.out.println("1. Failed to Accept the alert.");
			ac.rwExcel(TCnum, false, "Upload Custom Picture", "Alerts not showing properly, failed to accetp.");
		}
		if (alertPass) {
			vgP.clickBackToInventoryBtn(driver);
			ac.Wait(wt*2);
			igP.clickTemplatesBtn(driver);
			ac.Wait(wt*2);
			Templates tpP = new Templates(driver);
			tpP.clickInventoryGalleryBtn(driver);
			ac.Wait(wt*2);
			igP.verifyLoadPreviewTileImage(driver, SINGLE_VIN_RENDER_MAX_WT, vin01, vehGUID01, 1, TCnum);
			// System.out.println("\nPlease wait at least 60 seconds, vin: "+vin01+" is re-rendering...\n");
			// ac.Wait(60);
		} else {
			driver = reLogin(driver, ac, tBrowser, envDevice, env, baseURL, accountEmail, accountPS);
		}
		TCnum = "TC139922_11";
		// igP.clickRefleshF5Btn(driver, TCnum);
		// TCnum = "TC139922_12";
		// ac.Wait(wt);
		// igP.clickSelectBtn(driver, vin01, vehGUID01, TCnum);
		// TCnum = "TC139922_13";
		// igP.clickRerenderBtn(driver, "Yes", TCnum);
		// igP.verifyLoadPreviewTileImage(driver, SINGLE_VIN_RENDER_MAX_WT, vin01, vehGUID01, 1, TCnum);
		TCnum = "TC139922_14";
		String imageGUIDString = vgP.getImageGUID(dealerCode, vin01, vehGUID01, tempDateTime, serverName, dbName,
				userName, password);
		TCnum = "TC139497";
		igP.clickViewDetailsBtn(driver, vin01, vehGUID01, TCnum);
		// Window scroll down to make the custom image visible.
		igP.scrollUp(driver, 650, TCnum);
		vgP.clickPreviewBtn(driver, imageGUIDString, vin01, TCnum);
		TCnum = "TC139922_14_1";
		igP.scrollUp(driver, -650, TCnum);
		ac.Wait(wt);
		TCnum = "TC139922_14_2";
		vgP.verifyLoadPannelImage(driver, TCnum);
		TCnum = "TC139922_14_3";
		igP.scrollUp(driver, 650, TCnum);
		TCnum = "TC139922_14_4";
		// vgP.clickSelectImageBtn(driver, imageGUIDString, vehGUID01, TCnum);// Testing now. need to clear up the code
		TCnum = "TC139502_9_Debug";
		boolean newLink = true;
		if (newLink) {
			String parentHandle = driver.getWindowHandle(); // get the current window handle
			vgP.clickURLLink(driver, brw, TCnum);
			ac.Wait(wt);
			for (String winHandle : driver.getWindowHandles()) {
				driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
			}
			vgP.verifyLoadURLImage(driver, TCnum);
			driver.close();
			driver.switchTo().window(parentHandle); // switch back to the original window
		} else {
			driver.get(urlLink);
			urlLink = vgP.getURLLink(driver, TCnum);
			vgP.verifyLoadURLImage(driver, TCnum);
			ac.GoBack(driver);
		}
		TCnum = "TC139922_17";
		igP.scrollUp(driver, 650, TCnum);
		vgP.clickDeleteBtn(driver, imageGUIDString, vehGUID01, TCnum);// Testing now. need to clear up the code
		ac.Wait(wt);
		TCnum = "TC139922_18";
		try {
			driver.switchTo().alert().accept();
			ac.Wait(wt);
			driver.switchTo().alert().accept();
			// driver.switchTo().alert().dismiss();
			driver.switchTo().defaultContent();
			System.out.println("2. Accept the alert.");
			ac.rwExcel(TCnum, true, "Delete Custom Picture", "Alerts showing, accetped.");
		} catch (Throwable e) {
			System.out.println("2. Failed to Accept the alert.");
			ac.rwExcel(TCnum, false, "Delete Custom Picture", "Alerts not showing properly, failed to accetp.");
		}
		// If second browser opened
		if (!alertPass) {
			driver.close();
		}
	}

	public static void VINpxTemplatesTC(WebDriver driver, String brw, String versionNum, String envment,
			String checkEmail) throws Exception {

		// Load environment parameters
		Properties prop = new Properties();
		// testprop.load(new FileInputStream("data/autopxConf.properties"));
		try {
			prop.load(AUTOpxController.class.getClassLoader().getResourceAsStream("data/autopxConf.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String env = prop.getProperty("AUTOpx.environment");
		String envBrowser = prop.getProperty("AUTOpx.browser");
		String render = prop.getProperty("AUTOpx.render");
		String addNewVIN = prop.getProperty("AUTOpx.addNewVIN");
		String overlayImage = prop.getProperty("AUTOpx.overlayImagePathFile");
		String accountEmail = prop.getProperty(env + ".VINpxEmail");
		String accountPS = prop.getProperty(env + ".VINpxPassword");
		// String baseURL = prop.getProperty(env + ".VINpxDealerPortalBaseURL");
		String dealershipName = prop.getProperty(env + ".VINpxDealershipname");
		String VINpxDealershipBrandName = prop.getProperty(env + ".VINpxDealershipBrandName");
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
		int MaxTimeForTemplatesPreview = Integer.parseInt(prop.getProperty(env + ".MaxTimeForTemplatesPreview"));
		String displayTemplatesStatusOnPage = prop.getProperty("AUTOpx.displayTemplatesStatusOnPage");
		int wt = Integer.parseInt(prop.getProperty("AUTOpx.waitTime"));
		// Initial
		// final int wt_Secs = 6;
		String TCnum;
		// String ptitle;
		String tempVIN = "";
		String tempVehGUID = "";
		Comlibs ac = new Comlibs();
		ac.rwExcel("", "*********VINpx Inventory TCs**********", "");
		AUTOpxLogin loginP = new AUTOpxLogin(driver);
		TCnum = "TC139659_7_vinpx";
		loginP.verifyHeaderFooter(env, versionNum, TCnum);
		TCnum = "TC141679_6_vinpx";

		loginP.login(driver, accountEmail, accountPS);
		AcceptLicenseAgreementtoContinue acceptLicenseP = new AcceptLicenseAgreementtoContinue(driver);
		acceptLicenseP.clickCancelBtn(driver);

		loginP.login(driver, accountEmail, accountPS);
		// AcceptLicenseAgreementtoContinue acceptLicenseP = new AcceptLicenseAgreementtoContinue(driver);

		TCnum = "TC144867_vinpx";
		// acceptLicenseP.verifyAgreementTitle(driver, 1, "VINpx Agreement", true, TCnum); // VINpx Agreement, STOCKpx Agreement, LOTpx Agreement

		vinpxConent = acceptLicenseP.getFile("data/vinpxAgreement.txt");
		int vinpxAgreementTotalLines = acceptLicenseP.getLineNum();
		TCnum = "TC144867_5_vinpx";
		// acceptLicenseP.verifyPDF(driver, 1, "vinpx", vinpxConent, vinpxAgreementTotalLines, TCnum, envment);

		acceptLicenseP.clickAcceptBtn(driver);
		ImageGallery igP = new ImageGallery(driver);
		igP.clickDealerShipInfoBtn(driver);
		DealerProfile dpP = new DealerProfile(driver);
		dpP.selectBand(driver, VINpxDealershipBrandName);

		igP.clickTemplatesBtn(driver);
		Templates tpP = new Templates(driver);

		TCnum = "";
		String checkboxName = "";
		String checkboxSelectorID = "";
		String checkboxCSSstyleID = "transform-origin";
		String checkboxCSSstyleValue = "50% 50%";
		boolean checkboxIsChecked = false;
		String successfulMsg = "";
		boolean MessageExist = false;
		// *************************1. Header**************************************************************************
		// // Header
		// boolean set_Header = true;
		// boolean set_Header_DealershipLogo = true;
		// boolean set_Header_DealershipAddress = false;
		// boolean set_Header_DealershipPhone = true;
		// boolean set_Header_DealershipEmail = false;
		// boolean set_Header_DealershipWebsite = true;
		//
		// boolean set_Footer = true;
		// boolean set_Footer_BrandLog = false;
		// boolean set_Footer_VehicleInfo = true;
		// boolean set_Footer_Vin = false;
		// boolean set_Footer_StockNumber = false;
		//
		// boolean set_MarketingMessageTop = false;
		// boolean set_MarketingMessageBotton = true;
		// boolean set_AddAdditionalOverlay = false;
		//
		// boolean set_TextImage_VDI = false;
		// boolean set_TextImage_WCI = true;
		// boolean set_TextImage_VBI = false;

		// Header with random true or false
		boolean set_Header = true;
		boolean set_Header_DealershipLogo = igP.truefalseRandom();
		boolean set_Header_DealershipAddress = igP.truefalseRandom();
		boolean set_Header_DealershipPhone = igP.truefalseRandom();
		boolean set_Header_DealershipEmail = igP.truefalseRandom();
		boolean set_Header_DealershipWebsite = igP.truefalseRandom();

		boolean set_Footer = true;
		boolean set_Footer_BrandLog = igP.truefalseRandom();
		boolean set_Footer_VehicleInfo = igP.truefalseRandom();
		boolean set_Footer_Vin = igP.truefalseRandom();
		boolean set_Footer_StockNumber = igP.truefalseRandom();

		boolean set_MarketingMessageTop = igP.truefalseRandom();
		boolean set_MarketingMessageBotton = igP.truefalseRandom();
		boolean set_AddAdditionalOverlay = false;

		boolean set_TextImage_VDI = igP.truefalseRandom();
		boolean set_TextImage_WCI = igP.truefalseRandom();
		boolean set_TextImage_VBI = igP.truefalseRandom();
		/**
		 * second run set_Header=true; set_Header_DealershipLogo=false; set_Header_DealershipAddress=true; set_Header_DealershipPhone=false; set_Header_DealershipEmail=true; set_Header_DealershipWebsite=false;
		 * 
		 * 
		 * set_Footer=true; set_Footer_BrandLog=true; set_Footer_VehicleInfo=false; set_Footer_Vin=false; set_Footer_StockNumber=false;
		 * 
		 * set_MarketingMessageTop=false; set_MarketingMessageBotton=true; set_AddAdditionalOverlay=false;
		 * 
		 * set_TextImage_VDI=true; set_TextImage_WCI=false; set_TextImage_VBI=true;
		 **/

		// try {
		// tpP.clickHeaderEditBtn(driver);
		// } catch (Exception e) {
		// System.out.println(" Header checkbox is not checked, click again to check it! ");
		// tpP.clickHeaderCheckBox(driver);
		// tpP.clickSaveBtn(driver);
		// tpP.clickHeaderEditBtn(driver);
		// }
		for (int i = 1; i <= 4; i++) {
			// 1.Header checkbox;
			TCnum = i + ": " + "Header Checkbox";
			checkboxName = "HeaderCheckbox";
			checkboxSelectorID = "#templateBuilder > div.col-lg-3.col-md-5.col-sm-12.col-xs-12 > label:nth-child(1) > span";
			checkboxIsChecked = tpP.checkboxWithPseudoElement(checkboxName, driver, set_Header, false,
					checkboxSelectorID, checkboxCSSstyleID, checkboxCSSstyleValue, TCnum);
			if (!checkboxIsChecked) {
				tpP.clickHeaderCheckBox(driver);
				checkboxIsChecked = tpP.checkboxWithPseudoElement(checkboxName, driver, set_Header, true,
						checkboxSelectorID, checkboxCSSstyleID, checkboxCSSstyleValue, TCnum);
				if (!checkboxIsChecked) {
					// Failed;
					System.out.println("Failed to click the checkbox -" + checkboxName);
				}
			}
			if (set_Header) {
				tpP.clickHeaderEditBtn(driver);
				ac.Wait(wt);
				// Header Content checkboxes;
				TCnum = i + ": " + "1.1. Check the checkbox for DealershipLogo";
				checkboxName = "DealershipLogo";
				checkboxSelectorID = "#headerContent > label:nth-child(4) > span";
				checkboxIsChecked = tpP.checkboxWithPseudoElement(checkboxName, driver, set_Header_DealershipLogo,
						false, checkboxSelectorID, checkboxCSSstyleID, checkboxCSSstyleValue, TCnum);
				if (!checkboxIsChecked) {
					tpP.clickDealershipLogoCheckBox(driver);
					checkboxIsChecked = tpP.checkboxWithPseudoElement(checkboxName, driver, set_Header_DealershipLogo,
							true, checkboxSelectorID, checkboxCSSstyleID, checkboxCSSstyleValue, TCnum);
					if (!checkboxIsChecked) {
						// Failed;
						System.out.println("Failed to click the checkbox -" + checkboxName);

					}
				}

				TCnum = i + ": " + "1.2. Check the checkbox for DealershipAddress";
				checkboxName = "DealershipAddress";
				checkboxSelectorID = "#headerContent > label:nth-child(6) > span";
				checkboxIsChecked = tpP.checkboxWithPseudoElement(checkboxName, driver, set_Header_DealershipAddress,
						false, checkboxSelectorID, checkboxCSSstyleID, checkboxCSSstyleValue, TCnum);
				if (!checkboxIsChecked) {
					tpP.clickDealershipAddressCheckBox(driver);
					checkboxIsChecked = tpP.checkboxWithPseudoElement(checkboxName, driver,
							set_Header_DealershipAddress, true, checkboxSelectorID, checkboxCSSstyleID,
							checkboxCSSstyleValue, TCnum);
					if (!checkboxIsChecked) {
						// Failed;
						System.out.println("Failed to click the checkbox -" + checkboxName);

					}
				}

				TCnum = i + ": " + "1.3. Check the checkbox for DealershipPhone";
				checkboxName = "DealershipPhone";
				checkboxSelectorID = "#headerContent > label:nth-child(8) > span";
				checkboxIsChecked = tpP.checkboxWithPseudoElement(checkboxName, driver, set_Header_DealershipPhone,
						false, checkboxSelectorID, checkboxCSSstyleID, checkboxCSSstyleValue, TCnum);
				if (!checkboxIsChecked) {
					tpP.clickDealershipPhoneCheckBox(driver);
					checkboxIsChecked = tpP.checkboxWithPseudoElement(checkboxName, driver, set_Header_DealershipPhone,
							true, checkboxSelectorID, checkboxCSSstyleID, checkboxCSSstyleValue, TCnum);
					if (!checkboxIsChecked) {
						// Failed;
						System.out.println("Failed to click the checkbox -" + checkboxName);

					}
				}

				TCnum = i + ": " + "1.4. Check the checkbox for DealershipEmail";
				checkboxName = "DealershipEmail";
				checkboxSelectorID = "#headerContent > label:nth-child(10) > span";
				checkboxIsChecked = tpP.checkboxWithPseudoElement(checkboxName, driver, set_Header_DealershipEmail,
						false, checkboxSelectorID, checkboxCSSstyleID, checkboxCSSstyleValue, TCnum);
				if (!checkboxIsChecked) {
					tpP.clickDealershipEmailCheckBox(driver);
					checkboxIsChecked = tpP.checkboxWithPseudoElement(checkboxName, driver, set_Header_DealershipEmail,
							true, checkboxSelectorID, checkboxCSSstyleID, checkboxCSSstyleValue, TCnum);
					if (!checkboxIsChecked) {
						// Failed;
						System.out.println("Failed to click the checkbox -" + checkboxName);

					}
				}

				TCnum = i + ": " + "1.5. Check the checkbox for DealershipWebsite";
				checkboxName = "DealershipWebsite";
				checkboxSelectorID = "#headerContent > label:nth-child(12) > span";
				checkboxIsChecked = tpP.checkboxWithPseudoElement(checkboxName, driver, set_Header_DealershipWebsite,
						false, checkboxSelectorID, checkboxCSSstyleID, checkboxCSSstyleValue, TCnum);
				if (!checkboxIsChecked) {
					tpP.clickDealershipWebsiteCheckBox(driver);
					checkboxIsChecked = tpP.checkboxWithPseudoElement(checkboxName, driver,
							set_Header_DealershipWebsite, true, checkboxSelectorID, checkboxCSSstyleID,
							checkboxCSSstyleValue, TCnum);
					if (!checkboxIsChecked) {
						// Failed;
						System.out.println("Failed to click the checkbox -" + checkboxName);

					}
				}
				tpP.clickContentHeaderSaveBtn(driver);
				ac.Wait(wt);
				TCnum = i + ": " + "Templates-Click SAVE on Header Content box";
				successfulMsg = "Your settings have been saved";// "Your settings have been saved"
				MessageExist = tpP.checkMessageDisplayedHead(driver, successfulMsg, TCnum);
				if (MessageExist) {
					ac.rwExcel(TCnum, true, "Click on SAVE in Header Content box",
							"Sucessful msg shows: Your settings have been saved");
				} else {
					ac.rwExcel(TCnum, false, "Click on SAVE in Header Content box",
							"Failed to shows msg: Your settings have been saved.");
				}

				tpP.clickHeaderXBtn(driver);
			}
			// *************************End of 1. Header**************************************************************************

			// *************************2. Footer**************************************************************************
			// Footer
			// try {
			// tpP.clickHeaderEditBtn(driver);
			// } catch (Exception e) {
			// System.out.println(" Footer checkbox is not checked, click again to check it! ");
			// tpP.clickFooterCheckBox(driver);
			// tpP.clickSaveBtn(driver);
			// tpP.clickFooterEditBtn(driver);
			// }

			// 2.Footer checkbox;
			TCnum = i + ": " + "Footer Checkbox";
			checkboxName = "FooterCheckbox";
			checkboxSelectorID = "#templateBuilder > div.col-lg-3.col-md-5.col-sm-12.col-xs-12 > label:nth-child(4) > span";
			checkboxIsChecked = tpP.checkboxWithPseudoElement(checkboxName, driver, set_Footer, false,
					checkboxSelectorID, checkboxCSSstyleID, checkboxCSSstyleValue, TCnum);
			if (!checkboxIsChecked) {
				tpP.clickFooterCheckBox(driver);
				checkboxIsChecked = tpP.checkboxWithPseudoElement(checkboxName, driver, set_Footer, true,
						checkboxSelectorID, checkboxCSSstyleID, checkboxCSSstyleValue, TCnum);
				if (!checkboxIsChecked) {
					// Failed;
					System.out.println("Failed to click the checkbox -" + checkboxName);
				}
			}
			if (set_Footer) {
				tpP.clickFooterEditBtn(driver);
				ac.Wait(wt);
				// Footer Content checkboxes;
				TCnum = i + ": " + "2.1. Check the checkbox for BrandLogo";
				checkboxName = "BrandLogo";
				checkboxSelectorID = "#footerContent > label:nth-child(4) > span";
				checkboxIsChecked = tpP.checkboxWithPseudoElement(checkboxName, driver, set_Footer_BrandLog, false,
						checkboxSelectorID, checkboxCSSstyleID, checkboxCSSstyleValue, TCnum);
				if (!checkboxIsChecked) {
					tpP.clickBrandLogoCheckBox(driver);
					checkboxIsChecked = tpP.checkboxWithPseudoElement(checkboxName, driver, set_Footer_BrandLog, true,
							checkboxSelectorID, checkboxCSSstyleID, checkboxCSSstyleValue, TCnum);
					if (!checkboxIsChecked) {
						// Failed;
						System.out.println("Failed to click the checkbox -" + checkboxName);

					}
				}

				TCnum = i + ": " + "2.2. Check the checkbox for VehicleInfo";
				checkboxName = "VehicleInfo";
				checkboxSelectorID = "#vehInfoLabel > span";
				checkboxIsChecked = tpP.checkboxWithPseudoElement(checkboxName, driver, set_Footer_VehicleInfo, false,
						checkboxSelectorID, checkboxCSSstyleID, checkboxCSSstyleValue, TCnum);
				if (!checkboxIsChecked) {
					tpP.clickVehicleInfoCheckBox(driver);
					checkboxIsChecked = tpP.checkboxWithPseudoElement(checkboxName, driver, set_Footer_VehicleInfo,
							true, checkboxSelectorID, checkboxCSSstyleID, checkboxCSSstyleValue, TCnum);
					if (!checkboxIsChecked) {
						// Failed;
						System.out.println("Failed to click the checkbox -" + checkboxName);

					}
				}

				TCnum = i + ": " + "2.3. Check the checkbox for VIN";
				checkboxName = "VIN";
				checkboxSelectorID = "#vinLabel > span";
				checkboxIsChecked = tpP.checkboxWithPseudoElement(checkboxName, driver, set_Footer_Vin, false,
						checkboxSelectorID, checkboxCSSstyleID, checkboxCSSstyleValue, TCnum);
				if (!checkboxIsChecked) {
					tpP.clickVINCheckBox(driver);
					checkboxIsChecked = tpP.checkboxWithPseudoElement(checkboxName, driver, set_Footer_Vin, true,
							checkboxSelectorID, checkboxCSSstyleID, checkboxCSSstyleValue, TCnum);
					if (!checkboxIsChecked) {
						// Failed;
						System.out.println("Failed to click the checkbox -" + checkboxName);

					}
				}

				TCnum = i + ": " + "2.4. Check the checkbox for StockNumber";
				checkboxName = "StockNumber";
				checkboxSelectorID = "#stockLabel > span";
				checkboxIsChecked = tpP.checkboxWithPseudoElement(checkboxName, driver, set_Footer_StockNumber, false,
						checkboxSelectorID, checkboxCSSstyleID, checkboxCSSstyleValue, TCnum);
				if (!checkboxIsChecked) {
					tpP.clickStockNumberCheckBox(driver);
					checkboxIsChecked = tpP.checkboxWithPseudoElement(checkboxName, driver, set_Footer_StockNumber,
							true, checkboxSelectorID, checkboxCSSstyleID, checkboxCSSstyleValue, TCnum);
					if (!checkboxIsChecked) {
						// Failed;
						System.out.println("Failed to click the checkbox -" + checkboxName);

					}
				}
				tpP.clickContentFooterSaveBtn(driver);
				ac.Wait(wt);
				TCnum = i + ": " + "Templates-Click SAVE on Footer Content box";
				successfulMsg = "Your settings have been saved";// "Your settings have been saved"
				MessageExist = tpP.checkMessageDisplayedHead(driver, successfulMsg, TCnum);
				if (MessageExist) {
					ac.rwExcel(TCnum, true, "Click on SAVE in Footer Content box",
							"Sucessful msg shows: Your settings have been saved");
				} else {
					ac.rwExcel(TCnum, false, "Click on SAVE in Footer Content box",
							"Failed to shows msg: Your settings have been saved.");
				}
				tpP.clickFooterXBtn(driver);
			}
			// *************************End of 2. Footer**************************************************************************

			// *************************3.MarketingMessageTop checkbox;*************************
			TCnum = i + ": " + "MarketingMessageTop Checkbox";
			checkboxName = "MarketingMessageTop";
			checkboxSelectorID = "#templateBuilder > div.col-lg-3.col-md-5.col-sm-12.col-xs-12 > label:nth-child(7) > span";
			checkboxIsChecked = tpP.checkboxWithPseudoElement(checkboxName, driver, set_MarketingMessageTop, false,
					checkboxSelectorID, checkboxCSSstyleID, checkboxCSSstyleValue, TCnum);
			if (!checkboxIsChecked) {
				tpP.clickMarketingMessageTopCheckBox(driver);
				checkboxIsChecked = tpP.checkboxWithPseudoElement(checkboxName, driver, set_MarketingMessageTop, true,
						checkboxSelectorID, checkboxCSSstyleID, checkboxCSSstyleValue, TCnum);
				if (!checkboxIsChecked) {
					// Failed;
					System.out.println("Failed to click the checkbox -" + checkboxName);

				}
			}
			if (set_MarketingMessageTop) {
				// tpP.clickOverlayXBtn(driver);
				tpP.clickMarketingMessageTopEditBtn(driver);
				tpP.inputGlbMsgIntoMarketingMSGTopContentField(driver, "Top_Global Message!");
				tpP.clickContentOverlaySaveBtn(driver);
				ac.Wait(wt);
				tpP.clickOverlayXBtn(driver);
				ac.Wait(wt);
			}
			// *************************4.MarketingMessageBotton checkbox;*************************
			TCnum = i + ": " + "MarketingMessageBotton Checkbox";
			checkboxName = "MarketingMessageBotton";
			checkboxSelectorID = "#templateBuilder > div.col-lg-3.col-md-5.col-sm-12.col-xs-12 > label:nth-child(10) > span";
			checkboxIsChecked = tpP.checkboxWithPseudoElement(checkboxName, driver, set_MarketingMessageBotton, false,
					checkboxSelectorID, checkboxCSSstyleID, checkboxCSSstyleValue, TCnum);
			if (!checkboxIsChecked) {
				tpP.clickMarketingMessageBottomCheckBox(driver);// clickMarketingMessageBottomCheckBox
				checkboxIsChecked = tpP.checkboxWithPseudoElement(checkboxName, driver, set_MarketingMessageBotton,
						true, checkboxSelectorID, checkboxCSSstyleID, checkboxCSSstyleValue, TCnum);
				if (!checkboxIsChecked) {
					// Failed;
					System.out.println("Failed to click the checkbox -" + checkboxName);

				}
			}
			if (set_MarketingMessageBotton) {
				// tpP.clickOverlayXBtn(driver);
				tpP.clickMarketingMessageBottomEditBtn(driver);
				tpP.inputGlbMsgIntoMarketingMSGTopContentField(driver, "Bottom_Global Message!");
				tpP.clickContentOverlaySaveBtn(driver);
				ac.Wait(wt);
				tpP.clickOverlayXBtn(driver);
				ac.Wait(wt);
			}

			// ************************* 5.AddAdditionalOverlay checkbox;*************************
			TCnum = i + ": " + "AddAdditionalOverlay Checkbox";
			checkboxName = "AddAdditionalOverlay";
			checkboxSelectorID = "#templateBuilder > div.col-lg-3.col-md-5.col-sm-12.col-xs-12 > label:nth-child(14) > span";
			checkboxIsChecked = tpP.checkboxWithPseudoElement(checkboxName, driver, set_AddAdditionalOverlay, false,
					checkboxSelectorID, checkboxCSSstyleID, checkboxCSSstyleValue, TCnum);
			if (!checkboxIsChecked) {
				tpP.clickAddAdditionalOverlayCheckBox(driver, TCnum);// clickMarketingMessageBottomCheckBox
				checkboxIsChecked = tpP.checkboxWithPseudoElement(checkboxName, driver, set_AddAdditionalOverlay, true,
						checkboxSelectorID, checkboxCSSstyleID, checkboxCSSstyleValue, TCnum);
				if (!checkboxIsChecked) {
					// Failed;
					System.out.println("Failed to click the checkbox -" + checkboxName);

				}
			}
			if (set_AddAdditionalOverlay) {
				tpP.uploadOverlayPicture(driver, overlayImage, TCnum);
				tpP.verifyDealershipTemplate(driver, TCnum);
				tpP.clickXonDealershipTemplate(driver, TCnum);
				tpP.uploadOverlayPicture(driver, overlayImage, TCnum);
				tpP.verifyDealershipTemplate(driver, TCnum);
			}

			// ************************* 6.1 Features - VDI checkbox;*************************
			tpP.scrollUp(driver, 3000, TCnum);
			TCnum = i + ": " + "6.1 Check the checkbox for VDI";
			checkboxName = "VDI";
			checkboxSelectorID = "#main-container > div > div:nth-child(5) > div:nth-child(2) > label > span";
			checkboxIsChecked = tpP.checkboxWithPseudoElement(checkboxName, driver, set_TextImage_VDI, false,
					checkboxSelectorID, checkboxCSSstyleID, checkboxCSSstyleValue, TCnum);
			if (!checkboxIsChecked) {
				tpP.clickFeaturesVDICheckBox(driver);
				checkboxIsChecked = tpP.checkboxWithPseudoElement(checkboxName, driver, set_Footer_Vin, true,
						checkboxSelectorID, checkboxCSSstyleID, checkboxCSSstyleValue, TCnum);
				if (!checkboxIsChecked) {
					// Failed;
					System.out.println("Failed to click the checkbox -" + checkboxName);

				}
			}

			// ************************* 6.2 What's Cool - WCI checkbox;*************************
			TCnum = i + ": " + "6.2 Check the checkbox for WCI";
			checkboxName = "WCI";
			checkboxSelectorID = "#main-container > div > div:nth-child(5) > div:nth-child(3) > label > span";
			checkboxIsChecked = tpP.checkboxWithPseudoElement(checkboxName, driver, set_TextImage_WCI, false,
					checkboxSelectorID, checkboxCSSstyleID, checkboxCSSstyleValue, TCnum);
			if (!checkboxIsChecked) {
				tpP.clickWhatsCoolCheckBox(driver);
				checkboxIsChecked = tpP.checkboxWithPseudoElement(checkboxName, driver, set_Footer_Vin, true,
						checkboxSelectorID, checkboxCSSstyleID, checkboxCSSstyleValue, TCnum);
				if (!checkboxIsChecked) {
					// Failed;
					System.out.println("Failed to click the checkbox -" + checkboxName);

				}
			}

			// ************************* 6.3 Benefits - VBI checkbox;*************************
			TCnum = i + ": " + "6.3 Check the checkbox for VBI";
			checkboxName = "VBI";
			checkboxSelectorID = "#main-container > div > div:nth-child(5) > div:nth-child(4) > label > span";
			checkboxIsChecked = tpP.checkboxWithPseudoElement(checkboxName, driver, set_TextImage_VBI, false,
					checkboxSelectorID, checkboxCSSstyleID, checkboxCSSstyleValue, TCnum);
			if (!checkboxIsChecked) {
				tpP.clickBenefitsVBICheckBox(driver);
				checkboxIsChecked = tpP.checkboxWithPseudoElement(checkboxName, driver, set_Footer_Vin, true,
						checkboxSelectorID, checkboxCSSstyleID, checkboxCSSstyleValue, TCnum);
				if (!checkboxIsChecked) {
					// Failed;
					System.out.println("Failed to click the checkbox -" + checkboxName);

				}
			}

			ac.Wait(wt);
			tpP.scrollUp(driver, -3000, TCnum);
			TCnum = i + ": " + "Templates-Click the up right SAVE";
			tpP.clickSaveBtn(driver);
			successfulMsg = "Your settings have been saved";// "Your settings have been saved"
			MessageExist = tpP.checkMessageDisplayedHead(driver, successfulMsg, TCnum);
			if (MessageExist) {
				ac.rwExcel(TCnum, true, "Click on SAVE in Templates page",
						"Sucessful msg shows: Your settings have been saved");
			} else {
				ac.rwExcel(TCnum, false, "Click on SAVE in Templates page",
						"Failed to shows msg: Your settings have been saved.");
			}
			ac.Wait(wt);
			tpP.clickInventoryGalleryBtn(driver);

			// Verify Rerender Single Vehicle 01 for VINpx dealer
			TCnum = i + ": " + "TC139706_8";
			ac.Wait(wt);
			igP.enterTextInSearch(vin01);
			ac.Wait(wt);
			igP.verifyRerenderBtnStatus(driver, false, TCnum);
			// igP.verifyGenerateURLsBtnStatus(driver, false, TCnum);
			igP.clickSelectBtn(driver, vin01, vehGUID01, TCnum);
			igP.verifyRerenderBtnStatus(driver, true, TCnum);
			// igP.verifyGenerateURLsBtnStatus(driver, true, TCnum);
			ac.rwExcel("", "------ Single VIN re-rendering ------", "");
			igP.clickRerenderBtn(driver, render, TCnum);
			System.out.println("\n\n 1st time. Please check images once rendering is complete.....\n\n");
			ac.Wait(wt);
			igP.verifyGoodMsgShowing(driver, TCnum);
			igP.verifyLoadPreviewTileImage(driver, SINGLE_VIN_RENDER_MAX_WT, vin01, vehGUID01, 1, TCnum);
			igP.verifyRerenderBtnStatus(driver, true, TCnum);
			TCnum = i + ": " + "TC139706_9_vin02";
			igP.clickViewDetailsBtn(driver, vin01, vehGUID01, TCnum);
			VehicleGallery vgP = new VehicleGallery(driver);
			TCnum = i + ": " + "TC139706_10_vin02";
			vgP.verifyLoadPannelImage(driver, TCnum);
			String templatesStatus = "";
			int noRenderWT = 10;

			if (!render.equalsIgnoreCase("Yes")) {
				MaxTimeForTemplatesPreview = noRenderWT;
			}
			templatesStatus = "<p style=\"color:red;LINE-HEIGHT:6px;font-size:10px;\">HEADER=" + set_Header
					+ ": ------------------------------------------------------------------------------------------"
					+ MaxTimeForTemplatesPreview
					+ " seconds to check---------------------------------------------</p><p style=\"color:blue;LINE-HEIGHT:6px;font-size:10px;\">1.Logo="
					+ set_Header_DealershipLogo + ", 2.Address=" + set_Header_DealershipAddress + ",3.Phone="
					+ set_Header_DealershipPhone + ", 4.Email=" + set_Header_DealershipEmail + ", 5.Website="
					+ set_Header_DealershipWebsite
					+ ".</p>   <p style=\"color:red;LINE-HEIGHT:6px;font-size:10px;\"> FOOTER=" + set_Footer
					+ ": </p><p style=\"color:blue;LINE-HEIGHT:6px;font-size:10px;\">1.BrandLogo=" + set_Footer_BrandLog
					+ ", 2.VehicleInfo=" + set_Footer_VehicleInfo + ", 3.VIN=" + set_Footer_Vin + ", 4.StockNumber="
					+ set_Footer_StockNumber + ". MsgTop=" + set_MarketingMessageTop + ", MsgBotton="
					+ set_MarketingMessageBotton + ", Overlay=" + set_AddAdditionalOverlay + ", VDI="
					+ set_TextImage_VDI + ", WCI=" + set_TextImage_WCI + ", VBI=" + set_TextImage_VBI + "</p>";
			// String templatesStatus="Checking: HEADER:"+set_Header + ", 1.DealershipLogo=" + set_Header_DealershipLogo+", 2.DealershipAddress=" + set_Header_DealershipAddress + ",3.DealershipPhone="+set_Header_DealershipPhone + ", 4.DealershipEmail=" + set_Header_DealershipEmail+", 5.DealershipWebsite=" + set_Header_DealershipWebsite + ". FOOTER: "+set_Footer + ", 1.BrandLogo=" +set_Footer_BrandLog + ", 2.VehicleInfo="+set_Footer_VehicleInfo + ", 3.VIN=" + set_Footer_Vin+", 4.set_Footer_StockNumber=" + set_Footer_StockNumber+", MarketingMessageTop=" + set_MarketingMessageTop + ",MarketingMessageBotton="+set_MarketingMessageBotton + ", AddAdditionalOverlay=" +
			// set_AddAdditionalOverlay+", Text Images: VDI=" + set_TextImage_VDI + ", WCI=" + set_TextImage_WCI+", VBI=" + set_TextImage_VBI +"";
			vgP.textDisplayOnPage(driver, templatesStatus, displayTemplatesStatusOnPage);
			tpP.scrollUp(driver, 1000, TCnum);
			ac.Wait(wt * 2);
			tpP.scrollUp(driver, -1000, TCnum);
			if (render.equalsIgnoreCase("Yes")) {
				System.out.println(
						"\nVIN #" + i + ". Waiting for " + MaxTimeForTemplatesPreview + ", check Templates settings");
				// ac.Wait(MaxTimeForTemplatesPreview, true,
				// "\nVIN #1. Waiting for 60 seconds, please check Templates settings:\n\nHeader:\nDealership Logo, Dealership Phone, Dealership Website, \nFooter:\n+Vehicle Info (year, make, model, trim, Glb MSG Top, Text Images:WCI for vin: "
				// + vin01 + "\n");
				ac.Wait(MaxTimeForTemplatesPreview, true, "\nVIN #" + i + ". Waiting for " + MaxTimeForTemplatesPreview
						+ " seconds, please check Templates settings:\n\nHeader: " + set_Header + "\n1.DealershipLogo="
						+ set_Header_DealershipLogo + ", \n2.DealershipAddress=" + set_Header_DealershipAddress
						+ ", \n3.DealershipPhone=" + set_Header_DealershipPhone + ", \n4.DealershipEmail="
						+ set_Header_DealershipEmail + ", \n5.DealershipWebsite=" + set_Header_DealershipWebsite
						+ "\n\nFooter: " + set_Footer + "\n1.BrandLogo=" + set_Footer_BrandLog + ", \n2.VehicleInfo="
						+ set_Footer_VehicleInfo + ", \n3.VIN=" + set_Footer_Vin + ", \n4.set_Footer_StockNumber="
						+ set_Footer_StockNumber + ", \n\nMarketingMessageTop=" + set_MarketingMessageTop
						+ ", \nMarketingMessageBotton=" + set_MarketingMessageBotton + ", \nAddAdditionalOverlay="
						+ set_AddAdditionalOverlay + ", \n\nText Images: \nVDI=" + set_TextImage_VDI + ",  \nWCI="
						+ set_TextImage_WCI + ",  \nVBI=" + set_TextImage_VBI + ",  for vin=" + vin01 + "\n");
				// System.out.println("\n1. press Enter key to continue......");
			} else {
				System.out.println("\nVIN #\"+i+\". Waiting for " + noRenderWT + ", check Templates settings");
				// ac.Wait(10, true,
				// "\nVIN #1. Waiting for 0 seconds, please check Templates settings:\n\nHeader:\nDealership Logo, Dealership Phone, Dealership Website, \nFooter:\n+Vehicle Info (year, make, model, trim, Glb MSG Top, Text Images:WCI for vin: "
				// + vin01 + "\n");
				ac.Wait(noRenderWT, true, "\nVIN #" + i + ". Waiting for " + noRenderWT
						+ " seconds, please check Templates settings:\n\nHeader: " + set_Header + "\n1.DealershipLogo="
						+ set_Header_DealershipLogo + ", \n2.DealershipAddress=" + set_Header_DealershipAddress
						+ ", \n3.DealershipPhone=" + set_Header_DealershipPhone + ", \n4.DealershipEmail="
						+ set_Header_DealershipEmail + ", \n5.DealershipWebsite=" + set_Header_DealershipWebsite
						+ "\n\nFooter: " + set_Footer + "\n1.BrandLogo=" + set_Footer_BrandLog + ", \n2.VehicleInfo="
						+ set_Footer_VehicleInfo + ", \n3.VIN=" + set_Footer_Vin + ", \n4.set_Footer_StockNumber="
						+ set_Footer_StockNumber + ", \n\nMarketingMessageTop=" + set_MarketingMessageTop
						+ ", \nMarketingMessageBotton=" + set_MarketingMessageBotton + ", \nAddAdditionalOverlay="
						+ set_AddAdditionalOverlay + ", \n\nText Images: \nVDI=" + set_TextImage_VDI + ",  \nWCI="
						+ set_TextImage_WCI + ",  \nVBI=" + set_TextImage_VBI + ",  for vin=" + vin01 + "\n");
			}
			vgP.clickBackToInventoryBtn(driver);
			ac.Wait(wt);
			// *************************2nd**************************************************************************
			igP.clickTemplatesBtn(driver);
			ac.Wait(wt);
			if (i == 1) {
				set_Header = true;
				set_Header_DealershipLogo = false;
				set_Header_DealershipAddress = true;
				set_Header_DealershipPhone = false;
				set_Header_DealershipEmail = true;
				set_Header_DealershipWebsite = false;

				set_Footer = true;
				set_Footer_BrandLog = true;
				set_Footer_VehicleInfo = false;
				set_Footer_Vin = true;
				set_Footer_StockNumber = true;

				set_MarketingMessageTop = true;
				set_MarketingMessageBotton = false;
				set_AddAdditionalOverlay = true;

				set_TextImage_VDI = true;
				set_TextImage_WCI = false;
				set_TextImage_VBI = true;
			} else {
				set_Header = false;
				set_Header_DealershipLogo = false;
				set_Header_DealershipAddress = true;
				set_Header_DealershipPhone = false;
				set_Header_DealershipEmail = true;
				set_Header_DealershipWebsite = false;

				set_Footer = false;
				set_Footer_BrandLog = true;
				set_Footer_VehicleInfo = false;
				set_Footer_Vin = true;
				set_Footer_StockNumber = true;

				set_MarketingMessageTop = false;
				set_MarketingMessageBotton = false;
				set_AddAdditionalOverlay = false;

				set_TextImage_VDI = true;
				set_TextImage_WCI = true;
				set_TextImage_VBI = true;

			}
			if (i == 3) {// Re-assignment - No Header no Footer
				set_Header = true;
				set_Header_DealershipLogo = true;
				set_Header_DealershipAddress = false;
				set_Header_DealershipPhone = true;
				set_Header_DealershipEmail = false;
				set_Header_DealershipWebsite = true;

				set_Footer = true;
				set_Footer_BrandLog = true;
				set_Footer_VehicleInfo = true;
				set_Footer_Vin = true;
				set_Footer_StockNumber = true;

				set_MarketingMessageTop = true;
				set_MarketingMessageBotton = false;
				set_AddAdditionalOverlay = false;

				set_TextImage_VDI = true;
				set_TextImage_WCI = true;
				set_TextImage_VBI = true;
			}

		}

		TCnum = "TC139684_06";
		igP.clickLogout(driver);
	}

	private static String[] fetchOneDemArrayFromPropFile(String propertyName, Properties propFile) {

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

	public static void AddAllVINs(WebDriver driver, String brw, String envment)
			throws IOException, InterruptedException, ClassNotFoundException, SQLException {

		// Load environment parameters
		Properties prop = new Properties();
		// testprop.load(new FileInputStream("data/autopxConf.properties"));
		try {
			prop.load(AUTOpxController.class.getClassLoader().getResourceAsStream("data/autopxConf.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String env = prop.getProperty("AUTOpx.environment");
		String envDevice = prop.getProperty("AUTOpx.envDevice");
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
		String VINpxDealershipBrandName = prop.getProperty(env + ".VINpxDealershipBrandName");
		int wt = Integer.parseInt(prop.getProperty("AUTOpx.waitTime"));
		String TCnum;
		String tempVIN = "";
		String tempVehGUID = "";
		Comlibs ac = new Comlibs();
		ac.rwExcel("", "*********Add VINs**********", "");
		AUTOpxLogin loginP = new AUTOpxLogin(driver);
		TCnum = "";
		loginP.login(driver, accountEmail, accountPS);
		AcceptLicenseAgreementtoContinue acceptLicenseP = new AcceptLicenseAgreementtoContinue(driver);
		
		acceptLicenseP.clickAcceptBtn(driver);
		ImageGallery igP = new ImageGallery(driver);
		igP.clickDealerShipInfoBtn(driver);
		DealerProfile dpP = new DealerProfile(driver);
		dpP.selectBand(driver, VINpxDealershipBrandName);
		dpP.clickInventoryGalleryBtn(driver, TCnum);
		
		// *********************Add VIN *******************************************************
		// Add VIN

		TCnum = "Add VIN";
		igP.clickAddInventoryBtn(driver, TCnum);
		igP.clickAddInventoryCancelBtn(driver, TCnum);
		ac.Wait(wt);
		igP.clickAddInventoryBtn(driver, TCnum);
		igP.clickAddInventoryXBtn(driver, TCnum);
		ac.Wait(wt);
		igP.clickAddInventoryBtn(driver, TCnum);
		int total = VINpxNewVINs.length;
		ac.rwExcel("", "VINs Total=" + total, "");
		int c = 0;
		for (String vin : VINpxNewVINs) {
			c++;
			igP.inputVinInAddInventoryField(driver, vin, TCnum);
			igP.clickAddBtn(driver, TCnum);
			if (igP.verifyGoodOrBadMsgShowing(driver, TCnum)) {
				// good msg shows
				tempVIN = vin;
				ac.rwExcel("VIN#= " + c, true, "VIN= " + vin,
						"VIN added, good message is showing under My Inventory Gallery");
				// break;
			} else {
				// error msg shows
				tempVIN = vin;
				ac.rwExcel("VIN#= " + c, false, "VIN= " + vin, "error message is showing under My Inventory Gallery");

			}
		}
		igP.clickAddInventoryXBtn(driver, TCnum);
		igP.clickRefleshF5Btn(driver, TCnum);
		// ********************End of Add VIN********************************************************
		igP.clickLogout(driver);
	}

	public static void tempDebug(WebDriver driver)
			throws IOException, InterruptedException, ClassNotFoundException, SQLException, AWTException {

		// Load environment parameters
		Properties prop = new Properties();
		Properties propdata = new Properties();
		// testprop.load(new FileInputStream("data/autopxConf.properties"));
		try {
			prop.load(AUTOpxController.class.getClassLoader().getResourceAsStream("data/autopxConf.properties"));
			// propdata.load(AUTOpxController.class.getClassLoader().getResourceAsStream("data/vinpxAgreement.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String env = prop.getProperty("AUTOpx.environment");
		String envDevice = prop.getProperty("AUTOpx.envDevice");
		String render = prop.getProperty("AUTOpx.render");
		String img = prop.getProperty("AUTOpx.customPicPathFile");
		String tBrowser = prop.getProperty("AUTOpx.browser");
		String accountEmail = prop.getProperty(env + ".AllProdEmail");
		String accountPS = prop.getProperty(env + ".AllProdPassword");
		// String baseURL = prop.getProperty(env + ".VINpxDealerPortalBaseURL");
		String dealershipName = prop.getProperty(env + ".Dealershipname");
		String dealerCode = prop.getProperty(env + ".AllProdDealerCode");
		String vin01 = prop.getProperty(env + ".Vin01");
		String vin02 = prop.getProperty(env + ".Vin02");
		String vehGUID01 = prop.getProperty(env + ".Vin01GUID");
		String vehGUID02 = prop.getProperty(env + ".Vin02GUID");
		// String vinpxnewVin01 = prop.getProperty(env + ".VINpxNewVIN01");
		// String[] VINpxNewVINs = fetchOneDemArrayFromPropFile(env + ".VINpxNewVINs", prop);
		String serverName = prop.getProperty(env + ".serverName");
		String dbName = prop.getProperty(env + ".dbName");
		String userName = prop.getProperty(env + ".userName");
		String password = prop.getProperty(env + ".password");
		String baseURL = prop.getProperty(env + ".DealerPortalBaseURL");

		// ************************************************************

		// Load dealer profile settings
		Properties profileProp = new Properties();

		try {
			profileProp.load(AUTOpxController.class.getClassLoader()
					.getResourceAsStream("data/dealerProfileSettings.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String DealershipIDBrands = profileProp.getProperty(env + ".DealershipIDBrands");
		String dealershipNameP = profileProp.getProperty(env + ".dealershipName");// same in autopxConf.properties
		String address = profileProp.getProperty(env + ".address");
		String city = profileProp.getProperty(env + ".city");
		String country = profileProp.getProperty(env + ".country");
		String stateProvince = profileProp.getProperty(env + ".stateProvince");
		String zipPostalCode = profileProp.getProperty(env + ".zipPostalCode");
		String accountEmailP = profileProp.getProperty(env + ".accountEmail");
		String fisrtName = profileProp.getProperty(env + ".fisrtName");// same in autopxConf.properties
		String lastName = profileProp.getProperty(env + ".lastName");
		String dealershipEmail = profileProp.getProperty(env + ".dealershipEmail");
		String dealershipPhone = profileProp.getProperty(env + ".dealershipPhone");
		String website = profileProp.getProperty(env + ".website");
		String tagLine = profileProp.getProperty(env + ".tagLine");

		String globalMarketingMsg = profileProp.getProperty(env + ".globalMarketingMsg");
		String passwordP = profileProp.getProperty(env + ".password");// same in autopxConf.properties
		String confirmPassword = profileProp.getProperty(env + ".confirmPassword");
		boolean vinpx = Boolean.parseBoolean(profileProp.getProperty(env + ".vinpx"));
		boolean lotpx = Boolean.parseBoolean(profileProp.getProperty(env + ".lotpx"));
		boolean stockpx = Boolean.parseBoolean(profileProp.getProperty(env + ".stockpx"));
		boolean receiveDailyInventoryEmail = Boolean
				.parseBoolean(profileProp.getProperty(env + ".receiveDailyInventoryEmail"));

		String[] dealershipBackgrounds = fetchOneDemArrayFromPropFile(env + ".dealershipBackground", profileProp);
		String dealershipBackgroundSelected = profileProp.getProperty(env + ".dealershipBackgroundSelected");

		// *************************************************************

		// Initial
		// final int wt_Secs = 6;
		String TCnum = "Debug01";
		// String ptitle;
		String tempVIN = "";
		String tempVehGUID = "";
		int allVinNums = 0;
		Comlibs ac = new Comlibs();
		ac.rwExcel("", "*********VINpx Inventory TCs**********", "");
		AUTOpxLogin loginP = new AUTOpxLogin(driver);

		// **********************************Debug Area**************************************************************
		// **********************************Debug Area**************************************************************

		loginP.login(driver, accountEmail, accountPS);
		AcceptLicenseAgreementtoContinue acceptLicenseP = new AcceptLicenseAgreementtoContinue(driver);

		acceptLicenseP.clickAcceptBtn(driver);
		ImageGallery igP = new ImageGallery(driver);
		TCnum = "TC_Debug_01";
		igP.gotoMyDealerShip(driver);
		DealerProfile dpP = new DealerProfile(driver);
		// TCnum = "TC139666_13_14";
		// dpP.verifyCountryStateDropDowns(driver, country,stateProvince, TCnum);
		// TCnum = "TC139666_20";
		// dpP.verifyProds(driver, vinpx, lotpx, stockpx, TCnum);
		// TCnum = "TC139666_26";
		// dpP.verifyReceiveEailCheckBoxSetting(driver, receiveDailyInventoryEmail, TCnum);
		// TCnum = "TC139666_27_28";
		// dpP.verifyTitleOfDealershipBackground(driver, dealershipBackgrounds, dealershipBackgroundSelected, TCnum);
		dpP.jSONParse();

		// igP.enterTextInSearch(vin01);
		// igP.clickViewDetailsBtn(driver, vin01, vehGUID01, TCnum);
		// VehicleGallery vgP = new VehicleGallery(driver);

		System.out.println("This test is complete in the tempDebug area!!!");

		// // ********************************End of Debug Area*****************************************************************
		// // ********************************End of Debug Area*****************************************************************
	}

	public static WebDriver reLogin(WebDriver driver, Comlibs bc, String tBrowser, String envDevice, String env,
			String baseURL, String accountEmail, String accountPS) throws IOException {
		driver = bc.drivers(tBrowser);// Firefox, Chrome
		driver.manage().deleteAllCookies();
		System.out.println("Test Browser = " + tBrowser + "\n");

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		bc.SelecBroswerResolution(driver, envDevice);
		bc.rwExcel("", "****** Load Login Again******", "");
		bc.rwExcel("", "Test Browser", tBrowser);
		bc.rwExcel("", "Test Environment", env);
		loadURL(driver, baseURL);
		AUTOpxLogin loginP = new AUTOpxLogin(driver);
		loginP.login(driver, accountEmail, accountPS);
		AcceptLicenseAgreementtoContinue acceptLicenseP = new AcceptLicenseAgreementtoContinue(driver);
		acceptLicenseP.clickAcceptBtn(driver);
		// ImageGallery igP = new ImageGallery(driver);
		return driver;
	}

	public static void main(String[] args) throws Exception {
		// Load environment parameters
		Properties prop = new Properties();
		// testprop.load(new FileInputStream("data/autopxConf.properties"));
		try {
			prop.load(AUTOpxController.class.getClassLoader().getResourceAsStream("data/autopxConf.properties"));
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
		String baseURL = prop.getProperty(env + ".DealerPortalBaseURL");
		String chkEmail = prop.getProperty("AUTOpx.checkEmail");

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

			loadURL(driver, baseURL);
			//// tempDebug(driver);// ***************************************Debug*****************************************
			//AddAllVINs(driver, tBrowser, env); //works, need to execlude #VINpx only in properties file, and include ##Add All VINs to VINpx - Add all New VIN

			//// 0.General Inventory Gallery
			bc.rwExcel("", "-----General Inventory Gallery Testing started-----" + (i + 1), "");
			inventoryGalleryTC(driver, tBrowser, env, versionNum);
			vehicleGallery(driver, tBrowser, env);
			// verifyRerender(driver, tBrowser);

			////// 1.VINpx:
			bc.rwExcel("", "-----VINpx Testing started-----" + (i + 1), "");
			VINpxInventoryTC(driver, tBrowser, versionNum, env, chkEmail);

			// bc.rwExcel("", "-----STOCKpx Testing started-----" + (i + 1), "");

			////// 2. STOCKpx
			bc.rwExcel("", "-----STOCKpx Testing started-----" + (i + 1), "");
			STOCKpxInventoryTC(driver, tBrowser, env);

			////// 3. Templates
			bc.rwExcel("", "-----Templates Testing started-----" + (i + 1), "");
			VINpxTemplatesTC(driver, tBrowser, versionNum, env, chkEmail);

			////// bc.Wait(18*60);//wait 18 minutes;
			//
			// ////// 4. LOTpx
			bc.rwExcel("", "-----LOTpx Testing started-----" + (i + 1), "");
			// LOTpxInventoryTC(driver, tBrowser, env);// Need to update since there are lots of changes
			LOTpxUploadCustomPic(driver, tBrowser, "LOTpx"); // All or LOTpx. This should be in the end of all testing
			bc.rwExcel("", "****** Testing is complete ****** " + (i + 1), "");
			driver.close();
			System.out.println("Test is complete!!!");
		}
		return;
	}
}
