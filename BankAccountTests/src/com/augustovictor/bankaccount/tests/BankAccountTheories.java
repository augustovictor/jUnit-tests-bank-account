package com.augustovictor.bankaccount.tests;



import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import com.augustovictor.bankaccount.Account;
import com.augustovictor.bankaccount.InvalidAmountException;
import com.augustovictor.bankaccount.NotifierStub;

import static org.junit.Assert.*;

import org.junit.Assume;



@RunWith(Theories.class)
public class BankAccountTheories {

	@DataPoints
	public static double[] data() {
		return new double[] {
			2.0, 3.3, 5.4, 7.1, 50.0, -2.1	
		};
	}
	
	@Theory
	public void PositiveValuesShouldAlwaysPositiveBalance(double value) throws InvalidAmountException {
		Assume.assumeTrue(value > 0);
		Account acc = new Account(new NotifierStub());
		acc.makeDeposit(value);
		assertTrue(acc.getBalance() > 0);
	}
	
}
