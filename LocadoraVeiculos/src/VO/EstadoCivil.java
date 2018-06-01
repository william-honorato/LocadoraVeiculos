package VO;

public class EstadoCivil {
	
	private String cod_estado_civil;
	private String desc_estado_civil;
	
	public String getCod_estado_civil() {
		return cod_estado_civil;
	}
	public void setCod_estado_civil(String cod_estado_civil) {
		this.cod_estado_civil = cod_estado_civil;
	}
	public String getDesc_estado_civil() {
		return desc_estado_civil;
	}
	public void setDesc_estado_civil(String desc_estado_civil) {
		this.desc_estado_civil = desc_estado_civil;
	}
	
	//Sobrescreve toString e passa o que vai aparecer no combobox
	@Override
	public String toString() {
		return getDesc_estado_civil();		
	}

}