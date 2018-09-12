package Mail.TDAF.src;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

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
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
public class ZljLibrary {
		String resultfile = "C:\\1\\Eclipse\\Workspace\\TestResult.xls";
//    	String resultfile="C:\\Documents and Settings\\zhoul\\My Documents\\Chrysler_US\\01_CF\\Automated\\Test Results\\SeleniumTDAF\\TestResult.xls";
		    public void rwExcel(Boolean testStatus, String founctionality, String description)throws IOException
		    {
		       	int n=0;       	
		       	String sName, passOrfail, dateStamp, timeStamp;
		       	Calendar cal=Calendar.getInstance();
		       	cal.getTime();
		       	SimpleDateFormat sdf =new SimpleDateFormat("HH:mm:ss");
		       	timeStamp=sdf.format(cal.getTime());
		       	DateFormat df=DateFormat.getDateInstance(DateFormat.SHORT);
		       	Date d=new Date();
		       	dateStamp=df.format(d);
		       	timeStamp=dateStamp+"  "+timeStamp;
		       	writeTitle(resultfile);

				FileInputStream file = new FileInputStream(new File(resultfile));

		    	//Get the workbook instance for XLS file
		    	HSSFWorkbook workbook = new HSSFWorkbook(file);
		    	
		    	//Get first sheet from the workbook
		    	HSSFSheet s = workbook.getSheetAt(0);

		    	Iterator<Row> rol = s.iterator();
	 	
		    	n=s.getLastRowNum();
		    	sName=s.getSheetName();
		   	 	s.createRow(n+1);
		    	n=s.getLastRowNum();
		    	Row r1=s.getRow(n);
		    	n=r1.getRowNum();
		    
		    	HSSFCellStyle style=workbook.createCellStyle(); //will crash when exceeded about 4213 lines of sheet
		   	 	style.setFillBackgroundColor(IndexedColors.AQUA.getIndex());   	 	
		   	 	style.setFillForegroundColor(HSSFColor.RED.index);
		   	 	
		   		if (testStatus) {
		   			//passOrfail="Passed";
			   	 	style.setFillForegroundColor(HSSFColor.GREEN.index);
			   	 	Cell cell=r1.createCell(1);
			   	 	cell.setCellValue("Passed");
			   	 	cell.setCellStyle(style);
		  		}
		  		else 
		  		{
		   			//passOrfail="Failed";
			   	 	style.setFillForegroundColor(HSSFColor.RED.index);
			   	 	Cell cell=r1.createCell(1);
			   	 	cell.setCellValue("Failed");
			   	 	cell.setCellStyle(style);
			   	 	Font titleFont=workbook.createFont();
			   	 	titleFont.setFontHeight((short) 200);
			   	 	titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
			   	 	style.setFont(titleFont);
		  		}
		   	 	style.setFillPattern(CellStyle.SOLID_FOREGROUND);

		   	 	r1.createCell(0).setCellValue(n);
		   	 	//r1.createCell(1).setCellValue(passOrfail);
		   	 	r1.createCell(2).setCellValue(founctionality);	
		   	 	r1.createCell(3).setCellValue(description);
		   	 	r1.createCell(4).setCellValue(timeStamp);

	/*
		   	 	Font f1 =workbook.createFont();
		   	 	Font f2=workbook.createFont();
		   	 	f1.setColor((short)Font.COLOR_RED);
		   	 	f2.setFontHeight((short) 22);
		   	 	f2.setBoldweight(Font.BOLDWEIGHT_BOLD);
	*/
		    	  	 	
		  	 try {
		    	 FileOutputStream out = new FileOutputStream(resultfile); //
		    	 workbook.write(out);
		    	 out.close(); 
//		    	 return 1;
		    	 }
		    	 catch (Exception e){
		    		 e.printStackTrace();
//		    		 return 0;
		    	 }
		    }

