package br.com.rsds.crudspringcisco3905.persistfilexml;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CreatePersistFile {
	public void createPersistFile(String nameFile, String ipCentral, String ramal, String passWord) throws IOException {

		System.out.printf("Iniciando...");

		FileWriter arq = new FileWriter("d:\\" + nameFile + ".cnf.xml");

		PrintWriter gravarArq = new PrintWriter(arq);

		gravarArq.printf(new Xml().xmlContent(), ipCentral, ramal, ramal, ramal, ramal, passWord, ramal);

		arq.close();

		System.out.printf("\n Arquivo " + nameFile + ".cnf.xml foi gravado com sucesso em d:\\");
	}
}
