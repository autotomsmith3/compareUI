package cada;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import DealerPortal.AUTOpxController;
import cPP.com_libs;

public class getDataInfoSquishVin {

	public static void getDataInfoFromSquishVin(String vin, String serverName, String dbName, String userName,
			String password, String savePathFile, String[] titleStringPaitInfo)
			throws ClassNotFoundException, SQLException, IOException {

		cPP.com_libs.writeTitle(savePathFile, titleStringPaitInfo);

		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

		Connection conn = DriverManager.getConnection("jdbc:sqlserver://" + serverName + ";user=" + userName
				+ ";password=" + password + ";database=" + dbName + "");

		System.out.println("test");

		Statement sta = conn.createStatement();

		String Sql = "DECLARE @vin char(17) = '1FTEW1CFXFFA96154'   SELECT Acode  , YEAR AS Model_Year  , MAKE  , MODEL  , MFR_CODE AS Model_Code  , TRIM  , UNIQUE_PACKAGE_CODES AS [Sub-Trim/Package]  , DOORS AS Body_Description_Door_Count  , BOX_STYLE AS Body_Description_Bed_Style  , CAB_TYPE AS Body_Description_Cab_Style  , BODY_TYPE AS Body_Description_Body_Style  , EngineDescription + ' ' + CAM_TYPE + ' ' + FUEL_INDUCTION AS Engine_Description  , CAST(TRAN_SPEED AS VARCHAR(3)) + '-Speed ' + TRAN_TYPE AS Transmission_Description   , DRIVE_TYPE_CODE AS Drive_Train_Description  , WHEELBASE  , BaseMSRP AS Base_MSRP  FROM VinDecode..FullSV (nolock)  WHERE squish_vin = SUBSTRING(@vin,1,8) + SUBSTRING(@vin,10,2)   AND LngCode = 'EN' ";

		String Acode = "";
		String Model_Year = "";
		String MAKE = "";
		String MODEL = "";
		String Model_Code = "";
		String TRIM = "";
		String Sub_Trim_Package = "";
		String Body_Description_Door_Count = "";
		String Body_Description_Bed_Style = "";
		String Body_Description_Cab_Style = "";
		String Body_Description_Body_Style = "";
		String Engine_Description = "";
		String Transmission_Description = "";
		String Drive_Train_Description = "";
		String WHEELBASE = "";
		String Base_MSRP = "";

		int wSize = titleStringPaitInfo.length;
		String[] infoValue = new String[wSize];

		ResultSet rs = sta.executeQuery(Sql); // good

		int icolumn = rs.getRow();
		while (rs.next()) {
			icolumn = rs.getRow();
			Acode = rs.getString("Acode");
			Model_Year = rs.getString("Model_Year");
			MAKE = rs.getString("MAKE");
			MODEL = rs.getString("MODEL");
			Model_Code = rs.getString("Model_Code");
			TRIM = rs.getString("TRIM");
			Sub_Trim_Package = rs.getString("Sub-Trim/Package");
			Body_Description_Door_Count = rs.getString("Body_Description_Door_Count");
			Body_Description_Bed_Style = rs.getString("Body_Description_Bed_Style");
			Body_Description_Cab_Style = rs.getString("Body_Description_Cab_Style");
			Body_Description_Body_Style = rs.getString("Body_Description_Body_Style");
			Engine_Description = rs.getString("Engine_Description");
			Transmission_Description = rs.getString("Transmission_Description");
			Drive_Train_Description = rs.getString("Drive_Train_Description");
			Base_MSRP = rs.getString("Base_MSRP");
			WHEELBASE = rs.getString("WHEELBASE");

			System.out.println("Row =" + icolumn);
			System.out.println("Acode = " + rs.getString("Acode") + " Model_Year = " + rs.getString("Model_Year")
					+ " MAKE = " + rs.getString("MAKE") + " MODEL = " + rs.getString("MODEL") + " Model_Code = "
					+ rs.getString("Model_Code") + " TRIM = " + rs.getString("TRIM") + " Sub-Trim/Package = "
					+ rs.getString("Sub-Trim/Package") + " Body_Description_Bed_Style = "
					+ rs.getString("Body_Description_Bed_Style"));

			infoValue[0] = Integer.toString(icolumn);
			infoValue[1] = vin;
			infoValue[2] = Acode;
			infoValue[3] = Model_Year;
			infoValue[4] = MAKE;
			infoValue[5] = MODEL;
			infoValue[6] = Model_Code;
			infoValue[7] = TRIM;
			infoValue[8] = Sub_Trim_Package;
			infoValue[9] = Body_Description_Door_Count;
			infoValue[10] = Body_Description_Bed_Style;
			infoValue[11] = Body_Description_Cab_Style;
			infoValue[12] = Body_Description_Body_Style;
			infoValue[13] = Engine_Description;
			infoValue[14] = Transmission_Description;
			infoValue[15] = Drive_Train_Description;
			infoValue[16] = WHEELBASE;
			infoValue[17] = Base_MSRP;

			cPP.com_libs.writeToSheet(savePathFile, infoValue);
		}

		if (icolumn == 1) {
			System.out.println("One result returns\n");

		} else {
			System.out.println("Multiple results return\n");
		}
		rs.close();
		sta.close();
		conn.close();

	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		String SavePathFile = "C:\\1\\Eclipse\\Test Results\\CADA\\CADA_DB_SquishVin_Return.xls";

		String[] titleStringInfo = { "S/N", "VIN", "Acode:", "Model_Year:", "MAKE:", "MODEL:", "Model_Code:", "TRIM:",
				"Sub_Trim_Package:", "Body_Description_Door_Count:", "Body_Description_Bed_Style:",
				"Body_Description_Cab_Style:", "Body_Description_Body_Style:", "Engine_Description:",
				"Transmission_Description:", "Drive_Train_Description:", "Base_MSRP:", "WHEELBASE:" };

		Properties prop = new Properties();
		// testprop.load(new FileInputStream("data/autopxConf.properties"));
		try {
			prop.load(AUTOpxController.class.getClassLoader().getResourceAsStream("cada_data/cadaData.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String serverName = prop.getProperty("serverName");
		String dbName = prop.getProperty("dbNameVinDecode");// *******************
		String userName = prop.getProperty("userName");
		String password = prop.getProperty("password");

		String[] vins = com_libs.fetchOneDemArrayFromPropFile("vins", prop);
		// String[] options= com_libs.fetchOneDemArrayFromPropFile("optCodes", prop);

		// String[][] acodeoptions = com_libs.fetchArrayFromPropFile("acodes", prop);

		for (String vin : vins) {
			getDataInfoFromSquishVin(vin, serverName, dbName, userName, password, SavePathFile, titleStringInfo);
		}
		int c = 0;
		// String opt = "";
		// for (int i = 0; i < acodeoptions.length; i++) {
		// String acode = acodeoptions[i][0];
		// int opts = acodeoptions[i].length;
		// for (int j = 1; j < opts; j++) {
		// c++;
		// opt = acodeoptions[i][j];
		// getDataInfoFromSquishVin(acode, opt, serverName, dbName, userName, password, SavePathFile,
		// titleStringInfo);
		//
		// }
		//
		// }

		System.out.println("The End!");
	}

}
