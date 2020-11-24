package TelaGerenciaPostIt.src;
import AcessoPostIt.AcessarPostIt;
import Auditoria.GerenciadorAuditoria;
import TelaPainelPostIt.src.ControllerPainel;
import Validacao.ValidaPostIt;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import vos.PostIt;

import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.ResourceBundle;

public class ControllerGPost implements Initializable {
    private GerenciaPost alert = new GerenciaPost();
    //botoes painel post-It
    @FXML
    private Button btn_cancelar, btn_deletar,btn_salvar;

    //dados gerais do PostIt
    @FXML private TextField txt_nome;
    @FXML private TextArea txtA_descricao;
    @FXML private TextField txt_tempo;
    @FXML private ComboBox cb_situacao;

    ControllerPainel tela  = new ControllerPainel();

    @FXML
    private void Cancelar(){
        Stage stage = (Stage) btn_cancelar.getScene().getWindow(); //pega a janela atual
        stage.close();
    }

    @FXML
    private void Salvar()
    {
        PostIt post = new PostIt();
        ArrayList<String> erros;
        ValidaPostIt validacao = new ValidaPostIt();

        try
        {
            Stage stage = (Stage)  btn_salvar.getScene().getWindow(); //pega a janela atual
            post.setNome(txt_nome.getText());
            post.setTempo(txt_tempo.getText());
            post.setDescricao(txtA_descricao.getText());
            post.setSituacao(cb_situacao.getSelectionModel().getSelectedIndex());

            erros = validacao.ValidaDados(post);

            if(erros.size() > 0)
            {
                alert.popupError(erros);
            }
            else
            {
                AcessarPostIt postSalvar = new AcessarPostIt();
                Boolean cadastrado = postSalvar.salvarPostIt(post);
                //AnchorPane pane = FXMLLoader.load(getClass().getResource("../../TelaMiniPostIt/MiniPostIt.fxml"));
                //tela.an_do.getChildren().add(pane);

                if(!cadastrado)
                {
                    erros.add("Sistema fora de serviço!");
                    GerenciadorAuditoria.getInstancia().adicionaMsgAuditoria("DataBase - Sistema fora de serviço!");
                    alert.popupError(erros);
                }
                else
                {
                    alert.popupSuccess(stage);
                }
           }
            stage.close();

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList list = FXCollections.observableArrayList("Do","Development","Late","Done");
        cb_situacao.setItems(list);
    }
}
