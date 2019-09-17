package com.francescomalagrino.model;


public class DataFilterUtilityRequest {

	private String dataFilterLimit;
	private String dataFilterMinValue;
	private String filePath;

	public DataFilterUtilityRequest() {
	};

	public DataFilterUtilityRequest(String dataFilterLimit, String dataFilterMinValue, String filePath) {
		super();
		this.dataFilterLimit = dataFilterLimit;
		this.dataFilterMinValue = dataFilterMinValue;
		this.filePath = filePath;
	}

	public String getDataFilterLimit() {
		return dataFilterLimit;
	}

	public void setDataFilterLimit(String dataFilterLimit) {
		this.dataFilterLimit = dataFilterLimit;
	}

	public String getDataFilterMinValue() {
		return dataFilterMinValue;
	}

	public void setDataFilterMinValue(String dataFilterMinValue) {
		this.dataFilterMinValue = dataFilterMinValue;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public String toString() {
		return "DataFilterUtilityModel [dataFilterLimit=" + dataFilterLimit + ", dataFilterMinValue="
				+ dataFilterMinValue + ", filePath=" + filePath + "]";
	}
}
