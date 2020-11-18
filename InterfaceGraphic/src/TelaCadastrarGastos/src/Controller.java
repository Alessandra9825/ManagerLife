package TelaCadastrarGastos.src;
import Enums.EnumFrequencia;
import MSSQL.GastosMSSQL;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import vos.Gastos;

import javax.swing.*;
import java.net.URL;
import java.util.Date;
import java.sql.SQLException;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML private RadioButton rbGastoGeral, rbGastoCartaoCredito;
    @FXML private TextField txtValor;
    @FXML private DatePicker dtpDataGasto;
    @FXML private TextArea txtDescricao;
    @FXML private ComboBox cbFrequencia;
    public GastosMSSQL dao;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbFrequencia.getItems().addAll(EnumFrequencia.GASTO_UNICO.getDescricao(),
                                       EnumFrequencia.MENSAL.getDescricao());
        cbFrequencia.getSelectionModel().select(0);
        dao = new GastosMSSQL();
        txtValor.setText("0");
    }

    public void adicionarGasto(MouseEvent mouseEvent) throws SQLException {
        Gastos gasto = new Gastos();
        try {
            gasto.setId(gasto.getId());
            gasto.setTipo(getTipoGasto());
            gasto.setValor(Double.parseDouble(txtValor.getText()));
            gasto.setDescricao(txtDescricao.getText());
            if (dtpDataGasto.getValue() != null) {
                gasto.setData(Date.from(Instant.from(dtpDataGasto.getValue().atStartOfDay(ZoneId.systemDefault()))));
            }
            gasto.setFrequencia((String) cbFrequencia.getSelectionModel().getSelectedItem());
            dao = new GastosMSSQL();
            dao.inserirGastos(gasto);

            JOptionPane.showMessageDialog(null, "Gasto inserido com sucesso! ");

        } catch (Exception e) {
                  JOptionPane.showMessageDialog(null, e);
        }
    }

    public void cancelarCadastro(MouseEvent mouseEvent) {

    }

    public String getTipoGasto() {
        if(rbGastoCartaoCredito.isSelected()) {
            return rbGastoCartaoCredito.getText();
        }
        else
            return rbGastoGeral.getText();
    }
}
