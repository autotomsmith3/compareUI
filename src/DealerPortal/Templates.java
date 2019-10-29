package DealerPortal;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.json.*;

public class Templates extends Comlibs {
	private final WebDriver driver;

	public Templates(WebDriver driver) throws IOException {
		this.driver = driver;
		// String wh1=driver.getWindowHandle();
		String sPageTitle = "Templates";
		boolean existTitle = TitleDisplay(driver, sPageTitle);
		if (existTitle) {
			rwExcel("", true, "Page Title is displayed", sPageTitle);
		} else {
			rwExcel("", false, "Page title is NOT showing properly. Exceed time limit!",
					"The page title is NOT - " + sPageTitle);
		}
		if (!sPageTitle.equals(driver.getTitle())) {
			String gettitleString = driver.getTitle();
			System.out.println("Page title does not matche. Expected page title =" + sPageTitle);
		}
	}

	By viewInventoryBtnLocator = By.id("viewInventoryBtn");
	By dealerNameDropDownLocator = By.cssSelector("span.glyphicon.glyphicon-menu-down");
	By logOutLinkLocator = By.linkText("Logout");
	By dealerShipIDBrands = By.id("brands");

	By receiveDailyInventoryEmaillocator = By.id("emailOptIn");

	// ********************************************************************************************
	// Templates:
	By dealerShipInfoBtnLocator = By.xpath("//*[@id='navbarTabs']/li[2]/a");
	By inventoryGalleryBtnLocator = By.xpath("//*[@id='navbarTabs']/li[1]/a");
	By templatesBtnLocator = By.xpath("//*[@id=\"navbarTabs\"]/li[3]/a");
	By saveBtnLocator = By.xpath("//*[@id=\"saveBtn\"]/span");

	// By headerCheckBoxLocator = By.xpath("//*[@id=\"templateBuilder\"]/div[3]/form/label[1]");////*[@id="templateBuilder"]/div[3]/form/label[1]/span
	By headerCheckBoxLocator = By.xpath("//*[@id='templateBuilder']/div[2]/div[2]/label[1]/span");//  //*[@id="templateBuilder"]/div[2]/div[2]/label[1]/span
	By headerEditLocator = By.xpath("//*[@id=\"headerEditBtn\"]");
	By headerContent_DealershipLogo_CheckBoxLocator = By.xpath("//*[@id='headerContent']/label[2]/span");
	By headerContent_DealershipAddress_CheckBoxLocator = By.xpath("//*[@id='headerContent']/label[3]/span");
	By headerContent_DealershipPhone_CheckBoxLocator = By.xpath("//*[@id='headerContent']/label[4]/span");
	By headerContent_DealershipEmail_CheckBoxLocator = By.xpath("//*[@id='headerContent']/label[5]/span");
	By headerContent_DealershipWebsite_CheckBoxLocator = By.xpath("//*[@id='headerContent']/label[6]/span");

	By contentHeaderXBtnLocator = By.xpath("//*[@id='closeHeaderDiv']");
	By contentFooterXBtnLocator = By.xpath("//*[@id='closeFooterDiv']");
	By contentOverlayXBtnLocator = By.xpath("//*[@id='closeOverlayDiv']");

	By contentHeaderSAVEBtnLocator = By.xpath("//*[@id=\"saveHeaderContentBtn\"]");
	By contentFooterSAVEBtnLocator = By.xpath("//*[@id=\"saveFooterContentBtn\"]");
	By contentOverlaySAVEBtnLocator = By.xpath("//*[@id=\"saveOverlayContentBtn\"]");

	// By footerCheckBoxLocator = By.xpath("//*[@id='templateBuilder']/div[3]/form/label[2]/span"); // //*[@id="templateBuilder"]/div[3]/form/label[2]/span
	By footerCheckBoxLocator = By.xpath("//*[@id='templateBuilder']/div[2]/div[2]/label[2]/span");

	By footerEditLocator = By.xpath("//*[@id=\"footerEditBtn\"]");
	// By footerContent_VehicleInfo_CheckBoxLocator = By.xpath("//*[@id=\"footerContent\"]/label[2]/span");// new //*[@id="vehInfoLabel"]/span
	By footerContent_VehicleInfo_CheckBoxLocator = By.xpath("//*[@id=\"vehInfoLabel\"]/span");// new //*[@id="vehInfoLabel"]/span
	// By footerContent_VIN_CheckBoxLocator = By.xpath("//*[@id=\"footerContent\"]/label[3]/span");//old
	By footerContent_VIN_CheckBoxLocator = By.xpath("//*[@id=\"vinLabel\"]/span");// new
	// By footerContent_StockNumber_CheckBoxLocator = By.xpath("//*[@id=\"footerContent\"]/label[4]/span");//old
	By footerContent_StockNumber_CheckBoxLocator = By.xpath("//*[@id=\"stockLabel\"]/span");// new
	// By footerContent_BrandLogo_CheckBoxLocator = By.xpath("//*[@id=\"footerContent\"]/label[5]/span");// old //*[@id=\"footerContent\"]/label[5]/span ,new //*[@id="footerContent"]/label[2]/span
	By footerContent_BrandLogo_CheckBoxLocator = By.xpath("//*[@id=\"footerContent\"]/label[2]/span");// old //*[@id=\"footerContent\"]/label[5]/span ,new //*[@id="footerContent"]/label[2]/span

