package lab6;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class BillTest {

    // 1.5.1
    @Test
    public void testPayBillFromCheckingAccountToUtilityCompanyAccount() {
        UtilityCompany utilityCompany = new UtilityCompany();
        UtilityAccount utilityAccount = utilityCompany.createAccount("validUser", "validPass");
        CheckingAccount checkingAccount = new CheckingAccount();
        double billAmount = 100;
        Date paymentDate = new Date();

        // Assuming there's a method to pay a bill from a checking account to a utility company account
        checkingAccount.payBill(utilityAccount, billAmount);

        assertEquals(billAmount, utilityAccount.getLastThreeBillPayments().get(0).getAmount(), 0.01);
    }

    // 1.5.2
    @Test
    public void testPayBillWithInsufficientFunds() {
        UtilityCompany utilityCompany = new UtilityCompany();
        UtilityAccount utilityAccount = utilityCompany.createAccount("validUser", "validPass");
        CheckingAccount checkingAccount = new CheckingAccount();
        double insufficientAmount = 1000; // Assuming the checking account has less than this amount
        Date paymentDate = new Date();

        // Assuming there's a method to pay a bill from a checking account to a utility company account
        checkingAccount.payBill(utilityAccount, insufficientAmount);
    }

    // 1.5.3
    @Test
    public void testCheckBillPaymentHistory() {
        UtilityCompany utilityCompany = new UtilityCompany();
        UtilityAccount utilityAccount = utilityCompany.createAccount("validUser", "validPass");
        CheckingAccount checkingAccount = new CheckingAccount();
        Date paymentDate = new Date();

        // Assuming there's a method to pay a bill from a checking account to a utility company account
        checkingAccount.payBill(utilityAccount, 100, paymentDate);
        checkingAccount.payBill(utilityAccount, 200, paymentDate);
        checkingAccount.payBill(utilityAccount, 300, paymentDate);
        checkingAccount.payBill(utilityAccount, 400, paymentDate);

        List<BillPayment> lastThreePayments = utilityAccount.getLastThreeBillPayments();

        assertEquals(3, lastThreePayments.size());
        assertEquals(300, lastThreePayments.get(0).getAmount(), 0.01);
        assertEquals(200, lastThreePayments.get(1).getAmount(), 0.01);
        assertEquals(100, lastThreePayments.get(2).getAmount(), 0.01);
    }

    // 1.5.4
    @Test
    public void testCheckNextBillPaymentAmountAndDueDate() {
        UtilityCompany utilityCompany = new UtilityCompany();
        UtilityAccount utilityAccount = utilityCompany.createAccount("validUser", "validPass");
        Date paymentDate = new Date();

        // Assuming there's a method to pay a bill from a checking account to a utility company account
        utilityAccount.addBillPayment(100, paymentDate);
        utilityAccount.addBillPayment(200, paymentDate);
        utilityAccount.addBillPayment(300, paymentDate);

        BillPayment nextBillPayment = utilityAccount.getNextBillPayment();

        assertNotNull(nextBillPayment);
        assertEquals(300, nextBillPayment.getAmount(), 0.01);
        assertEquals(paymentDate, nextBillPayment.getPaymentDate());
    }

}
