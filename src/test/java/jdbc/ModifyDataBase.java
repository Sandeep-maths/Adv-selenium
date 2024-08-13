package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ModifyDataBase {
public static void main(String[] args) throws SQLException {
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/advsel", "root", "root");
	
	Statement st = con.createStatement();
	
	 int res = st.executeUpdate("insert into student(sid,snmae,phno,course) value(6,\"usagi\", 6465453345, \"scram master\")");
	 
	 System.out.println(res);
	 
	 con.close();
	 
}
}
