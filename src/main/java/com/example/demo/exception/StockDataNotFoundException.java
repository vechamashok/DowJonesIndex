package com.example.demo.exception;

public class StockDataNotFoundException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StockDataNotFoundException(String message) {
        super(message);
    }

    public StockDataNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}