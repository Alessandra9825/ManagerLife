package TelaRecadastrarSenha.src;

import Acesso.NovaSenha;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import Utilitarios.Utilitarios;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import singleUsuario.usuarioSingleton;
import vos.Usuario;

public class RecadastrarSenhaController implements Initializable {
    private boolean maskOldPass = true;
    private boolean maskNewPass = true;
    private boolean maskConfPass = true;

    private String oldPass = "";
    private String newPass = "";
    private String confPass = "";

    @FXML private TextField oldPass_txt;
    @FXML private TextField newPass_txt;
    @FXML private TextField confPass_txt;

    @FXML private Button oldPass_btn;
    @FXML private Button newPass_btn;
    @FXML private Button confPass_btn;

    @FXML private TextField title;
    @FXML private ImageView result_image;
    @FXML private Button recadastrar_btn;

    public RecadastrarSenhaController() {
    }

    public void initialize(URL location, ResourceBundle resources) {
        File eye_Off = new File("InterfaceGraphic/src/TelaRecadastrarSenha/src/image/eye_Off.png");
        Image eye_Off_Image = new Image(eye_Off.toURI().toString());

        ImageView viewOldPass = new ImageView(eye_Off_Image);
        ImageView viewNewPass = new ImageView(eye_Off_Image);
        ImageView viewConfPass = new ImageView(eye_Off_Image);

        viewOldPass.setFitHeight(20.0D);
        viewOldPass.setFitWidth(20.0D);

        viewNewPass.setFitHeight(20.0D);
        viewNewPass.setFitWidth(20.0D);

        viewConfPass.setFitHeight(20.0D);
        viewConfPass.setFitWidth(20.0D);

        oldPass_btn.setGraphic(viewOldPass);
        newPass_btn.setGraphic(viewNewPass);
        confPass_btn.setGraphic(viewConfPass);

        title.setStyle("-fx-text-inner-color: white; -fx-background-color: #0598ff");

        oldPass_txt.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() < oldValue.length()) {
                oldPass_txt.setText("");
                oldPass = "";
            } else if (maskOldPass && newValue.charAt(newValue.length() - 1) != 9679) {
                oldPass = oldPass + newValue.substring(oldValue.length());
                if (maskOldPass) {
                    oldPass_txt.setText(maskText(oldPass));
                }
            } else if (!maskOldPass) {
                this.oldPass = newValue;
            }

        });

        newPass_txt.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() < oldValue.length()) {
                newPass_txt.setText("");
                newPass = "";
            } else if (maskNewPass && newValue.charAt(newValue.length() - 1) != 9679) {
                newPass = newPass + newValue.substring(oldValue.length());
                if (maskNewPass) {
                    newPass_txt.setText(maskText(newPass));
                }
            } else if (!maskNewPass) {
                newPass = newValue;
            }

        });

        confPass_txt.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() < oldValue.length()) {
                confPass_txt.setText("");
                confPass = "";
            } else if (maskConfPass && newValue.charAt(newValue.length() - 1) != 9679) {
                confPass = confPass + newValue.substring(oldValue.length());
                if (maskConfPass) {
                    confPass_txt.setText(maskText(confPass));
                }
            } else if (!maskConfPass) {
                confPass = newValue;
            }

        });
    }

    @FXML
    public void exit(ActionEvent event) {
        Stage stage = (Stage) recadastrar_btn.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void recadastrar(ActionEvent event) {
        recadastrar_btn.setDisable(true);
        NovaSenha solicitacao = new NovaSenha();
        Usuario user = usuarioSingleton.usuario;

        if (!Utilitarios.getInstancia().isNullorEmpty(newPass) && newPass.equals(confPass) && user.getSenha().equals(oldPass)) {
            String erros = solicitacao.newPassword(user.getEmail(), newPass);
            if (Utilitarios.getInstancia().isNullorEmpty(erros)) {
                passOK();
            } else {
                passError();
            }
        } else {
            passError();
        }

    }
    private void passError() {
        File lockError = new File("InterfaceGraphic/src/TelaRecadastrarSenha/src/image/lockError.png");
        Image lockError_Image = new Image(lockError.toURI().toString());
        result_image.setImage(lockError_Image);

        title.setText("Dados inválidos!");
        title.setStyle("-fx-text-fill: red; -fx-font-size: 22px; -fx-background-color: #0598ff");

        recadastrar_btn.setDisable(false);
    }

    private void passOK() {
        File lockError = new File("InterfaceGraphic/src/TelaRecadastrarSenha/src/image/open.png");
        Image lockError_Image = new Image(lockError.toURI().toString());
        result_image.setImage(lockError_Image);

        title.setText("Senha alterada com Sucesso!");
        title.setStyle("-fx-text-fill: green; -fx-font-size: 20px; -fx-background-color: #0598ff");

        recadastrar_btn.setDisable(true);
    }

    @FXML
    public void maskField(ActionEvent event) {
        if (((Button)event.getSource()).getId() == oldPass_btn.getId()) {
            changeImage(oldPass_btn, maskOldPass);
            maskOldPass = !maskOldPass;

            if (!maskOldPass) {
                oldPass_txt.setText(oldPass);
            } else {
                oldPass_txt.setText(maskText(oldPass));
            }
        } else if (((Button)event.getSource()).getId() == newPass_btn.getId()) {
            changeImage(newPass_btn, maskNewPass);
            maskNewPass = !maskNewPass;

            if (!maskNewPass) {
                newPass_txt.setText(newPass);
            } else {
                newPass_txt.setText(maskText(newPass));
            }
        } else if (((Button)event.getSource()).getId() == confPass_btn.getId()) {
            changeImage(confPass_btn, maskConfPass);
            maskConfPass = !maskConfPass;
            if (!maskConfPass) {
                confPass_txt.setText(confPass);
            } else {
                confPass_txt.setText(maskText(confPass));
            }
        }

    }

    private void changeImage(Button btn, boolean check) {
        File eye;
        Image eye_On;
        ImageView view;

        if (check) {
            eye = new File("InterfaceGraphic/src/TelaRecadastrarSenha/src/image/eye_On.png");
            eye_On = new Image(eye.toURI().toString());
            view = new ImageView(eye_On);
            view.setFitHeight(20.0D);
            view.setFitWidth(20.0D);
            btn.setGraphic(view);
        } else {
            eye = new File("InterfaceGraphic/src/TelaRecadastrarSenha/src/image/eye_Off.png");
            eye_On = new Image(eye.toURI().toString());
            view = new ImageView(eye_On);
            view.setFitHeight(20.0D);
            view.setFitWidth(20.0D);
            btn.setGraphic(view);
        }

    }

    private String maskText(String text) {
        int n = text.length();
        StringBuilder passwordBuilder = new StringBuilder(n);

        for(int i = 0; i < n; ++i) {
            passwordBuilder.append('●');
        }

        return passwordBuilder.toString();
    }
}