	// *[@id="templateBuilder"]/div[3]/label[3]/span
	// By MarketingMessageTopCheckBoxLocator = By.xpath("//*[@id='templateBuilder']/div[3]/label[3]/span");// //*[@id="templateBuilder"]/div[3]/form/label[3]/span
	By MarketingMessageTopCheckBoxLocator = By.xpath("//*[@id='templateBuilder']/div[2]/div[2]/label[3]/span");
	By MarketingMessageTopEditLocator = By.xpath("//*[@id=\"overlayTopEditBtn\"]");// //*[@id="overlayTopEditBtn"]
	By MarketingMessageTopContent_globleMSG_fieldLocator = By.xpath("//*[@id=\"overlayContent\"]/textarea");
	// By MarketingMessageBottomCheckBoxLocator = By.xpath("//*[@id='templateBuilder']/div[3]/label[4]/span");//
	By MarketingMessageBottomCheckBoxLocator = By.xpath("//*[@id='templateBuilder']/div[2]/div[2]/label[4]/span");//
	By MarketingMessageBottomEditLocator = By.xpath("//*[@id=\"overlayBottomEditBtn\"]");//
	By MarketingMessageBottomContent_globleMSG_fieldLocator = By.xpath("//*[@id=\"gmm\"]");
	By uploadOverlayImageBtn = By.xpath("//*[@id=\"uploadLogo\"]");
	By dealershipTemplate = By.xpath("//*[@id=\"dealer-template\"]");
	By dealershipTemplateXbtn = By.xpath("//*[@id=\"removeLogo\"]/span");
	// *[@id="templateBuilder"]/div[3]/label[5]/span //- AddAdditionalOverlay

	// *[@id="templateBuilder"]/div[3]/label[3]/span
	// *[@id="overlayTopEditBtn"]
	// *[@id="gmm"]
	// *[@id="templateBuilder"]/div[3]/label[4]/span
	// *[@id="overlayBottomEditBtn"]
	// *[@id="gmm"]

	// *[@id="saveOverlayContentBtn"]/span

	By replaceTemplateWithOwnCheckBoxLocator = By.xpath("//*[@id=\"templateBuilder\"]/div[3]/label[5]/span");//  Add Additional Overlay:
	//*[@id="templateBuilder"]/div[2]/div[2]/label[5]/span
	// *[@id="templateBuilder"]/div[3]/label[5]/span

	By uploadBtnLocator = By.xpath("//*[@id=\"dealerLogoContainer\"]/div[2]/div[1]/div");
	// *[@id="dealerLogoContainer"]/div[2]/div[1]/div

	By featuresVDILocator = By.xpath("//*[@id='main-container']/div/div[4]/div[2]/label/span");// //*[@id="main-container"]/div/div[5]/div[2]/label/span
	// *[@id="main-container"]/div/div[5]/div[2]/label/span   before 20190827
	//*[@id="main-container"]/div/div[4]/div[2]/label/span    after 20190827
	By whatsCoolWCILocator = By.xpath("//*[@id='main-container']/div/div[4]/div[3]/label/span");
	// *[@id="main-container"]/div/div[5]/div[3]/label/span   before 20190827
	//*[@id="main-container"]/div/div[4]/div[3]/label/span   after 20190827
	By benefitsVBILocator = By.xpath("//*[@id='main-container']/div/div[4]/div[4]/label/span");
	//*[@id='main-container']/div/div[5]/div[4]/label/span  before 20190827
	//*[@id="main-container"]/div/div[4]/div[4]/label/span  after 20190827

	By MessageDisplayedOnHead = By.xpath("//*[@id=\"successMessage\"]");
	// By Locator=By.xpath("");
	// By Locator=By.xpath("");

	public DealerProfile clickDealerShipInfoBtn(WebDriver driver) throws IOException {
		elementExist(driver, dealerShipInfoBtnLocator, true, "clickDealerShipInfoBtn");
		driver.findElement(dealerShipInfoBtnLocator).click();
		return new DealerProfile(driver);
	}

	public ImageGallery clickInventoryGalleryBtn(WebDriver driver) throws IOException {
		elementExist(driver, inventoryGalleryBtnLocator, true, "clickInventoryGalleryBtn");
		driver.findElement(inventoryGalleryBtnLocator).click();
		return new ImageGallery(driver);
	}

	public Templates clickTemplatesBtn(WebDriver driver) throws IOException {
		elementExist(driver, templatesBtnLocator, true, "clickTemplatesBtn");
		driver.findElement(templatesBtnLocator).click();
		return this;
	}

	public Templates clickSaveBtn(WebDriver driver) throws IOException {
		elementExist(driver, saveBtnLocator, true, "clickSaveBtn");
		driver.findElement(saveBtnLocator).click();
		return this;
	}

