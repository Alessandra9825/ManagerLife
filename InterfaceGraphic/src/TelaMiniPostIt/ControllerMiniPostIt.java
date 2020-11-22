package TelaMiniPostIt;
import TelaGerenciaPostIt.ControllerPostUpdates;
import TelaGerenciaPostIt.PostUpdates;
import TelaGerenciaPostItSalvar.src.ControllerGPost;
import TelaGerenciaPostItSalvar.src.GerenciaPost;
import TelaMenu.src.Controller;
import TelaPainelPostIt.src.ControllerPainel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import vos.PostIt;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerMiniPostIt implements Initializable {

    @FXML TextField txt_nomeMini, txt_tempoMini;
    @FXML AnchorPane an_postIt;
    @FXML private javafx.scene.control.Button btn_detalhar;
    private boolean isOpen;
    public void handleButtonAction(javafx.event.ActionEvent event) throws Exception
    {
        if (event.getSource() == btn_detalhar)
        {
            //Carregar Dados do postIt atual
            ControllerPostUpdates.nomePost = txt_nomeMini.getText();
            ControllerPostUpdates control = new ControllerPostUpdates();
            control.AlteraPostIT(txt_nomeMini.getText());
            PostUpdates tela = new PostUpdates();
            tela.start(new Stage());
        }
    }

    public void preencheTextField(PostIt post){
        String tempo =Integer.toString( post.getTempo());
        this.txt_tempoMini.setText(tempo);
        this.txt_nomeMini.setText(post.getNome());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(GerenciaPost.isOpen)
            preencheTextField(ControllerGPost.compartilhaDados());
       else if(ControllerPainel.isOpen)
           preencheTextField(ControllerPainel.compartilhaPostItPainel());
    }



}
