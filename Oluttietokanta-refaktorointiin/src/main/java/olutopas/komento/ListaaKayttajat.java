package olutopas.komento;

import java.util.List;
import olutopas.model.User;
import olutopas.tietokanta.Datamapper;

public class ListaaKayttajat implements Komento {

	private Datamapper datamapper;

	public ListaaKayttajat(Datamapper datamapper) {
		this.datamapper = datamapper;
	}

	@Override
	public void suorita() {
		List<User> users = datamapper.listUsers();
        for (User user : users) {
            System.out.println(user.getName() + " " + user.getRatings().size() + " ratings");
        }
	}
	
}
