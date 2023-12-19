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
import br.com.rsds.crudspringcisco3905.persistfilexml.StartCreatePersistFile;
import br.com.rsds.crudspringcisco3905.serice.RamaisService;
import io.swagger.v3.oas.annotations.Operation;
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

	@Operation(tags = "/api/ramais-list", summary = "Listar ou filtrar através de uma string", description = "Metodo que retorna uma Page. Se passado uma string no searchTerm, o método retorna uma page com filtro de panação correspodente a string passada no parametro, se não for passado uma string no searchTerm o método retorna uma page com todos os ramais. Caso os parametros page e size não sejam passados, por defull o parametro page sera igual a 0 e o size igual a 10.")
	@GetMapping
	public @ResponseBody PageDTO list(@RequestParam(required = false) String searchTerm,
			@RequestParam(defaultValue = "0") @PositiveOrZero int page,
			@RequestParam(defaultValue = "10") @Positive @Max(25) int size) {
		return ramaisService.list(searchTerm, page, size);
	}

	@Operation(tags = "/api/ramais-list", summary = "Busca pelo ID", description = "Metodo que busca o ramal pelo ID. O ID é obrigatório e deve ser um número positivo maior que 0.")
	@GetMapping("/{id}")
	public RamaisDTO findById(@PathVariable @NotNull @Positive Long id) {
		return ramaisService.findById(id);
	}

	@Operation(tags = "/api/ramais-list", summary = "Criar", description = "Metodo que cria um ramal")
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public RamaisDTO create(@RequestBody @Valid RamaisDTO record) throws IOException {
		RamaisDTO ramal = ramaisService.create(record);
		new StartCreatePersistFile().startCreateFile(ramal.serialNumber(), ramal.ipCentral(), ramal.ramal(),
				ramal.passWord());
		return ramal;
	}

	@Operation(tags = "/api/ramais-list", summary = "Editar", description = "Metodo que edita um ramal")
	@PutMapping("/{id}")
	public RamaisDTO Update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid RamaisDTO record) {
		return ramaisService.Update(id, record);
	}

	@Operation(tags = "/api/ramais-list", summary = "Remover", description = "Metodo que remove um ramal")
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void Delete(@PathVariable @NotNull @Positive Long id) {
		ramaisService.Delete(id);
	}
}