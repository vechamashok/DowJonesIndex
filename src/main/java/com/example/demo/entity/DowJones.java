package com.example.demo.entity;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "dow_jones")
public class DowJones implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quarter;
    
    private String stock;

    private LocalDate date;

    private String open;

    private String high;

    private String low;

    private String close;

    private Long volume;

    private Double percent_change_price;
    
    private Double percent_change_volume_over_last_wk;

    private Long previous_weeks_volume;

    private String next_weeks_open;

    private String next_weeks_close;

    private Double percent_change_next_weeks_price;
    
    private Double days_to_next_dividend;
    
    private Double percent_return_next_dividend;

	public DowJones(int quarter, String stock, LocalDate date, String open, String high, String low, String close,
			Long volume, Double percent_change_price, Double percent_change_volume_over_last_wk,
			Long previous_weeks_volume, String next_weeks_open, String next_weeks_close,
			Double percent_change_next_weeks_price, Double days_to_next_dividend, Double percent_return_next_dividend) {
		super();
		this.quarter = quarter;
		this.stock = stock;
		this.date = date;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.volume = volume;
		this.percent_change_price = percent_change_price;
		this.percent_change_volume_over_last_wk = percent_change_volume_over_last_wk;
		this.previous_weeks_volume = previous_weeks_volume;
		this.next_weeks_open = next_weeks_open;
		this.next_weeks_close = next_weeks_close;
		this.percent_change_next_weeks_price = percent_change_next_weeks_price;
		this.days_to_next_dividend = days_to_next_dividend;
		this.percent_return_next_dividend = percent_return_next_dividend;
	}

	public DowJones() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
