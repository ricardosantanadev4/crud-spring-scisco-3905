package br.com.rsds.crudspringcisco3905;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrudSpringCisco3905Application {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringCisco3905Application.class, args);
	}

//	@Bean
//	CommandLineRunner initDataBase(RamaisRepository ramaisRepository) {
//		return args -> {
//			ramaisRepository.deleteAll();
//			RamaisList ramal = new RamaisList();
//			ramal.setRamal("9999");
//			ramal.setPassWord(null);
//			ramal.setSerialNumber("SEP2C0BE90579C4");
//			ramal.setIpCentral("192.168.0.230");
//			ramal.setStatus(RamalStatus.INDISPONIVEL.toString());
//			ramaisRepository.save(ramal);
//		};
//	}

}
