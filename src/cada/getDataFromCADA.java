package cada;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class getDataFromCADA {

	public static String getDataInfoFromCADA(String ac, String sVin) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection conn = DriverManager
				.getConnection("jdbc:sqlserver://LN-CO-DI-SQL1:1433;user=read-only;password=quba90sont;database=CADA");
		// "jdbc:sqlserver://LNOC-Q13V-MSQ1.autodata.org;user=read-only;password=quba90sont;database=CADA");

		System.out.println("test");

		Statement sta = conn.createStatement();
		String Sql = "SELECT *  FROM MM04_Trim where acode ='"+ac+"'";
		String acode = "";
		ResultSet rs = sta.executeQuery(Sql);
		int icolumn = rs.getRow();
		while (rs.next()) {
			icolumn = rs.getRow();
			acode = rs.getString("acode");
			System.out.println("Row =" + icolumn);
			System.out.println("KeyValue = " + rs.getString("KeyValue") + "  TUID = " + rs.getString("TUID") + "  Acode = " + rs.getString("acode"));
		}
		if (icolumn == 1) {
			System.out.println("One VehGUID\n");
			// rs.getString("VehGUID");
			// String vGUID = rs.getString("VehGUID");
		} else {
			System.out.println("No any VehGUID or more than one\n");
			acode = "error!";
		}
		rs.close();
		sta.close();
		conn.close();
		return acode;
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		String Acode = getDataInfoFromCADA("USC50CHT279C0", "1GTN1LEC3GZ183469");
		System.out.println("Acode=" + Acode);
		System.out.println("Complete!");
	}

}
