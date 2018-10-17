package AdminPortal;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import DealerPortal.ImageGallery;

public class BackgroundSets extends Comlibs {
	private final WebDriver driver;

	public BackgroundSets(WebDriver driver) throws IOException {
		this.driver = driver;
		// String wh1=driver.getWindowHandle();
		String sPageTitle = "Background Sets";
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

	By creatNewSetBtnLocator = By.xpath("//*[@id=\"creatNewSetBtn\"]");
	By searchLocator = By.xpath("//input[@type='search']");
	By mapBackGroundsLocator1 = By.xpath("(//button[@id='listViewBtn'])[1]"); //// *[@id="listViewBtn"]/span
	By mapBackGroundsLocator2 = By.xpath("(//button[@id='listViewBtn'])[5]");// 2rd //*[@id="listViewBtn"]/span
	By mapBackGroundsLocator3 = By.xpath("xpath=(//button[@id='listViewBtn'])[9]");// 3rd
	By mapBackGroundsLocator4 = By.xpath("xpath=(//button[@id='listViewBtn'])[13]");// 4thd, there is a 4 btw set.
	By editsetBtnLocator1 = By.xpath("(//button[@id='listViewBtn'])[2]");// 2,6,10..., there is a 4 btw set.
	By editsetBtnLocator2 = By.xpath("(//button[@id='listViewBtn'])[6]");
	By editsetBtnLocator3 = By.xpath("(//button[@id='listViewBtn'])[10]");
	By editsetBtnLocator4 = By.xpath("(//button[@id='listViewBtn'])[14]");
	By manageBGsetBtnLocator1 = By.xpath("(//button[@id='listViewBtn'])[3]");// 3,7,11...
	By manageBGsetBtnLocator2 = By.xpath("(//button[@id='listViewBtn'])[7]");
	By manageBGsetBtnLocator3 = By.xpath("(//button[@id='listViewBtn'])[11]");
	By manageBGsetBtnLocator4 = By.xpath("(//button[@id='listViewBtn'])[15]");
	By dealersUseBGLocator1 = By.xpath("(//button[@id='listViewBtn'])[4]");
	By dealersUseBGLocator2 = By.xpath("(//button[@id='listViewBtn'])[8]");
	By dealersUseBGLocator3 = By.xpath("(//button[@id='listViewBtn'])[12]");
	By dealersUseBGLocator4 = By.xpath("(//button[@id='listViewBtn'])[16]");
	By deleteBGsetLocator1 = By.xpath("(//button[@id='dealerViewBtn'])[1]");
	By deleteBGsetLocator2 = By.xpath("(//button[@id='dealerViewBtn'])[2]");
	By deleteBGsetLocator3 = By.xpath("(//button[@id='dealerViewBtn'])[3]");
	By deleteBGsetLocator4 = By.xpath("(//button[@id='dealerViewBtn'])[4]");
	By editSetPopUpCancelLocator = By.xpath("//*[@id=\"newSetModal\"]/div/div/div[3]/button[2]");
	By editSetPopUpSubmitLocator = By.xpath("//*[@id=\"newSetModalBtn\"]");
	By editSetPopUpAllDealerCheckBoxLocator = By.xpath("//*[@id=\"ckAllDealers\"]");
	By dealerUsingBGLocator = By.xpath("//*[@id=\"dealerListModal\"]/div/div/div[3]/button");
	By setnameLocator = By.xpath("//*[@id=\"setDesc\"]");
	By subitBtnLocator = By.xpath("//*[@id=\"newSetModalBtn\"]");
	By cancelBtnLocator = By.xpath("//*[@id=\"newSetModal\"]/div/div/div[3]/button[2]");
	By chooseFilesBtnLocator = By.xpath("//*[@id=\"images\"]");
	By ManageImageTypesLocator = By.xpath("//*[@id=\"imagetypeMenu\"]");// Firefox: //*[@id="imagetypeMenu"]
	// By Locator=By.xpath("");
	// By Locator=By.xpath("");
	// By Locator=By.xpath("");
	// By Locator=By.xpath("");
	// By Locator=By.xpath("");
	// By Locator=By.xpath("");
	// By Locator=By.xpath("");

	public BackgroundSets clickCreateNewSet(WebDriver driver) throws IOException {
		driver.findElement(creatNewSetBtnLocator).click();
		return this;
	}

	public Backgrounds clickMapBackGroundsBtn(WebDriver driver, int num) throws IOException {
		// By mapBackGroundsLocator=By.xpath("(//button[@id='listViewBtn'])[1]");//[1],[5],[9]....4 btw.
		num = (num - 1) * 4 + 1;// 1,5,9
		By mapBackGroundsLocator = By.xpath("(//button[@id='listViewBtn'])[" + num + "]");
		System.out.println("Please wait at least 2 minutes untill Backgrounds page showing......");
		driver.findElement(mapBackGroundsLocator).click();
		return new Backgrounds(driver);
	}

	public BackgroundSets clickEditSetBtn(WebDriver driver, int num) throws IOException {
		num = (num - 1) * 4 + 2;
		By editsetBtnLocator1 = By.xpath("(//button[@id='listViewBtn'])[" + num + "]");// 2,6,10..., there is a 4 btw set.
		driver.findElement(editsetBtnLocator1).click();
		return this;
	}

	public ManageBackgrounds clickManageBGImageBtn(WebDriver driver, int num) throws IOException {
		num = (num - 1) * 4 + 3;
		By manageBGsetBtnLocator = By.xpath("(//button[@id='listViewBtn'])[" + num + "]");
		driver.findElement(manageBGsetBtnLocator).click();
		return new ManageBackgrounds(driver);
	}

	public BackgroundSets clickDealersUseBackGroundBtn(WebDriver driver, int num) throws IOException {
		num = (num - 1) * 4 + 4;
		By dealersUseBGLocator = By.xpath("(//button[@id='listViewBtn'])[" + num + "]");// 4,8,12...
		driver.findElement(dealersUseBGLocator).click();
		return this;
	}

	public BackgroundSets clickDeleteBGSetBtn(WebDriver driver, int num) throws IOException {
		By deleteBGsetLocator = By.xpath("(//button[@id='dealerViewBtn'])[" + num + "]");// 1,2,3,4....
		driver.findElement(deleteBGsetLocator).click();
		return this;
	}

	public BackgroundSets inputSearch(WebDriver driver, String dlrID) throws IOException {
		driver.findElement(searchLocator).sendKeys(dlrID);
		return this;
	}

	public BackgroundSets clickAllDealershipsCheckBox(WebDriver driver) throws IOException {
		driver.findElement(editSetPopUpAllDealerCheckBoxLocator).click();
		return this;
	}

	public BackgroundSets clickCancel(WebDriver driver) throws IOException {
		driver.findElement(editSetPopUpCancelLocator).click();
		return this;
	}

	public BackgroundSets clickClose(WebDriver driver) throws IOException {
		driver.findElement(dealerUsingBGLocator).click();
		return this;
	}

	public BackgroundSets inputSetName(WebDriver driver, String setName) throws IOException {
		driver.findElement(setnameLocator).sendKeys(setName);
		return this;
	}

	public Backgrounds clickSubmit(WebDriver driver) throws IOException {
		Wait(2);
		driver.findElement(subitBtnLocator).click();
		Wait(30);
		return new Backgrounds(driver);
	}
	public ImageTypeList clickManageImageType(WebDriver driver) throws IOException {
		driver.findElement(ManageImageTypesLocator).click();
		return new ImageTypeList(driver);
	}
	public BackgroundSets selectSetType(WebDriver driver,int num) throws IOException {
		By SetTypeLocator = By.xpath("//*[@id='sequence']/option["+num+"]");
		driver.findElement(SetTypeLocator).click();
		return this;
	}
	//*[@id='sequence']/option[3]
	
//	public BackgroundSets clickRefleshF5Btn(WebDriver driver, String tc) throws IOException {
//		// driver.findElement(addInventoryBtn).sendKeys(Keys.F5);
//		driver.navigate().to(driver.getCurrentUrl());
//		return this;
//	}

//	public void acceptAlert(String tc, String alertType) throws IOException {
//		boolean alertPass = false;
//		Wait(1);
//		try {
//			driver.switchTo().alert().accept();// on the “Ok” button as soon as the pop up window appears.
//			// driver.switchTo().alert().dismiss();// clicks on the “Cancel” button as soon as the pop up window appears.
//			driver.switchTo().defaultContent();//
//			alertPass = true;
//			System.out.println("1. Accept the alert.");
//			// rwExcel(tc, true, "Alert showing, Accept the alert =" + alertType, "Accetped successfully.");
//		} catch (Throwable e) {
//			alertPass = false;
//			System.out.println("1. Failed to Accept the alert.");
//			rwExcel(tc, false, "Alert showing, Accept the alert =" + alertType, "failed to accetp.");
//		}
//		// return alertPass;
//	}
}
