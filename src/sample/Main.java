package sample;

import Auditoria.GerenciadorAuditoria;
import TelaLogin.src.login;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try{
            GerenciadorAuditoria.getInstancia().ativar();

            login l = new login();
            l.start(primaryStage);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() {
        GerenciadorAuditoria.getInstancia().desativar();
    }
}
