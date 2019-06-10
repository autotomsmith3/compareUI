package DealerPortal;

import java.sql.DriverManager;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.sql.ResultSet;

public class JDBCconnect {
	public static void getJDBCconnect(String serverName, String dbName, String userName, String password)
			throws SQLException, ClassNotFoundException {
		// Loading the required JDBC Driver class
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		// Creating a connection to the database
		// Connection conn = DriverManager.getConnection(
		// "jdbc:sqlserver://LNOC-Q13V-MSQ1.autodata.org;user=VDVIWebServicesUserQA;password=HDuMy873JRFpkkU9;database=VDVI_Master");
		Connection conn = DriverManager.getConnection("jdbc:sqlserver://" + serverName + ";user=" + userName
				+ ";password=" + password + ";database=" + dbName);

		// getConnection("jdbc:sqlserver://LNOC-Q13V-MSQ1.autodata.org;user=VDVIWebServicesUserQA;password=HDuMy873JRFpkkU9;database=VDVI_Master")

		// Executing SQL query and fetching the result
		Statement st = conn.createStatement();
		String sqlStr = "select dt01.DlrCode,vt01.VIN, vt01.VehGUID from DT01_Dealer as dt01 inner join VT01_DealerVehicles as vt01 on DT01.DlrGUID=VT01.DlrGUID where vt01.VIN=\'"
				+ "1GCGSCE30G1288863" + "\' and dt01.DlrCode=\'" + "165673" + "\'"; // QA=158325, DEV =165673
		ResultSet rs = st.executeQuery(sqlStr);
		while (rs.next()) {
			System.out.println(rs.getString("VehGUID"));
		}
	}

	public static void testVehGUID() throws ClassNotFoundException, SQLException {

		Properties prop = new Properties();
		// testprop.load(new FileInputStream("data/autopxConf.properties"));
		try {
			prop.load(AUTOpxController.class.getClassLoader().getResourceAsStream("data/autopxConf.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String env = prop.getProperty("AUTOpx.environment");

		// String accountPS = prop.getProperty(env + ".AllProdPassword");

		String serverName = prop.getProperty(env + ".serverName");
		String dbName = prop.getProperty(env + ".dbName");
		String userName = prop.getProperty(env + ".userName");
		String password = prop.getProperty(env + ".password");

		JDBCconnect JC = new JDBCconnect();

		JC.getJDBCconnect(serverName, dbName, userName, password);

	}

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		// Loading the required JDBC Driver class
		String serverName, dbName, userName, password;
		serverName = "LNOC-Q13V-MSQ2.autodata.org";
		dbName = "VDVI_Master";
		userName = "VDVIWebServicesUserQA";
		password = "HDuMy873JRFpkkU9";
		getJDBCconnect(serverName, dbName, userName, password);
		
		//User properties file to get all info
		testVehGUID();

		System.out.println("Complete!!!");

	}
}