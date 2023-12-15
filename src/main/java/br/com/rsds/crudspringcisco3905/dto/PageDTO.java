package br.com.rsds.crudspringcisco3905.dto;

import java.util.List;

public record PageDTO(List<RamaisDTO> ramais, long TotalElements, int TotalPages) {

}
