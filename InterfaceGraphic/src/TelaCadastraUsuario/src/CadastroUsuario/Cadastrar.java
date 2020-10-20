package TelaCadastraUsuario.src.CadastroUsuario;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Cadastrar extends Application {

    //Painel principal
    @FXML private AnchorPane princPanel;

    //Dados Gerais do usuario
    @FXML private TextField nomeTxt;
    @FXML private TextField emailTxt;
    @FXML private TextField celTxt;
    @FXML private TextField dataNascTxt;

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

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Cadastrar.fxml"));
        primaryStage.setTitle("Cadastre-se");
        primaryStage.setScene(new Scene(root, 597, 552));
        primaryStage.show();
    }

    public void Salvar_btn(ActionEvent event) {
    }
}
