package br.com.rsds.crudspringcisco3905.persistfilexml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.stereotype.Component;

@Component
public class FileManipulator {

	private final Xml xml;
	
	public FileManipulator(Xml xml) {
		this.xml = xml;
	}

	public void fileCreator(String newFileName, String ipCentral, String ramal, String passWord) throws IOException {

		System.out.printf("\n Iniciando...");

		FileWriter arq = new FileWriter("D:\\" + newFileName + ".cnf.xml");

		PrintWriter gravarArq = new PrintWriter(arq);

		gravarArq.printf(new Xml().xmlContent(), ipCentral, ramal, ramal, ramal, ramal, passWord, ramal);

		arq.close();

		System.out.printf("\n Arquivo " + newFileName + ".cnf.xml gerado em D:\\");
	}

	public void fileEditor(String newFileName, String ipCentral, String ramal, String passWord, String oldFileName)
			throws IOException {

		System.out.printf("\n Iniciando...");

		FileWriter arq = new FileWriter("D:\\" + newFileName + ".cnf.xml");

		PrintWriter gravarArq = new PrintWriter(arq);

		gravarArq.printf(xml.xmlContent(), ipCentral, ramal, ramal, ramal, ramal, passWord, ramal);

		arq.close();

		System.out.printf("\n Gerada uma nova atualização do arquivo " + oldFileName + ".cnf.xml Old" + " em D:\\"
				+ "\n Novo arquivo " + newFileName + ".cnf.xml" + "\n Iniciando a remoção do arquivo anterior " + oldFileName + ".cnf.xml"
				+ " Old ....");

		this.fileDelete(oldFileName);

	}

	public void fileDelete(String nameFile) {

		File file = new File("D:\\" + nameFile + ".cnf.xml");
		file.delete();

		System.out.printf("\n Remoção do arquivo " + nameFile + ".cnf.xml concluida caminho D:\\");
	}
}
