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

	By dealerShipName = By.cssSelector("span");

//	 By Locator=By.xpath("");
//	 By Locator=By.xpath("");
//	 By Locator=By.xpath("");
//	 By Locator=By.xpath("");
//	 By Locator=By.xpath("");
//	 By Locator=By.xpath("");

	public AngleMappingList clickCreateNewSet(WebDriver driver) throws IOException {
			driver.findElement(dealerShipName).click();
			return this;
		}
	public AngleMappingList clickMapBackGrounds(WebDriver driver, int num) {
//		By mapBackGroundsLocator=By.xpath("(//button[@id='listViewBtn'])[1]");
		num=num*4-3;
		By mapBackGroundsLocator=By.xpath("(//button[@id='listViewBtn'])["+num+"]");	
		driver.findElement(mapBackGroundsLocator).click();
		return this;

	}
	public AngleMappingList clickEditSet(WebDriver driver) throws IOException {
		driver.findElement(dealerShipName).click();
		return this;
	}

}
