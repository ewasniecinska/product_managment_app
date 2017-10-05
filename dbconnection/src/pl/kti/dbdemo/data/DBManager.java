package pl.kti.dbdemo.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
	public static String _host = "sql8.freesqldatabase.com";
	public static String _port = "3306";
	public static String _user = "sql8167592";
	public static String _pass = "wwst5vGbph";
	public static String _db = "sql8167592";
	
	private static Connection connection;
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		if (connection == null) {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://" + _host + ":" + _port + "/" + _db + "";
			connection = DriverManager.getConnection(url , _user, _pass);
		}
		return connection;
	}
}
