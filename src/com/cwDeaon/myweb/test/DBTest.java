package com.cwDeaon.myweb.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBTest {

	/**
	 * @param args
	 */
	static Connection conn;
	static Statement sql;
	static ResultSet res;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
//			Class.forName("com.mysql.jdbc.Driver");
//			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","123456");
			DataBaseUtil dbUtil = DataBaseUtil.getInstance();
			conn = dbUtil.getConnection();
			System.out.println(conn);
			
			sql = conn.createStatement();
			res = sql.executeQuery("select * from dbtest;");
			while(res.next()){
				String userseq = res.getString("userseq");
				String username = res.getString("username");
				String password = res.getString("password");
				
				System.out.println(userseq+'/'+username+'/'+password);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	}

}
