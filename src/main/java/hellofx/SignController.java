package hellofx;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class SignController {

    public JFXTextField Username;
    public JFXTextField email;
    public JFXPasswordField Password;
    public JFXTextField code;
    public Text invalidData;
    public AnchorPane signIn;
    public Label notification;

    private void closeStageSingIn() {
        ((Stage) signIn.getScene().getWindow()).close();
    }


    public void Back(ActionEvent actionEvent) throws IOException {
        closeStageSingIn();
        loadLog();
    }
    private void loadLog() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/loginTable.fxml")));
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Login");
        stage.setScene(new Scene(root, 341, 249));
        stage.show();
    }

    public void SubmitDB(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        String c  = code.getText();
        String u  = Username.getText();
        String p  = Password.getText();
        String isAdmin = "";
        notification.setText("");

            if(c.equals("0541") && !u.equals("") && !p.equals("")){
                isAdmin = isAdmin(u);
                if (isAdmin != null) {
                    try {
                        java.util.Date dateToSend = new Date();
                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        String dateSend = (df.format(dateToSend));
                        Connection con = DBConector.getConections();
                        PreparedStatement posted = con.prepareStatement("INSERT into users (date, username, password, mail, verKey, isAdmin)" +
                                "VALUES('" + dateSend + "', '" + Username.getText() + "', '" + Password.getText() + "', '" + email.getText() + "', '" + code.getText() + "', '" + isAdmin + "');");


                        posted.executeLargeUpdate();
                        closeStageSingIn();
                        loadLog();
                    } catch (SQLException e) {
                        e.printStackTrace();
                        System.out.println(e);
                    }
                    try {
                        DBConector.closeConnection();
                        System.out.println("close connection DB");

                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                }else{
                    notification.setText("Username is busy !");
                }
        }
    }

    private String isAdmin(String u) {
        if(u.equals("admin")){
            List<String> isAdmin = new ArrayList<>();
            List<String> containsUser = new ArrayList<>();
            try {
                Connection con = DBConector.getConections();
                ResultSet rs = con.createStatement().executeQuery("select * from "+DBConector.DBName+".users");
                while (rs.next()){
                    isAdmin.add(rs.getString(7));
                    containsUser.add(rs.getString(3));
                }
                if (containsUser.contains(Username.getText())){
                    return null;
                }

                for(int i = 0; i< isAdmin.size(); i++){
                    if(isAdmin.get(i).equals("Administrator")) return "user";
                }
                return "Administrator";
            }catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return "user";

    }


}
