package TelaFinancas.src;

import TelaCadastraSaldoContaCorrente.src.SaldoContaCorrente;
import TelaCadastrarGastos.src.Gastos;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Button btn_adicionarGasto,btn_adicionarSaldo;

    public void handleButtonAction(javafx.event.ActionEvent event) throws Exception {
        if (event.getSource() == btn_adicionarGasto)
        {
            Gastos tela = new Gastos();
            tela.start(new Stage());
        }
        else if(event.getSource() == btn_adicionarSaldo)
        {
            SaldoContaCorrente tela = new SaldoContaCorrente();
            tela.start(new Stage());
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
