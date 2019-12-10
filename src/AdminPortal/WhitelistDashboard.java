package AdminPortal;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WhitelistDashboard extends Comlibs {
	private final WebDriver driver;

	public WhitelistDashboard(WebDriver driver) throws IOException {
		this.driver = driver;
		// String wh1=driver.getWindowHandle();
		String sPageTitle = "Whitelist Dashboard";
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
	By searchField = By.cssSelector(".webix_ss_filter > input:nth-child(1)");


	public WhitelistDashboard inputSearch(WebDriver driver, String searchText, String tc) throws IOException {
		elementExist(driver, searchField, true, tc);
		driver.findElement(searchField).sendKeys(searchText);
		return this;
	}

	public WhitelistDashboard clickDeleteIcon(WebDriver driver, int num, String tc) throws IOException {
		By deleteIcon = By.cssSelector("div.webix_column:nth-child(9) > div:nth-child("+num+")");
		elementExist(driver, deleteIcon, true, tc);
//		driver.findElement(deleteIcon).click();  //Need to find a way to add record then this can be used
		return this;

	}

	public WhitelistDashboard clickEditSet(WebDriver driver) throws IOException {
		driver.findElement(dealerShipName).click();
		return this;
	}


}
