import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class subLedger extends Ledger {
    public Map<String, Account> accounts;
    Scanner scanner = new Scanner(System.in);

    public subLedger(String ledgerName){
        setLedgerName(ledgerName);
        accounts = new HashMap<>();
    }
    
    public void createAccount(String accName, Boolean isDebit) {
        Account newAccount = new Account(accName, isDebit);
        accounts.put(accName, newAccount);
    }
    
    public void updateSubLedger(){ 
        setTotalCredit(0.0); 
        setTotalDebit(0.0);
        for (Account akun : accounts.values()) {
            setTotalDebit(getTotalDebit() + akun.getTotalDebit());
            setTotalCredit(getTotalCredit() + akun.getTotalCredit());
        }
    }

    public Account getAccount(String accName) {
        return accounts.get(accName);
    }
}
