package br.com.model;

import java.util.Calendar;

public class Locacoes {

	private Integer cod_soc;
	private Calendar dat_loc;
	private Integer val_loc;
	private Calendar dat_venc;
	private String sta_pgto;
	private Calendar dat_pgto;
	public Integer getCod_soc() {
		return cod_soc;
	}
	public void setCod_soc(Integer cod_soc) {
		this.cod_soc = cod_soc;
	}
	public Calendar getDat_loc() {
		return dat_loc;
	}
	public void setDat_loc(Calendar dat_loc) {
		this.dat_loc = dat_loc;
	}
	public Integer getVal_loc() {
		return val_loc;
	}
	public void setVal_loc(Integer val_loc) {
		this.val_loc = val_loc;
	}
	public Calendar getDat_venc() {
		return dat_venc;
	}
	public void setDat_venc(Calendar dat_venc) {
		this.dat_venc = dat_venc;
	}
	public String getSta_pgto() {
		return sta_pgto;
	}
	public void setSta_pgto(String sta_pgto) {
		this.sta_pgto = sta_pgto;
	}
	public Calendar getDat_pgto() {
		return dat_pgto;
	}
	public void setDat_pgto(Calendar dat_pgto) {
		this.dat_pgto = dat_pgto;
	}
	
	
	
}
