package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class telaMainController implements Initializable {

	@FXML
	private BorderPane borderpane;	

    @FXML
    private Button btnAlienacao;

    @FXML
    private Button btnMarcas;

    @FXML
    private Button btnRelatorio;

    @FXML
    private Button btnClientes;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadUi("telaAlienacao");
	}
	
    @FXML
    private void close(MouseEvent event) {	
    	
    	Stage stage = (Stage) borderpane.getScene().getWindow();
    	stage.close();   	
    }    
    
    private void LimparSelecaoButtons() {
    	btnRelatorio.setStyle("-fx-text-fill: #f0ffff; -fx-background-color: #303030;");
    	btnClientes.setStyle("-fx-text-fill: #f0ffff; -fx-background-color: #303030;");
    	btnMarcas.setStyle("-fx-text-fill: #f0ffff; -fx-background-color: #303030;");
    	btnAlienacao.setStyle("-fx-text-fill: #f0ffff; -fx-background-color: #303030;");
    }
    
    @FXML
    private void relatorio(MouseEvent event) {
    	loadUi("telaRelatorio");
    	LimparSelecaoButtons();
    	btnRelatorio.setStyle("-fx-text-fill: #f0ffff; -fx-background-color: #505050;");
    }
            
    @FXML
    private void clientes(MouseEvent event) {
    	loadUi("telaClientes");
    	LimparSelecaoButtons();
    	btnClientes.setStyle("-fx-text-fill: #f0ffff; -fx-background-color: #505050;");
    }
    
    @FXML
    private void alienacao(MouseEvent event) {
    	loadUi("telaAlienacao");
    	LimparSelecaoButtons();
    	btnAlienacao.setStyle("-fx-text-fill: #f0ffff; -fx-background-color: #505050;");
    }
    
    @FXML
    private void marcas(MouseEvent event) {
    	loadUi("telaMarcas");
    	LimparSelecaoButtons();
    	btnMarcas.setStyle("-fx-text-fill: #f0ffff; -fx-background-color: #505050;");
    }
    
    private void loadUi(String ui) {
    	Parent root = null;
    	
    	try {
    		root = FXMLLoader.load(getClass().getResource(ui + ".fxml"));
    		    		
    	}catch(IOException ex){
    		System.out.println(ex.getMessage());
    		Logger.getLogger(telaClienteController.class.getName()).log(Level.SEVERE, null, ex);
    	}
    	
    	borderpane.setCenter(root);    	
    }   
}