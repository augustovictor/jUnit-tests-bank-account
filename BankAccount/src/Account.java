
public class Account {
    private String number = "123";
    private Double balance = 0.0;

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
    	if(amount < 0) throw new InvalidAmountException();
        setBalance(this.balance += amount);
    }

    public void makeWithdrawal(double amount) throws InvalidAmountException {
    	if(amount < 0) throw new InvalidAmountException();
        setBalance(this.balance -= amount);
        if(getBalance() < 0) {
        	setBalance(0.0);
        }
    }
}
