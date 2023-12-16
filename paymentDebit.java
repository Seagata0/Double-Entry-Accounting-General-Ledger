import java.util.Date;

public class paymentDebit {
    private Date tanggal;
    private double amount;

    public paymentDebit(double amount, Date tanggal) {
        setDebit(amount);
        setTanggal(tanggal);
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setDebit(double amount) {
        this.amount = amount;
    }

    public void add(double amount){
        setDebit(getDebit()+amount);
    }

    public double getDebit() {
        return amount;
    }
}
