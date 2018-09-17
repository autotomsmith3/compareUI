package AdminPortal;

import java.io.IOException;
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
	By fileNameLocator = By.xpath("//*[@id=\"filename\"]");
	By userLocator = By.xpath("//*[@id=\"user\"]");
	By passwordLocator = By.xpath("//*[@id=\"password\"]");
	By hostLocator = By.xpath("//*[@id=\"host\"]");
	By templateLocator = By.xpath("//*[@id=\"template\"]");
	By combinedFileLocator = By.xpath("//*[@id=\"combineFile\"]");
	By brandedImagesLocator = By.xpath("//*[@id=\"branded\"]");
	// By nameLocator= By.xpath("//tr[2]/td"); //1,2,3...

	public ExportTemplateList clickAddExportTemplateBtn(WebDriver driver) throws IOException {
		driver.findElement(addExportTemplateBtnLocator).click();
		return this;
	}

	public ExportTemplateList inputSearch(WebDriver driver, String name) throws IOException {
		driver.findElement(searchLocator).sendKeys(name);
		return this;
	}

	public ExportTemplateList clickEditBtn(WebDriver driver, int num) throws IOException {
		By editLocator = By.xpath("(//button[@id='listViewBtn'])[" + num + "]");// 1,2,3....
		driver.findElement(editLocator).click();
		return this;
	}

	public ExportTemplateList clickDeleteBtn(WebDriver driver, int num) throws IOException {
		By deleteLocator = By.xpath("(//button[@id='dealerViewBtn'])[\"+num+\"]");// 1,2,3....
		driver.findElement(deleteLocator).click();
		return this;
	}

	public ExportTemplateList clickCancel(WebDriver driver) throws IOException {
		driver.findElement(cancelBtnLocator).click();
		return this;
	}

	public ExportTemplateList clickSubmit(WebDriver driver) throws IOException {
		driver.findElement(submitBtnLocator).click();
		return this;
	}

	public ExportTemplateList inputExportName(WebDriver driver, String exportName) throws IOException {
		Wait(1);
		driver.findElement(exportNameLocator).clear();
		driver.findElement(exportNameLocator).sendKeys(exportName);
		return this;
	}

	public ExportTemplateList inputFileName(WebDriver driver, String fileName) throws IOException {
		driver.findElement(fileNameLocator).clear();
		driver.findElement(fileNameLocator).sendKeys(fileName);
		return this;
	}

	public ExportTemplateList inputUser(WebDriver driver, String user) throws IOException {
		driver.findElement(userLocator).clear();
		driver.findElement(userLocator).sendKeys(user);
		return this;
	}

	public ExportTemplateList inputPassword(WebDriver driver, String password) throws IOException {
		driver.findElement(passwordLocator).clear();
		driver.findElement(passwordLocator).sendKeys(password);
		return this;
	}

	public ExportTemplateList inputHost(WebDriver driver, String host) throws IOException {
		driver.findElement(hostLocator).clear();
		driver.findElement(hostLocator).sendKeys(host);
		return this;
	}

	public ExportTemplateList inputTemplate(WebDriver driver, String template) throws IOException {
		driver.findElement(templateLocator).clear();
		driver.findElement(templateLocator).sendKeys(template);
		return this;
	}

	public ExportTemplateList clickCombinedFileCheckBox(WebDriver driver) throws IOException {
		driver.findElement(combinedFileLocator).click();
		return this;
	}

	public ExportTemplateList clickBrandedImagesCheckBox(WebDriver driver) throws IOException {
		driver.findElement(brandedImagesLocator).click();
		return this;
	}

	public String getNameString(WebDriver driver, int num) throws IOException {
		By nameLocator = By.xpath("//tr[" + num + "]/td"); // 1,2,3...
		String nameS = driver.findElement(nameLocator).getText();
		return nameS;
	}
}
