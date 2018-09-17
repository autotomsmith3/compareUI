package AdminPortal;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GlobalConfig extends Comlibs {
	private final WebDriver driver;

	public GlobalConfig(WebDriver driver) throws IOException {
		this.driver = driver;
		// String wh1=driver.getWindowHandle();
		String sPageTitle = "Global Config";
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

	By addGlobalConfigBtnLocator = By.xpath("//*[@id=\"addImageTypeBtn\"]/span");
	By searchLocator = By.xpath("//*[@id=\"configTable_filter\"]/label/input");
	// By editLocator =By.xpath("(//button[@id='editBtn'])[2]");//1,2,3...
	// By deleteLocator =By.xpath("(//button[@id='dealerViewBtn'])[2]");//1,2,3...
	By cancelBtnLocator = By.xpath("//*[@id=\"newImageTypeModal\"]/div/div/div[3]/button[2]");
	By submitBtnLocator = By.xpath("//*[@id=\"newImageTypeModalBtn\"]");
	By keyLocator = By.xpath("//*[@id=\"key\"]");
	By valueLocator = By.xpath("//*[@id=\"value\"]");
	By keyRowLocator = By.xpath("//tr[2]/td");// 1,2,3...

	public GlobalConfig clickAddGlobalConfigBtn(WebDriver driver) throws IOException {
		driver.findElement(addGlobalConfigBtnLocator).click();
		return this;
	}

	public GlobalConfig inputSearch(WebDriver driver, String name) throws IOException {
		Wait(1);
		driver.findElement(searchLocator).sendKeys(name);
		return this;
	}

	public GlobalConfig clickEditBtn(WebDriver driver, int num) throws IOException {
		By editLocator = By.xpath("(//button[@id='editBtn'])[" + num + "]");// 1,2,3...
		driver.findElement(editLocator).click();
		return this;
	}

	public GlobalConfig clickDeleteBtn(WebDriver driver, int num) throws IOException {
		By deleteLocator = By.xpath("(//button[@id='dealerViewBtn'])[" + num + "]");// 1,2,3...
		driver.findElement(deleteLocator).click();
		return this;
	}

	public GlobalConfig clickCancel(WebDriver driver) throws IOException {
		driver.findElement(cancelBtnLocator).click();
		return this;
	}

	public GlobalConfig clickSubmit(WebDriver driver) throws IOException {
		driver.findElement(submitBtnLocator).click();
		return this;
	}

	public GlobalConfig inputKey(WebDriver driver, String key) throws IOException {
		Wait(1);
		driver.findElement(keyLocator).clear();
		driver.findElement(keyLocator).sendKeys(key);
		return this;
	}
	public boolean checkKeyFieldLocked(WebDriver driver, String tc) throws IOException {
		Wait(1);
		boolean keyFieldEnable=driver.findElement(keyLocator).isEnabled();
		return keyFieldEnable;
	}

	public GlobalConfig inputValue(WebDriver driver, String value) throws IOException {
		driver.findElement(valueLocator).clear();
		driver.findElement(valueLocator).sendKeys(value);
		return this;
	}

	public String getKeyString(WebDriver driver, int num) throws IOException {
		By keyRowLocator = By.xpath("//tr[" + num + "]/td"); // 1,2,3...
		String keyString = driver.findElement(keyRowLocator).getText();
		return keyString;
	}

}
