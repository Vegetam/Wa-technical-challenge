package com.francescomalagrino.api.serviceImpl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.francescomalagrino.api.util.DataFilterUtility;
import com.francescomalagrino.model.DataFilterUtilityRequest;
import com.francescomalagrino.model.DataFilterUtilityResponse;

@Component(value = "dataFilterUtilityService")
public class DataFilterUtilityServiceImpl {

	/**
	 * @param utilityRequest
	 * @return
	 * @throws IOException
	 */
	public DataFilterUtilityResponse generateReport(DataFilterUtilityRequest utilityRequest) throws IOException {
		BufferedReader br = null;
		FileWriter fileWriter = null;
		DataFilterUtilityResponse dataFilterUtilityResponse = new DataFilterUtilityResponse();
		try {
			Resource resource = new ClassPathResource(utilityRequest.getFilePath());
			InputStream inputFS = resource.getInputStream();
			br = new BufferedReader(new InputStreamReader(inputFS));

			 // retrieve the header data from csv file
			List<String> headerList = br.lines().findFirst()
					.map(line -> Arrays.asList(line.split(DataFilterUtility.DEFAULT_SEPARATOR))).get();

			// read data line by line
			List<Map<Double, Integer>> dataList = br.lines().skip(0).map(mapFromListData).collect(Collectors.toList());
			Iterator<Map<Double, Integer>> iterator = dataList.iterator();

			List<List<String>> resultDataList = fetchRowData(iterator, utilityRequest, headerList);

			String fileName = "data_result.csv";
			fileWriter = new FileWriter(fileName);
			DataFilterUtility.writeData(fileWriter, resultDataList);

			if (!resultDataList.isEmpty()) {
				dataFilterUtilityResponse.setResList(resultDataList);
			} else
				dataFilterUtilityResponse.setMsg("No data found for give input.");

			dataFilterUtilityResponse.setFilePath(fileName);
		} catch (FileNotFoundException e) {

		} finally {
			br.close();
			fileWriter.flush();
			fileWriter.close();
		}
		return dataFilterUtilityResponse;
	}

	private static Function<String, LinkedHashMap<Double, Integer>> mapFromListData = (line) -> {
		String[] rowStr = line.split(DataFilterUtility.DEFAULT_SEPARATOR);
		LinkedHashMap<Double, Integer> map = new LinkedHashMap<>();

		for (int i = 1; rowStr.length > i; i++) {
			map.put(new Double(rowStr[i]), i);
		}
		return map;
	};

	/**
	 * @param iterator
	 * @param utilityRequest
	 * @param headerList
	 * @return
	 */
	private List<List<String>> fetchRowData(Iterator<Map<Double, Integer>> iterator,
			DataFilterUtilityRequest utilityRequest, List<String> headerList) {
		
		List<List<String>> resultDataList = new ArrayList<>();
		while (iterator.hasNext()) {
			List<String> tempList = new ArrayList<>();
			Map<Double, Integer> sortedMap = iterator.next().entrySet().stream()
					.sorted(Collections.reverseOrder(Map.Entry.comparingByKey())).collect(Collectors
							.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

			int count = 0;
			for (Entry<Double, Integer> entry : sortedMap.entrySet()) {
				if (count == new Integer(utilityRequest.getDataFilterLimit())) {
					break;
				} else if (new Double(utilityRequest.getDataFilterMinValue()) < new Double(entry.getKey())) {
					tempList.add(headerList.get(entry.getValue()) + DataFilterUtility.COLON_VALUE + entry.getKey());
				}
				count++;
			}
			if (!tempList.isEmpty()) {
				resultDataList.add(tempList);
			}
		}
		return resultDataList;
	}

}
