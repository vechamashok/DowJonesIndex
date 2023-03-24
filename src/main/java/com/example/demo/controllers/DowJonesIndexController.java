package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.DowJones;
import com.example.demo.model.StockData;
import com.example.demo.service.DowJonesIndexService;

@RestController
@RequestMapping("/api/stock-data")
public class DowJonesIndexController {

	@Autowired
	public DowJonesIndexService dowJonesIndexService;

	@PostMapping("/bulk-insert")
	public ResponseEntity<String> uploadFile(@RequestHeader(value = "X-Client_Id") String clientId,
			@RequestParam("file") MultipartFile file) {
		String message = "";
		try {
			dowJonesIndexService.bulkInsert(file);

			message = "Uploaded the file successfully: " + file.getOriginalFilename();
			return ResponseEntity.status(HttpStatus.CREATED).body(message);
		} catch (Exception e) {
			message = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}
	}
	
    @GetMapping("/{ticker}")
    public ResponseEntity<List<DowJones>> getByTicker (
            @RequestHeader(value = "X-Client_Id") String clientId,
            @PathVariable String ticker) {
        List<DowJones> stockDataList = dowJonesIndexService.findStockDataByTicker(ticker);
        return ResponseEntity.ok(stockDataList);
    }

    @PostMapping("/")
    public ResponseEntity<String> addRecord(
            @RequestHeader(value = "X-Client_Id") String clientId,
            @RequestBody StockData stockData) {
    	dowJonesIndexService.addRecord(stockData);
        return ResponseEntity.ok("Record added to the Database");
    }
}