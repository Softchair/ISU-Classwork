package lab6;

import java.util.ArrayList;
import java.util.List;

import java.util.Date;
import java.util.List;

public class UtilityAccount {
    private String username;
    private String password;
    private String accountNumber;
    private List<BillPayment> billPayments;

    public UtilityAccount(String username, String password) {
        this.username = username;
        this.password = password;
        this.accountNumber = generateAccountNumber();
        this.billPayments = new ArrayList<>();
    }

    private String generateAccountNumber() {
        // Generate a 6-digit account number
        return String.format("%06d", (int) (Math.random() * 1000000));
    }

    public String getUsername() {
        return username;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void addBillPayment(double amount, Date paymentDate) {
        billPayments.add(new BillPayment(amount, paymentDate));
    }

    public List<BillPayment> getLastThreeBillPayments() {
        return billPayments.subList(Math.max(billPayments.size() - 3, 0), billPayments.size());
    }

    public BillPayment getNextBillPayment() {
        // Assuming the next bill payment is the last one in the list
        return billPayments.isEmpty() ? null : billPayments.get(billPayments.size() - 1);
    }
}


