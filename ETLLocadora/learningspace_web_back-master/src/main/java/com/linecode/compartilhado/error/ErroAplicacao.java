package com.linecode.compartilhado.error;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ErroAplicacao extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private final static Logger logger = LogManager.getLogger("log");
	
	public ErroAplicacao(String msg, Exception e) {
		super(msg);
		logger.fatal(msg,e);
	}	
}