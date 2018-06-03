package VO;

public class Marcas {
	
	private int cod_marca;
	private String desc_marca;
	
	public int getCod_marca() {
		return cod_marca;
	}
	public void setCod_marca(int cod_marca) {
		this.cod_marca = cod_marca;
	}
	public String getDesc_marca() {
		return desc_marca;
	}
	public void setDesc_marca(String desc_marca) {
		this.desc_marca = desc_marca;
	}
	
	//Sobrescreve toString e passa o que vai aparecer no combobox
	@Override
	public String toString() {
		return desc_marca;		
	}
}
