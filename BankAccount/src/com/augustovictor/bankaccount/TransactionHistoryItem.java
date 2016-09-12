package com.augustovictor.bankaccount;

public class TransactionHistoryItem {
	private final int id;
	private final double amount;
	private final double balance;
	private final String message;
	

	public TransactionHistoryItem(int id, double amount, String historyMessage, double balance) {
		this.id = id;
		this.amount = amount;
		this.message = historyMessage;
		this.balance = balance;
	}


	public int getId() {
		return id;
	}


	public double getAmount() {
		return amount;
	}


	public double getBalance() {
		return balance;
	}


	public String getMessage() {
		return message;
	}

}
