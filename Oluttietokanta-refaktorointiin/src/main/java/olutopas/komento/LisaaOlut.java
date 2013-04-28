package olutopas.komento;

import java.util.Scanner;
import olutopas.model.Beer;
import olutopas.model.Brewery;
import olutopas.tietokanta.Datamapper;

public class LisaaOlut implements Komento {

	private Scanner scanner;
	private Datamapper datamapper;

	public LisaaOlut(Scanner scanner, Datamapper datamapper) {
		this.scanner = scanner;
		this.datamapper = datamapper;
	}

	@Override
	public void suorita() {
		System.out.print("to which brewery: ");
		String name = scanner.nextLine();
//		Brewery brewery = server.find(Brewery.class).where().like("name", name).findUnique();
		Brewery brewery = datamapper.brewerywithName(name);

		if (brewery == null) {
			System.out.println(name + " does not exist");
			return;
		}

		System.out.print("beer to add: ");

		name = scanner.nextLine();

//		Beer exists = server.find(Beer.class).where().like("name", name).findUnique();
		Beer exists = datamapper.beerWithName(name);
		if (exists != null) {
			System.out.println(name + " exists already");
			return;
		}

		brewery.addBeer(new Beer(name));
//		server.save(brewery);
		datamapper.save(brewery);
		System.out.println(name + " added to " + brewery.getName());
	}
}
