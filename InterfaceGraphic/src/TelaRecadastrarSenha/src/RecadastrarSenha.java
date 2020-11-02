package TelaRecadastrarSenha.src;

import Auditoria.GerenciadorAuditoria;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class RecadastrarSenha extends Application {
    public static Stage stage;
    private double xOffset = 0.0D;
    private double yOffset = 0.0D;

    public RecadastrarSenha() {
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void start(final Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("RecadastrarSenha.fxml"));
        primaryStage.setScene(new Scene(root, 500.0D, 300.0D));
        GerenciadorAuditoria.getInstancia().ativar();
        StageStyle var10001 = StageStyle.DECORATED;
        primaryStage.initStyle(StageStyle.UNDECORATED);
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                RecadastrarSenha.this.xOffset = event.getSceneX();
                RecadastrarSenha.this.yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() - RecadastrarSenha.this.xOffset);
                primaryStage.setY(event.getScreenY() - RecadastrarSenha.this.yOffset);
            }
        });
        primaryStage.show();
        primaryStage.setOnCloseRequest((event) -> {
            System.out.println("Thread is closing");
            GerenciadorAuditoria.getInstancia().desativar();
        });
        stage = primaryStage;
    }
}