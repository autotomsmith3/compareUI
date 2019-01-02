package AdminPortal;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

	public void clickBackToDealerListBtn(WebDriver driver, String windowHandle, String tc) throws IOException {
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

//		return new DealerList(driver);
	}

	public DealerProfile clickSaveBtn(WebDriver driver, String tc) {
		driver.findElement(SaveBtn).click();
		return this;
	}

	public boolean checkMessageDisplayedHead(WebDriver driver, String message,String tc) throws Exception {
		String msg = driver.findElement(MessageDisplayedOnHead).getText();// .getAttribute("messageBox");
		boolean messageExist = false;
		if (msg.equalsIgnoreCase(message)) {
			messageExist = true;
		}else {
			rwExcel(tc, false, "The message is displayed on head - Does NOT match","Expected msg is:\""+message+"\". The real message is displayed on head is:\""+msg+"\"");
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

	public DealerProfile selectOEM(WebDriver driver, int oemNum) {
		By OEMSelect = By.xpath("//*[@id=\"dealerOem\"]/option[" + oemNum + "]");// 13=GM
		driver.findElement(OEMSelect).click();
		return this;
	}

	public DealerProfile selectOEMBrands(WebDriver driver, int oemBrandNum) {
		By OEMBrand = By.xpath("//*[@id=\"dealerInfoBody\"]/div/div/div[1]/div[2]/div[1]/div/div/div/div[13]/label["
				+ oemBrandNum + "]/input");// 1=Buick, 2=Cadillac, 3=Chevrolet, 4=GMC
		driver.findElement(OEMBrand).click();
		return this;
	}

	public DealerProfile inputDealersipID(WebDriver driver, String dlrID) {
		driver.findElement(DealershipIDLocator).sendKeys(dlrID);
		;
		return this;

	}

	public DealerProfile inputDealershipName(WebDriver driver, String dlrName) {
		driver.findElement(DealershipNameLocator).sendKeys(dlrName);
		;
		return this;
	}

	public DealerProfile inputDealersipEmail(WebDriver driver, String dlrEmail) {
		driver.findElement(DealershipEmailLocator).sendKeys(dlrEmail);
		;
		return this;
	}

	public DealerProfile inputAccountEmail(WebDriver driver, String acctEmail) {
		driver.findElement(AccountEmailLocator).sendKeys(acctEmail);
		;
		return this;
	}

	public DealerProfile inputMetadata(WebDriver driver, String metaData) {
		driver.findElement(MetadataLocator).sendKeys(metaData);
		;
		return this;
	}

	public DealerProfile selectVINpxProd(WebDriver driver) {
		driver.findElement(ProductVINpxLocator).click();
		;
		return this;
	}

	public DealerProfile selectSTOCKpxProd(WebDriver driver) {
		driver.findElement(ProductSTOCKpxLocator).click();
		;
		return this;
	}

	public DealerProfile selectLOTpxProd(WebDriver driver) {
		driver.findElement(ProductLOTpxLocator).click();
		;
		return this;
	}

	public DealerProfile inputAddress(WebDriver driver, String address) {
		driver.findElement(AddrssLocator).sendKeys(address);
		return this;
	}

	public DealerProfile inputAddressLine2(WebDriver driver, String address2) {
		driver.findElement(AddressLine2Locator).sendKeys(address2);
		return this;
	}

	public DealerProfile inputCity(WebDriver driver, String city) {
		driver.findElement(CityLocator).sendKeys(city);
		return this;
	}

	public DealerProfile inputState(WebDriver driver, int state) {
		By StateProvinceLocator = By.xpath("//*[@id=\"dealerState\"]/option[" + state + "]");// NY=33
		driver.findElement(StateProvinceLocator).click();
		;
		return this;
	}

	public DealerProfile inputCountry(WebDriver driver, int country) {
		By CountryLocator = By.xpath("//*[@id=\"dealerCountry\"]/option[" + country + "]");// USA=1
		driver.findElement(CountryLocator).click();
		;
		return this;
	}

	public DealerProfile inputZipCode(WebDriver driver, String zipCode) {
		driver.findElement(ZipPostalCodeLocator).sendKeys(zipCode);
		return this;
	}

	public DealerProfile inputFirstName(WebDriver driver, String firstName) {
		driver.findElement(FirstNameLocator).sendKeys(firstName);
		return this;
	}

	public DealerProfile inputLastName(WebDriver driver, String lastName) {
		driver.findElement(LastNameLocator).sendKeys(lastName);
		return this;
	}

	public DealerProfile inputTagLineMarkingMsg(WebDriver driver, String msg) {
		driver.findElement(TagLineMarkingMsgLocator).sendKeys(msg);
		return this;
	}

	public DealerProfile inputWebsite(WebDriver driver, String website) {
		driver.findElement(WebsiteLocator).sendKeys(website);
		return this;
	}

	public DealerProfile inputDealershipPhone(WebDriver driver, String num) {
		driver.findElement(DealershipPhoneNumberLocator).sendKeys(num);
		return this;
	}

	public DealerProfile selectTemplateSetting(WebDriver driver, int num) {
		By TemplateSettingsLocator = By.xpath("//*[@id=\"selectedBackground\"]/option[" + num + "]");// DEFAULT=1; replace=2;overlay=3;
		driver.findElement(TemplateSettingsLocator).click();
		return this;
	}

	public DealerProfile clickUploadBtn(WebDriver driver, int num) {
		driver.findElement(UploadBtn).click();
		return this;
	}

	public DealerProfile selectBackGroundSet(WebDriver driver, int num) {
		By backGroundSetLocator = By.xpath("//*[@id=\"bg-" + num + "\"]/div[1]/img");// Generic Dealership=7; White Gradient=0
		// By backGroundSetLocator=By.xpath("//*[@id=\"bg-7\"]/div[1]/img");
		// By backGroundLocator=By.xpath("//*[@id=\"bg-0\"]/div[1]/img");
		scrollUp(driver, 1000, "ddd"); // QA -2000 Prod -3000
		driver.findElement(backGroundSetLocator).click();
		return this;
	}

	public void scrollUp(WebDriver driver, int scrollNum, String tc) {

		// Window scroll down to make the custom image visible.
		JavascriptExecutor jsx = (JavascriptExecutor) driver;
		jsx.executeScript("window.scrollBy(0," + scrollNum + ")", "");
	}
	public void uploadDealershipLogo(WebDriver driver, String imageLogPath,String msg, String tc) throws IOException {
		By alertMsg=By.xpath("//*[@id=\"header\"]/div/div[2]/span");
		try {
			driver.findElement(By.id("uploadLogo")).sendKeys(imageLogPath);
			//scrollup
			scrollUp(driver, -2000, "ddd"); // QA -2000 Prod -3000
			Wait(2);
			String alertMsgExist=driver.findElement(alertMsg).getText();
			if (alertMsgExist.equalsIgnoreCase(msg)) {
				rwExcel(tc, true, "Upload a dealership Logo", "Verify alert message showing in Dealer Profile page");
			}else {
				rwExcel(tc, false, "Upload a dealership Logo", "Verify alert message showing in Dealer Profile page. Now the message is:\""+alertMsgExist+"\". ");
			}
			Wait(4);
		}catch(Exception e) {
			rwExcel("Upload Logo", false, "Upload a dealership Logo", "in Dealer Profile page");
			Wait(4);
		}

	}
	public void uploadDealershipLogo(WebDriver driver, String imageLogPath, String tc) throws IOException {
//		driver.findElement(By.id("btnUploadPics")).sendKeys(vehicleImage);
//		driver.findElement(By.xpath("//*[@id='uploadLogo']")).sendKeys(imageLogPath);
		Wait(2);
		try {
			driver.findElement(By.id("uploadLogo")).sendKeys(imageLogPath);
			rwExcel(tc, true, "Upload a dealership Logo", "in Dealer Profile page");
			Wait(4);
		}catch(Exception e) {
			rwExcel("Upload Logo", false, "Upload a dealership Logo", "in Dealer Profile page");
			Wait(4);
		}

	}
}
