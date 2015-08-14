package pl.crawler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CrawlerDBOperations {

	private static final String className = "com.mysql.jdbc.Driver";
	private static final String dataBaseAdress = "jdbc:mysql://localhost:3306/crawler";
	private static final String dataBaseLogin = "root";
	private static final String dataBasePassword = "";

	public Connection conn = null;

	public CrawlerDBOperations(){

		try {
			conn = getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws Exception {
		Connection connn = null;
		try {
			Class.forName(className);
			connn = DriverManager.getConnection(dataBaseAdress,
					dataBaseLogin, dataBasePassword);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return connn;
	}

	public ResultSet runSql(String sql) throws SQLException {
		Statement sta = conn.createStatement();
		return sta.executeQuery(sql);
	}

	public boolean runSql2(String sql) throws SQLException {
		Statement sta = conn.createStatement();
		return sta.execute(sql);
	}

	@Override
	protected void finalize() throws Throwable {
		if (conn != null || !conn.isClosed()) {
			conn.close();
		}
	}

}
