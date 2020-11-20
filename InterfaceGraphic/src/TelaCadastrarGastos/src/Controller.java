package TelaCadastrarGastos.src;
import Enums.EnumFrequencia;
import MSSQL.GastosMSSQL;
import Validacao.ValidaGasto;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import vos.Gastos;

import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
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
        ArrayList<String> erros;
        ValidaGasto validacao = new ValidaGasto();

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

            erros = validacao.ValidaDados(gasto);

            if(erros.size() > 0){
                popupError(erros);
            }
            else {
                JOptionPane.showMessageDialog(null, "Gasto inserido com sucesso! ");
            }

        } catch (Exception e) {
                  JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    @FXML private Button btnCancelar;
    public void cancelarCadastro(MouseEvent mouseEvent) {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    public String getTipoGasto() {
        if(rbGastoCartaoCredito.isSelected()) {
            return rbGastoCartaoCredito.getText();
        }
        else
            return rbGastoGeral.getText();
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
}
