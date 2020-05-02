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
	By vinDropListBtn = By.xpath("//*[@id=\"App\"]/div/div[2]/div/div/article/div/div[2]/div[2]/div/div/form/div[1]/div[2]/div/div");
	By vinDropArrow = By.xpath("//*[@id=\"App\"]/div/div[2]/div/div/article/div/div[2]/div[2]/div/div/form/div[1]/div[2]/div/div/div[2]/span");

	
	By vinDropListSize = By.xpath("//*[@id=\"App\"]/div/div[2]/div/div/article/div/div[2]/div[2]/div/div/form/div[1]/div[2]/div/div[2]/div");
	By goActiveBtn = By.xpath("//*[@id=\"App\"]/div/div[2]/div/div/article/div/div[2]/div[2]/div/div/form/div[2]/div/input");
	By goInActiveBtn = By.xpath("//*[@id=\"App\"]/div/div[2]/div/div/article/div/div[2]/div[2]/div/div/form/div[2]/div/input");
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
	public int  getVinCountFromVinDropList(WebDriver driver, String tc) throws Exception {
		int vinC=0;
		elementExist(driver, vinDropListSize, true, tc);
		vinC=driver.findElements(vinDropListSize).size();
//		vinC=Integer.parseInt(vinCount);
		return vinC;
	}
	public StudyPRICEDemo selectVinFromDropList(WebDriver driver, int num, String tc) throws Exception {
		By vinDropListNum = By.xpath("//*[@id=\"App\"]/div/div[2]/div/div/article/div/div[2]/div[2]/div/div/form/div[1]/div[2]/div/div[2]/div["+num+"]");
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
	
	public StudyPRICEDemo verifyGoInActiveBtn(WebDriver driver, String tc) throws Exception {
		elementExist(driver, goInActiveBtn, true, tc);

		return this;
	}
	
}
