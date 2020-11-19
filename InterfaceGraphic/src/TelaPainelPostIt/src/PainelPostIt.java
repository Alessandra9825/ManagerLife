package TelaPainelPostIt.src;

import TelaMiniPostIt.ControllerMiniPostIt;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import vos.PostIt;

import java.io.IOException;

public class PainelPostIt extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.initStyle(StageStyle.UNDECORATED);
        Parent root = FXMLLoader.load(getClass().getResource("Painel.fxml"));
        primaryStage.setScene(new Scene(root, 600, 500));
        primaryStage.initModality(Modality.WINDOW_MODAL);
        primaryStage.showAndWait();
    }

    public void tornaComponentesVisivel(AnchorPane telaPost) throws IOException {
        Stage painel = new Stage();
        painel.initStyle(StageStyle.UNDECORATED);
        painel.initModality(Modality.WINDOW_MODAL);
        Parent root;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ControllerPainel.class.getResource("Painel.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        painel.setScene(scene);
        // Define a pessoa no controller.
        ControllerPainel controller = loader.getController();
        controller.an_do.getChildren().setAll(telaPost);
    }
}
