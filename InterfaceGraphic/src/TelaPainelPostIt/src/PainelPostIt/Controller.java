package TelaPainelPostIt.src.PainelPostIt;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.awt.event.ActionEvent;

public class Controller {

    @FXML
    private Pane pnl_calendario,pnl_financas,pnl_config,pnl_painel,pnl_eventos;

    @FXML
    private Button btn_config, btn_financas,btn_painel,btn_eventos,btn_calendario;
    @FXML
    private void handleButtonAction(ActionEvent event){
        if(event.getSource()== btn_calendario)
        {
            pnl_calendario.toFront();
        }
        else if(event.getSource()== btn_config)
        {
            pnl_config.toFront();
        }
        else if(event.getSource()== btn_eventos)
        {
            pnl_eventos.toFront();
        }
        else if(event.getSource()== btn_financas)
        {
            pnl_financas.toFront();
        }
        else if(event.getSource()== btn_painel)
        {
            pnl_painel.toFront();
        }
    }
}
