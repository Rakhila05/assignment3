import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/bank";
    private static final String USER = "postgres";
    private static final String PASS = "1908";
    public static Scanner scanner = new Scanner(System.in);

    public Main() throws SQLException {
    }


    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }


    public static void main(String[] args) {
        createTable();
        Bank bank = new Bank();
        ScannerProvider scannerProvider = new ScannerProvider();
        MainMenu mainMenu = new MainMenu(bank, scannerProvider);
        mainMenu.run();
    }


    public static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS bank (id serial PRIMARY KEY, balance INTEGER NOT NULL, OwnerName VARCHAR(255));";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    static void insertBank(int balance){
        String sql = "INSERT INTO bank (balance) VALUES(?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, balance);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    static void insertName(String OwnerName){
        String sql = "INSERT INTO bank (OwnerName) VALUES(?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(2, OwnerName);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void viewBank() {
        String sql = "SELECT * FROM bank";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("ID\tOwnerName\tbalance");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t"
                        + rs.getInt("OwnerName") + "\t\t"
                        + rs.getInt("balance") + "\t\t");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void clearBank() {
        String sql = "DELETE FROM bank";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public boolean updateBalance(int balance){
        Connection con = null;
        try {
            String sql = "UPDATE books set balance = ? WHERE id = ?;";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, balance);
            st.executeUpdate();
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }return false;
    }
}
