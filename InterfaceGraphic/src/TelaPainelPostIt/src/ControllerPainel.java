package TelaPainelPostIt.src;

import TelaGerenciaPostIt.src.GerenciaPost;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerPainel implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    @FXML
    private Button btn_detalhar,btn_adicionar;
    @FXML
    public AnchorPane an_do;

    String ret="";
    public void handleButtonAction(javafx.event.ActionEvent event) throws Exception {
        if (event.getSource() == btn_detalhar || event.getSource() == btn_adicionar)
        {
            Stage telaPost = new Stage();
            telaPost.initStyle(StageStyle.UNDECORATED);
            Parent root = FXMLLoader.load(getClass().getResource("../../TelaGerenciaPostIt/src/PostIt.fxml"));
            telaPost.setScene(new Scene(root, 495, 350));
            telaPost.showAndWait();
        }
    }



}
