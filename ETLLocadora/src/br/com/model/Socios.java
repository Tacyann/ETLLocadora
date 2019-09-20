package br.com.model;
import java.util.Calendar;

public class Socios {
	
	private Integer cod_soc;
	private Calendar dat_cad;
	private Integer cod_tps;
	private String sta_soc;
	private String nom_soc;
	public Integer getCod_soc() {
		return cod_soc;
	}
	public void setCod_soc(Integer cod_soc) {
		this.cod_soc = cod_soc;
	}
	public Calendar getDat_cad() {
		return dat_cad;
	}
	public void setDat_cad(Calendar dat_cad) {
		this.dat_cad = dat_cad;
	}
	public Integer getCod_tps() {
		return cod_tps;
	}
	public void setCod_tps(Integer cod_tps) {
		this.cod_tps = cod_tps;
	}
	public String getSta_soc() {
		return sta_soc;
	}
	public void setSta_soc(String sta_soc) {
		this.sta_soc = sta_soc;
	}
	public String getNom_soc() {
		return nom_soc;
	}
	public void setNom_soc(String nom_soc) {
		this.nom_soc = nom_soc;
	}
	
	

}
