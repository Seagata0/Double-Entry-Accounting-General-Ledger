package generalLedgerYuuka;

import java.util.Date;

public class journalEntry {
    private int jenumber;
    private Date date;
    private String description;
    private int amount;
    private int dr_acc_id;
    private int cr_acc_id;
    
    public journalEntry() {}
    
    public journalEntry(int jenumber, Date date, String description, int amount, int dr_acc_id, int cr_acc_id) {
        this.jenumber = jenumber;
        this.date = date;
        this.description = description;
        this.amount = amount;
        this.dr_acc_id = dr_acc_id;
        this.cr_acc_id = cr_acc_id;
    }

    public void setJenumber(int jenumber) {
        this.jenumber = jenumber;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setDr_acc_id(int dr_acc_id) {
        this.dr_acc_id = dr_acc_id;
    }

    public void setCr_acc_id(int cr_acc_id) {
        this.cr_acc_id = cr_acc_id;
    }

    public int getJenumber() {
        return jenumber;
    }

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public int getAmount() {
        return amount;
    }

    public int getDr_acc_id() {
        return dr_acc_id;
    }

    public int getCr_acc_id() {
        return cr_acc_id;
    }
    
}