import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
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
		account = new Account();
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
	
	@Test(expected = InvalidAmountException.class)
	@Category(GoodTestsCategory.class)
	public void WhenAmountProvidedNegativeExceptionIsThrown() throws InvalidAmountException {
		account.makeDeposit(-5.0);
	}
	
	@Test(timeout = 200)
	@Ignore
	public void timeoutTest() {
		for (int i = 0; i < 100000; i++) {
			System.out.println("Timeout did not occur");
		}
	}

}
