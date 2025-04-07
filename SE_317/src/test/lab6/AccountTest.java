package lab6;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {

    User curUser;

    @Before
    public void setup() {
        curUser = new User("Default");
    }

    // 1.1.1
    @Test
    public void testCreateUserWithCheckingAndSavingAccount() throws Exception {
        CheckingAccount checkingAccount = new CheckingAccount();
        SavingAccount savingAccount = new SavingAccount();

        curUser.setCheckingAccount(checkingAccount);
        curUser.setSavingAccount(savingAccount);

        assertNotNull(curUser.getCheckingAccount());
        assertNotNull(curUser.getSavingAccount());
    }

    // 1.1.2, expect to fail
    @Test
    public void testCreateSecondCheckingAccountForSameUser() throws Exception {
        CheckingAccount firstCheckingAccount = new CheckingAccount();
        CheckingAccount secondCheckingAccount = new CheckingAccount();

        curUser.setCheckingAccount(firstCheckingAccount);
        curUser.setCheckingAccount(secondCheckingAccount);

    }

    // 1.1.3
    @Test
    public void testCreateUtilityAccountForUser() {
        UtilityAccount utilityAccount = new UtilityAccount("user1", "password123");

        assertNotNull(utilityAccount);
        assertEquals("user1", utilityAccount.getUsername());
        assertNotNull(utilityAccount.getAccountNumber());
    }

}

