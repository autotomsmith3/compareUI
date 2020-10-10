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
	By CompareScriptURL=By.xpath("//*[@id=\"compare-script-url\"]");
	By CompareAPIBaseURL = By.xpath("//*[@id=\"data-compare-url\"]");	
	By AccessToken = By.xpath("//*[@id=\"data-access-token\"]");	
	By Profile = By.xpath("//*[@id=\"data-profile\"]");	
	By ProductKey = By.xpath("//*[@id=\"data-product-key\"]");	
	By Locale = By.xpath("//*[@id=\"data-locale\"]");	
	By VIN1 = By.xpath("//*[@id='vin-1']");	
	By PlusBtn1 = By.xpath("//*[@id='custom-vin-button-1']");	
	By DealerPrice1 = By.xpath("//*[@id='vin-dealer-price-1']");	
	By Image1 = By.xpath("//*[@id='vin-image-1']");	
	
	By VIN2 = By.xpath("//*[@id='vin-2']");		
	By PlusBtn2 = By.xpath("//*[@id='custom-vin-button-2']");	
	By DealerPrice2 = By.xpath("//*[@id='vin-dealer-price-2']");	
	By Image2 = By.xpath("//*[@id='vin-image-2']");	
	
	By VIN3 = By.xpath("//*[@id='vin-3']");	
	
	
	By VIN4 = By.xpath("//*[@id='vin-4']");	
	
	
	By VIN5 = By.xpath("//*[@id='vin-5']");	
	
	
	By VIN6 = By.xpath("//*[@id='vin-5']");		
	
	
//	By xxxxxx = By.xpath("");	
//	By xxxxxx = By.xpath("");	
//	By xxxxxx = By.xpath("");
//	By xxxxxx = By.xpath("");	
//	By xxxxxx = By.xpath("");	
//	By xxxxxx = By.xpath("");	
//	By xxxxxx = By.xpath("");
	
	public Vin2VinCompare clickConfigurealeParameters(WebDriver driver, String tc) throws Exception {
		elementExist(driver, ConfigurableParameters, true, tc);
		driver.findElement(ConfigurableParameters).click();
		return this;
	}
	public Vin2VinCompare inputCompareScriptURL(WebDriver driver, String un,String tc)throws Exception  {
		elementExist(driver, CompareScriptURL, true, tc);
		driver.findElement(CompareScriptURL).clear();
		driver.findElement(CompareScriptURL).sendKeys(un);
		return this;
	}
	
	public Vin2VinCompare inputCompareAPIBaseURL(WebDriver driver, String un,String tc)throws Exception  {
		elementExist(driver, CompareAPIBaseURL, true, tc);
		driver.findElement(CompareAPIBaseURL).clear();
		driver.findElement(CompareAPIBaseURL).sendKeys(un);
		return this;
	}
	
	public Vin2VinCompare inputAccessToken(WebDriver driver, String un,String tc)throws Exception  {
		elementExist(driver, AccessToken, true, tc);
		driver.findElement(AccessToken).clear();
		driver.findElement(AccessToken).sendKeys(un);
		return this;
	}
	
	public Vin2VinCompare inputProfile(WebDriver driver, String un,String tc)throws Exception  {
		elementExist(driver, Profile, true, tc);
		driver.findElement(Profile).clear();
		driver.findElement(Profile).sendKeys(un);
		return this;
	}
	
	
	public Vin2VinCompare inputProductKey(WebDriver driver, String un,String tc)throws Exception  {
		elementExist(driver, ProductKey, true, tc);
		driver.findElement(ProductKey).clear();
		driver.findElement(ProductKey).sendKeys(un);
		return this;
	}
	
	public Vin2VinCompare inputLocale(WebDriver driver, String un,String tc)throws Exception  {
		elementExist(driver, Locale, true, tc);
		driver.findElement(Locale).clear();
		driver.findElement(Locale).sendKeys(un);
		return this;
	}
	
	
	
	
	
	

}
