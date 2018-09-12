package cPP;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

public class getSyndicationWS {

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
	
	
	public static void syndicationWSCompleteImageList(String wsResultfile, String[] titleSyndicationWS, String text,
			String dealershipID) throws IOException {
		JSONObject obj = new JSONObject(text);
		int size1 = obj.length();
		int wSize = titleSyndicationWS.length;
		String[] jsonValue = new String[wSize];
		com_libs.writeTitle(wsResultfile, titleSyndicationWS);
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

			com_libs.writeToSheet(wsResultfile, jsonValue);

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
		String jsonTextFrSydicationWS = com_libs.getSourceCode(sydicationWSCompleteImageListURL, "");
		//// syndicationWSCompleteImageList(jsonTextFrSydicationWS);
		// syndicationWSCompleteImageList(sydicationWSsavePathFile, titleStringSydicationWS, jsonTextFrSydicationWS, dealershipID);

		// jSONText="{\"serverTime\":1472499464523,\"message\":null,\"serverID\":\"LNOC-Q13V-XWA1 (LNOC-Q13V-XWA1/172.16.130.88)\",\"images\":[{\"dealerGuid\":\"FEB6AE7C-4DDC-42CD-9F61-A1BDE81AF204\",\"vehicleGuid\":\"C5CB3E17-B5B5-46FC-93DA-257732B72807\",\"vin\":\"1GKEC13V82R226994\",\"stockNum\":\"G9394C\",\"publishDate\":null,\"imageGuId\":\"C9AFAC6B-1A12-4359-9344-3034F93B5211\",\"shotId\":\"999\",\"imageDescription\":\"Vehicle Benefits
		// Image\",\"url\":\"http://lnoc-q13v-xwa1.autodata.org/SyndicationWebServices/FEB6AE7C-4DDC-42CD-9F61-A1BDE81AF204/1GKEC13V82R226994/640/999/Garnet-Red-Metallic-(72U)-2002-GMC-Yukon--SLE-London-NY-999.jpg\",\"sequence\":10099,\"imgGroup\":\"Custom\"},{\"dealerGuid\":\"FEB6AE7C-4DDC-42CD-9F61-A1BDE81AF204\",\"vehicleGuid\":\"72AEE0F6-8272-4C8E-970A-5CAE3E8B29AF\",\"vin\":\"1GKEC13Z42R186593\",\"stockNum\":\"G9175A\",\"publishDate\":null,\"imageGuId\":\"3F9749A8-B6F1-4816-B577-C7093E2D11FE\",\"shotId\":\"999\",\"imageDescription\":\"Vehicle Benefits
		// Image\",\"url\":\"http://lnoc-q13v-xwa1.autodata.org/SyndicationWebServices/FEB6AE7C-4DDC-42CD-9F61-A1BDE81AF204/1GKEC13Z42R186593/640/999/Summit-White-(50U)-2002-GMC-Yukon--SLT-London-NY-999.jpg\",\"sequence\":10099,\"imgGroup\":\"Custom\"},{\"dealerGuid\":\"FEB6AE7C-4DDC-42CD-9F61-A1BDE81AF204\",\"vehicleGuid\":\"6FD76724-DF74-4C70-8D7D-3DDB61833D57\",\"vin\":\"1GYEK63N03R254845\",\"stockNum\":\"B1062A\",\"publishDate\":null,\"imageGuId\":\"9B841967-D6B0-4429-B734-C5985D6DD473\",\"shotId\":\"999\",\"imageDescription\":\"Vehicle Benefits
		// Image\",\"url\":\"http://lnoc-q13v-xwa1.autodata.org/SyndicationWebServices/FEB6AE7C-4DDC-42CD-9F61-A1BDE81AF204/1GYEK63N03R254845/640/999/White-Diamond-(98U)-2003-Cadillac-Escalade--null-London-NY-999.jpg\",\"sequence\":10099,\"imgGroup\":\"Custom\"}]}";
		syndicationWSCompleteImageList(sydicationWSSavePathFile, titleStringSydicationWS, jsonTextFrSydicationWS,
				dealershipID);
	}

	
	
	
	
	
	
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		// // From what I can read on json.org, all JSON strings should start with { (curly brace - object), and [ characters (square brackets - array) represent an array element in JSON.
		// // ******************************************************curly brace - object*********************************************************
		 jSonObjec_SydicationWS();
//		 jSonObjec_CPP_FeulEconomyWS();
//		jSonObjec_CPP_MasterFeatureWS();
//		jSonObjec_CPP_BuildDataExtractOrchestrationWS();
		// // ******************************************************End of curly brace - object*********************************************************

		// //// ******************************************************start with [ characters (square brackets - array*********************************************************
		//
		// jSonArry_UnityInventory(); //On Tuesday Auguest 30, 2016: API returns { "message": "An error has occurred." } from https://data.dealervideos.com/v1.0/vehicles/2FHFIYI
		// // //******************************************************End of start with [ characters (square brackets - array*********************************************************

		System.out.println("Complete!!!");
	}

}
