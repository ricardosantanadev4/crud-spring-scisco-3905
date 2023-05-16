package br.com.rsds.crudspringcisco3905.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
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
import br.com.rsds.crudspringcisco3905.serice.RamaisService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@RestController
@RequestMapping("/api/ramais-list")
public class RamaisController {

	private final RamaisService ramaisService;

	public RamaisController(RamaisService ramaisService) {
		this.ramaisService = ramaisService;
	}

	@GetMapping
	public @ResponseBody List<RamaisList> list(@RequestParam(required = false) String serial) {
		System.out.println("list");
		return ramaisService.list(serial);
	}

	@GetMapping("/{id}")
	public RamaisList FindById(@PathVariable @NotNull @Positive Long id) {
		return ramaisService.FindById(id);
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public RamaisList create(@RequestBody @Valid RamaisList record) {
		return ramaisService.create(record);
	}

	@PutMapping("/{id}")
	public RamaisList Update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid RamaisList record) {
		return ramaisService.Update(id, record);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void Delete(@PathVariable @NotNull @Positive Long id) {
		ramaisService.Delete(id);

	}
}
