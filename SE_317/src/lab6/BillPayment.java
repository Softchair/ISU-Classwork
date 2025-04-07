package lab6;

import java.util.Date;

public class BillPayment {
    private double amount;
    private Date paymentDate;

    public BillPayment(double amount, Date paymentDate) {
        this.amount = amount;
        this.paymentDate = paymentDate;
    }

    public double getAmount() {
        return amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }
}
