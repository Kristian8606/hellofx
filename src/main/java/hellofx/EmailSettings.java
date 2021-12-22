package hellofx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import static hellofx.ReadJson.myJson;


public class EmailSettings implements Initializable {

    @FXML
    public Spinner<Integer> spinerHours;
    @FXML
    public  PasswordField passwordTextField;
    @FXML
    public  TextField fromEmailTextField;
    @FXML
    public  TextField toEmailTextField;
    @FXML
    public Spinner<Integer> spinerMinute;

    static int H = myJson.getH();
    static int m = myJson.getM();
    static String user;
    static String pass;
    static String toUser;

    public Text labelEmail;
    public Pane pane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        spinerHours.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,23, myJson.getH()));
        spinerMinute.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,59,myJson.getM()));
        setTextField();
    }



    public  void setTextField() {


        try {

            fromEmailTextField.setText(myJson.getFromMail());
            passwordTextField.setText(myJson.getPassword());
            toEmailTextField.setText(myJson.getToMail());
            H = myJson.getH();
            m = myJson.getM();
            user = myJson.getFromMail();
            pass = myJson.getPassword();
            toUser = myJson.getToMail();
            System.out.println(myJson.getFromMail());
            System.out.println(myJson.getPassword());
            System.out.println(myJson.getToMail());
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public void saveButton(ActionEvent actionEvent) throws Exception {
        if(myJson.isRead()) {
            QuartzTest.stop();
            System.out.println("Quartz stop from Button");
        }
            try{
            int hours = Integer.parseInt(String.valueOf(spinerHours.getValue()));
            int minutes = Integer.parseInt(String.valueOf(spinerMinute.getValue()));
            myJson.setFromMail(fromEmailTextField.getText());
            myJson.setPassword(passwordTextField.getText());
            myJson.setToMail(toEmailTextField.getText());
            myJson.setH(hours);
            myJson.setM(minutes);
            H = myJson.getH();
            m = myJson.getM();
            myJson.writeJson();

            if (myJson.readJson()) {
                QuartzTest.run();
                System.out.println("Save settings");
                myJson.getMailLogLabel().setText("the email will be sent: " + myJson.getH() + ":" + myJson.getM() + "h");
            }

            ((Stage) pane.getScene().getWindow()).close();
        }catch (Exception e){
            e.printStackTrace();
        }


    }

}
