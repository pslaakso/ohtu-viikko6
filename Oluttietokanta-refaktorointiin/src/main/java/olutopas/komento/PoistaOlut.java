package olutopas.komento;

import java.util.Scanner;
import olutopas.model.Beer;
import olutopas.tietokanta.Datamapper;

public class PoistaOlut implements Komento {

	private Datamapper datamapper;
	private Scanner scanner;

	public PoistaOlut(Datamapper datamapper, Scanner scanner) {
		this.datamapper = datamapper;
		this.scanner = scanner;
	}

	@Override
	public void suorita() {
        System.out.print("beer to delete: ");
        String n = scanner.nextLine();
        Beer beerToDelete = datamapper.getServer().find(Beer.class).where().like("name", n).findUnique();

        if (beerToDelete == null) {
            System.out.println(n + " not found");
            return;
        }

        datamapper.getServer().delete(beerToDelete);
        System.out.println("deleted: " + beerToDelete);
	}
	
}
