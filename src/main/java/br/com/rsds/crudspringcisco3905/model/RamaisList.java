package br.com.rsds.crudspringcisco3905.model;

import org.hibernate.validator.constraints.Length;

import br.com.rsds.crudspringcisco3905.enums.Status;
import br.com.rsds.crudspringcisco3905.enums.converters.StatusConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "RAMAISLIST")

public class RamaisList {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank
	@NotNull
	@Column(length = 4, nullable = false)
	@Length(min = 4, max = 4)
	private String ramal;

	@NotBlank
	@NotNull
	@Column(length = 15, nullable = false)
	@Length(min = 15, max = 15)
	private String serialNumber;

	@NotBlank
	@NotNull
	@Column(length = 100, nullable = false)
	@Length(min = 1, max = 100)
	private String passWord;

	@NotBlank
	@NotNull
	@Column(length = 15, nullable = false)
	@Length(min = 7, max = 15)
	private String ipCentral;

	@NotNull
	@Column(name = "STATUS", nullable = false)
	@Convert(converter = StatusConverter.class)
	private Status status = Status.INDISPONIVEL;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRamal() {
		return ramal;
	}

	public void setRamal(@NotBlank @NotNull @Length(min = 4, max = 4) String ramal) {
		this.ramal = ramal;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(@NotBlank @NotNull @Length(min = 7, max = 15) String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(@NotBlank @NotNull @Length(min = 1, max = 100) String passWord) {
		this.passWord = passWord;
	}

	public String getIpCentral() {
		return ipCentral;
	}

	public void setIpCentral(@NotBlank @NotNull @Length(min = 7, max = 15) String ipCentral) {
		this.ipCentral = ipCentral;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(@NotNull Status status) {
		this.status = status;
	}

}