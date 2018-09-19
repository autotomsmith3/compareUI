package AdminPortal;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ImageTypeList extends Comlibs {
	private final WebDriver driver;

	public ImageTypeList(WebDriver driver) throws IOException {
		this.driver = driver;
		// String wh1=driver.getWindowHandle();
		String sPageTitle = "Image Type List";
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

	By addImageTypeBtnLocator=By.xpath("//*[@id='addImageTypeBtn']/span");
	By searchLocator=By.xpath("//*[@id='imageTypeTable_filter']/label/input");
//	By editLocator=By.xpath("(//button[@id='listViewBtn'])["+num+"]");//1,2,3...
//	By deleteLocator=By.xpath("(//button[@id='dealerViewBtn'])["+num+"]");//1,2,3...
	By shotIdentifierLocator=By.xpath("//*[@id='shotIdentifier']");
	By imageGroupLocator=By.xpath("//*[@id='imgGroup']");
	By imageDefinitionLocator=By.xpath("//*[@id='imgDefinition']");
	By imageDescriptionLocator=By.xpath("//*[@id='imgDescription']");
	By defaultSequenceLocator=By.xpath("//*[@id='defaultSequence']");
	By backGroundTypeLocator=By.xpath("//*[@id='backgroundType']");
	By submitBtnLocator=By.xpath("//*[@id='newImageTypeModalBtn']");
	By cancelBtnLocator=By.xpath("//*[@id='newImageTypeModal']/div/div/div[3]/button[2]");


//	 By Locator=By.xpath("");
	public ImageTypeList clickAddImageTypeBtn(WebDriver driver) throws IOException {
		driver.findElement(addImageTypeBtnLocator).click();
		return this;
	}
	public ImageTypeList inputSearch(WebDriver driver, String defaultSequence) throws IOException {
		driver.findElement(searchLocator).sendKeys(defaultSequence);
		return this;
	}
	public ImageTypeList clickEditBtn(WebDriver driver, int num) throws IOException {
		By editLocator=By.xpath("(//button[@id='listViewBtn'])["+num+"]");//1,2,3...
		driver.findElement(editLocator).click();
		return this;
	}

	public ImageTypeList clickDeleteBtn(WebDriver driver, int num) throws IOException {
		By deleteLocator=By.xpath("(//button[@id='dealerViewBtn'])["+num+"]");//1,2,3...
		driver.findElement(deleteLocator).click();
		return this;
	}
	
	
	public ImageTypeList clickCancel(WebDriver driver) throws IOException {
		driver.findElement(cancelBtnLocator).click();
		return this;
	}
	public ImageTypeList clickSubmit(WebDriver driver) throws IOException {
		driver.findElement(submitBtnLocator).click();
		return this;
	}
	
	public ImageTypeList inputShortIdentifier(WebDriver driver, String shortIdentifier) throws IOException {
		Wait(1);
		driver.findElement(shotIdentifierLocator).clear();
		driver.findElement(shotIdentifierLocator).sendKeys(shortIdentifier);
		return this;
	}
	public ImageTypeList inputImageGroup(WebDriver driver, String imageGroup) throws IOException {
		Wait(1);
		driver.findElement(imageGroupLocator).clear();
		driver.findElement(imageGroupLocator).sendKeys(imageGroup);
		return this;
	}
	public ImageTypeList inputImageDefinition(WebDriver driver, String imageDefinition) throws IOException {
		Wait(1);
		driver.findElement(imageDefinitionLocator).clear();
		driver.findElement(imageDefinitionLocator).sendKeys(imageDefinition);
		return this;
	}
	public ImageTypeList inputImageDescription(WebDriver driver, String imageDescription) throws IOException {
		Wait(1);
		driver.findElement(imageDescriptionLocator).clear();
		driver.findElement(imageDescriptionLocator).sendKeys(imageDescription);
		return this;
	}
	public ImageTypeList inputDefaultSequence(WebDriver driver, String defaultSequence) throws IOException {
		Wait(1);
		driver.findElement(defaultSequenceLocator).clear();
		driver.findElement(defaultSequenceLocator).sendKeys(defaultSequence);
		return this;
	}
	public ImageTypeList inputBackGroundType(WebDriver driver, String backGroundType) throws IOException {
		Wait(1);
		driver.findElement(backGroundTypeLocator).clear();
		driver.findElement(backGroundTypeLocator).sendKeys(backGroundType);
		return this;
	}
	public String getDefaultSequenceRowString(WebDriver driver, int num) throws IOException {
		By defaultSequenceRowLocator = By.xpath("//tr[" + num + "]/td"); // 1,2,3...
		String nameS = driver.findElement(defaultSequenceRowLocator).getText();
		return nameS;
	}

}
