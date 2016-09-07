import static org.junit.Assert.*;
import org.junit.Test;

public class HelloJUnitTest {

	@Test
	public void NewAccountNumberIs123() {
		Account account = new Account();
		assertEquals("Account number is not 123", "123", account.getNumber());
	}
	
	@Test
	public void WhenMakingDepositAccountBalanceIncreases() {
		Account account = new Account();
		account.makeDeposit(10.0);
		assertEquals("Account balance was not correct.", 10.0, account.getBalance(), 0);
	}

}
