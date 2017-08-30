package net.javaonline.spring.jasper.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JasperReportDAO {
	
	public Connection getPostgreSQLConnetion() throws SQLException{
		Connection conn = null;
			try {
				Class.forName("org.postgresql.Driver");	
			 	} catch (Exception e) {
			 		System.out.println("Please include Classpath Where your MySQL Driver is located");
			 		e.printStackTrace(); 
			 	}  
			conn = DriverManager.getConnection("jdbc:postgresql://10.50.50.51:5432/db_atic-ewallet-demo","postgres","postgre");
			System.out.println("berhasil konek db ppostgreSQL...");	
			 
		 if (conn != null)
		 {  System.out.println("Database Connected");	 }
		 else
		 {  System.out.println("Connection Failed ");	 }
	return conn;
	}

}
