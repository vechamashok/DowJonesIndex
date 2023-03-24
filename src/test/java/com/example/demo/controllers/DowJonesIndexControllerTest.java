package com.example.demo.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.DowJones;
import com.example.demo.model.StockData;
import com.example.demo.service.DowJonesIndexService;

public class DowJonesIndexControllerTest {

	@Mock
	private DowJonesIndexService dowJonesIndexService;

	@InjectMocks
	private DowJonesIndexController dowJonesIndexController;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testUploadFile() throws Exception {
		// given
		String clientId = "1234";
		MultipartFile file = new MockMultipartFile("test.csv", "test.csv", "text/csv", "test".getBytes());
		ResponseEntity<String> responseEntity = new ResponseEntity<>("Uploaded the file successfully: test.csv",
				HttpStatus.CREATED);
		when(dowJonesIndexService.bulkInsert(any(MultipartFile.class))).thenReturn(true);

		// when
		ResponseEntity<String> result = dowJonesIndexController.uploadFile(clientId, file);

		// then
		verify(dowJonesIndexService, times(1)).bulkInsert(file);
		assert (result.getBody().equals(responseEntity.getBody()));
	}

	@Test
	void testGetByTicker() throws Exception {
		// given
		String clientId = "1234";
		String ticker = "AAPL";
		List<DowJones> stockDataList = new ArrayList<>();
		DowJones stockData = mock(DowJones.class);
		stockDataList.add(stockData);
		when(dowJonesIndexService.findStockDataByTicker(anyString())).thenReturn(stockDataList);

		// when
		ResponseEntity<List<DowJones>> result = dowJonesIndexController.getByTicker(clientId, ticker);

		// then
		verify(dowJonesIndexService, times(1)).findStockDataByTicker(ticker);
		assert (result.getBody().equals(stockDataList));
	}

	@Test
	void testAddRecord() throws Exception {
		// given
		String clientId = "1234";
		StockData stockData = mock(StockData.class);
		when(dowJonesIndexService.addRecord(any(StockData.class))).thenReturn(true);

		// when
		ResponseEntity<String> result = dowJonesIndexController.addRecord(clientId, stockData);

		// then
		verify(dowJonesIndexService, times(1)).addRecord(stockData);
		assert (result.getBody().equals("Record added to the Database"));
	}
}
