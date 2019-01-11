package AdminPortal;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ManageBackgrounds extends Comlibs {
	private final WebDriver driver;

	public ManageBackgrounds(WebDriver driver) throws IOException {
		this.driver = driver;
		// String wh1=driver.getWindowHandle();
		String sPageTitle = "Manage Backgrounds";
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

	 By backToManageSetsBtnLocator=By.xpath("//*[@id=\"backBtn\"]/span");
//	 By Locator=By.xpath("");
//	 By Locator=By.xpath("");
//	 By Locator=By.xpath("");
//	 By Locator=By.xpath("");
//	 By Locator=By.xpath("");

	public BackgroundSets clickBackToManageSets(WebDriver driver, String tc) throws IOException {
		try {
			driver.findElement(backToManageSetsBtnLocator).click();
			rwExcel(tc, true, "Click on Back To Manage Sets button", "Working fine."); 
		}catch (Exception e) {
			rwExcel(tc, false, "Click on Back To Manage Sets button", "Not Working."); 
		}
			return new BackgroundSets(driver);
		}
//	public ManageBackgrounds clickMapBackGrounds(WebDriver driver, int num) {
////		By mapBackGroundsLocator=By.xpath("(//button[@id='listViewBtn'])[1]");
//		num=num*4-3;
//		By mapBackGroundsLocator=By.xpath("(//button[@id='listViewBtn'])["+num+"]");	
//		driver.findElement(mapBackGroundsLocator).click();
//		return this;
//
//	}
//	public ManageBackgrounds clickEditSet(WebDriver driver) throws IOException {
//		driver.findElement(dealerShipName).click();
//		return this;
//	}

}
