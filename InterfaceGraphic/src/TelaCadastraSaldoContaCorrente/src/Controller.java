package TelaCadastraSaldoContaCorrente.src;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Button btn_cancelar,btn_adicionar;
    public void handleButtonAction(ActionEvent event) throws Exception {
        if (event.getSource() == btn_cancelar)
        {
            Stage stage = (Stage) btn_cancelar.getScene().getWindow(); //pega a janela atual
            stage.close();
        }
        else if (event.getSource() == btn_adicionar)
        {
            //implements
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
