package AdminPortal;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
			System.out.println("Page title does not matche. Expected page title =" + sPageTitle);
		}
	}

	By dealerShipName = By.cssSelector("span");

	By exteriorModelYearBtn = By.xpath("//*[@id=\"dealerList\"]/div/div[2014]/button"); // = modelcode bar 2019-GM-4NF56-4NF56-1SD
	By interiorModelYearBtn = By.xpath("//*[@id=\"dealerList\"]/div/div[4867]/button"); // = modelcode bar 2019-GM-4NF56-1SD-4NF56-1SD
	By bigCarWithBGImage = By.xpath("//*[@id=\"carousel-example-generic\"]/div/div[1]/div/img[2]");
	By bigCarWithBGImagefailed = By.xpath("//*[@id=\"carousel-example-generic\"]/a[1]");// ("//*[@id=\"carousel-example-generic\"]/div/div[2]");
	By closeX_Btn = By.xpath("//*[@id=\"modal\"]/div/div/div[1]/button/span");
	By rightArrowBtn = By.xpath("//*[@id=\"carousel-example-generic\"]/a[2]/span[1]");
	By leftArrowBtn = By.xpath("//*[@id=\"carousel-example-generic\"]/a[1]/span[1]");
	By BackgroundPicLocator = By.xpath("//*[@id=\"background-row\"]/div/img");
	By BackgroundPic2Locator = By.xpath("//*[@id=\"background-row\"]/div[2]/img");

	By saveAndCopyToAllMatching = By.xpath("//*[@id=\"btnSaveAll\"]");
	By saveBtn = By.xpath("//*[@id=\"btnSave\"]");
	By chooseFileBtn = By.xpath("//*[@id=\"btnUploadPics\"]");

	public Backgrounds clickCreateNewSet(WebDriver driver) throws IOException {
		driver.findElement(dealerShipName).click();
		return this;
	}

	public Backgrounds clickMapBackGrounds(WebDriver driver, int num) {
		// By mapBackGroundsLocator=By.xpath("(//button[@id='listViewBtn'])[1]");
		num = num * 4 - 3;
		By mapBackGroundsLocator = By.xpath("(//button[@id='listViewBtn'])[" + num + "]");
		driver.findElement(mapBackGroundsLocator).click();
		return this;

	}

	public Backgrounds clickEditSet(WebDriver driver) throws IOException {
		driver.findElement(dealerShipName).click();
		return this;
	}

	public Backgrounds ClickOneExteriorModelYearBtn(WebDriver driver, String tc) throws Exception {
		elementExist(driver, exteriorModelYearBtn, true, tc);
		driver.findElement(exteriorModelYearBtn).click();

		return this;
	}

	public Backgrounds ClickOneInteriorModelYearBtn(WebDriver driver, String tc) throws IOException {
		elementExist(driver, interiorModelYearBtn, true, tc);
		driver.findElement(interiorModelYearBtn).click();
		return this;
	}

	public Backgrounds ClickAnyOneOfExteriorOrInteriorModelYearBtn(WebDriver driver, int sn, String tc)
			throws Exception {
		By anyModelYearBtn = By.xpath("//*[@id=\"dealerList\"]/div/div[" + sn + "]/button");
		elementExist(driver, anyModelYearBtn, true, tc);
		driver.findElement(anyModelYearBtn).click();

		return this;
	}

	public void VerifyCarImage(WebDriver driver, String tc) throws IOException {
		rwExcel(tc, "Verify Load Vehicle Image", " ");
		// if (elementExist(driver, bigCarWithBGImagefailed, false, tc)) {
		// rwExcel(tc, false,"Verify failed to load Vehicle Image", " Image failed to load...");
		// };
		VerifyImageLoaded(driver, bigCarWithBGImage, tc);
	}

	public void VerifyCarImaeLoadedFailed(WebDriver driver, String tc) throws IOException {
		rwExcel(tc, "Verify failed to load Vehicle Image", " ");
		VerifyImageLoaded(driver, bigCarWithBGImagefailed, tc);
	}

	public Backgrounds clickCloseX(WebDriver driver, String tc) throws IOException {
		elementExist(driver, closeX_Btn, true, tc);
		driver.findElement(closeX_Btn).click();
		return this;
	}

	public Backgrounds clickRightArrowBtn(WebDriver driver, String tc) throws IOException {
		elementExist(driver, rightArrowBtn, true, tc);
		driver.findElement(rightArrowBtn).click();
		return this;
	}

	public Backgrounds clickLeftArrowBtn(WebDriver driver, String tc) throws IOException {
		elementExist(driver, leftArrowBtn, true, tc);
		driver.findElement(leftArrowBtn).click();
		return this;
	}

	public Backgrounds clickBackgroundPic(WebDriver driver, String tc) throws IOException {
		try {
			driver.findElement(BackgroundPicLocator).click();
		} catch (Exception e) {
			rwExcel(tc, false, "Upload background pictures", "Click on background picture.");
		}
		return this;
	}

	public Backgrounds clickBackgroundPic2(WebDriver driver, String tc) throws IOException {
		try {
			driver.findElement(BackgroundPic2Locator).click();
		} catch (Exception e) {
			rwExcel(tc, false, "Upload background pictures", "Click on background picture 2.");
		}
		return this;
	}

	public Backgrounds clickSaveAndCopyToAllMatching(WebDriver driver, String tc) throws IOException {
		try {
			driver.findElement(saveAndCopyToAllMatching).click();
			rwExcel(tc, true, "Upload background pictures", "Click on Save And Copy To AllMatching button.");
		} catch (Exception e) {
			rwExcel(tc, false, "Upload background pictures", "Click on Save And Copy To AllMatching button.");
		}
		return this;
	}

	public void uploadBackgroundPicture(WebDriver driver, String imagePath, String tc) throws IOException {

		try {
			// driver.findElement(By.id("btnUploadPics")).sendKeys(imagePath); // By id - ok
			driver.findElement(chooseFileBtn).sendKeys(imagePath);// By X.Path - ?
			Wait(2);
			rwExcel(tc, true, "Upload background pictures",
					"Click on Choose Files button in Backgrounds page successfully.");
		} catch (Exception e) {
			rwExcel(tc, false, "Upload background pictures", "Click on Choose Files button in Backgrounds page.");
			Wait(2);
		}

	}

	public Backgrounds uploadBackgroundPictureFrBackgroundsPage(WebDriver driver, String imagePath, String browser,
			int maxWaitTime, int minWaitTime, String tc) throws IOException {
		int waitTime = maxWaitTime;
		if (browser.equalsIgnoreCase("Chrome")) {// Chrome browser does not need the maxWaitTime because Chrome will check the Backgrounds page till it is up but FF does not.
			waitTime = minWaitTime;
		}
		try {
			// driver.findElement(By.id("btnUploadPics")).sendKeys(imagePath); // By id - ok
			driver.findElement(chooseFileBtn).sendKeys(imagePath);// By X.Path - ?
			System.out.println("\nPlease wait...this will be Waiting \"" + waitTime
					+ "\" seconds for Backgrounds page reloadling... \n");
			Wait(waitTime);
			rwExcel(tc, true, "Upload background pictures from Background page",
					"Click on Choose Files button in Backgrounds page successfully.");
		} catch (Exception e) {
			rwExcel(tc, false, "Upload background pictures from Background page",
					"Click on Choose Files button in Backgrounds page.");
			Wait(2);
		}
		// return this; // does not work for waiting
		return new Backgrounds(driver);
	}
}
