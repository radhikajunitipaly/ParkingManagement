package parkingManagement.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import parkingManagement.model.ParkingArea;
import parkingManagement.util.*;

public class ParkingspotDao {
	
	static SQLConnection sqlconnection = SQLConnection.getInstance();

	public ArrayList<ParkingArea> getParkingAreaList(ParkingArea parkingArea) {
		ArrayList<ParkingArea> parkingList = new ArrayList<ParkingArea>();
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
	try {
		stmt = conn.createStatement();
		String queryString = null;
		if (parkingArea.getParkingtype().equals("Premium"))
			queryString = "select * from parkingarea where parkingArea_name='" +parkingArea.getParkingarea_name()+ "' and"
				+ " parkingtype in  ('" +parkingArea.getParkingtype() + "', 'Midrange', 'Basic') ";
		else if (parkingArea.getParkingtype().equals("Midrange"))
			queryString = "select * from parkingarea where parkingArea_name='" +parkingArea.getParkingarea_name()+ "' and"
				+ " parkingtype in ('" +parkingArea.getParkingtype() + "', 'Basic') ";
		else
			queryString = "select * from parkingarea where parkingArea_name='" +parkingArea.getParkingarea_name()+ "' and"
					+ " parkingtype = '" +parkingArea.getParkingtype() + "' ";
		
		ResultSet parkingResultSet = stmt.executeQuery(queryString);
			
		while (parkingResultSet.next()) {
			ParkingArea parkingAreaFromdb = new ParkingArea(); 
			parkingAreaFromdb.setParkingarea_id(parkingResultSet.getInt("parkingarea_id"));
			parkingAreaFromdb.setParkingarea_name(parkingResultSet.getString("parkingarea_name"));
			parkingAreaFromdb.setParkingtype(parkingResultSet.getString("parkingtype"));
			parkingAreaFromdb.setCapacity(parkingResultSet.getInt("capacity"));
			parkingAreaFromdb.setFloor(parkingResultSet.getInt("floor")); 
			parkingList.add(parkingAreaFromdb);	
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			if(conn!=null)
				conn.close();
			if(stmt!=null)
				stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		};
	}
	return parkingList;

	}
}
