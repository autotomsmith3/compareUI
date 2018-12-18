package AdminPortal;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.URL;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.util.PDFTextStripper;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UserList extends Comlibs {
	private final WebDriver driver;
	int lineNum;

	public UserList(WebDriver driver) throws IOException {
		this.driver = driver;
		// String wh1=driver.getWindowHandle();
		String sPageTitle = "User List";
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

	By acceptBtnLocator = By.xpath("//button[@type='submit']");
	// By editBtnLocator = By.xpath("//*[@id=\"listViewBtn\"]/span"); // //*[@id="listViewBtn"]/span or xpath=(//button[@id='listViewBtn'])[3] 
	By agreementTitles = By.xpath("//*[@id=\"tabs\"]/li");
	By firstAgreementLocator = By.xpath("//*[@id=\"tabs\"]/li[1]/a");
	By secondAgreementLocator = By.xpath("//*[@id=\"tabs\"]/li[2]/a");
	By thirdAgreementLocator = By.xpath("//*[@id=\"tabs\"]/li[3]/a");
	By pdfURLLocator = By.xpath("//*[@id=\"stockpx\"]/iframe");
	By addDealerShipBtnLocator = By.xpath("//*[@id=\"addDealershipBtn\"]");
	By searchLocator = By.xpath("//input[@type='search']");
	By ManageAccountsLocator = By.xpath("//*[@id=\"accountMenu\"]");
	By ManageDealerShipsLocator = By.xpath("//*[@id=\"dealershipMenu\"]");
	By ManageBGSetsLocator = By.xpath("//*[@id=\"backgroundMenu\"]");
	By ManageImageTypesLocator = By.xpath("//*[@id=\"imagetypeMenu\"]");// Firefox: //*[@id="imagetypeMenu"]
	By ManageAngleMappingsLocator = By.xpath("//*[@id=\"importanglemappingMenu\"]");
	By ManageExportTemplatesLocator = By.xpath("//*[@id=\"exportTemplateMenu\"]");
	By ManageGlobalConfigLocator = By.xpath("//*[@id=\"configMenu\"]");
	By AddAccountBtnLocator = By.xpath("//*[@id=\"addAccountButton\"]/span");
	 By accountsLocator=By.xpath("//table[@id='dealerTable']/tbody");//xpath=//table[@id='dealerTable']/tbody
	 By nextLocator=By.xpath("//*[@id=\"dealerTable_next\"]");
	 By firstNameSortLocator=By.xpath("//*[@id=\"dealerTable\"]/thead/tr/th[4]");
	// By Locator=By.xpath("");
	// By Locator=By.xpath("");
	// By Locator=By.xpath("");
	// By Locator=
	// By Locator=

	public DealerProfile clickEditBtnOld(WebDriver driver, String num) throws IOException {
		// By editBtnLocator = By.xpath("//*[@id=\"listViewBtn\"]/span"); // //*[@id="listViewBtn"]/span or xpath=(//button[@id='listViewBtn'])[3]
		// By editBtnLocator = By.xpath("(//button[@id='listViewBtn'])[3]"); //ok //*[@id="listViewBtn"]/span or xpath=(//button[@id='listViewBtn'])[3]
		By editBtnLocator = By.xpath("(//button[@id='listViewBtn'])[" + num + "]");
		try {
			driver.findElement(editBtnLocator).click();
		} catch (Exception e) {
			checkEditBtnLocationAndScroll(driver, num);
			driver.findElement(editBtnLocator).click();
		}
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
		}
		return new DealerProfile(driver);
	}

	public AccountProfile clickEditBtn(WebDriver driver, String num) throws IOException {
		By editBtnLocator = By.xpath("(//button[@id='accountViewBtn'])[" + num + "]");// 1,2,3
		try {
			driver.findElement(editBtnLocator).click();
		} catch (Exception e) {
			driver.findElement(editBtnLocator).click();
		}
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
		}
		return new AccountProfile(driver);
	}

	public UserList checkEditBtnLocationAndScroll(WebDriver driver, String num) throws IOException {
		num = Integer.toString(Integer.parseInt(num) + 3);// Integer.parseInt(MaxVins)
		By editBtnLocator = By.xpath("(//button[@id='listViewBtn'])[" + num + "]");
		// boolean numShowInPage = elementExist(driver, editBtnLocator, true, "tc");
		boolean numShowInPage = driver.findElement(editBtnLocator).isDisplayed();
		if (numShowInPage) {
			scrollUp(driver, 100, "ddd");
		} else {
			scrollUp(driver, 80, "ddd");
		}
		return this;
	}

	public UserList clickDisplayDropDownBtn(WebDriver driver, String num) throws IOException {
		By displayDropDownLocator = By.xpath("//*[@id=\"dealerTable_length\"]/label/select/option[" + num + "]");
		driver.findElement(displayDropDownLocator).click();
		return this;
	}
	public UserList clickDisplay(WebDriver driver,int num) throws IOException {
		By DisplayDropDown = By.xpath("	//*[@id=\"dealerTable_length\"]/label/select/option["+num+"]");//1,2,3... 		//*[@id="dealerTable_length"]/label/select/option[3]
		driver.findElement(DisplayDropDown).click();
		return this;
	}
	public DealerProfile clickAcceptPSBtn(WebDriver driver) throws IOException {
		driver.findElement(acceptBtnLocator).click();
		return new DealerProfile(driver);
	}

	public void clickFirstAgreementTab(WebDriver driver, String tc) throws IOException {
		boolean elementExist = elementExist(driver, firstAgreementLocator, true, tc);
		if (elementExist) {
			driver.findElement(firstAgreementLocator).click();
		}
	}

	public void clickSecondAgreementTab(WebDriver driver, String tc) throws IOException {
		boolean elementExist = elementExist(driver, secondAgreementLocator, true, tc);
		if (elementExist) {
			driver.findElement(secondAgreementLocator).click();
		}
	}

	public String getPdfURL(WebDriver driver, String px, String tc) throws IOException {
		By pdfURLLocator = By.xpath("//*[@id=\"" + px + "\"]/iframe");
		String pdfURL = "";
		boolean elementExist = elementExist(driver, pdfURLLocator, true, tc);
		if (elementExist) {
			pdfURL = driver.findElement(pdfURLLocator).getAttribute("src");
		}
		return pdfURL;
	}

	public void clickThirdAgreementTab(WebDriver driver, String tc) throws IOException {
		boolean elementExist = elementExist(driver, thirdAgreementLocator, true, tc);
		if (elementExist) {
			driver.findElement(thirdAgreementLocator).click();
		}
	}

	public String getAgreementText(WebDriver driver, By element, boolean agreementExist, String tc) throws IOException {
		String temp = "";
		boolean exist;
		exist = elementExist(driver, element, true, tc);
		if (exist) {
			temp = driver.findElement(element).getText();
		}
		return temp;
	}

	public int getNumOfAgreements(WebDriver driver, int numOfAgreements, String tc) throws IOException {
		int count = 0;
		boolean exist;
		exist = elementExist(driver, agreementTitles, true, tc);
		if (exist) {
			count = driver.findElements(agreementTitles).size();
			// if (count==numOfAgreements){
			// rwExcel(tc, true,"There is (are) "+count+" Agreement title (s) exist.", " The number of Agreement matches the expectation.");
			// }else{
			// rwExcel(tc, false,"There is (are) "+count+" Agreement title (s) exist.", " The number of Agreement does match the expectation ="+numOfAgreements);
			//
			// }
		}
		return count;
	}
	public int getNumOfAccounts(WebDriver driver, int numOfpage, String tc) throws IOException {
		By accountsLocator=By.xpath("//table[@id='dealerTable']/tbody/tr");//xpath=//table[@id='dealerTable']/tbody/tr
		int count = 0;
		boolean exist;
		exist = elementExist(driver, accountsLocator, true, tc);
		if (exist) {
			count = driver.findElements(accountsLocator).size();
		}
		return count;
	}
	public int getNumOfDealerships(WebDriver driver, int numOfpage, String tc) throws IOException {
		By dealershipsLocator=By.xpath("//table[@id='dealerTable']");// ///*[@id="dealersTable"]/tbody/tr[2]
		int count = 0;//  //*[@id="dealerTable"]/tbody/tr[1]
		boolean exist;
		exist = elementExist(driver, dealershipsLocator, true, tc);
		if (exist) {
			count = driver.findElements(dealershipsLocator).size();
		}
		return count;
	}
	public String getAccountEmail(WebDriver driver, int num, String tc) throws IOException {
		String accEmail="null";
		By accountEmail = By.xpath("//table[@id='dealerTable']/tbody/tr["+num+"]/td[2]");//xpath=//table[@id='dealerTable']/tbody/tr[3]/td[2]  --- [3].. 1,2,3...
		boolean elementExist = elementExist(driver, accountEmail, true, tc);
		if (elementExist) {
			accEmail = driver.findElement(accountEmail).getText();//.getAttribute("src");
		}
		return accEmail;
	}
	
	public boolean verifyFirstLeftAgreementTitle(WebDriver driver, int totalAgreement, String agreement,
			boolean agreementExist, String tc) throws IOException {
		String temp = "";
		boolean exist;
		int numOfAgreement = getNumOfAgreements(driver, totalAgreement, tc);
		if (!(numOfAgreement == totalAgreement)) {
			rwExcel(tc, false, "Number of Agreement should =" + totalAgreement,
					"But number of Agreement from site=" + numOfAgreement);
		}
		exist = elementExist(driver, firstAgreementLocator, agreementExist, tc);
		if (agreementExist && exist) {
			temp = driver.findElement(firstAgreementLocator).getText();
		}
		if (temp.equals(agreement) && agreementExist) {
			rwExcel(tc, true, "Verify " + agreement + " title exist on the first left", agreement + " title exists.");
		} else if (agreementExist) {
			rwExcel(tc, false, "Verify " + agreement + " title exist  on the first left",
					agreement + " title does not exist. The title from site is=" + temp);
		} else {
			rwExcel(tc, false, "Verify " + agreement + " title should not exist on the first left",
					"But the " + agreement + " title exists. The title from site is=" + temp);
			exist = false;
		}
		return exist;
	}

	public boolean verifyAgreementTitle(WebDriver driver, int totalAgreement, String agreement, boolean agreementExist,
			String tc) throws IOException {
		// One Agreement
		String temp = "";
		boolean exist;
		int numOfAgreement = getNumOfAgreements(driver, totalAgreement, tc);
		if (!(numOfAgreement == totalAgreement)) {
			rwExcel(tc, false, "Number of Agreement should =" + totalAgreement,
					"But number of Agreement from site=" + numOfAgreement);
		}
		exist = elementExist(driver, firstAgreementLocator, agreementExist, tc);
		if (agreementExist && exist) {
			temp = driver.findElement(firstAgreementLocator).getText();
		}
		if (temp.equals(agreement) && agreementExist) {
			rwExcel(tc, true, "Verify " + agreement + " title exist on the first left", agreement + " title exists.");
		} else if (agreementExist) {
			rwExcel(tc, false, "Verify " + agreement + " title exist  on the first left",
					agreement + " title does not exist. The title from site is=" + temp);
			exist = false;
		} else {
			rwExcel(tc, false, "Verify " + agreement + " title should not exist on the first left",
					"But the " + agreement + " title exists. The title from site is=" + temp);
			exist = false;
		}
		return exist;
	}

	public boolean verifyAgreementTitle(WebDriver driver, int totalAgreement, String agreement1, String agreement2,
			boolean agreementExist, String tc) throws IOException {
		// Two Agreements
		String temp = "";
		boolean exist1, exist2, combinedExist;
		int numOfAgreement = getNumOfAgreements(driver, totalAgreement, tc);
		if (!(numOfAgreement == totalAgreement)) {
			rwExcel(tc, false, "Number of Agreement should =" + totalAgreement,
					"But number of Agreement from site=" + numOfAgreement);
		}
		// First left
		exist1 = elementExist(driver, firstAgreementLocator, agreementExist, tc);
		if (agreementExist && exist1) {
			temp = driver.findElement(firstAgreementLocator).getText();
		}
		if (temp.equals(agreement1) && agreementExist) {
			rwExcel(tc, true, "Verify " + agreement1 + " title exist on the first left", agreement1 + " title exists.");
		} else if (agreementExist) {
			rwExcel(tc, false, "Verify " + agreement1 + " title exist  on the first left",
					agreement1 + " title does not exist. The title from site is=" + temp);
			exist1 = false;
		} else {
			rwExcel(tc, false, "Verify " + agreement1 + " title should not exist on the first left",
					"But the " + agreement1 + " title exists. The title from site is=" + temp);
			exist1 = false;
		}

		// Second left
		exist2 = elementExist(driver, secondAgreementLocator, agreementExist, tc);
		if (agreementExist && exist2) {
			temp = driver.findElement(secondAgreementLocator).getText();
		}
		if (temp.equals(agreement2) && agreementExist) {
			rwExcel(tc, true, "Verify " + agreement2 + " title exist on the second left",
					agreement2 + " title exists.");
		} else if (agreementExist) {
			rwExcel(tc, false, "Verify " + agreement2 + " title exist  on the second left",
					agreement2 + " title does not exist. The title from site is=" + temp);
			exist2 = false;
		} else {
			rwExcel(tc, false, "Verify " + agreement2 + " title should not exist on the second left",
					"But the " + agreement2 + " title exists. The title from site is=" + temp);
			exist2 = false;
		}
		combinedExist = exist1 && exist2;
		return combinedExist;
	}

	public boolean verifyAgreementTitle(WebDriver driver, int totalAgreement, String agreement1, String agreement2,
			String agreement3, boolean agreementExist, String tc) throws IOException {
		// Three Agreements
		String temp = "";
		boolean exist1, exist2, exist3, combinedExist;
		int numOfAgreement = getNumOfAgreements(driver, totalAgreement, tc);
		if (!(numOfAgreement == totalAgreement)) {
			rwExcel(tc, false, "Number of Agreement should =" + totalAgreement,
					"But number of Agreement from site=" + numOfAgreement);
		}
		// First left
		exist1 = elementExist(driver, firstAgreementLocator, agreementExist, tc);
		if (agreementExist && exist1) {
			temp = driver.findElement(firstAgreementLocator).getText();
		}
		if (temp.equals(agreement1) && agreementExist) {
			rwExcel(tc, true, "Verify " + agreement1 + " title exist on the first left", agreement1 + " title exists.");
		} else if (agreementExist) {
			rwExcel(tc, false, "Verify " + agreement1 + " title exist  on the first left",
					agreement1 + " title does not exist. The title from site is=" + temp);
			exist1 = false;
		} else {
			rwExcel(tc, false, "Verify " + agreement1 + " title should not exist on the first left",
					"But the " + agreement1 + " title exists. The title from site is=" + temp);
			exist1 = false;
		}

		// Second left
		exist2 = elementExist(driver, secondAgreementLocator, agreementExist, tc);
		if (agreementExist && exist2) {
			temp = driver.findElement(secondAgreementLocator).getText();
		}
		if (temp.equals(agreement2) && agreementExist) {
			rwExcel(tc, true, "Verify " + agreement2 + " title exist on the second left",
					agreement2 + " title exists.");
		} else if (agreementExist) {
			rwExcel(tc, false, "Verify " + agreement2 + " title exist  on the second left",
					agreement2 + " title does not exist. The title from site is=" + temp);
			exist2 = false;
		} else {
			rwExcel(tc, false, "Verify " + agreement2 + " title should not exist on the second left",
					"But the " + agreement2 + " title exists. The title from site is=" + temp);
			exist2 = false;
		}

		// Third left
		exist3 = elementExist(driver, thirdAgreementLocator, agreementExist, tc);
		if (agreementExist && exist3) {
			temp = driver.findElement(thirdAgreementLocator).getText();
		}
		if (temp.equals(agreement3) && agreementExist) {
			rwExcel(tc, true, "Verify " + agreement3 + " title exist on the third left", agreement3 + " title exists.");
		} else if (agreementExist) {
			rwExcel(tc, false, "Verify " + agreement3 + " title exist  on the third left",
					agreement3 + " title does not exist. The title from site is=" + temp);
			exist2 = false;
		} else {
			rwExcel(tc, false, "Verify " + agreement3 + " title should not exist on the third left",
					"But the " + agreement3 + " title exists. The title from site is=" + temp);
			exist2 = false;
		}

		combinedExist = exist1 && exist2;
		return combinedExist;
	}

	public void scrollUp(WebDriver driver, int scrollNum, String tc) {

		// Window scroll down to make the custom image visible.
		JavascriptExecutor jsx = (JavascriptExecutor) driver;
		jsx.executeScript("window.scrollBy(0," + scrollNum + ")", "");
	}

	public String readFile(String path) throws IOException {
		String sCurrentLine;
		StringBuilder sb = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			while ((sCurrentLine = br.readLine()) != null) {
				sb.append(sCurrentLine);
			}
		}
		return sb.toString();
	}

	public String[] getFile(String fileName) throws IOException {
		String[] line;
		line = new String[200];
		StringBuilder result = new StringBuilder("");

		// Get file from resources folder
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream file = classLoader.getResourceAsStream(fileName);

		// File file = new File(classLoader.getResource(fileName).getFile());
		int i = 1;
		try (Scanner scanner = new Scanner(file)) {

			while (scanner.hasNextLine()) {
				line[i] = scanner.nextLine();
				i++;
				// result.append(line).append("\n");
			}
			lineNum = i - 1;
			file.close();
			scanner.close();
		}
		return line;
	}

	public int getLineNum() {
		return lineNum;
	}

	public DealerProfile clickAddDealerShip(WebDriver driver) throws IOException {
		driver.findElement(addDealerShipBtnLocator).click();
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
		}
		Wait(2);
		return new DealerProfile(driver);
	}

	public UserList inputSearch(WebDriver driver, String dlrID) throws IOException {
		driver.findElement(searchLocator).sendKeys(dlrID);
		return this;
	}
	public UserList clickManageAccounts(WebDriver driver) throws IOException {
		driver.findElement(ManageAccountsLocator).click();
		return this;
	}
