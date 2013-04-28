package statistics.matcher;

import java.util.ArrayList;

public class QueryBuilder {

	private ArrayList<Matcher> matchers;

	public QueryBuilder() {
		this.matchers = new ArrayList<Matcher>();
	}

	private QueryBuilder(ArrayList<Matcher> matchers) {
		this.matchers = matchers;
	}

	public QueryBuilder playsIn(String teamName) {
		matchers.add(new PlaysIn(teamName));
		return new QueryBuilder(matchers);
	}

	public QueryBuilder hasAtLeast(int value, String category) {
		matchers.add(new HasAtLeast(value, category));
		return new QueryBuilder(matchers);
	}

	public QueryBuilder hasFewerThan(int value, String category) {
		matchers.add(new HasFewerThan(value, category));
		return new QueryBuilder(matchers);
	}


	public Matcher oneOf(Matcher... matchers) {
		Matcher[] m = new Matcher[this.matchers.size()];
		for (int i=0; i<this.matchers.size(); i++) {
			m[i] = this.matchers.get(i);
		}
		return new Or(m);
	}

	public Matcher build() {
		Matcher[] m = new Matcher[this.matchers.size()];
		for (int i=0; i<this.matchers.size(); i++) {
			m[i] = this.matchers.get(i);
		}
		return new And(m);
	}

}
