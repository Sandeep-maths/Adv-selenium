package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class ReadDataFromDataBase {
	public static void main(String[] args) throws SQLException {

		// step 1 create Driver instance
		// Driver db = new Driver();

		// step 2 register to jdbc driver
		// DriverManager.registerDriver(db);

		// step 1 & 2 is not required for selenium vesrion 4.6

		// step 3 establish jdbc connection

		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/advsel", "root", "root");

		// step 4 create statement
		Statement stat = connection.createStatement();

		// step 5 execute query to fetch data

		ResultSet res = stat.executeQuery("select * from student;");

		while (res.next())

		{
			System.out.println(res.getInt("sid") + "\t" + res.getString("snmae") + "\t" + res.getString("phno") + "\t"
					+ res.getString("course"));
		}

		// step 6 close database
		connection.close();

	}
}
