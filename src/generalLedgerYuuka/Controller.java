package generalLedgerYuuka;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Controller implements Initializable {
    @FXML
    private Label label;
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    Connection conn;
    PreparedStatement stmt;
    
    //GeneralLedger
    @FXML private TextField textGL;   

    @FXML private TextField textSL;
    
    @FXML private TextField textAcc;
    
    @FXML private TextField idSubledger;
    
    @FXML private RadioButton buttonCR;
    @FXML private RadioButton buttonDR;
    
    @FXML private TextField JENumber;
    @FXML private TextField AccDr;
    @FXML private TextField AccCr;
    @FXML private TextField Amount;
    @FXML private TextField Desc;
    
    @FXML private TableView<journalEntry> tableJE;
    @FXML private TableColumn<journalEntry, Integer> colJE;
    @FXML private TableColumn<journalEntry, Date> colDate;
    @FXML private TableColumn<journalEntry, Integer> colDr;
    @FXML private TableColumn<journalEntry, Integer> colCr;
    @FXML private TableColumn<journalEntry, Integer> colJumlah;
    @FXML private TableColumn<journalEntry, String> colDesc;
    
    @FXML private TableView<subLedger> tableSL;
    @FXML private TableColumn<subLedger, Integer> colSLID;
    @FXML private TableColumn<subLedger, String> colSLName;
    
    @FXML private TableView<Account> tableAcc;
    @FXML private TableColumn<Account, Integer> colAccID;
    @FXML private TableColumn<Account, String> colAccName;
    @FXML private TableColumn<Account, String> colAccTipe;
    @FXML private TableColumn<Account, Integer> colTotDr;
    @FXML private TableColumn<Account, Integer> colTotCr;
    
    @FXML private TableView<Account> tableMain;
    
    @FXML private TextField mark;
    
    @FXML private Label totCredit;
    @FXML private Label totDebit;
    
    public void Connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/yuuka?zeroDateTimeBehavior=convertToNull","root","");
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (ClassNotFoundException ex){
            System.out.println(ex);
        }
    }
    
    @FXML
    void saveGLedger(ActionEvent event){
        Connect();
        String GLName = textGL.getText();
        try {
            stmt = conn.prepareStatement("update generalledger set name='"+GLName+"' where general_ledger_id=1001;");
            stmt.executeUpdate();
        }catch (SQLException ex){
            System.out.println(ex);
        }  
    }
    
    public void newSLedger(){
        Connect();
        String SLName = textSL.getText();
        try {
            stmt = conn.prepareStatement("INSERT INTO subledger (name,general_ledger_id) VALUES(?,1001);");
            stmt.setString(1, SLName);
            stmt.executeUpdate();
            updateTableSL();
        }catch (SQLException ex){
        System.out.println(ex);}   
    }
    
    public void newAccount(){
        Connect();
        String AccName = textAcc.getText();
        String IdSL = idSubledger.getText();
        String dr = null;
        if(buttonCR.isSelected()){
            dr = "0";
        }else if(buttonDR.isSelected()){
            dr = "1";
        }
        try {
            stmt = conn.prepareStatement("INSERT INTO accounts (account_name,debit,subledger_id) VALUES(?,?,?);");
            stmt.setString(1, AccName);
            stmt.setString(2, dr);
            stmt.setString(3, IdSL);
            stmt.executeUpdate();
            updateTableAcc();
        }catch (SQLException ex){
            System.out.println(ex);
        }   
    }
    
    public void newJE(){
        Connect();
        String JE = JENumber.getText();
        String ADr = AccDr.getText();
        String ACr = AccCr.getText();
        String Jumlah = Amount.getText();
        String Deskripsi = Desc.getText();
        try {
            stmt = conn.prepareStatement("INSERT INTO journalentry (jenumber, date, description, amount, dr_acc_id, cr_acc_id) VALUES(?,now(),?,?,?,?);");
            stmt.setString(1, JE);
            stmt.setString(2, Deskripsi);
            stmt.setString(3, Jumlah);
            stmt.setString(4, ADr);
            stmt.setString(5, ACr);
            stmt.executeUpdate();
            updateTableJE();
        }catch (SQLException ex){
            System.out.println(ex);
        }   
    }
    
    @FXML
    void updateTableJE(){
        Connect();
        ObservableList<journalEntry> JEs = FXCollections.observableArrayList();
        try {
            stmt = conn.prepareStatement("SELECT * FROM journalentry;");
            ResultSet results = stmt.executeQuery();
            while (results.next()){
                journalEntry JE = new journalEntry();
                JE.setJenumber(results.getInt("jenumber"));
                JE.setDate(results.getDate("date"));
                JE.setDescription(results.getString("description"));
                JE.setAmount(results.getInt("amount"));
                JE.setDr_acc_id(results.getInt("dr_acc_id"));
                JE.setCr_acc_id(results.getInt("cr_acc_id"));
                JEs.add(JE);
            }
            colJE.setCellValueFactory(new PropertyValueFactory<journalEntry, Integer>("jenumber"));
            colDate.setCellValueFactory(new PropertyValueFactory<journalEntry, Date>("date"));
            colDr.setCellValueFactory(new PropertyValueFactory<journalEntry, Integer>("dr_acc_id"));
            colCr.setCellValueFactory(new PropertyValueFactory<journalEntry, Integer>("cr_acc_id"));
            colJumlah.setCellValueFactory(new PropertyValueFactory<journalEntry, Integer>("amount"));
            colDesc.setCellValueFactory(new PropertyValueFactory<journalEntry, String>("description"));
                
            tableJE.setItems(JEs);
        }catch (SQLException ex){
            System.out.println(ex);
        }   
    }
    
    @FXML
    void updateTableSL(){
        Connect();
        ObservableList<subLedger> SLs = FXCollections.observableArrayList();
        try {
            stmt = conn.prepareStatement("SELECT * FROM subledger;");
            ResultSet results = stmt.executeQuery();
            while (results.next()){
                subLedger SL = new subLedger();
                SL.setIdSL(results.getInt("subledger_id"));
                SL.setSubledger_name(results.getString("name"));
                SLs.add(SL);
            }
            colSLID.setCellValueFactory(new PropertyValueFactory<subLedger, Integer>("IdSL"));
            colSLName.setCellValueFactory(new PropertyValueFactory<subLedger, String>("Subledger_name"));
                
            tableSL.setItems(SLs);
        }catch (SQLException ex){
            System.out.println(ex);
        }   
    }
    
    @FXML
    void updateTableAcc(){
        Connect();
        ObservableList<Account> Accs = FXCollections.observableArrayList();
        try {
            stmt = conn.prepareStatement("SELECT * FROM accounts INNER JOIN subledger ON accounts.subledger_id = subledger.subledger_id;");
            ResultSet results = stmt.executeQuery();
            while (results.next()){
                Account ACC = new Account();
                int ACCid = results.getInt("account_id");
                ACC.setAccount_id(ACCid);
                ACC.setAccount_name(results.getString("account_name"));
                int debit = results.getInt("debit");
                if (debit==1){
                    ACC.setDebit("Debit");      
                }else if(debit==0){
                    ACC.setDebit("Credit");
                }
                int SID = results.getInt("subledger_id");
                ACC.setSubledger_id(SID);
                ACC.setSubledger_name(results.getString("name"));
                
                Accs.add(ACC);
            }
            colAccID.setCellValueFactory(new PropertyValueFactory<Account, Integer>("account_id")); 
            colSLID.setCellValueFactory(new PropertyValueFactory<subLedger, Integer>("subledger_id"));
            colSLName.setCellValueFactory(new PropertyValueFactory<subLedger, String>("subledger_name"));
            colAccName.setCellValueFactory(new PropertyValueFactory<Account, String>("account_name"));
            colAccTipe.setCellValueFactory(new PropertyValueFactory<Account, String>("debit"));
                
            tableAcc.setItems(Accs);
        }catch (SQLException ex){
            System.out.println(ex);
        }   
    }
    
    void updateTableMain(){
        Connect();
        ObservableList<Account> AccsFake = FXCollections.observableArrayList();
        int totalCr = 0;
        int totalDr = 0;
        try {
            stmt = conn.prepareStatement("SELECT * FROM accounts INNER JOIN subledger ON accounts.subledger_id = subledger.subledger_id;");
            ResultSet results = stmt.executeQuery();
            while (results.next()){
                Account ACC = new Account();
                int ACCid = results.getInt("account_id");
                ACC.setAccount_name(results.getString("account_name"));
                int debit = results.getInt("debit");
                if (debit==1){
                    ACC.setDebit("Debit");      
                }else if(debit==0){
                    ACC.setDebit("Credit");
                }
                ACC.setSubledger_name(results.getString("name"));
                
                int totalCredit = 0;
                int totalDebit = 0;

                stmt = conn.prepareStatement("SELECT amount FROM journalEntry WHERE cr_acc_id = "+ACCid+" ;");
                ResultSet cred = stmt.executeQuery();
                while (cred.next()){
                    totalCredit = totalCredit + cred.getInt("amount");
                }
                totalCr = totalCr + totalCredit;
                ACC.setTotal_credit(totalCredit);
                
                stmt = conn.prepareStatement("SELECT amount FROM journalEntry WHERE dr_acc_id = '"+ACCid+"' ;");
                ResultSet deb = stmt.executeQuery();
                while (deb.next()){
                    totalDebit = totalDebit + deb.getInt("amount");
                }
                ACC.setTotal_debit(totalDebit);
                totalDr = totalDr + totalDebit;
                
                AccsFake.add(ACC);
            }
            colSLName.setCellValueFactory(new PropertyValueFactory<subLedger, String>("subledger_name"));
            colAccName.setCellValueFactory(new PropertyValueFactory<Account, String>("account_name"));
            colAccTipe.setCellValueFactory(new PropertyValueFactory<Account, String>("debit"));
            colTotDr.setCellValueFactory(new PropertyValueFactory<Account, Integer>("total_debit"));
            colTotCr.setCellValueFactory(new PropertyValueFactory<Account, Integer>("total_credit"));
              
            tableMain.setItems(AccsFake);
                
            totCredit.setText(Integer.toString(totalCr));
            totDebit.setText(Integer.toString(totalDr));
        }catch (SQLException ex){
            System.out.println(ex);
        }       
    }
    
    @FXML
    void DeleteSL(ActionEvent event){
        Connect();
        int myIndex = tableSL.getSelectionModel().getSelectedIndex();
        int SLId = Integer.parseInt(String.valueOf(tableSL.getItems().get(myIndex).getIdSL()));
        try {
            stmt = conn.prepareStatement("DELETE FROM subledger WHERE subledger_id= ? ;");
            stmt.setInt(1, SLId);
            stmt.executeUpdate();
            updateTableSL();
        }catch (SQLException ex){
            System.out.println(ex);
        }
    }   
    
    @FXML
    void DeleteAcc(ActionEvent event){
        Connect();
        int myIndex = tableAcc.getSelectionModel().getSelectedIndex();
        int AccID = Integer.parseInt(String.valueOf(tableAcc.getItems().get(myIndex).getAccount_id()));
        try {
            stmt = conn.prepareStatement("DELETE FROM accounts WHERE account_id= ? ;");
            stmt.setInt(1, AccID);
            stmt.executeUpdate();
            updateTableAcc();
        }catch (SQLException ex){
            System.out.println(ex);
        }
    }
    
    @FXML
    void DeleteJE(ActionEvent event){
        Connect();
        int myIndex = tableJE.getSelectionModel().getSelectedIndex();
        int jenum = Integer.parseInt(String.valueOf(tableJE.getItems().get(myIndex).getJenumber()));
        try {
            stmt = conn.prepareStatement("DELETE FROM journalentry WHERE jenumber= ? ;");
            stmt.setInt(1, jenum);
            stmt.executeUpdate();
            updateTableJE();
        }catch (SQLException ex){
            System.out.println(ex);
        }
    }
    
    public void checkName(String title){
        Connect();
        try {
            stmt = conn.prepareStatement("SELECT * FROM generalledger WHERE general_ledger_id=1001;");
            ResultSet result = stmt.executeQuery();
            while (result.next()){
                stage.setTitle("General Ledger - "+result.getString("name"));
            }
        }catch (SQLException ex){
            stage.setTitle(title);
        }
    }
    
    public void switchToGeneralLedger(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("GeneralLedger.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        checkName("General Ledger Yuuka - General Ledger");
        stage.show();
    }
    
    public void switchToMain(ActionEvent event) throws IOException  {
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        checkName("General Ledger Yuuka");
        stage.show();
    }
    
    public void switchToSubLedger(ActionEvent event) throws IOException  {
        Parent root = FXMLLoader.load(getClass().getResource("SubLedger.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        checkName("General Ledger Yuuka - Sub Ledger");
        stage.show();
    }
    
    public void switchToAccount(ActionEvent event) throws IOException  {
        Parent root = FXMLLoader.load(getClass().getResource("Account.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        checkName("General Ledger Yuuka - Account");
        stage.show();
    }
    
    public void switchToJournalEntry(ActionEvent event) throws IOException  {
        Parent root = FXMLLoader.load(getClass().getResource("JournalEntry.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);    
        stage.setScene(scene);
        checkName("General Ledger Yuuka - Journal Entry");
        stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connect();
        String markScene = mark.getText();
        if(markScene.equals("JE")){
            updateTableJE();
        }else if(markScene.equals("SL")){
            updateTableSL();
        }else if(markScene.equals("Acc")){
            updateTableAcc();
        }else if(markScene.equals("Main")){
            updateTableMain();
        }
    }    
}
