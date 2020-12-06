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
	By vinField = By.xpath("//*[@id=\"root\"]/div/div/div[3]/div[2]/div[1]/form/div/div[1]/input");
	By customImageField = By.xpath("//*[@id=\"root\"]/div/div/div[3]/div[2]/div[1]/form/div/div[2]/input[1]");
	By customDealerPriceField = By.xpath("//*[@id=\"root\"]/div/div/div[3]/div[2]/div[1]/form/div/div[2]/input[2]");
	By xxxxxx = By.xpath("");
	By AddBtn = By.xpath("//*[@id=\"root\"]/div/div/div[3]/div[2]/div[1]/form/div/div[3]/input");
	By StartCompare =		 By.xpath("//*[@id=\"root\"]/div/div/div[3]/div[2]/div[3]/div/div[2]/button");
	By StartCompareVehicle = By.xpath("/html/body/div[1]/div/div/div[3]/div[2]/div[2]/div/div/div[2]/button");
	//						
	By NewCompare = By.xpath("/html/body/div[3]/div/div/button");
	By vehicle01 = By.xpath("//*[@id=\"root\"]/div/div/div[3]/div[2]/div[1]/div[1]/div[1]/img");	
	By vehicle02 = By.xpath("//*[@id=\"root\"]/div/div/div[3]/div[2]/div[1]/div[2]/div[1]/img");	
	By vehicle03 = By.xpath("");	
	By vehicle04 = By.xpath("//*[@id=\"root\"]/div/div/div[3]/div[2]/div[1]/div[4]/div[1]/img");	
	By vehicle05 = By.xpath("");	
	By vehicle06 = By.xpath("");	
//	By xxxxxx = By.xpath("");	
//	By xxxxxx = By.xpath("");	
//	By xxxxxx = By.xpath("");	
//	By xxxxxx = By.xpath("");	
//	By xxxxxx = By.xpath("");	
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

	public Vin_Compare inputCustomImage(WebDriver driver, String imageURL, String tc) throws Exception {
		elementExist(driver, customImageField, true, tc);
		driver.findElement(customImageField).clear();
		driver.findElement(customImageField).sendKeys(imageURL);
		return this;
	}

	public Vin_Compare inputCustomDealerPrice(WebDriver driver, String dealerPrice, String tc) throws Exception {
		elementExist(driver, customDealerPriceField, true, tc);
		driver.findElement(customDealerPriceField).clear();
		driver.findElement(customDealerPriceField).sendKeys(dealerPrice);
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
	public Vin_Compare clickStartCompareWithVehicle(WebDriver driver, String tc) throws Exception {
		elementExist(driver, StartCompareVehicle, true, tc);
		driver.findElement(StartCompareVehicle).click();
		return this;
	}

	public Vin_Compare clickNewCompare(WebDriver driver, String tc) throws Exception {
		elementExist(driver, NewCompare, true, tc);
		driver.findElement(NewCompare).click();
		return this;
	}
	

	
	public Vin_Compare clickVehicle(WebDriver driver, int num, String tc) throws Exception {
		By vehicle = By.xpath("//*[@id=\"root\"]/div/div/div[3]/div[2]/div[1]/div["+num+"]/div[1]/img");
		elementExist(driver, vehicle, true, tc);
		driver.findElement(vehicle).click();
		return this;
	}
	public Vin_Compare clickVehicleShowMore(WebDriver driver, int num, String tc) throws Exception {
		//*[@id="root"]/div/div/div[3]/div[2]/div[1]/div[2]/div[2]/button   - 2
		//*[@id="root"]/div/div/div[3]/div[2]/div[1]/div[5]/div[2]/button   - 5
		//*[@id="root"]/div/div/div[3]/div[2]/div[1]/div[7]/div[2]/button  - 7
		By showMore = By.xpath("//*[@id=\"root\"]/div/div/div[3]/div[2]/div[1]/div["+num+"]/div[2]/button");
		elementExist(driver, showMore, true, tc);
		driver.findElement(showMore).click();
		return this;
	}

	public int countVehicleNumber(WebDriver driver, String tc) throws Exception {
		By vehicles = By.xpath("//*[@id=\"root\"]/div/div/div[3]/div[2]/div[1]/div");
		elementExist(driver, vehicles, true, tc);
		int vehicleNum=driver.findElements(vehicles).size();
		return vehicleNum;
	}

}
