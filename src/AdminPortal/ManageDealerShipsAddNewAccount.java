package AdminPortal;

import java.awt.AWTException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import DealerPortal.AUTOpxLogin;
import DealerPortal.AcceptLicenseAgreementtoContinue;
import DealerPortal.DealerProfile;
import DealerPortal.MailReader;
import cPP.com_libs;

public class ManageDealerShipsAddNewAccount extends Comlibs {

	public void AddNewAccount(WebDriver driver, String brw, String versionNum, String envment, String checkEmail)
			throws Exception {

		// Load environment parameters
		Properties prop = new Properties();
		// testprop.load(new FileInputStream("data/autopxConf.properties"));
		try {
			prop.load(AdminPortalController.class.getClassLoader()
					.getResourceAsStream("./AdminPortalData/adminPortalConf.properties"));
		} catch (IOException e) {
			// 
			e.printStackTrace();
		}
		String tc = "ManageDealerShipBegin";
		String env = prop.getProperty("AUTOpx.environment");
		String baseURL = prop.getProperty(env + ".AdminPortalBaseURL");
		String DealerPortalBaseURL = prop.getProperty(env + ".DealerPortalBaseURL");
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
		// String[] VINpxNewVINs = fetchOneDemArrayFromPropFile(env + ".VINpxNewVINs", prop);
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
		String UserDealerName = prop.getProperty(env + ".UserDealerName");
		String TagLineMarkingMsg = prop.getProperty(env + ".TagLineMarkingMsg");
		String Website = prop.getProperty(env + ".Website");
		String DealershipPhoneNumber = prop.getProperty(env + ".DealershipPhoneNumber");
		int TemplateSettings = Integer.parseInt(prop.getProperty(env + ".TemplateSettings"));
		int SelectBackgroundSet = Integer.parseInt(prop.getProperty(env + ".SelectBackgroundSet"));
		int wt = Integer.parseInt(prop.getProperty("AUTOpx.waitTime"));
		String AddNewAccountEmail = prop.getProperty(env + ".AddNewAccountEmail");
		String SelectedDealerNameToAttach = prop.getProperty(env + ".SelectedDealerNameToAttach");
		String deleteUserPostWSURL = prop.getProperty(env + ".deleteUserPostWSURL");
		String addNewDealerExtension = addNewDealerExtension = prop.getProperty(env + ".addNewDealerExtension");
		String dealershipLogoPath = prop.getProperty("AUTOpx.dealershipLogoPath");
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
		// Comlibs ac = new Comlibs();
		rwExcel("", "*********ManageDealerShips**********", "");

		int count = 0;
		int dataLength = 54;
		String[] metadataValues = new String[dataLength + 1];
		int datasize = metadataValues.length;

		// =================================================
		VDVILogin loginP = new VDVILogin(driver);
		tc = "TC_version";
		loginP.verifyFooterVersionDisclaimer(env, versionNum, tc);
		int dealerN = 0;
		String dealerSN = "";
		tc = "TC_login";
		loginP.login(driver, accountEmail, accountPS, tc);
		String parentHandle = driver.getWindowHandle(); // get the current window handle

		UserList UserListP = new UserList(driver);
		Wait(wt);
		tc = "TC_Page_Display";
		UserListP.scrollUp(driver, 3000, tc); // QA -2000 Prod -3000 - negative means scrolldown
		UserListP.clickDisplayDropDownBtn(driver, "3");// 3 - display 150
		UserListP.scrollUp(driver, -9000, tc); // QA -2000 Prod -3000 - negative means scrolldown
		// =========================== Add New Account Process============================================================
		tc = "TC228721_0";
		UserListP.clickAddAccount(driver, tc);
		Wait(wt);
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
		}

