//The following code only works in InternetExplorerDriver, not FirefoxDriver 
//https://code.google.com/p/selenium/issues/detail?id=349
	
	import java.util.List;
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
//	import org.openqa.selenium.RenderedWebElement;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.JavascriptExecutor;
	import org.openqa.selenium.firefox.FirefoxDriver;

	public class FaceBook1 {
		
		
	    public static void main(String[] args) throws Exception {
		WebDriver driver=new FirefoxDriver();

		driver.get("https://login.facebook.com/login.php");
	        driver.findElement(By.name("email")).clear();
	        driver.findElement(By.name("email")).sendKeys("username");
	        driver.findElement(By.name("pass")).sendKeys("password");
		driver.findElement(By.xpath("//input[@type = 'submit']")).click();
		int item=0;
		List<WebElement> items=null;
		do { 
		    items=driver.findElements(By.className("UIUpcoming_Item"));
		    if (items.get(item).getText().contains("birthday Today")) {
			items.get(item).findElement(By.tagName("a")).click();
			Thread.sleep(3000);

			boolean exists=false;
			List<WebElement> stories=driver.findElements(By.className("UIStory"));
			for (WebElement story: stories) {
			    if (story.getText().contains("Misha Koshelev Happy Birthday!")) {
				exists=true;
				break;
			    }
			}
			if (!exists) {
			    driver.findElement(By.className("Mentions_Input")).sendKeys("Happy Birthday!");
			   
	driver.findElement(By.className("UIComposer_SubmitButton")).findElement(By.tagName("input")).click();
			}
			driver.findElement(By.linkText("Home")).click();
			Thread.sleep(3000);
		    }
		    item=item+1;
		} while (item<items.size());
		driver.quit();
	    }
	}
