package AdminPortal;

/*Example to find the number of sub of an object:
      	By oneDealerLocator = By.xpath("//*[@id='allDealers']/option["+num+"]"); // 1,2,3...
		By allDealerLocator = By.xpath("//*[@id='allDealers']/option"); // 1,2,3...
		int allDealersNum=driver.findElements(allDealerLocator).size();
*/
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.json.*;

public class AccountProfile extends Comlibs {
	private final WebDriver driver;

	public AccountProfile(WebDriver driver) throws IOException {
		this.driver = driver;
		// String wh1=driver.getWindowHandle();
		String sPageTitle = "Account Profile";
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

	// *[@id="dealerState"]/option[33]
	// *[@id="selectedBackground"]/option[2]
	By DealershipIDLocator = By.xpath("//*[@id=\"dealerCode\"]");
	By DealershipNameLocator = By.xpath("//*[@id=\"dealerName\"]");
	By DealershipEmailLocator = By.xpath("//*[@id=\"dealerEmail\"]");
	By AccountEmailLocator = By.xpath("//*[@id=\"accEmail\"]");
	By MetadataLocator = By.xpath("//*[@id=\"metadata\"]");
	By BackToDealerListLocator = By.xpath("//*[@id=\"dealerListBtn\"]/span");
	By ProductVINpxLocator = By.xpath("//*[@id=\"vinpx\"]");
	By ProductSTOCKpxLocator = By.xpath("//*[@id=\"stockpx\"]");
	By ProductLOTpxLocator = By.xpath("//*[@id=\"lotpx\"]");
	// By OEMSelect=By.xpath("//*[@id=\"dealerOem\"]/option[13]");
	By AddrssLocator = By.xpath("//*[@id=\"dealerAddr1\"]");
	By AddressLine2Locator = By.xpath("//*[@id=\"dealerAddr2\"]");
	By CityLocator = By.xpath("//*[@id=\"dealerCity\"]");
	// By StateProvinceLocator=By.xpath("//*[@id=\"dealerState\"]/option[33]") ;//NY
	// By CountryLocator=By.xpath("//*[@id=\"dealerCountry\"]/option[1]") ;//USA
	By ZipPostalCodeLocator = By.xpath("//*[@id=\"dealerZip\"]");
	By UserDealerNameLocator = By.xpath("//*[@id=\"userCommonName\"]");

	By FirstNameLocator = By.xpath("//*[@id=\"userFirstName\"]");
	By LastNameLocator = By.xpath("//*[@id=\"userLastName\"]");
	By TagLineMarkingMsgLocator = By.xpath("//*[@id=\"dealerTag\"]");
	By WebsiteLocator = By.xpath("//*[@id=\"dealerSite\"]");
	By DealershipPhoneNumberLocator = By.xpath("//*[@id=\"dealerPhone\"]");
	// By TemplateSettingsLocator=By.xpath("//*[@id=\"selectedBackground\"]/option[1]") ;//DEFAULT=1; replace=2;overlay=3;
	By UploadBtn = By.xpath("//*[@id=\"dealerLogoContainer\"]/div[3]/div[1]");
	By ResetPasswordBtn = By.xpath("//*[@id=\"resetPassBtn\"]");
	By SaveBtn = By.xpath("//*[@id=\"saveBtn\"]/span");
	// By backGroundLocator=By.xpath("//*[@id=\"bg-7\"]/div[1]/img");//Generic Dealership=7; White Gradient=0
	// By backGroundLocator=By.xpath("//*[@id=\"bg-0\"]/div[1]/img");
	// *[@id="dealerListBtn"]/span
	By MessageDisplayedOnHead = By.xpath("//*[@id=\"header\"]/div/div[2]/span");
	// By AccountStatusLocator=By.xpath("//*[@id=\"userAccStatus\"]");
	By AccountStatusLocator = By.xpath("//*[@id='userAccStatus']/option[1]"); // 1- Active, 2- Lock out, 3-Change Password, 4-Disabled
	By SearchLocator = By.xpath("//*[@id=\"dealerTable_filter\"]/label/input");
	By msgOnPage = By.xpath("//*[@id=\"header\"]/div/div[2]");// //*[@id="header"]/div/div[2]/span
	By leftArrowDetachLocator = By.xpath("//*[@id='detachButton']");
	By rightArrowAttachLocator = By.xpath("//*[@id='attachButton']");

	public String getDealershipID(WebDriver driver) throws IOException {
		String dealershipip = driver.findElement(DealershipIDLocator).getAttribute("value");
		return dealershipip;
	}

	public String getDealershipName(WebDriver driver) throws IOException {
		String dealershipname = driver.findElement(DealershipNameLocator).getAttribute("value");
		return dealershipname;
	}

	public String getDealershipEmail(WebDriver driver) throws IOException {
		String dealerdshipemail = driver.findElement(DealershipEmailLocator).getAttribute("value");
		return dealerdshipemail;
	}

	public String getAccountEmail(WebDriver driver) throws IOException {
		String accountemail = driver.findElement(AccountEmailLocator).getAttribute("value");
		return accountemail;
	}

	public String getMetadata(WebDriver driver) throws IOException {
		String metadata = driver.findElement(MetadataLocator).getAttribute("value");
		return metadata;
	}

	public String getVINpxProduct(WebDriver driver, String tc) throws IOException {
		boolean elementExist = elementExist(driver, ProductVINpxLocator, true, tc);
		String getVINpx = "";
		if (elementExist) {
			getVINpx = driver.findElement(ProductVINpxLocator).getAttribute("checked");
		} else {
			getVINpx = "";
		}
		return getVINpx;
	}

	public String getSTOCKpxProduct(WebDriver driver, String tc) throws IOException {
		boolean elementExist = elementExist(driver, ProductSTOCKpxLocator, true, tc);
		String getSTOCKpx = "";
		if (elementExist) {
			getSTOCKpx = driver.findElement(ProductSTOCKpxLocator).getAttribute("checked");
		} else {
			getSTOCKpx = "";
		}
		return getSTOCKpx;
	}

	public String getLOTpxProduct(WebDriver driver, String tc) throws IOException {
		boolean elementExist = elementExist(driver, ProductLOTpxLocator, true, tc);
		String getLOTpx = "";
		if (elementExist) {
			getLOTpx = driver.findElement(ProductLOTpxLocator).getAttribute("checked");
		} else {
			getLOTpx = "";
		}
		return getLOTpx;
	}

	public DealerList clickBackToDealerListBtn(WebDriver driver, String windowHandle, String tc) throws IOException {
		boolean elementExist = elementExist(driver, BackToDealerListLocator, true, tc);
		if (elementExist) {
			driver.findElement(BackToDealerListLocator).click();
		} else {
			System.out.println("BackToDealerList button does not exist in the page!");
		}

		// get the current window handles before swithTo. This resolved the "driver.switchTo().window(windowHandle);" break sometimes.
		Set<String> windowHandles = driver.getWindowHandles();

		System.out.println("Original WindowHandle=" + windowHandle);
		System.out.println("WindowHandles=" + windowHandles);

		for (String NewWindowHandle : windowHandles) {
			windowHandle = NewWindowHandle;
		}

		driver.switchTo().window(windowHandle);

		return new DealerList(driver);
	}

	public AccountProfile clickSaveBtn(WebDriver driver, String tc) {
		driver.findElement(SaveBtn).click();
		return this;
	}

	public AccountProfile clickUploadBtn(WebDriver driver, int num) {
		driver.findElement(UploadBtn).click();
		return this;
	}

	public AccountProfile clickEditBtn(WebDriver driver, int num) {
		By EditBtnLocator = By.xpath("(//button[@id='accountViewBtn'])[" + num + "]"); // xpath=(//button[@id='accountViewBtn'])[2] //1,2,3...
		driver.findElement(EditBtnLocator).click();
		return this;
	}

	public AccountProfile clickLeftArrowDetachBtn(WebDriver driver) {
		driver.findElement(leftArrowDetachLocator).click();
		Wait(1);
		return this;
	}

	public AccountProfile clickRightArrowAttachBtn(WebDriver driver) {
		driver.findElement(rightArrowAttachLocator).click();
		Wait(1);
		return this;
	}

	public boolean checkMessageDisplayedHead(WebDriver driver, String message) {
		String msg = driver.findElement(MessageDisplayedOnHead).getText();// .getAttribute("messageBox");
		boolean messageExist = false;
		if (msg.equalsIgnoreCase(message)) {
			messageExist = true;
		}
		return messageExist;
	}

	public String getDlrGuid(WebDriver driver) throws IOException {
		String dlrGuid = driver.getCurrentUrl();

		return dlrGuid;
	}

	public String trimURL(String urlString) throws IOException {
		// urlString="//http://lnoc-q13v-xwa1.autodata.org/AdminPortal/dealersetting?dlrGuid=C9B47888-E227-4C53-8162-352CC650FA90";
		int beginIdx = urlString.indexOf("dlrGuid=") + 8;

		String dlrGuid = urlString.substring(beginIdx);

		return dlrGuid;
	}

	public AccountProfile selectAccountStatus(WebDriver driver, int num) {
		By AccountStatusLocator = By.xpath("//*[@id='userAccStatus']/option[" + num + "]"); // 1- Active, 2- Lock out, 3-Change Password, 4-Disabled
		driver.findElement(AccountStatusLocator).click();
		return this;
	}

	public AccountProfile selectDisplay(WebDriver driver, int num) {
		By DisplayLocator = By.xpath("//*[@id='dealerTable_length']/label/select/option[" + num + "]"); // 1- =50, 2- =100, 3- =150
		driver.findElement(DisplayLocator).click();
		return this;
	}

	public String selectOneDealerFrAllDealers(WebDriver driver, int num) {
		By oneDealerLocator = By.xpath("//*[@id='allDealers']/option[" + num + "]"); // 1,2,3...
		By allDealerLocator = By.xpath("//*[@id='allDealers']/option"); // 1,2,3...
		int allDealersNum = driver.findElements(allDealerLocator).size();
		for (int i = 1; i <= allDealersNum; i++) {
			oneDealerLocator = By.xpath("//*[@id='allDealers']/option[" + i + "]");
			driver.findElement(oneDealerLocator).click();

		}
		oneDealerLocator = By.xpath("//*[@id='allDealers']/option[" + num + "]"); // 1,2,3...
		boolean selected = driver.findElement(oneDealerLocator).isSelected();
		driver.findElement(oneDealerLocator).click();
		selected = driver.findElement(oneDealerLocator).isSelected();
		String attachedDealerIDnDealerNamex = driver.findElement(oneDealerLocator).getText();
		return attachedDealerIDnDealerNamex;
	}

	public AccountProfile selectOneDealerFrAccountDealers(WebDriver driver, String selectedName) {
		int num = 0;
		By AccountDealersLocator = By.xpath("//*[@id='userAttachedDealers']/option");// //*[@id="userAttachedDealers"]
		By AccountDealerLocator = By.xpath("//*[@id='userAttachedDealers']/option[" + num + "]");// 1,2,3...
		num = driver.findElements(AccountDealersLocator).size();
		for (int i = 1; i <= num; i++) {
			AccountDealerLocator = By.xpath("//*[@id='userAttachedDealers']/option[" + i + "]");
			boolean enabled = driver.findElement(AccountDealerLocator).isSelected();
			if (enabled) {
				driver.findElement(AccountDealerLocator).click();
			}

		}

		for (int i = 1; i <= num; i++) {
			AccountDealerLocator = By.xpath("//*[@id='userAttachedDealers']/option[" + i + "]");
			String ActDealerName = driver.findElement(AccountDealerLocator).getText();
			if (selectedName.equalsIgnoreCase(ActDealerName)) {
				num = i;
				boolean enabled = driver.findElement(AccountDealerLocator).isSelected();
				if (!enabled) {
					driver.findElement(AccountDealerLocator).click();
				}
			}
		}
		// driver.findElement(AccountDealerLocator).click();
		return this;
	}

	public AccountProfile inputSearch(WebDriver driver, String searchForActEmail) {
		driver.findElement(SearchLocator).sendKeys(searchForActEmail);
		return this;

	}

	public AccountProfile inputDealersipID(WebDriver driver, String dlrID) {
		driver.findElement(DealershipIDLocator).sendKeys(dlrID);
		;
		return this;

	}

	public AccountProfile inputDealershipName(WebDriver driver, String dlrName) {
		driver.findElement(DealershipNameLocator).sendKeys(dlrName);
		;
		return this;
	}

	public AccountProfile inputDealersipEmail(WebDriver driver, String dlrEmail) {
		driver.findElement(DealershipEmailLocator).sendKeys(dlrEmail);
		;
		return this;
	}

	public AccountProfile inputAccountEmail(WebDriver driver, String acctEmail) {
		driver.findElement(AccountEmailLocator).sendKeys(acctEmail);
		;
		return this;
	}

	public AccountProfile inputMetadata(WebDriver driver, String metaData) {
		driver.findElement(MetadataLocator).sendKeys(metaData);
		;
		return this;
	}

	public AccountProfile inputAddress(WebDriver driver, String address) {
		driver.findElement(AddrssLocator).sendKeys(address);
		return this;
	}

	public AccountProfile inputAddressLine2(WebDriver driver, String address2) {
		driver.findElement(AddressLine2Locator).sendKeys(address2);
		return this;
	}

	public AccountProfile inputCity(WebDriver driver, String city) {
		driver.findElement(CityLocator).sendKeys(city);
		return this;
	}

	public AccountProfile inputState(WebDriver driver, int state) {
		By StateProvinceLocator = By.xpath("//*[@id=\"dealerState\"]/option[" + state + "]");// NY=33
		driver.findElement(StateProvinceLocator).click();
		;
		return this;
	}

	public AccountProfile inputCountry(WebDriver driver, int country) {
		By CountryLocator = By.xpath("//*[@id=\"dealerCountry\"]/option[" + country + "]");// USA=1
		driver.findElement(CountryLocator).click();
		;
		return this;
	}

	public AccountProfile inputZipCode(WebDriver driver, String zipCode) {
		driver.findElement(ZipPostalCodeLocator).sendKeys(zipCode);
		return this;
	}

	public AccountProfile inputUserDealerName(WebDriver driver, String firstName) {
		driver.findElement(UserDealerNameLocator).sendKeys(firstName);
		return this;
	}

	public AccountProfile inputFirstName(WebDriver driver, String firstName) {
		driver.findElement(FirstNameLocator).sendKeys(firstName);
		return this;
	}

	public AccountProfile inputLastName(WebDriver driver, String lastName) {
		driver.findElement(LastNameLocator).sendKeys(lastName);
		return this;
	}

	public AccountProfile inputTagLineMarkingMsg(WebDriver driver, String msg) {
		driver.findElement(TagLineMarkingMsgLocator).sendKeys(msg);
		return this;
	}

	public AccountProfile inputWebsite(WebDriver driver, String website) {
		driver.findElement(WebsiteLocator).sendKeys(website);
		return this;
	}

	public AccountProfile inputDealershipPhone(WebDriver driver, String num) {
		driver.findElement(DealershipPhoneNumberLocator).sendKeys(num);
		return this;
	}

	public void scrollUp(WebDriver driver, int scrollNum, String tc) {

		// Window scroll down to make the custom image visible.
		JavascriptExecutor jsx = (JavascriptExecutor) driver;
		jsx.executeScript("window.scrollBy(0," + scrollNum + ")", "");
	}

	public boolean verifyOneDealerInAllDealersField(WebDriver driver, String attachedDealerName, boolean dealerExisting,
			String tc) throws IOException {
		boolean nameExist = false;
		int num = 0;
		String oneDealerName = "";
		By oneDealerLocator = By.xpath("//*[@id='allDealers']/option[" + num + "]"); // 1,2,3...
		By allDealerLocator = By.xpath("//*[@id='allDealers']/option"); // 1,2,3...
		int allDealersNum = driver.findElements(allDealerLocator).size();
		for (int i = 1; i <= allDealersNum; i++) {
			oneDealerLocator = By.xpath("//*[@id='allDealers']/option[" + i + "]");
			oneDealerName = driver.findElement(oneDealerLocator).getText();
			if (attachedDealerName.equalsIgnoreCase(oneDealerName)) {
				if (dealerExisting) {
					rwExcel(tc, true, "Verify Atached Dealer in All Dealers field", "The dealership with name = ("
							+ attachedDealerName + " ) still in the Account Dealers filed. ");
					nameExist = true;
					break;
				} else {
					rwExcel(tc, false, "Verify Atached Dealer in All Dealers field", "The dealership with name = ("
							+ attachedDealerName + " ) still in the Account Dealers filed. ");
					nameExist = true;
				}

			} else {
				if (i == num) {
					nameExist = false;
					if (dealerExisting) {
						rwExcel(tc, true, "Verify Atached Dealer in All Dealers field", "The dealership with name = ("
								+ attachedDealerName + " ) does NOT exist in the Account Dealers filed. ");
					} else {
						rwExcel(tc, false, "Verify Atached Dealer in All Dealers field", "The dealership with name = ("
								+ attachedDealerName + " ) does NOT exist in the Account Dealers filed. ");
					}
				}
			}
		}

		return nameExist;
	}

	public boolean verifyOneDealerInAccountDealersField(WebDriver driver, String attachedDealerName,
			boolean dealerExisting, String tc) throws IOException {
		boolean nameExist = false;
		int num = 0;
		String ActDealerName = "";
		By AccountDealerLocator = By.xpath("//*[@id='userAttachedDealers']/option[" + num + "]");
		By allDealerLocator = By.xpath("//*[@id='userAttachedDealers']/option"); // 1,2,3...
		num = driver.findElements(allDealerLocator).size();
		for (int i = 1; i <= num; i++) {
			AccountDealerLocator = By.xpath("//*[@id='userAttachedDealers']/option[" + i + "]");
			ActDealerName = driver.findElement(AccountDealerLocator).getText();
			if (attachedDealerName.equalsIgnoreCase(ActDealerName)) {
				if (dealerExisting) {
					rwExcel(tc, true, "The dealership with name = (" + attachedDealerName
							+ " ) exist in the Account Dealers filed. ", "Detached from All Account dealers.");
					nameExist = true;
					break;
				} else {
					rwExcel(tc, false,
							"The dealership with name = (" + attachedDealerName
									+ " ) still exist in the Account Dealers filed when should not. ",
							"Failed to detach from All Account dealers.");
					nameExist = true;
				}
				nameExist = true;
				break;
			} else {
				if (i == num) {
					nameExist = false;

					if (dealerExisting) {
						rwExcel(tc, true, "The dealership with name = (" + attachedDealerName + ")",
								"Sucessfully detached from All Account dealers. It cannot be found in the Account Dealers filed");
					} else {
						rwExcel(tc, true, "The dealership with name = (" + attachedDealerName + ")",
								"Sucessfully detached from All Account dealers. It cannot be found in the Account Dealers filed");
					}
				}
			}
		}

		return nameExist;
	}
}
