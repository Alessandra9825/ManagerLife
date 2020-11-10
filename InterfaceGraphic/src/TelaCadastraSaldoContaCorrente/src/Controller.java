package TelaCadastraSaldoContaCorrente.src;

import Basis.Entidade;
import MSSQL.SaldoCCMSSQL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import vos.SaldoCC;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    //Debugging area
    int id = 1; //id conta corrente TEMP
    //

    @FXML
    DatePicker DPDataPagamento;
    @FXML
    TextField txtValor;
    @FXML
    TextArea txtDescricao;
    @FXML
    ChoiceBox cbSituacao;

    public void cancelar(ActionEvent event){
        Stage stage = (Stage) txtValor.getScene().getWindow();
        stage.close();
    }

    public void adicionar(ActionEvent event) throws SQLException {
        SaldoCC saldo = new SaldoCC();
        LocalDate dataLocal = DPDataPagamento.getValue();
        Date data = Date.from(dataLocal.atStartOfDay(ZoneId.systemDefault()).toInstant());
        saldo.setData(data);
        saldo.setDescricao(txtDescricao.getText());
        saldo.setCCId(id);
        saldo.setMensal(false); //revisar??
        saldo.setValor(Double.parseDouble(txtValor.getText()));
        saldo.setId(2);
        SaldoCCMSSQL dao = new SaldoCCMSSQL();
        dao.salvar(saldo);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
