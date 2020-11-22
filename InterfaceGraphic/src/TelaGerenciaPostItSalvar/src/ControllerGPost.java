package TelaGerenciaPostItSalvar.src;
import AcessoPostIt.AcessarPostIt;
import Auditoria.GerenciadorAuditoria;
import Validacao.ValidaPostIt;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import vos.PostIt;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerGPost implements Initializable {

    AcessarPostIt acessarPost = new AcessarPostIt();
    private GerenciaPost alert = new GerenciaPost();
    PostIt post = new PostIt();
    static PostIt objPost;
    static Integer quantidadePostIt;
    ArrayList<String> erros;

    @FXML private Button btn_cancelar, btn_deletar,btn_salvar;
    //dados gerais do PostIt
    @FXML private TextField txt_nome;
    @FXML private TextArea txtA_descricao;
    @FXML private TextField txt_tempo;
    @FXML private ComboBox cb_situacao;



    @FXML
    private void Cancelar(){
        Stage stage = (Stage) btn_cancelar.getScene().getWindow(); //pega a janela atual
        stage.close();
    }
    @FXML
    private void Salvar() throws Exception {
        Stage stage = (Stage)  btn_salvar.getScene().getWindow(); //pega a janela atual

        quantidadePostIt = acessarPost.contaPostIt(post);
        if(quantidadePostIt >= 3)
        {
            alert.popupLotado(stage);
            stage.close();

        }
        else {
            try {
                ValidaPostIt validacao = new ValidaPostIt();

                try {
                    post.setNome(txt_nome.getText());
                    post.setTempo(Integer.parseInt(txt_tempo.getText()));
                    post.setDescricao(txtA_descricao.getText());
                    post.setSituacao(cb_situacao.getSelectionModel().getSelectedIndex());
                } catch (Exception err) {
                    erros.add("Erros ao preenche os dados do PostIt, revise os campos informados!");
                    alert.popupError(erros);
                }

                erros = validacao.ValidaDados(post);

                if (erros.size() > 0) {
                    alert.popupError(erros);
                } else {
                    Boolean cadastrado = acessarPost.salvarPostIt(post);

                    if (!cadastrado) {
                        erros.add("Sistema fora de serviço!");
                        GerenciadorAuditoria.getInstancia().adicionaMsgAuditoria("DataBase - Sistema fora de serviço!");
                        alert.popupError(erros);
                    } else {
                        alert.popupSuccess(stage);
                        stage.close();
                        //carrega os dados do postIt p compartilhar entre as telas
                        objPost = post;
                    }
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList list = FXCollections.observableArrayList("Do");
        cb_situacao.setItems(list);
    }

    static public PostIt compartilhaDados(){
        return objPost;
    }
    static public int PosicaoPost()
    {
        return quantidadePostIt;
    }

}
