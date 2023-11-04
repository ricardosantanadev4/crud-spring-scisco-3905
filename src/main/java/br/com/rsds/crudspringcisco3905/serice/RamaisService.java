package br.com.rsds.crudspringcisco3905.serice;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.rsds.crudspringcisco3905.dto.RamaisDTO;
import br.com.rsds.crudspringcisco3905.dto.mapper.RamaisMapper;
import br.com.rsds.crudspringcisco3905.exception.RecordNotFoundException;
import br.com.rsds.crudspringcisco3905.repository.RamaisRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Service
@Validated
public class RamaisService {
	private final RamaisRepository ramaisRepository;
	private final RamaisMapper ramaisMapper;

	public RamaisService(RamaisRepository ramaisRepository, RamaisMapper ramaisMapper) {
		this.ramaisRepository = ramaisRepository;
		this.ramaisMapper = ramaisMapper;
	}

	public List<RamaisDTO> list(String serial) {
		if (serial == null) {
			return ramaisRepository.findAll().stream().map(ramaisMapper::toDto).collect(Collectors.toList());
		}
		return ramaisRepository.findBySerialNumber(serial).stream().map(ramaisMapper::toDto)
				.collect(Collectors.toList());
	}

	public RamaisDTO FindById(@PathVariable @NotNull @Positive Long id) {
		return ramaisRepository.findById(id).map(ramaisMapper::toDto)
				.orElseThrow(() -> new RecordNotFoundException(id));
	}

	public RamaisDTO create(@Valid RamaisDTO record) {
		return ramaisMapper.toDto(this.ramaisRepository.save(ramaisMapper.toEntity(record)));
	}

	public RamaisDTO Update(@NotNull @Positive Long id, @RequestBody @Valid RamaisDTO record) {
		return ramaisRepository.findById(id).map(recordFound -> {
			recordFound.setRamal(record.ramal());
			recordFound.setSerialNumber(record.serialNumber());
			recordFound.setIpCentral(record.ipCentral());
			return ramaisMapper.toDto(ramaisRepository.save(recordFound));
		}).orElseThrow(() -> new RecordNotFoundException(id));
	}

	public void Delete(@PathVariable @NotNull @Positive Long id) {
		ramaisRepository.delete(ramaisRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));
	}
}
