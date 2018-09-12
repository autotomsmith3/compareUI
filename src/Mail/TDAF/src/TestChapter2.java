import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.openqa.selenium.*;
import org.junit.*;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

public class TestChapter2 {
	WebDriver selenium; 
	
	@Before 
	public void setUp(){
		selenium = new FirefoxDriver();
    	selenium.manage().window().maximize();
	} 
	
	@After 
	public void tearDown(){
		selenium.quit();
	}
	
	public Chapter2 clickChapter2(){
		clickChapter("2");
		return PageFactory.initElements(selenium, Chapter2.class);
	} 
	private void clickChapter (String number){
		selenium.findElement(By.linkText("Chapter"+number)).click();
	}	
	@Test
	public void ShouldLoadTheHomePageAndThenCheckButtonOnChapter2() { 
		/*selenium.get("http://book.theautomatedtester.co.uk");
		HomePage hp = new HomePage(selenium);
		Chapter2 ch2 = hp.clickChapter2();
		assertTrue(ch2.isButtonPresent("but1"));*/
		Chapter2 cht=new Chapter2(selenium).get();
		cht.isButtonDisplayed("but1");
	} 
}
