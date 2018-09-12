package cPP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import cPP.com_libs;

public class Stock360_Phase2 {
	private static int blank = 0;
	private static int noObj = 0;

	public static void getStock360_Phase2() throws Exception {

		// ******************************************************
		// ******************************************************
		String testDateFolder;
		int vintotal = 2;
		int startToRun = 1;// Default=1;
		int endToRun = 1;// Default =vintotal
		String dateFolder = "20180618_180";
		 //// ******************************************************
		 //// *******************180 TCs***********************************
		 String[] SpiraID = { "TC184206", "TC184207", "TC184208", "TC184209", "TC184210", "TC184211", "TC184212",
		 "TC184213", "TC184214", "TC184215", "TC184216", "TC184217", "TC184218", "TC184219", "TC184220",
		 "TC184221", "TC184222", "TC184223", "TC184224", "TC184225", "TC184226", "TC184227", "TC184228",
		 "TC184229", "TC184230", "TC184231", "TC184232", "TC184233", "TC184234", "TC184235", "TC184236",
		 "TC184237", "TC184238", "TC184239", "TC184240", "TC184241", "TC184242", "TC184243", "TC184244",
		 "TC184245", "TC184246", "TC184247", "TC184248", "TC184249", "TC184250", "TC184251", "TC184252",
		 "TC184253", "TC184254", "TC184255", "TC184256", "TC184257", "TC184258", "TC184259", "TC184260",
		 "TC184261", "TC184262", "TC184263", "TC184264", "TC184265", "TC184266", "TC184267", "TC184268",
		 "TC184269", "TC184270", "TC184271", "TC184272", "TC184273", "TC184274", "TC184275", "TC184276",
		 "TC184277", "TC184278", "TC184279", "TC184280", "TC184281", "TC184282", "TC184283", "TC184284",
		 "TC184285", "TC184286", "TC184287", "TC184288", "TC184289", "TC184290", "TC184291", "TC184292",
		 "TC184293", "TC184294", "TC184295", "TC184296", "TC184297", "TC184298", "TC184299", "TC184300",
		 "TC184301", "TC184302", "TC184303", "TC184304", "TC184305", "TC184306", "TC184307", "TC184308",
		 "TC184309", "TC184310", "TC184311", "TC184312", "TC184313", "TC184314", "TC184315", "TC184316",
		 "TC184317", "TC184318", "TC184319", "TC184320", "TC184321", "TC184322", "TC184323", "TC184324",
		 "TC184325", "TC184326", "TC184327", "TC184328", "TC184329", "TC184330", "TC184331", "TC184332",
		 "TC184333", "TC184334", "TC184335", "TC184336", "TC184337", "TC184338", "TC184339", "TC184340",
		 "TC184341", "TC184342", "TC184343", "TC184344", "TC184345", "TC184346", "TC184347", "TC184348",
		 "TC184349", "TC184350", "TC184351", "TC184352", "TC184353", "TC184354", "TC184355", "TC184356",
		 "TC184357", "TC184358", "TC184359", "TC184360", "TC184361", "TC184362", "TC184363", "TC184364",
		 "TC184365", "TC184366", "TC184367", "TC184368", "TC184369", "TC184370", "TC184371", "TC184372",
		 "TC184373", "TC184374", "TC184375", "TC184376", "TC184377", "TC184378", "TC184379", "TC184380",
		 "TC184381", "TC184382", "TC184383", "TC184384", "TC184385" };
		
//		// //// ******************************************************
////		// //// *******************72 TCs***********************************
//		String[] SpiraID = { "TC188226", "TC188227", "TC188228", "TC188229", "TC188230", "TC188231", "TC188232",
//				"TC188233", "TC188234", "TC188235", "TC188236", "TC188237", "TC188238", "TC188239", "TC188240",
//				"TC188241", "TC188242", "TC188243", "TC188244", "TC188245", "TC188246", "TC188247", "TC188248",
//				"TC188249", "TC188250", "TC188251", "TC188252", "TC188253", "TC188254", "TC188255", "TC188256",
//				"TC188257", "TC188258", "TC188259", "TC188260", "TC188261", "TC188262", "TC188263", "TC188264",
//				"TC188265", "TC188266", "TC188267", "TC188268", "TC188269", "TC188270", "TC188271", "TC188272",
//				"TC188273", "TC188274", "TC188275", "TC188276", "TC188277", "TC188278", "TC188279", "TC188280",
//				"TC188281", "TC188282", "TC188283", "TC188284", "TC188285", "TC188286", "TC188287", "TC188288",
//				"TC188289", "TC188290", "TC188291", "TC188292", "TC188293", "TC188294", "TC188295", "TC188296",
//				"TC188297" };
//
////		//// ******************************************************
//		//// ******************************************************
		for (int k = startToRun; k <= endToRun; k++) {// vintotal; k++) {
			int count = 0;
			String kStr = Integer.toString(k);
			String pre_path = "C:\\1\\Eclipse\\Test Results\\Colorized360 Phase2\\stock360\\";
			String getStock360_Phase2SavePathFile = pre_path + dateFolder + "\\Stock360Phase2_" + kStr + ".xls";
			String jsonpathfilename = pre_path + dateFolder + "\\Stock360_Phase2_" + kStr;// + ".json";

			String[] titleStringgetStock360WS = { "S/N", "URL_parameter", "RequestBody", "serverTime", "error",
					"executionTimeMS", "resultObj", "playlistObj", "vehicleDetailsObj", "oemLogo", "year", "make",
					"presentationsObj", "version", "date", "currentSessionId", "currentRequestId", "presentationId",
					"presentationsObj#", "type", "trackType", "location", "model", "trim", "colorGroup",
					"colorGroupCode", "color", "colorCode", "imagePlayListObj", "imagePlayListObj#", "url", "errors" };
			// DEV
			// String stock360PutServiceURL = "http://lnoc-dvcp-xws1.autodatacorp.org:8080/image/rest/stock360";//DEV
			// QA
			String stock360PutServiceURL = "http://lnoc-q1cp-xws1.autodatacorp.org:8080/image/rest/stock360";//QA
			// Prod
//			String stock360PutServiceURL = "http://cpp.chromedata.com/image/rest/stock360";//Prod
//			String stock360PutServiceURL = "http://cpp-mi.chromedata.com/image/rest/stock360";//Prod Flint
//			String stock360PutServiceURL = "http://cpp-or.chromedata.com/image/rest/stock360";//Prod Hillsboro
			
			// ******************************************************
			// ******************************************************
			int dataLength =SpiraID.length;//180;// 180;// 30;// 18;// one vin has 5 tc.

			if (dataLength == 72) {
				testDateFolder = "TestDataRTM - Original_72";
			} else if (dataLength == 180) {
				testDateFolder = "TestData_Original_180";
			} else {
				testDateFolder = "";
			}

			// ******************************************************
			// ******************************************************
			String[] Stock360MSBodyParameters = new String[dataLength + 1];
			int datasize = Stock360MSBodyParameters.length;
			for (int i = 0; i < datasize - 1; i++) {
				count++;
				String iStr = Integer.toString(i + 1);

				String bodyDatafile = pre_path + testDateFolder + "\\stock360BodyData_" + k + "\\stock360Body" + iStr
						+ ".txt"; // # group number testing

				Stock360MSBodyParameters[i] = getFile(bodyDatafile); // ok

				// PUT New MS Image endpoint
				String jsonTextFrgetStock360WS = com_libs.getSourceCodeJson(Stock360MSBodyParameters[i],
						stock360PutServiceURL, "");// PUT
				SaveScratch(jsonpathfilename + "_" + (i + 1) + "_" + SpiraID[i] + ".json", jsonTextFrgetStock360WS);
				// GET old
				// String jsonTextFrgetStock360WS = com_libs.getSourceCode("", imagePutServiceURL);// get OK
				getStock360_Phase2Details(getStock360_Phase2SavePathFile, titleStringgetStock360WS,
						jsonTextFrgetStock360WS, stock360PutServiceURL, Stock360MSBodyParameters[i], count);
			}
		}
	}

