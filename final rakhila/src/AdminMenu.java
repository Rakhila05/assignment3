import java.sql.DriverManager;
import java.sql.Statement;

class AdminMenu {
    private final Bank bank;
    private final ScannerProvider scannerProvider;

    public AdminMenu(Bank bank, ScannerProvider scannerProvider) {
        this.bank = bank;
        this.scannerProvider = scannerProvider;
    }

    public void run() {
        System.out.println("Enter admin username:");
        String username = scannerProvider.getScanner().next();
        System.out.println("Enter admin password:");
        String password = scannerProvider.getScanner().next();

        if (bank.logInAsAdmin(username, password)) {
            System.out.println("Logged in as admin.");
            while (true) {
//                System.out.println("1: to view all accounts");
                System.out.println("1: View a specific account");
                System.out.println("2: log out");
                int choice = scannerProvider.getScanner().nextInt();
//
//                if (choice == 1) {
//                    System.exit(0);

                if (choice == 1) {
                    viewAccount();
                } else if (choice == 2) {
                    System.out.println("Logged out.");
                    break;
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            }
        } else {
            System.out.println("Invalid username or password.");
        }
    }


    private void viewAccount() {
        System.out.println("Enter account number:");
        int accountNumber = scannerProvider.getScanner().nextInt();
        bank.viewAccount(accountNumber);
    }
}