	public Templates clickContentHeaderSaveBtn(WebDriver driver) throws IOException {
		elementExist(driver, contentHeaderSAVEBtnLocator, true, "clickContentHeaderSaveBtn");
		driver.findElement(contentHeaderSAVEBtnLocator).click();
		return this;
	}

	public Templates clickContentFooterSaveBtn(WebDriver driver) throws IOException {
		elementExist(driver, contentFooterSAVEBtnLocator, true, "clickContentFooterSaveBtn");
		driver.findElement(contentFooterSAVEBtnLocator).click();
		return this;
	}

	public Templates clickContentOverlaySaveBtn(WebDriver driver) throws IOException {
		elementExist(driver, contentOverlaySAVEBtnLocator, true, "clickContentOverlaySaveBtn");
		driver.findElement(contentOverlaySAVEBtnLocator).click();
		return this;
	}

	public Templates clickHeaderXBtn(WebDriver driver) throws IOException {
		elementExist(driver, contentHeaderXBtnLocator, true, "clickHeaderXBtn");
		driver.findElement(contentHeaderXBtnLocator).click();
		return this;
	}

	public Templates clickFooterXBtn(WebDriver driver) throws IOException {
		elementExist(driver, contentFooterXBtnLocator, true, "clickFooterXBtn");
		driver.findElement(contentFooterXBtnLocator).click();
		return this;
	}

	public Templates clickOverlayXBtn(WebDriver driver) throws IOException {
		elementExist(driver, contentOverlayXBtnLocator, true, "clickOverlayXBtn");
		driver.findElement(contentOverlayXBtnLocator).click();
		return this;
	}

	public Templates clickHeaderCheckBoxNotReadyYet(WebDriver driver, boolean checkbox, String tc) throws IOException {
		By headerCheckBoxLocator = By.xpath("//*[@id=\"templateBuilder\"]/div[3]/label[1]/span"); // //div[@id='templateBuilder']/div[3]/form/label/span
		// By headerCheckBoxLocator=By.cssSelector("span.checkmark");// OK, css=form > .checkContain:nth-child(1) > .checkmark, css=span.checkmark
		// By headerCheckBoxLocator=By.xpath("//span[contains(@class,'checkmark')]"); //Saturday ok size =17
		// By headerCheckBoxLocator=By.xpath("//*[@id='templateBuilder']/div[3]/label[1]/span[contains(@class,'checkmark')]"); //Saturday ok size=1

		headerEditLocator = By.xpath("//*[@id=\"headerEditBtn\"]");
		footerCheckBoxLocator = By.xpath("//*[@id='templateBuilder']/div[3]/form/label[2]/span");
		// *[@id="templateBuilder"]/div[3]/form/label[1]/span
		// String CheckBoxStatus=driver.findElement(headerCheckBoxLocator).getAttribute("checkmark");
		/**
		 * How to use Java script to get content from Span ::after //from: https://stackoverflow.com/questions/28244911/selenium-webdriver-get-text-from-css-property-content-on-a-before-pseudo-ele/28265738#28265738
		 * 
		 * headerCheckBoxLocator=By.cssSelector("#templateBuilder > div:nth-child(3) > label:nth-child(1) > span"); WebElement switchLabel = driver.findElement(headerCheckBoxLocator); // WebElement switchLabel = driver.findElement(headerCheckBoxLocator); // String colorRGB = ((JavascriptExecutor)driver).executeScript("return window.getComputedStyle(arguments[0], ':before').getPropertyValue('background-color');",switchLabel).toString(); String colorRGB = ((JavascriptExecutor)driver).executeScript("return window.getComputedStyle(arguments[0], ':after').getPropertyValue('background-color');",switchLabel).toString();
		 * 
		 * System.out.println("1st test: "+colorRGB); headerCheckBoxLocator=By.cssSelector("#templateBuilder > div:nth-child(3) > label:nth-child(1) > span"); // switchLabel = driver.findElement(headerCheckBoxLocator); // String colorRGB = ((JavascriptExecutor)driver).executeScript("return window.getComputedStyle(arguments[0], ':before').getPropertyValue('background-color');",switchLabel).toString(); colorRGB = ((JavascriptExecutor)driver).executeScript("return window.getComputedStyle(arguments[0], ':after').getPropertyValue('background-color');",switchLabel).toString();
		 * 
		 * System.out.println("2nd test: "+colorRGB);
		 **/
		try {
			driver.findElement(headerEditLocator).click();
			System.out.println("1st test: click OK!");
		} catch (Exception e) {
			System.out.println("Failed to click");
			driver.findElement(headerCheckBoxLocator).click();
			driver.findElement(headerEditLocator).click();
		}
		System.out.println("1st test:");

		boolean element = driver.findElement(headerCheckBoxLocator).isDisplayed();
		// pressAnyKeyToContinue();
		element = driver.findElement(headerCheckBoxLocator).isEnabled();
		element = driver.findElement(headerCheckBoxLocator).isSelected();
		String ele = driver.findElement(headerCheckBoxLocator).getText();
		ele = driver.findElement(headerCheckBoxLocator).getAttribute("after");
		ele = driver.findElement(headerCheckBoxLocator).getText();
		int ssize = driver.findElements(headerCheckBoxLocator).size();

		String Status = driver.findElement(headerCheckBoxLocator).getText();
		System.out.println(element + Status);
		Status = driver.findElement(headerCheckBoxLocator).getTagName();// label
		Status = driver.findElement(headerCheckBoxLocator).getText();// Header

		boolean pageCheckBoxStatus = driver.findElement(headerCheckBoxLocator).isDisplayed();// when selected, it's true
		pageCheckBoxStatus = driver.findElement(headerCheckBoxLocator).isSelected();// when selected, it's false
		pageCheckBoxStatus = driver.findElement(headerCheckBoxLocator).isEnabled();// when selected, it's true

		pageCheckBoxStatus = driver.findElement(headerCheckBoxLocator).isDisplayed();// when selected, it's true
		pageCheckBoxStatus = driver.findElement(headerCheckBoxLocator).isSelected();// when selected, it's false
		pageCheckBoxStatus = driver.findElement(headerCheckBoxLocator).isEnabled();// when selected, it's true

		// Footer
		pageCheckBoxStatus = driver.findElement(footerCheckBoxLocator).isDisplayed();// when unselected, it's true
		pageCheckBoxStatus = driver.findElement(footerCheckBoxLocator).isSelected();// when unselected, it's false
		pageCheckBoxStatus = driver.findElement(footerCheckBoxLocator).isEnabled();// when unselected, it's true

		if (pageCheckBoxStatus && checkbox) {
			driver.findElement(headerCheckBoxLocator).click();
			driver.findElement(headerCheckBoxLocator).click();
			pageCheckBoxStatus = driver.findElement(headerCheckBoxLocator).isSelected();
			if (pageCheckBoxStatus) {
				rwExcel(tc, true, "Click Header CheckBox", "Header CheckBox is checked.");
			} else {
				rwExcel(tc, false, "Click Header CheckBox", "Header CheckBox is checked.");
			}
		} else if ((!checkbox) || pageCheckBoxStatus) {
			driver.findElement(headerCheckBoxLocator).click();
			pageCheckBoxStatus = driver.findElement(headerCheckBoxLocator).isSelected();
			if (!pageCheckBoxStatus) {
				rwExcel(tc, true, "Click Header to uncheck the CheckBox", "Header CheckBox is unchecked.");
			} else {
				rwExcel(tc, false, "Click Header to uncheck the CheckBox", "Header CheckBox is NOT unchecked.");
			}
		} else {
			driver.findElement(headerCheckBoxLocator).click();
			pageCheckBoxStatus = driver.findElement(headerCheckBoxLocator).isSelected();
			if (pageCheckBoxStatus) {
				rwExcel(tc, true, "Click Header CheckBox", "Header CheckBox is checked.");
			} else {
				rwExcel(tc, false, "Click Header CheckBox", "Header CheckBox is checked.");
			}
		}

		return this;
	}

