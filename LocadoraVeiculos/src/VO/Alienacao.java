package VO;

import java.util.Date;

public class Alienacao {
	
	private int cod_alienacao;
	private int cod_cliente;
	private Date data_cadastro;
	private Date data_liberacao;
	private int cod_marca;
	private String modelo;
	private int ano;
	private int ano_modelo;
	private int cod_cor;
	private String numchassi;
	private String cod_uf_placa;
	
	public int getCod_alienacao() {
		return cod_alienacao;
	}
	public void setCod_alienacao(int cod_alienacao) {
		this.cod_alienacao = cod_alienacao;
	}
	public int getCod_cliente() {
		return cod_cliente;
	}
	public void setCod_cliente(int cod_cliente) {
		this.cod_cliente = cod_cliente;
	}
	public Date getData_cadastro() {
		return data_cadastro;
	}
	public void setData_cadastro(Date data_cadastro) {
		this.data_cadastro = data_cadastro;
	}
	public Date getData_liberacao() {
		return data_liberacao;
	}
	public void setData_liberacao(Date data_liberacao) {
		this.data_liberacao = data_liberacao;
	}
	public int getCod_marca() {
		return cod_marca;
	}
	public void setCod_marca(int cod_marca) {
		this.cod_marca = cod_marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public int getAno_modelo() {
		return ano_modelo;
	}
	public void setAno_modelo(int ano_modelo) {
		this.ano_modelo = ano_modelo;
	}
	public int getCod_cor() {
		return cod_cor;
	}
	public void setCod_cor(int cod_cor) {
		this.cod_cor = cod_cor;
	}
	public String getNumchassi() {
		return numchassi;
	}
	public void setNumchassi(String numchassi) {
		this.numchassi = numchassi;
	}
	public String getCod_uf_placa() {
		return cod_uf_placa;
	}
	public void setCod_uf_placa(String cod_uf_placa) {
		this.cod_uf_placa = cod_uf_placa;
	}
}
