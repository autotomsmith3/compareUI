import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class MakeAPaymentThankYou {
	private final WebDriver driver;
	public MakeAPaymentThankYou(WebDriver driver){
		this.driver=driver;
	   	if (!"TD Auto Finance - Make a Payment - Thank You".equals(driver.getTitle()))
	   	{
		 throw new IllegalStateException("This is not the Make a Payment Thank You page");
	   	}
	}

	By logoutLocator=By.className("logout");//same

	
	
    public MakeAPayment clickGO() {
    	driver.findElement(logoutLocator).click();

    	return new MakeAPayment(driver);
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