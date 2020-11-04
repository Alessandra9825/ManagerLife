package TelaDashboardFinancasTransacoes.src.Dashboard;

import Auditoria.GerenciadorAuditoria;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.io.IOException;

public class Dashboard extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        primaryStage.setTitle("Dashboard");
        primaryStage.setScene(new Scene(root, 600, 416));
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> {
            System.out.println("Thread is closing");
            GerenciadorAuditoria.getInstancia().desativar();
        });
    }

    public void Gastos_btn(ActionEvent event){
        try {
            FXMLLoader fxml = new FXMLLoader(getClass().getResource("/TelaCadastrarGastos/src/Gastos.fxml"));
            Parent root1 = (Parent) fxml.load();
            Stage cenario = new Stage();
            cenario.setTitle("Cadastro de Saldo");
            cenario.setScene(new Scene(root1));
            cenario.showAndWait();
        }
        catch (Exception e)
        {

        }
    }

    public void Saldo_btn(ActionEvent event){
        try {
            FXMLLoader fxml = new FXMLLoader(getClass().getResource("/TelaCadastraSaldoContaCorrente/src/SaldoContaCorrente.fxml"));
            Parent root1 = (Parent) fxml.load();
            Stage cenario = new Stage();
            cenario.setTitle("Cadastro de Saldo");
            cenario.setScene(new Scene(root1));
            cenario.showAndWait();
        }
        catch (Exception e)
        {

        }
    }
}
