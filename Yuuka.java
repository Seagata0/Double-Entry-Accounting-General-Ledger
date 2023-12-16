import java.util.InputMismatchException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Yuuka {
    static int pilihan = 0;
    static Scanner scanner = new Scanner(System.in);
    static generalLedger GLedger;
    static subLedger SLedger;
    static List<journalEntry> JEList;
    
    public static int pilihan(int pilihan){
        pilihan = 0;
        System.out.println("========== Menu ==========");
        System.out.println("1. Input J/E");
        System.out.println("2. Delete J/E");
        System.out.println("3. Initialize General Ledger");
        System.out.println("4. Create Subledger");
        System.out.println("5. Create Account");
        System.out.println("6. Print");
        System.out.println("0. Exit");
        System.out.println("==========================");   
        System.out.print("Masukkan Pilihan: ");
        while (pilihan < 1 || pilihan > 9){
            try {
                pilihan = scanner.nextInt();
            } catch(InputMismatchException e) {
                System.out.println("Input yang dimasukkan tidak Valid");
                System.out.print("Masukkan Pilihan Sekali Lagi: ");
            }
        }
        return pilihan;
    }

    public static subLedger findSLedger(String SLedgerName){
        for (subLedger subledger : GLedger.getSubLedgers().values()){
            if (subledger.getLedgerName().equals(SLedgerName)){
                return subledger; 
            }
        }
        return null;
    }

    public static void main(String[] args) {
        String accName, generalLedgerName, subLedgerName;
        boolean isDebit;
        JEList = new ArrayList<>();

        pilihan = pilihan(pilihan);
        while(true){
            if(pilihan == 1) {
                journalEntry JE = new journalEntry(GLedger);
                JEList.add(JE);
                GLedger.updateGeneralLedger();
            }else if(pilihan == 2){

            }else if(pilihan == 3){
                System.out.print("Masukkan Nama General Ledger: ");
                generalLedgerName = scanner.next();
                GLedger = new generalLedger(generalLedgerName);
            }else if(pilihan == 4){
                System.out.print("Masukkan Nama Sub Ledger: ");
                subLedgerName = scanner.next();
                GLedger.createSubledger(subLedgerName);
            }else if(pilihan == 5){
                System.out.print("Masukkan Nama Sub Ledger: ");
                String SLName = scanner.next();
                SLedger = findSLedger(SLName);
                while(SLedger == null){
                    System.out.println("Sub Ledger tidak ditemukan");
                    System.out.print("Masukkan Nama Sub Ledger sekali lagi: ");
                    SLName = scanner.next();
                    SLedger = findSLedger(SLName);
                }
                System.out.print("Enter nama Account: ");
                accName = scanner.next();
                try {
                    System.out.print("Apakah Account Debit?(true/false): ");
                    isDebit = scanner.nextBoolean();
                } catch (InputMismatchException e){
                    System.out.println("Input Salah! Pastikan input berupa true/false");
                    System.out.print("Apakah Account Debit?(true/false): ");
                    isDebit = scanner.nextBoolean();
                }
                SLedger.createAccount(accName, isDebit);
            }else if(pilihan == 6){
                System.out.println("========== Menu ==========");
                System.out.println("1. T-Account");
                System.out.println("2. Print General Ledger");
                System.out.println("3. Print Balance Sheet");
                System.out.println("==========================");   
                System.out.println("Masukkan Pilihan: ");
                while (pilihan < 1 || pilihan > 3){
                    try {
                        pilihan = scanner.nextInt();
                    } catch(InputMismatchException e) {
                        System.out.println("Input yang dimasukkan tidak Valid");
                        System.out.print("Masukkan Pilihan Sekali Lagi: ");
                    }
                }

                if(pilihan == 1){

                }else if(pilihan == 2){
                    for (int i=-2; i<=GLedger.getLedgerName().length();i++){
                        System.out.print("=");
                    }
                    System.out.println("=");
                    System.out.println("| "+ GLedger.getLedgerName() +" |");
                    for (int i=-2; i<=GLedger.getLedgerName().length();i++){
                        System.out.print("=");
                    }
                    System.out.println("=");
                    System.out.println("\nSub Ledger");
                    int x = 0;
                    for (subLedger sLedger : GLedger.subLedgers.values()) {
                        System.out.print("\n+");
                        for (int i=-1; i<=sLedger.getLedgerName().length();i++){
                            System.out.print("-");
                        }
                        System.out.println("+");
                        System.out.println("| "+sLedger.getLedgerName()+" |");
                        System.out.print("+");
                        for (int i=-1; i<=sLedger.getLedgerName().length();i++){
                            System.out.print("-");
                        }
                        System.out.println("+");
                        for(Account acc : sLedger.accounts.values()){
                            System.out.println(" "+acc.getAccName()+" ");
                            for (int i=1; i<=48;i++){
                                System.out.print("-");
                            }
            
                            if(acc.getDr().isEmpty()==false){
                                //print tanggal
                                isDebit = acc.getDebit().get(x)==true;
                                if(isDebit){
                                    System.out.print(acc.getDr().get(x).getTanggal());
                                }else if(!isDebit){
                                    System.out.print(acc.getCr().get(x).getTanggal());
                                }
                                
                                System.out.print("   ");
            
                                //print nominal
                                if(isDebit){
                                    System.out.print(acc.getDr().get(x).getDebit());
                                }
                                System.out.print("\t\t\t |");
                                if(!isDebit){
                                    System.out.print(acc.getCr().get(x).getCredit());
                                }
                                x++;
                            }
                        }
                        System.out.print("Totals  "+SLedger.getTotalCredit()+"\t\t\t | "+SLedger.getTotalDebit());
                    }
                }else if(pilihan == 3){

                }
            }else if(pilihan == 0){
                break;
            } 
            pilihan = pilihan(pilihan);
        }
    }
}
