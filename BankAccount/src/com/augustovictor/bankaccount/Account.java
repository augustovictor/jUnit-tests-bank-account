package com.augustovictor.bankaccount;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private String number = "123";
    private Double balance = 0.0;
    private List<TransactionHistoryItem> history = new ArrayList<TransactionHistoryItem>();
    private final Double GOAL = 100.00;
	private Notifier notifier;
	private int historyId;
	
	public Account(Notifier notifier) {
		this.notifier = notifier;
	}

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public void makeDeposit(Double amount) throws InvalidAmountException {
    	if(amount < 0) throw new InvalidAmountException("Negative values are not allowed");
        setBalance(this.balance += amount);
        
        if(getBalance() > GOAL) {
        	boolean sendMessageGoal = notifier.send("goal met");
        	String historyMessage = "sent: goal met";
        	if(!sendMessageGoal) {
        		historyMessage = "send_error: goal met";
        	}
        	history.add(new TransactionHistoryItem(historyId++, amount, historyMessage, getBalance()));
        }
    }

    public void makeWithdrawal(double amount) throws InvalidAmountException {
    	if(amount < 0) throw new InvalidAmountException("Negative values are not allowed");
        setBalance(this.balance -= amount);
        if(getBalance() < 0) {
        	setBalance(0.0);
        }
    }

	public List<TransactionHistoryItem> getHistory() {
		return this.history;
	}
}
