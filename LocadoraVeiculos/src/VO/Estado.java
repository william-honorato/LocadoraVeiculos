package VO;

public class Estado {
	
	private String cod_uf;
	private String desc_uf;
	
	public String getCod_uf() {
		return cod_uf;
	}
	public void setCod_uf(String cod_uf) {
		this.cod_uf = cod_uf;
	}
	public String getDesc_uf() {
		return desc_uf;
	}
	public void setDesc_uf(String desc_uf) {
		this.desc_uf = desc_uf;
	}
	
	//Sobrescreve toString e passa o que vai aparecer no combobox
	@Override
	public String toString() {
		return desc_uf;		
	}

}