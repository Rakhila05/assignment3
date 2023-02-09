import java.util.HashMap;
import java.util.Map;

class Bank {
    private static final Map<Integer, BankAccount> accounts = new HashMap<>();
    private final Map<String, String> admins = new HashMap<>();
    public static String getAccounts(){
        return accounts.toString();
    }

    public Bank() {
        // Add admin accounts
        admins.put("admin1", "password1");
        admins.put("admin2", "password2");
    }

    public boolean logInAsAdmin(String username, String password) {
        String correctPassword = admins.get(username);
        if (correctPassword == null || !correctPassword.equals(password)) {
            return false;
        }
        return true;
    }

    public void createNewAccount(String ownerName, int initialDeposit) {
        int newAccountNumber = accounts.size() + 1;
        BankAccount newAccount = new BankAccount(newAccountNumber, ownerName, initialDeposit);
        accounts.put(newAccountNumber, newAccount);
        System.out.println("Created new account with account number " + newAccountNumber + " and owner " + ownerName);
    }

//    public void replenishAccount(int accountNumber, double depositAmount) {
//        BankAccount account = accounts.get(accountNumber);
//        if (account == null) {
//            System.out.println("Invalid account number. Could not replenish account.");
//            return;
//        }
//        account.deposit(depositAmount);
//    }



    public void viewAccount(int accountNumber) {
        BankAccount account = accounts.get(accountNumber);
        if (account == null) {
            System.out.println("Invalid account number.");
            return;
        }

        System.out.println("Account number: " + account.getAccountNumber());
        System.out.println("Owner name: " + account.getOwnerName());
        System.out.println("Balance: " + account.getBalance());
    }
}