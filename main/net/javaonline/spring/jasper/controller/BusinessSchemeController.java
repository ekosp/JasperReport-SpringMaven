package net.javaonline.spring.jasper.controller;

import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import net.javaonline.spring.jasper.form.BusinessSchemeForm;
import net.javaonline.spring.jasper.testing.testong;

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

	//ReportGenerator.generateCustomReport();
	
	System.out.println("berhasil jalankan /generateReportBusinessScheme");
	//String version = System.getProperty("java.version");
    //System.out.println(version);
	testong.printJasper();
	
	return null;
	
	
}



}