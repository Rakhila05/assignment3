class MainMenu extends Bank{
    private final Bank bank;
    private final ScannerProvider scannerProvider;
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/bank";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "1908";

    public MainMenu(Bank bank, ScannerProvider scannerProvider) {
        this.bank = bank;
        this.scannerProvider = scannerProvider;
    }

    public void run() {
        Main.createTable();
        while (true) {
            System.out.println("1: to log in as admin");
            System.out.println("2: Create a new bank account");
//            System.out.println("3: Replenish a bank account");
            System.out.println("3: View all accounts");
            int choice = scannerProvider.getScanner().nextInt();

            if (choice == 1) {
                logInAsAdmin();
            } else if (choice == 2) {
                createNewBankAccount();
                Main.insertName(Bank.getAccounts());
                Main.insertBank(BankAccount.getBalance());
//            } else if (choice == 3) {
////                replenishBankAccount();

            }else if (choice == 3){
                Main.viewBank();
            }
            else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    private void logInAsAdmin() {
        AdminMenu adminMenu = new AdminMenu(bank, scannerProvider);
        adminMenu.run();
    }

    private void createNewBankAccount() {
        System.out.println("Enter new account owner's name:");
        String ownerName = scannerProvider.getScanner().next();
        System.out.println("Enter initial deposit amount:");
        int initialDeposit = (int) scannerProvider.getScanner().nextDouble();

        bank.createNewAccount(ownerName, initialDeposit);
    }

//    private void replenishBankAccount() {
//        System.out.println("Enter account number:");
//        int accountNumber = scannerProvider.getScanner().nextInt();
//        System.out.println("Enter deposit amount:");
//        double depositAmount = scannerProvider.getScanner().nextDouble();
//        bank.replenishAccount(accountNumber, depositAmount);
//
//    }
    public void updateBalance(int id ,int balance){
        int updated = BankAccount.getAccountNumber();
    }
}