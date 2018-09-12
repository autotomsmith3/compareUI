import java.util.concurrent.TimeUnit;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class MakeAPayment {
	private final WebDriver driver;
	public MakeAPayment(WebDriver driver){
		this.driver=driver;
	   	if (!"TD Auto Finance - Make a Payment - Profile Established".equals(driver.getTitle()))
	   	{
		 throw new IllegalStateException("This is not the Make a Payment with Profile page");
	   	}
	}
//	By olpSubLocator=By.className("subLinks").findElements(By.cssSelector("area shape").findElement(By.cssSelector("href")).g
	By makeAPaymentSubLocator=By.xpath("//div[@id='contentContainer']/map/area[2]");	
//	driver.findElement(By.xpath("//div[@id='contentContainer']/map/area[2]")).click(); //works
	By oneTimePayGOLocator=By.id("oneTimeBtn");
	By autoPayGOLocator=By.id("oneTimeBtn");
	By submitBtnLocator=By.cssSelector("button[type=\"submit\"]");
	
	
    public MakeAPaymentConfirm stepOneClickSubmit() {
//    	
//    	List<WebElement> elements = driver.findElements(By.className("subLinks"));
//    	elements.get(2).click();
    	
    	driver.findElement(submitBtnLocator).click();
//    	driver.findElement(loginButtonLocator).click(); //work? try later
    	return new MakeAPaymentConfirm(driver);
    }
}

//*************http://code.google.com/p/selenium/issues/detail?id=2354
//List<WebElement> elements = driver.findElements(By.cssSelector("map area"));
//elements.get(1).click();
//wait.until(ExpectedConditions.titleIs("HTML Station--Welcome"));
//driver.quit();

//<div id="mainContent">
//<div id="content">
//	
//  <div id="contentContainer">
//    
//			*****************Main Navs*****************
//
//  </div>
//	
//  <map name="pageLinks">
//      <area shape="rect" coords="0,0,202,30" href="/myaccount/accountSummary.do" />
//      <area shape="rect" coords="220,0,296,30" href="/faqGlossaryLanding.page" />
//      <area shape="rect" coords="313,0,412,30" href="/aboutUsLanding.page" />
//      <area shape="rect" coords="429,0,546,30" href="/myaccount/smc/contactUs.do" />
//  </map>
//	
//
//<div class="subLinks">
//			*****************Sub Navs*****************
//<map name="tabNavMap">
//<area shape="rect" coords="3,3,97,38" href = "/myaccount/accountSummary.do" />
//<area shape="rect" coords="97,3,192,38" href = "/myaccount/makePayment.do" />
//<area shape="rect" coords="193,3,285,38" href = "/myaccount/paymentHistory.do" />
//<area shape="rect" coords="286,3,369,38" href = "/myaccount/payoffQuote.do" />
//<area shape="rect" coords="370,3,486,38" href = "/myaccount/statements.do" />
//<area shape="rect" coords="487,3,608,38" href = "/myaccount/manageYourProfile.do" />
//<area shape="rect" coords="609,3,719,38" href = "/myaccount/smc/messages.do" />
//</map>