package br.com.natanael.Modelo;

public class Linha {

	public enum estado {OPERANDO,ATENCAO,DESATIVADA};
	
	private String nomeLinha;
	private estado estadoLinha;
	private int iconeLinha;
	private String informacaoLinha;
	
	public String getInformacaoLinha() {
		return informacaoLinha;
	}
	public void setInformacaoLinha(String informacaoLinha) {
		this.informacaoLinha = informacaoLinha;
	}
	public int getIconeLinha() {
		return iconeLinha;
	}
	public void setIconeLinha(int iconeLinha) {
		this.iconeLinha = iconeLinha;
	}
	public String getNomeLinha() {
		return nomeLinha;
	}
	public void setNomeLinha(String nomeLinha) {
		this.nomeLinha = nomeLinha;
	}
	public estado getEstadoLinha() {
		return estadoLinha;
	}
	public void setEstadoLinha(estado estadoLinha) {
		this.estadoLinha = estadoLinha;
	}
	
	
	
}
