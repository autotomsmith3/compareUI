import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class FAQsPage extends ZljLibrary{
	private final WebDriver driver;
	public FAQsPage(WebDriver driver) throws IOException{
		this.driver=driver;
	   	if (!"TD Auto Finance - FAQs".equals(driver.getTitle()))
	   	{
	   		rwExcel(false, "Try to load FAQs page", "But page title is not the TD Auto Finance - FAQs"); 
		 throw new IllegalStateException("This is not the TD Auto Finance - FAQs");
	   	}
	}
	By secretAnswerLocator = By.id("secretAnswer");
	By loginButtonLocator = By.cssSelector("button[type=\"submit\"]");
	By cancelLinkLocator=By.linkText("Cancel");
	By secureLoginLocator=By.className("pageHeader");
	


    
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
	

}