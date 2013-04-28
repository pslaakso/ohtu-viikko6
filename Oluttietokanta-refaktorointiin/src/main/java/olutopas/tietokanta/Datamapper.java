package olutopas.tietokanta;

import com.avaje.ebean.EbeanServer;
import java.util.List;
import olutopas.model.Beer;
import olutopas.model.Brewery;
import olutopas.model.Rating;
import olutopas.model.User;

public interface Datamapper {

	public Brewery brewerywithName(String n);

	public Beer beerWithName(String name);

//	public void saveRating(Rating rating);

	public void addBeer(Beer beer);

//	public void saveBrewery(Brewery brewery);

	public void save(Object o);

	public List<Beer> listBeers();

	public List<User> listUsers();

	public Brewery findBrewery(String name);

	public void saveBrewery(Brewery brewery);

	User getCurrentUser();
    void setCurrentUser(User user);

	public EbeanServer getServer();
}
