package olutopas.komento;

import java.util.List;
import olutopas.model.Brewery;
import olutopas.tietokanta.Datamapper;

public class ListaaPanimot implements Komento {

	private Datamapper datamapper;

	public ListaaPanimot(Datamapper datamapper) {
		this.datamapper = datamapper;
	}

	@Override
	public void suorita() {
        List<Brewery> breweries = datamapper.getServer().find(Brewery.class).findList();
        for (Brewery brewery : breweries) {
            System.out.println(brewery);
        }
	}
	
}
