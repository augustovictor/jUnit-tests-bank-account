package com.augustovictor.bankaccount.tests;



import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.augustovictor.bankaccount.Account;
import com.augustovictor.bankaccount.InvalidAmountException;

@RunWith(Parameterized.class)
public class ParameterizedTests {
	
	// Static because we want this object to stick around. It is associates with the class. Not objects of the class;
	private static Account acc = new Account();
	private double input;
	private double expected;
	
	@Parameters
	public static List<Object[]> data() {
		return Arrays.asList(new Object[][] {
			{5.0, 5.0},
			{5.0, 10.0},
			{-12.0, 0.0},
			{50.0, 50.0},
			{1.0, 51.0}
		});
	}
	
	public ParameterizedTests(double input, double expected) {
		this.input = input;
		this.expected = expected;
	}
	
	@Test
	public void test() throws InvalidAmountException {
		if(input >= 0) {
			acc.makeDeposit(input);
		} else {
			acc.makeWithdrawal(-input);
		}
		
		assertEquals("Total balance was not correct.", expected, acc.getBalance(), 0);
	}
}
