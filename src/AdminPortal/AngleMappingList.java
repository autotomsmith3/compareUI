package AdminPortal;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AngleMappingList extends Comlibs {
	private final WebDriver driver;

	public AngleMappingList(WebDriver driver) throws IOException {
		this.driver = driver;
		// String wh1=driver.getWindowHandle();
		String sPageTitle = "Angle Mapping List";
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

	By addAngleMappingLocator = By.xpath("//*[@id=\"addImportAngleMappingBtn\"]/span");
	By angleMappingErrorsLocator = By.xpath("//*[@id=\"angleMappingErrorModalBtn\"]");
	By searchLocator = By.xpath("//*[@id=\"importAngleMappingTable_filter\"]/label/input");
	// By editLocator=By.xpath("(//button[@id='listViewBtn'])["+num+"]");//1,2,3....
	// By deleteLocator=By.xpath("(//button[@id='dealerViewBtn'])["+num+"]");//1,2,3....
	By cancelBtnLocator = By.xpath("//*[@id=\"newImportAngleMappingModal\"]/div/div/div[3]/button[2]");
	By submitBtnLocator = By.xpath("//*[@id=\"newImportAngleMappingModalBtn\"]");
	By instanceLocator = By.xpath("//*[@id=\"instance\"]");
	By OEMLocator = By.xpath("//*[@id=\"oem\"]");
	By sequenceLocator = By.xpath("//*[@id=\"sequence\"]");
	By NoteLocator = By.xpath("//*[@id=\"note\"]");
	By patternLocator = By.xpath("//*[@id=\"pattern\"]");

	By angleMappingErrorsTabLocator = By.xpath("//*[@id=\"angleMappingErrorModal\"]/div/div/div[2]/ul/li[2]/a");
	By flikVehiclesErrorsTabLocator = By.xpath("//*[@id=\"angleMappingErrorModal\"]/div/div/div[2]/ul/li[1]/a");
	By closeBtnLocator = By.xpath("//*[@id=\"angleMappingErrorModal\"]/div/div/div[3]/button");

	public AngleMappingList clickAddAngleMappingBtn(WebDriver driver) throws IOException {
		driver.findElement(addAngleMappingLocator).click();
		return this;
	}

	public AngleMappingList clickAngleMappingErrorsBtn(WebDriver driver) throws IOException {
		driver.findElement(angleMappingErrorsLocator).click();
		return this;
	}

	public AngleMappingList clickAngleMappingErrorsTab(WebDriver driver) throws IOException {
		driver.findElement(angleMappingErrorsTabLocator).click();
		return this;
	}

	public AngleMappingList clickFlikVehiclesErrorsTab(WebDriver driver) throws IOException {
		driver.findElement(flikVehiclesErrorsTabLocator).click();
		return this;
	}

	public AngleMappingList clickCloseBtn(WebDriver driver) throws IOException {
		driver.findElement(closeBtnLocator).click();
		return this;
	}

	public AngleMappingList inputSearch(WebDriver driver, String sequence) throws IOException {
		driver.findElement(searchLocator).sendKeys(sequence);
		return this;
	}

	public AngleMappingList clickEditBtn(WebDriver driver, int num) throws IOException {
		By editLocator = By.xpath("(//button[@id='listViewBtn'])[" + num + "]");// 1,2,3....
		driver.findElement(editLocator).click();
		return this;
	}

	public AngleMappingList clickDeleteBtn(WebDriver driver, int num) throws IOException {
		By deleteLocator = By.xpath("(//button[@id='dealerViewBtn'])[" + num + "]");// 1,2,3....
		driver.findElement(deleteLocator).click();
		return this;
	}

	public AngleMappingList clickCancel(WebDriver driver) throws IOException {
		driver.findElement(cancelBtnLocator).click();
		return this;
	}

	public AngleMappingList clickSubmit(WebDriver driver) throws IOException {
		driver.findElement(submitBtnLocator).click();
		return this;
	}

	public AngleMappingList inputInstance(WebDriver driver, String instance) throws IOException {
		Wait(1);
		driver.findElement(instanceLocator).clear();
		driver.findElement(instanceLocator).sendKeys(instance);
		return this;
	}

	public AngleMappingList inputOEM(WebDriver driver, String OEM) throws IOException {
		Wait(1);
		driver.findElement(OEMLocator).clear();
		driver.findElement(OEMLocator).sendKeys(OEM);
		return this;
	}

	public AngleMappingList inputSequence(WebDriver driver, String sequence) throws IOException {
		Wait(1);
		driver.findElement(sequenceLocator).clear();
		driver.findElement(sequenceLocator).sendKeys(sequence);
		return this;
	}

	public AngleMappingList inputNote(WebDriver driver, String note) throws IOException {
		Wait(1);
		driver.findElement(NoteLocator).clear();
		driver.findElement(NoteLocator).sendKeys(note);
		return this;
	}

	public AngleMappingList inputPattern(WebDriver driver, String pattern) throws IOException {
		Wait(1);
		driver.findElement(patternLocator).clear();
		driver.findElement(patternLocator).sendKeys(pattern);
		return this;
	}

	public AngleMappingList selectImageType(WebDriver driver, String shot, int typeNum, String tc) throws IOException {
		By selectImageTypeLocator = By.xpath("//*[@id='imgtype_" + shot + "']/option[" + typeNum + "]");// 1,2,3
		// //*[@id="imgtype_10019"]/option[2]
		// //*[@id="imgtype_1002"]/option[2]
		try {
			driver.findElement(selectImageTypeLocator).click();
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("\nFirst clicking failed, wait for 60 seconds to click again...");
			Wait(60);
			try {
				driver.findElement(selectImageTypeLocator).click();
			} catch (Exception ee) {
				rwExcel(tc, false, "Add Angle Mapping to select Image Type", "Clicking twice but still failed to select image type.");
				return this;
			}
		}
		return this;

	}

	public String getNoteNameString(WebDriver driver, int num) throws IOException {
		By noteRowLocator = By.xpath("//*[@id=\"importAngleMappingTable\"]/tbody/tr[" + num + "]/td[5]"); // 1,2,3...
		// //*[@id="importAngleMappingTable"]/tbody/tr[3]/td[5]
		String noteS = "";
		try {
			noteS = driver.findElement(noteRowLocator).getText();
		} catch (Exception ex2) {
			noteS = "null";
		}
		return noteS;
	}

}
