package Cassandra;

//It works on November 25, 2017
//Updated on November 23, 2017
//It works on November 25, 2017
//20180504_Copied from test.java. need to work....not finished.
//20180911_ test...
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.utils.UUIDs;

import cig.CassandraConnection;

public class VehicleTokenMS {
	public static void SaveScratch(String pathfilename, String ScratchText) {
		try {
			// BufferedWriter out2 = new BufferedWriter(new FileWriter(dataDir+ "Acodes.txt", true)); //original OK
			BufferedWriter out2 = new BufferedWriter(new FileWriter(pathfilename, true));
			// out2.write("("+i+"): "+Acode+": ");
			// out2.write(i + ". " + Acode + ": "); //Original OK
			// out2.newLine();
			out2.write(ScratchText);
			out2.newLine();
			out2.close();
		} catch (Exception e) {// Catch  exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}

	public static void getBatch_Processing_Request(int stylenum, ResultSet res, int totalStyle, String pathfilename) {
		String ColormappingTitle = "id,styleid,imageid,filename,ext1mfrfullcode,ext2mfrfullcode,ext1rgbhex,ext2rgbhex,type,angle,background,size,year,division,model,body,carryover,exactmatch,OEMTemp,country,lastmodified";
		UUID id;
		String styleid = "";
		String imageid = "";
		String filename = "";
		String ext1mfrfullcode = "";
		String ext2mfrfullcode = "";
		String ext1rgbhex = "";
		String ext2rgbhex = "";
		String type = "";
		String angle = "";
		String background = "";
		String size = "";
		String year = "";
		String division = "";
		String model = "";
		String body = "";
		String carryover = "";
		String exactmatch = "";
		String OEMTemp = "";
		String country = "";
		Date lastmodified;
		String rowString = "";
		if (stylenum == 1) {
			System.out.println("");
			System.out.println("Start:" + stylenum);
			System.out.println(ColormappingTitle);
			SaveScratch(pathfilename, ColormappingTitle);
		}
		for (Row row : res) {
			// Integer.toString(currentDynamicValue.getJSONObject(i).getInt("groupId"));

			id=row.getUUID("batchguid");
			styleid=row.getString("endpoint");
			
			//			id = row.getUUID("id");
//			styleid = Integer.toString(row.getInt("styleid"));
//			imageid = row.getString("imageid");
//			filename = row.getString("filename");
//			ext1mfrfullcode = row.getString("ext1mfrfullcode");
//			ext2mfrfullcode = row.getString("ext2mfrfullcode");
//			ext1rgbhex = row.getString("ext1rgbhex");
//			ext2rgbhex = row.getString("ext2rgbhex");
//			type = row.getString("type");
//			angle = row.getString("angle");
//			background = row.getString("background");
//			size = row.getString("size");
//			year = Integer.toString(row.getInt("year"));
//			division = row.getString("division");
//			model = row.getString("model");
//			body = row.getString("body");
//			carryover = row.getString("carryover");
//			// exactmatch = row.getString("exactmatch");
//			// OEMTemp = row.getString("OEMTemp");
//			country = row.getString("country");
//			lastmodified = row.getTimestamp("lastmodified");
//			rowString = id + "," + styleid + "," + imageid + "," + filename + "," + ext1mfrfullcode + ","
//					+ ext2mfrfullcode + "," + ext1rgbhex + "," + ext2rgbhex + "," + type + "," + angle + ","
//					+ background + "," + size + "," + year + "," + division + "," + model + "," + body + "," + carryover
//					+ "," + exactmatch + "," + OEMTemp + "," + country + "," + lastmodified;

			rowString=id+","+styleid;
			
			System.out.println(rowString);
			SaveScratch(pathfilename, rowString);
		}
		if (stylenum == totalStyle) {
			System.out.println("End!" + stylenum);
		}
	}

	public static void getVehiclemappings(int stylenum, ResultSet res, int totalStyle, String pathfilename) {
		String VehiclemappingTitle = "id,styleid,imageid,filename,type,background,size,carryover,year,division,model,body,exactmatch,OEMTemp,country,lastmodified";
		UUID id;
		String styleid = "";
		String imageid = "";
		String filename = "";
		String type = "";
		String background = "";
		String size = "";
		String carryover = "";
		String year = "";
		String division = "";
		String model = "";
		String body = "";
		String exactmatch = "";
		String OEMTemp = "";
		String country = "";
		Date lastmodified;
		String rowString = "";
		if (stylenum == 1) {
			System.out.println("");
			System.out.println("Start:" + stylenum);
			System.out.println(VehiclemappingTitle);
			SaveScratch(pathfilename, VehiclemappingTitle);
		}
		for (Row row : res) {
			// Integer.toString(currentDynamicValue.getJSONObject(i).getInt("groupId"));
			id = row.getUUID("id");
			styleid = Integer.toString(row.getInt("styleid"));
			imageid = row.getString("imageid");
			filename = row.getString("filename");
			type = row.getString("type");
			background = row.getString("background");
			size = row.getString("size");
			carryover = row.getString("carryover");
			year = Integer.toString(row.getInt("year"));
			division = row.getString("division");
			model = row.getString("model");
			body = row.getString("body");
//			exactmatch = row.getString("exactmatch");
//			OEMTemp = row.getString("OEMTemp");
			country = row.getString("country");
			lastmodified = row.getTimestamp("lastmodified");
			rowString = id + "," + styleid + "," + imageid + "," + filename + "," + type + "," + background + "," + size
					+ "," + carryover + "," + year + "," + division + "," + model + "," + body + "," + exactmatch + ","
					+ OEMTemp + "," + country + "," + lastmodified;
			System.out.println(rowString);
			SaveScratch(pathfilename, rowString);
		}
		if (stylenum == totalStyle) {
			System.out.println("End!" + stylenum);
		}
	}

	public static void main(String[] args) {
		String serverIp = "172.16.167.27"; //LCOA-DVTP-XCA1.autodatacorp.org. Login: batchprocessingqauser, Ps: 
		String keyspace = "batch_processing_request_qa";
		CassandraConnection connection;
//		Cluster cluster = Cluster.builder().addContactPoints(serverIp).build();
		Cluster cluster = Cluster.builder().addContactPoints(serverIp).withCredentials("batchprocessingqauser", "password").build();//works
		Session session = cluster.connect(keyspace);
		String[] style = {"389544", "387896" };// { "389544", "387896","389544" };
		String datestring = "20171124";
		int styleLength = style.length;
		String colorpathfile = "C:\\chrome\\loader\\CIG-MAPPING-IMPORT\\Test Results\\test\\JavaResults\\batchrequest_"
				+ datestring + "_" + styleLength + ".txt";
		String vehiclepathfile = "C:\\chrome\\loader\\CIG-MAPPING-IMPORT\\Test Results\\test\\JavaResults\\vehiclemapping_"
				+ datestring + "_" + styleLength + ".txt";
		// ***************************Colormappings**************************
		 for (int i = 0; i < styleLength; i++) {
		 String cqlStatement = "select * from batch_processing_request_qa.batchrequest";// + style[i];
		 ResultSet results = session.execute(cqlStatement);
		 getBatch_Processing_Request(i + 1, results, styleLength, colorpathfile);
		 }
		// ***************************Colormappings**************************

//		// ***************************Vehiclemappings**************************
//		for (int i = 0; i < styleLength; i++) {
//			String cqlStatement = "select * from vehiclemappings where styleid=" + style[i];
//			ResultSet results = session.execute(cqlStatement);
//			getVehiclemappings(i + 1, results, styleLength, vehiclepathfile);
//		}
//		// ***************************Colormappings**************************

		// for (Row row : results) {
		// System.out.println(row.getUUID("id"));
		// System.out.format("%s %s\n", row.getString("division"), row.getString("model"));
		// }
		// for (Row row : session.execute(cqlStatement)) {
		// System.out.println(row.toString());
		// }
		cluster.close();
	}
}