	public Templates selectHeaderCheckBoxNotReadyYet(WebDriver driver, boolean checkbox1, boolean checkbox2,
			boolean checkbox3, boolean checkbox4, boolean checkbox5, String tc) throws IOException {
		/**
		 * * checkbox1-Logo, checkbox2-Address, checkbox3-Phone, checkbox4-Email, checkbox5-Website
		 */
		By headerContent_DealershipLogo_CheckBoxLocator = By.xpath("//*[@id='headerContent']/label[2]/span");

		boolean pageCheckBoxStatus = driver.findElement(headerContent_DealershipLogo_CheckBoxLocator).isDisplayed();// when selected, it's true
		pageCheckBoxStatus = driver.findElement(headerContent_DealershipLogo_CheckBoxLocator).isSelected();// when selected, it's false
		pageCheckBoxStatus = driver.findElement(headerContent_DealershipLogo_CheckBoxLocator).isEnabled();// when selected, it's true

		if (pageCheckBoxStatus && checkbox1) {
			driver.findElement(headerCheckBoxLocator).click();
			driver.findElement(headerCheckBoxLocator).click();
			pageCheckBoxStatus = driver.findElement(headerCheckBoxLocator).isSelected();
			if (pageCheckBoxStatus) {
				rwExcel(tc, true, "Click Header CheckBox", "Header CheckBox is checked.");
			} else {
				rwExcel(tc, false, "Click Header CheckBox", "Header CheckBox is checked.");
			}
		} else if ((!checkbox1) || pageCheckBoxStatus) {
			driver.findElement(headerCheckBoxLocator).click();
			pageCheckBoxStatus = driver.findElement(headerCheckBoxLocator).isSelected();
			if (!pageCheckBoxStatus) {
				rwExcel(tc, true, "Click Header to uncheck the CheckBox", "Header CheckBox is unchecked.");
			} else {
				rwExcel(tc, false, "Click Header to uncheck the CheckBox", "Header CheckBox is NOT unchecked.");
			}
		} else {
			driver.findElement(headerCheckBoxLocator).click();
			pageCheckBoxStatus = driver.findElement(headerCheckBoxLocator).isSelected();
			if (pageCheckBoxStatus) {
				rwExcel(tc, true, "Click Header CheckBox", "Header CheckBox is checked.");
			} else {
				rwExcel(tc, false, "Click Header CheckBox", "Header CheckBox is checked.");
			}
		}

		return this;
	}

