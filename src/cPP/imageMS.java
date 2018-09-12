package cPP;
// This is for CPP-762.  /image-service/getImages/gvuids
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import cPP.com_libs;

public class imageMS {
	private static int blank = 0;
	private static int noObj = 0;

	public static void getImageMS() throws Exception {
		// Properties prop = new Properties();
		// try {
		// // prop.load(JSONParser.class.getClassLoader().getResourceAsStream("cPP_data/compareS.properties"));
		// prop.load(ComparePhoto.class.getClassLoader().getResourceAsStream("cPP_data/modelwalk.properties"));
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// ******************************************************
		// ******************************************************
		int vintotal = 2;
		int startToRun = 1;// Default=1;
		int endToRun = 1;// Default =vintotal
		String dateFolder = "20180208";
		String[] SpiraID = { "TC173696", "TC173697", "TC173698", "TC173699", "TC173700", "TC173701", "TC173702",
				"TC173703", "TC173704", "TC173705", "TC173706", "TC173707", "TC173708", "TC173709", "TC173710",
				"TC173711", "TC173712", "TC173713", "TC173714", "TC173715", "TC173716", "TC173717", "TC173718",
				"TC173719", "TC173720", "TC173721", "TC173722", "TC173723", "TC173724", "TC173725", "TC173726",
				"TC173727", "TC173728", "TC173729", "TC173730", "TC173731", "TC173732", "TC173733", "TC173734",
				"TC173735", "TC173736", "TC173737", "TC173738", "TC173739", "TC173740", "TC173741", "TC173742",
				"TC173743", "TC173744", "TC173745", "TC173746", "TC174437", "TC174438", "TC174440" };

		// ******************************************************
		// ******************************************************
		for (int k = startToRun; k <= endToRun; k++) {// vintotal; k++) {
			int count = 0;
			String kStr = Integer.toString(k); /// C:\1\Eclipse\Test Results\CompareMS\ImageMS\20170401
			String getImageMSSavePathFile = "C:\\1\\Eclipse\\Test Results\\CompareMS\\ImageMS\\" + dateFolder
					+ "\\image_" + kStr + ".xls";
			String jsonpathfilename = "C:\\1\\Eclipse\\Test Results\\CompareMS\\ImageMS\\" + dateFolder + "\\image_"
					+ kStr;// + ".json";

			String[] titleStringgetImageWS = { "S/N", "URL_parameter", "RequestBody", "serverTime", "serverTimeString",
					"serverName", "executionTimeMS", "resultObj", "gvuid", "imageID", "shotID", "imageName", "colorID",
					"colorCode1", "colorCode2", "colorCode3", "optionCode", "shotDescription", "rangeDescription",
					"groupDescription", "rangeID", "groupID" };
			// // DEV get
			// String imagePutServiceURL = "http://lnoc-dvcp-xmw1:8080/image-service/getImages/929321ba-84b1-4e05-ab25-2455ffafec0e/34";
			//// // DEV PUT
			// String imagePutServiceURL = "http://lnoc-dvcp-xmw1:8080/image-service/getImages/gvuids";

			// // QA
			// String comparePutServiceURL = "http://lnoc-q1cp-xws1.autodatacorp.org:8080/image/rest/imageMS";
			// // QA PUT
			String imagePutServiceURL = "http://lnoc-q1cp-xmw1:8080/image-service/getImages/gvuids";
			// int dataLength = 10;//one vin has 10 tcs.// colorized360(stock360) used before. OK
			// ******************************************************
			// ******************************************************
			int dataLength = 54;// one vin has 5 tc.
			// ******************************************************
			// ******************************************************
			String[] imageMSBodyParameters = new String[dataLength + 1];
			int datasize = imageMSBodyParameters.length;
			for (int i = 0; i < datasize - 1; i++) {
				count++;
				String iStr = Integer.toString(i + 1);

				// String bodyDatafile = "imageMSBodyData_" + k + "/imageMSBody" + iStr + ".txt"; // # Vin working.
				// C:\\1\\Eclipse\\Test Results\\CompareMS\\ImageMS\\TestData\\imageBodyDate_1\\imageBody1.txt
				String bodyDatafile = "C:\\1\\Eclipse\\Test Results\\CompareMS\\ImageMS\\TestData\\imageBodyData_" + k
						+ "\\imageBody" + iStr + ".txt"; // # group number testing

				imageMSBodyParameters[i] = getFile(bodyDatafile); // ok
				// imageMSBodyParameters[i] = LoadFileToString(bodyDatafile); //failed
				// String getSourceCode(String prefix, String url)

				// PUT New MS Image endpoint
				String jsonTextFrgetImageWS = com_libs.getSourceCodeJson(imageMSBodyParameters[i], imagePutServiceURL,
						"");// PUT
				SaveScratch(jsonpathfilename + "_" + SpiraID[i] + "_" + (i + 1) + ".json", jsonTextFrgetImageWS);
				// GET old
				// String jsonTextFrgetImageWS = com_libs.getSourceCode("", imagePutServiceURL);// get OK
				getImageMSDetails(getImageMSSavePathFile, titleStringgetImageWS, jsonTextFrgetImageWS,
						imagePutServiceURL, imageMSBodyParameters[i], count);
			}
		}
	}

