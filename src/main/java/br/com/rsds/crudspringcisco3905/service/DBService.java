package br.com.rsds.crudspringcisco3905.service;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import br.com.rsds.crudspringcisco3905.model.RamaisList;
import br.com.rsds.crudspringcisco3905.repository.RamaisRepository;

@Service
public class DBService {

	private final RamaisRepository ramaisRepository;

	public DBService(RamaisRepository ramaisRepository) {
		this.ramaisRepository = ramaisRepository;
	}

	public void dbService() {
		RamaisList ramaisList1 = new RamaisList();
		ramaisList1.setRamal("6001");
		ramaisList1.setPassWord("R1c4rd1");
		ramaisList1.setSerialNumber("LKFCDERTZTF6001");
		ramaisList1.setIpCentral("192.168.169.110");

		RamaisList ramaisList2 = new RamaisList();
		ramaisList2.setRamal("6002");
		ramaisList2.setPassWord("R1c4rd2");
		ramaisList2.setSerialNumber("LKFCDERTJTF6002");
		ramaisList2.setIpCentral("192.168.169.112");

		RamaisList ramaisList3 = new RamaisList();
		ramaisList3.setRamal("6003");
		ramaisList3.setPassWord("R1c4rd3");
		ramaisList3.setSerialNumber("LKFCDERTFGF6003");
		ramaisList3.setIpCentral("192.168.169.113");

		RamaisList ramaisList4 = new RamaisList();
		ramaisList4.setRamal("6004");
		ramaisList4.setPassWord("R1c4rd4");
		ramaisList4.setSerialNumber("LKFCDERTFTF6004");
		ramaisList4.setIpCentral("192.168.169.114");

		RamaisList ramaisList5 = new RamaisList();
		ramaisList5.setRamal("6005");
		ramaisList5.setPassWord("R1c4rd5");
		ramaisList5.setSerialNumber("LKFCDERTFTF6005");
		ramaisList5.setIpCentral("192.168.169.110");

		ramaisRepository.saveAll(Arrays.asList(ramaisList1, ramaisList2, ramaisList3, ramaisList4, ramaisList5));

	}

}
