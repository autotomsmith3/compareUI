package AdminPortal;

import java.io.IOException;

import org.json.JSONArray;

public class getBackgroundAllMatrix {

	public static void jSonArry_getbackgroundAllMatrix() throws IOException {
		String[] titleString = { "S/N", "matrixGUID:", "brand:", "angle:", "mfgCode:", "image:"};
		String savePathFile = "C:\\1\\Eclipse\\Test Results\\backgroundMatrix\\backgroundAllMatrix.xls";

		String adminWSURL = "http://lnoc-q13v-xws1.autodata.org:8080/AdminWebServices/background/matrix/all"; 


		// Returning nothing: "","",
			String jsonTextFradminWSURL = cPP.com_libs.getSourceCode(adminWSURL,"");
			if (jsonTextFradminWSURL == "") {
				System.out.println("jsonTextFradminWSURL=" + adminWSURL + " returns empty");
			} else {
				getBackgroundAllMatrixFrJSONParser(savePathFile, titleString, jsonTextFradminWSURL, "");// Array OK - start with [ characters (square brackets - array)
			}
	}

	public static void getBackgroundAllMatrixFrJSONParser(String resultfile, String[] titleString, String text,
			String sourceUnityID) throws IOException {
		JSONArray jsonarray = new JSONArray(text);
		int size = jsonarray.length();
		int wSize = titleString.length;
		String[] jsonValue = new String[wSize];
		cPP.com_libs.writeTitle(resultfile, titleString);
		// System.out.println("S/N" + ", vehicleId:" + ", vin:" + ", stockNumber:" + ", used:" + ", certified:"
		// + ", dateReceived:" + ", make:" + ", model:" + ", year:" + ", mileage:" + ", trim:" + ", bodyStyle:"
		// + ", engine:" + ", transmission:" + ", exteriorColor:" + ", interiorColor:" + ", listPrice:"
		// + ", picture:" + ", ctx:");

		for (int i = 0; i < size; i++) {
			String matrixGUID = jsonarray.getJSONObject(i).getString("matrixGUID");
			String brand = jsonarray.getJSONObject(i).getString("brand");
			String angle = jsonarray.getJSONObject(i).getString("angle");
			String mfgCode = jsonarray.getJSONObject(i).getString("mfgCode");
			String image = jsonarray.getJSONObject(i).getString("image");
			
			jsonValue[0] = Integer.toString(i);
			jsonValue[1] = matrixGUID;
			jsonValue[2] = brand;
			jsonValue[3] = angle;
			jsonValue[4] = mfgCode;
			jsonValue[5] = image;


			cPP.com_libs.writeToSheet(resultfile, jsonValue);
			if ((i + 1) == size) {
				System.out.println("sourceUnityID=" + sourceUnityID + ".  Total lines= " + (i + 1));
			}

			// System.out.println("" + i + ", " + vehicleId + ", " + vin + ", " + stockNumber + ", " + used + ", "
			// + certified + ", " + dateReceived + ", " + make + ", " + model + ", " + year + ", " + mileage + ", "
			// + trim + ", " + bodyStyle + ", " + engine + ", " + transmission + ", " + exteriorColor + ", "
			// + interiorColor + ", " + listPrice + ", " + picture + ", " + ctx);

		}
		System.out.println(sourceUnityID + "  - is complete!!! Continue......");
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		// // From what I can read on json.org, all JSON strings should start with { (curly brace - object), and [ characters (square brackets - array) represent an array element in JSON.
		// // ******************************************************curly brace - object*********************************************************
		// jSonObjec_SydicationWS();
		// jSonObjec_CPP_FeulEconomyWS();
		// jSonObjec_CPP_MasterFeatureWS();
		// jSonObjec_CPP_BuildDataExtractOrchestrationWS();
		// // ******************************************************End of curly brace - object*********************************************************

		// //// ******************************************************start with [ characters (square brackets - array*********************************************************
		System.out.println("Starting...this will take several minutes (>30)... file size will reach to about 3,969 KB.");
		jSonArry_getbackgroundAllMatrix(); // On Tuesday Auguest 30, 2016: API returns { "message": "An error has occurred." } from https://data.dealervideos.com/v1.0/vehicles/2FHFIYI
		// // //******************************************************End of start with [ characters (square brackets - array*********************************************************
		System.out.println("\nCompleted!!!");

	}

}
