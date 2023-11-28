package br.com.rsds.crudspringcisco3905.enums;

public enum Status {
	DISPONIVEL("Disponivel"), INDISPONIVEL("Indisponivel"), OCUPADO("Ocupado");

	private String value;

	Status(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

	@Override
	public String toString() {
		return value;
	}

}