package TelaLogin.src;

import Acesso.Acesso;
import Auditoria.GerenciadorAuditoria;
import TelaCadastraUsuario.src.CadastrarUsuario;
import TelaRecuperarSenha.src.RecuperarSenha;
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
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class loginController implements Initializable {

    private Stage stage;
    @FXML private TextField user_txt;
    @FXML private TextField pass_txt;
    @FXML private ImageView imageLogin;
    @FXML private ImageView imageKey;
    @FXML private ImageView imageUser;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File login = new File("InterfaceGraphic/src/TelaLogin/src/image/login.png");
        Image loginImage = new Image(login.toURI().toString());
        imageLogin.setImage(loginImage);

        File user = new File("InterfaceGraphic/src/TelaLogin/src/image/user.png");
        Image userImage = new Image(user.toURI().toString());
        imageUser.setImage(userImage);

        File key = new File("InterfaceGraphic/src/TelaLogin/src/image/key.png");
        Image keyImage = new Image(key.toURI().toString());
        imageKey.setImage(keyImage);
    }

    @FXML
    public void exit(ActionEvent event){
        stage = (Stage)imageLogin.getScene().getWindow();
        stage.close();
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
    public void RecuperarSenha_Button(ActionEvent event) throws IOException {
        RecuperarSenha tela = new RecuperarSenha();
        tela.start(new Stage());
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
