package br.com.rsds.crudspringcisco3905.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rsds.crudspringcisco3905.model.RamaisList;

public interface RamaisRepository extends JpaRepository<RamaisList, Long>{
	
}
