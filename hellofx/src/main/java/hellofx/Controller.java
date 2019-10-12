package hellofx;


import java.io.IOException;
import java.sql.*;

import com.jfoenix.controls.JFXToggleButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import javax.swing.table.DefaultTableModel;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


import static hellofx.DBConector.DBName;

public class Controller implements Initializable {

    // private static Person[] editList;
    public TextField textFieldmark;
    public TextField textFieldNum;
    public TextField textFieldPrincipal;
    public TextField textFieldInterest;
    public TextField textFieldGold;
    public TextField textFieldSilver;
    public DatePicker dateBar;
    public Button refreshBut;
    public TextField textFieldIncome;
    public TextField textFieldCost;
    public TextField textFieldNumOfBet;
    public TextField textFieldSumOfBet;
    public TextField textFieldOrderGold;
    public TextField textFieldOrderSilver;


    @FXML
    public  TableView<hellofx.Person> myTable;
    public  Text sumDayCashbox;
    public Text interestCashbox;

    public Text loginUser;
    public  TextField searchTable;
    public Text lableStartSum;
    public Label lableBet;
    public Label lableOverloads;
    public  Label lableInterest;
    public Label VersionLable;

    public  String dateTodayAndPrintJTable;
    public  JFXToggleButton sortedID;
    public TextFlow textNote;
    public TextField textToNotes;
    //   public JFXTextArea Notification;

    @FXML
    private TableColumn<Person, String> id;
    @FXML
    private TableColumn<Person, String> date;
    @FXML
    private TableColumn<Person, String> mark;
    @FXML
    private TableColumn<Person, String> num;
    @FXML
    private TableColumn<Person, String> interest;
    @FXML
    private TableColumn<Person, String> principal;
    @FXML
    private TableColumn<Person, String> goldGr;
    @FXML
    private TableColumn<Person, String> silverGr;
    @FXML
    private TableColumn<Person, String> income;
    @FXML
    private TableColumn<Person, String> cost;
    @FXML
    private TableColumn<Person, String> numOfBet;
    @FXML
    private TableColumn<Person, String> sumOfBet;
    @FXML
    private TableColumn<Person, String> goldPrice;
    @FXML
    private TableColumn<Person, String> silverPrice;

    static DefaultTableModel model1;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        VersionLable.setText("V 1.5.1");
        initTable();

        // DateToday.returnDateToday();
        try {
            getCashBoxDay(DateToday.returnDateToday());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //   Cashbox.newDayChange();

        refreshDay();
        ////////////////////////

        myTable.setEditable(true);
        myTable.setTableMenuButtonVisible(true);
        interest.setCellFactory(TextFieldTableCell.forTableColumn());
        income.setCellFactory(TextFieldTableCell.forTableColumn());
        numOfBet.setCellFactory(TextFieldTableCell.forTableColumn());
        sumOfBet.setCellFactory(TextFieldTableCell.forTableColumn());
        cost.setCellFactory(TextFieldTableCell.forTableColumn());
        principal.setCellFactory(TextFieldTableCell.forTableColumn());
        mark.setCellFactory(TextFieldTableCell.forTableColumn());
        goldGr.setCellFactory(TextFieldTableCell.forTableColumn());
        silverGr.setCellFactory(TextFieldTableCell.forTableColumn());
        num.setCellFactory(TextFieldTableCell.forTableColumn());
        goldPrice.setCellFactory(TextFieldTableCell.forTableColumn());
        silverPrice.setCellFactory(TextFieldTableCell.forTableColumn());


    }

    private void getCashBoxDay(String date) {
        lableInterest.setText(GetSum.getInterest(date));
        sumDayCashbox.setText(GetSum.getCashBoxOnDate(date));
        lableBet.setText(GetSum.getBet(date));
        lableOverloads.setText(GetSum.getOverload(date));
        interestCashbox.setText(GetSum.getWin(date));
    }


    private void initTable() {
        intiCols();
    }


    private void intiCols() {
        //  id.setCellValueFactory(new PropertyValueFactory<>("id"));
        // date.setCellValueFactory(new PropertyValueFactory<>("date"));
        mark.setCellValueFactory(new PropertyValueFactory<>("mark"));
        num.setCellValueFactory(new PropertyValueFactory<>("num"));
        interest.setCellValueFactory(new PropertyValueFactory<>("interest"));
        principal.setCellValueFactory(new PropertyValueFactory<>("principal"));
        goldGr.setCellValueFactory(new PropertyValueFactory<>("goldGr"));
        silverGr.setCellValueFactory(new PropertyValueFactory<>("silverGr"));
        income.setCellValueFactory(new PropertyValueFactory<>("income"));
        cost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        numOfBet.setCellValueFactory(new PropertyValueFactory<>("numOfBet"));
        sumOfBet.setCellValueFactory(new PropertyValueFactory<>("sumOfBet"));
        goldPrice.setCellValueFactory(new PropertyValueFactory<>("goldPrice"));
        silverPrice.setCellValueFactory(new PropertyValueFactory<>("silverPrice"));
//        profit.setCellValueFactory(new PropertyValueFactory<>("profit"));
    }


