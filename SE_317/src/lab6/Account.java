package lab6;

public abstract class Account {
    protected double balance;
    protected String accountType;

    public Account(String accountType) {
        this.accountType = accountType;
        this.balance = 0;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public abstract void deposit(double amount);
    public abstract void withdraw(double amount);
    public abstract void transfer(double amount, Account targetAccount);
}

