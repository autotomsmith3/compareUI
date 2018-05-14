package cPP;

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

public class readAndcompareTwosheets_CombinedVehicleBS {
	public static String[][] readSheet(String resultfile, String sheetName, int rowStart, int rowEnd, int colStart,
			int colTotal, int keyStart) throws IOException {

		int n = 0;
		int c = 0;
		final int ROWS = 12000;

		String[][] expectArray = new String[ROWS][50];
		String[][] realResoult = new String[ROWS][50];
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
				System.out.println("A=" + A[i][keyPosition] + ". B=" + B[i][keyPosition]);

				if (A[i][keyPosition].equals("null")) {
					System.out.println("A=" + A[i][keyPosition] + ". B=" + B[i][keyPosition]);
				}

				if (A[i][keyPosition].equalsIgnoreCase(B[i][keyPosition])) {

					if (A[i][j].equalsIgnoreCase(B[i][j])) {
						C[i][j] = "Y";

					} else {
						C[i][j] = "Expected=" + A[i][j] + " Real=" + B[i][j];
					}
				} else {
					// vinPosition not matching
					C[i][keyPosition] = "Expected =" + A[i][keyPosition] + " Real=" + B[i][keyPosition];
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

		// ***********************************************VIN1***********************************************************************
		// ************************Expected sheet************************
		int rowStart1 = 2;// 5;//
		int rowEnd1 = 3537;// 74;//
		int colStart1 = 1;// 4;//
		// ************************Real tested sheet************************
		int rowStart2 = 2;//
		int rowEnd2 = 3537;// 71;//
		int colStart2 = 1;// 2;//

		// ************************Compared sheet************************
		// Total fields
		int colTotal = 23 + 3;/// there are 3 header columns. seems no need to change this when having diff columns.
		// Key column start
		int keyColumnStart = 4;/// there are 3 header columns
		// StartCompareRow
		int startCompRow = 1;
		// EndCompareRow
		int endCompRow = rowEnd2 - rowStart2 + 1;
		// StartCompareColumn
		int startCompCol = 4;// 1;//4;
		int endCompCol = 23 + 3;// there are 3 header columns. Change this only when having diff columns.
		// ***********************************************VIN1******************************************************************

		System.out.println("Start...");
		final int ROWS = 12000;
		// ***********************************************VINs******************************************************************
		// **********************************************VINs DATA Info*********************************************************
		// *********************************************************************************************************************
		String expectedSheetFolder = "20180418";
		String realSheetFolder = "20180426";
		String compareSheetFolder = realSheetFolder;
		int sheetrows[] = {300,290,300 };// The last line number (EXCEL number).
		int totalVins = sheetrows.length;
		// From vin# to vin#
		int startToRun = 1;// default=1
		int endToRun = 1;// =15, or default=totalVins;
		// ***********************************************VINs DATA Info********************************************************
		// *********************************************************************************************************************
		// *********************************************************************************************************************
		for (int k = startToRun; k <= endToRun; k++) {

			// ************************Expected sheet************************

			rowEnd1 = sheetrows[k - 1];// 3537;// 74;//

			// ************************Real tested sheet************************

			rowEnd2 = rowEnd1;// 3537;// 71;//

			// ************************Compared sheet************************
			endCompRow = rowEnd2 - rowStart2 + 1;

			// ***********************************************VIN1***********************************************************************

			String kStr = Integer.toString(k);

			System.out.println("Start...");
			// final int ROWS = 12000;
			// ***********************************************VIN1***********************************************************************
			// **********************************************************************************************************************

			// ************************Expected sheet************************
			// String filePath1 = "C:\\1\\Eclipse\\Test Results\\Colorized360\\stock360\\" + expectedSheetFolder
			// + "\\getStock360_Vin_" + kStr + ".xls";

			String filePath1 = "C:\\1\\Eclipse\\Test Results\\CombinedVehicleBS\\"
					+ expectedSheetFolder + "\\Combined-Vehicle_" + kStr + ".xls";
			// C:\1\Eclipse\Test Results\CompareMS\ImageBy_modelYearIds_CPP-950\20170505
			// "C:\\1\\Eclipse\\Test Results\\CompareMS\\ImageMS\\" + dateFolder
			// + "\\image_" + kStr + ".xls";

			String sheetname1 = "Sheet0";// "Expected";//

			// ************************Real tested sheet************************
			// String filePath2 = "C:\\1\\Eclipse\\Test Results\\Colorized360\\stock360\\" + realSheetFolder
			// + "\\getStock360_Vin_" + kStr + ".xls";

			String filePath2 = "C:\\1\\Eclipse\\Test Results\\CombinedVehicleBS\\"
					+ realSheetFolder + "\\Combined-Vehicle_" + kStr + ".xls";

			String sheetname2 = "Sheet0";// "Expected";//

			// After comparing: Save filePaht************************
			// String finalResultfile = "F:\\Quality Assurance\\AUTOpx\\CPP\\Stock360\\20170315\\Compare_getStock360_Vin_1.xls";
			String finalResultfile = "C:\\1\\Eclipse\\Test Results\\CombinedVehicleBS\\"
					+ compareSheetFolder + "\\Compare_Combined-Vehicle_" + expectedSheetFolder + "_vs_" + realSheetFolder
					+ "_" + kStr + ".xls";

			String[][] expectedResoult = new String[ROWS][50];
			String[][] realResoult = new String[ROWS][50];
			String[][] finalResoult = new String[ROWS][50];
			expectedResoult = readSheet(filePath1, sheetname1, rowStart1 - 1, rowEnd1 - 1, colStart1 - 1, colTotal,
					keyColumnStart);
			realResoult = readSheet(filePath2, sheetname2, rowStart2 - 1, rowEnd2 - 1, colStart2 - 1, colTotal,
					keyColumnStart);
			// compareSheetCell(String[][] A, String[][] B, int keyPosition)
			finalResoult = compareSheetCell(expectedResoult, realResoult, startCompRow - 1, endCompRow - 1,
					startCompCol - 1, endCompCol - 1, keyColumnStart - 1);
			// Save to Excel sheet.xls
			// String [] tString=new String[35];
			String[] tString = { "Num", "Date Time", "Position S/N", "S/N", "URL_parameter", "RequestBody", "id", "serverTime",
					"error ", "executionTimeMS", "copyright", "resultObj", "tokensObj", "token", "primaryGVUIDObj",
					"primaryGVUID", "gvuid", "typeId", "typeIdValue", "add_GVUIDSObj", "add_gvuid", "add_typeId",
					"add_typeIdValue", "buildData", "buildDataSource", "vehicleOptions" };
			System.out.println("Writing to Excel for Vin# = " + kStr + ". Waiting ...");
			cPP.com_libs.writeTitle(finalResultfile, tString);
			for (int i = 0; i <= endCompRow + 1; i++) {
				cPP.com_libs.writeToSheet(finalResultfile, finalResoult[i]);
			}
			System.out.println("Vin = " + kStr + " Ended..." + kStr);
		}
		System.out.println("All completed!");
	}
}
