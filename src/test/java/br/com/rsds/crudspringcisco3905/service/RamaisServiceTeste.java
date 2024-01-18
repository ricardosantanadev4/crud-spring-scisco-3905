package br.com.rsds.crudspringcisco3905.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import br.com.rsds.crudspringcisco3905.dto.RamaisDTO;
import br.com.rsds.crudspringcisco3905.dto.mapper.RamaisMapper;
import br.com.rsds.crudspringcisco3905.exception.RecordNotFoundException;
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

	@Captor
	private ArgumentCaptor<RamaisList> ramaisListArgumentCaptor;

	@Captor
	private ArgumentCaptor<Long> longArgumentCaptor;

	@Nested
	class create {
		@Test
		@DisplayName("should create an ramal with successfully")
		void shouldCreateRamalWithSuccessfully() throws IOException {

			// Arrange
			var input = new RamaisDTO(1L, "6001", "VBFHDNCEHFFGFJF", "R1c4rd0", "192.168.0.230", "Indisponivel");

			var ramaisList = new RamaisList();
			ramaisList.setId(input.id());
			ramaisList.setRamal(input.ramal());
			ramaisList.setSerialNumber(input.serialNumber());
			ramaisList.setPassWord(input.passWord());
			ramaisList.setIpCentral(input.ipCentral());
			ramaisList.setStatus(ramaisList.getStatus());

			doReturn(ramaisList).when(ramaisMapper).toEntity(any());
			doReturn(ramaisList).when(ramaisRepository).save(ramaisListArgumentCaptor.capture());
			doReturn(input).when(ramaisMapper).toDto(any());

//		    Act
			var output = ramaisService.create(input);
			var ramalCapturedRamaisList = ramaisListArgumentCaptor.getValue();

//		    Assert
			assertNotNull(output);

			assertEquals(output.id(), ramalCapturedRamaisList.getId());
			assertEquals(output.ramal(), ramalCapturedRamaisList.getRamal());
			assertEquals(output.serialNumber(), ramalCapturedRamaisList.getSerialNumber());
			assertEquals(output.passWord(), ramalCapturedRamaisList.getPassWord());
			assertEquals(output.ipCentral(), ramalCapturedRamaisList.getIpCentral());
			assertEquals(output.status(), ramalCapturedRamaisList.getStatus().getValue());
		}

		@Test
		@DisplayName("should throw exception when errror occurs")
		void createThrowExceptionErrorOccurs() {

			// Arrange
			var input = new RamaisDTO(null, null, null, null, null, null);

			doThrow(new RuntimeException()).when(ramaisRepository).save(any());

			// Act & Assert
			assertThrows(RuntimeException.class, () -> ramaisService.create(input));
		}
	}

	@Nested
	class findById {

		@Test
		@DisplayName("Should get ramal by id with sucess when optional is present")
		void shouldGetRamalByIdWihSucessWhenOptionalIsPresent() {

//			Arrange
			var ramaisList = new RamaisList();
			ramaisList.setId(1L);
			ramaisList.setRamal("6001");
			ramaisList.setSerialNumber("VBFHDNCEHFFGFJF");
			ramaisList.setPassWord("R1c4rd0");
			ramaisList.setIpCentral("192.168.169.110");
			ramaisList.setStatus(ramaisList.getStatus());

			var ramalDTO = new RamaisDTO(ramaisList.getId(), ramaisList.getRamal(), ramaisList.getSerialNumber(),
					ramaisList.getPassWord(), ramaisList.getIpCentral(), ramaisList.getStatus().toString());

			doReturn(Optional.of(ramaisList)).when(ramaisRepository).findById(longArgumentCaptor.capture());
			doReturn(ramalDTO).when(ramaisMapper).toDto(any());

//			ACT
			var output = ramaisService.findById(ramaisList.getId());

			var ramalCaptured = longArgumentCaptor.getValue();
			System.out.println(ramalCaptured);

//			Assert
			assertNotNull(output);
			assertEquals(output.id(), longArgumentCaptor.getValue());

		}

		@Test
		@DisplayName("Should get throw exception when optinal is enpty")
		void shouldGetThrowExceptionWithOptionalIsEnpty() {

//			Arrange
			var id = 1L;

			doThrow(new RecordNotFoundException(id)).when(ramaisRepository).findById(any());

//			ACT & Assert			
			assertThrows(RecordNotFoundException.class, () -> ramaisService.findById(id));

		}
	}

	@Nested
	class list {
		@Test
		@DisplayName("must return all ramais whith sucess when no value is passed in serarchTerm, performing the query using the "
				+ "default values")
		void mustReturnAllRamisWithSucess() {

//			Arrange
			String searchTerm = null;
			int pageValueDefault = 0;
			int sizeValueDefault = 10;

			var ramaisDTO = new RamaisDTO(1L, "6001", "VBFHDNCEHFFGFJF", "R1c4rd0", "192.168.0.230", "Indisponivel");

			var listRamaisDTO = new ArrayList<RamaisDTO>();
			listRamaisDTO.add(ramaisDTO);

			PageRequest pageRequest = PageRequest.of(pageValueDefault, sizeValueDefault, Sort.Direction.ASC, "id");

			when((ramaisRepository).findAll(pageRequest)).thenReturn(Page.empty(pageRequest));

//			ACT
			var output = ramaisService.list(searchTerm, pageValueDefault, sizeValueDefault);

//			Assert
			assertNotNull(output);
		}

		@Test
		@DisplayName("must search when passing some data in the searchTerm parameter")
		void mustSearchWhenPassingSomeDataInTheSearchTermParameter() {

//			Arrange
			var searchTerm = "60";
			int pageValueDefault = 0;
			int sizeValueDefault = 10;

			PageRequest pageRequest = PageRequest.of(pageValueDefault, sizeValueDefault, Sort.Direction.ASC, "id");
			when((ramaisRepository).search(searchTerm, pageRequest)).thenReturn(Page.empty(pageRequest));

//			ACT
			var output = ramaisService.list(searchTerm, pageValueDefault, sizeValueDefault);

//			Assert
			assertNotNull(output);
		}
	}

	@Nested
	class delete {
		@Test
		@DisplayName("should delete successfully if the extension exists in the database")
		void shouldDeleteSuccessfullyIfTheExtensionExistsInTheDatabase() {

//			Arrange

			var ramaisList = new RamaisList();
			ramaisList.setId(1L);
			ramaisList.setRamal("6001");
			ramaisList.setSerialNumber("VBFHDNCEHFFGFJF");
			ramaisList.setPassWord("r1c4rd0");
			ramaisList.setIpCentral("192.168.169.110");
			ramaisList.setStatus(ramaisList.getStatus());

			when((ramaisRepository).findById(longArgumentCaptor.capture())).thenReturn(Optional.of(ramaisList));

//			ACT
			ramaisService.Delete(ramaisList.getId());

//			Assert
			assertEquals(ramaisList.getId(), longArgumentCaptor.getValue());
			verify(ramaisRepository, times(1)).findById(any());
		}

		@Test
		@DisplayName("must return an exception if the ramal does not exist in the database")
		void mustReturnAnExceptionIfTheRamalDoesNotExistInTheDatabase() {
//			Arrange
			var id = 1L;
			when((ramaisRepository).findById(any())).thenThrow(new RecordNotFoundException(id));

//			ACT & Assert
			assertThrows(RecordNotFoundException.class, () -> ramaisService.Delete(id));

		}
	}

}
