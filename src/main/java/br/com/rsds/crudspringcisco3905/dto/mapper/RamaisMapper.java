package br.com.rsds.crudspringcisco3905.dto.mapper;

import org.springframework.stereotype.Component;

import br.com.rsds.crudspringcisco3905.dto.RamaisDTO;
import br.com.rsds.crudspringcisco3905.model.RamaisList;

@Component
public class RamaisMapper {
	public RamaisDTO toDto(RamaisList ramaisList) {
		if (ramaisList == null) {
			return null;
		}

		return new RamaisDTO(ramaisList.getId(), ramaisList.getRamal(), ramaisList.getSerialNumber(),
				ramaisList.getPassWord(), ramaisList.getIpCentral(), ramaisList.getStatus().getValue());
	}

	public RamaisList toEntity(RamaisDTO ramaisDTO) {
		if (ramaisDTO == null) {
			return null;
		}

		RamaisList ramaisList = new RamaisList();

		if (ramaisDTO.id() != null) {
			ramaisList.setId(ramaisDTO.id());
		}

		ramaisList.setRamal(ramaisDTO.ramal());
		ramaisList.setSerialNumber(ramaisDTO.serialNumber());
		ramaisList.setPassWord(ramaisDTO.passWord());
		ramaisList.setIpCentral(ramaisDTO.ipCentral());
		ramaisList.setStatus(ramaisList.getStatus());
		return ramaisList;
	}
}