package br.com.rsds.crudspringcisco3905.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.rsds.crudspringcisco3905.dto.PageDTO;
import br.com.rsds.crudspringcisco3905.dto.RamaisDTO;
import br.com.rsds.crudspringcisco3905.dto.mapper.RamaisMapper;
import br.com.rsds.crudspringcisco3905.exception.RecordNotFoundException;
import br.com.rsds.crudspringcisco3905.model.RamaisList;
import br.com.rsds.crudspringcisco3905.persistfilexml.FileManipulator;
import br.com.rsds.crudspringcisco3905.repository.RamaisRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Service
@Validated
public class RamaisService {
	private final RamaisRepository ramaisRepository;
	private final RamaisMapper ramaisMapper;
	private final FileManipulator fileManipulator;

	public RamaisService(RamaisRepository ramaisRepository, RamaisMapper ramaisMapper,
			FileManipulator fileManipulator) {
		this.ramaisRepository = ramaisRepository;
		this.ramaisMapper = ramaisMapper;
		this.fileManipulator = fileManipulator;
	}

	public PageDTO list(@RequestParam(required = false) String searchTerm, @RequestParam @PositiveOrZero int page,
			@RequestParam @Positive @Max(25) int size) {
		if (searchTerm == null) {
			Page<RamaisList> ramalPage = this.ramaisRepository
					.findAll(PageRequest.of(page, size, Sort.Direction.ASC, "id"));
			List<RamaisDTO> ramais = ramalPage.get().map(this.ramaisMapper::toDto).collect(Collectors.toList());
			return new PageDTO(ramais, ramalPage.getTotalElements(), ramalPage.getTotalPages());
		}

		PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "id");
		Page<RamaisList> ramalPage = this.ramaisRepository.search(searchTerm.toLowerCase(), pageRequest);
		List<RamaisDTO> ramais = ramalPage.get().map(this.ramaisMapper::toDto).collect(Collectors.toList());
		return new PageDTO(ramais, ramalPage.getTotalElements(), ramalPage.getTotalPages());
	}

	public RamaisDTO findById(@PathVariable @NotNull @Positive Long id) {
		return this.ramaisRepository.findById(id).map(this.ramaisMapper::toDto)
				.orElseThrow(() -> new RecordNotFoundException(id));
	}

	public Boolean findByRamal(RamaisDTO ramaisDTO) {
		Optional<RamaisList> getSerialNumber = this.ramaisRepository.findBySerialNumber(ramaisDTO.serialNumber());
		Optional<RamaisList> getRamalIpCentral = this.ramaisRepository.findByRamal(ramaisDTO.ramal().toLowerCase(),
				ramaisDTO.ipCentral());

		if (getSerialNumber.isEmpty() && getRamalIpCentral.isEmpty()) {
			return true;
		}
		throw new IllegalArgumentException("Operação não concluida!\n" + "Cadastro existente na base de dados.\n"
				+ "Observações:\n"
				+ "Existe um ramal igual ao fornecido cadastrado na mesma central ou o serial informado já possui cadastro em alguma central.\n"
				+ "Ação a ser tomada: Edite ou exclua o cadastro existente.\n");
	}

	public RamaisDTO create(@Valid RamaisDTO ramaisDTO) throws IOException {
		this.findByRamal(ramaisDTO);

		this.fileManipulator.fileCreator(ramaisDTO.serialNumber(), ramaisDTO.ipCentral(), ramaisDTO.ramal(),
				ramaisDTO.passWord());

		return this.ramaisMapper.toDto(this.ramaisRepository.save(this.ramaisMapper.toEntity(ramaisDTO)));
	}

	public RamaisDTO Update(@NotNull @Positive Long id, @RequestBody @Valid RamaisDTO ramaisDTO) {
		return this.ramaisRepository.findById(id).map(ramaisList -> {
			this.findByRamal(ramaisDTO);

			try {
				this.fileManipulator.fileEditor(ramaisDTO.serialNumber(), ramaisDTO.ipCentral(), ramaisDTO.ramal(),
						ramaisDTO.passWord(), ramaisList.getSerialNumber());
			} catch (IOException e) {
				e.printStackTrace();
			}

			ramaisList.setRamal(ramaisDTO.ramal());
			ramaisList.setPassWord(ramaisDTO.passWord());
			ramaisList.setSerialNumber(ramaisDTO.serialNumber());
			ramaisList.setIpCentral(ramaisDTO.ipCentral());

			return this.ramaisMapper.toDto(ramaisRepository.save(ramaisList));
		}).orElseThrow(() -> new RecordNotFoundException(id));
	}

	public void Delete(@PathVariable @NotNull @Positive Long id) {

		RamaisList getSerial;

		this.ramaisRepository
				.delete(getSerial = ramaisRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));

		this.fileManipulator.fileDelete(getSerial.getSerialNumber());

	}
}
