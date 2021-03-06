package TelaMiniPostIt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MiniPostIt extends Application implements Initializable {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.initStyle(StageStyle.UNDECORATED);
        Parent root = FXMLLoader.load(getClass().getResource("MiniPostIt.fxml"));
        primaryStage.setScene(new Scene(root, 131, 115));
        primaryStage.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
