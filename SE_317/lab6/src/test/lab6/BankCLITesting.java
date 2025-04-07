package lab6;

import java.util.List;

public class BankCLITesting {
    public static void main(String[] args) {

    }
}


// Testing code
//        User user = new User("John Doe");
//        CheckingAccount checkingAccount = new CheckingAccount();
//        SavingAccount savingAccount = new SavingAccount();
//
//        user.setCheckingAccount(checkingAccount);
//        user.setSavingAccount(savingAccount);
//
//        // Deposit and withdraw from checking account
//        checkingAccount.deposit(1000);
//        checkingAccount.withdraw(500);
//
//        // Transfer from checking to saving account
//        checkingAccount.transfer(200, savingAccount);
//
//        // Check balances
//        System.out.println("Checking Account Balance: " + checkingAccount.getBalance());
//        System.out.println("Saving Account Balance: " + savingAccount.getBalance());
//
//        UtilityCompany utilityCompany = new UtilityCompany();
//        UtilityAccount utilityAccount = utilityCompany.createAccount("user123", "password123");
//
//        // Assuming a user has a checking account
//        CheckingAccount checkingAccount = new CheckingAccount();
//        checkingAccount.deposit(1000); // Deposit some money
//
//        // Pay a bill to the utility company
//        checkingAccount.payBill(utilityAccount, 500);
//
//        // Check bill payment history
//        List<BillPayment> lastThreePayments = utilityAccount.getLastThreeBillPayments();
//        for (BillPayment payment : lastThreePayments) {
//            System.out.println("Amount: " + payment.getAmount() + ", Date: " + payment.getPaymentDate());
//        }
//
//        // Check next bill payment amount and due date
//        BillPayment nextPayment = utilityAccount.getNextBillPayment();
//        if (nextPayment != null) {
//            System.out.println("Next Bill Payment: " + nextPayment.getAmount() + ", Due Date: " + nextPayment.getPaymentDate());
//        }