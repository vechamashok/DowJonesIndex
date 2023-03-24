package com.example.demo.exception;

public class StockDataException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StockDataException(String message) {
        super(message);
    }
}
