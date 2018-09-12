package DealerPortal;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
import org.json.*;

public class JSONParse {
	private static int blank = 0;
	private static int noObj = 0;

	public static void jSONParser(String text) {
		JSONObject obj = new JSONObject(text);
		// String pageName = obj.getJSONObject("posts").getString("pageName");

		JSONArray arr = obj.getJSONArray("posts");
		int size = arr.length();
		// String post_id=arr.getJSONObject(2).getString("post_id");

		for (int i = 0; i < size; i++) {
			long post_id = arr.getJSONObject(i).getLong("post_id");
			String actor_id = arr.getJSONObject(i).getString("actor_id");

			String picOfPersonWhoPosted = arr.getJSONObject(i).getString("picOfPersonWhoPosted");
			String nameOfPersonWhoPosted = arr.getJSONObject(i).getString("nameOfPersonWhoPosted");
			String message = arr.getJSONObject(i).getString("message");
			boolean likesCount = arr.getJSONObject(i).getBoolean("likesCount");
			// String comments = arr.getJSONObject(i).getString("comments");
			String timeOfPost = arr.getJSONObject(i).getString("timeOfPost");

			System.out.println("post_id:" + post_id + ", actor_id:" + actor_id + ", picOfPersonWhoPosted:"
					+ picOfPersonWhoPosted + ", nameOfPersonWhoPosted:" + nameOfPersonWhoPosted + ", message:" + message
					+ ", likesCount:" + likesCount + ", timeOfPost:" + timeOfPost);
		}
		System.out.println("Stop here!!!");
	}

