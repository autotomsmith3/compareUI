package AdminPortal;

import java.awt.AWTException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

public class ManageDealerShipsAddNewAccount extends Comlibs {
	
	public void AddNewAccount(WebDriver driver, String brw, String versionNum, String envment,
			String checkEmail)
			throws IOException, InterruptedException, ClassNotFoundException, SQLException, AWTException {

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
//		Comlibs ac = new Comlibs();
		rwExcel("", "*********ManageDealerShips**********", "");

		int count = 0;
		String getMetadataSavePathFile = "C:\\1\\Eclipse\\Test Results\\AUTOpx" + "\\Metadata_" + env + ".xls";
		String[] titleString = { "Env.", "S/N", "Dealership_ID", "Dealership_Name", "Account_Email", "Dealership_Email",
				"ProductVINpx", "ProductSTOCKpx", "ProductLOTpx", "Metadata", "dlrGuid" };
		// =================================================
		writeTitle(getMetadataSavePathFile, titleString);
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
		Wait(wt);
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
			rwExcel(tc, false, "Add Account - Account Status ", "Not Working - Can change status to 2 - Lock out");
		} catch (Exception e) {
			rwExcel(tc, true, "Add Account - Account Status ", "Works good - Cannot change status to 2 - Lock out");
			AccountProfileP.selectAccountStatus(driver, 1);
		}
		AccountProfileP.clickSaveBtn(driver, tc);
		tc = "TC_addNewAct_with_Existing_ActEamil_checkMSG";

		boolean MessageExistForAddExistAccountEmail = AccountProfileP.checkMessageDisplayedHead(driver,
				"Check required fields");
		if (MessageExistForAddExistAccountEmail) {
			rwExcel(tc, true, "Add an Account ", "With Exist Account Email");
		} else {
			rwExcel(tc, false, "Add an Account ", "With Exist Account Email");
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
				"There is already a record with this Manufacturer and Dealer Code.");// "There is already a user record with this Login");
		if (MessageExist) {
			rwExcel(tc, true, "Add a dealership ", "With Exist DealershipID");
		} else {
			rwExcel(tc, false, "Add a dealership ", "With Exist DealershipID");
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
		MessageExist = DealerProfieP.checkMessageDisplayedHead(driver, "Your settings have been saved");
		// Bug here since entered Metadata. See AUTOPXOPS-1227. Now it shows an error "An error occurred. Please try again."
		// but the dealership has been created in our system.
		if (MessageExist) {
			rwExcel("AddDealervalid", true, "Add a new dealership with all fields",
					"Sucessful msg shows: Your settings have been saved");
		} else {
			rwExcel("AddDealervalid", false, "Add a new dealership with all fields",
					"Failed to shows msg: Your settings have been saved. Currently it shows: An error occurred. Please try again. There is bug here, see AUTOPXOPS-1227");
		}

		DealerProfieP.clickBackToDealerListBtn(driver, parentHandle, tc);
		// =========================== Add Dealership============================================================

		// **************************************************************************************
		//
		UserListP.clickManageDealerShips(driver);
		UserListP.inputSearch(driver, AllProdEmail);
		// **************************************************************************************
		// **************************************************************************************
		// **************************************************************************************
		// **************************************************************************************
		// **************************************************************************************

		UserListP.clickEditBtn(driver, "1");// 1,2,3...
		String attachedDealerName = AccountProfileP.selectOneDealerFrAllDealers(driver, 7);
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
		AccountProfileP.selectOneDealerFrAccountDealers(driver, attachedDealerName);
		Wait(wt);
		AccountProfileP.clickLeftArrowDetachBtn(driver);
		tc = "TC_Verify detach a dealer from Account Dealers_01";
		dealerExistInAccountDealers = AccountProfileP.verifyOneDealerInAccountDealersField(driver, attachedDealerName,
				true, tc);

		AccountProfileP.scrollUp(driver, -3000, tc);
		AccountProfileP.clickBackToDealerListBtn(driver, parentHandle, tc);
		tc = "TC_xxxx";
		UserListP.clickExpandDealersArrow(driver, 1);
		UserListP.clickEditOnDealer(driver, 1);
		DealerProfieP.clickBackToDealerListBtn(driver, parentHandle, tc);
		UserListP.clickViewDealerPortal(driver, 1);

		DealerPortal.DealerProfile DealerPortalDealerProfieP = new DealerPortal.DealerProfile(driver);
		DealerPortalDealerProfieP.clickInventoryGalleryBtn(driver, tc);
		DealerPortal.ImageGallery DealerPortalImageGalleryP = new DealerPortal.ImageGallery(driver);
		DealerPortalImageGalleryP.clickDealerShipInfoBtn(driver);

		driver.close();// Close Dealer Profile page
		// goto parent page
		// for (String winHandle : driver.getWindowHandles()) {
		// driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
		// }
		switchToWindow(driver);

		System.out.println("Stop here 2018-10-03");
		// Stop here!!! 2018-10-01

		// driver.close();
		// switchToWindow(driver);
		// UserListP.clickDealerViewBtn(driver, 1);
		// driver.close();
		// switchToWindow(driver);
		// *************************UserListP******************************************************
		//// *************************UserListP******************************************************


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
}
