package TelaLogin.src;

import Acesso.Acesso;
import Auditoria.GerenciadorAuditoria;
import Validacao.ValidaLogin;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import vos.Usuario;
import java.io.IOException;
import java.util.ArrayList;

public class login extends Application {
    private loginController controller = new loginController();
    @FXML private TextField user_txt;
    @FXML private TextField pass_txt;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("login");
        primaryStage.setScene(new Scene(root, 700, 430));
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> {
            System.out.println("Thread is closing");
            GerenciadorAuditoria.getInstancia().desativar();
        });
    }

    @FXML
    public void Login_Button(ActionEvent event) {
        ValidaLogin validacao = new ValidaLogin();
        Usuario user = new Usuario();
        ArrayList<String> erros;
        boolean logado = false;

        user.setEmail(user_txt.getText());
        user.setSenha(pass_txt.getText());

        erros = validacao.ValidaDados(user);

        if(erros.size() > 0){
            controller.popupError(erros);
        }
        else
        {
            //busca usuario no banco e salvo no log se foi OK ou nao
            Acesso buscaUsuario = new Acesso();
            logado = buscaUsuario.validaUsuario(user);

            if(logado){
                controller.home();
            }
            else{
                erros.add("Usuário não cadastrado");
                GerenciadorAuditoria.getInstancia().adicionaMsgAuditoria("Login - Usuário não cadastrado");
                controller.popupError(erros);
            }
        }
    }

    @FXML
    public void CadastrarUsuario_Button(ActionEvent event) throws Exception {
        controller.cadastroUsuario();
    }
}
