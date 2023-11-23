/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectpbo;

/**
 *
 * @author ACER
 */
public class Account {
    private String accName;
    private double totalDebit;
    private double totalCredit;
    private Payment[] cr;
    private Payment[] dr;
    private boolean isDebit;

    public Account(String accName, boolean isDebit) {
        this.accName = accName;
        this.isDebit = isDebit;
    }

    public void update() {
        Subledger.updateSubledger(this);
    }
     
    public void addDebit(Double amount) {
        totalDebit += amount;
        Payment payment = new Payment(amount);
        dr = addPayment(dr, payment);
        update();
    }
    
    public void addCredit(Double amount) {
        totalCredit += amount;
        Payment payment = new Payment(amount);
        cr = addPayment(cr, payment);
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
      
}
