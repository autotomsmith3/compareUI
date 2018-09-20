package DealerPortal;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.json.*;

public class DealerProfile extends Comlibs {
	private final WebDriver driver;

	public DealerProfile(WebDriver driver) throws IOException {
		this.driver = driver;
		// String wh1=driver.getWindowHandle();
		String sPageTitle = "Dealer Profile";
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
	By userPassword = By.id("userPassword");
	By userPasswordConfirm = By.id("userConfirm");
	By saveBtn = By.id("saveBtn");
	By reRenderNOBtn = By.xpath("//div[@id='rerenderPrompt']/div/div/div[3]/button[2]");

	// SELECT LogoImageGUID FROM dbo.DT01_Dealer WHERE(DlrCode = '123456')
	By logoImageGUID = By.xpath("//*[@id=\"dealer-logo\"]");
	// By dealershipIDBrands = By.xpath("")
	By dealershipNamelocator = By.id("dealerName");
	By websitelocator = By.id("dealerSite");
	By tagLinelocator = By.id("dealerTag");
	By dealershipEmaillocator = By.id("dealerEmail");
	By dealershipPhonelocator = By.id("dealerPhone");
	By globalMarketingMsglocator = By.id("globalMarketingMessage");
	By fisrtNamelocator = By.id("userFirstName");
	By lastNamelocator = By.id("userLastName");
	By addresslocator = By.id("dealerAddr1");
	By citylocator = By.id("dealerCity");
	By countrylocator = By.id("dealerCountry");
	By stateProvincelocator = By.id("dealerState");
	By zipPostalCodelocator = By.id("dealerZip");
	By receiveDailyInventoryEmaillocator = By.id("emailOptIn");
	By dealershipBackground = By.xpath("//*[@id='formDealerInfo']/div[2]/div[2]/div/div/div");
	By vinpxprod = By.xpath("//*[@id=\"formDealerInfo\"]/div[1]/div[2]/div/div[1]/div[2]/label[3]/img");
	By lotpxprod = By.xpath("//*[@id=\"formDealerInfo\"]/div[1]/div[2]/div/div[1]/div[2]/label[4]/img");
	By stockpxprod = By.xpath("//*[@id=\"formDealerInfo\"]/div[1]/div[2]/div/div[1]/div[2]/label[5]/img");

//	By webSiteProviderDropDownLocator=By.xpath("//*[@id='websiteProvider']/option["+num+"]");//num=1,2,3...
	By addWebSiteProviderBtnLocator=By.xpath("//*[@id='formDealerInfo']/div[2]/div[2]/div/div/div[2]/div/div/button");
//	By brandDropDownLocator=By.xpath("//*[@id='dealerBrand']/option["+num+"]");//num=1,2,3...
	By changeBtnLocator=By.xpath("//*[@id='uploadCaption']");
	By xdeleteBtnLocator=By.xpath("//*[@id='removeLogo']");
//	By Locator=By.xpath("");
//	By Locator=By.xpath("");
	
	
	
	
	
	
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
	public DealerProfile clickWebsiteProviderAndSelectNum(WebDriver driver,int num) throws IOException {
		By webSiteProviderDropDownLocator=By.xpath("//*[@id='websiteProvider']/option["+num+"]");//num=1,2,3...
		driver.findElement(webSiteProviderDropDownLocator).click();
		return this;
	}
	public DealerProfile clickBrandAndSelectNum(WebDriver driver,int num) throws IOException {
		By brandDropDownLocator=By.xpath("//*[@id='dealerBrand']/option["+num+"]");//num=1,2,3...
		driver.findElement(brandDropDownLocator).click();
		return this;
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

	public DealerProfile changePS(WebDriver driver, String ps, String tc) throws IOException {
		driver.findElement(userPassword).clear();
		driver.findElement(userPassword).sendKeys(ps);
		driver.findElement(userPasswordConfirm).clear();
		driver.findElement(userPasswordConfirm).sendKeys(ps);
		driver.findElement(saveBtn).click();
		return new DealerProfile(driver);
	}

	public DealerProfile clickNOBtn(WebDriver driver, String tc) throws IOException {
		driver.findElement(reRenderNOBtn).click();
		return new DealerProfile(driver);
	}

	public void verifyTitleOfDealershipBackground(WebDriver driver, String[] backgrounds, String backgroundSelected,
			String tc) throws IOException {
		elementExist(driver, dealershipBackground, true, tc);
		boolean in = false;
		boolean found = false;
		String bgSelected = "";
		int count = 0;
		String[] nameList = GetListWebName(driver, dealershipBackground, "", 10);// (WebDriver driver, By elementLocator, String myId, int NameNum);
		List<WebElement> background = driver.findElements(dealershipBackground);
		int numberOfBackgroundFrSite = driver.findElements(dealershipBackground).size();
		int numberOfBackgroundFrSettings = backgrounds.length;
		if (numberOfBackgroundFrSite == numberOfBackgroundFrSettings) {
			rwExcel(tc, true, "Verify number of Dealership Backgrounds",
					"Dealership Backgrounds =" + numberOfBackgroundFrSite);
			for (String bg : backgrounds) {
				found = false;
				for (int n = 0; n < numberOfBackgroundFrSettings; n++) {

					if (bg.equals(nameList[n])) {
						in = true;
						found = true;
						count++;
						// verify the background is selected
						if (bg.equals(backgroundSelected)) {
							// it matches backgroundSelected. Verify if it is selected
							bgSelected = driver.findElement(By.xpath("//*[@id='bg-" + n + "']/div[1]"))
									.getAttribute("class");

							if (bgSelected.contains("veh-img-tile-selected-ok")) {
								rwExcel(tc, true, "Verify if background is selected",
										"Background tilte=\"" + bg + "\" is selected as expected");
							} else {
								rwExcel(tc, false, "Verify if background is selected",
										"Background tilte=\"" + bg + "\" is not selected");
							}
						}
					} else if ((n == numberOfBackgroundFrSettings - 1) && (!found)) {
						// not found
						rwExcel(tc, false, "Verify background title",
								"Background tilte=\"" + bg + "\" does not show in the page");
					} else {
						// not finish yet. Continue...
					}
				}
			}
			if (in && count == numberOfBackgroundFrSettings) {
				rwExcel(tc, true, "Verify background title", "Background tiltes show as expected");
			} else {
				rwExcel(tc, false, "Verify background titles",
						"Background tiltes do not show as expected. Total matched=" + count + ". Expected="
								+ numberOfBackgroundFrSettings);
			}

		} else {
			rwExcel(tc, false, "Verify number of Dealership Backgrounds", "Expected Dealership Backgrounds ="
					+ numberOfBackgroundFrSettings + ". Site shows " + numberOfBackgroundFrSite);
			System.out.println("Expected Dealership Backgrounds =" + numberOfBackgroundFrSettings + ". Site shows "
					+ numberOfBackgroundFrSite);
		}
	}

	public void verifyProds(WebDriver driver, boolean vinpxP, boolean lotpxP, boolean stockpxP, String tc)
			throws IOException {
		String vP = "";
		String lP = "";
		String sP = "";
		elementExist(driver, vinpxprod, true, tc);
		elementExist(driver, lotpxprod, true, tc);
		elementExist(driver, stockpxprod, true, tc);
		vP = driver.findElement(vinpxprod).getAttribute("src");
		lP = driver.findElement(lotpxprod).getAttribute("src");
		sP = driver.findElement(stockpxprod).getAttribute("src");
		// Verify vinpx
		if (vinpxP) {
			// vinpx prod
			if (vP.contains("VINpx.png")) {
				// pass
				rwExcel(tc, true, "Verify VINpx prod setting", "Product - VINpx is selected.");
			} else {
				// fail
				rwExcel(tc, false, "Verify VINpx prod setting", "Product - VINpx is NOT selected.");
			}
		} else {
			// not vinpx prod
			if (vP.contains("VINpx_grey.png")) {
				// pass
				rwExcel(tc, true, "Verify VINpx prod setting", "Product - VINpx is not selected as expected.");
			} else {
				// fail
				rwExcel(tc, false, "Verify VINpx prod setting", "Product - VINpx is selected when should not.");
			}
		}
		// Verify LOTpx
		if (lotpxP) {
			// LOTpx prod
			if (lP.contains("LOTpx.png")) {
				// pass
				rwExcel(tc, true, "Verify LOTpx prod setting", "Product - LOTpx is selected.");
			} else {
				// fail
				rwExcel(tc, false, "Verify LOTpx prod setting", "Product - LOTpx is NOT selected.");
			}
		} else {
			// not LOTpx prod
			if (lP.contains("LOTpx_grey.png")) {
				// pass
				rwExcel(tc, true, "Verify LOTpx prod setting", "Product - LOTpx is not selected as expected.");
			} else {
				// fail
				rwExcel(tc, false, "Verify LOTpx prod setting", "Product - LOTpx is selected when should not.");
			}
		}
		// Verify STOCKpx
		if (stockpxP) {
			// STOCKpx prod
			if (sP.contains("STOCKpx.png")) {
				// pass
				rwExcel(tc, true, "Verify STOCKpx prod setting", "Product - STOCKpx is selected.");
			} else {
				// fail
				rwExcel(tc, false, "Verify STOCKpx prod setting", "Product - STOCKpx is NOT selected.");
			}
		} else {
			// not STOCKpx prod
			if (sP.contains("STOCKpx_grey.png")) {
				// pass
				rwExcel(tc, true, "Verify STOCKpx prod setting", "Product - STOCKpx is not selected as expected.");
			} else {
				// fail
				rwExcel(tc, false, "Verify STOCKpx prod setting", "Product - STOCKpx is selected when should not.");
			}
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

	public void verifyCountryStateDropDowns(WebDriver driver, String country, String state, String tc)
			throws IOException {
		String cty = "";
		String sts = "";
		elementExist(driver, countrylocator, true, tc);
		elementExist(driver, stateProvincelocator, true, tc);
		// new Select(driver.findElement(countrylocator)).selectByVisibleText("Canada");
		cty = new Select(driver.findElement(countrylocator)).getFirstSelectedOption().getText();

		// new Select(driver.findElement(stateProvincelocator)).selectByVisibleText("ON");
		sts = new Select(driver.findElement(stateProvincelocator)).getFirstSelectedOption().getText();

		if (cty.equals(country)) {
			rwExcel(tc, true, "Verify the Country setting", "It matches as expected.");
		} else {
			rwExcel(tc, false, "Verify the Country setting",
					"It does not match. Expected Country=" + country + ". The site shows=\"" + cty + "\"");
		}
		if (sts.equals(state)) {
			rwExcel(tc, true, "Verify the State setting", "It matches as expected.");
		} else {
			rwExcel(tc, false, "Verify the State setting",
					"It does not match. Expected State=" + state + ". The site shows=\"" + sts + "\"");
		}
	}

	public void verifys(WebDriver driver, String country, String state, String tc) throws IOException {
		String cty = "";
		String sts = "";
		elementExist(driver, countrylocator, true, tc);
		elementExist(driver, stateProvincelocator, true, tc);
		// new Select(driver.findElement(countrylocator)).selectByVisibleText("Canada");
		cty = new Select(driver.findElement(countrylocator)).getFirstSelectedOption().getText();

		// new Select(driver.findElement(stateProvincelocator)).selectByVisibleText("ON");
		sts = new Select(driver.findElement(stateProvincelocator)).getFirstSelectedOption().getText();

		if (cty.equals(country)) {
			rwExcel(tc, true, "Verify the Country setting", "It matches as expected.");
		} else {
			rwExcel(tc, false, "Verify the Country setting",
					"It does not match. Expected Country=" + country + ". The site shows=\"" + cty + "\"");
		}
		if (sts.equals(state)) {
			rwExcel(tc, true, "Verify the State setting", "It matches as expected.");
		} else {
			rwExcel(tc, false, "Verify the State setting",
					"It does not match. Expected State=" + state + ". The site shows=\"" + sts + "\"");
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
