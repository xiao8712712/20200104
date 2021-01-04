package com.xiao.sqlservertest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class sqlserver {

	public static void main(String[] args) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://localhost:1433;DatabaseName=USER1";
			String username="sa";
			String password="123456789";
			Connection connection = DriverManager.getConnection(url, username, password);
			if(connection==null) {
				System.out.println("连接失败1!!!!");
				
			}else {
				System.out.println("连接成功");
			}
			Statement stat = connection.createStatement();
			String sql="insert into [1] values('11112','xiali',23)";
			int update = stat.executeUpdate(sql);
			//System.out.println(update);
			String sqsl="SELECT name FROM  master..sysdatabases WHERE name NOT IN ( 'master', 'model', 'msdb', 'tempdb', 'northwind','pubs' )";
			ResultSet rs = stat.executeQuery(sqsl);
			while(rs.next()){//如果对象中有数据，就会循环打印出来
                System.out.println(rs.getString("name"));
            }
			ResultSetMetaData data = rs.getMetaData();
			for(int i=1;i<=data.getColumnCount();i++) {
				System.out.println(data.getPrecision(i)+"-------"+data.getColumnName(i));
				
			}
			System.out.println("11111111111111111111111111111111111111111111111111111");
			/*
			 * while(rs.next()) { System.out.println("123  "+rs.getString(1));
			 * System.out.println("123444  "+rs.getString(2));
			 * System.out.println(rs.getInt(3)); }
			 */
			//获取数据库表名和字段类型
			 ResultSet tables = connection.getMetaData().getTables(null, null, null, new String[] {"TABLE"});
			 System.out.println(connection.getMetaData());
			 while(tables.next()) {
				 String tablename = tables.getString("TABLE_NAME");
				 System.out.println(tablename);
				 ResultSet columns = connection.getMetaData().getColumns(null, null,tablename , "%");
				 while(columns.next()) {
					 System.out.println(columns.getMetaData().getPrecision(1)+"---"+columns.getString("TYPE_NAME")+"---------"+columns.getString("COLUMN_NAME"));
					 
				 }
			 }
			
			stat.close();
			connection.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
