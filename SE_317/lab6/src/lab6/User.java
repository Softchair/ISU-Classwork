package lab6;

public class User {
    private String name;
    private CheckingAccount checkingAccount;
    private SavingAccount savingAccount;

    public User(String name) {
        this.name = name;
        checkingAccount = null;
        savingAccount = null;
    }

    public void setCheckingAccount(CheckingAccount checkingAccount) throws Exception {
        if (this.checkingAccount == null) {
            this.checkingAccount = checkingAccount;
        } else {
            throw new Exception("Already existing checking account");
        }
    }

    public void setSavingAccount(SavingAccount savingAccount) throws Exception {
        if (this.savingAccount == null) {
            this.savingAccount = savingAccount;
        } else {
            throw new Exception("Already existing savings account");
        }
    }

    public CheckingAccount getCheckingAccount() {
        return checkingAccount;
    }

    public SavingAccount getSavingAccount() {
        return savingAccount;
    }

    public String getName() {
        return name;
    }
}

