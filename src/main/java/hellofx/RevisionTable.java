package hellofx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;


import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static hellofx.DBConector.DBName;

public class RevisionTable implements Initializable {

    @FXML
    private TableColumn <ObjectRevisionTable, String> soldNewSilver;
    @FXML
    private TableColumn<ObjectRevisionTable, String> date;
    @FXML
    private TableColumn<ObjectRevisionTable, String> win;
    @FXML
    private TableColumn<ObjectRevisionTable, String> soldGold;
    @FXML
    private TableColumn<ObjectRevisionTable, String> soldSilver;
    @FXML
    private TableColumn<ObjectRevisionTable, String> cashBoxPrevioursPeriod;

    @FXML
    private TableColumn<ObjectRevisionTable, String> income;
    @FXML
    private TableColumn<ObjectRevisionTable, String> cost;

    @FXML
    private TableColumn<ObjectRevisionTable, String> goldPrice;
    @FXML
    private TableColumn<ObjectRevisionTable, String> silverPrice;


    public TableView<ObjectRevisionTable> revisionTableView;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //  ObservableList<ObjectRevisionTable> revisionTable = FXCollections.observableArrayList();


        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        win.setCellValueFactory(new PropertyValueFactory<>("win"));
        soldGold.setCellValueFactory(new PropertyValueFactory<>("soldGold"));
        soldSilver.setCellValueFactory(new PropertyValueFactory<>("soldSilver"));
        income.setCellValueFactory(new PropertyValueFactory<>("income"));
        cost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        goldPrice.setCellValueFactory(new PropertyValueFactory<>("goldPrice"));
        silverPrice.setCellValueFactory(new PropertyValueFactory<>("silverPrice"));
        cashBoxPrevioursPeriod.setCellValueFactory(new PropertyValueFactory<>("cashBoxPrevioursPeriod"));
        soldNewSilver.setCellValueFactory(new PropertyValueFactory<>("soldNewSilver"));

