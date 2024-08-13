package genericUtilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataBaseUtility {
	Connection con;
	Statement stat;
	/**
	 * This method use initialize  data base
	 * @param url
	 * @param user
	 * @param pwsd
	 */
	public  void databaseInIt(String url, String user, String pwsd) {
		try {
			con = DriverManager.getConnection(url, user, pwsd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			stat = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * This method raeds data from database column wise
	 * @param query
	 * @param colname
	 * @return List<Object>
	 */
	
	public List<Object> raedDataFromDataBase(String query, String colname)
	{
		ResultSet res = null;
		try {
			res = stat.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ArrayList<Object> list = new ArrayList<Object>();
		
		try {
			while(res.next()) {
				list.add(res.getObject(colname));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
		
	}
	/**
	 * This method is used to modify database
	 * @param query
	 * @return
	 */
	public int modifyDataBase(String query) {
		int res = 0;
		
		try {
			res= stat.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
		
	}
	
	/**
	 * This method closes database connection
	 */
	public void closeDataBase()
	{
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
