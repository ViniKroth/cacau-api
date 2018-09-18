package com.cacau.api.exception;

/**
 * Excecoes de Banco de Dados
 * 
 * @author Cassio
 *
 */

public class PersistencyException extends Exception {

	private static final long serialVersionUID = -3517170452748690517L;

	public PersistencyException(String error) {
		super(error);
	}

	public PersistencyException(Exception e) {
		super(e.getMessage());
	}

	public PersistencyException(String error, Exception e) {
		super(error, e);
	}

}
