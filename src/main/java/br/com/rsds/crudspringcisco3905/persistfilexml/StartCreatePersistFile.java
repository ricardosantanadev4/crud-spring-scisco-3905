package br.com.rsds.crudspringcisco3905.persistfilexml;

import java.io.IOException;

public class StartCreatePersistFile {
	public void startCreateFile(String nameFile, String ipCentral, String ramal, String passWord) throws IOException {

		new CreatePersistFile().createPersistFile(nameFile, ipCentral, ramal, passWord);
	}
}
