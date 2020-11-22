package TelaCadastraSaldoContaCorrente.src;

import Basis.Entidade;
import MSSQL.SaldoCCMSSQL;
import Utilitarios.Utilitarios;
import Validacao.ValidaSaldoCC;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import singleUsuario.usuarioSingleton;
import vos.SaldoCC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    DatePicker DPDataPagamento;
    @FXML
    TextField txtValor;
    @FXML
    TextArea txtDescricao;


    public void cancelar(ActionEvent event){
        Stage stage = (Stage) txtValor.getScene().getWindow();
        stage.close();
    }

    public void adicionar(ActionEvent event) throws SQLException {
        Utilitarios util = Utilitarios.getInstancia();
        SaldoCC saldo = new SaldoCC();
        ArrayList<String> erros;
        ValidaSaldoCC validacao = new ValidaSaldoCC();
        try {
            if (DPDataPagamento.getValue() != null) {
                saldo.setData(Date.from(Instant.from(DPDataPagamento.getValue().atStartOfDay(ZoneId.systemDefault()))));
            }
            saldo.setDescricao(txtDescricao.getText());
            saldo.setCCId(util.idConta);
            saldo.setValor(Double.parseDouble(txtValor.getText()));
            SaldoCCMSSQL dao = new SaldoCCMSSQL();
            erros = validacao.ValidaDados(saldo);

            if(erros.size() > 0){
                popupError(erros);
            }
            else{
                dao.salvar(saldo);
                JOptionPane.showMessageDialog(null, "Saldo inserido com sucesso! ");
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void popupError(ArrayList<String> erros){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText("Ops, Ocorreu um erro!");

        String text = "";

        for (int i = 0; i < erros.size(); i++){
            text += erros.get(i) + "\n";
        }

        alert.setContentText(text);

        alert.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
