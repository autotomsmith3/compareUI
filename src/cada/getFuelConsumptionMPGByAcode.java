package cada;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import DealerPortal.AUTOpxController;
import cPP.com_libs;

public class getFuelConsumptionMPGByAcode {

	public static void getTheFuelConsumptionMPGByAcode(String acode, String serverName, String dbName, String userName,
			String password, String savePathFile, String[] titleStringPaitInfo)
			throws ClassNotFoundException, SQLException, IOException {

		cPP.com_libs.writeTitle(savePathFile, titleStringPaitInfo);

		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

		Connection conn = DriverManager.getConnection("jdbc:sqlserver://" + serverName + ";user=" + userName
				+ ";password=" + password + ";database=" + dbName + "");

		System.out.println("test");

		Statement sta = conn.createStatement();
		
		String Sql = "DECLARE @acode4 char(13) = '" + acode
				+ "' SELECT PT1.Acode, TrimCodes, PT1.RevCode, PT1.TeamCode, EngCode, TransCode ,(PT4.ICCSectionCode+PT4.ICCGroupCode+PT4.ICCFamilyCode+PT4.ICCSubFamCode) AS ICC_Code , ICCItemValue = (0.001 * CAST(ICCItemValue AS INT)) , MPG = CONVERT(DECIMAL(4,1),ROUND((100 * 3.785411784) / (1.609344 * (0.001 * CAST(ICCItemValue AS INT))),2)) FROM PT01_PowerTeams PT1  JOIN PT04_PowerTeamContents PT4 ON PT4.Acode = PT1.Acode AND PT4.RevCode = PT1.RevCode AND PT4.TeamCode = PT1.TeamCode WHERE PT1.Acode = Substring(@acode4,1,11)  AND (TrimCodes LIKE '%' + Substring(@acode4,12,1) + '%' OR TrimCodes LIKE '*') AND PT1.RevCode = (SELECT MAX(RevCode) FROM PT01_PowerTeams WHERE PT1.Acode = Acode AND PT1.TeamCode = TeamCode ) AND PT4.ICCSectionCode = 'R' AND PT4.ICCGroupCode = 'A' AND PT4.ICCFamilyCode = 'A' AND PT4.ICCSubFamCode IN (01,02,08) ORDER BY PT1.RevCode DESC, PT1.TeamCode";
		String Acode = "";
		String TrimCodes = "";
		String RevCode = "";
		String TeamCode = "";
		String EngCode = "";
		String TransCode = "";
		String ICC_Code = "";
		String ICCItemValue = "";
		String MPG = "";

		int wSize = titleStringPaitInfo.length;
		String[] infoValue = new String[wSize];

		ResultSet rs = sta.executeQuery(Sql);
		int icolumn = rs.getRow();
		while (rs.next()) {
			icolumn = rs.getRow();
			Acode = rs.getString("Acode");

			TrimCodes = rs.getString("TrimCodes");
			RevCode = rs.getString("RevCode");
			TeamCode = rs.getString("TeamCode");
			EngCode = rs.getString("EngCode");
			TransCode = rs.getString("TransCode");
			ICC_Code = rs.getString("ICC_Code");
			ICCItemValue = rs.getString("ICCItemValue");
			MPG = rs.getString("MPG");

			System.out.println("Row =" + icolumn);
			System.out.println("Acode = " + rs.getString("Acode") + " TrimCodes = " + rs.getString("TrimCodes")
					+ " RevCode = " + rs.getString("RevCode") + " TeamCode = " + rs.getString("TeamCode")
					+ " EngCode = " + rs.getString("EngCode") + " TransCode = " + rs.getString("TransCode")
					+ " ICC_Code = " + rs.getString("ICC_Code") + " ICCItemValue = " + rs.getString("ICCItemValue")
					+ " MPG = " + rs.getString("MPG"));

			// Acode TrimCode RevCode TeamCode EngCode TransCode ICC_Code ICCItemValue MPG Advertising TripleICC_Code

			infoValue[0] = Integer.toString(icolumn);
			infoValue[1] = Acode;
			infoValue[2] = TrimCodes;
			infoValue[3] = RevCode;
			infoValue[4] = TeamCode;
			infoValue[5] = EngCode;
			infoValue[6] = TransCode;
			infoValue[7] = ICC_Code;
			infoValue[8] = ICCItemValue;
			infoValue[9] = MPG;

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
		String SavePathFile = "C:\\1\\Eclipse\\Test Results\\CADA\\CADA_DB_FuelConsumptionMPGByAcode_Return.xls";

		String[] titleStringInfo = { "S/N", "Acode:", "TrimCode:", "RevCode:", "TeamCode:", "EngCode:", "TransCode:",
				"ICC_Code:", "ICCItemValue:", "MPG:" };

		Properties prop = new Properties();
		// testprop.load(new FileInputStream("data/autopxConf.properties"));
		try {
			prop.load(AUTOpxController.class.getClassLoader().getResourceAsStream("cada_data/cadaData.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String serverName = prop.getProperty("serverName");
		String dbName = prop.getProperty("dbName");
		String userName = prop.getProperty("userName");
		String password = prop.getProperty("password");

		String[] acodes = com_libs.fetchOneDemArrayFromPropFile("acodes", prop);

		for (String acode : acodes) {
			getTheFuelConsumptionMPGByAcode(acode, serverName, dbName, userName, password, SavePathFile, titleStringInfo);
		}

		System.out.println("The End!");
	}

}
