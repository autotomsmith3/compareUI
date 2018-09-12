package DealerPortal;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Crunchify.com
 * 
 */

public class CrunchifyParseJSONObject {
	public static void main(String[] args) throws FileNotFoundException, JSONException {
		String jsonData = "";
		BufferedReader br = null;
		try {
			String line;
			br = new BufferedReader(new FileReader("C:\\1\\Eclipse\\Test Results\\Crunchify_JSON - Copy.txt"));
			while ((line = br.readLine()) != null) {
				jsonData += line + "\n";
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		 System.out.println("File Content: \n" + jsonData);
		JSONObject obj = new JSONObject(jsonData);
		System.out.println("blogURL: " + obj.getString("blogURL"));
		System.out.println("twitter: " + obj.getString("twitter"));
		System.out.println("social: " + obj.getJSONObject("social"));
	}
}
