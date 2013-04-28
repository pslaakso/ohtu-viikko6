package statistics;

import statistics.matcher.*;

public class Main {
    public static void main(String[] args) {
        Statistics stats = new Statistics(new PlayerReaderImpl("http://nhlstatistics.herokuapp.com/players.txt"));
          
        Matcher m = new And( new HasAtLeast(10, "goals"),
                             new HasAtLeast(10, "assists"),
                             new PlaysIn("PHI")
        );
        
        for (Player player : stats.matches(m)) {
            System.out.println( player );
        }

		System.out.println("---------------------- Builder ----------------------");

		QueryBuilder query = new QueryBuilder();
		Matcher matcher = query.playsIn("NYR")
                     .hasAtLeast(10, "goals")
                     .hasFewerThan(25, "assists").build();

		for (Player player : stats.matches(matcher)) {
			System.out.println(player);
		}
	}
}
