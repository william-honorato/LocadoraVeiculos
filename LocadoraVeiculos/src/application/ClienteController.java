package application;

import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
    	txtNomeCliente.requestFocus();
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
    void SalvarRegistro(ActionEvent event) throws SQLException, ParseException {
    	
    	Cliente cliente = new Cliente();
    	
    	if(!txtCodCliente.getText().equals(""))//Se tiver código, pega o codigo para update
    	{
    		String stringCod = txtCodCliente.getText();
    		int cod_cliente = Integer.parseInt(stringCod);
    		cliente.setCod_cliente(cod_cliente);
    	}
    	
    	String nomeCliente = txtNomeCliente.getText().trim();
    	if(nomeCliente.equals(""))
    	{
        	Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Cliente");
        	alert.setHeaderText("Salvar registro");
        	alert.setContentText("Digite o nome do cliente.");
        	alert.show();
        	return;
    	}
    	cliente.setNome(nomeCliente); 
    	
    	//Regex para validar se é uma data valida dd/mm/aaaa
    	if(txtDtNascimento.getText().matches("^([0-2][0-9]|[3][01])\\/([0][1-9]|1[0-2])\\/(19|20)\\d{2}$"))
    	{
    		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	    	Date dt_nascimento = (Date)formatter.parse(txtDtNascimento.getText());	    	
	    	cliente.setDt_nascimento(dt_nascimento);
    	}
    	else {
        	Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Cliente");
        	alert.setHeaderText("Formato de data inválido");
        	alert.setContentText("Digite a data no formato dd/mm/aaaa");
        	alert.show();
    		return;
    	}
    	    	
    	try {
    		String valorRendimento = txtRendimento.getText().replace("R$","").replace(".","").replace(",",".").trim();
    		
    		if(valorRendimento.equals("")) {
            	Alert alert = new Alert(AlertType.ERROR);
            	alert.setTitle("Cliente");
            	alert.setHeaderText("Formato de moeda inválido");
            	alert.setContentText("Digite o valor do rendimento no formato 999,99");
            	alert.show();
        		return;
    		}
    		
    		cliente.setRendimento(Double.parseDouble(valorRendimento));
    	}
    	catch (Exception e) {
        	Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Cliente");
        	alert.setHeaderText("Formato de moeda inválido");
        	alert.setContentText("Digite o valor do rendimento no formato 999,99");
        	alert.show();
    		return;
		}
    	
   	
    	
    	cliente.setCod_estado_civil(comboEstadoCivil.getValue().getCod_estado_civil());
    	cliente.setCod_uf(comboEstado.getValue().getCod_uf());
    	
    	if(comboSexo.getValue().equals("Masculino"))
    		cliente.setTipo_pessoa("M");
    	else
    		cliente.setTipo_pessoa("F");
    	
    	new ClienteDAO().Salvar(cliente);
    	
    	txtCodCliente.setText(Integer.toString(cliente.getCod_cliente()));
    	
    	//Atualiza lista de clientes
    	listaCliente = new ClienteDAO().RetornaClientes();
    	
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Cliente");
    	alert.setHeaderText("Salvar registro");
    	alert.setContentText("Registro salvo com sucesso.");
    	alert.show();

    }
    
    @FXML
    void NovoRegistro(ActionEvent event) throws SQLException {
    	LimparTela();
    	txtNomeCliente.requestFocus();    	
    }
    
    
    @FXML    
    void ExcluirRegistro(ActionEvent event) throws SQLException {
    	
    	try {
    		
        	Alert alert = new Alert(AlertType.WARNING);
        	alert.setTitle("Cliente");
        	alert.setHeaderText("Exclusão de registro");
        	alert.setContentText("Deseja realmente excluir esse registro?");
        	ButtonType yesButton = new ButtonType("Sim");
        	yesButton = ButtonType.YES;        	
            ButtonType noButton = new ButtonType("Não");
            noButton = ButtonType.NO;
            alert.getButtonTypes().setAll(yesButton, noButton);
            
        	alert.showAndWait().ifPresent(rs -> {
        	    if (rs == ButtonType.YES) {//Se pressionado o botão ok exclui registro
            		int cod_cliente = Integer.parseInt(txtCodCliente.getText());
            		try {
						new ClienteDAO().Excluir(cod_cliente);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            		LimparTela();
            		listaCliente.clear();
            		try {
						listaCliente = new ClienteDAO().RetornaClientes();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            		index = 0;
            		PopularTela();
        	    }
        	});
    		
		} catch (Exception e) {

			e.printStackTrace();
		}
    	
    }
    
    @FXML
    void PrimeiroRegistro(ActionEvent event) throws SQLException {
    	
    	index = 0;
    	PopularTela();
    }
    
    
    @FXML
    void UltimoRegistro(ActionEvent event) throws SQLException {
    	    	
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
