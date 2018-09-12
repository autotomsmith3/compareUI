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

public class getPaintInformationByAcode {

	public static void getThePaintInformationByAcode(String acode, String serverName, String dbName, String userName,
			String password, String savePathFile, String[] titleStringPaitInfo)
			throws ClassNotFoundException, SQLException, IOException {

		cPP.com_libs.writeTitle(savePathFile, titleStringPaitInfo);

		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		// Connection conn = DriverManager.getConnection("jdbc:sqlserver://LNOC-Q13V-MSQ1.autodata.org;user=VDVIWebServicesUserQA;password=HDuMy873JRFpkkU9;database=VDVI_Master");
		Connection conn = DriverManager.getConnection("jdbc:sqlserver://" + serverName + ";user=" + userName
				+ ";password=" + password + ";database=" + dbName + "");

		System.out.println("test");

		Statement sta = conn.createStatement();
		
		String Sql = "DECLARE @acode5 char(13) = '" + acode
				+ "' SELECT DISTINCT MT13.Acode , MT13.PntTrims, PNT01.clrLookupCode AS Colour_Code, PNT01.VarCode AS Variation,PNT01.clrShortDesc AS Color_Description_Short, PNT01.clrLongDesc AS Color_Description_Long, PNT05.clrTableCode, [Exterior/Interior/Extra] = (CASE WHEN PNT01.clrAreaCode = 'D1C' THEN 'Exterior' WHEN PNT01.clrAreaCode = 'D3C' THEN 'Extra'	ELSE 'Interior' END) FROM CADA.dbo.MT13_PaintTableAttach MT13 INNER JOIN PNT05_PaintTables PNT05 ON MT13.clrTableCode = PNT05.clrTableCode INNER JOIN PNT01_ColourLookup PNT01 ON PNT01.clrKeyValue = PNT05.clrKeyValue AND PNT01.clrAreaCode = PNT05.clrAreaCode WHERE acode = Substring(@acode5,1,11) AND (PntTrims LIKE '%' + Substring(@acode5,12,1) + '%' OR PntTrims LIKE '*') ORDER BY PNT05.clrTableCode, [Exterior/Interior/Extra] DESC";

		String Acode = "";
		String PntTrims = "";
		String Colour_Code = "";
		String Variation = "";
		String Color_Description_Short = "";
		String Color_Description_Long = "";
		String clrTableCode = "";
		String Exterior_Interior_Extra = "";
		int wSize = titleStringPaitInfo.length;
		String[] paintInfoValue = new String[wSize];

		ResultSet rs = sta.executeQuery(Sql);
		int icolumn = rs.getRow();
		while (rs.next()) {
			icolumn = rs.getRow();
			Acode = rs.getString("Acode");

			PntTrims = rs.getString("PntTrims");
			Colour_Code = rs.getString("Colour_Code");
			Variation = rs.getString("Variation");
			Color_Description_Short = rs.getString("Color_Description_Short");
			Color_Description_Long = rs.getString("Color_Description_Long");
			clrTableCode = rs.getString("clrTableCode");
			Exterior_Interior_Extra = rs.getString("Exterior/Interior/Extra");

			System.out.println("Row =" + icolumn);
			System.out.println("Acode = " + rs.getString("Acode") + " PntTrims = " + rs.getString("PntTrims")
					+ " Colour_Code = " + rs.getString("Colour_Code") + " Variation = " + rs.getString("Variation")
					+ " Color_Description_Short = " + rs.getString("Color_Description_Short")
					+ " Color_Description_Long = " + rs.getString("Color_Description_Long") + " clrTableCode = "
					+ rs.getString("clrTableCode") + " Exterior_Interior_Extra = "
					+ rs.getString("Exterior/Interior/Extra"));

			paintInfoValue[0] = Integer.toString(icolumn);
			paintInfoValue[1] = Acode;
			paintInfoValue[2] = PntTrims;
			paintInfoValue[3] = Colour_Code;
			paintInfoValue[4] = Variation;
			paintInfoValue[5] = Color_Description_Short;
			paintInfoValue[6] = Color_Description_Long;
			paintInfoValue[7] = clrTableCode;
			paintInfoValue[8] = Exterior_Interior_Extra;

			cPP.com_libs.writeToSheet(savePathFile, paintInfoValue);
		}

		if (icolumn == 1) {
			System.out.println("One result returns\n");
			// rs.getString("VehGUID");
			// String vGUID = rs.getString("VehGUID");
		} else {
			System.out.println("Multiple results return\n");
		}
		rs.close();
		sta.close();
		conn.close();

	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		String SavePathFile = "C:\\1\\Eclipse\\Test Results\\CADA\\CADA_DB_PaintInformationByAcode_Return.xls";

		String[] titleStringPaitInfo = { "S/N", "Acode:", "PntTrims:", "Colour_Code:", "Variation:",
				"Color_Description_Short:", "Color_Description_Long:", "clrTableCode:", "Exterior/Interior/Extra:" };

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
			getThePaintInformationByAcode(acode, serverName, dbName, userName, password, SavePathFile,
					titleStringPaitInfo);
		}

		System.out.println("The End!");
	}

}
