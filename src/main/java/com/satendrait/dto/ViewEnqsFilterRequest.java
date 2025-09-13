package com.satendrait.dto;

public class ViewEnqsFilterRequest {
	
	
	private String courseName;
	private String classMode;
	private String EnqStatus;
	public String getCourseName() {
		return courseName;
	

}
	public String getClassMode() {
		return classMode;
	}
	public void setClassMode(String classMode) {
		this.classMode = classMode;
	}
	public String getEnqStatus() {
		return EnqStatus;
	}
	public void setEnqStatus(String enqStatus) {
		EnqStatus = enqStatus;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
}
