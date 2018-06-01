package application;

import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

import DAO.ClienteDAO;
import DAO.EstadoCivilDAO;
import DAO.EstadoDAO;
import VO.Cliente;
import VO.Estado;
import VO.EstadoCivil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class ClienteController implements Initializable {

	static int index = 0;
	
	//Listas para não ficar acessando o banco direto
	ArrayList<Cliente> listaCliente = new ArrayList<>();
	ArrayList<Estado> listaEstados = new ArrayList<>();
	ArrayList<EstadoCivil> listaEstadoCivil = new ArrayList<>();
	
    @FXML
    private Button btnNovo;
	
    @FXML
    private ComboBox<EstadoCivil> comboEstadoCivil;

    @FXML
    private TextField txtCodCliente;

    @FXML
    private Button btnSalvar;

    @FXML
    private Button btnProximo;

    @FXML
    private TextField txtDtNascimento;

    @FXML
    private Button btnAnterior;

    @FXML
    private ComboBox<Estado> comboEstado;

    @FXML
    private TextField txtRendimento;

    @FXML
    private ComboBox<String> comboSexo;

    @FXML
    private TextField txtNomeCliente;

    @FXML
    private Button btnExcluir;
    
    @FXML
    private Button btnPrimeiro;
    
    @FXML
    private Button btnUltimo;
    
    private void LimparTela() {
    	    	
    	txtCodCliente.clear();
    	txtDtNascimento.clear();
    	txtNomeCliente.clear();
    	txtRendimento.clear();
    	comboEstado.getSelectionModel().select(0);
    	comboEstadoCivil.getSelectionModel().select(0);
    	comboSexo.getSelectionModel().select(0);
    }

    private void PopularTela() {
    	Cliente c = listaCliente.get(index);
    	LimparTela();
    	txtCodCliente.setText( String.valueOf(c.getCod_cliente()) );
    	txtNomeCliente.setText(c.getNome());
    	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    	txtDtNascimento.setText( df.format(c.getDt_nascimento()) );
    	String resultado = String.format("%.2f", c.getRendimento());
    	txtRendimento.setText("R$" + resultado);
    	
    	for (EstadoCivil ec : listaEstadoCivil) {
			if(ec.getCod_estado_civil().equals(c.getCod_estado_civil()) ) {
				comboEstadoCivil.setValue(ec);
				break;
			}
		}
    	
    	for (Estado estado : listaEstados) {
			if(estado.getCod_uf().equals(c.getCod_uf()) ) {
				comboEstado.setValue(estado);
				break;
			}
		}
    	
    	if(c.getTipo_pessoa().equals("M")) {
    		comboSexo.setValue("Masculino");
    	}else {
    		comboSexo.setValue("Feminino");
    	}

    }

    @FXML
    void ProximoRegistro(ActionEvent event) throws SQLException {
    	    	
    	if(index < listaCliente.size() - 1)
    	{
    		++index;
    		PopularTela();
    	}
    }
    
    @FXML
    void RegistroAnterior(ActionEvent event) throws SQLException {
    	
    	if(index > 0)
    	{
    		--index;
    		PopularTela();
    	}
    }
    
    @FXML
    void SalvarRegistro(ActionEvent event) throws SQLException {
    	

    }
    
    @FXML
    void NovoRegistro(ActionEvent event) throws SQLException {
    	

    }
    
    @FXML
    void ExcluirRegistro(ActionEvent event) throws SQLException {
    	
    	try {
    		
    		int cod_cliente = Integer.parseInt(txtCodCliente.getText());
    		new ClienteDAO().Excluir(cod_cliente);
    		LimparTela();
    		listaCliente = new ClienteDAO().RetornaClientes();
    		index = 0;
    		PopularTela();
    		
		} catch (Exception e) {

			e.printStackTrace();
		}
    	
    }
    
    @FXML
    void ProximoPrimeiro(ActionEvent event) throws SQLException {
    	
    	index = 0;
    	PopularTela();
    }
    
    @FXML
    void ProximoUltimo(ActionEvent event) throws SQLException {
    	    	
    	index = listaCliente.size() - 1;
    	PopularTela();
    }
    
	private void PopularCombos() throws SQLException {
		
		ObservableList<Estado> obsEstados = FXCollections.observableArrayList(listaEstados);		
		comboEstado.setItems(obsEstados);
		comboEstado.getSelectionModel().select(0);//Seleciona o primeiro item
						
		ObservableList<EstadoCivil> obsECivil = FXCollections.observableArrayList(listaEstadoCivil);
		comboEstadoCivil.setItems(obsECivil);
		comboEstadoCivil.getSelectionModel().select(0);//Seleciona o primeiro item		

	    comboSexo.getItems().addAll("Masculino","Feminino");
	    comboSexo.getSelectionModel().select(0);	    
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		try 
		{
			//Carrega as listas com dados do banco de dados
			listaCliente = new ClienteDAO().RetornaClientes();
			listaEstados = new EstadoDAO().RetornaEstados();
			listaEstadoCivil = new EstadoCivilDAO().RetornaEstadosCivis();

			//Carrega todo os combos
			PopularCombos();
			
			//Se tiver dados na lista
			if(listaCliente.size() > 0)
				PopularTela();

		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

}
