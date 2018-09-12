import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.junit.Assert.*;


public class BestPractise3 {
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
	public void ShouldLoadTheHomePageAndThenCheckButtonOnChapter2(){
		selenium.get("http://book.theautomatedtester.co.uk");
		HomePage hp =new HomePage (selenium);
		//tdaf.login();
		Chapter2 ch2=hp.clickChapter2();
		//assertTrue(ch2.isButtonPresent("but1"));
		assertTrue(ch2.isButtonPresent("but1"));//isButtonPresent("but1"));  //this but1 exists. pass
		assertFalse(ch2.isButtonPresent("but2"));//"but2")); //this but2 does not exist. pass
 
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
