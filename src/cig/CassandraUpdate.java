package cig;

/**
 * Get the id in first CQL and use that id to update the lastmodified value on both Colormappings and Vehiclemappings.
 * Works fine on February 02, 2018
 */
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

public class CassandraUpdate {
	public static void main(String[] args) {
		System.out.println("Started!");
		String serverIp = "10.120.10.99";
		String keyspace = "cigmapping_Lucas";
		CassandraUpdate connection;
		Cluster cluster = Cluster.builder().addContactPoints(serverIp).build();
		Session session = cluster.connect(keyspace);
		// cigmapping_color_Lucas total=13: "381489","381490","381491","381492","381493","381494","381495","381496","389796","389798","396984","396985","396986"
		// cigmapping_vehicle_Lucas=13: "383791","389160","388039","388050","388074","388085","385061","389934","388877","388035","385389","385655","385663"
		// cigmapping_Lucas total=26: "381489","381490","381491","381492","381493","381494","381495","381496","389796","389798","396984","396985","396986","383791","389160","388039","388050","388074","388085","385061","389934","388877","388035","385389","385655","385663"
		// String[] style = {"381489","381490","381491","381492","381493","381494","381495","381496","389796","389798","396984","396985","396986","383791","389160","388039","388050","388074","388085","385061","389934","388877","388035","385389","385655","385663"};// { "389544", "387896","389544" };
		// USC70FOT11 styleids
		String[] style = { "387900", "381489" };// {"387896","387897","387898","387899","387900","387901","387902","387903","387904","387905","387906","387907","387908","387909","387910","387911","387912","387913","387914","387915","387916","387917","387918","387919","387920","387921","387922","387923","387924","387925","387926","387927","387928","387929","387930","387931","387932","387933","387934","387935","387936","387937","388260","388261"};

		UUID id;
		String DateAndTimeString = "2008-11-29 01:46:54+0000";

		int styleLength = style.length;

		// ***************************Colormappings**************************
		for (int i = 0; i < styleLength; i++) {
			String cqlStatement = "select * from colormappings where styleid=" + style[i];
			ResultSet results = session.execute(cqlStatement);

			for (Row row : results) {
				// Integer.toString(currentDynamicValue.getJSONObject(i).getInt("groupId"));
				id = row.getUUID("id");
				// String identify=id.toString();

				// ***************************Update Colormappings**************************
				// UPDATE colormappings SET lastmodified='2019-11-29 01:46:54+0000' where id=fced0aa6-0bd7-44a3-907c-cda97fb3515f;
				String cqlStatementUpdate1 = "UPDATE colormappings SET lastmodified='" + DateAndTimeString
						+ "' where id=" + id + ";";
				ResultSet result = session.execute(cqlStatementUpdate1);
				System.out.println("Updating " + style[i]);
			}

		}
		// ***************************Colormappings**************************

		// ***************************Vehiclemappings**************************
		for (int i = 0; i < styleLength; i++) {
			String cqlStatement = "select * from vehiclemappings where styleid=" + style[i];
			ResultSet results = session.execute(cqlStatement);
			for (Row row : results) {
				// Integer.toString(currentDynamicValue.getJSONObject(i).getInt("groupId"));
				id = row.getUUID("id");
				// String identify=id.toString();

				// ***************************Update Colormappings**************************
				// UPDATE colormappings SET lastmodified='2019-11-29 01:46:54+0000' where id=fced0aa6-0bd7-44a3-907c-cda97fb3515f;
				String cqlStatementUpdate2 = "UPDATE vehiclemappings SET lastmodified='" + DateAndTimeString
						+ "' where id=" + id + ";";
				ResultSet result = session.execute(cqlStatementUpdate2);
				System.out.println("Updating " + style[i]);
			}

		}
		// ***************************Vehiclemappings**************************

		cluster.close();
		System.out.println();
		System.out.println("Complete!");
	}
}