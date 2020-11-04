package TelaCadastraSaldoContaCorrente.src;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    TextField txtDataPagamento;

    public void cancelar(ActionEvent event){
        Stage stage = (Stage) txtDataPagamento.getScene().getWindow();
        stage.close();
    }

    public void adicionar(ActionEvent event){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
