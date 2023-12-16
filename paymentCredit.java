import java.util.Date;

public class paymentCredit {
    private Date tanggal;
    private double amount;

    public paymentCredit(double amount, Date tanggal) {
        setCredit(amount);
        setTanggal(tanggal);
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setCredit(double amount) {
        this.amount = amount;
    }

    public void add(double amount){
        setCredit(getCredit()+amount);
    }

    public double getCredit() {
        return amount;
    }
}
