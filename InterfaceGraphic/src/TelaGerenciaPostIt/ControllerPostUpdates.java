package TelaGerenciaPostIt;

import AcessoPostIt.AcessarPostIt;
import TelaPainelPostIt.src.ControllerPainel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import vos.PostIt;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerPostUpdates implements Initializable {
    @FXML
    private Button btn_cancelar, btn_deletar,btn_salvar;
    //dados gerais do PostIt
    @FXML private TextField txt_nome;
    @FXML private TextArea txtA_descricao;
    @FXML private TextField txt_tempo;
    @FXML private ComboBox cb_situacao;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList <PostIt> listPost = ControllerPainel.compartilhaListPost();
        for (int i = 0; i<listPost.size();i++)
        {
            if(((listPost.get(i).getNome()).equals(nomePost)))
            {
                String tempo =Integer.toString( (listPost.get(i).getTempo()));
                txt_nome.setText(listPost.get(i).getNome());
                txtA_descricao.setText(listPost.get(i).getDescricao());
                txt_tempo.setText(tempo);
                cb_situacao.setId(Integer.toString(listPost.get(i).getSituacao()));
            }
        }

        ObservableList list = FXCollections.observableArrayList("Development","Late","Done");
        cb_situacao.setItems(list);
    }

    static public String nomePost;
    AcessarPostIt acesso = new AcessarPostIt();

    public void AlteraPostIT(String nomePost) throws Exception {
        ArrayList <PostIt> listPost = ControllerPainel.compartilhaListPost();
        for (int i = 0; i<listPost.size();i++)
        {
            if(((listPost.get(i).getNome()).equals(nomePost)))
            {
                acesso.alterarPostIt( listPost.get(i));
                break;
            }
        }
    }

    @FXML
    private void Cancelar(){
        Stage stage = (Stage) btn_cancelar.getScene().getWindow(); //pega a janela atual
        stage.close();
    }
}
