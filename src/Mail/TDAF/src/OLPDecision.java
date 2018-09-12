import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class OLPDecision extends ZljLibrary{
	private final WebDriver driver;
	public OLPDecision(WebDriver driver)throws IOException{
		this.driver=driver;
	   	if (!"TD Auto Finance - Make a Payment".equals(driver.getTitle()))
	   	{
	   		rwExcel(false, "Try to load OLP Decision page", "But page title is not the TD Auto Finance - Make a Payment"); 
		 throw new IllegalStateException("This is not the Make a Payment (OLP) Decision page");
	   	}
	}

	By logoutLocator=By.className("logout");//same
	By makeAPaymentSubLocator=By.xpath("//div[@id='contentContainer']/map/area[2]");	
//	driver.findElement(By.xpath("//div[@id='contentContainer']/map/area[2]")).click(); //works
	By oneTimePayGOLocator=By.className("oneTimeBtn");
	By autoPayGOLocator=By.id("oneTimeBtn");
	By pendingOnlinePaymentLocator=By.linkText("Pending Online Payments");
	By deleteLinkLocator=By.linkText("Delete");
	By deleteBtnLocator= By.cssSelector("button[type=\"submit\"]");
	
    public MakeAPayment clickOneTimePayGO() {

    	List<WebElement> GOButtons = driver.findElements(By.cssSelector("button"));
    	GOButtons.get(0).click();
		
 	
//    	driver.findElement(oneTimePayGOLocator).click();//works.
//    	driver.findElement(loginButtonLocator).click(); //work? try later
    	return new MakeAPayment(driver);
    }
	
	public void removePendingOnlinePayment() throws IOException {
		driver.findElement(pendingOnlinePaymentLocator).click();
		while (VerifyTextLinkExist(driver, "Delete")) {
			driver.findElement(deleteLinkLocator).click();
			driver.findElement(deleteBtnLocator).click();
			driver.findElement(pendingOnlinePaymentLocator).click();
		}
    }
    
    
    
    
	
	public Logout ClickLogout(WebDriver driver, String browser) throws IOException{
//		"LOG OUT"
		driver.findElement(logoutLocator).click(); 
//		if (browser.equalsIgnoreCase("IE"))
//			driver.findElement(logoutLocator).click(); 
		//Synchronization needed here
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); //works
    	return new Logout(driver);
	}
}