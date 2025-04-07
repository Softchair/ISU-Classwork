package lab6;

import java.util.ArrayList;
import java.util.List;

public class UtilityCompany {
    private List<UtilityAccount> utilityAccounts;

    public UtilityCompany() {
        this.utilityAccounts = new ArrayList<>();
    }

    public UtilityAccount createAccount(String username, String password) {
        UtilityAccount newAccount = new UtilityAccount(username, password);
        utilityAccounts.add(newAccount);
        return newAccount;
    }

    public UtilityAccount findAccountByUsername(String username) {
        return utilityAccounts.stream()
                .filter(account -> account.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }
}

