package TelaGerenciaPostIt.src;

import AcessoPostIt.AcessarPostIt;
import Auditoria.GerenciadorAuditoria;
import TelaCadastraSaldoContaCorrente.src.Controller;
import TelaPainelPostIt.src.ControllerPainel;
import Validacao.ValidaPostIt;
import Validacao.ValidaUsuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import vos.PostIt;
import vos.Usuario;

import java.sql.Date;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;

public class ControllerGPost {
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
            post.setSituacao(cb_situacao.getId());

            erros = validacao.ValidaDados(post);

            if(erros.size() > 0)
            {
                alert.popupError(erros);
            }
            else
            {
                AcessarPostIt postSalvar = new AcessarPostIt();
                Boolean cadastrado = postSalvar.salvarPostIt(post);
                //tenta cadastrar no banco o novo post it
                //cria o postIt na tela
                AnchorPane pane = FXMLLoader.load(getClass().getResource("MiniPostIt.fxml"));
                tela.an_do.getChildren().add(pane);

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

}
