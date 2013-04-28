package olutopas.komento;

import java.util.Scanner;
import olutopas.model.Beer;
import olutopas.model.Brewery;
import olutopas.tietokanta.Datamapper;

public class HaePanimo implements Komento {

	private Scanner scanner;
	private Datamapper datamapper;

	public HaePanimo(Scanner scanner, Datamapper datamapper) {
		this.scanner = scanner;
		this.datamapper = datamapper;
	}

	@Override
	public void suorita() {
        System.out.print("brewery to find: ");
        String n = scanner.nextLine();
        Brewery foundBrewery = datamapper.brewerywithName(n);

        if (foundBrewery == null) {
            System.out.println(n + " not found");
            return;
        }

        System.out.println(foundBrewery);
        for (Beer bier : foundBrewery.getBeers()) {
            System.out.println("   " + bier.getName());
        }
	}
	
}