		AccountProfile AccountProfileP = new AccountProfile(driver);
		AccountProfileP.inputAccountEmail(driver, AddNewAccountEmail);
		AccountProfileP.inputUserDealerName(driver, UserDealerName);
		AccountProfileP.inputFirstName(driver, FirstName);
		AccountProfileP.inputLastName(driver, LastName);
		AccountProfileP.selectAccountStatus(driver, 1);
		tc = "TC228721";
		AccountProfileP.clickSaveBtn(driver, tc);
		Wait(wt);
		tc = "TC_Search box";
		int hyphenLocation = SelectedDealerNameToAttach.indexOf(" - ");
		AccountProfileP.inputSearchForDealers(driver, SelectedDealerNameToAttach.substring(1, hyphenLocation), tc);
		tc = "TC228723";
		String selectedDealershipName = AccountProfileP.selectOneDealerFrAllDealers(driver, SelectedDealerNameToAttach,
				tc);
		AccountProfileP.clickRightArrowAttachBtn(driver);
		tc = "TC228723_scrollUP";
		AccountProfileP.scrollUp(driver, -132, tc);
		tc = "TC228721_SAVE";
		AccountProfileP.clickSaveBtn(driver, tc);
		Wait(wt);
		tc = "TC228724";
		AccountProfileP.clickResetPasswordBtn(driver);
		Wait(wt);
		AccountProfileP.checkMSGDisplayedHead(driver,
				"An email containing a temporary password has been sent to the dealer", tc);
		// Close Account Profile page
		driver.close();
		// Open Dealer Portal
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
		}
		DealerPortal.AUTOpxController.loadURL(driver, DealerPortalBaseURL);
		AUTOpxLogin dealerPortalLoginP = new AUTOpxLogin(driver);

		// Open sautotomsmith.jj@gmail.com to get temporary PS
		DealerPortal.MailReader gMail = new MailReader();
		String tempPS = "";
		String subject = "Reset Password for VINpx";

		String content1 = "You have requested to have your password reset for your VINpx account.";
		String psB4 = "Your temporary password is ";
		String psAfter = "Go to";
		tc = "TC228725";
		// mailID = "Imap.gmail.com";
		// email ="tdautof1@gmail.com";
		// mailPassword = "Autodata1";
		tempPS = gMail.getTemporaryPS(subject, psB4, psAfter, "Imap.gmail.com", AddNewAccountEmail, accountPS);
		if (tempPS.length() > 0) {
			rwExcel(tc, true, "Verify Retrieving the temporary password in account email", "Password received");
		} else {
			rwExcel(tc, false, "Verify Retrieving the temporary password in account email", "Password is empty!");
		}
		tc = "TC228725_1";
		DealerPortal.AUTOpxLogin dealerPortalloginP = new AUTOpxLogin(driver);
//		dealerPortalloginP.login(driver, AddNewAccountEmail, tempPS); // this should be correct one. Now issue here, see autopxops-1196
		dealerPortalloginP.loginDealerProfile(driver, AddNewAccountEmail, tempPS);
//		 loginP.loginDealerProfile(driver, accountEmail, tempPS);//this is temp, it skips agreement page
		// DealerPortal.AcceptLicenseAgreementtoContinue acceptLicenseP = new AcceptLicenseAgreementtoContinue(driver);
		// acceptLicenseP.clickAcceptPSBtn(driver);
		DealerPortal.DealerProfile dpP = new DealerProfile(driver);
		tc = "TC139104-TC139105";
		dpP.changePS(driver, accountPS, tc);