	public static void getStock360_Phase2Details(String wsResultfile, String[] titleString, String text,
			String URLString, String parameterS, int countNum) throws IOException {
		cPP.com_libs.writeTitle(wsResultfile, titleString);
		String serverTime = "";
		String error = "";
		String executionTimeMS = "";
		String resultObj = "";
		String playlistObj = "";
		String vehicleDetailsObj = "";
		String oemLogo = "";
		String year = "";
		String make = "";
		String presentationsObj = "";
		String version = "";
		String date = "";
		String currentSessionId = "";
		String currentRequestId = "";
		String presentationId = "";
		String presentationsObjNum = "";
		String type = "";
		String trackType = "";
		String location = "";
		String model = "";
		String trim = "";
		String colorGroup = "";
		String colorGroupCode = "";
		String color = "";
		String colorCode = "";
		String imagePlayListObj = "";
		String imagePlayListObjNum = "";
		String url = "";
		String errors = "";

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
				serverTime = obj.getString("serverTime");
				error = Boolean.toString(obj.getBoolean("error"));
				executionTimeMS = Long.toString(obj.getLong("executionTimeMS"));

				JSONObject result = obj.getJSONObject("result");
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
					try {
						errors = Boolean.toString(result.getBoolean("errors"));
					} catch (Exception ex) {
						errors = "null";
					}

					try {
						JSONObject playlistObject = result.getJSONObject("playlist");
						int playlistSize = playlistObject.length();
						playlistObj = Integer.toString(playlistSize);
						if (playlistSize == 0) {
							playlistObj = "null";
						}
						try {
							version = playlistObject.getString("version");
						} catch (Exception ex) {
							version = "null";
						}
						try {
							date = playlistObject.getString("date");
						} catch (Exception ex) {
							date = "null";
						}
						try {
							currentSessionId = playlistObject.getString("currentSessionId");
						} catch (Exception ex) {
							currentSessionId = "null";
						}
						try {
							currentRequestId = playlistObject.getString("currentRequestId");
						} catch (Exception ex) {
							currentRequestId = "null";
						}
						try {
							presentationId = playlistObject.getString("presentationId");
						} catch (Exception ex) {
							presentationId = "null";
						}

						JSONObject vehicleDetailsObject = playlistObject.getJSONObject("vehicleDetails");
						int vehicleDetailsSize = vehicleDetailsObject.length();
						vehicleDetailsObj = Integer.toString(vehicleDetailsSize);
						if (vehicleDetailsSize == 0) {
							vehicleDetailsObj = "null";
						}
						try {
							oemLogo = vehicleDetailsObject.getString("oemLogo");
						} catch (Exception ex) {
							oemLogo = "null";
						}
						try {
							year = vehicleDetailsObject.getString("year");
						} catch (Exception ex) {
							year = "null";
						}
						try {
							make = vehicleDetailsObject.getString("make");
						} catch (Exception ex) {
							make = "null";
						}

						JSONArray presentationsObject = playlistObject.getJSONArray("presentations");
						int presentationsSize = presentationsObject.length();
						presentationsObj = Integer.toString(presentationsSize);
						if (presentationsSize == 0) {
							presentationsObj = "null";
							System.out.println("S/N: " + countNum);
							System.out.println("executionTimeMS: " + executionTimeMS);
							System.out.println("URLString: " + URLString);

							int wSize = titleString.length;
							String[] jsonValue = new String[wSize];

							jsonValue[0] = Integer.toString(countNum);
							jsonValue[1] = URLString;
							jsonValue[2] = parameterS;
							jsonValue[3] = serverTime;
							jsonValue[4] = error;
							jsonValue[5] = executionTimeMS;
							jsonValue[6] = resultObj;
							jsonValue[7] = playlistObj;
							jsonValue[8] = vehicleDetailsObj;
							jsonValue[9] = oemLogo;
							jsonValue[10] = year;
							jsonValue[11] = make;
							jsonValue[12] = presentationsObj;
							jsonValue[13] = version;
							jsonValue[14] = date;
							jsonValue[15] = currentSessionId;
							jsonValue[16] = currentRequestId;
							jsonValue[17] = presentationId;

							jsonValue[31] = errors;

							cPP.com_libs.writeToSheet(wsResultfile, jsonValue);

						}
						for (int i = 0; i < presentationsSize; i++) {
							presentationsObjNum = Integer.toString(i);
							try {
								type = presentationsObject.getJSONObject(i).getString("type");
							} catch (Exception ex) {
								type = "null";
							}
							try {
								trackType = presentationsObject.getJSONObject(i).getString("trackType");
							} catch (Exception ex) {
								trackType = "null";
							}
							try {
								location = presentationsObject.getJSONObject(i).getString("location");
							} catch (Exception ex) {
								location = "null";
							}
							try {
								model = presentationsObject.getJSONObject(i).getString("model");
							} catch (Exception ex) {
								model = "null";
							}
							try {
								trim = presentationsObject.getJSONObject(i).getString("trim");
							} catch (Exception ex) {
								trim = "null";
							}
							try {
								colorGroup = presentationsObject.getJSONObject(i).getString("colorGroup");
							} catch (Exception ex) {
								colorGroup = "null";
							}
							try {
								colorGroupCode = presentationsObject.getJSONObject(i).getString("colorGroupCode");
							} catch (Exception ex) {
								colorGroupCode = "null";
							}
							try {
								color = presentationsObject.getJSONObject(i).getString("color");
							} catch (Exception ex) {
								color = "null";
							}
							try {
								colorCode = presentationsObject.getJSONObject(i).getString("colorCode");
							} catch (Exception ex) {
								colorCode = "null";
							}

							JSONArray imagePlayListObject = presentationsObject.getJSONObject(i)
									.getJSONArray("imagePlayList");
							int imagePlayListSize = imagePlayListObject.length();
							imagePlayListObj = Integer.toString(imagePlayListSize);
							if (imagePlayListSize == 0) {
								imagePlayListObj = "null";
							}

							for (int j = 0; j < imagePlayListSize; j++) {
								imagePlayListObjNum = Integer.toString(j);
								try {
									url = imagePlayListObject.getJSONObject(j).getString("url");
								} catch (Exception ex) {
									url = "null";
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
								jsonValue[4] = error;
								jsonValue[5] = executionTimeMS;
								jsonValue[6] = resultObj;
								jsonValue[7] = playlistObj;
								jsonValue[8] = vehicleDetailsObj;
								jsonValue[9] = oemLogo;
								jsonValue[10] = year;
								jsonValue[11] = make;
								jsonValue[12] = presentationsObj;
								jsonValue[13] = version;
								jsonValue[14] = date;
								jsonValue[15] = currentSessionId;
								jsonValue[16] = currentRequestId;
								jsonValue[17] = presentationId;
								jsonValue[18] = presentationsObjNum;
								jsonValue[19] = type;
								jsonValue[20] = trackType;
								jsonValue[21] = location;
								jsonValue[22] = model;
								jsonValue[23] = trim;
								jsonValue[24] = colorGroup;
								jsonValue[25] = colorGroupCode;
								jsonValue[26] = color;
								jsonValue[27] = colorCode;
								jsonValue[28] = imagePlayListObj;
								jsonValue[29] = imagePlayListObjNum;
								jsonValue[30] = url;
								jsonValue[31] = errors;

								cPP.com_libs.writeToSheet(wsResultfile, jsonValue);

							}

						}

					} catch (Exception ex) {
						playlistObj = "null";

						System.out.println("S/N: " + countNum);
						System.out.println("executionTimeMS: " + executionTimeMS);
						System.out.println("URLString: " + URLString);

						int wSize = titleString.length;
						String[] jsonValue = new String[wSize];

						jsonValue[0] = Integer.toString(countNum);
						jsonValue[1] = URLString;
						jsonValue[2] = parameterS;
						jsonValue[3] = serverTime;
						jsonValue[4] = error;
						jsonValue[5] = executionTimeMS;
						jsonValue[6] = resultObj;
						jsonValue[7] = playlistObj;
						jsonValue[8] = "";
						jsonValue[9] = "";
						jsonValue[10] = "";
						jsonValue[11] = "";
						jsonValue[12] = "";
						jsonValue[13] = "";
						jsonValue[14] = "";
						jsonValue[15] = "";
						jsonValue[16] = "";
						jsonValue[17] = "";
						jsonValue[18] = "";
						jsonValue[19] = "";
						jsonValue[20] = "";
						jsonValue[21] = "";
						jsonValue[22] = "";
						jsonValue[23] = "";
						jsonValue[24] = "";
						jsonValue[25] = "";
						jsonValue[26] = "";
						jsonValue[27] = "";
						jsonValue[28] = "";
						jsonValue[29] = "";
						jsonValue[30] = "";
						jsonValue[31] = errors;

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
		System.out.println("Started...");
		getStock360_Phase2();
		System.out.println("Complete!!!");
	}

}