	public Templates clickHeaderCheckBox(WebDriver driver) throws IOException {
		elementExist(driver, headerCheckBoxLocator, true, "clickHeaderCheckBox checkbox");
		driver.findElement(headerCheckBoxLocator).click();
		return this;
	}

	public Templates clickHeaderEditBtn(WebDriver driver) throws IOException {
		elementExist(driver, headerEditLocator, true, "clickHeaderEditBtn checkbox");
		driver.findElement(headerEditLocator).click();
		return this;
	}

	public Templates clickDealershipLogoCheckBox(WebDriver driver) throws IOException {
		elementExist(driver, headerContent_DealershipLogo_CheckBoxLocator, true, "clickDealershipLogoCheckBox checkbox");
		driver.findElement(headerContent_DealershipLogo_CheckBoxLocator).click();
		return this;
	}

	public Templates clickDealershipAddressCheckBox(WebDriver driver) throws IOException {
		elementExist(driver, headerContent_DealershipAddress_CheckBoxLocator, true, "clickDealershipAddressCheckBox checkbox");
		driver.findElement(headerContent_DealershipAddress_CheckBoxLocator).click();
		return this;
	}

	public Templates clickDealershipPhoneCheckBox(WebDriver driver) throws IOException {
		elementExist(driver, headerContent_DealershipPhone_CheckBoxLocator, true, "clickDealershipPhoneCheckBox checkbox");
		driver.findElement(headerContent_DealershipPhone_CheckBoxLocator).click();
		return this;
	}

	public Templates clickDealershipEmailCheckBox(WebDriver driver) throws IOException {
		elementExist(driver, headerContent_DealershipEmail_CheckBoxLocator, true, "clickDealershipEmailCheckBox checkbox");
		driver.findElement(headerContent_DealershipEmail_CheckBoxLocator).click();
		return this;
	}

	public Templates clickDealershipWebsiteCheckBox(WebDriver driver) throws IOException {
		elementExist(driver, headerContent_DealershipWebsite_CheckBoxLocator, true, "clickDealershipWebsiteCheckBox checkbox");
		driver.findElement(headerContent_DealershipWebsite_CheckBoxLocator).click();
		return this;
	}

	public Templates clickFooterCheckBox(WebDriver driver) throws IOException {
		elementExist(driver, footerCheckBoxLocator, true, "clickFooterCheckBox checkbox");
		driver.findElement(footerCheckBoxLocator).click();
		return this;
	}

	public Templates clickFooterEditBtn(WebDriver driver) throws IOException {
		elementExist(driver, footerEditLocator, true, "clickFooterEditBtn checkbox");
		driver.findElement(footerEditLocator).click();
		return this;
	}

	public Templates clickVehicleInfoCheckBox(WebDriver driver) throws IOException {
		elementExist(driver, footerContent_VehicleInfo_CheckBoxLocator, true, "clickVehicleInfoCheckBox checkbox");
		driver.findElement(footerContent_VehicleInfo_CheckBoxLocator).click();
		return this;
	}

	public Templates clickVINCheckBox(WebDriver driver) throws IOException {
		elementExist(driver, footerContent_VIN_CheckBoxLocator, true, "clickVINCheckBox checkbox");
		driver.findElement(footerContent_VIN_CheckBoxLocator).click();
		return this;
	}

	public Templates clickStockNumberCheckBox(WebDriver driver) throws IOException {
		elementExist(driver, footerContent_StockNumber_CheckBoxLocator, true, "clickStockNumberCheckBox checkbox");
		driver.findElement(footerContent_StockNumber_CheckBoxLocator).click();
		return this;
	}

	public Templates clickBrandLogoCheckBox(WebDriver driver) throws IOException {
		elementExist(driver, footerContent_BrandLogo_CheckBoxLocator, true, "clickBrandLogoCheckBox checkbox");
		driver.findElement(footerContent_BrandLogo_CheckBoxLocator).click();
		return this;
	}

	public Templates clickMarketingMessageTopCheckBox(WebDriver driver) throws IOException {
		elementExist(driver, MarketingMessageTopCheckBoxLocator, true, "clickMarketingMessageTopCheckBox checkbox");
		driver.findElement(MarketingMessageTopCheckBoxLocator).click();
		return this;
	}

	public Templates clickMarketingMessageBottomCheckBox(WebDriver driver) throws IOException {
		elementExist(driver, MarketingMessageBottomCheckBoxLocator, true, "clickMarketingMessageBottomCheckBox checkbox");
		driver.findElement(MarketingMessageBottomCheckBoxLocator).click();
		return this;
	}

