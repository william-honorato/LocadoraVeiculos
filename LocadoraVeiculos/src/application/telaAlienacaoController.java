package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import DAO.AlienacaoDAO;
import DAO.ClienteDAO;
import DAO.CoresDAO;
import DAO.EstadoDAO;
import DAO.MarcasDAO;
import VO.Alienacao;
import VO.Cliente;
import VO.Cores;
import VO.Estado;
import VO.EstadoCivil;
import VO.Marcas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class telaAlienacaoController implements Initializable {

	//private static Cliente cliente;
	static int index = 0;	

	//Listas para não ficar acessando o banco direto
	ArrayList<Alienacao> listaAlienacao = new ArrayList<>();
	ArrayList<Estado> listaEstados = new ArrayList<>();
	ArrayList<Cores> listaCores = new ArrayList<>();
	ArrayList<Marcas> listaMarcas = new ArrayList<>();
	

    @FXML
    private TextField txtChassi;

    @FXML
    private TextField txtModelo;

    @FXML
    private ComboBox<Marcas> comboMarca;

    @FXML
    private ComboBox<Estado> comboEstado;

    @FXML
    private TextField txtAno;

    @FXML
    private TextField txtCodCliente;

    @FXML
    private Button btnSalvar;

    @FXML
    private TextField txtCodAlienacao;

    @FXML
    private Button btnProximo;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnUltimo;

    @FXML
    private ComboBox<Cores> comboCor;

    @FXML
    private Button btnAnterior;

    @FXML
    private TextField txtNomeCliente;

    @FXML
    private Button btnExcluir;

    @FXML
    private Button btnNovo;

    @FXML
    private Button btnPrimeiro;
	
	/*public void setCliente(Cliente c) {
		cliente = c;
		txtNomeCliente.setText(c.getNome());
	}*/
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			listaAlienacao = new AlienacaoDAO().RetornaAlienados();
			listaEstados = new EstadoDAO().RetornaEstados();
			listaCores = new CoresDAO().RetornaCores();
			listaMarcas = new MarcasDAO().RetornaMarcas();
			
			CarregarCombos();
			
			if(listaAlienacao.size() > 0) {
				PopularTela();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

    @FXML    
    void abrirPesquisaCliente(ActionEvent event) throws SQLException, IOException {	
    	Stage stage = new Stage();
    	Parent root = FXMLLoader.load(getClass().getResource("telaPesquisaCliente.fxml"));    	
    	Scene scene = new Scene(root);
    	stage.setScene(scene);
    	stage.setTitle("Pesquisar Cliente");
    	stage.setResizable(false);    	
    	stage.show();
    }
    
    private void LimparTela() {
    	
    	txtCodAlienacao.setText("");
    	txtAno.clear();
    	txtChassi.clear();
    	txtCodAlienacao.clear();
    	txtCodCliente.clear();
    	txtModelo.clear();
    	txtNomeCliente.clear();
    	comboCor.getSelectionModel().select(0);
    	comboEstado.getSelectionModel().select(0);
    	comboMarca.getSelectionModel().select(0);
    	
    }

    private void PopularTela() throws SQLException {
    	
    	Alienacao a = listaAlienacao.get(index);
    	LimparTela();
    	txtCodAlienacao.setText( String.valueOf(a.getCod_alienacao()) );
    	txtCodCliente.setText( String.valueOf(a.getCod_cliente()) );
    	Cliente c = new ClienteDAO().RetornaCliente(a.getCod_cliente());
    	txtNomeCliente.setText(c.getNome());
    	txtAno.setText( String.valueOf(a.getAno()) );
    	txtChassi.setText( a.getNumchassi() );
    	txtModelo.setText( a.getModelo() );
    	    	
    	for (Estado estado : listaEstados) {
			if(estado.getCod_uf().equals(c.getCod_uf()) ) {
				comboEstado.setValue(estado);
				break;
			}
		}
    	
    	for (Cores cor : listaCores) {
			if(cor.getCod_cor() == a.getCod_cor() ) {
				comboCor.setValue(cor);
				break;
			}
		}
    	
    	for (Marcas marca : listaMarcas) {
			if(marca.getCod_marca() == a.getCod_marca() ) {
				comboMarca.setValue(marca);
				break;
			}
		}
    }
    
    @FXML    
    void ProximoRegistro(ActionEvent event) throws SQLException {
    	    	
    	if(index < listaAlienacao.size() - 1)
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
    void NovoRegistro(ActionEvent event) throws SQLException {
    	LimparTela();
    	txtCodCliente.requestFocus();    	
    }
    
    @FXML
    void SalvarRegistro(ActionEvent event) throws SQLException, ParseException {
    	
    	Alienacao a = new Alienacao();
    	
    	if(!txtCodAlienacao.getText().equals(""))//Se tiver código, pega o codigo para update
    	{
    		String stringCod = txtCodAlienacao.getText();
    		int cod_a = Integer.parseInt(stringCod);
    		a.setCod_alienacao(cod_a);
    	}
    	
    	try {
    		String codCliente = txtCodCliente.getText().trim();
    		a.setAno(Integer.parseInt(codCliente));
    	}
    	catch (Exception e) {
        	Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Cliente");
        	alert.setHeaderText("Salvar registro");
        	alert.setContentText("Digite o código do cliente.");
        	alert.show();
        	return;
		} 	
    	
    	try {
    		String ano = txtAno.getText().trim();
    		a.setAno(Integer.parseInt(ano));
    	}
    	catch (Exception e) {
        	Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Alienação");
        	alert.setHeaderText("Formato inválido");
        	alert.setContentText("Digite apenas números no ano");
        	alert.show();
    		return;
		}
    	
    	a.setModelo(txtModelo.getText());
    	a.setNumchassi(txtChassi.getText());
    	a.setCod_cor(comboCor.getValue().getCod_cor());
    	a.setCod_uf_placa(comboEstado.getValue().getCod_uf());
    	a.setCod_marca(comboMarca.getValue().getCod_marca());
    	
    	new AlienacaoDAO().Salvar(a);
    	
    	txtCodAlienacao.setText(Integer.toString(a.getCod_alienacao()));
    	
    	//Atualiza lista de clientes
    	listaAlienacao = new AlienacaoDAO().RetornaAlienados();
    	
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Alienação");
    	alert.setHeaderText("Salvar registro");
    	alert.setContentText("Registro salvo com sucesso.");
    	alert.show();

    }
   

    @FXML
    void PrimeiroRegistro(ActionEvent event) throws SQLException {
    	
    	index = 0;
    	PopularTela();
    }
        
    @FXML
    void UltimoRegistro(ActionEvent event) throws SQLException {
    	    	
    	index = listaAlienacao.size() - 1;
    	PopularTela();
    }
        
	private void CarregarCombos() throws SQLException {
		
		ObservableList<Estado> obsEstados = FXCollections.observableArrayList(listaEstados);		
		comboEstado.setItems(obsEstados);
		comboEstado.getSelectionModel().select(0);//Seleciona o primeiro item
		
		ObservableList<Cores> obsCores = FXCollections.observableArrayList(listaCores);		
		comboCor.setItems(obsCores);
		comboCor.getSelectionModel().select(0);//Seleciona o primeiro item
		
		ObservableList<Marcas> obsMarcas = FXCollections.observableArrayList(listaMarcas);		
		comboMarca.setItems(obsMarcas);
		comboMarca.getSelectionModel().select(0);//Seleciona o primeiro item
  
	}

}
