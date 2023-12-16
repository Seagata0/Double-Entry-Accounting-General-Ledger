public abstract class Ledger {
    private String ledgerName;
    private double totalDebit;
    private double totalCredit;

    public String getLedgerName() {
        return ledgerName;
    }

    public void setLedgerName(String ledgerName) {
        this.ledgerName = ledgerName;
    }
    
    public void ledger(String ledgerName){
        setLedgerName(ledgerName);
    }
    
    public void setTotalDebit(double totalDebit) {
        this.totalDebit = totalDebit;
    }

    public void setTotalCredit(double totalCredit) {
        this.totalCredit = totalCredit;
    }
    
    public double getTotalCredit() {
        return totalCredit;
    }

    public double getTotalDebit() {
        return totalDebit;
    }
}
