package statistics.matcher;

import junit.framework.Assert;
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

	/**
	 * Test of playsIn method, of class QueryBuilder.
	 */
	public void testPlaysIn() {
//		System.out.println("playsIn");
//		String teamName = "";
//		QueryBuilder instance = new QueryBuilder();
//		QueryBuilder expResult = null;
//		QueryBuilder result = instance.playsIn(teamName);
//		assertEquals(expResult, result);
//		// TODO review the generated test code and remove the default call to fail.
//		fail("The test case is a prototype.");
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

	/**
	 * Test of hasAtLeast method, of class QueryBuilder.
	 */
	public void testHasAtLeast() {
//		System.out.println("hasAtLeast");
//		int value = 0;
//		String category = "";
//		QueryBuilder instance = new QueryBuilder();
//		QueryBuilder expResult = null;
//		QueryBuilder result = instance.hasAtLeast(value, category);
//		assertEquals(expResult, result);
//		// TODO review the generated test code and remove the default call to fail.
//		fail("The test case is a prototype.");
	}

	/**
	 * Test of hasFewerThan method, of class QueryBuilder.
	 */
	public void testHasFewerThan() {
//		System.out.println("hasFewerThan");
//		int value = 0;
//		String category = "";
//		QueryBuilder instance = new QueryBuilder();
//		QueryBuilder expResult = null;
//		QueryBuilder result = instance.hasFewerThan(value, category);
//		assertEquals(expResult, result);
//		// TODO review the generated test code and remove the default call to fail.
//		fail("The test case is a prototype.");
	}

	/**
	 * Test of build method, of class QueryBuilder.
	 */
	public void testBuild() {
//		System.out.println("build");
//		QueryBuilder instance = new QueryBuilder();
//		Matcher expResult = null;
//		Matcher result = instance.build();
//		assertEquals(expResult, result);
//		// TODO review the generated test code and remove the default call to fail.
//		fail("The test case is a prototype.");
	}
}