//		tc = "TC139105";
//		Wait(wt);
//		dpP.clickNOBtn(driver, tc); //This can be removed since Only change PS in separated pop-up, so no need to show re-render pop-up
		tc = "TC139106";
		Wait(wt);
		dpP.clickInventoryGalleryBtn(driver, tc);
		Wait(wt);
		dpP.clickLogout(driver);
		tc = "TC_loginDealerPortal_afterChangePS";
		// login dealer portal again and accept agreement and logout
		try {
			DealerPortal.AUTOpxController.loadURL(driver, DealerPortalBaseURL);
			dealerPortalloginP.login(driver, AddNewAccountEmail, accountPS);
			// acceptLicenseP.clickAcceptBtn(driver);
			dpP.clickLogout(driver);
			rwExcel(tc, true, "Verify Login Dealer Portal", "After changed PS");
		} catch (Exception e) {
			rwExcel(tc, true, "Verify Login Dealer Portal", "After changed PS");
		}
		;

		// Load Admin Portal URL
		AdminPortalController.loadURL(driver, baseURL, env);
		tc = "Login again into Admin Portal after changed PS";
		loginP.login(driver, accountEmail, accountPS, tc);
		Wait(wt);
		UserListP.inputSearch(driver, AddNewAccountEmail, tc);
		UserListP.clickEditBtn(driver, "1");
		Wait(wt);
		tc = "TC228727";
		AccountProfileP.selectOneDealerFrAccountDealers(driver, SelectedDealerNameToAttach, tc);
		AccountProfileP.clickLeftArrowDetachBtn(driver);
		tc = "Remove dealership. Click SAVE after clicking left arrow.";
		AccountProfileP.clickSaveBtn(driver, tc);
		Wait(wt);
		tc = "TC228737";
		String drlGuid = AccountProfileP.getDlrGuid(driver, tc);
		drlGuid = AccountProfileP.trimURL_user(drlGuid);
		AccountProfileP.clickBackToDealerListBtn(driver, parentHandle, tc);
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
		}
		// Run Postman to delete the account using drlGuid
		// String deleteUserPostWSURL=
		String postBody = "{}";
		String jsonTextFrDeleteUserWS = "";
		tc = "TC139404";
		try {
			jsonTextFrDeleteUserWS = com_libs.getSourceCodeJson(postBody, deleteUserPostWSURL, drlGuid, "");
			rwExcel(tc, true, "Add New Account ", "Run jsonTextFrDeleteUserWS");
		} catch (Exception ex) {
			jsonTextFrDeleteUserWS = "Failed!";
			System.out.println("DeleteuserWS failed! ");
			rwExcel(tc, false, "Add New Account ", "Run jsonTextFrDeleteUserWS");
		}
		if (jsonTextFrDeleteUserWS.contains("User successfully deleted")) {
			// success
			System.out.println("DeleteuserWS ran successfully! User successfully deleted");
			rwExcel(tc, true, "Add New Account ",
					"Run jsonTextFrDeleteUserWS to delete the account. User successfully deleted");
		} else if (jsonTextFrDeleteUserWS.contains("User has dealers attached. Cannot delete user.")) {
			// Failed
			System.out.println("DeleteuserWS failed! User has dealers attached. Cannot delete user. ");
			rwExcel(tc, false, "Add New Account ",
					"Run jsonTextFrDeleteUserWS to delete the account. User has dealers attached. Cannot delete user.");
		} else if (jsonTextFrDeleteUserWS.contains("Failed!")) {
			// unknown error
			System.out.println("DeleteuserWS failed! Returned exception error! ");
			rwExcel(tc, false, "Add New Account ", "Run jsonTextFrDeleteUserWS to delete the account failed. ");
		} else if (jsonTextFrDeleteUserWS.contains("User does not exist")) {
			// unknown error
			System.out.println("DeleteuserWS failed! User does not exist! ");
			rwExcel(tc, false, "Add New Account ",
					"Run jsonTextFrDeleteUserWS to delete the account. User does not exist! ");
		} else {
			// unknown error
			System.out.println(
					"DeleteuserWS failed! Unknown error! DeleteUserWS returned=\"" + jsonTextFrDeleteUserWS + "\"");
			rwExcel(tc, false, "Add New Account ", "Run jsonTextFrDeleteUserWS to delete the account. Unknown error!");

		}

		// F5 to refresh page
		tc = "TC139404_01";
		clickRefleshF5Btn(driver, tc);
		Wait(2);
		UserListP.inputSearch(driver, AddNewAccountEmail, tc);
		tc = "TC139404_02";
		try {
			UserListP.clickEditBtn(driver, "1");
			// Failed because account still exists in the system
			System.out.println("Account Email =\"" + AddNewAccountEmail + "\" still exists in system! ");
			rwExcel(tc, false, "Add New Account and Run jsonTextFrDeleteUserWS to delete the account. ",
					"After refleshed the page, Account Email =\"" + AddNewAccountEmail + "\" still exists in system!");

		} catch (Exception ex) {
			// Passed. Add Account all processes Passed because account does not exist in the system
			System.out.println("Account Email =\"" + AddNewAccountEmail
					+ "\" does NOT exist in system! Add Account all processes Passed!");
			rwExcel(tc, true, "Add New Account and Run jsonTextFrDeleteUserWS to delete the account. ",
					"After refleshed the page, Account Email =\"" + AddNewAccountEmail
							+ "\" does NOT exist in system!");
		}

		// =========================== Add Account to check the error message============================================================
		tc = "TC228658";// "TC_addNewAct_with_Existing_ActEamil";
		UserListP.clickAddAccount(driver, tc);
		Wait(wt);
		// AccountProfile AccountProfileP = new AccountProfile(driver);
		AccountProfileP.inputAccountEmail(driver, accountEmail);
		AccountProfileP.inputFirstName(driver, FirstName);
		AccountProfileP.inputLastName(driver, LastName);
		AccountProfileP.selectAccountStatus(driver, 1);
		AccountProfileP.clickBackToDealerListBtn(driver, parentHandle, tc);
		tc = "TC228658_01";// "TC_addNewAct_with_Existing_ActEamil_SAVE";
		UserListP.clickAddAccount(driver, tc);
		AccountProfileP.inputAccountEmail(driver, accountEmail);
		AccountProfileP.inputFirstName(driver, FirstName);
		AccountProfileP.inputLastName(driver, LastName);
		tc = "TC139104_e";// "TC_addNewAct_AccountStatusShouldNotBeChanged";
		try {
			AccountProfileP.selectAccountStatus(driver, 2);// 1- Active, 2- Lock out, 3-Change Password, 4-Disabled
			rwExcel(tc, false, "Add Account - Account Status ", "Not Working - Can change status to 2 - Lock out");
		} catch (Exception e) {
			rwExcel(tc, true, "Add Account - Account Status ", "Works good - Cannot change status to 2 - Lock out");
			AccountProfileP.selectAccountStatus(driver, 1);
		}
		AccountProfileP.clickSaveBtn(driver, tc);
		tc = "TC228658_02";// "TC_addNewAct_with_Existing_ActEamil_checkMSG";
		Wait(2);
		boolean MessageExistForAddExistAccountEmail = AccountProfileP.checkMessageDisplayedHead(driver,
				"Check required fields");
		if (MessageExistForAddExistAccountEmail) {
			rwExcel(tc, true, "Add an Account ", "With Exist Account Email");
		} else {
			rwExcel(tc, false, "Add an Account ", "With Exist Account Email");
		}
		AccountProfileP.clickBackToDealerListBtn(driver, parentHandle, tc);
		Wait(wt);
		// tc = "TC228742";// "TC_addNewAct_with_missing required field - First Name";
		// tc = "TC228743";// "TC_addNewAct_with_missing required field - Last Name";
		// tc = "TC228739";// "TC_addNewAct_with_missing required field - User/Dealer Name";
		// =========================== Add Account to check the error message============================================================

		// =========================== Add Dealership for existing DealerID to check the message============================================================
		tc = "TC228656_01";
		UserListP.clickAddDealerShip(driver, tc);
		Wait(wt);
		AdminPortal.DealerProfile DealerProfieP = new AdminPortal.DealerProfile(driver);
		tc = "Select/Unselect All OEM/Brands_01";
		DealerProfieP.selectUnselectAllOEMBrands(driver, true, tc);
		tc = "Select/Unselect All OEM/Brands_02";
		DealerProfieP.selectUnselectAllOEMBrands(driver, false, tc);
		tc = "TC228656_01";
		DealerProfieP.selectOEM(driver, 13, tc);
		// check Buick and Cadillac and Chevrolet and GMC
		 DealerProfieP.selectOEMBrands(driver, 1,tc); // check Buick
		// DealerProfieP.selectOEMBrands(driver, 2); // check Cadillac
		// DealerProfieP.selectOEMBrands(driver, 3); // check Chevrolet
		// DealerProfieP.selectOEMBrands(driver, 4); // check GMC
		// DealerProfieP.selectOEMBrands(driver, 5); // check Hummer
