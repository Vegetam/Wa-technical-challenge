package com.francescomalagrino.api.util;

import java.io.IOException;
import java.io.Writer;
import java.util.List;


public final class DataFilterUtility {

	public static final String DEFAULT_SEPARATOR = ",";
	public static final String COLON_VALUE = ":";

	/**
	 * @param w :writer object
	 * @param values :data to write into csv
	 * @throws IOException
	 */
	public static void writeData(Writer w, List<List<String>> values) throws IOException {

		StringBuilder sb = new StringBuilder();
		for (List<String> value : values) {
			boolean first = true;
			for (String strVal : value) {
				if (!first) {
					sb.append(DEFAULT_SEPARATOR);
				}
				sb.append(strVal);
				first = false;
			}
			sb.append("\n");
		}
		w.append(sb.toString());
	}
}
