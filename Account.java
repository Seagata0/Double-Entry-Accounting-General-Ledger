import java.util.List;
import java.util.ArrayList;

public class Account {
    private String accName;
    private double totalDebit;
    private double totalCredit;
    private boolean isDebit;
    private List<paymentCredit> cr;
    private List<paymentDebit> dr;
    private List<Boolean> debit;

    public Account(String accName, boolean isDebit) {
        this.cr = new ArrayList<>();
        this.dr = new ArrayList<>();
        this.debit = new ArrayList<>();
        this.accName = accName;
        this.isDebit = isDebit;
    }

    public void update() {
        setTotalCredit(0.0); 
        setTotalDebit(0.0);
        for(int i=0; i<cr.size();i++){
            setTotalCredit(getTotalCredit() + cr.get(i).getCredit());
        }
        for(int j=0; j<dr.size();j++){
            setTotalDebit(getTotalDebit() + dr.get(j).getDebit());
        }
    }
     
    public void addDebit(paymentDebit Dr) {
        dr.add(Dr);
        debit.add(true);
        update();
    }
    
    public void addCredit(paymentCredit Cr) {
        cr.add(Cr);
        debit.add(false);
        update();
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public void setIsDebit(boolean isDebit) {
        this.isDebit = isDebit;
    }

    public String getAccName() {
        return accName;
    }

    public boolean isIsDebit() {
        return isDebit;
    }

    public List<paymentCredit> getCr() {
        return cr;
    }

    public List<paymentDebit> getDr() {
        return dr;
    }

    public List<Boolean> getDebit() {
        return debit;
    }
    

    public void setTotalCredit(double totalCredit) {
        this.totalCredit = totalCredit;
    }

    public void setTotalDebit(double totalDebit) {
        this.totalDebit = totalDebit;
    }
    
    public double getTotalCredit() {
        return totalCredit;
    }

    public double getTotalDebit() {
        return totalDebit;
    }
}
