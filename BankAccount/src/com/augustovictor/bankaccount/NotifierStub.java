package com.augustovictor.bankaccount;

public class NotifierStub implements Notifier{

	@Override
	public boolean send(String message) {
		return true;
	}

}
