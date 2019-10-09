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

public class NewVehiclesAndWhiteList extends Comlibs {
	private final WebDriver driver;

	public NewVehiclesAndWhiteList(WebDriver driver) throws IOException {
		this.driver = driver;
		// String wh1=driver.getWindowHandle();
		String sPageTitle = "New Vehicles";
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

	By searchField = By
			.xpath("/html/body/div[1]/div/div[2]/div/div/div/div/div/div[1]/div[2]/table/tbody/tr[3]/td/div/input");
	// By vehicleCountPreViewLink = By.xpath("//*[@id=\"previousYearBackgrounds\"]");
	By vehicleCountPreViewLink = By
			.xpath("/html/body/div[1]/div/div[2]/div/div/div/div/div/div[2]/div[2]/div/div[1]/div[2]/a");
	// By delerCoiuntPreViewLink = By.xpath("//*[@id=\"previousDealers\"]");

	By xBtn = By.xpath("/html/body/div[4]/div/div[1]/div/div[2]/div/button/span");// /html/body/div[4]/div/div[1]/div/div[2]/div/button
	By popupElements = By.xpath("/html/body/div[4]/div/div[2]/div/div/div");// /html/body/div[4]/div/div[2]/div/div/div[1]/li
//	 By dealersCount = By.xpath("/html/body/div[1]/div/div[2]/div/div/div/div/div/div[2]/div[2]/div/div[5]/div[2]/text()");
	 
	 
	// By xxxxxx = By.xpath("");
	// By xxxxxx = By.xpath("");
	// By xxxxxx = By.xpath("");
	// By xxxxxx = By.xpath("");
	// By xxxxxx = By.xpath("");

	public NewVehiclesAndWhiteList inputSearch(WebDriver driver, String anyText, String tc) throws IOException {
		Wait(1);
		driver.findElement(searchField).clear();
		driver.findElement(searchField).sendKeys(anyText);
		return this;
	}

	public NewVehiclesAndWhiteList clickVehicleUsagePreViewLink(WebDriver driver,int num, String tc) throws IOException {
		By vehicleCountPreViewLink = By.xpath("/html/body/div[1]/div/div[2]/div/div/div/div/div/div[2]/div[2]/div/div[1]/div["+num+"]/a");
		elementExist(driver, vehicleCountPreViewLink, true, tc);
		driver.findElement(vehicleCountPreViewLink).click();
		return new NewVehiclesAndWhiteList(driver);
	}

	public NewVehiclesAndWhiteList clickDealerCountPreViewLink(WebDriver driver,int num, String tc) throws IOException {
		By delerCoiuntPreViewLink = By.xpath("/html/body/div[1]/div/div[2]/div/div/div/div/div/div[2]/div[2]/div/div[5]/div["+num+"]/a");
		elementExist(driver, delerCoiuntPreViewLink, true, tc);
		driver.findElement(delerCoiuntPreViewLink).click();
		return new NewVehiclesAndWhiteList(driver);
	}

	public NewVehiclesAndWhiteList clickXBtn(WebDriver driver, String tc) throws IOException {
		elementExist(driver, xBtn, true, tc);
		driver.findElement(xBtn).click();
		return new NewVehiclesAndWhiteList(driver);
	}
	public int returnNewVehicleRecordsFrPage(WebDriver driver, String tc) throws IOException {
		By recordsCount = By.xpath("/html/body/div[1]/div/div[2]/div/div/div/div/div/div[2]/div[2]/div/div[2]");
		elementExist(driver, recordsCount, true, tc);
		int recordCount=driver.findElements(recordsCount).size();
//		int recordCount=2;
		return recordCount;

	}
	public int returnVehicleUsageFrPage(WebDriver driver,int row, String tc) throws IOException {
		 By vehiclesCount = By.xpath("/html/body/div[1]/div/div[2]/div/div/div/div/div/div[2]/div[2]/div/div[1]/div["+row+"]");
		elementExist(driver, vehiclesCount, true, tc);
		int vehicleCount=Integer.parseInt(driver.findElement(vehiclesCount).getText());
		return vehicleCount;

	}
	public int returnVehiclesFrPopup(WebDriver driver, String tc) throws IOException {
		elementExist(driver, popupElements, true, tc);
		int vehicleSets = driver.findElements(popupElements).size();
		return vehicleSets;

	}
	public int returnDealersFrPage(WebDriver driver,int row, String tc) throws IOException {
		 By dealersCount = By.xpath("/html/body/div[1]/div/div[2]/div/div/div/div/div/div[2]/div[2]/div/div[5]/div["+row+"]");
		elementExist(driver, dealersCount, true, tc);
		int dealerCount=Integer.parseInt(driver.findElement(dealersCount).getText());
		return dealerCount;

	}
	public int returnDealersFrPopup(WebDriver driver, String tc) throws IOException {
		elementExist(driver, popupElements, true, tc);
		int dealerSets = driver.findElements(popupElements).size();
		return dealerSets;

	}
}
