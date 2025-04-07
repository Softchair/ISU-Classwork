package lab6;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UtilityTests {

    UtilityAccount curAcc;

    @Before
    public void setup() {
        curAcc = new UtilityAccount("Username", "Password");
    }


    // 1.4.1
    @Test
    public void testCreateNewUtilityCompanyAccount() {
        UtilityCompany utilityCompany = new UtilityCompany();
        UtilityAccount utilityAccount = utilityCompany.createAccount("validUser", "validPass");

        assertNotNull(utilityAccount);
        assertEquals("validUser", utilityAccount.getUsername());
        assertNotNull(utilityAccount.getAccountNumber());
    }

    // 1.4.2
    @Test
    public void testCreateUtilityCompanyAccountWithExistingUsername() {
        UtilityCompany utilityCompany = new UtilityCompany();
        utilityCompany.createAccount("existingUser", "validPass");
        UtilityAccount secondAccount = utilityCompany.createAccount("existingUser", "anotherPass");

        assertNull(secondAccount);
    }

    // 1.4.3
    @Test
    public void testLoginToUtilityCompanyAccountWithValidCredentials() {
        UtilityCompany utilityCompany = new UtilityCompany();
        utilityCompany.createAccount("validUser", "validPass");
        UtilityAccount foundAccount = utilityCompany.findAccountByUsername("validUser");

        assertNotNull(foundAccount);
        assertEquals("validUser", foundAccount.getUsername());
    }

    // 1.4.4
    @Test
    public void testLoginToUtilityCompanyAccountWithInvalidCredentials() {
        UtilityCompany utilityCompany = new UtilityCompany();
        utilityCompany.createAccount("validUser", "validPass");
        UtilityAccount foundAccount = utilityCompany.findAccountByUsername("invalidUser");

        assertNull(foundAccount);
    }


}
