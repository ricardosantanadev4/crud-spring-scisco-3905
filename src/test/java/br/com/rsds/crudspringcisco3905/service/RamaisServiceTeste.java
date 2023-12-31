package br.com.rsds.crudspringcisco3905.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.rsds.crudspringcisco3905.dto.RamaisDTO;
import br.com.rsds.crudspringcisco3905.dto.mapper.RamaisMapper;
import br.com.rsds.crudspringcisco3905.model.RamaisList;
import br.com.rsds.crudspringcisco3905.repository.RamaisRepository;

@ExtendWith(MockitoExtension.class)
public class RamaisServiceTeste {

	@Mock
	private RamaisMapper ramaisMapper;

	@Mock
	private RamaisRepository ramaisRepository;

	@InjectMocks
	private RamaisService ramaisService;

	@Nested
	class create {
		@Test
		@DisplayName("should create an ramal successfully")
		void createTest() {
			// Arrange
			var record = new RamaisDTO(1L, "6001", "VBFHDNCEHFFGFJF", "R1c4rd0", "192.168.0.230", "Indisponivel");

			var ramaisList = new RamaisList();
			ramaisList.setId(record.id());
			ramaisList.setRamal(record.ramal());
			ramaisList.setSerialNumber(record.serialNumber());
			ramaisList.setPassWord(record.passWord());
			ramaisList.setIpCentral(record.ipCentral());
			ramaisList.setStatus(ramaisList.getStatus());

			doReturn(ramaisList).when(ramaisMapper).toEntity(any());
			doReturn(record).when(ramaisMapper).toDto(any());
			doReturn(ramaisList).when(ramaisRepository).save(any());

			// Act
			var ramalDTO = ramaisService.create(record);

			// Assert
			assertNotNull(ramalDTO);
		}
	}

}
