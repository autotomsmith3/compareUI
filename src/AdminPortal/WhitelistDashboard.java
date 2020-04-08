package AdminPortal;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

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
	// /html/body/div[4]/div/div[2]/div/div[3]/div/div[2]/div[2]/div/button
	// disable /html/body/div[4]/div/div[2]/div/div[3]/div/div[2]/div[2]/div[2]
	By saveActiveBtn = By.xpath("/html/body/div[4]/div/div[2]/div/div[3]/div/div[2]/div[2]/div/button");
	// /html/body/div[4]/div/div[2]/div/div[3]/div/div[2]/div[2]/div
	By saveInactiveBtn = By.xpath("/html/body/div[4]/div/div[2]/div/div[3]/div/div[2]/div[2]/div[2]");// disabled
	// /html/body/div[4]/div/div[2]/div/div[3]/div/div[2]/div[2]/div/button

//	By noBtnDel = By.xpath("/html/body/div[4]/div[3]/div[1]/div");//  /html/body/div[4]/div[3]/div[1]/div  - not good
	By noBtnDel = By.cssSelector(
			"body > div.webix_modal_box.webix_confirm > div.webix_popup_controls > div:nth-child(1) > div");
	// body > div.webix_modal_box.webix_confirm > div.webix_popup_controls >
	// div:nth-child(1) > div
	By yesBtnDel = By.xpath("/html/body/div[4]/div[3]/div[2]"); //

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
//		By editIcon = By.cssSelector("div.webix_column:nth-child(8) > div:nth-child("+num+") > a:nth-child(1)");// 1,2,3... FF works fine but Chrome
		By editIcon = By.xpath(
				"/html/body/div[1]/div/div[2]/div/div/div/div/div/div[2]/div[2]/div/div[8]/div[" + num + "]/a[1]");// 1,2,3... works?Yes and FF as well, but need to make it visible, copy from Chrome full xpath
//		By editIcon = By.xpath("/html/body/div[1]/div/div[2]/div/div/div/div/div/div[2]/div[2]/div/div[8]/div["+num+"]/a[1]");// 1,2,3... works?, copy from Chrome full xpath
		if (!elementExist(driver, editIcon, false, tc)) {
			for (int i = 1; i < 10; i++) {
				Wait(2);
				System.out.println("\nFailed to identify element!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
				if (elementExist(driver, editIcon, false, tc)) {
					break;
				}
			}
		};
		elementExist(driver, editIcon, true, tc);
		driver.findElement(editIcon).click(); //
		return this;

	}

	public WhitelistDashboard clickDeleteIcon(WebDriver driver, boolean delete, int num, String tc) throws IOException {
//		By editIcon = By.cssSelector("div.webix_column:nth-child(8) > div:nth-child(" + num + ") > a:nth-child(2)");// 1,2,3... after moved window to right it wouldn't work.
		By editIcon = By.xpath(
				"/html/body/div[1]/div/div[2]/div/div/div/div/div/div[2]/div[2]/div/div[8]/div[" + num + "]/a[2]");// 1,2,3...copy full xpath
		// div.webix_column:nth-child(8) > div:nth-child(3) > a:nth-child(2)
		// /html/body/div[1]/div/div[2]/div/div/div/div/div/div[2]/div[2]/div/div[7]/div[2]/a[2] -- 2
		// "/html/body/div[1]/div/div[2]/div/div/div/div/div/div[2]/div[2]/div/div[7]/div["+num+"]/a[2]"

		if (delete) {
			elementExist(driver, editIcon, true, tc);
		}
		driver.findElement(editIcon).click(); //
		return this;

	}

	public WhitelistDashboard selectBackground(WebDriver driver, int row, int col, String tc) throws IOException {
		By background = By.cssSelector(".webix_dataview > div:nth-child(1) > div:nth-child(" + row
				+ ") > div:nth-child(" + col + ") > div:nth-child(1)");

		if (!elementExist(driver, background, false, tc)) {
			for (int i = 1; i < 10; i++) {
				Wait(10);
				if (elementExist(driver, background, false, tc)) {
					break;
				}
			}
		}
		;
		elementExist(driver, background, true, tc);
		driver.findElement(background).click();
		return this;
	}

	public WhitelistDashboard selectBackgrounds(WebDriver driver, int row1, int col1, int row2, int col2, String tc)
			throws IOException {
		By background1 = By.cssSelector(".webix_dataview > div:nth-child(1) > div:nth-child(" + row1
				+ ") > div:nth-child(" + col1 + ") > div:nth-child(1)");
		By background2 = By.cssSelector(".webix_dataview > div:nth-child(1) > div:nth-child(" + row2
				+ ") > div:nth-child(" + col2 + ") > div:nth-child(1)");
		// selected bg 1
		elementExist(driver, background1, true, tc);
		boolean selected = driver.findElement(background1).isSelected();
		System.out.println("Selected = " + selected);
		driver.findElement(background1).click();
		selected = driver.findElement(background1).isSelected();
		System.out.println("Selected Row =" + row1 + ", col =" + col1 + ".   " + selected);

		// selected bg 2
		elementExist(driver, background2, true, tc);
//		selected=driver.findElement(background2).isSelected();
//		System.out.println("Selected = "+selected);
		driver.findElement(background2).click();
//		selected=driver.findElement(background1).isSelected();
//		System.out.println("Selected Row ="+row2+", col ="+col2+".   "+selected);

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
		if (!elementExist(driver, cancelBtn, false, tc)) {
			for (int i = 1; i < 10; i++) {
				Wait(10);
				if (elementExist(driver, cancelBtn, false, tc)) {
					break;
				}
			}
		}
		;
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

	public boolean checkInactiveSaveBtn(WebDriver driver, String tc) throws IOException {

		boolean saveBth = elementExist(driver, saveInactiveBtn, false, tc);
//				
//				
//				
//		if (elementExist(driver, saveInactiveBtn, true, tc)) {
//			driver.findElement(saveInactiveBtn).click();
//		} else {
//			rwExcel(tc, false, "Click on Save button", "it seems Save butoon is not active or does not exist.");
//		}
//		;

		return saveBth;
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

	public WhitelistDashboard clickYesOfAlertBox(WebDriver driver, String tc) throws IOException {
		driver.switchTo().alert().accept();
		return this;
	}

}
