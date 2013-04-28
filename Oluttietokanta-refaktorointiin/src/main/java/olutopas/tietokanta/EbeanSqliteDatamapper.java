package olutopas.tietokanta;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.EbeanServerFactory;
import com.avaje.ebean.Transaction;
import com.avaje.ebean.config.DataSourceConfig;
import com.avaje.ebean.config.ServerConfig;
import com.avaje.ebean.config.dbplatform.SQLitePlatform;
import java.util.List;
import olutopas.model.Beer;
import olutopas.model.Brewery;
import olutopas.model.Rating;
import olutopas.model.User;

public class EbeanSqliteDatamapper implements Datamapper {

	private Class[] luokat;
	private EbeanServer server;
	private String tietokantaUrl;

	private boolean dropAndCreate;

	private User currentUser;

	public EbeanSqliteDatamapper(String tietokantaUrl, boolean dropAndCreate, Class... luokat) {
		this.luokat = luokat;
		this.dropAndCreate = dropAndCreate;
		this.tietokantaUrl = tietokantaUrl;
		init();
	}

	public void init() {
		ServerConfig config = new ServerConfig();
		config.setName("beerDb");
		DataSourceConfig sqLite = new DataSourceConfig();
		// loput konfiguraatiosta
		sqLite.setDriver("org.sqlite.JDBC");
		sqLite.setUsername("mluukkai");
		sqLite.setPassword("mluukkai");
		sqLite.setUrl("jdbc:sqlite:beer.db");
		config.setDataSourceConfig(sqLite);
		config.setDatabasePlatform(new SQLitePlatform());
		config.getDataSourceConfig().setIsolationLevel(Transaction.READ_UNCOMMITTED);
		
		config.setDdlGenerate(dropAndCreate);
		config.setDdlRun(dropAndCreate);

		// konstruktorin parametrina annettavat hallinnoitavat luokat lisätään seuraavasti
		for (Class luokka : luokat) {
			config.addClass(luokka);
		}

		server = EbeanServerFactory.create(config);
	}



	@Override
	public Brewery brewerywithName(String n) {
		return server.find(Brewery.class).where().like("name", n).findUnique();
	}



	// apumetodi, jonka avulla Application-olio pääsee aluksi käsiksi EbeanServer-olioon
	@Override
	public EbeanServer getServer() {
		return server;
	}

	@Override
	public Beer beerWithName(String name) {
		return server.find(Beer.class).where().like("name", name).findUnique();
	}

//	@Override
//	public void saveRating(Rating rating) {
//		server.save(rating);
//	}

	@Override
	public User getCurrentUser() {
		return this.currentUser;
	}

	@Override
	public void setCurrentUser(User user) {
		this.currentUser = user;
	}

	@Override
	public void addBeer(Beer beer) {
		server.save(beer);
	}

//	@Override
//	public void saveBrewery(Brewery brewery) {
//		server.save(brewery);
//	}

	@Override
	public void save(Object o) {
		server.save(o);
	}

	@Override
	public List<Beer> listBeers() {
		return server.find(Beer.class).orderBy("brewery.name").findList();
	}

	@Override
	public List<User> listUsers() {
		return server.find(User.class).findList();
	}

	@Override
	public Brewery findBrewery(String name) {
		return server.find(Brewery.class).where().like("name", name).findUnique();
	}

	@Override
	public void saveBrewery(Brewery brewery) {
		server.save(brewery);
	}

}
