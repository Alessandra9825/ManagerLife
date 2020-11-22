package TelaCadastraUsuario.src;

import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;

public class CadastrarUsuarioController {
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
        alert.setHeaderText("Usuario Cadastrado com sucesso!");
        alert.showAndWait();
    }
}
