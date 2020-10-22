package TelaLogin.src;

import TelaCadastraUsuario.src.CadastrarUsuario;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.util.ArrayList;

public class loginController {

    public void popupError(ArrayList<String> erros){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText("Ops, Ocorreu um erro!");

        String text = "";

        for (int i = 0; i < erros.size(); i++){
            text += erros.get(i) + "\n";
        }

        alert.setContentText(text);

        alert.showAndWait();
    }

    public void cadastroUsuario() throws Exception {
        CadastrarUsuario tela = new CadastrarUsuario();
        tela.start(new Stage());
    }

    public void home(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText("Ops, Não temos a tela Home!");
        alert.setContentText("Parabéns!!!");

        alert.showAndWait();
    }
}
