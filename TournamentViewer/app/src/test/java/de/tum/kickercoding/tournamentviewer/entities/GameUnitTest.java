package de.tum.kickercoding.tournamentviewer.entities;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GameUnitTest {

	List<Player> players2on2;
	List<Player> players1on1;

	@Before
	public void preparePlayerList() {
		players2on2 = Arrays.asList(new Player("p1"), new Player("p2"), new Player("p3"), new Player("p4"));
		players1on1 = Arrays.asList(new Player("p1"), new Player("p2"));
	}

	@Test
	public void test2on2Getters() {
		Game game = new Game(players2on2);
		assertEquals(false, game.isOneOnOne());

		List<Player> team1 = game.getTeam1();
		assertEquals(2, team1.size());
		List<String> team1Names = game.getTeam1PlayerNames();
		assertEquals("p1", team1Names.get(0));
		assertEquals("p2", team1Names.get(1));

		List<Player> team2 = game.getTeam2();
		assertEquals(2, team2.size());
		List<String> team2Names = game.getTeam2PlayerNames();
		assertEquals("p3", team2Names.get(0));
		assertEquals("p4", team2Names.get(1));

		game.setScoreTeam1(2);
		game.setScoreTeam2(4);
		assertEquals(2, game.getScoreTeam1());
		assertEquals(4, game.getScoreTeam2());
	}

	@Test
	public void test1on1Getters() {
		Game game = new Game(players1on1);
		assertEquals(true, game.isOneOnOne());

		List<Player> team1 = game.getTeam1();
		assertEquals(1, team1.size());
		List<String> team1Names = game.getTeam1PlayerNames();
		assertEquals("p1", team1Names.get(0));

		List<Player> team2 = game.getTeam2();
		assertEquals(1, team2.size());
		List<String> team2Names = game.getTeam2PlayerNames();
		assertEquals("p2", team2Names.get(0));

		game.setScoreTeam1(4);
		game.setScoreTeam2(2);
		assertEquals(4, game.getScoreTeam1());
		assertEquals(2, game.getScoreTeam2());
	}

	@Test
	public void testToAndFromJson() {
		List<Player> players = Arrays.asList(new Player("p1"), new Player("p2"), new Player("p3"), new Player
				("p4"));
		Game game = new Game(players);
		game.setScoreTeam1(5);
		game.setScoreTeam2(7);
		game.setFinished(true);
		game.setResultCommitted(false);
		String gameAsJson = game.toJson();
		Game gameFromJson = Game.fromJson(gameAsJson);
		assertEquals(game.getScoreTeam1(), gameFromJson.getScoreTeam1());
		assertEquals(game.getScoreTeam2(), gameFromJson.getScoreTeam2());
		assertEquals(game.isFinished(), gameFromJson.isFinished());
		assertEquals(game.isResultCommitted(), gameFromJson.isResultCommitted());
		// use .equals instead of object reference via assertEquals (only checks name equality though)
		assertTrue(game.getTeam1().get(0).equals(game.getTeam1().get(0)));
		assertTrue(game.getTeam1().get(1).equals(game.getTeam1().get(1)));
		assertTrue(game.getTeam2().get(0).equals(game.getTeam2().get(0)));
		assertTrue(game.getTeam2().get(1).equals(game.getTeam2().get(1)));
	}
}
