import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class journalEntry {
    private int JENumber;
    private Date date;
    private String desc;
    private String namaAccDr;
    private String namaAccCr;
    private Account accDr;
    private Account accCr;
    private paymentDebit dr;
    private paymentCredit cr;
    private double amount;
    
    public journalEntry(generalLedger genLedger) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter JENumber: ");
            setJENumber(scanner.nextInt());

            System.out.print("Enter description: ");
            setDesc(scanner.next());

            System.out.print("Enter namaAccDr: ");
            namaAccDr = scanner.next();

            System.out.print("Enter namaAccCr: ");
            namaAccCr = scanner.next();

            System.out.print("Enter amount: ");
            amount = scanner.nextDouble();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter again.");
        }
        
        dr = new paymentDebit(amount, date);
        cr = new paymentCredit(amount, date);

        accDr = findAccount(namaAccDr, genLedger);
        accCr = findAccount(namaAccCr, genLedger);

        accCr.addCredit(cr);
        accDr.addDebit(dr);
        System.out.println("Done!");
    }
    public Account findAccount(String namaAcc, generalLedger gLedger) {
        for (subLedger subledger : gLedger.getSubLedgers().values()) {
            Account account = subledger.getAccount(namaAcc);
            if (account != null) {
                return account;
            }
        }
        return null;
    }

    public void setJENumber(int JENumber) {
        this.JENumber = JENumber;
    }

    public int getJENumber() {
        return JENumber;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
