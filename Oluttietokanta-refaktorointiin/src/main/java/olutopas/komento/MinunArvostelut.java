package olutopas.komento;

import olutopas.model.Rating;
import olutopas.tietokanta.Datamapper;

public class MinunArvostelut implements Komento {

	private Datamapper datamapper;

	public MinunArvostelut(Datamapper datamapper) {
		this.datamapper = datamapper;
	}

	@Override
	public void suorita() {
        System.out.println("Ratings by " + datamapper.getCurrentUser().getName());
        for (Rating rating : datamapper.getCurrentUser().getRatings()) {
            System.out.println(rating);
        }
	}
	
}
