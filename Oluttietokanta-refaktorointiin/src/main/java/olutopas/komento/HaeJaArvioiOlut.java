package olutopas.komento;

import java.util.Scanner;
import olutopas.Application;
import olutopas.model.Beer;
import olutopas.model.Rating;
import olutopas.model.User;
import olutopas.tietokanta.Datamapper;

public class HaeJaArvioiOlut implements Komento {

	private Scanner scanner;
	private Datamapper datamapper;
	
	public HaeJaArvioiOlut(Scanner reader, Datamapper datamapper) {
		this.scanner = reader;
		this.datamapper = datamapper;
	}

	@Override
	public void suorita() {
		System.out.print("beer to find: ");
		String n = scanner.nextLine();
//		Beer foundBeer = server.find(Beer.class).where().like("name", n).findUnique();
		Beer foundBeer = datamapper.beerWithName(n);

		if (foundBeer == null) {
			System.out.println(n + " not found");
			return;
		}

		System.out.println(foundBeer);

		if (foundBeer.getRatings() != null && foundBeer.getRatings().size() != 0) {
			System.out.println("  number of ratings: " + foundBeer.getRatings().size() + " average " + foundBeer.averageRating());
		} else {
			System.out.println("no ratings");
		}

		System.out.print("give rating (leave emtpy if not): ");
		try {
			int rating = Integer.parseInt(scanner.nextLine());
			addRating(foundBeer, rating);
		} catch (Exception e) {
		}
	}

	private void addRating(Beer foundBeer, int value) {
		Rating rating = new Rating(foundBeer, datamapper.getCurrentUser(), value);
		datamapper.save(rating);
	}
}
