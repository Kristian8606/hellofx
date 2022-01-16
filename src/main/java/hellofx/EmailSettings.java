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

    static int H = 0;//= Controller.myController.json.getH();
    static int m = 0;//=  Controller.myController.json.getM();
    static String user;
    static String pass;
    static String toUser;

    public Text labelEmail;
    public Pane pane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        spinerHours.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,23,  Controller.myController.json.getH()));
        spinerMinute.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,59, Controller.myController.json.getM()));
        setTextField();
    }



    public  void setTextField() {


        try {

            fromEmailTextField.setText( Controller.myController.json.getFromMail());
            passwordTextField.setText( Controller.myController.json.getPassword());
            toEmailTextField.setText( Controller.myController.json.getToMail());
          //  H =  Controller.myController.json.getH();
          //  m =  Controller.myController.json.getM();
            user =  Controller.myController.json.getFromMail();
            pass =  Controller.myController.json.getPassword();
            toUser =  Controller.myController.json.getToMail();
            System.out.println( Controller.myController.json.getFromMail());
            System.out.println( Controller.myController.json.getPassword());
            System.out.println( Controller.myController.json.getToMail());
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public void saveButton(ActionEvent actionEvent) throws Exception {

            try{
                if( Controller.myController.json.isRead()) {
                    if (Controller.myController.quartz.schedulerCheckExist()) {
                        Controller.myController.quartz.deleteJob();
                        Controller.myController.quartz.stop();
                    }
                }
            int hours = Integer.parseInt(String.valueOf(spinerHours.getValue()));
            int minutes = Integer.parseInt(String.valueOf(spinerMinute.getValue()));
                Controller.myController.json.setFromMail(fromEmailTextField.getText());
                Controller.myController.json.setPassword(passwordTextField.getText());
                Controller.myController.json.setToMail(toEmailTextField.getText());
                Controller.myController.json.setH(hours);
                Controller.myController.json.setM(minutes);
         //   H =  Controller.myController.json.getH();
          //  m =  Controller.myController.json.getM();
                Controller.myController.json.writeJson();

            if ( Controller.myController.json.readJson()) {
                Controller.myController.quartz.run();
                System.out.println("Save settings");
                Controller.myController.json.getMailLogLabel().setText("the email will be sent: " +  Controller.myController.json.getH() + ":" +  Controller.myController.json.getM() + "h");
            }

            ((Stage) pane.getScene().getWindow()).close();
        }catch (Exception e){
            e.printStackTrace();
        }


    }

}
