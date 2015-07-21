package tests.game.ai.shared.player.factory;

import static org.junit.Assert.*;
import  org.hamcrest.Matcher;


import game.ai.shared.player.factory.PairsPlayerFactoryChoices;
import game.ai.shared.player.factory.specification.IPairsPlayerFactory;
import game.ai.shared.player.specification.PairsPlayerSpecification;

import org.junit.Test;

public class TestPairsPlayerFactoryChoices {
	/**
	 * @see {@link PairsPlayerFactoryChoices#getMode()} </br>
	 */
	@Test
	public void testGetMode() {
		int mode = PairsPlayerFactoryChoices.getMode();
		assertTrue(mode < 3);
		assertTrue(mode >= 0);
	}
	/**
	 * @see {@link PairsPlayerFactoryChoices#getFactory()} </br>
	 */
	@Test
	public void testGetFactory(){
		IPairsPlayerFactory factory = PairsPlayerFactoryChoices.getFactory();
		assertNotNull(factory);
	}
	/**
	 * @see {@link PairsPlayerFactoryChoices#getRandomPairsPlayer()} </br>
	 * This method will returns a {@link PairsPlayerSpecification} </br>
	 * Expecting the return value is not null </br>
	 */
	@Test
	public void testGetRandomPairsPlayer(){
		PairsPlayerSpecification pairs = PairsPlayerFactoryChoices.getRandomPairsPlayer();
		assertNotNull(pairs);
	}
}
