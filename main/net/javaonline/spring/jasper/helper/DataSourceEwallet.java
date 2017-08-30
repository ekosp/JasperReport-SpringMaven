package net.javaonline.spring.jasper.helper;

import javax.sql.DataSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DataSourceEwallet {
	
	private static final String driverClassName = "org.postgresql.Driver";
	private static final String url = "jdbc:postgresql://10.50.50.51:5432/db_atic-ewallet-demo";
	private static final String dbUsername = "postgres";
	private static final String dbPassword = "postgre";

	private static DataSource dataSource;
	
	public static DataSource getCustomDataSource() {
		dataSource = getDataSource();
		if (dataSource == null) System.out.println("datasource null boy...");
		return dataSource;		
	}
	
	public static DriverManagerDataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		//dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(dbUsername);
		dataSource.setPassword(dbPassword);
		return dataSource;
    }
	
}