	public static void getImageMSDetails(String wsResultfile, String[] titleString, String text, String URLString,
			String parameterS, int countNum) throws IOException {
		cPP.com_libs.writeTitle(wsResultfile, titleString);
		String serverTime = "";
		String serverTimeString = "";
		String serverName = "";
		String executionTimeMS = "";
		String resultObj = "";

		String gvuid = "";
		String imageID = "";
		String shotID = "";
		String imageName = "";
		String colorID = "";
		String colorCode1 = "";
		String colorCode2 = "";
		String colorCode3 = "";
		String optionCode = "";
		String shotDescription = "";
		String rangeDescription = "";
		String groupDescription = "";
		String rangeID = "";
		String groupID = "";

		String[] temp = new String[30];
		if (text.equals("")) {
			blank++;
			temp[0] = Integer.toString(countNum);
			temp[1] = URLString;
			temp[2] = parameterS;
			temp[3] = "404 error";
			System.out.println("S/N: " + countNum);
			System.out.println("404 ERROR ON : " + URLString);
			cPP.com_libs.writeToSheet(wsResultfile, temp);
		} else {
			try {
				JSONObject obj = new JSONObject(text);
				int objLen = obj.length();

				serverTime = Long.toString(obj.getLong("serverTime"));
				serverTimeString = obj.getString(("serverTimeString"));
				serverName = obj.getString(("serverName"));
				executionTimeMS = Long.toString(obj.getLong("executionTimeMS"));

				JSONArray result = obj.getJSONArray("result");
				int size = result.length();
				resultObj = Integer.toString(size);
				if (size == 0) {
					blank++;
					temp[0] = Integer.toString(countNum);
					temp[1] = URLString;
					temp[2] = parameterS;
					temp[3] = "result [] object is blank";
					cPP.com_libs.writeToSheet(wsResultfile, temp);
					System.out.println("gvuid = " + "  " + " - Result [] object is blank. ");

				} else {
					// try {
					// errors = Boolean.toString(result.getBoolean("errors"));
					// } catch (Exception ex) {
					// errors = "null";
					// }
					for (int i = 0; i < size; i++) {

						try {
							gvuid = result.getJSONObject(i).getString("gvuid");
						} catch (Exception ex) {
							gvuid = "null";
						}
						try {
							imageID = Integer.toString(result.getJSONObject(i).getInt("imageID"));
						} catch (Exception ex) {
							imageID = "null";
						}
						try {
							shotID = Integer.toString(result.getJSONObject(i).getInt("shotID"));
						} catch (Exception ex) {
							shotID = "null";
						}
						try {
							imageName = result.getJSONObject(i).getString("imageName");
						} catch (Exception ex) {
							imageName = "null";
						}
						try {
							colorID = result.getJSONObject(i).getString("colorID");
						} catch (Exception ex) {
							colorID = "null";
						}
						try {
							colorCode1 = result.getJSONObject(i).getString("colorCode1");
						} catch (Exception ex) {
							colorCode1 = "null";
						}
						try {
							colorCode2 = result.getJSONObject(i).getString("colorCode2");
						} catch (Exception ex) {
							colorCode2 = "null";
						}
						try {
							colorCode3 = result.getJSONObject(i).getString("colorCode3");
						} catch (Exception ex) {
							colorCode3 = "null";
						}
						try {
							optionCode = result.getJSONObject(i).getString("optionCode");
						} catch (Exception ex) {
							optionCode = "null";
						}
						try {
							shotDescription = result.getJSONObject(i).getString("shotDescription");
						} catch (Exception ex) {
							shotDescription = "null";
						}
						try {
							rangeDescription = result.getJSONObject(i).getString("rangeDescription");
						} catch (Exception ex) {
							rangeDescription = "null";
						}
						try {
							groupDescription = result.getJSONObject(i).getString("groupDescription");
						} catch (Exception ex) {
							groupDescription = "null";
						}
						try {
							rangeID = Integer.toString(result.getJSONObject(i).getInt("rangeID"));
						} catch (Exception ex) {
							rangeID = "null";
						}
						try {
							groupID = Integer.toString(result.getJSONObject(i).getInt("groupID"));
						} catch (Exception ex) {
							groupID = "null";
						}
						System.out.println("S/N: " + countNum);
						System.out.println("executionTimeMS: " + executionTimeMS);
						System.out.println("URLString: " + URLString);

						int wSize = titleString.length;
						String[] jsonValue = new String[wSize];

						jsonValue[0] = Integer.toString(countNum);
						jsonValue[1] = URLString;
						jsonValue[2] = parameterS;
						jsonValue[3] = serverTime;
						jsonValue[4] = serverTimeString;
						jsonValue[5] = serverName;
						jsonValue[6] = executionTimeMS;

						jsonValue[7] = resultObj;

						jsonValue[8] = gvuid;
						jsonValue[9] = imageID;
						jsonValue[10] = shotID;
						jsonValue[11] = imageName;
						jsonValue[12] = colorID;
						jsonValue[13] = colorCode1;
						jsonValue[14] = colorCode2;
						jsonValue[15] = colorCode3;
						jsonValue[16] = optionCode;
						jsonValue[17] = shotDescription;
						jsonValue[18] = rangeDescription;
						jsonValue[19] = groupDescription;
						jsonValue[20] = rangeID;
						jsonValue[21] = groupID;

						cPP.com_libs.writeToSheet(wsResultfile, jsonValue);

					}

				}
			} catch (Exception ex) {
				System.out.println("error occurs!");
				noObj++;
				temp[0] = Integer.toString(countNum);
				temp[1] = URLString;
				temp[2] = parameterS;
				temp[3] = "200 error";
				System.out.println("S/N: " + countNum);
				System.out.println("ERROR 200 ON : " + URLString);
				cPP.com_libs.writeToSheet(wsResultfile, temp);
			}

		}

	}

