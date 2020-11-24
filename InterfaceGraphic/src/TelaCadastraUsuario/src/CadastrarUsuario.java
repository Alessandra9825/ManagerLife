package TelaCadastraUsuario.src;

import Auditoria.GerenciadorAuditoria;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CadastrarUsuario extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Cadastrar.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("Cadastre-se");
        primaryStage.setScene(new Scene(root, 597, 387));
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> {
            System.out.println("Thread is closing");
            GerenciadorAuditoria.getInstancia().desativar();
        });
    }
}
