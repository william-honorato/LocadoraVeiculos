package VO;

import java.util.Date;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Cliente {
		
	private int cod_cliente;
	private String nome;
	private Date dt_nascimento;
	private String tipo_pessoa;
	private String cod_estado_civil;
	private double rendimento;
	private String cod_uf;
	
	//Usado na Pesquisa
	public SimpleIntegerProperty colCodigo;
	public SimpleStringProperty colNome;
    
    public void setColCodigo(SimpleIntegerProperty colCodigo) {
		this.colCodigo = colCodigo;
	}
	public void setColNome(SimpleStringProperty colNome) {
		this.colNome = colNome;
	}	
	public SimpleIntegerProperty getColCodigo() {
		return colCodigo;
	}
	public SimpleStringProperty getColNome() {
		return colNome;
	}
	
	public int getCod_cliente() {
		return cod_cliente;
	}
	public void setCod_cliente(int cod_cliente) {
		this.cod_cliente = cod_cliente;
	}	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getDt_nascimento() {
		return dt_nascimento;
	}
	public void setDt_nascimento(Date dt_nascimento) {
		this.dt_nascimento = dt_nascimento;
	}
	public String getTipo_pessoa() {
		return tipo_pessoa;
	}
	public void setTipo_pessoa(String tipo_pessoa) {
		this.tipo_pessoa = tipo_pessoa;
	}
	public String getCod_estado_civil() {
		return cod_estado_civil;
	}
	public void setCod_estado_civil(String cod_estado_civil) {
		this.cod_estado_civil = cod_estado_civil;
	}
	public double getRendimento() {
		return rendimento;
	}
	public void setRendimento(double rendimento) {
		this.rendimento = rendimento;
	}
	public String getCod_uf() {
		return cod_uf;
	}
	public void setCod_uf(String cod_uf) {
		this.cod_uf = cod_uf;
	}	

}