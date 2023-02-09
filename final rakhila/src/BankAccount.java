public class BankAccount implements Interface{
    private static int accountNumber;
    private static String ownerName;
    public static int balance;

    public BankAccount(int accountNumber, String ownerName, int balance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
    }

    public static int getAccountNumber() {
        return accountNumber;
    }

    public static String getOwnerName() {
        return ownerName;
    }

    public static int getBalance() {
        return balance;
    }

    @Override
    public void deposit(double amount) {
        if (amount>0){
            balance += amount;
        } else{
            System.out.println("Invalid input");
        }
    }
    @Override
    public void withdraw(double amount) {
        balance -= amount;
    }
}
