package AdminPortal;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class hello extends Comlibs {
	public static VDVILogin loadURL(WebDriver driver, String bURL, String env)
			throws IOException, InterruptedException {
		driver.get(bURL);
		// Below to accept authentication only works for Firefox, Chrome scripts are not ready yet. 2018-11-06
		if (env.equalsIgnoreCase("Prod")) {
			Thread.sleep(2 * 1000);
			driver.switchTo().alert().sendKeys("admin" + Keys.TAB + "g4TT73Xy!");
			driver.switchTo().alert().accept();
		}
		;
		return new VDVILogin(driver);
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

		// =========================== Debug here============================================================
		tc = "click view dealer portal link from admin portal";
		UserListP.clickExpandDealersArrow(driver, 1, tc);
		UserListP.clickViewDealerPortal(driver, 1, tc);
		DealerPortal.DealerProfile DealerPortal_DealerProfile = new DealerPortal.DealerProfile(driver);
		DealerPortal_DealerProfile.clickTemplatesBtn(driver);

		DealerPortal.Templates DealerPortal_Templates = new DealerPortal.Templates(driver);
		DealerPortal_Templates.clickHeaderEditBtn(driver);
		// Templates:
		// By Header_dealerShipAddressCheckBox=By.xpath("//*[@id=\"headerContent\"]/label[3]/span"); // //*[@id="headerContent"]/label[3]/span; //checked: //*[@id="headerContent"]/label[3]/span
		// By Header_dealerShipAddressCheckBox=By.xpath("//*[@id='headerContent']/label[3]/span");// //*[@id="headerContent"]/label[3]/span
		// By Header_dealerShipAddressCheckBox=By.cssSelector("//*[@id='headerContent']/label[3]/span");// //*[@id="headerContent"]/label[3]/span

		// see ::after URL here:
		// 1. https://stackoverflow.com/questions/51992258/xpath-to-find-pseudo-element-after-in-side-a-div-element-with-out-any-content
		// 2. https://stackoverflow.com/questions/3012716/css-how-to-remove-pseudo-elements-after-before

		// console.log(window.getComputedStyle(document.querySelector('#headerContent > label:nth-child(6) > span'), ':after'));
		//
		// System.out.println("Begin...");
		//
		//// console.log(window.window.getComputedStyle(document.querySelector("#headerContent > label:nth-child(6) > span"), ":after"));
		// window.getComputedStyle(document.querySelector("#headerContent > label:nth-child(6) > span"), ":after");
		//
		// JavascriptExecutor js = (JavascriptExecutor) driver;
		// js.executeScript("return document.title");
		//
		//// console.log(window.window.getComputedStyle(document.querySelector("#headerContent > label:nth-child(6) > span"), ":after"));
		//
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String title = js.executeScript("return document.title").toString();
		System.out.println("\ntitle=" + title);

		// JavascriptExecutor js = (JavascriptExecutor) driver;
		//// String tt=js.executeScript("return document.title").toString();//ok to see title
		// // Dealership address: xpath://*[@id="headerContent"]/label[3]/span
		// // Dealership address: selecter: #headerContent > label:nth-child(6) > span . #headerContent > label:nth-child(6)
		// String tt=js.executeScript("return document.querySelector('#headerContent > label:nth-child(6) > span'), ''").toString();
		// System.out.println("\ntt="+tt);
		// JavascriptExecutor jss = (JavascriptExecutor) driver;
		//// String tt=js.executeScript("return document.title").toString();//ok to see title
		// String ttt=jss.executeScript("return document.querySelector('#headerContent > label:nth-child(6) > span'), 'span'").toString();
		// System.out.println("\nttt="+ttt);
		// By Header_dealerShipAddressCheckBox=By.cssSelector("#headerContent > label:nth-child(6) > span");// //*[@id="headerContent"]/label[3]/span

		// console.log(window.window.getComputedStyle(document.querySelector('#headerContent > label:nth-child(6) > span'), ':begin'))
		// console.log(window.window.getComputedStyle(document.querySelector("#headerContent > label:nth-child(6) > span"), ":begin"));

		String dealershipAddress = "#headerContent > label:nth-child(6) > span";
		String dealershipAddressCSSstyleID = "transform-origin";
		String dealershipAddressCSSstyleValue = "50% 50%";
		boolean checkboxIsChecked;

		// ***************Final script 1 checked ******************************************************
		tc = "Check the checkbox for DealershipAddress";
		checkboxIsChecked = checkboxWithPseudoElement("DealershipAddress", driver, true, false, dealershipAddress,
				dealershipAddressCSSstyleID, "50% 50%", tc);
		if (!checkboxIsChecked) {
			DealerPortal_Templates.clickDealershipAddressCheckBox(driver);
			checkboxIsChecked = checkboxWithPseudoElement("DealershipAddress", driver, true, true, dealershipAddress,
					dealershipAddressCSSstyleID, "50% 50%", tc);
		}
		// ***************End of Final script 1 checked ******************************************************

		// ***************Final script 2 unchecked ******************************************************
		tc = "Uncheck the checkbox for DealershipAddress";
		checkboxIsChecked = checkboxWithPseudoElement("DealershipAddress", driver, false, false, dealershipAddress,
				dealershipAddressCSSstyleID, "50% 50%", tc);
		if (!checkboxIsChecked) {
			DealerPortal_Templates.clickDealershipAddressCheckBox(driver);
			checkboxIsChecked = checkboxWithPseudoElement("DealershipAddress", driver, false, true, dealershipAddress,
					dealershipAddressCSSstyleID, "50% 50%", tc);
		}

		// ***************End of Final script 2 unchecked ******************************************************

		// ***************return window.getComputedStyle(document.querySelector***********************************************************************************************************
		/**
		 * How to use xpath to find pseudo-element ::after in side a div element with out any content? https://stackoverflow.com/questions/51992258/xpath-to-find-pseudo-element-after-in-side-a-div-element-with-out-any-content
		 * 
		 * 1. Pseudo-elements don't exist in the DOM tree (hence the name), therefore they cannot be selected with XPath 2. Unfortunately, that's not possible with XPath. As mentioned by Tomalak, Pseudo-elements don't exist in the DOM tree (hence the name), therefore they cannot be selected with XPath and Selenium does not expose them as well. In general, ::before and ::after pseudo-elements are used for styling of containing element. 3. My solution: a. get all styles b. find out what is different between checked and unchecked c. getPropertyValue to identify checked and unchecked - transform-origin=50% 50% (unchecked) or 2.5px 5px (checked)
		 **/

		JavascriptExecutor js2 = (JavascriptExecutor) driver;
		// String tt=js.executeScript("return document.title").toString();//ok to see title
		// Dealership address: xpath://*[@id="headerContent"]/label[3]/span
		// Dealership address: selecter: #headerContent > label:nth-child(6) > span . #headerContent > label:nth-child(6)

		// window.getComputedStyle(document.querySelector('#element'),':after').getPropertyValue('content')

		String script = "return window.getComputedStyle(document.querySelector('#headerContent > label:nth-child(6) > span'),':after').getPropertyValue('transform-origin')";//// get only value of transform-origin style. returned styles on checkbox checked and unchecked. On unchecked it contains one "moz-transform-origin=50% 50%"
		String xxchecked = js2.executeScript(script).toString();
		System.out.println("xxchecked:" + xxchecked);
		String searchTxt = "50% 50%";// moz-transform-origin=50% 50% (unchecked) or 2.5px 5px (checked)
		boolean checked = xxchecked.contains(searchTxt);
		if (checked) {
			System.out.println("checkbox is unchecked");
		} else {
			System.out.println("checkbox is checked");
		}

		DealerPortal_Templates.clickDealershipAddressCheckBox(driver);

		script = "return window.getComputedStyle(document.querySelector('#headerContent > label:nth-child(6) > span'),':after').getPropertyValue('transform-origin')";
		String unchecked = js2.executeScript(script).toString();
		System.out.println("unchecked:" + unchecked);
		checked = unchecked.contains(searchTxt);
		if (checked) {
			System.out.println("checkbox is unchecked");
		} else {
			System.out.println("checkbox is checked");
		}

		// ****************return window.window.getComputedStyle(document.querySelector**********************************************************************************************************

		script = "return window.window.getComputedStyle(document.querySelector('#headerContent > label:nth-child(6) > span'), ':after')";// get all CSS styles
		String checked2 = js2.executeScript(script).toString();
		System.out.println("checked2:" + checked2);
		System.out.println("unchecked:" + "Completed!\n");
		checked = checked2.contains(searchTxt);
		System.out.println("checked:" + checked);

		DealerPortal_Templates.clickDealershipAddressCheckBox(driver);

		script = "return window.window.getComputedStyle(document.querySelector('#headerContent > label:nth-child(6) > span'), ':after')";// get all CSS styles
		String xnchecked = js2.executeScript(script).toString();
		System.out.println("unchecked:" + xnchecked);
		checked = xnchecked.contains(searchTxt);
		System.out.println("checked:" + checked);

		System.out.println("unchecked:" + "Completed!");
		// window.getComputedStyle(document.querySelector('#headerContent > label:nth-child(6) > span'),':before')
		// **************************************************************************************************************************

		// =========================== Debug here============================================================

		// =========================== Add New Account Process============================================================
		tc = "TC_addNewAct_AttachDealer_DeleteIt";
		UserListP.clickAddAccount(driver, tc);
		AccountProfile AccountProfileP = new AccountProfile(driver);
		AccountProfileP.inputAccountEmail(driver, AddNewAccountEmail);
		AccountProfileP.inputFirstName(driver, FirstName);
		AccountProfileP.inputLastName(driver, LastName);
		AccountProfileP.selectAccountStatus(driver, 1);
		AccountProfileP.clickBackToDealerListBtn(driver, parentHandle, tc);

		// =========================== Add Account============================================================
		tc = "TC228658_n";// "TC_addNewAct_with_Existing_ActEamil";
		UserListP.clickAddAccount(driver, tc);
		// AccountProfile AccountProfileP = new AccountProfile(driver);
		AccountProfileP.inputAccountEmail(driver, accountEmail);
		AccountProfileP.inputFirstName(driver, FirstName);
		AccountProfileP.inputLastName(driver, LastName);
		AccountProfileP.selectAccountStatus(driver, 1);
		AccountProfileP.clickBackToDealerListBtn(driver, parentHandle, tc);
		tc = "TC228658_s";// "TC_addNewAct_with_Existing_ActEamil_SAVE";
		UserListP.clickAddAccount(driver, tc);
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
		UserListP.clickAddDealerShip(driver, tc);

		DealerProfile DealerProfieP = new DealerProfile(driver);
		DealerProfieP.selectOEM(driver, 13, tc);
		// check Buick and Cadillac and Chevrolet and GMC
		// DealerProfieP.selectOEMBrands(driver, 1); // check Buick
		// DealerProfieP.selectOEMBrands(driver, 2); // check Cadillac
		// DealerProfieP.selectOEMBrands(driver, 3); // check Chevrolet
		// DealerProfieP.selectOEMBrands(driver, 4); // check GMC
		// DealerProfieP.selectOEMBrands(driver, 5); // check Hummer
		for (String brand : Brands) {
			DealerProfieP.selectOEMBrands(driver, Integer.parseInt(brand), tc);
		}
		DealerProfieP.inputDealersipID(driver, DealershipID, tc);
		DealerProfieP.selectVINpxProd(driver, tc);
		DealerProfieP.selectSTOCKpxProd(driver, tc);
		// DealerProfieP.selectLOTpxProd(driver);
		// DealerProfieP.inputMetadata(driver, MetadataValues);

		// DealerProfieP.selectTemplateSetting(driver, TemplateSettings);// DEFAULT=1; replace=2;overlay=3;
		// DealerProfieP.selectTemplateSetting(driver, 1);
		DealerProfieP.inputDealershipName(driver, DealershipName, tc);
		DealerProfieP.inputAddress(driver, Address, tc);
		DealerProfieP.inputAddressLine2(driver, AddressLine2, tc);
		DealerProfieP.inputCity(driver, City, tc);
		DealerProfieP.inputDealersipEmail(driver, DealershipEmail, tc);
		DealerProfieP.inputZipCode(driver, ZipPostalCode, tc);
		DealerProfieP.inputCountry(driver, Country, tc);// USA=1
		DealerProfieP.inputState(driver, StateProvince, tc);// NY=33
		DealerProfieP.inputTagLineMarkingMsg(driver, TagLineMarkingMsg, tc);
		DealerProfieP.inputWebsite(driver, Website, tc);
		DealerProfieP.inputDealershipPhone(driver, DealershipPhoneNumber, tc);

		DealerProfieP.selectBackGroundSet(driver, SelectBackgroundSet, tc);// Generic Dealership=7; White Gradient=0
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
		UserListP.clickAddDealerShip(driver, tc);
		DealerProfieP.selectOEM(driver, 13, tc);
		for (String brand : Brands) {
			DealerProfieP.selectOEMBrands(driver, Integer.parseInt(brand), tc);
		}
		// String addNewDealerExtension = "_New_Added_18";// ************************ New one should be 18 ****************************
		DealerProfieP.inputDealersipID(driver, DealershipID + addNewDealerExtension, tc);
		DealerProfieP.selectVINpxProd(driver, tc);
		DealerProfieP.selectSTOCKpxProd(driver, tc);
		// DealerProfieP.selectLOTpxProd(driver);
		DealerProfieP.inputMetadata(driver, MetadataValues, tc);

		// DealerProfieP.selectTemplateSetting(driver, TemplateSettings);// DEFAULT=1; replace=2;overlay=3;
		// DealerProfieP.selectTemplateSetting(driver, 1);
		DealerProfieP.inputDealershipName(driver, DealershipName, tc);
		DealerProfieP.inputAddress(driver, Address, tc);
		DealerProfieP.inputAddressLine2(driver, AddressLine2, tc);
		DealerProfieP.inputCity(driver, City, tc);
		DealerProfieP.inputDealersipEmail(driver, "Autotomsmith4@gmail.com", tc);// Auto_Added_"+DealershipEmail);
		DealerProfieP.inputZipCode(driver, ZipPostalCode, tc);
		DealerProfieP.inputCountry(driver, Country, tc);// USA=1
		DealerProfieP.inputState(driver, StateProvince, tc);// NY=33
		DealerProfieP.inputTagLineMarkingMsg(driver, TagLineMarkingMsg, tc);
		DealerProfieP.inputWebsite(driver, Website, tc);
		DealerProfieP.inputDealershipPhone(driver, DealershipPhoneNumber, tc);
		alertmessage = "You must save the dealer information before you can take this action";
		DealerProfieP.uploadDealershipLogo(driver, dealershipLogoPath, alertmessage, tc);
		ac.Wait(wt);
		DealerProfieP.selectBackGroundSet(driver, SelectBackgroundSet, tc);// Generic Dealership=7; White Gradient=0
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
		UserListP.clickManageAccounts(driver, tc);
		ac.Wait(wt);
		UserListP.inputSearch(driver, AllProdEmail, tc);
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
		UserListP.clickExpandDealersArrow(driver, 1, tc);
		UserListP.clickEditOnDealer(driver, 1, tc);
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
		UserListP.clickManageDealerShips(driver, tc);
		ac.Wait(wt * 2);
		DealerList2 DealerListP2 = new DealerList2(driver);
		// Checking View On Dealer Portal link
		tc = "TC229379_d";// "Checking View On Dealer Portal link";
		DealerListP2.inputSearch(driver, "123456_New_Added_Cadillac");
		DealerListP2.clickViewOnDealerPortalBtn(driver, 1, tc);
		driver.close();
		ac.switchToWindow(driver);
		UserListP.clickManageDealerShips(driver, tc);
		ac.Wait(wt * 3);
		tc = "TC229371_d1";
		DealerListP2.clickAddDealerShip(driver);
		ac.Wait(wt * 2);
		DealerProfile DealerProfileP = new DealerProfile(driver);
		DealerProfileP.selectOEM(driver, 13, tc);
		// check Buick and Cadillac and Chevrolet and GMC
		// DealerProfileP.selectOEMBrands(driver, 1); // check Buick
		// DealerProfileP.selectOEMBrands(driver, 2); // check Cadillac
		// DealerProfileP.selectOEMBrands(driver, 3); // check Chevrolet
		// DealerProfileP.selectOEMBrands(driver, 4); // check GMC
		// DealerProfileP.selectOEMBrands(driver, 5); // check Hummer
		for (String brand : Brands) {
			DealerProfileP.selectOEMBrands(driver, Integer.parseInt(brand), tc);
		}
		DealerProfileP.inputDealersipID(driver, DealershipID, tc);
		DealerProfileP.selectVINpxProd(driver, tc);
		DealerProfileP.selectSTOCKpxProd(driver, tc);
		// DealerProfileP.selectLOTpxProd(driver);
		// DealerProfileP.inputMetadata(driver, MetadataValues);

		// DealerProfileP.selectTemplateSetting(driver, TemplateSettings);// DEFAULT=1; replace=2;overlay=3;
		// DealerProfileP.selectTemplateSetting(driver, 1);
		DealerProfileP.inputDealershipName(driver, DealershipName, tc);
		DealerProfileP.inputAddress(driver, Address, tc);
		DealerProfileP.inputAddressLine2(driver, AddressLine2, tc);
		DealerProfileP.inputCity(driver, City, tc);
		DealerProfileP.inputDealersipEmail(driver, DealershipEmail, tc);
		DealerProfileP.inputZipCode(driver, ZipPostalCode, tc);
		DealerProfileP.inputCountry(driver, Country, tc);// USA=1
		DealerProfileP.inputState(driver, StateProvince, tc);// NY=33
		DealerProfileP.inputTagLineMarkingMsg(driver, TagLineMarkingMsg, tc);
		DealerProfileP.inputWebsite(driver, Website, tc);
		DealerProfileP.inputDealershipPhone(driver, DealershipPhoneNumber, tc);
		ac.Wait(wt);
		DealerProfileP.selectBackGroundSet(driver, SelectBackgroundSet, tc);// Generic Dealership=7; White Gradient=0
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
		UserListP.clickAddDealerShip(driver, tc);
		DealerProfileP.selectOEM(driver, 13, tc);
		for (String brand : Brands) {
			DealerProfileP.selectOEMBrands(driver, Integer.parseInt(brand), tc);
		}
		// String addNewDealerExtension="_New_Added_16";// *******************************New one should be 17********************
		tc = "TC229370_d1";
		String addNewDealership = DealershipID + addNewDealerExtension + "_D";
		DealerProfileP.inputDealersipID(driver, addNewDealership, tc);//
		DealerProfileP.selectVINpxProd(driver, tc);
		DealerProfileP.selectSTOCKpxProd(driver, tc);
		// DealerProfileP.selectLOTpxProd(driver);
		DealerProfileP.inputMetadata(driver, MetadataValues, tc);

		// DealerProfileP.selectTemplateSetting(driver, TemplateSettings);// DEFAULT=1; replace=2;overlay=3;
		// DealerProfileP.selectTemplateSetting(driver, 1);
		DealerProfileP.inputDealershipName(driver, DealershipName, tc);
		DealerProfileP.inputAddress(driver, Address, tc);
		DealerProfileP.inputAddressLine2(driver, AddressLine2, tc);
		DealerProfileP.inputCity(driver, City, tc);
		DealerProfileP.inputDealersipEmail(driver, "Autotomsmith4@gmail.com", tc);// Auto_Added_"+DealershipEmail);
		DealerProfileP.inputZipCode(driver, ZipPostalCode, tc);
		DealerProfileP.inputCountry(driver, Country, tc);// USA=1
		DealerProfileP.inputState(driver, StateProvince, tc);// NY=33
		DealerProfileP.inputTagLineMarkingMsg(driver, TagLineMarkingMsg, tc);
		DealerProfileP.inputWebsite(driver, Website, tc);
		DealerProfileP.inputDealershipPhone(driver, DealershipPhoneNumber, tc);
		alertmessage = "You must save the dealer information before you can take this action";
		DealerProfieP.uploadDealershipLogo(driver, dealershipLogoPath, alertmessage, tc);
		ac.Wait(wt);
		DealerProfileP.selectBackGroundSet(driver, SelectBackgroundSet, tc);// Generic Dealership=7; White Gradient=0
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
		UserListP.clickManageDealerShips(driver, tc);
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
		UserListP.clickManageBGSets(driver, tc);
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
		ManageBackgroundsP.clickBackToManageSets(driver, tc);
		BackgroundSetsP.clickDealersUseBackGroundBtn(driver, 1, tc);
		ac.Wait(wt);
		BackgroundSetsP.clickClose(driver, tc);
		ac.Wait(wt);
		tc = "TC139447";
		BackgroundSetsP.clickCreateNewSet(driver);
		ac.Wait(wt);
		String tempSetName = "a";
		BackgroundSetsP.inputSetName(driver, tempSetName);
		BackgroundSetsP.clickCancel(driver);
		ac.Wait(wt);
		BackgroundSetsP.clickCreateNewSet(driver);
		ac.Wait(wt);
		BackgroundSetsP.inputSetName(driver, tempSetName);
		BackgroundSetsP.selectSetType(driver, 3);// 1-Old (Do Not User), 2-Flat on Flat on Flat, 3-Normal, 4-GM Only, 5-FCA Only
		System.out.println("\nPlease wait at least 3 minutes until Backgrounds page showing...");
		ac.Wait(wt);
		BackgroundSetsP.clickSubmit(driver);
		ac.Wait(wt);

		// ********************* check bg image *********************
		int sn = 1;
		double oneSNForScrollupPoint = 8.44091;
		double totalPoints = oneSNForScrollupPoint * 1;
		int scrollupPoints = 1;
		Backgrounds BackgroundsP = new Backgrounds(driver);

		// tc = "GM_Exterior_2019-GM-4NF56-4NF56-1SD_old ";////= modelcode bar 2019-GM-4NF56-4NF56-1SD - sn=2014
		// UserListP.scrollUp(driver, 17000, tc);// 17150 - value is on uper side
		// BackgroundsP.ClickOneExteriorModelYearBtn(driver,tc);
		// BackgroundsP.VerifyCarImage(driver, tc);
		// BackgroundsP.clickRightArrowBtn(driver, tc);
		// ac.Wait(wt);
		// BackgroundsP.VerifyCarImage(driver, tc);
		// BackgroundsP.clickLeftArrowBtn(driver, tc);
		// ac.Wait(wt);
		// BackgroundsP.VerifyCarImage(driver, tc);
		// BackgroundsP.clickCloseX(driver, tc);
		// UserListP.scrollUp(driver, -100000, tc);//scroll back to top
		//
		// tc = "GM_Interior_2019-GM-4NF56-1SD-4NF56-1SD_old";//= modelcode bar 2019-GM-4NF56-1SD-4NF56-1SD - sn=4876
		// UserListP.scrollUp(driver, 41158, tc);// 24100 - value is on uper side
		// BackgroundsP.ClickOneInteriorModelYearBtn(driver,tc);
		// BackgroundsP.VerifyCarImage(driver, tc);
		// BackgroundsP.clickRightArrowBtn(driver, tc);
		// BackgroundsP.clickLeftArrowBtn(driver, tc);
		// BackgroundsP.clickCloseX(driver, tc);
		// ac.Wait(wt);
		// UserListP.scrollUp(driver, -100000, tc);//scroll back to top
		//

		// ************************Check the failed loading car image from sn ************************

		tc = "FCA_2016_bf";// = modelcode bar 2016_bf - sn=17 from Excel-BG_CarCode table - green cols.
		sn = 2;
		totalPoints = oneSNForScrollupPoint * sn;
		scrollupPoints = (int) Math.round(totalPoints);
		UserListP.scrollUp(driver, scrollupPoints, tc);// 860
		BackgroundsP.ClickAnyOneOfExteriorOrInteriorModelYearBtn(driver, sn, tc);
		ac.Wait(wt * 2);
		BackgroundsP.VerifyCarImage(driver, tc);
		BackgroundsP.clickRightArrowBtn(driver, tc);
		ac.Wait(wt);
		BackgroundsP.VerifyCarImage(driver, tc + "_Right_Arrow");
		BackgroundsP.clickLeftArrowBtn(driver, tc);
		ac.Wait(wt);
		BackgroundsP.VerifyCarImage(driver, tc + "_Left_Arrow");
		BackgroundsP.clickCloseX(driver, tc);
		ac.Wait(wt);
		UserListP.scrollUp(driver, -100000, tc);// scroll back to top
		// ************************Check the loading car image from sn ************************
		tc = "FCA_2019_lx";// = modelcode bar 2019_lx - sn=102 from Excel-BG_CarCode table
		sn = 102;
		totalPoints = oneSNForScrollupPoint * sn;
		scrollupPoints = (int) Math.round(totalPoints);
		UserListP.scrollUp(driver, scrollupPoints, tc);// 860
		BackgroundsP.ClickAnyOneOfExteriorOrInteriorModelYearBtn(driver, sn, tc);
		ac.Wait(wt * 2);
		BackgroundsP.VerifyCarImage(driver, tc);
		BackgroundsP.clickRightArrowBtn(driver, tc);
		ac.Wait(wt);
		BackgroundsP.VerifyCarImage(driver, tc + "_Right_Arrow");
		BackgroundsP.clickLeftArrowBtn(driver, tc);
		ac.Wait(wt);
		BackgroundsP.VerifyCarImage(driver, tc + "_Left_Arrow");
		BackgroundsP.clickCloseX(driver, tc);
		ac.Wait(wt);
		UserListP.scrollUp(driver, -100000, tc);// scroll back to top

		tc = "GM_Exterior_2019-GM-4NF56-4NF56-1SD_new ";//// = modelcode bar 2019-GM-4NF56-4NF56-1SD - sn=2014
		sn = 2014;
		totalPoints = oneSNForScrollupPoint * sn;
		scrollupPoints = (int) Math.round(totalPoints);
		UserListP.scrollUp(driver, scrollupPoints, tc);// 860
		BackgroundsP.ClickAnyOneOfExteriorOrInteriorModelYearBtn(driver, sn, tc);
		ac.Wait(wt * 2);
		BackgroundsP.VerifyCarImage(driver, tc);
		BackgroundsP.clickRightArrowBtn(driver, tc);
		ac.Wait(wt);
		BackgroundsP.VerifyCarImage(driver, tc + "_Right_Arrow");
		BackgroundsP.clickLeftArrowBtn(driver, tc);
		ac.Wait(wt);
		BackgroundsP.VerifyCarImage(driver, tc + "_Left_Arrow");
		BackgroundsP.clickCloseX(driver, tc);
		ac.Wait(wt);
		UserListP.scrollUp(driver, -100000, tc);// scroll back to top

		tc = "GM_Interior_2019-GM-4NF56-1SD-4NF56-1SD_new";// = modelcode bar 2019-GM-4NF56-1SD-4NF56-1SD - sn=4876
		sn = 4876;
		totalPoints = oneSNForScrollupPoint * sn;
		scrollupPoints = (int) Math.round(totalPoints);
		UserListP.scrollUp(driver, scrollupPoints, tc);// 860
		BackgroundsP.ClickAnyOneOfExteriorOrInteriorModelYearBtn(driver, sn, tc);
		ac.Wait(wt * 2);
		BackgroundsP.VerifyCarImage(driver, tc);
		BackgroundsP.clickRightArrowBtn(driver, tc);
		ac.Wait(wt);
		BackgroundsP.VerifyCarImage(driver, tc + "_Right_Arrow");
		BackgroundsP.clickLeftArrowBtn(driver, tc);
		ac.Wait(wt);
		BackgroundsP.VerifyCarImage(driver, tc + "_Left_Arrow");
		BackgroundsP.clickCloseX(driver, tc);
		ac.Wait(wt);
		UserListP.scrollUp(driver, -100000, tc);// scroll back to top
		// ************************End of Check the loading car image from sn ************************
		// Add New BG Set
		UserListP.clickManageBGSets(driver, tc);
		ac.clickRefleshF5Btn(driver, tc);
		tc = "TC139534"; // Edit "a" background set
		ac.Wait(wt);
		BackgroundSetsP.inputSearch(driver, tempSetName);
		ac.Wait(wt);
		BackgroundSetsP.clickEditSetBtn(driver, 1);
		ac.Wait(wt);
		String editString = "_Edited";
		BackgroundSetsP.inputSetName(driver, editString);
		BackgroundSetsP.selectSetType(driver, 4);// 4-GM
		BackgroundSetsP.clickSubmitOnEdit(driver, tc);
		ac.Wait(wt);
		BackgroundSetsP.inputSearch(driver, tempSetName + editString);
		ac.Wait(wt);
		try {
			BackgroundSetsP.clickEditSetBtn(driver, 1);
			ac.rwExcel(tc, true, "Verify Edited background set", "Edited background set exists!");
		} catch (Exception e) {
			ac.rwExcel(tc, false, "Verify Edited background set", "Edited background set does NOT exist!");
		}
		ac.Wait(wt);
		BackgroundSetsP.clickSubmitOnEdit(driver, tc);
		tc = "TC139457"; // Verify Manage Backgrounds Images
		ac.Wait(wt);
		BackgroundSetsP.inputSearch(driver, tempSetName + editString);
		ac.Wait(wt);
		BackgroundSetsP.clickManageBGImageBtn(driver, 1);
		ManageBackgroundsP.clickBackToManageSets(driver, tc);
		tc = "TC226031"; // Verify Get List of Dealer button available
		BackgroundSetsP.inputSearch(driver, tempSetName + editString);
		ac.Wait(wt);
		BackgroundSetsP.clickDealersUseBackGroundBtn(driver, 1, tc);
		ac.Wait(wt);
		tc = "TC226032"; // Verify Get List of Dealer on the background
		BackgroundSetsP.clickClose(driver, tc);

		ac.Wait(wt);
		BackgroundSetsP.inputSearch(driver, tempSetName + editString);
		ac.Wait(wt);
		tc = "TC139558";
		BackgroundSetsP.clickDeleteBGSetBtn(driver, 1, tc);
		ac.acceptAlert(driver, tc, "OK");
		ac.clickRefleshF5Btn(driver, tc);
		BackgroundSetsP.inputSearch(driver, tempSetName + editString);
		ac.Wait(wt);
		tc = "TC139459_d";
		try {
			BackgroundSetsP.clickEditSetBtn(driver, 1);
			ac.rwExcel(tc, false, "Try to click the Edit button which should not exist",
					"Edit element exists! It should not happen!");
		} catch (Exception e) {
			ac.rwExcel(tc, true, "Try to click the Edit button which should not exist", "Edit element does not exist!");
		}

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
		UserListP.clickManageImageType(driver, tc);
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
		UserListP.clickManageImageType(driver, tc);
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
		String noteS = "19 Cadillac Int XT5";
		String editedNotesS = "Edited_19 Cadillac Int XT5";
		UserListP.clickManageAngleMappings(driver, tc);
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
		AngleMappingListP.inputNote(driver, "19 Cadillac Int XT5");
		// Note for Pattern:
		// 2019-GM-6N[A-Z]26-...-6N[A-Z]26-...
		// good but need to input all info: vdvi_interior, GM, 2, 19 Cadillac Int XT5, or only vdvi_interior is must.
		AngleMappingListP.inputPattern(driver, patternS);
		ac.Wait(wt * 4);
		AngleMappingListP.selectImageType(driver, "1001", 2, tc);
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
		// good but need to input all info: vdvi_interior, GM, 2, 19 Cadillac Int XT5, or only vdvi_interior is must.
		AngleMappingListP.inputPattern(driver, patternS);
		ac.Wait(wt);
		// matches from QA: 10019-042,10029-044,10039-059,10049-058
		// AngleMappingListP.selectImageType(driver, "1001", 2);
		AngleMappingListP.selectImageType(driver, "10019", 42, tc);
		// AngleMappingListP.selectImageType(driver, "1002", 4);
		AngleMappingListP.selectImageType(driver, "10029", 44, tc);
		// AngleMappingListP.selectImageType(driver, "1003", 6);
		AngleMappingListP.selectImageType(driver, "10039", 59, tc);
		// AngleMappingListP.selectImageType(driver, "1004", 8);
		AngleMappingListP.selectImageType(driver, "10049", 58, tc);
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
		AngleMappingListP.selectImageType(driver, "10019", 43, tc);
		// AngleMappingListP.selectImageType(driver, "1002", 4);
		AngleMappingListP.selectImageType(driver, "10029", 45, tc);
		// AngleMappingListP.selectImageType(driver, "1003", 6);
		AngleMappingListP.selectImageType(driver, "10039", 57, tc);
		// AngleMappingListP.selectImageType(driver, "1004", 8);
		AngleMappingListP.selectImageType(driver, "10049", 56, tc);
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

		UserListP.clickManageExportTemplates(driver, tc);
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
//		ExportTemplateListP.clickCombinedFileCheckBox(driver, tc);
//		ExportTemplateListP.clickBrandedImagesCheckBox(driver, tc);
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
//		ExportTemplateListP.clickCombinedFileCheckBox(driver);
//		ExportTemplateListP.clickBrandedImagesCheckBox(driver);
//		ac.Wait(wt);
//		ExportTemplateListP.clickBrandedImagesCheckBox(driver);
//		ExportTemplateListP.clickBrandedImagesCheckBox(driver);
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
//		ExportTemplateListP.clickCombinedFileCheckBox(driver);
//		ExportTemplateListP.clickBrandedImagesCheckBox(driver);
//		ac.Wait(wt);
//		ExportTemplateListP.clickCombinedFileCheckBox(driver);
//		ExportTemplateListP.clickBrandedImagesCheckBox(driver);
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
			UserListP.clickManageGlobalConfig(driver,tc);
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

	private static String[] fetchOneDemArrayFromPropFile(String propertyName, Properties propFile)
			throws IOException, InterruptedException {
		// get array split up by the colin
		String a[] = propFile.getProperty(propertyName).split(",");
		return a;
	}

	public static boolean checkboxWithPseudoElement(String checkboxName, WebDriver driver, boolean checked, boolean secondTime,
			String pseudoElementSelectorID, String cssStyle, String cssStyleValue, String tc) throws IOException {

		// ***************return window.getComputedStyle(document.querySelector***********************************************************************************************************
		/**
		 * How to use xpath to find pseudo-element ::after in side a div element with out any content? https://stackoverflow.com/questions/51992258/xpath-to-find-pseudo-element-after-in-side-a-div-element-with-out-any-content
		 * 
		 * 1. Pseudo-elements don't exist in the DOM tree (hence the name), therefore they cannot be selected with XPath 2. Unfortunately, that's not possible with XPath. As mentioned by Tomalak, Pseudo-elements don't exist in the DOM tree (hence the name), therefore they cannot be selected with XPath and Selenium does not expose them as well. In general, ::before and ::after pseudo-elements are used for styling of containing element. 3. My solution: a. get all styles b. find out what is different between checked and unchecked c. getPropertyValue to identify checked and unchecked - transform-origin=50% 50% (unchecked) or 2.5px 5px (checked)
		 **/
		boolean isTrue = false;

		JavascriptExecutor js = (JavascriptExecutor) driver;
		// String tt=js.executeScript("return document.title").toString();//ok to see title
		// Dealership address: xpath://*[@id="headerContent"]/label[3]/span
		// Dealership address: selecter: #headerContent > label:nth-child(6) > span . #headerContent > label:nth-child(6)

		// window.getComputedStyle(document.querySelector('#element'),':after').getPropertyValue('content')

		String Xscript = "return window.getComputedStyle(document.querySelector('#headerContent > label:nth-child(6) > span'),':after').getPropertyValue('transform-origin')";

		String script = "return window.getComputedStyle(document.querySelector('" + pseudoElementSelectorID
				+ "'),':after').getPropertyValue('" + cssStyle + "')";//// get only value of transform-origin style. returned styles on checkbox checked and unchecked. On unchecked it contains one "moz-transform-origin=50% 50%"
		String xxchecked = js.executeScript(script).toString();
		System.out.println("xxchecked:" + xxchecked);
		String searchTxt = "50% 50%";// moz-transform-origin=50% 50% (unchecked) or 2.5px 5px (checked)
		boolean siteChecked = xxchecked.contains(searchTxt);
		if (siteChecked) {
			if (!checked) {
				isTrue = true;
				/// write to excel pass; checkbox should not be checked
				System.out.println("Passed." + checkboxName + " Checkbox is not checked!");
//				rwExcel(tc, true, "Verify " + checkboxName, "Checkbox is not checked");
			} else {
				isTrue = false;
				if (secondTime) {
					// write to excel failed; checkbox cannot be unchecked.
					System.out.println("Failed," + checkboxName + " checkbox is unchecked when should be checked!");
//					rwExcel(tc, false, "Verify " + checkboxName, "Checkbox is unchecked when should be checked!");
				}
			}
			System.out.println("The " + checkboxName + " Checkbox is unchecked");
		} else if (checked) {
			isTrue = true;
			System.out.println("Passed," + checkboxName + " checkbox is checked");
			// write to excel pass; checkbox should be checked
//			rwExcel(tc, true, "Verify " + checkboxName, "Checkbox is checked");
		} else {
			isTrue = false;
			if (secondTime) {
				// write to excel failed; checkbox cannot be unchecked.
				System.out.println("Failed," + checkboxName + "checkbox is checked when should be unchecked!");
//				rwExcel(tc, false, "Verify " + checkboxName, "Checkbox is checked when should be unchecked!");
			}
		}
		return isTrue;
	};

	// public static void checkboxcheck(WebDriver driver, String checkboxSelectorID, String checkboxCSSStyleID, String tc) {
	// // ***************Final script 1 checked ******************************************************
	//// tc = "Check the checkbox for DealershipAddress";
	// boolean checkboxIsChecked = checkboxWithPseudoElement("DealershipAddress", driver, true, false, checkboxSelectorID,
	// checkboxCSSStyleID, "50% 50%", tc);
	// if (!checkboxIsChecked) {
	// DealerPortal_Templates.clickDealershipAddressCheckBox(driver);
	// checkboxIsChecked = checkboxWithPseudoElement("DealershipAddress", driver, true, true, checkboxSelectorID,
	// checkboxCSSStyleID, "50% 50%", tc);
	// }
	// // ***************End of Final script 1 checked ******************************************************
	//
	// }

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
			System.out.println("Debugging is started in " + env + "\n");
			// Initial
			Comlibs bc = new Comlibs();
			final WebDriver driver;
			driver = bc.drivers(tBrowser);// Firefox, Chrome
			driver.manage().deleteAllCookies();
			System.out.println("Debugging Browser = " + tBrowser + "\n");

			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			if (!tBrowser.equalsIgnoreCase("Chromexxxxxxxxx")) { // only Chrome doesn't work since Chrome updated on Jul, 2017, works on Dec 13,2017 webdriver ver3.8.5
				bc.SelecBroswerResolution(driver, envDevice);
			}
			bc.rwExcel("", "****** Debugging started ******" + (i + 1), "");
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

			// ////// 1.ManageDealerShipsAddNewAccount:
			// bc.rwExcel("", "-----ManageAccounts - Add An New Account Testing started-----" + (i + 1), "");
			// ManageDealerShipsAddNewAccount ManageDealerShips = new ManageDealerShipsAddNewAccount();
			// ManageDealerShips.AddNewAccount(driver, tBrowser, versionNum, env, chkEmail);

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
