package br.com.rsds.crudspringcisco3905.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.rsds.crudspringcisco3905.model.RamaisList;
import br.com.rsds.crudspringcisco3905.repository.RamaisRepository;
import br.com.rsds.crudspringcisco3905.serice.RamaisService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@RestController
@RequestMapping("/api/ramais-list")
//@AllArgsConstructor
public class RamaisController {

//	private final RamaisRepository ramaisRepository;
	private final RamaisService ramaisService;

	public RamaisController(RamaisService ramaisService) {
//		this.ramaisRepository = ramaisRepository;
		this.ramaisService = ramaisService;
	}

//	@GetMapping
//	public List<RamaisList> list() {
//		List<RamaisList> ramal = ramaisRepository.findAll();
//		return ramal;
//	}

	@GetMapping
	public List<RamaisList> list(@RequestParam(required = false) String serial) {
		System.out.println("list");
		if (serial == null) {
			System.out.println("sem parametro");
			List<RamaisList> ramais = ramaisService.list(serial);
			return ramais;
		} else {
			System.out.println("com parametro");
			List<RamaisList> ramais = ramaisService.list(serial);
			return ramais;
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<RamaisList> FindById(@PathVariable @NotNull @Positive Long id) {
		System.out.println("RamaisList");
		return ramaisService.FindById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public RamaisList create(@RequestBody @Valid RamaisList record) {
		return ramaisService.create(record);
	}

	@PutMapping("/{id}")
	public ResponseEntity<RamaisList> Update(@PathVariable @NotNull @Positive Long id,
			@RequestBody @Valid RamaisList record) {
		return ramaisService.Update(id, record).map(recordFind -> ResponseEntity.ok(recordFind))
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> Delete(@PathVariable @NotNull @Positive Long id) {
		if (ramaisService.Delete(id)) {
			return ResponseEntity.noContent().<Void>build();
		}
		return (ResponseEntity.notFound().build());
	}
}
