import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.server.browserlaunchers.InternetExplorerLauncher;
import org.openqa.selenium.support.ui.Select;
public class LoginPage extends ZljLibrary{
	private final WebDriver driver;
	
	public LoginPage(WebDriver driver) throws IOException{
		this.driver=driver;
		String sPageTitle="TD Auto Finance - Log In";
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
	By tdLogLocator = By.cssSelector("img[alt=\"Auto Finance\"]");
	By manageYourAccountLocator = By.linkText("Manage Your Account");
	By faqsLocator = By.linkText("FAQs");
	By aboutUsLocator = By.linkText("About Us");
	By contacUsLocator = By.linkText("Contact Us");
	By announcementsLocator = By.linkText("Announcements");
	By helpfulLocator = By.linkText("Helpful Links");
	By usernameLocator = By.id("username");
	By passwordLocator = By.id("password");
	By goLocator = By.cssSelector("#loginForm > button.btn.btn-default");
	By loginButtonLocator = By.cssSelector("#loginForm > button.btn.btn-default");

	public LoginPage typeUsername(String username){
		driver.findElement(usernameLocator).sendKeys(username);
		return this;       
	}
	
	public LoginPage typePassword(String password){
		driver.findElement(passwordLocator).sendKeys(password);
		return this;
	}
	
    public SecureLogin submitLogin() throws IOException {
        driver.findElement(loginButtonLocator).submit();
		//driver.findElement(secretAnswerLocator).sendKeys("2002");     
        //driver.findElement(loginButtonLocator).submit();		
        //return new HomePage(driver);    
        return new SecureLogin(driver); 
        }	
    public LoginPage submitLoginExpectingFailure() {
        driver.findElement(loginButtonLocator).submit();
        return new LoginPage(driver);   
    }	
	
    public SecureLogin loginAs(String username, String password) throws IOException {
        typeUsername(username);
        typePassword(password);
        return submitLogin();
    }

    
}
