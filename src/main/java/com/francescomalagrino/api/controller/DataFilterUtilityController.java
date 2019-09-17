package com.francescomalagrino.api.controller;

import java.io.IOException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.francescomalagrino.api.serviceImpl.DataFilterUtilityServiceImpl;
import com.francescomalagrino.model.DataFilterUtilityRequest;
import com.francescomalagrino.model.DataFilterUtilityResponse;
import com.google.common.base.Strings;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "ActionController", description = "API for csv Data Filter")
@RestController
public class DataFilterUtilityController {
	
	private static final Logger logger = LoggerFactory.getLogger(DataFilterUtilityController.class);
	
	@Autowired
	private DataFilterUtilityServiceImpl dataFilterUtilityService;

	/**
	 * @param utilityRequest
	 * @return
	 */
	@ApiOperation(value = "Get filtered data from file", response = DataFilterUtilityRequest.class, tags = "getFilteredData")
	@RequestMapping(value = "/getFilteredData", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<DataFilterUtilityResponse> getFilteredData(
			@RequestBody DataFilterUtilityRequest utilityRequest) {
		
		DataFilterUtilityResponse providerResponse = new DataFilterUtilityResponse();
		logger.info("Input request :"+utilityRequest);
		
		if (!Strings.isNullOrEmpty(utilityRequest.getFilePath())) {
			try {
				providerResponse = dataFilterUtilityService.generateReport(utilityRequest);
				providerResponse.setTimestamp(new Date());
				providerResponse.setHttpStatus(HttpStatus.OK.value());
			} catch (IOException e) {
				providerResponse.setMsg(e.getMessage());
				providerResponse.setHttpStatus(HttpStatus.NOT_FOUND.value());
			}
		} else
			providerResponse.setMsg("No file found :" + utilityRequest.getFilePath());

		return new ResponseEntity<DataFilterUtilityResponse>(providerResponse, HttpStatus.OK);

	}

}
