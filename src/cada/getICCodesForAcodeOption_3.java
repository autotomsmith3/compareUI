package cada;
//not working yet
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

public class getICCodesForAcodeOption_03 {

	public static void getICCodesForAcodeOption(String acode, String option, String serverName, String dbName,
			String userName, String password, String savePathFile, String[] titleStringPaitInfo)
			throws ClassNotFoundException, SQLException, IOException {

		cPP.com_libs.writeTitle(savePathFile, titleStringPaitInfo);

		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

		Connection conn = DriverManager.getConnection("jdbc:sqlserver://" + serverName + ";user=" + userName
				+ ";password=" + password + ";database=" + dbName + "");

		System.out.println("test");

		Statement sta = conn.createStatement();

		String Sql = "DECLARE @acode1 char(13) = 'USC50CHT279C0'   DECLARE @optcode varchar(10) = 'L83'     DECLARE @ICC table   (Acode varchar(11),  TrimCode varchar(50),  RevCode numeric(13,3),  OptCode varchar(12),  OptVar char(2),  OptOrder INT,  ICC_Code varchar(5),  Item varchar(2),  Value varchar(200),  ICCPicture char(20),  Description varchar(200))      INSERT INTO @ICC  SELECT OT01.Acode, OT02.OptionTrims, OT01.revcode, OT01.OptCode, OT02.OptVar, OT13.OptOrder  , (OT05.ICCSectionCode+OT05.ICCGroupCode+OT05.ICCFamilyCode+OT05.ICCSubFamCode) AS ICC_Code  , OT05.ICCItemCode AS Item  , (CASE WHEN OT05.ICCItemValue IS NOT NULL THEN OT05.ICCItemValue ELSE IC07.ICCDesc END) AS Value  , IC04.ICCPicture  , IC072.ICCDesc AS Description  FROM OT02_Options OT02  INNER JOIN OT01_BaseOptions OT01 ON OT02.OptUID = OT01.OptUID and OT02.Acode = OT01.Acode AND OT02.RevCode = OT01.RevCode  INNER JOIN (select Acode, max(RevCode) Revcode, OptUID, OptVar from OT02_Options group by Acode, OptUID, OptVar) OPT ON OT02.Acode = OPT.Acode AND OT02.RevCode = OPT.RevCode AND OT02.OptUID = OPT.OptUID AND OT02.OptVar = OPT.OptVar  LEFT JOIN OT11_LOGIC OT11 ON OPT.Acode = OT11.Acode AND OPT.OptUID = OT11.OptUID AND OPT.Revcode = OT11.RevCode AND OPT.OptVar = OT11.OptVar  LEFT JOIN OT13_OptionUID OT13 ON OT02.Acode = OT13.Acode AND OT02.OptUID = OT13.OptUID  LEFT JOIN OT15_OptionClusters OT15 ON OPT.Acode = OT15.Acode AND OPT.OptUID = OT15.OptUID AND OPT.Revcode = OT15.RevCode  LEFT JOIN OT18_VehClusterList OT18 ON OPT.Acode = OT18.Acode AND OT15.OptClusterCode = OT18.OptClusterCode  LEFT JOIN OT04_OptClusterList OT04 ON OT15.OptClusterCode = OT04.OptClusterCode  LEFT JOIN OT05_OptionalICC OT05 ON OPT.Acode = OT05.Acode AND OPT.OptUID = OT05.OptUID AND OPT.Revcode = OT05.RevCode AND OPT.OptVar = OT05.OptVar  JOIN IC05_ICAttribute (NOLOCK) IC05 ON OT05.ICCSectionCode = IC05.ICCSectionCode  	AND OT05.ICCGroupCode = IC05.ICCGroupCode AND OT05.ICCFamilyCode = IC05.ICCFamilyCode AND OT05.ICCSubFamCode = IC05.ICCSubFamCode AND OT05.ICCItemCode = IC05.ICCItemCode  JOIN IC07_ICCDescriptions (NOLOCK) IC07 ON IC05.ICCDescCode = IC07.ICCDescCode AND IC07.LngCode = 'EN'  JOIN IC04_ICComponent (NOLOCK) IC04 ON OT05.ICCSectionCode = IC04.ICCSectionCode  	AND OT05.ICCGroupCode = IC04.ICCGroupCode AND OT05.ICCFamilyCode = IC04.ICCFamilyCode AND OT05.ICCSubFamCode = IC04.ICCSubFamCode  JOIN IC07_ICCDescriptions (NOLOCK) IC072 ON IC04.ICCDescCode = IC072.ICCDescCode AND IC072.LngCode = 'EN'  WHERE OT02.Acode = Substring(@acode1,1,11)   AND (OptionTrims LIKE (Substring(@acode1,12,1) + '%') OR OptionTrims LIKE '*')  AND OptCode = @optcode  AND OT01.DELETED = 'F'  UNION ALL  SELECT OT01.Acode, OT02.OptionTrims, OT01.revcode, OT01.OptCode, OT02.OptVar, OT13.OptOrder  , (PT05.ICCSectionCode+PT05.ICCGroupCode+PT05.ICCFamilyCode+PT05.ICCSubFamCode) AS ICC_Code  , PT05.ICCItemCode AS Item  , (CASE WHEN PT05.ICCItemValue IS NOT NULL THEN PT05.ICCItemValue ELSE IC07.ICCDesc END) AS Value  , IC04.ICCPicture  , IC072.ICCDesc AS Description  FROM OT02_Options OT02  INNER JOIN OT01_BaseOptions OT01 ON OT02.OptUID = OT01.OptUID and OT02.Acode = OT01.Acode AND OT02.RevCode = OT01.RevCode  INNER JOIN (select Acode, max(RevCode) Revcode, OptUID, OptVar from OT02_Options group by Acode, OptUID, OptVar) OPT ON OT02.Acode = OPT.Acode AND OT02.RevCode = OPT.RevCode AND OT02.OptUID = OPT.OptUID AND OT02.OptVar = OPT.OptVar  LEFT JOIN OT11_LOGIC OT11 ON OPT.Acode = OT11.Acode AND OPT.OptUID = OT11.OptUID AND OPT.Revcode = OT11.RevCode AND OPT.OptVar = OT11.OptVar  LEFT JOIN OT13_OptionUID OT13 ON OT02.Acode = OT13.Acode AND OT02.OptUID = OT13.OptUID  LEFT JOIN OT15_OptionClusters OT15 ON OPT.Acode = OT15.Acode AND OPT.OptUID = OT15.OptUID AND OPT.Revcode = OT15.RevCode  LEFT JOIN OT18_VehClusterList OT18 ON OPT.Acode = OT18.Acode AND OT15.OptClusterCode = OT18.OptClusterCode  LEFT JOIN OT04_OptClusterList OT04 ON OT15.OptClusterCode = OT04.OptClusterCode  LEFT JOIN OT12_OptionalEngTrans OT12 ON OPT.Acode = OT12.Acode AND OPT.OptUID = OT12.OptUID AND OPT.Revcode = OT12.RevCode AND OPT.OptVar = OT12.OptVar   JOIN PT05_EngineContents (NOLOCK) PT05 ON OT12.EngCode = PT05.EngCode AND OT12.CountryCode = PT05.CountryCode AND OT12.YearCode = PT05.YearCode AND OT12.MfgCode = PT05.MfgCode   JOIN IC05_ICAttribute (NOLOCK) IC05 ON PT05.ICCSectionCode = IC05.ICCSectionCode  	AND PT05.ICCGroupCode = IC05.ICCGroupCode AND PT05.ICCFamilyCode = IC05.ICCFamilyCode AND PT05.ICCSubFamCode = IC05.ICCSubFamCode AND PT05.ICCItemCode = IC05.ICCItemCode  JOIN IC07_ICCDescriptions (NOLOCK) IC07 ON IC05.ICCDescCode = IC07.ICCDescCode AND IC07.LngCode = 'EN'  JOIN IC04_ICComponent (NOLOCK) IC04 ON PT05.ICCSectionCode = IC04.ICCSectionCode  	AND PT05.ICCGroupCode = IC04.ICCGroupCode AND PT05.ICCFamilyCode = IC04.ICCFamilyCode AND PT05.ICCSubFamCode = IC04.ICCSubFamCode  JOIN IC07_ICCDescriptions (NOLOCK) IC072 ON IC04.ICCDescCode = IC072.ICCDescCode AND IC072.LngCode = 'EN'  WHERE OT02.Acode = Substring(@acode1,1,11)   AND (OptionTrims LIKE (Substring(@acode1,12,1) + '%') OR OptionTrims LIKE '*')  AND OptCode = @optcode  AND OT01.DELETED = 'F'  UNION ALL  SELECT OT01.Acode, OT02.OptionTrims, OT01.revcode, OT01.OptCode, OT02.OptVar, OT13.OptOrder  , (PT06.ICCSectionCode+PT06.ICCGroupCode+PT06.ICCFamilyCode+PT06.ICCSubFamCode) AS ICC_Code  , PT06.ICCItemCode AS Item  , (CASE WHEN PT06.ICCItemValue IS NOT NULL THEN PT06.ICCItemValue ELSE IC07.ICCDesc END) AS Value  , IC04.ICCPicture  , IC072.ICCDesc AS Description  FROM OT02_Options OT02  INNER JOIN OT01_BaseOptions OT01 ON OT02.OptUID = OT01.OptUID and OT02.Acode = OT01.Acode AND OT02.RevCode = OT01.RevCode  INNER JOIN (select Acode, max(RevCode) Revcode, OptUID, OptVar from OT02_Options group by Acode, OptUID, OptVar) OPT ON OT02.Acode = OPT.Acode AND OT02.RevCode = OPT.RevCode AND OT02.OptUID = OPT.OptUID AND OT02.OptVar = OPT.OptVar  LEFT JOIN OT11_LOGIC OT11 ON OPT.Acode = OT11.Acode AND OPT.OptUID = OT11.OptUID AND OPT.Revcode = OT11.RevCode AND OPT.OptVar = OT11.OptVar  LEFT JOIN OT13_OptionUID OT13 ON OT02.Acode = OT13.Acode AND OT02.OptUID = OT13.OptUID  LEFT JOIN OT15_OptionClusters OT15 ON OPT.Acode = OT15.Acode AND OPT.OptUID = OT15.OptUID AND OPT.Revcode = OT15.RevCode  LEFT JOIN OT18_VehClusterList OT18 ON OPT.Acode = OT18.Acode AND OT15.OptClusterCode = OT18.OptClusterCode  LEFT JOIN OT04_OptClusterList OT04 ON OT15.OptClusterCode = OT04.OptClusterCode  LEFT JOIN OT12_OptionalEngTrans OT12 ON OPT.Acode = OT12.Acode AND OPT.OptUID = OT12.OptUID AND OPT.Revcode = OT12.RevCode AND OPT.OptVar = OT12.OptVar   JOIN PT06_TransContents (NOLOCK) PT06 ON OT12.TransCode = PT06.TransCode AND OT12.CountryCode = PT06.CountryCode AND OT12.YearCode = PT06.YearCode AND OT12.MfgCode = PT06.MfgCode   JOIN IC05_ICAttribute (NOLOCK) IC05 ON PT06.ICCSectionCode = IC05.ICCSectionCode  	AND PT06.ICCGroupCode = IC05.ICCGroupCode AND PT06.ICCFamilyCode = IC05.ICCFamilyCode AND PT06.ICCSubFamCode = IC05.ICCSubFamCode AND PT06.ICCItemCode = IC05.ICCItemCode  JOIN IC07_ICCDescriptions (NOLOCK) IC07 ON IC05.ICCDescCode = IC07.ICCDescCode AND IC07.LngCode = 'EN'  JOIN IC04_ICComponent (NOLOCK) IC04 ON PT06.ICCSectionCode = IC04.ICCSectionCode  	AND PT06.ICCGroupCode = IC04.ICCGroupCode AND PT06.ICCFamilyCode = IC04.ICCFamilyCode AND PT06.ICCSubFamCode = IC04.ICCSubFamCode  JOIN IC07_ICCDescriptions (NOLOCK) IC072 ON IC04.ICCDescCode = IC072.ICCDescCode AND IC072.LngCode = 'EN'  WHERE OT02.Acode = Substring(@acode1,1,11)   AND (OptionTrims LIKE (Substring(@acode1,12,1) + '%') OR OptionTrims LIKE '*')  AND OptCode = @optcode  AND OT01.DELETED = 'F'  ORDER BY OptVar, ICC_Code;  SELECT *  FROM @ICC WHERE Value NOT LIKE '%X' --these lines exclude empty values, can add new ones as you find them AND Value NOT LIKE 'OOOOOOOOO' AND Value NOT LIKE '000000000' AND Value NOT LIKE 'OOOOOOOO' AND Value <> 'Unknown / Uncertain' ";
		String Sql1 = "SELECT *  FROM @ICC WHERE Value NOT LIKE '%X' --these lines exclude empty values, can add new ones as you find them AND Value NOT LIKE 'OOOOOOOOO' AND Value NOT LIKE '000000000' AND Value NOT LIKE 'OOOOOOOO' AND Value <> 'Unknown / Uncertain' ";

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

//		ResultSet rs = sta.executeQuery(Sql);
		int  rs1 = sta.executeUpdate(Sql);
		PreparedStatement pstmt = conn.prepareStatement(Sql1);
		ResultSet rstest = pstmt.executeQuery(Sql);  //not working
		
		
		
//		ResultSet rs = sta.executeQuery(Sql1);

//		int icolumn = rs.getRow();
//		while (rs.next()) {
//			icolumn = rs.getRow();
//			Acode = rs.getString("Acode");
//
//			TrimCode = rs.getString("TrimCode");
//			RevCode = rs.getString("RevCode");
//			MSRP = rs.getString("MSRP");
//			Invoice = rs.getString("Invoice");
//			HoldBack = rs.getString("HoldBack");
//			Net = rs.getString("Net");
//			Financing = rs.getString("Financing");
//			DoubleNet = rs.getString("DoubleNet");
//			Advertising = rs.getString("Advertising");
//			TripleNet = rs.getString("TripleNet");
//
//			System.out.println("Row =" + icolumn);
//			System.out.println("Acode = " + rs.getString("Acode") + " TrimCode = " + rs.getString("TrimCode")
//					+ " RevCode = " + rs.getString("RevCode") + " MSRP = " + rs.getString("MSRP") + " Invoice = "
//					+ rs.getString("Invoice") + " HoldBack = " + rs.getString("HoldBack") + " Net = "
//					+ rs.getString("Net") + " Financing = " + rs.getString("Financing") + " DoubleNet = "
//					+ rs.getString("DoubleNet") + " Advertising = " + rs.getString("Advertising") + " TripleNet = "
//					+ rs.getString("TripleNet"));
//
//			// Acode TrimCode RevCode MSRP Invoice HoldBack Net Financing DoubleNet Advertising TripleNet
//
//			infoValue[0] = Integer.toString(icolumn);
//			infoValue[1] = Acode;
//			infoValue[2] = TrimCode;
//			infoValue[3] = RevCode;
//			infoValue[4] = MSRP;
//			infoValue[5] = Invoice;
//			infoValue[6] = HoldBack;
//			infoValue[7] = Net;
//			infoValue[8] = Financing;
//			infoValue[9] = DoubleNet;
//			infoValue[10] = Advertising;
//			infoValue[11] = TripleNet;
//
//			cPP.com_libs.writeToSheet(savePathFile, infoValue);
//		}
//
//		if (icolumn == 1) {
//			System.out.println("One result returns\n");
//
//		} else {
//			System.out.println("Multiple results return\n");
//		}
//		rs.close();
//		sta.close();
//		conn.close();

	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		String SavePathFile = "C:\\1\\Eclipse\\Test Results\\CADA\\CADA_DB_getICCodesForAcodeOption_03_Return.xls";

		String[] titleStringInfo = { "S/N", "Acode:", "TrimCode:", "RevCode:", "MSRP:", "Invoice:", "HoldBack:", "Net:",
				"Financing:", "DoubleNet:", "Advertising:", "TripleNet:" };

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

		// String[] acodes = com_libs.fetchOneDemArrayFromPropFile("acodes", prop);
		// String[] options= com_libs.fetchOneDemArrayFromPropFile("optCodes", prop);

		String[][] acodeoptions = com_libs.fetchArrayFromPropFile("acodes", prop);

		// for (String acode : acodes) {
		// getICCodesForAcodeOption(acode, opt, serverName, dbName, userName, password, SavePathFile, titleStringInfo);
		// }
		int c = 0;
		String opt = "";
		for (int i = 0; i < acodeoptions.length; i++) {
			String acode = acodeoptions[i][0];
			int opts = acodeoptions[i].length;
			for (int j = 1; j < opts; j++) {
				c++;
				opt = acodeoptions[i][j];
				getICCodesForAcodeOption(acode, opt, serverName, dbName, userName, password, SavePathFile,
						titleStringInfo);

			}

		}

		System.out.println("The End!");
	}

}
