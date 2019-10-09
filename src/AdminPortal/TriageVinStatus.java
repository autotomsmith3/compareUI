//package AdminPortal;
//
//public class NewVehiclesAndWhiteList {
//
//}

package AdminPortal;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TriageVinStatus extends Comlibs {
	private final WebDriver driver;

	public TriageVinStatus(WebDriver driver) throws IOException {
		this.driver = driver;
		// String wh1=driver.getWindowHandle();
		String sPageTitle = "Triage Vin Status";
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

	By searchVinField = By.xpath("//*[@id=\"searchVin\"]");
	 By submit = By.xpath("//*[@id=\"buttonSubmit\"]");
	 By resultBox = By.xpath("//*[@id=\"vinData\"]");
	 By triageVinStatus = By.xpath("//*[@id=\"vinStatus\"]/span");
	// By xxxxxx = By.xpath("");
	// By xxxxxx = By.xpath("");

	public TriageVinStatus inputSearch(WebDriver driver, String anyText, String tc) throws IOException {
		Wait(1);
		elementExist(driver, searchVinField, true, tc);
		driver.findElement(searchVinField).clear();
		driver.findElement(searchVinField).sendKeys(anyText);
		return this;
	}

	public TriageVinStatus clickSubmit(WebDriver driver, String tc) throws IOException {
		elementExist(driver, submit, true, tc);
		driver.findElement(submit).click();
		return new TriageVinStatus(driver);
	}

	public String retrieveResoltData(WebDriver driver,String tc) throws IOException {
		elementExist(driver, resultBox, true, tc);
		String dataText=driver.findElement(resultBox).getText();
		return dataText;
	}

	public TriageVinStatus clickTriageVinStatus(WebDriver driver, String tc) throws IOException {
		elementExist(driver, triageVinStatus, true, tc);
		driver.findElement(triageVinStatus).click();
		return new TriageVinStatus(driver);
	}
}
