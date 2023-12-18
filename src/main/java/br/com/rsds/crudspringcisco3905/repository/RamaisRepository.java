package br.com.rsds.crudspringcisco3905.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.rsds.crudspringcisco3905.model.RamaisList;

@Repository
public interface RamaisRepository extends JpaRepository<RamaisList, Long> {

	@Query("FROM RamaisList c "
			+ "WHERE LOWER(c.ramal) like %:searchTerm% OR LOWER(c.serialNumber) like %:searchTerm% OR LOWER(c.passWord) like %:searchTerm% "
			+ "OR LOWER(c.ipCentral) like %:searchTerm% OR LOWER(c.status) like %:searchTerm%")
	Page<RamaisList> search(@Param("searchTerm") String searchTerm, Pageable pageable);
}
