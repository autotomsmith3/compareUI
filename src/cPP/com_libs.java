package cPP;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;

public class com_libs {
	public static String getSourceCode(String prefix, String url) {
		String inputLine = "";
		String inputLineOne = "";
		try {
			URL MyURL = new URL(prefix + url);
			BufferedReader in = new BufferedReader(new InputStreamReader(MyURL.openStream(), "UTF8"));
			while ((inputLineOne = in.readLine()) != null) {
				inputLine = inputLine + inputLineOne + "\n";
			}
			in.close();
		} catch (Exception ee) {
			System.out.println("Exception through out: " + ee);
		}
		return inputLine;
	}

	public static String getSourceCode(String urlParameters, String url1, String url2) throws Exception {
		// PUT method - works
		final String USER_AGENT = "Mozilla/5.0";
		URL obj = new URL(url1 + url2);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("PUT");// for daaSNI is "POST"
		con.setRequestProperty("User-Agent", USER_AGENT);
		// con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		// //Original
		con.setRequestProperty("Accept-Language", "en-US,fr-CA;q=0.7,en;q=0.3");
		// Send post request en-US,fr-CA;q=0.7,en;q=0.3
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		String outputString;
		if (!(responseCode == 404) && !(responseCode == 400) && !(responseCode == 503) && !(responseCode == 500)) {
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF8"));
			String inputLine;
			StringBuffer postData = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				postData.append(inputLine);
			}
			in.close();
			outputString = postData.toString();
			con.disconnect();
		} else {
			outputString = "";
		}
		return outputString;
	}

	public static String getSourceCodeJson(String urlParameters, String url1, String url2) throws Exception {
		// PUT method Json - works
		final String USER_AGENT = "Mozilla/5.0";
		URL obj = new URL(url1 + url2);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("PUT");// for daaSNI is "POST"
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Content-Type", "application/json");
		// con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		// //Original
		con.setRequestProperty("Accept-Language", "en-US,fr-CA;q=0.7,en;q=0.3");
		// Send post request en-US,fr-CA;q=0.7,en;q=0.3
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		String outputString;
		if (!(responseCode == 404) && !(responseCode == 400) && !(responseCode == 503) && !(responseCode == 500)) {
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF8"));
			String inputLine;
			StringBuffer postData = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				postData.append(inputLine);
			}
			in.close();
			outputString = postData.toString();
			con.disconnect();
		} else {
			outputString = "";
		}
		return outputString;
	}

	public static String getSourceCodeJson(String url1, String auth_key) throws Exception {
		// GET method - Works after removed below #1 to #5
		// add auth_key in Headers
		final String USER_AGENT = "Mozilla/5.0";
		URL obj = new URL(url1);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");//
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("auth_key", auth_key);
		// con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		// //Original
		con.setRequestProperty("Accept-Language", "en-US,fr-CA;q=0.7,en;q=0.3");
		// Send post request en-US,fr-CA;q=0.7,en;q=0.3
		con.setDoOutput(true);
		// #1 DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		// #2 String urlParameters="urlParameters";
		// #3 wr.writeBytes(urlParameters);
		// #4 wr.flush();
		// #5 wr.close();

		int responseCode = con.getResponseCode();
		String outputString;
		if (!(responseCode == 404) && !(responseCode == 400) && !(responseCode == 401) && !(responseCode == 402)
				&& !(responseCode == 403) && !(responseCode == 503) && !(responseCode == 500)) {
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF8"));
			String inputLine;
			StringBuffer postData = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				postData.append(inputLine);
			}
			in.close();
			outputString = postData.toString();
			con.disconnect();
		} else {
			outputString = "";
		}
		return outputString;
	}

	public static String getSourceCodeJson(String urlParameters, String url1, String url2, String auth_key)
			throws Exception {
		// POST method - works
		// add auth_key in Headers
		final String USER_AGENT = "Mozilla/5.0";
		URL obj = new URL(url1 + url2);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");// for daaSNI is "POST"
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("auth_key", auth_key);
		// con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		// //Original
		con.setRequestProperty("Accept-Language", "en-US,fr-CA;q=0.7,en;q=0.3");
		// Send post request en-US,fr-CA;q=0.7,en;q=0.3
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		String outputString;
		if (!(responseCode == 404) && !(responseCode == 400) && !(responseCode == 503) && !(responseCode == 500)) {
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF8"));
			String inputLine;
			StringBuffer postData = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				postData.append(inputLine);
			}
			in.close();
			outputString = postData.toString();
			con.disconnect();
		} else {
			outputString = "";
		}
		return outputString;
	}

	public static String getSourceCodeJsonPost(String urlParameters, String url1, String url2) throws Exception {
		// POST method - works
		final String USER_AGENT = "Mozilla/5.0";
		URL obj = new URL(url1 + url2);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");// for daaSNI is "POST"
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("api_key", "QWERTYUIOP"); //This is for Car360 Login API only
		// con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		// //Original
		con.setRequestProperty("Accept-Language", "en-US,fr-CA;q=0.7,en;q=0.3");
		// Send post request en-US,fr-CA;q=0.7,en;q=0.3
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		String outputString;
		if (!(responseCode == 404) && !(responseCode == 400) && !(responseCode == 403) && !(responseCode == 503)
				&& !(responseCode == 500)) {
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF8"));
			String inputLine;
			StringBuffer postData = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				postData.append(inputLine);
			}
			in.close();
			outputString = postData.toString();
			con.disconnect();
		} else {
			outputString = "";
		}
		return outputString;
	}

	public static String[] fetchOneDemArrayFromPropFile(String propertyName, Properties propFile) {
		// get array split up by the colin
		String a[] = propFile.getProperty(propertyName).split(",");
		return a;
	}

	public static String[][] fetchArrayFromPropFile(String propertyName, Properties propFile) {

		// get array split up by the semicolin
		String[] a = propFile.getProperty(propertyName).split(";");

		// create the two dimensional array with correct size
		String[][] array = new String[a.length][a.length];

		// combine the arrays split by semicolin and comma
		for (int i = 0; i < a.length; i++) {
			array[i] = a[i].split(",");
		}
		return array;
	}

	public static void writeTitle(String resultfile, String[] tString) throws IOException {
		int tStringLength = tString.length;
		int n = 0;
		File f = new File(resultfile);
		if (f.exists()) {
			FileInputStream file = new FileInputStream(new File(resultfile));
		} else {
			// Write an empty file with a title.
			HSSFWorkbook workbook = new HSSFWorkbook();
			// Create first sheet from the workbook
			HSSFSheet s = workbook.createSheet();
			HSSFCellStyle hdstyle = workbook.createCellStyle();
			hdstyle.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
			hdstyle.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);

			Font titleFont = workbook.createFont();
			titleFont.setFontHeight((short) 220);
			titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
			hdstyle.setFont(titleFont);

			s.createRow(0);
			n = s.getLastRowNum();
			Row r1 = s.getRow(n);
			n = r1.getRowNum();

			for (int i = 0; i < tStringLength; i++) {
				Cell cell0 = r1.createCell(i);
				cell0.setCellValue(tString[i]);
				cell0.setCellStyle(hdstyle);
			}

			hdstyle.setFillPattern(CellStyle.SOLID_FOREGROUND);

			try {
				FileOutputStream out = new FileOutputStream(resultfile); //
				workbook.write(out);
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void writeToSheet(String resultfile, String[] jSONValues) throws IOException {
		int n = 0;
		String sName, passOrfail, dateStamp, timeStamp;
		Calendar cal = Calendar.getInstance();
		cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		timeStamp = sdf.format(cal.getTime());
		DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
		Date d = new Date();
		dateStamp = df.format(d);
		timeStamp = dateStamp + "  " + timeStamp;
		// writeTitle(resultfile);

		FileInputStream file = new FileInputStream(new File(resultfile));
		int jSONValuesLength = jSONValues.length;

		// Get the workbook instance for XLS file
		HSSFWorkbook workbook = new HSSFWorkbook(file);

		// Get first sheet from the workbook
		HSSFSheet s = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = s.iterator();

		n = s.getLastRowNum();
		sName = s.getSheetName();
		s.createRow(n + 1);
		n = s.getLastRowNum();
		Row r1 = s.getRow(n);
		n = r1.getRowNum();

		for (int i = 0; i < jSONValuesLength; i++) {
			r1.createCell(i).setCellValue(jSONValues[i]);
		}

		try {
			FileOutputStream out = new FileOutputStream(resultfile);
			workbook.write(out);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
