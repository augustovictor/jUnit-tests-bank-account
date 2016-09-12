package com.augustovictor.bankaccount.tests;



import static org.junit.Assert.*;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.ExpectedException;
import org.junit.rules.Timeout;

import com.augustovictor.bankaccount.Account;
import com.augustovictor.bankaccount.InvalidAmountException;
import com.augustovictor.bankaccount.Notifier;
import com.augustovictor.bankaccount.NotifierStub;
import com.augustovictor.bankaccount.TransactionHistoryItem;

import static org.hamcrest.CoreMatchers.*;

public class HelloJUnitTest {
	
	private Account account;
	
	@BeforeClass
	public static void before() {
		System.out.println("Executed before class");
	}
	
	@AfterClass
	public static void after() {
		System.out.println("Executed after class");
	}
	
	@Before
	public void setUp() {
		System.out.println("Before");
		account = new Account(new NotifierStub());
	}
	
	@After
	public void tearDown() {
		System.out.println("After (Used when rolling back created data)");
	}

	@Test
	@Category({GoodTestsCategory.class, BadTestsCategory.class})
	public void NewAccountNumberIs123() {
		assertEquals("Account number is not 123", "123", account.getNumber());
	}
	
	@Test
	@Category(GoodTestsCategory.class)
	public void WhenMakingDepositAccountBalanceIncreases() throws InvalidAmountException {
		account.makeDeposit(10.0);
		assertThat(account.getBalance(), allOf(
				is(10.0),
				instanceOf(Double.class)
				));
	}
	
	@Test
	@Category(GoodTestsCategory.class)
	public void WhenWithdrawalAccountBalanceDecreases() throws InvalidAmountException {
		account.makeDeposit(10.0);
		account.makeWithdrawal(5);
		assertEquals("Account balance was not correct.", 5.0, account.getBalance(), 0);
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	@Category(GoodTestsCategory.class)
	public void WhenAmountProvidedNegativeExceptionIsThrown() throws InvalidAmountException {
		thrown.expect(InvalidAmountException.class);
//		thrown.expectMessage("Negative values are not allowed");
		thrown.expectMessage(containsString("not allowed")); // Case sensitive 
		account.makeDeposit(-5.0);
	}
	
//	@Rule
//	public Timeout timeout = new Timeout(20);
	
	@Test
	@Ignore
	public void timeoutTest() {
		for (int i = 0; i < 100000; i++) {
			System.out.println("Timeout did not occur");
		}
	}
	
	@Test
	public void WhenGoalIsMetHistoryIsUpdated() throws InvalidAmountException {
		Mockery context = new Mockery();
		final Notifier mockNotifier = context.mock(Notifier.class);
		account = new Account(mockNotifier);
		context.checking(new Expectations() {{
			// It's will pass if we get ONE 'goal met' result 
			oneOf(mockNotifier).send("goal met");
			// It will pass if return true
			will(returnValue(true));
		}});
		
		account.makeDeposit(200.0);
		
		TransactionHistoryItem result = account.getHistory().get(0);
		assertEquals("sent: goal met", result.getMessage());
		
		context.assertIsSatisfied();
	}

}
