package olutopas.komento;

import java.util.Scanner;
import olutopas.model.Brewery;
import olutopas.tietokanta.Datamapper;

public class PoistaPanimo implements Komento {

	private Datamapper datamapper;
	private Scanner scanner;

	public PoistaPanimo(Datamapper datamapper, Scanner scanner) {
		this.datamapper = datamapper;
		this.scanner = scanner;
	}

	@Override
	public void suorita() {
        System.out.print("to which brewery: ");
        String name = scanner.nextLine();
        Brewery brewery = datamapper.getServer().find(Brewery.class).where().like("name", name).findUnique();

        if (brewery == null) {
            System.out.println(name + " does not exist");
            return;
        }

        datamapper.getServer().delete(brewery);

        System.out.println("deleted: " + name);
	}
	
}
