package studyPriceDemoUI;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StudyPRICEDemo extends Comlibs {
	private final WebDriver driver;

	public StudyPRICEDemo(WebDriver driver) throws IOException {
		this.driver = driver;
		// String wh1=driver.getWindowHandle();
		String sPageTitle = "StudyPRICE Demo";
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

	By vinBtn = By.xpath("//*[@id=\"App\"]/div/div[2]/div/div/article/div/div[2]/div[1]/div/div/span/label[1]");
	By vehicleInfoBtn = By.xpath("//*[@id=\"App\"]/div/div[2]/div/div/article/div/div[2]/div[1]/div/div/span/label[2]");
	By vinDropListBtn = By
			.xpath("//*[@id=\"App\"]/div/div[2]/div/div/article/div/div[2]/div[2]/div/div/form/div[1]/div[2]/div/div");
	By vinDropArrow = By.xpath(
			"//*[@id=\"App\"]/div/div[2]/div/div/article/div/div[2]/div[2]/div/div/form/div[1]/div[2]/div/div/div[2]/span");

	By vinDropListSize = By.xpath(
			"//*[@id=\"App\"]/div/div[2]/div/div/article/div/div[2]/div[2]/div/div/form/div[1]/div[2]/div/div[2]/div");
	By goActiveBtn = By
			.xpath("//*[@id=\"App\"]/div/div[2]/div/div/article/div/div[2]/div[2]/div/div/form/div[2]/div/input");
	By goInActiveBtn = By
			.xpath("//*[@id=\"App\"]/div/div[2]/div/div/article/div/div[2]/div[2]/div/div/form/div[2]/div/input");
	By searchYMM = By.xpath(
			"//*[@id=\"App\"]/div/div[2]/div/div/article/div/div[2]/div[2]/div/div/div/div/div/div/div[1]/div[1]");
	By searchYMMInput = By
			.xpath("//*[@id=\"App\"]/div/div[2]/div/div/article/div/div[2]/div[2]/div/div/div/div/div/div/div[1]");
	By getVehicleCount = By.xpath("/html/body/div/div/div[2]/div/div/article/div/div[2]/div[2]/div/div/div/div/div");
	By yearArrow = By.xpath("//*[@id=\"react-tabs-9\"]/div[2]/div[1]/div/div[2]/span");
	By yearArrowVehicleList = By.xpath("//*[@id=\"react-tabs-9\"]/div[2]/div[1]/div[2]/div");
//	By xxxxxx 			    = By.xpath("//*[@id=\"react-tabs-9\"]/div[2]/div[1]/div[2]/div[3]");
	By MakeArrow = By.xpath("//*[@id=\"react-tabs-9\"]/div[2]/div[2]/div/div[2]/span");
	By MakeArrowVehicleList = By.xpath("//*[@id=\"react-tabs-9\"]/div[2]/div[2]/div[2]/div");

	By ModelArrow = By.xpath("//*[@id=\"react-tabs-9\"]/div[2]/div[3]/div/div[2]/span");
	By ModelArrowVehicleList = By.xpath("//*[@id=\"react-tabs-9\"]/div[2]/div[3]/div[2]/div");
	By goActiveBtn2 = By
			.xpath("//*[@id=\"App\"]/div/div[2]/div/div/article/div/div[2]/div[2]/div/div/form/div[2]/div/input");

	By TrimArrow = By.xpath("//*[@id=\"react-tabs-9\"]/div[2]/div[4]/div/div[2]/span");
	// //*[@id="react-tabs-1"]/div[2]/div[4]/div/div[2]/span
	By TrimArrowVehicleList = By.xpath("//*[@id=\"react-tabs-9\"]/div[2]/div[4]/div[2]/div");
	// //*[@id="react-tabs-9"]/div[2]/div[4]/div[2]/div[4]
	By safetyActive = By.xpath("//*[@id=\"tabList-tab-Safety - Active\"]");
	By safetyActiveInactive = By.xpath("//*[@id=\"tabList-tab-Safety - Active\"]");

	By safetyPassive = By.xpath("//*[@id=\"tabList-tab-Safety - Passive\"]");
	By safetyPassiveInactive = By.xpath("");

	By theftPrevention = By.xpath("//*[@id=\"tabList-tab-Theft Prevention\"]");
	By theftPreventionInactive = By.xpath("");

	By highValue = By.xpath("//*[@id=\"tabList-tab-High Replacement Cost\"]");
	By highValueInactive = By.xpath("");

	By powertrain = By.xpath("//*[@id=\"tabList-tab-Powertrain and Mechanical\"]");
	By powertrainInactive = By.xpath("");

	By allFeatures = By.xpath("//*[@id=\"tabList-tab-\"]");
	By allFeaturesInactive = By.xpath("");

	By searchField = By.xpath("//*[@id=\"App\"]/div/div[2]/div/div/article/div/div[3]/div/input");
//	By searchBtn = By.xpath("//*[@id=\"App\"]/div/div[2]/div/div/article/div/div[3]/div/button/svg/path");	
	By searchBtn = By.xpath("//*[@id=\"App\"]/div/div[2]/div/div/article/div/div[3]/div/button");
//	By xxxxxx = By.xpath("");	
//	By xxxxxx = By.xpath("");	
//	By xxxxxx = By.xpath("");	
//	By xxxxxx = By.xpath("");	
//	By xxxxxx = By.xpath("");	
//	By xxxxxx = By.xpath("");	
//	By xxxxxx = By.xpath("");	
//	

	public StudyPRICEDemo inputUername(WebDriver driver, String un) {
		driver.findElement(vehicleInfoBtn).clear();
		driver.findElement(vehicleInfoBtn).sendKeys(un);
		return this;
	}

	public StudyPRICEDemo clickVehicleInfoBtn(WebDriver driver, String tc) throws Exception {
		elementExist(driver, vehicleInfoBtn, true, tc);
		driver.findElement(vehicleInfoBtn).click();
		return this;
	}

	public StudyPRICEDemo clickVinBtn(WebDriver driver, String tc) throws Exception {
		elementExist(driver, vinBtn, true, tc);
		driver.findElement(vinBtn).click();
		return this;
	}

	public int getVinCountFromVinDropList(WebDriver driver, String tc) throws Exception {
		int vinC = 0;
		elementExist(driver, vinDropListSize, true, tc);
		vinC = driver.findElements(vinDropListSize).size();
//		vinC=Integer.parseInt(vinCount);
		return vinC;
	}

	public StudyPRICEDemo selectVinFromDropList(WebDriver driver, int num, String tc) throws Exception {
		By vinDropListNum = By.xpath(
				"//*[@id=\"App\"]/div/div[2]/div/div/article/div/div[2]/div[2]/div/div/form/div[1]/div[2]/div/div[2]/div["
						+ num + "]");
		elementExist(driver, vinDropListNum, true, tc);
		driver.findElement(vinDropListNum).click();
		return this;
	}

	public StudyPRICEDemo clickVinArrow(WebDriver driver, String tc) throws Exception {
		elementExist(driver, vinDropArrow, true, tc);
		driver.findElement(vinDropArrow).click();
		return this;
	}

	public StudyPRICEDemo clickGoActiveBtn(WebDriver driver, String tc) throws Exception {
		elementExist(driver, goActiveBtn, true, tc);
		driver.findElement(goActiveBtn).click();
		return this;
	}

	public StudyPRICEDemo verifySearchHintText(WebDriver driver, String hintText, String tc) throws Exception {
		elementExist(driver, searchYMM, true, tc);
		String hintS = driver.findElement(searchYMM).getText();
		if (hintS.equalsIgnoreCase(hintText)) {
			rwExcel(tc, true, "Search hint text", " Match");
		} else {
			rwExcel(tc, false, "Search hint text",
					" Does NOT Match!" + ". Expected is " + hintText + ", site shows " + hintS);
		}
		return this;
	}

	public StudyPRICEDemo inputYMM(WebDriver driver, String ymm, String tc) throws Exception {
//		By searchYMMINput=By.xpath("//*[@id=\"App\"]/div/div[2]/div/div/article/div/div[2]/div[2]/div/div/div");
//		By searchYMMINput=By.xpath("//*[@id=\"App\"]/div/div[2]/div/div/article/div/div[2]/div[2]/div/div/div/div"); // - 1 - no padding col
//		By searchYMMINput=By.xpath("//*[@id=\"App\"]/div/div[2]/div/div/article/div/div[2]/div[2]/div/div/div/div/div"); // - 2
//		By searchYMMINput=By.xpath("//*[@id=\"App\"]/div/div[2]/div/div/article/div/div[2]/div[2]/div/div/div/div/div/div"); // - 3
//		By searchYMMINput=By.xpath("//*[@id=\"App\"]/div/div[2]/div/div/article/div/div[2]/div[2]/div/div/div/div/div/div/div[1]/div[1]"); // - 4
//		By searchYMMINput=By.xpath("//*[@id=\"App\"]/div/div[2]/div/div/article/div/div[2]/div[2]/div/div/div/div/div/div/div[1]/div[2]"); // - 5 - css-lg6gooi
//		By searchYMMINput=By.xpath("//*[@id=\"App\"]/div/div[2]/div/div/article/div/div[2]/div[2]/div/div/div/div/div/div/div[1]/div[2]/div"); // - 6

		// css
//		By searchYMMINput=By.cssSelector("#App > div > div.container > div > div > article > div > div.greyContainer > div:nth-child(2) > div > div > div > div > div"); // - 7  FF searchRow row
//		By searchYMMINput=By.cssSelector("#App > div > div.container > div > div > article > div > div.greyContainer > div:nth-child(2) > div > div > div > div > div > div"); // - 8
//		By searchYMMINput=By.cssSelector("#App > div > div.container > div > div > article > div > div.greyContainer > div:nth-child(2) > div > div > div > div > div > div > div.react-select__value-container.css-1hwfws3"); // - 9
//		By searchYMMINput=By.cssSelector("#App > div > div.container > div > div > article > div > div.greyContainer > div:nth-child(2) > div > div > div > div > div > div > div.react-select__value-container.css-1hwfws3 > div.react-select__placeholder.css-1wa3eu0-placeholder"); // - 10  click ok above
//		By searchYMMINput=By.cssSelector("#App > div > div.container > div > div > article > div > div.greyContainer > div:nth-child(2) > div > div > div > div > div > div > div.react-select__value-container.css-1hwfws3 > div.css-1g6gooi"); // - 11
//		By searchYMMINput=By.cssSelector("#App > div > div.container > div > div > article > div > div.greyContainer > div:nth-child(2) > div > div > div > div > div > div > div.react-select__value-container.css-1hwfws3 > div.css-1g6gooi > div"); // - 12
		By searchYMMINput = By.cssSelector("#react-select-3-input"); // - 13 good for input 2020
//		By searchYMMINput=By.cssSelector("#react-select-3-input"); // - 114
////		By searchYMMINput=By.cssSelector(""); // - 114

		elementExist(driver, searchYMMINput, true, tc);
//		driver.findElement(searchYMMInput).clear();
		driver.findElement(searchYMMINput).sendKeys(ymm);// input ok but no options showing
		Wait(8);
		return this;
	}

//Year
	public int getVehicleCountFromVehicleList(WebDriver driver, String tc) throws Exception {
		int vinC = 0;
		elementExist(driver, getVehicleCount, true, tc);
		vinC = driver.findElements(getVehicleCount).size();
//		vinC=Integer.parseInt(vinCount);
		return vinC;
	}

	public StudyPRICEDemo clickYearArrow(WebDriver driver, String tc) throws Exception {
		elementExist(driver, yearArrow, true, tc);
		driver.findElement(yearArrow).click();
		return this;
	}

	public int getYearCountFromYearList(WebDriver driver, String tc) throws Exception {
		int vinC = 0;
		elementExist(driver, yearArrowVehicleList, true, tc);
		vinC = driver.findElements(yearArrowVehicleList).size();
//		vinC=Integer.parseInt(vinCount);
		return vinC;
	}

	public StudyPRICEDemo selectYearFromYearArrowList(WebDriver driver, int num, String tc) throws Exception {
		By yearArrowVehicleListNum = By.xpath("//*[@id=\"react-tabs-9\"]/div[2]/div[1]/div[2]/div[" + num + "]");
		elementExist(driver, yearArrowVehicleListNum, true, tc);
		driver.findElement(yearArrowVehicleListNum).click();
		return this;
	}

//Make

	public StudyPRICEDemo clickMakeArrow(WebDriver driver, String tc) throws Exception {
		elementExist(driver, MakeArrow, true, tc);
		driver.findElement(MakeArrow).click();
		return this;
	}

	public int getMakeCountFromMakeList(WebDriver driver, String tc) throws Exception {
		int vinC = 0;
		elementExist(driver, MakeArrowVehicleList, true, tc);
		vinC = driver.findElements(MakeArrowVehicleList).size();
//		vinC=Integer.parseInt(vinCount);
		return vinC;
	}

	public StudyPRICEDemo selectMakeFromMakeArrowList(WebDriver driver, int num, String tc) throws Exception {
//		By MakeArrowVehicleListNum = By.xpath("//*[@id=\"react-tabs-9\"]/div[2]/div[1]/div[2]/div["+num+"]");
		By MakeArrowVehicleListNum = By.xpath("//*[@id=\"react-tabs-9\"]/div[2]/div[2]/div[2]/div[" + num + "]");

		//
		elementExist(driver, MakeArrowVehicleListNum, true, tc);
		driver.findElement(MakeArrowVehicleListNum).click();
		return this;
	}

// Model
	public StudyPRICEDemo clickModelArrow(WebDriver driver, String tc) throws Exception {
		elementExist(driver, ModelArrow, true, tc);
		driver.findElement(ModelArrow).click();
		return this;
	}

	public int getModelCountFromModelList(WebDriver driver, String tc) throws Exception {
		int vinC = 0;
		elementExist(driver, ModelArrowVehicleList, true, tc);
		vinC = driver.findElements(ModelArrowVehicleList).size();
//		vinC=Integer.parseInt(vinCount);
		return vinC;
	}

	public StudyPRICEDemo selectModelFromModelArrowList(WebDriver driver, int num, String tc) throws Exception {
		By ModelArrowVehicleListNum = By.xpath("//*[@id=\"react-tabs-9\"]/div[2]/div[3]/div[2]/div[" + num + "]");
		elementExist(driver, ModelArrowVehicleListNum, true, tc);
		driver.findElement(ModelArrowVehicleListNum).click();
		return this;
	}

	// Trim
	public StudyPRICEDemo clickTrimArrow(WebDriver driver, String tc) throws Exception {
		elementExist(driver, TrimArrow, true, tc);
		driver.findElement(TrimArrow).click();
		return this;
	}

	public int getTrimCountFromTrimList(WebDriver driver, String tc) throws Exception {
		int vinC = 0;
		elementExist(driver, TrimArrowVehicleList, true, tc);
		vinC = driver.findElements(TrimArrowVehicleList).size();
//			vinC=Integer.parseInt(vinCount);
		return vinC;
	}

	public StudyPRICEDemo selectTrimFromTrimArrowList(WebDriver driver, int num, String tc) throws Exception {
		By TrimArrowVehicleListNum = By.xpath("//*[@id=\"react-tabs-9\"]/div[2]/div[4]/div[2]/div[" + num + "]");
		elementExist(driver, TrimArrowVehicleListNum, true, tc);
		driver.findElement(TrimArrowVehicleListNum).click();
		return this;
	}

	public StudyPRICEDemo verifyGoActiveBtn(WebDriver driver, String tc) throws Exception {
		elementExist(driver, goActiveBtn, true, tc);
//			driver.findElement(goActiveBtn).click();
		return this;
	}

	public StudyPRICEDemo verifyGoInActiveBtn(WebDriver driver, String tc) throws Exception {
		elementExist(driver, goInActiveBtn, true, tc);

		return this;
	}

	public StudyPRICEDemo clickSafetyActive(WebDriver driver, String tc) throws Exception {
		elementExist(driver, safetyActive, true, tc);
		driver.findElement(safetyActive).click();
		return this;
	}

	public StudyPRICEDemo verifySafetyActiveInactive(WebDriver driver, String tc) throws Exception {
		elementExist(driver, safetyActiveInactive, true, tc);
//			String tableIndex=driver.findElement(safetyActiveInactive).;
//			tableIndex=driver.findElement(safetyActiveInactive).getCssValue("tableindex");
//			
//			tableIndex=driver.findElement(safetyActiveInactive).getAttribute("tableindex");
//			tableIndex=driver.findElement(safetyActiveInactive).getAttribute("tableindex");
//			boolean table=driver.findElement(safetyActiveInactive).isEnabled();
//			table=driver.findElement(safetyActiveInactive).isEnabled();
//			table=driver.findElement(safetyActiveInactive).isSelected();
//			table=driver.findElement(safetyActiveInactive).isSelected();
//			tableIndex=driver.findElement(safetyActiveInactive).;
//			tableIndex=driver.findElement(safetyActiveInactive).getText("");

//			if (!(tableIndex.equalsIgnoreCase("-1"))) {
//				rwExcel(tc, false, "Check SafetyActive button Inactive", "SafetyActive button is not inactive!");
//			}

		return this;
	}

	public StudyPRICEDemo clickSafetyPassive(WebDriver driver, String tc) throws Exception {
		elementExist(driver, safetyPassive, true, tc);
		driver.findElement(safetyPassive).click();
		return this;
	}

	public StudyPRICEDemo clickTheftPrevention(WebDriver driver, String tc) throws Exception {
		elementExist(driver, theftPrevention, true, tc);
		driver.findElement(theftPrevention).click();
		return this;
	}

	public StudyPRICEDemo clickHighValue(WebDriver driver, String tc) throws Exception {
		elementExist(driver, highValue, true, tc);
		driver.findElement(highValue).click();
		return this;
	}

	public StudyPRICEDemo clickPowertrain(WebDriver driver, String tc) throws Exception {
		elementExist(driver, powertrain, true, tc);
		driver.findElement(powertrain).click();
		return this;
	}

	public StudyPRICEDemo clickAllFeatures(WebDriver driver, String tc) throws Exception {
		elementExist(driver, allFeatures, true, tc);
		driver.findElement(allFeatures).click();
		return this;
	}

	public StudyPRICEDemo inpurtSearchField(WebDriver driver, String text, String tc) throws Exception {
		elementExist(driver, searchField, true, tc);
		driver.findElement(searchField).clear();
		driver.findElement(searchField).sendKeys(text);
		return this;
	}

	public StudyPRICEDemo clickSearchBtn(WebDriver driver, String tc) throws Exception {
		elementExist(driver, searchBtn, true, tc);
		driver.findElement(searchBtn).click();
		return this;
	}

}
