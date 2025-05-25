package hellofx;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.util.Pair;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class ExeptionDialog {
    public static String username1, password1;

    public static void exeptionDialog(SQLException e) {
      /*  String exeption = e.toString();
        Pattern exe = Pattern.compile(":");
        int i = exeption.indexOf(String.valueOf(exe));
        int endIndex = exeption.indexOf("\n");
        String word = exeption.substring(i+1,endIndex -1);
       */
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Exception Dialog");
        alert.setHeaderText("Look, an Exception Dialog");
        alert.setContentText("Error");

        Exception ex = new FileNotFoundException("Exeption");

// Create expandable Exception.
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String exceptionText = e.toString();

        Label label = new Label("The exception stacktrace was:");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

// Set expandable Exception into the dialog pane.
        alert.getDialogPane().setExpandableContent(expContent);

        alert.showAndWait();

    }

    public static void alertDialog(String s) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(s);

        alert.showAndWait();
    }

    public static boolean administrator() {
        // Create the custom dialog.
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Login Dialog");
        dialog.setHeaderText("Look, a Custom Login Dialog");

// Set the icon (must be included in the project).
        dialog.setGraphic(new ImageView(ExeptionDialog.class.getResource("/admin.png").toString()));

// Set the button types.
        ButtonType loginButtonType = new ButtonType("Login", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

// Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField username = new TextField();
        username.setPromptText("Username");
        PasswordField password = new PasswordField();
        password.setPromptText("Password");

        grid.add(new Label("Username:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("Password:"), 0, 1);
        grid.add(password, 1, 1);

// Enable/Disable login button depending on whether a username was entered.
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

// Do some validation (using the Java 8 lambda syntax).
        username.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });

        String a = username.getText();
        String b = password.getText();
        a= a;
        b = b;

        dialog.getDialogPane().setContent(grid);

// Request focus on the username field by default.
        Platform.runLater(username::requestFocus);

// Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(username.getText(), password.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();
        username1 = null;
        password1 = null;
        result.ifPresent(usernamePassword -> {
            //   System.out.println("Username=" + usernamePassword.getKey() + ", Password=" + usernamePassword.getValue());
            //s(usernamePassword.getKey(), usernamePassword.getValue());
            getValues(usernamePassword.getKey(), usernamePassword.getValue());

        });

        return  s(username1, password1);
    }

    private static void getValues(String username, String password) {
        username1 = username;
        password1 = password;
    }



    public static boolean s(String key, String value) {
        List<String> username = new ArrayList<>();
        List<String> password = new ArrayList<>();
        List<String> isAdmin = new ArrayList<>();


        try {
            Connection con = DBConector.getConections();
            ResultSet rs = con.createStatement().executeQuery("select * from "+DBConector.DBName+".users where isAdmin = 'Administrator';");//"select * from "+DBConector.DBName+".users");
            while (rs.next()){
                username.add(rs.getString(3));
                password.add(rs.getString(4));
                isAdmin.add(rs.getString(7));
            }
        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        for(int i = 0; i<username.size(); i++){
            if(isAdmin.get(i).equals("Administrator")){
                if(username.get(i).equals(key)){
                    if(password.get(i).equals(value)){
                        return true;
                    }
                }
            }
        }
        return false;

    }


    public static void exeptionDialog(RuntimeException e) {
    }
}
