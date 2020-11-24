package TelaCadastraUsuario.src;

import AcessoUsuario.AcessarUsuario;
import Auditoria.GerenciadorAuditoria;
import Validacao.ValidaUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import vos.Usuario;

import java.net.URL;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import singleUsuario.usuarioSingleton;

public class CadastrarUsuarioController implements Initializable {

    //Painel principal
    @FXML
    private AnchorPane princPanel;

    //Dados Gerais do usuario
    @FXML private TextField nomeTxt;
    @FXML private TextField emailTxt;
    @FXML private TextField celTxt;
    @FXML private DatePicker dataNasc;

    @FXML private Button btn_cancelar,btn_salvar;


    private AcessarUsuario acessar = new AcessarUsuario();

    public void popupError(ArrayList<String> erros){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initStyle(StageStyle.UNDECORATED);
        alert.setTitle("Error Dialog");
        alert.setHeaderText("Ops, Ocorreu um erro!");
        String text = "";
        for (int i = 0; i < erros.size(); i++){
            text += erros.get(i) + "\n";
        }
        alert.setContentText(text);
        alert.showAndWait();
    }

    public void popupSuccess(Stage stage){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initStyle(StageStyle.UNDECORATED);
        alert.setTitle("Sucesso");
        alert.setHeaderText("Usuario Cadastrado com sucesso!");
        alert.showAndWait();
    }

    private final LocalDate datePicker(String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }

    public void Salvar_btn(ActionEvent event) {
        Usuario user = new Usuario();
        ArrayList<String> erros;
        ValidaUsuario validacao = new ValidaUsuario();
        boolean cadastrado = false;

        try {
            user.setNome(nomeTxt.getText());
            user.setEmail(emailTxt.getText());
            user.setCel(celTxt.getText());

            if(dataNasc.getValue() != null){
                user.setDataNascimento(Date.from(Instant.from(dataNasc.getValue().atStartOfDay(ZoneId.systemDefault()))));
            }

            erros = validacao.ValidaDados(user);

            if(erros.size() > 0){
                popupError(erros);
            }
            else{
                Stage stage = (Stage)  celTxt.getScene().getWindow(); //pega a janela atual
                //tenta cadastrar no banco o novo usuario
                cadastrado = acessar.salvarUsuario(user);

                if (!cadastrado) {
                    erros.add("Sistema fora de serviço!");
                    GerenciadorAuditoria.getInstancia().adicionaMsgAuditoria("DataBase - Sistema fora de serviço!");
                    popupError(erros);
                } else {
                    popupSuccess(stage);
                    stage.close();
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void Cancelar_btn(){
        Stage stage = (Stage)  btn_cancelar.getScene().getWindow(); //pega a janela atual
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nomeTxt.setText(usuarioSingleton.usuario.getNome());
        emailTxt.setText(usuarioSingleton.usuario.getEmail());
        celTxt.setText(usuarioSingleton.usuario.getCel());
    }
}
