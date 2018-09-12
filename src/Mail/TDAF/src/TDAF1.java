import java.io.*;
import java.lang.*;
import java.util.List;

import junit.framework.*;

import org.openqa.selenium.*;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.Select;


      public class TDAF1 {
 /*    
    	  public static boolean isElementPresent(String byId, WebDriver driver) { 
    			try {
    			driver.findElement(By.id(byId)).isDisplayed(); 
    			return true; 
    			} 
    			catch (NoSuchElementException e) {
    			return false; 
    			} 
    		}
    	  public static boolean AddBankProfile(WebDriver driver)
    	  {
    	  	try {
    	  	driver.findElement(By.id("accountNickname")).clear();
    	  	driver.findElement(By.id("accountNickname")).sendKeys("Tom");
    	  	driver.findElement(By.id("checking")).click();
    	      driver.findElement(By.id("bankRoutingNumber")).clear();
    	      driver.findElement(By.id("bankRoutingNumber")).sendKeys("272471548");
    	      driver.findElement(By.id("bankAccountNumber")).clear();
    	      driver.findElement(By.id("bankAccountNumber")).sendKeys("1234567890");
    	      driver.findElement(By.id("confirmEmailAddress")).clear();
    	      driver.findElement(By.id("confirmEmailAddress")).sendKeys("lucas.zhou@autodata.net");
    	  	return true; 
    	  	}	
    	  	catch (NoSuchElementException e) {
    	      return false; 
    	      } 
    	  }
 */   	  

            public static void startBrowserUAT()throws IOException// extends ReadXMLfile 
            {
            	String sId;
            	String sAccount;

            	String[][] B=new String[2][50];
            	
        		String fileNamePath = "C:\\1\\Eclipse\\Workspace\\TestResult.xls";
//            	String fileNamePath="C:\\Documents and Settings\\zhoul\\My Documents\\Chrysler_US\\01_CF\\Automated\\Test Results\\SeleniumTDAF\\TestResult.xls";
               	RWExcel uatResult = new RWExcel();
               	uatResult.rwExcel("UAT testing", "Make A Payment", fileNamePath);
               	//uatResult.rwExcel(true, "OLP test", "Make A Payment Step 1", fileNamePath);
               	//uatResult.rwExcel(false, "OLP test", "Make A Payment Step 1", fileNamePath); 	
               	
               	
            	//B=readfromxmlfile();
            	//ReadXMLfile D=new ReadXMLfile();
            	
            	//B=D.readfromxmlfile();
            	
            	WebDriver driver = new FirefoxDriver();
            	/*FirefoxProfile firefoxProfile = new FirefoxProfile();
            	WebDriver driver = new FirefoxDriver(firefoxProfile);*/
//            	WebDriver driver = new InternetExplorerDriver();
//            	WebDriver driver = new ChromeDriver();
//            	WebDriver driver = new SafariDriver();
      //driver.manage().window().maximize();
            	//String URL="http://qa-cfui.autodata.net/home.do";
            	String URL="https://uat.tdautofinance.com";
            	//driver.get("http://qa-cfui.autodata.net/home.do");
            	driver.get(URL);            	
                  //WebElement el = driver.findElement(zipBoxLocator);
                  //el.click();          	
            	String loginTitle=driver.getTitle();
            	if (!"TD Auto Finance - Home".equals(driver.getTitle())){
           		 throw new IllegalStateException("This is not the Home login page");
            	}            	
            	LoginPage slogIn=new LoginPage(driver); 
            	slogIn.loginAs("sectest102", "Password88");

            	WebElement element = driver.findElement(By.xpath(".//*[@id='container']/p"));
            	String strngAcc = element.getText();
            	
            	int elementsize=driver.findElements(By.xpath(".//*[@id='container']/p")).size();//ok
            	elementsize=driver.findElements(By.linkText("Cancel")).size();//ok
//            	elementsize=driver.findElements(By.cssSelector(".pageHeader")).size();//ok this is SECURE LOGIN. pls see css from firebug in the page
//            	strngAcc=driver.findElements(By.cssSelector(".pageHeader")).get(0).getText();//ok this is SECURE LOGIN. 
//            	List<WebElement> elements = driver.findElements(By.className("pageHeader")); //OK for SECURE LOGIN

//  stop here          	SecureLogin sLogin=new SecureLogin();
            	

            	
//            	elementsize=driver.findElements(By.cssSelector(".errors")).size()
//            	strngAcc=driver.findElements(By.cssSelector(".errors")).get(0).getText()
            	
            	
            	if(driver.findElements(By.xpath(".//*[@id='container']/p")).size() != 0){
            		System.out.println("Element is Present"+driver.findElements(By.xpath(".//*[@id='container']/p")).size());
            		}else{
            		System.out.println("Element is Absent"+driver.findElements(By.xpath(".//*[@id='container']/p1")).size());
            		}
            	
            	
//            	if (element[text()='Please enter your answer to the password question.']){
//            		System.out.println(elementsize);
//            	}else{
//            		System.out.println(elementsize);
//            	}
            	System.out.println(strngAcc);//"Please enter your answer to the password question."
//        		assertTrue(isElementPresent(By.id("accountNickname")));
//            	asserTrue(element.isDisplayed());
            	Assert.assertEquals("Please enter your answer to the password question.", strngAcc); 
            	
//            	verifyElementPresent(driver.findElement(By.xpath(".//*[@id='container']/p")));
            	
            	
//            	SecureLogin securePage= new SecureLogin(driver); //ok
//            	securePage.Login("2002"); //ok


            	
            	
            	String pageTitle=driver.getTitle();
//            	if (!"TD Auto Finance - Manage Your Account".equals(driver.getTitle())){
//            		 throw new IllegalStateException("This is not the Account Summary page");
//            	}
//        		driver.findElement(By.linkText("LOG OUT")).click();
//            	if (!"TD Auto Finance - Log Out".equals(driver.getTitle())){
//              		 throw new IllegalStateException("This is not the Log Out page");
//               	}  
//                driver.findElement(By.id("logo")).click();
//            	".//*[@id='header']/map/area[1]"
          		driver.findElement(By.xpath(".//*[@id='header']/map/area[1]")).click(); // OK: area[1] to [4] Main Nav.
          		pageTitle=driver.getTitle();
            	if (!"TD Auto Finance - Log In".equals(driver.getTitle())){
           		 throw new IllegalStateException("This is not the Log In page");
            	}
          		String usernameid = "sectest102";
          		String PS="Password88";
          		String PSAnswer="2002";
          		System.out.println("Username is " + usernameid);
          		//driver.findElement(By.id("username")).clear();
          		driver.findElement(By.id("username")).sendKeys(usernameid);
          		driver.findElement(By.id("password")).clear();
          		driver.findElement(By.id("password")).sendKeys(PS);
          		driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
          		driver.findElement(By.id("secretAnswer")).sendKeys(PSAnswer);
          		driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
          		
          		String allPageCode=driver.getPageSource();
          		Boolean textExist=allPageCode.contains("Everything you need to quickly and easily manage your account is located right here.");
          		textExist=allPageCode.contains("We offer several different payment options to pay your bill (online, mail, phone and automatic payments).");//®).");
          		if (textExist) {
          			System.out.println("Text exists");
          			uatResult.rwExcel(true, "Verify content in Account Summary page - Right Rail", "We offer several different payment options to pay ...", fileNamePath); 	
          		}
          		else {
          			System.out.println("Text does not exist");
                   	uatResult.rwExcel(false, "Verify content in Account Summary page - Right Rail", "We offer several different payment options to pay ...", fileNamePath); 
                   	uatResult.rwExcel(false, "Try to log into Manage Your Account", "Account Summary Page does not show", fileNamePath); 
          			
                   	textExist=driver.findElement(By.id("secretAnswer")).isEnabled();
              		if (textExist) {
                       	uatResult.rwExcel(false, "Submit with security password", "It still loads the Security Login page when should load Account Summary page.", fileNamePath); 
                       	uatResult.rwExcel(false, "Login fails", "Account Summary page does not show. Probably due to CFWS Web Services is down!", fileNamePath); 
              		}
              		else {
                      	uatResult.rwExcel(false, "Submit with security password", "It loads unknown page when should load Account Summary page.", fileNamePath); 
                       	uatResult.rwExcel(false, "Login fails", "Account Summary page does not show. Probably due to CFWS Web Services is down!", fileNamePath); 
              		}	
                   	System.out.println("Probably the TDAF cfweb is down!");
            		driver.quit();
                   	return;
          		}
          		MyClass2 t = new MyClass2(8);
          		t.info(6);
          		t.info();
          		t.height=9;
          		System.out.println(t.width="222");
          		sId="defaultAccountNumber";
          		sAccount="1100553406";
          		
    			//new Select(driver.findElement(By.id("defaultAccountNumber"))).selectByVisibleText("1100025439");
    			new Select(driver.findElement(By.id(sId))).selectByVisibleText(sAccount);
          		driver.findElement(By.xpath("//div[@id='contentContainer']/map/area[2]")).click(); //works
          		//List<WebElement> Clips = driver.findElements(By.cssSelector("button")); //works
          		//List<WebElement> Clips = driver.findElements(By.linkText("View Payment Details"));//works for PmtHsty
          		//List<WebElement> Clips = driver.findElements(By.linkText("View Payment Details"));
          		//List<WebElement> Clips = driver.findElements(By.className("maxWidth"));
          		//List<WebElement> Clips = driver.findElements(By.id("autopayBtn")); //works
          		//int countnumber=Clips.size();//Return number of elements
          		//Clips.get(1).click();
          		List<WebElement> ClipsCheckBox = driver.findElements(By.cssSelector("button"));
          		int numGO=ClipsCheckBox.size();
          		List<WebElement> numText = driver.findElements(By.cssSelector("p"));
          		numGO=numText.size();
          		String iContent=numText.get(2).getText(); //Enroll in automatic payments today, and your monthly scheduled payment of $422.24 will be debited on your scheduled payment due date.
          		if (numGO>=2 & iContent.equals("Enroll in automatic payments today, and your monthly scheduled payment of $422.24 will be debited on your scheduled payment due date." )) 
//           		if (numGO>=2) 
          		{          		 		
          			ClipsCheckBox.get(1).click();// ok if it is a button
          		}
          		//WebElement butn=driver.findElement(By.xpath(".//*[@id='oneTimeBtn']/span/em/button"));
          		WebElement butn=driver.findElement(By.cssSelector("button"));
          		//String BN=butn.getAttribute("id");
          		WebElement butn1=driver.findElement(By.xpath(".//*[@id='autopayBtn']/span/em/button"));
          		String TN=butn.getTagName();
          		String TN1=butn1.getTagName();
          				//driver.findElement(By.xpath(".//*[.='tt']")).isDisplayed();
          		
          		String accuntnumber="1001973718";
    			new Select(driver.findElement(By.id("defaultAccountNumber"))).selectByVisibleText(accuntnumber);
        		driver.findElement(By.xpath(".//*[@id='defaultAccountNumber']")).click();//useless
        		String PageTitle="xxx";
        		PageTitle=driver.getCurrentUrl();
        		driver.findElement(By.xpath("//div[@id='contentContainer']/map/area[2]")).click();
        		//new Select(driver.findElement(By.id("defaultAccountNumber"))).selectByValue("1100023692");

    			//new Select(driver.findElement(By.id("defaultAccountNumber"))).selectByVisibleText("1100075601");
        		driver.findElement(By.xpath("//div[@id='contentContainer']/map/area[2]")).click();
        		driver.findElement(By.xpath(".//*[@id='autopayBtn']/span/em/button")).click();
        		driver.findElement(By.xpath("//div[@id='contentContainer']/map/area[2]")).click();
        		driver.findElement(By.xpath(".//*[@id='oneTimeBtn']/span/em/button")).click();
        		/*if Account Nickname field exist {
        		1. enter Account nickname
        		2. select Account Type
        		3. enter routing number
        		4. enter bank account number
        		5. enter email address
        		6. enter confirm email address
        		7. assertTrue(isElementPresent(By.id(byId)));
        		}      		
        		*/
        		
        		boolean fieldExist;
        		/*
        		try { 
        		fieldExist = (driver.findElement(By.id("accountNickname")).isDisplayed()); 

        		} catch (NoSuchElementException e){
        			fieldExist=false;
        		}
				*/
        		String byId1="accountNickname";
        		MyClass2 OLP=new MyClass2();
        		
        		fieldExist=OLP.isElementPresent(byId1, driver);
        		//assertTrue(isElementPresent(By.id("accountNickname")));
        		if (fieldExist) {
        			fieldExist=OLP.AddBankProfile(driver);
        			if (fieldExist){
        				System.out.println("Passed to add Bank Profile in One Time Payment Page");
                 		uatResult.rwExcel(true, "Make A Payment Step 1","Passed to add Bank Profile in One Time Payment Page", fileNamePath);
        			}
        			else{
        				System.out.println("Failed to add Bank Profile in One Time Payment Page");
            			uatResult.rwExcel(false, "Make A Payment Step 1","Passed to add Bank Profile in One Time Payment Page", fileNamePath);
        			}
        		}	
        		else{
        			System.out.println("Already has the Bank Profile in One Time Payment Page");
          			uatResult.rwExcel(true, "Make A Payment Step 1","Already has the Bank Profile in One Time Payment Page", fileNamePath);
        		}

        		driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
        		driver.findElement(By.linkText("Back")).click();
    			driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
        		driver.findElement(By.cssSelector("button")).click();

        		driver.findElement(By.linkText("Pending Online Payments")).click();

        		driver.findElement(By.linkText("Delete")).click();

        		driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();

        		//driver.findElement(By.cssSelector("button")).click();
        		//driver.findElement(By.id("paymentAmount")).clear();
        		//driver.findElement(By.id("paymentAmount")).sendKeys("1.00");
        		//driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
        		//driver.findElement(By.cssSelector("button")).click();
        		//driver.findElement(By.linkText("Pending Online Payments")).click();
        		//driver.findElement(By.linkText("Delete")).click();
        		driver.findElement(By.linkText("LOG OUT")).click();
        		System.out.println("Test is complete!");
        		driver.quit();
          		
            }
            
            public static void startBrowserQA()
            {
            	String sId;
            	String sAccount;
           	
            	WebDriver driver = new FirefoxDriver();
            	/*FirefoxProfile firefoxProfile = new FirefoxProfile();
            	WebDriver driver = new FirefoxDriver(firefoxProfile);
            	WebDriver driver = new InternetExplorerDriver();*/
            	driver.manage().window().maximize();
            	String URL="http://qa-cfui.autodata.net/home.do";
            	//String URL="https://uat.tdautofinance.com";
            	//driver.get("http://qa-cfui.autodata.net/home.do");
            	driver.get(URL);            	
                  //WebElement el = driver.findElement(zipBoxLocator);
                  //el.click();          	
            	
                  driver.findElement(By.id("logo")).click();
          		String usernameid = "sectest1000";
          		System.out.println("Username is " + usernameid);
          		//driver.findElement(By.id("username")).clear();
          		driver.findElement(By.id("username")).sendKeys(usernameid);
          		driver.findElement(By.id("password")).clear();
          		driver.findElement(By.id("password")).sendKeys("Password88");
          		driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
          		String allPageCode=driver.getPageSource();
          		Boolean textExist=allPageCode.contains("Everything you need to quickly and easily manage your account is located right here.");
          		textExist=allPageCode.contains("We offer several different payment options to pay your bill (online, mail, phone and AUTO-PAY");//®).");
          		if (textExist) {
          			System.out.println("Text exists");
          		}
          		else {
          			System.out.println("Text does not exist");
          		}
          		MyClass2 t = new MyClass2(8);
          		t.info(6);
          		t.info();
          		t.height=9;
          		System.out.println(t.width="222");
          		
          		
          		
          		sId="defaultAccountNumber";
          		sAccount="1007311959";//in QA
          		
    			//new Select(driver.findElement(By.id("defaultAccountNumber"))).selectByVisibleText("1100025439");
    			new Select(driver.findElement(By.id(sId))).selectByVisibleText(sAccount);
          		driver.findElement(By.xpath("//div[@id='contentContainer']/map/area[2]")).click(); //works
          		//List<WebElement> Clips = driver.findElements(By.cssSelector("button")); //works
          		//List<WebElement> Clips = driver.findElements(By.linkText("View Payment Details"));//works for PmtHsty
          		//List<WebElement> Clips = driver.findElements(By.linkText("View Payment Details"));
          		//List<WebElement> Clips = driver.findElements(By.className("maxWidth"));
          		//List<WebElement> Clips = driver.findElements(By.id("autopayBtn")); //works
          		//int countnumber=Clips.size();//Return number of elements
          		//Clips.get(1).click();
          		List<WebElement> ClipsCheckBox = driver.findElements(By.cssSelector("button"));
          		
          		//WebElement butn=driver.findElement(By.xpath(".//*[@id='oneTimeBtn']/span/em/button"));
          		WebElement butn=driver.findElement(By.cssSelector("button"));
          		//String BN=butn.getAttribute("id");
          		WebElement butn1=driver.findElement(By.xpath(".//*[@id='autopayBtn']/span/em/button"));
          		String TN=butn.getTagName();
          		String TN1=butn1.getTagName();
          				//driver.findElement(By.xpath(".//*[.='tt']")).isDisplayed();
          		
          		String accuntnumber="1100075601";
    			new Select(driver.findElement(By.id("defaultAccountNumber"))).selectByVisibleText(accuntnumber);
        		driver.findElement(By.xpath(".//*[@id='defaultAccountNumber']")).click();
        		String PageTitle="xxx";
        		PageTitle=driver.getCurrentUrl();
        		driver.findElement(By.xpath("//div[@id='contentContainer']/map/area[2]")).click();
        		//new Select(driver.findElement(By.id("defaultAccountNumber"))).selectByValue("1100023692");

    			//new Select(driver.findElement(By.id("defaultAccountNumber"))).selectByVisibleText("1100075601");
        		driver.findElement(By.xpath("//div[@id='contentContainer']/map/area[2]")).click();
        		driver.findElement(By.xpath(".//*[@id='autopayBtn']/span/em/button")).click();
        		driver.findElement(By.xpath("//div[@id='contentContainer']/map/area[2]")).click();
        		driver.findElement(By.xpath(".//*[@id='oneTimeBtn']/span/em/button")).click();

        		driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
        		driver.findElement(By.linkText("Back")).click();
    			driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
        		driver.findElement(By.cssSelector("button")).click();

        		driver.findElement(By.linkText("Pending Online Payments")).click();

        		driver.findElement(By.linkText("Delete")).click();

        		driver.findElement(By.cssSelector("button[type=\"submic\"]")).click();

        		//driver.findElement(By.cssSelector("button")).click();
        		//driver.findElement(By.id("paymentAmount")).clear();
        		//driver.findElement(By.id("paymentAmount")).sendKeys("1.00");
        		//driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
        		//driver.findElement(By.cssSelector("button")).click();
        		//driver.findElement(By.linkText("Pending Online Payments")).click();
        		//driver.findElement(By.linkText("Delete")).click();
        		driver.findElement(By.linkText("LOG OUT")).click();
        		System.out.println("Test is complete!");
        		driver.quit();
          		
            }
            
 
            public static void main(String [] args) throws IOException
            {
           	
                  startBrowserUAT ();

                  try {
                	  for (int i=1;i<=100;i++){
                		  System.out.println("Sleep begin! i="+i);
                		  Thread.sleep(30000);//sleep for 10000 ms
                		  Thread.interrupted();
                		  System.out.println("After sleeping!");
                		  startBrowserUAT ();
                	  }
                	  //except KeyboardInterrupt:
                	  System.out.println("Test is interrupted by others! In Try statement");
                  }
                	catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
	        		System.out.println("Test is interrupted by others! Catched");
				} 
                  


                  //startBrowserQA ();                  

            }

      }
