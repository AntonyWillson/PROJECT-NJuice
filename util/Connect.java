package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {
	private final String USERNAME = "root";
	private final String PASSWORD = "";
	private final String DATABASE = "njuice";
	private final String HOST = "localhost:3306";
	private final String CONNECTION = String.format("jdbc:mysql://%s/%s", HOST,DATABASE);
	
	public Connection con;
	private Statement st;
	public PreparedStatement pst;
	
	public ResultSet rs;
	public ResultSetMetaData rsm;
	
	public static Connect instance;
	
	private Connect() {
		try {
			con = DriverManager.getConnection(CONNECTION,USERNAME,PASSWORD);
			st = con.createStatement();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connect getInstance() {
		if (instance == null) {
			instance = new Connect();
		}
		return instance;
	}
	
	public ResultSet executeQuery(String query) {
		try {
			rs = st.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public void executeUpdate(String query) {
		try {
			st.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	 public PreparedStatement prepareStatement(String query) throws SQLException {
	        pst = con.prepareStatement(query);
	        return pst;
	    }
	 
	 public int getMaxJuiceId() {
		    int maxId = 0;
		    String query = "SELECT MAX(CAST(SUBSTRING(JuiceID, 3) AS SIGNED)) AS MaxId FROM msjuice";
		    rs = executeQuery(query);
		    try {
		        if (rs.next()) {
		            maxId = rs.getInt("MaxId");
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return maxId;
		}
	 
	 
}
