package TelaMiniPostIt;
import TelaGerenciaPostIt.src.GerenciaPost;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import vos.PostIt;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerMiniPostIt implements Initializable {

    @FXML
    TextField txt_nomeMini, txt_tempoMini;
    @FXML
    AnchorPane an_postIt;

    String nome,tempo;
    @FXML
    private javafx.scene.control.Button btn_detalhar;

    public void handleButtonAction(javafx.event.ActionEvent event) throws Exception
    {
        if (event.getSource() == btn_detalhar)
        {
            //Carregar Dados do postIt atual
            GerenciaPost tela = new GerenciaPost();
            tela.start( new Stage());
        }
    }

    public void preenchePostItCriado(PostIt post) {
        txt_nomeMini.setText(post.getNome());
        txt_tempoMini.setText(post.getTempo());
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}
