package TelaCadastraUsuario.src;

import Auditoria.GerenciadorAuditoria;
import Validacao.ValidaUsuario;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
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

    //Credito
    @FXML private TextField dataFaturaTxt;
    @FXML private TextField limiteTxt;
    @FXML private TextField indentificadorTxt;

    //Corrente
    @FXML private TextField dataPagTxt;
    @FXML private TextField valorTxt;
    @FXML private TextField descricaoTxt;
    @FXML private ComboBox situacao;

    public static void main(String[] args) {
        launch(args);
    }

    private CadastrarUsuarioController controller = new CadastrarUsuarioController();

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Cadastrar.fxml"));
        primaryStage.setTitle("Cadastre-se");
        primaryStage.setScene(new Scene(root, 597, 552));
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

            if(dataNasc.getValue() != null){
                user.setDataNascimento(Date.from(Instant.from(dataNasc.getValue().atStartOfDay(ZoneId.systemDefault()))));
            }

            erros = validacao.ValidaDados(user);

            if(erros.size() > 0){
                controller.popupError(erros);
            }
            else{
                //tenta cadastrar no banco o novo usuario

                if(!cadastrado){
                    erros.add("Sistema fora de serviço!");
                    GerenciadorAuditoria.getInstancia().adicionaMsgAuditoria("DataBase - Sistema fora de serviço!");
                    controller.popupError(erros);
                }
                else{
                    //cadastro realizado com sucesso
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
