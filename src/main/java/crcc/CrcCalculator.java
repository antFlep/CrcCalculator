package crcc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CrcCalculator extends Application {

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
        System.out.println("hello World!");
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws RuntimeException, IOException {
        Parent root;
        root = FXMLLoader.load(getClass().getResource("/crcc/gui/CrcCalculator.fxml"));

        primaryStage.setTitle("CRCC - The CRC 32 Calculator");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
