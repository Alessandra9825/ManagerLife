package TelaCadastraSaldoContaCorrente.src;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class SaldoContaCorrente extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("SaldoContaCorrente.fxml"));
        primaryStage.setTitle("Adicionar saldo CC");
        primaryStage.setScene(new Scene(root, 400, 400));
        primaryStage.show();
    }

}
