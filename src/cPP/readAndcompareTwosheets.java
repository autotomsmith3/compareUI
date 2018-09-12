package cPP;

//public class readAndcompareTwosheets {
//
//	package cPP;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;

import com.ibm.icu.util.Calendar;

public class readAndcompareTwosheets {
	public static String[][] readSheet(String resultfile, String sheetName, int rowStart, int rowEnd, int colStart,
			int colTotal, int keyStart) throws IOException {

		int n = 0;
		int c = 0;

		String[][] expectArray = new String[100][50];
		String[][] realResoult = new String[100][50];
		String sName, dateStamp, timeStamp;
		Calendar cal = Calendar.getInstance();
		cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		timeStamp = sdf.format(cal.getTime());
		DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
		Date d = new Date();
		dateStamp = df.format(d);
		timeStamp = dateStamp + " " + timeStamp;

		// writeTitle(resultfile);

		FileInputStream file = new FileInputStream(new File(resultfile));

		// Get the workbook instance for XLS file
		HSSFWorkbook workbook = new HSSFWorkbook(file);

		// Get first sheet from the workbook
		HSSFSheet s = workbook.getSheetAt(0);
		int tabs = workbook.getNumberOfSheets();
		for (int i = 0; i < tabs; i++) {
			s = workbook.getSheetAt(i);
			sName = s.getSheetName();
			if (sName.equalsIgnoreCase(sheetName)) {
				break;
				// s=workbook.getSheetAt(i);
			}
		}

		// Iterator<Row> rol = s.iterator();

		n = s.getLastRowNum();
		c = 35;
		sName = s.getSheetName();
		s.createRow(n + 1);
		n = s.getLastRowNum();
		Row r1 = s.getRow(n);
		n = r1.getRowNum();
		Row row = s.getRow(10);
		int gap = keyStart - colStart - 1;// original =2
		Cell cell = row.getCell(1);

		// String[][] expectArray = new String[n + 10][c + 15];
		for (int i = rowStart; i <= rowEnd; i++) {
			row = s.getRow(i);
			for (int j = colStart; j < colTotal + colStart; j++) {
				cell = row.getCell(j);

				// System.out.println(cell.getNumericCellValue());
				try {
					System.out.println(cell.getStringCellValue());
					expectArray[i - rowStart][j + gap] = cell.getStringCellValue();
					// if (j==keyStart){
					// expectArray[i - rowStart][1] = cell.getStringCellValue();
					// }
				} catch (Exception ex) {
					try {
						System.out.println("number=" + cell.getNumericCellValue());
						expectArray[i - rowStart][j + gap] = String.valueOf(cell.getNumericCellValue());
						// if (j==keyStart){
						// expectArray[i - rowStart][1] = cell.getStringCellValue();
						// }
					} catch (Exception ex2) {
						expectArray[i - rowStart][j + gap] = "null";

					}
				}

			}

		}

		for (int i = rowStart; i <= rowEnd; i++) {

			for (int j = colStart; j < colTotal + colStart; j++) {

				System.out.print(expectArray[i - rowStart][j + gap] + "  ");
			}
			System.out.println("\n");
		}
		return expectArray;
	}

