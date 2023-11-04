package br.com.rsds.crudspringcisco3905.controller;

import java.io.IOException;
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

import br.com.rsds.crudspringcisco3905.dto.RamaisDTO;
import br.com.rsds.crudspringcisco3905.persistfilexml.StartCreatePersistFile;
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
	public @ResponseBody List<RamaisDTO> list(@RequestParam(required = false) String serial) {
		return ramaisService.list(serial);
	}

	@GetMapping("/{id}")
	public RamaisDTO FindById(@PathVariable @NotNull @Positive Long id) {
		return ramaisService.FindById(id);
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public RamaisDTO create(@RequestBody @Valid RamaisDTO record) throws IOException {
		RamaisDTO ramal = ramaisService.create(record);
		new StartCreatePersistFile().startCreateFile(ramal.serialNumber(), ramal.ipCentral(), ramal.ramal(),
				ramal.passWord());
		return ramal;
	}

	@PutMapping("/{id}")
	public RamaisDTO Update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid RamaisDTO record) {
		return ramaisService.Update(id, record);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void Delete(@PathVariable @NotNull @Positive Long id) {
		ramaisService.Delete(id);
	}
}