    public void buttonAdd(ActionEvent actionEvent) throws ClassNotFoundException {
        sendInfo();

        refreshDay();
    }
/*
    public void getDBSumOfDayAndPrincipal(String dateToday) {
        try {
            ResultSet rsInterestCashboxGet = Cashbox.interestCashboxGet(dateToday);
            while (rsInterestCashboxGet.next()) {
                //   double givenMoney = Double.parseDouble(rsInterestCashboxGet.getString(6));
                //   givenMoney = (givenMoney < 0) ? 0 : givenMoney;
                interestCashbox.setText(rsInterestCashboxGet.getString(7) + " лв.");
                totalSumInterestPrint = rsInterestCashboxGet.getString(7) + " лв.";

                lableStartSum.setText(rsInterestCashboxGet.getString(4) + " лв.");
                lableBet.setText(rsInterestCashboxGet.getString(13) + " лв.");
                lableOverloads.setText(rsInterestCashboxGet.getString(16) + " лв.");
                //
            }
            ResultSet rsTotalCashbox = Cashbox.totalCashbox(dateToday);
            while (rsTotalCashbox.next()) {
                sumDayCashbox.setText(rsTotalCashbox.getString(1) + " лв.");
                totalSumPrint = rsTotalCashbox.getString(1) + " лв.";
            }
            rsInterestCashboxGet.close();
            rsTotalCashbox.close();

            ResultSet getLoginUser = Cashbox.getLoginUser(dateToday);
            while (getLoginUser.next()) {
                loginUser.setText(getLoginUser.getString(3));

            }
            getLoginUser.close();

            ResultSet returnInterest = Cashbox.returnInterest(dateToday);
            while (returnInterest.next()) {
                lableInterest.setText(returnInterest.getString(1) + " лв.");
            }
            returnInterest.close();
            //////////////////////////// getLoginUser


        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            DBConector.closeConnection();
            System.out.println("close connection DB");

        } catch (Exception ex) {
            System.out.println(ex);
        }


        try {
            Cashbox.updateCashbox(date);

            ResultSet rsInterestCashboxGet = Cashbox.interestCashboxGet(date);
            while (rsInterestCashboxGet.next()) {
                double givenMoney = Double.parseDouble(rsInterestCashboxGet.getString(5));
                givenMoney = (givenMoney < 0) ? 0 : givenMoney;
                interestCashbox.setText(rsInterestCashboxGet.getString(6) + " лв.");
                givenMoneyCashbox.setText(givenMoney + "" + " лв.");

            }
            ResultSet rsTotalCashbox = Cashbox.totalCashbox(date);
            while (rsTotalCashbox.next()) {
                sumDayCashbox.setText(rsTotalCashbox.getString(1) + " лв.");

            }
            rsInterestCashboxGet.close();
            rsTotalCashbox.close();

        } catch (Exception e) {
            System.out.println(e);
        }



    }
    */

    public void buttonRefresh(ActionEvent actionEvent) {

        refreshDay();
    }

