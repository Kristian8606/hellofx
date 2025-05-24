package hellofx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;


public class MainFX extends Application {
    public static double WIDE = 1200;
    public static int HIGH = 660;

    public void start(Stage primaryStage) throws Exception {


        Parent root = FXMLLoader.load(Objects.requireNonNull(MainFX.class.getResource("/loginTable.fxml")));
        Scene scene = new Scene(root,280,180);
        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/image.png")));
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();

    }




    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void stop() throws Exception {
        Controller.myController.quartz.deleteJob();
        Controller.myController.quartz.stop();
        super.stop();
        System.exit(0);
    }

}
