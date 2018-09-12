package cPP;

import java.io.IOException;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONObject;

public class getVINDecoderDetailsCA {
	private static int blank = 0;
	private static int noObj = 0;

	public static void getVINDecoderDetailsCA() throws Exception {
		Properties prop = new Properties();
		try {
			prop.load(JSONParser.class.getClassLoader().getResourceAsStream("cPP_data/jsondata.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// String env = prop.getProperty("environment");
		String[] vinNums = com_libs.fetchOneDemArrayFromPropFile("VinDecoder_Vins", prop);
		// String[] vinNums = { "3GCPCREC3FG408056", "1GCHSCE38F1271361" };

		String VinDecoderWSSavePathFile = "C:\\1\\Eclipse\\Test Results\\VinDecode\\VinDecoderWS_Return.xls";
		// String jSONText = "";
		// jSONParser(jSONText);// start with { (curly brace - object) OK
		String[] titleStringVinDecoderWS = { "S/N", "buildDataFound:", "vin:", "modelYear:", "make:", "model:",
				"modelCode:", "trim_01:", "Body Description:", "Installed Options List:", "styleIds:", "total styles:",
				"styleId_01:" };
		String envURL = "http://lnoc-q1cp-xmw1:8080/VinId/vin/";
		int count = 0;
		for (String vin : vinNums) {
			count++;
			String vinDecoderURL = envURL + vin;
			String jsonTextFrVindecoderWS = com_libs.getSourceCode(vinDecoderURL, "");
			getVINDecoderDetailsWS(VinDecoderWSSavePathFile, titleStringVinDecoderWS, jsonTextFrVindecoderWS, vin,
					count);

		}

	}

	public static void getVINDecoderDetailsWS(String wsResultfile, String[] titleVinDecoderWS, String text,
			String vinNum, int countNum) throws IOException {
		com_libs.writeTitle(wsResultfile, titleVinDecoderWS);
		String modelCode = "";
		String buildDate = "";
		String source = "";
		String options = "";
		String country = "";
		double engineDisplacementL = 0;

		String[] temp = new String[30];
		if (text.equals("")) {
			blank++;
			temp[0] = Integer.toString(countNum);
			temp[1] = "404 error";
			temp[2] = vinNum;
			com_libs.writeToSheet(wsResultfile, temp);
		} else {
			try {
				JSONObject obj = new JSONObject(text);
				long serverTime = obj.getLong("serverTime");
				String serverTimeString = obj.getString("serverTimeString");
				String serverName = obj.getString("serverName");
				int executionTimeMS = obj.getInt("executionTimeMS");
				JSONObject result = obj.getJSONObject("result");

				String submittedVIN = result.getString("submittedVIN");
				String vin = result.getString("vin");
				int modelId = result.getInt("modelId");
				String identificationCommand = result.getString("identificationCommand");

				// buildData obj:
				JSONObject buildData = result.getJSONObject("buildData");
				boolean buildDataFound = buildData.getBoolean("buildDataFound");

				try {
					modelCode = buildData.getString("modelCode");
				} catch (Exception ex) {
					modelCode = "null";
				}
				try {
					buildDate = buildData.getString("buildDate");
				} catch (Exception ex) {
					buildDate = "null";
				}
				try {
					source = buildData.getString("source");
				} catch (Exception ex) {
					source = "null";
				}

				try {
					JSONArray optionsObj = buildData.getJSONArray("options");
					options = optionsObj.toString();
				} catch (Exception ex) {
					options = "null";
				}
				try {
					country = buildData.getString("country");
				} catch (Exception ex) {
					country = "null";
				}
				// unitedStatesPattern obj
				JSONObject unitedStatesPattern = result.getJSONObject("canadaPattern");
				int patternMatchIndex = unitedStatesPattern.getInt("patternMatchIndex");
				String pattern = unitedStatesPattern.getString("pattern");

				try {
					engineDisplacementL = unitedStatesPattern.getDouble("engineDisplacementL");
				} catch (Exception ex) {
					engineDisplacementL = -1;
				}

				String modelYear = unitedStatesPattern.getString("modelYear");
				String division = unitedStatesPattern.getString("division");
				String model = unitedStatesPattern.getString("model");
				String styleName = unitedStatesPattern.getString("styleName");
				JSONArray styleIdsObj = unitedStatesPattern.getJSONArray("styleIds");
				String styleIds = styleIdsObj.toString();

				// styles obj
				int styleId_01 = 0;
				String trim_01 = "";
				String styleDesc_01 = "";
				String mmc_01 = "";
				JSONArray stylesObj = result.getJSONArray("styles");
				String styles = stylesObj.toString();
				// int styleId_01=stylesObj.getJSONObject(0).getInt("styleId");
				// int styleId_02=stylesObj.getJSONObject(1).getInt("styleId");
				int styleid_count = stylesObj.length();
				if (styleid_count > 0) {
					int[] styleArray = new int[styleid_count];
					String[] trim = new String[styleid_count];
					String[] styleDesc = new String[styleid_count];
					String[] mmc = new String[styleid_count];
					for (int i = 0; i < styleid_count; i++) {
						styleArray[i] = stylesObj.getJSONObject(i).getInt("styleId");
						trim[i] = stylesObj.getJSONObject(i).getString("trim");
						styleDesc[i] = stylesObj.getJSONObject(i).getString("styleDesc");
						mmc[i] = stylesObj.getJSONObject(i).getString("mmc");
					}
					styleId_01 = styleArray[0];
					trim_01 = trim[0];
					styleDesc_01 = styleDesc[0];
					mmc_01 = mmc[0];

				}

				// belong to result Obj
				boolean validVIN = result.getBoolean("validVIN");// -- same level with buildData object

				// Stop here!

				// Old invalid below:

				System.out.println("S/N: " + countNum);
				System.out.println("buildDataFound: " + buildDataFound);
				System.out.println("vin: " + vin);
				System.out.println("modelYear: " + modelYear);
				System.out.println("make: " + division);
				System.out.println("model: " + model);
				System.out.println("modelCode: " + mmc_01);
				// System.out.println("modelYear: " + modelYear);
				System.out.println("trim_01: " + trim_01);
				System.out.println("Body Description: " + styleDesc_01);
				System.out.println("Installed Options List: " + options);
				System.out.println("styleIds: " + styles);

				System.out.println("total styles: " + styleid_count);

				System.out.println("styleId_01: " + styleId_01);

				int wSize = titleVinDecoderWS.length;
				String[] jsonValue = new String[wSize];

				jsonValue[0] = Integer.toString(countNum);
				jsonValue[1] = Boolean.toString(buildDataFound);
				jsonValue[2] = vin;
				jsonValue[3] = modelYear;
				jsonValue[4] = division;
				jsonValue[5] = model;
				jsonValue[6] = mmc_01;// model code, select the matching one.
				jsonValue[7] = trim_01;
				jsonValue[8] = styleDesc_01;
				jsonValue[9] = options;
				jsonValue[10] = styles;
				jsonValue[11] = Integer.toString(styleid_count);
				jsonValue[12] = Integer.toString(styleId_01);
				// jsonValue[13] = vinNum;
				// jsonValue[14] = vinNum;

				com_libs.writeToSheet(wsResultfile, jsonValue);

			} catch (Exception ex) {
				System.out.println("error occurs!");
				noObj++;
				temp[0] = Integer.toString(countNum);
				temp[1] = "200 error";
				temp[2] = vinNum;
				com_libs.writeToSheet(wsResultfile, temp);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// // From what I can read on json.org, all JSON strings should start with { (curly brace - object), and [ characters (square brackets - array) represent an array element in JSON.
		// // ******************************************************curly brace - object*********************************************************
		getVINDecoderDetailsCA();
		// jSonObjec_CPP_BuildDataExtractOrchestrationWS();
		// // ******************************************************End of curly brace - object*********************************************************

		// //// ******************************************************start with [ characters (square brackets - array*********************************************************
		//
		// jSonArry_UnityInventory(); //On Tuesday Auguest 30, 2016: API returns { "message": "An error has occurred." } from https://data.dealervideos.com/v1.0/vehicles/2FHFIYI
		// // //******************************************************End of start with [ characters (square brackets - array*********************************************************

		System.out.println("Complete!!!");
	}

}
