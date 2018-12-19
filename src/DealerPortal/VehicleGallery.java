package DealerPortal;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class VehicleGallery extends Comlibs {
	private final WebDriver driver;

	public VehicleGallery(WebDriver driver) throws IOException {
		this.driver = driver;
		// String wh1=driver.getWindowHandle();
		String sPageTitle = "Vehicle Gallery";
		boolean existTitle = TitleDisplay(driver, sPageTitle);
		if (existTitle) {
			rwExcel("", true, "Page Title is displayed", sPageTitle);
		} else {
			rwExcel("", false, "Page title is NOT showing properly. Exceed time limit!",
					"The page title is NOT - " + sPageTitle);
		}
		if (!sPageTitle.equals(driver.getTitle())) {
			//
			System.out.println("Page title does not matche. Expected page title =" + sPageTitle);
		}
	}

	By xxx = By.xpath("");
	By xxxx = By.xpath("");
	By logo_leftUpLocator = By.xpath("//img[@alt='Autodata']");
	By pannelimageLocator = By.xpath("//img[@id='topImage']");
	By leftnextimageLocator = By.xpath("//div[@id='galleryLeft']/img");
	By rightnextimageLocator = By.xpath("//div[@id='galleryRight']/img");

	// next vehicle on hover button
	By leftnextvehiclehoverLocator = By.xpath("//div[@id='vehicleLeft']/div/img");
	By rightnextvehiclehoverLocator = By.xpath("//div[@id='vehicleRight']/div/img");
	// next vehicle on hover image (small)
	By leftnextvehiclehoverimageLocator = By.xpath("//div[@id='prevImage']/img");
	By rightnextvehiclehoverimageLocator = By.xpath("//div[@id='nextImage']/img");

	By backToInventoryBtnLocator = By.id("viewInventoryBtn");
	By dealerNameDropDownLocator = By.cssSelector("span.glyphicon.glyphicon-menu-down");
	By logOutLinkLocator = By.linkText("Logout");
	By vinStringLocator = By.cssSelector("div.col-sm-9.padding-left-20 > span"); // ok for Chrome but not for FF
	// By vinStringLocator =By.xpath("//*[@id='content']/div[2]/div[2]/div[2]/div[2]/div/div/div[1]/div[2]/span"); //still not working for FF

	// By urlLocator = By.xpath("//input[@id='previewImageUrl']"); // this is old one - url field
	// By urlLocator = By.xpath("//*[@id=\"content\"]/div[2]/div[2]/div[2]/div[2]/div/div/div[11]/div[2]/span"); // failed on Dec. 04, 2018
	By urlLocator_9 = By.xpath("/html/body/div/div[2]/div[2]/div[2]/div[2]/div/div/div[9]/div[2]/span");// New, OK on Dec. 04, 2018
	By urlLocator_11 = By.xpath("/html/body/div/div[2]/div[2]/div[2]/div[2]/div/div/div[11]/div[2]/span");// New, OK on Dec. 04, 2018
	By urlLocatorChrome = By.xpath("//*[@id=\"imagePreviewLink\"]");

	By urImageLocator = By.xpath("//img");

	By copyLocator = By.id("copyImageUrlBtn");

	By upLoadCustomPic = By.id("btnUploadPics");

	By statusDropBox = By.cssSelector("select.form-control"); // .select("Inactive");
																// .select("Active");

	By msgFiledLocator = By.xpath("//*[@id=\"marketingMessageText\"]");

	By saveChanges = By.id("btnUpdateMarketing");

	By exteriorsLocator = By.id("selectExterior");

	By interiorsLocator = By.id("selectInteriors");

	By selectImageBtn = By.xpath("//button[@id='selectImageBtn1']"); // start from Btn0
	By previewBtn = By.xpath("//button[@id='previewBtn1']"); // start from Btn0
	By deleteBtn = By.xpath("");// start from Btn0
	By newVehicleCbx = By.xpath("//*[@id=\"newVehicle\"]");
	// By =By.xpath();
	// By =By.xpath();
	// By =By.xpath();

	public VehicleGallery clickSaveChangesBtn(WebDriver driver, String tc) throws IOException {
		elementExist(driver, saveChanges, true, tc);
		driver.findElement(saveChanges).click();
		return this;
	}

	public VehicleGallery clickLeftNextVehicleBtn(WebDriver driver, String tc) throws IOException {
		rwExcel(tc, "Click on left arrow previous vehicle Btn", " ");
		driver.findElement(leftnextvehiclehoverLocator).click();
		return this;
	}

	public VehicleGallery clickRightNextVehicleBtn(WebDriver driver, String tc) throws IOException {
		WebElement we0 = driver.findElement(rightnextvehiclehoverLocator);
		String javaScript = "var evObj = document.createEvent('MouseEvents');"
				+ "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
				+ "arguments[0].dispatchEvent(evObj);";
		((JavascriptExecutor) driver).executeScript(javaScript, we0);
		driver.findElement(rightnextvehiclehoverLocator).click();
		return this;
	}

	public VehicleGallery clickLeftNextImageBtn(WebDriver driver, String tc) throws IOException {
		WebElement we0 = driver.findElement(leftnextimageLocator);
		String javaScript = "var evObj = document.createEvent('MouseEvents');"
				+ "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
				+ "arguments[0].dispatchEvent(evObj);";
		((JavascriptExecutor) driver).executeScript(javaScript, we0);
		driver.findElement(leftnextimageLocator).click();
		return this;
	}

	public VehicleGallery clickRightNextImageBtn(WebDriver driver, String tc) throws IOException {
		WebElement we0 = driver.findElement(rightnextimageLocator);
		String javaScript = "var evObj = document.createEvent('MouseEvents');"
				+ "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
				+ "arguments[0].dispatchEvent(evObj);";
		((JavascriptExecutor) driver).executeScript(javaScript, we0);
		driver.findElement(rightnextimageLocator).click();
		return this;
	}

	public ImageGallery clickBackToInventoryBtn(WebDriver driver) throws IOException {
		driver.findElement(backToInventoryBtnLocator).click();
		return new ImageGallery(driver);
	}

	public AUTOpxLogin clickLogout(WebDriver driver) throws IOException {
		driver.findElement(dealerNameDropDownLocator).click();
		driver.findElement(logOutLinkLocator).click();
		return new AUTOpxLogin(driver);
	}

	public void verifyLoadPannelImage(WebDriver driver, String tc) throws IOException {
		Wait(1);
		VerifyImageLoaded(driver, pannelimageLocator, tc);
		// return new AUTOpxLogin(driver);
	}

	public void verifyLoadNextControlImage(WebDriver driver, String tc) throws IOException {
		rwExcel(tc, "Verify Load Next Control Image", " ");
		VerifyImageLoaded(driver, rightnextvehiclehoverimageLocator, tc);
	}

	public void verifyLoadPreviousControlImage(WebDriver driver, String tc) throws IOException {
		rwExcel(tc, "Verify Load Previous Control Image", " ");
		VerifyImageLoaded(driver, leftnextvehiclehoverimageLocator, tc);
	}

	public void verifyLoadURLImage(WebDriver driver, String tc) throws IOException {
		rwExcel(tc, "Verify URL Copy Image", " ");
		boolean loadImage = false;
		try {
			loadImage = elementExist(driver, urImageLocator, true, tc);
		} catch (Exception e) {
			loadImage = false;
		}

		if (loadImage) {
			VerifyImageLoaded(driver, urImageLocator, tc);
		} else {
			rwExcel(tc, false, "Verify URL Copy Image", "Image does not show");
		}
	}

	public void verifyUplaodCustomPicsBtnExist(WebDriver driver, boolean status, String tc) throws IOException {
		rwExcel(tc, "Verify UPLOAD CUSTOM PICTURE(S) button", " ");
		if (status) {
			if (elementExist(driver, upLoadCustomPic, status, tc)) {
				rwExcel(tc, true, "Verify UPLOAD CUSTOM PICTURE(S) button", "UPLOAD CUSTOM PICTURE(S) button exists. ");
			} else {
				rwExcel(tc, false, "Verify UPLOAD CUSTOM PICTURE(S) button",
						"UPLOAD CUSTOM PICTURE(S) button dose not exist. ");
			}
		} else {
			if (elementExist(driver, upLoadCustomPic, status, tc)) {
				rwExcel(tc, false, "Verify UPLOAD CUSTOM PICTURE(S) button",
						"UPLOAD CUSTOM PICTURE(S) button exists as unexpected ");
			} else {
				rwExcel(tc, true, "Verify UPLOAD CUSTOM PICTURE(S) button",
						"UPLOAD CUSTOM PICTURE(S) button dose not exist as expected ");
			}
		}
	}

	public VehicleGallery hoverOnLeftArrowPreviousVehicleBtn(WebDriver driver, String tc) throws IOException {
		rwExcel(tc, "Hover On left arrow previous vehicle Btn", " ");
		WebElement we0 = driver.findElement(leftnextvehiclehoverLocator);
		String javaScript = "var evObj = document.createEvent('MouseEvents');"
				+ "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
				+ "arguments[0].dispatchEvent(evObj);";
		((JavascriptExecutor) driver).executeScript(javaScript, we0);
		return this;
	}

	public VehicleGallery hoverOnRightArrowNextVehicleBtn(WebDriver driver, String tc) throws IOException {
		rwExcel(tc, "Hover On right arrow next vehicle Btn", " ");
		WebElement we0 = driver.findElement(rightnextvehiclehoverLocator);
		String javaScript = "var evObj = document.createEvent('MouseEvents');"
				+ "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
				+ "arguments[0].dispatchEvent(evObj);";
		((JavascriptExecutor) driver).executeScript(javaScript, we0);
		return this;
	}

	public void clickUploadCustomPicturesBtn(WebDriver driver, String tc) throws IOException {
		elementExist(driver, upLoadCustomPic, true, tc);
		driver.findElement(upLoadCustomPic).click();
	}

	public VehicleGallery inputMessage(WebDriver driver, String msg, String tc) throws IOException {
		elementExist(driver, msgFiledLocator, true, tc);
		driver.findElement(msgFiledLocator).clear();
		driver.findElement(msgFiledLocator).sendKeys(msg);
		return this;
	}

	public String getURLLink(WebDriver driver, String tc) throws IOException {
		elementExist(driver, urlLocator_9, true, tc);
		// String urlLink = driver.findElement(urlLocator).getAttribute("value");
		// .getText("placeholder");
		String urlLink = driver.findElement(urlLocator_9).getText();
		return urlLink;
	}

	public void clickURLLink(WebDriver driver, String envBrowser, String tc) throws IOException {
		Wait(2);
		try {
			if (envBrowser.equalsIgnoreCase("Chrome")) {
				elementExist(driver, urlLocatorChrome, true, tc);
				driver.findElement(urlLocatorChrome).click();
			} else if (envBrowser.equalsIgnoreCase("FireFox")) {
				elementExist(driver, urlLocator_9, true, tc);
				driver.findElement(urlLocator_9).click();
			} else {
				elementExist(driver, urlLocatorChrome, true, tc);
				driver.findElement(urlLocatorChrome).click();
			}
		} catch (Exception e) {
			Wait(2);
			tc = tc + "_11";
			if (envBrowser.equalsIgnoreCase("Chrome")) {
				elementExist(driver, urlLocatorChrome, true, tc);
				driver.findElement(urlLocatorChrome).click();
			} else if (envBrowser.equalsIgnoreCase("FireFox")) {
				elementExist(driver, urlLocator_11, true, tc);
				driver.findElement(urlLocator_11).click();
			} else {
				elementExist(driver, urlLocatorChrome, true, tc);
				driver.findElement(urlLocatorChrome).click();
			}
			Wait(2);
		}

	}

	public String retriveVIN(WebDriver driver) throws IOException {
		Wait(1);
		String vinText = driver.findElement(vinStringLocator).getText();
		return vinText;
	}

	public void uploadCustomPicture(WebDriver driver, String vehicleImage) throws IOException {

		// driver.findElement(By.id("btnUploadPics")).sendKeys("H:\\My Documents\\AutoPix\\QA\\Car_Images\\Old_Civic.png");
		driver.findElement(By.id("btnUploadPics")).sendKeys(vehicleImage);

	}

	public void uploadCustomPicture01(WebDriver driver, String vehicleImage) throws IOException, AWTException {
		// Here is same function as uploadCustomPicture
		// driver.findElement(By.id("btnUploadPics")).sendKeys("H:\\My Documents\\AutoPix\\QA\\Car_Images\\Old_Civic.png");
		driver.findElement(By.id("btnUploadPics")).click();
		Wait(1);
		// Don't try to debug below code
		StringSelection ss = new StringSelection(vehicleImage);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		Wait(1);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Wait(1);
		System.out.println("Load file!");
	}

	public void clickOKwithWindowAlert() throws AWTException {
		Wait(1);
		// String vehicleImage="";
		// //Don't try to debug below code
		// StringSelection ss = new StringSelection(vehicleImage);
		// Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		Robot robot = new Robot();
		try {
			robot.keyPress(KeyEvent.VK_ACCEPT);
			robot.keyRelease(KeyEvent.VK_ACCEPT);

		} catch (Throwable e) {
			System.out.println("0 Error to Accept the alert.");
		}
		// robot.keyPress(KeyEvent.VK_CONTROL);
		// robot.keyPress(KeyEvent.VK_V);
		// robot.keyRelease(KeyEvent.VK_V);
		// robot.keyRelease(KeyEvent.VK_CONTROL);
		// robot.keyPress(KeyEvent.VK_ENTER);
		// Wait(1);
		// robot.keyRelease(KeyEvent.VK_ENTER);
		Wait(1);
		System.out.println("Click Accept!");
	}

	public void clickOKwithWindowAlert1() throws AWTException {
		Wait(1);
		// String vehicleImage="";
		// //Don't try to debug below code
		// StringSelection ss = new StringSelection(vehicleImage);
		// Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_SPACE);
			robot.keyRelease(KeyEvent.VK_SPACE);
		} catch (Throwable e) {
			System.out.println("1 Error to Accept the alert.");
		}

		// robot.keyPress(KeyEvent.VK_CONTROL);
		// robot.keyPress(KeyEvent.VK_V);
		// robot.keyRelease(KeyEvent.VK_V);
		// robot.keyRelease(KeyEvent.VK_CONTROL);
		// robot.keyPress(KeyEvent.VK_ENTER);
		// Wait(1);
		// robot.keyRelease(KeyEvent.VK_ENTER);
		Wait(3);
		System.out.println("Click Space!");
	}

	public void clickOKwithWindowAlert2() throws AWTException {
		Wait(1);
		// String vehicleImage="";
		// //Don't try to debug below code
		// StringSelection ss = new StringSelection(vehicleImage);
		// Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_O);
			robot.keyRelease(KeyEvent.VK_O);
		} catch (Throwable e) {
			System.out.println("2 Error to Accept the alert.");
		}

		// robot.keyPress(KeyEvent.VK_CONTROL);
		// robot.keyPress(KeyEvent.VK_V);
		// robot.keyRelease(KeyEvent.VK_V);
		// robot.keyRelease(KeyEvent.VK_CONTROL);
		// robot.keyPress(KeyEvent.VK_ENTER);
		// Wait(1);
		// robot.keyRelease(KeyEvent.VK_ENTER);
		Wait(3);
		System.out.println("Click O key!");
	}

	public String getCurrentDateTime(int hoursAdd, String serverName, String dbName, String userName, String password)
			throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection conn = DriverManager.getConnection("jdbc:sqlserver://" + serverName + ";user=" + userName
				+ ";password=" + password + ";database=" + dbName);

		System.out.println("test");

		Statement sta = conn.createStatement();
		String Sql = "select dateadd(HOUR, (" + hoursAdd + "), getdate()) as time_added";
		String currentDateTime = "";
		ResultSet rs = sta.executeQuery(Sql);
		int icolumn = rs.getRow();
		while (rs.next()) {
			icolumn = rs.getRow();
			currentDateTime = rs.getString("time_added");
			System.out.println("Row =" + icolumn);
		}
		rs.close();
		sta.close();
		conn.close();
		System.out.println("SQL Server current data and time =" + currentDateTime);
		return currentDateTime;
	}

	// select dateadd(HOUR, (-3), getdate()) as time_added,
	// getdate() as curr_date

	// SELECT ImageGUID
	// FROM dbo.VT03_RenderedImage
	// WHERE (CreatedDT > CONVERT(DATETIME, '2016-07-15 21:18:58.617', 102)) AND (VehGUID = 'b5d9541b-ebdf-4503-9412-ad1978f2f16f')

	public String getImageGUID(String dlrCode, String sVin, String vehGUID, String currentDateTimeString,
			String serverName, String dbName, String userName, String password)
			throws ClassNotFoundException, SQLException, IOException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		// Connection conn = DriverManager.getConnection("jdbc:sqlserver://LNOC-Q13V-MSQ1.autodata.org;user=VDVIWebServicesUserQA;password=HDuMy873JRFpkkU9;database=VDVI_Master");
		Connection conn = DriverManager.getConnection("jdbc:sqlserver://" + serverName + ";user=" + userName
				+ ";password=" + password + ";database=" + dbName);

		System.out.println("test. Current Date Time =" + currentDateTimeString);

		Statement sta = conn.createStatement();
		String Sql = "SELECT ImageGUID FROM dbo.VT03_RenderedImage WHERE (CreatedDT > CONVERT(DATETIME, '"
				+ currentDateTimeString + "', 102)) AND (VehGUID = '" + vehGUID + "')";
		String imageGUID = "";
		ResultSet rs = sta.executeQuery(Sql);
		int icolumn = rs.getRow();
		while (rs.next()) {
			icolumn = rs.getRow();
			imageGUID = rs.getString("ImageGUID");
			System.out.println("Row =" + icolumn);
		}
		if (icolumn == 1) {
			System.out.println("imageGUID=\n" + imageGUID);
			// rs.getString("VehGUID");
			// String vGUID = rs.getString("VehGUID");
		} else {
			System.out.println("No any ImageGUID or more than one\n");
			// imageGUID = "error!";
			rwExcel("", false, "GetImageGUID", "No any ImageGUID or more than one! ");
		}
		rs.close();
		sta.close();
		conn.close();
		return imageGUID;
	}

	public VehicleGallery clickPreviewBtn(WebDriver driver, String imageGUIDString, String vGUID, String tc)
			throws IOException {
		String customDealerImage = "Custom Dealer Image";
		// By.xpath("//div[@id='renderedVehList']/div/div/div");
		// By SelectBtn = By.xpath("//button[@id='selectImageBtn" + vGUID + "']"); // ok

		// By PreviewBtn = By.xpath("//button[@id='previewBtn10']");

		// By allImagesLocator=By.xpath("//div[@id='content']/div[2]/div[2]/div[4]/div[2]");
		// By allImagesLocator=By.cssSelector("div.veh-img-tile-hover-container");//load 15
		By allImagesLocator = By.xpath("//div[@id='content']/div[2]/div[2]/div[4]/div[2]/div");// ok for finding custom dealer iamge
		// By allImagesLocator=By.xpath("");

		boolean selected = false;
		int listText1;
		List<WebElement> listItems = driver.findElements(allImagesLocator);

		int vinsCount = listItems.size();
		for (int i = 0; i < vinsCount; i++) {
			// String text0 = listItems.get(i).getText();
			listText1 = listItems.get(i).getText().indexOf(customDealerImage);
			if (listText1 >= 0) {
				By PreviewBtn = By.xpath("//*[@id='previewBtn" + i + "']"); // //*[@id="previewBtn10"] - use chrome F12, find the element and right click to select "copy xpath"
				By hoverOnLocatori = By.xpath("//div[@id='" + i + "']/div/div/div");
				// By previewBtn=By.xpath("//*[@id=\"previewBtn"+i+"\"]");
				String data_image_guid = driver.findElement(PreviewBtn).getAttribute("data-image-guid");
				System.out.println("data_image_guid =" + data_image_guid);
				if (data_image_guid.equalsIgnoreCase(imageGUIDString)) {
					hoverOnWebElement(driver, driver.findElement(hoverOnLocatori));
					if (elementExist(driver, PreviewBtn, true, tc)) {
						driver.findElement(PreviewBtn).click();
						selected = true;
						break;
					} else {
						System.out.println("imageGUID =" + customDealerImage + " Is NOT Found");
						rwExcel(tc, false,
								"Hover on VIN " + customDealerImage + " and click on SELECT VEHICLE. VehGUID=" + vGUID,
								"The vehicle's VehGUID - " + vGUID + " - does NOT exist. ");
					}
				} else {
					System.out.println("imageGUID =" + customDealerImage + " Is NOT Found");
				}
			} else {
				System.out.println("imageGUID =" + customDealerImage + " Is NOT Found");
				rwExcel(tc, false,
						"Hover on VIN " + customDealerImage + " and click on SELECT VEHICLE. VehGUID=" + vGUID,
						"The vehicle's imageGUID - " + imageGUIDString + " - does NOT exist. ");
			}
		}
		if (!selected) {
			rwExcel(tc, false,
					"Hover on Custom Dealer Image (imageGUID) " + customDealerImage
							+ " title picture and click on SELECT VEHICLE button",
					"The Custom Dealer Image (imageGUID) - " + customDealerImage
							+ " - does NOT exist or is NOT selected");
		}
		return this;
	}

	public VehicleGallery clickPreviewBtnOrigianlWorksforFirstCustomPic(WebDriver driver, String imageGUIDString,
			String vGUID, String tc) throws IOException {
		String customDealerImage = "Custom Dealer Image";
		// By.xpath("//div[@id='renderedVehList']/div/div/div");
		// By SelectBtn = By.xpath("//button[@id='selectImageBtn" + vGUID + "']"); // ok

		// By PreviewBtn = By.xpath("//button[@id='previewBtn10']");

		// By allImagesLocator=By.xpath("//div[@id='content']/div[2]/div[2]/div[4]/div[2]");
		// By allImagesLocator=By.cssSelector("div.veh-img-tile-hover-container");//load 15
		By allImagesLocator = By.xpath("//div[@id='content']/div[2]/div[2]/div[4]/div[2]/div");// ok for finding custom dealer iamge
		// By allImagesLocator=By.xpath("");

		boolean selected = false;
		int listText1;
		List<WebElement> listItems = driver.findElements(allImagesLocator);

		int vinsCount = listItems.size();
		for (int i = 0; i < vinsCount; i++) {
			// String text0 = listItems.get(i).getText();
			listText1 = listItems.get(i).getText().indexOf(customDealerImage);
			if (listText1 >= 0) {
				System.out.println("customDealerImage =" + customDealerImage + " Found");
				// By PreviewBtn = By.xpath("//button[@id='previewBtn" + i + "']");////button[@id='previewBtn10']
				By PreviewBtn = By.xpath("//*[@id='previewBtn" + i + "']"); // //*[@id="previewBtn10"] - use chrome F12, find the element and right click to select "copy xpath"
				By hoverOnLocatori = By.xpath("//div[@id='" + i + "']/div/div/div");
				// hoverOnWebElement(driver, listItems.get(i));
				hoverOnWebElement(driver, driver.findElement(hoverOnLocatori));
				if (elementExist(driver, PreviewBtn, true, tc)) {
					driver.findElement(PreviewBtn).click();
					selected = true;
					break;
				} else {
					System.out.println("imageGUID =" + customDealerImage + " Is NOT Found");
					rwExcel(tc, false,
							"Hover on VIN " + customDealerImage + " and click on SELECT VEHICLE. VehGUID=" + vGUID,
							"The vehicle's VehGUID - " + vGUID + " - does NOT exist. ");
				}
			}
		}
		if (!selected) {
			rwExcel(tc, false,
					"Hover on Custom Dealer Image (imageGUID) " + customDealerImage
							+ " title picture and click on SELECT VEHICLE button",
					"The Custom Dealer Image (imageGUID) - " + customDealerImage
							+ " - does NOT exist or is NOT selected");
		}
		return this;
	}

	public VehicleGallery clickPreviewBtnWithImageGUID(WebDriver driver, String imageGUIDString, String vGUID,
			String tc) throws IOException {
		// This works fine but clickSelectImageBtn can not use the ImageGUID so do not use this for the time being.

		String customDealerImage = "Custom Dealer Image";
		// By.xpath("//div[@id='renderedVehList']/div/div/div");
		// By SelectBtn = By.xpath("//button[@id='selectImageBtn" + vGUID + "']"); // ok

		// By PreviewBtn = By.xpath("//button[@id='previewBtn10']");

		// By allImagesLocator=By.xpath("//div[@id='content']/div[2]/div[2]/div[4]/div[2]");
		// By allImagesLocator=By.cssSelector("div.veh-img-tile-hover-container");//load 15
		By allImagesLocator = By.xpath("//div[@id='content']/div[2]/div[2]/div[4]/div[2]/div");// ok for finding custom dealer iamge
		// By allImagesLocator=By.xpath("");

		boolean selected = false;
		int listText1;
		List<WebElement> listItems = driver.findElements(allImagesLocator);

		int vinsCount = listItems.size();
		for (int i = 0; i < vinsCount; i++) {
			// String text0 = listItems.get(i).getText();
			listText1 = listItems.get(i).getText().indexOf(customDealerImage);
			if (listText1 >= 0) {
				System.out.println("customDealerImage =" + customDealerImage + " Found");
				By PreviewBtn = By.xpath("//button[@id='previewBtn" + i + "']");

				String textImageGUID = driver.findElement(PreviewBtn).getAttribute("data-image-guid");// .getAttribute(data-image-guid).;
				if (textImageGUID.equalsIgnoreCase(imageGUIDString)) {
					System.out.println("imageGUIDString =" + imageGUIDString + " Found");
					By hoverOnLocatori = By.xpath("//div[@id='" + i + "']/div/div/div");
					// hoverOnWebElement(driver, listItems.get(i));
					hoverOnWebElement(driver, driver.findElement(hoverOnLocatori));

					if (elementExist(driver, PreviewBtn, true, tc)) {
						driver.findElement(PreviewBtn).click();
						selected = true;
						break;
					} else {
						System.out.println("imageGUID =" + customDealerImage + " Is NOT Found");
						rwExcel(tc, false,
								"Hover on VIN " + customDealerImage + " and click on SELECT VEHICLE. VehGUID=" + vGUID,
								"The vehicle's VehGUID - " + vGUID + " - does NOT exist. ");
					}

				} else {
					System.out.println("imageGUIDString =" + imageGUIDString + " NOT Found");
				}
			}
		}
		if (!selected) {
			rwExcel(tc, false,
					"Hover on Custom Dealer Image (imageGUID) " + customDealerImage
							+ " title picture and click on SELECT VEHICLE button",
					"The Custom Dealer Image (imageGUID) - " + customDealerImage
							+ " - does NOT exist or is NOT selected");
		}
		return this;
	}

	public VehicleGallery clickSelectImageBtnOriginalWorks(WebDriver driver, String imageGUIDString, String vGUID,
			String tc) throws IOException {
		String customDealerImage = "Custom Dealer Image";
		// By.xpath("//div[@id='renderedVehList']/div/div/div");
		// By SelectBtn = By.xpath("//button[@id='selectImageBtn" + vGUID + "']"); // ok

		// By PreviewBtn = By.xpath("//button[@id='previewBtn10']");

		// By allImagesLocator=By.xpath("//div[@id='content']/div[2]/div[2]/div[4]/div[2]");
		// By allImagesLocator=By.cssSelector("div.veh-img-tile-hover-container");//load 15
		By allImagesLocator = By.xpath("//div[@id='content']/div[2]/div[2]/div[4]/div[2]/div");// ok for finding custom dealer iamge
		// By allImagesLocator=By.xpath("");

		boolean selected = false;
		int listText1;
		List<WebElement> listItems = driver.findElements(allImagesLocator);

		int vinsCount = listItems.size();
		for (int i = 0; i < vinsCount; i++) {
			// String text0 = listItems.get(i).getText();
			listText1 = listItems.get(i).getText().indexOf(customDealerImage);
			if (listText1 >= 0) {
				System.out.println("customDealerImage =" + customDealerImage + " Found");
				By SelectImageBtn = By.xpath("//button[@id='selectImageBtn" + i + "']");
				By hoverOnLocatori = By.xpath("//div[@id='" + i + "']/div/div/div");
				// hoverOnWebElement(driver, listItems.get(i));
				hoverOnWebElement(driver, driver.findElement(hoverOnLocatori));

				if (elementExist(driver, SelectImageBtn, true, tc)) {

					driver.findElement(SelectImageBtn).click();
					selected = true;
					break;
				} else {
					System.out.println("imageGUID =" + customDealerImage + " Is NOT Found");
					rwExcel(tc, false,
							"Hover on VIN " + customDealerImage + " and click on SELECT VEHICLE. VehGUID=" + vGUID,
							"The vehicle's VehGUID - " + vGUID + " - does NOT exist. ");
				}
			}
		}
		if (!selected) {
			rwExcel(tc, false,
					"Hover on Custom Dealer Image (imageGUID) " + customDealerImage
							+ " title picture and click on SELECT VEHICLE button",
					"The Custom Dealer Image (imageGUID) - " + customDealerImage
							+ " - does NOT exist or is NOT selected");
		} else {
			rwExcel(tc, true, "Click on Select Image Button", "Button is clicked");
		}
		return this;
	}

	public VehicleGallery clickSelectImageBtn(WebDriver driver, String imageGUIDString, String vGUID, String tc)
			throws IOException {
		String customDealerImage = "Custom Dealer Image";
		// By.xpath("//div[@id='renderedVehList']/div/div/div");
		// By SelectBtn = By.xpath("//button[@id='selectImageBtn" + vGUID + "']"); // ok
		// By PreviewBtn = By.xpath("//button[@id='previewBtn10']");
		// By allImagesLocator=By.xpath("//div[@id='content']/div[2]/div[2]/div[4]/div[2]");
		// By allImagesLocator=By.cssSelector("div.veh-img-tile-hover-container");//load 15
		By allImagesLocator = By.xpath("//div[@id='content']/div[2]/div[2]/div[4]/div[2]/div");// ok for finding custom dealer iamge
		// By allImagesLocator=By.xpath("");
		boolean selected = false;
		int listText1;
		List<WebElement> listItems = driver.findElements(allImagesLocator);
		int vinsCount = listItems.size();
		for (int i = 0; i < vinsCount; i++) {
			// String text0 = listItems.get(i).getText();
			listText1 = listItems.get(i).getText().indexOf(customDealerImage);
			if (listText1 >= 0) {
				System.out.println("customDealerImage =" + customDealerImage + " Found");
				By PreviewBtn = By.xpath("//*[@id='previewBtn" + i + "']");
				By SelectImageBtn = By.xpath("//button[@id='selectImageBtn" + i + "']");
				By hoverOnLocatori = By.xpath("//div[@id='" + i + "']/div/div/div");
				String data_image_guid = driver.findElement(PreviewBtn).getAttribute("data-image-guid");
				System.out.println("data_image_guid =" + data_image_guid);

				if (data_image_guid.equalsIgnoreCase(imageGUIDString)) {
					hoverOnWebElement(driver, driver.findElement(hoverOnLocatori));
					if (elementExist(driver, SelectImageBtn, true, tc)) {
						driver.findElement(SelectImageBtn).click();
						selected = true;
						break;
					} else {
						System.out.println("imageGUID =" + customDealerImage + " Is NOT Found");
						rwExcel(tc, false,
								"Hover on VIN " + customDealerImage + " and click on SELECT VEHICLE. VehGUID=" + vGUID,
								"The vehicle's VehGUID - " + vGUID + " - does NOT exist. ");
					}
				} else {
					System.out.println("imageGUID =" + customDealerImage + " Is NOT Found");
				}
			}
		}
		if (!selected) {
			rwExcel(tc, false,
					"Hover on Custom Dealer Image (imageGUID) " + customDealerImage
							+ " title picture and click on SELECT VEHICLE button",
					"The Custom Dealer Image (imageGUID) - " + customDealerImage
							+ " - does NOT exist or is NOT selected");
		} else {
			rwExcel(tc, true, "Click on Select Image Button", "Button is clicked");
		}
		return this;
	}

	public VehicleGallery clickDeleteBtnOriginalWorks(WebDriver driver, String imageGUIDString, String vGUID, String tc)
			throws IOException {
		String customDealerImage = "Custom Dealer Image";
		// By.xpath("//div[@id='renderedVehList']/div/div/div");
		// By SelectBtn = By.xpath("//button[@id='selectImageBtn" + vGUID + "']"); // ok

		// By PreviewBtn = By.xpath("//button[@id='previewBtn10']");

		// By allImagesLocator=By.xpath("//div[@id='content']/div[2]/div[2]/div[4]/div[2]");
		// By allImagesLocator=By.cssSelector("div.veh-img-tile-hover-container");//load 15
		By allImagesLocator = By.xpath("//div[@id='content']/div[2]/div[2]/div[4]/div[2]/div");// ok for finding custom dealer iamge
		// By allImagesLocator=By.xpath("");

		boolean selected = false;
		int listText1;
		List<WebElement> listItems = driver.findElements(allImagesLocator);

		int vinsCount = listItems.size();
		for (int i = 0; i < vinsCount; i++) {
			// String text0 = listItems.get(i).getText();
			listText1 = listItems.get(i).getText().indexOf(customDealerImage);
			if (listText1 >= 0) {
				System.out.println("customDealerImage =" + customDealerImage + " Found");
				By SelectImageBtn = By.xpath("//button[@id='deleteBtn" + i + "']");
				By hoverOnLocatori = By.xpath("//div[@id='" + i + "']/div/div/div");
				// hoverOnWebElement(driver, listItems.get(i));
				hoverOnWebElement(driver, driver.findElement(hoverOnLocatori));
				if (elementExist(driver, SelectImageBtn, true, tc)) {
					driver.findElement(SelectImageBtn).click();
					selected = true;
					break;
				} else {
					System.out.println("imageGUID =" + customDealerImage + " Is NOT Found");
					rwExcel(tc, false,
							"Hover on VIN " + customDealerImage + " and click on SELECT VEHICLE. VehGUID=" + vGUID,
							"The vehicle's VehGUID - " + vGUID + " - does NOT exist. ");
				}
			}
		}
		if (!selected) {
			rwExcel(tc, false,
					"Hover on Custom Dealer Image (imageGUID) " + customDealerImage
							+ " title picture and click on SELECT VEHICLE button",
					"The Custom Dealer Image (imageGUID) - " + customDealerImage
							+ " - does NOT exist or is NOT selected");
		} else {
			rwExcel(tc, true, "Click on Delete button", "Button is clicked");
		}
		return this;
	}

	public VehicleGallery clickDeleteBtn(WebDriver driver, String imageGUIDString, String vGUID, String tc)
			throws IOException {
		String customDealerImage = "Custom Dealer Image";
		// By.xpath("//div[@id='renderedVehList']/div/div/div");
		// By SelectBtn = By.xpath("//button[@id='selectImageBtn" + vGUID + "']"); // ok
		// By PreviewBtn = By.xpath("//button[@id='previewBtn10']");
		// By allImagesLocator=By.xpath("//div[@id='content']/div[2]/div[2]/div[4]/div[2]");
		// By allImagesLocator=By.cssSelector("div.veh-img-tile-hover-container");//load 15
		By allImagesLocator = By.xpath("//div[@id='content']/div[2]/div[2]/div[4]/div[2]/div");// ok for finding custom dealer iamge
		// By allImagesLocator=By.xpath("");
		boolean selected = false;
		int listText1;
		List<WebElement> listItems = driver.findElements(allImagesLocator);
		int vinsCount = listItems.size();
		for (int i = 0; i < vinsCount; i++) {
			// String text0 = listItems.get(i).getText();
			listText1 = listItems.get(i).getText().indexOf(customDealerImage);
			if (listText1 >= 0) {
				System.out.println("customDealerImage =" + customDealerImage + " Found");
				By DeleteImageBtn = By.xpath("//button[@id='deleteBtn" + i + "']");
				By hoverOnLocatori = By.xpath("//div[@id='" + i + "']/div/div/div");
				String data_image_guid = driver.findElement(DeleteImageBtn).getAttribute("data-image-guid");
				System.out.println("data_image_guid =" + data_image_guid);
				if (data_image_guid.equalsIgnoreCase(imageGUIDString)) {
					hoverOnWebElement(driver, driver.findElement(hoverOnLocatori));
					if (elementExist(driver, DeleteImageBtn, true, tc)) {
						driver.findElement(DeleteImageBtn).click();
						selected = true;
						break;
					} else {
						System.out.println("Delete imageGUID =" + customDealerImage + " Is NOT Found");
						rwExcel(tc, false,
								"Hover on VIN " + customDealerImage + " and click on Delete VEHICLE. VehGUID=" + vGUID,
								"The vehicle's VehGUID - " + vGUID + " - does NOT exist. ");
					}
				} else {
					System.out.println("Delete imageGUID =" + customDealerImage + " Is NOT Found");
				}
			}
		}
		if (!selected) {
			rwExcel(tc, false,
					"Hover on Custom Dealer Image (imageGUID) " + customDealerImage
							+ " title picture and click on SELECT VEHICLE button",
					"The Custom Dealer Image (imageGUID) - " + customDealerImage
							+ " - does NOT exist or is NOT selected");
		} else {
			rwExcel(tc, true, "Click on Delete button", "Button is clicked");
		}
		return this;
	}

	public void acceptAlert(String tc, String alertType) throws IOException {
		boolean alertPass = false;
		Wait(1);
		try {
			driver.switchTo().alert().accept();// on the “Ok” button as soon as the pop up window appears.
			// driver.switchTo().alert().dismiss();// clicks on the “Cancel” button as soon as the pop up window appears.
			driver.switchTo().defaultContent();//
			alertPass = true;
			System.out.println("1. Accept the alert.");
			// rwExcel(tc, true, "Alert showing, Accept the alert =" + alertType, "Accetped successfully.");
		} catch (Throwable e) {
			alertPass = false;
			System.out.println("1. Failed to Accept the alert.");
			rwExcel(tc, false, "Alert showing, Accept the alert =" + alertType, "failed to accetp.");
		}
		// return alertPass;
	}

	public void verifyVinMsg(WebDriver driver, String vin, String vinMSG, int vinMsgFiledMaxLength, String tc)
			throws IOException {
		String tempVinMsg = driver.findElement(msgFiledLocator).getAttribute("value");
		String maxlenString = driver.findElement(msgFiledLocator).getAttribute("maxlength");
		int fieldMaxlen = Integer.parseInt(maxlenString);
		if (tempVinMsg.equals(vinMSG)) {
			rwExcel(tc, true, "Verify VIN message saved for VIN=" + vin,
					"The VIN message has been saved successfully.");
		} else {
			rwExcel(tc, false, "Verify VIN message saved for VIN=" + vin + " . The msg from site is: " + tempVinMsg,
					"The VIN message does not match the expected VIN message: " + vinMSG);
		}

		if (fieldMaxlen == vinMsgFiledMaxLength) {
			rwExcel(tc, true, "Verify VIN message field maxlength for VIN=" + vin,
					"The VIN message field maxlength matches the expected maxlenght=" + vinMsgFiledMaxLength);
		} else {
			rwExcel(tc, false, "Verify VIN message field maxlength for VIN=" + vin,
					"The expected maxlength is: " + vinMsgFiledMaxLength + ". The site defined is: " + fieldMaxlen);
		}

	}

	public int twoRandomNum() {
		Random r = new Random();
		int Low = 10;
		int High = 100;
		int Result = r.nextInt(High - Low) + Low;
		return Result;
	}

	public void selectNewVehicleCheckBox(WebDriver driver) {

		boolean checkBoxChecked = driver.findElement(newVehicleCbx).isSelected();
		if (checkBoxChecked) {
			driver.findElement(newVehicleCbx).click();
			driver.findElement(newVehicleCbx).click();
		} else {
			driver.findElement(newVehicleCbx).click();
		}
	}

	public void unSelectNewVehicleCheckBox(WebDriver driver) {

		boolean checkBoxChecked = driver.findElement(newVehicleCbx).isSelected();
		if (checkBoxChecked) {
			driver.findElement(newVehicleCbx).click();
		} else {
			driver.findElement(newVehicleCbx).click();
			driver.findElement(newVehicleCbx).click();
		}
	}

	public void verifyNewVehicleCheckBox(WebDriver driver, String vin, boolean isSelected, String tc)
			throws IOException {

		boolean checkBoxChecked = driver.findElement(newVehicleCbx).isSelected();
		if (isSelected && checkBoxChecked) {
			System.out.println("New vehicle checkbox is selected. VIN=" + vin + ". Pass!!");
			rwExcel(tc, true, "Verify New Vehicle Checkbox. VIN=" + vin, "Checkbox is selected.");
		} else if (!(isSelected) && !(checkBoxChecked)) {
			System.out.println("New vehicle checkbox is not selected as expected. VIN=" + vin + ". Pass!!");
			rwExcel(tc, true, "Verify New Vehicle Checkbox. VIN=" + vin, "Checkbox is not selected as expected.");
		} else {
			// Fail
			System.out.println("Verify New Vehicle Checkbox. VIN=" + vin + ". Fail!!!  Expected checkbox=" + isSelected
					+ ".  Checkbox in the browser=" + checkBoxChecked);
			rwExcel(tc, false, "Verify New Vehicle Checkbox. VIN=" + vin,
					"Expected checkbox=" + isSelected + ".  Checkbox in the browser=" + checkBoxChecked);
		}
	}
}
