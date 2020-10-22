package TelaPainelPostIt.src;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PainelPostIt extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Painel.fxml"));
        primaryStage.setTitle("Atividades");
        primaryStage.setScene(new Scene(root, 700, 430));
        primaryStage.show();
    }

    public void handleButtonAction(ActionEvent actionEvent) {
    }
}
