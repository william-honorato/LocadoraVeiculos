package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class telaAlienacaoController implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
    @SuppressWarnings("null")
	@FXML
    private void abrirPesquisaCliente(MouseEvent event) throws IOException {
    	
    	Stage stage = new Stage();
    	Parent root = FXMLLoader.load(getClass().getResource("telaPesquisaCliente.fxml"));
    	Scene scene = new Scene(root);
    	stage.setScene(scene);
    	stage.setTitle("Pesquisar Cliente");
    	stage.setResizable(false);
    	stage.show();
    	
    }

}
