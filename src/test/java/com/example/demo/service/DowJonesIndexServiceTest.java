package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;

import com.example.demo.entity.DowJones;
import com.example.demo.exception.StockDataException;
import com.example.demo.model.StockData;
import com.example.demo.repository.DowJonesRepository;

public class DowJonesIndexServiceTest {
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");

	@InjectMocks
	private DowJonesIndexService service;
	
	@Mock
	private DowJonesRepository repository;
	
	private StockData stockData;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		stockData = new StockData();
        stockData.setQuarter(1);
        stockData.setStock("ABC");
        stockData.setDate(LocalDate.now());
        stockData.setOpen("10.5");
        stockData.setHigh("12.5");
        stockData.setLow("9.5");
        stockData.setClose("11.5");
        stockData.setVolume(10000L);
        stockData.setPercent_change_price(1.5);
        stockData.setPercent_change_volume_over_last_wk(0.5);
        stockData.setPrevious_weeks_volume(9000L);
        stockData.setNext_weeks_open("12.0");
        stockData.setNext_weeks_close("10.0");
        stockData.setPercent_change_next_weeks_price(-0.2);
        stockData.setDays_to_next_dividend(30.0);
        stockData.setPercent_return_next_dividend(0.1);
	}
	
	@Test
	public void testBulkInsert() throws IOException {
		String csv = "Quarter,Stock,Date,Open,High,Low,Close,Volume,% Change Price,% Change Volume over Last Week,Previous Week's Volume,Next Week's Open,Next Week's Close,% Change Next Week's Price,Days to Next Dividend,% Return Next Dividend\n"
				+ "1,AAPL,1/7/2011,336.33,341.44,333.53,339.94,13827800,0.0368389,-0.0826144,13394900,344.17,345.03,0.002491,-12.0,0.182704";
		InputStream inputStream = new ByteArrayInputStream(csv.getBytes());
		MockMultipartFile file = new MockMultipartFile("test.csv", inputStream);
		
		List<DowJones> data = new ArrayList<>();
		DowJones dowJones = new DowJones();
		dowJones.setQuarter(1);
		dowJones.setStock("AAPL");
		dowJones.setDate(LocalDate.parse("1/7/2011", service.formatter));
		dowJones.setOpen("336.33");
		dowJones.setHigh("341.44");
		dowJones.setLow("333.53");
		dowJones.setClose("339.94");
		dowJones.setVolume(13827800L);
		dowJones.setPercent_change_price(0.0368389);
		dowJones.setPercent_change_volume_over_last_wk(-0.0826144);
		dowJones.setPrevious_weeks_volume(13394900L);
		dowJones.setNext_weeks_open("344.17");
		dowJones.setNext_weeks_close("345.03");
		dowJones.setPercent_change_next_weeks_price(0.002491);
		dowJones.setDays_to_next_dividend(-12.0);
		dowJones.setPercent_return_next_dividend(0.182704);
		data.add(dowJones);
		
		when(repository.saveAll(data)).thenReturn(data);
		
		boolean result = service.bulkInsert(file);
		assertEquals(true, result);
	}
	
	@Test
	public void testBulkInsertWithException() throws IOException {
		MockMultipartFile file = new MockMultipartFile("test.csv", new byte[]{});
		
		when(repository.saveAll(new ArrayList<>())).thenThrow(new RuntimeException("Test Exception"));
		
		StockDataException exception = assertThrows(StockDataException.class, () -> {
			service.bulkInsert(file);
		});
		
		assertEquals("Error While persisting into Database", exception.getMessage());
	}
	
	@Test
    public void testFindStockDataByTicker() {
        List<DowJones> testData = new ArrayList<>();
        testData.add(new DowJones(1, "AAPL", LocalDate.parse("1/1/2021", formatter), "100", "110", "90", "95", 1000L,
                5.5, 10.0, 2000L, "102", "98", 2.0, 30.0, 1.5));
        testData.add(new DowJones(2, "AAPL", LocalDate.parse("1/8/2021", formatter), "98", "105", "90", "100", 1500L,
                2.5, 8.0, 1800L, "101", "99", 0.5, 25.0, 1.0));

        Mockito.when(repository.findByStock(Mockito.anyString())).thenReturn(testData);

        List<DowJones> result = service.findStockDataByTicker("AAPL");

        assertEquals(2, result.size());
        assertEquals("AAPL", result.get(0).getStock());
        assertEquals(LocalDate.parse("1/1/2021", formatter), result.get(0).getDate());
        assertEquals(1000L, result.get(0).getVolume());
        assertEquals(2.5, result.get(1).getPercent_change_price());

        Mockito.verify(repository, Mockito.times(1)).findByStock(Mockito.anyString());
    }
	
	@Test
    void testAddRecord() {
        DowJones dowJones = new DowJones();
        dowJones.setQuarter(stockData.getQuarter());
        dowJones.setStock(stockData.getStock());
        dowJones.setDate(stockData.getDate());
        dowJones.setOpen(stockData.getOpen());
        dowJones.setHigh(stockData.getHigh());
        dowJones.setLow(stockData.getLow());
        dowJones.setClose(stockData.getClose());
        dowJones.setVolume(stockData.getVolume());
        dowJones.setPercent_change_price(stockData.getPercent_change_price());
        dowJones.setPercent_change_volume_over_last_wk(stockData.getPercent_change_volume_over_last_wk());
        dowJones.setPrevious_weeks_volume(stockData.getPrevious_weeks_volume());
        dowJones.setNext_weeks_open(stockData.getNext_weeks_open());
        dowJones.setNext_weeks_close(stockData.getNext_weeks_close());
        dowJones.setPercent_change_next_weeks_price(stockData.getPercent_change_next_weeks_price());
        dowJones.setDays_to_next_dividend(stockData.getDays_to_next_dividend());
        dowJones.setPercent_return_next_dividend(stockData.getPercent_return_next_dividend());

        Mockito.when(repository.save(Mockito.any(DowJones.class))).thenReturn(dowJones);

        boolean isRecordAdded = service.addRecord(stockData);
        assertEquals(true, isRecordAdded);
    }
	
	@Test
	void testParseCsvFile() throws IOException {
		String csvData = "quarter,stock,date,open,high,low,close,volume,percent_change_price,percent_change_volume_over_last_wk,previous_weeks_volume,next_weeks_open,next_weeks_close,percent_change_next_weeks_price,days_to_next_dividend,percent_return_next_dividend\n"
				+ "1,AA,1/7/2011,15.82,16.72,15.78,16.42,239655616,3.79267,1.380223028,24240829,16.71,15.97,-4.42849,26,0.182704\n"
				+ "1,AA,1/14/2011,16.71,16.71,15.64,15.97,24240829,-4.42849,-2.470660572,239655616,16.19,15.79,-2.47066,19,0.187852\n"
				+ "1,AIG,1/7/2011,39.76,41.24,38.79,40.88,14726712,3.00227,-21.50189953,17357190,41.24,41.05,-0.485662,26,0.007282\n"
				+ "1,AIG,1/14/2011,41.60,41.93,41.31,41.60,11404898,0.000000,0.000000000,1514733,41.71,41.88,0.115987,19,0.006105\n";

		InputStream is = new ByteArrayInputStream(csvData.getBytes());
		List<DowJones> dowJonesList = service.parseCsvFile(is);

		assertEquals(4, dowJonesList.size());

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
		LocalDate expectedDate = LocalDate.parse("1/7/2011", formatter);

		assertEquals(1, dowJonesList.get(0).getQuarter());
		assertEquals("AA", dowJonesList.get(0).getStock());
		assertEquals(expectedDate, dowJonesList.get(0).getDate());
		assertEquals("15.82", dowJonesList.get(0).getOpen());
		assertEquals("16.72", dowJonesList.get(0).getHigh());
		assertEquals("15.78", dowJonesList.get(0).getLow());
		assertEquals("16.42", dowJonesList.get(0).getClose());
	}
}
