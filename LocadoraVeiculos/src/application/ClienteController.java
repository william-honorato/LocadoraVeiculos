package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import DAO.ClienteDAO;
import VO.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class ClienteController implements Initializable {

	static int index = 0;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	private BorderPane borderpane;
	
    @FXML
    private TextField txtCodCliente;

    @FXML
    private Button btnProximo;

    @FXML
    private Button btnAnterior;

    @FXML
    private TextField txtNomeCliente;
    
    @FXML
    private TextField txtDtNascimento;

    @FXML
    void ProximoRegistro(ActionEvent event) throws SQLException {
    	ArrayList<Cliente> listaCliente = new ClienteDAO().RetornaClientes();
    	
    	if(index < listaCliente.size())
    		++index;
    	
    	Cliente c = listaCliente.get(index);
    	
    	txtCodCliente.setText( String.valueOf(c.getCod_cliente()) );
    	txtNomeCliente.setText(c.getNome());
    	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    	txtDtNascimento.setText( df.format(c.getDt_nascimento()) );
    }
    
    @FXML
    void RegistroAnterior(ActionEvent event) throws SQLException {
    	ArrayList<Cliente> listaCliente = new ClienteDAO().RetornaClientes();
    	
    	if(index > 0)
    		--index;
    	
    	Cliente c = listaCliente.get(index);
    	
    	txtCodCliente.setText( String.valueOf(c.getCod_cliente()) );
    	txtNomeCliente.setText(c.getNome());
    	//txtDtNascimento.setText( String.valueOf(c.getDt_nascimento()) );
    }
    
    
    @FXML
    private void close(MouseEvent event) {	
    	
    	Stage stage = (Stage) borderpane.getScene().getWindow();
    	stage.close();
    	
    	
    }
    
     
    @FXML
    private void estados(MouseEvent event) {
    	loadUi("telaEstados");
    }
    
    @FXML
    private void clientes(MouseEvent event) {
    	loadUi("telaClientes");
    }
    @FXML
    private void alienacao(MouseEvent event) {
    	loadUi("telaAlienacao");
    }
    @FXML
    private void marcas(MouseEvent event) {
    	loadUi("telaMarcas");
    }

    
    private void loadUi(String ui) {
    	Parent root = null;
    	
    	try {
    		root = FXMLLoader.load(getClass().getResource(ui + ".fxml"));
    		    		
    	}catch(IOException ex){
    		
    		Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
    	}
    	
    	borderpane.setCenter(root);
    	
    }

	
    
   
}
