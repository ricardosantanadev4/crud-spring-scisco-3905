package br.com.rsds.crudspringcisco3905.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rsds.crudspringcisco3905.model.RamaisList;

@Repository
public interface RamaisRepository extends JpaRepository<RamaisList, Long> {

}
