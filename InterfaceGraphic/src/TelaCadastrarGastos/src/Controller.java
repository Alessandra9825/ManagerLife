package TelaCadastrarGastos.src;
import Enums.EnumFrequencia;
import MSSQL.GastosMSSQL;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import vos.Gastos;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML private RadioButton rbGastoGeral, rbGastoCartaoCredito;
    @FXML private TextField txtValor;
    @FXML private DatePicker dtpDataGasto;
    @FXML private TextArea txtDescricao;
    @FXML private ComboBox cbFrequencia;
    public GastosMSSQL dao;

    private final LocalDate datePicker(String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }

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
            gasto.setId(gasto.getId());
            gasto.setTipo(getTipoGasto());
            gasto.setValor(Double.parseDouble(txtValor.getText()));
            gasto.setDescricao(txtDescricao.getText());
            if(dtpDataGasto.getValue() != null){
                gasto.setData(Date.from(Instant.from(dtpDataGasto.getValue().atStartOfDay(ZoneId.systemDefault()))));
            }
            gasto.setFrequencia((String) cbFrequencia.getSelectionModel().getSelectedItem());
            dao = new GastosMSSQL();
            dao.inserirGastos(gasto);
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
