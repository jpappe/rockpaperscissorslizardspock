package com.jpappe.rockpaperscissors.framework;

/**
 * Defines the ways that the game interacts with players
 * 
 * @author jacob
 * 
 */
public interface Player {

	/**
	 * Ask the player for the Hand they want to play.
	 * 
	 * @return hand
	 */
	Hand getHand();

	/**
	 * Inform the user of the outcome of a round (i.e., who won, what hands were played, etc.).
	 * 
	 * @param outcome
	 */
	void reportOutcome( RoundOutcome outcome );
}
