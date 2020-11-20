package vinCompareDemoUI;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Vin_Compare extends Comlibs {
	private final WebDriver driver;

	public Vin_Compare(WebDriver driver) throws IOException {
		this.driver = driver;
		// String wh1=driver.getWindowHandle();
		String sPageTitle = "Vin Compare";
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

//	By vinBtn = By.xpath("/html/body/div[1]/form/div/div[1]/div[1]/h4/a");
//	By vehicleInfoBtn = By.xpath("//*[@id=\"App\"]/div/div[2]/div/div/article/div/div[2]/div[1]/div/div/span/label[2]");
//	By vinDropListBtn = By
//			.xpath("//*[@id=\"App\"]/div/div[2]/div/div/article/div/div[2]/div[2]/div/div/form/div[1]/div[2]/div/div");
//
//	By ConfigurableParameters = By.xpath("/html/body/div[1]/form/div/div[1]/div[1]/h4/a");
//	By CompareScriptURL = By.xpath("//*[@id=\"compare-script-url\"]");
//	By CompareAPIBaseURL = By.xpath("//*[@id=\"data-compare-url\"]");
//	By AccessToken = By.xpath("//*[@id=\"data-access-token\"]");
//	By Profile = By.xpath("//*[@id=\"data-profile\"]");
//	By ProductKey = By.xpath("//*[@id=\"data-product-key\"]");
//	By Locale = By.xpath("//*[@id=\"data-locale\"]");
//	By CompareBtn = By.xpath("/html/body/div[1]/form/button[1]");
//	By EditConfiguration = By.xpath("/html/body/div[1]/form/button[2]");
//	By NewConfiguration = By.xpath("/html/body/div[1]/form/button[3]");
	By GMLocator = By.xpath("//*[@id=\"root\"]/div/div/div/div[2]/div[2]/div[1]/div/img");
	By ChevroletLocator = By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div[2]/div[1]/div/img");
	By BuickLocator = By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div[2]/div[2]/div/img");
	By GMCLocator = By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div[2]/div[3]/div/img");
	By CadillacLocator = By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div[2]/div[4]/div/img");
	By ManuallyEnterVins = By.xpath("//*[@id=\"root\"]/div/div/div[3]/div[1]/span");
	By vinField = By.xpath("//*[@id=\"root\"]/div/div/div[3]/div[2]/div[1]/form/input[1]");
	By AddBtn = By.xpath("//*[@id=\"root\"]/div/div/div[3]/div[2]/div[1]/form/input[2]");
	By StartCompare = By.xpath("//*[@id=\"root\"]/div/div/div[3]/div[2]/div[3]/div/div[2]/button");
	By NewCompare = By.xpath("/html/body/div[3]/div/div/button");	
//	By xxxxxx = By.xpath("");	

	public Vin_Compare clickGM(WebDriver driver, String tc) throws Exception {
		elementExist(driver, GMLocator, true, tc);
		driver.findElement(GMLocator).click();
		return this;
	}

	public Vin_Compare clickChevrolet(WebDriver driver, String tc) throws Exception {
		elementExist(driver, ChevroletLocator, true, tc);
		driver.findElement(ChevroletLocator).click();
		return this;
	}

	public Vin_Compare clickBuick(WebDriver driver, String tc) throws Exception {
		elementExist(driver, BuickLocator, true, tc);
		driver.findElement(BuickLocator).click();
		return this;
	}

	public Vin_Compare clickGMC(WebDriver driver, String tc) throws Exception {
		elementExist(driver, GMCLocator, true, tc);
		driver.findElement(GMCLocator).click();
		return this;
	}

	public Vin_Compare clickCadillac(WebDriver driver, String tc) throws Exception {
		elementExist(driver, CadillacLocator, true, tc);
		driver.findElement(CadillacLocator).click();
		return this;
	}

	public Vin_Compare clickManuallyEnterVinsLink(WebDriver driver, String tc) throws Exception {
		elementExist(driver, ManuallyEnterVins, true, tc);
		driver.findElement(ManuallyEnterVins).click();
		return this;
	}

	public Vin_Compare inputVin(WebDriver driver, String vin, String tc) throws Exception {
		elementExist(driver, vinField, true, tc);
		driver.findElement(vinField).clear();
		driver.findElement(vinField).sendKeys(vin);
		return this;
	}

	public Vin_Compare clickAdd(WebDriver driver, String tc) throws Exception {
		elementExist(driver, AddBtn, true, tc);
		driver.findElement(AddBtn).click();
		return this;
	}

	public Vin_Compare clickStartCompare(WebDriver driver, String tc) throws Exception {
		elementExist(driver, StartCompare, true, tc);
		driver.findElement(StartCompare).click();
		return this;
	}
	public Vin_Compare clickNewCompare(WebDriver driver, String tc) throws Exception {
		elementExist(driver, NewCompare, true, tc);
		driver.findElement(NewCompare).click();
		return this;
	}
//	
//	public Vin_Compare inputVins(WebDriver driver, String vins[], String tc) throws Exception {
//		int n = vins.length;
//
//		for (int i = 1; i <= n; i++) {
//
//			By VIN = By.xpath("//*[@id='vin-" + i + "']");
//
//			elementExist(driver, VIN, true, tc);
//			driver.findElement(VIN).clear();
//			driver.findElement(VIN).sendKeys(vins[i - 1]);
//
//		}
//		return this;
//	}
//
//	public Vin_Compare inputDealerPrices(WebDriver driver, String vins[], String DealerPrices[], String Images[],
//			String tc) throws Exception {
//		int n = vins.length;
//
//		for (int i = 1; i <= n; i++) {
//
//			By PlusBtn = By.xpath("//*[@id='custom-vin-button-" + i + "']");
//			By DealerPrice = By.xpath("//*[@id='vin-dealer-price-" + i + "']");
//			By Image = By.xpath("//*[@id='vin-image-" + i + "']");
////			click +
//			elementExist(driver, PlusBtn, true, tc);
//			driver.findElement(PlusBtn).click();
//
////			enter dealerprices:
//			elementExist(driver, DealerPrice, true, tc);
//			driver.findElement(DealerPrice).clear();
//			driver.findElement(DealerPrice).sendKeys(DealerPrices[i - 1]);
//
////			enter image urls:
//			elementExist(driver, Image, true, tc);
//			driver.findElement(Image).clear();
//			driver.findElement(Image).sendKeys(Images[i - 1]);
//
//		}
//		return this;
//	}
//
//	public Vin_Compare clickCompareBtn(WebDriver driver, String tc) throws Exception {
//		elementExist(driver, CompareBtn, true, tc);
//		driver.findElement(CompareBtn).click();
//		return this;
//	}
//
//	public Vin_Compare clickEditConfigurationBtn(WebDriver driver, String tc) throws Exception {
//		elementExist(driver, EditConfiguration, true, tc);
//		driver.findElement(EditConfiguration).click();
//		return this;
//
//	}
//
//	public Vin_Compare clickNewConfigurationBtn(WebDriver driver, String tc) throws Exception {
//		elementExist(driver, NewConfiguration, true, tc);
//		driver.findElement(NewConfiguration).click();
//		return this;
//	}
//
//	public void verifyDealerPriceWhichIsNot0(WebDriver driver, String dealerPrice, String tc) throws Exception {
//		By dealerPrice1 = By.xpath("/html/body/div[2]/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div/span");
//		By dealerPrice2 = By.xpath("/html/body/div[2]/div/div[2]/div[2]/div[2]/div/div[4]/div[3]/span");
//		By dealerPrice3 = By.xpath("/html/body/div[2]/div/div[2]/div[2]/div[2]/div/div[4]/div[4]/div/span");
//		
//		int dp1=Integer.parseInt(dealerPrice);
//		if (dp1==0) {
//			rwExcel(tc, false, "Verify Deapler Price for VIN1", "Dealer Price is 0 which cannot be verify in this test case. Change the deapler price in properties file to make this test valid!");
//			return;
//		}
//		elementExist(driver, dealerPrice1, true, tc);
//		String dp1String =driver.findElement(dealerPrice1).getText();
//		dp1String=dp1String.replace("$","");
//		dp1String=dp1String.replace(",","");
//		int dp1Int=Integer.parseInt(dp1String);
//		
//		if (dp1==dp1Int) {
//			rwExcel(tc, true, "Verify Deapler Price for VIN1", "Dealer Price 1="+dp1String);
//			
//		}else {
//			rwExcel(tc, false, "Verify Deapler Price for VIN1", "Dealer Price from page shows:"+dp1Int+". Expected Dealer Price 1="+dealerPrice1);
//		}
//	}
//	public void verifyDealerPriceWhichIs0(WebDriver driver, String dealerPrice, String tc) throws Exception {
//		By dealerPrice2 = By.xpath("/html/body/div[2]/div/div[2]/div[2]/div[2]/div/div[4]/div[3]/span");
//		By dealerPrice3 = By.xpath("/html/body/div[2]/div/div[2]/div[2]/div[2]/div/div[4]/div[4]/div/span");
//		String dp2="Contact Dealer";
//		int dp1=Integer.parseInt(dealerPrice);//0
//		if (!(dp1==0)) {
//			rwExcel(tc, false, "Verify Deapler Price for VIN1 which is 0", "Dealer Price is not 0 which cannot be verify in this test case. Change the deapler price in properties file to make this test valid!");
//			return;
//		}else {
//			
//		}
//		elementExist(driver, dealerPrice2, true, tc);
//		String dp1String =driver.findElement(dealerPrice2).getText();
//		if (dp1String.equalsIgnoreCase(dp2)) {
//			rwExcel(tc, true, "Verify Deapler Price for VIN2", "Dealer Price 2 = "+dealerPrice+". Page shows: "+dp2);
//		}else {
//			rwExcel(tc, false, "Verify Deapler Price for VIN2 which is 0", "Dealer Price from page shows:"+"\""+dp1String+"\""+". Expected Dealer Price 2 = "+"\""+dp2+"\"");
//		}
//		
////		int dp1frPage=
////		dp1String =driver.findElement(dealerPrice1).getCssValue("span");
////		dp1String =driver.findElement(dealerPrice1).getTagName();
//		
//	}
//
//	public void verifyVin1Image(WebDriver driver, String tc) throws Exception {
//		
//		By Vin1imageLocator=By.xpath("/html/body/div[2]/div/div[1]/div[2]/div[2]/div[2]/div[1]/span/img");
//		VerifyImageLoaded(driver, Vin1imageLocator, tc);
//	}
//	public void verifyVin2Image(WebDriver driver, String tc) throws Exception {
//		
//		By Vin1imageLocator=By.xpath("/html/body/div[2]/div/div[1]/div[2]/div[2]/div[2]/div[2]/span/img");
//		VerifyImageLoaded(driver, Vin1imageLocator, tc);
//	}	
//	
//	

}
