package br.com.rsds.crudspringcisco3905.service;

import java.io.IOException;
import java.util.List;
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
			Page<RamaisList> ramalPage = ramaisRepository.findAll(PageRequest.of(page, size, Sort.Direction.ASC, "id"));
			List<RamaisDTO> ramais = ramalPage.get().map(ramaisMapper::toDto).collect(Collectors.toList());
			return new PageDTO(ramais, ramalPage.getTotalElements(), ramalPage.getTotalPages());
		}

		PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "id");
		Page<RamaisList> ramalPage = ramaisRepository.search(searchTerm.toLowerCase(), pageRequest);
		List<RamaisDTO> ramais = ramalPage.get().map(ramaisMapper::toDto).collect(Collectors.toList());
		return new PageDTO(ramais, ramalPage.getTotalElements(), ramalPage.getTotalPages());
	}

	public RamaisDTO findById(@PathVariable @NotNull @Positive Long id) {
		return ramaisRepository.findById(id).map(ramaisMapper::toDto)
				.orElseThrow(() -> new RecordNotFoundException(id));
	}

	public RamaisDTO create(@Valid RamaisDTO ramaisDTO) throws IOException {
//		RamaisList ramaisList = new RamaisList();
//		ramaisList.setId(record.id());
//		ramaisList.setRamal(record.ramal());
//		ramaisList.setSerialNumber(record.serialNumber());
//		ramaisList.setPassWord(record.passWord());
//		ramaisList.setIpCentral(record.ipCentral());
//		ramaisList.setStatus(ramaisList.getStatus());

//		return ramaisMapper.toDto(this.ramaisRepository.save(ramaisList));

		fileManipulator.fileCreator(ramaisDTO.serialNumber(), ramaisDTO.ipCentral(), ramaisDTO.ramal(),
				ramaisDTO.passWord());

		return ramaisMapper.toDto(this.ramaisRepository.save(this.ramaisMapper.toEntity(ramaisDTO)));
	}

	public RamaisDTO Update(@NotNull @Positive Long id, @RequestBody @Valid RamaisDTO ramaisDTO) {
		RamaisDTO output = ramaisRepository.findById(id).map(ramaisList -> {

			String getSerial = ramaisList.getSerialNumber();

			try {
				fileManipulator.fileEditor(ramaisDTO.serialNumber(), ramaisDTO.ipCentral(), ramaisDTO.ramal(),
						ramaisDTO.passWord(), getSerial);
			} catch (IOException e) {
				e.printStackTrace();
			}

			ramaisList.setRamal(ramaisDTO.ramal());
			ramaisList.setPassWord(ramaisDTO.passWord());
			ramaisList.setSerialNumber(ramaisDTO.serialNumber());
			ramaisList.setIpCentral(ramaisDTO.ipCentral());

			return ramaisMapper.toDto(ramaisRepository.save(ramaisList));
		}).orElseThrow(() -> new RecordNotFoundException(id));

		return output;
	}

	public void Delete(@PathVariable @NotNull @Positive Long id) {

		RamaisList getSerial;

		ramaisRepository
				.delete(getSerial = ramaisRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));
		
		fileManipulator.fileDelete(getSerial.getSerialNumber());

	}
}
