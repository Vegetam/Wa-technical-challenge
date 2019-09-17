package com.francescomalagrino.api.exception;

import com.francescomalagrino.model.DataFilterUtilityResponse;

public class GenericException extends Exception {
	/**
	 * initialization serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private static final int HTTP_ERROR_CODE = 500;

	/**
	 * 
	 * @param errorMessage
	 */
	public GenericException(String errorMessage) {
		super(errorMessage);
		DataFilterUtilityResponse providerResponse = new DataFilterUtilityResponse();
		providerResponse.setMsg(errorMessage);
		providerResponse.setHttpStatus(HTTP_ERROR_CODE);
	}
}
