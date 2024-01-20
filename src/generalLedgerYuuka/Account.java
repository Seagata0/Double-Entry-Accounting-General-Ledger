package generalLedgerYuuka;

public class Account extends subLedger {
    private int account_id, total_credit, total_debit, subledger_id;
    private String debit, account_name;
    
    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public String getDebit() {
        return debit;
    }

    public void setDebit(String debit) {
        this.debit = debit;
    }

    public int getTotal_credit() {
        return total_credit;
    }

    public void setTotal_credit(int total_credit) {
        this.total_credit = total_credit;
    }

    public int getTotal_debit() {
        return total_debit;
    }

    public void setTotal_debit(int total_debit) {
        this.total_debit = total_debit;
    }

    public int getSubledger_id() {
        return subledger_id;
    }

    public void setSubledger_id(int subledger_id) {
        this.subledger_id = subledger_id;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }      
}
