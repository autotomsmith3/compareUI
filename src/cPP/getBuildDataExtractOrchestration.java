package cPP;

import java.io.IOException;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONObject;

public class getBuildDataExtractOrchestration {

	private static int blank = 0;
	private static int noObj = 0;

	public static void jSonObjec_CPP_BuildDataExtractOrchestrationWS() throws Exception {
		Properties prop = new Properties();
		try {
			prop.load(JSONParser.class.getClassLoader().getResourceAsStream("cPP_data/jsondata.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// String env = prop.getProperty("environment");
		String[] vinNums = com_libs.fetchOneDemArrayFromPropFile("build_data_vins", prop);
		// String[] vinNums = { "3GCPCREC3FG408056", "1GTN2LEH6GZ134145", "W04WV3N58GG042641" };

		String BuildDataExtractPathFile = "C:\\1\\Eclipse\\Test Results\\BuildDataExtractWS\\BuildDataExtractWS_Return.xls";
		// String jSONText = "";
		// jSONParser(jSONText);// start with { (curly brace - object) OK
		String[] titleStringBuildDataExtractWS = { "S/N", "VIN:", "modelYear:", "make:", "model", "modelCode:", "trim:",
				"subTrim/Package?:", "bodyDescription:", "seatingCapacity:", "engineDescription:", "horsepower:",
				"transmission Description:", "driveTrain Description:", "wheelBase:", "payloadCapacity:", "fuelCity:",
				"fuelHighway:", "exteriorCode:", "exteriorColour:", "interiorCode:", "interiorColour:", "interiorDesc:",
				"baseMSRP:", "standardEquipment:", "installedOptions:", "safetyRating:", "factoryWarranty:",
				"totalMSRP:", "fuelCombined:", "destinationCharge:", "invoicePrice:", "buildDate:", "id:",
				"serverTime:", "error", "executionTimeMS", "error2", "", "", "OEM", "Brand", "Year", "Model",
				"modelID:", "Acode", "styleID:", "gvuid:" };

		// ************QA**********:
		//// http://lnoc-dvcp-xws1.autodatacorp.org:8080/build-data-extract-orchestration/rest/extract/1GCHSCE38F1271361
		// String envURL = "http://lnoc-dvcp-xws1.autodatacorp.org:8080/build-data-extract-orchestration/rest/extract/";
		 String env = "http://lnoc-q1cp-xws1.autodatacorp.org:8080/build-data-extract-orchestration/rest/extract/";
		 //QA from Shange: "http://lnoc-q1cp-xws1:8080/build-data-extract-orchestration/rest/extract/1GCHSCE38F1271361"
		 //                 "http://lnoc-q1cp-xws1.autodatacorp.org:8080/build-data-extract-orchestration/rest/extract/";
		// ************QA**********:
		// // ************DEV**********
		// http://lnoc-dvcp-xws1.autodatacorp.org:8080/build-data-extract-orchestration/rest/extract/1GCHSCE38F1271361 //Mike's
		// http://lnoc-dvcp-xws1.autodatacorp.org:8080/build-data-extract-orchestration/rest/extract/3GCPCREC3FG408056 //Tyson's
		// String env = "http://lnoc-dvcp-xws1.autodatacorp.org:8080/build-data-extract-orchestration/rest/extract/";
		//
		// // ************DEV**********:
		//// // ************Prod********:
		// http://azur-prcp-xlb1.centralus.cloudapp.azure.com/build-data-extract-orchestration/rest/extract/KL4CJDSB8GB649280
//		 String env = "http://azur-prcp-xlb1.centralus.cloudapp.azure.com/build-data-extract-orchestration/rest/extract/";
		// String [] vinNums = {"3GCPCREC3FG408056","1GTN2LEH6GZ134145","W04WV3N58GG042641"};
		//// // ************Prod********:
		// String masterFeatureParameters="vin=\"3GCPCREC3FG408056\"&used=true&unstructuredText=tires: p245/35r20 performance summer&unstructuredText=auto-dimming interior mirror w/homelink/compass&unstructuredText=power w/tilt down heated mirrors";
		// String masterFeatureParameters = "{\"vin\":\"1G1PE5SB1G7169014\"}";// 3GCPCREC3FG408056, 1G1PE5SB1G7169014
		int sn=0;
		for (String vin : vinNums) {
			sn=sn+1;
			String buildDataExtractURL = env + vin;
			String jsonTextFrBuildDataExtractOrchestrationWS = com_libs.getSourceCode(buildDataExtractURL, "");
			// stop here!
			cppBuildDataExtractWS2(BuildDataExtractPathFile, titleStringBuildDataExtractWS,
					jsonTextFrBuildDataExtractOrchestrationWS, vin,sn);
			System.out.println("S/N: "+sn+". Vin=" + vin + " has been completed!");
		}

	}

	public static void cppBuildDataExtractWS(String wsResultfile, String[] titleBuildDataWS, String text, String vinNum)
			throws IOException {
		com_libs.writeTitle(wsResultfile, titleBuildDataWS);
		String[] temp = new String[50];
		if (text.equals("")) {
			blank++;
			temp[0] = Integer.toString(blank);
			temp[1] = vinNum;
			temp[2] = "404 error";
			temp[3] = "404 error";
			temp[4] = "";
			com_libs.writeToSheet(wsResultfile, temp);
		} else {
			try {
				JSONObject obj = new JSONObject(text);
				String id = obj.getString("id");
				String serverTime = obj.getString("serverTime");
				boolean error = obj.getBoolean("error");
				int executionTimeMS = obj.getInt("executionTimeMS");
				JSONObject result = obj.getJSONObject("result");
				boolean error2 = result.getBoolean("error");
				String styleID = result.getString("styleID");
				String gvuid = result.getString("gvuid");
				String vin = result.getString("vin");
				int modelYear = result.getInt("modelYear");
				String make = result.getString("make");
				String model = result.getString("model");
				String modelID = result.getString("modelID");
				String modelCode = result.getString("modelCode");
				String buildDate = result.getString("buildDate");
				String trim = result.getString("trim");
				String subTrim = result.getString("subTrim");
				String bodyDescription = result.getString("bodyDescription");
				int seatingCapacity = result.getInt("seatingCapacity");
				String engineDescription = result.getString("engineDescription");
				int horsepower = result.getInt("horsepower");
				String transmission = result.getString("transmission");
				String driveTrain = result.getString("driveTrain");
				double wheelBase = result.getDouble("wheelBase");
				double payloadCapacity = result.getDouble("payloadCapacity");
				int fuelCity = result.getInt("fuelCity");
				int fuelHighway = result.getInt("fuelHighway");
				int fuelCombined = result.getInt("fuelCombined");
				String exteriorCode = result.getString("exteriorCode");
				String exteriorColour = result.getString("exteriorColour");
				String interiorCode = result.getString("interiorCode");
				String interiorColour = result.getString("interiorColour");
				String interiorDesc = result.getString("interiorDesc");
				String standardEquipment = result.getString("standardEquipment");
				String installedOptions = result.getString("installedOptions");
				String safetyRating = result.getString("safetyRating");
				String factoryWarranty = result.getString("factoryWarranty");
				double destinationCharge = result.getDouble("destinationCharge");
				double baseMSRP = result.getDouble("baseMSRP");
				double totalMSRP = result.getDouble("totalMSRP");
				double invoicePrice = result.getDouble("invoicePrice");

				int wSize = titleBuildDataWS.length;
				String[] jsonValue = new String[wSize];
				jsonValue[0] = "";
				jsonValue[1] = vin;
				jsonValue[2] = Integer.toString(modelYear);
				jsonValue[3] = make;
				jsonValue[4] = model;
				jsonValue[5] = modelCode;
				jsonValue[6] = trim;
				jsonValue[7] = subTrim;
				jsonValue[8] = bodyDescription;
				jsonValue[9] = Integer.toString(seatingCapacity);
				jsonValue[10] = engineDescription;
				jsonValue[11] = Integer.toString(horsepower);
				jsonValue[12] = transmission;
				jsonValue[13] = driveTrain;
				jsonValue[14] = Double.toString(wheelBase);
				jsonValue[15] = Double.toString(payloadCapacity);
				jsonValue[16] = Integer.toString(fuelCity);
				jsonValue[17] = Integer.toString(fuelHighway);
				jsonValue[18] = exteriorCode;
				jsonValue[19] = exteriorColour;
				jsonValue[20] = interiorCode;
				jsonValue[21] = interiorColour;
				jsonValue[22] = interiorDesc;
				jsonValue[23] = Double.toString(baseMSRP);
				jsonValue[24] = standardEquipment;
				jsonValue[25] = installedOptions;
				jsonValue[26] = safetyRating;
				jsonValue[27] = factoryWarranty;
				jsonValue[28] = Double.toString(totalMSRP);
				jsonValue[29] = Integer.toString(fuelCombined);
				jsonValue[30] = Double.toString(destinationCharge);
				jsonValue[31] = Double.toString(invoicePrice);
				jsonValue[32] = buildDate;
				jsonValue[33] = id;
				jsonValue[34] = serverTime;
				jsonValue[35] = String.valueOf(error);
				jsonValue[36] = Integer.toString(executionTimeMS);
				jsonValue[37] = String.valueOf(error2);
				jsonValue[38] = "";
				jsonValue[39] = "";
				jsonValue[40] = "";
				jsonValue[41] = "";
				jsonValue[42] = Integer.toString(modelYear);
				jsonValue[43] = model;
				jsonValue[44] = modelID;
				jsonValue[45] = "";
				jsonValue[46] = styleID;
				jsonValue[47] = gvuid;

				com_libs.writeToSheet(wsResultfile, jsonValue);

			} catch (Exception ex) {
				System.out.println("error occurs!");
				noObj++;
				temp[0] = Integer.toString(noObj);
				temp[1] = vinNum;
				temp[2] = "200 No Obj";
				temp[3] = "200 No Obj";
				temp[4] = "";
				com_libs.writeToSheet(wsResultfile, temp);
			}
		}
	}

	public static void cppBuildDataExtractWS2(String wsResultfile, String[] titleBuildDataWS, String text,
			String vinNum, int sNum) throws IOException {
		String id;
		String serverTime;
		boolean error;
		int executionTimeMS;
		JSONObject result;
		boolean error2;

		String styleID;
		String gvuid;
		String vin;
		int modelYear;
		String make;
		String model;
		String modelID;
		String modelCode;
		String buildDate;
		String trim;
		String subTrim;
		String bodyDescription;
		int seatingCapacity;
		String engineDescription;
		int horsepower;
		String transmission;
		String driveTrain;
		double wheelBase;
		double payloadCapacity;
		int fuelCity;
		int fuelHighway;
		int fuelCombined;
		String exteriorCode;
		String exteriorColour;
		String interiorCode;
		String interiorColour;
		String interiorDesc;
		String standardEquipment;
		String installedOptions;
		String safetyRating;
		String factoryWarranty;
		double destinationCharge;
		double baseMSRP;
		double totalMSRP;
		double invoicePrice;

		int wSize = titleBuildDataWS.length;
		String[] jsonValue = new String[wSize];

		com_libs.writeTitle(wsResultfile, titleBuildDataWS);
		String[] temp = new String[50];
		if (text.equals("")) {
			blank++;
			temp[0] = Integer.toString(blank);
			temp[1] = vinNum;
			temp[2] = "404 error";
//			temp[3] = "404 error";
//			temp[4] = "";
			com_libs.writeToSheet(wsResultfile, temp);
		} else {
			try {
				JSONObject obj = new JSONObject(text);
				id = obj.getString("id");
				serverTime = obj.getString("serverTime");
				error = obj.getBoolean("error");
				executionTimeMS = obj.getInt("executionTimeMS");
				result = obj.getJSONObject("result");
				error2 = result.getBoolean("error");

				try {
					styleID = result.getString("styleID");
				} catch (Exception ex) {
					styleID = "error";
				}
				try {
					gvuid = result.getString("gvuid");
				} catch (Exception ex) {
					gvuid = "error";
				}
				try {
					vin = result.getString("vin");
				} catch (Exception ex) {
					vin = "error";
				}
				try {
					modelYear = result.getInt("modelYear");
				} catch (Exception ex) {
					modelYear = 999999;
				}
				try {
					make = result.getString("make");
				} catch (Exception ex) {
					make = "error";
				}
				try {
					model = result.getString("model");
				} catch (Exception ex) {
					model = "error";
				}
				try {
					modelID = result.getString("modelID");
				} catch (Exception ex) {
					modelID = "error";
				}
				try {
					modelCode = result.getString("modelCode");
				} catch (Exception ex) {
					modelCode = "error";
				}
				try {
					buildDate = result.getString("buildDate");
				} catch (Exception ex) {
					buildDate = "error";
				}
				try {
					trim = result.getString("trim");
				} catch (Exception ex) {
					trim = "error";
				}
				try {
					subTrim = result.getString("subTrim");
				} catch (Exception ex) {
					subTrim = "error";
				}
				try {
					bodyDescription = result.getString("bodyDescription");
				} catch (Exception ex) {
					bodyDescription = "error";
				}
				try {
					seatingCapacity = result.getInt("seatingCapacity");
				} catch (Exception ex) {
					seatingCapacity = 999999;
				}
				try {
					engineDescription = result.getString("engineDescription");
				} catch (Exception ex) {
					engineDescription = "error";
				}
				try {
					horsepower = result.getInt("horsepower");
				} catch (Exception ex) {
					horsepower = 999999;
				}
				try {
					transmission = result.getString("transmission");
				} catch (Exception ex) {
					transmission = "error";
				}
				try {
					driveTrain = result.getString("driveTrain");
				} catch (Exception ex) {
					driveTrain = "error";
				}
				try {
					wheelBase = result.getDouble("wheelBase");
				} catch (Exception ex) {
					wheelBase = 999999;
				}
				try {
					payloadCapacity = result.getDouble("payloadCapacity");
				} catch (Exception ex) {
					payloadCapacity = 999999;
				}
				try {
					fuelCity = result.getInt("fuelCity");
				} catch (Exception ex) {
					fuelCity = 999999;
				}
				try {
					fuelHighway = result.getInt("fuelHighway");
				} catch (Exception ex) {
					fuelHighway = 999999;
				}
				try {
					fuelCombined = result.getInt("fuelCombined");
				} catch (Exception ex) {
					fuelCombined =999999;
				}
				try {
					exteriorCode = result.getString("exteriorCode");
				} catch (Exception ex) {
					exteriorCode = "error";
				}
				try {
					exteriorColour = result.getString("exteriorColour");
				} catch (Exception ex) {
					exteriorColour = "error";
				}
				try {
					interiorCode = result.getString("interiorCode");
				} catch (Exception ex) {
					interiorCode = "error";
				}
				try {
					interiorColour = result.getString("interiorColour");
				} catch (Exception ex) {
					interiorColour = "error";
				}
				try {
					interiorDesc = result.getString("interiorDesc");
				} catch (Exception ex) {
					interiorDesc = "error";
				}
				try {
					standardEquipment = result.getString("standardEquipment");
				} catch (Exception ex) {
					standardEquipment = "error";
				}
				try {
					installedOptions = result.getString("installedOptions");
				} catch (Exception ex) {
					installedOptions = "error";
				}
				try {
					safetyRating = result.getString("safetyRating");
				} catch (Exception ex) {
					safetyRating = "error";
				}
				try {
					factoryWarranty = result.getString("factoryWarranty");
				} catch (Exception ex) {
					factoryWarranty = "error";
				}
				try {
					destinationCharge = result.getDouble("destinationCharge");
				} catch (Exception ex) {
					destinationCharge = 999999;
				}
				try {
					baseMSRP = result.getDouble("baseMSRP");
				} catch (Exception ex) {
					baseMSRP = 999999;
				}
				try {
					totalMSRP = result.getDouble("totalMSRP");
				} catch (Exception ex) {
					totalMSRP = 999999;
				}
				try {
					invoicePrice = result.getDouble("invoicePrice");
				} catch (Exception ex) {
					invoicePrice = 999999;
				}

				// int wSize = titleBuildDataWS.length;
				// String[] jsonValue = new String[wSize];
				jsonValue[0] = Integer.toString(sNum);;
				jsonValue[1] = vin;
				jsonValue[2] = Integer.toString(modelYear);
				jsonValue[3] = make;
				jsonValue[4] = model;
				jsonValue[5] = modelCode;
				jsonValue[6] = trim;
				jsonValue[7] = subTrim;
				jsonValue[8] = bodyDescription;
				jsonValue[9] = Integer.toString(seatingCapacity);
				jsonValue[10] = engineDescription;
				jsonValue[11] = Integer.toString(horsepower);
				jsonValue[12] = transmission;
				jsonValue[13] = driveTrain;
				jsonValue[14] = Double.toString(wheelBase);
				jsonValue[15] = Double.toString(payloadCapacity);
				jsonValue[16] = Integer.toString(fuelCity);
				jsonValue[17] = Integer.toString(fuelHighway);
				jsonValue[18] = exteriorCode;
				jsonValue[19] = exteriorColour;
				jsonValue[20] = interiorCode;
				jsonValue[21] = interiorColour;
				jsonValue[22] = interiorDesc;
				jsonValue[23] = Double.toString(baseMSRP);
				jsonValue[24] = standardEquipment;
				jsonValue[25] = installedOptions;
				jsonValue[26] = safetyRating;
				jsonValue[27] = factoryWarranty;
				jsonValue[28] = Double.toString(totalMSRP);
				jsonValue[29] = Integer.toString(fuelCombined);
				jsonValue[30] = Double.toString(destinationCharge);
				jsonValue[31] = Double.toString(invoicePrice);
				jsonValue[32] = buildDate;
				jsonValue[33] = id;
				jsonValue[34] = serverTime;
				jsonValue[35] = String.valueOf(error);
				jsonValue[36] = Integer.toString(executionTimeMS);
				jsonValue[37] = String.valueOf(error2);
				jsonValue[38] = "";
				jsonValue[39] = "";
				jsonValue[40] = "";
				jsonValue[41] = "";
				jsonValue[42] = Integer.toString(modelYear);
				jsonValue[43] = model;
				jsonValue[44] = modelID;
				jsonValue[45] = "";
				jsonValue[46] = styleID;
				jsonValue[47] = gvuid;

				com_libs.writeToSheet(wsResultfile, jsonValue);

			} catch (Exception ex) {
				System.out.println("error occurs!");
				noObj++;
				temp[0] = Integer.toString(noObj);
				temp[1] = vinNum;
				temp[2] = "200 No Obj";
//				temp[3] = "200 No Obj";
//				temp[4] = "";
				com_libs.writeToSheet(wsResultfile, temp);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		// // From what I can read on json.org, all JSON strings should start with { (curly brace - object), and [ characters (square brackets - array) represent an array element in JSON.
		// // ******************************************************curly brace - object*********************************************************
		// jSonObjec_SydicationWS();
		// jSonObjec_CPP_FeulEconomyWS();
		// jSonObjec_CPP_MasterFeatureWS();
		jSonObjec_CPP_BuildDataExtractOrchestrationWS();
		// // ******************************************************End of curly brace - object*********************************************************

		// //// ******************************************************start with [ characters (square brackets - array*********************************************************
		//
		// jSonArry_UnityInventory(); //On Tuesday Auguest 30, 2016: API returns { "message": "An error has occurred." } from https://data.dealervideos.com/v1.0/vehicles/2FHFIYI
		// // //******************************************************End of start with [ characters (square brackets - array*********************************************************

		System.out.println("Complete!!!");

	}

}
