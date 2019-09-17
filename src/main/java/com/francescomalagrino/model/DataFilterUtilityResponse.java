package com.francescomalagrino.model;

import java.util.Date;
import java.util.List;

public class DataFilterUtilityResponse {

	private  List<List<String>> resList;
	private String filePath;
	private String msg;
	private Date timestamp;
	private int httpStatus;
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public int getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(int httpStatus) {
		this.httpStatus = httpStatus;
	}
	
	public List<List<String>> getResList() {
		return resList;
	}
	public void setResList(List<List<String>> resList) {
		this.resList = resList;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
}
