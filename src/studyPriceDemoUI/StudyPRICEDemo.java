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

	By dealerShipName = By.cssSelector("span");

	public StudyPRICEDemo inputUername(WebDriver driver, String un) {
		driver.findElement(dealerShipName).clear();
		driver.findElement(dealerShipName).sendKeys(un);
		return this;
	}

	
	
}
