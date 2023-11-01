package br.com.rsds.crudspringcisco3905;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.rsds.crudspringcisco3905.model.RamaisList;
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
			ramal.setRamal("9999");
			ramal.setPassWord("4p0nt3");
			ramal.setSerialNumber("SEP2C0BE90579C4");
			ramal.setIpCentral("192.168.0.230");
			ramaisRepository.save(ramal);
		};
	}

}
