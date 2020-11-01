package TelaConfiguracao.src.Configuracoes;

import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.awt.*;

public class Controller {
    @FXML
    private  javafx.scene.control.Button btn_cancelar;
    @FXML
    private void Cancelar(){
        Stage stage = (Stage) btn_cancelar.getScene().getWindow(); //pega a janela atual
        stage.close();
    }
}
