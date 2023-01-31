package br.com.rsds.crudspringcisco3905.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "RAMAISLIST")
@Data
public class RamaisList {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "RAMAL", nullable = false, length = 4)
	private String ramal;
	@Column(name = "SERIALNUMBER", nullable = false, length = 11)
	private String serialNumber;
	@Column(name = "IPCENTRAL", nullable = false, length = 15)
	private String ipCentral;
	@Column(name = "STATUS", nullable = false, length = 12)
	private String status = RamalStatus.INDIPONIVEL.toString();
}
