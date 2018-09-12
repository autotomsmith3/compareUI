package cPP;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
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
import org.json.JSONArray;
import org.json.JSONObject;

public class getFuelEconomy {

	public static void feulEconomyWS(String wsResultfile, String[] titleFeulEconomyWS, String text, String vinNum)
			throws IOException {
		String[] temp = new String[9];
		if (text.equals("")) {
			// jsonValue[0] = Integer.toString(i);
			// jsonValue[1] = type;
			// jsonValue[2] = Integer.toString(city);
			// jsonValue[3] = Integer.toString(hwy);
			// jsonValue[4] = Integer.toString(combined);
			// jsonValue[5] = mileageUnit;
			// jsonValue[6] = range;
			// jsonValue[7] = rangeUnit;
			temp[8] = vinNum;
			com_libs.writeToSheet(wsResultfile, temp);
		} else {
			JSONObject obj = new JSONObject(text);
			String id = obj.getString("id");
			String serverTime = obj.getString("serverTime");
			boolean error = obj.getBoolean("error");
			int executionTimeMS = obj.getInt("executionTimeMS");
			JSONObject result = obj.getJSONObject("result");
			boolean errors = result.getBoolean("errors");
			JSONArray fuel = result.getJSONArray("fuelEconomyList");
			System.out.println("id: " + obj.getString("id"));
			System.out.println("serverTime: " + obj.getString("serverTime"));
			System.out.println("error: " + obj.getBoolean("error"));
			System.out.println("executionTimeMS: " + obj.getInt("executionTimeMS"));
			System.out.println("obj: " + obj);
			System.out.println("result: " + result);
			System.out.println("fuelEconomyList: " + fuel);
			int wSize = titleFeulEconomyWS.length;
			String[] jsonValue = new String[wSize];
			com_libs.writeTitle(wsResultfile, titleFeulEconomyWS);
			// String pageName = obj.getJSONObject("posts").getString("pageName");
			int city = 0;
			int hwy = 0;
			int combined = 0;
			String mileageUnit = "";
			JSONArray arr = result.getJSONArray("fuelEconomyList");
			int sizeArray = arr.length();
			for (int i = 0; i < sizeArray; i++) {
				String type = arr.getJSONObject(i).getString("type");
				try {
					city = arr.getJSONObject(i).getInt("city");
				} catch (Exception ex) {
					city = 0;
				}
				try {
					hwy = arr.getJSONObject(i).getInt("hwy");
				} catch (Exception ex) {
					hwy = 0;
				}
				try {
					combined = arr.getJSONObject(i).getInt("combined");
				} catch (Exception ex) {
					combined = 0;
				}
				try {
					mileageUnit = arr.getJSONObject(i).getString("mileageUnit");
				} catch (Exception ex) {
					mileageUnit = "null";
				}
				String range = arr.getJSONObject(i).getString("range");
				// String comments = arr.getJSONObject(i).getString("comments");
				String rangeUnit = arr.getJSONObject(i).getString("rangeUnit");
				jsonValue[0] = Integer.toString(i);
				jsonValue[1] = type;
				jsonValue[2] = Integer.toString(city);
				jsonValue[3] = Integer.toString(hwy);
				jsonValue[4] = Integer.toString(combined);
				jsonValue[5] = mileageUnit;
				jsonValue[6] = range;
				jsonValue[7] = rangeUnit;
				jsonValue[8] = vinNum;
				com_libs.writeToSheet(wsResultfile, jsonValue);

				System.out.println("S/N:" + i + ", type:" + type + ", city:" + city + ", hwy:" + hwy + ", combined:"
						+ combined + ", mileageUnit:" + mileageUnit + ", range:" + range + ", rangeUnit:" + rangeUnit
						+ ", vinNum:" + vinNum);

			}

		}
	}

	public static void getFuelEconomy() throws IOException {
		Properties prop = new Properties();
		try {
			prop.load(getFuelEconomy.class.getClassLoader().getResourceAsStream("cPP_data/jsondata.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// String env = prop.getProperty("environment");
		String[] vinNums = com_libs.fetchOneDemArrayFromPropFile("fuel_economy_vins", prop);
		// String[] vinNums = { "3GCPCREC3FG408056", "1GTN2LEH6GZ134145", "W04WV3N58GG042641" };

		String FeulEconomyWSSavePathFile = "C:\\1\\Eclipse\\Test Results\\cpp\\FeulEconomyWS_Return.xls";
		// String jSONText = "";
		// jSONParser(jSONText);// start with { (curly brace - object) OK
		String[] titleStringFeulEconomyWS = { "S/N", "type:", "city:", "hwy:", "combined:", "mileageUnit:", "range:",
				"rangeUnit:", "VIN:" };

		// ************QA**********:
		// http://lnoc-q1cp-xws1:8080/fuel-economy-orchestration/rest/fueleconomy/1G1PE5SB1G7169014?country=US&language=EN
		String env = "http://lnoc-q1cp-xws1:8080/fuel-economy-orchestration/rest/fueleconomy/";

		// ************QA**********:
		// // ************DEV**********:
		// String env = "http://lnoc-dvcp-xws1:8080/fuel-economy-orchestration/rest/fueleconomy/";
		//
		// // ************DEV**********:
		//// // ************Prod********:
		// String env = "http://lnoc-dvcp-xws1:8080/fuel-economy-orchestration/rest/fueleconomy/";
		// String [] vinNums = {"3GCPCREC3FG408056","1GTN2LEH6GZ134145","W04WV3N58GG042641"};
		//// // ************Prod********:

		for (String vin : vinNums) {

			String feulEconomyURL = env + vin + "?country=US&language=EN";
			String jsonTextFrFeulEconomyWS = com_libs.getSourceCode(feulEconomyURL, "");
			feulEconomyWS(FeulEconomyWSSavePathFile, titleStringFeulEconomyWS, jsonTextFrFeulEconomyWS, vin);

		}
	}

	public static void main(String[] args) throws Exception {
		// // From what I can read on json.org, all JSON strings should start with { (curly brace - object), and [ characters (square brackets - array) represent an array element in JSON.
		// // ******************************************************curly brace - object*********************************************************
		// jSonObjec_SydicationWS();
		getFuelEconomy();
		// jSonObjec_CPP_MasterFeatureWS();
		// jSonObjec_CPP_BuildDataExtractOrchestrationWS();
		// // ******************************************************End of curly brace - object*********************************************************

		// //// ******************************************************start with [ characters (square brackets - array*********************************************************
		//
		// jSonArry_UnityInventory(); //On Tuesday Auguest 30, 2016: API returns { "message": "An error has occurred." } from https://data.dealervideos.com/v1.0/vehicles/2FHFIYI
		// // //******************************************************End of start with [ characters (square brackets - array*********************************************************

		System.out.println("Complete!!!");

	}
}