package hellofx;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

import static hellofx.MainFX.HIGH;
import static hellofx.MainFX.WIDE;
import static hellofx.DBConector.DBName;

public class LoginController implements Initializable {
    public TextField portForTextFile;
    public TextField portForConnect;
    public AnchorPane anchorPane;
    public TextField DbExac;
    Connection con;

    public PasswordField password;
    public TextField username;
    public Text textStatus;
    public AnchorPane pane;
    public Button ButtonLogin;

    public static String User;
    public TextField hostSaveToFile;
    public TextField DBSaveToFile;
    public TextField userSaveToFile;
    public Label lable;
    public PasswordField PassSaveToFile;
    @FXML
    private TextField hostTextFiel;

    @FXML
    private TextField DBTextField;

    @FXML
    private TextField userTextField;

    @FXML
    private TextField passwordTextField;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
/*
    public void actionLogin(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        List<String> userList = new ArrayList<>();
        List<String> passwordList = new ArrayList<>();
        boolean conteinsPasword = false;
        boolean conteinsUser = false;
        try {
            Connection con = DBConector.getConections();
            ResultSet rs = con.createStatement().executeQuery("select username, password from " + DBName + ".users");
            while (rs.next()) {
                userList.add(rs.getString(1));
                passwordList.add(rs.getString(2));
            }
            if (userList.contains(username.getText())) {
                conteinsUser = true;
            } else {
                hellofx.ExeptionDialog.alertDialog("Username is not filled in or is wrong!");
                System.out.println("Username  not valid!");
            }
            if (passwordList.contains(password.getText())) {
                conteinsPasword = true;
            } else {
                hellofx.ExeptionDialog.alertDialog("The password is not filled or is wrong!");

                System.out.println("Password  not valid!");
            }
            if (conteinsPasword && conteinsUser) {
                loginUser(username.getText());
                User = username.getText();
                userList.clear();
                passwordList.clear();
                userList = null;
                passwordList = null;
                loadMainTable();
                closeStageSingIn();
            }
            //   System.out.println("Username  not valid!");


        } catch (SQLException e) {
            // e.printStackTrace();
            System.out.println(e);
            hellofx.ExeptionDialog.exeptionDialog(e);
        }
        try {
            DBConector.closeConnection();
            System.out.println("close connection DB");

        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

 */
public void actionLogin(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
    String user = username.getText();
    String pass = password.getText();

    if (user == null || user.isBlank()) {
        hellofx.ExeptionDialog.alertDialog("Моля, въведете потребителско име.");
        return;
    }

    if (pass == null || pass.isBlank()) {
        hellofx.ExeptionDialog.alertDialog("Моля, въведете парола.");
        return;
    }
    Connection con1 = DBConector.getConections();
    String query = "SELECT * FROM " + DBName + ".users WHERE username = ? AND password = ?";

    try (Connection con = DBConector.getConections();
         PreparedStatement stmt = con.prepareStatement(query)) {

        stmt.setString(1, user);
        stmt.setString(2, pass);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            // Успешен вход
            loginUser(user);
            User = user;
            loadMainTable();
            closeStageSingIn();
        } else {
            // Грешни данни
            hellofx.ExeptionDialog.alertDialog("Невалидни потребителско име или парола.");
        }

    } catch (SQLException | ClassNotFoundException e) {
        System.out.println("Database error: " + e.getMessage());
        assert e instanceof SQLException;
        hellofx.ExeptionDialog.exeptionDialog((SQLException) e);
    } finally {
        try {
            DBConector.closeConnection();
            System.out.println("Затворена връзка с БД");
        } catch (Exception ex) {
            System.out.println("Грешка при затваряне на БД: " + ex.getMessage());
        }
    }
}


    public static void loginUser(String user) throws SQLException, ClassNotFoundException {
        Connection con = DBConector.getConections();
        try {
            String time = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
            String date = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
            System.out.println(date +" "+ time);
            PreparedStatement loginUsers = con.prepareStatement("insert into " + DBName + ".loginUser (date, user) " +
                    "values ('"+date+"', '" + user + "');");
            loginUsers.executeLargeUpdate();
        } catch (Exception ex) {
            System.out.println(ex);

        }
    }


    public void closeStageSingIn() {
        ((Stage) pane.getScene().getWindow()).close();
    }


     public static void loadMainTable() throws IOException {
        //  ExeptionDialog.administrator();
        Parent root = FXMLLoader.load((Objects.requireNonNull(LoginController.class.getResource("/sample.fxml"))));
//        Parent root = FXMLLoader.load(getClass().getResource("/RevisionTable.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);
        Image icon = new Image((Objects.requireNonNull(LoginController.class.getResourceAsStream("/image.png"))));
        stage.getIcons().add(icon);
        stage.setTitle("Table");
        stage.setScene(new Scene(root, WIDE, HIGH));
        stage.show();
        /*
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(5000),
                ae -> doSometing()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        */
    }

    private void doSometing() {
        if (TimeChecker.checkTime()) {
            System.exit(0);
        }
        System.out.println("Task");
    }


    public void CancelSingIn(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void OpenReg(ActionEvent actionEvent) throws IOException {
        closeStageSingIn();
        loadReg();
    }

    private void loadReg() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/signup.fxml")));
        Stage stage = new Stage(StageStyle.DECORATED);
        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/image.png")));
        stage.getIcons().add(icon);
        stage.setTitle("SignIn");
        stage.setScene(new Scene(root, 365, 450));
        stage.show();
    }

    public void CreateTable(ActionEvent actionEvent) throws SQLException, IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/createDB.fxml")));
        Stage stage = new Stage(StageStyle.DECORATED);
        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/image.png")));
        stage.getIcons().add(icon);
        stage.setTitle("SignIn");
        stage.setScene(new Scene(root, 389, 634));
        stage.show();


    }

    public void ConnectDataBase(ActionEvent actionEvent) throws SQLException {
        connectDB();
    }

    private void connectDB() throws SQLException {
        try {
            String host = hostTextFiel.getText();
            String database = DBTextField.getText();
            String user = userTextField.getText();
            String pas = passwordTextField.getText();
            String port = portForConnect.getText();
            //&serverTimezone=UTC, useSSL=false
            con = DriverManager.getConnection("jdbc:mariadb://" + host + ":" + port + "/" + database + "?", user, pas);
            // con = DriverManager.getConnection("jdbc:mysql://192.168.0.108:3306/DB", "user", "admin");
            lable.setText("Connect to Database");


        } catch (Exception ex) {
            lable.setText("Not connected");
            ExeptionDialog.exeptionDialog((SQLException) ex);
            System.out.println(ex);
        }
    }

    public void createDB(String valie) throws SQLException, Exception {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Create Database?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            if (con != null) {
                PreparedStatement createDB = con.prepareStatement("create database if not exists " + valie + ";");
                createDB.executeLargeUpdate();

            } else {
                System.out.println("Not Connected");
                ExeptionDialog.alertDialog("Not Connected");
                return;
            }
            con.close();
            System.out.println("close connection DB");

            System.out.println("Created Database");
            createTable();
        } else {
            System.out.println("Not Created Database");
        }
    }

    public void saveDBButton(ActionEvent actionEvent) throws Exception {
        String host = hostSaveToFile.getText();
        String databaseName = DBSaveToFile.getText();
        String user = userSaveToFile.getText();
        String pass = PassSaveToFile.getText();
        String port = portForTextFile.getText();

        if (!host.equals("") && !databaseName.equals("") && !user.equals("") && !pass.equals("") && !port.equals(""))
            TextFile.WriteTextFile(host, databaseName, user, pass, port);

        createDB(databaseName);
    }

    public void cancelDBButton(ActionEvent actionEvent) {

        ((Stage) anchorPane.getScene().getWindow()).close();
        //System.exit(0);
    }

    private void createTable() {
        String date = DateToday.returnDateToday();
        try {
            Connection con = DBConector.getConections();
            String dbName = DBName;
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Look, a Confirmation Dialog");
            alert.setContentText("Create Table ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                PreparedStatement createTable1 = con.prepareStatement("create table if not exists "+dbName+".table1(ID int auto_increment,date date, mark text, num int(11), interest double(10,2), principal double(10,2)," +
                        "goldGr double(10,2), silverGr double(10,2), newSilverGr double(10,2), goldText varchar(255), silverText varchar(255), newSilverText varchar(255), primary key (ID));");
                createTable1.executeLargeUpdate();

                PreparedStatement createTable2 = con.prepareStatement("create table if not exists " + dbName + ".table2(ID int auto_increment,date date,income double(10,2),cost double(10,2), " +
                        "incomeString varchar(255), costString varchar(255), primary key (ID));");
                createTable2.executeLargeUpdate();

                PreparedStatement createTable3 = con.prepareStatement("create table if not exists " + dbName + ".table3(ID int auto_increment,date date,numberOfBet int(11),sumOfBet double(10,2),primary key (ID));");
                createTable3.executeLargeUpdate();

                PreparedStatement createTable4 = con.prepareStatement("create table if not exists " + dbName + ".table4(ID int auto_increment, date date, goldPrice double(10,2), silverPrice double(10,2), goldText varchar(255), silverText varchar(255), primary key (ID));");
                createTable4.executeLargeUpdate();

                PreparedStatement tableCashbox = con.prepareStatement("create table if not exists "+dbName+".updateAllTable(ID int auto_increment,date date,period varchar(255), interest double(10,2),goldGr double(10,2)," +
                        " silverGr double(10,2),income double(10,2),costs double(10,2), purchasedGold double(10,2),purchasedSilver double(10,2)," +
                        " cashierOfPrevioursRevision double(10,2), startSum double(10,2), newSilverGr double(10,2), primary key (ID));");
                tableCashbox.executeLargeUpdate();

                PreparedStatement users = con.prepareStatement("create table if not exists " + dbName + ".users(ID int auto_increment,date date, username varchar(45), password varchar(45), " +
                        "mail varchar(45), verKey varchar(45), isAdmin text, primary key (ID));");
                users.executeLargeUpdate();

                PreparedStatement loginUser = con.prepareStatement("create table if not exists " + dbName + ".loginUser(ID int auto_increment, date date, user varchar(255),  primary key (ID));");
                loginUser.executeLargeUpdate();

             //   PreparedStatement revisionNumber = con.prepareStatement("create table if not exists " + dbName + ".revisionVariable(numberOfRevision int);");
             //   revisionNumber.executeLargeUpdate();

                /*
                PreparedStatement revisionPeriod = con.prepareStatement("create table if not exists " + dbName + ".revisionPeriod(ID int auto_increment, date date, fromDate date, toDate date, revisionStartSum double(10,2)," +
                        " revisionEndSum double(10,2),  primary key (ID));");
                revisionPeriod.executeLargeUpdate();

                 */

                PreparedStatement notes = con.prepareStatement("create table if not exists " + dbName + ".notes(ID int auto_increment, date date, notification varchar(255),  primary key (ID));");
                notes.executeLargeUpdate();

                PreparedStatement newPeriod = con.prepareStatement("INSERT INTO "+DBName+".updateAllTable (date,period,interest, goldGr, silverGr, income, costs, purchasedGold, purchasedSilver,  cashierOfPrevioursRevision, startSum, newSilverGr)" +
                        " VALUES('"+date+"','0','0','0','0','0','0','0','0','0','0','0');");
                newPeriod.executeLargeUpdate();
                //create revisionTableView DB.loginUser(ID int auto_increment primary key, date datetime,user varchar(255));
            } else {
                // ... user chose CANCEL or closed the dialog
            }
        } catch (Exception e) {
            System.out.println(e);
            ExeptionDialog.exeptionDialog((SQLException) e);
        }
        try {
            DBConector.closeConnection();
            System.out.println("close connection DB");

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }


    public void sqlExac(ActionEvent actionEvent) {
        try {
            if (!DbExac.getText().equals("")){
                String dbName = DbExac.getText();
                Connection con = DBConector.getConections();
                PreparedStatement exac1 = con.prepareStatement("ALTER TABLE `"+dbName+"`.`table1` " +
                        "ADD COLUMN `newSilverText` VARCHAR(255) NULL DEFAULT NULL AFTER `silverText`;");
                exac1.executeLargeUpdate();
                PreparedStatement exac2 = con.prepareStatement("ALTER TABLE `"+dbName+"`.`table1` " +
                        "ADD COLUMN `newSilverGr` DOUBLE(10,2) NULL DEFAULT NULL AFTER `silverGr`;");
                exac2.executeLargeUpdate();
                PreparedStatement exac3 = con.prepareStatement("ALTER TABLE `"+dbName+"`.`updateAllTable` " +
                        "ADD COLUMN `newSilverGr` DOUBLE(10,2) NULL DEFAULT NULL AFTER `startSum`;");
                exac3.executeLargeUpdate();
            }
        }catch (Exception ex){
            ExeptionDialog.exeptionDialog((SQLException) ex);
        }
    }
}
