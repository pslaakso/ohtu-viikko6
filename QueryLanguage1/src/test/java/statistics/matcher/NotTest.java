package statistics.matcher;

import junit.framework.Assert;
import junit.framework.TestCase;
import statistics.Player;
import statistics.PlayerReaderImpl;
import statistics.Statistics;

public class NotTest extends TestCase {

	private Statistics stats;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		stats = new Statistics(new PlayerReaderImpl("http://nhlstatistics.herokuapp.com/players.txt"));
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Test of matches method, of class Not.
	 */
	public void testMatches() {
		Matcher m = new Not(new HasFewerThan(5, "goals"),
				new HasFewerThan(10, "assists"),
				new PlaysIn("PHI"));

		for (Player player : stats.matches(m)) {
			System.out.println(player);
			Assert.assertTrue("Too few goals", player.getGoals()>=5);
			Assert.assertTrue("Too few assists", player.getAssists()>=10);
			Assert.assertTrue("Wrong team", !player.getTeam().contains("PHI"));
		}
	}

}
