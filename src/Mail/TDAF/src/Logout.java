import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Logout extends ZljLibrary{

	private final WebDriver driver;
//	public Logout(WebDriver driver)throws IOException{
//		ZljLibrary lg=new ZljLibrary();
//		lg.Wait(2);
//		this.driver=driver;
//	   	if (!"TD Auto Finance - Log Out".equals(driver.getTitle()))
//	   	{
//	   		rwExcel(false, "Try to load Log Out page", "But page title is not the TD Auto Finance - Log Out"); 
//		 throw new IllegalStateException("This is not the Log Out page");
//	   	}
//	}
	public Logout(WebDriver driver) throws IOException{
		this.driver=driver;
		String sPageTitle="TD Auto Finance - Log Out";
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
	By logoutLinkLocator=By.cssSelector("a.logout > small");
	By logoutTitle=By.className("pageHeader");
//	<div id="pageHeader" class="pageHeader">Logged Out</div>
	public void VerifyContents() throws IOException{
    	List<WebElement> elements = driver.findElements(By.className("pageHeader")); 
    	int elementsize=elements.size();
    	String contentTitle = driver.findElement(logoutTitle).getText();
    	if(contentTitle.equals("LOGGED OUT")){
    		System.out.println("LOGGED OUT is Visible. pageHeader Size="+elementsize);
    		rwExcel(true, "Try to load Log Out page", "Page content title is LOGGED OUT"); 
    		}else{
    		System.out.println("LOGGED OUT is InVisible. pageHeader Size="+elementsize);
    		rwExcel(false, "Try to load Log Out page", "But page content title is not LOGGED OUT"); 
    		}    	
	} 	
 
	
}

//public void VerifyLinksAndContents() throws IOException {
//	List<WebElement> elements = driver.findElements(By.className("pageHeader")); 
//	int elementsize=elements.size();
//	String strngAcc = driver.findElement(secureLoginLocator).getText();
//	if(strngAcc.equals("SECURE LOGIN")){
//		System.out.println("SECURE LOGIN is Visible. pageHeader Size="+elementsize);
//		rwExcel(true, "Try to verify text link", "Text link shows"); 
//		}else{
//		System.out.println("SECURE LOGIN is InVisible. pageHeader Size="+elementsize);
//		rwExcel(false, "Try to verify text link", "Text link does not show properly"); 
//		}    	
//
//	boolean linkExist=driver.findElement(cancelLinkLocator).isDisplayed();