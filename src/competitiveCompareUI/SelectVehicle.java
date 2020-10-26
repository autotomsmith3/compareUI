package competitiveCompareUI;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SelectVehicle extends Comlibs {
	private final WebDriver driver;

	public SelectVehicle(WebDriver driver) throws IOException {
		this.driver = driver;
		// String wh1=driver.getWindowHandle();
		String sPageTitle = "Select Vehicle";
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

//  Select vehicles:
	By vehicle01 = By.xpath("/html/body/div[1]/div[3]/div[1]/div[1]/div[1]/div/div[3]/div/div[1]/div/div[1]/img");
	By vehicle02 = By.xpath("/html/body/div[1]/div[3]/div[1]/div[1]/div[1]/div/div[3]/div/div[2]/div/div[1]/img");
	By vehicle03 = By.xpath("/html/body/div[1]/div[3]/div[1]/div[1]/div[1]/div/div[3]/div/div[3]/div/div[1]/img");

	By vehicle04 = By.xpath("/html/body/div[1]/div[3]/div[1]/div[2]/div[1]/div/div[3]/div/div[1]/div/div[1]/img");

//	Select trims:
	By trim01 = By.xpath("//*[@id=\"vehicle-select-radio\"]");// //*[@id="vehicle-select-radio"]
	By trim02 = By.xpath("//*[@id=\"vehicle-select-radio\"]");// //*[@id="vehicle-select-radio"]
	By trim03 = By.xpath("");
	By trim04 = By.xpath("");
//	By xxx = By.xpath("");
//	By xxx = By.xpath("");

//	By xxx = By.xpath("");
//	By xxx = By.xpath("");

	public SelectVehicle clickOnVehicle(WebDriver driver, int vehicleTypeNumber, int vehicleNum, String tc)
			throws Exception {
		By vehicle01 = By.xpath("/html/body/div[1]/div[3]/div[1]/div[" + vehicleTypeNumber
				+ "]/div[1]/div/div[3]/div/div[" + vehicleNum + "]/div/div[1]/img");

		By vehicle02 = By.xpath("/html/body/div[1]/div[3]/div[1]/div[1]/div[1]/div/div[3]/div/div[2]/div/div[1]/img");
		By vehicle03 = By.xpath("/html/body/div[1]/div[3]/div[1]/div[1]/div[1]/div/div[3]/div/div[3]/div/div[1]/img");

		By vehicle04 = By.xpath("/html/body/div[1]/div[3]/div[1]/div[2]/div[1]/div/div[3]/div/div[1]/div/div[1]/img");

		elementExist(driver, vehicle01, true, tc);
		driver.findElement(vehicle01).click();
		return this;
	}

	public void clickOnGotIt(WebDriver driver2, String tc) throws Exception {
		By GOTIT = By.xpath("/html/body/div[1]/div/div/div[2]/button");
		elementExist(driver, GOTIT, true, tc);
		driver.findElement(GOTIT).click();

	}

	public Compare clickOnTrim(WebDriver driver, String un, String tc) throws Exception {
		By trim01 = By.xpath("//*[@id=\"vehicle-select-radio\"]");
		elementExist(driver, trim01, true, tc);
		driver.findElement(trim01).click();
		Wait(5);
//		if (un.contains("Mitsubishi")) {
//			driver.switchTo().alert().dismiss();
//		}
		return new Compare(driver);
	}

	public void selectYear(WebDriver driver, String year, String tc) throws Exception {

		String y = "";
		By allYears = By.xpath("/html/body/div[1]/div[2]/div/nav/div/div/div[2]/ul/li");
		By curretTestYear = By.xpath("/html/body/div[1]/div[2]/div/nav/div/div/div[2]/ul/li[1]/button");
		elementExist(driver, allYears, true, tc);
		int totalYears = driver.findElements(allYears).size();

		for (int i = 1; i <= totalYears - 2; i++) {
			curretTestYear = By.xpath("/html/body/div[1]/div[2]/div/nav/div/div/div[2]/ul/li[" + i + "]/button");
			y = driver.findElement(curretTestYear).getText();
			if (y.equalsIgnoreCase(year)) {
				curretTestYear = By.xpath("/html/body/div[1]/div[2]/div/nav/div/div/div[2]/ul/li[" + i + "]/button");
				System.out.println("Year = " + y + " \n");
				break;
			} else if (i == (totalYears - 2)) {
				curretTestYear = null;
			}
		}

		if (curretTestYear == null) {
			System.out.println("Year = " + year + " cannot be found!\n");
			rwExcel(tc, "Select Year = " + year + "", year + " cannot be found in the site");
		} else {
			driver.findElement(curretTestYear).click();
			Wait(2);
		}

	}

}
