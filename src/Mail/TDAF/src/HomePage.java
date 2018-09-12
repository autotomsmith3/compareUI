//************************************
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HomePage extends ZljLibrary{
	private final WebDriver driver;
	final int wt_Secs=0;
	public HomePage(WebDriver driver) throws IOException{
		this.driver=driver;
	   	if (!"TD Auto Finance - Home".equals(driver.getTitle()))
	   	{
	   		rwExcel(false, "Try to load Home page", "But page title is not the Auto Finance - Home"); 
		 throw new IllegalStateException("This is not the Home page");
	   	}
	}
	By tdLogLocator = By.cssSelector("img[alt=\"TD Auto Finance\"]");
	By manageYourAccountLocator =By.xpath(".//*[@id='navbar-collapse-header']/ul[1]/li[1]/a");
	//By.linkText("Manage Your //By.xpath(".//*[@id='navbar-collapse-header']/ul[2]/li[1]/a");//By.linkText("Manage Your Account");
	By faqsLocator = By.xpath(".//*[@id='navbar-collapse-header']/ul[1]/li[2]/a");//By.linkText("FAQs");
	By aboutUsLocator = By.xpath(".//*[@id='navbar-collapse-header']/ul[1]/li[3]/a");//By.linkText("About Us");
	By contacUsLocator = By.xpath(".//*[@id='navbar-collapse-header']/ul[1]/li[4]/a");//By.linkText("Contact Us");
	By announcementsLocator = By.xpath(".//*[@id='wrap']/div/section/article/ul/li[2]/a");//By.linkText("Announcements");
	By helpfulLocator = By.xpath(".//*[@id='wrap']/div/section/article/ul/li[1]/a");//By.linkText("Helpful Links");
	By usernameLocator = By.id("username");
	By passwordLocator = By.id("password");
	By goLocator = By.cssSelector("#loginForm > button.btn.btn-default");
	By securityLinkLocator=By.linkText("security");
	By menuButtonHeadLocator = By.cssSelector("button.navbar-toggle");
	By menuButtonFootLocator = By.cssSelector("div.navbar-left.clearfix > button.navbar-toggle");

//	By  ;
//	By Locator
//	By Locator
//	By Locator
//	By Locator
	
	public HomePage clickTDLog(){
		driver.findElement(tdLogLocator).click();
		return this;
	}
	public HomePage clickMenuButtonHeader(){
		driver.findElement(menuButtonHeadLocator).click();
		return this;
	}
	public HomePage clickMenuButtonFooter(){
		driver.findElement(menuButtonFootLocator).click();
		return this;
	}
	public HomePage clickMenuButtonHeaderIfExist(String eqpmt){

    	if ((eqpmt.equalsIgnoreCase("tablet"))|| (eqpmt.equalsIgnoreCase("smartphone-iphone4s"))){
    		clickMenuButtonHeader();
    		Wait(3);
    	}	
		return this;
	}
	public HomePage clickMenuButtonFooterIfExist(String eqpmt){ // this no longer exists 
		
    	if ((eqpmt.equalsIgnoreCase("tablet"))|| (eqpmt.equalsIgnoreCase("smartphone-iphone4s"))){
    		clickMenuButtonFooter();
    		Wait(3);
    	}	
		return this;
	}
	public HomePage typeUsername(String username){
		driver.findElement(usernameLocator).sendKeys(username);
		return this;
	}
	public HomePage typePassword(String password){
		driver.findElement(passwordLocator).sendKeys(password);
		return this;
	}
    public SecureLogin submitLogin() throws IOException {
        driver.findElement(goLocator).submit();
		//driver.findElement(secretAnswerLocator).sendKeys("2002");     
        //driver.findElement(loginButtonLocator).submit();		
        //return new HomePage(driver);    
        return new SecureLogin(driver); 
        }		
    public SecureLogin clickGO() throws IOException {
    	driver.findElement(goLocator).submit();
//    	driver.findElement(loginButtonLocator).click(); //work? try later
    	return new SecureLogin(driver);
    	}	

    public SecureLogin loginAs(String username, String password) throws IOException {
        typeUsername(username);
        typePassword(password);
        return submitLogin();
    }
    
    public LoginPage submitLoginExpectingFailure() {
        driver.findElement(goLocator).submit();
		Wait(wt_Secs);
        return new LoginPage(driver);   
    }	
    public LoginPage clickMainNavManageYourAccount()throws IOException{
    	driver.findElement(manageYourAccountLocator).click();	
		Wait(wt_Secs);
    	return new LoginPage(driver);
    }
    public FAQsPage clickMainNavFAQs() throws IOException{
    	driver.findElement(faqsLocator).click();	
		Wait(wt_Secs);
    	return new FAQsPage(driver);
    }
    public AboutUsPage clickMainNavAboutUs() throws IOException{
    	driver.findElement(aboutUsLocator).click();	
		Wait(wt_Secs);
    	return new AboutUsPage(driver);
    } 
    public ContactUsPage clickMainNavContactUs() throws IOException{
    	driver.findElement(contacUsLocator).click();
		Wait(wt_Secs);
    	return new ContactUsPage(driver);
    } 
    public SecurityPage clickHomePageSecurityLink() throws IOException{
    	driver.findElement(securityLinkLocator).click();	
		Wait(wt_Secs);
    	return new SecurityPage(driver);
    } 
    
	public void VerifyLinksAndContents() throws IOException {
    	List<WebElement> elements = driver.findElements(By.className("pageHeader")); 
    	int elementsize=elements.size();
    	String strngAcc = driver.findElement(secureLoginLocator).getText();
    	if(strngAcc.equals("SECURE LOGIN")){
    		System.out.println("SECURE LOGIN is Visible. pageHeader Size="+elementsize);
    		rwExcel(true, "Try to verify text link", "Text link shows"); 
    		}else{
    		System.out.println("SECURE LOGIN is InVisible. pageHeader Size="+elementsize);
    		rwExcel(false, "Try to verify text link", "Text link does not show properly"); 
    		}    	

    	boolean linkExist=driver.findElement(cancelLinkLocator).isDisplayed();
    	
    	VerifyTextLink(driver,"Cance l");
    	VerifyTextLink(driver,"Site Map");
    	VerifyTextLink(driver,"Read More");
    	VerifyTextLink(driver,"Learn More");
    	VerifyTextLink(driver,"Privacy");
    	
    	if(linkExist){
    		System.out.println("Cancel link is Visible");
    		rwExcel(true, "Try to verify text link - Cancel", "Cancel link visible"); 
    		}else{
    		System.out.println("Cancel link is InVisible");
    		rwExcel(false, "Try to verify text link - Cancel", "Cancel link is invisible"); 
    		}

    	

		
	
	}
	

	
	
	   public static void main(String [] args)throws IOException
	    {	
		   final int wt_Secs=6;
	    	String subject0, content1, content2, content3, content4, content5=null;
	    	String [] browsers=new String[2];
	    	browsers[1]="firefox";
//	    	browsers[2]="ie";//click the sub nav using area causes problem, 4/01: load ie with erro in 245 zljlib
	    	browsers[0]="Chrome";   // works on rwd site
	    	String [] eqpmts=new String[3];
	    	eqpmts[0]="pc";
	    	eqpmts[1]="tablet";
	    	eqpmts[2]="smartphone-iphone4s";
	    for (int ctu=0;ctu<200;ctu++) {	
	       	for (String browser: browsers){
	       	for (String eqpmt:eqpmts){	
	       	ZljLibrary td = new ZljLibrary();
	       	td.rwExcel((ctu+1)+"  Round(s). "+"   Home page", browser+" -- "+eqpmt+" --QA testing");
	       	td.rwExcel(true, "OLP test", "Make A Payment Step 1");
//	       	td.rwExcel(false, "OLP test", "Make A Payment Step 1 - this is only fake msg!"); 	
	       	WebDriver driver=td.drivers(browser);
	       	
//	       	http://assertselenium.com/2013/01/29/webdriver-wait-commands/
	       	
//	       	WebDriverWait _wait = new WebDriverWait(driver,wt_Secs);
	        driver.manage().timeouts().implicitlyWait(wt_Secs, TimeUnit.SECONDS); //will wait longer
//	        driver.manage().timeouts().pageLoadTimeout(wt_Secs, TimeUnit.SECONDS); //will wait wt_Secs, exceeding it will abort
//	        WebDriverWait wait = new WebDriverWait(driver, 60); //not try yet
//	        WebElement o_element = wait.until(ExpectedConditions.elementToBeClickable(By.id("Object Id"))); //not try yet

//	        driver.manage().timeouts().setScriptTimeout(100,TimeUnit.SECONDS); //not really work

	        
	        

	       	if (driver==null){
	       		System.out.println("Browser name is wrong!");
	       		//Exit now;
	       		return ;
	       	}
			
			td.SelecBroswerResolution(driver,eqpmt);
//			td.SelecBroswerResolution(driver,"tablet");
//			td.SelecBroswerResolution(driver,"smartphone-iphone4s");
//			td.SelecBroswerResolution(driver,"smartphone-s");//wrong device
	   	
//	    	WebDriver driver = new FirefoxDriver();
//	    	WebDriver driver = new ChromeDriver();
//	       	File file=new File("C:/Documents and Settings/zhoul/My Documents/eclipse/IE/IEDriverServer.exe");
//	       	System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
//	    	WebDriver driver = new InternetExplorerDriver();
//	    	String URL="https://uat.tdautofinance.com";
	    	String URL="https://qa-cfui.autodata.net/home.do";
//	    	driver.get("https://qa-cfui.autodata.net/home.do");
	    	driver.get(URL);   

	    	HomePage rwdHome =new HomePage(driver);
	    	rwdHome.clickMenuButtonHeaderIfExist(eqpmt);
	    	rwdHome.clickMainNavManageYourAccount();
	    	rwdHome.clickTDLog();
	    	rwdHome.clickMenuButtonHeaderIfExist(eqpmt);
//	    	rwdHome.clickMainNavFAQs();
//	    	rwdHome.clickMenuButtonHeaderIfExist(eqpmt);
//	    	rwdHome.clickMainNavAboutUs();
	    	rwdHome.clickTDLog();
	    	rwdHome.clickMenuButtonHeaderIfExist(eqpmt);
	    	rwdHome.clickMainNavContactUs();
	    	rwdHome.clickMenuButtonHeaderIfExist(eqpmt);
	    	rwdHome.clickTDLog();
	    	rwdHome.clickHomePageSecurityLink();
	    	rwdHome.clickTDLog();
	    	rwdHome.loginAs("sectest1000", "Password88");
	    	td.Wait(2);
	    	SecureLogin SL=new SecureLogin(driver);
//	    	rwdHome.clickMenuButtonFooterIfExist(eqpmt); //this no longer exists.
	    	rwdHome.clickMenuButtonHeaderIfExist(eqpmt);	    	
	    	SL.VerifyLinksAndContents();
	       	td.GoBack(driver);
	       	td.rwExcel(true, "SECURE LOGIN", "Links and contents are displayed");
	       	td.GoForward(driver);
	       	SL.Login("2002");
			AccountSummary AS=new AccountSummary(driver);
	       	td.rwExcel(true, "Account Summary", "We are in Account Summary page");
			System.out.println("Account Summary page is Visible");
			


			String act="1100395881";
			boolean actExist=false;
			if ((eqpmt.equalsIgnoreCase("tablet"))|| (eqpmt.equalsIgnoreCase("smartphone-iphone4s"))){
//			if ((eqpmt.equalsIgnoreCase(eqpmts[1])) || (eqpmt.equalsIgnoreCase(eqpmts[2]))){
				actExist=td.mobileSelectAccount(driver, act);
			}else{
				actExist=td.SelectAccount(driver, act);
			}
			if (actExist) {
		       	td.rwExcel(true, "Account Summary", "Selecting account#: "+act+" ");
			}else{
		       	td.rwExcel(false, "Account Summary", "Selecting account#: "+act+" is not successful. Ensure this account is associated with the prfoile.");
		       	return;
			}
			//
//	    	driver.quit();
	    	
//			Todo:
	    	
//			AS.ClickMakeAPaymentSubNav(driver, browser);
//			OLPDecision DL=new OLPDecision(driver);
//			DL.clickOneTimePayGO();
//			MakeAPayment MP=new  MakeAPayment(driver);
//			MP.stepOneClickSubmit();
//			MakeAPaymentConfirm MPC=new MakeAPaymentConfirm(driver);
//			MPC.clickConfirm();
//			MakeAPaymentThankYou MPTHX=new MakeAPaymentThankYou(driver);
//			td.Wait(2);
////			new MailReader("Your Recent Payment");
//	        subject0="Your Recent Payment"; 
//	        content1="Dear FN313107 LN313107,"; 
//	        content2="Thank you for scheduling your TD Auto Finance payment online. Your transaction information is listed below."; 
//	        content3="Payment Amount:"; 
//	        content4="Please allow 1 business day for payment processing and 2 business days for your payment to appear in your online payment history."; 
//	        content5="tdautofinance.com"; 
//	         
//	       new MailReader(subject0, content1, content2,content3,content4,content5); 
//			
//			
//			DL.removePendingOnlinePayment();
//			td.Wait(2);
//			 subject0="Your Recently Deleted Payment";
//			 content1="Dear FN313107 LN313107,";
//			 content2="This message confirms that your scheduled online payment to TD Auto Finance has been deleted. Your transaction information is listed below.";
//			 content3="tdautofinance.com";
//			 content4="Thank you for choosing TD Auto Finance.";
//			 content5="Please note: Because email is not a secure method of communicating personal information, this email box does not accept replies. Please visit";
//			new MailReader(subject0, content1, content2,content3,content4,content5);
//			
//			
////			AccountSummary AS = new AccountSummary(driver);
////			td.rwExcel(true, "Account Summary",
////					"We are in Account Summary page");
////			System.out.println("Account Summary page is Visible");
//			// Logout
//			AS.ClickLogout(driver, browser);
//			Logout Lo = new Logout(driver);
//			Lo.VerifyContents();
//			System.out.println(browser + " Logged Out!");
			driver.close();
			td.rwExcel(true, browser + " test", "Browser Closed on " + browser);
	       	}
	       	}
	    }
//	      System.exit(2);
	        return;
	     
	    }	

}
