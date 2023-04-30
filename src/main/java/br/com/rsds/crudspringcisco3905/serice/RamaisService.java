package br.com.rsds.crudspringcisco3905.serice;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

	@GetMapping
	public List<RamaisList> list(String serial) {
		if (serial == null) {
			List<RamaisList> ramais = ramaisRepository.findAll();
			return ramais;
		} else {
			List<RamaisList> ramais = ramaisRepository.findBySerialNumber(serial);
			return ramais;
		}
	}

	@GetMapping("/{id}")
	public Optional<RamaisList> FindById(@PathVariable @NotNull @Positive Long id) {
		return ramaisRepository.findById(id);
	}

	@PostMapping
	public RamaisList create(@Valid RamaisList record) {
		return this.ramaisRepository.save(record);
	}

	@PutMapping("/{id}")
	public Optional<RamaisList> Update(@NotNull @Positive Long id, @Valid RamaisList record) {
		return ramaisRepository.findById(id).map(recordFind -> {
			recordFind.setRamal(record.getRamal());
			recordFind.setSerialNumber(record.getSerialNumber());
			recordFind.setIpCentral(record.getIpCentral());
			RamaisList ramal = ramaisRepository.save(recordFind);
			return ramal;
		});
	}

	@DeleteMapping("/{id}")
	public Boolean Delete(@PathVariable @NotNull @Positive Long id) {
		return ramaisRepository.findById(id).map(ramal -> {
			ramaisRepository.deleteById(id);
			return true;
		}).orElse(false);
	}
}
