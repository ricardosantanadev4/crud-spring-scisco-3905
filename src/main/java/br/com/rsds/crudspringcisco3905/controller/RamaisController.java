package br.com.rsds.crudspringcisco3905.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.rsds.crudspringcisco3905.model.RamaisList;
import br.com.rsds.crudspringcisco3905.repository.RamaisRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;

@Validated
@RestController
@RequestMapping("/api/ramais-list")
@AllArgsConstructor
public class RamaisController {

	RamaisRepository ramaisRepository;

//	@GetMapping
//	public List<RamaisList> list() {
//		List<RamaisList> ramal = ramaisRepository.findAll();
//		return ramal;
//	}

	@GetMapping
	public List<RamaisList> listaAlunos(@RequestParam(required = false) @NotNull @NotBlank String serial) {
		System.out.println("listaAlunos");
		if (serial == null) {
			System.out.println("sem parametro");
			List<RamaisList> ramais = ramaisRepository.findAll();
			return ramais;
		} else {
			System.out.println("com parametro");
			List<RamaisList> ramais = ramaisRepository.findBySerialNumber(serial);
			return ramais;
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<RamaisList> GetById(@PathVariable @NotNull @Positive Long id) {
		System.out.println("RamaisList");
		return ramaisRepository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public RamaisList create(@RequestBody @Valid RamaisList record) {
		return this.ramaisRepository.save(record);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remove(@PathVariable @NotNull @Positive Long id) {

		return ramaisRepository.findById(id).map(ramal -> {
			ramaisRepository.deleteById(id);
			return ResponseEntity.noContent().<Void>build();
		}).orElse(ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<RamaisList> Update(@PathVariable @NotNull @Positive Long id,
			@RequestBody @Valid RamaisList record) {
		return ramaisRepository.findById(id).map(recordFind -> {
			recordFind.setRamal(record.getRamal());
			recordFind.setSerialNumber(record.getSerialNumber());
			recordFind.setIpCentral(record.getIpCentral());
			RamaisList ramal = ramaisRepository.save(recordFind);
			return ResponseEntity.ok(recordFind);
		}).orElse(ResponseEntity.notFound().build());
	}

}
