package TelaCadastrarGastos.src;

import MSSQL.SaldoCCMSSQL;
import Utilitarios.Utilitarios;
import Validacao.ValidaSaldoCC;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import vos.SaldoCC;

import javax.swing.*;
import java.net.URL;
import java.sql.SQLException;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML private TextField txtValor;
    @FXML private DatePicker dtpDataGasto;
    @FXML private TextArea txtDescricao;
    public SaldoCCMSSQL dao;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtValor.setText("0");
    }

    public void adicionarGasto(MouseEvent mouseEvent) throws SQLException {
        Utilitarios util = Utilitarios.getInstancia();
        SaldoCC gasto = new SaldoCC();
        ArrayList<String> erros;
        ValidaSaldoCC validacao = new ValidaSaldoCC();

        try {
            gasto.setValor(Double.parseDouble(txtValor.getText()));
            gasto.setDescricao(txtDescricao.getText());
            if (dtpDataGasto.getValue() != null) {
                gasto.setData(Date.from(Instant.from(dtpDataGasto.getValue().atStartOfDay(ZoneId.systemDefault()))));
            }
            gasto.setCCId(util.idConta); //AJUSTAR
            dao = new SaldoCCMSSQL();
            erros = validacao.ValidaDados(gasto);
            gasto.setValor(gasto.getValor()*-1); //ajustando o valor para negativo, pois é um gasto
            if(erros.size() > 0){
                popupError(erros);
            }
            else {
                dao.salvar(gasto);
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
