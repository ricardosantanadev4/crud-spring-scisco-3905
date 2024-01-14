package br.com.rsds.crudspringcisco3905.dto.mapper;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import br.com.rsds.crudspringcisco3905.dto.RamaisDTO;
import br.com.rsds.crudspringcisco3905.model.RamaisList;
import jakarta.inject.Inject;

public class RamaisMapperTest {

	@Inject
	RamaisMapper ramaisMapper;

	@Nested
	class ToEntity {
		@Test
		void toEntity() {
//			Arrange
			var input = new RamaisDTO(1L, "6001", "VBFHDNCEHFFGFJF", "R1c4rd0", "192.168.0.230", "Indisponivel");

			var ramaisList = new RamaisList();
			ramaisList.setId(input.id());
			ramaisList.setRamal(input.ramal());
			ramaisList.setSerialNumber(input.serialNumber());
			ramaisList.setPassWord(input.passWord());
			ramaisList.setIpCentral(input.ipCentral());
			ramaisList.setStatus(ramaisList.getStatus());
			
//			ACT
			var output = ramaisMapper.toEntity(input);
			
//			Assert
		}
	}
}
