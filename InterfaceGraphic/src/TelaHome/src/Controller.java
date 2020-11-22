package TelaHome.src;

import MSSQL.ContaCorrenteMSSQL;
import MSSQL.SaldoCCMSSQL;
import Utilitarios.Utilitarios;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import singleUsuario.usuarioSingleton;
import vos.ContaCorrente;
import vos.SaldoCC;

import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private LineChart<?, ?> lcGrafico;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            ContaCorrenteMSSQL daoConta = new ContaCorrenteMSSQL();
            Utilitarios util = Utilitarios.getInstancia();
            util.idConta = daoConta.localizaPorIdUser(usuarioSingleton.getIdUsuario()).getId();
            SaldoCCMSSQL daoSaldo = new SaldoCCMSSQL();
            ContaCorrente conta = daoConta.localiza(util.idConta);
            double valor = conta.getValor();
            ArrayList<SaldoCC> ultimas7 = daoSaldo.localizaUltima7Transicoes(util.idConta);
            XYChart.Series linha = new XYChart.Series();
            int aux = ultimas7.size();
            double[] valorAPlotar = new double[aux];
            String[] dataAPlotar = new String[aux];
            SimpleDateFormat formatacao = new SimpleDateFormat("dd/MM/yy");
            for (int i = aux-1; i>= 0; i--)
            {
                try {
                    valorAPlotar[i] = valorAPlotar[i + 1] - ultimas7.get(i+1).getValor();
                    dataAPlotar[i] = formatacao.format(ultimas7.get(i).getData());
                }
                catch (IndexOutOfBoundsException e)
                {
                    valorAPlotar[i] = valor;
                    dataAPlotar[i] = formatacao.format(ultimas7.get(i).getData());
                }
            }
            for (int i = 0; i< valorAPlotar.length;i++) {
                linha.getData().add(new XYChart.Data(dataAPlotar[i],valorAPlotar[i]));
            }
            lcGrafico.getData().clear();
            lcGrafico.getData().addAll(linha);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
