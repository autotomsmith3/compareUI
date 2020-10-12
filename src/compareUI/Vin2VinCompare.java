package compareUI;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Vin2VinCompare extends Comlibs {
	private final WebDriver driver;

	public Vin2VinCompare(WebDriver driver) throws IOException {
		this.driver = driver;
		// String wh1=driver.getWindowHandle();
		String sPageTitle = "VIN 2 VIN Compare";
		boolean existTitle = TitleDisplay(driver, sPageTitle);
		if (existTitle) {
			rwExcel("", true, "Page Title is displayed", sPageTitle);
		} else {
			rwExcel("", false, "Page title is NOT showing properly. Exceed time limit!",
					"The page title is NOT - " + sPageTitle);
		}
		if (!sPageTitle.equals(driver.getTitle())) {
			System.out.println("Page title does not matche. Expected page title =" + sPageTitle);
		}
	}

	By vinBtn = By.xpath("/html/body/div[1]/form/div/div[1]/div[1]/h4/a");
	By vehicleInfoBtn = By.xpath("//*[@id=\"App\"]/div/div[2]/div/div/article/div/div[2]/div[1]/div/div/span/label[2]");
	By vinDropListBtn = By
			.xpath("//*[@id=\"App\"]/div/div[2]/div/div/article/div/div[2]/div[2]/div/div/form/div[1]/div[2]/div/div");

	By ConfigurableParameters = By.xpath("/html/body/div[1]/form/div/div[1]/div[1]/h4/a");
	By CompareScriptURL = By.xpath("//*[@id=\"compare-script-url\"]");
	By CompareAPIBaseURL = By.xpath("//*[@id=\"data-compare-url\"]");
	By AccessToken = By.xpath("//*[@id=\"data-access-token\"]");
	By Profile = By.xpath("//*[@id=\"data-profile\"]");
	By ProductKey = By.xpath("//*[@id=\"data-product-key\"]");
	By Locale = By.xpath("//*[@id=\"data-locale\"]");
	By CompareBtn = By.xpath("/html/body/div[1]/form/button[1]");
	By EditConfiguration = By.xpath("/html/body/div[1]/form/button[2]");
	By NewConfiguration = By.xpath("/html/body/div[1]/form/button[3]");
//	By xxxxxx = By.xpath("");	
//	By xxxxxx = By.xpath("");	
//	By xxxxxx = By.xpath("");	
//	By xxxxxx = By.xpath("");

	public Vin2VinCompare clickConfigurealeParameters(WebDriver driver, String tc) throws Exception {
		elementExist(driver, ConfigurableParameters, true, tc);
		driver.findElement(ConfigurableParameters).click();
		return this;
	}

	public Vin2VinCompare inputCompareScriptURL(WebDriver driver, String un, String tc) throws Exception {
		elementExist(driver, CompareScriptURL, true, tc);
		driver.findElement(CompareScriptURL).clear();
		driver.findElement(CompareScriptURL).sendKeys(un);
		return this;
	}

	public Vin2VinCompare inputCompareAPIBaseURL(WebDriver driver, String un, String tc) throws Exception {
		elementExist(driver, CompareAPIBaseURL, true, tc);
		driver.findElement(CompareAPIBaseURL).clear();
		driver.findElement(CompareAPIBaseURL).sendKeys(un);
		return this;
	}

	public Vin2VinCompare inputAccessToken(WebDriver driver, String un, String tc) throws Exception {
		elementExist(driver, AccessToken, true, tc);
		driver.findElement(AccessToken).clear();
		driver.findElement(AccessToken).sendKeys(un);
		return this;
	}

	public Vin2VinCompare inputProfile(WebDriver driver, String un, String tc) throws Exception {
		elementExist(driver, Profile, true, tc);
		driver.findElement(Profile).clear();
		driver.findElement(Profile).sendKeys(un);
		return this;
	}

	public Vin2VinCompare inputProductKey(WebDriver driver, String un, String tc) throws Exception {
		elementExist(driver, ProductKey, true, tc);
		driver.findElement(ProductKey).clear();
		driver.findElement(ProductKey).sendKeys(un);
		return this;
	}

	public Vin2VinCompare inputLocale(WebDriver driver, String un, String tc) throws Exception {
		elementExist(driver, Locale, true, tc);
		driver.findElement(Locale).clear();
		driver.findElement(Locale).sendKeys(un);
		return this;
	}

//	public Vin2VinCompare inputVins(WebDriver driver, String [] vins, String [] DealerPrices, String[] images, String tc)throws Exception  {
//		
//		
//		
//		elementExist(driver, Locale, true, tc);
//		driver.findElement(Locale).clear();
//		driver.findElement(Locale).sendKeys(un);
//		return this;
//	}
	public Vin2VinCompare inputVins(WebDriver driver, String vins[], String tc) throws Exception {
		int n = vins.length;

		for (int i = 1; i <= n; i++) {

			By VIN = By.xpath("//*[@id='vin-" + i + "']");

			elementExist(driver, VIN, true, tc);
			driver.findElement(VIN).clear();
			driver.findElement(VIN).sendKeys(vins[i - 1]);

		}
		return this;
	}

	public Vin2VinCompare inputDealerPrices(WebDriver driver, String vins[], String DealerPrices[], String Images[],
			String tc) throws Exception {
		int n = vins.length;

		for (int i = 1; i <= n; i++) {

			By PlusBtn = By.xpath("//*[@id='custom-vin-button-" + i + "']");
			By DealerPrice = By.xpath("//*[@id='vin-dealer-price-" + i + "']");
			By Image = By.xpath("//*[@id='vin-image-" + i + "']");
//			click +
			elementExist(driver, PlusBtn, true, tc);
			driver.findElement(PlusBtn).click();

//			enter dealerprices:
			elementExist(driver, DealerPrice, true, tc);
			driver.findElement(DealerPrice).clear();
			driver.findElement(DealerPrice).sendKeys(DealerPrices[i - 1]);

//			enter image urls:
			elementExist(driver, Image, true, tc);
			driver.findElement(Image).clear();
			driver.findElement(Image).sendKeys(Images[i - 1]);

		}
		return this;
	}

	public Vin2VinCompare clickCompareBtn(WebDriver driver, String tc) throws Exception {
		elementExist(driver, CompareBtn, true, tc);
		driver.findElement(CompareBtn).click();
		return this;
	}

	public Vin2VinCompare clickEditConfigurationBtn(WebDriver driver, String tc) throws Exception {
		elementExist(driver, EditConfiguration, true, tc);
		driver.findElement(EditConfiguration).click();
		return this;

	}

	public Vin2VinCompare clickNewConfigurationBtn(WebDriver driver, String tc) throws Exception {
		elementExist(driver, NewConfiguration, true, tc);
		driver.findElement(NewConfiguration).click();
		return this;
	}

	public void verifyDealerPriceWhichIsNot0(WebDriver driver, String dealerPrice, String tc) throws Exception {
		By dealerPrice1 = By.xpath("/html/body/div[2]/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div/span");
		By dealerPrice2 = By.xpath("/html/body/div[2]/div/div[2]/div[2]/div[2]/div/div[4]/div[3]/span");
		By dealerPrice3 = By.xpath("/html/body/div[2]/div/div[2]/div[2]/div[2]/div/div[4]/div[4]/div/span");
		
		int dp1=Integer.parseInt(dealerPrice);
		if (dp1==0) {
			rwExcel(tc, false, "Verify Deapler Price for VIN1", "Dealer Price is 0 which cannot be verify in this test case. Change the deapler price in properties file to make this test valid!");
			return;
		}
		elementExist(driver, dealerPrice1, true, tc);
		String dp1String =driver.findElement(dealerPrice1).getText();
		dp1String=dp1String.replace("$","");
		dp1String=dp1String.replace(",","");
		int dp1Int=Integer.parseInt(dp1String);
		
		if (dp1==dp1Int) {
			rwExcel(tc, true, "Verify Deapler Price for VIN1", "Dealer Price 1="+dp1String);
			
		}else {
			rwExcel(tc, false, "Verify Deapler Price for VIN1", "Dealer Price from page shows:"+dp1Int+". Expected Dealer Price 1="+dealerPrice1);
		}
	}
	public void verifyDealerPriceWhichIs0(WebDriver driver, String dealerPrice, String tc) throws Exception {
		By dealerPrice2 = By.xpath("/html/body/div[2]/div/div[2]/div[2]/div[2]/div/div[4]/div[3]/span");
		By dealerPrice3 = By.xpath("/html/body/div[2]/div/div[2]/div[2]/div[2]/div/div[4]/div[4]/div/span");
		String dp2="Contact Dealer";
		int dp1=Integer.parseInt(dealerPrice);//0
		if (!(dp1==0)) {
			rwExcel(tc, false, "Verify Deapler Price for VIN1 which is 0", "Dealer Price is not 0 which cannot be verify in this test case. Change the deapler price in properties file to make this test valid!");
			return;
		}else {
			
		}
		elementExist(driver, dealerPrice2, true, tc);
		String dp1String =driver.findElement(dealerPrice2).getText();
		if (dp1String.equalsIgnoreCase(dp2)) {
			rwExcel(tc, true, "Verify Deapler Price for VIN2", "Dealer Price 2 = "+dealerPrice+". Page shows: "+dp2);
		}else {
			rwExcel(tc, false, "Verify Deapler Price for VIN2 which is 0", "Dealer Price from page shows:"+"\""+dp1String+"\""+". Expected Dealer Price 2 = "+"\""+dp2+"\"");
		}
		
//		int dp1frPage=
//		dp1String =driver.findElement(dealerPrice1).getCssValue("span");
//		dp1String =driver.findElement(dealerPrice1).getTagName();
		
	}

	public void verifyVin1Image(WebDriver driver, String tc) throws Exception {
		
		By Vin1imageLocator=By.xpath("/html/body/div[2]/div/div[1]/div[2]/div[2]/div[2]/div[1]/span/img");
		VerifyImageLoaded(driver, Vin1imageLocator, tc);
	}
	public void verifyVin2Image(WebDriver driver, String tc) throws Exception {
		
		By Vin1imageLocator=By.xpath("/html/body/div[2]/div/div[1]/div[2]/div[2]/div[2]/div[2]/span/img");
		VerifyImageLoaded(driver, Vin1imageLocator, tc);
	}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
