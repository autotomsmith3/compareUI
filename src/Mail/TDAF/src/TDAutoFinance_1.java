
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.junit.Assert.*;


public class TDAutoFinance_1 {
	WebDriver selenium;
	//See JUnit Tutorial at  http://www.vogella.com/articles/JUnit/article.html
	@Before
	public void setUp(){
		selenium=new FirefoxDriver();
    	selenium.manage().window().maximize();
	}
	
	@After
	public void tearDown(){
		selenium.quit();
	}
	
	@Test
	public void OLP1(){
		selenium.get("https://uat.tdautofinance.com");
		HomePage tdaf =new HomePage (selenium);
		tdaf.login();
		tdaf.makepayment();
		
		Chapter2 ch2=tdaf.clickChapter2();
		//assertTrue(ch2.isButtonPresent("but1"));
		assertTrue(ch2.isButtonPresent("but1"));  //this but1 exists. pass
		assertFalse(ch2.isButtonPresent("but2")); //this but2 does not exist. pass
 
		boolean t1=ch2.isButtonPresent("but2");
				//.isButtonPresent("but1");
  		if (!!t1){
  	  		System.out.println("ShouldLoadTheHomePageAndThenCheckButtonOnChapter2 testing is done!");
  		}
  		else {
  			System.out.println("Test is complete but but2 does not exist!");
  		}
		//assertTrue(ch2.isButtonPresent("but1"));
	}

}
