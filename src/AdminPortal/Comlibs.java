package AdminPortal;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.util.HSSFColor;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

public class Comlibs {

	public void rwExcel(String SpID, Boolean testStatus, String functionality, String description) throws IOException {

		Properties prop = new Properties();
		// testprop.load(new FileInputStream("data/autopxConf.properties"));
		prop.load(AdminPortalController.class.getClassLoader().getResourceAsStream("AdminPortalData/adminPortalConf.properties"));
		String resultfile = prop.getProperty("AUTOpx.resutlPathFile");

		int n = 0;
		String sName, passOrfail, dateStamp, timeStamp;
		Calendar cal = Calendar.getInstance();
		cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		timeStamp = sdf.format(cal.getTime());
		DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
		Date d = new Date();
		dateStamp = df.format(d);
		timeStamp = dateStamp + "  " + timeStamp;
		writeTitle(resultfile);

		FileInputStream file = new FileInputStream(new File(resultfile));

		// Get the workbook instance for XLS file
		HSSFWorkbook workbook = new HSSFWorkbook(file);

		// Get first sheet from the workbook
		HSSFSheet s = workbook.getSheetAt(0);

		Iterator<Row> rol = s.iterator();

		n = s.getLastRowNum();
		sName = s.getSheetName();
		s.createRow(n + 1);
		n = s.getLastRowNum();
		Row r1 = s.getRow(n);
		n = r1.getRowNum();

		HSSFCellStyle style = workbook.createCellStyle(); // will crash when exceeded about 4213 lines of sheet
		style.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
		style.setFillForegroundColor(HSSFColor.RED.index);

		if (testStatus) {
			// passOrfail="Passed";
			style.setFillForegroundColor(HSSFColor.GREEN.index);
			Cell cell = r1.createCell(2);
			cell.setCellValue("Passed");
			cell.setCellStyle(style);
		} else {
			// passOrfail="Failed";
			style.setFillForegroundColor(HSSFColor.RED.index);
			Cell cell = r1.createCell(2);
			cell.setCellValue("Failed");
			cell.setCellStyle(style);
			Font titleFont = workbook.createFont();
			titleFont.setFontHeight((short) 200);
			titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
			style.setFont(titleFont);
		}
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);

		r1.createCell(0).setCellValue(n);
		// r1.createCell(1).setCellValue(passOrfail);
		r1.createCell(1).setCellValue(SpID);
		r1.createCell(3).setCellValue(functionality);
		r1.createCell(4).setCellValue(description);
		r1.createCell(5).setCellValue(timeStamp);

		/*
		 * Font f1 =workbook.createFont(); Font f2=workbook.createFont(); f1.setColor((short)Font.COLOR_RED); f2.setFontHeight((short) 22); f2.setBoldweight(Font.BOLDWEIGHT_BOLD);
		 */

