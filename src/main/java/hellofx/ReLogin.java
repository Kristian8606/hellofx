package hellofx;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static hellofx.DBConector.DBName;

public class ReLogin {

    public Button CancelSingInRelog;
    public AnchorPane pane;
    public TextField username;
    public PasswordField password;
    public Button ButtonReLogin;


    public void actionLoginPassword(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        System.out.println(password.getText() + "Enter button");
        if (checkPassandUser()){
            System.out.println("User and Password is contains!");
            LoginController.loginUser(username.getText());
            System.out.println("Login User!");
            closeStageReLogin();
            Controller.myController.refreshDay();

        }else{
            System.out.println("User and Password not contains!");
        }
    }

    public void actionReLoginButton(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        System.out.println(password.getText() + "click Button");
        if (checkPassandUser()){
            System.out.println("User and Password is contains!");
            LoginController.loginUser(username.getText());
            System.out.println("Login User!");
            closeStageReLogin();
            Controller.myController.refreshDay();

        }else{
            System.out.println("User and Password not contains!");
        }
    }

    public void actionCancelRelogin(ActionEvent actionEvent) {
    }

    public boolean checkPassandUser ( ) throws SQLException, ClassNotFoundException {
        Connection con = DBConector.getConections();
        String userInput = username.getText();  // потребителското име, което търсим
        String query = "SELECT password FROM " + DBName + ".users WHERE username = ?";

        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, userInput);

        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            String passwords = rs.getString("password");

            if (passwords.equals(password.getText())){
                System.out.println("Паролата на потребителя е: " + passwords);
                return true;
            }else{
                System.out.println("Паролата не отговаря!");
                hellofx.ExeptionDialog.alertDialog("Моля, въведете валидно потребителско име или парола.");
            }

        } else {
            System.out.println("Потребителят не е намерен.");
            hellofx.ExeptionDialog.alertDialog("Потребителят не е намерен.");
            return false;
        }
        return false;
        /*
        List<String> userList = new ArrayList<>();
        List<String> passwordList = new ArrayList<>();
        boolean conteinsPasword = false;
        boolean conteinsUser = false;

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

                userList.clear();
                passwordList.clear();
                userList = null;
                passwordList = null;
               return true;
            }
            return false;

         */
    }

    public void closeStageReLogin() {
        ((Stage) pane.getScene().getWindow()).close();
    }
}

