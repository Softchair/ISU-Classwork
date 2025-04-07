package lab6;

public class SavingAccount extends Account {
    private static final double MAX_DEPOSIT_PER_DAY = 5000;
    private static final double MAX_TRANSFER_PER_DAY = 100;

    public SavingAccount() {
        super("Saving");
    }

    @Override
    public void deposit(double amount) {
        if (amount <= MAX_DEPOSIT_PER_DAY) {
            balance += amount;
        } else {
            System.out.println("Deposit limit exceeded for today.");
        }
    }

    @Override
    public void withdraw(double amount) {
        System.out.println("Withdrawals are not allowed from this account.");
    }

    @Override
    public void transfer(double amount, Account targetAccount) {
        if (amount <= MAX_TRANSFER_PER_DAY && amount <= balance) {
            balance -= amount;
            targetAccount.deposit(amount);
        } else {
            System.out.println("Transfer limit exceeded for today or insufficient funds.");
        }
    }
}