//	public UserList clickManageDealerShips(WebDriver driver) throws IOException {
//		driver.findElement(ManageDealerShipsLocator).click();
//		Wait(3);
//		return this;
//	}
	public DealerList clickManageDealerShips(WebDriver driver) throws IOException {
		driver.findElement(ManageDealerShipsLocator).click();
		Wait(3);
		return new DealerList(driver);
	}
	public BackgroundSets clickManageBGSets(WebDriver driver) throws IOException {
		driver.findElement(ManageBGSetsLocator).click();
		return new BackgroundSets(driver);
	}

	public ImageTypeList clickManageImageType(WebDriver driver) throws IOException {
//		ManageImageTypesLocator=By.id("imagetypeMenu");
//		WebElement we0 = driver.findElement(ManageImageTypesLocator);
//		hoverOnWebElement(driver,we0);
//		boolean existE=driver.findElement(ManageImageTypesLocator).isDisplayed();
//		existE=elementExist(ManageImageTypesLocator);
		driver.findElement(ManageImageTypesLocator).click();
		return new ImageTypeList(driver);
	}

	public AngleMappingList clickManageAngleMappings(WebDriver driver) throws IOException {
		driver.findElement(ManageAngleMappingsLocator).click();
		return new AngleMappingList(driver);
	}

	public ExportTemplateList clickManageExportTemplates(WebDriver driver) throws IOException {
		driver.findElement(ManageExportTemplatesLocator).click();
		return new ExportTemplateList(driver);
	}

	public GlobalConfig clickManageGlobalConfig(WebDriver driver) throws IOException {
		driver.findElement(ManageGlobalConfigLocator).click();
		return new GlobalConfig(driver);
	}

	public DealerPortal.ImageGallery clickDealerViewBtn(WebDriver driver, int sn) throws IOException {
		By dealerViewBtnLocator = By.xpath("(//button[@id='dealerViewBtn'])[" + sn + "]");// 1,2,3...
		driver.findElement(dealerViewBtnLocator).click();
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
		}
		return new DealerPortal.ImageGallery(driver);
	}

	public AccountProfile clickAddAccount(WebDriver driver) throws IOException {
		driver.findElement(AddAccountBtnLocator).click();
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
		}
		return new AccountProfile(driver);
	}
	public UserList clickExpandDealersArrow(WebDriver driver,int num) throws IOException {
		By expandDealersArrowLocator = By.xpath("//*[@id='dealerTable']/tbody/tr/td["+num+"]");//1,2,3... //table[@id='dealerTable']/tbody/tr[1]/td
		driver.findElement(expandDealersArrowLocator).click();
		return this;
	}
	public DealerProfile clickEditOnDealer(WebDriver driver,int num) throws IOException {
		By editOnDealerLocator = By.xpath("(//button[@id='listViewBtn'])["+num+"]");//1,2,3... //table[@id='dealerTable']/tbody/tr[1]/td
		driver.findElement(editOnDealerLocator).click();
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
		}
		return new DealerProfile(driver);
	}
	public DealerPortal.DealerProfile clickViewDealerPortal(WebDriver driver,int num) throws IOException {
		By viewDealerPortalLocator = By.xpath("(//button[@id='dealerViewBtn'])["+num+"]");//1,2,3... //table[@id='dealerTable']/tbody/tr[1]/td
		driver.findElement(viewDealerPortalLocator).click();
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
		}
		return new DealerPortal.DealerProfile(driver);
	}
	public UserList clickViewDealerships(WebDriver driver,int num) throws IOException {
		By viewDealerships = By.xpath("//table[@id='dealerTable']/tbody/tr["+num+"]/td");//1,2,3... 	xpath=//table[@id='dealerTable']/tbody/tr[2]/td
		driver.findElement(viewDealerships).click();
		return this;
	}
	public DealerProfile clickEditOnDealership(WebDriver driver,int num) throws IOException {
		By editOnDealerLocator = By.xpath("(//button[@id='listViewBtn'])["+num+"]");//1,2,3... //table[@id='dealerTable']/tbody/tr[1]/td
		driver.findElement(editOnDealerLocator).click();
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
		}
		return new DealerProfile(driver);
	}
	public UserList clickNext(WebDriver driver, int num, String tc) throws IOException {
//		By expandDealersArrowLocator = By.xpath("//*[@id='dealerTable']/tbody/tr/td["+num+"]");//1,2,3... //table[@id='dealerTable']/tbody/tr[1]/td
		By expandDealersArrowLocator = By.xpath("//*[@id=\"dealerTable_next\"]");

		//*[@id="dealerTable_next"]
		driver.findElement(expandDealersArrowLocator).click();
		return this;
	}
	public UserList clickFirstNameSort(WebDriver driver, String tc) throws IOException {
		driver.findElement(firstNameSortLocator).click();
		return this;
	}

	
}