        getTable();
    }

    private void getTable() {
        try {


            Connection con = DBConector.getConections();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM " + DBName + ".updateAllTable;");

            printTable(rs);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }


    public void printTable(ResultSet rs) {
        ObservableList<ObjectRevisionTable> dataTable = FXCollections.observableArrayList();
        // List<String> listId = new ArrayList<>();
        List<String> date = new ArrayList<>();
        List<String> win = new ArrayList<>();
        List<String> soldGold = new ArrayList<>();
        List<String> soldSilver = new ArrayList<>();
        List<String> soldNewSilverList = new ArrayList<>();
        List<String> income = new ArrayList<>();
        List<String> cost = new ArrayList<>();
        List<String> goldPrice = new ArrayList<>();
        List<String> silverPrice = new ArrayList<>();
        List<String> cashBoxPrevioursPeriod = new ArrayList<>();

        try {

            while (rs.next()) {
                // listId.add(rs.get
                date.add(rs.getString(2));
                win.add(rs.getString(4));
                soldGold.add(rs.getString(5));
                soldSilver.add(rs.getString(6));
                income.add(rs.getString(7));
                cost.add(rs.getString(8));
                goldPrice.add(rs.getString(9));
                silverPrice.add(rs.getString(10));
                cashBoxPrevioursPeriod.add(rs.getString(12));
                soldNewSilverList.add(rs.getString(13));
                //sumOfBet.add(rs.getString(10));

            }

            int[] sizes = new int[10];
            sizes[0] = date.size();
            sizes[1] = win.size();
            sizes[2] = soldGold.size();
            sizes[3] = soldSilver.size();
            sizes[4] = income.size();
            sizes[5] = cost.size();
            sizes[6] = goldPrice.size();
            sizes[7] = silverPrice.size();
            sizes[8] = cashBoxPrevioursPeriod.size();
            sizes[9] = soldNewSilverList.size();

            int maxsize = 0;
            for (int i = 0; i < sizes.length; i++) {
                if (sizes[i] > maxsize) maxsize = sizes[i];
            }
            //Adding empty string to all the tables
            for (int i = sizes[0]; i < maxsize; i++) {
                date.add("");
            }
            for (int i = sizes[1]; i < maxsize; i++) {
                win.add("");
            }
            for (int i = sizes[2]; i < maxsize; i++) {
                soldGold.add("");
            }
            for (int i = sizes[3]; i < maxsize; i++) {
                soldSilver.add("");
            }
            for (int i = sizes[4]; i < maxsize; i++) {
                income.add("");
            }
            for (int i = sizes[5]; i < maxsize; i++) {
                cost.add("");
            }
            for (int i = sizes[6]; i < maxsize; i++) {
                goldPrice.add("");
            }
            for (int i = sizes[7]; i < maxsize; i++) {
                silverPrice.add("");
            }
            for (int i = sizes[8]; i < maxsize; i++) {
                cashBoxPrevioursPeriod.add("");
            }
            for (int i = sizes[9]; i < maxsize; i++) {
                soldNewSilverList.add("");
            }


            for (int i = 0; i < maxsize; i++) {
                dataTable.add(new ObjectRevisionTable(date.get(i), win.get(i), soldGold.get(i), soldSilver.get(i), income.get(i),
                        cost.get(i), goldPrice.get(i), silverPrice.get(i), cashBoxPrevioursPeriod.get(i), soldNewSilverList.get(i)));


            }

            revisionTableView.setItems(dataTable);

        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    public void PrintAction(ActionEvent actionEvent) throws ClassNotFoundException {


    }

    public void startNewRevisionPeriodButton(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException, URISyntaxException {

        if (ExeptionDialog.administrator()) {
            if (BackupDB.Backupdbtosql()) {
                if (uploud()) {
                    if (newPeriod()) {
                        if (deleteDB()) {

                                ExeptionDialog.alertDialog("Операцията е изпълнена!");
                            getTable();
                        }
                    }
                }
            } else {
                ExeptionDialog.alertDialog("Операцията е неуспешна!");
            }

        } else {
            ExeptionDialog.alertDialog("Операцията е неуспешна!");
        }
    }



    private boolean newPeriod() {
        try {
            String date = DateToday.returnDateToday();
            Connection con = DBConector.getConections();

            PreparedStatement newPeriod = con.prepareStatement("INSERT INTO "+DBName+".updateAllTable (date,period,interest, goldGr, silverGr, income, costs, purchasedGold, purchasedSilver,  cashierOfPrevioursRevision, startSum, newSilverGr)" +
                    " VALUES('"+date+"','0','0','0','0','0','0','0','0','0', " +
                    " (SELECT * FROM (SELECT coalesce(cashierOfPrevioursRevision,0) + (SELECT coalesce(startSum,0))" +
                    " FROM "+DBName+".updateAllTable WHERE ID = (SELECT MAX(ID) FROM "+DBName+".updateAllTable)) as t), '0');");
            newPeriod.executeLargeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    private boolean deleteDB() throws SQLException, ClassNotFoundException {
        try {
            Connection con = DBConector.getConections();


            PreparedStatement deleteDB1 = con.prepareStatement("TRUNCATE " + DBName + ".table1;");
            deleteDB1.executeLargeUpdate();

            PreparedStatement deleteDB2 = con.prepareStatement("TRUNCATE " + DBName + ".table2;");
            deleteDB2.executeLargeUpdate();

            PreparedStatement deleteDB3 = con.prepareStatement("TRUNCATE " + DBName + ".table3;");
            deleteDB3.executeLargeUpdate();

            PreparedStatement deleteDB4 = con.prepareStatement("TRUNCATE " + DBName + ".table4;;");
            deleteDB4.executeLargeUpdate();

            return true;
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return false;
    }

    private boolean uploud() {
        return true;
    }


}







