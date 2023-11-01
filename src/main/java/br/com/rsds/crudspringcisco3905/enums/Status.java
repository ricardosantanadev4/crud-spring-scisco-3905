package br.com.rsds.crudspringcisco3905.enums;

public enum Status {
	DISPONIVEL("Disponivel"), INDISPONIVEL("Indisponivel");

	private String value;

	Status(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

}