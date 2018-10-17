package DealerPortal;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
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
			String gettitleString=driver.getTitle();
			System.out.println("Page title does not matche. Expected page title =" + sPageTitle);
		}
	}

	By viewInventoryBtnLocator = By.id("viewInventoryBtn");
	By dealerNameDropDownLocator = By.cssSelector("span.glyphicon.glyphicon-menu-down");
	By logOutLinkLocator = By.linkText("Logout");
	By dealerShipIDBrands = By.id("brands");
//	By userPassword = By.id("userPassword");
//	By userPasswordConfirm = By.id("userConfirm");
//	By saveBtn = By.id("saveBtn");
//	By reRenderNOBtn = By.xpath("//div[@id='rerenderPrompt']/div/div/div[3]/button[2]");
//
//	// SELECT LogoImageGUID FROM dbo.DT01_Dealer WHERE(DlrCode = '123456')
//	By logoImageGUID = By.xpath("//*[@id=\"dealer-logo\"]");
//	// By dealershipIDBrands = By.xpath("")
//	By dealershipNamelocator = By.id("dealerName");
//	By websitelocator = By.id("dealerSite");
//	By tagLinelocator = By.id("dealerTag");
//	By dealershipEmaillocator = By.id("dealerEmail");
//	By dealershipPhonelocator = By.id("dealerPhone");
//	By globalMarketingMsglocator = By.id("globalMarketingMessage");
//	By fisrtNamelocator = By.id("userFirstName");
//	By lastNamelocator = By.id("userLastName");
//	By addresslocator = By.id("dealerAddr1");
//	By citylocator = By.id("dealerCity");
//	By countrylocator = By.id("dealerCountry");
//	By stateProvincelocator = By.id("dealerState");
//	By zipPostalCodelocator = By.id("dealerZip");
	By receiveDailyInventoryEmaillocator = By.id("emailOptIn");
//	By dealershipBackground = By.xpath("//*[@id='formDealerInfo']/div[2]/div[2]/div/div/div");
//	By vinpxprod = By.xpath("//*[@id=\"formDealerInfo\"]/div[1]/div[2]/div/div[1]/div[2]/label[3]/img");
//	By lotpxprod = By.xpath("//*[@id=\"formDealerInfo\"]/div[1]/div[2]/div/div[1]/div[2]/label[4]/img");
//	By stockpxprod = By.xpath("//*[@id=\"formDealerInfo\"]/div[1]/div[2]/div/div[1]/div[2]/label[5]/img");
//
////	By webSiteProviderDropDownLocator=By.xpath("//*[@id='websiteProvider']/option["+num+"]");//num=1,2,3...
//	By addWebSiteProviderBtnLocator=By.xpath("//*[@id='formDealerInfo']/div[2]/div[2]/div/div/div[2]/div/div/button");
////	By brandDropDownLocator=By.xpath("//*[@id='dealerBrand']/option["+num+"]");//num=1,2,3...
//	By changeBtnLocator=By.xpath("//*[@id='uploadCaption']");
//	By xdeleteBtnLocator=By.xpath("//*[@id='removeLogo']");

//	********************************************************************************************
//	Templates:
	By dealerShipInfoBtnLocator=By.xpath("//*[@id='navbarTabs']/li[2]/a");
	By inventoryGalleryBtnLocator=By.xpath("//*[@id='navbarTabs']/li[1]/a");
	By templatesBtnLocator=By.xpath("//*[@id=\"navbarTabs\"]/li[3]/a");
	By saveBtnLocator=By.xpath("//*[@id=\"saveBtn\"]/span");
	
	By headerCheckBoxLocator=By.xpath("//label[1]/span");
	By headerEditLocator=By.xpath("//*[@id=\"headerEditBtn\"]");
	By headerContent_DealershipLogo_CheckBoxLocator=By.xpath("//*[@id='headerContent']/label[2]/span");
	By headerContent_DealershipAddress_CheckBoxLocator=By.xpath("//*[@id='headerContent']/label[3]/span");
	By headerContent_DealershipPhone_CheckBoxLocator=By.xpath("//*[@id='headerContent']/label[4]/span");
	By headerContent_DealershipEmail_CheckBoxLocator=By.xpath("//*[@id='headerContent']/label[5]/span");	
	By headerContent_DealershipWebsite_CheckBoxLocator=By.xpath("//*[@id='headerContent']/label[6]/span");
	
	By contentXBtnLocator=By.xpath("//*[@id=\"closeHeaderDiv\"]");//
	By contentSAVEBtnLocator=By.xpath("//*[@id=\"saveHeaderContentBtn\"]");	
	
	
	By footerCheckBoxLocator=By.xpath("//label[2]/span");
	By footerEditLocator=By.xpath("//*[@id=\"footerEditBtn\"]");
	By footerContent_VehicleInfo_CheckBoxLocator=By.xpath("//*[@id=\"footerContent\"]/label[2]/span");
	By footerContent_VIN_CheckBoxLocator=By.xpath("//*[@id=\"footerContent\"]/label[3]/span");
	By footerContent_StockNumber_CheckBoxLocator=By.xpath("//*[@id=\"footerContent\"]/label[4]/span");
	By footerContent_BrandLogo_CheckBoxLocator=By.xpath("//*[@id=\"footerContent\"]/label[5]/span");
	
	
	
	By overlayTopCheckBoxLocator=By.xpath("//label[3]/span");
	By overlayTopEditLocator=By.xpath("//*[@id=\"overlayTopEditBtn\"]");
	By overlayTopContent_globleMSG_fieldLocator=By.xpath("//*[@id=\"overlayContent\"]/textarea");
	
	
	
	
