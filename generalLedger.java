import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class generalLedger extends Ledger {
    public Map<String, subLedger> subLedgers;
    Scanner scanner = new Scanner(System.in);

    public generalLedger(String ledgerName){
        setLedgerName(ledgerName);
        subLedgers = new HashMap<>();
    }
    
    public void createSubledger(String subledgerName) {
        subLedger newSubledger = new subLedger(subledgerName);
        subLedgers.put(subledgerName, newSubledger);
    }
    
    public void updateGeneralLedger(){
        setTotalCredit(0.0); 
        setTotalDebit(0.0);
        for (subLedger sLedger : subLedgers.values()) {
            sLedger.updateSubLedger();
            setTotalDebit(getTotalDebit() + sLedger.getTotalDebit());
            setTotalCredit(getTotalCredit() + sLedger.getTotalCredit());
        }
    }

    public Map<String, subLedger> getSubLedgers() {
        return subLedgers;
    }
}
