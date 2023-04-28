package br.com.rsds.crudspringcisco3905;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.rsds.crudspringcisco3905.model.RamaisList;
import br.com.rsds.crudspringcisco3905.model.RamalStatus;
import br.com.rsds.crudspringcisco3905.repository.RamaisRepository;

@SpringBootApplication
public class CrudSpringCisco3905Application {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringCisco3905Application.class, args);
	}

	@Bean
	CommandLineRunner initDataBase(RamaisRepository ramaisRepository) {
		return args -> {
			ramaisRepository.deleteAll();
			RamaisList ramal = new RamaisList();
			ramal.setRamal("6001");
			ramal.setSerialNumber("SEP44ADD9D560B7");
			ramal.setIpCentral("192.168.0.230");
			ramal.setStatus(RamalStatus.INDISPONIVEL.toString());
			ramaisRepository.save(ramal);
		};
	}

}
