package TelaPainelPostIt.src;

import TelaConfiguracao.src.Configuracoes.Configuracoes;
import TelaGerenciaPostIt.src.GerenciaPost;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private Button btn_config, btn_financas,btn_detalhar,btn_eventos,btn_calendario;
    @FXML

    public void handleButtonAction(javafx.event.ActionEvent event) throws IOException {
        if(event.getSource()== btn_calendario)
        {

        }
        else if(event.getSource()== btn_config)
        {
            Configuracoes tela = new Configuracoes();
            tela.start(new Stage());
        }
        else if(event.getSource()== btn_eventos)
        {
        }
        else if(event.getSource()== btn_financas)
        {
        }
        else if(event.getSource()== btn_detalhar)
        {
            GerenciaPost tela = new GerenciaPost();
            tela.start(new Stage());
        }
    }


}