		    public void rwExcel(String founctionality, String description)throws IOException
		        {
		           	int n=0;       	
		           	String sName, passOrfail, dateStamp, timeStamp;
		           	Calendar cal=Calendar.getInstance();
		           	cal.getTime();
		           	SimpleDateFormat sdf =new SimpleDateFormat("HH:mm:ss");
		           	timeStamp=sdf.format(cal.getTime());
		           	DateFormat df=DateFormat.getDateInstance(DateFormat.SHORT);
		           	Date d=new Date();
		           	dateStamp=df.format(d);
		           	timeStamp=dateStamp+"  "+timeStamp;
			       	writeTitle(resultfile);

		        	FileInputStream file = new FileInputStream(new File(resultfile));

		        	//Get the workbook instance for XLS file
		        	HSSFWorkbook workbook = new HSSFWorkbook(file);
		        	
		        	//Get first sheet from the workbook
		        	HSSFSheet s = workbook.getSheetAt(0);
		        	Iterator<Row> rowIterator = s.iterator();

		        	n=s.getLastRowNum();
		        	sName=s.getSheetName();
		       	 	s.createRow(n+1);
		        	n=s.getLastRowNum();
		        	Row r1=s.getRow(n);
		        	n=r1.getRowNum();
		       	 	r1.createCell(0).setCellValue(n);
		       	 	//r1.createCell(1).setCellValue(passOrfail);
		       	 	r1.createCell(2).setCellValue(founctionality);	
		       	 	r1.createCell(3).setCellValue(description);
		       	 	r1.createCell(4).setCellValue(timeStamp);
		    	 
		        	 try {
		        	 FileOutputStream out = new FileOutputStream(resultfile); 
		        	 workbook.write(out);
		        	 out.close();                
		        	 }
		        	 catch (Exception e){
		        		 e.printStackTrace();
		        	 }
		        }    
		    
