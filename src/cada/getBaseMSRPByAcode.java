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

public class getBaseMSRPByAcode {

	public static void getBaseMSRPPriceByAcode(String acode, String serverName, String dbName, String userName,
			String password, String savePathFile, String[] titleStringPaitInfo)
			throws ClassNotFoundException, SQLException, IOException {

		cPP.com_libs.writeTitle(savePathFile, titleStringPaitInfo);

		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

		Connection conn = DriverManager.getConnection("jdbc:sqlserver://" + serverName + ";user=" + userName
				+ ";password=" + password + ";database=" + dbName + "");

		System.out.println("test");

		Statement sta = conn.createStatement();
		
		String Sql = "DECLARE @acode1 char(113) = '" + acode
				+ "' select distinct VT06.Acode, VT06.TrimCode, VT06.RevCode, MSRP = (select V6.Price from VT06_BasePricing V6 where V6.Acode = VT06.Acode and V6.TrimCode = VT06.TrimCode and V6.RevCode = VT06.RevCode and  V6.PriceTypeCode = 1 ) , Invoice = (select V6.Price from VT06_BasePricing V6 where V6.Acode = VT06.Acode and V6.TrimCode = VT06.TrimCode and V6.RevCode = VT06.RevCode and  V6.PriceTypeCode = 2 ) , HoldBack = (select V6.Price from VT06_BasePricing V6 where V6.Acode = VT06.Acode and V6.TrimCode = VT06.TrimCode and V6.RevCode = VT06.RevCode and  V6.PriceTypeCode = 4 ) , Net = (select V6.Price from VT06_BasePricing V6 where V6.Acode = VT06.Acode and V6.TrimCode = VT06.TrimCode and V6.RevCode = VT06.RevCode and  V6.PriceTypeCode = 3 ) , Financing = (select V6.Price from VT06_BasePricing V6 where V6.Acode = VT06.Acode and V6.TrimCode = VT06.TrimCode and V6.RevCode = VT06.RevCode and  V6.PriceTypeCode = 6 ) , DoubleNet = (select V6.Price from VT06_BasePricing V6 where V6.Acode = VT06.Acode and V6.TrimCode = VT06.TrimCode and V6.RevCode = VT06.RevCode and  V6.PriceTypeCode = 14 ) , Advertising = (select V6.Price from VT06_BasePricing V6 where V6.Acode = VT06.Acode and V6.TrimCode = VT06.TrimCode and V6.RevCode = VT06.RevCode and  V6.PriceTypeCode = 5 ) , TripleNet = (select V6.Price from VT06_BasePricing V6 where V6.Acode = VT06.Acode and V6.TrimCode = VT06.TrimCode and V6.RevCode = VT06.RevCode and  V6.PriceTypeCode = 14 )-(select V6.Price from VT06_BasePricing V6 where V6.Acode = VT06.Acode and V6.TrimCode = VT06.TrimCode and V6.RevCode = VT06.RevCode and  V6.PriceTypeCode = 5 ) from VT06_BasePricing VT06 join CT23_PriceTypes CT23 on VT06.PriceTypeCode = CT23.PriceTypeCode inner join( select Acode, TrimCode, max(RevCode) RevCode     from VT06_BasePricing     group by Acode, TrimCode) VT on VT06.Acode = VT.Acode and VT06.RevCode = VT.Revcode and VT06.TrimCode = VT.TrimCode where VT06.Acode = Substring(@acode1,1,11) AND VT06.TrimCode = Substring(@acode1,12,2) order by VT06.Acode, VT06.TrimCode";

		String Acode = "";
		String TrimCode = "";
		String RevCode = "";
		String MSRP = "";
		String Invoice = "";
		String HoldBack = "";
		String Net = "";
		String Financing = "";
		String DoubleNet = "";
		String Advertising = "";
		String TripleNet = "";
		// String Exterior_Interior_Extra = "";

		int wSize = titleStringPaitInfo.length;
		String[] infoValue = new String[wSize];

		ResultSet rs = sta.executeQuery(Sql);
		int icolumn = rs.getRow();
		while (rs.next()) {
			icolumn = rs.getRow();
			Acode = rs.getString("Acode");

			TrimCode = rs.getString("TrimCode");
			RevCode = rs.getString("RevCode");
			MSRP = rs.getString("MSRP");
			Invoice = rs.getString("Invoice");
			HoldBack = rs.getString("HoldBack");
			Net = rs.getString("Net");
			Financing = rs.getString("Financing");
			DoubleNet = rs.getString("DoubleNet");
			Advertising = rs.getString("Advertising");
			TripleNet = rs.getString("TripleNet");

			System.out.println("Row =" + icolumn);
			System.out.println("Acode = " + rs.getString("Acode") + " TrimCode = " + rs.getString("TrimCode")
					+ " RevCode = " + rs.getString("RevCode") + " MSRP = " + rs.getString("MSRP") + " Invoice = "
					+ rs.getString("Invoice") + " HoldBack = " + rs.getString("HoldBack") + " Net = "
					+ rs.getString("Net") + " Financing = " + rs.getString("Financing") + " DoubleNet = "
					+ rs.getString("DoubleNet") + " Advertising = " + rs.getString("Advertising") + " TripleNet = "
					+ rs.getString("TripleNet"));

			// Acode TrimCode RevCode MSRP Invoice HoldBack Net Financing DoubleNet Advertising TripleNet

			infoValue[0] = Integer.toString(icolumn);
			infoValue[1] = Acode;
			infoValue[2] = TrimCode;
			infoValue[3] = RevCode;
			infoValue[4] = MSRP;
			infoValue[5] = Invoice;
			infoValue[6] = HoldBack;
			infoValue[7] = Net;
			infoValue[8] = Financing;
			infoValue[9] = DoubleNet;
			infoValue[10] = Advertising;
			infoValue[11] = TripleNet;

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
		String SavePathFile = "C:\\1\\Eclipse\\Test Results\\CADA\\CADA_DB_BaseMSRPByAcode_Return.xls";

		String[] titleStringInfo = { "S/N", "Acode:", "TrimCode:", "RevCode:", "MSRP:", "Invoice:", "HoldBack:",
				"Net:", "Financing:", "DoubleNet:", "Advertising:", "TripleNet:" };

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
			getBaseMSRPPriceByAcode(acode, serverName, dbName, userName, password, SavePathFile, titleStringInfo);
		}

		System.out.println("The End!");
	}

}
