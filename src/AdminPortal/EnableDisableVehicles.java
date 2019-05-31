package AdminPortal;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EnableDisableVehicles extends Comlibs {
	private final WebDriver driver;

	public EnableDisableVehicles(WebDriver driver) throws IOException {
		this.driver = driver;
		// String wh1=driver.getWindowHandle();
		String sPageTitle = "Enable / Disable Vehicles";
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

	By enableDisableAll = By.xpath("//*[@id=\"addImageTypeBtn\"]/span");
	By disabledVehicles = By.xpath("//*[@id=\"configTable_filter\"]/label/input");
	By searchField = By.xpath("//*[@id=\"configTable_filter\"]/label/input");
	By disableCheckBox = By.xpath("//*[@id=\"newImageTypeModalBtn\"]");

	public EnableDisableVehicles inputSearch(WebDriver driver, String patternRegex, String tc) throws IOException {
		Wait(1);
		driver.findElement(searchField).sendKeys(patternRegex);
		return this;
	}

	public EnableDisableVehicles clickDisabledCheckBox(WebDriver driver, String patternRegex, String tc)
			throws IOException {
		// By editLocator = By.xpath("(//button[@id='editBtn'])[" + num + "]");
		By checkboxLocator = By.xpath("//*[@id=\"disable" + patternRegex + "\"]");
		try {
			driver.findElement(checkboxLocator).click();
			rwExcel(tc, true, "Enable/Disable Vehicles - Disable Check Box", "Clicking on Disable Check Box.");
		} catch (Exception e) {
			rwExcel(tc, false, "Enable/Disable Vehicles - Disable Check Box", "Clicking on Disable Check Box.");
		}
		return this;
	}

}
