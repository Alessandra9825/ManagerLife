package TelaLogin.src;

import Acesso.Acesso;
import Auditoria.GerenciadorAuditoria;
import TelaCadastraUsuario.src.CadastrarUsuario;
import Validacao.ValidaLogin;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import vos.Usuario;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class loginController implements Initializable {

    @FXML private TextField user_txt;
    @FXML private TextField pass_txt;
    @FXML private ImageView imageLogin;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File file = new File("InterfaceGraphic/src/TelaLogin/src/image/login.png");
        Image image = new Image(file.toURI().toString());
        imageLogin.setImage(image);
    }

    @FXML
    public void exit(ActionEvent event){
        System.exit(0);
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
            popupError(erros);
        }
        else
        {
            //busca usuario no banco e salvo no log se foi OK ou nao
            Acesso buscaUsuario = new Acesso();
            logado = buscaUsuario.validaUsuario(user);

            if(logado){
                home();
            }
            else{
                erros.add("Usuário não cadastrado");
                GerenciadorAuditoria.getInstancia().adicionaMsgAuditoria("Login - Usuário não cadastrado");
                popupError(erros);
            }
        }
    }

    @FXML
    public void RecuperarSenha_Button(ActionEvent event){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText("Recuperar Senha!");
        dialog.setContentText("Informe seu e-mail:");

        dialog.initStyle(StageStyle.DECORATED.UNDECORATED);
        Optional<String> result = dialog.showAndWait();

        // The Java 8 way to get the response value (with lambda expression).
        result.ifPresent(name -> System.out.println("Your name: " + name));
    }

    @FXML
    public void CadastrarUsuario_Button(ActionEvent event) throws Exception {
        CadastrarUsuario tela = new CadastrarUsuario();
        tela.start(new Stage());
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

    public void home(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText("Ops, Não temos a tela Home!");
        alert.setContentText("Parabéns!!!");

        alert.showAndWait();
    }
}
