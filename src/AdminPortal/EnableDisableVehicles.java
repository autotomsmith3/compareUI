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

	public EnableDisableVehicles inputSearch(WebDriver driver, String regexPattern, String tc) throws IOException {
		Wait(1);
		driver.findElement(searchField).clear();
		driver.findElement(searchField).sendKeys(regexPattern);
		return this;
	}

	public boolean CheckDisabledCheckBoxStatus(WebDriver driver, String regexPattern, String tc)
			throws IOException {
		By checkboxLocator = By.xpath("//*[@id=\"disable" + regexPattern + "\"]");
		boolean selected=false;
		try {
			selected=driver.findElement(checkboxLocator).isSelected();//works
		} catch (Exception e) {
			rwExcel(tc, false, "Enable/Disable Vehicles - Disable Check Box", "Check on Disable Check Box status.");
		}
		return selected;
	}
	public EnableDisableVehicles clickDisabledCheckBox(WebDriver driver, String regexPattern, String tc)
			throws IOException {
		// By editLocator = By.xpath("(//button[@id='editBtn'])[" + num + "]");
		By checkboxLocator = By.xpath("//*[@id=\"disable" + regexPattern + "\"]");
		boolean selected=false;
		try {
			selected=driver.findElement(checkboxLocator).isSelected();//works
			if (selected) {
				driver.findElement(checkboxLocator).click();
				rwExcel(tc, "Enable/Disable Vehicles - Disable Check Box", "Check Box is checked. Need to uncheck and try again.");
			}else {
				driver.findElement(checkboxLocator).click();
			}
//			rwExcel(tc, true, "Enable/Disable Vehicles - Disable Check Box", "Clicking on Disable Check Box.");
		} catch (Exception e) {
			rwExcel(tc, false, "Enable/Disable Vehicles - Disable Check Box", "Clicking on Disable Check Box.");
		}
		return this;
	}
}
