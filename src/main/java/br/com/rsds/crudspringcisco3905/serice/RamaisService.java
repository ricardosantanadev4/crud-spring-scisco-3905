package br.com.rsds.crudspringcisco3905.serice;

import java.util.ArrayList;
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

	public RamaisService(RamaisRepository ramaisRepository, RamaisMapper ramaisMapper) {
		this.ramaisRepository = ramaisRepository;
		this.ramaisMapper = ramaisMapper;
	}

	public PageDTO list(@RequestParam(required = false) String serial, @RequestParam @PositiveOrZero int page,
			@RequestParam @Positive @Max(25) int size) {
		if (serial == null) {
			Page<RamaisList> ramalPage = ramaisRepository.findAll(PageRequest.of(page, size, Sort.Direction.ASC, "id"));
			List<RamaisDTO> ramais = ramalPage.get().map(ramaisMapper::toDto).collect(Collectors.toList());
			return new PageDTO(ramais, ramalPage.getTotalElements(), ramalPage.getTotalPages());
		}

		List<RamaisDTO> ramais = new ArrayList<RamaisDTO>();
		ramais.add(ramaisRepository.findBySerialNumber(serial).map(ramaisMapper::toDto)
				.orElseThrow(() -> new RecordNotFoundException(serial)));
		return new PageDTO(ramais, 1, 1);
	}

	public RamaisDTO findById(@PathVariable @NotNull @Positive Long id) {
		return ramaisRepository.findById(id).map(ramaisMapper::toDto)
				.orElseThrow(() -> new RecordNotFoundException(id));
	}

	public RamaisDTO create(@Valid RamaisDTO record) {
		return ramaisMapper.toDto(this.ramaisRepository.save(ramaisMapper.toEntity(record)));
	}

	public RamaisDTO Update(@NotNull @Positive Long id, @RequestBody @Valid RamaisDTO record) {
		return ramaisRepository.findById(id).map(recordFound -> {
			recordFound.setRamal(record.ramal());
			recordFound.setPassWord(record.passWord());
			recordFound.setSerialNumber(record.serialNumber());
			recordFound.setIpCentral(record.ipCentral());
			return ramaisMapper.toDto(ramaisRepository.save(recordFound));
		}).orElseThrow(() -> new RecordNotFoundException(id));
	}

	public void Delete(@PathVariable @NotNull @Positive Long id) {
		ramaisRepository.delete(ramaisRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));
	}
}
