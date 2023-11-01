package br.com.rsds.crudspringcisco3905.model;

import org.hibernate.validator.constraints.Length;

import br.com.rsds.crudspringcisco3905.enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "RAMAISLIST")
@Data

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
	private Status status = Status.INDISPONIVEL;
}