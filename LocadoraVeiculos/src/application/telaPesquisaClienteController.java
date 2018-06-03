package application;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import DAO.ClienteDAO;
import VO.Cliente;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class telaPesquisaClienteController implements Initializable {

	ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
	private static telaAlienacaoController tela;
	
	public void setTelaDeRetorno(telaAlienacaoController t) {
		tela = t;
	}
	
    @FXML
    private TableView<Cliente> tabela = new TableView<Cliente>();
    
    @FXML
    private TableColumn<Cliente, String> colNome;

    @FXML
    private TableColumn<Cliente, Number> colCodigo;

    @FXML
    private TextField txtPesquisa;

    @FXML
    private Button btnPesquisar;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		//Associar nome da coluna com a classe Cliente
        /*colNome.setCellValueFactory(
                new PropertyValueFactory<>("colNome"));
        colCodigo.setCellValueFactory(
                new PropertyValueFactory<>("colCodigo"));*/
        
        colNome.setCellValueFactory(new Callback<CellDataFeatures<Cliente, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<Cliente, String> p) {
                return p.getValue().getColNome();
            }
         });
        
        colCodigo.setCellValueFactory(new Callback<CellDataFeatures<Cliente, Number>, ObservableValue<Number>>() {
            public ObservableValue<Number> call(CellDataFeatures<Cliente, Number> p) {
                return p.getValue().getColCodigo();
            }
         });
        
        colNome.setPrefWidth(116);
        colCodigo.setPrefWidth(226);
		
        try {
			listaClientes = new ClienteDAO().RetornaClientes();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        //Popula a tabela
        tabela.setItems(listaClientes());
        
        //Implementa duplo click na tabela para pegar o cliente
        tabela.setRowFactory( tv -> {
            TableRow<Cliente> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Cliente rowCliente = row.getItem();
                    txtPesquisa.setText(rowCliente.getNome());
                }
            });
            return row ;
        });
    }

    private ObservableList<Cliente> listaClientes() {
    	ObservableList<Cliente> obsClientes = FXCollections.observableArrayList(listaClientes);
    	return obsClientes;
    }
	
    @FXML    
    void pesquisarCliente(ActionEvent event) throws SQLException {

    	ArrayList<Cliente> listaTemp = new ArrayList<>();
    	
    	for(int x = 0; x < listaClientes.size(); x++){
    	    SimpleStringProperty nome = listaClientes.get(x).getColNome();
    	    SimpleIntegerProperty codigo = listaClientes.get(x).getColCodigo();
    	    
    	    String concat = codigo.getValue() + " " + nome.getValue();
    	    
    	    if(concat.toLowerCase().contains(txtPesquisa.getText().toLowerCase())) {
    	    	listaTemp.add(listaClientes.get(x));
    	    }
    	}
    	ObservableList<Cliente> obsPesquisaClientes = FXCollections.observableArrayList(listaTemp);
    	tabela.setItems(obsPesquisaClientes);

    }

}