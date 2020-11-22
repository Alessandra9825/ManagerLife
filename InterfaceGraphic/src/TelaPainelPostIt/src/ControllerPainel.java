package TelaPainelPostIt.src;

import AcessoPostIt.AcessarPostIt;
import TelaGerenciaPostItSalvar.src.ControllerGPost;
import TelaGerenciaPostItSalvar.src.GerenciaPost;
import TelaMiniPostIt.ControllerMiniPostIt;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import vos.PostIt;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerPainel implements Initializable {
    public ControllerPainel() throws IOException {
    }

    @FXML private Button btn_adicionar;
    @FXML public AnchorPane an_do1,an_do2,an_do3, an_dev1 ,an_dev2,an_dev3 ;
    @FXML public AnchorPane an_late1,an_late2, an_late3 ,an_done1,an_done2, an_done3;
    @FXML public AnchorPane an_central;
    ControllerMiniPostIt controllerMIni = new ControllerMiniPostIt();
    static public PostIt postIt;
    static public boolean isOpen = false;
    static public ArrayList postIts;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
           postIts = AcessarPostIt.listarPostit();
            if(postIts.size()> 0){
                {   //mostra todos os postIt em suas posições
                    isOpen = true;
                    montaPainel(postIts);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void handleButtonAction(javafx.event.ActionEvent event) throws Exception
    {
        if (event.getSource() == btn_adicionar)
        {
            GerenciaPost post = new GerenciaPost();
            GerenciaPost.isOpen = true;
            post.start(new Stage());
            AnchorPane miniPost = FXMLLoader.load(getClass().getResource("../../TelaMiniPostIt/MiniPostIt.fxml"));

            Integer in = ControllerGPost.PosicaoPost();
            switch (in){
                case 1:
                    an_do2.getChildren().add(miniPost);
                    break;
                case 2:
                    an_do3.getChildren().add(miniPost);
                    break;
                default:
                    an_do1.getChildren().add(miniPost);
            }
        }
    }

    public void montaPainel(ArrayList<PostIt> post) throws IOException {
        Integer contadorDo = 0;
        Integer contadorDev = 0;
        Integer contadorLate =0;
        Integer contadorDone = 0;

        for (int i = 0; i<post.size();i++)
        {
            // Inicializa dados do PostIt
            compartilhaPostItPainel(post.get(i));
            AnchorPane miniPost = FXMLLoader.load(getClass().getResource("../../TelaMiniPostIt/MiniPostIt.fxml"));

            //To do
            if ( (post.get(i).getSituacao()) == 0)
            {
                contadorDo++;
                switch (contadorDo){
                    case 1:
                        an_do1.getChildren().add(miniPost);
                        break;
                    case 2:
                        an_do2.getChildren().add(miniPost);
                        break;
                    default:
                        an_do3.getChildren().add(miniPost);
                }
            }
            //To dev
            else if ( (post.get(i).getSituacao()) == 1)
            {
                switch (contadorDev){
                    case 1:
                        an_dev1.getChildren().add(miniPost);
                        break;
                    case 2:
                        an_dev2.getChildren().add(miniPost);
                        break;
                    default:
                        an_dev3.getChildren().add(miniPost);
                }
            }
            //To Late
            else if ( (post.get(i).getSituacao()) == 2)
            {
                switch (contadorLate){
                    case 1:
                        an_late1.getChildren().add(miniPost);
                        break;
                    case 2:
                        an_late2.getChildren().add(miniPost);
                        break;
                    default:
                        an_late3.getChildren().add(miniPost);
                }
            }
            //To Done
            else if ( (post.get(i).getSituacao()) == 3)
            {
                switch (contadorDone){
                    case 1:
                        an_done1.getChildren().add(miniPost);
                        break;
                    case 2:
                        an_done2.getChildren().add(miniPost);
                        break;
                    default:
                        an_late3.getChildren().add(miniPost);
                }
            }
        }
    }

     public void compartilhaPostItPainel(PostIt post ){
         postIt = post;
     }

    static public PostIt compartilhaPostItPainel(){
        return postIt;
    }

    static public ArrayList compartilhaListPost(){return postIts;}

}