	public static void getUnityWorkInventoryFrJSONParser(String resultfile, String[] titleString, String text,
			String sourceUnityID) throws IOException {
		JSONArray jsonarray = new JSONArray(text);
		int size = jsonarray.length();
		int wSize = titleString.length;
		String[] jsonValue = new String[wSize];
		writeTitle(resultfile, titleString);
		System.out.println("S/N" + ", vehicleId:" + ", vin:" + ", stockNumber:" + ", used:" + ", certified:"
				+ ", dateReceived:" + ", make:" + ", model:" + ", year:" + ", mileage:" + ", trim:" + ", bodyStyle:"
				+ ", engine:" + ", transmission:" + ", exteriorColor:" + ", interiorColor:" + ", listPrice:"
				+ ", picture:" + ", ctx:");

		for (int i = 0; i < size; i++) {
			long vehicleId = jsonarray.getJSONObject(i).getLong("vehicleId");
			String vin = jsonarray.getJSONObject(i).getString("vin");
			String stockNumber = jsonarray.getJSONObject(i).getString("stockNumber");
			boolean used = jsonarray.getJSONObject(i).getBoolean("used");
			boolean certified = jsonarray.getJSONObject(i).getBoolean("certified");
			String dateReceived = jsonarray.getJSONObject(i).getString("dateReceived");
			String make = jsonarray.getJSONObject(i).getString("make");
			String model = jsonarray.getJSONObject(i).getString("model");
			String year = jsonarray.getJSONObject(i).getString("year");
			long mileage = jsonarray.getJSONObject(i).getLong("mileage");
			String trim = jsonarray.getJSONObject(i).getString("trim");
			String bodyStyle = jsonarray.getJSONObject(i).getString("bodyStyle");
			String engine = jsonarray.getJSONObject(i).getString("engine");
			String transmission = jsonarray.getJSONObject(i).getString("transmission");
			String exteriorColor = jsonarray.getJSONObject(i).getString("exteriorColor");
			String interiorColor = jsonarray.getJSONObject(i).getString("interiorColor");
			long listPrice = jsonarray.getJSONObject(i).getLong("listPrice");
			String picture = jsonarray.getJSONObject(i).getString("picture");
			String ctx = jsonarray.getJSONObject(i).getString("ctx");

			jsonValue[0] = Integer.toString(i);
			jsonValue[1] = Long.toString(vehicleId);
			jsonValue[2] = vin;
			jsonValue[3] = stockNumber;
			jsonValue[4] = Boolean.toString(used);
			jsonValue[5] = Boolean.toString(certified);
			jsonValue[6] = dateReceived;
			jsonValue[7] = make;
			jsonValue[8] = model;
			jsonValue[9] = year;
			jsonValue[10] = Long.toString(mileage);
			jsonValue[11] = trim;
			jsonValue[12] = bodyStyle;
			jsonValue[13] = engine;
			jsonValue[14] = transmission;
			jsonValue[15] = exteriorColor;
			jsonValue[16] = interiorColor;
			jsonValue[17] = Long.toString(listPrice);
			jsonValue[18] = picture;
			jsonValue[19] = ctx;
			jsonValue[20] = sourceUnityID;

			writeToSheet(resultfile, jsonValue);

			System.out.println("" + i + ", " + vehicleId + ", " + vin + ", " + stockNumber + ", " + used + ", "
					+ certified + ", " + dateReceived + ", " + make + ", " + model + ", " + year + ", " + mileage + ", "
					+ trim + ", " + bodyStyle + ", " + engine + ", " + transmission + ", " + exteriorColor + ", "
					+ interiorColor + ", " + listPrice + ", " + picture + ", " + ctx);

		}
		System.out.println("Stop here!!!");
	}

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
		}
		return inputLine;
	}

	public static String getSourceCode(String urlParameters, String url1, String url2) throws Exception {
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
		if (!(responseCode == 404)) {
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

	public static void syndicationWSCompleteImageList(String wsResultfile, String[] titleSyndicationWS, String text,
			String dealershipID) throws IOException {
		JSONObject obj = new JSONObject(text);
		int size1 = obj.length();
		int wSize = titleSyndicationWS.length;
		String[] jsonValue = new String[wSize];
		writeTitle(wsResultfile, titleSyndicationWS);
		// String pageName = obj.getJSONObject("posts").getString("pageName");
		String publishDate = "";
		String stockNum = "";

		JSONArray arr = obj.getJSONArray("images");
		int sizeArray = arr.length();

		// String serverTime=obj.getJSONObject("images").getString("serverTime");
		// String post_id=arr.getJSONObject(2).getString("post_id");

		for (int i = 0; i < sizeArray; i++) {
			String dealerGuid = arr.getJSONObject(i).getString("dealerGuid");
			String vehicleGuid = arr.getJSONObject(i).getString("vehicleGuid");
			String vin = arr.getJSONObject(i).getString("vin");
			try {
				stockNum = arr.getJSONObject(i).getString("stockNum");
			} catch (Exception ex) {
				stockNum = "null";
			}
			try {
				publishDate = arr.getJSONObject(i).getString("publishDate");
			} catch (Exception ex) {
				publishDate = "null";
			}

			String imageGuId = arr.getJSONObject(i).getString("imageGuId");
			// String comments = arr.getJSONObject(i).getString("comments");
			String shotId = arr.getJSONObject(i).getString("shotId");

			String imageDescription = arr.getJSONObject(i).getString("imageDescription");
			String url = arr.getJSONObject(i).getString("url");
			long sequence = arr.getJSONObject(i).getLong("sequence");
			String imgGroup = arr.getJSONObject(i).getString("imgGroup");

			jsonValue[0] = Integer.toString(i);
			jsonValue[1] = dealerGuid;
			jsonValue[2] = vehicleGuid;
			jsonValue[3] = vin;
			jsonValue[4] = stockNum;
			jsonValue[5] = publishDate;
			jsonValue[6] = imageGuId;
			jsonValue[7] = shotId;
			jsonValue[8] = imageDescription;
			jsonValue[9] = url;
			jsonValue[10] = Long.toString(sequence);
			jsonValue[11] = imgGroup;

			writeToSheet(wsResultfile, jsonValue);

			System.out.println("S/N:" + i + ", dealerGuid:" + dealerGuid + ", vehicleGuid:" + vehicleGuid + ", vin:"
					+ vin + ", stockNum:" + stockNum + ", publishDate:" + publishDate + ", imageGuId:" + imageGuId
					+ ", shotId:" + shotId + ", imageDescription:" + imageDescription + ", url:" + url + ", sequence:"
					+ sequence + ", imgGroup:" + imgGroup);
			// Tab split
			System.out.println("S/N:" + i + "\tdealerGuid:" + dealerGuid + "\tvehicleGuid:" + vehicleGuid + "\tvin:"
					+ vin + "\tstockNum:" + stockNum + "\tpublishDate:" + publishDate + "\timageGuId:" + imageGuId
					+ "\tshotId:" + shotId + "\timageDescription:" + imageDescription + "\turl:" + url + "\tsequence:"
					+ sequence + "\timgGroup:" + imgGroup);
		}
		System.out.println("Stop here!!!");
	}

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
			writeToSheet(wsResultfile, temp);
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
			writeTitle(wsResultfile, titleFeulEconomyWS);
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
				writeToSheet(wsResultfile, jsonValue);

				System.out.println("S/N:" + i + ", type:" + type + ", city:" + city + ", hwy:" + hwy + ", combined:"
						+ combined + ", mileageUnit:" + mileageUnit + ", range:" + range + ", rangeUnit:" + rangeUnit
						+ ", vinNum:" + vinNum);

			}

		}
	}

	public static void masterFeaturesWS(String wsResultfile, String[] titleMasterFeatureWS, String text, String vinNum)
			throws IOException {
		writeTitle(wsResultfile, titleMasterFeatureWS);
		String[] temp = new String[9];
		if (text.equals("")) {
			blank++;
			temp[0] = Integer.toString(blank);
			temp[1] = "404 error";
			temp[2] = "404 error";
			temp[3] = "404 error";
			temp[4] = vinNum;
			writeToSheet(wsResultfile, temp);
		} else {
			try {
				JSONObject obj = new JSONObject(text);
				String id = obj.getString("id");
				String serverTime = obj.getString("serverTime");
				boolean error = obj.getBoolean("error");
				int executionTimeMS = obj.getInt("executionTimeMS");
				JSONObject result = obj.getJSONObject("result");
				boolean errors = result.getBoolean("errors");
				JSONArray masterFeature = result.getJSONArray("masterFeatures");

				System.out.println("id: " + obj.getString("id"));
				System.out.println("serverTime: " + obj.getString("serverTime"));
				System.out.println("error: " + obj.getBoolean("error"));
				System.out.println("executionTimeMS: " + obj.getInt("executionTimeMS"));
				System.out.println("obj: " + obj);
				System.out.println("result: " + result);
				System.out.println("fuelEconomyList: " + masterFeature);
				int wSize = titleMasterFeatureWS.length;
				String[] jsonValue = new String[wSize];
				// writeTitle(wsResultfile, titleMasterFeatureWS);
				// String pageName = obj.getJSONObject("posts").getString("pageName");
				String description = "";
				String category = "";
				JSONArray arr = result.getJSONArray("masterFeatures");
				int sizeArray = arr.length();
				for (int i = 0; i < sizeArray; i++) {
					String idn = arr.getJSONObject(i).getString("id");
					try {
						description = arr.getJSONObject(i).getString("description");
					} catch (Exception ex) {
						description = "";
					}
					try {
						category = arr.getJSONObject(i).getString("category");
					} catch (Exception ex) {
						category = "";
					}
					jsonValue[0] = Integer.toString(i);
					jsonValue[1] = idn;
					jsonValue[2] = description;
					jsonValue[3] = category;
					jsonValue[4] = vinNum;
					if (i == 0) {
						jsonValue[5] = Integer.toString(executionTimeMS);
					} else {
						jsonValue[5] = "";
					}
					writeToSheet(wsResultfile, jsonValue);

					// System.out.println("S/N:" + i + ", id:" + idn + ", description:" + description + ", category:"
					// + category + ", vinNum:" + vinNum);

				}
			} catch (Exception ex) {
				System.out.println("error occurs!");
				noObj++;
				temp[0] = Integer.toString(noObj);
				temp[1] = "200 No masterFeatures Obj";
				temp[2] = "200 No masterFeatures Obj";
				temp[3] = "200 No masterFeatures Obj";
				temp[4] = vinNum;
				writeToSheet(wsResultfile, temp);
			}
		}
	}

	public static void jSonObjec_SydicationWS() throws IOException {
		String sydicationWSSavePathFile = "C:\\1\\Eclipse\\Test Results\\SydicationWS_Return.xls";

		String jSONText = "{\"posts\": [{\"post_id\":12345678901,\"actor_id\": \"12345678901\",\"picOfPersonWhoPosted\": \"http://example.com/photo.jpg1\",\"nameOfPersonWhoPosted\": \"Jane Doe1\",\"message\": \"1Sounds cool. Can't wait to see it!\",\"likesCount\": true,\"comments\": [],\"timeOfPost\": \"12345678901\"},{\"post_id\":123456789012,\"actor_id\": \"12345678902\",\"picOfPersonWhoPosted\": \"http://example.com/photo.jpg2\",\"nameOfPersonWhoPosted\": \"Jane Doe2\",\"message\": \"Sounds cool. Can't wait to see it!\",\"likesCount\": true,\"comments\": [],\"timeOfPost\": \"12345678902\"},{\"post_id\":123456789013,\"actor_id\": \"12345678903\",\"picOfPersonWhoPosted\": \"http://example.com/photo.jpg3\",\"nameOfPersonWhoPosted\": \"Jane Doe3\",\"message\": \"Sounds cool. Can't wait to see it!\",\"likesCount\": false,\"comments\": [],\"timeOfPost\": \"12345678903\"} ]}";
		jSONParser(jSONText);// start with { (curly brace - object) OK

		String[] titleStringSydicationWS = { "S/N", "dealerGuid:", "vehicleGuid:", "vin:", "stockNum:", "publishDate:",
				"imageGuId:", "shotId:", "imageDescription:", "url:", "sequence:", "imgGroup:" };

		// ************QA**********:
		String env = "http://lnoc-q13v-xwa1.autodata.org/SyndicationWebServices/";
		String dealershipID = "123456";
		// ************QA**********:
		// // ************Prod********:
		// String env = "http://syndication.vinpx.net/SyndicationWebServices/";
		// String dealershipID = "666666";
		// // ************Prod********:
		String sydicationWSCompleteImageListURL = env + "GM/" + dealershipID + "/CompleteImageList";
		String jsonTextFrSydicationWS = getSourceCode(sydicationWSCompleteImageListURL, "");
		//// syndicationWSCompleteImageList(jsonTextFrSydicationWS);
		// syndicationWSCompleteImageList(sydicationWSsavePathFile, titleStringSydicationWS, jsonTextFrSydicationWS, dealershipID);

		// jSONText="{\"serverTime\":1472499464523,\"message\":null,\"serverID\":\"LNOC-Q13V-XWA1 (LNOC-Q13V-XWA1/172.16.130.88)\",\"images\":[{\"dealerGuid\":\"FEB6AE7C-4DDC-42CD-9F61-A1BDE81AF204\",\"vehicleGuid\":\"C5CB3E17-B5B5-46FC-93DA-257732B72807\",\"vin\":\"1GKEC13V82R226994\",\"stockNum\":\"G9394C\",\"publishDate\":null,\"imageGuId\":\"C9AFAC6B-1A12-4359-9344-3034F93B5211\",\"shotId\":\"999\",\"imageDescription\":\"Vehicle Benefits
		// Image\",\"url\":\"http://lnoc-q13v-xwa1.autodata.org/SyndicationWebServices/FEB6AE7C-4DDC-42CD-9F61-A1BDE81AF204/1GKEC13V82R226994/640/999/Garnet-Red-Metallic-(72U)-2002-GMC-Yukon--SLE-London-NY-999.jpg\",\"sequence\":10099,\"imgGroup\":\"Custom\"},{\"dealerGuid\":\"FEB6AE7C-4DDC-42CD-9F61-A1BDE81AF204\",\"vehicleGuid\":\"72AEE0F6-8272-4C8E-970A-5CAE3E8B29AF\",\"vin\":\"1GKEC13Z42R186593\",\"stockNum\":\"G9175A\",\"publishDate\":null,\"imageGuId\":\"3F9749A8-B6F1-4816-B577-C7093E2D11FE\",\"shotId\":\"999\",\"imageDescription\":\"Vehicle Benefits
		// Image\",\"url\":\"http://lnoc-q13v-xwa1.autodata.org/SyndicationWebServices/FEB6AE7C-4DDC-42CD-9F61-A1BDE81AF204/1GKEC13Z42R186593/640/999/Summit-White-(50U)-2002-GMC-Yukon--SLT-London-NY-999.jpg\",\"sequence\":10099,\"imgGroup\":\"Custom\"},{\"dealerGuid\":\"FEB6AE7C-4DDC-42CD-9F61-A1BDE81AF204\",\"vehicleGuid\":\"6FD76724-DF74-4C70-8D7D-3DDB61833D57\",\"vin\":\"1GYEK63N03R254845\",\"stockNum\":\"B1062A\",\"publishDate\":null,\"imageGuId\":\"9B841967-D6B0-4429-B734-C5985D6DD473\",\"shotId\":\"999\",\"imageDescription\":\"Vehicle Benefits
		// Image\",\"url\":\"http://lnoc-q13v-xwa1.autodata.org/SyndicationWebServices/FEB6AE7C-4DDC-42CD-9F61-A1BDE81AF204/1GYEK63N03R254845/640/999/White-Diamond-(98U)-2003-Cadillac-Escalade--null-London-NY-999.jpg\",\"sequence\":10099,\"imgGroup\":\"Custom\"}]}";
		syndicationWSCompleteImageList(sydicationWSSavePathFile, titleStringSydicationWS, jsonTextFrSydicationWS,
				dealershipID);
	}

	public static void jSonObjec_CPP_FeulEconomyWS() throws IOException {
		Properties prop = new Properties();
		try {
			prop.load(AUTOpxController.class.getClassLoader().getResourceAsStream("data/JSONData.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// String env = prop.getProperty("environment");
		String[] vinNums = fetchOneDemArrayFromPropFile("FuelEconomyVINs", prop);
		// String[] vinNums = { "3GCPCREC3FG408056", "1GTN2LEH6GZ134145", "W04WV3N58GG042641" };

		String FeulEconomyWSSavePathFile = "C:\\1\\Eclipse\\Test Results\\FeulEconomyWS_Return.xls";
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
			String jsonTextFrFeulEconomyWS = getSourceCode(feulEconomyURL, "");
			feulEconomyWS(FeulEconomyWSSavePathFile, titleStringFeulEconomyWS, jsonTextFrFeulEconomyWS, vin);

		}

	}

	public static void jSonObjec_CPP_MasterFeatureWS() throws Exception {
		Properties prop = new Properties();
		try {
			prop.load(AUTOpxController.class.getClassLoader().getResourceAsStream("data/JSONData.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// String env = prop.getProperty("environment");
		String[] vinNums = fetchOneDemArrayFromPropFile("MasterFeatureVINs", prop);
		// String[] vinNums = { "3GCPCREC3FG408056", "1GTN2LEH6GZ134145", "W04WV3N58GG042641" };

		String MasterFeatureWSSavePathFile = "C:\\1\\Eclipse\\Test Results\\MasterFeatureWS_Return.xls";
		// String jSONText = "";
		// jSONParser(jSONText);// start with { (curly brace - object) OK
		String[] titleStringMasterFeatureWS = { "S/N", "id:", "description:", "category:", "VIN:", "executionTimeMS:" };

		// ************QA**********:
		// http://lnoc-q1cp-xws1.autodatacorp.org:8080/master-feature-orchestration/rest/vehicle/features/
		String envURL = "http://lnoc-q1cp-xws1.autodatacorp.org:8080/master-feature-orchestration/rest/vehicle/features/";

		// ************QA**********:
		// // ************DEV**********:
		// String env = "http://lnoc-dvcp-xws1:8080/master-feature-orchestration/rest/vehicle/features/";
		//
		// // ************DEV**********:
		//// // ************Prod********:
		// String env = "http://xxxxxxxlnoc-dvcp-xws1:8080/master-feature-orchestration/rest/vehicle/features/";
		// String [] vinNums = {"3GCPCREC3FG408056","1GTN2LEH6GZ134145","W04WV3N58GG042641"};
		//// // ************Prod********:
		// String masterFeatureParameters="vin=\"3GCPCREC3FG408056\"&used=true&unstructuredText=tires: p245/35r20 performance summer&unstructuredText=auto-dimming interior mirror w/homelink/compass&unstructuredText=power w/tilt down heated mirrors";
		// String masterFeatureParameters = "{\"vin\":\"1G1PE5SB1G7169014\"}";// 3GCPCREC3FG408056, 1G1PE5SB1G7169014
		String masterFeatureParameters = "";
		for (String vin : vinNums) {
			// masterFeatureParameters = "{\"vin\":\"1G1PE5SB1G7169014\"}";// 3GCPCREC3FG408056, 1G1PE5SB1G7169014
			masterFeatureParameters = "{\"vin\":\"" + vin + "\"}";
			String masterFeatureURL = envURL;
			String jsonTextFrMasterFeatureWS = getSourceCode(masterFeatureParameters, masterFeatureURL, "");
			masterFeaturesWS(MasterFeatureWSSavePathFile, titleStringMasterFeatureWS, jsonTextFrMasterFeatureWS, vin);

		}

	}
	public static void jSonObjec_CPP_BuildDataExtractOrchestrationWS() throws Exception {
		Properties prop = new Properties();
		try {
			prop.load(AUTOpxController.class.getClassLoader().getResourceAsStream("data/JSONData.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// String env = prop.getProperty("environment");
		String[] vinNums = fetchOneDemArrayFromPropFile("MasterFeatureVINs", prop);
		// String[] vinNums = { "3GCPCREC3FG408056", "1GTN2LEH6GZ134145", "W04WV3N58GG042641" };

		String MasterFeatureWSSavePathFile = "C:\\1\\Eclipse\\Test Results\\MasterFeatureWS_Return.xls";
		// String jSONText = "";
		// jSONParser(jSONText);// start with { (curly brace - object) OK
		String[] titleStringMasterFeatureWS = { "S/N", "id:", "description:", "category:", "VIN:", "executionTimeMS:" };

		// ************QA**********:
		// http://lnoc-q1cp-xws1.autodatacorp.org:8080/master-feature-orchestration/rest/vehicle/features/
		String envURL = "http://lnoc-q1cp-xws1.autodatacorp.org:8080/master-feature-orchestration/rest/vehicle/features/";

		// ************QA**********:
		// // ************DEV**********:
		// String env = "http://lnoc-dvcp-xws1:8080/master-feature-orchestration/rest/vehicle/features/";
		//
		// // ************DEV**********:
		//// // ************Prod********:
		// String env = "http://xxxxxxxlnoc-dvcp-xws1:8080/master-feature-orchestration/rest/vehicle/features/";
		// String [] vinNums = {"3GCPCREC3FG408056","1GTN2LEH6GZ134145","W04WV3N58GG042641"};
		//// // ************Prod********:
		// String masterFeatureParameters="vin=\"3GCPCREC3FG408056\"&used=true&unstructuredText=tires: p245/35r20 performance summer&unstructuredText=auto-dimming interior mirror w/homelink/compass&unstructuredText=power w/tilt down heated mirrors";
		// String masterFeatureParameters = "{\"vin\":\"1G1PE5SB1G7169014\"}";// 3GCPCREC3FG408056, 1G1PE5SB1G7169014
		String masterFeatureParameters = "";
		for (String vin : vinNums) {
			// masterFeatureParameters = "{\"vin\":\"1G1PE5SB1G7169014\"}";// 3GCPCREC3FG408056, 1G1PE5SB1G7169014
			masterFeatureParameters = "{\"vin\":\"" + vin + "\"}";
			String masterFeatureURL = envURL;
			String jsonTextFrMasterFeatureWS = getSourceCode(masterFeatureParameters, masterFeatureURL, "");
			masterFeaturesWS(MasterFeatureWSSavePathFile, titleStringMasterFeatureWS, jsonTextFrMasterFeatureWS, vin);

		}

	}
	public static void jSonArry_UnityInventory() throws IOException {
		String[] titleString = { "S/N", "vehicleId:", "vin:", "stockNumber:", "used:", "certified:", "dateReceived:",
				"make:", "model:", "year:", "mileage:", "trim:", "bodyStyle:", "engine:", "transmission:",
				"exteriorColor:", "interiorColor:", "listPrice:", "picture:", "ctx:", "unityWrokID" };
		String savePathFile = "C:\\1\\Eclipse\\Test Results\\JSON_Return.xls";

		//
		// String inventoryJSONText = "[ { \"vehicleId\": 67793090, \"vin\": \"1G6AN1RY9G0101247\", \"stockNumber\": \"G0101247\", \"used\": false, \"certified\": false, \"dateReceived\": \"2016-08-24T00:00:00\", \"make\": \"Cadillac\", \"model\": \"ATS-V Coupe\", \"year\": \"2016\", \"mileage\": 0, \"trim\": \"xxx\", \"bodyStyle\": \"2dr Car\", \"engine\": \"3.6L 6 cyl\", \"transmission\": \"Automatic\", \"exteriorColor\": \"Gray\", \"interiorColor\": \"Black\", \"listPrice\": 76057, \"picture\": \"http://inventory-dmg.assets-cdk.com/4/4/3/13352307344.jpg\", \"ctx\": \"BVJR64Q\" }, { \"vehicleId\": 68002238, \"vin\": \"1G6AB5SS4G0109293\", \"stockNumber\": \"G0109293\",
		// \"used\": false, \"certified\": false, \"dateReceived\": \"2016-08-24T00:00:00\", \"make\": \"Cadillac\", \"model\": \"ATS Sedan\", \"year\": \"2016\", \"mileage\": 0, \"trim\": \"Luxury Collection RWD\", \"bodyStyle\": \"4dr Car\", \"engine\": \"3.6L 6 cyl\", \"transmission\": \"Automatic\", \"exteriorColor\": \"Black\", \"interiorColor\": \"Black\", \"listPrice\": 47555, \"picture\": \"http://inventory-dmg.assets-cdk.com/8/4/6/13352307648.jpg\", \"ctx\": \"BVJR64Q\" }, { \"vehicleId\": 68002241, \"vin\": \"1G6AV5S82G0113631\", \"stockNumber\": \"G0113631\", \"used\": false, \"certified\": false, \"dateReceived\": \"2016-08-24T00:00:00\", \"make\":
		// \"Cadillac\", \"model\": \"CTS Sedan\", \"year\": \"2016\", \"mileage\": 0, \"trim\": \"V-Sport Premium RWD\", \"bodyStyle\": \"4dr Car\", \"engine\": \"3.6L 6 cyl\", \"transmission\": \"Automatic\", \"exteriorColor\": \"Black\", \"interiorColor\": \"Black\", \"listPrice\": 73735, \"picture\": \"http://inventory-dmg.assets-cdk.com/5/4/9/13352307945.jpg\", \"ctx\": \"BVJR64Q\" } ]";
		// getUnityWorkInventoryFrJSONParser(savePathFile,titleString, inventoryJSONText, "testID");// Array OK - start with [ characters (square brackets - array)

		// String unityWorkURL = "https://data.dealervideos.com/v0.9/vehicles/";
		String unityWorkURL = "https://data.dealervideos.com/v1.0/vehicles/"; // see VDVIIMG-759
		String[] unityWorkDealerShipID = { "4B5CDSU", "2FHFIYI" };// {"4B5CDSU","7U9G76C","DP9B0OU","1V0BJMB","2FHFIYI","DTK5S8O","FB7CIB0","COA94NS","D2E8HW2","PDZE4CK","246GNLU","F2NF3UN","CGPB0MT","HSOFV9O","27OE966"};//{"4B5CDSU","7U9G76C","DP9B0OU","1V0BJMB","2FHFIYI","DTK5S8O","FB7CIB0","COA94NS","D2E8HW2","PDZE4CK","246GNLU","F2NF3UN","CGPB0MT","HSOFV9O","27OE966"};
		for (String id : unityWorkDealerShipID) {
			String jsonTextFrUnityWork = getSourceCode(unityWorkURL, id);
			getUnityWorkInventoryFrJSONParser(savePathFile, titleString, jsonTextFrUnityWork, id);// Array OK - start with [ characters (square brackets - array)
		}
	}

	private static String[] fetchOneDemArrayFromPropFile(String propertyName, Properties propFile) {
		// get array split up by the colin
		String a[] = propFile.getProperty(propertyName).split(",");
		return a;
	}

	public static void main(String[] args) throws Exception {
		// // From what I can read on json.org, all JSON strings should start with { (curly brace - object), and [ characters (square brackets - array) represent an array element in JSON.
		// // ******************************************************curly brace - object*********************************************************
		// jSonObjec_SydicationWS();
		// jSonObjec_CPP_FeulEconomyWS();
//		jSonObjec_CPP_MasterFeatureWS();
		jSonObjec_CPP_BuildDataExtractOrchestrationWS();
		// // ******************************************************End of curly brace - object*********************************************************

		// //// ******************************************************start with [ characters (square brackets - array*********************************************************
		//
		// jSonArry_UnityInventory(); //On Tuesday Auguest 30, 2016: API returns { "message": "An error has occurred." } from https://data.dealervideos.com/v1.0/vehicles/2FHFIYI
		// // //******************************************************End of start with [ characters (square brackets - array*********************************************************

		System.out.println("Complete!!!");

	}
}
