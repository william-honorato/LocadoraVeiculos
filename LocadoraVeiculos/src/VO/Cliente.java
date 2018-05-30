package VO;

import java.util.Date;

public class Cliente {
	
	private int cod_cliente;
	private String nome;
	private Date dt_nascimento;
	private char tipo_pessoa;
	private int cod_estado_civil;
	private double rendimento;
	private int cod_uf;
	
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
	public char getTipo_pessoa() {
		return tipo_pessoa;
	}
	public void setTipo_pessoa(char tipo_pessoa) {
		this.tipo_pessoa = tipo_pessoa;
	}
	public int getCod_estado_civil() {
		return cod_estado_civil;
	}
	public void setCod_estado_civil(int cod_estado_civil) {
		this.cod_estado_civil = cod_estado_civil;
	}
	public double getRendimento() {
		return rendimento;
	}
	public void setRendimento(double rendimento) {
		this.rendimento = rendimento;
	}
	public int getCod_uf() {
		return cod_uf;
	}
	public void setCod_uf(int cod_uf) {
		this.cod_uf = cod_uf;
	}	

}