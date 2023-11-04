package br.com.rsds.crudspringcisco3905.dto;

import org.hibernate.validator.constraints.Length;

import br.com.rsds.crudspringcisco3905.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RamaisDTO(Long id,

		@NotBlank @NotNull @Length(min = 4, max = 4) String ramal,

		@NotBlank @NotNull @Length(min = 15, max = 15) String serialNumber,

		@NotBlank @NotNull @Length(min = 1, max = 100) String passWord,

		@NotBlank @NotNull @Length(min = 7, max = 15) String ipCentral,

		Status status) {
}