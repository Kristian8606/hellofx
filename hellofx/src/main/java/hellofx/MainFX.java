package hellofx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class MainFX extends Application {
    public static double WIDE = 1270;
    public static int HIGH = 740;

    public void start(Stage primaryStage) throws Exception {

      //  BackupDB.RestoreDB();
        Parent root = FXMLLoader.load(MainFX.class.getResource("/loginTable.fxml"));
        Scene scene = new Scene(root,320,265);
        Image icon = new Image(getClass().getResourceAsStream("/image.png"));
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();

    }




    public static void main(String[] args) {
        launch(args);

    }



}
