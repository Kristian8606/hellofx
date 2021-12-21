package hellofx;

import javafx.application.Platform;

import java.util.TimerTask;

public class myTask extends TimerTask {
    @Override
    public void run() {
        Platform.runLater(SendEmail::send);
    }
}