	public static String getFile_old(String fileName) throws IOException {
		int lineNum;
		String line = "";
		StringBuilder result = new StringBuilder("");

		// Get file from resources folder
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream file = classLoader.getResourceAsStream(fileName);

		// File file = new File(classLoader.getResource(fileName).getFile());
		int i = 1;
		try (Scanner scanner = new Scanner(file)) {

			while (scanner.hasNextLine()) {
				line = scanner.nextLine();
				i++;
				result.append(line).append("\n");
				// result.append(line).append("\n");
			}
			lineNum = i - 1;
			// file.close();
			// file.();
			scanner.close();
		}
		return result.toString();
	}

	public static String getFile(String filePathName) {
		String sCurrentLine;
		String fileString = "";
		StringBuilder sb = new StringBuilder();
		String prefix_fileName, tempfile;
		tempfile = "";
		// prefix_fileName = wsName;?
		String path = filePathName;
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			while ((sCurrentLine = br.readLine()) != null) {
				sb.append(sCurrentLine);
			}
			fileString = sb.toString();
			br.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
		prefix_fileName = "";
		return fileString;
	}

	public static void SaveScratch(String pathfilename, String ScratchText) {
		try {
			// BufferedWriter out2 = new BufferedWriter(new FileWriter(dataDir+ "Acodes.txt", true)); //original OK
			BufferedWriter out2 = new BufferedWriter(new FileWriter(pathfilename, true));
			// out2.write("("+i+"): "+Acode+": ");
			// out2.write(i + ". " + Acode + ": "); //Original OK
			out2.newLine();
			out2.write(ScratchText);
			out2.newLine();
			out2.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// // From what I can read on json.org, all JSON strings should start with { (curly brace - object), and [ characters (square brackets - array) represent an array element in JSON.
		// // ******************************************************curly brace - object*********************************************************
		System.out.println("Started...");
		getImageMS();
		// jSonObjec_CPP_BuildDataExtractOrchestrationWS();
		// // ******************************************************End of curly brace - object*********************************************************

		// //// ******************************************************start with [ characters (square brackets - array*********************************************************
		//
		// jSonArry_UnityInventory(); //On Tuesday Auguest 30, 2016: API returns { "message": "An error has occurred." } from https://data.dealervideos.com/v1.0/vehicles/2FHFIYI
		// // //******************************************************End of start with [ characters (square brackets - array*********************************************************

		System.out.println("Complete!!!");
	}

}