		try {
			FileOutputStream out = new FileOutputStream(resultfile); //
			workbook.write(out);
			out.close();
			// return 1;
		} catch (Exception e) {
			e.printStackTrace();
			// return 0;
		}
	}

	public void rwExcel(String SpID, String functionality, String description) throws IOException {
		Properties prop = new Properties();
		// testprop.load(new FileInputStream("data/autopxConf.properties"));
		prop.load(AdminPortalController.class.getClassLoader().getResourceAsStream("AdminPortalData/adminPortalConf.properties"));
		String resultfile = prop.getProperty("AUTOpx.resutlPathFile");
		int n = 0;
		String sName, passOrfail, dateStamp, timeStamp;
		Calendar cal = Calendar.getInstance();
		cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		timeStamp = sdf.format(cal.getTime());
		DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
		Date d = new Date();
		dateStamp = df.format(d);
		timeStamp = dateStamp + "  " + timeStamp;
		writeTitle(resultfile);

		FileInputStream file = new FileInputStream(new File(resultfile));

		// Get the workbook instance for XLS file
		HSSFWorkbook workbook = new HSSFWorkbook(file);

		// Get first sheet from the workbook
		HSSFSheet s = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = s.iterator();

		n = s.getLastRowNum();
		sName = s.getSheetName();
		s.createRow(n + 1);
		n = s.getLastRowNum();
		Row r1 = s.getRow(n);
		n = r1.getRowNum();
		r1.createCell(0).setCellValue(n);
		// r1.createCell(1).setCellValue(passOrfail);
		r1.createCell(1).setCellValue(SpID);
		r1.createCell(3).setCellValue(functionality);
		r1.createCell(4).setCellValue(description);
		r1.createCell(5).setCellValue(timeStamp);

		try {
			FileOutputStream out = new FileOutputStream(resultfile);
			workbook.write(out);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void rwExcel(String SpID, int testStatus, String functionality, String description) throws IOException {
		Properties prop = new Properties();
		// testprop.load(new FileInputStream("data/autopxConf.properties"));
		prop.load(AdminPortalController.class.getClassLoader().getResourceAsStream("AdminPortalData/adminPortalConf.properties"));
		String resultfile = prop.getProperty("AUTOpx.resutlPathFile");
		int n = 0;
		String sName, passOrfail, dateStamp, timeStamp;
		Calendar cal = Calendar.getInstance();
		cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		timeStamp = sdf.format(cal.getTime());
		DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
		Date d = new Date();
		dateStamp = df.format(d);
		timeStamp = dateStamp + "  " + timeStamp;
		writeTitle(resultfile);

		FileInputStream file = new FileInputStream(new File(resultfile));

		// Get the workbook instance for XLS file
		HSSFWorkbook workbook = new HSSFWorkbook(file);

		// Get first sheet from the workbook
		HSSFSheet s = workbook.getSheetAt(0);

		Iterator<Row> rol = s.iterator();

		n = s.getLastRowNum();
		sName = s.getSheetName();
		s.createRow(n + 1);
		n = s.getLastRowNum();
		Row r1 = s.getRow(n);
		n = r1.getRowNum();
		HSSFCellStyle style = workbook.createCellStyle(); // will crash when exceeded about 4213 lines of 														// sheet
		style.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
		style.setFillForegroundColor(HSSFColor.RED.index);

		if (testStatus > 0) {
			// passOrfail="Passed";
			style.setFillForegroundColor(HSSFColor.YELLOW.index);
			Cell cell = r1.createCell(2);
			cell.setCellValue(testStatus);
			cell.setCellStyle(style);
		} else {
			// passOrfail="Failed";
			style.setFillForegroundColor(HSSFColor.RED.index);
			Cell cell = r1.createCell(2);
			cell.setCellValue("Failed");
			cell.setCellStyle(style);
			Font titleFont = workbook.createFont();
			titleFont.setFontHeight((short) 200);
			titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
			style.setFont(titleFont);
		}
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);

		r1.createCell(0).setCellValue(n);
		// r1.createCell(1).setCellValue(passOrfail);
		r1.createCell(1).setCellValue(SpID);
		r1.createCell(3).setCellValue(functionality);
		r1.createCell(4).setCellValue(description);
		r1.createCell(5).setCellValue(timeStamp);

		/*
		 * Font f1 =workbook.createFont(); Font f2=workbook.createFont(); f1.setColor((short)Font.COLOR_RED); f2.setFontHeight((short) 22); f2.setBoldweight(Font.BOLDWEIGHT_BOLD);
		 */

		try {
			FileOutputStream out = new FileOutputStream(resultfile); //
			workbook.write(out);
			out.close();
			// return 1;
		} catch (Exception e) {
			e.printStackTrace();
			// return 0;
		}
	}

	public static void writeTitle(String resultfile) throws IOException {
		int n = 0;
		File f = new File(resultfile);
		if (f.exists()) {
			FileInputStream file = new FileInputStream(new File(resultfile));
		} else {
			// Write an empty file with a title.
			HSSFWorkbook workbook = new HSSFWorkbook();
			// Create first sheet from the workbook
			HSSFSheet s = workbook.createSheet();
			HSSFCellStyle hdstyle = workbook.createCellStyle();
			hdstyle.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
			hdstyle.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);

			Font titleFont = workbook.createFont();
			titleFont.setFontHeight((short) 220);
			titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
			hdstyle.setFont(titleFont);

			s.createRow(0);
			n = s.getLastRowNum();
			Row r1 = s.getRow(n);
			n = r1.getRowNum();

			Cell cell0 = r1.createCell(0);
			cell0.setCellValue("S.No.");
			cell0.setCellStyle(hdstyle);

			Cell cell1 = r1.createCell(1);
			cell1.setCellValue("SpiraID");
			cell1.setCellStyle(hdstyle);

			Cell cell2 = r1.createCell(2);
			cell2.setCellValue("Status");
			cell2.setCellStyle(hdstyle);

			Cell cell3 = r1.createCell(3);
			cell3.setCellValue("Functionality");
			cell3.setCellStyle(hdstyle);

			Cell cell4 = r1.createCell(4);
			cell4.setCellValue("Description");
			cell4.setCellStyle(hdstyle);

			Cell cell5 = r1.createCell(5);
			cell5.setCellValue("Time");
			cell5.setCellStyle(hdstyle);

			hdstyle.setFillPattern(CellStyle.SOLID_FOREGROUND);

			try {
				FileOutputStream out = new FileOutputStream(resultfile); //
				workbook.write(out);
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void switchWindow(WebDriver driver, int whichOne) {
		int iCounts = 0;
		int iTemp = 0;
		for (String winHandle : driver.getWindowHandles()) {
			iCounts++;
		}
		if (whichOne <= iCounts) {
			for (String winHandle : driver.getWindowHandles()) {
				iTemp++;
				if (iTemp == whichOne)
					driver.switchTo().window(winHandle);
			}
		} else {
			System.out.println("Window does not exist!");
		}
	}
	public void switchToWindow(WebDriver driver) {
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
		}
	}
	public WebDriver drivers(String browserID) throws IOException {
		if (browserID.equalsIgnoreCase("IE")) {
			// 1. Original one: //Working in XP IE8
			// File file = new File(
			// "C:/1/Eclipse/RefLibraris/IEDriverServer_x64_2.43.0/IEDriverServer.exe");
			System.setProperty("webdriver.internetexplorer.driver",
					"C:\\eclipse\\RefLibraris\\IEDriverServer_x64\\IEDriverServer.exe");
			// C:\1\Eclipse\RefLibraris\IEDriverServer_x64_2.41.0/IEDriverServer.exe
			// File file = new
			// File("C:/1/Eclipse/RefLibraris/IEDriverServer_Win32_2.39.0/IEDriverServer.exe");
			// //works on xy

			// File file=new
			// File("C:/Documents and Settings/zhoul/My Documents/eclipse/IE/IEDriverServer.exe");
			// System.setProperty("webdriver.ie.driver",
			// file.getAbsolutePath());
			WebDriver driver = new InternetExplorerDriver();
			// // 2. Improved one:
			// https://groups.google.com/forum/#!msg/selenium-users/jJLggNQB9yc/ghy2wsfx28wJ
			// // Note: this one can only open IE but hanged
			// // File file =new
			// File("C:\\Users\\Susanth\\lib\\IEDriverServer64.exe");
			//
			// // File file=new
			// File("C:/1/Eclipse/RefLibraris/IEDriverServer_Win32_2.39.0/IEDriverServer.exe");
			// // File file=new
			// File("C:/1/Eclipse/RefLibraris/IEDriverServer_x64_2.39.0/IEDriverServer.exe");
			// File file=new
			// File("C:/Documents and Settings/zhoul/My Documents/eclipse/IE/IEDriverServer.exe");
			// System.setProperty("webdriver.ie.driver",
			// file.getAbsolutePath());
			// DesiredCapabilities cap= new DesiredCapabilities();
			// cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
			// true);
			// cap.setCapability("initialBrowserUrl", "www.google.com");//this
			// is the solution? only open IE
			// cap.setCapability("nativeEvents", true);
			// cap.setCapability("ignoreZoomSetting",true);
			// cap.setJavascriptEnabled(true);
			// WebDriver driver=new InternetExplorerDriver(cap);
			return driver;
		} else if (browserID.equalsIgnoreCase("FireFox")) {
			final String firebugPath = "C:\\1\\Eclipse\\RefLibraris\\firebug-2.0.19.xpi";
			final String firepathPath = "C:\\1\\Eclipse\\RefLibraris\\firepath-0.9.7.1-fx.xpi";
			final String seleniumExpertIDE = "C:\\1\\Eclipse\\RefLibraris\\selenium_expert_selenium_ide-0.25-fx.xpi";
			//added geckodriver.exe since selenium 3.0 on-wards.
			System.setProperty("webdriver.gecko.driver","C:/1/Eclipse/RefLibraris/geckodriver.exe");
			FirefoxProfile profile = new FirefoxProfile();

			
			profile.addExtension(new File(firebugPath));
			profile.addExtension(new File(firepathPath));
//			profile.setAcceptUntrustedCertificates(true);   //doesn't work
//			profile.setAssumeUntrustedCertificateIssuer(false); //doesn't work
//			profile.setPreference("security.insecure_password.ui.enabled", false);   // works. 5/5/2017
			profile.setPreference("security.insecure_field_warning.contextual.enabled", true);  //better works. Using false/true. 03/27/2018
			
			// profile.addExtension(new File(seleniumExpertIDE));
			// Add more if needed
//			WebDriver driver = new FirefoxDriver(profile); //adding profile invalid since 12/13/2017
			WebDriver driver = new FirefoxDriver();

			//WebDriver driver = new FirefoxDriver();
			return driver;
		} else if (browserID.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"C:/1/Eclipse/RefLibraris/chromedriver_win32/chromedriver.exe");
			// System.setProperty("webdriver.chrome.driver",
			// "C:/Documents and Settings/zhoul/My Documents/eclipse/Chrome/chromedriver.exe");
			
			//Disable save password, below from https://sqa.stackexchange.com/questions/26275/how-to-disable-chrome-save-your-password-selenium-java
			ChromeOptions cOpt = new ChromeOptions();
		    ChromeOptions options = new ChromeOptions();
		    options.addArguments("--start-maximized");
		    options.addArguments("--disable-web-security");
		    options.addArguments("--no-proxy-server");
//		    // This is disalbe security for "Chrome is being controlled by automated test software" - 2018/03/27 OK
//		    options.addArguments("disable-infobars");  //- 2018/03/27 OK
		    Map<String, Object> prefs = new HashMap<String, Object>();
		    prefs.put("credentials_enable_service", false); 
		    prefs.put("profile.password_manager_enabled", false); 
		    options.setExperimentalOption("prefs", prefs);
			
		    
		    
			WebDriver driver = new ChromeDriver(options);
			return driver;
		}
		// else if (browserID.equalsIgnoreCase("Safario"))
		// {
		// WebDriver driver = new Safario();
		// return driver;
		// }
		else {
			System.out.println("Browser is wrong! Your browser setting is :"+browserID);
			return null;
		}
	}

	// public void testLinks() {
	// // ...
	// String[] links = selenium.getAllLinks();
	// foreach(String id : links) {
	// if(id != null) {
	// String linkText = selenium.getEval("this.browserbot.findElement('" + id +
	// "').innerHTML;");
	// selenium.click(id);
	// verifyTrue(driver.getTitle().contains(linkText));
	// selenium.goBack();
	// verifyEqual(selenium.getTitle().contains("title of the first page");
	// }
	// }
	// }
	public void GoBack(WebDriver driver) {
		driver.navigate().back();
		Wait(2);
	}

	public void GoForward(WebDriver driver) {
		driver.navigate().forward();
		Wait(2);
	}

	public void Wait(int i) {
		try {
			Thread.sleep(i * 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void VerifyTextLink(WebDriver driver, String linkText) throws IOException {
		By LinkLocator = By.linkText(linkText);
		try {
			boolean linkExist = driver.findElement(LinkLocator).isDisplayed();
			if (linkExist) {
				String realTextLink = driver.findElement(LinkLocator).getText();

				if ((realTextLink.equals(linkText)) && linkExist) {
					System.out.println(linkText + " is Visible.");
					rwExcel("", true, "Verify text link = " + linkText, linkText + " is visible");
				} else {
					System.out.println(linkText + " is Not Visible.");
					rwExcel("", false, "Verify text link = " + linkText, linkText + " is NOT visible");
				}
			} else {
				System.out.println(linkText + " is a wrong link.");
				rwExcel("", false, "Verify text link = " + linkText, linkText + " is a wrong link.");
			}
		} catch (Exception e) {
			System.out.println(linkText + " is a wrong link.");
			rwExcel("", false, "Verify text link = " + linkText, linkText + " is a wrong link.");
			// e.printStackTrace();
			return;
		}

	}

	public boolean VerifyTextLinkExist(WebDriver driver, String linkText) throws IOException {
		By LinkLocator = By.linkText(linkText);
		try {
			boolean linkExist = driver.findElement(LinkLocator).isDisplayed();
			if (linkExist) {
				String realTextLink = driver.findElement(LinkLocator).getText();

				if ((realTextLink.equals(linkText)) && linkExist) {
					System.out.println(linkText + " is Visible.");
					rwExcel("", true, "Verify text link = " + linkText, linkText + " is visible");
				} else {
					System.out.println(linkText + " is Not Visible.");
					rwExcel("", false, "Verify text link = " + linkText, linkText + " is NOT visible");
					return false;
				}
			} else {
				System.out.println(linkText + " is a wrong link.");
				rwExcel("", false, "Verify text link = " + linkText, linkText + " is a wrong link.");
				return false;
			}
		} catch (Exception e) {
			System.out.println(linkText + " is a wrong link.");
			rwExcel("", false, "Verify text link = " + linkText, linkText + " is a wrong link.");
			// e.printStackTrace();
			return false;
		}
		return true;

	}

	public boolean SelectAccount(WebDriver driver, String act) {
		By ActSelIdLocator = By.id("defaultAccountNumber");
		try {
			new Select(driver.findElement(ActSelIdLocator)).selectByVisibleText(act);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean mobileSelectAccount(WebDriver driver, String act) {
		By mobileActSelIdLocator = By.id("mobile-defaultAccountNumber");
		try {
			new Select(driver.findElement(mobileActSelIdLocator)).selectByVisibleText(act);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public void SelecBroswerResolution(WebDriver driver, String device) throws IOException {
		java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		Dimension dim = new Dimension((int) screenSize.getWidth(), (int) screenSize.getHeight());
		driver.manage().window().setSize(dim);
//		 driver.manage().window().setPosition(new Point(-1200, 12)); //launch  Original -1100, 60. 2018-09-14
		// at left screen
		driver.manage().window().setPosition(new Point(0, 0)); // launch at
																// rigth screen
		// driver.manage().window().setSize(new Dimension(320, 640)); //iPhone
		// 5: IE8, Chrome, FF work fine!
		if (device.equalsIgnoreCase("PC")) {
			driver.manage().window().setSize(new Dimension(1080, 990));  //Original one before May 04, 2017
//			driver.manage().window().setSize(new Dimension(1180, 1040));  //After Dec 13, 2017. 2018-08-22: 1080, 1040
//			driver.manage().window().setSize(new Dimension(1920, 1040));  // (width, high)
			dim = driver.manage().window().getSize();
			rwExcel("", true, "Set up browser resolution for device = " + device, "  " + dim);
		} else if (device.equalsIgnoreCase("Tablet")) {
			// driver.manage().window().setSize(new Dimension(764, 660));
			driver.manage().window().setSize(new Dimension(990, 660));
			dim = driver.manage().window().getSize();
			rwExcel("", true, "Set up browser resolution for device = " + device, "  " + dim);
		} else if (device.equalsIgnoreCase("Smartphone-iphone4s")) {
			driver.manage().window().setSize(new Dimension(320, 640));
			dim = driver.manage().window().getSize();
			rwExcel("", true, "Set up browser resolution for device = " + device, "  " + dim);
		} else {
			driver.manage().window().setSize(new Dimension(1080, 990)); // PC
			dim = driver.manage().window().getSize();
			System.out.println(device + " is a wrong device name. Current browser is setting to " + dim);
			rwExcel("", false, "Set up browser resolution for device = " + device + " is not available. ",
					"Force to set to " + dim);
		}
	}

	public boolean TitleDisplay(WebDriver driver, String title) throws IOException {
		final int s = 60; // wait time: seconds
		String sT = "";
		int x = 0;
		while (x < s) {
			sT = driver.getTitle();
			if (title.equals(driver.getTitle())) {
				break;
			} else {
				Wait(1);
				System.out.println("Waiting page title: " + title + " to show. Total = " + x + " times of " + s);
				x++;
			}
		}
		if (x == s) {
			rwExcel("", false, "Title is NOT: " + title, "Get page title is: " + sT);
			return false;
		} else {
			return true;
		}
	}

	public boolean Elementpresent(WebDriver driver, By iElement) throws IOException {
		final int s = 10; // wait time: seconds
		int x = 0;
		while (x < s) {
			if (elementExist(driver, iElement)) {
				System.out.println("Element: " + iElement + " exists!");
				break;
			} else {
				Wait(1);
				System.out.println("Waiting element: " + iElement + " to show. Total = " + x + " times of " + s);
				x++;
				rwExcel("", false, "Waiting element: " + iElement + " to show. Total = " + x + " times of " + s, "");
			}
		}
		if (x == s) {
			return false;
		} else {
			return true;
		}
	}

	public void SwitchToWindow(WebDriver driver) {

		// It seems like you are not actually switching to any new window. You
		// are supposed get the window handle of your original window, save
		// that, then get the window handle of the new window and switch to
		// that. Once you are done with the new window you need to close it,
		// then switch back to the original window handle. See my sample below:
		//
		// i.e.

		String parentHandle = driver.getWindowHandle(); // get the current window handle
		driver.findElement(By.xpath("//*[@id='someXpath']")).click(); // click some link that opens a new window

		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
		}

		// code to do something on new window

		driver.close(); // close newly opened window when done with it
		driver.switchTo().window(parentHandle); // switch back to the original window

	}

	public boolean elementExist(WebDriver driver, By elementLocator) throws IOException {
		// Agree with this answer but there's an implicit 3 second wait if no
		// elements are
		// found which can be switched on/off which is useful if you're
		// performing this action a lot:
		// driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		boolean exists = driver.findElements(elementLocator).size() != 0;
		// driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		// Putting that into a utility method should improve performance if
		// you're running a lot of tests

		if (exists) {
			rwExcel("", true, "Element" + elementLocator, " exists");
			return true;
		} else {
			rwExcel("", false, "Element" + elementLocator, " does NOT exist");
			return false;
		}
	}

	public boolean elementExist(WebDriver driver, By elementLocator, boolean rw, String tc) throws IOException {
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		boolean exists = driver.findElements(elementLocator).size() != 0;
		if (exists) {
			return true;
		} else {
			if (rw) {
				rwExcel(tc, false, "Element" + elementLocator, " does NOT exist");
			}
			return false;
		}
	}

	public void VerifyelementExist(WebDriver driver, By elementLocator) throws IOException {
		// Agree with this answer but there's an implicit 3 second wait if no
		// elements are
		// found which can be switched on/off which is useful if you're
		// performing this action a lot:
		// driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		boolean exists = driver.findElements(elementLocator).size() != 0;
		// driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		// Putting that into a utility method should improve performance if
		// you're running a lot of tests

		if (exists) {
			rwExcel("", true, "Element" + elementLocator, " exists");
		} else {
			rwExcel("", false, "Element" + elementLocator, " does NOT exist");
		}
	}

	public void VerifytextElementExist(WebDriver driver, By elementLocator, String Txt) throws IOException {
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		boolean exists = driver.findElements(elementLocator).size() != 0;
		String text1 = driver.findElement(elementLocator).getText();
		boolean txtExists = driver.findElement(elementLocator).getText().equalsIgnoreCase(Txt);

		if (exists && txtExists) {
			rwExcel("", true, "Element" + elementLocator + " & Text=" + Txt, "  exists");
		} else {
			rwExcel("", false, "Element" + elementLocator + " & Text=" + Txt, "  does NOT exist");
		}

	}

	// ArrayList<WebElement> imgElements =
	// driver.findElements(By.xpath("//*[contains(@src, '.gif')] | //*[contains(@src, '.png')]");
	//
	// for (WebElement element : imgElements){
	//
	// boolean imageLoaded = (boolean) ((JavascriptExecutor)
	// driver).executeScript("return arguments[0].complete && "+
	// "typeof arguments[0].naturalWidth != \"undefined\" && "+"arguments[0].naturalWidth > 0",
	// element);
	//
	// if (!imageLoaded)
	// {
	// System.out.println("Found broken image: "element.getAttribute("src"))
	// }

	public void VerifyImageLoaded(WebDriver driver, By imageLocator, String tc) throws IOException {
		waitElementToShow(driver, imageLocator);
		WebElement image1 = driver.findElement(imageLocator);
		boolean imageLoaded1 = (boolean) ((JavascriptExecutor) driver).executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
				image1);
		// boolean imageLoaded1= (boolean) ((JavascriptExecutor)
		// driver).executeScript("return arguments[0].complete && "+
		// "typeof arguments[0].naturalWidth != \"undefined\" && "+"arguments[0].naturalWidth > 0",
		// image1);

		if (!imageLoaded1) {
			System.out.println("Image is not present");
			rwExcel(tc, false, "ImageLocator = " + imageLocator, "Image is NOT loaded properly");
		} else {
			System.out.println("Image loaded!  -  " + imageLocator);
			rwExcel(tc, true, "ImageLocator = " + imageLocator, "Image is loaded");
		}
	}

	public void waitElementToShow(WebDriver driver, By elemt) throws IOException {
		boolean im = elementExist(driver, elemt);
		int i = 0;
		while ((!im) && (i < 20)) {
			System.out.println("i=" + i + ", waiting for image to show! " + elemt);
			im = elementExist(driver, elemt);
			i++;
		}
	}

	public void checkListWebElements(WebDriver driver, By elementLocator) throws IOException {
		String listText1;
		List<WebElement> listItems = driver.findElements(elementLocator);
		int trimSideBarCount = listItems.size();
		for (int i = 0; i < trimSideBarCount; i++) {
			listText1 = listItems.get(i).getText();
			System.out.println("i=" + i + ", listText1 =" + listText1);
			// rwExcel("", true, "WebElement = " + elementLocator, "Content " + i + " =" + listText1);
			// trimSideBar.get(i).click();
		}
	}

	public String[] GetListWebName(WebDriver driver, By elementLocator, String myId, int NameNum) throws IOException {
		// String[] elementNames = new String[NameNum];
		List<WebElement> listItems = driver.findElements(elementLocator);
		int engTransCount = listItems.size();
		String[] elementNames = new String[engTransCount];
		for (int i = 0; i < engTransCount; i++) {
			elementNames[i] = listItems.get(i).getText();
			// Get all Color Option List here
			System.out.println("\ni=" + i + ", Name =\n" + elementNames[i]);
		}
		System.out.print("\n");
		return elementNames;
	}

	public int ListWebElementsSize(WebDriver driver, By elementLocator) {
		List<WebElement> trimSideBar = driver.findElements(elementLocator);
		int trimSideBarCount = trimSideBar.size();
		return trimSideBarCount;
	}

	public void writeOneline(String prixName, String sValue) {
		// BufferedWriter writer = null;
		try {
			// create a temporary file
			// String timeLog = new
			// SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			String timeLog = new SimpleDateFormat("yyyyMMdd_HHmm").format(Calendar.getInstance().getTime());// only
																											// keep
																											// mm
			timeLog = prixName + "_" + timeLog + ".txt";
			File logFile = new File(timeLog);
			if (!logFile.exists()) {
				logFile.createNewFile();
			}
			// This will output the full path where the file will be written
			// to...
			System.out.println(logFile.getCanonicalPath());
			FileWriter writertxt = new FileWriter(logFile.getName(), true);
			BufferedWriter filewriter = new BufferedWriter(writertxt);
			filewriter.write(sValue);
			filewriter.newLine();
			filewriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void hoverOnWebElement(WebDriver driver, WebElement we0) {
		String javaScript = "var evObj = document.createEvent('MouseEvents');"
				+ "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
				+ "arguments[0].dispatchEvent(evObj);";
		((JavascriptExecutor) driver).executeScript(javaScript, we0);
	}
	public static void writeTitle(String resultfile, String[] tString) throws IOException {
		int tStringLength = tString.length;
		int n = 0;
		File f = new File(resultfile);
		if (f.exists()) {
			FileInputStream file = new FileInputStream(new File(resultfile));
		} else {
			// Write an empty file with a title.
			HSSFWorkbook workbook = new HSSFWorkbook();
			// Create first sheet from the workbook
			HSSFSheet s = workbook.createSheet();
			HSSFCellStyle hdstyle = workbook.createCellStyle();
			hdstyle.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
			hdstyle.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);

			Font titleFont = workbook.createFont();
			titleFont.setFontHeight((short) 220);
			titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
			hdstyle.setFont(titleFont);

			s.createRow(0);
			n = s.getLastRowNum();
			Row r1 = s.getRow(n);
			n = r1.getRowNum();

			for (int i = 0; i < tStringLength; i++) {
				Cell cell0 = r1.createCell(i);
				cell0.setCellValue(tString[i]);
				cell0.setCellStyle(hdstyle);
			}

			hdstyle.setFillPattern(CellStyle.SOLID_FOREGROUND);

			try {
				FileOutputStream out = new FileOutputStream(resultfile); //
				workbook.write(out);
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void acceptAlert(WebDriver driver,String tc, String alertType) throws IOException {
		boolean alertPass = false;
		Wait(1);
		try {
			driver.switchTo().alert().accept();// on the “Ok” button as soon as the pop up window appears.
			// driver.switchTo().alert().dismiss();// clicks on the “Cancel” button as soon as the pop up window appears.
			driver.switchTo().defaultContent();//
			alertPass = true;
			System.out.println("1. Accept the alert.");
			// rwExcel(tc, true, "Alert showing, Accept the alert =" + alertType, "Accetped successfully.");
		} catch (Throwable e) {
			alertPass = false;
			System.out.println("1. Failed to Accept the alert.");
			rwExcel(tc, false, "Alert showing, Accept the alert =" + alertType, "failed to accetp.");
		}
		// return alertPass;
	}
	public void clickRefleshF5Btn(WebDriver driver, String tc) throws IOException {
		// driver.findElement(addInventoryBtn).sendKeys(Keys.F5);
		driver.navigate().to(driver.getCurrentUrl());
	}
	public static void writeToSheet(String resultfile, String[] jSONValues) throws IOException {
		int n = 0;
		String sName, passOrfail, dateStamp, timeStamp;
		Calendar cal = Calendar.getInstance();
		cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		timeStamp = sdf.format(cal.getTime());
		DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
		Date d = new Date();
		dateStamp = df.format(d);
		timeStamp = dateStamp + "  " + timeStamp;
		// writeTitle(resultfile);

		FileInputStream file = new FileInputStream(new File(resultfile));
		int jSONValuesLength = jSONValues.length;

		// Get the workbook instance for XLS file
		HSSFWorkbook workbook = new HSSFWorkbook(file);

		// Get first sheet from the workbook
		HSSFSheet s = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = s.iterator();

		n = s.getLastRowNum();
		sName = s.getSheetName();
		s.createRow(n + 1);
		n = s.getLastRowNum();
		Row r1 = s.getRow(n);
		n = r1.getRowNum();

		for (int i = 0; i < jSONValuesLength; i++) {
			r1.createCell(i).setCellValue(jSONValues[i]);
		}

		try {
			FileOutputStream out = new FileOutputStream(resultfile);
			workbook.write(out);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// public static void main(String[] args) throws IOException {
	// // String
	// //
	// resultfile="C:\\Documents and Settings\\zhoul\\My Documents\\Chrysler_US\\01_CF\\Automated\\Test Results\\SeleniumTDAF\\TestResult.xls";
	// String fileNamePath = "C:\\1\\Eclipse\\Workspace\\TestResult.xls";
	// Comlibs uatResult = new Comlibs();
	// for (int i = 1; i <= 2; i++) {
	// uatResult.rwExcel("Begin for OLP test", "Make a Payment step " + i,
	// fileNamePath);
	// uatResult.rwExcel("TC123456", true, "LOGIN_01", "from DP1");//
	// // uatResult.rwExcel("",false, "OLP test","Make a Payment step "+i,
	// // fileNamePath);
	// uatResult.rwExcel("TC123456", false, "LOGIN_02", "from DP2");//
	// // uatResult.rwExcel("",true, "OLP test","Make a Payment step "+i,
	// // fileNamePath);
	// }
	//
	// }

}
