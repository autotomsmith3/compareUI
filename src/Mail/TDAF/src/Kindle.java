import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver; 
import org.openqa.selenium.WebElement; 
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;




public class Kindle {
	String username, password;

	
	public static void setup() {
		String URL;
		String username;
		username="sectest88";
  		String usernameid = "sectest1000";
  		System.out.println("Username is " + usernameid+"  useranme="+username);
  		
  		WebDriver driver = new FirefoxDriver();
    	driver.manage().window().maximize();
    	URL="http://book.theautomatedtester.co.uk";
    	loadKindleHomePage(driver); 
    	String pageTitle=driver.getTitle();
    	if (!"Selenium: Beginners Guide1".equals(driver.getTitle())){
    		driver.get("https://uat.tdautofinance.com");
    	}
    	pageTitle=driver.getTitle();
    	if (!"Selenium: Beginners Guide".equals(driver.getTitle())){
    		driver.get("http://book.theautomatedtester.co.uk/chapter2");

    	}
    	System.out.println("Kindle testing is done!");
    	driver.quit();
		}
	private static void loadKindleHomePage(WebDriver driver) {
		LoadBookCoUk(driver);
	}
	private static void LoadBookCoUk(WebDriver driver) {
		driver.get("http://book.theautomatedtester.co.uk");
	}
//	Private void clickAndLoadChapter2(){
//		driver.findElement(By.linkText("Chapter2")).click();
//	}
	public static void teardown() {
		String username, URL;
		username="sectest99";
  		String usernameid = "sectest2000";
  		System.out.println("Username is " + usernameid+"  useranme="+username);
  		WebDriver driver = new FirefoxDriver();
    	driver.manage().window().maximize();
  		URL="http://book.theautomatedtester.co.uk";
  		//driver.get(URL); 
    	loadKindleHomePage(driver); 
  		String pageTitle=driver.getTitle();
  		if (!"Selenium: Beginners Guide1".equals(driver.getTitle())){
  			driver.get("https://uat.tdautofinance.com");
  		}
  		//shouldCheckButtonOnChapter2Page();
  		pageTitle=driver.getTitle();
  		if (!"Selenium: Beginners Guide".equals(driver.getTitle())){
  			driver.get("http://book.theautomatedtester.co.uk/chapter2");
  		}
  		System.out.println("Kindle testing is done!");
  		driver.quit();
		}

	public void shouldCheckButtonOnChapter2Page(){
  		WebDriver driver = new FirefoxDriver();
    	//driver.manage().window().maximize();
		loadHomePage(driver);
		driver.findElement(By.linkText("Chapter2")).click();
	//	Assert.assertEqual(driver.findElements(By.id"but1").getSize(), 1);
		 }
	private void loadHomePage(WebDriver driver) {
		driver.get("http://book.theautomatedtester.co.uk");
	}

    public static void main(String [] args)  {
    
          setup();
          teardown();
          
    }
}
