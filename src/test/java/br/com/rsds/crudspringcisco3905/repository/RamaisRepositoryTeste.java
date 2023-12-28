package br.com.rsds.crudspringcisco3905.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import br.com.rsds.crudspringcisco3905.dto.RamaisDTO;
import br.com.rsds.crudspringcisco3905.dto.mapper.RamaisMapper;
import br.com.rsds.crudspringcisco3905.model.RamaisList;

@DataJpaTest
@ActiveProfiles("teste")
@ComponentScan("br.com.rsds.crudspringcisco3905.dto.mapper")
class RamaisRepositoryTeste {

//	@Autowired
//	private EntityManager entityManager;

	@Autowired
	private RamaisRepository ramaisRepository;

	@Autowired
	RamaisMapper ramaisMapper;

	@Test
	@DisplayName("Shoud must successfully obtain the ramais from the DB that contain the data passed in the searchTerm "
			+ "parameter in one of the columns")
	void searchSucess() {
		String searchTerm = "r1c4";
		int page = 0;
		int size = 10;
		PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "id");
		RamaisDTO ramalDTO = new RamaisDTO(null, "6001", "LZK6064890DFDR1", "R1c4rd0", "192.168.169.110", null);
		this.createRamal(ramalDTO);
		Page<RamaisList> ramalPage = this.ramaisRepository.search(searchTerm.toLowerCase(), pageRequest);
//		ramalPage.get().forEach(r -> System.out.println(r.getSerialNumber()));
		assertThat(ramalPage.isEmpty()).isFalse();
	};

	@Test
	@DisplayName("should not get ramais when none of the columns contain the data passed in the serarchTerm parameter")
	void searchUnsuccessfully() {
		String searchTerm = "6001";
		int page = 0;
		int size = 10;
		PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "id");
//		RamaisDTO ramalDTO = new RamaisDTO(null, "6001", "LZDFGDFRTOGTUGT", "R1c4rd0", "192.168.169.110", null);
//		this.createRamal(ramalDTO);
		Page<RamaisList> ramalPage = this.ramaisRepository.search(searchTerm, pageRequest);
		assertThat(ramalPage.isEmpty()).isTrue();
	};

	private RamaisDTO createRamal(RamaisDTO ramalDTO) {
//		entityManager.persist(this.ramaisMapper.toEntity(ramalDTO));
//		return ramalDTO;
		return this.ramaisMapper.toDto(this.ramaisRepository.save(this.ramaisMapper.toEntity(ramalDTO)));
	}
}