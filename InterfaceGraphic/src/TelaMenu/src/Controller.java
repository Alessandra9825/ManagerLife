package TelaMenu.src;

import TelaPainelPostIt.src.ControllerPainel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Button btn_config, btn_financas,btn_eventos,btn_logOut,btn_painel,btn_home;
    @FXML
    private AnchorPane an_painelCentral;


    @FXML
    // responsavel por link as ações aos botões p chamar as telas
    public void handleButtonAction(javafx.event.ActionEvent event) throws Exception {
        if(event.getSource()== btn_painel)
        {
            AnchorPane panePainel = FXMLLoader.load(ControllerPainel.class.getResource("../../TelaPainelPostIt/src/Painel.fxml"));
            an_painelCentral.getChildren().setAll(panePainel);
        }
        else if(event.getSource()== btn_home)
        {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../../TelaHome/home.fxml"));
            an_painelCentral.getChildren().setAll(pane);
        }
        else if(event.getSource()== btn_config)
        {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../../TelaConfiguracao/src/configuracoes.fxml"));
            an_painelCentral.getChildren().setAll(pane);
        }
        else if(event.getSource()== btn_eventos)
        {
        }
        else if(event.getSource()== btn_financas)
        {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../../TelaFinancas/src/Dashboard.fxml"));
            an_painelCentral.getChildren().setAll(pane);
        }
        else if(event.getSource()== btn_logOut)
        {
            Stage stage = (Stage) btn_logOut.getScene().getWindow(); //pega a janela atual
            stage.close();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../../TelaHome/home.fxml"));
            an_painelCentral.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
