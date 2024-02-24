package br.com.rsds.crudspringcisco3905.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.rsds.crudspringcisco3905.model.RamaisList;

@Repository
public interface RamaisRepository extends JpaRepository<RamaisList, Long> {

	@Query("FROM RamaisList obj WHERE LOWER(obj.ramal) LIKE %?1% OR LOWER(obj.serialNumber) LIKE %?1% "
			+ "OR LOWER(obj.ipCentral) LIKE %?1%" + "OR LOWER(obj.status) like %?1%")
	public Page<RamaisList> search(@Param("searchTerm") String searchTerm, Pageable pageable);

	public Optional<RamaisList> findBySerialNumber(String serialNumber);

	@Query("SELECT obj FROM RamaisList obj WHERE LOWER(obj.ramal) =?1 AND LOWER(obj.ipCentral) =?2")
	public Optional<RamaisList> findByRamal(String ramal, String ipCentral);

	@Query("SELECT obj FROM RamaisList obj WHERE obj.id !=?1 AND LOWER(obj.serialNumber) =?2")
	public Optional<RamaisList> findBySerialNumberToUpdate(Long id, String serialNumber);

	@Query("SELECT obj FROM RamaisList obj WHERE obj.id !=?1 AND LOWER(obj.ramal) =?2 AND LOWER(obj.ipCentral) =?3")
	public Optional<RamaisList> findByRamalToUpdate(Long id, String ramal, String ipCentral);

}