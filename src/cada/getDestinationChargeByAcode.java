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

public class getDestinationChargeByAcode {

	public static void getTheDestinationChargeByAcode(String acode, String serverName, String dbName, String userName,
			String password, String savePathFile, String[] titleStringPaitInfo)
			throws ClassNotFoundException, SQLException, IOException {

		cPP.com_libs.writeTitle(savePathFile, titleStringPaitInfo);

		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

		Connection conn = DriverManager.getConnection("jdbc:sqlserver://" + serverName + ";user=" + userName
				+ ";password=" + password + ";database=" + dbName + "");

		System.out.println("test");

		Statement sta = conn.createStatement();
		
		String Sql = "DECLARE @acode1 char(13) = '" + acode
				+ "'  SELECT DISTINCT OT01.Acode, OT01.OptCode, OT10.OptVar , MSRP = (select OT1.Price from CADA..OT10_OptionPricing OT1 where OT1.Acode = OT10.Acode AND OT1.OptUID = OT10.OptUID AND OT1.OptVar = OT10.Optvar AND OT1.RevCode = OT10.RevCode AND OT1.PriceTypeCode = 1 ) , Invoice = (select OT1.Price from CADA..OT10_OptionPricing OT1 where OT1.Acode = OT10.Acode AND OT1.OptUID = OT10.OptUID AND OT1.OptVar = OT10.Optvar AND OT1.RevCode = OT10.RevCode AND OT1.PriceTypeCode = 2 ) , HB = (select OT1.Price from CADA..OT10_OptionPricing OT1 where OT1.Acode = OT10.Acode AND OT1.OptUID = OT10.OptUID AND OT1.OptVar = OT10.Optvar AND OT1.RevCode = OT10.RevCode AND OT1.PriceTypeCode = 4 ) , Net = (select OT1.Price from CADA..OT10_OptionPricing OT1 where OT1.Acode = OT10.Acode AND OT1.OptUID = OT10.OptUID AND OT1.OptVar = OT10.Optvar AND OT1.RevCode = OT10.RevCode AND OT1.PriceTypeCode = 3 ) FROM CADA..OT10_OptionPricing (NOLOCK) OT10 JOIN OT01_BaseOptions (NOLOCK) OT01 ON OT10.OptUID = OT01.OptUID AND OT10.Acode = OT01.Acode AND OT10.RevCode = OT01.RevCode  WHERE OT01.Acode = Substring(@acode1,1,11)  AND OptCode = 'DEST' AND OT01.RevCode = (SELECT MAX(RevCode) FROM OT10_OptionPricing WHERE Acode = OT10.Acode AND OptUID = OT10.OptUID AND OptVar = OT10.OptVar) ORDER BY OT01.OptCode, OT10.OptVar";
		String Acode = "";
		String OptCode = "";
		String OptVar = "";
		String MSRP = "";
		String Invoice = "";
		String HB = "";
		String Net = "";

		int wSize = titleStringPaitInfo.length;
		String[] infoValue = new String[wSize];

		ResultSet rs = sta.executeQuery(Sql);
		int icolumn = rs.getRow();
		while (rs.next()) {
			icolumn = rs.getRow();
			Acode = rs.getString("Acode");

			OptCode = rs.getString("OptCode");
			OptVar = rs.getString("OptVar");
			MSRP = rs.getString("MSRP");
			Invoice = rs.getString("Invoice");
			HB = rs.getString("HB");
			Net = rs.getString("Net");

			System.out.println("Row =" + icolumn);
			System.out.println("Acode = " + rs.getString("Acode") + " OptCode = " + rs.getString("OptCode")
					+ " OptVar = " + rs.getString("OptVar") + " MSRP = " + rs.getString("MSRP") + " Invoice = "
					+ rs.getString("Invoice") + " HB = " + rs.getString("HB") + " Net = " + rs.getString("Net"));

			// Acode TrimCode OptVar MSRP Invoice HB Net Financing DoubleNet Advertising TripleNet

			infoValue[0] = Integer.toString(icolumn);
			infoValue[1] = Acode;
			infoValue[2] = OptCode;
			infoValue[3] = OptVar;
			infoValue[4] = MSRP;
			infoValue[5] = Invoice;
			infoValue[6] = HB;
			infoValue[7] = Net;

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
		String SavePathFile = "C:\\1\\Eclipse\\Test Results\\CADA\\CADA_DB_DestinationChargeByAcode_Return.xls";

		String[] titleStringInfo = { "S/N", "Acode:", "OptCode:", "OptVar:", "MSRP:", "Invoice:", "HB:", "Net:" };

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
			getTheDestinationChargeByAcode(acode, serverName, dbName, userName, password, SavePathFile, titleStringInfo);
		}

		System.out.println("The End!");
	}

}