//	By Locator=By.xpath("");
//	By Locator=By.xpath("");
//	By Locator=By.xpath("");
//	By Locator=By.xpath("");
//	By Locator=By.xpath("");
//	By Locator=By.xpath("");
//	By Locator=By.xpath("");
//	By Locator=By.xpath("");
//	By Locator=By.xpath("");
//	By Locator=By.xpath("");
//	By Locator=By.xpath("");
//	By Locator=By.xpath("");
//	By Locator=By.xpath("");
//	By Locator=By.xpath("");
//	By Locator=By.xpath("");
//	By Locator=By.xpath("");
//	By Locator=By.xpath("");
//	By Locator=By.xpath("");
//	By Locator=By.xpath("");
//	By Locator=By.xpath("");
//	By Locator=By.xpath("");
//	By Locator=By.xpath("");	
	
	
	public DealerProfile clickDealerShipInfoBtn(WebDriver driver) throws IOException {
		driver.findElement(dealerShipInfoBtnLocator).click();
		return new DealerProfile(driver);
	}	
	
	public ImageGallery clickInventoryGalleryBtn(WebDriver driver) throws IOException {
		driver.findElement(inventoryGalleryBtnLocator).click();
		return new ImageGallery(driver);
	}
	
	public Templates clickTemplatesBtn(WebDriver driver) throws IOException {
		driver.findElement(templatesBtnLocator).click();
		return this;
	}
	
	public Templates clickSaveBtn(WebDriver driver) throws IOException {
		driver.findElement(saveBtnLocator).click();
		return this;
	}
	
	
	public Templates clickHeaderCheckBox(WebDriver driver) throws IOException {
		driver.findElement(headerCheckBoxLocator).click();
		return this;
	}
	public Templates clickHeaderEditBtn(WebDriver driver) throws IOException {
		driver.findElement(headerEditLocator).click();
		return this;
	}
	public Templates clickDealershipLogoCheckBox(WebDriver driver) throws IOException {
		driver.findElement(headerContent_DealershipLogo_CheckBoxLocator).click();
		return this;
	}
	public Templates clickDealershipAddrressCheckBox(WebDriver driver) throws IOException {
		driver.findElement(headerContent_DealershipAddress_CheckBoxLocator).click();
		return this;
	}
	public Templates clickDealershipPhoneCheckBox(WebDriver driver) throws IOException {
		driver.findElement(headerContent_DealershipPhone_CheckBoxLocator).click();
		return this;
	}
	public Templates clickDealershipEmailCheckBox(WebDriver driver) throws IOException {
		driver.findElement(headerContent_DealershipEmail_CheckBoxLocator).click();
		return this;
	}
	public Templates clickDealershipWebsiteCheckBox(WebDriver driver) throws IOException {
		driver.findElement(headerContent_DealershipWebsite_CheckBoxLocator).click();
		return this;
	}	
	
	
	
	public Templates clickFooterCheckBox(WebDriver driver) throws IOException {
		driver.findElement(footerCheckBoxLocator).click();
		return this;
	}
	public Templates clickFooterEditBtn(WebDriver driver) throws IOException {
		driver.findElement(footerEditLocator).click();
		return this;
	}
	public Templates clickVehicleInfoCheckBox(WebDriver driver) throws IOException {
		driver.findElement(footerContent_VehicleInfo_CheckBoxLocator).click();
		return this;
	}
	public Templates clickVINCheckBox(WebDriver driver) throws IOException {
		driver.findElement(footerContent_VIN_CheckBoxLocator).click();
		return this;
	}	
	public Templates clickStockNumberCheckBox(WebDriver driver) throws IOException {
		driver.findElement(footerContent_StockNumber_CheckBoxLocator).click();
		return this;
	}		
	public Templates clickBrandLogoCheckBox(WebDriver driver) throws IOException {
		driver.findElement(footerContent_BrandLogo_CheckBoxLocator).click();
		return this;
	}
		
	public Templates clickOverlayTopCheckBox(WebDriver driver) throws IOException {
		driver.findElement(overlayTopCheckBoxLocator).click();
		return this;
	}
	public Templates clickOverlayTopEditBtn(WebDriver driver) throws IOException {
		driver.findElement(overlayTopEditLocator).click();
		return this;
	}
	public Templates inputGlbMsgIntoOverlayContentField(WebDriver driver,String glbMsg) throws IOException {
		driver.findElement(overlayTopContent_globleMSG_fieldLocator).click();
		return this;
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

	public Templates clickBrandAndSelectNum(WebDriver driver,int num) throws IOException {
		By brandDropDownLocator=By.xpath("//*[@id='dealerBrand']/option["+num+"]");//num=1,2,3...
		driver.findElement(brandDropDownLocator).click();
		return this;
	}
	public ImageGallery clickInventoryGalleryBtn(WebDriver driver, String tc) throws IOException {
		driver.findElement(inventoryGalleryBtnLocator).click();
		return new ImageGallery(driver);
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



	public void jSONParse() {
		// String text = "{\"employees\":[{\"firstName\":\"John\",\"lastName\":\"Doe\" },{\"firstName\":\"Anna\",\"lastName\":\"Smith\" },{\"firstName\":\"Peter\",\"lastName\":\"Jones\" }]}";
		// String text="{\"posts\": [{\"post_id\": \"123456789012_123456789012\",\"actor_id\": \"1234567890\",\"picOfPersonWhoPosted\": \"http://example.com/photo.jpg\",\"nameOfPersonWhoPosted\": \"Jane Doe\",\"message\": \"Sounds cool. Can't wait to see it!\",\"likesCount\": \"2\",\"comments\": [],\"timeOfPost\": \"1234567890\"} ]}";
		String text="{\"posts\": [{\"post_id\": \"123456789012_123456789012_1\",\"actor_id\": \"12345678901\",\"picOfPersonWhoPosted\": \"http://example.com/photo.jpg1\",\"nameOfPersonWhoPosted\": \"Jane Doe1\",\"message\": \"1Sounds cool. Can't wait to see it!\",\"likesCount\": \"1\",\"comments\": [],\"timeOfPost\": \"12345678901\"},{\"post_id\": \"123456789012_123456789012_2\",\"actor_id\": \"12345678902\",\"picOfPersonWhoPosted\": \"http://example.com/photo.jpg2\",\"nameOfPersonWhoPosted\": \"Jane Doe2\",\"message\": \"Sounds cool. Can't wait to see it!\",\"likesCount\": \"2\",\"comments\": [],\"timeOfPost\": \"12345678902\"},{\"post_id\": \"123456789012_123456789012_3\",\"actor_id\": \"12345678903\",\"picOfPersonWhoPosted\": \"http://example.com/photo.jpg3\",\"nameOfPersonWhoPosted\": \"Jane Doe3\",\"message\": \"Sounds cool. Can't wait to see it!\",\"likesCount\": \"3\",\"comments\": [],\"timeOfPost\": \"12345678903\"} ]}";		JSONObject obj = new JSONObject(text);
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
//			String comments = arr.getJSONObject(i).getString("comments");
			String timeOfPost = arr.getJSONObject(i).getString("timeOfPost");

			System.out.println("post_id:" + post_id + ", actor_id:" + actor_id + ", picOfPersonWhoPosted:"
					+ picOfPersonWhoPosted + ", nameOfPersonWhoPosted:" + nameOfPersonWhoPosted + ", message:" + message
					+ ", likesCount:" + likesCount + ", timeOfPost:" + timeOfPost);
		}
		System.out.println("Stop here!!!");
	}
}
