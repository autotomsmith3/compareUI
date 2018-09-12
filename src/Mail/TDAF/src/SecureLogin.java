import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class SecureLogin extends ZljLibrary{
	private final WebDriver driver;
//	public SecureLogin(WebDriver driver) throws IOException{
//		this.driver=driver;
////	   	if (!"TD Auto Finance - Forgot Password - Question & Answer".equals(driver.getTitle()))  //this is correct in prod
//		if (!"TD Auto Finance - Information Needed For Returning User".equals(driver.getTitle())) //this is wrong in RWD site	   		
//	   	{
//	   		rwExcel(false, "Try to load Secure Login page", "But page title is not the Auto Finance - Forgot Password - Question & Answer"); 
//		 throw new IllegalStateException("This is not the Secure Login page");
//	   	}
//	}
	
	public SecureLogin(WebDriver driver) throws IOException{
		this.driver=driver;
//		String wh1=driver.getWindowHandle();
		String sPageTitle="TD Auto Finance - Information Needed For Returning User";
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
		
	By secretAnswerLocator = By.id("secretAnswer");
//	By loginButtonLocator = By.cssSelector("button[type=\"submit\"]");
	By loginButtonLocator = By.cssSelector("button[class=\"btn btn-default\"]");
	By cancelLinkLocator=By.linkText("Cancel");
	By secureLoginLocator=By.className("pageHeader");
	
	public SecureLogin typePasswordAnswer(String passwordAnswer){
		driver.findElement(secretAnswerLocator).sendKeys(passwordAnswer);
		return this;
	}
	
    public AccountSummary Login(String passwordAnswer) throws IOException {
    	typePasswordAnswer(passwordAnswer);
        return submitLogin(); 
    }
	
    public AccountSummary submitLogin() throws IOException {
    	driver.findElement(loginButtonLocator).submit();
//    	driver.findElement(loginButtonLocator).click(); //work? try later. Doesn't work on IE
    	return new AccountSummary(driver);
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
    	
    	VerifyTextLink(driver,"Cancel");
    	VerifyTextLink(driver,"Site Map");
    	VerifyTextLink(driver,"Read More");
    	VerifyTextLink(driver,"Learn More"); //rwd is not a link. need to update
    	VerifyTextLink(driver,"Privacy");
    	VerifyTextLink(driver,"Terms of Use");
    	VerifyTextLink(driver,"Supported Browsers");
    	
    	if(linkExist){
    		System.out.println("Cancel link is Visible");
    		rwExcel(true, "Try to verify text link - Cancel", "Cancel link visible"); 
    		}else{
    		System.out.println("Cancel link is InVisible");
    		rwExcel(false, "Try to verify text link - Cancel", "Cancel link is invisible"); 
    		}

	}
	

}