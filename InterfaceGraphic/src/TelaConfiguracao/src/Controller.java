package TelaConfiguracao.src;

import TelaRecadastrarSenha.src.RecadastrarSenha;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import singleUsuario.usuarioSingleton;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML private TextField nometxt;
    @FXML private TextField emailtxt;
    @FXML private TextField celtxt;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nometxt.setText(usuarioSingleton.usuario.getNome());
        emailtxt.setText(usuarioSingleton.usuario.getEmail());
        celtxt.setText(usuarioSingleton.usuario.getCel());
    }

    public void newPassword(ActionEvent event) throws IOException {
        RecadastrarSenha tela = new RecadastrarSenha();
        tela.start(new Stage());
    }
}
