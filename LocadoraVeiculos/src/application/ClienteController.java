package application;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import DAO.ClienteDAO;
import VO.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ClienteController {

	static int index = 0;
	
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

}
