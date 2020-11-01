package TelaGerenciaPostIt.src;

import javafx.fxml.FXML;
import javafx.stage.Stage;

public class Controller {
    @FXML
    private  javafx.scene.control.Button btn_cancelar;
    @FXML
    private void Cancelar(){
        Stage stage = (Stage) btn_cancelar.getScene().getWindow(); //pega a janela atual
        stage.close();
    }
}
