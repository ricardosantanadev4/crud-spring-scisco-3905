package br.com.rsds.crudspringcisco3905.exception;

public class RecordNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RecordNotFoundException(Long id) {
		super("Registro não encontrado com id: " + id);
	}

	public RecordNotFoundException(String serial) {
		super("Registro não encontrado com serial: " + serial);
	}
}
