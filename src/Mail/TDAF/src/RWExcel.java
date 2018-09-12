import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.util.HSSFColor;

	public class RWExcel {
	    public void rwExcel(Boolean testStatus, String founctionality, String description, String resultfile)throws IOException
	    {
	       	int n=0;       	
	       	String sName, passOrfail, dateStamp, timeStamp;
	       	Calendar cal=Calendar.getInstance();
	       	cal.getTime();
	       	SimpleDateFormat sdf =new SimpleDateFormat("HH:mm:ss");
	       	timeStamp=sdf.format(cal.getTime());
	       	DateFormat df=DateFormat.getDateInstance(DateFormat.SHORT);
	       	Date d=new Date();
	       	dateStamp=df.format(d);
	       	timeStamp=dateStamp+"  "+timeStamp;
	       	writeTitle(resultfile);

			FileInputStream file = new FileInputStream(new File(resultfile));

	    	//Get the workbook instance for XLS file
	    	HSSFWorkbook workbook = new HSSFWorkbook(file);
	    	
	    	//Get first sheet from the workbook
	    	HSSFSheet s = workbook.getSheetAt(0);

	    	Iterator<Row> rol = s.iterator();
 	
	    	n=s.getLastRowNum();
	    	sName=s.getSheetName();
	   	 	s.createRow(n+1);
	    	n=s.getLastRowNum();
	    	Row r1=s.getRow(n);
	    	n=r1.getRowNum();
	    
	    	HSSFCellStyle style=workbook.createCellStyle();
	   	 	style.setFillBackgroundColor(IndexedColors.AQUA.getIndex());   	 	
	   	 	style.setFillForegroundColor(HSSFColor.RED.index);
	   	 	
	   		if (testStatus) {
	   			//passOrfail="Passed";
		   	 	style.setFillForegroundColor(HSSFColor.GREEN.index);
		   	 	Cell cell=r1.createCell(1);
		   	 	cell.setCellValue("Passed");
		   	 	cell.setCellStyle(style);
	  		}
	  		else 
	  		{
	   			//passOrfail="Failed";
		   	 	style.setFillForegroundColor(HSSFColor.RED.index);
		   	 	Cell cell=r1.createCell(1);
		   	 	cell.setCellValue("Failed");
		   	 	cell.setCellStyle(style);
		   	 	Font titleFont=workbook.createFont();
		   	 	titleFont.setFontHeight((short) 200);
		   	 	titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		   	 	style.setFont(titleFont);
	  		}
	   	 	style.setFillPattern(CellStyle.SOLID_FOREGROUND);

	   	 	r1.createCell(0).setCellValue(n);
	   	 	//r1.createCell(1).setCellValue(passOrfail);
	   	 	r1.createCell(2).setCellValue(founctionality);	
	   	 	r1.createCell(3).setCellValue(description);
	   	 	r1.createCell(4).setCellValue(timeStamp);

/*
	   	 	Font f1 =workbook.createFont();
	   	 	Font f2=workbook.createFont();
	   	 	f1.setColor((short)Font.COLOR_RED);
	   	 	f2.setFontHeight((short) 22);
	   	 	f2.setBoldweight(Font.BOLDWEIGHT_BOLD);
*/
	    	  	 	
	  	 try {
	    	 FileOutputStream out = new FileOutputStream(resultfile); //
	    	 workbook.write(out);
	    	 out.close();                
	    	 }
	    	 catch (Exception e){
	    		 e.printStackTrace();
	    	 }
	    }

	    public void rwExcel(String founctionality, String description, String resultfile)throws IOException
	        {
	           	int n=0;       	
	           	String sName, passOrfail, dateStamp, timeStamp;
	           	Calendar cal=Calendar.getInstance();
	           	cal.getTime();
	           	SimpleDateFormat sdf =new SimpleDateFormat("HH:mm:ss");
	           	timeStamp=sdf.format(cal.getTime());
	           	DateFormat df=DateFormat.getDateInstance(DateFormat.SHORT);
	           	Date d=new Date();
	           	dateStamp=df.format(d);
	           	timeStamp=dateStamp+"  "+timeStamp;
		       	writeTitle(resultfile);

	        	FileInputStream file = new FileInputStream(new File(resultfile));

	        	//Get the workbook instance for XLS file
	        	HSSFWorkbook workbook = new HSSFWorkbook(file);
	        	
	        	//Get first sheet from the workbook
	        	HSSFSheet s = workbook.getSheetAt(0);
	        	Iterator<Row> rowIterator = s.iterator();

	        	n=s.getLastRowNum();
	        	sName=s.getSheetName();
	       	 	s.createRow(n+1);
	        	n=s.getLastRowNum();
	        	Row r1=s.getRow(n);
	        	n=r1.getRowNum();
	       	 	r1.createCell(0).setCellValue(n);
	       	 	//r1.createCell(1).setCellValue(passOrfail);
	       	 	r1.createCell(2).setCellValue(founctionality);	
	       	 	r1.createCell(3).setCellValue(description);
	       	 	r1.createCell(4).setCellValue(timeStamp);
	    	 
	        	 try {
	        	 FileOutputStream out = new FileOutputStream(resultfile); 
	        	 workbook.write(out);
	        	 out.close();                
	        	 }
	        	 catch (Exception e){
	        		 e.printStackTrace();
	        	 }
	        }    
	    
	    public static void writeTitle(String resultfile)throws IOException
	    {	
	    	int n=0;
	       	File f = new File(resultfile);
	    	if (f.exists())
	    	{       	
	    		FileInputStream file = new FileInputStream(new File(resultfile));
	    	}
	    	else
	    	{
	    		//Write an empty file with a title. 
	        	HSSFWorkbook workbook = new HSSFWorkbook();
	        	//Create first sheet from the workbook
	        	HSSFSheet s = workbook.createSheet();
		    	HSSFCellStyle hdstyle=workbook.createCellStyle();
		   	 	hdstyle.setFillBackgroundColor(IndexedColors.AQUA.getIndex());   	 	
		   	 	hdstyle.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index); 	 	
		   	 	
		   	 	Font titleFont=workbook.createFont();
		   	 	titleFont.setFontHeight((short) 220);
		   	 	titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		   	 	hdstyle.setFont(titleFont);	
		   	 	
	       	 	s.createRow(0);
	        	n=s.getLastRowNum();
	        	Row r1=s.getRow(n);
	        	n=r1.getRowNum();

			 	Cell cell0=r1.createCell(0);
			  	cell0.setCellValue("S.No.");
			   	cell0.setCellStyle(hdstyle);
	        	
			 	Cell cell1=r1.createCell(1);
			  	cell1.setCellValue("Status");
			   	cell1.setCellStyle(hdstyle); 
			   	
			 	Cell cell2=r1.createCell(2);
			  	cell2.setCellValue("Founctionality");
			   	cell2.setCellStyle(hdstyle); 	        	
	        	
			 	Cell cell3=r1.createCell(3);
			  	cell3.setCellValue("Description");
			   	cell3.setCellStyle(hdstyle); 
			   	
			 	Cell cell4=r1.createCell(4);
			  	cell4.setCellValue("Time");
			   	cell4.setCellStyle(hdstyle); 

		   	 	hdstyle.setFillPattern(CellStyle.SOLID_FOREGROUND);

	        	 try {
	        	 FileOutputStream out = new FileOutputStream(resultfile); // 
	        	 workbook.write(out);
	        	 out.close();                
	        	 }
	        	 catch (Exception e){
	        		 e.printStackTrace();
	        	 }		
	    	}           	
	    }
	    
	     	public static void main(String [] args)throws IOException
	    {	
		    String fileNamePath="C:\\1\\Eclipse\\Workspace\\TestResult.xls";
//	     	String fileNamePath="C:\\Documents and Settings\\zhoul\\My Documents\\Chrysler_US\\01_CF\\Automated\\Test Results\\SeleniumTDAF\\TestResult.xls";
           	RWExcel uatResult = new RWExcel();
	    	for (int i=1;i<=2;i++){
	    		uatResult.rwExcel("Begin for OLP test","Make a Payment step "+i, fileNamePath);    
	    		uatResult.rwExcel(false, "OLP test","Make a Payment step "+i, fileNamePath);	
	    		uatResult.rwExcel(true, "OLP test","Make a Payment step "+i, fileNamePath);	
	    	}
	    	
	    }
	    
	}