	public Templates clickMarketingMessageTopEditBtn(WebDriver driver) throws IOException {
		elementExist(driver, MarketingMessageTopEditLocator, true, "xclickMarketingMessageTopEditBtnxxx checkbox");
		driver.findElement(MarketingMessageTopEditLocator).click();
		return this;
	}

	public Templates clickMarketingMessageBottomEditBtn(WebDriver driver) throws IOException {
		elementExist(driver, MarketingMessageBottomEditLocator, true, "clickMarketingMessageBottomEditBtn checkbox");
		driver.findElement(MarketingMessageBottomEditLocator).click();
		return this;
	}

	public Templates inputGlbMsgIntoMarketingMSGTopContentField(WebDriver driver, String glbMsg) throws IOException {
		elementExist(driver, MarketingMessageTopContent_globleMSG_fieldLocator, true, "inputGlbMsgIntoMarketingMSGTopContentField");
		driver.findElement(MarketingMessageTopContent_globleMSG_fieldLocator).clear();
		driver.findElement(MarketingMessageTopContent_globleMSG_fieldLocator).sendKeys(glbMsg);
		return this;
	}

	public Templates clickFeaturesVDICheckBox(WebDriver driver) throws IOException {
		elementExist(driver, featuresVDILocator, true, "VDI checkbox");
		driver.findElement(featuresVDILocator).click();
		return this;
	}

	public Templates clickWhatsCoolCheckBox(WebDriver driver) throws IOException {
		elementExist(driver, whatsCoolWCILocator, true, "WCI checkbox");
		driver.findElement(whatsCoolWCILocator).click();
		return this;
	}

	public Templates clickBenefitsVBICheckBox(WebDriver driver) throws IOException {
		elementExist(driver, benefitsVBILocator, true, "VBI checkbox");
		driver.findElement(benefitsVBILocator).click();
		return this;
	}

	public void scrollUp(WebDriver driver, int scrollNum, String tc) {

		// Window scroll down to make the custom image visible.
		JavascriptExecutor jsx = (JavascriptExecutor) driver;
		jsx.executeScript("window.scrollBy(0," + scrollNum + ")", "");
	}

	public ImageGallery clickViewInventoryBtn(WebDriver driver, String tc) throws IOException {
		boolean viewInventoryBtn = elementExist(driver, viewInventoryBtnLocator, true, tc);

		if (viewInventoryBtn) {
			driver.findElement(viewInventoryBtnLocator).click();
		} else {
			rwExcel(tc, false, "\"VIEW INVENTORY\" button", "The button does not exist.");
		}
		return new ImageGallery(driver);
	}

	public AUTOpxLogin clickLogout(WebDriver driver) throws IOException {
		driver.findElement(dealerNameDropDownLocator).click();
		driver.findElement(logOutLinkLocator).click();
		return new AUTOpxLogin(driver);
	}

	public Templates clickBrandAndSelectNum(WebDriver driver, int num) throws IOException {
		By brandDropDownLocator = By.xpath("//*[@id='dealerBrand']/option[" + num + "]");// num=1,2,3...
		driver.findElement(brandDropDownLocator).click();
		return this;
	}

	public ImageGallery clickInventoryGalleryBtn(WebDriver driver, String tc) throws IOException {
		driver.findElement(inventoryGalleryBtnLocator).click();
		return new ImageGallery(driver);
	}

	public Templates clickAddAdditionalOverlayCheckBox(WebDriver driver, String tc) throws IOException {
		driver.findElement(replaceTemplateWithOwnCheckBoxLocator).click();
		return this;
	}

	public Templates clickUploadBtn(WebDriver driver, String tc) throws IOException {
		driver.findElement(uploadBtnLocator).click();
		return this;
	}

	public Templates clickXonDealershipTemplate(WebDriver driver, String tc) throws IOException {
		try {
			driver.findElement(dealershipTemplateXbtn).click();
		} catch (Throwable e) {
			System.out.println("1. Failed to click X button on DealershipTemplate in Template page.");
			rwExcel(tc, false, "Click the X button to remove the Dealership Template", "failed to delete.");
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
			System.out.println("1. Accept the alert in Template page.");
			// rwExcel(tc, true, "Alert showing, Accept the alert =" + alertType, "Accetped successfully.");
		} catch (Throwable e) {
			alertPass = false;
			System.out.println("1. Failed to Accept the alert in Template page.");
			rwExcel(tc, false, "Alert showing, Accept the alert =" + alertType, "failed to accetp.");
		}
		// return alertPass;
	}

	public void verifyDealershipIDBrands(String dealershipID, String brands, String tc) throws IOException {
		// assertEquals("Lucas Zhou",
		// driver.findElement(By.cssSelector("span")).getText());
		String expectedText, rs;
		rs = driver.findElement(dealerShipIDBrands).getText();

		expectedText = dealershipID + brands;
		if (rs.equals(expectedText)) {
			rwExcel(tc, true, "DealershipID and Brands are displayed", expectedText);
		} else {
			rwExcel(tc, false, "DealershipID and Brands are not displayed proplerly", "DealershipID and Brands shows -"
					+ rs + "- ." + "Expected DealershipID and Brands =" + expectedText);
		}

	}

