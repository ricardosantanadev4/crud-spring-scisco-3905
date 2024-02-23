package br.com.rsds.crudspringcisco3905.controller;

import java.io.IOException;

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

import br.com.rsds.crudspringcisco3905.dto.PageDTO;
import br.com.rsds.crudspringcisco3905.dto.RamaisDTO;
import br.com.rsds.crudspringcisco3905.service.RamaisService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Validated
@RestController
@RequestMapping("/api/ramais-list")
public class RamaisController {

	private final RamaisService ramaisService;

	public RamaisController(RamaisService ramaisService) {
		this.ramaisService = ramaisService;
	}

	@GetMapping
	public @ResponseBody PageDTO list(@RequestParam(required = false) String searchTerm,
			@RequestParam(defaultValue = "0") @PositiveOrZero int page,
			@RequestParam(defaultValue = "10") @Positive @Max(25) int size) {
		return this.ramaisService.list(searchTerm, page, size);
	}

	@GetMapping("/{id}")
	public RamaisDTO findById(@PathVariable @NotNull @Positive Long id) {
		return this.ramaisService.findById(id);
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public RamaisDTO create(@RequestBody @Valid RamaisDTO ramaisDTO) throws IOException {
		return this.ramaisService.create(ramaisDTO);
	}

	@PutMapping("/{id}")
	public RamaisDTO Update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid RamaisDTO ramaisDTO) {
		return this.ramaisService.Update(id, ramaisDTO);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void Delete(@PathVariable @NotNull @Positive Long id) {
		this.ramaisService.Delete(id);
	}
}