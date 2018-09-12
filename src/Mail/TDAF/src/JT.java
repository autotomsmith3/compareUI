
import org.openqa.selenium.*;
//import org.openqa.selenium.firefox.FirefoxDriver;

class MyClass {
   int height;
   String width;
   MyClass() {
      System.out.println("bricks");
      height = 0;
   }
   MyClass(int i) {
      System.out.println("Building new House that is "
      + i + " feet tall");
      height = i;
   }
   MyClass(String i) {
	      System.out.println("Building new House that is "
	      + i + " feet tall");
	      width = i;
	      height = 6;
	   }
   void info() {
      System.out.println("House is " + height
      + " feet tall");
   }
   void info(String s) {
      System.out.println(s + ": House is "
      + height + " feet tall");
   }
   void info(int s) {
          System.out.println("int "+s + ": House is "
          + height + " feet tall");   
   }
}
class MyClass2 {
	   int height;
	   String width;
	   MyClass2() {
	      System.out.println("bricks");
	      height = 0;
	   }
	   MyClass2(int i) {
	      System.out.println("Building new House that is "
	      + i + " feet tall");
	      height = i;
	   }
	   MyClass2(String i) {
		      System.out.println("Building new House that is "
		      + i + " feet tall");
		      width = i;
		      height = 6;
		   }
	   void info() {
	      System.out.println("House is " + height
	      + " feet tall");
	   }
	   void info(String s) {
	      System.out.println(s + ": House is "
	      + height + " feet tall");
	   }
	   void info(int s) {
	          System.out.println("int "+s + ": House is "
	          + height + " feet tall");   
	   }
 	  public static boolean isElementPresent(String byId, WebDriver driver) { 
 			try {
 			driver.findElement(By.id(byId)).isDisplayed(); 
 			return true; 
 			} 
 			catch (NoSuchElementException e) {
 				
 			return false; 
 			} 
 		}
 	  public static boolean AddBankProfile(WebDriver driver)
 	  {
 	  	try {
 	  	driver.findElement(By.id("accountNickname")).clear();
 	  	driver.findElement(By.id("accountNickname")).sendKeys("Tom");
 	  	driver.findElement(By.id("checking")).click();
 	      driver.findElement(By.id("bankRoutingNumber")).clear();
 	      driver.findElement(By.id("bankRoutingNumber")).sendKeys("272471548");
 	      driver.findElement(By.id("bankAccountNumber")).clear();
 	      driver.findElement(By.id("bankAccountNumber")).sendKeys("1234567890");
 	      driver.findElement(By.id("emailAddress")).clear();
 	      driver.findElement(By.id("emailAddress")).sendKeys("autodatabbb@yahoo.ca");//for sectest102 	      
 	      driver.findElement(By.id("confirmEmailAddress")).clear();
 	      driver.findElement(By.id("confirmEmailAddress")).sendKeys("autodatabbb@yahoo.ca");//for sectest102
 	  	return true; 
 	  	}	
 	  	catch (NoSuchElementException e) {
 	      return false; 
 	      } 
 	  }
	   
	   
	   
}

  



/*public class JT {
   public static void main(String[] args) {
      MyClass t = new MyClass("6");
      t.info("3");
      t.info(3);
      t.info("overloaded method");
      //Overloaded constructor:
      new MyClass();
      MyClass2 u = new MyClass2(8);
      u.info("8");
      u.info(3);
      u.info("Overloaded method");
      new MyClass2();
   }
}

*/