
public class Customer {
    private String number;
    private String firstName;
    private String lastName;
    private List<Account> accountList;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void addAccount(Account account) {
        this.accountList.add(account);
    }

    public List<Account> getAccountList() {
        return accountList;
    }
}