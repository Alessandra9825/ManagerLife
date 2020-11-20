package TelaFinancas.src;

import MSSQL.ContaCorrenteMSSQL;
import MSSQL.SaldoCCMSSQL;
import TelaCadastraSaldoContaCorrente.src.SaldoContaCorrente;
import TelaCadastrarGastos.src.Gastos;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import vos.ContaCorrente;
import vos.SaldoCC;

import javax.swing.text.LabelView;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Button btn_adicionarGasto,btn_adicionarSaldo;

    @FXML
    private Label lblSaldo;

    @FXML
    private TextArea txtHistorico;

    @FXML
    private LineChart<?, ?> lcGrafico;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;

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
        SaldoCCMSSQL daoSaldo = new SaldoCCMSSQL();
        ContaCorrenteMSSQL daoConta = new ContaCorrenteMSSQL();
        try {
            ArrayList<SaldoCC> historico = daoSaldo.localizaTodasTransicoes(1);
            SimpleDateFormat formatacao = new SimpleDateFormat("dd/MM/yy");
            txtHistorico.clear();
            for (SaldoCC transacao: historico) {
                txtHistorico.appendText(formatacao.format(transacao.getData()) + " - R$" + String.format("%.2f", transacao.getValor()) + "\n");
            }
            ContaCorrente conta = daoConta.localiza(1);
            double valor = conta.getValor();
            lblSaldo.setText("R$ "+String.format("%.2f",valor));
            ArrayList<SaldoCC> ultimas30 = daoSaldo.localizaUltima30Transicoes(1);
            XYChart.Series linha = new XYChart.Series();
            int aux = ultimas30.size();
            double[] valorAPlotar = new double[aux];
            String[] dataAPlotar = new String[aux];
            for (int i = aux-1; i>= 0; i--)
            {
                try {
                    valorAPlotar[i] = valorAPlotar[i + 1] - ultimas30.get(i+1).getValor();
                    dataAPlotar[i] = formatacao.format(ultimas30.get(i).getData());
                }
                catch (IndexOutOfBoundsException e)
                {
                    valorAPlotar[i] = valor;
                    dataAPlotar[i] = formatacao.format(ultimas30.get(i).getData());
                }
            }
            for (int i = 0; i< valorAPlotar.length;i++) {
                linha.getData().add(new XYChart.Data(dataAPlotar[i],valorAPlotar[i]));
            }
            lcGrafico.getData().addAll(linha);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
