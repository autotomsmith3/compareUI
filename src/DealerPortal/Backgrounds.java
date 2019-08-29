package DealerPortal;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.json.*;

public class Backgrounds extends Comlibs {
	private final WebDriver driver;

	public Backgrounds(WebDriver driver) throws IOException {
		this.driver = driver;
		// String wh1=driver.getWindowHandle();
		String sPageTitle = "Backgrounds";
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

	By backgroundsLocator = By.xpath("//*[@id=\"navbarTabs\"]/li[4]/a");
	By AddBackground = By.xpath("//*[@id=\"root\"]/div/div/button");

	By viewInventoryBtnLocator = By.id("viewInventoryBtn");
	By dealerNameDropDownLocator = By.cssSelector("span.glyphicon.glyphicon-menu-down");
	By logOutLinkLocator = By.linkText("Logout");
	By dealerShipIDBrands = By.id("brands");

	By receiveDailyInventoryEmaillocator = By.id("emailOptIn");

	// ********************************************************************************************
	// Backgrounds:
	By dealerShipInfoBtnLocator = By.xpath("//*[@id='navbarTabs']/li[2]/a");
	By inventoryGalleryBtnLocator = By.xpath("//*[@id='navbarTabs']/li[1]/a");
	By yearDropDownLocator = By.xpath("//select");
	By makeDropDownLocator = By.xpath("//th[5]/select");
	By addBackgrounds = By.xpath("//*[@id=\"root\"]/div/div/button");
	By cccLocator = By.xpath("//*[@id=\"yearDropdown-82D0EBFF-31C8-4E62-A248-F644D7D0A135\"]");
	// By addLocator=By.xpath("/html/body/div[4]/div[2]/div/div/div[2]/div/div/div/div[2]/button"); //ok
	By addLocator = By.xpath("//div[2]/button");// position - ok
	// By addLocator=By.xpath("(//button[@type='button'])[7]");//attribute, //seems not good

	// By Locator=By.xpath("");
	// By Locator=By.xpath("");
	// By Locator=By.xpath("");
	// By Locator=By.xpath("");
	// By Locator=By.xpath("");

	public Backgrounds clickBackgroundsBtn(WebDriver driver, String tc) throws IOException {
		elementExist(driver, AddBackground, true, tc);
		driver.findElement(AddBackground).click();
		return this;
	}

	public Backgrounds clickYearDropDown(WebDriver driver, String tc) throws IOException {
		elementExist(driver, yearDropDownLocator, true, tc);
		driver.findElement(yearDropDownLocator).click();
		return this;
	}

	public Backgrounds clickAddBackgrounds(WebDriver driver, String tc) throws IOException {
		elementExist(driver, addBackgrounds, true, tc);
		driver.findElement(addBackgrounds).click();
		return new Backgrounds(driver);
	}

	public Backgrounds clickAddBtn(WebDriver driver, String tc) throws IOException {
		elementExist(driver, addLocator, true, tc);
		driver.findElement(addLocator).click();
		return new Backgrounds(driver);
	}

	public ImageGallery clickInventoryGalleryBtn(WebDriver driver) throws IOException {
		driver.findElement(inventoryGalleryBtnLocator).click();
		return new ImageGallery(driver);
	}

	public Backgrounds clickBackground(WebDriver driver, int num, String tc) throws IOException {
		By selectBackground = By
				.xpath("/html/body/div[2]/div[2]/div/div/div[2]/div/div/div/div[1]/div[" + num + "]/div/div[1]/img");// 1,2,3....level count
		elementExist(driver, selectBackground, true, tc);
		driver.findElement(selectBackground).click();
		return this;
	}
	public String getBackgroundName(WebDriver driver, int num, String tc) throws IOException {
		By selectBackground = By
				.xpath("/html/body/div[2]/div[2]/div/div/div[2]/div/div/div/div[1]/div["+num+"]/div/div[1]/img");// 1,2,3....level count
		           //     /html/body/div[2]/div[2]/div/div/div[2]/div/div/div/div[1]/div[11]/div/div[1]/img
		
		elementExist(driver, selectBackground, true, tc);
		String bgName=driver.findElement(selectBackground).getAttribute("alt");
		return bgName;
	}
	public Backgrounds clickXButton(WebDriver driver, int num, String tc) throws IOException {
		num = num + 1;
		By xBtn = By.cssSelector("tr:nth-child(" + num + ") .btn");// 1,2,3....vertical count
		elementExist(driver, xBtn, true, tc);
		driver.findElement(xBtn).click();
		return this;
	}
	
	public Backgrounds selectYear(WebDriver driver, int num, String tc) throws IOException {
		num = num + 2;
		By yearLocator = By.xpath("//div[@id='root']/div/table/tbody/tr[" + num + "]/th[4]/select");//ok but only open the dropdown menu    //*[@id="2019"]
		
		elementExist(driver, yearLocator, true, tc);
		driver.findElement(yearLocator).click();
		return this;
	}

	public String getBackgroundSetName(WebDriver driver, int num, String tc) throws IOException {
		num = num + 1;
		By backGroundSetLocator = By.xpath("//div[@id='root']/div/table/tbody/tr[" + num + "]/th[3]");//ok
		
		//*[@id="root"]/div/table/tbody/tr[2]/th[3]
		//*[@id="root"]/div/table/tbody/tr[3]/th[3]
		
		elementExist(driver, backGroundSetLocator, true, tc);
		String bgSetName=driver.findElement(backGroundSetLocator).getText();//ok
		return bgSetName;
	}
	
	public int getBGSetRow(WebDriver driver, String bgSetNameString, String tc) throws IOException {
		int SetNum=0;
		String bgSetNamefrPage="";
		By backGroundSetLocator = By.xpath("//*[@id='root']/div/table/tbody/tr");//
		//*[@id="root"]/div/table/tbody/tr[2]   - 1
		//*[@id="root"]/div/table/tbody/tr[3]/th[3]   - 2
		
		elementExist(driver, backGroundSetLocator, true, tc);
		int bgSetSize=driver.findElements(backGroundSetLocator).size();
		
		for (int i=2;i<=bgSetSize;i++) {
			By bgSetName=By.xpath("//*[@id='root']/div/table/tbody/tr["+i+"]/th[3]");//
			bgSetNamefrPage=driver.findElement(bgSetName).getText();
			if (bgSetNamefrPage.equalsIgnoreCase(bgSetNameString)) {
				SetNum=i-1;
				break;
			}else if (i==bgSetSize) {
				System.out.println("Failed to get bgSet number!");
				rwExcel(tc, false, "Get the background to match the background set name","Fails to get the number. Returns 0!");
			}
			
		}
		return SetNum;
	}
	
	public int getBGSetTotalRows(WebDriver driver, String tc) throws IOException {
		By backGroundSetLocator = By.xpath("//*[@id='root']/div/table/tbody/tr");//
		elementExist(driver, backGroundSetLocator, true, tc);
		int bgSetSize=driver.findElements(backGroundSetLocator).size()-1;
		return bgSetSize;
	}
	
	
	public Backgrounds ssxxxxselectYearValue(WebDriver driver,int yearRowNum, String tc) throws IOException {
//		By yearnumLocator = By.xpath("//*[@id=\""+yearValue+"\"]"); // ok //*[@id="2019"]  //div[@id='root']/div/table/tbody/tr[2]/th[4]/select/2019
		yearRowNum=yearRowNum+1;
		By yearnumLocator = By.xpath("//div[@id='root']/div/table/tbody/tr["+yearRowNum+"]/th[4]/select"); //  //div[@id='root']/div/table/tbody/tr[2]/th[4]/select/2019
		elementExist(driver, yearnumLocator, true, tc);
		driver.findElement(yearnumLocator).click();
		return this;
	}
	
	public Backgrounds selectYearValue(WebDriver driver,int yearRowNum, String yearString, String tc) throws IOException {
//		By yearnumLocator = By.xpath("//*[@id=\""+yearValue+"\"]"); // ok //*[@id="2019"]  //div[@id='root']/div/table/tbody/tr[2]/th[4]/select/2019
		yearRowNum=yearRowNum+1;
		By yearnumLocator = By.xpath("//div[@id='root']/div/table/tbody/tr["+yearRowNum+"]/th[4]/select"); //  //div[@id='root']/div/table/tbody/tr[2]/th[4]/select/2019
		elementExist(driver, yearnumLocator, true, tc);
		Select drpYear=new Select(driver.findElement(yearnumLocator));
		drpYear.selectByValue(yearString);
		return this;
	}	
	
	
	
	public Backgrounds selectMakeValue(WebDriver driver,int makeRowNum, String makeString, String tc) throws IOException {
		makeRowNum=makeRowNum+1;
//		By makeLocator = By.xpath("//div[@id='root']/div/table/tbody/tr["+makeRowNum+"]/th[5]/select"); // 
		
		//*[@id="root"]/div/table/tbody/tr[2]/th[5]   //chrome copy xpath on parent div
		By makeLocator = By.xpath("//*[@id=\"root\"]/div/table/tbody/tr["+makeRowNum+"]/th[5]/select"); // 

		elementExist(driver, makeLocator, true, tc);
		Select drpMaker=new Select(driver.findElement(makeLocator));
		drpMaker.selectByValue(makeString);
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

	public ImageGallery clickInventoryGalleryBtn(WebDriver driver, String tc) throws IOException {
		driver.findElement(inventoryGalleryBtnLocator).click();
		return new ImageGallery(driver);
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
