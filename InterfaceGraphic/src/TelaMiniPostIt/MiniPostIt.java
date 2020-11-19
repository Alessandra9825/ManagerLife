package TelaMiniPostIt;

import TelaGerenciaPostIt.src.GerenciaPost;
import TelaMenu.src.Controller;
import TelaPainelPostIt.src.ControllerPainel;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import vos.PostIt;

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
    public void showPostIt(PostIt post) throws IOException {
        Stage postIt = new Stage();
        postIt.initStyle(StageStyle.UNDECORATED);
        postIt.initModality(Modality.NONE);
        Parent root;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ControllerMiniPostIt.class.getResource("MiniPostIt.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        postIt.setScene(scene);
        // Define a pessoa no controller.
        ControllerMiniPostIt controller = loader.getController();
        controller.preenchePostItCriado(post);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            FXMLLoader.load(getClass().getResource("MiniPostIt.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
