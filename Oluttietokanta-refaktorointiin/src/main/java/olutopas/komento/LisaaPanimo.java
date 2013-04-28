package olutopas.komento;

import java.util.Scanner;
import olutopas.model.Brewery;
import olutopas.tietokanta.Datamapper;

public class LisaaPanimo implements Komento {

	private Datamapper datamapper;
	private Scanner scanner;

	public LisaaPanimo(Datamapper datamapper, Scanner scanner) {
		this.datamapper = datamapper;
		this.scanner = scanner;
	}

	@Override
	public void suorita() {
        System.out.print("brewery to add: ");
        String name = scanner.nextLine();
        Brewery brewery = datamapper.findBrewery(name);

        if (brewery != null) {
            System.out.println(name + " already exists!");
            return;
        }

        datamapper.getServer().save(new Brewery(name));
	}
	
}
