package com.game.risk.core.strategy.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.game.risk.RiskGamePhases;
import com.game.risk.core.CountriesGraph;
import com.game.risk.core.util.LoggingUtil;
import com.game.risk.model.Country;
import com.game.risk.model.Player;

/**
 * The Class AgressivePlayerStrategyTest.
 *
 * @author shubhangi_sheel
 * @author sohrab_singh
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ LoggingUtil.class, AgressivePlayerStrategy.class })
public class AgressivePlayerStrategyTest {

	/** Member variables for test class. */
	private AgressivePlayerStrategy agressivePlayerStrategy;

	/** The country 1. */
	private Country country1;

	/** The country 2. */
	private Country country2;

	/** The country 3. */
	private Country country3;

	/** The country 4. */
	private Country country4;

	/** The country 5. */
	private Country country5;

	/** The second strongest. */
	private Country secondStrongest;

	/** The risk game phases. */
	private RiskGamePhases riskGamePhases;

	/** The player. */
	Player player;

	/** The player 2. */
	Player player2;

	/** The countries graph. */
	private CountriesGraph countriesGraph;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		List<Player> players = new ArrayList<>();
		player = new Player();
		player2 = new Player();
		country1 = new Country("India");
		country2 = new Country("Canada");
		country3 = new Country("US");
		country4 = new Country("Pakistan");
		country5 = new Country("Nepal");
		country1.setCurrentNumberOfArmies(3);
		country2.setCurrentNumberOfArmies(6);
		country3.setCurrentNumberOfArmies(9);
		country4.setCurrentNumberOfArmies(7);
		country5.setCurrentNumberOfArmies(11);
		country1.setPlayerName("Sohrab");
		country2.setPlayerName("Sohrab");
		country3.setPlayerName("Sohrab");
		country4.setPlayerName("Sarthak");
		country5.setPlayerName("Sarthak");
		player.setPlayerName("Sohrab");
		player2.setPlayerName("Sarthak");
		player.addCountry(country1);
		player.addCountry(country2);
		player.addCountry(country3);
		player2.addCountry(country4);
		player2.addCountry(country5);
		players.add(player);
		players.add(player2);
		countriesGraph = new CountriesGraph();
		LinkedList<Country> countries = new LinkedList<>();
		countries.add(country4);
		countries.add(country2);
		countries.add(country5);
		HashMap<Country, LinkedList<Country>> adjacencyListHashMap = new HashMap<>();
		adjacencyListHashMap.put(country1, countries);
		countriesGraph.setAdjListHashMap(adjacencyListHashMap);
		riskGamePhases = new RiskGamePhases(null);
		riskGamePhases = mock(RiskGamePhases.class);
		when(riskGamePhases.getPlayerList()).thenReturn(players);
		agressivePlayerStrategy = new AgressivePlayerStrategy(player, countriesGraph, riskGamePhases);
		secondStrongest = countriesGraph.getAdjListHashMap().get(country1).get(0);
		PowerMockito.mockStatic(LoggingUtil.class);
		PowerMockito.doNothing().when(LoggingUtil.class);
	}

	/**
	 * Method to test reinforce.
	 */
	@Test
	public void testReinforce() {
		agressivePlayerStrategy.reinforce();
		assertEquals(0, player.getNumberOfArmies());
	}

	/**
	 * Method to test attack.
	 */
	//@Test
	public void testAttack() {
		agressivePlayerStrategy.attack();

	}

	/**
	 * Method to test fortification which sets the number of armies to less than 1.
	 */
	//@Test
	public void testFortify() {
		secondStrongest.setCurrentNumberOfArmies(5);
		agressivePlayerStrategy.fortify();
		System.out.println(secondStrongest.getCurrentNumberOfArmies());
		assertEquals(4, secondStrongest.getCurrentNumberOfArmies());

	}

}