	public static String[][] compareSheetCell(String[][] A, String[][] B, int startCompareRow, int endCompareRow,
			int startCompareColumn, int endCompareColumn, int keyPosition) {
		int rows = A.length;
		int columns = A[0].length;
		int rowNum = 1;
		String[][] C = new String[rows][columns];

		String sName, passOrfail, dateStamp, timeStamp;
		Calendar cal = Calendar.getInstance();
		cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		timeStamp = sdf.format(cal.getTime());
		DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
		Date d = new Date();
		dateStamp = df.format(d);
		timeStamp = dateStamp + "  " + timeStamp;

		for (int i = startCompareRow; i <= endCompareRow; i++) {
			C[i][0] = Integer.toString(rowNum++);
			C[i][1] = timeStamp;
			C[i][2] = A[i][keyPosition];
			for (int j = startCompareColumn; j <= endCompareColumn; j++) {
				System.out.println("A="+A[i][keyPosition]+". B="+B[i][keyPosition]);
				
				if (A[i][keyPosition].equals("null")){
					System.out.println("A="+A[i][keyPosition]+". B="+B[i][keyPosition]);
				}
				
				if (A[i][keyPosition].equalsIgnoreCase(B[i][keyPosition])) {

					if (A[i][j].equalsIgnoreCase(B[i][j])) {
						C[i][j] = "Y";

					} else {
						C[i][j] = "Expected=" + A[i][j] + " Real=" + B[i][j];
					}
				} else {
					// vinPosition not matching
					C[i][keyPosition] = "Expected VIN=" + A[i][keyPosition] + " Real=" + B[i][keyPosition];
				}
				;
			}
		}
		System.out.println("After comparing, C Array:");
		for (int i = startCompareRow; i <= endCompareRow; i++) {
			for (int j = startCompareColumn; j <= endCompareColumn; j++) {
				System.out.print(C[i][j] + "  ");
			}
			System.out.println("");
		}
		return C;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Start...");
		
//		 // ***********************************************1.GM_Output7VINs***********************************************************************
//		 // **********************************************************************************************************************
//		 // ************************Expected sheet************************
//		 String filePath1 = "H:\\My Documents\\VINs\\QA\\Test cases in Spira\\Lucas_Selected VINs for all 4 OEMs_ExpectedResults.xls";
//		 String sheetname1 = "1.GM_Output5VINs";// "Expected";//ConfigLogs15VINs
//		 // ************************Real tested sheet************************
//		 String filePath2 = "C:\\1\\Eclipse\\Test Results\\BuildDataExtractWS\\BuildDataExtractWS_Return.xls";//BuildDataExtractWS_Return20161031_ConfigLogs_15_VINs_01.xls";
//		 String sheetname2 = "Sheet0";// "Expected";//ConfigLogs15VINs
//		
//		 // After comparing: Save filePaht************************
//		 String finalResultfile = "C:\\1\\Eclipse\\Test Results\\BuildDataExtractWS\\ComparedArrayBuildDataExtractWS_Return.xls";
//		
//		 // ************************Expected sheet************************
//		 int rowStart1 = 5;// 6;5
//		 int rowEnd1 = 11;// 13;19
//		 int colStart1 = 4;// 4;1
//		 // ************************Real tested sheet************************
//		 int rowStart2 = 2;//
//		 int rowEnd2 = 8;//
//		 int colStart2 = 2;//
//		
//		 // ************************Compared sheet************************
//		 // Total fields
//		 int colTotal = 32;
//		 // Key column start
//		 int keyColumnStart = 4;
//		 // StartCompareRow
//		 int startCompRow = 1;
//		 // EndCompareRow
//		 int endCompRow = 7;
//		 // StartCompareColumn
//		 int startCompCol = 4;
//		 int endCompCol = 35;
//		 // ***********************************************1.GM_Output7VINs***********************************************************************
//		 // **********************************************************************************************************************
		
		
//
//		 // ***********************************************2.ConfigLogs15VINs***********************************************************************
//		 // **********************************************************************************************************************
//		 // ************************Expected sheet************************
//		 String filePath1 = "H:\\My Documents\\VINs\\QA\\Test cases in Spira\\Lucas_Selected VINs for all 4 OEMs_ExpectedResults.xls";
//		 String sheetname1 = "2.ConfigLogs15VINs";// "Expected";//ConfigLogs15VINs
//		 // ************************Real tested sheet************************
//		 String filePath2 = "C:\\1\\Eclipse\\Test Results\\BuildDataExtractWS\\BuildDataExtractWS_Return20161104_ConfigLog_15_VINs_01.xls";//BuildDataExtractWS_Return20161031_ConfigLogs_15_VINs_01.xls";
//		 String sheetname2 = "Sheet0";// "Expected";//ConfigLogs15VINs
//		
//		 // After comparing: Save filePaht************************
//		 String finalResultfile = "C:\\1\\Eclipse\\Test Results\\BuildDataExtractWS\\ComparedArrayBuildDataExtractWS_Return.xls";
//		
//		 // ************************Expected sheet************************
//		 int rowStart1 = 5;// 6;5
//		 int rowEnd1 = 19;// 13;19
//		 int colStart1 = 4;// 4;1
//		 // ************************Real tested sheet************************
//		 int rowStart2 = 2;//
//		 int rowEnd2 = 16;//
//		 int colStart2 = 2;//
//		
//		 // ************************Compared sheet************************
//		 // Total fields
//		 int colTotal = 32;
//		 // Key column start
//		 int keyColumnStart = 4;
//		 // StartCompareRow
//		 int startCompRow = 1;
//		 // EndCompareRow
//		 int endCompRow = rowEnd2-rowStart2+1;
//		 // StartCompareColumn
//		 int startCompCol = 4;
//		 int endCompCol = 35;
//		 // ***********************************************2.ConfigLogs15VINs***********************************************************************
//		 // **********************************************************************************************************************

//		 // ***********************************************3.ConfigLogs52VINs***********************************************************************
//		 // **********************************************************************************************************************
//		 // ************************Expected sheet************************
//		 String filePath1 = "H:\\My Documents\\VINs\\QA\\Test cases in Spira\\Lucas_Selected VINs for all 4 OEMs_ExpectedResults.xls";
//		 String sheetname1 = "4.Tota=52+15=67";// "Expected";//ConfigLogs15VINs
//		 // ************************Real tested sheet************************
//		 String filePath2 = "C:\\1\\Eclipse\\Test Results\\BuildDataExtractWS\\BuildDataExtractWS_Return20161107_52_Vins_01.xls";//BuildDataExtractWS_Return20161031_ConfigLogs_15_VINs_01.xls";
//		 String sheetname2 = "Sheet0";// "Expected";//ConfigLogs15VINs
//		
//		 // After comparing: Save filePaht************************
//		 String finalResultfile = "C:\\1\\Eclipse\\Test Results\\BuildDataExtractWS\\ComparedArrayBuildDataExtractWS_Return20161107_52_VINs_01.xls";
//		
//		 // ************************Expected sheet************************
//		 int rowStart1 = 5;//
//		 int rowEnd1 = 56;// 
//		 int colStart1 = 4;// 
//		 // ************************Real tested sheet************************
//		 int rowStart2 = 2;//
//		 int rowEnd2 = 53;//
//		 int colStart2 = 2;//
//		
//		 // ************************Compared sheet************************
//		 // Total fields
//		 int colTotal = 32;
//		 // Key column start
//		 int keyColumnStart = 4;
//		 // StartCompareRow
//		 int startCompRow = 1;
//		 // EndCompareRow
//		 int endCompRow = rowEnd2-rowStart2+1;
//		 // StartCompareColumn
//		 int startCompCol = 4;
//		 int endCompCol = 35;
//		 // ***********************************************3.ConfigLogs52VINs***********************************************************************
//		 // **********************************************************************************************************************
		
		
		
		
		
		
		 // ***********************************************4.Tota=52+15=67+3(chryslerCA)=70***********************************************************************
		 // **********************************************************************************************************************
		
		// ************************Expected sheet************************
		 String filePath1 = "H:\\My Documents\\VINs\\QA\\Test cases in Spira\\Lucas_Selected VINs for all 4 OEMs_ExpectedResults.xls";
		 String sheetname1 = "4.Tota=52+15=67+3=70";// "Expected";//ConfigLogs15VINs
		 
		 
		 // ************************Real tested sheet************************
		 String filePath2 = "C:\\1\\Eclipse\\Test Results\\BuildDataExtractWS\\BuildDataExtractWS_Return20161222_70_Vins_Prod_04.xls";//
		 String sheetname2 = "Sheet0";// "Expected";//ConfigLogs15VINs
		
		 // After comparing: Save filePaht************************
		 String finalResultfile = "C:\\1\\Eclipse\\Test Results\\BuildDataExtractWS\\ComparedArrayBuildDataExtractBS_Return_Since_20161115_70_VINs_01.xls";
		
		 // ************************Expected sheet************************
		 int rowStart1 = 5;//
		 int rowEnd1 = 74;// 
		 int colStart1 = 4;// 
		 // ************************Real tested sheet************************
		 int rowStart2 = 2;//
		 int rowEnd2 = 71;//
		 int colStart2 = 2;//
		
		 // ************************Compared sheet************************
		 // Total fields
		 int colTotal = 32;
		 // Key column start
		 int keyColumnStart = 4;
		 // StartCompareRow
		 int startCompRow = 1;
		 // EndCompareRow
		 int endCompRow = rowEnd2-rowStart2+1;
		 // StartCompareColumn
		 int startCompCol = 4;
		 int endCompCol = 35;
		 // ***********************************************4.Tota=52+15=67+3(chryslerCA)=70***********************************************************************
		 // **********************************************************************************************************************

		String[][] expectedResoult = new String[100][50];
		String[][] realResoult = new String[100][50];
		String[][] finalResoult = new String[100][50];
		expectedResoult = readSheet(filePath1, sheetname1, rowStart1 - 1, rowEnd1 - 1, colStart1 - 1, colTotal,
				keyColumnStart);
		realResoult = readSheet(filePath2, sheetname2, rowStart2 - 1, rowEnd2 - 1, colStart2 - 1, colTotal,
				keyColumnStart);
		// compareSheetCell(String[][] A, String[][] B, int keyPosition)
		finalResoult = compareSheetCell(expectedResoult, realResoult, startCompRow - 1, endCompRow - 1,
				startCompCol - 1, endCompCol - 1, keyColumnStart - 1);
		// Save to Excel sheet.xls
		// String [] tString=new String[35];
		String[] tString = { "S/N", "DateTime", "VIN", "VIN:", "modelYear:", "make:", "model", "modelCode:", "trim:",
				"subTrim/Package?:", "bodyDescription:", "seatingCapacity:", "engineDescription:", "horsepower:",
				"transmission Description:", "driveTrain Description:", "wheelBase:", "payloadCapacity:", "fuelCity:",
				"fuelHighway:", "exteriorCode:", "exteriorColour:", "interiorCode:", "interiorColour:", "interiorDesc:",
				"baseMSRP:", "standardEquipment:", "installedOptions:", "safetyRating:", "factoryWarranty:",
				"totalMSRP:", "fuelCombined:", "destinationCharge:", "invoicePrice:", "buildDate:" };

		com_libs.writeTitle(finalResultfile, tString);
		for (int i = 0; i <= endCompRow + 1; i++) {
			com_libs.writeToSheet(finalResultfile, finalResoult[i]);
		}
		System.out.println("End...");
	}

}