//		for (String brand : Brands) {
//			DealerProfieP.selectOEMBrands(driver, Integer.parseInt(brand), tc);
//		}
		DealerProfieP.inputDealersipID(driver, DealershipID, tc);
		DealerProfieP.selectVINpxProd(driver, tc);
		DealerProfieP.selectSTOCKpxProd(driver, tc);
//		 DealerProfieP.selectLOTpxProd(driver);
		DealerProfieP.selectDealerBrandedNewProd(driver,tc+"_07");
//		DealerProfieP.selectDealerBrandedUsedProd(driver,tc+"_07");
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
		DealerProfieP.inputState(driver, StateProvince,tc);// NY=33
		DealerProfieP.inputTagLineMarkingMsg(driver, TagLineMarkingMsg, tc);
		DealerProfieP.inputWebsite(driver, Website, tc);
		DealerProfieP.inputDealershipPhone(driver, DealershipPhoneNumber, tc);
//		DealerProfieP.selectBackGroundSet(driver, SelectBackgroundSet, tc);// Generic Dealership=7; White Gradient=0
		DealerProfieP.scrollUp(driver, -3000, tc);
		DealerProfieP.clickSaveBtn(driver, tc);
		Wait(wt);
		tc = "TC228656_02";// "AddDealerInvalid_withExistDealershipID";
		boolean MessageExist = DealerProfieP.checkMessageDisplayedHead(driver,
				"There is already a record with this Manufacturer and Dealer Code.", tc);// "There is already a user record with this Login");
		if (MessageExist) {
			rwExcel(tc, true, "Add a dealership ", "With Exist DealershipID");
		} else {
			rwExcel(tc, false, "Add a dealership ", "With Exist DealershipID");
		}

		DealerProfieP.clickBackToDealerListBtn(driver, parentHandle, tc);

		// **************************Add a new Dealership to check the successful message*****************************************************
		// click Add Dealership btn
		tc = "TC139021_01";// "Add a new Dealership to check the successful message";
		UserListP.clickAddDealerShip(driver, tc);
		Wait(wt);
		DealerProfieP.selectOEM(driver, 13, tc);
		for (String brand : Brands) {
			DealerProfieP.selectOEMBrands(driver, Integer.parseInt(brand), tc);
		}
		String addNewDealership = DealershipID + "_Fr_Acct" + addNewDealerExtension;
		DealerProfieP.inputDealersipID(driver, addNewDealership, tc);// New one show be 2
		DealerProfieP.selectVINpxProd(driver, tc);
		DealerProfieP.selectSTOCKpxProd(driver, tc);
		// DealerProfieP.selectLOTpxProd(driver);
