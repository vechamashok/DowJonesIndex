package com.example.demo.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class StockData {
	
	@JsonProperty("quarter")
    private int quarter;
    
	@JsonProperty("stock")
    private String stock;

	@JsonProperty("date")
	@JsonFormat(pattern = "M/d/yyyy")
    private LocalDate date;

	@JsonProperty("open")
    private String open;

	@JsonProperty("high")
    private String high;

	@JsonProperty("low")
    private String low;

	@JsonProperty("close")
    private String close;

	@JsonProperty("volume")
    private Long volume;

	@JsonProperty("percentChangePrice")
    private Double percent_change_price;
    
	@JsonProperty("percentChangeVolumeOverLastWk")
    private Double percent_change_volume_over_last_wk;

	@JsonProperty("previousWeeksVolume")
    private Long previous_weeks_volume;

	@JsonProperty("nextWeeksOpen")
    private String next_weeks_open;

	@JsonProperty("nextWeeksClose")
    private String next_weeks_close;

	@JsonProperty("percentChangeNextWeeksPrice")
    private Double percent_change_next_weeks_price;
    
	@JsonProperty("daysToNextDividend")
    private Double days_to_next_dividend;
    
	@JsonProperty("percentReturnNextDividend")
    private Double percent_return_next_dividend;

	public int getQuarter() {
		return quarter;
	}

	public void setQuarter(int quarter) {
		this.quarter = quarter;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public String getHigh() {
		return high;
	}

	public void setHigh(String high) {
		this.high = high;
	}

	public String getLow() {
		return low;
	}

	public void setLow(String low) {
		this.low = low;
	}

	public String getClose() {
		return close;
	}

	public void setClose(String close) {
		this.close = close;
	}

	public Long getVolume() {
		return volume;
	}

	public void setVolume(Long volume) {
		this.volume = volume;
	}

	public Double getPercent_change_price() {
		return percent_change_price;
	}

	public void setPercent_change_price(Double percent_change_price) {
		this.percent_change_price = percent_change_price;
	}

	public Double getPercent_change_volume_over_last_wk() {
		return percent_change_volume_over_last_wk;
	}

	public void setPercent_change_volume_over_last_wk(Double percent_change_volume_over_last_wk) {
		this.percent_change_volume_over_last_wk = percent_change_volume_over_last_wk;
	}

	public Long getPrevious_weeks_volume() {
		return previous_weeks_volume;
	}

	public void setPrevious_weeks_volume(Long previous_weeks_volume) {
		this.previous_weeks_volume = previous_weeks_volume;
	}

	public String getNext_weeks_open() {
		return next_weeks_open;
	}

	public void setNext_weeks_open(String next_weeks_open) {
		this.next_weeks_open = next_weeks_open;
	}

	public String getNext_weeks_close() {
		return next_weeks_close;
	}

	public void setNext_weeks_close(String next_weeks_close) {
		this.next_weeks_close = next_weeks_close;
	}

	public Double getPercent_change_next_weeks_price() {
		return percent_change_next_weeks_price;
	}

	public void setPercent_change_next_weeks_price(Double percent_change_next_weeks_price) {
		this.percent_change_next_weeks_price = percent_change_next_weeks_price;
	}

	public Double getDays_to_next_dividend() {
		return days_to_next_dividend;
	}

	public void setDays_to_next_dividend(Double days_to_next_dividend) {
		this.days_to_next_dividend = days_to_next_dividend;
	}

	public Double getPercent_return_next_dividend() {
		return percent_return_next_dividend;
	}

	public void setPercent_return_next_dividend(Double percent_return_next_dividend) {
		this.percent_return_next_dividend = percent_return_next_dividend;
	}
}
