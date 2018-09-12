



import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class MakeAPaymentConfirm {
	private final WebDriver driver;
	public MakeAPaymentConfirm(WebDriver driver){
		this.driver=driver;
	   	if (!"TD Auto Finance - Make a Payment - Confirm Payment".equals(driver.getTitle()))
	   	{
		 throw new IllegalStateException("This is not the Make a Payment Confirm page");
	   	}
	}

	By logoutLocator=By.className("logout");//same
	By confirmBtnLocator=By.id("confirmBtn");	
	
    public MakeAPaymentThankYou clickConfirm() {
    	driver.findElement(confirmBtnLocator).click();

    	return new MakeAPaymentThankYou(driver);
    }
	
	
	public Logout ClickLogout(WebDriver driver, String browser){
//		"LOG OUT"
		driver.findElement(logoutLocator).click(); 
//		if (browser.equalsIgnoreCase("IE"))
//			driver.findElement(logoutLocator).click(); 
		//Synchronization needed here
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); //works
    	return new Logout(driver);
	}
}