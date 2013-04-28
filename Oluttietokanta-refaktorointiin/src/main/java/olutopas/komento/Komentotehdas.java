package olutopas.komento;

import java.util.Scanner;
import olutopas.tietokanta.Datamapper;

public class Komentotehdas {

	private Datamapper datamapper;
	private Scanner scanner;

	public Komentotehdas(Datamapper mapper, Scanner scanner) {
		this.datamapper = mapper;
		this.scanner = scanner;
	}

	public Komento hae(String operaatio) {
		if ( operaatio.equals("1") || operaatio.equals("hae panimo")) {
			return new HaePanimo(scanner, datamapper);
		}
		if ( operaatio.equals("2") || operaatio.equals("hae/arvioi olut")) {
			return new HaeJaArvioiOlut(scanner, datamapper);
		}
		if (operaatio.equals("3") || operaatio.equals("lisaa olut")) {
			return new LisaaOlut(scanner, datamapper);
		}
		if (operaatio.equals("4") || operaatio.equals("listaa panimot")) {
			return new ListaaPanimot(datamapper);
		}
		if (operaatio.equals("5") || operaatio.equals("poista olut")) {
			return new PoistaOlut(datamapper, scanner);
		}
		if (operaatio.equals("6") || operaatio.equals("listaa oluet")) {
			return new ListaaOluet(datamapper);
		}
		if (operaatio.equals("7") || operaatio.equals("poista panimo")) {
			return new PoistaPanimo(datamapper, scanner);
		}
		if (operaatio.equals("8") || operaatio.equals("lisaa panimo")) {
			return new LisaaPanimo(datamapper, scanner);
		}
		if (operaatio.equals("9") || operaatio.equals("arvosteluni")) {
			return new MinunArvostelut(datamapper);
		}
		if (operaatio.equals("0") || operaatio.equals("listaa kayttajat")) {
			return new ListaaKayttajat(datamapper);
		}

		if (operaatio.equals("q")) {
			return new Lopeta();
		}
		return new Tuntematon();
	}

}
