package br.com.rsds.crudspringcisco3905.dto.mapper;

import br.com.rsds.crudspringcisco3905.dto.RamaisDTO;

public interface ToEntity <RamaisList> {
	RamaisList toEntity(RamaisDTO ramaisList);
}
