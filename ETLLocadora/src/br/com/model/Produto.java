package br.com.model;

public class Produto {
	
	private Integer cod_prod;
	private Integer qtd_estoque;
	private Integer per_parc;
	private Integer preco_pro;
	private Integer cod_forn;
	private String dsc_prod;
	public Integer getCod_prod() {
		return cod_prod;
	}
	public void setCod_prod(Integer cod_prod) {
		this.cod_prod = cod_prod;
	}
	public Integer getQtd_estoque() {
		return qtd_estoque;
	}
	public void setQtd_estoque(Integer qtd_estoque) {
		this.qtd_estoque = qtd_estoque;
	}
	public Integer getPer_parc() {
		return per_parc;
	}
	public void setPer_parc(Integer per_parc) {
		this.per_parc = per_parc;
	}
	public Integer getPreco_pro() {
		return preco_pro;
	}
	public void setPreco_pro(Integer preco_pro) {
		this.preco_pro = preco_pro;
	}
	public Integer getCod_forn() {
		return cod_forn;
	}
	public void setCod_forn(Integer cod_forn) {
		this.cod_forn = cod_forn;
	}
	public String getDsc_prod() {
		return dsc_prod;
	}
	public void setDsc_prod(String dsc_prod) {
		this.dsc_prod = dsc_prod;
	}
	
	

}
