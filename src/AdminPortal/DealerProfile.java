package AdminPortal;

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


	By DealershipIDLocator = By.xpath("//*[@id=\"dealerCode\"]");
	By DealershipNameLocator = By.xpath("//*[@id=\"dealerName\"]");
	By DealershipEmailLocator = By.xpath("//*[@id=\"dealerEmail\"]");
	By AccountEmailLocator = By.xpath("//*[@id=\"accEmail\"]");
	By MetadataLocator = By.xpath("//*[@id=\"metadata\"]");
	By BackToDealerListLocator = By.xpath("//*[@id=\"dealerListBtn\"]/span");
	By ProductVINpxLocator=By.xpath("//*[@id=\"vinpx\"]");
	By ProductSTOCKpxLocator=By.xpath("//*[@id=\"stockpx\"]");
	By ProductLOTpxLocator=By.xpath("//*[@id=\"lotpx\"]");
	
	
	
	//*[@id="dealerListBtn"]/span
	
	public String getDealershipID(WebDriver driver) throws IOException {
		String dealershipip=driver.findElement(DealershipIDLocator).getAttribute("value");
		return dealershipip;
	}

	public String getDealershipName(WebDriver driver) throws IOException {
		String dealershipname=driver.findElement(DealershipNameLocator).getAttribute("value");
		return dealershipname;
	}
	public String getDealershipEmail(WebDriver driver) throws IOException {
		String dealerdshipemail=driver.findElement(DealershipEmailLocator).getAttribute("value");
		return dealerdshipemail;
	}
	public String getAccountEmail(WebDriver driver) throws IOException {
		String accountemail=driver.findElement(AccountEmailLocator).getAttribute("value");
		return accountemail;
	}

	public String getMetadata(WebDriver driver) throws IOException {
		String metadata=driver.findElement(MetadataLocator).getAttribute("value");
		return metadata;
	}
	public String getVINpxProduct(WebDriver driver, String tc) throws IOException {
		boolean elementExist = elementExist(driver, ProductVINpxLocator, true, tc);
		String getVINpx="";
		if (elementExist) {
			getVINpx=driver.findElement(ProductVINpxLocator).getAttribute("checked");
		}else {
			getVINpx="";
		}
		return getVINpx;
	}
	public String getSTOCKpxProduct(WebDriver driver, String tc) throws IOException {
		boolean elementExist = elementExist(driver, ProductSTOCKpxLocator, true, tc);
		String getSTOCKpx="";
		if (elementExist) {
			getSTOCKpx=driver.findElement(ProductSTOCKpxLocator).getAttribute("checked");
		}else {
			getSTOCKpx="";
		}
		return getSTOCKpx;
	}
	public String getLOTpxProduct(WebDriver driver, String tc) throws IOException {
		boolean elementExist = elementExist(driver, ProductLOTpxLocator, true, tc);
		String getLOTpx="";
		if (elementExist) {
			getLOTpx=driver.findElement(ProductLOTpxLocator).getAttribute("checked");
		}else {
			getLOTpx="";
		}
		return getLOTpx;
	}
	public DealerList clickBackToDealerListBtn(WebDriver driver, String windowHandle, String tc) throws IOException {
		boolean elementExist = elementExist(driver, BackToDealerListLocator, true, tc);
		if (elementExist) {
			driver.findElement(BackToDealerListLocator).click();
		}else {
			System.out.println("BackToDealerList button does not exist in the page!" );
		}
		driver.switchTo().window(windowHandle); 
		
		return new DealerList(driver);
	}
	public String getDlrGuid(WebDriver driver) throws IOException {
		String dlrGuid=driver.getCurrentUrl();
		
		return dlrGuid;
	}
	public String trimURL(String urlString) throws IOException {
//		urlString="//http://lnoc-q13v-xwa1.autodata.org/AdminPortal/dealersetting?dlrGuid=C9B47888-E227-4C53-8162-352CC650FA90";
		int beginIdx = urlString.indexOf("dlrGuid=")+8;
		
		String dlrGuid=urlString.substring(beginIdx);
		
		return dlrGuid;
	}
}