	public void verifyReceiveEailCheckBoxSetting(WebDriver driver, boolean receiveEmail, String tc) throws IOException {
		elementExist(driver, receiveDailyInventoryEmaillocator, true, tc);
		boolean cbx = driver.findElement(receiveDailyInventoryEmaillocator).isSelected();
		// Verify Receive Daily Inventory Email check box
		if (receiveEmail) {
			if (cbx) {
				rwExcel(tc, true, "Verify the checkbox of Receive Daily Inventory Email ",
						"The Checkbox is checked as expected.");
			} else {
				rwExcel(tc, false, "Verify the checkbox of Receive Daily Inventory Email ",
						"The Checkbox is NOT checked. Check your settings");
			}
		} else {
			if (!cbx) {
				rwExcel(tc, true, "Verify the checkbox of Receive Daily Inventory Email ",
						"The Checkbox is NOT checked as expected.");
			} else {
				rwExcel(tc, false, "Verify the checkbox of Receive Daily Inventory Email ",
						"The Checkbox is checked when should not. Check your settings");
			}
		}
	}

	public void pressAnyKeyToContinue() {
		System.out.println("Press Enter key to continue...");
		try {
			System.in.read();
		} catch (Exception e) {
		}
	}

	public boolean checkboxWithPseudoElement(String checkboxName, WebDriver driver, boolean checked, boolean secondTime,
			String pseudoElementSelectorID, String cssStyle, String cssStyleValue, String tc) throws IOException {

		// ***************return window.getComputedStyle(document.querySelector***********************************************************************************************************
		/**
		 * How to use xpath to find pseudo-element ::after in side a div element with out any content? https://stackoverflow.com/questions/51992258/xpath-to-find-pseudo-element-after-in-side-a-div-element-with-out-any-content
		 * 
		 * 1. Pseudo-elements don't exist in the DOM tree (hence the name), therefore they cannot be selected with XPath 2. Unfortunately, that's not possible with XPath. As mentioned by Tomalak, Pseudo-elements don't exist in the DOM tree (hence the name), therefore they cannot be selected with XPath and Selenium does not expose them as well. In general, ::before and ::after pseudo-elements are used for styling of containing element. 3. My solution: a. get all styles b. find out what is different between checked and unchecked c. getPropertyValue to identify checked and unchecked - transform-origin=50% 50% (unchecked) or 2.5px 5px (checked)
		 **/
		boolean isTrue = false;
		Wait(1);
		try {

			JavascriptExecutor js = (JavascriptExecutor) driver;
			// String tt=js.executeScript("return document.title").toString();//ok to see title
			// Dealership address: xpath://*[@id="headerContent"]/label[3]/span
			// Dealership address: selecter: #headerContent > label:nth-child(6) > span . #headerContent > label:nth-child(6)

			// window.getComputedStyle(document.querySelector('#element'),':after').getPropertyValue('content')

			// String Xscript = "return window.getComputedStyle(document.querySelector('#headerContent > label:nth-child(6) > span'),':after').getPropertyValue('transform-origin')";
			// //get all CSS styles
			// String script = "return window.window.getComputedStyle(document.querySelector('#headerContent > label:nth-child(6) > span'), ':after')";// get all CSS styles only Firefox
			// String script = "return window.getComputedStyle(document.querySelector('" + pseudoElementSelectorID
			// + "'),':after')";// get all CSS styles only Firefox
			String script = "return window.getComputedStyle(document.querySelector('" + pseudoElementSelectorID
					+ "'),':after').getPropertyValue('" + cssStyle + "')";//// get only value of transform-origin style. returned styles on checkbox checked and unchecked. On unchecked it contains one "moz-transform-origin=50% 50%"
			String xxchecked = js.executeScript(script).toString();
			System.out.println("xxchecked:" + xxchecked);
			Wait(1);
			// String searchTxt = "50% 50%";// moz-transform-origin=50% 50% (unchecked) or 2.5px 5px (checked)
			boolean siteChecked = xxchecked.contains(cssStyleValue);
			if (siteChecked) {
				if (!checked) {
					isTrue = true;
					/// write to excel pass; checkbox should not be checked
					System.out.println("Passed." + checkboxName + "  is not checked!");
					rwExcel(tc, true, "Verify " + checkboxName, "Checkbox is not checked");
				} else {
					isTrue = false;
					if (secondTime) {
						// write to excel failed; checkbox cannot be unchecked.
						System.out.println("Failed," + checkboxName + "  is unchecked when should be checked!");
						rwExcel(tc, false, "Verify " + checkboxName, "Checkbox is unchecked when should be checked!");
					}
				}
				System.out.println("The " + checkboxName + "  is unchecked");
			} else if (checked) {
				isTrue = true;
				System.out.println("Passed," + checkboxName + "  is checked");
				// write to excel pass; checkbox should be checked
				rwExcel(tc, true, "Verify " + checkboxName, "Checkbox is checked");
			} else {
				isTrue = false;
				if (secondTime) {
					// write to excel failed; checkbox cannot be unchecked.
					System.out.println("Failed," + checkboxName + " is checked when should be unchecked!");
					rwExcel(tc, false, "Verify " + checkboxName, "Checkbox is checked when should be unchecked!");
				}
			}
		} catch (Exception e) {
			isTrue = false;
			rwExcel(tc, false, "Verify " + checkboxName, "Checkbox cannot be identified!");
		}
		return isTrue;
	};

