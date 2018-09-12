import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class MakeAPaymentNoProfile extends ZljLibrary{
	private final WebDriver driver;
	public MakeAPaymentNoProfile(WebDriver driver)throws IOException{
		this.driver=driver;
	   	if (!"TD Auto Finance - Make a Payment - No Profile Established".equals(driver.getTitle()))
	   	{
	   		rwExcel(false, "Try to load OLP No Profile page", "But page title is not the TD Auto Finance - Make a Payment - No Profile Established"); 
		 throw new IllegalStateException("This is not the Make a Payment No Profile page");
	   	}
	}
//	By logoutLinkLocator=By.linkText("LOG OUT");//IE8,9 not working properly, shows clicked but not clicked
	By logoutLocator=By.className("logout");//same
	By makeAPaymentSubLocator=By.xpath("//div[@id='contentContainer']/map/area[2]");	
//	driver.findElement(By.xpath("//div[@id='contentContainer']/map/area[2]")).click(); //works
	
	
    public MakeAPayment clickGO() {
    	driver.findElement(makeAPaymentSubLocator).click();
//    	driver.findElement(loginButtonLocator).click(); //work? try later
    	return new MakeAPayment(driver);
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