package AdminPortal;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ExportTemplateList extends Comlibs {
	private final WebDriver driver;

	public ExportTemplateList(WebDriver driver) throws IOException {
		this.driver = driver;
		// String wh1=driver.getWindowHandle();
		String sPageTitle = "Export Template List";
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

	By addExportTemplateBtnLocator = By.xpath("//*[@id=\"addImageTypeBtn\"]");
	By searchLocator = By.xpath("//*[@id=\"imageTypeTable_filter\"]/label/input");
	// By editLocator =By.xpath("(//button[@id='listViewBtn'])[2]");//1,2,3....
	// By deleteLocator =By.xpath("(//button[@id='dealerViewBtn'])[2]");//1,2,3....
	By cancelBtnLocator = By.xpath("//*[@id=\"newImageTypeModal\"]/div/div/div[3]/button[2]");
	By submitBtnLocator = By.xpath("//*[@id=\"newImageTypeModalBtn\"]");
	By exportNameLocator = By.xpath("//*[@id=\"name\"]");
	By exportPrettyNameLocator=By.xpath("//*[@id=\"prettyName\"]");
	By fileNameLocator = By.xpath("//*[@id=\"filename\"]");
	By userLocator = By.xpath("//*[@id=\"user\"]");
	By passwordLocator = By.xpath("//*[@id=\"password\"]");
	By hostLocator = By.xpath("//*[@id=\"host\"]");
	By templateLocator = By.xpath("//*[@id=\"template\"]");
	By combinedFileLocator = By.xpath("//*[@id=\"combineFile\"]");
	By brandedImagesLocator = By.xpath("//*[@id=\"branded\"]");
//	By runExporticon= By.xpath("//*[@id='runExportBtn']/span"); //1,2,3...
//	By runExporticon2= By.xpath("//button[@id='runExportBtn']/span"); //1,2,3...
//	By runExporticon3= By.xpath("//td[7]/button/span"); //1,2,3...
//	By runExporticon4= By.cssSelector(".odd:nth-child(1) #runExportBtn > .glyphicon");//1,2,3...
	
//	By downLoadicon= By.xpath("//*[@id=\"imageTypeTable\"]/tbody/tr[1]/td[7]/form/button/span"); //1,2,3...
	// By nameLocator= By.xpath("//tr[2]/td"); //1,2,3...
	// By nameLocator= By.xpath("//tr[2]/td"); //1,2,3...

	public ExportTemplateList clickAddExportTemplateBtn(WebDriver driver, String tc) throws IOException {
		elementExist(driver, addExportTemplateBtnLocator, true, tc);
		driver.findElement(addExportTemplateBtnLocator).click();
		return this;
	}

	public ExportTemplateList inputSearch(WebDriver driver, String name, String tc) throws IOException {
		driver.findElement(searchLocator).clear();
		driver.findElement(searchLocator).sendKeys(name);
		return this;
	}

	public ExportTemplateList clickEditBtn(WebDriver driver, int num, String tc) throws IOException {
		By editLocator = By.xpath("(//button[@id='listViewBtn'])[" + num + "]");// 1,2,3....
		driver.findElement(editLocator).click();
		return this;
	}

	public ExportTemplateList clickDeleteBtn(WebDriver driver, int num, String tc) throws IOException {
		By deleteLocator = By.xpath("(//button[@id='dealerViewBtn'])[\"+num+\"]");// 1,2,3....
		driver.findElement(deleteLocator).click();
		return this;
	}

	public ExportTemplateList clickCancel(WebDriver driver, String tc) throws IOException {
		driver.findElement(cancelBtnLocator).click();
		return this;
	}

	public ExportTemplateList clickSubmit(WebDriver driver, String tc) throws IOException {
		driver.findElement(submitBtnLocator).click();
		return this;
	}
	
	public ExportTemplateList clickRunExoprt(WebDriver driver, String tc) throws IOException {
		By runExporticon= By.xpath("//*[@id='runExportBtn']/span"); //1,2,3...
//		By runExporticon2= By.xpath("//button[@id='runExportBtn']/span"); //1,2,3...
//		By runExporticon3= By.xpath("//td[7]/button/span"); //1,2,3...
//		By runExporticon4= By.cssSelector(".odd:nth-child(1) #runExportBtn > .glyphicon");//1,2,3...
		boolean elementExist = elementExist(driver, runExporticon, true, tc);
		if (elementExist) {
			driver.findElement(runExporticon).click();
//			driver.findElement(runExporticon2).click();
//			driver.findElement(runExporticon3).click();
//			driver.findElement(runExporticon4).click();
		}
		
		return this;
	}
	public void clickOKwithWindowAlert() throws AWTException {
		Wait(1);
		// String vehicleImage="";
		// //Don't try to debug below code
		// StringSelection ss = new StringSelection(vehicleImage);
		// Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		Robot robot = new Robot();
		try {
			robot.keyPress(KeyEvent.VK_ACCEPT);
			robot.keyRelease(KeyEvent.VK_ACCEPT);

		} catch (Throwable e) {
			System.out.println("0 Error to Accept the alert.");
		}
		// robot.keyPress(KeyEvent.VK_CONTROL);
		// robot.keyPress(KeyEvent.VK_V);
		// robot.keyRelease(KeyEvent.VK_V);
		// robot.keyRelease(KeyEvent.VK_CONTROL);
		// robot.keyPress(KeyEvent.VK_ENTER);
		// Wait(1);
		// robot.keyRelease(KeyEvent.VK_ENTER);
		Wait(1);
		System.out.println("Click Accept!");
	}
	public void clickOKwithWindowAlert1() throws AWTException {
		Wait(1);
		// String vehicleImage="";
		// //Don't try to debug below code
		// StringSelection ss = new StringSelection(vehicleImage);
		// Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_SPACE);
			robot.keyRelease(KeyEvent.VK_SPACE);
		} catch (Throwable e) {
			System.out.println("1 Error to Accept the alert.");
		}

		// robot.keyPress(KeyEvent.VK_CONTROL);
		// robot.keyPress(KeyEvent.VK_V);
		// robot.keyRelease(KeyEvent.VK_V);
		// robot.keyRelease(KeyEvent.VK_CONTROL);
		// robot.keyPress(KeyEvent.VK_ENTER);
		// Wait(1);
		// robot.keyRelease(KeyEvent.VK_ENTER);
		Wait(3);
		System.out.println("Click Space!");
	}

	public void clickOKwithWindowAlert2() throws AWTException {
		Wait(1);
		// String vehicleImage="";
		// //Don't try to debug below code
		// StringSelection ss = new StringSelection(vehicleImage);
		// Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_O);
			robot.keyRelease(KeyEvent.VK_O);
		} catch (Throwable e) {
			System.out.println("2 Error to Accept the alert.");
		}

		// robot.keyPress(KeyEvent.VK_CONTROL);
		// robot.keyPress(KeyEvent.VK_V);
		// robot.keyRelease(KeyEvent.VK_V);
		// robot.keyRelease(KeyEvent.VK_CONTROL);
		// robot.keyPress(KeyEvent.VK_ENTER);
		// Wait(1);
		// robot.keyRelease(KeyEvent.VK_ENTER);
		Wait(3);
		System.out.println("Click O key!");
	}
	public ExportTemplateList clickDownload(WebDriver driver, String tc) throws IOException {
//		By downLoadicon= By.xpath("//*[@id=\"imageTypeTable\"]/tbody/tr[1]/td[7]/form/button/span"); //1,2,3... not working since 20191108
		By downLoadicon= By.xpath("//*[@id=\"imageTypeTable\"]/tbody/tr/td[7]/form/button/span");		//new 20191108. only one row:    //*[@id="imageTypeTable"]/tbody/tr/td[7]/form/button/span
		boolean elementExist = elementExist(driver, downLoadicon, true, tc);
		if (elementExist) {
			driver.findElement(downLoadicon).click();
//			driver.findElement(downLoadicon).click();
//			driver.findElement(downLoadicon).click();
//			driver.findElement(downLoadicon).click();
		}

		return this;
	}

	public ExportTemplateList inputExportName(WebDriver driver, String exportName, String tc) throws IOException {
		Wait(1);
		driver.findElement(exportNameLocator).clear();
		driver.findElement(exportNameLocator).sendKeys(exportName);
		return this;
	}

	public ExportTemplateList inputExportPrettyName(WebDriver driver, String fileName, String tc) throws IOException {
		driver.findElement(exportPrettyNameLocator).clear();
		driver.findElement(exportPrettyNameLocator).sendKeys(fileName);
		return this;
	}
	public ExportTemplateList inputFileName(WebDriver driver, String fileName, String tc) throws IOException {
		driver.findElement(fileNameLocator).clear();
		driver.findElement(fileNameLocator).sendKeys(fileName);
		return this;
	}
	public ExportTemplateList inputUser(WebDriver driver, String user, String tc) throws IOException {
		driver.findElement(userLocator).clear();
		driver.findElement(userLocator).sendKeys(user);
		return this;
	}

	public ExportTemplateList inputPassword(WebDriver driver, String password, String tc) throws IOException {
		driver.findElement(passwordLocator).clear();
		driver.findElement(passwordLocator).sendKeys(password);
		return this;
	}

	public ExportTemplateList inputHost(WebDriver driver, String host, String tc) throws IOException {
		driver.findElement(hostLocator).clear();
		driver.findElement(hostLocator).sendKeys(host);
		return this;
	}

	public ExportTemplateList inputTemplate(WebDriver driver, String template, String tc) throws IOException {
		driver.findElement(templateLocator).clear();
		driver.findElement(templateLocator).sendKeys(template);
		return this;
	}

	public ExportTemplateList clickCombinedFileCheckBox_removed_by_AUTOPXOPS_1816(WebDriver driver) throws IOException {
		driver.findElement(combinedFileLocator).click();
		return this;
	}

	public ExportTemplateList clickBrandedImagesCheckBox_removed_by_AUTOPXOPS_1816(WebDriver driver) throws IOException {
		driver.findElement(brandedImagesLocator).click();
		return this;
	}

	public String getNameString(WebDriver driver, int num, String tc) throws IOException {
		String nameS = "";
		try {
			By nameLocator = By.xpath("//tr[" + num + "]/td"); // 1,2,3...
			nameS = driver.findElement(nameLocator).getText();
		} catch (Exception ex2) {
			nameS = "null";
		}
		return nameS;
	}
	public String timeString() {
		String dateStamp, timeStamp;
		Calendar cal = Calendar.getInstance();
		cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		timeStamp = sdf.format(cal.getTime());
		DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
		Date d = new Date();
		dateStamp = df.format(d);
		timeStamp = dateStamp + "  " + timeStamp;
		timeStamp = timeStamp.replace("/", "_");
		timeStamp = timeStamp.replace(":", "_");
		timeStamp = timeStamp.replace(" ", "_");
		return timeStamp;
	}
}