	public void uploadOverlayPicture(WebDriver driver, String imagePath, String tc) throws IOException {

		try {
			// driver.findElement(By.id("images")).sendKeys(imagePath); // By id - ok
			driver.findElement(uploadOverlayImageBtn).sendKeys(imagePath);// By X.Path - ok
			Wait(2);
			rwExcel(tc, true, "Upload Overlay image", "Click on Choose Files button successfully.");
		} catch (Exception e) {
			rwExcel(tc, false, "Upload Overlay image", "Click on Choose Files button.");
			Wait(2);
		}

	}

	public void verifyDealershipTemplate(WebDriver driver, String tc) throws IOException {

		VerifyImageLoaded(driver, dealershipTemplate, tc);

	}

	public boolean checkMessageDisplayedHead(WebDriver driver, String message, String tc) throws Exception {
		String msg = driver.findElement(MessageDisplayedOnHead).getText();// .getAttribute("messageBox");
		boolean messageExist = false;
		if (msg.equalsIgnoreCase(message)) {
			messageExist = true;
		} else {
			rwExcel(tc, false, "The message is displayed on head - Does NOT match",
					"Expected msg is:\"" + message + "\". The real message is displayed on head is:\"" + msg + "\"");
		}
		return messageExist;
	}

	public void jSONParse() {
		// String text = "{\"employees\":[{\"firstName\":\"John\",\"lastName\":\"Doe\" },{\"firstName\":\"Anna\",\"lastName\":\"Smith\" },{\"firstName\":\"Peter\",\"lastName\":\"Jones\" }]}";
		// String text="{\"posts\": [{\"post_id\": \"123456789012_123456789012\",\"actor_id\": \"1234567890\",\"picOfPersonWhoPosted\": \"http://example.com/photo.jpg\",\"nameOfPersonWhoPosted\": \"Jane Doe\",\"message\": \"Sounds cool. Can't wait to see it!\",\"likesCount\": \"2\",\"comments\": [],\"timeOfPost\": \"1234567890\"} ]}";
		String text = "{\"posts\": [{\"post_id\": \"123456789012_123456789012_1\",\"actor_id\": \"12345678901\",\"picOfPersonWhoPosted\": \"http://example.com/photo.jpg1\",\"nameOfPersonWhoPosted\": \"Jane Doe1\",\"message\": \"1Sounds cool. Can't wait to see it!\",\"likesCount\": \"1\",\"comments\": [],\"timeOfPost\": \"12345678901\"},{\"post_id\": \"123456789012_123456789012_2\",\"actor_id\": \"12345678902\",\"picOfPersonWhoPosted\": \"http://example.com/photo.jpg2\",\"nameOfPersonWhoPosted\": \"Jane Doe2\",\"message\": \"Sounds cool. Can't wait to see it!\",\"likesCount\": \"2\",\"comments\": [],\"timeOfPost\": \"12345678902\"},{\"post_id\": \"123456789012_123456789012_3\",\"actor_id\": \"12345678903\",\"picOfPersonWhoPosted\": \"http://example.com/photo.jpg3\",\"nameOfPersonWhoPosted\": \"Jane Doe3\",\"message\": \"Sounds cool. Can't wait to see it!\",\"likesCount\": \"3\",\"comments\": [],\"timeOfPost\": \"12345678903\"} ]}";
		JSONObject obj = new JSONObject(text);
		// String pageName = obj.getJSONObject("posts").getString("pageName");

		JSONArray arr = obj.getJSONArray("posts");
		// int arr.length();
		// String post_id=arr.getJSONObject(2).getString("post_id");

		for (int i = 0; i < arr.length(); i++) {
			String post_id = arr.getJSONObject(i).getString("post_id");
			String actor_id = arr.getJSONObject(i).getString("actor_id");

			String picOfPersonWhoPosted = arr.getJSONObject(i).getString("picOfPersonWhoPosted");
			String nameOfPersonWhoPosted = arr.getJSONObject(i).getString("nameOfPersonWhoPosted");
			String message = arr.getJSONObject(i).getString("message");
			String likesCount = arr.getJSONObject(i).getString("likesCount");
			// String comments = arr.getJSONObject(i).getString("comments");
			String timeOfPost = arr.getJSONObject(i).getString("timeOfPost");

			System.out.println("post_id:" + post_id + ", actor_id:" + actor_id + ", picOfPersonWhoPosted:"
					+ picOfPersonWhoPosted + ", nameOfPersonWhoPosted:" + nameOfPersonWhoPosted + ", message:" + message
					+ ", likesCount:" + likesCount + ", timeOfPost:" + timeOfPost);
		}
		System.out.println("Stop here!!!");
	}
}
