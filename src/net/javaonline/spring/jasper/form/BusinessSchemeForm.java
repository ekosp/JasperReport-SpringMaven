package net.javaonline.spring.jasper.form;

import org.hibernate.validator.constraints.NotEmpty;

public class BusinessSchemeForm {
	
	@NotEmpty
	private String dateFrom;
	
	private String dateTo;
	
	private String status;
	
	private String groupMinistry;
	
	private String subsidyType;
		
	private String repotFormat="Html";
	
	public String getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public String getDateTo() {
		return dateTo;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getGroupMinistry() {
		return groupMinistry;
	}

	public void setGroupMinistry(String groupMinistry) {
		this.groupMinistry = groupMinistry;
	}

	public String getSubsidyType() {
		return subsidyType;
	}

	public void setSubsidyType(String subsidyType) {
		this.subsidyType = subsidyType;
	}

	public String getRepotFormat() {
		return repotFormat;
	}

	public void setRepotFormat(String repotFormat) {
		this.repotFormat = repotFormat;
	}


	
	}