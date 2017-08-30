package net.javaonline.spring.jasper.testing;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class testong {
	
	public static void printJasper() {
		 AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
	        ctx.register(JasperRerportsSimpleConfig.class);
	        ctx.refresh();

	        SimpleReportFiller simpleReportFiller = ctx.getBean(SimpleReportFiller.class);
	        simpleReportFiller.setReportFileName("employeeReport.jrxml");
	        simpleReportFiller.compileReport();

	        Map<String, Object> parameters = new HashMap<String, Object>();
	        
	        parameters.put("DATE_FROM", "20160422");
	        parameters.put("DATE_TO", "20170422");
	        parameters.put("STATUS", "status_dummy");
	        parameters.put("GROUP_MINISTRY", "ministry_dummy");
	        parameters.put("SUBSIDI_TYPE", "type_dummy");
	        

	        simpleReportFiller.setParameters(parameters);
	        simpleReportFiller.fillReport();

	        SimpleReportExporter simpleExporter = ctx.getBean(SimpleReportExporter.class);
	        simpleExporter.setJasperPrint(simpleReportFiller.getJasperPrint());

	        simpleExporter.exportToPdf("employeeReport.pdf", "baeldung");
	        simpleExporter.exportToXlsx("employeeReport.xlsx", "Employee Data");
	        simpleExporter.exportToCsv("employeeReport.csv");
	        simpleExporter.exportToHtml("employeeReport.html");
	        
	        ctx.close();
	}

}
