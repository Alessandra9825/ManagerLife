package TelaCadastraUsuario.src;

import AcessoUsuario.AcessarUsuario;
import Auditoria.GerenciadorAuditoria;
import Validacao.ValidaUsuario;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import vos.Usuario;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CadastrarUsuario extends Application {

    //Painel principal
    @FXML private AnchorPane princPanel;

    //Dados Gerais do usuario
    @FXML private TextField nomeTxt;
    @FXML private TextField emailTxt;
    @FXML private TextField celTxt;
    @FXML private DatePicker dataNasc;

    @FXML private Button btn_cancelar,btn_salvar;

    public static void main(String[] args) {
        launch(args);
    }

    private CadastrarUsuarioController controller = new CadastrarUsuarioController();
    private AcessarUsuario acessar = new AcessarUsuario();

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
            user.setSenha(celTxt.getText());
            user.setCel(celTxt.getText());

            if(dataNasc.getValue() != null){
                user.setDataNascimento(Date.from(Instant.from(dataNasc.getValue().atStartOfDay(ZoneId.systemDefault()))));
            }

            erros = validacao.ValidaDados(user);

            if(erros.size() > 0){
                controller.popupError(erros);
            }
            else{
                Stage stage = (Stage)  celTxt.getScene().getWindow(); //pega a janela atual
                //tenta cadastrar no banco o novo usuario
                 cadastrado = acessar.salvarUsuario(user);

                if (!cadastrado) {
                    erros.add("Sistema fora de serviço!");
                    GerenciadorAuditoria.getInstancia().adicionaMsgAuditoria("DataBase - Sistema fora de serviço!");
                    controller.popupError(erros);
                } else {
                    controller.popupSuccess(stage);
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

}
