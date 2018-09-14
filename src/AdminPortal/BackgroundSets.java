package AdminPortal;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BackgroundSets extends Comlibs {
	private final WebDriver driver;

	public BackgroundSets(WebDriver driver) throws IOException {
		this.driver = driver;
		// String wh1=driver.getWindowHandle();
		String sPageTitle = "Background Sets";
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


	 By creatNewSetBtnLocator=By.xpath("//*[@id=\"creatNewSetBtn\"]");
	 By mapBackGroundsLocator1=By.xpath("(//button[@id='listViewBtn'])[1]"); ////*[@id="listViewBtn"]/span
	 By mapBackGroundsLocator2=By.xpath("(//button[@id='listViewBtn'])[5]");//2rd //*[@id="listViewBtn"]/span
	 By mapBackGroundsLocator3=By.xpath("xpath=(//button[@id='listViewBtn'])[9]");//3rd
	 By mapBackGroundsLocator4=By.xpath("xpath=(//button[@id='listViewBtn'])[13]");//4thd, there is a 4 btw set.
	 By editsetBtnLocator1=By.xpath("(//button[@id='listViewBtn'])[2]");
	 By editsetBtnLocator2=By.xpath("(//button[@id='listViewBtn'])[6]");
	 By editsetBtnLocator3=By.xpath("(//button[@id='listViewBtn'])[10]");
	 By editsetBtnLocator4=By.xpath("(//button[@id='listViewBtn'])[14]");
	 By manageBGsetBtnLocator1=By.xpath("(//button[@id='listViewBtn'])[3]");
	 By manageBGsetBtnLocator2=By.xpath("(//button[@id='listViewBtn'])[7]");
	 By manageBGsetBtnLocator3=By.xpath("(//button[@id='listViewBtn'])[11]");
	 By manageBGsetBtnLocator4=By.xpath("(//button[@id='listViewBtn'])[15]");
	 By dealersUseBGLocator1=By.xpath("(//button[@id='listViewBtn'])[4]");
	 By dealersUseBGLocator2=By.xpath("(//button[@id='listViewBtn'])[8]");
	 By dealersUseBGLocator3=By.xpath("(//button[@id='listViewBtn'])[12]");
	 By dealersUseBGLocator4=By.xpath("(//button[@id='listViewBtn'])[16]");
	 By deleteBGsetLocator1=By.xpath("(//button[@id='dealerViewBtn'])[1]");
	 By deleteBGsetLocator2=By.xpath("(//button[@id='dealerViewBtn'])[2]");
	 By deleteBGsetLocator3=By.xpath("(//button[@id='dealerViewBtn'])[3]");
	 By deleteBGsetLocator4=By.xpath("(//button[@id='dealerViewBtn'])[4]");
//	 By Locator=By.xpath("");
//	 By Locator=By.xpath("");
//	 By Locator=By.xpath("");
//	 By Locator=By.xpath("");
//	 By Locator=By.xpath("");
//	 By Locator=By.xpath("");

	public BackgroundSets clickCreateNewSet(WebDriver driver) throws IOException {
			driver.findElement(creatNewSetBtnLocator).click();
			return this;
		}
	public Backgrounds clickMapBackGrounds(WebDriver driver, int num) throws IOException {
//		By mapBackGroundsLocator=By.xpath("(//button[@id='listViewBtn'])[1]");//[1],[5],[9]....4 btw.
		num=(num-1)*4+1;//1,5,9
		By mapBackGroundsLocator=By.xpath("(//button[@id='listViewBtn'])["+num+"]");	
		System.out.println("Please wait at least 2 minutes untill Backgrounds page showing......");
		driver.findElement(mapBackGroundsLocator).click();
		return new Backgrounds(driver);
	}
	public BackgroundSets clickEditSet(WebDriver driver) throws IOException {
		driver.findElement(editsetBtnLocator1).click();
		return this;
	}

}
