//Reading data from excel:
//Writing data to excel: see http://seleniumhelper.blogspot.in/2012/05/reading-and-writing-data-from-excel.html

import jxl.write.Label;

public String[][] getXLData(String location, String sheetname)
       {
               Workbook w = null;
               try {
                       w = Workbook.getWorkbook(new File(location));
               } catch (BiffException e) {
                       e.printStackTrace();
               } catch (IOException e) {
                       e.printStackTrace();
               }
               Sheet s = w.getSheet(sheetname);
               String a[][] = new String[10][10];
               try
               {
               for (int j=0;j<s.getColumns();j++)
               {
                       for (int i=0;i<s.getRows();i++)
                       {
                               a[j][i] = s.getCell(j, i).getContents();
                               System.out.println(j+" and "+i+" "+a[j][i]);
                       }
               }


               }
               catch(Exception e)
               {
                       e.printStackTrace();
               }
               return a;
       }

public class google {
	 private WebDriver driver;

	 @Before
	 public void setUp() throws Exception {
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	 }

	 @Test
	 public void test() throws Exception {
	  driver.get("http://www.google.co.in/");
	  driver.findElement(By.id("gbqfq")).clear();
	  driver.findElement(By.id("gbqfq")).sendKeys("Testing");
	  driver.findElement(By.id("gbqfq")).sendKeys(Keys.ENTER);
	  driver.findElement(By.linkText("Software testing - Wikipedia, the free encyclopedia")).click();
	  String s = driver.getTitle();
	  writereport(s);
	  
	 }

	 @After
	 public void tearDown() throws Exception {
	  driver.quit();
	 }



	public void writereport(String text) 
	       { 
	        try
	        {
	       FileOutputStream f = new FileOutputStream("c:\\Test\\output.xls",true);
	       WritableWorkbook book = Workbook.createWorkbook(f); 
	       WritableSheet sheet = book.createSheet("output", 0);
	       Label l = new Label(0, 0, text);
	       sheet.addCell(l);
	       book.write(); 
	       book.close(); 
	        }
	        catch (Exception e)
	        {
	         e.printStackTrace();
	        }
	        }

