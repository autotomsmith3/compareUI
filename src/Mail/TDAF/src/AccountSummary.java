import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AccountSummary extends ZljLibrary {
	private final WebDriver driver;

//	public AccountSummary(WebDriver driver)throws IOException {
//		this.driver = driver;
//		if (!"TD Auto Finance - Manage Your Account".equals(driver.getTitle())) {
//			rwExcel(false, "Try to load Account Summary page", "But page title is not the TD Auto Finance - Manage Your Account"); 
//			throw new IllegalStateException(
//					"This is not the Account Summary page");
//		}
//	}
	public AccountSummary(WebDriver driver) throws IOException{
		this.driver=driver;
		String sPageTitle="TD Auto Finance - Manage Your Account";
		boolean existTitle=TitleDisplay(driver, sPageTitle);
		if (existTitle){
			rwExcel(true, "Page Title is displayed", sPageTitle); 
		}else{
			rwExcel(false, "Page title is NOT showing properly. Exceed time limit!", "The page title is NOT - "+sPageTitle); 
		}
	   	if (!sPageTitle.equals(driver.getTitle()))
	   	{
		 throw new IllegalStateException("The page title is NOT - "+sPageTitle);
	   	}
	}	
	// By logoutLinkLocator=By.linkText("LOG OUT");//IE8,9 not working properly,
	// shows clicked but not clicked
	By logoutLocator = By.className("logout");// same
	By makeAPaymentSubLocator = By.xpath("//div[@id='contentContainer']/map/area[2]");
	//     FirePath found:                 .//*[@id='contentContainer']/map/area[2]
	

	public OLPDecision ClickMakeAPaymentSubNav(WebDriver driver, String browser) throws IOException {

		List<WebElement> elements = driver.findElements(By.cssSelector("area[shape=rect]")); // ok; <map name="pageLinks">
    	int elemSize=elements.size();

    	if (elemSize==6){
    		elements.get(0).click();
    		elements = driver.findElements(By.cssSelector("area[shape=rect]")); // ok; <map name="pageLinks">
        	elemSize=elements.size();
        	if (elemSize==11) {
        		elements.get(5).click();
        	}else {
        		System.out.println("elemSize= "+elemSize+". Click the Main Nav - Manage Your Account,But it looks like you have not logged in yet!" );
        	 	rwExcel(false, "elemSize= "+elemSize+". Click the Main Nav - Manage Your Account and try to load Make A Payment (OLP) Decision page", "But it looks like you have not logged in yet!"); 
        	}
    	}else if (elemSize==11) {
        	elements.get(5).click();
    	}else{
    		System.out.println("Try to load Make A Payment (OLP) Decision page but fails. elemSize= "+elemSize);
    	 	rwExcel(false, "Try to load Make A Payment (OLP) Decision page", "But it looks like you have not logged in yet!"); 
    	}
		
//		driver.findElement(makeAPaymentSubLocator).click(); // not working for IE
		// get title
		String pageTitle = driver.getTitle();
		if (pageTitle.equals("TD Auto Finance - Make a Payment")) {
			return new OLPDecision(driver);
		} else {
	       	rwExcel(false, "Try to load Make A Payment (OLP) Decision page", "But page title is NOT TD Auto Finance - Make a Payment"); 
			throw new IllegalStateException(
					"This is not the Make A Payment (OLP) Decision page");
			
		}

	}

	public Logout ClickLogout(WebDriver driver, String browser) throws IOException {
		// "LOG OUT"
		driver.findElement(logoutLocator).click();
		// if (browser.equalsIgnoreCase("IE"))
		// driver.findElement(logoutLocator).click();
		// Synchronization needed here
		// driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// //works
		return new Logout(driver);
	}
}