    public  void refreshDay() {
        try {
            String dateOfToday = DateToday.returnDateToday();
            Refresh(dateOfToday);
            dateBar.setValue(LocalDate.parse(dateOfToday));
          //  sumDayCashbox.setText(GetSum.getCurrentCashbox());
            // UpdateAllTable.updateCashbox();

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void onEditSorted(ActionEvent actionEvent) throws ClassNotFoundException {
        serchToDatePicker();
    }

    public  void Refresh(String dateToday) throws IOException {


        try {
            UpdateAllTable.updateCashbox();
            getCashBoxDay(dateToday);
            //  getDBSumOfDayAndPrincipal(dateToday);
            String sortQery;

            if (sortedID.isSelected()) {
                sortQery = " order by num asc";
            } else {
                sortQery = "";
            }
            Connection con = DBConector.getConections();
            ResultSet rs = con.createStatement().executeQuery("select * from " + DBName + ".table1 where date='" + dateToday + "'" + sortQery + ";");
            ResultSet rs1 = con.createStatement().executeQuery("select * from " + DBName + ".table2 where date='" + dateToday + "'");
            ResultSet rs2 = con.createStatement().executeQuery("select * from " + DBName + ".table3 where date='" + dateToday + "'");
            ResultSet rs3 = con.createStatement().executeQuery("select * from " + DBName + ".table4 where date='" + dateToday + "'");
            printTable(rs, rs1, rs2, rs3);
            //  rs.close();
            //  rs1.close();
            // rs2.close();
            // rs3.close();
            //  Connection con = DBConector.getConections();


        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateNotes(String s) {
        try {
            Connection con = DBConector.getConections();

            //  String dateNotes = dateBar.getValue().toString();
            StringBuilder sb = new StringBuilder();
            Text text = new Text();
            ResultSet getNoteDB = con.createStatement().executeQuery("select notification from " + DBName + ".notes where date = '" + s + "';");
            while (getNoteDB.next()) {
                sb.append(getNoteDB.getString(1));
                sb.append("\n");
            }
            //  textNote = new TextFlow();
            textNote.getChildren().clear();
            //  textNote.getChildren().removeAll();
            text.setText(String.valueOf(sb));
            textNote.getChildren().add(text);
            System.out.println(textNote.getLayoutX());
            System.out.println(textNote.getLayoutY() + ", " + textNote.getPrefHeight());
            // textNote.setLayoutY(450);


        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void serchToDatePicker() throws ClassNotFoundException {
        String getDateOfDatePicker = "";
        try {
            String getDate = dateBar.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            getDateOfDatePicker = getDate;
            getCashBoxDay(getDateOfDatePicker);
        } catch (Exception ex) {
            System.out.println(ex + " Not Date");
        }
        //  updateNotes(getDateOfDatePicker);
        //  getDBSumOfDayAndPrincipal(getDateOfDatePicker);

        String sortQery;

        if (sortedID.isSelected()) {
            sortQery = " order by num asc";
        } else {
            sortQery = "";
        }

        try {

            Connection con = DBConector.getConections();
            ResultSet rs = con.createStatement().executeQuery("select * from " + DBName + ".table1 where date='" + getDateOfDatePicker + "'" + sortQery + ";");
            // ResultSet rs = con.createStatement().executeQuery("select * from table1 where date='" + getDateOfDatePicker + "';");
            ResultSet rs1 = con.createStatement().executeQuery("select * from " + DBName + ".table2 where date='" + getDateOfDatePicker + "';");
            ResultSet rs2 = con.createStatement().executeQuery("select * from " + DBName + ".table3 where date='" + getDateOfDatePicker + "';");
            ResultSet rs3 = con.createStatement().executeQuery("select * from " + DBName + ".table4 where date='" + getDateOfDatePicker + "';");


            printTable(rs, rs1, rs2, rs3);
            /*
            rs.close();
            rs1.close();
            rs2.close();
            rs3.close();

             */


        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*
        try {
            DBConector.closeConnection();
            System.out.println("close connection DB");

        } catch (Exception ex) {
            System.out.println(ex);
        }

         */

    }

    public void sendInfo() throws ClassNotFoundException {

        String num = textFieldNum.getText();
        String interest = textFieldInterest.getText();
        String principal = textFieldPrincipal.getText();

        String income = textFieldIncome.getText();
        String cost = textFieldCost.getText();
        String numOfBet = textFieldNumOfBet.getText();
        String sumOfBet = textFieldSumOfBet.getText();
        boolean isSend = false;

        boolean markSilverGold = (textFieldmark.getText().equals("") && (textFieldGold.getText().equals("") && textFieldSilver.getText().equals("")));
        boolean numInterestPrincipal = (!textFieldNum.getText().equals("") && !textFieldInterest.getText().equals("") && !textFieldPrincipal.getText().equals(""));
        boolean marcAndSilverOrGold = (!textFieldNum.getText().equals("") && !textFieldInterest.getText().equals("") && !textFieldPrincipal.getText().equals(""))
                && (!textFieldmark.getText().equals("") && (!textFieldGold.getText().equals("") || !textFieldSilver.getText().equals("")));
        /*try {
            revisionValue = Integer.parseInt(RevisionValue.revisionValue());

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);

        }

         */

        //  DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String dateTodaySend = DateToday.returnDateToday();
        try {
            //Cashbox.newDayChange();
            // CangeNewDay.changeDay();
            Connection con = DBConector.getConections();

            if (/*markSilverGold &&*/ numInterestPrincipal || marcAndSilverOrGold) {
                String gold = textFieldGold.getText();
                String silver = textFieldSilver.getText();
                String mark = textFieldmark.getText();
                if (!markSilverGold) {
                    if (gold.equals("") && silver.equals("")) {
                        ExeptionDialog.alertDialog("Не е въведен грамаж!");
                    }
                    if (mark.equals("")) {
                        ExeptionDialog.alertDialog("Въведен е грамаж, но не е маркиран като продажба!");
                    }
                }
                if (!mark.equals("")) {
                    mark = "--||--";
                }
                if (gold.equals("")) {
                    gold = "null";
                }
                if (silver.equals("")) {
                    silver = "null";
                }

                //  System.out.println(df.format(dateToSend));
                PreparedStatement posted = con.prepareStatement("INSERT into " + DBName + ".table1 ( date, mark, num, interest, principal, " +
                        "goldGr, silverGr, goldText, silverText)" +
                        "VALUES (  " + "'" + dateTodaySend + "'" + ", " + "'" + mark + "'" + ", " + num + ", " + interest + ", " + principal + ", " + Regex.returnDigit(gold) + ", "
                        + Regex.returnDigit(silver) + ",'" + Regex.returnText(gold) + "','" + Regex.returnText(silver) + "');");


                posted.executeLargeUpdate();
                posted.close();
                textFieldmark.clear();
                textFieldGold.clear();
                textFieldSilver.clear();
                textFieldNum.clear();
                textFieldInterest.clear();
                textFieldPrincipal.clear();
                isSend = true;
            }

            if (!income.equals("") || !cost.equals("")) {
                if (income.equals("")) {
                    income = null;
                }
                if (cost.equals("")) {
                    cost = null;
                }


                PreparedStatement postedIncomeAndCost = con.prepareStatement("INSERT into " + DBName + ".table2 ( date, income, cost, incomeString, costString) " +
                        "VALUES (  " + "'" + dateTodaySend + "'" + "," + Regex.returnDigit(income) + "," + Regex.returnDigit(cost) + ", '" + Regex.returnText(income) + "', '" + Regex.returnText(cost) + "' );");

                postedIncomeAndCost.executeLargeUpdate();
                postedIncomeAndCost.close();
                textFieldIncome.clear();
                textFieldCost.clear();
                isSend = true;
            }
            if (!numOfBet.equals("") && !sumOfBet.equals("")) {
                PreparedStatement postedSumAndNum = con.prepareStatement("INSERT into " + DBName + ".table3 ( date, numberOfBet, sumOfBet) " +
                        "VALUES (  " + "'" + dateTodaySend + "'" + "," + "'" + numOfBet + "'" + "," + "'" + sumOfBet + "'" + ");");

                postedSumAndNum.executeLargeUpdate();
                postedSumAndNum.close();
                try {
                    int numberOfBet = Integer.parseInt(textFieldNumOfBet.getText());
                    textFieldNumOfBet.clear();
                    numberOfBet = numberOfBet + 1;
                    textFieldNumOfBet.setText(String.valueOf(numberOfBet));
                } catch (Exception e) {
                    textFieldNumOfBet.clear();
                    System.out.println(e + "increst number 0f Bet");
                }
                textFieldSumOfBet.clear();

                isSend = true;
            }
            if (!textFieldOrderGold.getText().equals("") || !textFieldOrderSilver.getText().equals("")) {
                String goldPrice = textFieldOrderGold.getText();
                String silverPrice = textFieldOrderSilver.getText();
                if (goldPrice.equals("")) {
                    goldPrice = null;
                }
                if (silverPrice.equals("")) {
                    silverPrice = null;
                }

                PreparedStatement postedGoldPrice = con.prepareStatement("INSERT into " + DBName + ".table4 ( date, goldPrice, silverPrice, goldText, silverText) " +
                        "VALUES (  " + "'" + dateTodaySend + "'" + "," + Regex.returnDigit(goldPrice) + "," + Regex.returnDigit(silverPrice) + ",'" + Regex.returnText(goldPrice) + "','" + Regex.returnText(silverPrice) + "' );");

                postedGoldPrice.executeLargeUpdate();
                postedGoldPrice.close();
                textFieldOrderGold.clear();
                textFieldOrderSilver.clear();
                isSend = true;
            }
            System.out.println("Sending info");
            if (isSend) {
                Refresh(dateTodaySend);
                //  UpdateAllTable.updateCashbox();
                BackupDB.Backupdbtosql();
            }
        } catch (Exception e) {
            System.out.println(e);
        }


    }


    public  void printTable(ResultSet rs, ResultSet rs1, ResultSet rs2, ResultSet rs3) {
        ObservableList<Person> dataTable = FXCollections.observableArrayList();

        // List<String> listId = new ArrayList<>();
        List<String> idTable2 = new ArrayList<>();
        List<String> dateTable2 = new ArrayList<>();
        List<String> dateTable3 = new ArrayList<>();
        List<String> idTable3 = new ArrayList<>();
        List<String> listId = new ArrayList<>();
        List<String> listNum = new ArrayList<>();
        List<String> listDate = new ArrayList<>();
        List<String> listInterest = new ArrayList<>();
        List<String> listMark = new ArrayList<>();
        List<String> listPrincipal = new ArrayList<>();
        List<String> listGold = new ArrayList<>();
        List<String> listSilver = new ArrayList<>();
        List<String> income = new ArrayList<>();
        List<String> cost = new ArrayList<>();
        List<String> numOfBet = new ArrayList<>();
        List<String> sumOfBet = new ArrayList<>();
        List<String> idTable4 = new ArrayList<>();
        List<String> dateTable4 = new ArrayList<>();
        List<String> goldPrice = new ArrayList<>();
        List<String> silverPrice = new ArrayList<>();
        List<String> profit = new ArrayList<>();
        try {

            while (rs.next()) {
                // listId.add(rs.get
                listId.add(rs.getString(1));
                listDate.add(rs.getString(2));
                listMark.add(rs.getString(3));
                listNum.add(rs.getString(4));
                listInterest.add(rs.getString(5));
                listPrincipal.add(rs.getString(6));
                String val = rs.getString(7);
                if (val == null || val.equals("0.00")) {
                    val = "";
                }
                String val1 = rs.getString(8);
                if (val1 == null || val1.equals("0.00")) {
                    val1 = "";
                }
                listGold.add(val + " " + ((rs.getString(9).equals("null")) ? "" : (rs.getString(9))));
                listSilver.add(val1 + " " + ((rs.getString(10).equals("null")) ? "" : (rs.getString(10))));

            }

            while (rs1.next()) {
                idTable2.add(rs1.getString(1));
                dateTable2.add(rs1.getString(2));

                income.add(rs1.getString(3) == null ? null : rs1.getString(3) + " " + rs1.getString(5));
                cost.add(rs1.getString(4) == null ? null : rs1.getString(4) + " " + rs1.getString(6));
            }
            while (rs2.next()) {
                idTable3.add(rs2.getString(1));
                dateTable3.add(rs2.getString(2));
                numOfBet.add(rs2.getString(3));
                sumOfBet.add(rs2.getString(4));
            }
            while (rs3.next()) {
                idTable4.add(rs3.getString(1));
                dateTable4.add(rs3.getString(2));
                String a = (rs3.getString(3) == null ? "" : rs3.getString(3)) + " " + (rs3.getString(5).equals("null") ? "" : rs3.getString(5));
                String b = (rs3.getString(4) == null ? "" : rs3.getString(4)) + " " + (rs3.getString(6).equals("null") ? "" : rs3.getString(6));
                if (a.equals("null")) {
                    a = "";
                }
                if (b.equals("null")) {
                    b = "";
                }
                goldPrice.add(a);
                silverPrice.add(b);
            }

            int[] sizes = new int[21];
            sizes[0] = listId.size();
            sizes[1] = listDate.size();
            sizes[2] = listMark.size();
            sizes[3] = listNum.size();
            sizes[4] = listInterest.size();
            sizes[5] = listPrincipal.size();
            sizes[6] = listGold.size();
            sizes[7] = listSilver.size();
            sizes[8] = income.size();
            sizes[9] = cost.size();
            sizes[10] = numOfBet.size();
            sizes[11] = sumOfBet.size();
            sizes[12] = goldPrice.size();
            sizes[13] = silverPrice.size();
            sizes[14] = profit.size();
            sizes[15] = idTable2.size();
            sizes[16] = idTable3.size();
            sizes[17] = dateTable3.size();
            sizes[18] = dateTable2.size();
            sizes[19] = idTable4.size();
            sizes[20] = dateTable4.size();
            int maxsize = 0;
            for (int i = 0; i < sizes.length; i++) {
                if (sizes[i] > maxsize) maxsize = sizes[i];
            }
            //Adding empty string to all the tables
            for (int i = sizes[0]; i < maxsize; i++) {
                listId.add("");
            }
            for (int i = sizes[1]; i < maxsize; i++) {
                listDate.add("");
            }
            for (int i = sizes[2]; i < maxsize; i++) {
                listMark.add("");
            }
            for (int i = sizes[3]; i < maxsize; i++) {
                listNum.add("");
            }
            for (int i = sizes[4]; i < maxsize; i++) {
                listInterest.add("");
            }
            for (int i = sizes[5]; i < maxsize; i++) {
                listPrincipal.add("");
            }
            for (int i = sizes[6]; i < maxsize; i++) {
                listGold.add("");
            }
            for (int i = sizes[7]; i < maxsize; i++) {
                listSilver.add("");
            }
            for (int i = sizes[8]; i < maxsize; i++) {
                income.add("");
            }
            for (int i = sizes[9]; i < maxsize; i++) {
                cost.add("");
            }
            for (int i = sizes[10]; i < maxsize; i++) {
                numOfBet.add("");
            }
            for (int i = sizes[11]; i < maxsize; i++) {
                sumOfBet.add("");
            }
            for (int i = sizes[12]; i < maxsize; i++) {
                goldPrice.add("");
            }
            for (int i = sizes[13]; i < maxsize; i++) {
                silverPrice.add("");
            }
            for (int i = sizes[14]; i < maxsize; i++) {
                profit.add("");
            }
            for (int i = sizes[15]; i < maxsize; i++) {
                idTable2.add("");
            }
            for (int i = sizes[16]; i < maxsize; i++) {
                idTable3.add("");
            }
            for (int i = sizes[17]; i < maxsize; i++) {
                dateTable3.add("");
            }
            for (int i = sizes[18]; i < maxsize; i++) {
                dateTable2.add("");
            }
            for (int i = sizes[19]; i < maxsize; i++) {
                idTable4.add("");
            }
            for (int i = sizes[20]; i < maxsize; i++) {
                dateTable4.add("");
            }

            model1 = new DefaultTableModel();

            // model1.addColumn("Каса");
            // model1.addColumn("Дата");
            model1.addColumn("Марк");
            model1.addColumn("Д-р.Номер");
            model1.addColumn("Лихва");
            model1.addColumn("Главница");
            model1.addColumn("Зл./гр.");
            model1.addColumn("Ср./Гр.");
            model1.addColumn("Приход");
            model1.addColumn("Разход");
            model1.addColumn("Номер Залог");
            model1.addColumn("Сума Залог");
            model1.addColumn("Куп.Зл.");
            model1.addColumn("Куп.Ср.");
            // model1.addColumn("Пелалба");
            // String cash = "";
            for (int i = 0; i < maxsize; i++) {
                dataTable.add(new Person(listId.get(i), listDate.get(i), listMark.get(i), listNum.get(i), listInterest.get(i),
                        listPrincipal.get(i), listGold.get(i), listSilver.get(i), idTable2.get(i), dateTable2.get(i), income.get(i), cost.get(i),
                        idTable3.get(i), dateTable3.get(i), numOfBet.get(i), sumOfBet.get(i), idTable4.get(i), dateTable4.get(i), goldPrice.get(i), silverPrice.get(i), profit.get(i)));

                // String a = listDate.get(i);
                dateTodayAndPrintJTable = listDate.get(i);
                String b = listMark.get(i);
                String c = listNum.get(i);
                String d = listInterest.get(i);
                String p = listPrincipal.get(i);
                String lg = listGold.get(i);
                String ls = listSilver.get(i);
                String in = income.get(i);
                String co = cost.get(i);
                String mb = numOfBet.get(i);
                String sb = sumOfBet.get(i);
                String gp = goldPrice.get(i);
                String sp = silverPrice.get(i);
                //  String pf = profit.get(i);

                model1.addRow(new Object[]{b, c, d, p, lg, ls, in, co, mb, sb, gp, sp});

            }


            //  revisionTableView.setItems(dataTable);

            FilteredList<Person> filteredData = new FilteredList<>(dataTable, p -> true);
            searchTable.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(person -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (person.getNum().toLowerCase().contains(lowerCaseFilter)) {

                        return true;
                    }
                    if (person.getNumOfBet().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    return false;
                });
            });
            SortedList<Person> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(myTable.comparatorProperty());
            myTable.setItems(sortedData);

            //  myTable.getSortOrder().addAll(num);

        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    private void updateDB(String collumName, String dateCell, String idForCell, String cellValue, String table) throws ClassNotFoundException {

        sendAndUpdateDB(collumName, dateCell, idForCell, cellValue, table);

    }


    private void sendAndUpdateDB(String collumName, String dateCell, String idForCell, String cellValue, String table) throws ClassNotFoundException {
        try {  //TODO correct send data

            Connection con = DBConector.getConections();
            //  ResultSet rs = con.createStatement().executeQuery("select * from "+DBName+".table1 where date='" + date + "'");
            if (table.equals("table1") && (collumName.equals("goldGr") || collumName.equals("silverGr"))) {
                String collumn = collumName.equals("goldGr") ? "goldText" : "silverText";
                PreparedStatement updateTable1 = con.prepareStatement("UPDATE " + DBName + "." + table + " SET " + collumName + " = '" + Regex.returnDigit(cellValue) + "', " + collumn + " = '" + Regex.returnText(cellValue) + "' WHERE ID = " + idForCell + ";");
                updateTable1.executeLargeUpdate();
                updateTable1.close();

            } else if (table.equals("table2")) {
                String collumn = collumName.equals("income") ? "incomeString" : "costString";
                PreparedStatement updateTable1 = con.prepareStatement("UPDATE " + DBName + "." + table + " SET " + collumName + " = '" + Regex.returnDigit(cellValue) + "', " + collumn + " = '" + Regex.returnText(cellValue) + "' WHERE ID = " + idForCell + ";");
                updateTable1.executeLargeUpdate();
                updateTable1.close();
            } else if (table.equals("table4")) {
                String collumn = collumName.equals("goldPrice") ? "goldText" : "silverText";
                PreparedStatement updateTable1 = con.prepareStatement("UPDATE " + DBName + "." + table + " SET " + collumName + " = '" + Regex.returnDigit(cellValue) + "', " + collumn + " = '" + Regex.returnText(cellValue) + "' WHERE ID = " + idForCell + ";");
                updateTable1.executeLargeUpdate();
                updateTable1.close();
            } else {
                PreparedStatement updateTable1 = con.prepareStatement("UPDATE " + DBName + "." + table + " SET " + collumName + " = '" + cellValue + "' WHERE ID = " + idForCell + ";");
                updateTable1.executeLargeUpdate();
                updateTable1.close();
            }


            //Cashbox.updateCashbox(dateCell);
        } catch (
                SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
/*
        try {
            DBConector.closeConnection();
            System.out.println("close connection DB");

        } catch (Exception ex) {
            System.out.println(ex);
        }

 */
    }


    public void onEditInterest(TableColumn.CellEditEvent<Person, String> personStringCellEditEvent) {
        Person person = myTable.getSelectionModel().getSelectedItem();
        String cellValue = person.setInterest(personStringCellEditEvent.getNewValue());
        String oldValue = person.setInterest(personStringCellEditEvent.getOldValue());

        String idForCell = myTable.getSelectionModel().getSelectedItem().getId();
        String date = myTable.getSelectionModel().getSelectedItem().getDate();
        System.out.println("id - " + idForCell + " " + "val - " + cellValue);
        if (!oldValue.equals("")) {
            try {
                Double.parseDouble(cellValue);
                String collumName = "interest";
                String table = "table1";
                updateDB(collumName, date, idForCell, cellValue, table);

            } catch (Exception e) {
                System.out.println(e);
                refreshDay();
            }
        }
        refreshDay();


    }

    public void onEditPrincipal(TableColumn.CellEditEvent<Person, String> personStringCellEditEvent) {
        Person person = myTable.getSelectionModel().getSelectedItem();
        String cellValue = person.setInterest(personStringCellEditEvent.getNewValue());
        String oldValue = person.setInterest(personStringCellEditEvent.getOldValue());

        String idForCell = myTable.getSelectionModel().getSelectedItem().getId();
        String date = myTable.getSelectionModel().getSelectedItem().getDate();
        System.out.println("id - " + idForCell + " " + "val - " + cellValue);
        if (!oldValue.equals("")) {
            try {
                Double.parseDouble(cellValue);
                String collumName = "principal";
                String table = "table1";
                updateDB(collumName, date, idForCell, cellValue, table);

            } catch (Exception e) {
                System.out.println(e);
                refreshDay();
            }
        }
        refreshDay();


    }

    public void onEditIncome(TableColumn.CellEditEvent<Person, String> personStringCellEditEvent) throws ClassNotFoundException {
        Person person = myTable.getSelectionModel().getSelectedItem();
        String cellValue = person.setInterest(personStringCellEditEvent.getNewValue());
        String oldValue = person.setInterest(personStringCellEditEvent.getOldValue());
        String idForCell = myTable.getSelectionModel().getSelectedItem().getIdTable2();
        String date = myTable.getSelectionModel().getSelectedItem().getDateTable2();
        System.out.println("id - " + idForCell + " " + "val - " + cellValue);

        if (!oldValue.equals("")) {
            String collumName = "income";
            String table = "table2";
            updateDB(collumName, date, idForCell, cellValue, table);
            refreshDay();
        } else {
            refreshDay();
        }
    }

    public void onEditCost(TableColumn.CellEditEvent<Person, String> personStringCellEditEvent) throws ClassNotFoundException {
        Person person = myTable.getSelectionModel().getSelectedItem();
        String cellValue = person.setInterest(personStringCellEditEvent.getNewValue());
        String oldValue = person.setInterest(personStringCellEditEvent.getOldValue());
        String idForCell = myTable.getSelectionModel().getSelectedItem().getIdTable2();
        String date = myTable.getSelectionModel().getSelectedItem().getDateTable2();
        System.out.println("id - " + idForCell + " " + "val - " + cellValue);

//        if (!oldValue.equals("")) {
        String collumName = "cost";
        String table = "table2";
        updateDB(collumName, date, idForCell, cellValue, table);
        //       } else {
        refreshDay();
        //       }
    }

    public void onEditMark(TableColumn.CellEditEvent<Person, String> personStringCellEditEvent) throws ClassNotFoundException {
        Person person = myTable.getSelectionModel().getSelectedItem();
        String cellValue = person.setInterest(personStringCellEditEvent.getNewValue());
        String oldValue = person.setInterest(personStringCellEditEvent.getOldValue());

        String idForCell = myTable.getSelectionModel().getSelectedItem().getId();
        String date = myTable.getSelectionModel().getSelectedItem().getDate();
        System.out.println("id - " + idForCell + " " + "val - " + cellValue);

        if (oldValue.equals("")) {
            if (!cellValue.equals("--||--")) {
                cellValue = "--||--";
            }
        }
        String collumName = "mark";
        String table = "table1";
        updateDB(collumName, date, idForCell, cellValue, table);
        refreshDay();
        //   } else {
        //       refreshDay();
        //   }
    }

    public void onEditNumOfBet(TableColumn.CellEditEvent<Person, String> personStringCellEditEvent) {
        Person person = myTable.getSelectionModel().getSelectedItem();
        String cellValue = person.setInterest(personStringCellEditEvent.getNewValue());
        String oldValue = person.setInterest(personStringCellEditEvent.getOldValue());

        String idForCell = myTable.getSelectionModel().getSelectedItem().getIdTable3();
        String date = myTable.getSelectionModel().getSelectedItem().getDateTable3();
        System.out.println("id - " + idForCell + " " + "val - " + cellValue);
        if (!oldValue.equals("")) {
            try {
                Integer.parseInt(cellValue);
                String collumName = "numberOfBet";
                String table = "table3";
                updateDB(collumName, date, idForCell, cellValue, table);

            } catch (Exception e) {
                System.out.println(e);
                refreshDay();
            }
        }
        refreshDay();

    }

    public void onEditSumOfBet(TableColumn.CellEditEvent<Person, String> personStringCellEditEvent) {
        Person person = myTable.getSelectionModel().getSelectedItem();
        String cellValue = person.setInterest(personStringCellEditEvent.getNewValue());
        String oldValue = person.setInterest(personStringCellEditEvent.getOldValue());

        String idForCell = myTable.getSelectionModel().getSelectedItem().getIdTable3();
        String date = myTable.getSelectionModel().getSelectedItem().getDateTable3();
        System.out.println("id - " + idForCell + " " + "val - " + cellValue);
        if (!oldValue.equals("")) {
            try {
                Double.parseDouble(cellValue);
                String collumName = "sumOfBet";
                String table = "table3";
                updateDB(collumName, date, idForCell, cellValue, table);

            } catch (Exception e) {
                System.out.println(e);
                refreshDay();
            }
        }
        refreshDay();

    }


    public void onEditGoldPrice(TableColumn.CellEditEvent<Person, String> personStringCellEditEvent) {
        Person person = myTable.getSelectionModel().getSelectedItem();
        String cellValue = person.setInterest(personStringCellEditEvent.getNewValue());
        String oldValue = person.setInterest(personStringCellEditEvent.getOldValue());

        String idForCell = myTable.getSelectionModel().getSelectedItem().getIdTable4();
        String date = myTable.getSelectionModel().getSelectedItem().getDateTable4();
        System.out.println("id - " + idForCell + " " + "val - " + cellValue);


        try {
            //  Double.parseDouble(cellValue);
            String collumName = "goldPrice";
            String table = "table4";
            updateDB(collumName, date, idForCell, cellValue, table);
        } catch (Exception e) {
            System.out.println(e);
            refreshDay();
        }

        refreshDay();

    }

    public void onEditSilverPrice(TableColumn.CellEditEvent<Person, String> personStringCellEditEvent) {
        Person person = myTable.getSelectionModel().getSelectedItem();
        String cellValue = person.setInterest(personStringCellEditEvent.getNewValue());
        String oldValue = person.setInterest(personStringCellEditEvent.getOldValue());

        String idForCell = myTable.getSelectionModel().getSelectedItem().getIdTable4();
        String date = myTable.getSelectionModel().getSelectedItem().getDateTable4();
        System.out.println("id - " + idForCell + " " + "val - " + cellValue);

        try {
            //  Double.parseDouble(cellValue);
            String collumName = "silverPrice";
            String table = "table4";
            updateDB(collumName, date, idForCell, cellValue, table);

        } catch (Exception e) {
            System.out.println(e);
            refreshDay();
        }

        refreshDay();

    }

    public void onEditNum(TableColumn.CellEditEvent<Person, String> personStringCellEditEvent) {
        Person person = myTable.getSelectionModel().getSelectedItem();
        String cellValue = person.setInterest(personStringCellEditEvent.getNewValue());
        String oldValue = person.setInterest(personStringCellEditEvent.getOldValue());

        String idForCell = myTable.getSelectionModel().getSelectedItem().getId();
        String date = myTable.getSelectionModel().getSelectedItem().getDate();
        System.out.println("id - " + idForCell + " " + "val - " + cellValue);
        if (!oldValue.equals("")) {
            try {
                Integer.parseInt(cellValue);
                String collumName = "num";
                String table = "table1";
                updateDB(collumName, date, idForCell, cellValue, table);

            } catch (Exception e) {
                System.out.println(e);
                refreshDay();
            }
        }
        refreshDay();

    }

    public void onEditGoldGr(TableColumn.CellEditEvent<Person, String> personStringCellEditEvent) throws ClassNotFoundException {
        Person person = myTable.getSelectionModel().getSelectedItem();
        String cellValue = person.setInterest(personStringCellEditEvent.getNewValue());
        String oldValue = person.setInterest(personStringCellEditEvent.getOldValue());

        String idForCell = myTable.getSelectionModel().getSelectedItem().getId();
        String date = myTable.getSelectionModel().getSelectedItem().getDate();
        System.out.println("id - " + idForCell + " " + "val - " + cellValue);

        if (cellValue.equals("")) {
            cellValue = "0";
        }


        String collumName = "goldGr";
        String table = "table1";
        updateDB(collumName, date, idForCell, cellValue, table);
        refreshDay();
    }

    public void onEditSilverGr(TableColumn.CellEditEvent<Person, String> personStringCellEditEvent) throws ClassNotFoundException {
        Person person = myTable.getSelectionModel().getSelectedItem();
        String cellValue = person.setInterest(personStringCellEditEvent.getNewValue());
        String oldValue = person.setInterest(personStringCellEditEvent.getOldValue());

        String idForCell = myTable.getSelectionModel().getSelectedItem().getId();
        String date = myTable.getSelectionModel().getSelectedItem().getDate();
        System.out.println("id - " + idForCell + " " + "val - " + cellValue);

        if (cellValue.equals("")) {
            cellValue = "0";
        }
        String collumName = "silverGr";
        String table = "table1";
        updateDB(collumName, date, idForCell, cellValue, table);
        refreshDay();
    }


    public void setDatePicker(ActionEvent actionEvent) throws ClassNotFoundException {
        serchToDatePicker();
    }

    public void close(ActionEvent actionEvent) {
        System.exit(0);
    }


    public void openRevisionTable(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/RevisionTable.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);
        javafx.scene.image.Image icon = new Image(getClass().getResourceAsStream("/image.png"));
        stage.getIcons().add(icon);
        stage.setTitle("Revision");
        stage.setScene(new Scene(root, 960, 600));
        stage.show();
    }

    public void textFieldActionEnter(ActionEvent actionEvent) throws ClassNotFoundException {
        buttonAdd(actionEvent);
        //  textFieldNumOfBet.requestFocus();
    }

    public void textFieldActionEnterPrincipal(ActionEvent actionEvent) throws ClassNotFoundException {
        buttonAdd(actionEvent);
        textFieldNum.requestFocus();
    }

    public void textFieldActionEnterGold(ActionEvent actionEvent) throws ClassNotFoundException {
        buttonAdd(actionEvent);
        textFieldmark.requestFocus();
    }

    public void textFieldActionEnterSilver(ActionEvent actionEvent) throws ClassNotFoundException {
        buttonAdd(actionEvent);
        textFieldmark.requestFocus();
    }

    public void textFieldActionEnterIncome(ActionEvent actionEvent) throws ClassNotFoundException {
        buttonAdd(actionEvent);
    }

    public void textFieldActionEnterCost(ActionEvent actionEvent) throws ClassNotFoundException {
        buttonAdd(actionEvent);
    }

    public void textFieldActionEnterPriceGold(ActionEvent actionEvent) throws ClassNotFoundException {
        buttonAdd(actionEvent);
    }

    public void textFieldActionEnterPriceSilver(ActionEvent actionEvent) throws ClassNotFoundException {
        buttonAdd(actionEvent);
    }

    public void openMenuBarDiagram(ActionEvent actionEvent) throws IOException {
        //  ((Stage) signIn.getScene().getWindow()).close();

        Parent root = FXMLLoader.load(getClass().getResource("/diagram.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);
        Image icon = new Image(getClass().getResourceAsStream("/image.png"));
        stage.getIcons().add(icon);
        stage.setTitle("Diagram");
        stage.setScene(new Scene(root, 1080, 600));
        stage.show();

    }

    public void SetTimer(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(SetTimerController.class.getResource("/LoginTimer.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("/RevisionTable.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);
        Image icon = new Image(SetTimerController.class.getResourceAsStream("/image.png"));
        stage.getIcons().add(icon);
        stage.setTitle("Set Timer");
        stage.setScene(new Scene(root, 365, 262));
        stage.show();
        // SetTimerController.openWindow();
    }


    public void Print(ActionEvent actionEvent) {
/*
        table gui = new table();
        gui.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        //  gui.setSize(640,560);

        gui.setBounds(300, 50, 1080, 610);
        gui.setVisible(true);

 */

    }

    public void onEditAddRow(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(SetTimerController.class.getResource("/AddRow.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("/RevisionTable.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);
        Image icon = new Image(SetTimerController.class.getResourceAsStream("/image.png"));
        stage.getIcons().add(icon);
        stage.setTitle("Add Row");
        stage.setScene(new Scene(root, 756, 213));
        stage.show();
    }

    public void addTextToNote(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (textToNotes.getText() != null || !(textToNotes.getText().equals(""))) {
            Connection con = DBConector.getConections();
            try {
                Date dateToSend = new Date();
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String dateTodaySend = (df.format(dateToSend));

                PreparedStatement sendDBNotes = con.prepareStatement("insert into " + DBName + ".notes (date, notification) values ('" + dateTodaySend + "', '" + textToNotes.getText() + "');");
                sendDBNotes.executeLargeUpdate();
                textToNotes.clear();
                refreshDay();
            } catch (Exception e) {
                System.out.println(e + "send notes to DB");
            }

        }
    }






  /*
    public static class table extends JFrame {
        JTable table;

        public table() {
            setLayout(new BorderLayout());
           // table.setPreferredScrollableViewportSize(new Dimension(1048, 510));
            setPreferredSize(new Dimension(1048,510));
            DefaultTableModel model = new DefaultTableModel();
            model = model1;
            table = new JTable(model);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);



            table.getColumnModel().getColumn(0).setPreferredWidth(90);
            table.getColumnModel().getColumn(1).setPreferredWidth(45);
            table.getColumnModel().getColumn(2).setPreferredWidth(80);
            table.getColumnModel().getColumn(5).setPreferredWidth(40);
            table.getColumnModel().getColumn(6).setPreferredWidth(40);
            table.getColumnModel().getColumn(7).setPreferredWidth(120);
            table.getColumnModel().getColumn(8).setPreferredWidth(120);
            table.getColumnModel().getColumn(9).setPreferredWidth(80);
            table.getColumnModel().getColumn(10).setPreferredWidth(80);
            table.getColumnModel().getColumn(11).setPreferredWidth(50);
            table.getColumnModel().getColumn(12).setPreferredWidth(50);
            JScrollPane scrollPane = new JScrollPane(table);
            add(scrollPane,BorderLayout.CENTER);


            JButton button = new JButton("Print");
          //  table.setFillsViewportHeight(true);
            // revisionTableView.setBorder(new EtchedBorder((EtchedBorder.RAISED)));
            table.setGridColor(Color.lightGray);
            UIManager.getLookAndFeelDefaults().put("Table.alternateRowColor", Color.getHSBColor(36,16,83));
            add(button, BorderLayout.SOUTH);
            table.setFont(new Font("Consolas",Font.ITALIC,14));
            table.setRowHeight(25);

            ActionListener printAction = e -> {
             //   String printHeader = "";
                //  table.setFont(new Font("Dialog",Font.BOLD,12));
               // printMode = JTable.PrintMode.FIT_WIDTH;
                 // table.getTableHeader().setFont(new Font("Dialog",Font.ITALIC,10));
                MessageFormat header = new MessageFormat("Каса: "+ totalSumPrint + " Печалба: "+totalSumInterestPrint);
                MessageFormat footer = new MessageFormat("Page{0,number,integer}");
                PrintRequestAttributeSet set = new HashPrintRequestAttributeSet();
                set.add(OrientationRequested.PORTRAIT);
                PrinterJob job = PrinterJob.getPrinterJob();
                PageFormat pf = job.defaultPage();
                Paper paper = pf.getPaper();
                double margin = 30;

                //   table.print(printMode, header, footer, true, set, true);
                paper.setImageableArea(margin,paper.getImageableY(),paper.getWidth() -2*margin,paper.getImageableHeight());
                pf.setPaper(paper);

                //  pf.setOrientation(PageFormat.LANDSCAPE);

                job.setPrintable(table.getPrintable(JTable.PrintMode.FIT_WIDTH,header,footer));
                job.validatePage(pf);
                if (job.printDialog()) {
                    try {
                        job.print();
                    } catch (PrinterException ex) {
                        ex.printStackTrace();
                    }
                }

                try {
                    PrintRequestAttributeSet set = new HashPrintRequestAttributeSet();
                    set.add(OrientationRequested.LANDSCAPE);
                    MessageFormat footer = new MessageFormat("Page{0,number,integer}");
                    MessageFormat header = new MessageFormat("print Report");

                    // revisionTableView.setFont(new Font("Consolas",Font.BOLD,14));
                    table.print(JTable.PrintMode.FIT_WIDTH, header, footer, true, set, true);

                } catch (PrinterException pe) {
                    System.err.println("Error Print" + pe.getMessage());
                }



            };
            button.addActionListener(printAction);


        }


    }

   */


}

