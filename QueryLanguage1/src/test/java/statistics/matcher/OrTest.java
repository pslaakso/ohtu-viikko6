package statistics.matcher;

import junit.framework.Assert;
import junit.framework.TestCase;
import statistics.Player;
import statistics.PlayerReaderImpl;
import statistics.Statistics;

public class OrTest extends TestCase {

	private Statistics stats;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		this.stats = new Statistics(new PlayerReaderImpl("http://nhlstatistics.herokuapp.com/players.txt"));
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Test of matches method, of class Or.
	 */
	public void testMatches() {
		Matcher m = new Or(new HasAtLeast(5, "goals"),
				new HasAtLeast(10, "assists"));
		for (Player player : stats.matches(m)) {
			System.out.println(player);
			Assert.assertTrue(player.getGoals()>=5 || player.getAssists()>=10);
		}
	}
}
