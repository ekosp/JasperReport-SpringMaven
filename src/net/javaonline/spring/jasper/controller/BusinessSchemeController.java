package net.javaonline.spring.jasper.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import net.javaonline.spring.jasper.form.BusinessSchemeForm;
import net.javaonline.spring.jasper.form.JasperInputForm;
import net.javaonline.spring.jasper.helper.JasperReportDAO;
import net.javaonline.spring.jasper.helper.ReportGenerator;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleHtmlReportConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BusinessSchemeController {
	
	
	@ModelAttribute("jasperRptFormats")
	public ArrayList getJasperRptFormats()
	{
		ArrayList < String> jasperRptFormats = new ArrayList<String>();
		jasperRptFormats.add("Html");
		jasperRptFormats.add("PDF");
		
		return jasperRptFormats;
	}	
	
	
@RequestMapping(value = "/businessScheme", method = RequestMethod.GET)
	public String loadSurveyPg(@ModelAttribute("businessSchemeForm") BusinessSchemeForm businessSchemeForm, Model model) {
	model.addAttribute("JasperInputForm", businessSchemeForm);
		
	return "businessScheme";
}


@RequestMapping(value = "/generateReportBusinessScheme", method = RequestMethod.POST)
public String generateReport(@Valid @ModelAttribute("businessSchemeForm") BusinessSchemeForm businessSchemeForm, BindingResult result,Model model, HttpServletRequest request,HttpServletResponse response) throws ParseException {
	
	if (result.hasErrors()) {
		System.out.println("validation error occured in jasper input form");
		return "businessScheme";
        
    }
	

	//String reportFileName = "JREmp1";
	String reportFileName = "report_business_scheme";
	JasperReportDAO jrdao = new JasperReportDAO();
	
	Connection conn = null;
	
	
	try {
	conn = jrdao.getPostgreSQLConnetion();
			
		
		  String rptFormat = businessSchemeForm.getRepotFormat();
		  String datFrom = businessSchemeForm.getDateFrom();
		  
		  System.out.println("rpt format " + rptFormat);
		  System.out.println("no of years " + datFrom);
    
		   
		   HashMap<String,Object> hmParams=new HashMap<String,Object>();
		   hmParams.put("datFrom", new Integer(datFrom));
		   hmParams.put("Title", "Employees working more than "+ datFrom + " Years");
		   
		
	        
			JasperReport jasperReport = ReportGenerator.getCompiledFile(reportFileName, request);

		if (rptFormat.equalsIgnoreCase("html") ) {
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, hmParams, conn);
			//ReportGenerator.generateReportHtml(jasperPrint, request, response); // For HTML report
			ReportGenerator.generateReportHtmlnew(jasperPrint, request, response);

		}

		else if  (rptFormat.equalsIgnoreCase("pdf") )  {
			
			ReportGenerator.generateReportPDF(response, hmParams, jasperReport, conn); // For PDF report	
			//ReportGenerator.generateReportPDFNew();
		    }

	   } catch (Exception sqlExp) {
		   System.out.println( "Exception::" + sqlExp.toString());
	   } finally {
    		try {
    		if (conn != null) {
	    		conn.close();
	    		conn = null;
    		}
    		} catch (SQLException expSQL) {
    			System.out.println("SQLExp::CLOSING::" + expSQL.toString());
    		}
	       }

	return null;
	
	
}



}