		    public static void writeTitle(String resultfile)throws IOException
		    {	
		    	int n=0;
		       	File f = new File(resultfile);
		    	if (f.exists())
		    	{       	
		    		FileInputStream file = new FileInputStream(new File(resultfile));
		    	}
		    	else
		    	{
		    		//Write an empty file with a title. 
		        	HSSFWorkbook workbook = new HSSFWorkbook();
		        	//Create first sheet from the workbook
		        	HSSFSheet s = workbook.createSheet();
			    	HSSFCellStyle hdstyle=workbook.createCellStyle();
			   	 	hdstyle.setFillBackgroundColor(IndexedColors.AQUA.getIndex());   	 	
			   	 	hdstyle.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index); 	 	
			   	 	
			   	 	Font titleFont=workbook.createFont();
			   	 	titleFont.setFontHeight((short) 220);
			   	 	titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
			   	 	hdstyle.setFont(titleFont);	
			   	 	
		       	 	s.createRow(0);
		        	n=s.getLastRowNum();
		        	Row r1=s.getRow(n);
		        	n=r1.getRowNum();

				 	Cell cell0=r1.createCell(0);
				  	cell0.setCellValue("S.No.");
				   	cell0.setCellStyle(hdstyle);
		        	
				 	Cell cell1=r1.createCell(1);
				  	cell1.setCellValue("Status");
				   	cell1.setCellStyle(hdstyle); 
				   	
				 	Cell cell2=r1.createCell(2);
				  	cell2.setCellValue("Founctionality");
				   	cell2.setCellStyle(hdstyle); 	        	
		        	
				 	Cell cell3=r1.createCell(3);
				  	cell3.setCellValue("Description");
				   	cell3.setCellStyle(hdstyle); 
				   	
				 	Cell cell4=r1.createCell(4);
				  	cell4.setCellValue("Time");
				   	cell4.setCellStyle(hdstyle); 

			   	 	hdstyle.setFillPattern(CellStyle.SOLID_FOREGROUND);

		        	 try {
		        	 FileOutputStream out = new FileOutputStream(resultfile); // 
		        	 workbook.write(out);
		        	 out.close();                
		        	 }
		        	 catch (Exception e){
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
		    public WebDriver drivers(String browserID)
		    {
		    	if (browserID.equalsIgnoreCase("IE"))
		    	{
//		    	1. Original one:  //Working in XP IE8
				File file = new File("C:/1/Eclipse/RefLibraris/IEDriverServer_x64_2.39.0/IEDriverServer.exe");
//		       	File file=new File("C:/Documents and Settings/zhoul/My Documents/eclipse/IE/IEDriverServer.exe");
		       	System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
		    	WebDriver driver = new InternetExplorerDriver();
////				2. Improved one: https://groups.google.com/forum/#!msg/selenium-users/jJLggNQB9yc/ghy2wsfx28wJ
////    			Note: this one can only open IE but hanged
////            File file =new File("C:\\Users\\Susanth\\lib\\IEDriverServer64.exe");
//    		
////	    	File file=new File("C:/1/Eclipse/RefLibraris/IEDriverServer_Win32_2.39.0/IEDriverServer.exe");
////       		File file=new File("C:/1/Eclipse/RefLibraris/IEDriverServer_x64_2.39.0/IEDriverServer.exe");
//       		File file=new File("C:/Documents and Settings/zhoul/My Documents/eclipse/IE/IEDriverServer.exe");
//       		System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
//            DesiredCapabilities cap= new DesiredCapabilities();
//            cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
//            cap.setCapability("initialBrowserUrl", "www.google.com");//this is the solution? only open IE
//            cap.setCapability("nativeEvents", true);
//            cap.setCapability("ignoreZoomSetting",true);
//            cap.setJavascriptEnabled(true);
//            WebDriver driver=new InternetExplorerDriver(cap);	
		    	return driver;
		    	}
		    	else if (browserID.equalsIgnoreCase("FireFox"))
		    	{
		    		WebDriver driver = new FirefoxDriver();
			    	return driver;
				}
		    	else if (browserID.equalsIgnoreCase("Chrome"))
		    	{
					System.setProperty("webdriver.chrome.driver","C:/1/Eclipse/RefLibraris/chromedriver_win32/chromedriver.exe");
//		    		System.setProperty("webdriver.chrome.driver", "C:/Documents and Settings/zhoul/My Documents/eclipse/Chrome/chromedriver.exe");
		    		WebDriver driver = new ChromeDriver();
		    		return driver;
		    	}
//		    	else if (browserID.equalsIgnoreCase("Safario"))
//		    	{
//		    		WebDriver driver = new Safario();
//		    		return driver;
//		    	}
		    	else
				{
					return null;
				}
            }          
//		    public void testLinks() {
////		        ...
//		        String[] links = selenium.getAllLinks();
//		        foreach(String id : links) {
//		            if(id != null) {
//		                String linkText = selenium.getEval("this.browserbot.findElement('" + id + "').innerHTML;");
//		                selenium.click(id);
//		                verifyTrue(driver.getTitle().contains(linkText));
//		                selenium.goBack();
//		                verifyEqual(selenium.getTitle().contains("title of the first page");
//		            }
//		        }
//		    }  
		    public void GoBack(WebDriver driver)
		    {
		    	driver.navigate().back();
		    	Wait(2);
		    }    
		    public void GoForward(WebDriver driver)
		    {
		    	driver.navigate().forward();
		    	Wait(2);
		    }  
		    public void Wait(int i){
		    try {
	           	Thread.sleep(i*1000);
	           	}
	           	catch (Exception e){
	    		 e.printStackTrace();
	           	}
		    }

	public void VerifyTextLink(WebDriver driver, String linkText) throws IOException{
				By LinkLocator=By.linkText(linkText);
				try{
					boolean linkExist=driver.findElement(LinkLocator).isDisplayed();
		    	if (linkExist){
		    		String realTextLink = driver.findElement(LinkLocator).getText();
		    	
				if((realTextLink.equals(linkText))&& linkExist) {
		    		System.out.println(linkText+" is Visible.");
		    		rwExcel(true, "Verify text link = "+linkText, linkText+" is visible"); 
		    	}else{
		    		System.out.println(linkText+" is Not Visible.");
		    		rwExcel(false, "Verify text link = "+linkText, linkText+" is NOT visible"); 
		    		}
				}else{
		    		System.out.println(linkText+" is a wrong link.");
		    		rwExcel(false, "Verify text link = "+linkText, linkText+" is a wrong link."); 
				} 
				}
		    	catch (Exception e){
		    		System.out.println(linkText+" is a wrong link.");
		    		rwExcel(false, "Verify text link = "+linkText, linkText+" is a wrong link."); 
//		    		 e.printStackTrace();
		    		 return;
				}           	
			
			}
	public boolean VerifyTextLinkExist(WebDriver driver, String linkText) throws IOException{
		By LinkLocator=By.linkText(linkText);
		try{
			boolean linkExist=driver.findElement(LinkLocator).isDisplayed();
    	if (linkExist){
    		String realTextLink = driver.findElement(LinkLocator).getText();
    	
		if((realTextLink.equals(linkText))&& linkExist) {
    		System.out.println(linkText+" is Visible.");
    		rwExcel(true, "Verify text link = "+linkText, linkText+" is visible"); 
    	}else{
    		System.out.println(linkText+" is Not Visible.");
    		rwExcel(false, "Verify text link = "+linkText, linkText+" is NOT visible"); 
    		return false;
    		}
		}else{
    		System.out.println(linkText+" is a wrong link.");
    		rwExcel(false, "Verify text link = "+linkText, linkText+" is a wrong link."); 
    		return false;
		} 
		}
    	catch (Exception e){
    		System.out.println(linkText+" is a wrong link.");
    		rwExcel(false, "Verify text link = "+linkText, linkText+" is a wrong link."); 
//    		 e.printStackTrace();
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
			new Select(driver.findElement(mobileActSelIdLocator))
					.selectByVisibleText(act);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public void SelecBroswerResolution(WebDriver driver,String device) throws IOException{
       	java.awt.Dimension screenSize=java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		Dimension dim = new Dimension((int) screenSize.getWidth(),(int) screenSize.getHeight());
		driver.manage().window().setSize(dim);
//		driver.manage().window().setPosition(new Point(-1100, 0)); //launch at left screen
		driver.manage().window().setPosition(new Point(0, 0));		
//		driver.manage().window().setSize(new Dimension(320, 640)); //iPhone 5: IE8, Chrome, FF work fine!
		if (device.equalsIgnoreCase("PC")){
			driver.manage().window().setSize(new Dimension(1080, 990));
			dim=driver.manage().window().getSize();
			rwExcel(true, "Set up browser resolution for device = "+device,"  "+dim); 
		}else if (device.equalsIgnoreCase("Tablet")){
			driver.manage().window().setSize(new Dimension(764, 660));
			dim=driver.manage().window().getSize();
			rwExcel(true, "Set up browser resolution for device = "+device,"  "+dim); 
		}else if (device.equalsIgnoreCase("Smartphone-iphone4s")){
			driver.manage().window().setSize(new Dimension(320, 640));
			dim=driver.manage().window().getSize();
			rwExcel(true, "Set up browser resolution for device = "+device,"  "+dim); 
		}else{
			driver.manage().window().setSize(new Dimension(1080, 990)); //PC
			dim=driver.manage().window().getSize();
			System.out.println(device+" is a wrong device name. Current browser is setting to "+dim);
			rwExcel(false, "Set up browser resolution for device = "+device+" is not available. ","Force to set to "+dim); 
		}
	}
	public boolean TitleDisplay(WebDriver driver, String title) {
		final int s = 60; // wait time: seconds
		int x = 0;
		while (x < s) {
			String sT = driver.getTitle();
			if (title.equals(driver.getTitle())) {
				break;
			} else {
				Wait(1);
				System.out.println("Waiting page to show. Total = "+x+" times of "+s);
				x++;
			}
		}
		if (x == s) {
			return false;
		} else {
			return true;
		}
	}
	public void SwitchToWindow(WebDriver driver){

//		 It seems like you are not actually switching to any new window. You are supposed get the window handle of your original window, save that, then get the window handle of the new window and switch to that. Once you are done with the new window you need to close it, then switch back to the original window handle. See my sample below:
//
//		 i.e.

		 String parentHandle = driver.getWindowHandle(); // get the current window handle
		 driver.findElement(By.xpath("//*[@id='someXpath']")).click(); // click some link that opens a new window

		 for (String winHandle : driver.getWindowHandles()) {
		     driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
		 }

		 //code to do something on new window

		 driver.close(); // close newly opened window when done with it
		 driver.switchTo().window(parentHandle); // switch back to the original window


	}
		    public static void main(String [] args)throws IOException
		    {	
//		       	String resultfile="C:\\Documents and Settings\\zhoul\\My Documents\\Chrysler_US\\01_CF\\Automated\\Test Results\\SeleniumTDAF\\TestResult.xls";
		    	String fileNamePath="C:\\1\\Eclipse\\Workspace\\TestResult.xls";
	           	RWExcel uatResult = new RWExcel();
		    	for (int i=1;i<=2;i++){
		    		uatResult.rwExcel("Begin for OLP test","Make a Payment step "+i, fileNamePath);    
		    		uatResult.rwExcel(false, "OLP test","Make a Payment step "+i, fileNamePath);	
		    		uatResult.rwExcel(true, "OLP test","Make a Payment step "+i, fileNamePath);	
		    	}
		    	
		    }
		    
}
