package compareUI;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Vin2VinCompare extends Comlibs {
	private final WebDriver driver;

	public Vin2VinCompare(WebDriver driver) throws IOException {
		this.driver = driver;
		// String wh1=driver.getWindowHandle();
		String sPageTitle = "VIN 2 VIN Compare";
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

	By vinBtn = By.xpath("/html/body/div[1]/form/div/div[1]/div[1]/h4/a");
	By vehicleInfoBtn = By.xpath("//*[@id=\"App\"]/div/div[2]/div/div/article/div/div[2]/div[1]/div/div/span/label[2]");
	By vinDropListBtn = By
			.xpath("//*[@id=\"App\"]/div/div[2]/div/div/article/div/div[2]/div[2]/div/div/form/div[1]/div[2]/div/div");

	By ConfigurableParameters = By.xpath("/html/body/div[1]/form/div/div[1]/div[1]/h4/a");	
	By xxxxxx = By.xpath("/html/body/div[1]/form/div/div[1]/div[1]/h4/a");	
//	By xxxxxx = By.xpath("");	
//	By xxxxxx = By.xpath("");	
//	By xxxxxx = By.xpath("");	
//	By xxxxxx = By.xpath("");	
//	By xxxxxx = By.xpath("");	
//	
	
	public Vin2VinCompare clickConfigurealeParameters(WebDriver driver, String tc) throws Exception {
		elementExist(driver, ConfigurableParameters, true, tc);
		driver.findElement(ConfigurableParameters).click();
		return this;
	}

	public Vin2VinCompare inputUername(WebDriver driver, String un) {
		driver.findElement(vehicleInfoBtn).clear();
		driver.findElement(vehicleInfoBtn).sendKeys(un);
		return this;
	}



	public Vin2VinCompare clickVinBtn(WebDriver driver, String tc) throws Exception {
		elementExist(driver, vinBtn, true, tc);
		driver.findElement(vinBtn).click();
		return this;
	}


}
