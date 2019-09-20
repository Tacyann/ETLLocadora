package com.linecode.compartilhado.regex;

public class RegexUtil {

	//regex's
	public static final String EMAIL = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";
	public static final String TELEFONE = "[0-9]{10,11}";
	
	//mensagens de erro
	public static final String MSG_ERRO_EMAIL = "Email inválido";
	public static final String MSG_ERRO_TELEFONE = "Telefone inválido";
	private RegexUtil() {
		//somente atributos staticos
	} 
}
