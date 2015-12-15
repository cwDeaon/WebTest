package com.cwDeaon.myweb.test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;


public class DataBaseUtil {
	private static DataBaseUtil dbUtil;
	private String driver;
	private String url;
	private String username;
	private String password;
	
	private static String FILE_NAME="database.properties";
	
	private void init(){
		try{
			InputStream in = getClass().getResourceAsStream(FILE_NAME);
			Properties props = new Properties();
			
			props.load(in);
			in.close();
			driver = props.getProperty("jdbc.driver");
			url = props.getProperty("jdbc.url");
			username = props.getProperty("jdbc.username");
			password = props.getProperty("jdbc.password");
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	private DataBaseUtil(){
		init();
	}
	
	public static DataBaseUtil getInstance(){
		if(dbUtil==null){
			dbUtil=new DataBaseUtil();
		}
		return dbUtil;
	}
	
	public Connection getConnection(){
		Connection conn = null;
		try{
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
		}catch(SQLException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		return conn;
	}
	
	public void DBClose(Connection conn,PreparedStatement ps,ResultSet res){
		try {
			if(res!=null)
				res.close();
			if(ps!=null)
				ps.close();
			if(conn!=null)
				conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
