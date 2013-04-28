package statistics.matcher;

import junit.framework.Assert;
import static junit.framework.Assert.assertTrue;
import junit.framework.TestCase;
import statistics.Player;
import statistics.PlayerReaderImpl;
import statistics.Statistics;

public class QueryBuilderTest extends TestCase {

	private Statistics stats;
	private Matcher oldStyleMatcher;
	private Matcher builtMatcher;

	public QueryBuilderTest(String testName) {
		super(testName);
        stats = new Statistics(new PlayerReaderImpl("http://nhlstatistics.herokuapp.com/players.txt"));
		oldStyleMatcher = new And( new HasAtLeast(10, "goals"),
                             new HasAtLeast(10, "assists"),
                             new PlaysIn("PHI")
        );

		QueryBuilder query = new QueryBuilder();
		builtMatcher = query.hasAtLeast(10, "goals").
				hasAtLeast(10, "assists").
				playsIn("PHI").build();
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testQueryBuilder() {
		StringBuilder sb1 = new StringBuilder();
		for (Player player : stats.matches(oldStyleMatcher)) {
            sb1.append(player.toString());
        }

		StringBuilder sb2 = new StringBuilder();
		for (Player player : stats.matches(builtMatcher)) {
			sb2.append(player.toString());
		}
		Assert.assertTrue(sb1.toString().equals(sb2.toString()));
	}

	public void testOneOf() {
		QueryBuilder query = new QueryBuilder();
		Matcher m1 = query.playsIn("PHI")
				.hasAtLeast(10, "goals")
				.hasFewerThan(15, "assists").build();
		for (Player player : stats.matches(m1)) {
			assertTrue("matcher1", player.getGoals()>=10 && player.getAssists()<15 && player.getTeam().contains("PHI"));
		}
		QueryBuilder q2 = new QueryBuilder();
		Matcher m2 = q2.playsIn("EDM")
                  .hasAtLeast(50, "points").build();
		for (Player player : stats.matches(m2)) {
			Assert.assertTrue("matcher2", player.getPoints()>=50 && player.getTeam().contains("EDM"));
		}

		QueryBuilder q3 = new QueryBuilder();
		Matcher m = q3.oneOf(m1, m2);
		for (Player player : stats.matches(m)) {
			boolean b1 = player.getGoals()>=10 && player.getAssists()<15 && player.getTeam().contains("PHI");
//			Assert.assertTrue("b1", b1);
			boolean b2 = player.getPoints()>=50 && player.getTeam().contains("EDM");
//			Assert.assertTrue("b2",b2);

			System.out.println(player);
			Assert.assertTrue(b1 || b2);
		}
	}

}
