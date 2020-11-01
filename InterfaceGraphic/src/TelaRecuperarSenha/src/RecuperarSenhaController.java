package TelaRecuperarSenha.src;

import Acesso.NovaSenha;
import Utilitarios.Utilitarios;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class RecuperarSenhaController implements Initializable {

    private Stage stage;
    @FXML private ImageView imageLock;
    @FXML private ImageView imageEmail;
    @FXML private TextField title;
    @FXML private TextField email_txt;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File lock = new File("InterfaceGraphic/src/TelaRecuperarSenha/src/image/lock.png");
        Image loginImage = new Image(lock.toURI().toString());
        imageLock.setImage(loginImage);

        File email = new File("InterfaceGraphic/src/TelaRecuperarSenha/src/image/mail.png");
        Image emailImage = new Image(email.toURI().toString());
        imageEmail.setImage(emailImage);

        title.setStyle("-fx-text-fill: #0589ff");
    }

    @FXML
    public void exit(ActionEvent event){
        stage = (Stage) title.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void recuperarSenha(ActionEvent event){
        String email = email_txt.getText();

        if(Utilitarios.getInstancia().isNullorEmpty(email))
            emailEmpty();
        else {
            NovaSenha newPass = new NovaSenha();
            String erro = newPass.newPassword(email, "");

            if(Utilitarios.getInstancia().isNullorEmpty(erro))
                newPassSucess();
            else
                newPassError(erro);
        }
    }

    private void newPassError(String erro) {
        File lockError = new File("InterfaceGraphic/src/TelaRecuperarSenha/src/image/lockError.png");
        Image lockErrorImage = new Image(lockError.toURI().toString());
        imageLock.setImage(lockErrorImage);

        title.setText(erro);
        title.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
    }

    private void newPassSucess() {
        File changeOK = new File("InterfaceGraphic/src/TelaRecuperarSenha/src/image/changeOk.png");
        Image changeOKImage = new Image(changeOK.toURI().toString());
        imageLock.setImage(changeOKImage);

        title.setText("Senha enviado com Sucesso!");
        title.setStyle("-fx-text-fill: green; -fx-font-size: 16px;");

        stage.close();
    }

    private void emailEmpty(){
        File changeOK = new File("InterfaceGraphic/src/TelaRecuperarSenha/src/image/emailEmpty.png");
        Image changeOKImage = new Image(changeOK.toURI().toString());
        imageEmail.setImage(changeOKImage);

        title.setText("Informe um e-mail!");
        title.setStyle("-fx-text-fill: red; -fx-font-size: 22px;");
    }
}
