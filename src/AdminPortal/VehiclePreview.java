package AdminPortal;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class VehiclePreview extends Comlibs {
	private final WebDriver driver;

	public VehiclePreview(WebDriver driver) throws IOException {
		this.driver = driver;
		// String wh1=driver.getWindowHandle();
		String sPageTitle = "Vehicle Preview";
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

	By dealerShipName = By.cssSelector("span");
	By changeAngle = By.xpath("//*[@id=\"anglesToggle\"]");
	
	By acodeField = By.xpath("//*[@id=\"filterAcode\"]/input");
	By acodeSubmitBtn = By.xpath("//*[@id=\"acodeBtn\"]");
	By yearDropDown = By.xpath("//*[@id=\"yearsDropdown\"]");

	
	//*[@id="yearsDropdown"]
	By makeDropDown = By.xpath("//*[@id=\"makesDropdown\"]");
	By makeAll = By.xpath("//*[@id=\"makesToggle\"]");
	By makeOne = By.xpath("/html/body/div/div/div[2]/div/div/div/div[1]/div[2]/div[2]/ul/li[3]");// 3...
	
	
	By modelDropDown = By.xpath("//*[@id=\"modelsDropdown\"]");

	By trimDropDown = By.xpath("//*[@id=\"trimsDropdown\"]");
	By ymmSubmitBtn = By.xpath("//*[@id=\"ymmtBtn\"]");
	
//	By year = By.xpath("");
	// By xxxx = By.xpath("");

	public VehiclePreview inputAcode(WebDriver driver, String acode, String tc) throws IOException {
		elementExist(driver, acodeField, true, tc);
		driver.findElement(acodeField).clear();
		driver.findElement(acodeField).sendKeys(acode);
		return this;
	}
	
	public VehiclePreview clickAcodeSubmitBtn(WebDriver driver, String tc) throws IOException {
		elementExist(driver, acodeSubmitBtn, true, tc);
		driver.findElement(acodeSubmitBtn).click();
		return this;
	}
	
	
	public VehiclePreview clickChangeAngleDropDown(WebDriver driver, String tc) throws IOException {
		elementExist(driver, changeAngle, true, tc);
		driver.findElement(changeAngle).click();
		return this;
	}

	public VehiclePreview selectOneAngleFrChangeAngleDropDown(WebDriver driver, int num, String tc) throws IOException {
		By changeAngleNum = By.xpath("//*[@id=\"anglesDropdownList\"]/li[" + num + "]");
		elementExist(driver, changeAngleNum, true, tc);
		driver.findElement(changeAngleNum).click();
		return this;
	}

//	public VehiclePreview clickCreateNewSet(WebDriver driver) throws IOException {
//		driver.findElement(dealerShipName).click();
//		return this;
//	}
//
//	public VehiclePreview clickMapBackGrounds(WebDriver driver, int num) {
//		// By mapBackGroundsLocator=By.xpath("(//button[@id='listViewBtn'])[1]");
//		num = num * 4 - 3;
//		By mapBackGroundsLocator = By.xpath("(//button[@id='listViewBtn'])[" + num + "]");
//		driver.findElement(mapBackGroundsLocator).click();
//		return this;
//
//	}

//	public VehiclePreview clickEditSet(WebDriver driver, String tc) throws IOException {
//		elementExist(driver, dealerShipName, true, tc);
//		driver.findElement(dealerShipName).click();
//		return this;
//	}

	public void verifyLoadAngleImage(WebDriver driver, int num, String tc) throws IOException {
		// Verify angle image showing or not:
		Wait(1);
		By angleImage = By.xpath("//*[@id=\"wrapper\"]/div[" + num + "]/img[2]");
		elementExist(driver, angleImage, true, tc);
		VerifyImageLoaded(driver, angleImage, tc);

	}

	
	
	
	public VehiclePreview clickYearDropDown(WebDriver driver, String tc) throws IOException {
		elementExist(driver, yearDropDown, true, tc);
		driver.findElement(yearDropDown).click();
		return this;
	}
	
	public VehiclePreview clickYearOne(WebDriver driver, int num, String tc) throws IOException {
		By yearOne = By.xpath("/html/body/div/div/div[2]/div/div/div/div[1]/div[2]/div[1]/ul/li["+num+"]");  // 1,2...
		elementExist(driver, yearOne, true, tc);
		driver.findElement(yearOne).click();
		return this;
	}	
	
	public VehiclePreview clickMakeDropDown(WebDriver driver, String tc) throws IOException {
		elementExist(driver, makeDropDown, true, tc);
		driver.findElement(makeDropDown).click();
		return this;
	}
	
	public VehiclePreview clickMakeOne(WebDriver driver, int num, String tc) throws IOException {
		By makeOne = By.xpath("/html/body/div/div/div[2]/div/div/div/div[1]/div[2]/div[2]/ul/li["+num+"]");// 3...
		elementExist(driver, makeOne, true, tc);
		driver.findElement(makeOne).click();
		return this;
	}	
		
	public VehiclePreview clickModelDropDown(WebDriver driver, String tc) throws IOException {
		elementExist(driver, modelDropDown, true, tc);
		driver.findElement(modelDropDown).click();
		return this;
	}
	
	public VehiclePreview clickModelOne(WebDriver driver, int num, String tc) throws IOException {
		By modelOne = By.xpath("/html/body/div/div/div[2]/div/div/div/div[1]/div[2]/div[3]/ul/li["+num+"]");// 10
		elementExist(driver, modelOne, true, tc);
		driver.findElement(modelOne).click();
		return this;
	}	
		
	public VehiclePreview clickTrimDropDown(WebDriver driver, String tc) throws IOException {
		elementExist(driver, trimDropDown, true, tc);
		driver.findElement(trimDropDown).click();
		return this;
	}
	
	public VehiclePreview clickTrimOne(WebDriver driver, int num, String tc) throws IOException {
//		By trimOne = By.xpath("/html/body/div/div/div[2]/div/div/div/div[1]/div[2]/div[3]/ul/li["+num+"]");// 1,2,....10
		//						//*[@id="trimsDropdownList"]/li[2] 
		By trimOne = By.xpath("//*[@id=\"trimsDropdownList\"]/li["+num+"]");// 1,2,....10
		elementExist(driver, trimOne, true, tc);
		driver.findElement(trimOne).click();
		return this;
	}	
			
	
	
	
	
	public VehiclePreview clickYmmSubmit(WebDriver driver, String tc) throws IOException {
		elementExist(driver, ymmSubmitBtn, true, tc);
		driver.findElement(ymmSubmitBtn).click();
		return this;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
