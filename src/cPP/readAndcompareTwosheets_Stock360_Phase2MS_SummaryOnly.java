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

public class readAndcompareTwosheets_Stock360_Phase2MS_SummaryOnly {
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
		// for (int i = rowStart; i <= rowEnd; i++) {
		for (int i = rowStart; i <= n; i++) {
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

		// for (int i = rowStart; i <= rowEnd; i++) {
		for (int i = rowStart; i <= n; i++) {

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
		int colTotal = 28 + 3;/// there are 3 header columns
		// Key column start
		int keyColumnStart = 4;/// there are 3 header columns
		// StartCompareRow
		int startCompRow = 1;
		// EndCompareRow
		int endCompRow = rowEnd2 - rowStart2 + 1;
		// StartCompareColumn
		int startCompCol = 4;// 1;//4;
		int endCompCol = 28 + 3;// there are 3 header columns
		// ***********************************************VIN1******************************************************************

		System.out.println("Start...");
		final int ROWS = 12000;
		// ***********************************************VINs******************************************************************
		// **********************************************VINs DATA Info*********************************************************
		// *********************************************************************************************************************
		String expectedSheetFolder = "20170831_180";
		String realSheetFolder = "20170915_180";
		String compareSheetFolder = realSheetFolder;
		int sheetrows[] = {10439,9908, 8899, 600, 589, 597, 589, 581, 596, 589 };// see C:\1\Eclipse\Test Results\CompareMS\ImageMS\20170405\image_1.xls last line number.
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
			String pre_path = "C:\\1\\Eclipse\\Test Results\\Colorized360 Phase2\\stock360\\";
			String filePath1 = pre_path + expectedSheetFolder + "\\Stock360Phase2_" + kStr + ".xls";
			// C:\1\Eclipse\Test Results\CompareMS\ImageBy_modelYearIds_CPP-950\20170505
			// "C:\\1\\Eclipse\\Test Results\\CompareMS\\ImageMS\\" + dateFolder
			// + "\\image_" + kStr + ".xls";

			String sheetname1 = "Sheet0";// "Expected";//

			// ************************Real tested sheet************************
			// String filePath2 = "C:\\1\\Eclipse\\Test Results\\Colorized360\\stock360\\" + realSheetFolder
			// + "\\getStock360_Vin_" + kStr + ".xls";

			String filePath2 = pre_path + realSheetFolder + "\\Stock360Phase2_" + kStr + ".xls";

			String sheetname2 = "Sheet0";// "Expected";//

			// After comparing: Save filePaht************************
			// String finalResultfile = "F:\\Quality Assurance\\AUTOpx\\CPP\\Stock360\\20170315\\Compare_getStock360_Vin_1.xls";
			String SummaryfinalResultfile = pre_path + compareSheetFolder + "\\Summary_Stock360_Phase2MS_"
					+ expectedSheetFolder + "_" + realSheetFolder + "_Vin_" + kStr + ".xls";
			String finalResultfile = pre_path + compareSheetFolder + "\\Compare_Stock360_Phase2MS_"
					+ expectedSheetFolder + "_" + realSheetFolder + "_Vin_" + kStr + ".xls";
			String[][] expectedResoult = new String[ROWS][50];
			String[][] realResoult = new String[ROWS][50];
			String[][] tempResult = new String[ROWS][50];
			String[][] finalResoult = new String[ROWS][50];
			int expectedSheetRows = 0;
			int realSheetRows = 0;

			expectedResoult = readSheet(filePath1, sheetname1, rowStart1 - 1, rowEnd1 - 1, colStart1 - 1, colTotal,
					keyColumnStart);
			realResoult = readSheet(filePath2, sheetname2, rowStart2 - 1, rowEnd2 - 1, colStart2 - 1, colTotal,
					keyColumnStart);
			int expectedResoultTotalRows = expectedResoult.length;
			int realResoultTotalRows = realResoult.length;
			// ******************************ExpectedResoult******************************
			int testCaseCount = 0;
			int expectedCount = 0;
			int testCaseNumber = 0;
			int preTestCaseNumber = 1;
			for (int i = 0; i < expectedResoultTotalRows; i++) {
				System.out.println("TestCaseNumber=" + expectedResoult[i][3]);
				testCaseCount++;
				if (i == 594) {
					System.out.println("TestCaseNumber=");
				}
				try {
					testCaseNumber = Integer.valueOf(expectedResoult[i][3]);
					if ((testCaseNumber > 0)) {
						if (preTestCaseNumber == testCaseNumber) {
							preTestCaseNumber = testCaseNumber;
							tempResult[testCaseNumber][0] = String.valueOf(testCaseNumber);
							tempResult[testCaseNumber][1] = String.valueOf(testCaseCount);
							System.out.println(
									"TestCaseNumber=" + preTestCaseNumber + "; Total=" + tempResult[testCaseNumber][1]);
							expectedCount++;
						} else {
							preTestCaseNumber = testCaseNumber;
							tempResult[testCaseNumber][0] = String.valueOf(testCaseNumber);
							testCaseCount = 1;// reset count
							tempResult[testCaseNumber][1] = String.valueOf(testCaseCount);
							System.out.println(
									"TestCaseNumber=" + preTestCaseNumber + "; Total=" + tempResult[testCaseNumber][1]);
							expectedCount++;

						}
					}

				} catch (NumberFormatException e) {
					System.out.println("Error shows" + e);
					break;
				}
			}
			int expectedTestCaseCount = testCaseNumber;

			System.out.println("Expected Test Case Count=" + expectedTestCaseCount);
			for (int j = 0; j < expectedTestCaseCount + 1; j++) {
				System.out
						.println("Expected Tset case numbers and total =" + tempResult[j][0] + ", " + tempResult[j][1]);
			}

			// ************************RealResoult******************************
			testCaseCount = 0;
			expectedCount = 0;
			testCaseNumber = 0;
			preTestCaseNumber = 1;
			for (int i = 0; i < realResoultTotalRows; i++) {
				System.out.println("TestCaseNumber=" + realResoult[i][3]);
				testCaseCount++;
				if (i == 582) {
					System.out.println("TestCaseNumber=");
				}
				try {
					testCaseNumber = Integer.valueOf(realResoult[i][3]);
					if ((testCaseNumber > 0)) {
						if (preTestCaseNumber == testCaseNumber) {
							preTestCaseNumber = testCaseNumber;
							tempResult[testCaseNumber][0] = String.valueOf(testCaseNumber);
							tempResult[testCaseNumber][2] = String.valueOf(testCaseCount);
							System.out.println(
									"TestCaseNumber=" + preTestCaseNumber + "; Total=" + tempResult[testCaseNumber][2]);
							expectedCount++;
						} else {
							preTestCaseNumber = testCaseNumber;
							tempResult[testCaseNumber][0] = String.valueOf(testCaseNumber);
							testCaseCount = 1;// reset count
							tempResult[testCaseNumber][2] = String.valueOf(testCaseCount);
							System.out.println(
									"TestCaseNumber=" + preTestCaseNumber + "; Total=" + tempResult[testCaseNumber][2]);
							expectedCount++;

						}
					}

				} catch (NumberFormatException e) {
					System.out.println("Error shows" + e);
					break;
				}
			}
			System.out.println("Real Test Case Count=" + testCaseNumber);
			for (int j = 0; j < testCaseNumber + 1; j++) {
				System.out.println("Real Tset case numbers and total =" + tempResult[j][0] + ", " + tempResult[j][1]
						+ ", " + tempResult[j][2]);
			}

			int finalTestCaseCount = 0;
			if (testCaseNumber == expectedTestCaseCount) {
				finalTestCaseCount = testCaseNumber;
			} else if (testCaseNumber < expectedTestCaseCount) {
				finalTestCaseCount = expectedTestCaseCount;
			} else {
				finalTestCaseCount = testCaseNumber;
			}
			// check to see if equal
			int expectedCountInteger = 0;
			int realCountInteger = 0;

			for (int j = 1; j < finalTestCaseCount + 1; j++) {
				try {
					expectedCountInteger = Integer.valueOf(tempResult[j][1]);
				} catch (NumberFormatException e) {
					expectedCountInteger = 0;
				}
				try {
					realCountInteger = Integer.valueOf(tempResult[j][2]);
				} catch (NumberFormatException e) {
					realCountInteger = 0;
				}
				if (expectedCountInteger == realCountInteger) {
					tempResult[j][3] = "Yes";
					System.out.println("Real Tset case numbers and total =" + tempResult[j][0] + ", " + tempResult[j][1]
							+ ", " + tempResult[j][2] + ", " + tempResult[j][3]);
				} else {
					tempResult[j][3] = "No";
					System.out.println("Real Tset case numbers and total =" + tempResult[j][0] + ", " + tempResult[j][1]
							+ ", " + tempResult[j][2] + ", " + tempResult[j][3]);
				}
			}

			System.out.println("Writing to Excel = " + kStr + ". Waiting ...");
			String[] t0String = { "Test Case Number", "Expected Sheet", "Real Sheet", "Equal" };
			cPP.com_libs.writeTitle(SummaryfinalResultfile, t0String);
			for (int i = 0; i <= testCaseNumber + 2; i++) {
				cPP.com_libs.writeToSheet(SummaryfinalResultfile, tempResult[i]);
			}
			System.out.println("Writing to Excel is complete! \n");

			// compareSheetCell(String[][] A, String[][] B, int keyPosition)
			// Stop here!!!***********************************************************************************
			// Stop here!!!***********************************************************************************
			// Stop here!!!***********************************************************************************
			// finalResoult = compareSheetCell(expectedResoult, realResoult, startCompRow - 1, endCompRow - 1,
			// startCompCol - 1, endCompCol - 1, keyColumnStart - 1);
			// // Save to Excel sheet.xls
			// // String [] tString=new String[35];
			// String[] tString = { "Num", "Date Time", "Position S/N", "S/N", "URL_parameter", "RequestBody",
			// "serverTime", "serverTimeString", "serverName", "executionTimeMS", "resultObj", "modelYearId",
			// "imageName", "colorId", "primaryColorCode", "primaryColorDesc", "genericColor",
			// "secondaryColorCode", "secondaryColorDesc", "color2GenericDesc", "accentColorCode",
			// "accentColorDesc", "color3GenericDesc", "optionCode", "shotDescription", "rangeDescription",
			// "groupDescription", "shotId", "rangeId", "groupId", "ImageViews" };
			// System.out.println("Writing to Excel for Vin# = " + kStr + ". Waiting ...");
			// cPP.com_libs.writeTitle(finalResultfile, tString);
			// for (int i = 0; i <= endCompRow + 1; i++) {
			// cPP.com_libs.writeToSheet(finalResultfile, finalResoult[i]);
			// }
			// System.out.println("Vin = " + kStr + " Ended..." + kStr);
		}
		System.out.println("All completed!");
	}
}