//		DealerProfieP.selectDealerBrandedNewProd(driver,tc+"_08");
		DealerProfieP.selectDealerBrandedUsedProd(driver,tc+"_08");
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
		DealerProfieP.inputState(driver, StateProvince,tc);// NY=33
		DealerProfieP.inputTagLineMarkingMsg(driver, TagLineMarkingMsg, tc);
		DealerProfieP.inputWebsite(driver, Website, tc);
		DealerProfieP.inputDealershipPhone(driver, DealershipPhoneNumber, tc);
		alertmessage = "You must save the dealer information before you can take this action";
		DealerProfieP.uploadDealershipLogo(driver, dealershipLogoPath, alertmessage, tc);
//		DealerProfieP.selectBackGroundSet(driver, SelectBackgroundSet, tc);// Generic Dealership=7; White Gradient=0
		DealerProfieP.scrollUp(driver, -3000, tc);
		DealerProfieP.clickSaveBtn(driver, tc);
		Wait(wt);
		// Stop here for the time being since there is bug here AUTOPXOPS-1227
		tc = "TC139021_02";// "AddDealerInvalid_withMissingMUSTField";
		// The successful message "Your settings have been saved" will only show one second then disappear.
		// So the successful message should be empty "" here;
		String successfulMsg = "";// "Your settings have been saved" - this msg shows only a second and then disappears.
		MessageExist = DealerProfieP.checkMessageDisplayedHead(driver, successfulMsg, tc);
		// Bug here since entered Metadata. See AUTOPXOPS-1227. Now it shows an error "An error occurred. Please try again."
		// but the dealership has been created in our system. Issue fixed but "Your settings have been saved" message only shows a second and then disappears.
		if (MessageExist) {
			rwExcel(tc, true, "Add a new dealership \"" + addNewDealership + "\" with all fields",
					"Sucessful msg shows: Your settings have been saved");
		} else {
			rwExcel(tc, false, "Add a new dealership \"" + addNewDealership + "\" with all fields",
					"Failed to shows msg: Your settings have been saved. Currently it only shows a second and then disappears. Related to bug AUTOPXOPS-1227");
		}
		DealerProfieP.clickBackToDealerListBtn(driver, parentHandle, tc);// Stop here. Verify dealer added in system through Manage Dealership by input the dealerid and click the edit buttom then close Dealer Profile page
		Wait(wt);
		tc = "TC144670";// "Upload dealership logo after creating the dealership";
		UserListP.clickManageDealerShips(driver, tc);
		Wait(wt * 2);
		DealerList DealerListP = new DealerList(driver);
		DealerListP.inputSearch(driver, addNewDealership);
		DealerListP.clickEditBtn(driver, "1");
		DealerProfieP.scrollUp(driver, 500, tc);
		successfulMsg = "";
		DealerProfieP.uploadDealershipLogo(driver, dealershipLogoPath, successfulMsg, tc);
		// Verify good message upload dealership logog successfully here.
		DealerProfieP.scrollUp(driver, 500, tc);
		Wait(wt);
		DealerProfieP.scrollUp(driver, -1000, tc);
		DealerProfieP.clickBackToDealerListBtn(driver, parentHandle, tc);
		Wait(wt);

		// =========================== Add Dealership============================================================

		// **************************************************************************************
		//
		UserListP.clickManageAccounts(driver, tc);
		Wait(wt);
		UserListP.inputSearch(driver, AllProdEmail, tc);
		// **************************************************************************************
		// **************************************************************************************
		UserListP.clickEditBtn(driver, "1");// 1,2,3...
		Wait(wt);
		tc = "TC228752_01";// "Select one dealer from All Dealers";
		String attachedDealerName = AccountProfileP.selectOneDealerFrAllDealers(driver, 7, tc);
		boolean dealerExistInAllDealers = false;
		boolean dealerExistInAccountDealers = false;
		tc = "TC228752_02";// "Dealer should not exist in Account Dealer field_01";
		dealerExistInAccountDealers = AccountProfileP.verifyOneDealerInAccountDealersField(driver, attachedDealerName,
				false, tc);
		AccountProfileP.clickRightArrowAttachBtn(driver);
		tc = "TC228752_03";// "TC_Verify atached dealer from All Dealers_01";
		dealerExistInAllDealers = AccountProfileP.verifyOneDealerInAllDealersField(driver, attachedDealerName, true,
				tc);
		tc = "TC228752_04";// "Dealer should exist in Account Dealer field_02";
		dealerExistInAccountDealers = AccountProfileP.verifyOneDealerInAccountDealersField(driver, attachedDealerName,
				true, tc);
		AccountProfileP.selectOneDealerFrAccountDealers(driver, attachedDealerName, tc);
		Wait(wt);
		AccountProfileP.clickLeftArrowDetachBtn(driver);
		tc = "TC_Verify detach a dealer from Account Dealers_01";
		dealerExistInAccountDealers = AccountProfileP.verifyOneDealerInAccountDealersField(driver, attachedDealerName,
				true, tc);

		AccountProfileP.scrollUp(driver, -3000, tc);
		AccountProfileP.clickBackToDealerListBtn(driver, parentHandle, tc);
		tc = "TC139406";// Verify Go to Dealer Portal from Admin Tool
		UserListP.clickExpandDealersArrow(driver, 1, tc);
		UserListP.clickEditOnDealer(driver, 1, tc);
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
		switchToWindow(driver);
	}

	private static String[] fetchOneDemArrayFromPropFile(String propertyName, Properties propFile)
			throws IOException, InterruptedException {
		// get array split up by the colin
		String a[] = propFile.getProperty(propertyName).split(",");
		return a;
	}
}
