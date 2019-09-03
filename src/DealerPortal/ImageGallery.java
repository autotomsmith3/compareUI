package DealerPortal;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ImageGallery extends Comlibs {
	private final WebDriver driver;

	public ImageGallery(WebDriver driver) throws IOException {
		this.driver = driver;
		// String wh1=driver.getWindowHandle();
		String sPageTitle = "Image Gallery";
		boolean existTitle = TitleDisplay(driver, sPageTitle);
		if (existTitle) {
			rwExcel("", true, "Page Title is displayed", sPageTitle);
		} else {
			rwExcel("", false, "Page title is NOT showing properly. Exceed time limit!",
					"The page title is NOT - " + sPageTitle);
		}
		if (!sPageTitle.equals(driver.getTitle())) {
			throw new IllegalStateException("The page title is NOT - " + sPageTitle);
		}
	}

	By dealerShipName = By.cssSelector("span");
	By dealerNameDropDownLocator = By.cssSelector("span.glyphicon.glyphicon-menu-down");
	By myDealerShipLinkLocator = By.linkText("My Dealership");
	By logOutLinkLocator = By.linkText("Logout");
	// VIN: 5GAKRBKD5FJ372688
	By allVINsLocator = By.xpath("//div[@id='renderedVehList']/div/div/div");
	By hoverOn_01_Locator = By.xpath("//div[@id='vehicle_7D4732D8-57E6-41FE-A9C7-2298D21B1758']/div/div/div"); // 14.
	// Image tile shows - // image // OK
	// By hoverOn_01_imagetileLocator =
	// By.xpath("//div[@id='tile_7D4732D8-57E6-41FE-A9C7-2298D21B1758']/img");
	// "Failed, No images found to Render" - // test // OK
	By hoverOn_01_Failed_NoImageLocator = By.xpath("//div[@id='tile_535498BB-00AD-46AA-AD84-987DFDDF5F4E']/div");
	// "Unable to decode VIN. Images cannot be generated." - // test // ok,
	By hoverOn_01_UnableToDecodeVIN_NoImageLocator = By
			.xpath("//div[@id='tile_0CF51F3F-D51F-4CD4-B1AA-CD29AEAF3A5A']/div");
	// "Please wait while we render this VIN." -// Please - // wait...
	By hoverOn_01_Wait_to_render_NoImageLocator = By
			.xpath("//div[@id='tile_D05AB45B-CAC3-46F4-AA70-4D4EBA0749D7']/div");

	// One for 3 texts
	// By hoverOn_01_failed_unable_wait_Text_locator =
	// By.xpath("//div[@id='tile_7D4732D8-57E6-41FE-A9C7-2298D21B1758']/div");

	// 5GAKRBKD5FJ372688
	By hoverOn_01_previewLocator = By.xpath("//button[@id='previewBtn7D4732D8-57E6-41FE-A9C7-2298D21B1758']");
	By hoverOn_01_selectLocator = By.xpath("//button[@id='selectImageBtn7D4732D8-57E6-41FE-A9C7-2298D21B1758']");
	By hoverOn_01_deselectLocator = By.xpath("//button[@id='selectImageBtn7D4732D8-57E6-41FE-A9C7-2298D21B1758']");
	// By reRenderInactiveBtn =
	// By.xpath("//button[@id='rerenderSelectedVehicles']");
	By reRenderActiveBtn = By.xpath("//button[@id='rerenderSelectedVehicles']");// -----
	By generateURLs = By.xpath("//button[@id='generateUrlBtn']");

	By newreRenderActiveBtn = By.xpath("//button[@id='rerenderSelectedVehicles']");

	By selectAll = By.id("selectAll");
	By selectAll_xpath = By.xpath("//*[@id=\"selectAll\"]");
	By selectNone = By.id("selectNone");
	By errorMsgLocator = By.xpath("//div[@id='vinFail']/span");// By.xpath("//div[@id='content']/div[2]/div/div[2]/span");// "An error occured during rendering. Please try again.";
	By errorMsgLocator1 = By.xpath("//div[@id='content']/div[2]/div/div[2]/span");// An error occured during rendering. Please try again.";
	By goodMsgLocator = By.xpath("//div[@id='vinSuccess']/span");// By.xpath("//div[@id='vinFail']/span");//By.cssSelector("span.messageBox"); div[@id='vinFail']/span; modified at home
	By goodMsgLocator1 = By.cssSelector("span.messageBox"); // This good msg shows under My Inventory Gallery
	// Fielters
	By searchByVinFieldLocator = By.id("searchVinOrStockNo");
	By filterTypeLocator = By.xpath("//*[@id=\"viewTypeInventoryBtn\"]");
	By typeNew = By.xpath("//*[@id='newVehicleContentPanel']/div[1]/div/div[2]");
	By typeUsed = By.xpath("//div[@id='newVehicleContentPanel']/div[2]/div/div[2]/span");
	By typeApply = By.xpath("//div[@id='filterNewVehicleModal']/div/div/div[3]/button");
	By typeClearBtn = By.xpath("//div[@id='filterNewVehicleModal']/div/div/div[3]/button[2]");
	By typeXBtn = By.xpath("//div[@id='filterNewVehicleModal']/div/div/div/button");

	By filterYearLocator = By.xpath("//*[@id='viewYearInventoryBtn']");
	By filterAllYears = By.xpath("//div[@id='yearContentPanel']/div/div");

	By yearApply = By.xpath("//div[5]/div/div/div[3]/button");
	By yearClearBtn = By.xpath("//div[@id='filterYearModal']/div/div/div[3]/button[2]");
	By yearXBtn = By.xpath("//div[@id='filterYearModal']/div/div/div/button");

	By filterMakeLocator = By.xpath("//*[@id=\"viewMakeInventoryBtn\"]");
	By filterMakeBuickLocator = By.xpath("//div[@id='makeContentPanel']/div[1]/div/div[2]/span");
	By filterMakeCadillacLocator = By.xpath("//div[@id='makeContentPanel']/div[2]/div/div[2]/span");
	By filterMakeGMCLocator = By.xpath("//div[@id='makeContentPanel']/div[9]/div/div[2]/span");
	By filterMakeChevroletLocator = By.xpath("//div[@id='makeContentPanel']/div[11]/div/div[2]/span");
	By makeApply = By.xpath("//div[@id='filterBrandModal']/div/div/div[3]/button");
	By makeClearBtn = By.xpath("//div[@id='filterBrandModal']/div/div/div[3]/button[2]");
	By makeXBtn = By.xpath("//div[@id='filterBrandModal']/div/div/div/button");

	By filterModelLocator = By.xpath("//*[@id=\"viewModelInventoryBtn\"]");// //*[@id="viewModelInventoryBtn"]
	By filterModelBuickLocator = By.xpath("//ul[@id='modelNavTab']/li[4]/a/span");
	By filterModelBuickAllLocator = By.xpath("//div[@id='modelBuickTabPanel']/div/div/label/input");
	By filterModelBuickEnclaveLocator = By.xpath("//div[@id='modelBuickTabPanel']/div[2]/div/label/input");
	By filterModelBuickEncoreLocator = By.xpath("//div[@id='modelBuickTabPanel']/div[3]/div/label/input");
	By filterModelBuickCascadaLocator = By.xpath("//div[@id='modelBuickTabPanel']/div[4]/div/label/input");

	By filterModelCadillacLocator = By.xpath("//ul[@id='modelNavTab']/li[2]/a/span");
	By filterModelCadillacAllLocator = By.xpath("//div[@id='modelCadillacTabPanel']/div/div/label/input");
	By filterModelCadillacEscaladeLocator = By.xpath("//div[@id='modelCadillacTabPanel']/div[2]/div/label/input");
	By filterModelGMCLocator = By.xpath("//ul[@id='modelNavTab']/li[3]/a/span");
	By filterModelGMCAllLocator = By.xpath("//div[@id='modelGMCTabPanel']/div/div/label/input");
	By filterModelChevroletLocator = By.xpath("//ul[@id='modelNavTab']/li[5]/a/span");
	By filterModelChevroletAllLocator = By.xpath("//div[@id='modelChevroletTabPanel']/div/div/label/input");
	By modelApply = By.xpath("//div[@id='filterModelModal']/div/div/div[3]/button");
	By modelClearBtn = By.xpath("//div[@id='filterModelModal']/div/div/div[3]/button[2]");
	By modelXBtn = By.xpath("//div[@id='filterModelModal']/div/div/div/button");

	By filterColorsLocator = By.xpath("//*[@id=\"viewColorInventoryBtn\"]");
	// By
	// filterAllColors=By.xpath("//div[@id='extColorTabPanel']/div");//By.xpath("//div[@id='extColorTabPanel']/div[10]/div/label");
	By exteriorTab = By.xpath("//ul[@id='filterNavTab']/li/a/span");
	By interiorTab = By.xpath("//ul[@id='filterNavTab']/li[2]/a/span");
	By exteriorAllColors = By.xpath("//div[@id='extColorTabPanel']/div");
	By interiorAllColors = By.xpath("//div[@id='intColorTabPanel']/div");

	By colorApply = By.xpath("//div[@id='filterColorModal']/div/div/div[3]/button");
	By colorClearBtn = By.xpath("//div[@id='filterColorModal']/div/div/div[3]/button[2]");
	By colorXBtn = By.xpath("//div[@id='filterColorModal']/div/div/div/button");

	By clearAllFiltersBtnLocator = By.id("clearAllFilters");
	By showAllLocator = By.id("showAll");
	By loadMoreVehicleBtnLocator = By.id("loadMoreVehicles");
	// ADD INVENTORY
	By addInventoryBtn = By.xpath("//*[@id=\"addInventoryBtn\"]/span[1]");// By.id("addInventoryBtn");
	By addInventoryAddBtn = By.xpath("//button[@id='addVinBtn']");
	By addInventoryCancelBtn = By.xpath("//div[@id='addVinModal']/div/div/div[3]/button[2]");
	By addInventoryXbtn = By.xpath("//div[@id='addVinModal']/div/div/div/button");
	By addInventoryEnterVinField = By.id("vin");// By.xpath("//form[@id='addVinForm']/div/input");
	// By searchVinOrStockNo = By.id("searchVinOrStockNo");
	By searchVinOrStockNo = By.xpath("//*[@id=\"searchVinOrStockNo\"]");
	By templatesBtnLocator = By.xpath("//*[@id=\"navbarTabs\"]/li[3]/a");

	// By
	// hoverOn_01_Locator=By.xpath("//div[@id='vehicle_0CF51F3F-D51F-4CD4-B1AA-CD29AEAF3A5A']/div/div/div");
	// //1. VIN= 1J8G6B8K77W628202
	// By hoverOn_01_previewLocator =
	// By.id("previewBtn0CF51F3F-D51F-4CD4-B1AA-CD29AEAF3A5A");
	// By
	// selectlocator=By.xpath("//button[@id='selectImageBtn0CF51F3F-D51F-4CD4-B1AA-CD29AEAF3A5A']");

	// By
	// hoverOn_01_Locator=By.xpath("//div[@id='vehicle_3F71E5F5-7C8C-4884-ACD5-7C25CFC93260']/div/div/div");
	// //2. VIN= WVGZZZ7P6ED003548
	// By hoverOn_01_previewLocator =
	// By.xpath("//button[@id='previewBtn3F71E5F5-7C8C-4884-ACD5-7C25CFC93260']");
	// By
	// hoverOn_01_selectLocator=By.xpath("//button[@id='selectImageBtn3F71E5F5-7C8C-4884-ACD5-7C25CFC93260']");

	By backToInventoryBtnLocator = By.id("viewInventoryBtn");
	By addInventorybtnLocator = By.id("addInventoryBtn");

	// Help section
	By helpMarkLocator = By.cssSelector("span.glyphicon.glyphicon-question-sign");
	// By helpMarkLocator=By.xpath("//*[@id='bs-example-navbar-collapse-1']/ul[2]/li[2]/a");
	By ContactSupportLocator = By.xpath("//a[contains(text(),'Contact Support')]");
	By SystemHelpLocator = By.xpath("//a[contains(text(),'System Help')]");
	By ReportIssueLocator = By.xpath("//a[contains(text(),'Report Issue')]");
	// In popup window:
	By ContactSupportTile01 = By.xpath("//*[@id='reportIssueModalLabel']");// from chrome
	// By ContactSupportTile01 = By.id("reportIssueModalLabel");
	By ContactSupportLine102 = By.xpath("//*[@id='reportIssueModal']/div/div/div[2]/form/div/p[1]");
	By ContactSupportLine203 = By.xpath("//*[@id='reportIssueModal']/div/div/div[2]/form/div/p[2]");
	By ContactSupportMsgField04 = By.xpath("//*[@id='supportMessage']");
	By ContactSupportSubmitBtn05 = By.xpath("//*[@id='sendSupportBtn']");
	By ContactSupportCancelBtn06 = By.xpath("//*[@id='reportIssueModal']/div/div/div[2]/div/button[2]");
	By ContactSupportLine3to5_07 = By.xpath("//*[@id='reportIssueModal']/div/div/div[3]/div");
	By ContactSupportEmailLine308 = By.xpath("//*[@id='reportIssueModal']/div/div/div[3]/div/span[2]/a");
	By ContactSupportTelLine409 = By.xpath("//*[@id='reportIssueModal']/div/div/div[3]/div/span[3]");
	By dealerShipInfoBtnLocator = By.xpath("//*[@id='navbarTabs']/li[2]/a");
	By inventoryGalleryBtnLocator = By.xpath("//*[@id='navbarTabs']/li[1]/a");
	By listViewBtnLocator = By.xpath("//*[@id=\"listViewBtn\"]/span");
	By gridViewBtnLocator = By.xpath("//*[@id=\"gridViewBtn\"]/span");
	By backgroundsLocator = By.xpath("//*[@id=\"navbarTabs\"]/li[4]/a");

	static int allVinNums = 0;
	static int allImageNums = 0;

	public DealerProfile gotoMyDealerShip(WebDriver driver) throws IOException {
		driver.findElement(dealerNameDropDownLocator).click();
		driver.findElement(myDealerShipLinkLocator).click();
		return new DealerProfile(driver);
	}

	public DealerProfile clickDealerShipInfoBtn(WebDriver driver) throws IOException {
		driver.findElement(dealerShipInfoBtnLocator).click();
		return new DealerProfile(driver);
	}

	public ImageGallery clickInventoryGalleryBtn(WebDriver driver) throws IOException {
		driver.findElement(inventoryGalleryBtnLocator).click();
		return new ImageGallery(driver);
	}

	public AUTOpxLogin clickLogout(WebDriver driver) throws IOException {
		if (elementExist(driver, dealerNameDropDownLocator, true, "")) {
			driver.findElement(dealerNameDropDownLocator).click();// problem here, need to investigate AUTOpxController main (used to be line 377)
		}
		// driver.findElement(dealerNameDropDownLocator).click();
		if (elementExist(driver, logOutLinkLocator, true, "")) {
			driver.findElement(logOutLinkLocator).click();
		}
		// driver.findElement(logOutLinkLocator).click();
		return new AUTOpxLogin(driver);
	}

	public ImageGallery clickBackToInventoryBtn(WebDriver driver) throws IOException {
		driver.findElement(backToInventoryBtnLocator).click();
		return this;
	}

	public ImageGallery clickSelectBtn(WebDriver driver, String sVIN, String vGUID, String tc) throws IOException {
		// By allVINsLocator =
		// By.xpath("//div[@id='renderedVehList']/div/div/div");
		// By SelectBtn = By.xpath("//button[@id='selectImageBtn" + vGUID + "']"); // 6/13/2018 is ok in the new build
		By SelectBtn = By.xpath("//*[@id='selectImageBtn" + vGUID + "']/span[1]"); // new?
		boolean selected = false;
		int listText1;
		List<WebElement> listItems = driver.findElements(allVINsLocator);

		int vinsCount = listItems.size();
		for (int i = 0; i < vinsCount; i++) {
			String text0 = listItems.get(i).getText();
			listText1 = listItems.get(i).getText().indexOf(sVIN);
			if (listText1 > 0) {
				System.out.println("VIN =" + sVIN + " Found");
				hoverOnWebElement(driver, listItems.get(i));
				if (elementExist(driver, SelectBtn, true, tc)) {
					driver.findElement(SelectBtn).click();
					selected = true;
					break;
				} else {
					System.out.println("VIN =" + sVIN + " Is NOT Found");
					rwExcel(tc, false, "Hover on VIN " + sVIN + " and click on SELECT VEHICLE. VehGUID=" + vGUID,
							"The vehicle's VehGUID - " + vGUID + " - does NOT exist. ");
				}
			}
		}
		if (!selected) {
			rwExcel(tc, false, "Hover on VIN " + sVIN + " title picture and click on SELECT VEHICLE button",
					"The VIN - " + sVIN + " - does NOT exist or is NOT selected");
		}
		return this;
	}

	public ImageGallery clickDeSelectBtnOriginal(WebDriver driver, String sVIN, String vGUID, String tc)
			throws IOException {
		WebElement we0 = driver.findElement(hoverOn_01_Locator);
		hoverOnWebElement(driver, we0);
		driver.findElement(hoverOn_01_deselectLocator).click();
		return this;
	}

	public ImageGallery clickDeSelectBtn(WebDriver driver, String sVIN, String vGUID, String tc) throws IOException {
		// By allVINsLocator =
		// By.xpath("//div[@id='renderedVehList']/div/div/div");
		By deSelectLocator = By.xpath("//button[@id='selectImageBtn" + vGUID + "']"); // ok
		boolean selected = false;
		int listText1;
		List<WebElement> listItems = driver.findElements(allVINsLocator);

		int vinsCount = listItems.size();
		for (int i = 0; i < vinsCount; i++) {
			// String text0 = listItems.get(i).getText();
			listText1 = listItems.get(i).getText().indexOf(sVIN);
			if (listText1 > 0) {
				System.out.println("VIN =" + sVIN + " Found");
				hoverOnWebElement(driver, listItems.get(i));
				if (elementExist(driver, deSelectLocator, true, tc)) {
					driver.findElement(deSelectLocator).click();
					selected = true;
					break;
				} else {
					System.out.println("VIN =" + sVIN + " Is NOT Found");
					rwExcel(tc, false, "Hover on VIN " + sVIN + " and click on VIEW DETAILS button. VehGUID=" + vGUID,
							"The vehicle's VehGUID - " + vGUID + " - does NOT exist. ");
				}
			}
		}
		if (!selected) {
			rwExcel(tc, false, "Hover on VIN " + sVIN + " title picture and click on VIEW DETAILS button",
					"The VIN - " + sVIN + " - does NOT exist or is NOT selected");
		}
		return new ImageGallery(driver);
	}

	public VehicleGallery clickViewDetailsBtnOriginal(WebDriver driver) throws IOException {
		WebElement we0 = driver.findElement(hoverOn_01_Locator);
		hoverOnWebElement(driver, we0);
		driver.findElement(hoverOn_01_previewLocator).click();
		return new VehicleGallery(driver);
	}

	public VehicleGallery clickViewDetailsBtn(WebDriver driver, String sVIN, String vGUID, String tc)
			throws IOException {
		// By allVINsLocator =
		// By.xpath("//div[@id='renderedVehList']/div/div/div");
		By previewLocator = By.xpath("//button[@id='previewBtn" + vGUID + "']");

		boolean selected = false;
		int listText1;
		List<WebElement> listItems = driver.findElements(allVINsLocator);

		int vinsCount = listItems.size();
		for (int i = 0; i < vinsCount; i++) {
			String text0 = listItems.get(i).getText();
			listText1 = listItems.get(i).getText().indexOf(sVIN);
			if (listText1 > 0) {
				System.out.println("VIN =" + sVIN + " Found");
				hoverOnWebElement(driver, listItems.get(i));
				if (elementExist(driver, previewLocator, true, tc)) {
					driver.findElement(previewLocator).click();
					selected = true;
					break;
				} else {
					System.out.println("VIN =" + sVIN + " Is NOT Found");
					rwExcel(tc, false, "Hover on VIN " + sVIN + " and click on VIEW DETAILS button. VehGUID=" + vGUID,
							"The vehicle's VehGUID - " + vGUID + " - does NOT exist. ");
				}
			}
		}
		if (!selected) {
			rwExcel(tc, false, "Hover on VIN " + sVIN + " title picture and click on VIEW DETAILS button",
					"The VIN - " + sVIN + " - does NOT exist or is NOT selected");
		}
		return new VehicleGallery(driver);
	}

	public ImageGallery clickRerenderBtn(WebDriver driver, String render, String tc) throws IOException {
		if (render.equalsIgnoreCase("Yes")) {
			elementExist(driver, reRenderActiveBtn, true, tc);
			driver.findElement(reRenderActiveBtn).click();
		} else {
			System.out.println("Not click the Render button! \n");
		}
		return new ImageGallery(driver);
	}

	public ImageGallery clickSelectAllBtn(WebDriver driver, String tc) throws IOException {
		elementExist(driver, selectAll, true, tc);
		driver.findElement(selectAll).click();
		Wait(5);
		driver.findElement(selectAll_xpath).click();
		return new ImageGallery(driver);
	}

	public ImageGallery clickSelectNoneBtn(WebDriver driver, String tc) throws IOException {
		elementExist(driver, selectAll, true, tc);
		driver.findElement(selectNone).click();
		return new ImageGallery(driver);
	}

	public void inputVIN(WebDriver driver, String sVin, String tc) throws IOException {
		// Not stable. use EnterTextInSearch instead.
		elementExist(driver, searchByVinFieldLocator, true, tc);
		driver.findElement(searchByVinFieldLocator).clear();
		driver.findElement(searchByVinFieldLocator).sendKeys(sVin);
	}

	public ImageGallery clickTypeBtn(WebDriver driver, String tc) throws IOException {
		elementExist(driver, filterTypeLocator, true, tc);
		driver.findElement(filterTypeLocator).click();
		return new ImageGallery(driver);
	}

	public ImageGallery clickUsedBtn(WebDriver driver, String tc) throws IOException {
		elementExist(driver, typeUsed, true, tc);
		driver.findElement(typeUsed).click();
		return new ImageGallery(driver);
	}

	public ImageGallery clickNewBtn(WebDriver driver, String tc) throws IOException {
		elementExist(driver, typeNew, true, tc);
		driver.findElement(typeNew).click();
		return new ImageGallery(driver);
	}

	public ImageGallery clickTypeApplyBtn(WebDriver driver, String tc) throws IOException {
		elementExist(driver, typeApply, true, tc);
		driver.findElement(typeApply).click();
		return new ImageGallery(driver);
	}

	public ImageGallery clickTypeClearBtn(WebDriver driver, String tc) throws IOException {
		elementExist(driver, typeClearBtn, true, tc);
		driver.findElement(typeClearBtn).click();
		return new ImageGallery(driver);
	}

	public ImageGallery clickTypeXBtn(WebDriver driver, String tc) throws IOException {
		elementExist(driver, typeXBtn, true, tc);
		driver.findElement(typeXBtn).click();
		return new ImageGallery(driver);
	}

	public ImageGallery clickYearBtn(WebDriver driver, String tc) throws IOException {
		elementExist(driver, filterYearLocator, true, tc);
		driver.findElement(filterYearLocator).click();
		return new ImageGallery(driver);
	}

	public ImageGallery clickYearSelected(WebDriver driver, String sYear, String tc) throws IOException {
		boolean selected = false;
		String listText1;
		List<WebElement> listItems = driver.findElements(filterAllYears);

		int yearsCount = listItems.size();
		for (int i = 0; i < yearsCount; i++) {
			listText1 = listItems.get(i).getText();
			if (listText1.equalsIgnoreCase(sYear)) {
				listItems.get(i).click();
				// Try to catch the selected mark here:
				// if(listItems.get(i).isEnabled()){ //isEnabled, isDisplayed
				// only work for true, not false. isSelected does NOT work at
				// all.
				// System.out.println("Selected");
				// }else{
				// System.out.println("Not Selected");
				// }
				rwExcel(tc, true, "Click the Year " + sYear + " button", "The year - " + sYear + " - is selected");
				selected = true;
				break;
			}
		}
		if (!selected) {
			rwExcel(tc, false, "Click the Year " + sYear + " button", "The year - " + sYear + "- does NOT exist");
		}
		return new ImageGallery(driver);
	}

	public ImageGallery clickYearApplyBtn(WebDriver driver, String tc) throws IOException {
		elementExist(driver, yearApply, true, tc);
		driver.findElement(yearApply).click();
		return new ImageGallery(driver);
	}

	public ImageGallery clickYearClearBtn(WebDriver driver, String tc) throws IOException {
		elementExist(driver, yearClearBtn, true, tc);
		driver.findElement(yearClearBtn).click();
		return new ImageGallery(driver);
	}

	public ImageGallery clickYearXBtn(WebDriver driver, String tc) throws IOException {
		elementExist(driver, yearXBtn, true, tc);
		driver.findElement(yearXBtn).click();
		return new ImageGallery(driver);
	}

	public ImageGallery clickMakeBtn(WebDriver driver, String tc) throws IOException {
		elementExist(driver, filterMakeLocator, true, tc);
		driver.findElement(filterMakeLocator).click();
		return new ImageGallery(driver);
	}

	public ImageGallery clickMakeBrandBtn(WebDriver driver, String brand, String tc) throws IOException {
		String tempName;
		By makeContentPanel = By.xpath("//div[@id='makeContentPanel']/div/div/div[2]/span");
		elementExist(driver, makeContentPanel, true, tc);
		List<WebElement> makeList = driver.findElements(makeContentPanel);
		int makeCount = makeList.size();
		if (makeCount >= 1) {
			for (int i = 1; i < (makeCount + 1); i++) {
				tempName = driver.findElement(By.xpath("//div[@id='makeContentPanel']/div[" + i + "]/div/div[2]/span"))
						.getText();
				if (tempName.equalsIgnoreCase(brand)) {
					driver.findElement(By.xpath("//div[@id='makeContentPanel']/div[" + i + "]/div/div[2]/span"))
							.click();
					break;
				} else {
					// not found, go to the next one but if this is last report
					// cannot find the brand
					if (i == makeCount) {
						System.out.println("Brand name=" + brand + " is Not found!!!");
						rwExcel(tc, false, "Click the Make " + brand + " button",
								"Brand name=" + brand + " is Not found!!!");

					}
				}

			}

		} else {
			// ==0 list is empty
			System.out.println("Make list is empty!!!");
			rwExcel(tc, false, "Click the Make " + brand + " button", "Make list is empty!!!");
		}

		return new ImageGallery(driver);
	}

	public ImageGallery clickMakeCadillacBtn(WebDriver driver, String tc) throws IOException {
		elementExist(driver, filterMakeCadillacLocator, true, tc);
		driver.findElement(filterMakeCadillacLocator).click();
		return new ImageGallery(driver);
	}

	public ImageGallery clickMakeGBtn(WebDriver driver, String tc) throws IOException {
		elementExist(driver, filterMakeGMCLocator, true, tc);
		driver.findElement(filterMakeGMCLocator).click();
		return new ImageGallery(driver);
	}

	public ImageGallery clickMakeChevroletBtn(WebDriver driver, String tc) throws IOException {
		elementExist(driver, filterMakeChevroletLocator, true, tc);
		driver.findElement(filterMakeChevroletLocator).click();
		return new ImageGallery(driver);
	}

	public ImageGallery clickMakeApplyBtn(WebDriver driver, String tc) throws IOException {
		elementExist(driver, makeApply, true, tc);
		driver.findElement(makeApply).click();
		return new ImageGallery(driver);
	}

	public ImageGallery clickMakeClearBtn(WebDriver driver, String tc) throws IOException {
		elementExist(driver, makeClearBtn, true, tc);
		driver.findElement(makeClearBtn).click();
		return new ImageGallery(driver);
	}

	public ImageGallery clickMakeXBtn(WebDriver driver, String tc) throws IOException {
		elementExist(driver, makeXBtn, true, tc);
		driver.findElement(makeXBtn).click();
		return new ImageGallery(driver);
	}

	public ImageGallery clickModelBtn(WebDriver driver, String tc) throws IOException {
		elementExist(driver, filterModelLocator, true, tc);
		driver.findElement(filterModelLocator).click();
		return new ImageGallery(driver);
	}

	public void clickModelBuickTab(WebDriver driver, String tc) throws IOException {
		elementExist(driver, filterModelBuickLocator, true, tc);
		driver.findElement(filterModelBuickLocator).click();
	}

	public ImageGallery clickModelTab(WebDriver driver, String brand, String tc) throws IOException {
		String tempName;
		By modelContentPanel = By.xpath("//ul[@id='modelNavTab']/li/a/span");
		elementExist(driver, modelContentPanel, true, tc);
		List<WebElement> modelTabList = driver.findElements(modelContentPanel);
		int modelCount = modelTabList.size() / 2;
		if (modelCount >= 1) {
			for (int i = 1; i < (modelCount + 1); i++) {
				tempName = driver.findElement(By.xpath("//ul[@id='modelNavTab']/li[" + i + "]/a/span")).getText();
				if (tempName.equalsIgnoreCase(brand)) {
					driver.findElement(By.xpath("//ul[@id='modelNavTab']/li[" + i + "]/a/span")).click();
					break;
				} else {
					// not found, go to the next one but if this is last report
					// cannot find the brand
					if (i == modelCount) {
						System.out.println("Brand Tab name=" + brand + " is Not found!!!");
						rwExcel(tc, false, "Click the brand " + brand + " Tab",
								"Brand Tab name=" + brand + " is Not found!!!");

					}
				}

			}

		} else {
			// ==0 list is empty
			System.out.println("Model tab list is empty!!!");
			rwExcel(tc, false, "Click the Brand Tab " + brand + " button", "Model Tab list is empty!!!");
		}

		return new ImageGallery(driver);
	}

	public ImageGallery clickModelTrimCbx(WebDriver driver, String brand, String trim, String tc) throws IOException {
		String tempName;
		By trimContentPanel = By.xpath("//div[@id='model" + brand + "TabPanel']/div/div/label");
		elementExist(driver, trimContentPanel, true, tc);
		List<WebElement> trimcheckboxList = driver.findElements(trimContentPanel);
		int trimlCount = trimcheckboxList.size();
		if (trimlCount >= 1) {
			for (int i = 1; i < (trimlCount + 1); i++) {
				tempName = driver
						.findElement(By.xpath("//div[@id='model" + brand + "TabPanel']/div[" + i + "]/div/label"))
						.getText();
				if (tempName.equalsIgnoreCase(trim)) {
					driver.findElement(By.xpath("//div[@id='model" + brand + "TabPanel']/div[" + i + "]/div/label"))
							.click();
					break;
				} else {
					// not found, go to the next one but if this is last report
					// cannot find the brand
					if (i == trimlCount) {
						System.out.println("Trim checkbox name=" + trim + " is Not found!!!");
						rwExcel(tc, false, "Click the trim checkbox " + trim + " checkbox",
								"Trim checkbox name=" + trim + " is Not found!!!");

					}
				}

			}

		} else {
			// ==0 list is empty
			System.out.println("Model tab list is empty!!!");
			rwExcel(tc, false, "Click the Trim checkbox " + trim + " checkbox", "trim checkbox list is empty!!!");
		}

		return new ImageGallery(driver);
	}

	public void clickModelBuickAllCbx(WebDriver driver, String tc) throws IOException {
		elementExist(driver, filterModelBuickAllLocator, true, tc);
		driver.findElement(filterModelBuickAllLocator).click();
	}

	public void clickModelBuickEnclaveCbx(WebDriver driver, String tc) throws IOException {
		elementExist(driver, filterModelBuickEnclaveLocator, true, tc);
		driver.findElement(filterModelBuickEnclaveLocator).click();
	}

	public void clickModelBuickEncoreCbx(WebDriver driver, String tc) throws IOException {
		elementExist(driver, filterModelBuickEncoreLocator, true, tc);
		driver.findElement(filterModelBuickEncoreLocator).click();
	}

	public void clickModelBuickCascadaCbx(WebDriver driver, String tc) throws IOException {
		elementExist(driver, filterModelBuickCascadaLocator, true, tc);
		driver.findElement(filterModelBuickCascadaLocator).click();
	}

	public void clickModelCadillarTab(WebDriver driver, String tc) throws IOException {
		elementExist(driver, filterModelCadillacLocator, true, tc);
		driver.findElement(filterModelCadillacLocator).click();
	}

	public void clickModelCadillarAllCbx(WebDriver driver, String tc) throws IOException {
		elementExist(driver, filterModelCadillacAllLocator, true, tc);
		driver.findElement(filterModelCadillacAllLocator).click();
	}

	public void clickModelCadillarEscaladeCbx(WebDriver driver, String tc) throws IOException {
		elementExist(driver, filterModelCadillacEscaladeLocator, true, tc);
		driver.findElement(filterModelCadillacEscaladeLocator).click();
	}

	public void clickModelGMCTab(WebDriver driver, String tc) throws IOException {
		elementExist(driver, filterModelGMCLocator, true, tc);
		driver.findElement(filterModelGMCLocator).click();
	}

	public void clickModelGMCAllCbx(WebDriver driver, String tc) throws IOException {
		elementExist(driver, filterModelGMCAllLocator, true, tc);
		driver.findElement(filterModelGMCAllLocator).click();
	}

	public void clickModelCevroletTab(WebDriver driver, String tc) throws IOException {
		elementExist(driver, filterModelChevroletLocator, true, tc);
		driver.findElement(filterModelChevroletLocator).click();
	}

	public void clickModelCevroletAllCbx(WebDriver driver, String tc) throws IOException {
		elementExist(driver, filterModelChevroletAllLocator, true, tc);
		driver.findElement(filterModelChevroletAllLocator).click();
	}

	public void clickModelApplyBtn(WebDriver driver, String tc) throws IOException {
		elementExist(driver, modelApply, true, tc);
		driver.findElement(modelApply).click();
	}

	public void clickModelClearBtn(WebDriver driver, String tc) throws IOException {
		boolean trimCheckBoxAll, trimCheckBox1, trimCheckBox2;
		elementExist(driver, modelClearBtn, true, tc);
		driver.findElement(modelClearBtn).click();
		trimCheckBoxAll = driver.findElement(filterModelBuickAllLocator).isSelected();
		trimCheckBox1 = driver.findElement(filterModelBuickEnclaveLocator).isSelected();
		trimCheckBox2 = driver.findElement(filterModelBuickEncoreLocator).isSelected();
		if (!trimCheckBoxAll && !trimCheckBox1 && !trimCheckBox2) {
			rwExcel(tc, true, "Click the Model Clear button", "All check boxes are unchecked");
		} else {
			if (trimCheckBoxAll) {
				rwExcel(tc, false, "Click the Model Clear button", "All check box is unchecked");
			}
			if (trimCheckBox1) {
				rwExcel(tc, false, "Click the Model Clear button", "Buick Enclave check box is unchecked");
			}
			if (trimCheckBox2) {
				rwExcel(tc, false, "Click the Model Clear button", "Buick Encore check box is unchecked");
			}
		}
	}

	public void clickModelXBtn(WebDriver driver, String tc) throws IOException {
		elementExist(driver, modelXBtn, true, tc);
		driver.findElement(modelXBtn).click();
	}

	public ImageGallery clickColorsBtn(WebDriver driver, String tc) throws IOException {
		elementExist(driver, filterColorsLocator, true, tc);
		driver.findElement(filterColorsLocator).click();
		return new ImageGallery(driver);
	}

	public ImageGallery clickColorExteriorTab(WebDriver driver, String tc) throws IOException {
		elementExist(driver, exteriorTab, true, tc);
		driver.findElement(exteriorTab).click();
		return new ImageGallery(driver);
	}

	public ImageGallery clickColorInteriorTab(WebDriver driver, String tc) throws IOException {
		elementExist(driver, interiorTab, true, tc);
		driver.findElement(interiorTab).click();
		return new ImageGallery(driver);
	}

	public ImageGallery clickColorExteriorColorCheckBox(WebDriver driver, String colorDes, String tc)
			throws IOException {
		boolean selected = false;
		String listText1;
		List<WebElement> listItems = driver.findElements(exteriorAllColors);

		int colorsCount = listItems.size();
		for (int i = 0; i < colorsCount; i++) {
			listText1 = listItems.get(i).getText();
			if (listText1.equalsIgnoreCase(colorDes)) {
				listItems.get(i).click();
				rwExcel(tc, true, "Click the exterior color " + colorDes + " check-box",
						"The color - " + colorDes + " - is selected");
				selected = true;
				// selected = listItems.get(i).isSelected();
				break;
			}
		}
		if (!selected) {
			rwExcel(tc, false, "Click the exterior color " + colorDes + " check-box",
					"The color - " + colorDes + "- does NOT exist");
		}
		return new ImageGallery(driver);
	}

	public ImageGallery invaliclickColorInteriorColorCheckBox(WebDriver driver, String colorDes, String tc)
			throws IOException {
		boolean selected = false;
		String listText1;
		List<WebElement> listItems = driver.findElements(interiorAllColors);

		int colorsCount = listItems.size();
		for (int i = 0; i < colorsCount; i++) {
			listText1 = listItems.get(i).getText();
			if (listText1.equalsIgnoreCase(colorDes)) {
				listItems.get(i).click();
				rwExcel(tc, true, "Click the interior color " + colorDes + " check-box",
						"The color - " + colorDes + " - is selected");
				selected = true;
				// selected = listItems.get(i).isEnabled();
				break;
			}
		}
		if (!selected) {
			rwExcel(tc, false, "Click the interior color " + colorDes + " check-box",
					"The color - " + colorDes + "- does NOT exist");
		}
		return new ImageGallery(driver);
	}

	public ImageGallery clickColorInteriorColorCheckBox(WebDriver driver, String colorDes, String tc)
			throws IOException {
		String tempName;
		boolean selected = false;
		// By interColorContentPanel = By.xpath("//div[@id='intColorTabPanel']/div/div/label");
		By interColorContentPanel = By.xpath("(//input[@type='checkbox'])");

		elementExist(driver, interColorContentPanel, true, tc);
		List<WebElement> interColorcheckboxList = driver.findElements(interColorContentPanel);
		int trimlCount = interColorcheckboxList.size();
		if (trimlCount >= 1) {
			for (int i = 1; i < (trimlCount + 1); i++) {
				WebElement interColrCheckbox = driver
						.findElement(By.xpath("//div[@id='intColorTabPanel']/div[" + i + "]/div/label"));

				tempName = interColrCheckbox.getText();
				if (tempName.equalsIgnoreCase(colorDes)) {
					// scrollUp(driver, 500, tc);
					interColrCheckbox.click();

					rwExcel(tc, true, "Click the interior color " + colorDes + " check-box",
							"The color - " + colorDes + " - is selected");
					selected = true;
					// stop here!!!
					boolean sselected = interColrCheckbox.isSelected();
					sselected = interColrCheckbox.isSelected();

					break;
				} else {
					// not found, go to the next one but if this is last report
					// cannot find the brand
					if (i == trimlCount) {
						System.out.println("Inter color checkbox name=" + colorDes + " is Not found!!!");
						rwExcel(tc, false, "Click the Inter color " + colorDes + " checkbox",
								"Inter color name=" + colorDes + " is Not found!!!");
					}
				}

			}
			if (!selected) {
				rwExcel(tc, false, "Click the interior color " + colorDes + " check-box",
						"The color - " + colorDes + "- does NOT exist");
			}
		} else {
			// ==0 list is empty
			System.out.println("Model tab list is empty!!!");
			rwExcel(tc, false, "Click the Trim checkbox " + colorDes + " checkbox", "trim checkbox list is empty!!!");
		}

		return new ImageGallery(driver);
	}

	public void clickColorApplyBtn(WebDriver driver, String tc) throws IOException {
		elementExist(driver, colorApply, true, tc);
		driver.findElement(colorApply).click();
	}

	public void clickColorClearBtn(WebDriver driver, String tc) throws IOException {
		elementExist(driver, colorClearBtn, true, tc);
		driver.findElement(colorClearBtn).click();
	}

	public void clickColorXBtn(WebDriver driver, String tc) throws IOException {
		elementExist(driver, colorXBtn, true, tc);
		driver.findElement(colorXBtn).click();
	}

	public ImageGallery clickClearAllFiltersBtn(WebDriver driver, String tc) throws IOException {
		elementExist(driver, clearAllFiltersBtnLocator, true, tc);
		driver.findElement(clearAllFiltersBtnLocator).click();
		return new ImageGallery(driver);
	}

	public ImageGallery clickShowAllBtn(WebDriver driver, String tc) throws IOException {
		elementExist(driver, showAllLocator, true, tc);
		driver.findElement(showAllLocator).click();
		return new ImageGallery(driver);
	}

	public ImageGallery clickAddInventoryBtn(WebDriver driver, String tc) throws IOException {
		elementExist(driver, addInventoryBtn, true, tc);
		driver.findElement(addInventoryBtn).click();
		return new ImageGallery(driver);
	}

	public ImageGallery clickAddInventoryXBtn(WebDriver driver, String tc) throws IOException {
		elementExist(driver, addInventoryXbtn, true, tc);
		driver.findElement(addInventoryXbtn).click();
		return new ImageGallery(driver);
	}

	public ImageGallery clickAddInventoryCancelBtn(WebDriver driver, String tc) throws IOException {
		elementExist(driver, addInventoryCancelBtn, true, tc);
		driver.findElement(addInventoryCancelBtn).click();
		return new ImageGallery(driver);
	}

	public Templates clickTemplatesBtn(WebDriver driver) throws IOException {
		driver.findElement(templatesBtnLocator).click();
		return new Templates(driver);
	}

	public ImageGallery inputVinInAddInventoryField(WebDriver driver, String sVin, String tc) throws IOException {
		elementExist(driver, addInventoryEnterVinField, true, tc);
		driver.findElement(addInventoryEnterVinField).clear();
		driver.findElement(addInventoryEnterVinField).sendKeys(sVin);
		return new ImageGallery(driver);
	}

	public ImageGallery clickAddBtn(WebDriver driver, String tc) throws IOException {
		elementExist(driver, addInventoryAddBtn, true, tc);
		driver.findElement(addInventoryAddBtn).click();
		return new ImageGallery(driver);
	}

	public ImageGallery clickRefleshF5Btn(WebDriver driver, String tc) throws IOException {
		// driver.findElement(addInventoryBtn).sendKeys(Keys.F5);
		driver.navigate().to(driver.getCurrentUrl());
		return new ImageGallery(driver);
	}

	public ImageGallery clickLoadMoreVehicleBtn(WebDriver driver, String tc) throws IOException {
		elementExist(driver, loadMoreVehicleBtnLocator, true, tc);
		driver.findElement(loadMoreVehicleBtnLocator).click();
		return new ImageGallery(driver);
	}

	public ImageGallery clickGridViewBtn(WebDriver driver, String tc) throws IOException {
		elementExist(driver, listViewBtnLocator, true, tc);
		driver.findElement(listViewBtnLocator).click();
		return this;
	}

	public ImageGallery clickTilesViewBtn(WebDriver driver, String tc) throws IOException {
		elementExist(driver, gridViewBtnLocator, true, tc);
		driver.findElement(gridViewBtnLocator).click();
		return this;
	}

	public void clickGridRowOnlyOneRecordToCheck(WebDriver driver, String tc) throws IOException {
		By onlyOneRecord = By.xpath("//*[@id=\"vehicleTable\"]/tbody/tr/td[1]/input");
		elementExist(driver, onlyOneRecord, true, tc);
		driver.findElement(onlyOneRecord).click();
	}

	public VehicleGallery clickGridRowOnlyOneRecord(WebDriver driver, String tc) throws IOException {
		By OneRecordVin = By.xpath("//*[@id=\"vehicleTable\"]/tbody/tr/td[6]");
		elementExist(driver, OneRecordVin, true, tc);
		driver.findElement(OneRecordVin).click();
		return new VehicleGallery(driver);
	}

	public void clickGridRowOneRecordWithNumToCheck(WebDriver driver, String num, String tc) throws IOException {
		By OneRecord = By.xpath("//*[@id=\"vehicleTable\"]/tbody/tr[" + num + "]/td[1]/input");
		elementExist(driver, OneRecord, true, tc);
		driver.findElement(OneRecord).click();
	}

	public VehicleGallery clickGridRowOneRecordWithNum(WebDriver driver, String num, String tc) throws IOException {
		By OneRecordVin = By.xpath("//*[@id=\"vehicleTable\"]/tbody/tr[" + num + "]/td[6]");
		elementExist(driver, OneRecordVin, true, tc);
		driver.findElement(OneRecordVin).click();
		return new VehicleGallery(driver);
	}

	public Backgrounds clickBackgroundsBtn(WebDriver driver, String tc) throws IOException {
		elementExist(driver, backgroundsLocator, true, tc);
		driver.findElement(backgroundsLocator).click();
		return new Backgrounds(driver);
	}

	public String getVehGUID(String dlrCode, String sVin, String serverName, String dbName, String userName,
			String password) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		// System.setProperty("java.net.preferIPv6Addresses", "true"); //doesn't help to resolve getConnection below

		// Connection conn = DriverManager.getConnection("jdbc:sqlserver://LNOC-Q13V-MSQ2.autodata.org;user=VDVIWebServicesUserQA;password=HDuMy873JRFpkkU9;database=VDVI_Master");
		// Connection conn = DriverManager.getConnection("jdbc:sqlserver://LNOC-Q13V-MSQ2.autodata.org;user=zhoul;password=Zljzlj19591112;database=VDVI_Master");
		Connection conn = DriverManager.getConnection("jdbc:sqlserver://" + serverName + ";user=" + userName
				+ ";password=" + password + ";database=" + dbName);// worked one.

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

	public boolean verifyShowAllBtnStatus(WebDriver driver, boolean status, String tc) throws IOException {
		boolean tempStatus, actualStatus = false;
		String disable;
		// inActive status=false
		if (!status) {
			disable = driver.findElement(showAllLocator).getAttribute("disabled");
			if (disable == null) {
				rwExcel(tc, false, "Check inactive status on Show All button ",
						"Show All button is displayed with inactive");
			} else {
				tempStatus = disable.equalsIgnoreCase("true");
				if (tempStatus) {
					rwExcel(tc, true, "Check inactive status on Show All button ",
							"Show All button is displayed with inactive");
					actualStatus = false;
				} else {
					rwExcel(tc, false, "Check inactive status on Show All button ",
							"Show All button is displayed with inactive (true)");
					actualStatus = true;
				}
			}
		} else {
			// Active status=true
			tempStatus = driver.findElement(showAllLocator).isEnabled();
			if (tempStatus) {
				rwExcel(tc, true, "Check active status on Show All button ",
						"Show All button is displayed with active");
				actualStatus = true;
			} else {
				rwExcel(tc, false, "Check active status on Show All button ",
						"Show All button is displayed with inactive");
				actualStatus = false;
			}
		}

		return actualStatus;

	}

	public boolean verifyLoadMoreVehicleBtnStatus(WebDriver driver, boolean status, String tc) throws IOException {
		boolean tempStatus, actualStatus = false;
		String disable;
		// inActive status=false
		if (!status) {
			disable = driver.findElement(loadMoreVehicleBtnLocator).getAttribute("disabled");
			if (disable == null) {
				rwExcel(tc, false, "Check inactive status on LOAD MORE VEHICLE button ",
						"LOAD MORE VEHICLE button is displayed with inactive");
			} else {
				tempStatus = disable.equalsIgnoreCase("true");
				if (tempStatus) {
					rwExcel(tc, true, "Check inactive status on LOAD MORE VEHICLE button ",
							"LOAD MORE VEHICLE button is displayed with inactive");
					actualStatus = false;
				} else {
					rwExcel(tc, false, "Check inactive status on LOAD MORE VEHICLE button ",
							"LOAD MORE VEHICLE button is displayed with inactive (true)");
					actualStatus = true;
				}
			}
		} else {
			// Active status=true
			tempStatus = driver.findElement(loadMoreVehicleBtnLocator).isEnabled();
			if (tempStatus) {
				rwExcel(tc, true, "Check active status on LOAD MORE VEHICLE button ",
						"LOAD MORE VEHICLE button is displayed with active");
				actualStatus = true;
			} else {
				rwExcel(tc, false, "Check active status on LOAD MORE VEHICLE button ",
						"LOAD MORE VEHICLE button is displayed with inactive");
				actualStatus = false;
			}
		}

		return actualStatus;

	}

	public void verifyRerenderBtnStatus(WebDriver driver, boolean status, String tc) throws IOException {
		boolean tempStatus;
		String disable;
		// inActive status=false
		if (!status) {
			disable = driver.findElement(reRenderActiveBtn).getAttribute("disabled");
			if (disable == null) {
				rwExcel(tc, false, "Check inactive status on Rerender button ",
						"Rerender button is displayed with inactive");
			} else {
				tempStatus = disable.equalsIgnoreCase("true");
				// tempStatus = Elementpresent(driver, reRenderInactiveBtn);
				if (tempStatus) {
					rwExcel(tc, true, "Check inactive status on Rerender button ",
							"Rerender button is displayed with inactive");
				} else {
					rwExcel(tc, false, "Check inactive status on Rerender button ",
							"Rerender button is displayed with inactive (true)");
				}
			}
		} else {
			// Active status=true
			tempStatus = driver.findElement(reRenderActiveBtn).isEnabled();
			// tempStatus = Elementpresent(driver, reRenderActiveBtn);
			if (tempStatus) {
				rwExcel(tc, true, "Check active status on Rerender button ",
						"Rerender button is displayed with active");
			} else {
				rwExcel(tc, false, "Check active status on Rerender button ",
						"Rerender button is displayed with active");
			}
		}

		// Active status

	}

	public void verifyGenerateURLsBtnStatus(WebDriver driver, boolean status, String tc) throws IOException {
		boolean tempStatus;
		String disable;
		// inActive status=false
		if (!status) {
			disable = driver.findElement(generateURLs).getAttribute("disabled");
			if (disable == null) {
				rwExcel(tc, false, "Check inactive status on GENERATE URLs button ",
						"GENERATE URLs button is displayed with inactive");
			} else {
				tempStatus = disable.equalsIgnoreCase("true");
				// tempStatus = Elementpresent(driver, reRenderInactiveBtn);
				if (tempStatus) {
					rwExcel(tc, true, "Check inactive status on GENERATE URLs button ",
							"GENERATE URLs button is displayed with inactive");
				} else {
					rwExcel(tc, false, "Check inactive status on GENERATE URLs button ",
							"GENERATE URLs button is displayed with inactive (true)");
				}
			}
		} else {
			// Active status=true
			tempStatus = driver.findElement(generateURLs).isEnabled();
			// tempStatus = Elementpresent(driver, reRenderActiveBtn);
			if (tempStatus) {
				rwExcel(tc, true, "Check active status on GENERATE URLs button ",
						"GENERATE URLs button is displayed with active");
			} else {
				rwExcel(tc, false, "Check active status on GENERATE URLs button ",
						"GENERATE URLs button is displayed with active");
			}
		}

		// Active status

	}

	public void verifyLoadPreviewTileImage(WebDriver driver, double maxWT, String sVIN, String vGUID, int totalVINs,
			String tc) throws IOException, InterruptedException {

		// By hoverOn_01_failed_unable_wait_Text_locator =
		// By.xpath("//div[@id='tile_7D4732D8-57E6-41FE-A9C7-2298D21B1758']/div");
		By hoverOn_01_failed_unable_wait_Text_locator = By.xpath("//div[@id='tile_" + vGUID + "']/div");
		// By hoverOn_01_imagetileLocator =
		// By.xpath("//div[@id='tile_7D4732D8-57E6-41FE-A9C7-2298D21B1758']/img");
		By hoverOn_01_imagetileLocator = By.xpath("//div[@id='tile_" + vGUID + "']/img");

		rwExcel(tc, "Verify loading preview tile image", " ");
		boolean tile = false;
		double waitTime = 0;
		double time_interval = 10;
		// double maxWT=240;// 4*60 = 4 minutes;
		maxWT = maxWT * 60;
		String textString = "";
		while (waitTime < maxWT) {
			if (elementExist(driver, hoverOn_01_failed_unable_wait_Text_locator, false, tc)) {
				// Text exists,
				try {
					textString = driver.findElement(hoverOn_01_failed_unable_wait_Text_locator).getText();
				} catch (Throwable e) {
					System.out.println("Error occurs during getText(). Continue!");
					rwExcel(tc, false, "VerifyLoadPreviewTileImage. Error occur time=" + waitTime + " seconds",
							"Try to getText, an error occurs during getText()!");
				}
				if ((textString.equalsIgnoreCase("Please wait while we render this VIN."))
						|| (textString.equalsIgnoreCase("Please wait while we decode this VIN."))) {
					// please wait ...
					if (textString.equalsIgnoreCase("Please wait while we decode this VIN.")) {
						System.out.println("Decoding the VIN. The text = " + textString + " \n");
					}
					waitTime = waitTime + 10;
					Wait((int) time_interval);
					System.out.println("Total VINs=" + totalVINs + ".  Waiting for rerendering, time elapsed "
							+ waitTime + " seconds = " + round(waitTime / 60, 1)
							+ " minute(s). Please wait... is showing on the tile.");

				} else if (textString.equalsIgnoreCase("Unable to decode VIN. Images cannot be generated.")) {
					// Unable to decode VIN...
					waitTime = maxWT;
					rwExcel(tc, false, "Verify loading preview tile image - Preview tile image is NOT showing",
							"Unable to decode VIN...showing");
					System.out
							.println("TC failed. Preview tile image is NOT showing, Unable to decode VIN...showing \n");
				} else if (textString.equalsIgnoreCase("Failed, No images found to Render")) {
					// failed, No image...
					waitTime = maxWT;
					rwExcel(tc, false, "Verify loading preview tile image - Preview tile image is NOT showing",
							"Failed, No image...showing");
					System.out.println("TC failed. Preview tile image is NOT showing, Failed, No image...showing \n");
				} else {
					// don't know text
					waitTime = maxWT;
					rwExcel(tc, false, "Verify loading preview tile image - Preview tile image is NOT showing",
							"Don't kow text - " + textString + " - ...is showing");
					System.out.println("TC failed. Don't know text, the text = " + textString + " \n");
				}

			} else {
				// preview tile mayby exist, verify tile
				VerifyImageLoaded(driver, hoverOn_01_imagetileLocator, tc);
				tile = true;
				rwExcel(tc, "Total VINs=" + totalVINs + ".  VIN=" + sVIN + ". " + "Rerendering time = "
						+ round(waitTime / 60, 1) + " minutes.", " ");
				waitTime = maxWT;
			}
			if ((waitTime >= maxWT) && (!tile)) {
				// TC fail
				System.out.println("Total VINs=" + totalVINs + ".  VIN=" + sVIN + ". "
						+ "TC failed. Preview tile image is NOT showing, wait time exceeds max. " + round(maxWT / 60, 1)
						+ " minutes \n");
				rwExcel(tc, false,
						"VIN=" + sVIN + ". " + "Verify loading preview tile image - Preview tile image is NOT showing",
						"Please wait ....is always showing. Waiting time exceeds max " + round(maxWT / 60, 1)
								+ " minutest");
			}
		}
		// String Text =
		// driver.findElement(hoverOn_01_failed_unable_wait_Text_locator).getText();
		// String failedText =
		// driver.findElement(hoverOn_01_Failed_NoImageLocator).getText();
		// String waitTorenderText =
		// driver.findElement(hoverOn_01_Wait_to_render_NoImageLocator).getText();
		// String unableTodecodeText =
		// driver.findElement(hoverOn_01_UnableToDecodeVIN_NoImageLocator).getText();
		//
		// VerifyImageLoaded(driver, hoverOn_01_imagetileLocator, tc);
	}

	public void verifyErrorMsgShowing(WebDriver driver, String tc) throws IOException {
		boolean msgExist = false;
		String msgString;
		String goodMsg00 = "Rerendering request has been sent - check back soon";
		String errorMsg01 = "An error occured during rendering. Please try again.";

		msgExist = elementExist(driver, errorMsgLocator, false, tc);
		if (msgExist) {
			msgString = driver.findElement(errorMsgLocator).getText();
			if (msgString.equalsIgnoreCase(goodMsg00)) {
				rwExcel(tc, true, "There is a message showing under My Inventory Gallery", "The msg is:" + goodMsg00);
			} else if (msgString.equalsIgnoreCase(errorMsg01)) {
				rwExcel(tc, false, "There is an error message showing under My Inventory Gallery",
						"The error msg is:" + msgString);

			} else {
				rwExcel(tc, false, "Unexpected error message is showing under My Inventory Gallery",
						"The error msg is:" + msgString);

			}

		} else {
			rwExcel(tc, false, "Check expected message element under My Inventory Gallery",
					"The expected element or message dose not exit");
		}
	}

	public void verifyGoodMsgShowing(WebDriver driver, String tc) throws IOException {
		boolean goodMsgExist = false;
		boolean errorMsgExist = false;
		String goodMsgString = "";
		String errorMsgString = "";
		String goodMsg00 = "Rerendering request has been sent - check back soon";
		String errorMsg01 = "An error occured during rendering. Please try again.";
		goodMsgExist = elementExist(driver, goodMsgLocator1, false, tc);
		goodMsgString = driver.findElement(goodMsgLocator1).getText();
		errorMsgExist = elementExist(driver, errorMsgLocator1, false, tc);
		errorMsgString = driver.findElement(errorMsgLocator1).getText();
		if ((goodMsgExist) && (errorMsgString.isEmpty())) {
			if (goodMsgString.equalsIgnoreCase(goodMsg00)) {
				rwExcel(tc, true, "There is a message showing under My Inventory Gallery", "The msg is:" + goodMsg00);
			} else if (goodMsgString.equalsIgnoreCase(errorMsg01)) {
				rwExcel(tc, false, "There is an error message showing under My Inventory Gallery",
						"The error msg is:" + goodMsgString);

			} else {
				rwExcel(tc, false, "Unexpected error message is showing under My Inventory Gallery",
						"The error msg is:" + goodMsgString);
			}

		} else if (errorMsgExist) {
			if (errorMsgString.equalsIgnoreCase(errorMsg01)) {
				rwExcel(tc, false, "There is an error message showing under My Inventory Gallery",
						"The error msg is:" + errorMsgString);
			} else {
				rwExcel(tc, false, "Unexpected error message is showing under My Inventory Gallery",
						"The error msg is:" + errorMsgString);
			}
		} else {
			rwExcel(tc, false, "Check expected message element under My Inventory Gallery",
					"The expected element or message dose not exit");
		}
	}

	public boolean verifyGoodOrBadMsgShowing(WebDriver driver, String tc) throws IOException {
		boolean goodMsgExist = false;
		boolean errorMsgExist = false;
		boolean result = false;
		String goodMsgString = "";
		String errorMsgString = "";
		String goodMsg00 = "Vehicle will be added to your Gallery - check back soon";
		String errorMsg01 = "VIN already exists for this dealer. Update the record instead!";
		String errorMsg02 = "VIN is not valid.";
		String errorMsg03 = "Please enter VIN number";

		goodMsgExist = elementExist(driver, goodMsgLocator, false, tc);
		goodMsgString = driver.findElement(goodMsgLocator).getText();
		errorMsgExist = elementExist(driver, errorMsgLocator, false, tc);
		errorMsgString = driver.findElement(errorMsgLocator).getText();
		if ((goodMsgExist) && (errorMsgString.isEmpty())) {
			if (goodMsgString.equalsIgnoreCase(goodMsg00)) {
				System.out.println("There is a message showing under My Inventory Gallery.  The msg is:" + goodMsg00);
				rwExcel(tc, true, "There is a message showing under My Inventory Gallery", "The msg is:" + goodMsg00);
				result = true;
			} else if (goodMsgString.equalsIgnoreCase(errorMsg01)) {
				System.out.println("There is an error message showing under My Inventory Gallery. The error msg is:"
						+ goodMsgString);
				rwExcel(tc, false, "There is an error message showing under My Inventory Gallery",
						"The error msg is:" + goodMsgString);
				result = false;
			} else {
				System.out.println("Unexpected error message is showing under My Inventory Gallery. The error msg is:"
						+ goodMsgString);
				rwExcel(tc, false, "Unexpected error message is showing under My Inventory Gallery",
						"The error msg is:" + goodMsgString);
				result = false;
			}

		} else if (errorMsgExist) {
			if (errorMsgString.equalsIgnoreCase(errorMsg01)) {
				System.out.println("There is an error message showing under My Inventory Gallery. The error msg is:"
						+ errorMsgString);
				rwExcel(tc, false, "There is an error message showing under My Inventory Gallery",
						"The error msg is:" + errorMsgString);
				result = false;
			} else if (errorMsgString.equalsIgnoreCase(errorMsg02)) {
				System.out.println("There is an error message showing under My Inventory Gallery. The error msg is:"
						+ errorMsgString);
				rwExcel(tc, false, "There is an error message showing under My Inventory Gallery",
						"The error msg is:" + errorMsgString);
				result = false;
			} else if (errorMsgString.equalsIgnoreCase(errorMsg03)) {
				System.out.println("There is an error message showing under My Inventory Gallery. The error msg is:"
						+ errorMsgString);
				rwExcel(tc, false, "There is an error message showing under My Inventory Gallery",
						"The error msg is:" + errorMsgString);
				result = false;
			} else {
				System.out.println("Unexpected error message is showing under My Inventory Gallery. The error msg is:"
						+ errorMsgString);
				rwExcel(tc, false, "Unexpected error message is showing under My Inventory Gallery",
						"The error msg is:" + errorMsgString);
				result = false;
			}
		} else {
			System.out.println(
					"Check expected message element under My Inventory Gallery. The expected element or message dose not exit");
			rwExcel(tc, false, "Check expected message element under My Inventory Gallery",
					"The expected element or message dose not exit");
			result = false;
		}
		return result;
	}

	public void verifyDealershipname(String dealershipname, String tc) throws IOException {
		// assertEquals("Lucas Zhou",
		// driver.findElement(By.cssSelector("span")).getText());
		String expectedText, rs;
		rs = driver.findElement(dealerShipName).getText();
		expectedText = dealershipname;
		if (rs.equals(expectedText)) {
			rwExcel(tc, true, "Dealership name is displayed", expectedText);
		} else {
			rwExcel(tc, false, "Dealership name is NOT showing properly. Exceed time limit!",
					"Dealership name is NOT - " + expectedText);
		}

	}

	public void verifyHelpContactSupport(WebDriver driver, String email, String tel, String VINpxSupportEmail,
			String VINpxSupportEmailPS, String chkEmaiol, String tc) throws IOException {
		String rs1, rs2, rs3, rs4, rs5, rs6;
		driver.findElement(helpMarkLocator).click();
		// Contact Support
		driver.findElement(ContactSupportLocator).click();
		Wait(1);
		rs1 = driver.findElement(ContactSupportTile01).getText();
		rs2 = driver.findElement(ContactSupportLine102).getText();
		rs3 = driver.findElement(ContactSupportLine203).getText();
		rs4 = driver.findElement(ContactSupportLine3to5_07).getText();
		rs5 = driver.findElement(ContactSupportEmailLine308).getText();
		rs6 = driver.findElement(ContactSupportTelLine409).getText();
		String expected_rs1 = "Contact Support";
		String expected_rs2 = "We have captured this URL to forward to the Customer Support Team.";
		String expected_rs3 = "Please briefly describe the issue you are having.";
		String expected_rs4 = "For Immediate Assistance: contact@unityworks.com\n1-800-293-2056";
		if (rs1.equals(expected_rs1)) {
			rwExcel(tc, true, "Contact Support shows correctly", "Contact Support =" + rs1);
		} else {
			rwExcel(tc, false, "Contact Support does not match",
					"Expected is:" + expected_rs1 + ".  Site shows: " + rs1);
		}
		if (rs2.equals(expected_rs2)) {
			rwExcel(tc, true, "We have captured... shows correctly", "We have captured... =" + rs2);
		} else {
			rwExcel(tc, false, "We have captured... does not match",
					"Expected is:" + expected_rs2 + ".  Site shows: " + rs2);
		}
		if (rs3.equals(expected_rs3)) {
			rwExcel(tc, true, "Please briefly... shows correctly", "Please briefly... =" + rs3);
		} else {
			rwExcel(tc, false, "Please briefly... does not match",
					"Expected is:" + expected_rs3 + ".  Site shows: " + rs3);
		}
		if (rs4.equals(expected_rs4)) {
			rwExcel(tc, true, "For Immediate... shows correctly", "Please briefly... =" + rs4);
		} else {
			rwExcel(tc, false, "For Immediate... does not match",
					"Expected is:" + expected_rs4 + ".  Site shows: " + rs4);
		}

		if (rs5.equals(email)) {
			rwExcel(tc, true, "Contact Support Email shows correctly", "Email =" + email);
		} else {
			rwExcel(tc, false, "Contact Support Email does not show correctly",
					"Expected Email =" + email + ". Site shows Email=" + rs5);
		}
		if (rs6.equals(tel)) {
			rwExcel(tc, true, "Contact Support phone # shows correctly", "phone # =" + tel);
		} else {
			rwExcel(tc, false, "Contact Support phone # does not show correctly",
					"Expected phone # =" + email + ". Site shows phone #=" + rs6);
		}
		// driver.findElement(By.id("supportMessage")).clear(); //record
		driver.findElement(ContactSupportMsgField04).clear();
		// driver.findElement(By.id("supportMessage")).sendKeys("This is testing for Support/Contact US request. Ignore please. ");//record
		String text2 = "This is testing for Support/Contact US request. Ignore please.";
		driver.findElement(ContactSupportMsgField04).sendKeys(text2);
		driver.findElement(ContactSupportCancelBtn06).click();
		Wait(2);
		driver.findElement(helpMarkLocator).click();
		// Contact Support
		driver.findElement(ContactSupportLocator).click();
		Wait(1);
		// driver.findElement(By.id("supportMessage")).clear(); //record
		driver.findElement(ContactSupportMsgField04).clear();
		// driver.findElement(By.id("supportMessage")).sendKeys("This is testing. Ignore please. ");//record
		driver.findElement(ContactSupportMsgField04)
				.sendKeys("This is testing for Support/Contact US request. Ignore please. ");
		// driver.findElement(By.cssSelector("button.btn.asc-secondary-btn")).click();//record
		driver.findElement(ContactSupportSubmitBtn05).click();
		Wait(2);

		// Verify email sent out to tdautoaa@gmail.com in QA
		if (chkEmaiol.equalsIgnoreCase("Yes")) {
			tc = "TC139675";
			DealerPortal.MailReader gMail = new MailReader();

			// String subject = "Support/Contact Us request from autotomsmith3@gmail.com";//This is from QA.
			String subject = "Support/Contact Us request from";// This is from QA.
			String text3 = "Additional Context";
			// (String mainID, String email, String mailPassword, String emailSuj,String content2, String content3, String tc)
			tc = "TC139675";
			gMail.VerifyGetMailContentFrSubContain("Imap.gmail.com", VINpxSupportEmail, VINpxSupportEmailPS, subject,
					text2, text3, tc);
		}
	}

	public void verifyHelpSystemHelp(WebDriver driver, String tc) throws IOException {

		String expectedText, rs1, rs2, rs3, rs4, rs5, rs6, rs7, rs8;
		driver.findElement(helpMarkLocator).click();
		// Report Issue
		String mainWindowHandle = driver.getWindowHandle();
		driver.findElement(SystemHelpLocator).click();
		Wait(1);
		Set<String> strHandles = driver.getWindowHandles();
		for (String handle : strHandles) {
			driver.switchTo().window(handle);
			String titleStr = driver.getTitle();
			if (titleStr.equalsIgnoreCase("VINPX Help")) {
				// Verify the content in the new window
				// More verification...

				// close new window here
				driver.close();
				driver.switchTo().window(mainWindowHandle);
			}
		}
		if (elementExist(driver, SystemHelpLocator, false, tc)) {
			driver.findElement(helpMarkLocator).click();// Close the overlay if it is in open status
		}
		Wait(1);
	}

	public void verifyHelpReportIssue(WebDriver driver, String email, String tel, String SupportEmail,
			String SupportEmailPS, String chkEmail, String tc) throws IOException {
		String rs1, rs2, rs3, rs4, rs5, rs6;
		driver.findElement(helpMarkLocator).click();
		// Report Issue
		driver.findElement(ReportIssueLocator).click();
		Wait(1);
		rs1 = driver.findElement(ContactSupportTile01).getText();
		rs2 = driver.findElement(ContactSupportLine102).getText();
		rs3 = driver.findElement(ContactSupportLine203).getText();
		rs4 = driver.findElement(ContactSupportLine3to5_07).getText();
		rs5 = driver.findElement(ContactSupportEmailLine308).getText();
		rs6 = driver.findElement(ContactSupportTelLine409).getText();
		String expected_rs1 = "Report Issue";
		String expected_rs2 = "We have captured this URL to forward to the Customer Support Team.";
		String expected_rs3 = "Please briefly describe the issue you are having.";
		String expected_rs4 = "For Immediate Assistance: contact@unityworks.com\n1-800-293-2056";
		if (rs1.equals(expected_rs1)) {
			rwExcel(tc, true, "Report Issue shows correctly", "Contact Support =" + rs1);
		} else {
			rwExcel(tc, false, "Report Issue does not match", "Expected is:" + expected_rs1 + ".  Site shows: " + rs1);
		}
		if (rs2.equals(expected_rs2)) {
			rwExcel(tc, true, "We have captured... shows correctly", "We have captured... =" + rs2);
		} else {
			rwExcel(tc, false, "We have captured... does not match",
					"Expected is:" + expected_rs2 + ".  Site shows: " + rs2);
		}
		if (rs3.equals(expected_rs3)) {
			rwExcel(tc, true, "Please briefly... shows correctly", "Please briefly... =" + rs3);
		} else {
			rwExcel(tc, false, "Please briefly... does not match",
					"Expected is:" + expected_rs3 + ".  Site shows: " + rs3);
		}
		if (rs4.equals(expected_rs4)) {
			rwExcel(tc, true, "For Immediate... shows correctly", "Please briefly... =" + rs4);
		} else {
			rwExcel(tc, false, "For Immediate... does not match",
					"Expected is:" + expected_rs4 + ".  Site shows: " + rs4);
		}

		if (rs5.equals(email)) {
			rwExcel(tc, true, "Contact Support Email shows correctly", "Email =" + email);
		} else {
			rwExcel(tc, false, "Contact Support Email does not show correctly",
					"Expected Email =" + email + ". Site shows Email=" + rs5);
		}
		if (rs6.equals(tel)) {
			rwExcel(tc, true, "Contact Support phone # shows correctly", "phone # =" + tel);
		} else {
			rwExcel(tc, false, "Contact Support phone # does not show correctly",
					"Expected phone # =" + email + ". Site shows phone #=" + rs6);
		}
		// driver.findElement(By.id("supportMessage")).clear(); //record
		driver.findElement(ContactSupportMsgField04).clear();
		// driver.findElement(By.id("supportMessage")).sendKeys("This is testing. Ignore please. ");//record
		String text2 = "This is testing for Report Issue. Ignore please.";
		driver.findElement(ContactSupportMsgField04).sendKeys(text2);
		driver.findElement(ContactSupportCancelBtn06).click();
		Wait(2);
		driver.findElement(helpMarkLocator).click();
		// Contact Support
		driver.findElement(ReportIssueLocator).click();
		Wait(1);
		// driver.findElement(By.id("supportMessage")).clear(); //record
		driver.findElement(ContactSupportMsgField04).clear();
		// driver.findElement(By.id("supportMessage")).sendKeys("This is testing. Ignore please. ");//record
		driver.findElement(ContactSupportMsgField04).sendKeys("This is testing for Report Issue. Ignore please. ");
		// driver.findElement(By.cssSelector("button.btn.asc-secondary-btn")).click();//record
		driver.findElement(ContactSupportSubmitBtn05).click();
		Wait(2);

		// Verify email sent out to tdautoaa@gmail.com in QA
		if (chkEmail.equalsIgnoreCase("Yes")) {
			tc = "TC229325";
			DealerPortal.MailReader gMail = new MailReader();

			// String subject = "Support/Contact Us request from autotomsmith3@gmail.com";//This is from QA.
			String subject = "Support/Contact Us request from";// This is from QA.
			String text3 = "Additional Context";
			// (String mainID, String email, String mailPassword, String emailSuj,String content2, String content3, String tc)
			tc = "TC229325";
			gMail.VerifyGetMailContentFrSubContain("Imap.gmail.com", SupportEmail, SupportEmailPS, subject, text2,
					text3, tc);
		}
	}

	public void enterTextInSearch(String sVIN) {
		driver.findElement(searchVinOrStockNo).clear();
		driver.findElement(searchVinOrStockNo).sendKeys(sVIN);
		Wait(2);
		driver.findElement(searchVinOrStockNo).sendKeys(Keys.RETURN);
	}

	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}

	// end here!Preview tile
	public int getGenerateURLsNum(WebDriver driver, String tc) {
		int num;
		String temp;
		num = 0;
		String urlsTxt = driver.findElement(generateURLs).getText();
		num = urlsTxt.length();
		temp = urlsTxt.substring(14, num);
		num = Integer.parseInt(temp);
		return num;
	}

	public int getReRenderNum(WebDriver driver, String tc) {
		int num;
		String temp;
		num = 0;
		String renderNumTxt = driver.findElement(reRenderActiveBtn).getText();
		// num = urlsTxt.length();
		// temp = urlsTxt.substring(14, num);
		num = Integer.parseInt(renderNumTxt);
		return num;
	}

	public int getTileImageNum(WebDriver driver, String vehGUID, String tc) throws IOException {
		int num;
		String temp;
		num = 0;
		try {
			temp = driver.findElement(By.xpath("//div[@id='vehicle_" + vehGUID + "']/div")).getText();
			if (temp.contains("Failed")) {
				rwExcel(tc, false, "Get Tile ImageNumber", "GetTileImageNumber contains fail");
			} else {
				num = Integer.parseInt(temp);
			}
		} catch (Throwable e) {
			rwExcel(tc, false, "getTileImageNum vehGUID =" + vehGUID, "try failed! may increase the search number in previous step?");
		}
		return num;
	}

	public void scrollUp(WebDriver driver, int scrollNum, String tc) {

		// Window scroll down to make the custom image visible.
		JavascriptExecutor jsx = (JavascriptExecutor) driver;
		jsx.executeScript("window.scrollBy(0," + scrollNum + ")", "");
	}

	public void removeVINfrDealer(String dlrGuid, String sVin, String serverName, String dbName, String userName,
			String password, String tc) throws ClassNotFoundException, SQLException, IOException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		// Connection conn = DriverManager.getConnection("jdbc:sqlserver://LNOC-Q13V-MSQ2.autodata.org;user=VDVIWebServicesUserQA;password=HDuMy873JRFpkkU9;database=VDVI_Master");
		Connection conn = DriverManager.getConnection("jdbc:sqlserver://" + serverName + ";user=" + userName
				+ ";password=" + password + ";database=" + dbName);
		System.out.println("Delete VIN:" + sVin + " from DB");
		Statement sta = conn.createStatement();
		String Sql_1 = "delete from VT03_RenderedImage where VehGUID in (select vehguid from VT01_DealerVehicles where DlrGUID =   \'"
				+ dlrGuid + "\' AND (dbo.VT01_DealerVehicles.VIN in (\'" + sVin + "\')))";
		String Sql_2 = "delete from VT02_VehicleMetadata where VehGUID in (select vehguid from VT01_DealerVehicles where DlrGUID = \'"
				+ dlrGuid + "\' AND (dbo.VT01_DealerVehicles.VIN in (\'" + sVin + "\')))";
		String Sql_3 = "delete from VT01_DealerVehicles where VehGUID in (select vehguid from VT01_DealerVehicles where DlrGUID =  \'"
				+ dlrGuid + "\' AND (dbo.VT01_DealerVehicles.VIN in (\'" + sVin + "\')))";

		// *********rs1****************
		// ResultSet rs1 = sta.executeQuery(Sql_1); //Cannot use this since there is no return - ResultSet return.
		// Delete a VIN from VT03
		try {
			sta.execute(Sql_1);
			rwExcel(tc, true, "Delete a VIN " + sVin + " from VT03 passed", Sql_1);
		} catch (Exception e) {
			rwExcel(tc, false, "Delete a VIN " + sVin + " from VT03 failed", Sql_1);
		}
		// Delete a VIN from VT02
		try {
			sta.execute(Sql_2);
			rwExcel(tc, true, "Delete a VIN " + sVin + " from VT02 passed", Sql_2);
		} catch (Exception e) {
			rwExcel(tc, false, "Delete a VIN " + sVin + " from VT02 failed", Sql_2);
		}
		try {
			sta.execute(Sql_3);
			rwExcel(tc, true, "Delete a VIN " + sVin + " from VT01 passed", Sql_3);
		} catch (Exception e) {
			rwExcel(tc, false, "Delete a VIN " + sVin + " from VT01 failed", Sql_3);
		}

		sta.close();
		conn.close();
	}

	public boolean truefalseRandom() {
		Random r = new Random();
		int Low = 1;
		int High = 3;
		boolean truefalseResult = false;
		int Result = r.nextInt(High - Low) + Low;// 1 or 2
		if (Result == 1) {
			truefalseResult = true;
		} else {
			truefalseResult = false;
		}
		return truefalseResult;// return true or false;
	}
}
