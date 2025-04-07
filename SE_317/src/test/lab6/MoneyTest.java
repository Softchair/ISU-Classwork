package lab6;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MoneyTest {

    User curUser;

    @Before
    public void setup() throws Exception {
        curUser = new User("Test");
        curUser.setCheckingAccount(new CheckingAccount());
        curUser.setSavingAccount(new SavingAccount());

    }

    // 1.2.1
    @Test
    public void testDepositMaxAmountInCheckingAccount() {
        double maxAmount = 5000; // Assuming the maximum deposit amount is $5k
        curUser.getCheckingAccount().deposit(maxAmount);
        assertEquals(maxAmount, curUser.getCheckingAccount().getBalance(), 0.01);
    }

    // 1.2.2
    @Test
    public void testDepositOverMaxAmountInCheckingAccount() {
        double maxAmount = 5001; // Assuming the maximum deposit amount is $5k
        curUser.getCheckingAccount().deposit(maxAmount);
        assertEquals(0, curUser.getCheckingAccount().getBalance(), 0.01);
    }

    // 1.2.3
    @Test
    public void testWithdrawMaxAmountFromCheckingAccount() {
        double maxAmount = 5000; // Assuming the maximum withdrawal amount is $5k
        curUser.getCheckingAccount().deposit(maxAmount); // First deposit the max amount
        curUser.getCheckingAccount().withdraw(500);
        assertEquals(4500, curUser.getCheckingAccount().getBalance(), 0.01);
    }

    // 1.2.4
    @Test
    public void testWithdrawOverMaxAmountFromCheckingAccount() {
        double maxAmount = 5000; // Assuming the maximum withdrawal amount is $5k
        curUser.getCheckingAccount().deposit(maxAmount); // First deposit the max amount
        curUser.getCheckingAccount().withdraw(maxAmount + 1); // Attempt to withdraw more than the max
        assertEquals(5000, curUser.getCheckingAccount().getBalance(), 0.01);
    }

    // 1.2.5
    @Test
    public void testDepositMaxAmountInSavingAccount() {
        double maxAmount = 5000; // Assuming the maximum deposit amount is $5k
        curUser.getSavingAccount().deposit(maxAmount);
        assertEquals(maxAmount, curUser.getSavingAccount().getBalance(), 0.01);
    }

    // 1.2.6
    @Test
    public void testDepositOverMaxAmountInSavingAccount() {
        double overMaxAmount = 5001; // Assuming the maximum deposit amount is $5k
        curUser.getSavingAccount().deposit(overMaxAmount);
        assertEquals(0, curUser.getSavingAccount().getBalance(), 0.01);
    }

    // 1.2.7
    @Test
    public void testWithdrawFromSavingAccount() {
        double amount = 100; // Any amount
        curUser.getSavingAccount().deposit(amount); // First deposit some amount
        curUser.getSavingAccount().withdraw(amount); // Attempt to withdraw
        assertEquals(100, curUser.getSavingAccount().getBalance(), 0.01);
    }

    // 1.3.1
    @Test
    public void testTransferMaxAmountFromCheckingToSavingAccount() {
        double maxAmount = 5000; // Assuming the maximum transfer amount is $5
        curUser.getCheckingAccount().deposit(maxAmount); // First deposit the max amount into checking account
        curUser.getCheckingAccount().transfer(maxAmount, curUser.getSavingAccount());
        assertEquals(0, curUser.getCheckingAccount().getBalance(), 0.01);
        assertEquals(maxAmount, curUser.getSavingAccount().getBalance(), 0.01);
    }

    // 1.3.2
    @Test
    public void testTransferMaxAmountFromSavingToCheckingAccount() {
        double maxAmount = 5000; // Assuming the maximum transfer amount is $5k
        curUser.getSavingAccount().deposit(maxAmount); // First deposit the max amount into saving account
        curUser.getSavingAccount().transfer(100, curUser.getCheckingAccount());
        assertEquals(4900, curUser.getSavingAccount().getBalance(), 0.01);
        assertEquals(100, curUser.getCheckingAccount().getBalance(), 0.01);
    }

    // 1.3.4
    @Test
    public void testTransferOverMaxAmountFromSavingToCheckingAccount() {
        double overMaxAmount = 101; // Assuming the maximum transfer amount is $50,000
        curUser.getSavingAccount().deposit(overMaxAmount); // First deposit the over max amount into saving account
        curUser.getSavingAccount().transfer(overMaxAmount, curUser.getCheckingAccount());
        assertEquals(101, curUser.getSavingAccount().getBalance(), 0.01);
    }



}
