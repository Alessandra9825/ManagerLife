package TelaPainelPostIt.src;

import TelaGerenciaPostIt.src.GerenciaPost;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    private Button btn_detalhar,btn_close;

    public void handleButtonAction(javafx.event.ActionEvent event) throws Exception {
        if (event.getSource() == btn_detalhar)
        {
            GerenciaPost tela = new GerenciaPost();
            tela.start(new Stage());
        }
        else if(event.getSource() == btn_close)
        {
            Stage stage = (Stage) btn_close.getScene().getWindow(); //pega a janela atual
            stage.close();
        }
    }



}
