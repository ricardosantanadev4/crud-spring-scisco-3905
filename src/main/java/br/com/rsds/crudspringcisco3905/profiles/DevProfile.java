package br.com.rsds.crudspringcisco3905.profiles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.rsds.crudspringcisco3905.persistfilexml.FileManipulator;

@Profile(value = "dev")
@Configuration
public class DevProfile {
	
	@Autowired
	FileManipulator fileManipulator;
	
	@Bean
	public Boolean tftpPath() {
		System.out.println(" >>>>>>> @Profile(value = dev) ");
		return fileManipulator.tftpPath("/var/lib/tftpboot/");
	}

}
