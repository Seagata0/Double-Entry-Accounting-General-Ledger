# **Tugas Besar Pemrograman Berbasis Object** _Double-Entry Accounting General Ledger_
## Kelompok 4
- Seagata
- Andi Ahmad
- Rivan
- Mursyid Najib Muhana (1301210411)
- Rashad

<br>

# **Cara Kerja**
![img1](img/1.png)
## General Ledger
General ledger/buku besar adalah catatan akuntansi yang mengkompilasi setiap transaksi keuangan perusahaan untuk memberikan entri yang akurat untuk laporan keuangan.

General Ledger memiliki catatan transaksi keuangan secara umum, dan tediri dari berbagai Sub ledger

## Subledger
Subsidiary Ledger atau Buku Besar Pembantu adalah buku yang berisi catatan keuangan yang lebih detail dan spesifik mengenai transaksi tertentu. 

Subledger adalah jenis ledger yang lebih spesifik dan berisikan berbagai akun serta fokus pada satu area tertentu seperti akun  asset, kewajiban(liabilities), ekuitas(Equity), pendapatan(Revenue) dan pengeluaran(expense). Subledger membantu memisahkan transaksi keuangan untuk memudahkan manajemen dan pelacakan.

<br> <br>

![Acc](img/3.png)
## Account
Akun merupakan alat akuntansi yang digunakan untuk mencatat transaksi keuangan yang mengakibatkan perubahan asset, kewajiban(liabilities), ekuitas(Equity), pendapatan(Revenue) dan pengeluaran(expense). 

Account berisikan payment debit dan credit
<br> <br> <br> 

![JE](img/2.png)
## Journal Entry
Journal Entry adalah catatan kronologis transaksi aktifitas bisnis perusahaan.  

Journal Entry berisikan tanggal beserta payment debit/credit yang akan di dimasukkan ke account

## Payment Credit dan Payment Debit
Payment Credit merepresentasikan darimana economic benefit datang, sedangkan Payment Debit merepresentasikan kemana economic benefit pergi. Detailnya lebih baik tidak dijelaskan karena panjang dan itu urusan orang Accountant.

## Driver
Ini adalah kelas yang berfungsi sebagai tampilan antarmuka User dan Software.

<br>

# **Class Diagram**

![Diagram](img/diagram.png)  

# **Class**


## **Yuuka**
>Class Driver untuk melakukan berbagai tugas

**Method**
+ inputJournalEntry()
+ DeleteJournalEntry()
+ Initialize generalLedger()
+ createSubledger()
+ createAcc()
+ printTAccount()
+ printTrialBalance()
+ printBalanceSheet()
+ pilihan() : int

## **journalEntry** 
>Journal Entry menginisialisasi pembuatan payment dan menginputnya pada account

**Variable**
- JENumber: int
- date : date
- desc : String
- namaAccDr : string
- namaAccCr : string
- accDr : Account   
- accCr : account
- dr : paymentDebit
- cr : paymentCredit
- amountDr : double
- amountCr : double


<br>**Method**
+ journalEntry(generalLedger genLed) //melakukan permintaan input pada user berupa JENumber, nama akun cr & dr, amountDr & Cr, dan deskripsi lalu melakukan new dr dan cr memakai konstruktor dari paymentDebit & Credit dan lakukan set JENumber setelah itu find account dan panggil function addDebit & Credit dari account
+ findAccount(string nama Acc, generalLedger genLed) //sega dah buat
+ setJENumber(int JENumber)
+ getJENumber(): int
+ save() //menyimpan dr dan cr ke account (sega dah buat) 


## **Account**
>di isi oleh paymentCredit & Debit


**Variable**
- accName: String
- totalDebit : double
- totalCredit : double
- cr : paymentCredit[]
- dr : paymentDebit[]
- isDebit : boolean

**Method**
+ Account(String accName, boolean isDebit)
+ update() //pada saat addDebit ataupun reduceDebit dipanggil update() juga dipanggil dan update() akan mengakses function updateSubledger pada class subledger
+ addDebit(Double amount)
+ addCredit(Double amount)
+ setAccName(String accName)
+ setIsDebit(boolean isDebit)
+ getAccName() : String
+ getIsDebit() : boolean
  

## **paymentCredit**
>Untuk transaksi tipe credit

**Variable**
- tanggal : Date
- amount : double

**Method**
+ paymentCredit(double amount, tanggal Date, account Account)
+ setCredit(double amount)
+ getCredit(): double  

## **paymentDebit**
>Untuk transaksi tipe Debit

**Variable**
- tanggal : Date
- amount : double

**Method**
+ paymentDebit(double amount, tanggal Date, account Account)
+ setDebit(double amount)
+ getDebit(): double  
<br>

## **_Ledger_**
>abstract untuk generalLedger & subLedger

**Variable**
- ledgerName: String
- totalDebit : double
- totalCredit : double

**Method**
+ ledger(String ledgerName)
+ setLedgerName(String ledgerName)
+ getLedgerName() : String

## **generalLedger**
>extends dari ledger dan di isi oleh banyak subLedger

**Variable**
- subLedgers: HashMap< String, subLedger >
- String subLedgerName

**Method**
+ generalLedger(String ledgerName) //setLedgerName
+ createSubledger(ledger SubledgerName) // new subledger lalu dimasukkin ke dictionary
+ updateGeneralLedger() //sega dah buat
<br>

## **subLedger**
>extends dari ledger dan di isi oleh banyak Account

**Variable**
- accounts: HashMap< String, Account >
- String accName
- boolean isDebit
  
**Method**
+ Subledger(String ledgerName) //setLedgerName
+ createAccount(String accName, boolean isDebit) //new acc lalu dimasukkan ke dictionary
+ updateSubLedger() //sega dah buat
