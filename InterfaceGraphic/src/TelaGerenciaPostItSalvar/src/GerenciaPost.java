package TelaGerenciaPostItSalvar.src;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.util.ArrayList;

public class GerenciaPost extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    public static boolean isOpen = false;
    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.initStyle(StageStyle.UNDECORATED);
        Parent root = FXMLLoader.load(getClass().getResource("PostIt.fxml"));
        primaryStage.setScene(new Scene(root, 495, 350));
        primaryStage.showAndWait();
    }

    public void popupError(ArrayList<String> erros){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initStyle(StageStyle.UNDECORATED);
        alert.setTitle("Error Dialog");
        alert.setHeaderText("Ops, Ocorreu um erro!");

        String text = "";

        for (int i = 0; i < erros.size(); i++){
            text += erros.get(i) + "\n";
        }

        alert.setContentText(text);

        alert.showAndWait();
    }
    public void popupSuccess(Stage stage){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initStyle(StageStyle.UNDECORATED);
        alert.setTitle("Sucesso");
        alert.setHeaderText("Post-It Cadastrado com sucesso!");
        alert.showAndWait();
    }
    public void popupLotado(Stage stage){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initStyle(StageStyle.UNDECORATED);
        alert.setTitle("Lotado");
        alert.setHeaderText("Infelizmente não é possivel realizar inclusão de novos Post-It!");
        alert.showAndWait();
    }


}
