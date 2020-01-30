package cassandraDB;

//It works on January 27, 2020
//Updated on January 28, 2020
//It works on January 28, 2020
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import org.json.JSONArray;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.utils.UUIDs;

import cig.CassandraConnection;

public class VINpxFCA_StagingSaveToSheet {
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
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}

	public static void getFCAInventory(int dealernum, ResultSet res,String dealerid, int totalDealers, String pathfilename,
			String datestring) throws Exception {

		String[] titleString = { "S/N", "dealercode", "vin", "bodystyle", "exteriorcolorcode",
				"exteriorcolordescription5", "make", "model", "optioncodes", "stocknum", "trimdescription10",
				"upperlevelpackage", "vehtype", "year" };
//		String savePathFile = "C:\\1\\Eclipse\\Test Results\\UnityInventory\\UnityInventory_JSON_Return.xls";
		String savePathFile = "C:\\1\\Eclipse\\Test Results\\FCA_CassandraStaging\\Staging_FCA_Inventory_" + datestring
				+ ".xls";
		int count = 0;

//		JSONArray jsonarray = new JSONArray(text);
//		int size = jsonarray.length();
		int wSize = titleString.length;
		String[] jsonValue = new String[wSize];
		cPP.com_libs.writeTitle(savePathFile, titleString);

		String FCAInventoryTitle = "dealercode,vin,bodystyle,exteriorcolorcode,exteriorcolordescription5,make,model,optioncodes,stocknum,trimdescription10,upperlevelpackage,vehtype,year13";
		UUID id;
		String dealercode = "";
		String vin = "";
		String bodystyle = "";
		String exteriorcolorcode = "";
		String exteriorcolordescription = "";
		String make = "";
		String model = "";
		String optioncodes = "";
		String stocknum = "";
		String trimdescription = "";
		String upperlevelpackage = "";
		String vehtype = "";
		String year = "";

		Date lastmodified;
		String rowString = "";
		if (dealernum == 1) {
			System.out.println("");
			System.out.println("Start:" + dealernum);
			System.out.println(FCAInventoryTitle);
			SaveScratch(pathfilename, FCAInventoryTitle);
		}
		for (Row row : res) {
			count++;
			dealercode = row.getString("dealercode");
			vin = row.getString("vin");
			bodystyle = row.getString("bodystyle");
			exteriorcolorcode = row.getString("exteriorcolorcode");
			exteriorcolordescription = row.getString("exteriorcolordescription");
			make = row.getString("make");
			model = row.getString("model");
			optioncodes = row.getString("optioncodes");
			stocknum = row.getString("stocknum");
			trimdescription = row.getString("trimdescription");
			upperlevelpackage = row.getString("upperlevelpackage");
			vehtype = row.getString("vehtype");
			year = row.getString("year");

//			rowString = row + "," + dealercode + "," + bodystyle + "," + exteriorcolorcode + ","
//					+ exteriorcolordescription + "," + make + "," + model + "," + optioncodes + "," + stocknum + ","
//					+ trimdescription + "," + model + "," + upperlevelpackage + "," + vehtype + "," + year;
//			System.out.println(rowString);
			SaveScratch(pathfilename, rowString);
			System.out.println("Dealerid ="+dealerid+". Count = " + count);
//			********Save to sheet***********************************************************************************************
			jsonValue[0] = Integer.toString(count);
			jsonValue[1] = dealercode;
			jsonValue[2] = vin;
			jsonValue[3] = bodystyle;
			jsonValue[4] = exteriorcolorcode;
			jsonValue[5] = exteriorcolordescription;
			jsonValue[6] = make;
			jsonValue[7] = model;
			jsonValue[8] = optioncodes;
			jsonValue[9] = stocknum;
			jsonValue[10] = trimdescription;
			jsonValue[11] = upperlevelpackage;
			jsonValue[12] = vehtype;
			jsonValue[13] = year;

			cPP.com_libs.writeToSheet(savePathFile, jsonValue);

		}
		if (dealernum == totalDealers) {
			System.out.println("End!" + dealernum);
		}
	}

	public static void main(String[] args) throws Exception {
		String serverIp = "10.100.64.63"; // nslookup LCOA-DVTP-XCA1.autodatacorp.org. Login: fcainventory_user, Ps: password, //LCOA-DVTP-XCA1 keyspace: fcainventory_user
//		String serverIp = "172.16.150.11"; //nslookup LCOA-DVTP-XCA1.autodatacorp.org. Login: fcainventory_user, Ps: password,  //LCOA-DVTP-XCA1 keyspace: fcainventory_user 
//		"172.16.150.11"  - nslookup LCOA-DVTP-XCA1.autodatacorp.org
//		"172.16.150.12"  - nslookup LCOA-DVTP-XCA2.autodatacorp.org
//		"172.16.150.13"  - nslookup LCOA-DVTP-XCA3.autodatacorp.org
//		"10.100.64.63" - nslookup pcoc-stfi-xca1.autodata.org
//		"10.100.64.64" - nslookup pcoc-stfi-xca2.autodata.org
//		
//		
//		pcoc-stfi-xca2.autodata.org/10.100.64.64:9042 -- what's this? don't know
//		Need to add below jars and dependency in porm.xml
//		 <dependency>
//         <groupId>com.datastax.cassandra</groupId>
//         <artifactId>cassandra-driver-core</artifactId>
//         <version>3.4.0</version>
//     </dependency>
//     <dependency>
//         <groupId>com.datastax.cassandra</groupId>
//         <artifactId>cassandra-driver-mapping</artifactId>
//         <version>3.4.0</version>
//     </dependency>
//     <dependency>
//         <groupId>com.datastax.cassandra</groupId>
//         <artifactId>cassandra-driver-extras</artifactId>
//         <version>3.4.0</version>
//     </dependency>
//     <dependency>
//         <groupId>org.slf4j</groupId>
//         <artifactId>slf4j-api</artifactId>
//         <version>1.7.25</version>
//     </dependency>
//     <dependency>
//         <groupId>org.slf4j</groupId>
//         <artifactId>slf4j-log4j12</artifactId>
//         <version>1.7.22</version>
//     </dependency>
//		
		String keyspace = "vdvi";
//		String dealerid="23871";//23871 =123456_FCA
		boolean alldealers = false;
		String datestring = "20200129";
		String[] dealerids = { "23871"};// "23871" - 123456_FCA, "45685"
		int dealersLength = dealerids.length;
//		CassandraConnection connection;
//		Cluster cluster = Cluster.builder().addContactPoints(serverIp).build();
		Cluster cluster = Cluster.builder().addContactPoints(serverIp).withCredentials("fcainventory_user", "password")
				.build();// works
		Session session = cluster.connect(keyspace);// 20200123 passed

		if (alldealers) {
			String cqlStatement = "select * from vi01_fcadigital_inventory";//
			ResultSet results = session.execute(cqlStatement);

			String fcastagingpathfile = "C:\\1\\Eclipse\\Test Results\\FCA_CassandraStaging\\Staging_FCA_Inventory_"
					+ datestring + "_All_Dealers" + ".txt";

			for (int i = 0; i < dealersLength; i++) {

				getFCAInventory(i + 1, results, "All", dealersLength, fcastagingpathfile, datestring);
			}
		} else {
			for (String dealerid : dealerids) {
				String cqlStatement = "select * from vi01_fcadigital_inventory where dealercode='" + dealerid
						+ "' allow filtering";//
				ResultSet results = session.execute(cqlStatement);

				String fcastagingpathfile = "C:\\1\\Eclipse\\Test Results\\FCA_CassandraStaging\\Staging_FCA_Inventory_"
						+ datestring + "_" + dealersLength + ".txt";
				for (int i = 0; i < dealersLength; i++) {
					getFCAInventory(i + 1, results, dealerid, dealersLength, fcastagingpathfile, datestring);
				}
			}
		}

		cluster.close();
	}
}