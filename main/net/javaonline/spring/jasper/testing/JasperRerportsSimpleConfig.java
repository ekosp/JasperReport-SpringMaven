package net.javaonline.spring.jasper.testing;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import net.javaonline.spring.jasper.helper.DataSourceEwallet;


@Configuration
public class JasperRerportsSimpleConfig {

    @Bean
    public DataSource dataSource() {
    	return DataSourceEwallet.getCustomDataSource();
    }

    @Bean
    public SimpleReportFiller reportFiller() {
        return new SimpleReportFiller();
    }

    @Bean
    public SimpleReportExporter reportExporter() {
        return new SimpleReportExporter();
    }

}