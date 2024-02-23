package br.com.rsds.crudspringcisco3905.profiles;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.rsds.crudspringcisco3905.persistfilexml.FileManipulator;
import br.com.rsds.crudspringcisco3905.service.DBService;

@Profile(value = "test")
@Configuration
public class TestProfile {

	private final FileManipulator fileManipulator;
	private final DBService dbService;

	public TestProfile(FileManipulator fileManipulator, DBService dbService) {
		this.fileManipulator = fileManipulator;
		this.dbService = dbService;
	}

	@Bean
	public Boolean tftpPath() {
		System.out.println(" >>>>>>> @Profile(value = test) ");
		return fileManipulator.tftpPath("D:\\");
	}

	@Bean
	public Boolean dbService() {
		this.dbService.dbService();
		return true;
	}
}
