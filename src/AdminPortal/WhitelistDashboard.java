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

	By searchField = By.cssSelector(".webix_ss_filter > input:nth-child(1)");
	By pattern = By.xpath("/html/body/div[4]/div/div[2]/div/div[2]/div/div[1]/div/input");
	By notes = By.xpath("/html/body/div[4]/div/div[2]/div/div[3]/div/div[1]/div/input");

	By xBtn = By.xpath("/html/body/div[4]/div/div[1]/div/div[2]/div/button/span");
	By cancelBtn = By.xpath("/html/body/div[4]/div/div[2]/div/div[3]/div/div[2]/div[1]/div/button");

	By saveBtn = By.xpath("/html/body/div[4]/div/div[2]/div/div[3]/div/div[2]/div[2]/div/button");
	//					   /html/body/div[4]/div/div[2]/div/div[3]/div/div[2]/div[2]/div/button
	//			disable	   /html/body/div[4]/div/div[2]/div/div[3]/div/div[2]/div[2]/div[2]
	By saveActiveBtn = By.xpath("/html/body/div[4]/div/div[2]/div/div[3]/div/div[2]/div[2]/div/button");
	//							 /html/body/div[4]/div/div[2]/div/div[3]/div/div[2]/div[2]/div/button

//	By noBtnDel = By.xpath("/html/body/div[4]/div[3]/div[1]/div");//  /html/body/div[4]/div[3]/div[1]/div  - not good
	By noBtnDel = By.cssSelector("body > div.webix_modal_box.webix_confirm > div.webix_popup_controls > div:nth-child(1) > div");
	//							  body > div.webix_modal_box.webix_confirm > div.webix_popup_controls > div:nth-child(1) > div
	By yesBtnDel = By.xpath("/html/body/div[4]/div[3]/div[2]");    // 

	By deselectedAll = By.xpath("/html/body/div[4]/div/div[2]/div/div[2]/div[1]/div/button");
	By selectedAll = By.xpath("/html/body/div[4]/div/div[2]/div/div[2]/div[2]/div/button");
	
	By xxxxxx = By.xpath("");
	By xxxxxdd = By.xpath("");
	By xxxxxd = By.xpath("");
	By xxxxxxd = By.xpath("");

	public WhitelistDashboard inputSearch(WebDriver driver, String searchText, String tc) throws IOException {
		elementExist(driver, searchField, true, tc);
		driver.findElement(searchField).clear();
		driver.findElement(searchField).sendKeys(searchText);
		return this;
	}

	public WhitelistDashboard clickEditIcon(WebDriver driver, int num, String tc) throws IOException {
		By editIcon = By.cssSelector("div.webix_column:nth-child(8) > div:nth-child(" + num + ") > a:nth-child(1)");// 1,2,3...
		//							  div.webix_column:nth-child(8) > div:nth-child(1) > a:nth-child(1)
		//                            div.webix_column:nth-child(8) > div:nth-child(3) > a:nth-child(2)
		elementExist(driver, editIcon, true, tc);
		driver.findElement(editIcon).click(); //
		return this;

	}
	public WhitelistDashboard clickDeleteIcon(WebDriver driver, boolean delete, int num, String tc) throws IOException {
		By editIcon = By.cssSelector("div.webix_column:nth-child(8) > div:nth-child(" + num + ") > a:nth-child(2)");// 1,2,3...
		//                            div.webix_column:nth-child(8) > div:nth-child(3) > a:nth-child(2)
		if (delete) {
			elementExist(driver, editIcon, true, tc);
		}
		driver.findElement(editIcon).click(); //
		return this;

	}

	public WhitelistDashboard selectBackground(WebDriver driver, int row, int col, String tc) throws IOException {
		By background = By.cssSelector(".webix_dataview > div:nth-child(1) > div:nth-child(" + row + ") > div:nth-child(" + col + ") > div:nth-child(1)");
		// .webix_dataview > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1)
									 // .webix_dataview > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1)
		elementExist(driver, background, true, tc);
		driver.findElement(background).click();
		return this;
	}

	public WhitelistDashboard inputPattern(WebDriver driver, String anyText, String tc) throws IOException {
		Wait(1);
		elementExist(driver, pattern, true, tc);
		driver.findElement(pattern).clear();
		driver.findElement(pattern).sendKeys(anyText);
		return this;
	}

	public String getPatternString(WebDriver driver, String tc) throws IOException {
		elementExist(driver, pattern, true, tc);
		String patternS = "";
		try {
			patternS = driver.findElement(pattern).getText();
		} catch (Exception e) {
			rwExcel(tc, false, "Get Pattern from Whitelist Edit entery pop-up",
					"it seems Pattern field showing empty!");
		}

		return patternS;
	}

	public WhitelistDashboard inputNotes(WebDriver driver, String anyText, String tc) throws IOException {
		Wait(1);
		elementExist(driver, notes, true, tc);
		driver.findElement(notes).clear();
		driver.findElement(notes).sendKeys(anyText);
		return this;
	}

	public WhitelistDashboard clickCancelBtn(WebDriver driver, String tc) throws IOException {
		elementExist(driver, cancelBtn, true, tc);
		driver.findElement(cancelBtn).click();
		return this;
	}

	public WhitelistDashboard clickSaveBtn(WebDriver driver, String tc) throws IOException {
		if (elementExist(driver, saveActiveBtn, true, tc)) {
			driver.findElement(saveActiveBtn).click();
		} else {
			rwExcel(tc, false, "Click on Save button", "it seems Save butoon is not active or does not exist.");
		}
		;

		return this;
	}
	public WhitelistDashboard clickXBtn(WebDriver driver, String tc) throws IOException {
		elementExist(driver, xBtn, true, tc);
		driver.findElement(xBtn).click();
		return this;
	}
	public WhitelistDashboard clickNoBtn(WebDriver driver, String tc) throws IOException {
		elementExist(driver, noBtnDel, true, tc);
		driver.findElement(noBtnDel).click();
		return this;
	}
	public WhitelistDashboard clickYesBtn(WebDriver driver, String tc) throws IOException {
		elementExist(driver, yesBtnDel, true, tc);
		driver.findElement(yesBtnDel).click();
		return this;
	}
	public WhitelistDashboard clickDeselectedAllBtn(WebDriver driver, String tc) throws IOException {
		elementExist(driver, deselectedAll, true, tc);
		driver.findElement(deselectedAll).click();
		return this;
	}
	public WhitelistDashboard clickSelectedAllBtn(WebDriver driver, String tc) throws IOException {
		elementExist(driver, selectedAll, true, tc);
		driver.findElement(selectedAll).click();
		return this;
	}
}
