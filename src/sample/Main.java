package sample;

import TelaLogin.src.login;
import TelaRecadastrarSenha.src.RecadastrarSenha;
import TelaRecuperarSenha.src.RecuperarSenha;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        login l = new login();
        l.start(new Stage());
        RecuperarSenha r = new RecuperarSenha();
        r.start(new Stage());
        RecadastrarSenha rs = new RecadastrarSenha();
        rs.start(new Stage());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
