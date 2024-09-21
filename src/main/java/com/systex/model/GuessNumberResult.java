package com.systex.model;

public class GuessNumberResult {
	private int remains;
	private int low;
	private int high;
	private String status;
	private String message;
	
	public GuessNumberResult() {
		
	}
	
	public GuessNumberResult(int remains, int low, int high, String status, String message) {
		super();
		this.remains = remains;
		this.low = low;
		this.high = high;
		this.status = status;
		this.message = message;
	}

	public int getRemains() {
		return remains;
	}

	public void setRemains(int remains) {
		this.remains = remains;
	}

	public int getLow() {
		return low;
	}

	public void setLow(int low) {
		this.low = low;
	}

	public int getHigh() {
		return high;
	}

	public void setHigh(int high) {
		this.high = high;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
