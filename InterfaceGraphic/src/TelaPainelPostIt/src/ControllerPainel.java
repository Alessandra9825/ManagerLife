package TelaPainelPostIt.src;

import TelaGerenciaPostIt.src.GerenciaPost;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerPainel implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    @FXML
    private Button btn_detalhar,btn_adicionar;
    @FXML
    public AnchorPane an_do;

    public void handleButtonAction(javafx.event.ActionEvent event) throws Exception {
        if (event.getSource() == btn_detalhar)
        {
            GerenciaPost tela = new GerenciaPost();
            tela.start(new Stage());
        }
        else if(event.getSource() == btn_adicionar)
        {
            GerenciaPost tela = new GerenciaPost();
            tela.start(new Stage());
        }
    }



}
