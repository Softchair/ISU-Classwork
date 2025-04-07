package lab6;

import java.util.Date;

public class CheckingAccount extends Account {
    private static final double MAX_DEPOSIT_PER_DAY = 5000;
    private static final double MAX_WITHDRAWAL_PER_DAY = 500;

    public CheckingAccount() {
        super("Checking");
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
        if (amount <= MAX_WITHDRAWAL_PER_DAY) {
            if (balance - amount >= 0) {
                balance -= amount;
            } else {
                System.out.println("Insufficient funds.");
            }
        } else {
            System.out.println("Withdrawal limit exceeded for today.");
        }
    }

    @Override
    public void transfer(double amount, Account targetAccount) {
        if (amount <= balance) {
            balance -= amount;
            targetAccount.deposit(amount);
        } else {
            System.out.println("Insufficient funds for transfer.");
        }
    }

    public void payBill(UtilityAccount utilityAccount, double amount) {
        if (amount <= balance) {
            balance -= amount;
            utilityAccount.addBillPayment(amount, new Date());
        } else {
            System.out.println("Insufficient funds for bill payment.");
        }
    }
}


