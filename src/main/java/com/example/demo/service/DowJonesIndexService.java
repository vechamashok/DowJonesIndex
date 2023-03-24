package com.example.demo.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.DowJones;
import com.example.demo.exception.StockDataException;
import com.example.demo.exception.StockDataNotFoundException;
import com.example.demo.model.StockData;
import com.example.demo.repository.DowJonesRepository;

@Service
public class DowJonesIndexService {

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");

	@Autowired
	private DowJonesRepository dowJonesRepo;

	public boolean bulkInsert(MultipartFile file) throws IOException {
		try {
			List<DowJones> data = parseCsvFile(file.getInputStream());
			dowJonesRepo.saveAll(data);
			return true;
		} catch (Exception e) {
			throw new StockDataException("Error While persisting into Database");
		}
	}

	public List<DowJones> findStockDataByTicker(String ticker) throws StockDataNotFoundException {
		try {
			List<DowJones> listOfDate = dowJonesRepo.findByStock(ticker);
			return listOfDate;
		} catch (Exception ex) {
			throw new StockDataException("Database error occurred while finding stock data by ticker: " + ticker);
		}
	}

	public boolean addRecord(StockData data) {

		try {
			DowJones dowJones = new DowJones();
			dowJones.setQuarter(data.getQuarter());
			dowJones.setStock(data.getStock());
			dowJones.setDate(data.getDate());
			dowJones.setOpen(data.getOpen());
			dowJones.setHigh(data.getHigh());
			dowJones.setLow(data.getLow());
			dowJones.setClose(data.getClose());
			dowJones.setVolume(data.getVolume());
			dowJones.setPercent_change_price(data.getPercent_change_price());
			dowJones.setPercent_change_volume_over_last_wk(data.getPercent_change_volume_over_last_wk());
			dowJones.setPrevious_weeks_volume(data.getPrevious_weeks_volume());
			dowJones.setNext_weeks_open(data.getNext_weeks_open());
			dowJones.setNext_weeks_close(data.getNext_weeks_close());
			dowJones.setPercent_change_next_weeks_price(data.getPercent_change_next_weeks_price());
			dowJones.setDays_to_next_dividend(data.getDays_to_next_dividend());
			dowJones.setPercent_return_next_dividend(data.getPercent_return_next_dividend());
			dowJonesRepo.save(dowJones);
			return true;
		} catch (Exception e) {
			throw new StockDataException("Error While persisting into Database");
		}
	}

	public List<DowJones> parseCsvFile(InputStream inputStream) throws IOException {
		List<DowJones> data = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
			String line = reader.readLine();
			while ((line = reader.readLine()) != null) {
				String[] fields = line.split(",");
				DowJones stockData = new DowJones();
				stockData.setQuarter(!fields[0].isBlank() ? Integer.parseInt(fields[0]) : null);
				stockData.setStock(fields[1]);
				stockData.setDate(!fields[2].isBlank() ? LocalDate.parse(fields[2], formatter) : null);
				stockData.setOpen(fields[3]);
				stockData.setHigh(fields[4]);
				stockData.setLow(fields[5]);
				stockData.setClose(fields[6]);
				stockData.setVolume(!fields[7].isBlank() ? Long.parseLong(fields[7]) : null);
				stockData.setPercent_change_price(!fields[8].isBlank() ? Double.parseDouble(fields[8]) : null);
				stockData.setPercent_change_volume_over_last_wk(
						!fields[9].isBlank() ? Double.parseDouble(fields[9]) : null);
				stockData.setPrevious_weeks_volume(!fields[10].isBlank() ? Long.parseLong(fields[10]) : null);
				stockData.setNext_weeks_open(fields[11]);
				stockData.setNext_weeks_close(fields[12]);
				stockData.setPercent_change_next_weeks_price(
						!fields[13].isBlank() ? Double.parseDouble(fields[13]) : null);
				stockData.setDays_to_next_dividend(!fields[14].isBlank() ? Double.parseDouble(fields[14]) : null);
				stockData
						.setPercent_return_next_dividend(!fields[15].isBlank() ? Double.parseDouble(fields[15]) : null);
				System.out.println(line);
				data.add(stockData);
			}
		}
		return data;
	}
}
