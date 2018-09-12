


//How to print summation of n numbers?
import java.io.IOException;

public class MainClass{ //AdditionStack {
   static int num;
   static int ans;
   static Stack theStack;
   public static void main(String[] args)
   throws IOException {
      num = 50;
      stackAddition();
      System.out.println("Sum=" + ans);
   }
   public static void stackAddition() {
      theStack = new Stack(10000); 
      ans = 0; 
      while (num > 0) {
         theStack.push(num); 
         --num; 
      }
      while (!theStack.isEmpty()) {
         int newN = theStack.pop(); 
         ans += newN; 
      }
   }
}

class Stack {
   private int maxSize; 
   private int[] data;
   private int top; 
   boolean top1;
   public Stack(int s) {
      maxSize = s;
      data = new int[maxSize];
      top = -1;
   }
   public void push(int p) {
      data[++top] = p;
   }
   public int pop() {
      return data[top--];
   }
   public int peek() {
      return data[top];
   }
   public boolean isEmpty() {
	   if (top==-1)
		   top1=true;
	   else
		   top1=false;
	   return (top1);
      //return (top == -1);
   }
}

/*

//How to create user defined Exception?
class WrongInputException extends Exception {
   WrongInputException(String s) {
      super(s);
   }
}
class Input {
   void method() throws WrongInputException {
      throw new WrongInputException("Wrong input");
   }
}
class MainClass {
   public static void main(String[] args){
      try {
         new Input().method();
      }
	  catch(WrongInputException wie) {
         System.out.println(wie.getMessage());
      }
   } 
}


// ThreadDemo.java - from another resource on internet
class MainClass//ThreadDemo
{
   public static void main (String [] args)
   {
      MyThread mt = new MyThread ();
      mt.start ();
      for (int i = 0; i < 50; i++)
           System.out.println ("i = " + i + ", i * i = " + i * i);
   }
}
class MyThread extends Thread
{
   public void run ()
   {
      for (int count = 1, row = 1; row < 20; row++, count++)
      {
           for (int i = 0; i < count; i++)
                System.out.print ('*');
           System.out.print ('\n');
      }
   }
}




//How to use exceptions with thread?
class MyThread extends Thread{
   public void run(){
      System.out.println("Throwing in " +"MyThread");
      throw new RuntimeException();
   }
}
class MainClass {
   public static void main(String[] args){
      MyThread t = new MyThread();
      t.start();
      try{
         Thread.sleep(1000);
      }
      catch (Exception x){
         System.out.println("Caught it" + x);
      }
      System.out.println("Exiting main");
   }
}


//How to print stack of the Exception?
public class MainClass{
   public static void main (String args[]){
      int array[]={20,20,40};
      int num1=15,num2=10;
      int result=10;
      try{
         result = num1/num2;
         System.out.println("The result is" +result);
         for(int i =5;i >=0; i--) {
            System.out.println("The value of array is" 
            +array[i]);
         }
      }
      catch (Exception e) {
         e.printStackTrace();
      }
   }
}



//How to handle the exception with overloaded methods ?
public class MainClass {
   double method(int i) throws Exception{
      return i/0;
   }
   boolean method(boolean b) {
      return !b;
   }
   static double method(int x, double y) throws Exception  {
      return x + y ;
   }
   static double method(double x, double y) {
      return x + y - 3;
   }   
   public static void main(String[] args) {
      MainClass mn = new MainClass();
      try{
         System.out.println(method(10, 20.0));
         System.out.println(method(10.0, 20));
         System.out.println(method(10.0, 20.0));
         System.out.println(mn.method(10));
      }
      catch (Exception ex){
         System.out.println("exception occoure: "+ ex);
      }
   }
}




public class MainClass {
   public static void main (String args[]) {
      int array[]={20,20,40};
      int num1=15,num2=10;
      int result=10;
      try {
         result = num1/num2;
         System.out.println("The result is" +result);
         for(int i =5;i >=0; i--) {
            System.out.println
            ("The value of array is" +array[i]);
         }
      }
      catch (ArrayIndexOutOfBoundsException e) {
         System.out.println("Array is out of Bounds"+e);
      }
      catch (ArithmeticException e) {
         System.out.println ("Can't divide by Zero"+e);
      }
   }
}



//This example shows how to handle multiple exceptions while deviding by zero?
public class MainClass {
   public static void main (String args[]) {
      int array[]={20,20,40};
      int num1=15,num2=0;
      int result=0;
      try {
         result = num1/num2;
         System.out.println("The result is" +result);
         for(int i =2;i >=0; i--){
            System.out.println
            ("The value of array is" +array[i]);
         }
      }
      catch (ArrayIndexOutOfBoundsException e) {
         System.out.println
         ("Error. Array is out of Bounds"+e);
      }
      catch (ArithmeticException e) {
         System.out.println 
         ("Can't be divided by Zero"+e);
      }
   }
}


//How to pass arguments while throwing checked exception?
public class MainClass{
   public static void main (String args[]) {
      try {
         throw new Exception("throwing an exception");
      }
      catch (Exception e) {
         System.out.println(e.getMessage());
      }
   }
}



//How to handle checked exception?
public class MainClass {
   public static void main (String args[]) {
      try{
         throw new Exception("throwing an exception");
      }
      catch (Exception e) {
         System.out.println(e.getMessage());
      }
   }
}



//How to handle the exception with overloaded methods ?
public class MainClass {
   double method(int i) throws Exception{
      return i/0;
   }
   boolean method(boolean b) {
      return !b;
   }
   static double method(int x, double y) throws Exception  {
      return x + y ;
   }
   static double method(double x, double y) {
      return x + y - 3;
   }   
   public static void main(String[] args) {
      MainClass mn = new MainClass();
      try{
         System.out.println(method(10, 20.0));
         System.out.println(method(10.0, 20));
         System.out.println(method(10.0, 20.0));
         System.out.println(mn.method(10));
         System.out.println(mn.method(false));
      }
      catch (Exception ex){
         System.out.println("exception occoure: "+ ex);
      }
   }
}



//How to use catch to handle chained exception?
public class MainClass{
   public static void main (String args[])throws Exception  {
      int n=20,result=0;
      try{
         result=n/0;
         System.out.println("The result is"+result);
      }
      catch(ArithmeticException ex){
         System.out.println
         ("Arithmetic exception occoured: "+ex);
         try {
         throw new NumberFormatException();
         }
         catch(NumberFormatException ex1) {
            System.out.println
            ("Chained exception thrown manually : "+ex1);
         }
      }
   }
}




//How to use catch to handle the exception?
public class MainClass{
   public static void main (String args[]) {
      int array[]={20,20,40};
      int num1=15,num2=10;
      int result=10;
      try{
         result = num1/num2;
         System.out.println("The result is " +result);
         for(int i =5;i >=0; i--) {
            System.out.println
            ("The value of array is" +array[i]);
         }
      }
      catch (Exception e) {
         System.out.println("Exception occoured : "+e);
      }
   }
}




//catch by zero - Lucas'
public class MainClass{
   public static void main (String args[]) {
      int array[]={20,20,40};
      int num1=15,num2=10;
      int result=10;
      try{

         for(int i =3;i >=0; i--) {
             result = num1/i;
             System.out.println("The result is " +result);    
         }
      }
      catch (Exception e) {
         System.out.println("Exception occoured : "+e);
         System.out.println("Exception occoured : "+e.getMessage());
      }
   }
}

//try, catch, finally
//How to use finally block for catching exceptions?
public class MainClass{//ExceptionDemo2 {
   public static void main(String[] argv) {
      new MainClass().doTheWork();//ExceptionDemo2().doTheWork();
   }
   public void doTheWork() {
      Object o = null;
      for (int i=0; i<5; i++) {
         try {
            o = makeObj(i);
         }
         catch (Exception e){//IllegalArgumentException e) {
            System.err.println
            ("Error: ("+ e.getMessage()+").");
            return;   
         }
         finally {
            System.err.println("All done");
            if (o==null)
            System.exit(0);
        }
        System.out.println(o); 
      }
   }
   public Object makeObj(int type) 
   throws IllegalArgumentException {
      if (type == 1)  
      throw new IllegalArgumentException
      ("Don't like type " + type);
      return new Object();
   }
}


//How to display all the directories in a directory?
import java.io.*;

class MainClass {
   public static void main(String[] args) {
      File dir = new File("C:\\Documents and Settings\\zhoul\\My Documents");
      File[] files = dir.listFiles();
      FileFilter fileFilter = new FileFilter() {
         public boolean accept(File file) {
            return file.isDirectory();
         }
      };
      files = dir.listFiles(fileFilter);
      System.out.println(files.length);
      if (files.length == 0) {
         System.out.println("Either dir does not exist or is not a directory");
      }
      else {
         for (int i=0; i< files.length; i++) {
            File filename = files[i];
            System.out.println(filename.toString());
         }
      }
   }
}



//How to print the directory hierarchy
import java.io.File;
import java.io.IOException;

public class MainClass {
   public static void main(String[] a)throws IOException{
      showDir(1, new File("C:\\Documents and Settings\\zhoul\\My Documents"));
   }
   static void showDir(int indent, File file) 
   throws IOException {
      for (int i = 0; i < indent; i++)
         System.out.print('-');
      System.out.println(file.getName());
      if (file.isDirectory()) {
         File[] files = file.listFiles();
         for (int i = 0; i < files.length; i++)
            showDir(indent + 4, files[i]);
      }
   }
}


//How to delete a directory?
import java.io.File;

public class MainClass {
   public static void main(String[] argv) throws Exception {
      deleteDir(new File("c:\\temp"));
   }
   public static boolean deleteDir(File dir) {
      if (dir.isDirectory()) {
         String[] children = dir.list();
         for (int i = 0; i < children.length; i++) {
            boolean success = deleteDir
            (new File(dir, children[i]));
            if (!success) {
               return false;
            }
         }
      }
      System.out.println("The directory is deleted.");
      return dir.delete();
  }
}



//How to create a new file?
import java.io.File;
import java.io.IOException;

public class MainClass {
   public static void main(String[] args) {
      try{
         File file = new File("C:/myfile.txt");
         if(file.createNewFile())
         System.out.println("Success!");
         else
         System.out.println
         ("Error, file already exists.");
     }
     catch(IOException ioe) {
        ioe.printStackTrace();
     }
   }
}


//method overloading
public class MainClass {
   static void vaTest(int ... no) {
      System.out.print("vaTest(int ...): " 
      + "Number of args: " + no.length +" Contents: ");
      for(int n : no)
      System.out.print(n + " ");
      System.out.println();
   }
   static void vaTest(boolean ... bl) {
      System.out.print("vaTest(boolean ...) " +
      "Number of args: " + bl.length + " Contents: ");
      for(boolean b : bl)
      System.out.print(b + " ");
      System.out.println();
   }
   static void vaTest(String msg, int ... no) {
      System.out.print("vaTest(String, int ...): " +
      msg +"no. of arguments: "+ no.length +" Contents: ");
      for(int  n : no)
      System.out.print(n + " ");
      System.out.println();
   }
   public static void main(String args[]){
      vaTest(1, 2, 3);
      vaTest("Testing: ", 10, 20);
      vaTest(true, false, false);
   }
}



//How to set dynomic array
//How to make a method take variable lentgth argument as an input
//This example creates sumvarargs() method which takes variable no of 
//int numbers as an argument and returns the sum of these arguments as an output.
public class MainClass {
   static int  sumvarargs(int... intArrays){
      int sum, i;
      sum=0;
      for(i=0; i< intArrays.length; i++) {
         sum += intArrays[i];
      }
      return(sum);
   }
   public static void main(String args[]){
      int sum=0;
      sum = sumvarargs(new int[]{10,12,33});
      System.out.println("The sum of the numbers is: " + sum);
   }
}





//Each For (int a: data), Loop For (int i=0;i<data.length;i++ 
public class MainClass {
   public static void main(String[] args) {
      int[] intary = { 1,2,3,4,6,0,9,21};
      forDisplay(intary);
      foreachDisplay(intary);
   }
   public static void forDisplay(int[] a){  
      System.out.println("Display an array using for loop");
      for (int i = 0; i < a.length; i++) {
         System.out.print(a[i] + " ");
      }
      System.out.println();
   }
   public static void foreachDisplay(int[] data){
      System.out.println("Display an array using for each loop");
      for (int a  : data) {
         System.out.print(a+ " ");
      }
   }
}


//How to use enum constructor, instance variable & method?
enum Car {
   lamborghini(900),tata(2),audi(50),fiat(15),honda(12);
   private int price;
   Car(int p) {
      price = p;
   }
   int getPrice() {
      return price;
   } 
}
public class MainClass {
   public static void main(String args[]){
	   System.out.println("All car prices:");
      for (Car c : Car.values())
      System.out.println(c + " costs " + c.getPrice() + " thousand dollars.");
   }
}



//How to use enum & switch statement
enum Car {
   lamborghini,tata,audi,fiat,honda
}
public class MainClass {
   public static void main(String args[]){
      Car c;
      c = Car.lamborghini;
      switch(c) {
         case lamborghini:
         System.out.println("You choose lamborghini!");
         break;
         case tata:
         System.out.println("You choose tata!");
         break;
         case audi:
         System.out.println("You choose audi!");
         break;
         case fiat:
         System.out.println("You choose fiat!");
         break;
         case honda:
         System.out.println("You choose honda!");
         break;
         default:
         System.out.println("I don't know your car.");
         break;
      }
   }
}



//how to jump to a particular label when 
//break or continue statements occur in a loop.
public class MainClass {
   public static void main(String[] args) {
      String strSearch = "This is the string in which you have to search for a substring.";
      String substring = "substring";
      boolean found = false;
      int max = strSearch.length() - substring.length();
      testlbl:
      for (int i = 0; i <= max; i++) {
         int length = substring.length();
         int j = i;
         int k = 0;
         while (length-- != 0) {
            if(strSearch.charAt(j++) != substring.charAt(k++)){
               continue testlbl;
            }
         }
         found = true;
         break testlbl;
      }
      if (found) {
         System.out.println("Found the substring .");
      }
      else {
         System.out.println("did not find the substing in the string.");
      }
   }
}




//continue
public class MainClass {
   public static void main(String[] args) {
      StringBuffer searchstr = new StringBuffer(
      "hello how are you. ");
      int length = searchstr.length();
      int count = 0;

      for (int i = 0; i < length; i++) {
         if (searchstr.charAt(i) != 'h')
         continue;
         count++;
         searchstr.setCharAt(i, 'h');
      }
      System.out.println("Found " + count 
      + " h's in the string.");
      System.out.println(searchstr);
   }
}




//break 
public class MainClass {
   public static void main(String[] args) {
      int[] intary = { 99,12,22,34,45,67,5678,8990 };
      int no = 5678;
      int i = 0;
      boolean found = false;
      i=intary.length;
      i=0;
      for ( ; i < intary.length; i++) {
         if (intary[i] == no) {
            found = true;
            break;
         }
      }
      if (found) {
         System.out.println("Found the no: " + no 
         + " at  index: " + i);
      } 
      else {
         System.out.println(no + "not found  in the array");
      }
   }
}



//object instance
import java.util.ArrayList;
import java.util.Vector;

public class MainClass {

public static void main(String[] args) {
   Object testObject = new ArrayList();
      displayObjectClass(testObject);
   }
   public static void displayObjectClass(Object o) {
      if (o instanceof Vector)// how to use if ((o instanceof Vector)!=true)
      System.out.println("Object was an instance of the class java.util.Vector");
      else if (o instanceof ArrayList)
      System.out.println("Object was an instance of the class java.util.ArrayList");
      else
      System.out.println("Object was an instance of the " 
      + o.getClass());
   }
}

//super - overriding in Inheritance for subclasses
public class MainClass{
   public static void main (String []agrs){
      Figure f= new Figure(10 , 10);
      Rectangle r= new Rectangle(9 , 5);
      Figure figref;
      figref=f;
      System.out.println("Area is :"+figref.area());
      figref=r;
      System.out.println("Area is :"+figref.area());
   }
}
class Figure{
   double dim1;
   double dim2;
   Figure(double a , double b) {
      dim1=a+2;
      dim2=b;
   }
   Double area() {
      System.out.println("Inside area for figure.");
      return(dim1*dim2);
   }
}
class Rectangle extends Figure {
   Rectangle(double a, double b) {
      super(a ,b);
   }
   Double area() {
      System.out.println("Inside area for rectangle.");
      return(dim1*dim2);
   }
}


//x! - Factorial of a number
public class MainClass{

	public static long factorial(long number){
		if (number<=1)
			return 1;
		else
			return number*factorial(number-1);
	}
	public static void main(String[] args){
		for (int counter =0; counter<=10; counter++){
			System.out.printf("%d! = %d\n", counter,factorial(counter));
		}
			
	}
	
}

//Fibonacci series
public class MainClass {
   public static long fibonacci(long number) {
      if ((number == 0) || (number == 1))
         return number;
      else
         return fibonacci(number - 1) + fibonacci(number - 2);
   }
   public static void main(String[] args) {
      for (int counter = 0; counter <= 10; counter++){
         System.out.printf("Fibonacci of %d is: %d\n",
         counter, fibonacci(counter));
      }
   }
}
//Hanoi tower
public class MainClass {
	   public static void main(String[] args) {
	      int nDisks = 3;
	      doTowers(nDisks, 'A', 'B', 'C');
	   }
	   public static void doTowers(int topN, char from,
	   char inter, char to) {
	      if (topN == 1){
	         System.out.println("Disk 1 from "
	         + from + " to " + to);
	      }else {
	         doTowers(topN - 1, from, to, inter);
	         System.out.println("Disk "
	         + topN + " from " + from + " to " + to);
	         doTowers(topN - 1, inter, from, to);
	      }
	   }
	}

//From http://www.tutorialspoint.com/javaexamples/method_for.htm

*/