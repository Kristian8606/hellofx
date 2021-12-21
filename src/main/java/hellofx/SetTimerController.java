package hellofx;

import com.jfoenix.controls.JFXTimePicker;

import javafx.event.ActionEvent;

import javafx.stage.Stage;

import java.io.IOException;

import java.time.LocalTime;



public class SetTimerController {
    public static String TimeToComp = "16:30";
    public JFXTimePicker TimePicker;

    public static void openWindow() throws IOException {

    }

    public void SetTimeBut(ActionEvent actionEvent) {
        LocalTime time = TimePicker.getValue();
        TimeToComp = time.toString();
        System.out.println(time.toString());
            ((Stage) TimePicker.getScene().getWindow()).close();
    }


}
