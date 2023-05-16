package br.com.rsds.crudspringcisco3905.serice;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.rsds.crudspringcisco3905.exception.RecordNotFoundException;
import br.com.rsds.crudspringcisco3905.model.RamaisList;
import br.com.rsds.crudspringcisco3905.repository.RamaisRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Service
@Validated
public class RamaisService {
	private final RamaisRepository ramaisRepository;

	public RamaisService(RamaisRepository ramaisRepository) {
		this.ramaisRepository = ramaisRepository;
	}

	public List<RamaisList> list(String serial) {
		if (serial == null) {
			return ramaisRepository.findAll();
		}
		return ramaisRepository.findBySerialNumber(serial);
	}

	public RamaisList FindById(@PathVariable @NotNull @Positive Long id) {
		return ramaisRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
	}

	public RamaisList create(@Valid RamaisList record) {
		return this.ramaisRepository.save(record);
	}

	public RamaisList Update(@NotNull @Positive Long id, @RequestBody @Valid RamaisList record) {
		return ramaisRepository.findById(id).map(recordFound -> {
			recordFound.setRamal(record.getRamal());
			recordFound.setSerialNumber(record.getSerialNumber());
			recordFound.setIpCentral(record.getIpCentral());
			return ramaisRepository.save(recordFound);
		}).orElseThrow(() -> new RecordNotFoundException(id));
	}

	public void Delete(@PathVariable @NotNull @Positive Long id) {
		ramaisRepository.delete(ramaisRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));
	}
}
