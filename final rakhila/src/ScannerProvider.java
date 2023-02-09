import java.util.Scanner;

class ScannerProvider {
    private final Scanner scanner = new Scanner(System.in);
    public  Scanner getScanner() {
        return scanner;
    }
}