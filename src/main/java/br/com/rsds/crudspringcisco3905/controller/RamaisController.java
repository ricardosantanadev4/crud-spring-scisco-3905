package br.com.rsds.crudspringcisco3905.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rsds.crudspringcisco3905.model.RamaisList;
import br.com.rsds.crudspringcisco3905.repository.RamaisRepository;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/ramais-list")
@AllArgsConstructor
public class RamaisController {

	RamaisRepository ramaisRepository;

	@GetMapping
	public List<RamaisList> list() {
		List<RamaisList> ramal = ramaisRepository.findAll();
		return ramal;
	}
}
